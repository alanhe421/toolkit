package com.qq.reader.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import com.qq.reader.common.monitor.f;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

public class GridViewWithHeaderAndFooter extends GridView {
    public static boolean a = false;
    private int b = -1;
    private View c = null;
    private int d = -1;
    private ArrayList<a> e = new ArrayList();
    private ArrayList<a> f = new ArrayList();

    private static class a {
        public ViewGroup a;
        public Object b;
        public boolean c;

        private a() {
        }
    }

    public static class b implements Filterable, WrapperListAdapter {
        static final ArrayList<a> a = new ArrayList();
        ArrayList<a> b;
        ArrayList<a> c;
        boolean d;
        private final DataSetObservable e = new DataSetObservable();
        private final ListAdapter f;
        private int g = 1;
        private int h = -1;
        private final boolean i;
        private boolean j = true;
        private boolean k = false;

        public b(ArrayList<a> arrayList, ArrayList<a> arrayList2, ListAdapter listAdapter) {
            boolean z = true;
            this.f = listAdapter;
            this.i = listAdapter instanceof Filterable;
            if (arrayList == null) {
                this.b = a;
            } else {
                this.b = arrayList;
            }
            if (arrayList2 == null) {
                this.c = a;
            } else {
                this.c = arrayList2;
            }
            if (!(a(this.b) && a(this.c))) {
                z = false;
            }
            this.d = z;
        }

        public void a(int i) {
            if (i >= 1 && this.g != i) {
                this.g = i;
                c();
            }
        }

        public void b(int i) {
            this.h = i;
        }

        public int a() {
            return this.b.size();
        }

        public int b() {
            return this.c.size();
        }

        public boolean isEmpty() {
            return (this.f == null || this.f.isEmpty()) && a() == 0 && b() == 0;
        }

