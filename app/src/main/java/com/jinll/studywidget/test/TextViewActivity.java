package com.jinll.studywidget.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jinll.studywidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jin Liang on 2017/9/26.
 */

public class TextViewActivity extends AppCompatActivity {

    @BindView(R.id.tv_n1)
    TextView n1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_textview_test);

        ButterKnife.bind(this);

        TextViewCompat.setTextAppearance(n1, android.R.style.TextAppearance_DeviceDefault_WindowTitle);
    }

}
