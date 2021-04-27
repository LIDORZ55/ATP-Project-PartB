package algorithms.search;

//For example to the problem  state: "{0,0}"
//4: (MazeState)-string "{0,0}",cost=0,camefrom=null
//For example to the problem  state: {0,0}->{0,1}
//4: (MazeState)-string "{0,1}",cost=10,camefrom={0,0}
//For example to the problem  state: {0,0}->{1,1}
//4: (MazeState)-string "{1,1}",cost=15,camefrom={0,0}

/**
 * This Class Make AN Adaptation(OR Transformation) To the Class Position in Maze
 */
public class MazeState extends AState {
    int Row;
    int Column;

    /**
     * The first Constructor-Get 3 Verbals
     * @param state
     * @param cost
     * @param cameFrom
     */
    public MazeState(String state, int cost, AState cameFrom) {
        super(state, cost, cameFrom);
        int start_index=1;
        int break_index=state.indexOf(",");
        int end_index=state.length()-1;
        String Row=state.substring(start_index,break_index);
        String Column=state.substring(break_index+1,end_index);
        this.Row=Integer.parseInt(Row);
        this.Column=Integer.parseInt(Column);

    }

    /**
     * The Second Constructor-Get 4 Verbals
     * @param row
     * @param column
     * @param cost
     * @param cameFrom
     */
    public MazeState(int row,int column, int cost, AState cameFrom) {
        super("{"+row+","+column+"}", cost, cameFrom);
        this.Row=row;
        this.Column=column;
    }
    /*** The Getter and the Setter***/
    public int getRow() {
        return Row;
    }

    public int getColumn() {
        return Column;
    }
}
