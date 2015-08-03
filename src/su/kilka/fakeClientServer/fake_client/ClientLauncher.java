package su.kilka.fakeClientServer.fake_client;
import java.io.IOException;


public class ClientLauncher {
    public static void main(String[] args) throws IOException, NullPointerException {
        Client client=new Client();
                client.startClient();
    }
}
