package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This Class represent The soultion of The Maze-Its Include TheGoldPath
 */
public class Solution {
    //Its save the whole AStates that represent the GoldPath
    ArrayList<AState> TheGoldPath;
    //Its represent the vertx that the Solution Algorithm has Developed
    int TotalVertx;
    //the Constructor
    public Solution() {
        this.TheGoldPath = new ArrayList<AState>();
        TotalVertx=0;
    }
    /**The Getter and Setter */
    public void Add_AState(AState s){
        this.TheGoldPath.add(s);
    }
    public void RemoveAState(AState s){
        this.TheGoldPath.remove(s);
    }
    public ArrayList<AState>getSolutionPath(){
        return TheGoldPath;
    }

    public int getTotalVertx() {
        return TotalVertx;
    }
    public void setTotalVertx(int totalVertx) {
        TotalVertx = totalVertx;
    }

    /**
     * This function reverse the Solution path-to his original rhythm
     */
    public void Reverse(){
        Collections.reverse(this.TheGoldPath);
    }

    /**
     * @return The Start AState of the Solution-The starting point
     */
    public AState GetStartAState(){
        return this.TheGoldPath.get(0);
    }
    /**
     * @return The End AState of the Solution-The End point
     */
    public AState GetGoalAState(){
        return this.TheGoldPath.get(this.TheGoldPath.size()-1);
    }

}
