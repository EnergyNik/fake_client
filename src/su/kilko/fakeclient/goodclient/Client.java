package su.kilko.fakeclient.goodclient;

/**
 * Created by Kosilov Nikita on 19.08.2015.
 */
public class Client {
    ServerConnector serverConnector;
    RequestGenerator requestGenerator;
    ResponseProcessor responseProcessor;

    public Client(RequestGenerator requestGenerator, ResponseProcessor responseProcessor){
        serverConnector = null;
        this.requestGenerator = requestGenerator;
        this.responseProcessor = responseProcessor;
    }

    public void connect(String host) throws Exception{
        serverConnector = new ServerConnector(host);
    }

    public void disconnect()throws Exception{
        checkState();

        serverConnector.close();
    }

    public Object getResponse(int chooseResponse, Object message) throws Exception{
        checkState();

        Object response = serverConnector.getResponse(requestGenerator.convertRequest(message));
        Object finishedResponse = responseProcessor.getApplicationsRequest(response);
        return finishedResponse;
    }

    public boolean isConnected() {
        if(serverConnector == null) {
            return false;
        }

        return true;
    }

    private void checkState() throws Exception {
        if(!isConnected()) {
            throw new Exception("Client wasn't connect to host!");
        }
    }
}
