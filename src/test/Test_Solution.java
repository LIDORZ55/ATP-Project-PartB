package test;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

public class Test_Solution {

    public static void main(String[] args) {
        //Test for Simple:
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = null; //Generate new maze
        try {
            maze = mazeGenerator.generate(50, 50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
