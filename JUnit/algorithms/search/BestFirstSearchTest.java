package algorithms.search;


import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {


    @Test
    void setTotalVertex() {
        ISearchingAlgorithm domain = new BestFirstSearch();
        domain.setTotalVertex(45);
        assertEquals(45,domain.getNumberOfNodesEvaluated());
    }

    @Test
    void getNumberOfNodesEvaluated() {
        ISearchingAlgorithm domain = new BestFirstSearch();
        assertEquals(0,domain.getNumberOfNodesEvaluated());
    }

    @Test
    void getName() {
        ISearchingAlgorithm domain = new BestFirstSearch();
        assertEquals("BestFirstSearch",domain.getName());
    }

    @Test
    void solve() throws Exception {
        AMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(2500, 2500);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        ISearchingAlgorithm domain = new BestFirstSearch();
        long StartTime=System.currentTimeMillis();
        //we operate the generate method
        Solution solution = domain.solve(searchableMaze);
        //we save the end time of the method
        long EndTime=System.currentTimeMillis();
        //the same Unix timestamp in seconds
        long TotalTime=(EndTime-StartTime)/ 1000;
        /**Test1: Check if the algorithm runs in less than 60 seconds **/
        assertTrue(TotalTime<=60);
        /**Test2: Check if the Solution Start in the StartAState **/
        assertTrue(searchableMaze.getStartState().equals(solution.GetStartAState()));
        /**Test3: Check if the Solution End Point Is the same Goal AState**/
        assertTrue(searchableMaze.getGoalState().equals(solution.GetGoalAState()));
    }
}