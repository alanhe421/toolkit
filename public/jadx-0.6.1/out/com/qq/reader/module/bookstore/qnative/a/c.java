package com.qq.reader.module.bookstore.qnative.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.z;
import java.util.List;

/* compiled from: NativeBookStoreListAdapter */
public class c extends BaseAdapter {
    Context a;
    List<s> b;
    private a c = null;
    private boolean d = false;
    private ListCardCommon e;

    public c(Context context, ListCardCommon listCardCommon, boolean z) {
        this.a = context;
        this.e = listCardCommon;
        this.b = this.e.getItemList();
        this.d = z;
    }

    public int getCount() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public Object getItem(int i) {
        if (this.b == null) {
            return null;
        }
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.a.getSystemService("layout_inflater")).inflate(this.e.getCardItemLayoutId(), null);
        }
        final z zVar = (z) getItem(i);
        zVar.a(view, i, this.d);
        if (this.c != null) {
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    if (zVar != null) {
                        zVar.a(this.b.c);
                    }
                }
            });
        }
        return view;
    }
}
