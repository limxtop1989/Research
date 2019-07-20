package com.limxtop.research.draw;

import android.app.Activity;
import android.os.Bundle;

import com.limxtop.research.R;
import com.limxtop.research.draw.model.Item;
import com.limxtop.research.draw.view.PieChartView;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends Activity {

    private List<Item> sources;

    private PieChartView mPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        mPieChart = findViewById(R.id.pie_chart);
        mPieChart.setDataSource(getSource());
    }

    private List<Item> getSource() {
        if (null != sources) {
            return sources;
        }
        sources = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            sources.add(new Item(i + 1, "item" + i));
        }

        return sources;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
