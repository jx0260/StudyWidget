package com.jinll.studywidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;

import butterknife.BindView;

public class AACameraActivity extends AppCompatActivity {

    @BindView(R.id.sfv_study_camera)
    SurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aacamera);






    }
}
