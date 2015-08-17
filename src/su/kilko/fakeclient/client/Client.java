package su.kilko.fakeclient.client;

import org.apache.log4j.Logger;
import su.kilko.fakeclient.services.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Kosilov Nikita on 26.07.2015.
 */

public class Client {
    private static Stream stream;
    private static ClientState clientState = new ClientState(false);
    private static String request;
    public static final Logger log =Logger.getLogger(Client.class);
    public static void run() throws IOException, NullPointerException {
        System.out.println("Welcome to Client side.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("To whom you want to connect?");
        while (true) {
            String host = reader.readLine();
            if (!host.isEmpty()) {
                try {
                    ClientStartConnect.run(host);
                    MessageController.run();
                    log.info("Programm closed by user");
                    break;
                } catch (Exception e) {
                    log.error("Exception: ", e);
                    if(clientState.isShutdownClient()) {
                        break;
                    }
                    System.out.println("Unknown host. Please try again");
                }
            } else {
                System.out.println("You entered an empty value");
            }
        }
    }


    public static Stream getStream() {
        return stream;
    }


    public static void setStream(Stream stream) {
        Client.stream = stream;
    }


    public static ClientState getClientState() {
        return clientState;
    }


    public static String getRequest() {
        return request;
    }



    public static void setRequest(String request) {
        Client.request = request;
    }

}
