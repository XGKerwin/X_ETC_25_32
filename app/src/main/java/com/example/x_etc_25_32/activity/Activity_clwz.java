package com.example.x_etc_25_32.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_25_32.R;

public class Activity_clwz extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private VideoView videoview;
    private String string;
    private int[] video = {R.raw.car1,R.raw.car2,R.raw.car3,R.raw.car4,R.raw.car1};
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clwz);
        initView();
        string = getIntent().getStringExtra("1");
        title.setText("车辆违章");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Log.i("vvvvvvvvvvvvvvvvvvv", "onCreate: "+string);

        switch (string){
            case "0":
                videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+video[0]));
                videoview.start();
                break;
            case "1":
                videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+video[1]));
                videoview.start();
                break;
            case "2":
                videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+video[2]));
                videoview.start();
                break;
            case "3":
                videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+video[3]));
                videoview.start();
                break;
            case "4":
                videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+video[4]));
                videoview.start();
                break;
        }




    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        videoview = findViewById(R.id.videoview);
    }
}