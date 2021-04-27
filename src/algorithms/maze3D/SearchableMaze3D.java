package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Queue;

public class SearchableMaze3D implements ISearchable {
    Maze3D maze3D;

    /*** The Constructor***/
    public SearchableMaze3D(Maze3D maze3D) {
        this.maze3D = maze3D;
    }

    /**
     * The method returns the stat point
     *
     * @return a AState that represent the starting point.
     */

    @Override
    public AState getStartState() {
        return new Maze3DState(this.maze3D.getStartPosition().toString(),0,null);
    }

    /**
     * The method searching the state that we can go foreword from the state input
     *
     * @param s            -represent the current state
     * @param vBlack_State
     * @return an ArrayList that include all the state that we can go foreword from the current state
     */
    @Override
    public Queue<AState> getAllPossible_AStates_With_Prices(AState s, Hashtable<String, AState> vBlack_State) {
        Queue<AState> output=new PriorityQueue<AState>();
        int Cur_row=((Maze3DState)s).getRow();
        int Cur_column=((Maze3DState)s).getColumn();
        int Cur_Depth=((Maze3DState)s).getDepth();
        int New_Row,New_column,New_Depth=0;
        AState New_Maze3DState;
        /****Move Row Up-(0,0,0)->(1,0,0)****/
        New_Row=Cur_row+1;
        New_column=Cur_column;
        New_Depth=Cur_Depth;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 10, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }

        /****Move Row Down-(1,0,0)->(0,0,0)****/
        New_Row=Cur_row-1;
        New_column=Cur_column;
        New_Depth=Cur_Depth;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 0, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }
        /****Move Column Up-(0,0,0)->(0,1,0)****/
        New_Row=Cur_row;
        New_column=Cur_column+1;
        New_Depth=Cur_Depth;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 10, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }
        /****Move Column Down-(0,1,0)->(0,0,0)****/
        New_Row=Cur_row;
        New_column=Cur_column-1;
        New_Depth=Cur_Depth;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 0, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }
        /****Move Depth Up-(0,0,0)->(0,0,1)****/
        New_Row=Cur_row;
        New_column=Cur_column;
        New_Depth=Cur_Depth+1;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 10, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }
        /****Move Depth Down-(0,0,1)->(0,0,0)****/
        New_Row=Cur_row;
        New_column=Cur_column;
        New_Depth=Cur_Depth-1;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 0, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }

        return output;
    }

    /**
     * The method returns the Goal point
     *
     * @return a AState that represent the end point.
     */
    @Override
    public AState getGoalState() {
        return new Maze3DState(this.maze3D.getGoalPosition().toString(),0,null);
    }

    /**
     * The method returns All the initial State-Not include the Goal State
     *
     * @return a AState that represent the end point.
     */
    @Override
    public ArrayList<AState> getAllInitialState() {
        return null;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s, Hashtable<String, AState> vBlack_State) {
        ArrayList<AState> output=new ArrayList<AState>();
        int Cur_row=((Maze3DState)s).getRow();
        int Cur_column=((Maze3DState)s).getColumn();
        int Cur_Depth=((Maze3DState)s).getDepth();
        int New_Row,New_column,New_Depth=0;
        AState New_Maze3DState;
        /****Move Row Up-(0,0,0)->(1,0,0)****/
        New_Row=Cur_row+1;
        New_column=Cur_column;
        New_Depth=Cur_Depth;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 0, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }

        /****Move Row Down-(1,0,0)->(0,0,0)****/
        New_Row=Cur_row-1;
        New_column=Cur_column;
        New_Depth=Cur_Depth;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 0, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }
        /****Move Column Up-(0,0,0)->(0,1,0)****/
        New_Row=Cur_row;
        New_column=Cur_column+1;
        New_Depth=Cur_Depth;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 0, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }
        /****Move Column Down-(0,1,0)->(0,0,0)****/
        New_Row=Cur_row;
        New_column=Cur_column-1;
        New_Depth=Cur_Depth;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 0, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }
        /****Move Depth Up-(0,0,0)->(0,0,1)****/
        New_Row=Cur_row;
        New_column=Cur_column;
        New_Depth=Cur_Depth+1;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 0, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }
        /****Move Depth Down-(0,0,1)->(0,0,0)****/
        New_Row=Cur_row;
        New_column=Cur_column;
        New_Depth=Cur_Depth-1;
        if(this.maze3D.IsLegal(New_Depth,New_Row,New_column)==true) {
            New_Maze3DState=new Maze3DState(New_Depth, New_Row,New_column, 0, s);
            if (vBlack_State.containsKey(New_Maze3DState.toString())==false)
                output.add(New_Maze3DState);
        }

        return output;
    }
}
