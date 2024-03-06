package com.toob.service.shortest.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;



/**
 * Technical Response mainly used for the REST API.
 * @author : Thabo Matjuda
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TechnicalResponse {

    private String info;
    private String error;
    private String description;

    /**
     * Used to build up the INFO response
     * @param info
     * @param description
     * @return
     */
    public static TechnicalResponse info(String info, String description) {

        TechnicalResponse technicalResponse = new TechnicalResponse();
        technicalResponse.setInfo( info);
        technicalResponse.setDescription( description);

        return technicalResponse;
    }


    /**
     * Used to build up the ERROR response
     * @param error
     * @param description
     * @return
     */
    public static TechnicalResponse error(String error, String description) {

        TechnicalResponse technicalResponse = new TechnicalResponse();
        technicalResponse.setError( error);
        technicalResponse.setDescription( description);

        return technicalResponse;
    }


}
