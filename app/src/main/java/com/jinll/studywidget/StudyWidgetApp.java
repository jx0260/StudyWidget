package com.jinll.studywidget;

import android.app.Application;

import com.onetime.nww.baservice.Nww;
import com.onetime.nww.exception.NwwUncaughtExceptionHandler;

/**
 * Created by Jin Liang on 2018/8/22.
 */

public class StudyWidgetApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Nww.init(this);

        NwwUncaughtExceptionHandler.getInstance().init(this);
    }
}
