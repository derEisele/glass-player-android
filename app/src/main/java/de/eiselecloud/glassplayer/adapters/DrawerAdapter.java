package de.eiselecloud.glassplayer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.eiselecloud.glassplayer.DrawerMenuItem;
import de.eiselecloud.glassplayer.R;

/**
 * Created by alexander on 30.10.17.
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.MyViewHolder> {
    private List<DrawerMenuItem> menuItems;

    public final static int TYPE_HEADER = 0;
    public final static int TYPE_MENU = 1;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView itemName, nameTxt, serverTxt;
        public ImageView itemIcon;

        public MyViewHolder(View view, int viewType) {
            super(view);
            if (viewType == TYPE_HEADER) {
                nameTxt = (TextView) view.findViewById(R.id.nameTxt);
                serverTxt = (TextView) view.findViewById(R.id.serverTxt);
            } else {
                itemName = (TextView) view.findViewById(R.id.itemNameTxt);
                itemIcon = (ImageView) view.findViewById(R.id.itemIcon);
            }
        }
    }

    public DrawerAdapter(List<DrawerMenuItem> menuItems){
        this.menuItems = menuItems;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position == 0){
            // TODO:
            holder.nameTxt.setText("Text User");
            holder.serverTxt.setText("Server");
        }else {
            DrawerMenuItem menuItem = menuItems.get(position -1 );
            holder.itemIcon.setImageResource(menuItem.getItemIconResource());
            holder.itemName.setText(menuItem.getItemName());
        }
    }

    @Override
    public int getItemCount(){
        return menuItems.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEADER;
        }
        return TYPE_MENU;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == TYPE_HEADER){
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.drawer_header, parent, false);
        }else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.drawer_item, parent, false);
        }
        return new MyViewHolder(itemView, viewType);
    }

    public interface DrawerListener{
        void onItemClick(int position);
    }
}
