package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
    private int row;
    private int column;

    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRowIndex() {
        return this.row;
    }

    public int getColumnIndex() {
        return this.column;
    }

    public void setRowIndex(int row) {
        this.row = row;
    }

    public void setColumnIndex(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "{" +this.row + "," + this.column + "}";
    }
}