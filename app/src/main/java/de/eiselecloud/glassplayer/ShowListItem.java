package de.eiselecloud.glassplayer;

import android.content.Context;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by alexander on 29.10.17.
 */

@Layout(R.layout.show_item)
public class ShowListItem {

    private int mID;
    private String mTitle;
    private Context mContext;
    private ShowCallBack mCallBack;

    @View(R.id.titleTxt)
    private TextView titleTxt;

    public ShowListItem(Context context, int id, String title){
        mContext = context;
        mID = id;
        mTitle = title;
    }

    @Resolve
    private void onResolved() {
        titleTxt.setText(mTitle);
    }

    @Click(R.id.show_item_view)
    private void onItemClick(){
        if(mCallBack != null)mCallBack.onClick(mID);
    }

    public interface ShowCallBack{
        void onClick(int showID);
    }

    public void setShowCallBack(ShowCallBack showCallBack){
        mCallBack = showCallBack;
    }
}
