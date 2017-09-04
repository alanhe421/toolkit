package com.qq.reader.module.bookstore.qweb.channel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;
import java.util.List;

/* compiled from: DragAdapter */
public class b extends BaseAdapter {
    boolean a = true;
    public List<ColumnWebEntity> b;
    public int c = -1;
    private boolean d = false;
    private Context e;
    private int f;
    private boolean g = false;
    private TextView h;

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public b(Context context, List<ColumnWebEntity> list) {
        this.e = context;
        this.b = list;
    }

    public int getCount() {
        return this.b == null ? 0 : this.b.size();
    }

    public ColumnWebEntity a(int i) {
        if (this.b == null || this.b.size() == 0) {
            return null;
        }
        return (ColumnWebEntity) this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.subscribe_gridview_items, null);
        this.h = (TextView) inflate.findViewById(R.id.column_tv_newstitle);
        this.h.setText(a(i).getTitleName());
        if (i < 2) {
            this.h.setEnabled(false);
        }
        if (this.g && i == this.f && !this.d) {
            this.h.setText("");
            this.h.setSelected(true);
            this.h.setEnabled(true);
            this.g = false;
        }
        if (!this.a && i == this.b.size() - 1) {
            this.h.setText("");
            this.h.setSelected(true);
            this.h.setEnabled(true);
        }
        if (this.c == i) {
            this.h.setText("");
        }
        return inflate;
    }

    public void a(ColumnWebEntity columnWebEntity) {
        this.b.add(columnWebEntity);
        notifyDataSetChanged();
    }

    public void a(int i, int i2) {
        try {
            this.f = i2;
            ColumnWebEntity a = a(i);
            if (i < i2) {
                this.b.add(i2 + 1, a);
                this.b.remove(i);
            } else {
                this.b.add(i2, a);
                this.b.remove(i + 1);
            }
            this.g = true;
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(int i) {
        this.c = i;
        notifyDataSetChanged();
    }

    public void a() {
        this.b.remove(this.c);
        this.c = -1;
        notifyDataSetChanged();
    }

    public void a(boolean z) {
        this.a = z;
    }

    public void b(boolean z) {
        this.d = z;
    }
}
