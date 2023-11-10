package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(value = {"tickets"})
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Statistics {
    private int income;
    private int available;
    private int purchased;

    private List<Ticket> tickets;

    public Statistics(List<Ticket> tickets, int size) {
        setTickets(tickets);
        setIncome();
        setAvailable(size);
        setPurchased();


    }

    public int getIncome() {
        return income;
    }

    public int getAvailable() {
        return available;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setIncome() {
        if(tickets.isEmpty()) {
            this.income = 0;
        }

        this.income = tickets.stream()
                .mapToInt(e -> e.getTicket()
                        .getPrice())
                .sum();
    }

    private void setAvailable(int size) {
        this.available = tickets.isEmpty() ? size : size - tickets.size();
    }

    private void setPurchased() {
        this.purchased = tickets.isEmpty() ? 0 :this.tickets.size();
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    private void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void updateStats(List<Ticket> tickets, int size) {
        setTickets(tickets);
        setIncome();
        setAvailable(size);
        setPurchased();
    }
}
