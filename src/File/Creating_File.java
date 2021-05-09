package File;

import IO.SimpleCompressorOutputStream;
import IO.SimpleDecompressorInputStream;
import Server.Configurations;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Creating_File {


    /**
     * @param maze -the function Get an Maze object and Search If there Is specific
     *             exiting File withe the same 6 Properties:
     *             1.Limit:(int Limit_Row,int Limit_Column)
     *             2.The StartPoint:(int StartRow,int StartColumn)
     *             3.The GoalPoint:(int GoalRow,int GoalColumn)
     * Return an array in size 2:
     * The first[0] Cell:["-1"(If the Maze Found),otherwise number-If the maze dont Found-and it will be it serial number]
     * The Second[1] Cell:[The full solution Path]
     */
    public String[] Searching_File(Maze maze) {
        //Initializing the Output
        String[] Output=new String[2];
        String tmpdir = System.getProperty("java.io.tmpdir");
        File dir = new File(tmpdir);
        File[] foundFiles = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                //Searching to the Whole File withe the same Parameters
                return name.startsWith(Get_Maze_FileName_withoutSerial(maze));
            }
        });
        //
        int Serial_number=0;
        for (File file : foundFiles) {

            // Process file
            //**************************
            byte savedMazeBytes[] = new byte[0];
            try {
                //read maze from file
                InputStream in = new SimpleDecompressorInputStream(new FileInputStream(file));

                savedMazeBytes = new byte[maze.toByteArray().length];
                in.read(savedMazeBytes);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Maze loadedMaze = new Maze(savedMazeBytes);
            boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
            //If We are Find a maze that had Old Solution
            if(areMazesEquals==true){
                System.out.println(String.format("Mazes equal: %s",areMazesEquals));
                Output[0]="-1";
                //In the Output[1] Should Be the SolutionPath
                String FullFileName=file.getName();
                int CurSerialNumber=this.Get_SerialNumber_FromFileName(FullFileName);
                String PartlyFileName=this.Get_Solution_FileName_withoutSerial(maze);
                String FullSolution_FileName=this.Get_FileName_witheSerialNumber(PartlyFileName,CurSerialNumber);
                Output[1]=FullSolution_FileName;
                return Output;
            }

            //Editing the serial Number:
            Serial_number++;
        }

        //If we are not found the old Maze
        Output[0]=String.valueOf(Serial_number);
        Output[1]="";
        return Output;

    }

    /**
     * @param maze Get The Object Maze
     * @param Serial_Number Get the Serial_Number of the spesific_maze
     * Create the next 2 Files:
     * 1.The Maze_FIle
     * 2.The Solution_File
     */
    public void Creating_Maze_File(Maze maze,int Serial_Number) {
        String tmpdir = System.getProperty("java.io.tmpdir");
        String FileName_Part_2=Get_Maze_FileName_withoutSerial(maze);
        String FileName_With_Serial_Number=Get_FileName_witheSerialNumber(FileName_Part_2,Serial_Number);
        String Full_FileName=tmpdir+FileName_With_Serial_Number;
        File file = new File(Full_FileName);
        /**Creating the Maze */
        try {
            // save maze to a file
            //The file Output Stream
            OutputStream out = new SimpleCompressorOutputStream(new FileOutputStream(file));
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void Creating_Solution_File(Maze maze,int Serial_Number) {
        String tmpdir = System.getProperty("java.io.tmpdir");
        String FileName_Part_2=Get_Solution_FileName_withoutSerial(maze);
        String FileName_With_Serial_Number=Get_FileName_witheSerialNumber(FileName_Part_2,Serial_Number);
        String Full_FileName=tmpdir+FileName_With_Serial_Number;
        File file = new File(Full_FileName);
        /**Creating the Maze */
        try {
            // save maze to a file
            //The file Output Stream
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            //Solving the inputMaze
            SearchableMaze searchableMaze = new SearchableMaze(maze); //not generic
            String mazesearchalg = Configurations.getInstance().getMazeSearchingAlgorithm();
            ISearchingAlgorithm mazeSearch;
            if(mazesearchalg.equals("BestFirstSearch")){
                mazeSearch = new BestFirstSearch();
            }
            else if(mazesearchalg.equals("DepthFirstSearch")){
                mazeSearch = new DepthFirstSearch();
            }
            else{
                mazeSearch = new BreadthFirstSearch();
            }
            Solution solution = mazeSearch.solve(searchableMaze);
            //ISearchingAlgorithm searcher = new DepthFirstSearch(); //not generic
            //Solution solution = mazeSearch.solve(searchableMaze);
            //Saving the input Maze
            out.writeObject(solution);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Solution Read_Solution_File(String Full_FileName) {


        Solution mazeSolution=new Solution();
        try {
            String tmpdir = System.getProperty("java.io.tmpdir");
            String New_Name=tmpdir+Full_FileName;
            ObjectInputStream input = new  ObjectInputStream(new FileInputStream(New_Name));
             mazeSolution = (Solution) input.readObject(); //read generated maze (compressed withMyCompressor) from server
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mazeSolution;
    }

/*
    public void Read_Solution_File(String Full_FileName) {
        try {
            ObjectInputStream input = new  ObjectInputStream(new FileInputStream(Full_FileName));
            Solution mazeSolution = (Solution) input.readObject(); //read generated maze (compressed withMyCompressor) from server
            //Print Maze Solution retrieved from the server
            System.out.println(String.format("Solution steps: %s", mazeSolution));
            ArrayList<AState> mazeSolutionSteps = mazeSolution.getSolutionPath();
            for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    //Helping functions:
    public String Get_Maze_FileName_withoutSerial(Maze maze){
        String Output="maze-";
        String LimitRow=String.valueOf(maze.GetRow());
        String LimitColumn=String.valueOf(maze.GetColumn());
        String StartRow= String.valueOf(maze.getStartPosition().getRowIndex());
        String StartColumn=String.valueOf(maze.getStartPosition().getColumnIndex());
        String GoalRow=String.valueOf(maze.getGoalPosition().getRowIndex());
        String GoalColumn=String.valueOf(maze.getGoalPosition().getColumnIndex());
        Output=Output+LimitRow+","+LimitColumn+","+StartRow+","+StartColumn+","+GoalRow+","+GoalColumn;
        return Output;
    }
    public String Get_Solution_FileName_withoutSerial(Maze maze){
        String Output="solution-";
        String LimitRow=String.valueOf(maze.GetRow());
        String LimitColumn=String.valueOf(maze.GetColumn());
        String StartRow= String.valueOf(maze.getStartPosition().getRowIndex());
        String StartColumn=String.valueOf(maze.getStartPosition().getColumnIndex());
        String GoalRow=String.valueOf(maze.getGoalPosition().getRowIndex());
        String GoalColumn=String.valueOf(maze.getGoalPosition().getColumnIndex());
        Output=Output+LimitRow+","+LimitColumn+","+StartRow+","+StartColumn+","+GoalRow+","+GoalColumn;
        return Output;
    }
    public String Get_FileName_witheSerialNumber(String input,int SerialNumber){
        return input+"-"+String.valueOf(SerialNumber);
    }
    public int Get_SerialNumber_FromFileName(String input){
        int lastIndexOf=input.lastIndexOf("-");
        String serialNumber=input.substring(lastIndexOf+1);
        return Integer.parseInt(serialNumber);
    }

}





