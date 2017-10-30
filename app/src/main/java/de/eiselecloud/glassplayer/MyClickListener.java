package de.eiselecloud.glassplayer;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by alexander on 30.10.17.
 */

public class MyClickListener implements RecyclerView.OnItemTouchListener{

    MyCallBack callBack;

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && callBack != null) {
            callBack.onClick(child, rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public void addCallBack(MyCallBack callBack){
        this.callBack = callBack;
    }

    public interface MyCallBack{
        void onClick(View view, int position);
    }
}
