package com.example.jremotecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jremotecontrol.Abstract.AppConstants;
import com.example.jremotecontrol.Network.Package;

public class ControlActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button bLeft;
    private Button bRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        bLeft = findViewById(R.id.leftbutton);
        bLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AppConstants.getClient().setPack(new Package(Package.MouseBtn.LEFT));
                AppConstants.getClient().send();
            }
        });

        bRight = findViewById(R.id.rightbutton);
        bRight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AppConstants.getClient().setPack(new Package(Package.MouseBtn.RIGHT));
                AppConstants.getClient().send();
            }
        });

        imageView = findViewById(R.id.imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageViewOnTouch(v, event);
                return true;
    }
});
    }

    private void ImageViewOnTouch(View v, MotionEvent event){
//        Log.d("X", String.valueOf(event.getX()));
//        Log.d("Y", String.valueOf(event.getY()));
        int eventAction = event.getAction();
        int ivWidth = v.getWidth();
        int ivHeight = v.getHeight();
        float evWidth = event.getX();
        float evHeight = event.getY();

        //Log.d("DUPA", "" + eventAction);

       if(eventAction == MotionEvent.ACTION_UP){
           //Release
           imageView.setImageResource(R.drawable.m1);
       }
       else{
           //Press

           if(evHeight < (ivHeight/4)){
                if(evWidth >= (ivWidth/4) && evWidth < (ivWidth * 3/4)) {
                    imageView.setImageResource(R.drawable.m2);
                    AppConstants.getClient().setPack(new Package(0,-1 * eventAction));
                    AppConstants.getClient().send();
                }
           }
           else if(evHeight >= (ivHeight/4) && evHeight < (ivHeight * 3/4)){
               if(evWidth <= (ivWidth/4)){
                   imageView.setImageResource(R.drawable.m5);
                   AppConstants.getClient().setPack(new Package(-1 * eventAction,0));
                   AppConstants.getClient().send();
               }
                else if(evWidth >= (ivWidth * 3/4)) {
                   imageView.setImageResource(R.drawable.m3);
                   AppConstants.getClient().setPack(new Package(1 * eventAction,0));
                   AppConstants.getClient().send();
               }
           }
           else if(evHeight >= (ivHeight * 3/4)){
               if(evWidth >= (ivWidth/4) && evWidth < (ivWidth * 3/4)) {
                   imageView.setImageResource(R.drawable.m4);
                   AppConstants.getClient().setPack(new Package(0,1 * eventAction));
                   AppConstants.getClient().send();
               }
           }
       }
    }

}
