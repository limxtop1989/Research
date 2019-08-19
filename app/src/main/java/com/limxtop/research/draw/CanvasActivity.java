package com.limxtop.research.draw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.limxtop.research.R;

public class CanvasActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private View mCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        mCanvasView = findViewById(R.id.canvas);

        RadioGroup rg = findViewById(R.id.radio_group);
        for (int i = 0; i < rg.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) rg.getChildAt(i);
            radioButton.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) {
            return;
        }

        switch (buttonView.getId()) {
            case R.id.translate:
                break;

            case R.id.rotate:
                break;
        }
    }
}
