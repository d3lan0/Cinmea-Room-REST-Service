package cinema.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralErrorException extends Exception {
    private String error = "error";

    public GeneralErrorException(String error) {
        super(error);

    }


}
