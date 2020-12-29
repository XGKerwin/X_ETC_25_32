package com.example.x_etc_25_32.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.bean.SJFX;
import com.example.x_etc_25_32.net.OkHttpLo;
import com.example.x_etc_25_32.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/3 21:11
 */
public class Fragment_sjfx extends Fragment {

    private TextView title;
    private ViewPager viewpager;
    private List<SJFX> sjfxList,sjfxyes,sjfxno;
    private List<Fragment> fragments;
    private LinearLayout Linear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx, null);
        initView(view);
        title.setText("数据分析");
        sjfxList = new ArrayList<>();



        getOkHttp();

        return view;
    }

    private void getOkHttp() {
        new OkHttpTo()
                .setUrl("get_peccancy")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        sjfxList.addAll((Collection<? extends SJFX>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<SJFX>>(){}.getType()));
                        getdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private Map<String,String> mapyes,mapno;
    private Map<String,Integer> map2;
    private int yes=0,no=0;
    private int s1=0,s3=0,s5=0;

    private void getdata() {
        mapno = new HashMap<>();
        mapyes = new HashMap<>();
        sjfxyes = new ArrayList<>();
        sjfxno = new ArrayList<>();
        map2 = new HashMap<>();

        Log.i("fffffffffffs", "getdata: "+sjfxList.size());

        for (int i=0;i<sjfxList.size();i++){
            SJFX sjfx = sjfxList.get(i);
            if (sjfx.getPaddr().equals("")){
                sjfxno.add(sjfx);
            }else {
                sjfxyes.add(sjfx);
            }
        }

        for (int i=0;i<sjfxyes.size();i++){
            SJFX sjfx = sjfxyes.get(i);
            mapyes.put(sjfx.getCarnumber(),"");
        }
        for (int i=0;i<sjfxno.size();i++){
            SJFX sjfx = sjfxno.get(i);
            mapno.put(sjfx.getCarnumber(),"");
        }

        for (int i=0;i<sjfxyes.size();i++){
            SJFX sjfx = sjfxyes.get(i);
            if (map2.get(sjfx.getCarnumber()) == null){
                map2.put(sjfx.getCarnumber(),1);
            }else {
                map2.put(sjfx.getCarnumber(),map2.get(sjfx.getCarnumber())+1);
            }
        }

        List<String> strings = new ArrayList<>();

        strings.addAll(map2.keySet());


        for (int i=0;i<strings.size();i++){
            if (map2.get(strings.get(i))==1){
                yes++;
            }else {
                no++;
            }
        }


        for (int i=0;i<strings.size();i++){
            if (map2.get(strings.get(i))>5){
                s5++;
            }else if (map2.get(strings.get(i))>2){
                s3++;
            }else {
                s1++;
            }
        }


        fragment1();
    }

    private void fragment1() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment_1(yes,no));
        fragments.add(new Fragment_2(s1,s3,s5));

        viewpager.setAdapter(new Pageradapter(getActivity().getSupportFragmentManager()));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <Linear.getChildCount();i++){
                    ImageView imageView = (ImageView) Linear.getChildAt(i);
                    if (position == i){
                        imageView.setImageResource(R.drawable.bg_hui);
                    }else {
                        imageView.setImageResource(R.drawable.bg_wu);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (int i=0;i<fragments.size();i++){
            ImageView imageView = new ImageView(getContext());
            if (i==0){
                imageView.setImageResource(R.drawable.bg_hui);
            }else {
                imageView.setImageResource(R.drawable.bg_wu);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(60,60));
            imageView.setPadding(10,10,10,10);
            Linear.addView(imageView);
        }

    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        viewpager = view.findViewById(R.id.viewpager);
        Linear = view.findViewById(R.id.Linear);
    }

    class Pageradapter extends FragmentPagerAdapter {

        public Pageradapter(FragmentManager fr) {
            super(fr);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
