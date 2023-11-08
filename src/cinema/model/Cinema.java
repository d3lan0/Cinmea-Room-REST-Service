package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@JsonIgnoreProperties(value = {"seatMap"})
public class Cinema {
    private int rows;
    private int columns;
    private List<Seat> seats;
    private Seat[][] seatMap;

    public Cinema(int rows, int columns) {
        seatMap = new Seat[rows][columns];
        createSeatingChart(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.seats = getSeats();
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

    private void createSeatingChart(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Seat tempSeat = new Seat(i + 1, j + 1);
                seatMap[i][j] = tempSeat;
            }
        }
    }

}
