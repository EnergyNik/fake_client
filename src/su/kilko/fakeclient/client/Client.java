package su.kilko.fakeclient.client;

import su.kilko.fakeclient.services.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Kosilov Nikita on 26.07.2015.
 */
public class Client {
    static Stream stream;
    private static int shutdownClient=0;
    public static void startClient() throws IOException, NullPointerException {
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
                        if(shutdownClient>0) break;
                        System.out.println("Unknown host. Please try again");
                    }

                } else {
                    System.out.println("You entered an empty value");
                }
            }

    }

    private static void startConnect(String host) throws Exception{
        System.out.println("Connecting to... " + host);

        Stream stream = new Stream(new Socket(host, 4444));

        System.out.println("The connection was successful.");
        exchangeMessage(stream);
    }
    private static void exchangeMessage(Stream stream) throws Exception
    {
        System.out.println("Enter your message...");
        String request, response;
        while (true) {
            request = stream.getInputStreamUser().readLine();
            stream.getOutputStream().println(request);
            response = stream.getInputStream().readLine();
            messageProcessing(request, response);
        }
    }

    private static void messageProcessing(String request, String response) throws IOException {
        if (request.equalsIgnoreCase("close") || request.equalsIgnoreCase("exit")) {
            System.out.println("Bye!");
            shutdownClient++;
            streamCloser();
        }

        System.out.println(response);

    }
    private static void streamCloser()throws IOException{
        stream.getOutputStream().close();
        stream.getInputStream().close();
        stream.getInputStreamUser().close();
    }

}
