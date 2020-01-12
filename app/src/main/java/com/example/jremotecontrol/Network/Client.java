package com.example.jremotecontrol.Network;

public class Client {

    private String servIP;
    private String servPort;

    public Client(){
        servPort = "8090";
    }

    public Client(String port){
        servPort = port;
    }

    public boolean Connect(){

        return true;
    }
}
