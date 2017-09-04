package com.qq.reader.module.redpacket.square.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.qq.reader.module.redpacket.square.card.SquareCommonCard;
import java.util.ArrayList;

/* compiled from: RedPacketSquareAdapter */
public class a extends BaseAdapter {
    private Context a;
    private com.qq.reader.module.bookstore.qnative.c.a b;
    private ArrayList<SquareCommonCard> c;

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(Context context, com.qq.reader.module.bookstore.qnative.c.a aVar) {
        this.a = context;
        this.b = aVar;
    }

    public void a(ArrayList<SquareCommonCard> arrayList) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.clear();
        this.c.addAll(arrayList);
        notifyDataSetChanged();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        return this.c.size();
    }

    public com.qq.reader.module.bookstore.qnative.card.a a(int i) {
        if (this.c == null || this.c.size() == 0) {
            return null;
        }
        return (com.qq.reader.module.bookstore.qnative.card.a) this.c.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        com.qq.reader.module.bookstore.qnative.card.a a = a(i);
        a.setPosition(i);
        if (view == null) {
            view = a.inflateView(this.a);
        }
        try {
            a.attachView(view);
        } catch (Exception e) {
        }
        return view;
    }
}
