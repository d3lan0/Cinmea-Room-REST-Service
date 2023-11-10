package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@JsonIgnoreProperties(value = {"seatMap", "tickets"})
public class Cinema {
    private int rows;
    private int columns;
    private List<Seat> seats;
    private Seat[][] seatMap;
    private List<Ticket> tickets;
    private Statistics stats;


    public Cinema(int rows, int columns) {
        seatMap = new Seat[rows][columns];
        createSeatingChart(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.seats = getSeats();
        this.tickets = new ArrayList<>();
        setStats();

    }

    public List<Seat> getSeats() {
        List<Seat> output = new ArrayList<>();
        Stream.of(seatMap).forEach(e -> {
            Arrays.stream(e).forEach(f -> output.add(f));
        });

        return output;
    }

    public Seat[][] getseatMap() {
        return seatMap;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Ticket addTicket(Seat seat) {
        Ticket ticket = new Ticket(seat);
        tickets.add(ticket);
        setStats();
        return ticket;
    }

    public Ticket getTicket(String uuid) {
        return tickets.stream().filter(ticket -> uuid.equals(ticket.getToken())).findFirst().orElse(null);
    }

    public void refundTicket(Ticket ticket, String uuid) {
        int row = ticket.getTicket().getRow();
        int col = ticket.getTicket().getColumn();
        seatMap[row - 1][col - 1] = new Seat(row, col);
        deleteTicket(uuid);
        ticket.clearToken();
        stats.updateStats(getTickets(), getSeats().size());
    }

    public Statistics getStats() {
        return stats;
    }

    private void setStats() {
        this.stats = new Statistics(tickets, seats.size());
    }

    private void deleteTicket(String uuid) {
        int indexOf = IntStream.range(0, tickets.size())
                .filter(i -> uuid.equals(tickets.get(i).getToken()))
                .findFirst()
                .orElse(-1);


        tickets.remove(indexOf);
    }

    private void createSeatingChart(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Seat tempSeat = new Seat(i + 1, j + 1);
                seatMap[i][j] = tempSeat;
            }
        }
    }


}
