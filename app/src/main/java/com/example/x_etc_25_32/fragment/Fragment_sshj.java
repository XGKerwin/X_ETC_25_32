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
 * date   : 2020/12/4 11:50
 */
public class Fragment_sshj extends Fragment {

    private TextView title;
    private TextView pmzhishu;
    private LinearLayout linear;
    private List<SSHJ> sshjList;
    private ViewPager ViewPager;
    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sshj, null);
        initView(view);
        title.setText("实时环境");
        sshjList = new ArrayList<>();

        getOkHttp();


        return view;
    }

    private void getOkHttp() {

        new OkHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        SSHJ sshj = new Gson().fromJson(jsonObject.toString(), SSHJ.class);
                        sshjList.add(sshj);
                        if (sshjList.size() == 6) {
                            sshjList.remove(0);
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

        fragmeng1();
    }

    private void fragmeng1() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment_sshj_1(sshjList));
        fragments.add(new Fragment_sshj_2(sshjList));
        fragments.add(new Fragment_sshj_3(sshjList));

        ViewPager.setAdapter(new Pageradapter(getActivity().getSupportFragmentManager()));
        ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<linear.getChildCount();i++){
                    ImageView imageView = (ImageView) linear.getChildAt(i);
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

        for (int i = 0; i < fragments.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            if (i == 0) {
                imageView.setImageResource(R.drawable.bg_hui);
            } else {
                imageView.setImageResource(R.drawable.bg_wu);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(60, 60));
            imageView.setPadding(10,10,10,10);
            linear.addView(imageView);
        }
    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        linear = view.findViewById(R.id.linear);
        ViewPager = view.findViewById(R.id.ViewPager);
    }

    class Pageradapter extends FragmentPagerAdapter {

        public Pageradapter(FragmentManager fx) {
            super(fx);
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
