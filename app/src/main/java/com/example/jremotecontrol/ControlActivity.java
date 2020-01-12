package com.example.jremotecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.jremotecontrol.Abstract.AppConstants;

public class ControlActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

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

       if(eventAction == MotionEvent.ACTION_UP){
           //Release
           imageView.setImageResource(R.drawable.m1);
       }
       else{
           //Press
           if(evHeight < (ivHeight/4)){
                if(evWidth >= (ivWidth/4) && evWidth < (ivWidth * 3/4)) {
                    imageView.setImageResource(R.drawable.m2);

                    // AppConstants.getClient().setPack(pack);
                    //AppConstants.getClient().execute();
                }
           }
           else if(evHeight >= (ivHeight/4) && evHeight < (ivHeight * 3/4)){
               if(evWidth <= (ivWidth/4)){
                   imageView.setImageResource(R.drawable.m5);
               }
                else if(evWidth >= (ivWidth * 3/4)) {
                   imageView.setImageResource(R.drawable.m3);
               }
           }
           else if(evHeight >= (ivHeight * 3/4)){
               if(evWidth >= (ivWidth/4) && evWidth < (ivWidth * 3/4)) {
                   imageView.setImageResource(R.drawable.m4);
               }
           }
       }
    }

}
