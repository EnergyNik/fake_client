package su.kilko.fakeclient.goodclient;

/**
 * Created by Kosilov Nikita on 19.08.2015.
 */
public class Client {
    ServerConnector serverConnector;
    RequestGenerator requestGenerator = new RequestGenerator();
    ResponseProcessor responseProcessor = new ResponseProcessor();
    Object finishedResponse;

    public void connect(String host) throws Exception{
        System.out.println("Connecting to... " + host);
        serverConnector = new ServerConnector(host);
    }

    public void disconnect()throws Exception{
        serverConnector.close();
    }

    public Object getResponse(Object message) throws Exception{
        Object request = requestGenerator.convertRequest(message);
        serverConnector.sendToServer(request);
        Object response =  serverConnector.getResponse();
        finishedResponse = responseProcessor.getApplicationsRequest(response);
        return finishedResponse;
    }

}
