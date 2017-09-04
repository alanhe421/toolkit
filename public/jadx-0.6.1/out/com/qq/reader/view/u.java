package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.widget.GridView;

/* compiled from: QRBaseTabMenu */
public class u {
    private GridView a;
    private v b;
    private Context c;

    public u(Activity activity, GridView gridView, v vVar) {
        this.c = activity;
        this.a = gridView;
        this.b = vVar;
        this.a.setAdapter(this.b);
    }

    public void a(a aVar) {
        if (this.b != null) {
            this.b.a(aVar);
        }
    }

    public void a(int i) {
        this.a.setNumColumns(i);
    }

    public void a(int i, String str) {
        if (this.b != null) {
            this.b.a(i, str, null, false);
        }
    }

    public boolean a(int[] iArr, String[] strArr) {
        int i = 0;
        for (String a : strArr) {
            this.b.a(iArr[i], a, null, false);
            i++;
        }
        this.a.setNumColumns(iArr.length);
        return true;
    }

    public void a() {
        if (this.b != null) {
            this.b.a();
        }
    }

    public void a(boolean z) {
        this.a.setVisibility(z ? 0 : 8);
    }

    public void b(int i) {
        if (this.b != null) {
            this.b.a(i);
        }
    }

    public int b() {
        if (this.b != null) {
            return this.b.b();
        }
        return 0;
    }

    public void c() {
        if (this.b != null) {
            this.b.notifyDataSetInvalidated();
        }
    }
}
