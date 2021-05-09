package test;

import Client.Client;
import Server.Server;
import Server.ServerStrategyGenerateMaze;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RunCommunicatationWithServers {
    public static void main(String[] args) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new ClientStrategy());
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
