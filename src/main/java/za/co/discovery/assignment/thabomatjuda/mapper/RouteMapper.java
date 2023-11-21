package za.co.discovery.assignment.thabomatjuda.mapper;


import org.mapstruct.Mapper;
import za.co.discovery.assignment.thabomatjuda.constants.CommonApplicationConstants;
import za.co.discovery.assignment.thabomatjuda.entity.Route;
import za.co.discovery.assignment.thabomatjuda.model.route.RouteModel;

import java.util.List;

/**
 * Mapper between our Entities and DTOs for the Route Domain
 * @author : Thabo Matjuda
 */
@Mapper( componentModel = CommonApplicationConstants.SPRING)
public abstract class RouteMapper {

    public abstract RouteModel asModel(Route route);
    public abstract Route asEntity(RouteModel routeModel);
    public abstract List<RouteModel> asModel(List<Route> routes);
    public abstract List<Route> asEntity(List<RouteModel> routeModels);

}
