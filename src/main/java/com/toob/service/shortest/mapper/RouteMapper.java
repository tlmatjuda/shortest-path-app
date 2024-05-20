package com.toob.service.shortest.mapper;


import com.toob.service.shortest.constants.CommonApplicationConstants;
import com.toob.service.shortest.entity.Route;
import com.toob.service.shortest.model.route.RouteMinimalModel;
import com.toob.service.shortest.model.route.RouteModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

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

    public RouteMinimalModel asMinimalModel(Route route) {
        RouteMinimalModel model = new RouteMinimalModel();
        model.setId(route.getId());
        model.setOrigin( route.getOrigin().getNode());
        model.setDestination( route.getDestination().getNode());
        model.setDistance( route.getDistance() );
        return model;
    }

    public List<RouteMinimalModel> asMinimalModel(List<Route> routes) {
        return routes.stream()
                .map(this::asMinimalModel)
                .collect(Collectors.toList());
    }
}
