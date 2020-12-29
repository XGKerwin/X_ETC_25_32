package com.example.x_etc_25_32.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.bean.YJFK;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 15:42
 */
public class X_WDYJ_adapter extends BaseAdapter {
    private List<YJFK> yjfkList;

    public X_WDYJ_adapter(List<YJFK> yjfkList) {
        this.yjfkList = yjfkList;
    }

    @Override
    public int getCount() {
        if (yjfkList.size() == 0) return 0;
        return yjfkList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wdyj, null);
            holder = new ViewHolder(convertView);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        YJFK yjfk = yjfkList.get(position);
        holder.biao.setText("标题："+yjfk.getTitle());
        holder.time1.setText("提交时间："+yjfk.getTime());
        return convertView;
    }

    class ViewHolder {
        private TextView biao;
        private TextView time1;

        public ViewHolder(View view) {
            biao = view.findViewById(R.id.biao);
            time1 = view.findViewById(R.id.time1);
        }
    }
}
