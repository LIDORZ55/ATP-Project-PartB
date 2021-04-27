package algorithms.search;
import java.util.*;
import java.util.ArrayList;

//For example to the problem  state: 4
//4: (WeightedGraphState)-string "4",cost=0,camefrom=null
//For example to the problem  state: 4
//4: (WeightedGraphState)-string "4->1",cost=4,camefrom=4
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    /**
     *Input:The method get an ArrayList of AState the Goal point
     *Output:The method return The AState with the LowestCost From the ArrayList
     */
    public AState GetLowCostAState(ArrayList<AState> arrayList){
        AState temp;
        AState SmallCost;
        if(arrayList.size()==0)
            return null;
        if(arrayList.size()==1)
        {
            return arrayList.get(0);
        }
        else {
            SmallCost=arrayList.get(1);
            for (int i = 0; i < arrayList.size(); i++) {
                temp=arrayList.get(i);
                if(temp.LowerCost(SmallCost))
                    SmallCost=temp;
            }
            return SmallCost;
        }

    }
    /**
     *Input:The method get an Unsorted ArrayList
     *Output:The method return The ArrayList after an sorting ,By an Cost
     */
    public ArrayList<AState>GetSortedAListByCost(ArrayList<AState> arrayList){
        return  null;
    }

    /**
     *@param Close
     *@param s
     *Output:The method return true if s existed in the ArrayList,otherwise the method return false.
     * the checking executed by the string value
     */
    public boolean HashTable_IsContained(Hashtable<String,AState> Close,AState s){
    return Close.containsKey(s.toString());
    }
    public boolean PriorityQuery_IsContained(Queue<AState> queue, AState s){
        Iterator<AState> iterator =queue.iterator();
        while(iterator.hasNext()) {
            if ((iterator.next()).equals(s))
                return true;
        }
        return false;
    }


}
