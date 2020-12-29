package com.example.x_etc_25_32.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.x_etc_25_32.R;
import com.example.x_etc_25_32.bean.GJCX;

import java.util.List;
import java.util.Map;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 11:05
 */
public class X_GJCX_adapter extends BaseExpandableListAdapter {
    private Map<String,List<GJCX>> map;
    private String[] arr = {"一号站台","二号站台"};

    public X_GJCX_adapter(Map<String, List<GJCX>> map) {
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(arr[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderFu holderFu;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gjcxfu, null);
            holderFu = new ViewHolderFu(convertView);
            convertView.setTag(holderFu);
        } else {
            holderFu = (ViewHolderFu) convertView.getTag();
        }
        holderFu.txtFu.setText(arr[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderZi holderZi;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gjcxzi, null);
            holderZi = new ViewHolderZi(convertView);
            convertView.setTag(holderZi);
        } else {
            holderZi = (ViewHolderZi) convertView.getTag();
        }
        GJCX gjcx = map.get(arr[groupPosition]).get(childPosition);
        switch (gjcx.getBus()){
            case 1:
                holderZi.txt1.setText("一号公交车");
                holderZi.txt2.setText(gjcx.getDistance()+"米");
                break;
            case 2:
                holderZi.txt1.setText("二号公交车");
                holderZi.txt2.setText(gjcx.getDistance()+"米");
                break;
        }



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class ViewHolderFu {
        private TextView txtFu;

        public ViewHolderFu(View view) {
            txtFu = view.findViewById(R.id.txt_fu);
        }
    }

    class ViewHolderZi {
        private TextView txt1;
        private TextView txt2;

        public ViewHolderZi(View view) {
            txt1 = view.findViewById(R.id.txt_1);
            txt2 = view.findViewById(R.id.txt_2);
        }
    }


}
