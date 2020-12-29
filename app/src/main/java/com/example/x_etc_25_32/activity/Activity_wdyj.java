package com.example.x_etc_25_32.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.adapter.X_WDYJ_adapter;
import com.example.x_etc_25_32.bean.YJFK;

import org.litepal.LitePal;

import java.util.List;

public class Activity_wdyj extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ListView listview;
    private List<YJFK> yjfkList;
    private X_WDYJ_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdyj);
        initView();
        yjfkList = LitePal.findAll(YJFK.class);
        title.setText("我的意见");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new X_WDYJ_adapter(yjfkList);
        listview.setAdapter(adapter);




    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        listview = findViewById(R.id.listview);
    }
}