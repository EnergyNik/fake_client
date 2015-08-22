package su.kilko.fakeclient.goodclient;

/**
 * Created by Kosilov Nikita on 19.08.2015.
 */
public class Client {
    ServerConnector serverConnector;

    public void connect(String host) throws Exception{
        System.out.println("Connecting to... " + host);
        serverConnector = new ServerConnector(host);
    }

    public void disconnect()throws Exception{
        serverConnector.close();
    }

    public Object doRequest(String request)throws Exception{
        Object response = serverConnector.send(request);
        if(response == null){
            response = "Client disconnected";
        }

        return response;
    }
}
