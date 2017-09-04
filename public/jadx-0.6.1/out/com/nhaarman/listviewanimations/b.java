package com.nhaarman.listviewanimations;

import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import com.nhaarman.listviewanimations.b.a;
import com.nhaarman.listviewanimations.b.d;
import com.nhaarman.listviewanimations.b.e;
import com.nhaarman.listviewanimations.b.f;
import com.nhaarman.listviewanimations.b.g;

/* compiled from: BaseAdapterDecorator */
public abstract class b extends BaseAdapter implements SectionIndexer, d, f, g {
    private final BaseAdapter a;
    private e b;

    protected b(BaseAdapter baseAdapter) {
        this.a = baseAdapter;
    }

    public BaseAdapter a() {
        return this.a;
    }

    protected BaseAdapter b() {
        BaseAdapter baseAdapter = this.a;
        while (baseAdapter instanceof b) {
            baseAdapter = ((b) baseAdapter).a();
        }
        return baseAdapter;
    }

    public void a(AbsListView absListView) {
        a(new a(absListView));
    }

    public e c() {
        return this.b;
    }

    public void a(e eVar) {
        this.b = eVar;
        if (this.a instanceof f) {
            ((f) this.a).a(eVar);
        }
    }

    public int getCount() {
        return this.a.getCount();
    }

    public Object getItem(int i) {
        return this.a.getItem(i);
    }

    public long getItemId(int i) {
        return this.a.getItemId(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.a.getView(i, view, viewGroup);
    }

    public boolean areAllItemsEnabled() {
        return this.a.areAllItemsEnabled();
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return this.a.getDropDownView(i, view, viewGroup);
    }

    public int getItemViewType(int i) {
        return this.a.getItemViewType(i);
    }

    public int getViewTypeCount() {
        return this.a.getViewTypeCount();
    }

    public boolean hasStableIds() {
        return this.a.hasStableIds();
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    public boolean isEnabled(int i) {
        return this.a.isEnabled(i);
    }

    public void notifyDataSetChanged() {
        if (!(this.a instanceof a)) {
            this.a.notifyDataSetChanged();
        }
    }

    public void notifyDataSetInvalidated() {
        this.a.notifyDataSetInvalidated();
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.a.registerDataSetObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.a.unregisterDataSetObserver(dataSetObserver);
    }

    public int getPositionForSection(int i) {
        if (this.a instanceof SectionIndexer) {
            return ((SectionIndexer) this.a).getPositionForSection(i);
        }
        return 0;
    }

    public int getSectionForPosition(int i) {
        if (this.a instanceof SectionIndexer) {
            return ((SectionIndexer) this.a).getSectionForPosition(i);
        }
        return 0;
    }

    public Object[] getSections() {
        Object[] objArr = new Object[0];
        if (this.a instanceof SectionIndexer) {
            return ((SectionIndexer) this.a).getSections();
        }
        return objArr;
    }

    public void a(int i, int i2) {
        if (this.a instanceof g) {
            ((g) this.a).a(i, i2);
        } else {
            Log.w("ListViewAnimations", "Warning: swapItems called on an adapter that does not implement Swappable!");
        }
    }

    public void a(int i, Object obj) {
        if (this.a instanceof d) {
            ((d) this.a).a(i, obj);
        } else {
            Log.w("ListViewAnimations", "Warning: add called on an adapter that does not implement Insertable!");
        }
    }
}
