package stream_for_client_and_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by 1 on 27.07.2015.
 */
public class stream {
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader inu;

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getInu() {
        return inu;
    }

    public  void make_stream(Socket fromserver) throws IOException {
        this.in = new
                BufferedReader(new
                InputStreamReader(fromserver.getInputStream()));
        this.out = new
                PrintWriter(fromserver.getOutputStream(), true);
        this.inu = new
                BufferedReader(new InputStreamReader(System.in));

    }
}

