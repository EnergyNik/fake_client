package su.kilko.fakeclient.goodclient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


// * Created by Kosilov Nikita on 19.08.2015.

public class ServerConnector {

    public void send(Object request, Socket socketToServer) throws IOException {

        PrintWriter sendToServer = new PrintWriter(socketToServer.getOutputStream(), true);
        BufferedReader messageFromServer = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()));

        String response;

        ClientLauncher.log.info("Sending request to the server");
        sendToServer.println(request);

        if (!ClientLauncher.isShutdownClient()) {
            response = messageFromServer.readLine();
            ClientLauncher.log.info(String.format("Get response: %s", response));

            System.out.println(response);
        }
        else {
        sendToServer.close();
        messageFromServer.close();
        }


    }
}
