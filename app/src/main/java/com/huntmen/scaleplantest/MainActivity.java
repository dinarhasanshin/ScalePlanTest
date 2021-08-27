package com.huntmen.scaleplantest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

//    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor;
    private RelativeLayout linearLayout;
    public float xDown, yDown;

    private Button plusButton;
    private Button minusButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (RelativeLayout) findViewById(R.id.scale_layout);

        plusButton = (Button) findViewById(R.id.plusButton);
        minusButton = (Button) findViewById(R.id.minusButton);

        scaleFactor = linearLayout.getScaleX();

//        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scaleFactor >= 1.0f) {
                    scaleFactor = scaleFactor + 0.2f;
                    linearLayout.setScaleX(scaleFactor);
                    linearLayout.setScaleY(scaleFactor);
                }

            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scaleFactor > 1.0f) {
                    scaleFactor = scaleFactor - 0.2f;
                    linearLayout.setScaleX(scaleFactor);
                    linearLayout.setScaleY(scaleFactor);
                }

            }
        });

        linearLayout.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getActionMasked()){

                    case MotionEvent.ACTION_DOWN:
                        xDown = event.getX();
                        yDown = event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float movedX, movedY;
                        movedX = event.getX();
                        movedY = event.getY();

                        float distanceX = movedX - xDown;
                        float distanceY = movedY- yDown;

                        linearLayout.setX(linearLayout.getX()+distanceX);
                        linearLayout.setY(linearLayout.getY()+distanceY);
                        break;

                }
                return true;
            }
        });

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        scaleGestureDetector.onTouchEvent(event);
//        return true;
//    }
//
//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
//
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            scaleFactor *= scaleGestureDetector.getScaleFactor();
//
//            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f));
//
//            linearLayout.setScaleX(scaleFactor);
//            linearLayout.setScaleY(scaleFactor);
//
//            return true;
//        }
//    }
}