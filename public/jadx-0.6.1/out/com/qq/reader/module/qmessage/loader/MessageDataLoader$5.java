package com.qq.reader.module.qmessage.loader;

import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.qmessage.data.b;
import com.qq.reader.module.qmessage.data.c;
import java.lang.ref.WeakReference;

class MessageDataLoader$5 extends ReaderDBTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ c val$dataPackage;
    final /* synthetic */ WeakReference val$handler;

    MessageDataLoader$5(a aVar, c cVar, WeakReference weakReference) {
        this.this$0 = aVar;
        this.val$dataPackage = cVar;
        this.val$handler = weakReference;
    }

    public void run() {
        b.b().a(this.val$dataPackage);
        this.this$0.f(this.val$handler, this.val$dataPackage);
    }
}
