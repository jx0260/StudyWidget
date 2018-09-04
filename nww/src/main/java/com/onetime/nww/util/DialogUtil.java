package com.onetime.nww.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.ViewGroup;

import com.onetime.nww.R;

/**
 * Dialog工具类
 *
 * Created by Jin Liang on 2018/9/4.
 */
public class DialogUtil {

    /**
     * 背景透明的dialog
     * @param context
     * @param layoutId
     * @return
     */
    public static AppCompatDialog showTransparentDialog(Context context, int layoutId){
        AppCompatDialog dialog = new AppCompatDialog(context, R.style.nww_dialog);
        dialog.setContentView(layoutId);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    /**
     * 背景半透明的dialog
     * @return
     */
    public static AppCompatDialog showTranslucentDialog(Context context, int layoutId){
        AppCompatDialog dialog = new AppCompatDialog(context, R.style.nww_dialog);
        dialog.setContentView(layoutId);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#88000000")));
        return dialog;
    }

}
