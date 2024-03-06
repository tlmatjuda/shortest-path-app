package com.toob.service.shortest.mapper;


import com.toob.service.shortest.constants.CommonApplicationConstants;
import com.toob.service.shortest.entity.Planet;
import com.toob.service.shortest.model.planet.PlanetModel;
import org.mapstruct.Mapper;

import java.util.List;


/**
 * Mapper between our Entities and DTOs for the Planet Domain
 * @author : Thabo Matjuda
 */
@Mapper( componentModel = CommonApplicationConstants.SPRING)
public abstract class PlanetMapper {

    public abstract PlanetModel asModel(Planet planet);
    public abstract Planet asEntity(PlanetModel planetModel);

    public abstract List<PlanetModel> asModel(List<Planet> planet);
    public abstract List<Planet> asEntity(List<PlanetModel> planetModel);

}
