package com.qq.reader.module.qmessage.loader;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.qmessage.data.b;
import com.qq.reader.module.qmessage.data.c;
import com.qq.reader.module.qmessage.data.d;
import java.lang.ref.WeakReference;

class MessageDataLoader$4 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ c val$dataPackage;
    final /* synthetic */ WeakReference val$handler;
    final /* synthetic */ long val$minTimeOfpage;
    final /* synthetic */ d val$page;

    MessageDataLoader$4(a aVar, long j, c cVar, WeakReference weakReference, d dVar) {
        this.this$0 = aVar;
        this.val$minTimeOfpage = j;
        this.val$dataPackage = cVar;
        this.val$handler = weakReference;
        this.val$page = dVar;
    }

    public void run() {
        if (b.b().b(this.val$minTimeOfpage)) {
            b.b().a(this.val$dataPackage);
        } else {
            b.b().a(this.val$dataPackage.d());
            b.b().a(this.val$dataPackage);
        }
        this.this$0.f(this.val$handler, this.val$dataPackage);
        if (this.val$dataPackage.d() == 2) {
            this.this$0.a(this.val$page.e().size());
        }
    }
}
