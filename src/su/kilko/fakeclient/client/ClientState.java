package su.kilko.fakeclient.client;

/**
 * Created by Kosilov Nikita on 09.08.2015.
 */
public class ClientState {
    private boolean isShutdownClient;
    public ClientState(boolean isShutdownClient){
        this.isShutdownClient=isShutdownClient;
    }
    public boolean isShutdownClient() {
        return isShutdownClient;
    }

    public void setIsShutdownClient(boolean isShutdownClient) {
        this.isShutdownClient = isShutdownClient;
    }

}
