package com.example.jremotecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jremotecontrol.Abstract.AppConstants;


public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        Button bconn = findViewById(R.id.connbutton);
        bconn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConstants.getClient();
                Intent newIntent = new Intent(ConnectActivity.this, ControlActivity.class);
                startActivity(newIntent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        //client.close();
        finishAndRemoveTask();
    }
}
