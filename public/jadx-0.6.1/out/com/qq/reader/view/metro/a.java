package com.qq.reader.view.metro;

import android.app.Activity;
import java.util.ArrayList;

/* compiled from: MetorNormalDialog */
public class a extends b {
    protected c a;

    public a(Activity activity, int i, String str, String str2, ArrayList<MetroItem> arrayList) {
        super(activity, i, str, str2, arrayList);
    }

    protected void a(Activity activity, int i, String str) {
        this.a = new c(activity.getApplicationContext(), i);
        this.a.a(this.e, str);
        this.c.setAdapter(this.a);
        this.a.a(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(MetroItem metroItem) {
                this.a.d.a(metroItem);
                this.a.cancel();
            }
        });
    }
}
