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
    private static String request;
    //private static final Logger log =Logger.getLogger(Client.class);
    private static boolean isShutdownClient = false;
    //private static boolean flagRequestMessage=true;
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
                    //log.error("Exception: ", e);
                    if(isShutdownClient) {
                        //log.info("Programm closed by user");
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
        controlMessage();
    }
    private static void controlMessage() throws Exception{
        System.out.println("Enter your message...");
        while (!isShutdownClient) {
            requestMessage();
            checkCommandMessage(request);
            responseMessage(request);
        }
        streamCloser();
    }

    private static void requestMessage() throws Exception {
            request = stream.getInputStreamUser().readLine();
            //log.info(String.format("Get request: %s", request));
    }

    private static boolean checkCommandMessage(String request) throws IOException {
        if (request.equalsIgnoreCase("close") || request.equalsIgnoreCase("exit")) {
            System.out.println("Bye!");

            isShutdownClient =true;
        }
        return isShutdownClient;
    }

    private static void responseMessage(String request)throws IOException{
        String response;
        //log.info("Sending request to the server");
        stream.getOutputStream().println(request);
        if(!isShutdownClient) {
            response = stream.getInputStream().readLine();
            //log.info(String.format("Get response: %s", response));
            System.out.println(response);
        }
    }

    private static void streamCloser()throws IOException{
        stream.getOutputStream().close();
        stream.getInputStream().close();
        stream.getInputStreamUser().close();
    }
}
