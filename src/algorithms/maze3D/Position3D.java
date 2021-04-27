package algorithms.maze3D;

public class Position3D {
    private int row;
    private int column;
    private int depth;

    public Position3D(int depth, int row, int column) {
        this.depth = depth;
        this.row = row;
        this.column = column;
    }

    public int getDepthIndex(){
        return this.depth;
    }

    public int getRowIndex(){
        return this.row;
    }

    public int getColumnIndex(){
        return this.column;
    }

    @Override
    public String toString() {
        return  "{" +this.depth + ","+this.row+"," + this.column + "}";
    }
}
