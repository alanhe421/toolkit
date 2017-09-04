package com.tencent.mm.performance;

import android.os.Bundle;
import android.os.Message;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.AlertDialog.a;
import com.tencent.feedback.proguard.R;
import com.tencent.mm.performance.util.Util;
import java.lang.ref.WeakReference;

public class MemoryLeakActivity extends ReaderBaseActivity {
    private static final String TAG = "MicroMsg.MemoryLeakActivity";
    private AlertDialog mAlertDialog;
    private String mKey;

    protected boolean handleMessageImp(Message message) {
        if (((WeakReference) WxPerformanceHandle.sWeakObjects.get(this.mKey)).get() == null) {
            finish();
        } else {
            this.mAlertDialog.show();
        }
        return true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.background_transparent);
        a aVar = new a(this);
        aVar.a((CharSequence) "memory leak");
        this.mKey = getIntent().getStringExtra("key");
        String stringExtra = getIntent().getStringExtra(WxPerformanceHandle.MESSAGE_TAG);
        String stringExtra2 = getIntent().getStringExtra(WxPerformanceHandle.MESSAGE_CLASS);
        if (stringExtra2.contains(" ")) {
            stringExtra2 = stringExtra2.substring(stringExtra2.indexOf(" "));
        }
        String replace = stringExtra2.replace(".", "_");
        aVar.b(stringExtra + stringExtra2 + "\n\n" + "path:" + Util.DUMP_FILE_PATH + replace + Util.HPROF_FORMAT);
        aVar.a(true);
        aVar.a((CharSequence) "dumphprof", new 1(this, replace));
        aVar.b((CharSequence) "cancel", new 2(this));
        this.mAlertDialog = aVar.a();
        this.mAlertDialog.setOnDismissListener(new 3(this));
        System.gc();
        System.gc();
        this.mHandler.sendEmptyMessageDelayed(0, 200);
    }

    protected void onDestroy() {
        super.onDestroy();
        WxPerformanceHandle.sWeakObjects.remove(this.mKey);
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.mAlertDialog != null && this.mAlertDialog.isShowing()) {
            this.mAlertDialog.dismiss();
            this.mAlertDialog = null;
        }
    }
}
