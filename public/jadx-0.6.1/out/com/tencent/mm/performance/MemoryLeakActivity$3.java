package com.tencent.mm.performance;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class MemoryLeakActivity$3 implements OnDismissListener {
    final /* synthetic */ MemoryLeakActivity this$0;

    MemoryLeakActivity$3(MemoryLeakActivity memoryLeakActivity) {
        this.this$0 = memoryLeakActivity;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.this$0.finish();
    }
}
