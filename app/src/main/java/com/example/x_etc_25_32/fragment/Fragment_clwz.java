package com.example.x_etc_25_32.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.activity.Activity_clwz;
import com.example.x_etc_25_32.adapter.X_clwz_adapter;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 11:50
 */
public class Fragment_clwz extends Fragment {

    private GridView gridview;
    private X_clwz_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_clwz, null);
        initView(view);

        showgrid();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), Activity_clwz.class)
                        .putExtra("1",position+"");
                startActivity(intent);
            }
        });


        return view;
    }

    private void showgrid() {
        adapter = new X_clwz_adapter();
        gridview.setAdapter(adapter);
    }

    private void initView(View view) {
        gridview = view.findViewById(R.id.gridview);
    }
}
