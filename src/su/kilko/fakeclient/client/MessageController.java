package su.kilko.fakeclient.client;

import java.io.IOException;

/**
 * Created by Kosilov Nikita on 17.08.2015.
 */
public class MessageController {
    public static void run() throws Exception{
        connecter();
    }
    private static void connecter() throws Exception{
        System.out.println("Enter your message...");
        while (!Client.getClientState().isShutdownClient()) {
            CheckerCommandMessage.check(RequestMessage.message());
            ResponseMessage.response(Client.getRequest());
        }
        streamCloser();
    }
    private static void streamCloser()throws IOException {
        Client.getStream().getOutputStream().close();
        Client.getStream().getInputStream().close();
        Client.getStream().getInputStreamUser().close();
    }
}
