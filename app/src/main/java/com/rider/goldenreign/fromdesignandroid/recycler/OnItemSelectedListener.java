package com.rider.goldenreign.fromdesignandroid.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by goldenreign on 4/3/2018.
 */

public abstract class OnItemSelectedListener implements RecyclerView.OnItemTouchListener {

    private final GestureDetector gestureDetector;

    protected OnItemSelectedListener(Context context) {
        gestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }
                });
    }

    public abstract void onItemSelected(RecyclerView.ViewHolder holder, int position);

    @Override
    public final boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if (gestureDetector.onTouchEvent(e)) {
            final View touchedView = rv.findChildViewUnder(e.getX(), e.getY());
            if (touchedView == null) return false;

            onItemSelected(rv.findContainingViewHolder(touchedView),
                    rv.getChildAdapterPosition(touchedView));
        }
        return false;
    }

    @Override
    public final void onTouchEvent(RecyclerView rv, MotionEvent e) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public final void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        throw new UnsupportedOperationException("Not implemented");
    }
}