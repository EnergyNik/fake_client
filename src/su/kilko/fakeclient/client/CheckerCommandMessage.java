package su.kilko.fakeclient.client;

import java.io.IOException;

/**
 * Created by Kosilov Nikita on 17.08.2015.
 */
public class CheckerCommandMessage {
    public static void check(String request) throws IOException{
        checker(request);
    }

    public static void checker(String request) throws IOException {
        if (request.equalsIgnoreCase("close") || request.equalsIgnoreCase("exit")) {
            System.out.println("Bye!");

            Client.getClientState().setIsShutdownClient(true);
        }
    }
}
