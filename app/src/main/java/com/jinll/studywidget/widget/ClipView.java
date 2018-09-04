package com.jinll.studywidget.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Jin Liang on 2018/8/31.
 */
public class ClipView extends View {

    Paint mPaint;
    Matrix mDrawMatrix;

    public ClipView(Context context) {
        this(context, null);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);

        mDrawMatrix = new Matrix();
        mDrawMatrix.setScale(2, 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(300,300, 400,400, mPaint);

//        canvas.scale(2, 2);
//        canvas.setMatrix(mDrawMatrix);

        canvas.drawRect(250,250, 350,350, mPaint);
    }
}
