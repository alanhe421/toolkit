package com.tencent.widget;

import android.view.View;
import com.tencent.widget.AbsListView.LayoutParams;
import java.util.ArrayList;
import java.util.List;

class AbsListView$RecycleBin {
    private View[] mActiveViews = new View[0];
    private ArrayList<View> mCurrentScrap;
    private int mFirstActivePosition;
    private AbsListView$RecyclerListener mRecyclerListener;
    private ArrayList<View>[] mScrapViews;
    private int mViewTypeCount;
    final /* synthetic */ AbsListView this$0;

    AbsListView$RecycleBin(AbsListView absListView) {
        this.this$0 = absListView;
    }

    public void setViewTypeCount(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
        }
        ArrayList[] arrayListArr = new ArrayList[i];
        for (int i2 = 0; i2 < i; i2++) {
            arrayListArr[i2] = new ArrayList();
        }
        this.mViewTypeCount = i;
        this.mCurrentScrap = arrayListArr[0];
        this.mScrapViews = arrayListArr;
    }

    public void markChildrenDirty() {
        int i = 0;
        int size;
        if (this.mViewTypeCount == 1) {
            ArrayList arrayList = this.mCurrentScrap;
            size = arrayList.size();
            while (i < size) {
                ((View) arrayList.get(i)).forceLayout();
                i++;
            }
            return;
        }
        int i2 = this.mViewTypeCount;
        for (size = 0; size < i2; size++) {
            ArrayList arrayList2 = this.mScrapViews[size];
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ((View) arrayList2.get(i3)).forceLayout();
            }
        }
    }

    public boolean shouldRecycleViewType(int i) {
        return i >= 0;
    }

    void clear() {
        int size;
        int i;
        if (this.mViewTypeCount == 1) {
            ArrayList arrayList = this.mCurrentScrap;
            size = arrayList.size();
            for (i = 0; i < size; i++) {
                AbsListView.access$4000(this.this$0, (View) arrayList.remove((size - 1) - i), false);
            }
            return;
        }
        size = this.mViewTypeCount;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList arrayList2 = this.mScrapViews[i2];
            int size2 = arrayList2.size();
            for (i = 0; i < size2; i++) {
                AbsListView.access$4100(this.this$0, (View) arrayList2.remove((size2 - 1) - i), false);
            }
        }
    }

    void fillActiveViews(int i, int i2) {
        if (this.mActiveViews.length < i) {
            this.mActiveViews = new View[i];
        }
        this.mFirstActivePosition = i2;
        View[] viewArr = this.mActiveViews;
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = this.this$0.getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (!(layoutParams == null || layoutParams.viewType == -2)) {
                viewArr[i3] = childAt;
            }
        }
    }

    View getActiveView(int i) {
        int i2 = i - this.mFirstActivePosition;
        View[] viewArr = this.mActiveViews;
        if (i2 < 0 || i2 >= viewArr.length) {
            return null;
        }
        View view = viewArr[i2];
        viewArr[i2] = null;
        return view;
    }

    View getScrapView(int i) {
        if (this.mViewTypeCount == 1) {
            return AbsListView.retrieveFromScrap(this.mCurrentScrap, i);
        }
        int itemViewType = this.this$0.mAdapter.getItemViewType(i);
        if (itemViewType < 0 || itemViewType >= this.mScrapViews.length) {
            return null;
        }
        return AbsListView.retrieveFromScrap(this.mScrapViews[itemViewType], i);
    }

    void addScrapView(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams != null) {
            int i2 = layoutParams.viewType;
            if (shouldRecycleViewType(i2)) {
                layoutParams.scrappedFromPosition = i;
                if (this.mViewTypeCount == 1) {
                    AbsListView.access$4300(this.this$0, view);
                    this.mCurrentScrap.add(view);
                } else {
                    AbsListView.access$4300(this.this$0, view);
                    this.mScrapViews[i2].add(view);
                }
                if (this.mRecyclerListener != null) {
                    this.mRecyclerListener.onMovedToScrapHeap(view);
                }
            } else if (i2 != -2) {
                AbsListView.access$4200(this.this$0, view, false);
            }
        }
    }

    void scrapActiveViews() {
        boolean z;
        View[] viewArr = this.mActiveViews;
        boolean z2 = this.mRecyclerListener != null;
        if (this.mViewTypeCount > 1) {
            z = true;
        } else {
            z = false;
        }
        ArrayList arrayList = this.mCurrentScrap;
        for (int length = viewArr.length - 1; length >= 0; length--) {
            View view = viewArr[length];
            if (view != null) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                int i = layoutParams.viewType;
                viewArr[length] = null;
                if (shouldRecycleViewType(i)) {
                    if (z) {
                        arrayList = this.mScrapViews[i];
                    }
                    AbsListView.access$4300(this.this$0, view);
                    layoutParams.scrappedFromPosition = this.mFirstActivePosition + length;
                    arrayList.add(view);
                    if (z2) {
                        this.mRecyclerListener.onMovedToScrapHeap(view);
                    }
                } else if (i != -2) {
                    AbsListView.access$4400(this.this$0, view, false);
                }
            }
        }
        pruneScrapViews();
    }

    private void pruneScrapViews() {
        int length = this.mActiveViews.length;
        int i = this.mViewTypeCount;
        ArrayList[] arrayListArr = this.mScrapViews;
        for (int i2 = 0; i2 < i; i2++) {
            ArrayList arrayList = arrayListArr[i2];
            int size = arrayList.size();
            int i3 = size - length;
            size--;
            int i4 = 0;
            while (i4 < i3) {
                int i5 = size - 1;
                AbsListView.access$4500(this.this$0, (View) arrayList.remove(size), false);
                i4++;
                size = i5;
            }
        }
    }

    void reclaimScrapViews(List<View> list) {
        if (this.mViewTypeCount == 1) {
            list.addAll(this.mCurrentScrap);
            return;
        }
        int i = this.mViewTypeCount;
        ArrayList[] arrayListArr = this.mScrapViews;
        for (int i2 = 0; i2 < i; i2++) {
            list.addAll(arrayListArr[i2]);
        }
    }

    void setCacheColorHint(int i) {
        int size;
        int i2;
        if (this.mViewTypeCount == 1) {
            ArrayList arrayList = this.mCurrentScrap;
            size = arrayList.size();
            for (i2 = 0; i2 < size; i2++) {
                ((View) arrayList.get(i2)).setDrawingCacheBackgroundColor(i);
            }
        } else {
            size = this.mViewTypeCount;
            int i3;
            for (i3 = 0; i3 < size; i3++) {
                ArrayList arrayList2 = this.mScrapViews[i3];
                int size2 = arrayList2.size();
                for (i2 = 0; i2 < size2; i2++) {
                    ((View) arrayList2.get(i2)).setDrawingCacheBackgroundColor(i);
                }
            }
        }
        for (View view : this.mActiveViews) {
            if (view != null) {
                view.setDrawingCacheBackgroundColor(i);
            }
        }
    }
}
