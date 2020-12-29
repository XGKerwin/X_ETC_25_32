package com.example.x_etc_25_32.fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.x_etc_25_32.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/5 9:40
 */
public class Fragment_sshj_1 extends Fragment {

    private List<SSHJ> sshjList;
    private TextView txtTitle;
    private LineChart linechart;
    private List<Entry> entryList;
    private List<Integer> colors;

    public Fragment_sshj_1(List<SSHJ> sshjList) {
        this.sshjList = sshjList;
    }
    
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            getData();
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sshj_1, null);
        initView(view);
        txtTitle.setText("PM2.5指数");

        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        handler.sendEmptyMessage(0);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while (true);
            }
        }).start();

        txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "1")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Much longer text that cannot fit one line...")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
                notificationManager.notify(1, builder.build());
            }
        });

        return view;
    }
    private void getData() {
        if (entryList == null){
            entryList = new ArrayList<>();
            colors = new ArrayList<>();
        }else {
            colors.clear();
            entryList.clear();
        }

        if (sshjList.size()==0){
            handler.sendEmptyMessage(0);
            return;
        }

        for (int i=0;i<sshjList.size();i++){
            SSHJ sshj = sshjList.get(i);
            entryList.add(new Entry((i+1)*3,sshj.getPm25()));
            if (sshj.getPm25()>200){
                colors.add(Color.RED);
            }else {
                colors.add(Color.GREEN);
            }
        }


        LineDataSet dataSet = new LineDataSet(entryList,"");
        dataSet.setValueTextSize(20);
        dataSet.setCircleColors(colors);
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleRadius(6);
        dataSet.setColor(Color.GRAY);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setLineWidth(5);
        LineData data = new LineData(dataSet);

        XAxis xAxis = linechart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(17);
        xAxis.setDrawGridLines(false);

        linechart.getAxisRight().setEnabled(false);
        YAxis yAxis = linechart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(400);
        yAxis.setTextSize(20);
        yAxis.setDrawAxisLine(false);

        linechart.setData(data);
        linechart.getDescription().setText("（秒）");
        linechart.getDescription().setTextSize(20);
        linechart.getLegend().setEnabled(false);
        linechart.setTouchEnabled(false);
        linechart.invalidate();

    }


    private void initView(View view) {
        txtTitle = view.findViewById(R.id.txt_title);
        linechart = view.findViewById(R.id.linechart);
    }
}
