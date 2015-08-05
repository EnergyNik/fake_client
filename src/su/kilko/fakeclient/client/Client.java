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
    private static boolean shutdownClient=false;
    private static boolean flagRequestMessage=true;
    private static boolean flagCommandMessage=false;
    public static void run() throws IOException, NullPointerException {
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
                    if(shutdownClient) {
                        break;
                    }
                    System.out.println("Unknown host. Please try again");
                }
            } else {
                System.out.println("You entered an empty value");
            }
        }
    }

    private static void startConnect(String host) throws Exception{
        System.out.println("Connecting to... " + host);

        stream = new Stream(new Socket(host, 4444));

        System.out.println("The connection was successful.");
        requestMessage(stream);
    }
    private static void requestMessage(Stream stream) throws Exception {
        System.out.println("Enter your message...");
        String request;
        while (flagRequestMessage) {
            request = stream.getInputStreamUser().readLine();
            checkCommandMessage(request);
        }
    }

    private static void checkCommandMessage(String request) throws IOException {
        if (request.equalsIgnoreCase("close") || request.equalsIgnoreCase("exit")) {
            System.out.println("Bye!");

            shutdownClient=true;
            flagCommandMessage=true;

            responseMessage(request);

            streamCloser();
            flagRequestMessage=false;
        }
        responseMessage(request);
    }

    private static void responseMessage(String request)throws IOException{
        String response;
        stream.getOutputStream().println(request);
        if(flagCommandMessage==false) {
            response = stream.getInputStream().readLine();
            System.out.println(response);
        }
    }

    private static void streamCloser()throws IOException{
        stream.getOutputStream().close();
        stream.getInputStream().close();
        stream.getInputStreamUser().close();
    }
}
