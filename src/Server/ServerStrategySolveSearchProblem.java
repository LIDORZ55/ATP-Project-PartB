package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;
import File.Creating_File;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            //Creating an object to thw whole Helping Functions:
            Creating_File creating_file=new Creating_File();
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            //Step 1:Saving the maze from the server:
            Maze maze = (Maze)fromClient.readObject();
            //Step 2:Checking if was in the past this Maze
            String SearchResult[]=creating_file.Searching_File(maze);
            //Step 3.a: if there is an solution in the server
            if(SearchResult[0]=="-1")
            {
                Solution solution=creating_file.Read_Solution_File(SearchResult[1]);
                toClient.writeObject(solution);
                toClient.flush();
                fromClient.close();
                toClient.close();

            }
            //Step 3.b: if there is no  solution in the server
            else {
                //At first,Send to the Client Solution
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

                toClient.writeObject(solution);
                toClient.flush();
                fromClient.close();
                toClient.close();

                int SerialNumber=Integer.parseInt(SearchResult[0]);
                //Creating an Maze File:
                creating_file.Creating_Maze_File(maze,SerialNumber);
                //Creating an SolutionFile
                creating_file.Creating_Solution_File(maze,SerialNumber);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
