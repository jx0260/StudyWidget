package com.onetime.nww.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jin Liang on 2018/8/22.
 */

public class ToastUtil {

    public static void show(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, String msg, int duration){
        Toast.makeText(context, msg, duration).show();
    }

}
