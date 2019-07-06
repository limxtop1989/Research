package com.limxtop.research.animator.base;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.limxtop.research.R;

/**
 * this class demonstrate the animator follow by bezier curve path, to know the bezier curve, see:
 * https://en.wikipedia.org/wiki/B%C3%A9zier_curve for detail please.
 */
public class BezierCurveAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mProduct;
    private TextView mShoppingCart;

    private Path mPath;
    private ObjectAnimator mObjectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_curve_animator);

        mPath = new Path();

        mProduct = (TextView) findViewById(R.id.product);
        mProduct.setOnClickListener(this);

        mShoppingCart = (TextView) findViewById(R.id.shopping_cart);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        int sourceX = view.getLeft() + view.getWidth() / 2;
        int sourceY = view.getTop() + view.getHeight() / 2;

        int destinationX;
        int destinationY;

        /**
         * TODO:
         * why destinationX and destinationY does not make the Product View layout in center of
         * Shopping Cart View.
         */
        destinationX = mShoppingCart.getLeft() + mShoppingCart.getWidth() / 2 - view.getWidth() / 2;
        destinationY = mShoppingCart.getTop() + mShoppingCart.getHeight() / 2 - view.getHeight() / 2;
        int controlX = destinationX;
        int controlY = sourceY;
        mPath.reset();
//        mPath.moveTo(sourceX, sourceY);
        mPath.quadTo(controlX, controlY, destinationX, destinationY);
        mPath.lineTo(destinationX, (destinationY - sourceY) / 2);
        mPath.lineTo(destinationX, destinationY);
        /**
         * if need to simulate the scene of object falls down and hit the floor, the solution is
         * use AnimatorSet and appropriate Interpolator rather than animate by path witch can use
         * single Interpolator only.
         */
        mObjectAnimator = ObjectAnimator.ofFloat(view, "translationX", "translationY", mPath);
        mObjectAnimator.setDuration(3000);
        mObjectAnimator.start();
    }
}
