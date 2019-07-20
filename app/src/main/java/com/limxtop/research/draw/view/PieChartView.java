package com.limxtop.research.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.limxtop.research.draw.model.Item;

import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;

public class PieChartView extends View {

    private List<Item> sources;
    private int total;

    private Paint mPaint;
    private int[] mArcColor;

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    public void setDataSource(List<Item> sources) {
       this.sources = sources;

       for (Item item : sources) {
           total += item.getData();
       }

       // TODO: How to allocate colors evenly distributed.
       mArcColor = new int[sources.size()];
       Random random = new Random();

       for (int i = 0; i < sources.size(); i++) {
           float r = random.nextInt(255);
           float g = random.nextInt(255);
           float b = random.nextInt(255);
           mArcColor[i] = Color.rgb(r, g, b);
       }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (null == sources) {
            return;
        }

        int width = getWidth();
        int height = getHeight();
        int min = Math.min(width, height);
        int radius = min >> 1;
        canvas.drawCircle(width >> 1, height >> 1, radius, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        // The coordinate is relative to left right corner of view.
        RectF rf = new RectF(0, 0, width, height);
        int startAngle = 0;
        for (int i = 0; i < sources.size(); i++) {
            mPaint.setColor(mArcColor[i]);
            int sweepAngle = sources.get(i).getData() * 360 / total;
            canvas.drawArc(rf, startAngle, sweepAngle, true, mPaint);
            startAngle += sweepAngle;
        }

    }

}
