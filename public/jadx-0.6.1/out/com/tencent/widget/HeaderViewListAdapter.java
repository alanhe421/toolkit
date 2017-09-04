package com.tencent.widget;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import com.qq.reader.common.monitor.debug.c;
import java.util.ArrayList;
import java.util.Iterator;

public class HeaderViewListAdapter implements Filterable, WrapperListAdapter {
    static final ArrayList<ListView$FixedViewInfo> EMPTY_INFO_LIST = new ArrayList();
    private final ListAdapter mAdapter;
    boolean mAreAllFixedViewsSelectable;
    ArrayList<ListView$FixedViewInfo> mFooterViewInfos;
    ArrayList<ListView$FixedViewInfo> mHeaderViewInfos;
    private final boolean mIsFilterable;

    public HeaderViewListAdapter(ArrayList<ListView$FixedViewInfo> arrayList, ArrayList<ListView$FixedViewInfo> arrayList2, ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
        this.mIsFilterable = listAdapter instanceof Filterable;
        if (arrayList == null) {
            this.mHeaderViewInfos = EMPTY_INFO_LIST;
        } else {
            this.mHeaderViewInfos = arrayList;
        }
        if (arrayList2 == null) {
            this.mFooterViewInfos = EMPTY_INFO_LIST;
        } else {
            this.mFooterViewInfos = arrayList2;
        }
        boolean z = areAllListInfosSelectable(this.mHeaderViewInfos) && areAllListInfosSelectable(this.mFooterViewInfos);
        this.mAreAllFixedViewsSelectable = z;
    }

    public int getHeadersCount() {
        return this.mHeaderViewInfos.size();
    }

    public int getFootersCount() {
        return this.mFooterViewInfos.size();
    }

    public boolean isEmpty() {
        return this.mAdapter == null || this.mAdapter.isEmpty();
    }

    private boolean areAllListInfosSelectable(ArrayList<ListView$FixedViewInfo> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (!((ListView$FixedViewInfo) it.next()).isSelectable) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean removeHeader(View view) {
        boolean z = false;
        for (int i = 0; i < this.mHeaderViewInfos.size(); i++) {
            if (((ListView$FixedViewInfo) this.mHeaderViewInfos.get(i)).view == view) {
                this.mHeaderViewInfos.remove(i);
                if (areAllListInfosSelectable(this.mHeaderViewInfos) && areAllListInfosSelectable(this.mFooterViewInfos)) {
                    z = true;
                }
                this.mAreAllFixedViewsSelectable = z;
                return true;
            }
        }
        return false;
    }

    public boolean removeFooter(View view) {
        boolean z = false;
        for (int i = 0; i < this.mFooterViewInfos.size(); i++) {
            if (((ListView$FixedViewInfo) this.mFooterViewInfos.get(i)).view == view) {
                this.mFooterViewInfos.remove(i);
                if (areAllListInfosSelectable(this.mHeaderViewInfos) && areAllListInfosSelectable(this.mFooterViewInfos)) {
                    z = true;
                }
                this.mAreAllFixedViewsSelectable = z;
                return true;
            }
        }
        return false;
    }

    public int getCount() {
        if (this.mAdapter != null) {
            return (getFootersCount() + getHeadersCount()) + this.mAdapter.getCount();
        }
        return getFootersCount() + getHeadersCount();
    }

    public boolean areAllItemsEnabled() {
        if (this.mAdapter == null) {
            return true;
        }
        if (this.mAreAllFixedViewsSelectable && this.mAdapter.areAllItemsEnabled()) {
            return true;
        }
        return false;
    }

    public boolean isEnabled(int i) {
        int headersCount = getHeadersCount();
        if (i < headersCount) {
            try {
                return ((ListView$FixedViewInfo) this.mHeaderViewInfos.get(i)).isSelectable;
            } catch (Exception e) {
                if (e != null) {
                    c.a("HeaderViewListAdapter", e.toString());
                }
            }
        }
        int i2 = i - headersCount;
        int i3 = 0;
        if (this.mAdapter != null) {
            i3 = this.mAdapter.getCount();
            if (i2 < i3) {
                return this.mAdapter.isEnabled(i2);
            }
        }
        headersCount = i3;
        try {
            return ((ListView$FixedViewInfo) this.mFooterViewInfos.get(i2 - headersCount)).isSelectable;
        } catch (Exception e2) {
            throw new RuntimeException("adapter index out of bound. adapter count: " + headersCount + ", footCount: " + this.mFooterViewInfos.size() + " , position: " + i2 + ", Adapter: " + this.mAdapter);
        }
    }

    public Object getItem(int i) {
        int headersCount = getHeadersCount();
        if (i < headersCount) {
            return ((ListView$FixedViewInfo) this.mHeaderViewInfos.get(i)).data;
        }
        int i2 = i - headersCount;
        headersCount = 0;
        if (this.mAdapter != null) {
            headersCount = this.mAdapter.getCount();
            if (i2 < headersCount) {
                return this.mAdapter.getItem(i2);
            }
        }
        return ((ListView$FixedViewInfo) this.mFooterViewInfos.get(i2 - headersCount)).data;
    }

    public long getItemId(int i) {
        int headersCount = getHeadersCount();
        if (this.mAdapter != null && i >= headersCount) {
            headersCount = i - headersCount;
            if (headersCount < this.mAdapter.getCount()) {
                return this.mAdapter.getItemId(headersCount);
            }
        }
        return -1;
    }

    public boolean hasStableIds() {
        if (this.mAdapter != null) {
            return this.mAdapter.hasStableIds();
        }
        return false;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int headersCount = getHeadersCount();
        if (i < headersCount) {
            return ((ListView$FixedViewInfo) this.mHeaderViewInfos.get(i)).view;
        }
        int i2 = i - headersCount;
        headersCount = 0;
        if (this.mAdapter != null) {
            headersCount = this.mAdapter.getCount();
            if (i2 < headersCount) {
                return this.mAdapter.getView(i2, view, viewGroup);
            }
        }
        int i3 = headersCount;
        try {
            return ((ListView$FixedViewInfo) this.mFooterViewInfos.get(i2 - i3)).view;
        } catch (Exception e) {
            throw new RuntimeException("adapter index out of bound. adapter count: " + i3 + ", footCount: " + this.mFooterViewInfos.size() + " , position: " + i2 + ", Adapter: " + this.mAdapter);
        }
    }

    public int getItemViewType(int i) {
        int headersCount = getHeadersCount();
        if (this.mAdapter != null && i >= headersCount) {
            headersCount = i - headersCount;
            if (headersCount < this.mAdapter.getCount()) {
                return this.mAdapter.getItemViewType(headersCount);
            }
        }
        return -2;
    }

    public int getViewTypeCount() {
        if (this.mAdapter != null) {
            return this.mAdapter.getViewTypeCount();
        }
        return 1;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.mAdapter != null) {
            this.mAdapter.registerDataSetObserver(dataSetObserver);
        }
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(dataSetObserver);
        }
    }

    public Filter getFilter() {
        if (this.mIsFilterable) {
            return ((Filterable) this.mAdapter).getFilter();
        }
        return null;
    }

    public ListAdapter getWrappedAdapter() {
        return this.mAdapter;
    }
}
