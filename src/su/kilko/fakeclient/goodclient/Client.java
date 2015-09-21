package su.kilko.fakeclient.goodclient;

import java.io.IOException;

/**
 * Created by Kosilov Nikita on 19.08.2015.
 */
public class Client {
    ServerConnector serverConnector;
    ResponseProcessor responseProcessor;

    public void connect(String host) throws Exception{
        System.out.println("Connecting to... " + host);
        serverConnector = new ServerConnector(host);
    }

    public void disconnect()throws Exception{
        serverConnector.close();
    }

    public void sendToServer(Object request) throws IOException{
        serverConnector.send(request);
    }
    public Object getResponse() throws IOException{
        return serverConnector.getResponse();
    }
}
