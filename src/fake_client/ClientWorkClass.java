package fake_client;

import stream_for_client_and_server.stream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by 1 on 26.07.2015.
 */
public class ClientWorkClass {
    public static void start_client() throws IOException {
        System.out.println("Welcome to Client side");
        System.out.println("To whom you want to connect?");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String host = reader.readLine();
            if (!host.isEmpty()) {
                try {
                    start_connect(host);
                    break;
                } catch (Exception e) {
                    System.out.println("Unknown host. Please try again");
                }

            } else {
                System.out.println("You entered an empty value");
            }
        }
    }

    private static void start_connect(String host) throws IOException, NullPointerException {
        System.out.println("Connecting to... " + host);

        Socket fromserver = new Socket(host, 4444);
        stream stream = new stream(fromserver);

        System.out.println("The connection was successful. Enter your message");
        get_message(stream);
    }

    private static void get_message(stream stream) throws IOException {
        String fuser, fserver;
        while (true) {
            fuser = stream.inu.readLine();
            stream.out.println(fuser);
            fserver = stream.in.readLine();
            if (fuser.equalsIgnoreCase("close")) {
                System.out.println("Bye!");
                break;
            }
            if (fuser.equalsIgnoreCase("exit")) {
                System.out.println("Bye!");
                break;
            }
            System.out.println(fserver);
        }
        stream.out.close();
        stream.in.close();
        stream.inu.close();
    }
}
