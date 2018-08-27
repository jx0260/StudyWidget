package com.jinll.studywidget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by Jin Liang on 2017/8/12.
 */

public class TestAdjustPanActivity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_adjustpan);

        wv = (WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);

        wv.loadUrl("file:///android_asset/example.html");
    }

}
