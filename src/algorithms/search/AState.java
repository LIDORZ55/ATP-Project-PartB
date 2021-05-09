package algorithms.search;

import java.io.Serializable;
import java.util.Objects;

public abstract class AState implements  Comparable<AState>, Serializable {
    //the attribute of AState:
    private String state;
    private int cost;
    private AState cameFrom;
    /***Mode 0-White-Unvisited
     * Mode 1-Gray-Visited But not examined
     * Mode 2-Black-Visited and examined
     */

    private int mode;

    /**
     * @return the mode of the state
     */
    public int getMode() {
        return mode;
    }

    /**
     * @param mode-the function set the mode of the AState
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * @param state
     * @param cost
     * @param cameFrom
     * The constructor
     */
    public AState(String state, int cost, AState cameFrom) {
        this.state = state;
        this.cost=cost;
        this.cameFrom=cameFrom;
        this.mode=0;
    }

    /**
     * @param o
     * @return-the function return true if the two objects are equals-otherwise -the function return false
     * the checking made by the string value of the object.
     *
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        String str1=this.toString();
        String str2=o.toString();
        return Objects.equals(str1,str2);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * The method returns true if This has Less cost then s
     *
     * @param s-represent an AState
     * @return true if this cost less and equal from o,otherwise return false
     */
    public boolean LowerCost(AState s) {
        return (this.cost<=s.getCost());
    }


    /**  Getter and Setter to Cost  */
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    /**
     * The method Just For The Ordering Function!!!!!!!!!
     *
     * @param s-represent an AState
     * @return true if this cost less and equal from o,otherwise return false
     */
    public int compareTo(AState s) {
        if(this.getCost()>=s.getCost())
        return 1;
        else
            return 0;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    @Override
    public String toString() {
        return this.state;

    }

}