        private boolean a(ArrayList<a> arrayList) {
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (!((a) it.next()).c) {
                        return false;
                    }
                }
            }
            return true;
        }

        public int getCount() {
            if (this.f != null) {
                return ((b() + a()) * this.g) + d();
            }
            return (b() + a()) * this.g;
        }

        public boolean areAllItemsEnabled() {
            if (this.f == null) {
                return true;
            }
            if (this.d && this.f.areAllItemsEnabled()) {
                return true;
            }
            return false;
        }

        private int d() {
            return (int) (Math.ceil((double) ((1.0f * ((float) this.f.getCount())) / ((float) this.g))) * ((double) this.g));
        }

        public boolean isEnabled(int i) {
            int a = a() * this.g;
            if (i < a) {
                boolean z;
                if (i % this.g == 0 && ((a) this.b.get(i / this.g)).c) {
                    z = true;
                } else {
                    z = false;
                }
                return z;
            }
            int i2 = i - a;
            if (this.f != null) {
                a = d();
                if (i2 < a) {
                    if (i2 >= this.f.getCount() || !this.f.isEnabled(i2)) {
                        return false;
                    }
                    return true;
                }
            }
            a = 0;
            a = i2 - a;
            if (a % this.g == 0 && ((a) this.c.get(a / this.g)).c) {
                return true;
            }
            return false;
        }

        public Object getItem(int i) {
            int a = a() * this.g;
            if (i >= a) {
                int i2 = i - a;
                a = 0;
                if (this.f != null) {
                    a = d();
                    if (i2 < a) {
                        return i2 < this.f.getCount() ? this.f.getItem(i2) : null;
                    }
                }
                a = i2 - a;
                return a % this.g == 0 ? ((a) this.c.get(a)).b : null;
            } else if (i % this.g == 0) {
                return ((a) this.b.get(i / this.g)).b;
            } else {
                return null;
            }
        }

        public long getItemId(int i) {
            int a = a() * this.g;
            if (this.f != null && i >= a) {
                a = i - a;
                if (a < this.f.getCount()) {
                    return this.f.getItemId(a);
                }
            }
            return -1;
        }

        public boolean hasStableIds() {
            if (this.f != null) {
                return this.f.hasStableIds();
            }
            return false;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int i2 = 0;
            if (GridViewWithHeaderAndFooter.a) {
                String str = "grid-view-with-header-and-footer";
                String str2 = "getView: %s, reused: %s";
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(i);
                objArr[1] = Boolean.valueOf(view == null);
                f.d(str, String.format(str2, objArr));
            }
            int a = a() * this.g;
            View view2;
            if (i < a) {
                view2 = ((a) this.b.get(i / this.g)).a;
                if (i % this.g == 0) {
                    return view2;
                }
                if (view == null) {
                    view = new View(viewGroup.getContext());
                }
                view.setVisibility(4);
                view.setMinimumHeight(view2.getHeight());
                return view;
            }
            a = i - a;
            if (this.f != null) {
                i2 = d();
                if (a < i2) {
                    if (a < this.f.getCount()) {
                        return this.f.getView(a, view, viewGroup);
                    }
                    if (view == null) {
                        view = new View(viewGroup.getContext());
                    }
                    view.setVisibility(4);
                    view.setMinimumHeight(this.h);
                    return view;
                }
            }
            a -= i2;
            if (a < getCount()) {
                view2 = ((a) this.c.get(a / this.g)).a;
                if (i % this.g == 0) {
                    return view2;
                }
                if (view == null) {
                    view = new View(viewGroup.getContext());
                }
                view.setVisibility(4);
                view.setMinimumHeight(view2.getHeight());
                return view;
            }
            throw new ArrayIndexOutOfBoundsException(i);
        }

        public int getItemViewType(int i) {
            int i2;
            int a = a() * this.g;
            if (this.f == null) {
                i2 = 0;
            } else {
                i2 = this.f.getViewTypeCount() - 1;
            }
            int i3 = -2;
            if (this.j && i < a) {
                if (i == 0 && this.k) {
                    i3 = (((this.b.size() + i2) + this.c.size()) + 1) + 1;
                }
                if (i % this.g != 0) {
                    i3 = ((i / this.g) + 1) + i2;
                }
            }
            int i4 = i - a;
            if (this.f != null) {
                int i5;
                a = d();
                if (i4 >= 0 && i4 < a) {
                    if (i4 < this.f.getCount()) {
                        i5 = a;
                        a = this.f.getItemViewType(i4);
                        i3 = i5;
                    } else if (this.j) {
                        i5 = a;
                        a = (this.b.size() + i2) + 1;
                        i3 = i5;
                    }
                }
                i5 = a;
                a = i3;
                i3 = i5;
            } else {
                a = i3;
                i3 = 0;
            }
            if (this.j) {
                i3 = i4 - i3;
                if (i3 >= 0 && i3 < getCount() && i3 % this.g != 0) {
                    a = ((i2 + this.b.size()) + 1) + ((i3 / this.g) + 1);
                }
            }
            if (GridViewWithHeaderAndFooter.a) {
                f.d("grid-view-with-header-and-footer", String.format("getItemViewType: pos: %s, result: %s", new Object[]{Integer.valueOf(i), Integer.valueOf(a), Boolean.valueOf(this.j), Boolean.valueOf(this.k)}));
            }
            return a;
        }

        public int getViewTypeCount() {
            int viewTypeCount = this.f == null ? 1 : this.f.getViewTypeCount();
            if (this.j) {
                int size = (this.b.size() + 1) + this.c.size();
                if (this.k) {
                    size++;
                }
                viewTypeCount += size;
            }
            if (GridViewWithHeaderAndFooter.a) {
                f.d("grid-view-with-header-and-footer", String.format("getViewTypeCount: %s", new Object[]{Integer.valueOf(viewTypeCount)}));
            }
            return viewTypeCount;
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.e.registerObserver(dataSetObserver);
            if (this.f != null) {
                this.f.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.e.unregisterObserver(dataSetObserver);
            if (this.f != null) {
                this.f.unregisterDataSetObserver(dataSetObserver);
            }
        }

        public Filter getFilter() {
            if (this.i) {
                return ((Filterable) this.f).getFilter();
            }
            return null;
        }

        public ListAdapter getWrappedAdapter() {
            return this.f;
        }

        public void c() {
            this.e.notifyChanged();
        }
    }

    private void a() {
    }

    public GridViewWithHeaderAndFooter(Context context) {
        super(context);
        a();
    }

    public GridViewWithHeaderAndFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public GridViewWithHeaderAndFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ListAdapter adapter = getAdapter();
        if (adapter != null && (adapter instanceof b)) {
            ((b) adapter).a(getNumColumnsCompatible());
            ((b) adapter).b(getRowHeight());
        }
    }

    public void setClipChildren(boolean z) {
    }

    public void setClipChildrenSupper(boolean z) {
        super.setClipChildren(false);
    }

    public int getHeaderViewCount() {
        return this.e.size();
    }

    public int getFooterViewCount() {
        return this.f.size();
    }

    @TargetApi(11)
    private int getNumColumnsCompatible() {
        return super.getNumColumns();
    }

    @TargetApi(16)
    private int getColumnWidthCompatible() {
        if (VERSION.SDK_INT >= 16) {
            return 0;
        }
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mColumnWidth");
            declaredField.setAccessible(true);
            return declaredField.getInt(this);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c = null;
    }

    public int getRowHeight() {
        if (this.d > 0) {
            return this.d;
        }
        ListAdapter adapter = getAdapter();
        int numColumnsCompatible = getNumColumnsCompatible();
        if (adapter == null || adapter.getCount() <= (this.e.size() + this.f.size()) * numColumnsCompatible) {
            return -1;
        }
        int columnWidthCompatible = getColumnWidthCompatible();
        View view = getAdapter().getView(numColumnsCompatible * this.e.size(), this.c, this);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2, 0);
            view.setLayoutParams(layoutParams);
        }
        view.measure(getChildMeasureSpec(MeasureSpec.makeMeasureSpec(columnWidthCompatible, 1073741824), 0, layoutParams.width), getChildMeasureSpec(MeasureSpec.makeMeasureSpec(0, 0), 0, layoutParams.height));
        this.c = view;
        this.d = view.getMeasuredHeight();
        return this.d;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.e.size() > 0 || this.f.size() > 0) {
            ListAdapter bVar = new b(this.e, this.f, listAdapter);
            int numColumnsCompatible = getNumColumnsCompatible();
            if (numColumnsCompatible > 1) {
                bVar.a(numColumnsCompatible);
            }
            bVar.b(getRowHeight());
            super.setAdapter(bVar);
            return;
        }
        super.setAdapter(listAdapter);
    }

    public void setNumColumns(int i) {
        super.setNumColumns(i);
        this.b = i;
        ListAdapter adapter = getAdapter();
        if (adapter != null && (adapter instanceof b)) {
            ((b) adapter).a(i);
        }
    }
}
