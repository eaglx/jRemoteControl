package com.example.jremotecontrol.Network;

import android.os.AsyncTask;

public class Client extends AsyncTask<Void, Void, Void> {

    private String servIP;
    private String servPort;
    private boolean isConn;
    private Package pack;

    public Client(){
        servPort = "8090";
        isConn = false;
    }

    public Client(String port){
        servPort = port;
        isConn = false;
    }

    public void Connect(){
        isConn = false;
    }

    public void DisConnect(){
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
