package com.example.jremotecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jremotecontrol.Abstract.AppConstants;
import com.example.jremotecontrol.Network.Package;


public class ConnectActivity extends AppCompatActivity {

    enum ConnectionStatus {
        UNCONNECTED,
        CONNECTING,
        CONNECTED,
        FAILED
    }

    private ConnectionStatus cs;
    private Button bconn;
    private ProgressBar progbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
//                Intent newIntent = new Intent(ConnectActivity.this, ControlActivity.class);
//                startActivity(newIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        // https://stackoverflow.com/questions/22395417/error-strictmodeandroidblockguardpolicy-onnetwork
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        cs = ConnectionStatus.UNCONNECTED;

        progbar = findViewById(R.id.progressBar);
        progbar.setVisibility(View.INVISIBLE);

        bconn = findViewById(R.id.connbutton);
        bconn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cs == ConnectionStatus.UNCONNECTED || cs == ConnectionStatus.FAILED) {
                    cs = ConnectionStatus.CONNECTING;
                    progbar.setVisibility(View.VISIBLE);
                    bconn.setText(R.string.connbuttonInConn);
                    bconn.setTextColor(Color.parseColor("#f5ba18"));

                    Thread th = new Thread(new Runnable(){
                        @Override
                        public void run() {
                            WifiManager mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                            AppConstants.getClient().connect(mWifiManager);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(AppConstants.getClient().isConn()) {
                                        bconn.setTextColor(Color.parseColor("#1673B1"));
                                        bconn.setText(R.string.connbuttonDisConn);
                                        cs = ConnectionStatus.CONNECTED;
                                        progbar.setVisibility(View.INVISIBLE);
                                        Intent newIntent = new Intent(ConnectActivity.this, ControlActivity.class);
                                        startActivity(newIntent);
                                    }
                                    else{
                                        bconn.setText(R.string.connbuttonErrorConn);
                                        bconn.setTextColor(Color.parseColor("#fc2003"));
                                        cs = ConnectionStatus.FAILED;
                                        progbar.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                        }
                    });
                    th.start();
                }
                else if(cs == ConnectionStatus.CONNECTED){
                    try {
                        AppConstants.getClient().disConnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bconn.setText(R.string.connbuttonConn);
                    AppConstants.setClientNull();
                    cs = ConnectionStatus.UNCONNECTED;
                }
                else if(cs == ConnectionStatus.CONNECTING) {
                    ;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(AppConstants.getClient().isConn()) {
            AppConstants.getClient().disConnect();
        }
        finishAndRemoveTask();
    }
}
