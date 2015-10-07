package su.kilko.fakeclient.goodclient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


// * Created by Kosilov Nikita on 19.08.2015.

public class ServerConnector {

    private Socket serverSocket;

    private PrintWriter output;
    private BufferedReader input;

    public ServerConnector(String host) throws IOException {
        serverSocket = new Socket(host, 4444);
        output = new PrintWriter(serverSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
    }

    public void close() throws IOException {
        output.close();
        input.close();
    }

    public Object getResponse(Object request) throws IOException {
        ClientLauncher.log.info("Sending request to the server");
        output.println(request);
        Object response = input.readLine();
        return response;
    }



}
