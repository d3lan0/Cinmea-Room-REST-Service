package cinema.rest.controller;

import cinema.exception.GeneralErrorException;
import cinema.model.Cinema;
import cinema.model.Seat;
import cinema.model.Ticket;
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
    public Ticket purchaseTicket(@RequestBody Map<String, String> payload) throws GeneralErrorException {
        int row = Integer.parseInt(payload.get("row"));
        int column = Integer.parseInt(payload.get("column"));

        if (!areRowAndColValid(row, column)) {
            throw new GeneralErrorException("The number of a row or a column is out of bounds!");

        }
        Seat currentSeat = seatChart[row - 1][column - 1];

        if (currentSeat.isSeatPurchased()) {
            throw new GeneralErrorException("The ticket has been already purchased!");
        }

        currentSeat.purchaseSeat();
        Ticket currentTicket = cinema.addTicket(currentSeat);
        return currentTicket;
    }

    @PostMapping("/return")
    public Ticket returnTicket(@RequestBody Map<String, String> payload) throws GeneralErrorException {
        String uuid = payload.get("token");
        Ticket ticket = cinema.getTicket(uuid);

        if (ticket == null) {
            throw new GeneralErrorException("Wrong token!");
        }

        ticket.getTicket().resetPurchaseValue();
        cinema.refundTicket(ticket, uuid);

        return ticket;
//

    }

    @ExceptionHandler(GeneralErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map> handleSeatSelectionException(GeneralErrorException e) {
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
