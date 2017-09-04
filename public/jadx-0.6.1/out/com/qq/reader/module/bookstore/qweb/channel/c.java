package com.qq.reader.module.bookstore.qweb.channel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;
import java.util.List;

/* compiled from: OtherAdapter */
public class c extends BaseAdapter {
    public List<ColumnWebEntity> a;
    boolean b = true;
    public int c = -1;
    private Context d;
    private TextView e;

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public c(Context context, List<ColumnWebEntity> list) {
        this.d = context;
        this.a = list;
    }

    public int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public ColumnWebEntity a(int i) {
        if (this.a == null || this.a.size() == 0) {
            return null;
        }
        return (ColumnWebEntity) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.subscribe_gridview_items, null);
        this.e = (TextView) inflate.findViewById(R.id.column_tv_newstitle);
        this.e.setText(a(i).getTitleName());
        if (!this.b && i == this.a.size() - 1) {
            this.e.setText("");
        }
        if (this.c == i) {
            this.e.setText("");
        }
        return inflate;
    }

    public void a(ColumnWebEntity columnWebEntity) {
        this.a.add(columnWebEntity);
        notifyDataSetChanged();
    }

    public void b(int i) {
        this.c = i;
        notifyDataSetChanged();
    }

    public void a() {
        try {
            this.a.remove(this.c);
            this.c = -1;
            notifyDataSetChanged();
        } catch (Exception e) {
        }
    }

    public void a(boolean z) {
        this.b = z;
    }
}
