package cinema.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = "seatPurchased")
public class Seat {
    int row;
    int column;
    int price;
    boolean purchased;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = setPrice();
        this.purchased = false;
    }

    private int setPrice() {
        return row < 4 ? 10 : 8;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return this.price;
    }

    public boolean isSeatPurchased() {
        return this.purchased;
    }

    public void purchaseSeat() {
        this.purchased = true;
    }
}



