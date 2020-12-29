package com.example.x_etc_25_32;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_dtgh1 extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtgh1);
        initView();
        title.setText("地铁规划");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageview.setOnTouchListener(new ImageListener(imageview));
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        imageview = findViewById(R.id.imageview);
    }

    public class ImageListener implements View.OnTouchListener {
        private ImageView imageView;
        private Matrix matrix = new Matrix();
        private Matrix current = new Matrix();
        private PointF pointF;
        private PointF start;
        private int mode;
        private int DOWN1 = 1;
        private int DOWN2 = 2;
        private float dis;

        public ImageListener(ImageView imageView) {
            this.imageView = imageView;
        }

        private float tance(MotionEvent event){
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
            return (float) Math.sqrt(dx * dx + dy *dy);
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
            switch (event.getAction()&MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    mode = DOWN1;
                    matrix.set(imageView.getImageMatrix());
                    current.set(imageView.getImageMatrix());
                    start = new PointF(event.getX() , event.getY());
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    mode = DOWN2;
                    dis = tance(event);
                    if (dis>10f){
                        float dx = (event.getX(0) + event.getX(1))/2;
                        float dy = (event.getY(0) + event.getY(1))/2;
                        current.set(imageView.getImageMatrix());
                        pointF = new PointF(dx,dy);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mode == DOWN1){
                        float dx = event.getX() - start.x;
                        float dy = event.getY() - start.y;
                        matrix.set(current);
                        matrix.postTranslate(dx,dy);
                    }else if (mode == DOWN2){
                        float end = tance(event);
                        if (end>10f){
                            float scale = end/dis;
                            matrix.set(current);
                            matrix.postScale(scale,scale,pointF.x,pointF.y);
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    mode = 0;
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    mode = 0;
                    break;
            }
            imageView.setImageMatrix(matrix);
            return true;
        }
    }



}