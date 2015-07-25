import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException, NullPointerException {

        System.out.println("Welcome to Client side");
        System.out.println("To whom you want to connect?");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String host = reader.readLine();
            if (!host.isEmpty()) {
                try {
                    fclient(host);
                    break;
                }
                catch (Exception e)
                {
                    System.out.println("Unknown host. Please try again");
                }

            }
            else {
                System.out.println("You entered an empty value");
            }
        }


    }
    public static void fclient(String host)throws IOException, NullPointerException{
        Socket fromserver = null;
     /*   if (host==null) {
            System.out.println("use: client hostname");
            System.exit(-1);
        }*/

       // System.out.println("Connecting to... "+host);


            fromserver = new Socket(host , 4444);
            BufferedReader in = new
                    BufferedReader(new
                    InputStreamReader(fromserver.getInputStream()));
            PrintWriter out = new
                    PrintWriter(fromserver.getOutputStream(), true);
            BufferedReader inu = new
                    BufferedReader(new InputStreamReader(System.in));


        System.out.println("The connection was successful. Enter your message");
        String fuser,fserver;
        while (true)
        {
            fuser = inu.readLine();
            out.println(fuser);
            fserver = in.readLine();
            if (fuser.equalsIgnoreCase("close")) {System.out.println("Bye!"); break;}
            if (fuser.equalsIgnoreCase("exit")){System.out.println("Bye!"); break;}
            System.out.println(fserver);




        }

        out.close();
        in.close();
        inu.close();
        fromserver.close();

    }
}
