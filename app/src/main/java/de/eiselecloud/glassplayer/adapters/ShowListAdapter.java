package de.eiselecloud.glassplayer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.eiselecloud.glassplayer.R;
import de.eiselecloud.glassplayer.models.Show;

/**
 * Created by alexander on 30.10.17.
 */

public class ShowListAdapter extends RecyclerView.Adapter<ShowListAdapter.MyViewHolder> {
    private List<Show> shows;
    private Context mContext;
    private OnItemClickListener clickListener;

    public  ShowListAdapter(Context context, List<Show> shows){
        this.mContext = context;
        this.shows = shows;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView titleTxt, infoTxt;
        ImageView posterImg;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            titleTxt = (TextView) view.findViewById(R.id.titleTxt);
            infoTxt = (TextView) view.findViewById(R.id.infoTxt);
            posterImg = (ImageView) view.findViewById(R.id.posterImg);
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

    public Show getShowByPosition(int position){
        return shows.get(position);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String infoStr = shows.get(position).getLang().toUpperCase() + " • " + shows.get(position).getYear().toString();
        holder.titleTxt.setText(shows.get(position).getTitle());
        holder.infoTxt.setText(infoStr);

        if (shows.get(0).getPoster() != "") {
            Picasso.with(mContext).load(shows.get(0).getPoster())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.posterImg);
        }
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
