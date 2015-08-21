package su.kilko.fakeclient.goodclient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


// * Created by Kosilov Nikita on 19.08.2015.

public class ServerConnector  {

    private Socket serverSocket;


    public void send(Object request) throws IOException {

        PrintWriter sendToServer = new PrintWriter(serverSocket.getOutputStream(), true);
        BufferedReader messageFromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

        String response;

        ClientLauncher.log.info("Sending request to the server");
        sendToServer.println(request);

        if (!ClientLauncher.isShutdownClient()) {
            response = messageFromServer.readLine();
            ClientLauncher.log.info(String.format("Get response: %s", response));

            System.out.println(response);
        } else {
            sendToServer.close();
            messageFromServer.close();
        }
    }

        public ServerConnector(Socket socket){
            this.serverSocket=socket;
        }



}
