package Server;


import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;


/**
 * After the connection between the client has made,we come to this point.
 * In this class we make an conversation between the both (Ping-Pong) in The next way:
 *The server gets an int [Column_Number,Row_Number] that the client send him
 * The server Create a Maze,Compress the maze
 * In the end The Server send to the client the maze,and the server close the communication between the both
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            //This Wraps the Input stream-This object just Defines an easily way to Read data From the stream
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            int[] array = (int[]) fromClient.readObject();
            //After we get the input from the Client we generating the maze:
            String mazegen = Configurations.getInstance().getMazeGeneratingAlgorithm();
            IMazeGenerator mg;

            if(mazegen.equals("MyMazeGenerator")){
                mg = new MyMazeGenerator();
            }
            else if(mazegen.equals("SimpleMazeGenerator")){
                mg = new SimpleMazeGenerator();
            }
            else{
                mg = new EmptyMazeGenerator();
            }

            Maze maze = mg.generate(array[0], array[1]);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            OutputStream z=new MyCompressorOutputStream(buffer);
            z.write(maze.toByteArray());
            z.flush();

            toClient.writeObject(buffer.toByteArray());
            toClient.flush();

            z.close();
            fromClient.close();
            toClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
