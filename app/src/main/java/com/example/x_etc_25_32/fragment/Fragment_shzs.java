package com.example.x_etc_25_32.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.bean.SHZS;
import com.example.x_etc_25_32.adapter.X_shzs_adapter;
import com.example.x_etc_25_32.net.OkHttpLo;
import com.example.x_etc_25_32.net.OkHttpTo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 10:11
 */
public class Fragment_shzs extends Fragment {
    private TextView pm;
    private TextView txtWendu;
    private TextView txtShidu;
    private GridView gridview;
    private List<SHZS> shzsList;
    private X_shzs_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_shzs, null);
        initView(view);

        getOkHttp();

        return view;
    }

    private void getOkHttp() {
        if (shzsList == null){
            shzsList = new ArrayList<>();
        }
        new OkHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        txtWendu.setText("温   度："+jsonObject.optString("temperature"));
                        pm.setText("PM2.5："+jsonObject.optString("pm25"));
                        txtShidu.setText("湿   度："+jsonObject.optString("humidity"));
                        shzsList.clear();
                        shzsList.add(new Gson().fromJson(jsonObject.toString(),SHZS.class));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
            adapter = new X_shzs_adapter(shzsList);
            gridview.setAdapter(adapter);

    }

    private void initView(View view) {
        pm = view.findViewById(R.id.pm);
        txtWendu = view.findViewById(R.id.txt_wendu);
        txtShidu = view.findViewById(R.id.txt_shidu);
        gridview = view.findViewById(R.id.gridview);
    }
}
