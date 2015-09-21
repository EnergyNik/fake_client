package su.kilko.fakeclient.goodclient;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Created by Kosilov Nikita on 15.09.2015.
 */
public class RequestGenerator {
    public Object getRequest() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Object message = reader.readLine();
        return message;
    }
}
