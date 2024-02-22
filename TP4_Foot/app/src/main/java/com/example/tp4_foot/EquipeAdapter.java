package com.example.tp4_foot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class EquipeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list;

    public EquipeAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (convertView == null) {
            layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.item_layout, parent, false);
        } else {
            layoutItem = (ConstraintLayout) convertView;
        }

        TextView tv = (TextView)layoutItem.findViewById(R.id.textView);
        ImageView iv = layoutItem.findViewById(R.id.imageView);

        int resID = context.getResources().getIdentifier("ic_"+list.get(position).toLowerCase(),"mipmap",context.getPackageName());
        iv.setImageResource(resID);

        tv.setText(list.get(position));

        return layoutItem;
    }
}
