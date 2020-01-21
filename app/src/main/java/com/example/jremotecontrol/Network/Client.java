package com.example.jremotecontrol.Network;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private String servIP;
    private String servPort;
    private boolean isConn;
    private Package pack;
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;

    public Client(){
        servPort = "8090";
        isConn = false;
    }

    public Client(String port){
        servPort = port;
        isConn = false;
    }

    private String getSubnetAddress(int address)
    {
        return String.format("%d.%d.%d", (address & 0xff), (address >> 8 & 0xff), (address >> 16 & 0xff)); // IP
    }

    private boolean findServerIp(String subnet)
    {
        int timeout = 200;
        servIP = null;
        for (int i=1; i<255; i++)
        {
            String host = subnet + "." + i;
            try{
                socket = new Socket();
                socket.connect(new InetSocketAddress(host, Integer.parseInt(servPort)), timeout);
                servIP = host;
                Log.d("INFO", "findServerIp() :: " + host + " is server");
                break;
            }catch (Exception e){
                Log.d("INFO", "findServerIp() :: " + host + " is not server");
            }
        }

        if(servIP == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public void connect( WifiManager mWifiManager){
        WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();
        String subnet = getSubnetAddress(mWifiManager.getDhcpInfo().gateway);
        Log.d("INFO", "subnet "+ subnet);
        if(findServerIp(subnet) == true) {
            isConn = true;
            Log.d("INFO", "connected");
            try {
                outputStream = socket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
            } catch (IOException e){
                isConn = false;
                e.printStackTrace();
            }
        }
        else{
            isConn = false;
            Log.d("INFO", "no connection with server");
        }
    }

    public void disConnect(){
        isConn = false;
        try {
            if(objectOutputStream != null) {
                objectOutputStream.close();
            }
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isConn() {
        return isConn;
    }

    public void send() {
        try {
            objectOutputStream.writeObject(pack);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }
}
