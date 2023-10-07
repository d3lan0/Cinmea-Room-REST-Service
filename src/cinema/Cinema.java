package cinema;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private int rows;
    private int columns;
    private List<Seat> seats;

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = createSeatingChart(rows, columns);
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    private List<Seat> createSeatingChart(int rows, int columns) {
        List<Seat> output = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                output.add(new Seat(i,j));
            }
        }
        return output;
    }

}
