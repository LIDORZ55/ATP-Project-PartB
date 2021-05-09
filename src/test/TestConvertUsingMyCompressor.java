package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.Arrays;

public class TestConvertUsingMyCompressor {
    public static void main(String[] args) {

        //Test for Simple:
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = null; //Generate new maze
        try {
            maze = mazeGenerator.generate(1000, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // save maze to a file
            //The file Output Stream
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));

            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try {
//read maze from file
            //HERE THE TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));

            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());

        System.out.println(String.format("Mazes equal: %s",areMazesEquals));//maze should be equal to loadedMaze

    }

}
