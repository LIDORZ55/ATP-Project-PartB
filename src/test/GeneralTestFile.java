package test;

import File.Creating_File;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

public class GeneralTestFile {
    public static void main(String[] args) {
        Creating_File creating_file=new Creating_File();
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = null; //Generate new maze
        try {
            maze = mazeGenerator.generate(999, 999);
        } catch (Exception e) {
            e.printStackTrace();
        }
     String Output=creating_file.Get_Maze_FileName_withoutSerial(maze);
        String x=creating_file.Get_FileName_witheSerialNumber(Output,5);
       int y=creating_file.Get_SerialNumber_FromFileName(x);
    }

}
