package com.example.x_etc_25_32.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.bean.SHZS;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 10:20
 */
public class X_shzs_adapter extends BaseAdapter {
    private List<SHZS> shzsList;

    public X_shzs_adapter(List<SHZS> shzsList) {
        this.shzsList = shzsList;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shzs, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SHZS shzs = shzsList.get(0);

        switch (position){
            case 0:
                holder.img.setImageResource(R.drawable.shengtaiyang);
                int guang = shzs.getIllumination();
                if (guang<1500){
                    holder.tit.setText("非常弱");
                    holder.tit.setTextColor(Color.BLACK);
                    holder.txtMsg.setText("您无须担心紫外线");
                }else if (guang>3000){
                    holder.tit.setText("弱");
                    holder.tit.setTextColor(Color.BLACK);
                    holder.txtMsg.setText("外出适当涂抹低倍数防晒霜");
                }else {
                    holder.tit.setText("强");
                    holder.tit.setTextColor(Color.RED);
                    holder.txtMsg.setText("外出需要涂抹中倍数防晒霜");
                }
                break;
            case 1:
                holder.img.setImageResource(R.drawable.shengpaobu);
                int pm = shzs.getPm25();
                if (pm<100){
                    holder.tit.setText("良好");
                    holder.tit.setTextColor(Color.BLACK);
                    holder.txtMsg.setText("气象条件会对晨练影响不大");
                    break;
                }else if (pm<200){
                    holder.tit.setText("轻度");
                    holder.tit.setTextColor(Color.BLACK);
                    holder.txtMsg.setText("受天气影响，较不宜晨练");
                    break;
                }else if (pm<300){
                    holder.tit.setText("重度");
                    holder.tit.setTextColor(Color.RED);
                    holder.txtMsg.setText("减少外出，出行注意戴口罩");
                    break;
                }else if (pm>300){
                    holder.tit.setText("爆表");
                    holder.tit.setTextColor(Color.RED);
                    holder.txtMsg.setText("停止一切外出活动");
                    break;
                }
        }

        return convertView;
    }


    class ViewHolder {
        private ImageView img;
        private TextView tit;
        private TextView txtMsg;

        public ViewHolder(View view) {
            img = view.findViewById(R.id.img);
            tit = view.findViewById(R.id.tit);
            txtMsg = view.findViewById(R.id.txt_msg);
        }
    }
}
