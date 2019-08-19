package com.limxtop.research.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import androidx.annotation.Nullable;

public class CanvasView extends View {


    private Paint mPaint;
    private float mDensity;

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        mDensity = getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.translate(0, 100);

        drawOrigin(canvas);

        canvas.save();
        canvas.translate(getFrameWidth() * 2, 0);
        canvas.clipRect(getFrameWidth(), 0, 20 * mDensity, 20 * mDensity);
        drawOrigin(canvas);
        canvas.restore();


        canvas.save();
        canvas.translate(getFrameWidth() * 4, 0);
        canvas.rotate(45);
        drawOrigin(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(getFrameWidth() * 6, 0);
        canvas.translate(-(getFrameWidth() / 2), -(getFrameHeight() / 2));
        canvas.rotate(45);
        canvas.translate(getFrameWidth() / 2 , getFrameHeight() / 2);
        drawOrigin(canvas);
        canvas.restore();

    }

    private void drawOrigin(Canvas canvas) {
        canvas.save();
        int width = getFrameWidth();
        int height = getFrameHeight();
        canvas.drawRect(0, 0, getFrameWidth(), getFrameHeight(), mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(width >> 1, height >> 1, getRadius(), mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(width >> 1, height >> 1, mPaint);
        canvas.translate(mDensity * 80, mDensity * 20);
        canvas.drawText("hello world", 0 , 0, mPaint);
        canvas.restore();
    }


    private int getRadius() {
        return (int) (mDensity * 25 + 0.5f);
    }

    private int getFrameWidth() {
        return (int) (mDensity * 60 + 0.5f);
    }

    private int getFrameHeight() {
        return (int) (mDensity * 60 + 0.5f);
    }

    private void action(Canvas canvas) {
        canvas.rotate(30);
    }

}
