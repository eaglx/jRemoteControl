package com.example.jremotecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jremotecontrol.Abstract.AppConstants;

import java.util.concurrent.TimeUnit;


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

        cs = ConnectionStatus.UNCONNECTED;

        progbar = findViewById(R.id.progressBar);
        progbar.setVisibility(View.GONE);

        bconn = findViewById(R.id.connbutton);
        bconn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cs == ConnectionStatus.UNCONNECTED || cs == ConnectionStatus.FAILED) {
                    progbar.setVisibility(View.VISIBLE);
                    bconn.setText(R.string.connbuttonInConn);
                    bconn.setTextColor(Color.parseColor("#f5ba18"));
                    AppConstants.getClient().Connect();
                    if(AppConstants.getClient().isConn()) {
                        bconn.setTextColor(Color.parseColor("#1673B1"));
                        bconn.setText(R.string.connbuttonDisConn);
                        cs = ConnectionStatus.CONNECTED;
                        progbar.setVisibility(View.GONE);
                        Intent newIntent = new Intent(ConnectActivity.this, ControlActivity.class);
                        startActivity(newIntent);
                    }
                    else{
                        bconn.setText(R.string.connbuttonErrorConn);
                        bconn.setTextColor(Color.parseColor("#fc2003"));
                        cs = ConnectionStatus.FAILED;
                        progbar.setVisibility(View.GONE);
                    }
                }
                else if(cs == ConnectionStatus.CONNECTED){
                    AppConstants.getClient().DisConnect();
                    bconn.setText(R.string.connbuttonConn);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(AppConstants.getClient().isConn()) {
            AppConstants.getClient().DisConnect();
        }
        finishAndRemoveTask();
    }
}
