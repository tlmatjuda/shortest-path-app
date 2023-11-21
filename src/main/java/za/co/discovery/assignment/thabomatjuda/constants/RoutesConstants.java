package za.co.discovery.assignment.thabomatjuda.constants;


/**
 * Used for EXCEL file Cell Indexes.
 * We also have some error messages for the Route Calcualtion of the Shortest path.
 * @author : Thabo Matjuda
 */
public class RoutesConstants {

    private RoutesConstants() {}

    public static final Integer EXCEL_COLUMN_ROUTE_ID = 0;
    public static final Integer EXCEL_COLUMN_PLANET_ORIGIN = 1;
    public static final Integer EXCEL_COLUMN_PLANET_DESTINATION = 2;
    public static final Integer EXCEL_COLUMN_PLANET_DISTANCE = 3;
    public static final String ORIGIN_NODE = "A";
    public static final String ERROR_DESTINATION_EQUAL_TO_ORIGIN = "Your destination is the same as your origin";
    public static final String ERROR_DESTINATION_NOT_FOUND = "The provided destination is not on the map";
    public static final String ERROR_REQUEST_BODY_NOT_FOUND = "Origin and Destination required, please supply.";
}
