package app20181205.luis.com.myapplication.recyclerView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

public class MyLayoutManager extends LinearLayoutManager {
    private static final String TAG = "MyLayoutManager";

    public MyLayoutManager(Context context) {
        super(context);
    }

    public MyLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int by = super.scrollVerticallyBy(dy, recycler, state);
        Log.v(TAG, "scrollVerticallyBy");
        //setChildOffsetsVertical(gravity, radius, center, peekDistance);
        return by;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int by = super.scrollHorizontallyBy(dx, recycler, state);
//        setChildOffsetsHorizontal(gravity, radius, center, peekDistance);
        Log.v(TAG, "scrollHorizontallyBy by="+by);
        return by;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
//        this.center = deriveCenter(gravity, getOrientation(), radius, peekDistance, center);
//        setChildOffsets(gravity, getOrientation(), radius, center, peekDistance);
        Log.v(TAG, "onLayoutChildren");
    }
}
