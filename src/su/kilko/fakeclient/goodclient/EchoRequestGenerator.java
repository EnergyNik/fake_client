package su.kilko.fakeclient.goodclient;


import java.io.IOException;

/**
 * Created by Kosilov Nikita on 15.09.2015.
 */
public class EchoRequestGenerator implements RequestGenerator {
    public Object convertRequest(Object message) throws IOException{
        return message;
    }

}
