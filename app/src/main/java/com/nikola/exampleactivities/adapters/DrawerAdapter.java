package com.nikola.exampleactivities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikola.exampleactivities.R;
import com.nikola.exampleactivities.model.NavigationItem;

import java.util.ArrayList;

/**
 * Created by Dzoni on 5/18/2017.
 */

public class DrawerAdapter extends BaseAdapter{

    Context context;
    ArrayList<NavigationItem> items;

    // Constructor should at least have context as a parameter
    public DrawerAdapter(Context context, ArrayList<NavigationItem> items) {
        this.context = context;
        this.items = items;
    }

    // Implement Methods

    //1.Returns the total number of items to be displayed in a list - Returns the item count
    @Override
    public int getCount() {
        //return 0;
        return items.size();
    }

    //2.Get the data item associated with the specified position- Returns an item
    @Override
    public Object getItem(int position) {
        //return null;
        return items.get(position);
    }

    //3. Returns a long value of item position to the adapter - Returns an item ID
    @Override
    public long getItemId(int position) {
        return 0; // return position
    }
    //4.Called when the list item view is ready to be displayed or about to be displayed
    //Returns a view that corresponds with an item
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //return null;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null); // layout xml file
        }

        TextView tvDrawerTitle = (TextView)view.findViewById(R.id.drawer_title);
        TextView tvDrawerSubtitle = (TextView)view.findViewById(R.id.drawer_subtitle);
        ImageView ivDrawerICon = (ImageView) view.findViewById(R.id.drawer_icon);

        tvDrawerTitle.setText(items.get(position).getTitle());
        tvDrawerSubtitle.setText(items.get(position).getSubtitle());
        ivDrawerICon.setImageResource(items.get(position).getIcon());
        return view;
    }
}
