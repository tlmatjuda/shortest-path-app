package za.co.discovery.assignment.thabomatjuda.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

    private String info;
    private String error;
    private String description;

    public static BaseResponse info(String info, String description) {

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setInfo( info);
        baseResponse.setDescription( description);

        return baseResponse;
    }

    public static BaseResponse error(String error, String description) {

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setError( error);
        baseResponse.setDescription( description);

        return baseResponse;
    }


}
