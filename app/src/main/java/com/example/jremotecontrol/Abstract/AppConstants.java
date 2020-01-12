package com.example.jremotecontrol.Abstract;

import com.example.jremotecontrol.Network.Client;

public abstract class AppConstants {
    private static Client client = null;

    public static Client getClient() {
        if(client == null) {
            client = new Client();
        }
        return client;
    }

    public static Client getClient(String port) {
        if(client == null) {
            client = new Client(port);
        }
        return client;
    }
}
