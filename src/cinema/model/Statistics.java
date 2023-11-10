package cinema.model;

import java.util.List;

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

    public void setIncome() {
        this.income = tickets.stream()
                .mapToInt(e -> e.getTicket()
                        .getPrice())
                .sum();
    }

    private void setAvailable(int size) {
        this.available = size - tickets.size();
    }

    private void setPurchased() {
        this.purchased = this.tickets.size();
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
