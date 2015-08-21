package su.kilko.fakeclient.goodclient;

import java.net.Socket;

/**
 * Created by Kosilov Nikita on 19.08.2015.
 */
public class Client {
    private Socket socketToServer;
    ServerConnector serverConnector= new ServerConnector();

    public void connect(String host) throws Exception{
        System.out.println("Connecting to... " + host);

        socketToServer= new Socket(host, 4444);
    }


    public void disconnect()throws Exception{
        socketToServer.close();
    }


    public void doRequest(String request)throws Exception{
        serverConnector.send(request, socketToServer);
    }
}
