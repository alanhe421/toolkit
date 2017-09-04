package com.qq.reader.view.web;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import com.qq.reader.common.monitor.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CustomArrayAdapter */
public class d<T> extends BaseAdapter implements Filterable {
    private List<T> a;
    private final Object b = new Object();
    private int c;
    private int d;
    private int e = 0;
    private boolean f = true;
    private Context g;
    private ArrayList<T> h;
    private a i;
    private LayoutInflater j;

    /* compiled from: CustomArrayAdapter */
    private class a extends Filter {
        final /* synthetic */ d a;

        private a(d dVar) {
            this.a = dVar;
        }

        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (this.a.h == null) {
                synchronized (this.a.b) {
                    this.a.h = new ArrayList(this.a.a);
                }
            }
            if (charSequence == null || charSequence.length() == 0) {
                synchronized (this.a.b) {
                    ArrayList arrayList = new ArrayList(this.a.h);
                    filterResults.values = arrayList;
                    filterResults.count = arrayList.size();
                }
            } else {
                ArrayList a = this.a.h;
                int size = a.size();
                ArrayList arrayList2 = new ArrayList(size);
                for (int i = 0; i < size; i++) {
                    arrayList2.add(a.get(i));
                }
                filterResults.values = arrayList2;
                filterResults.count = arrayList2.size();
            }
            return filterResults;
        }

        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            this.a.a = (List) filterResults.values;
            if (filterResults.count > 0) {
                this.a.notifyDataSetChanged();
            } else {
                this.a.notifyDataSetInvalidated();
            }
        }
    }

    public d(Context context, int i) {
        a(context, i, 0, new ArrayList());
    }

    public void a(T t) {
        if (this.h != null) {
            synchronized (this.b) {
                this.h.add(t);
                if (this.f) {
                    notifyDataSetChanged();
                }
            }
            return;
        }
        this.a.add(t);
        if (this.f) {
            notifyDataSetChanged();
        }
    }

    public void a() {
        if (this.h != null) {
            synchronized (this.b) {
                this.h.clear();
            }
        } else {
            this.a.clear();
        }
        if (this.f) {
            notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.f = true;
    }

    private void a(Context context, int i, int i2, List<T> list) {
        this.g = context;
        this.j = (LayoutInflater) context.getSystemService("layout_inflater");
        this.d = i;
        this.c = i;
        this.a = list;
        this.e = i2;
    }

    public int getCount() {
        if (this.a != null) {
            return this.a.size();
        }
        return 0;
    }

    public T getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return a(i, view, viewGroup, this.c);
    }

    private View a(int i, View view, ViewGroup viewGroup, int i2) {
        View inflate;
        if (view == null) {
            inflate = this.j.inflate(i2, viewGroup, false);
        } else {
            inflate = view;
        }
        try {
            TextView textView;
            if (this.e == 0) {
                textView = (TextView) inflate;
            } else {
                textView = (TextView) inflate.findViewById(this.e);
            }
            Object item = getItem(i);
            if (item != null) {
                if (item instanceof CharSequence) {
                    textView.setText((CharSequence) item);
                } else {
                    textView.setText(item.toString());
                }
            }
            return inflate;
        } catch (Throwable e) {
            f.a("ArrayAdapter", "You must supply a resource ID for a TextView");
            throw new IllegalStateException("ArrayAdapter requires the resource ID to be a TextView", e);
        }
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return a(i, view, viewGroup, this.d);
    }

    public Filter getFilter() {
        if (this.i == null) {
            this.i = new a();
        }
        return this.i;
    }
}
