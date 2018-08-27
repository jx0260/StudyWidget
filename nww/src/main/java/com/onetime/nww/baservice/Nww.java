package com.onetime.nww.baservice;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Jin Liang on 2018/8/22.
 */
public class Nww {

    static WindowManager windowManager;

    public static void init(Context context){
        windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
    }

}
