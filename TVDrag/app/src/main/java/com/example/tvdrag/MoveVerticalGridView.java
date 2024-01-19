package com.example.tvdrag;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.VerticalGridView;

public class MoveVerticalGridView extends VerticalGridView {

    boolean isSwapMode;
    int spanCount;
    ArrayObjectAdapter adapter;
    public MoveVerticalGridView(Context context) {
        super(context);
    }

    public MoveVerticalGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveVerticalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public View focusSearch(View focused, int direction) {
        View itemView = findContainingItemView(focused);
        if (itemView != null && isSwapMode()) {
            return swapItemsIfNeeded(itemView, direction);
        }
        return super.focusSearch(focused, direction);
    }

    private View swapItemsIfNeeded(View focused, int direction) {
        int position = getChildAdapterPosition(focused);
        ItemAnimator animator = getItemAnimator();
        if ((animator == null || !animator.isRunning())
                && canMoveInDirection(position, direction)) {
            int spanCount = getSpanCount();
            switch (direction) {
                case FOCUS_LEFT:
                    moveItem(position, position - 1);
                    break;
                case FOCUS_UP:
                    moveItem(position, position - spanCount);
                    break;
                case FOCUS_RIGHT:
                    moveItem(position, position + 1);
                    break;
                case FOCUS_DOWN:
                    moveItem(position, position + spanCount);
                    break;
                default:
                    break;
            }
        }
        return focused;
    }

    private boolean canMoveInDirection(int position, int direction) {
        int spanCount = getSpanCount();
        LayoutManager m = getLayoutManager();
        assert m != null;
        if (direction == FOCUS_LEFT) {
            return position % spanCount > 0;
        } else if (direction == FOCUS_UP) {
            return position - spanCount >= 0;
        } else if (direction == FOCUS_RIGHT) {
            return !(position % spanCount >= (spanCount - 1) ||
                    position >= m.getItemCount() - 1);
        } else if (direction == FOCUS_DOWN) {
            return position + spanCount <= m.getItemCount() - 1;
        }
        return false;
    }

    private void moveItem(int fromPosition, int toPosition) {
        if (adapter != null) {
            adapter.move(fromPosition, toPosition);
        }
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
        setNumColumns(spanCount);
    }

    public void setAdapter(ArrayObjectAdapter adapter) {
        this.adapter = adapter;
        ItemBridgeAdapter itemBridgeAdapter = new ItemBridgeAdapter(adapter);
        setAdapter(itemBridgeAdapter);
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSwapMode(boolean swapMode) {
        isSwapMode = swapMode;
    }

    public boolean isSwapMode() {
        return isSwapMode;
    }
}
