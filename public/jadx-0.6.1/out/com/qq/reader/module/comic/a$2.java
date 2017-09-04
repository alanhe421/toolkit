package com.qq.reader.module.comic;

import android.content.Context;
import com.qq.reader.view.af;
import com.qrcomic.c.g;

/* compiled from: ComicHelper */
class a$2 implements g {
    final /* synthetic */ a a;
    private af b;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void a(Context context, String str, int i) {
        a(context, str, i, false);
    }

    public void a(Context context, int i, int i2) {
        a(context, Integer.valueOf(i), i2, true);
    }

    private void a(Context context, Object obj, int i, boolean z) {
        if (this.b != null) {
            this.b.b();
        }
        if (z) {
            this.b = af.a(context, ((Integer) obj).intValue(), i);
        } else {
            this.b = af.a(context, (String) obj, i);
        }
        this.b.a();
    }
}
