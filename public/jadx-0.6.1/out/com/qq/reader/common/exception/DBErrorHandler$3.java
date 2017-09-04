package com.qq.reader.common.exception;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

class DBErrorHandler$3 extends ReaderShortTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ int val$type;

    DBErrorHandler$3(a aVar, int i) {
        this.this$0 = aVar;
        this.val$type = i;
    }

    public void run() {
        super.run();
        String a = this.this$0.d(this.val$type);
        String b = this.this$0.c(this.val$type);
        if (a != null && b != null) {
            File file = new File(b);
            if (file.exists()) {
                File file2 = new File(a);
                if (file2.exists()) {
                    file2.delete();
                }
                Object[] b2 = ao.b(file, file2);
                Map hashMap = new HashMap();
                if (((Boolean) b2[0]).booleanValue()) {
                    hashMap.put(s.ORIGIN, "0");
                    i.a("event_A185", hashMap, ReaderApplication.getApplicationImp());
                } else {
                    hashMap.put(s.ORIGIN, "1");
                    hashMap.put(SocialConstants.PARAM_SEND_MSG, String.valueOf(b2[1]));
                    i.a("event_A185", hashMap, ReaderApplication.getApplicationImp());
                }
                com.qq.reader.common.db.handle.i.b();
            }
        }
    }
}
