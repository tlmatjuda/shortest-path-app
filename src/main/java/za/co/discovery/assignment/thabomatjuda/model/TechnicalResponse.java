package za.co.discovery.assignment.thabomatjuda.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TechnicalResponse {

    private String info;
    private String error;
    private String description;

    public static TechnicalResponse info(String info, String description) {

        TechnicalResponse technicalResponse = new TechnicalResponse();
        technicalResponse.setInfo( info);
        technicalResponse.setDescription( description);

        return technicalResponse;
    }

    public static TechnicalResponse error(String error, String description) {

        TechnicalResponse technicalResponse = new TechnicalResponse();
        technicalResponse.setError( error);
        technicalResponse.setDescription( description);

        return technicalResponse;
    }


}
