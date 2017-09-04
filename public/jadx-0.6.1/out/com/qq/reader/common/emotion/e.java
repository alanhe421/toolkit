package com.qq.reader.common.emotion;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/* compiled from: EmoticonPagerAdapter */
public class e extends PagerAdapter {
    private final String a = e.class.getSimpleName();
    private List<h> b;
    private boolean c = true;

    public void a(List<h> list) {
        this.b = list;
    }

    public Object a(ViewGroup viewGroup, int i) {
        View a;
        int i2 = 0;
        for (h hVar : this.b) {
            if (hVar != null && (hVar instanceof f)) {
                f fVar = (f) hVar;
                int a2 = fVar.a();
                i2 += a2;
                if (i + 1 <= i2) {
                    a = fVar.a(a2 - (i2 - i));
                    break;
                }
            }
            i2 = i2;
        }
        a = null;
        viewGroup.addView(a);
        return a;
    }

    public void a(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
        int i2 = 0;
        for (h hVar : this.b) {
            if (hVar != null && (hVar instanceof f)) {
                f fVar = (f) hVar;
                int a = fVar.a();
                i2 += a;
                if (i + 1 <= i2) {
                    fVar.c(a - (i2 - i));
                    return;
                }
            }
            i2 = i2;
        }
    }

    public int a() {
        if (this.b == null || this.b.size() == 0) {
            return 0;
        }
        int i = 0;
        for (h hVar : this.b) {
            int i2;
            if (hVar == null || !(hVar instanceof f)) {
                i2 = i;
            } else {
                i2 = i + hVar.a();
            }
            i = i2;
        }
        return i;
    }

    public boolean a(View view, Object obj) {
        return view == obj;
    }
}
