package com.example.x_etc_25_32.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.activity.Activity_wdyj;
import com.example.x_etc_25_32.bean.YJFK;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 15:13
 */
public class Fragment_yjfk extends Fragment {

    private EditText editTitle;
    private EditText editMsg;
    private EditText editTel;
    private Button btnTijiao;
    private TextView title;
    private TextView wodeyijian;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_yjfk, null);
        initView(view);
        title.setText("我的意见");
        wodeyijian.setText("我的意见");

        wodeyijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_wdyj.class);
                startActivity(intent);
            }
        });

        btnTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String msg = editMsg.getText().toString();
                String tel = editTel.getText().toString();
                if (title.equals("")){
                    Toast.makeText(getContext(),"请输入标题",Toast.LENGTH_SHORT).show();
                }else if (msg.equals("")){
                    Toast.makeText(getContext(),"请输入您的意见",Toast.LENGTH_SHORT).show();
                }else if (tel.equals("")){
                    Toast.makeText(getContext(),"请输入您的手机号",Toast.LENGTH_SHORT).show();
                }else {
                    YJFK yjfk = new YJFK();
                    getTime();
                    yjfk.setTime(time);
                    yjfk.setTitle(title);
                    if (yjfk.save()){
                        editMsg.setText("");
                        editTel.setText("");
                        editTitle.setText("");
                        Toast.makeText(getContext(),"提交成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(),"提交失败",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }

    private String time;
    private void getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        time = format.format(date);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wodeyijian.setText("");
    }

    private void initView(View view) {
        wodeyijian = getActivity().findViewById(R.id.biaoti_wodeyijian);
        title = getActivity().findViewById(R.id.title);
        editTitle = view.findViewById(R.id.edit_title);
        editMsg = view.findViewById(R.id.edit_msg);
        editTel = view.findViewById(R.id.edit_tel);
        btnTijiao = view.findViewById(R.id.btn_tijiao);
    }
}
