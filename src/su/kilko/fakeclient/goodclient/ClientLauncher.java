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

        Client client = new Client();
        RequestGenerator requestGenerator = new RequestGenerator();
        ResponseProcessor responseProcessor = new ResponseProcessor();

        Object requestOfClient;
        Object responseFromServer;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("To whom you want to connect?");
        while (true) {
            String host = reader.readLine();
            if (!host.isEmpty()) {
                try {
                    client.connect(host);
                    System.out.println("The connection was successful.");
                    break;
                }
                catch (Exception e)
                {
                    log.error("Exception: ", e);
                    System.out.println("Unknown host. Please try again");
                }
            } else {
                System.out.println("You entered an empty value");
            }
        }

        System.out.println("Enter your message");
        while (true){
            requestOfClient = requestGenerator.getRequest();
            client.sendToServer(requestOfClient);

            responseFromServer = client.getResponse();
            System.out.println(responseProcessor.getApplicationsRequest(responseFromServer));
            if (requestOfClient.toString().equalsIgnoreCase("close") || requestOfClient.toString().equalsIgnoreCase("exit")) {
                reader.close();
                client.disconnect();
                break;
            }
        }
        System.out.println("Bye!");
    }

}