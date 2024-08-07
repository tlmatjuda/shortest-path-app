package com.toob.service.shortest.service;

import com.toob.service.shortest.constants.ExcelSheetConstants;
import com.toob.service.shortest.constants.PlanetConstants;
import com.toob.service.shortest.constants.RoutesConstants;
import com.toob.service.shortest.constants.SpecialCharacters;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.repository.PlanetRepository;
import com.toob.service.shortest.repository.RouteRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Business logic responsible for the Startup process that imports data from the supplied Excel File to the In-Memory Database.
 * @author : Thabo Matjuda
 */
@Slf4j
@Service
public class SupportDataFileService {

    public static final String SPP_APP_CONF_FILE_SUPPORT_DATA = "spp.app.conf.file.support-data";


    private final Environment environment;
    private String supportExcelFileResource;


    @Getter
    private List<Planet> planetList = new ArrayList<>();

    @Getter
    private List<Route> routeList = new ArrayList<>();


    private final PlanetRepository planetRepository;
    private final RouteRepository routeRepository;
    private final DataFormatter dataFormatter = new DataFormatter();


    public SupportDataFileService(PlanetRepository planetRepository, RouteRepository routeRepository, Environment environment) {
        this.planetRepository = planetRepository;
        this.routeRepository = routeRepository;
        this.environment = environment;
        String errorMessage = String.format("%s configurations is required", SPP_APP_CONF_FILE_SUPPORT_DATA);
        this.supportExcelFileResource = Objects.requireNonNull( this.environment.getProperty(SPP_APP_CONF_FILE_SUPPORT_DATA), errorMessage);
    }


    /**
     * Performs the whole process of extracting EXCEL data into the DATABASE.
     * @throws Exception
     */
    public void process() throws Exception {
        // Read the file into apache's POI's Workbook
        ClassPathResource excelFIleResource = new ClassPathResource(supportExcelFileResource);
        Workbook workbook = WorkbookFactory.create(excelFIleResource.getInputStream());
        log.info("Reading Excel File : {} which has {} sheets in total",
                excelFIleResource.getFilename(), workbook.getNumberOfSheets());

        List<Planet> planets = planetRepository.findAll();
        if (CollectionUtils.isEmpty( planets)) {
            extractPlanets(workbook);
        }

        List<Route> routes = routeRepository.findAll();
        if (CollectionUtils.isEmpty( routes)) {
            extractRoutes(workbook);
        }
        workbook.close();
    }

    /**
     * Stream through the PLANETS list and extract one by the given Node.
     *
     * @param planetNodeArg
     * @return
     */
    private Planet getPlanetByNodeFromList(String planetNodeArg) {
        Optional<Planet> foundPlanet = planetList.stream()
                .filter(planet -> planet.getNode().equals(planetNodeArg)).findFirst();

        return foundPlanet.orElse(null);
    }


    /**
     * Extracts out the content of the file into a list.
     * This list will be the list of PLANET Entites.
     *
     * @param workbook
     */
    private void extractPlanets(Workbook workbook) {

        // Get the sheet that contains the Planets info
        log.info("Processing the sheet \"{}\"", ExcelSheetConstants.PLANET_NAMES);
        Sheet planetNamesSheet = workbook.getSheet(ExcelSheetConstants.PLANET_NAMES);

        // Looping through the ROWS of the Sheet.
        planetNamesSheet.forEach(row -> {
            Planet planet = new Planet();

            // Skips columns headers in the sheet.
            if (row.getRowNum() != ExcelSheetConstants.HEADER_INDEX) {

                // Looping through each row's CELLS
                row.forEach(cell -> buildPlaneFromRow(cell, planet));
                planetList.add(planet);
            }
        });

        planetRepository.saveAll(planetList);

        log.info("Extracted a total of {} PLANETS from the excel file", planetList.size());
    }

    /**
     * Extracts the Routes from the Excel Sheet.
     * This list will be the list of ROUTES Entites.
     *
     * @param workbook
     */
    private void extractRoutes(Workbook workbook) {

        // Get the sheet that contains the Planets info
        log.info("Processing the sheet \"{}\"", ExcelSheetConstants.ROUTES);
        Sheet routesSheet = workbook.getSheet(ExcelSheetConstants.ROUTES);

        // Looping through the ROWS of the Sheet.
        routesSheet.forEach(row -> {
            Route route = new Route();

            // Skips columns headers in the sheet.
            if (row.getRowNum() != ExcelSheetConstants.HEADER_INDEX) {

                // Now look through each row's CELL and get the info mapped into the Entities.
                row.forEach( cell -> buildRouteFromRow( cell, route));

                // The nodes must exsits before we can save.
                if (Objects.nonNull( route.getOrigin()) && Objects.nonNull( route.getDestination())) {
                    routeList.add(route);
                }
            }
        });

        routeRepository.saveAll(routeList);

        log.info("Extracted a total of {} ROUTES from the excel file", routeList.size());
    }

    private void buildPlaneFromRow(Cell cell, Planet planet) {
        int columnIndex = cell.getColumnIndex();

        if (PlanetConstants.EXCEL_COLUMN_PLANET_NODE == columnIndex) {
            planet.setNode(dataFormatter.formatCellValue(cell).trim());
        }

        if (PlanetConstants.EXCEL_COLUMN_PLANET_NAME == columnIndex) {
            planet.setName(dataFormatter.formatCellValue(cell).trim());
        }
    }

    /**
     * Uses the HORIZONTAL collection of CELLS which is a ROW.
     * This collection is a ROW in EXCEL.
     * This is then used to build up a Route.
     * @param cell
     * @param route
     */
    private void buildRouteFromRow( Cell cell, Route route) {
        int columnIndex = cell.getColumnIndex();

        if (RoutesConstants.EXCEL_COLUMN_ROUTE_ID == columnIndex) {
            route.setId(Integer.parseInt(dataFormatter.formatCellValue(cell).trim()));
        }

        if (RoutesConstants.EXCEL_COLUMN_PLANET_ORIGIN == columnIndex) {
            String planetOriginKey = dataFormatter.formatCellValue(cell).trim();
            Planet planetOrigin = getPlanetByNodeFromList(planetOriginKey);
            if (Objects.nonNull(planetOrigin)) {
                route.setOrigin(planetOrigin);
            }
        }

        if (RoutesConstants.EXCEL_COLUMN_PLANET_DESTINATION == columnIndex) {
            String planetDestinationKey = dataFormatter.formatCellValue(cell).trim();
            Planet planetDestination = getPlanetByNodeFromList(planetDestinationKey);
            if (Objects.nonNull(planetDestination)) {
                route.setDestination(planetDestination);
            }
        }

        if (RoutesConstants.EXCEL_COLUMN_PLANET_DISTANCE == columnIndex) {
            String distanceText = dataFormatter.formatCellValue(cell).trim().replace(SpecialCharacters.COMMA, SpecialCharacters.PERIOD);
            route.setDistance(Double.parseDouble(distanceText));
        }
    }

}
