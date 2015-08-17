package su.kilko.fakeclient.client;

import su.kilko.fakeclient.services.Stream;

import java.net.Socket;

/**
 * Created by Kosilov Nikita on 17.08.2015.
 */
public class ClientStartConnect {
    public static void run(String host) throws Exception{
        connect(host);
    }

    private static void connect(String host) throws Exception{
        System.out.println("Connecting to... " + host);

        Client.setStream(new Stream(new Socket(host, 4444))) ;

        System.out.println("The connection was successful.");

    }
}
