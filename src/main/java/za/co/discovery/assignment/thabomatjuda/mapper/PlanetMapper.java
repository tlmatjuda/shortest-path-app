package za.co.discovery.assignment.thabomatjuda.mapper;


import org.mapstruct.Mapper;
import za.co.discovery.assignment.thabomatjuda.constants.CommonApplicationConstants;
import za.co.discovery.assignment.thabomatjuda.entity.Planet;
import za.co.discovery.assignment.thabomatjuda.model.planet.PlanetModel;

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
