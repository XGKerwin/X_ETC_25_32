package com.example.x_etc_25_32.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_25_32.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.w3c.dom.Entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 8:15
 */
public class Fragment_2 extends Fragment {

    private int s1, s3, s5;
    private float t1, t3, t5;
    private HorizontalBarChart barchart;

    public Fragment_2(int s1, int s3, int s5) {
        this.s1 = s1;
        this.s3 = s3;
        this.s5 = s5;
    }

    private List<String> strings;
    private List<Integer> colors;
    private List<BarEntry> barEntries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_2, null);
        initView(view);

        getData();


        return view;
    }

    private void getData() {
        float sun = 0;
        sun = s1+s3+s5;

        t1 = s1*100/sun;
        t3 = s3*100/sun;
        t5 = s5*100/sun;
        Log.i("ccccccccc", "getData: "+t5);
        Log.i("ccccccccc", "getData: "+t3);
        Log.i("ccccccccc", "getData: "+t1);

        if (barEntries == null){
            barEntries = new ArrayList<>();
            colors = new ArrayList<>();
            strings = new ArrayList<>();
        }else {
            strings.clear();
            barEntries.clear();
            colors.clear();
        }
        barEntries.add(new BarEntry(1,t1));
        barEntries.add(new BarEntry(2,t3));
        barEntries.add(new BarEntry(3,t5));
        colors.add(Color.parseColor("#90D24F"));
        colors.add(Color.parseColor("#5080C2"));
        colors.add(Color.parseColor("#BD0401"));
        BarDataSet dataSet = new BarDataSet(barEntries,"");
        dataSet.setColors(colors);
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.7f);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextColor(Color.RED);
        data.setValueTextSize(25);

        strings.add("1-2条违章");
        strings.add("3-5条违章");
        strings.add("5条以上违章");

        XAxis xAxis = barchart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"","1-2条违章","3-5条违章","5条以上违章"}));
        xAxis.setTextSize(20);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(3);


        YAxis yAxis = barchart.getAxisLeft();
        yAxis.setTextSize(15);
        yAxis.setDrawZeroLine(true);
        yAxis.setLabelCount(3);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(100);
        yAxis.setEnabled(false);

        YAxis yAxis1 = barchart.getAxisRight();
        yAxis1.setTextSize(15);
        yAxis1.setDrawZeroLine(true);
        yAxis1.setLabelCount(10);
        yAxis1.setAxisMinimum(0);
        yAxis1.setAxisMaximum(100);

        barchart.setData(data);
        barchart.setTouchEnabled(false);
        barchart.getLegend().setEnabled(false);
        barchart.getDescription().setEnabled(false);

    }

    private void initView(View view) {
        barchart = view.findViewById(R.id.barchart);
    }
}
