package fake_server;

import stream_for_client_and_server.stream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 1 on 26.07.2015.
 */
public class ServerWorkClass {
    public static void start_server() throws IOException{
        System.out.println("Welcome to Server side");
        ServerSocket servers = null;
        Socket fromclient = null;

        // create server socket
        try {
            servers = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }

        int count=0;
        while (true) {
            if (count == 0) {
                try {
                    System.out.print("Waiting for a client...");
                    fromclient = servers.accept();
                    System.out.println("Client connected");
                } catch (IOException e) {
                    System.out.println("Can't accept");
                    System.exit(-1);
                }
            } else {
                System.out.println("Server shutdown at the request of client");
                break;
            }

            stream stream = new stream();
            stream.make_stream(fromclient);

            String input;

            System.out.println("Wait for messages");

            while (true) {
                input = stream.getIn().readLine();
                if (input.equalsIgnoreCase("exit")) {
                    stream.out.close();
                    stream.in.close();
                    fromclient.close();
                    servers.close();
                    count++;
                    break;
                }
                if(input.equalsIgnoreCase("close"))
                {
                    System.out.println("Client was been disconnected");
                    fromclient.close();
                    break;
                }
                stream.out.println("S ::: " + input);
                System.out.println(input);
            }

        }
    }
}
