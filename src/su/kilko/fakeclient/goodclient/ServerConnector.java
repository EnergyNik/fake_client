package su.kilko.fakeclient.goodclient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


// * Created by Kosilov Nikita on 19.08.2015.

public class ServerConnector {

    private Socket serverSocket;

    private PrintWriter sendToServer;
    private BufferedReader messageFromServer;

    public void sendToServer(Object request) throws IOException {
        ClientLauncher.log.info("Sending request to the server");
        sendToServer.println(request);

    }

    public ServerConnector(String host) throws IOException {
        serverSocket = new Socket(host, 4444);
        sendToServer = new PrintWriter(serverSocket.getOutputStream(), true);
        messageFromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
    }

    public void close() throws IOException {
        sendToServer.close();
        messageFromServer.close();
    }

    public Object getResponse() throws IOException {
        Object response = messageFromServer.readLine();
        return response;
    }



}
