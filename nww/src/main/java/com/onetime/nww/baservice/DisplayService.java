package com.onetime.nww.baservice;

import android.util.DisplayMetrics;

/**
 * Created by Jin Liang on 2018/8/22.
 */
public class DisplayService {

    public static DisplayMetrics getDisplayMetrics(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Nww.windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static DisplayMetrics getRealDisplayMetrics(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Nww.windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }
}
