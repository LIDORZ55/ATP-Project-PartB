package test;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

public class GeneralConvertMaze_Byte {
    public static void main(String args[]){
        AMazeGenerator mg = new MyMazeGenerator();
        try {
            Maze maze = mg.generate(5, 5);
            maze.print();
            Maze maze2= new Maze(maze.toByteArray());
            System.out.println("***************************");
            maze2.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
