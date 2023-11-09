package cinema.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticket {
    private String token;
    private Seat ticket;

    public Ticket(Seat ticket) {
        this.token = String.valueOf(UUID.randomUUID());
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    public void clearToken() {
        this.token = null;
    }

}
