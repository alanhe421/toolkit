package com.nhaarman.listviewanimations.b;

import android.view.View;

/* compiled from: AdapterViewUtil */
public class b {
    public static int a(e eVar, View view) {
        return eVar.a(view) - eVar.f();
    }

    public static View a(e eVar, int i) {
        int e = eVar.e();
        View view = null;
        int i2 = 0;
        while (i2 < e && view == null) {
            View a = eVar.a(i2);
            if (a == null || a(eVar, a) != i) {
                a = view;
            }
            i2++;
            view = a;
        }
        return view;
    }
}
