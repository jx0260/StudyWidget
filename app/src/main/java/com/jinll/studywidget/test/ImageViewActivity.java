package com.jinll.studywidget.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jinll.studywidget.R;
import com.onetime.nww.IDoResult;
import com.onetime.nww.media.CaptureScreenMod;
import com.onetime.nww.util.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jin Liang on 2017/9/26.
 */
public class ImageViewActivity extends AppCompatActivity {

    private static final String TAG ="ImageViewActivity";

    @BindView(R.id.iv_imageView_test)
    ImageView mTestIv;
    @BindView(R.id.iv_imageView_second)
    ImageView mSecondView;
    @BindView(R.id.btn_imageView_capture)
    Button mCaptureBtn;

    private ImageViewActivity this0;

    private MediaProjectionManager mMediaProjectionManager;
    private int REQUEST_MEDIA_PROJECTION = 1;
    private CaptureScreenMod captureScreenMod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this0 = this;
        setContentView(R.layout.activity_imageview_test);
        ButterKnife.bind(this);
        mMediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
//        TextViewCompat.setTextAppearance(n1, android.R.style.TextAppearance_DeviceDefault_WindowTitle);
    }

    @OnClick(R.id.btn_imageView_capture)
    void captureScreen(){
        startActivityForResult(mMediaProjectionManager.createScreenCaptureIntent(), REQUEST_MEDIA_PROJECTION);
    }

    @OnClick(R.id.iv_imageView_test)
    void captureView(){
        if(captureScreenMod != null){
            String sdCardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
            String filePath = sdCardPath + File.separator + "screenshot.png";
            captureScreenMod.captureView(mSecondView, filePath);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_MEDIA_PROJECTION){
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
                    MediaProjection mMediaProjection = mMediaProjectionManager.getMediaProjection(resultCode, data);
                    captureScreenMod = new CaptureScreenMod(mMediaProjection);
                    captureScreenMod.captureScreen(new IDoResult() {
                        @Override
                        public void onSuccess(Object o) {
                            ToastUtil.show(this0, "截屏成功，路径为："+ o);
                        }
                        @Override
                        public void onFail(String msg) {
                            ToastUtil.show(this0, msg);
                        }
                    }, directory);
                } else {
                    Toast.makeText(this, "获取媒体投影服务失败！data=null", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "取消操作", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
