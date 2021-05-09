package Server;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        /**Here we are initializing the threads pool of java
         * and we are Define How many Clients (Threads) we can connect simultaneity
         * we Must using the thread pool 'to control the whole threads that we created
         * */

        int threadsize = Configurations.getInstance().getThreadPoolSize();
        this.threadPool = Executors.newFixedThreadPool(threadsize);//from file
    }
    public void start() {
        new Thread(()->{runServer();}).start();
    }

    public void runServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS); //check
           // System.out.println(("Starting server at port = " + port));

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                //    System.out.println("Client accepted: " + clientSocket.toString());


                    // Insert the new task into the thread pool:
                    /** This is enter the code into an thread-The handle client warps in Runnable */
                    threadPool.submit(() -> {
                        handleClient(clientSocket); //creates a runnable using lambda exp
                    });

                } catch (SocketTimeoutException e){
                  //  System.out.println("Socket timeout");
                }
            }
            serverSocket.close();
            /**The shut Down not get a new task-he dont get a new clients **/
            threadPool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param clientSocket
     * We make an  sequence of action after the connection  between the client to server is made in simultaneity way.
     * In this way,after the first  connection between the server and the client had made-The Conversation
     * Continue here-in this point.We are Warp this Method in thread-That this method will be independent
     */
    private void handleClient(Socket clientSocket) {
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
          //  System.out.println("Done handling client: " + clientSocket.toString());
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stop(){
        //System.out.println("Stopping server...");
        stop = true;
    }
}
