package com.onetime.nww.util;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Jin Liang on 2018/8/22.
 */

public class BitmapUtil {

    private static final String TAG = "[Nww]BitmapUtil";

    public static void compressBitmapToFile(Bitmap bitmap, String filePath, Bitmap.CompressFormat compressFormat){
        if (bitmap != null && !TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(compressFormat, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                Log.i(TAG, Log.getStackTraceString(e));
            }
        }
    }

}
