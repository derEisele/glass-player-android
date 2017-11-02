package de.eiselecloud.glassplayer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.eiselecloud.glassplayer.R;
import de.eiselecloud.glassplayer.models.Episode;

/**
 * Created by alexander on 31.10.17.
 */

public class EpisodeListAdapter extends RecyclerView.Adapter<EpisodeListAdapter.MyViewHolder> {
    private List<Episode> episodes;
    private Context mContext;
    private OnItemClickListener clickListener;

    public  EpisodeListAdapter(Context context){
        this.mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView titleTxt, numberTxt;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            titleTxt = (TextView) view.findViewById(R.id.titleTxt);
            numberTxt = (TextView) view.findViewById(R.id.numberTxt);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null){
                clickListener.onClick(view, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if(clickListener != null){
                clickListener.onLongClick(view, getAdapterPosition());
            }
            return false;
        }
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public void setClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public void setEpisodes(List<Episode> episodes){
        Log.i("GLASS", "Set episodes");
        this.episodes = episodes;
        notifyDataSetChanged();
    }

    public Episode getEpisodeByPosition(int position){
        return episodes.get(position);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String numberText = "S" + episodes.get(position).getSeason() + " • E" + episodes.get(position).getEpisode();
        holder.titleTxt.setText(episodes.get(position).getTitle());
        holder.numberTxt.setText(numberText);
    }

    @Override
    public int getItemCount(){
        if (episodes != null){
            return episodes.size();
        } else {
            return 0;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.episode_item, parent, false);
        return new MyViewHolder(itemView);
    }
}

