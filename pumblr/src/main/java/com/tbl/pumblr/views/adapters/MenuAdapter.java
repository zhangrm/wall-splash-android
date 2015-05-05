package com.tbl.pumblr.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tbl.pumblr.R;
import com.tbl.pumblr.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 201503105229 on 2015/5/2.
 */
public class MenuAdapter extends BaseAdapter {

    private List<MenuItem> list = new ArrayList<>();
    private Context context;

    public MenuAdapter(Context context) {
        list.add(new MenuItem(R.drawable.menu_home_default,"Home"));
        list.add(new MenuItem(R.drawable.menu_favor_default,"Collect"));
        list.add(new MenuItem(R.drawable.menu_about_default,"About"));
        this.context = context;
    }

    @Override
    public int getCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public MenuItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.menu_listview_item,null);

        ImageView itemImageView = (ImageView) convertView.findViewById(R.id.menutItemIv);
        TextView itemTextView = (TextView) convertView.findViewById(R.id.menuItemTv);

        MenuItem item = getItem(position);

        itemImageView.setImageResource(item.getIcon());
        itemTextView.setText(item.getName());


        return convertView;
    }
}
