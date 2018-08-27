package com.jinll.studywidget.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.jinll.studywidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jin Liang on 2017/9/26.
 */

public class GestureActivity extends AppCompatActivity {

    @BindView(R.id.ll_gesture_container)
    LinearLayout mContainLl;

    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gesture);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        ButterKnife.bind(this);

        mContainLl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                mDetector.onTouchEvent(event);

                int actionMasked = MotionEventCompat.getActionMasked(event);
                switch(actionMasked){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Event", "MotionEvent down");
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("Event", "MotionEvent move");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("Event", "MotionEvent up");
                        break;
                }

                return false;
//                return GestureActivity.super.onTouchEvent(event);
            }
        });
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        this.mDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
//    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d(DEBUG_TAG,"onSingleTapUp: " + e.toString());
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d(DEBUG_TAG,"onLongPress: " + e.toString());
            super.onLongPress(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d(DEBUG_TAG,"onDoubleTap: " + e.toString());
            return super.onDoubleTap(e);
        }
    }
}
