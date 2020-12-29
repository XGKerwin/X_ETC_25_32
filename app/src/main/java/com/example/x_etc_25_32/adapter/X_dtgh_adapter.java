package com.example.x_etc_25_32.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_25_32.bean.DTGH;
import com.example.x_etc_25_32.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 16:04
 */
public class X_dtgh_adapter extends BaseExpandableListAdapter {
    private List<DTGH> dtghList;

    public X_dtgh_adapter(List<DTGH> dtghList) {
        this.dtghList = dtghList;
    }

    @Override
    public int getGroupCount() {
        return dtghList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dtghList.get(groupPosition).getSite().size();
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
        ViewHolderFu holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dtcx_fu, null);
            holder = new ViewHolderFu(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderFu) convertView.getTag();
        }
        DTGH dtgh = dtghList.get(groupPosition);
        holder.txtFu.setText(dtgh.getName());
        if (!isExpanded){
            holder.imgFu.setImageResource(R.drawable.zuojiantou);
        }else {
            holder.imgFu.setImageResource(R.drawable.xiajiantou);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderZi holderZi;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dtcx_zi, null);
            holderZi = new ViewHolderZi(convertView);
            convertView.setTag(holderZi);
        } else {
            holderZi = (ViewHolderZi) convertView.getTag();
        }
        DTGH dtgh = dtghList.get(groupPosition);
        holderZi.txtZi.setText(dtgh.getSite().get(childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class ViewHolderFu {
        private TextView txtFu;
        private ImageView imgFu;

        public ViewHolderFu(View view) {
            txtFu = view.findViewById(R.id.txt_fu);
            imgFu = view.findViewById(R.id.img_fu);
        }
    }

    class ViewHolderZi {
        private TextView txtZi;

        public ViewHolderZi(View view) {
            txtZi = view.findViewById(R.id.txt_zi);
        }
    }
}
