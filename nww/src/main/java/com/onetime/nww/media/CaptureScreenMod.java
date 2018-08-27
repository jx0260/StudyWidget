package com.onetime.nww.media;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.onetime.nww.IDoResult;
import com.onetime.nww.baservice.DisplayService;
import com.onetime.nww.util.BitmapUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;

/**
 * 截屏模块
 * Created by Jin Liang on 2018/8/22.
 */

public class CaptureScreenMod {

    private static final String TAG = "[Nww]CaptureScreenMod";
    private MediaProjection mMediaProjection;

    public CaptureScreenMod(MediaProjection mediaProjection){
        mMediaProjection = mediaProjection;
    }

    public void captureScreen(IDoResult result, String directoryPath){
        try {
            DisplayMetrics displayMetrics = DisplayService.getRealDisplayMetrics();
            int windowWidth = displayMetrics.widthPixels;
            int windowHeight = displayMetrics.heightPixels;
            float mScreenDensity = displayMetrics.density;

            ImageReader mImageReader = ImageReader.newInstance(windowWidth, windowHeight, PixelFormat.RGBA_8888, 2);
            VirtualDisplay mVirtualDisplay = mMediaProjection.createVirtualDisplay("screen-mirror",
                windowWidth, windowHeight, (int) mScreenDensity, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                mImageReader.getSurface(), null, null);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
            String strDate = dateFormat.format(new java.util.Date());
            String nameImage = directoryPath + File.separator + strDate + ".png";

            Image image = mImageReader.acquireLatestImage();
            final Image.Plane[] planes = image.getPlanes();
            final ByteBuffer buffer = planes[0].getBuffer();
            Bitmap bitmap = Bitmap.createBitmap(windowWidth, windowHeight, Bitmap.Config.ARGB_8888);
            bitmap.copyPixelsFromBuffer(buffer);
            image.close();
            BitmapUtil.compressBitmapToFile(bitmap, nameImage, Bitmap.CompressFormat.PNG);

            if (mVirtualDisplay != null) {
                mVirtualDisplay.release();
                mVirtualDisplay = null;
            }

            result.onSuccess(nameImage);
        } catch (Exception e){
            result.onFail(e.getMessage());
        }
    }

    public void captureView(View view, String filePath){
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        BitmapUtil.compressBitmapToFile(bitmap, filePath, Bitmap.CompressFormat.JPEG);
    }
}
