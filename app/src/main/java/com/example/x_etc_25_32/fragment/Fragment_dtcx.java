package com.example.x_etc_25_32.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_25_32.Activity_dtgh1;
import com.example.x_etc_25_32.bean.DTGH;
import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.adapter.X_dtgh_adapter;
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
 * date   : 2020/12/4 16:00
 */
public class Fragment_dtcx extends Fragment {

    private TextView title;
    private TextView dtgh;
    private ExpandableListView EListview;
    private X_dtgh_adapter adapter;
    private List<DTGH> dtghList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_dtcx, null);
        initView(view);
        title.setText("地铁查询");
        dtgh.setText("地铁规划");
        dtgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_dtgh1.class);
                startActivity(intent);
            }
        });
        EListview.setGroupIndicator(null);
        getOkHttp();
        return view;
    }

    private void getOkHttp() {
        if (dtghList == null){
            dtghList = new ArrayList<>();
        }else {
            dtghList.clear();
        }
        new OkHttpTo()
                .setUrl("get_metro")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        dtghList.addAll((Collection<? extends DTGH>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DTGH>>(){}.getType()));
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void showList() {
        if (adapter == null){
            adapter = new X_dtgh_adapter(dtghList);
            EListview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dtgh.setText("");
    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        dtgh = getActivity().findViewById(R.id.biaoti_wodeyijian);
        EListview = view.findViewById(R.id.EListview);
    }
}
