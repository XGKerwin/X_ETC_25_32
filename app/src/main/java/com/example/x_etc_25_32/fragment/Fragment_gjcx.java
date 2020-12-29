package com.example.x_etc_25_32.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_25_32.bean.GJCX;
import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.adapter.X_GJCX_adapter;
import com.example.x_etc_25_32.net.OkHttpLo;
import com.example.x_etc_25_32.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 10:50
 */
public class Fragment_gjcx extends Fragment {

    private ExpandableListView elistview;
    private Map<String,List<GJCX>> map;
    private X_GJCX_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_gjcx, null);
        initView(view);
        getOKHttp();

        return view;
    }

    private void getOKHttp() {
        new OkHttpTo()
                .setUrl("get_bus_stop_distance")
                .setJsonObject("UserName","user1")
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (map == null){
                            map = new HashMap<>();
                        }else {
                            map.clear();
                        }
                        List<GJCX> gjcxes = new Gson().fromJson(jsonObject.optJSONArray("中医院站").toString(),
                                new TypeToken<List<GJCX>>(){}.getType());
                        Collections.sort(gjcxes, new Comparator<GJCX>() {
                            @Override
                            public int compare(GJCX o1, GJCX o2) {
                                return o1.getDistance() - o2.getDistance();
                            }
                        });
                        map.put("一号站台",gjcxes);

                        List<GJCX> gjcxes1 = new Gson().fromJson(jsonObject.optJSONArray("联想大厦站").toString(),
                                new TypeToken<List<GJCX>>(){}.getType());
                        Collections.sort(gjcxes1, new Comparator<GJCX>() {
                            @Override
                            public int compare(GJCX o1, GJCX o2) {
                                return o1.getDistance() - o2.getDistance();
                            }
                        });
                        map.put("二号站台",gjcxes1);
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }


    private void show() {
        if (adapter == null){
            adapter = new X_GJCX_adapter(map);
            elistview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    private void initView(View view) {
        elistview = view.findViewById(R.id.elistview);
    }
}
