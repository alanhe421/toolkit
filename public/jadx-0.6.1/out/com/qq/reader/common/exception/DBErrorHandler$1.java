package com.qq.reader.common.exception;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.utils.ao;
import java.io.File;
import java.util.Calendar;

class DBErrorHandler$1 extends ReaderShortTask {
    final /* synthetic */ a this$0;
    final /* synthetic */ int val$type;

    DBErrorHandler$1(a aVar, int i) {
        this.this$0 = aVar;
        this.val$type = i;
    }

    public void run() {
        super.run();
        if (Math.abs(Calendar.getInstance().get(12) - d.bh(this.this$0.c.getApplicationContext())) > 720) {
            String a = this.this$0.d(this.val$type);
            String b = this.this$0.c(this.val$type);
            if (a != null && b != null) {
                File file = new File(a);
                if (file.exists()) {
                    File file2 = new File(b);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    if (ao.a(file, file2)) {
                        d.bg(this.this$0.c.getApplicationContext());
                    }
                }
            }
        }
    }
}
