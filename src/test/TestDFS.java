package test;

import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.*;
import algorithms.search.*;

public class TestDFS {
    public static void main(String[] args) throws Exception {
        /**** Basic test Unit 1: */
        AMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(999, 999);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        ISearchingAlgorithm domain = new BestFirstSearch();
        maze.print();
        long StartTime=System.currentTimeMillis();
        //we operate the generate method
        Solution solution = domain.solve(searchableMaze);
        //we save the end time of the method
        long EndTime=System.currentTimeMillis();
        System.out.println(solution.getSolutionPath().toString());
        System.out.println(solution.getTotalVertx());

        System.out.println(EndTime-StartTime);

    }
}
