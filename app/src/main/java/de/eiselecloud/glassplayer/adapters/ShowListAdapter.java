package de.eiselecloud.glassplayer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.eiselecloud.glassplayer.R;
import de.eiselecloud.glassplayer.models.Show;

/**
 * Created by alexander on 30.10.17.
 */

public class ShowListAdapter extends RecyclerView.Adapter<ShowListAdapter.MyViewHolder> {
    private List<Show> shows;

    public  ShowListAdapter(List<Show> shows){
        this.shows = shows;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt;

        public MyViewHolder(View view){
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.titleTxt);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.titleTxt.setText(shows.get(position).getTitle());
    }

    @Override
    public int getItemCount(){
        return shows.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_item, parent, false);
        return new MyViewHolder(itemView);
    }
}
