package com.tencent.mm.performance;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.tencent.mm.performance.util.Util;

class MemoryLeakActivity$1 implements OnClickListener {
    final /* synthetic */ MemoryLeakActivity this$0;
    final /* synthetic */ String val$messageName;

    MemoryLeakActivity$1(MemoryLeakActivity memoryLeakActivity, String str) {
        this.this$0 = memoryLeakActivity;
        this.val$messageName = str;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Util.dumpHprofFileForName(this.val$messageName);
        if (MemoryLeakActivity.access$000(this.this$0) != null && MemoryLeakActivity.access$000(this.this$0).isShowing()) {
            MemoryLeakActivity.access$000(this.this$0).dismiss();
        }
        this.this$0.finish();
    }
}
