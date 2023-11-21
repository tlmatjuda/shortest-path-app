package za.co.discovery.assignment.thabomatjuda.model.planet;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@NotNull( message = "Planet data is required")
public class PlanetModel implements Serializable {

    @NotBlank( message = "PlanetNode is required")
    private String planetNode;

    @NotBlank( message = "PlanetName is required")
    private String planetName;

}