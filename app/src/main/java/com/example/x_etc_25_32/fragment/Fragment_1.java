package com.example.x_etc_25_32.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_25_32.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 8:07
 */
public class Fragment_1 extends Fragment {

    private int yes, no;

    public Fragment_1(int yes, int no) {
        this.yes = yes;
        this.no = no;
    }
    private float yes1,no2;
    private float sum;

    private PieChart pieChart;
    private List<Integer> colors;
    private List<PieEntry> pieEntryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_1, null);
        initView(view);
        getData();

        return view;
    }

    private void getData() {
        if (pieEntryList == null) {
            pieEntryList = new ArrayList<>();
            colors = new ArrayList<>();
        } else {
            pieEntryList.clear();
            colors.clear();
        }
        sum = yes+no;
        yes1 = yes*100/sum;
        no2 = no*100/sum;

        pieEntryList.add(new PieEntry(yes1, "有违章"));
        pieEntryList.add(new PieEntry(no2, "无违章"));
        colors.add(Color.parseColor("#A94643"));
        colors.add(Color.parseColor("#4572A7"));
        PieDataSet pieDataSet = new PieDataSet(pieEntryList,"");
        pieDataSet.setValueTextSize(25);
        pieDataSet.setColors(colors);
        pieDataSet.setValueLinePart1OffsetPercentage(100);
        pieDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.0");
                return format.format(value*1)+"%";
            }
        });
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLinePart1Length(0.8f);
        pieDataSet.setValueLinePart2Length(0.1f);
        pieDataSet.setValueLineColor(Color.BLACK);
        pieDataSet.setSliceSpace(10f);
        pieDataSet.setValueTextSize(25);
        PieData data = new PieData(pieDataSet);
        Legend legend = pieChart.getLegend();
        legend.setTextSize(25);
        legend.setFormSize(25);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setUsePercentValues(true);
        pieChart.setTouchEnabled(false);
        pieChart.setEntryLabelColor(Color.BLACK);




    }

    private void initView(View view) {
        pieChart = view.findViewById(R.id.PieChart);
    }
}
