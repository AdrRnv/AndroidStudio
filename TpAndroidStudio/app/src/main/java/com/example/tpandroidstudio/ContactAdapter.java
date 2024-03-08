package com.example.tpandroidstudio;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contact> list;
    Uri img;

    public ContactAdapter(Context context, ArrayList<Contact> list) {
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

        TextView tv = (TextView)layoutItem.findViewById(R.id.TextPrenom);
        ImageView iv = layoutItem.findViewById(R.id.imageView);
        ImageView tv2 = layoutItem.findViewById(R.id.imageView2);

        Contact contact = list.get(position);
        String prenom = contact.getPrenom();
        tv.setText(prenom);

        if(contact.getImg().equals("homme") || contact.getImg().equals("femme")) {
            tv2.setImageResource(context.getResources().getIdentifier(contact.getImg(), "drawable", context.getPackageName()));
        }else{
            img = Uri.parse(contact.getImg());
            tv2.setImageURI(img);
        }

        Log.d("img", String.valueOf(img));
        Log.d("img2", String.valueOf(contact.getImg()));

        return layoutItem;
    }
}
