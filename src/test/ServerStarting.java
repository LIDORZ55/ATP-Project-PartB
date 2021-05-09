package test;

import Server.Server;
import Server.ServerStrategyGenerateMaze;

public class ServerStarting {
    public static void main(String[] args) {
        Server mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        mazeGeneratingServer.start();
        mazeGeneratingServer.stop();
    }
}
