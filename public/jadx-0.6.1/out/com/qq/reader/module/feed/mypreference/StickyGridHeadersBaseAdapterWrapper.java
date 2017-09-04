package com.qq.reader.module.feed.mypreference;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class StickyGridHeadersBaseAdapterWrapper extends BaseAdapter {
    private final Context a;
    private int b;
    private boolean c = false;
    private DataSetObserver d = new DataSetObserver(this) {
        final /* synthetic */ StickyGridHeadersBaseAdapterWrapper a;

        {
            this.a = r1;
        }

        public void onChanged() {
            this.a.b();
        }

        public void onInvalidated() {
            this.a.c = false;
        }
    };
    private final e e;
    private StickyGridHeadersGridView f;
    private View g;
    private View h;
    private int i = 1;

    protected class FillerView extends View {
        final /* synthetic */ StickyGridHeadersBaseAdapterWrapper a;
        private View b;

        public FillerView(StickyGridHeadersBaseAdapterWrapper stickyGridHeadersBaseAdapterWrapper, Context context) {
            this.a = stickyGridHeadersBaseAdapterWrapper;
            super(context);
        }

        public void setMeasureTarget(View view) {
            this.b = view;
        }

        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(this.b.getMeasuredHeight(), 1073741824));
        }
    }

    protected class HeaderFillerView extends FrameLayout {
        final /* synthetic */ StickyGridHeadersBaseAdapterWrapper a;
        private int b;

        public HeaderFillerView(StickyGridHeadersBaseAdapterWrapper stickyGridHeadersBaseAdapterWrapper, Context context) {
            this.a = stickyGridHeadersBaseAdapterWrapper;
            super(context);
        }

        public int getHeaderId() {
            return this.b;
        }

        public void setHeaderId(int i) {
            this.b = i;
        }

        protected LayoutParams generateDefaultLayoutParams() {
            return new LayoutParams(-1, -1);
        }

        protected void onMeasure(int i, int i2) {
            View view = (View) getTag();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            if (view.getVisibility() != 8) {
                view.measure(getChildMeasureSpec(MeasureSpec.makeMeasureSpec(this.a.f.getWidth(), 1073741824), 0, layoutParams.width), getChildMeasureSpec(MeasureSpec.makeMeasureSpec(0, 0), 0, layoutParams.height));
            }
            setMeasuredDimension(MeasureSpec.getSize(i), view.getMeasuredHeight());
        }
    }

    protected class a {
        protected int a;
        protected int b;
        final /* synthetic */ StickyGridHeadersBaseAdapterWrapper c;

        protected a(StickyGridHeadersBaseAdapterWrapper stickyGridHeadersBaseAdapterWrapper, int i, int i2) {
            this.c = stickyGridHeadersBaseAdapterWrapper;
            this.b = i;
            this.a = i2;
        }
    }

    public StickyGridHeadersBaseAdapterWrapper(Context context, StickyGridHeadersGridView stickyGridHeadersGridView, e eVar) {
        this.a = context;
        this.e = eVar;
        this.f = stickyGridHeadersGridView;
        eVar.registerDataSetObserver(this.d);
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public int getCount() {
        int i = 0;
        if (this.c) {
            return this.b;
        }
        this.b = 0;
        int a = this.e.a();
        if (a == 0) {
            this.b = this.e.getCount();
            this.c = true;
            return this.b;
        }
        while (i < a) {
            this.b += (this.e.a(i) + d(i)) + this.i;
            i++;
        }
        this.c = true;
        return this.b;
    }

    public Object getItem(int i) throws ArrayIndexOutOfBoundsException {
        a c = c(i);
        if (c.b == -1 || c.b == -2) {
            return null;
        }
        return this.e.getItem(c.b);
    }

    public long getItemId(int i) {
        a c = c(i);
        if (c.b == -2) {
            return -1;
        }
        if (c.b == -1) {
            return -2;
        }
        if (c.b == -3) {
            return -3;
        }
        return this.e.getItemId(c.b);
    }

    public int getItemViewType(int i) {
        a c = c(i);
        if (c.b == -2) {
            return 1;
        }
        if (c.b == -1) {
            return 0;
        }
        if (c.b == -3) {
            return 2;
        }
        int itemViewType = this.e.getItemViewType(c.b);
        return itemViewType != -1 ? itemViewType + 3 : itemViewType;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a c = c(i);
        if (c.b == -2) {
            View b = b(c.a, view, viewGroup);
            View a = this.e.a(c.a, (View) b.getTag(), viewGroup);
            this.f.b((View) b.getTag());
            b.setTag(a);
            this.f.a(a);
            this.g = b;
            b.forceLayout();
            return b;
        } else if (c.b == -3) {
            r0 = a(view, viewGroup, this.g);
            r0.forceLayout();
            return r0;
        } else if (c.b == -1) {
            return a(view, viewGroup, this.h);
        } else {
            r0 = this.e.getView(c.b, view, viewGroup);
            this.h = r0;
            return r0;
        }
    }

    public int getViewTypeCount() {
        return this.e.getViewTypeCount() + 3;
    }

    public e a() {
        return this.e;
    }

    public boolean hasStableIds() {
        return this.e.hasStableIds();
    }

    public boolean isEmpty() {
        return this.e.isEmpty();
    }

    public boolean isEnabled(int i) {
        a c = c(i);
        if (c.b == -1 || c.b == -2) {
            return false;
        }
        return this.e.isEnabled(c.b);
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        super.registerDataSetObserver(dataSetObserver);
        this.e.registerDataSetObserver(dataSetObserver);
    }

    public void a(int i) {
        this.i = i;
        this.c = false;
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        super.unregisterDataSetObserver(dataSetObserver);
        this.e.unregisterDataSetObserver(dataSetObserver);
    }

    private FillerView a(View view, ViewGroup viewGroup, View view2) {
        view = (FillerView) view;
        if (view == null) {
            view = new FillerView(this, this.a);
        }
        view.setMeasureTarget(view2);
        return view;
    }

    private HeaderFillerView b(int i, View view, ViewGroup viewGroup) {
        HeaderFillerView headerFillerView = (HeaderFillerView) view;
        if (headerFillerView == null) {
            return new HeaderFillerView(this, this.a);
        }
        return headerFillerView;
    }

    private int d(int i) {
        if (this.i == 0) {
            return 0;
        }
        int a = this.e.a(i) % this.i;
        if (a != 0) {
            return this.i - a;
        }
        return 0;
    }

    protected long b(int i) {
        return (long) c(i).a;
    }

    protected View a(int i, View view, ViewGroup viewGroup) {
        if (this.e.a() == 0) {
            return null;
        }
        return this.e.a(c(i).a, view, viewGroup);
    }

    protected a c(int i) {
        int i2 = 0;
        int a = this.e.a();
        if (a != 0) {
            int i3 = i;
            while (i2 < a) {
                int a2 = this.e.a(i2);
                if (i == 0) {
                    return new a(this, -2, i2);
                }
                int i4 = i - this.i;
                if (i4 < 0) {
                    return new a(this, -3, i2);
                }
                int i5 = i3 - this.i;
                if (i4 < a2) {
                    return new a(this, i5, i2);
                }
                i3 = d(i2);
                i5 -= i3;
                i = i4 - (i3 + a2);
                if (i < 0) {
                    return new a(this, -1, i2);
                }
                i2++;
                i3 = i5;
            }
            return new a(this, -1, i2);
        } else if (i >= this.e.getCount()) {
            return new a(this, -1, 0);
        } else {
            return new a(this, i, 0);
        }
    }

    protected void b() {
        int i = 0;
        this.b = 0;
        int a = this.e.a();
        if (a == 0) {
            this.b = this.e.getCount();
            this.c = true;
            return;
        }
        while (i < a) {
            this.b += this.e.a(i) + this.i;
            i++;
        }
        this.c = true;
    }
}
