package com.limxtop.research.animator.property;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.limxtop.research.R;

public class ZoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View container = findViewById(R.id.container);
        ImageView source = findViewById(R.id.source);
        ImageView destination = findViewById(R.id.destination);

        container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //处理完后remove掉，至于为什么，后面有解释
                container.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }

        });

        new Handler().postDelayed(() -> {
            new UIAnimation(container, source, destination, 2000, null);
        }, 200);
    }
}