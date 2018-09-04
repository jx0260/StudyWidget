package com.jinll.studywidget.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.widget.TextView;

import com.jinll.studywidget.R;
import com.onetime.nww.util.DialogUtil;

/**
 * Created by Jin Liang on 2018/9/4.
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDialog appCompatDialog1 = DialogUtil.showTransparentDialog(this, R.layout.dialog_transparent);
        TextView tv = appCompatDialog1.findViewById(R.id.tv_dialog_title);
        tv.setText("NINININI");

        AppCompatDialog appCompatDialog2 = DialogUtil.showTranslucentDialog(this, R.layout.dialog_transparent);
    }
}
