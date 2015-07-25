import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException, NullPointerException {
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
            if(count==0) {
                try {
                    System.out.print("Waiting for a client...");
                    fromclient = servers.accept();
                    System.out.println("Client connected");
                } catch (IOException e) {
                    System.out.println("Can't accept");
                    System.exit(-1);
                }
            }
            else
            {
                System.out.println("Server shutdown at the request of client");
                break;
            }

            BufferedReader in = new BufferedReader(new
                    InputStreamReader(fromclient.getInputStream()));
            PrintWriter out = new PrintWriter(fromclient.getOutputStream(), true);
            String input, output;

            System.out.println("Wait for messages");

            while (true) {
                input = in.readLine();
                if (input.equalsIgnoreCase("exit")) {
                    out.close();
                    in.close();
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
                out.println("S ::: " + input);
                System.out.println(input);
                }


        }
    }
}