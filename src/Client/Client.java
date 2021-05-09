package Client;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
public class Client {
    //the client need 2 things to send an request to communicate with the server:
    //1.IP
    //2. Port of the server(the exactly address of the server-like a house number)
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;
    /**
     * The Constructor!!!!!!!!!!!!!!!!
     * @param serverIP
     * @param serverPort
     * @param strategy
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void communicateWithServer(){
        /**How the connection between the client to the server made?
         * At first,The client is the only one who can Promoter the connection between the both
         * 1.The client try to connect the server by Creating a socket-When we Create an socket in the
         * ClientSide-its send a request to the server that client want to connect to him*/
        try(Socket serverSocket=new Socket(serverIP,serverPort)){
         //   System.out.println("connected to server- IP= "+serverIP);
            //The server get the InputStream and the OutputStream after an respond from the server-an by the bouth he can Send and Get Data from the server
            strategy.clientStrategy(serverSocket.getInputStream(),serverSocket.getOutputStream());

        } catch (IOException e) {
            //could be that the server number is invalid
            e.printStackTrace();
        }
    }

}
