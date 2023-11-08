package cinema.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatSelectionException extends Exception {
    private String error = "error";

    public SeatSelectionException(String error) {
        super(error);

    }


}
