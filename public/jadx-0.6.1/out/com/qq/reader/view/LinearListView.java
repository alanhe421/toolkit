package com.qq.reader.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class LinearListView extends IcsLinearLayout {
    private View c;
    private BaseAdapter d;
    private boolean e;
    private b f;
    private DataSetObserver g;

    public interface b {
        void a(LinearListView linearListView, View view, int i, long j);
    }

    private class a implements OnClickListener {
        int a;
        final /* synthetic */ LinearListView b;

        public a(LinearListView linearListView, int i) {
            this.b = linearListView;
            this.a = i;
        }

        public void onClick(View view) {
            if (this.b.f != null && this.b.d != null) {
                this.b.f.a(this.b, view, this.a, this.b.d.getItemId(this.a));
            }
        }
    }

    public LinearListView(Context context) {
        this(context, null);
    }

    public LinearListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new DataSetObserver(this) {
            final /* synthetic */ LinearListView a;

            {
                this.a = r1;
            }

            public void onChanged() {
                this.a.a();
            }

            public void onInvalidated() {
                this.a.a();
            }
        };
    }

    public void setOrientation(int i) {
        if (i != getOrientation()) {
            int i2 = this.b;
            this.b = this.a;
            this.a = i2;
        }
        super.setOrientation(i);
    }

    public void setDividerThickness(int i) {
        if (getOrientation() == 1) {
            this.b = i;
        } else {
            this.a = i;
        }
        requestLayout();
    }

    public ListAdapter getAdapter() {
        return this.d;
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        if (this.d != null) {
            this.d.unregisterDataSetObserver(this.g);
        }
        this.d = baseAdapter;
        if (this.d != null) {
            this.d.registerDataSetObserver(this.g);
            this.e = this.d.areAllItemsEnabled();
        }
        a();
    }

    public void setOnItemClickListener(b bVar) {
        this.f = bVar;
    }

    public final b getOnItemClickListener() {
        return this.f;
    }

    public void setEmptyView(View view) {
        this.c = view;
        ListAdapter adapter = getAdapter();
        boolean z = adapter == null || adapter.isEmpty();
        a(z);
    }

    public View getEmptyView() {
        return this.c;
    }

    private void a(boolean z) {
        if (!z) {
            if (this.c != null) {
                this.c.setVisibility(8);
            }
            setVisibility(0);
        } else if (this.c != null) {
            this.c.setVisibility(0);
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    private void a() {
        int i = 0;
        removeAllViews();
        boolean z = this.d == null || this.d.isEmpty();
        a(z);
        if (this.d != null) {
            while (i < this.d.getCount()) {
                View view = this.d.getView(i, null, this);
                if (view != null) {
                    if (this.e || this.d.isEnabled(i)) {
                        view.setOnClickListener(new a(this, i));
                    }
                    addViewInLayout(view, -1, view.getLayoutParams(), true);
                }
                i++;
            }
        }
    }

    public View b(int i) {
        return getChildAt(i);
    }
}
