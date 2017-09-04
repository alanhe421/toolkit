package com.tencent.mm.performance;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class MemoryLeakActivity$2 implements OnClickListener {
    final /* synthetic */ MemoryLeakActivity this$0;

    MemoryLeakActivity$2(MemoryLeakActivity memoryLeakActivity) {
        this.this$0 = memoryLeakActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (MemoryLeakActivity.access$000(this.this$0) != null && MemoryLeakActivity.access$000(this.this$0).isShowing()) {
            MemoryLeakActivity.access$000(this.this$0).dismiss();
        }
        this.this$0.finish();
    }
}
