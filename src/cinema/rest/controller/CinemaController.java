package cinema.rest.controller;

import cinema.exception.SeatSelectionException;
import cinema.model.Cinema;
import cinema.model.Seat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {
    private final Cinema cinema = new Cinema(9, 9);
    private Seat[][] seatChart = cinema.getseatMap();

    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }

    @PostMapping("/purchase")
    public Seat purchaseSeat(@RequestBody Map<String, String> payload) throws SeatSelectionException {
        int row = Integer.parseInt(payload.get("row"));
        int column = Integer.parseInt(payload.get("column"));
        System.out.println(row + " " + column);
        if (!areRowAndColValid(row, column)) {
            throw new SeatSelectionException("The number of a row or a column is out of bounds!");

        }
        Seat currentSeat = seatChart[row - 1][column - 1];


        if (currentSeat.isSeatPurchased()) {
            throw new SeatSelectionException("The ticket has been already purchased!");
        }

        currentSeat.purchaseSeat();
        return currentSeat;
    }

    @ExceptionHandler(SeatSelectionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map> handleSeatSelectionException(SeatSelectionException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
    }


    private boolean areRowAndColValid(int row, int col) {
        if (row < 1 ||
                row > cinema.getRows() ||
                col < 1 ||
                col > cinema.getColumns()) {
            return false;
        }
        return true;
    }
}
