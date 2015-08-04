package su.kilko.fakeclient.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Kosilov Nikita on 27.07.2015.
 */
public class Stream {
    private BufferedReader inputStream;
    private PrintWriter outputStream;
    private BufferedReader inputStreamUser;

    public BufferedReader getInputStream() {
        return inputStream;
    }

    public PrintWriter getOutputStream() {
        return outputStream;
    }

    public BufferedReader getInputStreamUser() {
        return inputStreamUser;
    }



    public Stream(Socket socketFromServer) throws IOException {
        this.inputStream = new BufferedReader(new InputStreamReader(socketFromServer.getInputStream()));
        this.outputStream = new PrintWriter(socketFromServer.getOutputStream(), true);
        this.inputStreamUser = new BufferedReader(new InputStreamReader(System.in));
    }
}

