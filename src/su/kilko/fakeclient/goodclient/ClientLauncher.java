package su.kilko.fakeclient.goodclient;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Kosilov Nikita on 19.08.2015.
 */
public class ClientLauncher {

    public static final Logger log = Logger.getLogger(ClientLauncher.class);

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Client side.");

        Client client = new Client(
            new RequestGenerator(),
            new EchoRequestGenerator(),
            new ResponseProcessor()
        );


        Object requestOfClient;
        Object finishedResponse;
        int chooseResponse;
        boolean isExit = false;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("To whom you want to connect?");
            String host = reader.readLine();
            while (true) {
                if (!host.isEmpty()) {
                    try {
                        System.out.println("Connecting to... " + host);
                        client.connect(host);
                        System.out.println("The connection was successful.");
                        break;
                    } catch (Exception e) {
                        log.error("Exception: ", e);
                        System.out.println("Unknown host. Please try again");
                    }
                } else {
                    System.out.println("You entered an empty value");
                }
            }


            System.out.println("What kind of request you want to send? 1 = echo, 2 = xml");
            while (true) {
                chooseResponse = Integer.parseInt(reader.readLine());
                if (chooseResponse == 1 || chooseResponse == 2) {
                    System.out.println("You entered " + chooseResponse);
                    break;
                }
                else{
                    System.out.println("You have entered invalid parameter. Please try again");
                }
            }
            System.out.println("Enter your message");
            while (true) {
                requestOfClient = reader.readLine();
                finishedResponse = client.getResponse(chooseResponse, requestOfClient);
                System.out.println(finishedResponse);
                if (requestOfClient.toString().equalsIgnoreCase("close connection")) {
                    break;
                } else if (requestOfClient.toString().equalsIgnoreCase("exit")) {
                    reader.close();
                    isExit = true;
                    break;
                }
            }
            client.disconnect();
            System.out.println("Bye!");
            if(isExit) {
                break;
            }
        }
    }
}