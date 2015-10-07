package su.kilko.fakeclient.server;

import su.kilko.fakeclient.services.Stream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kosilov Nikita on 26.07.2015.
 */
public class Server extends Thread {
    Socket socketFromClient = null;
    static ServerSocket socketServers = null;
    int numClient;
    public static void startServer() throws IOException {

        System.out.println("Welcome to Server side");

        int numOfClients=1; //count client
        // create server socket
        try {
            socketServers = new ServerSocket(4444);
            System.out.println("Server started");
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }

        while (true) {
            try {
                new Server(numOfClients, socketServers.accept());
                numOfClients++;
            }
            catch (Exception e)
            {
                break;
            }
        }
    }
    public Server(int numClients, Socket socketAccept)throws Exception {
        socketFromClient = socketAccept;
        numClient=numClients;

        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }
    public void run() {
        int count = 0;
        int count1 = 0;
        try {
            while (true) {
                if (count == 0) {
                    try {

                        System.out.println("Client " + numClient + " connected");
                    } catch (Exception e) {
                        System.out.println("Can't accept");
                        System.exit(-1);
                    }
                } else {
                    System.out.println("Server shutdown at the request of Client " + numClient);
                    break;
                }

                Stream stream = new Stream(socketFromClient);

                String inputMessage;

                //ystem.out.println("Wait for messages");
                while (true) {
                    inputMessage = stream.getInputStream().readLine();
                    if (inputMessage.equalsIgnoreCase("exit")) {
                        stream.getOutputStream().close();
                        stream.getInputStream().close();
                        socketFromClient.close();
                        socketServers.close();
                        count++;
                        break;
                    }
                    if (inputMessage.equalsIgnoreCase("close connection")) {
                        System.out.println("Client " + numClient + " disconnected");
                        socketFromClient.close();
                        count1++;
                        break;
                    }
                    stream.getOutputStream().println("S ::: " + inputMessage);
                    System.out.println("Client " + numClient + ": " + inputMessage);
                }
                if (count1 != 0) {
                    break;
                }

            }
        }
        catch (Exception e)
        {
            System.out.println("Client "+numClient+" crashed");
        }
    }
}