package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Hashtable;

public class DepthFirstSearch extends ASearchingAlgorithm{
    private int TotalVertex=0;

    public int getNumberOfNodesEvaluated() {
        return TotalVertex;
    }

    public void setTotalVertex(int totalVertex) {
        TotalVertex = totalVertex;
    }


    /**
     * The method returns the name of the Algorithm
     *
     * @return a AState that represent the all phat-the gold path of the solution
     */
    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
    /**
     * @param TheProblem -the Func get an problem-in our case (Maze\3DMaze)
     * @return the Function return an Solution Object that represent the Gold path-that solve the Problem input
     */
    public Solution solve(ISearchable TheProblem){

        /**Step 1:DefineThe Initial Vertex: **/
        //Initializing the StartAState and The GoalAState
        AState Start_AState=TheProblem.getStartState();
        AState End_AState=TheProblem.getGoalState();
        AState CurAState=Start_AState;
        // Initializing the vertex_Count
        int Vertex_count=0;
        //Initializing The Stack:
        Stack<AState> AState_WhoFound=new Stack<AState>();
        //
        Hashtable<String,AState> vBlack_State = new Hashtable<>();
        //
        Solution solution=new Solution();
        /**Step 2:Put the start node in the stack **/

        // start.visit = true;
        CurAState.setMode(1);
        AState_WhoFound.push(CurAState);
        /**Step 3:While there is node to be handled in the stack **/
        while (!AState_WhoFound.empty()){
            /**Step 3.1:Handle the node on the top of the stack and retrieve its unvisited neighbors **/
            CurAState=AState_WhoFound.pop();
            /***Step 3.2:Terminate if the goal is reached  **/
            if (CurAState.equals(End_AState)==true)
            //The GoldPath Structure:
            {
                //recurse the solution
                solution.Add_AState(CurAState);
                while (CurAState.equals(TheProblem.getStartState())!=true)
                {
                    CurAState=CurAState.getCameFrom();
                    solution.Add_AState(CurAState);
                }
                solution.setTotalVertx(Vertex_count);
                solution.Reverse();
                this.setTotalVertex(solution.getTotalVertx());
                break;
            }
            /*** Step 3.3:Take neighbors, set its parent, mark as visited, and add to the queue **/
            ArrayList<AState>Successors_AL=TheProblem.getAllSuccessors(CurAState,vBlack_State);
            AState Son;
            Solution solution2;
            for (int i = 0; i < Successors_AL.size(); i++) {
                Son = Successors_AL.get(i);
                Son.setMode(1);
                Son.setCameFrom(CurAState);
                Vertex_count=Vertex_count+1;
                AState_WhoFound.push(Son);
            }
            CurAState.setMode(2);
            vBlack_State.put(CurAState.toString(),CurAState);
        }
        return solution;
    }


}
