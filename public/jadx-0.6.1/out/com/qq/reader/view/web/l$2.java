package com.qq.reader.view.web;

import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import com.bumptech.glide.load.resource.a.b;
import com.bumptech.glide.request.b.j;
import com.bumptech.glide.request.e;
import com.qq.reader.common.offline.c;
import com.qq.reader.module.rookie.presenter.a;
import com.tencent.util.WeakReferenceHandler;

/* compiled from: PopRookieDialog */
class l$2 implements e<String, b> {
    final /* synthetic */ com.qq.reader.module.rookie.a.b a;
    final /* synthetic */ WeakReferenceHandler b;
    final /* synthetic */ l c;

    l$2(l lVar, com.qq.reader.module.rookie.a.b bVar, WeakReferenceHandler weakReferenceHandler) {
        this.c = lVar;
        this.a = bVar;
        this.b = weakReferenceHandler;
    }

    public boolean a(Exception exception, String str, j<b> jVar, boolean z) {
        a.a().b(false);
        if (this.c.a != null) {
            this.c.a.onCancel(l.a(this.c));
        }
        c.a(this.c.e).a("WEBDIALOG");
        return false;
    }

    public boolean a(b bVar, String str, j<b> jVar, boolean z, boolean z2) {
        this.c.c.setVisibility(0);
        this.c.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ l$2 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                l.a(this.a.c, this.a.a);
                a.a().a(this.a.c.e, this.a.a);
                this.a.c.dismiss();
            }
        });
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.obj = this.a;
        obtainMessage.what = 65552;
        this.b.sendMessage(obtainMessage);
        a.a().b(false);
        return false;
    }
}
