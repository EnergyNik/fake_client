package su.kilko.fakeclient.client;

import java.io.IOException;

/**
 * Created by Kosilov Nikita on 17.08.2015.
 */
public class ResponseMessage {
    public static void response(String request) throws IOException{
        responseer(request);
    }

    private static void responseer(String request)throws IOException {
        String response;
        Client.log.info("Sending request to the server");
        Client.getStream().getOutputStream().println(request);
        if(!Client.getClientState().isShutdownClient()) {
            response = Client.getStream().getInputStream().readLine();
            Client.log.info(String.format("Get response: %s", response));
            System.out.println(response);
        }
    }
}
