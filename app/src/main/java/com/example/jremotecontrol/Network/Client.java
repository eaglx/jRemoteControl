package com.example.jremotecontrol.Network;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends AsyncTask<Void, Void, Void> {

    private String servIP;
    private String servPort;
    private boolean isConn;
    private Package pack;
    private Socket socket;

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

    private void checkHosts(String subnet)
    {
        try
        {
            int timeout=5;
            for (int i=1;i<255;i++)
            {
                String host = subnet + "." + i;
                if (InetAddress.getByName(host).isReachable(timeout))
                {
                    Log.d("INFO", "checkHosts() :: "+host + " is reachable");
                }
                else{
                    Log.d("INFO", "checkHosts() :: "+host + " is not reachable");
                }
            }
        }
        catch (UnknownHostException e)
        {
            Log.d("ERROR", "checkHosts() :: UnknownHostException e : "+e);
            e.printStackTrace();
        }
        catch (IOException e)
        {
            Log.d("ERROR", "checkHosts() :: IOException e : "+e);
            e.printStackTrace();
        }
    }

    public void connect(Context mContext){
        WifiManager mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();
        String subnet = getSubnetAddress(mWifiManager.getDhcpInfo().gateway);
        Log.d("INFO", "subnet "+ subnet);
        //checkHosts(subnet);
        //isConn = true;
        try {
            socket = new Socket("192.168.1.12", 8090);
            Log.d("INFO", "connected");
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            Package messages = new Package(2,3);
            objectOutputStream.writeObject(messages);
            objectOutputStream.flush();
            socket.close();
            Log.d("INFO", "disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disConnect(){
        isConn = false;
    }

    public boolean isConn() {
        return isConn;
    }

    @Override
    protected void onPreExecute() {
        ;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        ;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }
}
