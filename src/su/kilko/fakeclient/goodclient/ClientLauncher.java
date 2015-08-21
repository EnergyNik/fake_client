package su.kilko.fakeclient.goodclient;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Kosilov Nikita on 19.08.2015.
 */
public class ClientLauncher {

    public static final Logger log = Logger.getLogger(ClientLauncher.class);
    private static boolean isShutdownClient;

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Client side.");

        Client client = new Client();

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
            String request = reader.readLine();
            if (request.equalsIgnoreCase("close") || request.equalsIgnoreCase("exit")) {
                System.out.println("Bye!");
                isShutdownClient=true;
                reader.close();
                client.doRequest(request);
                client.disconnect();
                break;
            }
            client.doRequest(request);

        }

    }


    public static boolean isShutdownClient(){
        return isShutdownClient;
    }
}