package com.onetime.nww.exception;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * app异常处理
 * Manifest.xml中定义 CrashHomeDir meta-data 设置异常信息的存储路径
 *
 * Created by Jin Liang on 2018/8/27.
 */
public class NwwUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "NwwUncaughtExceptionHdl";

    private static NwwUncaughtExceptionHandler instance = new NwwUncaughtExceptionHandler();

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;
    private Application mApp;
    private SimpleArrayMap<String, String> mInfoMap = new SimpleArrayMap<>();
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    public static NwwUncaughtExceptionHandler getInstance() {
        return instance;
    }

    public void init(Application application) {
        mApp = application;
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(instance);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        final Throwable tt = throwable;
        new Thread(new Runnable() {
            @Override
            public void run() {
                collectDeviceInfo(mApp);
                saveCrashInfo2File(tt);
            }
        }).start();

        if(mDefaultExceptionHandler != null){
            mDefaultExceptionHandler.uncaughtException(thread, throwable);
        }
    }

    public void collectDeviceInfo(Application app) {
        try {
            PackageManager pm = app.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(app.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                mInfoMap.put("versionName", versionName);
                mInfoMap.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mInfoMap.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect Build Fields", e);
            }
        }
    }

    private String saveCrashInfo2File(Throwable ex) {
        StringBuffer crashSb = new StringBuffer();
        for (int i = 0; i < mInfoMap.size(); i++) {
            String key = mInfoMap.keyAt(i);
            String value = mInfoMap.get(key);
            crashSb.append(key + "=" + value + "\n");
        }
        crashSb.append("=====================【以下为异常信息】=========================\n")
            .append(Log.getStackTraceString(ex));

        String content = crashSb.toString();

        try {
            ApplicationInfo ai = mApp.getPackageManager().getApplicationInfo(mApp.getPackageName(), PackageManager.GET_META_DATA);
            String homeDir = (String) ai.metaData.get("CrashHomeDir");
            if (TextUtils.isEmpty(homeDir)) {
                homeDir = mApp.getApplicationContext().getApplicationInfo().packageName.replace(".", "_");
            }
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash_" + time + "_" + timestamp + ".txt";

            String dirPath = Environment.getExternalStorageDirectory() + File.separator + homeDir + "/crash/";
            File dir = new File(dirPath);

            if (dir.isDirectory() || dir.mkdirs()) {
                File crashFile = new File(dirPath, fileName);
                if (crashFile.exists()) {
                    writeStr2File(crashFile, content);
                } else {
                    if(crashFile.createNewFile()){
                        writeStr2File(crashFile, content);
                    }
                }
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }

    private void writeStr2File(File crashFile, String content){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(crashFile);
            fos.write(content.getBytes());
            fos.flush();
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        } finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
