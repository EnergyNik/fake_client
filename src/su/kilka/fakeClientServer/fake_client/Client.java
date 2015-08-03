package su.kilka.fakeClientServer.fake_client;

import su.kilka.fakeClientServer.services.Stream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Kosilov Nikita on 26.07.2015.
 */
public class Client {
    public  void startClient() throws IOException, NullPointerException {
        System.out.println("Welcome to Client side.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("To whom you want to connect?");
        while (true) {
            String host = reader.readLine();
            if (!host.isEmpty()) {
                try {
                    startConnect(host);
                    break;
                } catch (Exception e) {
                    System.out.println("Unknown host. Please try again");
                }

            } else {
                System.out.println("You entered an empty value");
            }
        }
    }

    private static void startConnect(String host) throws IOException, NullPointerException {
        System.out.println("Connecting to... " + host);

        Stream stream = new Stream(new Socket(host, 4444));

        System.out.println("The connection was successful.");
        getMessage(stream);
    }

    private static void getMessage(Stream stream) throws IOException {
        System.out.println("Enter your message...");
        String fromUser, fromServer;
        while (true) {
            fromUser = stream.getInputStreamUser().readLine();
            stream.getOutputStream().println(fromUser);
            fromServer = stream.getInputStream().readLine();
            if (fromUser.equalsIgnoreCase("close")) {
                System.out.println("Bye!");
                break;
            }
            if (fromUser.equalsIgnoreCase("exit")) {
                System.out.println("Bye!");
                break;
            }
            System.out.println(fromServer);
        }
        stream.getOutputStream().close();
        stream.getInputStream().close();
        stream.getInputStreamUser().close();
    }

}
