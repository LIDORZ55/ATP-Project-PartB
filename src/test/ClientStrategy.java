package test;

import Client.IClientStrategy;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;

import java.io.*;

public class ClientStrategy implements IClientStrategy {

    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
        try {
            ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
            int[] array = new int[2];
            array[0] = 10;
            array[1] = 10;
            System.out.println("Sending array: " + array.toString());
            toServer.writeObject(array);
            toServer.flush();
            ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
            byte[] serverResult = (byte[])fromServer.readObject();
            InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(serverResult));
            byte[] decompressedMaze = new byte[1000 /*CHANGESIZE ACCORDING TO YOU MAZE SIZE*/]; //allocating byte[] for the decompressedmaze -
            is.read(decompressedMaze); //Fill decompressed Maze25 | P a g e with bytes
            Maze maze = new Maze(decompressedMaze);
            maze.print();

            fromServer.close();
            toServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
