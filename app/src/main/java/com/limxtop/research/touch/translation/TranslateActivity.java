package com.limxtop.research.touch.translation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.limxtop.research.R;

public class TranslateActivity extends AppCompatActivity implements TranslationViewGroup.Translatable {

    private View mChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        TranslationViewGroup tvg = findViewById(R.id.parent);
        tvg.setListener(this);

        mChild = findViewById(R.id.child);
    }

    @Override
    public void translate(float x, float y) {
        mChild.setTranslationX(x);
        mChild.setTranslationY(y);
    }
}
