package com.limxtop.research.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.limxtop.research.R;

/**
 * Created by limxtop on 5/7/16.
 */
public class MatrixView extends ImageView {

    private Bitmap mBitMap;

    private Matrix mMatrix;

    public MatrixView(Context context) {
        super(context);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitMap, 0, 0, null);
        //
        canvas.drawBitmap(mBitMap, mMatrix, null);
        super.onDraw(canvas);
    }

    public Bitmap getImageBitmap() {
        return mBitMap;
    }

    public void setMatrix(Matrix matrix) {
        mMatrix.set(matrix);
//        super.setImageMatrix(matrix);
    }
}
