package za.co.discovery.assignment.thabomatjuda.startup;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import za.co.discovery.assignment.thabomatjuda.constants.ExcelSheetConstants;
import za.co.discovery.assignment.thabomatjuda.constants.PlanetConstants;
import za.co.discovery.assignment.thabomatjuda.constants.RoutesConstants;
import za.co.discovery.assignment.thabomatjuda.constants.SpecialCharacters;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;
import za.co.discovery.assignment.thabomatjuda.entity.Route;
import za.co.discovery.assignment.thabomatjuda.repository.PlanetRepository;
import za.co.discovery.assignment.thabomatjuda.repository.RouteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class SupportDataFileProcessor implements CommandLineRunner {



    @Value("${spp.app.conf.file.suppport-data}")
    private Resource supportExcelFileResource;

    private final PlanetRepository planetRepository;
    private final RouteRepository routeRepository;
    private List<Planet> planetList = new ArrayList<>();
    private List<Route> routeList = new ArrayList<>();
    private DataFormatter dataFormatter = new DataFormatter();

    public SupportDataFileProcessor(PlanetRepository planetRepository, RouteRepository routeRepository) {
        this.planetRepository = planetRepository;
        this.routeRepository = routeRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        // Read the file into apache's POI's Workbook
        Workbook workbook = WorkbookFactory.create(supportExcelFileResource.getFile());
        log.info("Reading Excel File : {} which has {} sheets in total",
                supportExcelFileResource.getFilename(), workbook.getNumberOfSheets());

        // Extract information and then insert into the database.
        extractPlanets(workbook);
        extractRoutes(workbook);
        loadDatabaseWithData();
        workbook.close();
    }

    /**
     * Leverages of the PLANETS & ROUTES lists and inserts them into the database.
     */
    private void loadDatabaseWithData() {
        log.info("Saving the data from file : {} to the database", supportExcelFileResource.getFilename());
        planetRepository.saveAll(getPlanetList());
        routeRepository.saveAll(getRouteList());
        log.info("Content now saved to the database!");
    }

    /**
     * Stream through the PLANETS list and extract one by the given Node.
     *
     * @param planetNodeArg
     * @return
     */
    public Planet getPlanetByNodeFromList(String planetNodeArg) {
        Optional<Planet> foundPlanet = planetList.stream()
                .filter(planet -> planet.getPlanetNode().equals(planetNodeArg)).findFirst();

        if (foundPlanet.isPresent()) {
            return foundPlanet.get();
        }

        return null;
    }


    /**
     * Extracts out the content of the file into a list.
     * This list will be the list of PLANET Entites.
     *
     * @param workbook
     * @return
     */
    private List<Planet> extractPlanets(Workbook workbook) {

        // Get the sheet that contains the Planets info
        log.info("Processing the sheet \"{}\"", ExcelSheetConstants.PLANET_NAMES);
        Sheet planetNamesSheet = workbook.getSheet(ExcelSheetConstants.PLANET_NAMES);

        // Looping through the ROWS of the Sheet.
        planetNamesSheet.forEach(row -> {
            Planet planet = new Planet();

            // Skips columns headers in the sheet.
            if (row.getRowNum() != 0) {

                // Looping through each row's CELLS
                row.forEach(cell -> {
                    int columnIndex = cell.getColumnIndex();

                    if (PlanetConstants.EXCEL_COLUMN_PLANET_NODE == columnIndex)
                        planet.setPlanetNode(dataFormatter.formatCellValue(cell).trim());

                    if (PlanetConstants.EXCEL_COLUMN_PLANET_NAME == columnIndex)
                        planet.setPlanetName(dataFormatter.formatCellValue(cell).trim());
                });

                planetList.add(planet);
            }
        });

        // If we don't have records then something went wrong with reading the excel file.
        if (CollectionUtils.isEmpty(planetList)) {
            throw new RuntimeException("extractPlanets() - No records found while reading the Excel file");
        }

        log.info("Extracted a total of {} PLANETS from the excel file", planetList.size());
        return planetList;
    }

    /**
     * Extracts the Routes from the Excel Sheet.
     * This list will be the list of ROUTES Entites.
     *
     * @param workbook
     * @return
     */
    private List<Route> extractRoutes(Workbook workbook) {

        // Get the sheet that contains the Planets info
        log.info("Processing the sheet \"{}\"", ExcelSheetConstants.ROUTES);
        Sheet routesSheet = workbook.getSheet(ExcelSheetConstants.ROUTES);

        // Looping through the ROWS of the Sheet.
        routesSheet.forEach(row -> {
            Route route = new Route();

            // Skips columns headers in the sheet.
            if (row.getRowNum() != 0) {

                // Now look through each row's CELL and get the info mapped into the Entities.
                row.forEach( cell -> {
                    int columnIndex = cell.getColumnIndex();

                    if (RoutesConstants.EXCEL_COLUMN_ROUTE_ID == columnIndex) {
                        route.setRouteId(Integer.parseInt(dataFormatter.formatCellValue(cell).trim()));
                    }

                    if (RoutesConstants.EXCEL_COLUMN_PLANET_ORIGIN == columnIndex) {
                        String planetOriginKey = dataFormatter.formatCellValue(cell);
                        Planet planetOrigin = getPlanetByNodeFromList(planetOriginKey);
                        route.setPlanetOrigin(planetOrigin);
                    }

                    if (RoutesConstants.EXCEL_COLUMN_PLANET_DESTINATION == columnIndex) {
                        String planetDestinationKey = dataFormatter.formatCellValue(cell);
                        Planet planetDestination = getPlanetByNodeFromList(planetDestinationKey);
                        route.setPlanetDestination(planetDestination);
                    }

                    if (RoutesConstants.EXCEL_COLUMN_PLANET_DISTANCE == columnIndex) {
                        String distanceText = dataFormatter.formatCellValue(cell).trim().replace(SpecialCharacters.COMMA, SpecialCharacters.PERIOD);
                        route.setDistanceInLightYears(Double.parseDouble(distanceText));
                    }

                });

                routeList.add(route);
            }
        });

        // If we don't have records then something went wrong with reading of the excel file.
        if (CollectionUtils.isEmpty(routeList)) {
            throw new RuntimeException("extractRoutes() - No records found while reading the Excel file");
        }

        log.info("Extracted a total of {} ROUTES from the excel file", routeList.size());
        return routeList;
    }

    public List<Planet> getPlanetList() {
        return planetList;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

}
