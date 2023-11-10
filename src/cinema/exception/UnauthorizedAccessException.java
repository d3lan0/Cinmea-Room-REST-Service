package cinema.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnauthorizedAccessException extends Exception {

    public UnauthorizedAccessException() {
        super();

    }


}
