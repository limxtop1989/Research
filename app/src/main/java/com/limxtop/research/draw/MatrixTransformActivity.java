package com.limxtop.research.draw;

import android.graphics.Matrix;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.limxtop.research.R;

public class MatrixTransformActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = MatrixTransformActivity.class.getSimpleName();
    private MatrixView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_transform);
        view = (MatrixView) findViewById(R.id.matrix_view);
        addClickListener();
    }

    private void addClickListener() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group);
        int count = group.getChildCount();
        for (int i = 0; i < count; i++) {
            ((RadioButton) group.getChildAt(i)).setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            return;
        }
        Matrix matrix = new Matrix();
        int id = buttonView.getId();
        float matrix_values[];
        Log.d(TAG, "image size: width x height = " + view.getImageBitmap().getWidth() + " x " + view.getImageBitmap().getHeight());
        switch (id) {
            case R.id.translate:
                // 1. translate by bitmap's width horizontally and bitmap's height vertically
                Log.d(TAG, matrix.toString());
                matrix.postTranslate(view.getImageBitmap().getWidth(), view.getImageBitmap().getHeight());
                Log.d(TAG, matrix.toShortString());
                view.setMatrix(matrix);
                break;

            case R.id.rotate:
                // rotate around the center point
                Log.d(TAG, matrix.toString());
                matrix.setRotate(45f, view.getImageBitmap().getWidth() / 2f, view.getImageBitmap().getHeight() / 2f);
                matrix.postTranslate(view.getImageBitmap().getWidth() * 1.5f, 0f);
                Log.d(TAG, matrix.toShortString());
                view.setMatrix(matrix);
                break;

            case R.id.rotate_translate:
                // 3. rotate around the pivot and translate
                matrix.setRotate(45f);
                matrix.preTranslate(-1f * view.getImageBitmap().getWidth() / 2f, -1f * view.getImageBitmap().getHeight() / 2f);
                matrix.postTranslate((float) view.getImageBitmap().getWidth() / 2f, (float) view.getImageBitmap().getHeight() / 2f);

                // make the translate bitmap not overlap the original one.
                matrix.postTranslate((float) view.getImageBitmap().getWidth() * 1.5f, 0f);
                Log.d(TAG, matrix.toShortString());
                view.setMatrix(matrix);
                break;
            case R.id.scale:
                // scale
                matrix.setScale(2f, 2f);
                matrix.postTranslate(view.getImageBitmap().getWidth(), view.getImageBitmap().getHeight());
                view.setMatrix(matrix);
                Log.d(TAG, matrix.toShortString());
                break;
            case R.id.skew_horizontal:
                // 5. skew by horizontal
                matrix.setSkew(0.5f, 0f);
                matrix.postTranslate(view.getImageBitmap().getWidth(), 0f);
                Log.d(TAG, matrix.toShortString());
                view.setMatrix(matrix);
                break;
            case R.id.skew_vertical:
                // 6. skew by vertically
                matrix.setSkew(0f, 0.5f);
                matrix.postTranslate(0f, view.getImageBitmap().getHeight());
                Log.d(TAG, matrix.toShortString());
                view.setMatrix(matrix);
                break;
            case R.id.skew:
                // 7. skew by horizontally + vertically
                matrix.setSkew(0.5f, 0.5f);
                matrix.postTranslate(0f, view.getImageBitmap().getHeight());
                view.setMatrix(matrix);
                break;
            case R.id.symmetry_horizontal:
                // 8. symmetric axis is y = 0
                matrix_values = new float[] {1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f};
                matrix.setValues(matrix_values);
                matrix.postTranslate(0f, view.getImageBitmap().getHeight() * 2f);
                Log.d(TAG, matrix.toShortString());
                view.setMatrix(matrix);
                break;

            case R.id.symmetry_vertical:
                // 9. symmetric axis is x = 0;
                matrix_values = new float[] {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
                matrix.setValues(matrix_values);
                matrix.postTranslate(view.getImageBitmap().getWidth() * 2f, 0f);
                Log.d(TAG, matrix.toShortString());
                view.setMatrix(matrix);
                break;
            case R.id.symmetry:
                // symmetric axis is y = x;
                matrix_values = new float[] {0f, -1f, 0f, -1f, 0f, 0f, 0f, 0f, 1f};
                matrix.setValues(matrix_values);
                matrix.postTranslate(view.getImageBitmap().getHeight() + view.getImageBitmap().getWidth(),
                        view.getImageBitmap().getHeight() + view.getImageBitmap().getWidth());
                Log.d(TAG, matrix.toShortString());
                view.setMatrix(matrix);
                break;

        }

        view.invalidate();
    }
}
