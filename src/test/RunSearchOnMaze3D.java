package test;

import algorithms.maze3D.AMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.search.*;

import java.util.ArrayList;

public class RunSearchOnMaze3D {
    public static void main(String[] args) throws Exception {
        AMaze3DGenerator mg = new MyMaze3DGenerator();
        Maze3D maze = mg.generate(5, 5,100);
        maze.print();
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
         for (int i = 0; i < solutionPath.size(); i++) {
             System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
         }
    }
}
