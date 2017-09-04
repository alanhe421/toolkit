package com.qq.reader.module.findhome.b;

import android.content.Context;
import android.support.v7.widget.RecyclerView.a;
import android.support.v7.widget.RecyclerView.s;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FindHomeRecyclerAdapter */
public class b extends a<com.qq.reader.module.findhome.base.b> {
    private d a = new d();
    private List<com.qq.reader.module.findhome.base.a> b = new ArrayList();
    private Context c;
    private LayoutInflater d;
    private final int e = 10;

    public /* synthetic */ s a(ViewGroup viewGroup, int i) {
        return c(viewGroup, i);
    }

    public b(Context context) {
        this.c = context;
        this.d = LayoutInflater.from(this.c);
    }

    public void a(List<com.qq.reader.module.findhome.base.a> list) {
        this.b.clear();
        if (list != null && list.size() > 0) {
            this.b.addAll(list);
        }
    }

    public com.qq.reader.module.findhome.base.b c(ViewGroup viewGroup, int i) {
        return this.a.a(i, this.d, viewGroup);
    }

    public void a(com.qq.reader.module.findhome.base.b bVar, int i) {
        bVar.a((com.qq.reader.module.findhome.base.a) this.b.get(i));
    }

    public int a() {
        return this.b.size();
    }

    public int a(int i) {
        return ((com.qq.reader.module.findhome.base.a) this.b.get(i)).a(this.a);
    }
}
