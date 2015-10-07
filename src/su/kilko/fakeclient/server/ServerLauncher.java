package su.kilko.fakeclient.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerLauncher {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        Server.startServer();
    }
}