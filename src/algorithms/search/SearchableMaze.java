package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.io.Serializable;
import java.util.*;

public class SearchableMaze implements ISearchable, Serializable {
    Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * The method returns the stat point
     *
     * @return a AState that represent the starting point.
     */
    @Override
    public AState getStartState() {
        return new MazeState(this.maze.getStartPosition().toString(),0,null);
    }

    /**
     * The method searching the state that we can go foreword from the state input
     *
     * @param s -represent the current state
     * @return an ArrayList that include all the state that we can go foreword from the current state
     */
    public Queue<AState> getAllPossible_AStates_With_Prices(AState s, Hashtable<String,AState> vBlack_State){
        Queue<AState> output=new PriorityQueue<AState>();

        int Cur_row=((MazeState)s).getRow();
        int Cur_column=((MazeState)s).getColumn();
        int New_Row,New_column=0;
        AState New_MazeState;

        Boolean IsLegal_Upper,IsLegal_Lower,IsLegal_Right,IsLegal_Left=false;
        /****Upper-(1,1)->(0,1)****/
        New_Row=Cur_row-1;
        New_column=Cur_column;
        IsLegal_Upper=this.maze.IsLegal(New_Row,New_column);
        if(IsLegal_Upper==true) {
            New_MazeState=new MazeState(New_Row, New_column, 10, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }

        /****Lower-(1,1)->(2,1)****/
        New_Row=Cur_row+1;
        New_column=Cur_column;
        IsLegal_Lower=this.maze.IsLegal(New_Row,New_column);
        if(IsLegal_Lower==true)
        {
            New_MazeState=new MazeState(New_Row, New_column, 10, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }


        /****Left-(1,1)->(2,1)****/
        New_Row=Cur_row;
        New_column=Cur_column-1;
        IsLegal_Left=this.maze.IsLegal(New_Row,New_column);
        if(IsLegal_Left==true)
        {
            New_MazeState=new MazeState(New_Row, New_column, 10, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }

        /****Right-(1,1)->(0,1)****/
        New_Row=Cur_row;
        New_column=Cur_column+1;
        IsLegal_Right=this.maze.IsLegal(New_Row,New_column);
        if(IsLegal_Right==true)
        {
            New_MazeState=new MazeState(New_Row, New_column, 10, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }


        //Diagonal down right-(1,1)->(2,2)
        New_Row=Cur_row+1;
        New_column=Cur_column+1;
        if((this.maze.IsLegal(New_Row,New_column)==true)&&(IsLegal_Right==true))
        {
            New_MazeState=new MazeState(New_Row, New_column, 15, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }



        //Diagonal top right-(1,1)->(0,2)
        New_Row=Cur_row-1;
        New_column=Cur_column+1;
        //Upping+Right IsLegal Checking
        if((this.maze.IsLegal(New_Row,New_column)==true)&&(IsLegal_Upper==true))
        {
            New_MazeState=new MazeState(New_Row, New_column, 15, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }

        //Diagonal top left-(1,1)->(0,0)
        New_Row=Cur_row-1;
        New_column=Cur_column-1;
        if((this.maze.IsLegal(New_Row,New_column)==true)&&(IsLegal_Upper==true))
        {
            New_MazeState=new MazeState(New_Row, New_column, 15, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }


        //Diagonal down left-(1,1)->(2,0)
        New_Row=Cur_row+1;
        New_column=Cur_column-1;
        if((this.maze.IsLegal(New_Row,New_column)==true)&&(IsLegal_Left==true))
        {
            New_MazeState=new MazeState(New_Row, New_column, 15, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }
        return output;


    }

    /**
     * The method searching the state that we can go foreword from the state input
     * @param s -represent the current state
     * @param vBlack_State-represent the AState who crated
     * The Method Create an AStates Without Prices
     * @return an ArrayList that include all the state that we can go foreword from the current state
     */
    public ArrayList<AState> getAllSuccessors(AState s, Hashtable<String,AState> vBlack_State ) {
        ArrayList<AState> output=new ArrayList<AState>();
        int Cur_row=((MazeState)s).getRow();
        int Cur_column=((MazeState)s).getColumn();
        int New_Row,New_column=0;
        AState New_MazeState;

        Boolean IsLegal_Upper,IsLegal_Lower,IsLegal_Right,IsLegal_Left=false;
        /****Upper-(1,1)->(0,1)****/
        New_Row=Cur_row-1;
        New_column=Cur_column;
        IsLegal_Upper=this.maze.IsLegal(New_Row,New_column);
        if(IsLegal_Upper==true) {
            New_MazeState=new MazeState(New_Row, New_column, 0, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }

        /****Lower-(1,1)->(2,1)****/
        New_Row=Cur_row+1;
        New_column=Cur_column;
        IsLegal_Lower=this.maze.IsLegal(New_Row,New_column);
        if(IsLegal_Lower==true)
        {
            New_MazeState=new MazeState(New_Row, New_column, 0, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }


        /****Left-(1,1)->(2,1)****/
        New_Row=Cur_row;
        New_column=Cur_column-1;
        IsLegal_Left=this.maze.IsLegal(New_Row,New_column);
        if(IsLegal_Left==true)
        {
            New_MazeState=new MazeState(New_Row, New_column, 0, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }

        /****Right-(1,1)->(0,1)****/
        New_Row=Cur_row;
        New_column=Cur_column+1;
        IsLegal_Right=this.maze.IsLegal(New_Row,New_column);
        if(IsLegal_Right==true)
        {
            New_MazeState=new MazeState(New_Row, New_column, 0, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }


        //Diagonal down right-(1,1)->(2,2)
        New_Row=Cur_row+1;
        New_column=Cur_column+1;
        if((this.maze.IsLegal(New_Row,New_column)==true)&&(IsLegal_Right==true))
        {
            New_MazeState=new MazeState(New_Row, New_column, 0, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }



        //Diagonal top right-(1,1)->(0,2)
        New_Row=Cur_row-1;
        New_column=Cur_column+1;
        //Upping+Right IsLegal Checking
        if((this.maze.IsLegal(New_Row,New_column)==true)&&(IsLegal_Upper==true))
        {
            New_MazeState=new MazeState(New_Row, New_column, 0, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }

        //Diagonal top left-(1,1)->(0,0)
        New_Row=Cur_row-1;
        New_column=Cur_column-1;
        if((this.maze.IsLegal(New_Row,New_column)==true)&&(IsLegal_Upper==true))
        {
            New_MazeState=new MazeState(New_Row, New_column, 0, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
        }


        //Diagonal down left-(1,1)->(2,0)
        New_Row=Cur_row+1;
        New_column=Cur_column-1;
        if((this.maze.IsLegal(New_Row,New_column)==true)&&(IsLegal_Left==true))
        {
            New_MazeState=new MazeState(New_Row, New_column, 0, s);
            if (vBlack_State.containsKey(New_MazeState.toString())==false)
                output.add(New_MazeState);
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
        String st=this.maze.getGoalPosition().toString();
        return new MazeState(st,0,null);

    }
    /**
     * The method returns the all initial State-We convert each cell in the matrix to an -AState-(Adaptation)
     *Dont Include the StartState!!!!!!!!!!
     * To the whole AStates we giving them initial Values:came From=Null,Cost:0
     * @return ArrayList<AState>-Each cell,its an AState-Dont Include the StartState!!!!!!!!!!
     */
    public ArrayList<AState> getAllInitialState() {
        ArrayList<AState> output=new ArrayList<AState>();
        int row=this.maze.GetRow();
        int column=this.maze.GetColumn();
        AState StartState=this.getStartState();
        AState NewState;
        Position CurPosition;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                    CurPosition=new Position(i,j);
                    NewState=new MazeState(CurPosition.toString(),0,null);
                    //Just to ensure that I dont create the StartState
                    if ((NewState.equals(StartState)==false))
                    output.add(NewState);
            }

        }
        return output;
    }




}
