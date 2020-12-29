package com.example.x_etc_25_32.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_25_32.bean.LKCX;
import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.net.OkHttpLo;
import com.example.x_etc_25_32.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 20:52
 */
public class Fragment_lkcx extends Fragment {

    private TextView pm;
    private TextView wendu;
    private TextView shidu;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView im1;
    private TextView im2;
    private TextView im3;
    private List<LKCX> lkcxList;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_lkcx, null);
        initView(view);

        title.setText("路况查询");

        getOkhttp();

        return view;
    }

    private void getOkhttp() {

        new OkHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        pm.setText("PM2.5："+jsonObject.optString("pm25"));
                        wendu.setText("温    度："+jsonObject.optString("temperature"));
                        shidu.setText("湿    度："+jsonObject.optString("humidity"));
                        getdaolu();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void getdaolu() {
        if (lkcxList == null){
            lkcxList = new ArrayList<>();
        }
        new OkHttpTo()
                .setUrl("get_road_status")
                .setJsonObject("UserName","user1")
                .setJsonObject("RoadId",0)
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        lkcxList.clear();
                        lkcxList.addAll((Collection<? extends LKCX>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<LKCX>>(){}.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void show() {
        for (int i=0;i<lkcxList.size();i++){
            LKCX lkcx = lkcxList.get(i);
            if (lkcx.getRoadId().equals("1")){
                show1(txt1,im1,i,1);
            }
            if (lkcx.getRoadId().equals("2")){
                show1(txt2,im2, i, 2);
            }
            if (lkcx.getRoadId().equals("3")){
                show1(txt3,im3, i, 3);
            }
        }
        
    }

    private void show1(TextView txt3, TextView im3, int i, int i1) {
        String s = lkcxList.get(i).getState();
        switch (s){
            case "1":
                txt3.setText(i1+"号道路：通畅");
                im3.setBackgroundResource(R.drawable.tongchang);
                break;
            case "2":
                txt3.setText(i1+"号道路：较通畅");
                im3.setBackgroundResource(R.drawable.jiaochangtong);
                break;
            case "3":
                txt3.setText(i1+"号道路：拥挤");
                im3.setBackgroundResource(R.drawable.yongji);
                break;
            case "4":
                txt3.setText(i1+"号道路：堵塞");
                im3.setBackgroundResource(R.drawable.dusai);
                break;
            case "5":
                txt3.setText(i1+"号道路：爆表");
                im3.setBackgroundResource(R.drawable.baoman);
                break;
        }

    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        pm = view.findViewById(R.id.pm);
        wendu = view.findViewById(R.id.wendu);
        shidu = view.findViewById(R.id.shidu);
        txt1 = view.findViewById(R.id.txt_1);
        txt2 = view.findViewById(R.id.txt_2);
        txt3 = view.findViewById(R.id.txt_3);
        im1 = view.findViewById(R.id.im_1);
        im2 = view.findViewById(R.id.im_2);
        im3 = view.findViewById(R.id.im_3);
    }
}
