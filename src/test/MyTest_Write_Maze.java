package test;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import File.Creating_File;

public class MyTest_Write_Maze {

    public static void main(String[] args) {
        /**Creating a new file in the existing tmpdir,And write to the the file **/
        /*
        //Creating a new file in the existing tmpdir,And write to the the file
        try {
            String tmpdir = System.getProperty("java.io.tmpdir");
            System.out.println("Temp file path: " + tmpdir);
            String S="NetaLavi6";
            String z=tmpdir+S;
            File file = new File(z);
            OutputStream out = new FileOutputStream(file);
            // Write your data
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

         */

        Creating_File creating_file=new Creating_File();
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = null; //Generate new maze
        try {
            maze = mazeGenerator.generate(50, 50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //creating_file.Creating_2Files(maze);
        //creating_file.Searching_File(maze);
        creating_file.Creating_Solution_File(maze,0);
        String tmpdir = System.getProperty("java.io.tmpdir");
        String FileName_Part_2="charoncherry8_Solution";
        String Full_FileName=tmpdir+FileName_Part_2;
        creating_file.Read_Solution_File(Full_FileName);


    }
}
