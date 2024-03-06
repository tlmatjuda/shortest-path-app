package com.toob.service.shortest.mapper;


import com.toob.service.shortest.constants.CommonApplicationConstants;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.model.route.RouteModel;
import org.mapstruct.Mapper;

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
