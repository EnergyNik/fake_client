package su.kilko.fakeclient.goodclient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


// * Created by Kosilov Nikita on 19.08.2015.

public class ServerConnector  {

    private Socket serverSocket;

    private PrintWriter sendToServer;
    private BufferedReader messageFromServer;

    public Object send(Object request) throws IOException {
        ClientLauncher.log.info("Sending request to the server");
        sendToServer.println(request);

        String response = messageFromServer.readLine();
        ClientLauncher.log.info(String.format("Get response: %s", response));

        return response;
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



}
