package com.qq.reader.view.web;

import android.os.Message;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.tencent.util.WeakReferenceHandler;

/* compiled from: PopNativeGameDialog */
class j$2 implements e<String, b> {
    final /* synthetic */ WeakReferenceHandler a;
    final /* synthetic */ j b;

    j$2(j jVar, WeakReferenceHandler weakReferenceHandler) {
        this.b = jVar;
        this.a = weakReferenceHandler;
    }

    public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
        return false;
    }

    public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
        j.a(this.b).setVisibility(0);
        Message obtainMessage = this.a.obtainMessage();
        obtainMessage.what = 65555;
        this.a.sendMessage(obtainMessage);
        return false;
    }
}
