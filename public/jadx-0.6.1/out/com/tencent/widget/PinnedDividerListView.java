package com.tencent.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.LinkedList;

public class PinnedDividerListView extends XListView implements AbsListView$OnScrollListener {
    private static final int PINNED_DIVDER_STATE_PINNED = 0;
    private static final int PINNED_DIVDER_STATE_PUSHING_UP = 1;
    private static final int PINNED_DIVIDER_STATE_INVISIBLE = -1;
    private DividerAdapter mAdapter = null;
    private Context mContext = null;
    private int mCurDividerViewPushUpDistance = 0;
    private int mCurDividerViewState = 0;
    private View mFloatingView = null;
    private LinkedList<View> mHeaderViews = new LinkedList();
    private OnLayoutListener mOnLayoutListener = null;
    private AbsListView$OnScrollListener mOnScrollListener = null;

    public static abstract class DividerAdapter extends BaseAdapter {
        public abstract void configDividerView(View view, int i);

        public abstract int getDividerLayout();

        public abstract boolean isDividerView(int i);
    }

    public interface OnLayoutListener {
        void onLayout(View view, int i, int i2, int i3, int i4);
    }

    public PinnedDividerListView(Context context) {
        super(context);
        init(context);
    }

    public PinnedDividerListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public PinnedDividerListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void setOnLayoutListener(OnLayoutListener onLayoutListener) {
        this.mOnLayoutListener = onLayoutListener;
    }

    public void addHeaderView(View view) {
        addHeaderView(view, null, false);
    }

    public void addHeaderView(View view, Object obj, boolean z) {
        super.addHeaderView(view, obj, z);
        this.mHeaderViews.add(view);
    }

    public boolean removeHeaderView(View view) {
        boolean removeHeaderView = super.removeHeaderView(view);
        if (removeHeaderView) {
            this.mHeaderViews.remove(view);
        }
        return removeHeaderView;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter instanceof DividerAdapter) {
            this.mAdapter = (DividerAdapter) listAdapter;
            int dividerLayout = this.mAdapter.getDividerLayout();
            if (dividerLayout != 0) {
                this.mFloatingView = LayoutInflater.from(this.mContext).inflate(dividerLayout, this, false);
                requestLayout();
            }
            super.setAdapter(this.mAdapter);
            return;
        }
        this.mAdapter = null;
        super.setAdapter(listAdapter);
    }

    public void setOnScrollListener(AbsListView$OnScrollListener absListView$OnScrollListener) {
        this.mOnScrollListener = absListView$OnScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.mFloatingView != null) {
            int i4;
            if (i >= this.mHeaderViews.size()) {
                View childAt;
                i -= this.mHeaderViews.size();
                this.mAdapter.configDividerView(this.mFloatingView, i);
                if (this.mAdapter.isDividerView(i)) {
                    this.mCurDividerViewState = 0;
                    i4 = 1;
                } else if (getChildAt(0).getBottom() > this.mFloatingView.getMeasuredHeight()) {
                    this.mCurDividerViewState = 0;
                    i4 = 0;
                } else if (this.mAdapter.isDividerView(i + 1)) {
                    this.mCurDividerViewState = 1;
                    i4 = 0;
                } else {
                    this.mCurDividerViewState = 0;
                    i4 = 0;
                }
                int childCount = getChildCount();
                if (i4 != 0) {
                    childAt = getChildAt(0);
                    if (childAt.getVisibility() != 4) {
                        childAt.setVisibility(4);
                    }
                }
                i4 = i4 != 0 ? 1 : 0;
                while (i4 < childCount) {
                    childAt = getChildAt(i4);
                    if (childAt.getVisibility() != 0) {
                        childAt.setVisibility(0);
                    }
                    i4++;
                }
                if (this.mCurDividerViewState == 1) {
                    View childAt2 = getChildAt(1);
                    this.mCurDividerViewPushUpDistance = this.mFloatingView.getMeasuredHeight() - (childAt2 != null ? childAt2.getTop() : 0);
                } else {
                    this.mCurDividerViewPushUpDistance = 0;
                }
                this.mFloatingView.setVisibility(0);
                this.mFloatingView.layout(0, -this.mCurDividerViewPushUpDistance, this.mFloatingView.getMeasuredWidth(), this.mFloatingView.getMeasuredHeight() - this.mCurDividerViewPushUpDistance);
            } else {
                this.mCurDividerViewState = -1;
                this.mFloatingView.setVisibility(4);
                i4 = this.mHeaderViews.size();
                if (this.mAdapter.getCount() > 0 && this.mAdapter.isDividerView(0) && i4 >= i && i4 < i + i2) {
                    getChildAt(i4 - i).setVisibility(0);
                }
            }
        }
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScroll(absListView, i, i2, i3);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mFloatingView != null) {
            measureChild(this.mFloatingView, i, i2);
        }
    }

    @SuppressLint({"WrongCall"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int firstVisiblePosition;
        super.onLayout(z, i, i2, i3, i4);
        if (this.mFloatingView != null) {
            firstVisiblePosition = getFirstVisiblePosition() - this.mHeaderViews.size();
            if (firstVisiblePosition >= 0) {
                this.mFloatingView.setVisibility(0);
                this.mFloatingView.layout(0, -this.mCurDividerViewPushUpDistance, this.mFloatingView.getMeasuredWidth(), this.mFloatingView.getMeasuredHeight() - this.mCurDividerViewPushUpDistance);
                this.mAdapter.configDividerView(this.mFloatingView, firstVisiblePosition);
            } else {
                this.mFloatingView.setVisibility(4);
            }
        }
        int childCount = getChildCount();
        for (firstVisiblePosition = this.mHeaderViews.size() + 1; firstVisiblePosition < childCount; firstVisiblePosition++) {
            View childAt = getChildAt(firstVisiblePosition);
            if (childAt.getVisibility() != 0) {
                childAt.setVisibility(0);
            }
        }
        if (this.mOnLayoutListener != null) {
            this.mOnLayoutListener.onLayout(this, i, i2, i3, i4);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mFloatingView != null && this.mFloatingView.getVisibility() == 0) {
            drawChild(canvas, this.mFloatingView, getDrawingTime());
        }
    }

    private void init(Context context) {
        this.mContext = context;
        super.setOnScrollListener(this);
    }
}
