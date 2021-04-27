package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState {
    int Row;
    int Column;
    int Depth;
    /**
     * The first Constructor-Get 3 Verbals
     * @param state
     * @param cost
     * @param cameFrom
     */
    public Maze3DState(String state, int cost, AState cameFrom) {
        super(state, cost, cameFrom);
        //state="{3,222225,143324424242}"
        String S=state;
        int start_index=1;
        int break_index=S.indexOf(",");
        int end_index=S.length()-1;
        String Row,Column,Depth="";
        Depth=S.substring(start_index,break_index);
        //Updating after one SubString:
        S=S.substring(break_index+1,end_index);
        end_index=S.length();
        break_index=S.indexOf(",");
        Row=S.substring(0,break_index);
        //Updating after second SubString:
        Column=S.substring(break_index+1,end_index);
        this.Depth=Integer.parseInt(Depth);
        this.Row=Integer.parseInt(Row);
        this.Column=Integer.parseInt(Column);
    }
    /**
     * The Second Constructor-Get 5 Verbals
     * @param depth
     * @param row
     * @param column
     * @param cost
     * @param cameFrom
     */
    public Maze3DState(int depth,int row,int column, int cost, AState cameFrom) {
        super("{"+depth+","+row+","+column+"}", cost, cameFrom);
        this.Row=row;
        this.Column=column;
        this.Depth=depth;
    }
    /*** The Getter and the Setter***/
    public int getRow() {
        return Row;
    }

    public void setRow(int row) {
        Row = row;
    }

    public int getColumn() {
        return Column;
    }

    public void setColumn(int column) {
        Column = column;
    }

    public int getDepth() {
        return Depth;
    }

    public void setDepth(int depth) {
        Depth = depth;
    }
}
