package su.kilko.fakeclient.client;

/**
 * Created by Kosilov Nikita on 17.08.2015.
 */
public class RequestMessage {
    public static String message() throws Exception {
        Client.setRequest(Client.getStream().getInputStreamUser().readLine());
        Client.log.info(String.format("Get request: %s", Client.getRequest()));
        return Client.getRequest();
    }



}
