package com.qq.reader.module.feed.mypreference;

import android.text.TextUtils;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.module.feed.mypreference.a.a;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.ArrayList;
import java.util.List;

class MyFeedPreferenceActivity$1 extends ReaderIOTask {
    final /* synthetic */ MyFeedPreferenceActivity this$0;

    MyFeedPreferenceActivity$1(MyFeedPreferenceActivity myFeedPreferenceActivity) {
        this.this$0 = myFeedPreferenceActivity;
    }

    public void run() {
        Object obj = 1;
        super.run();
        Object b = a.a().b();
        if (!TextUtils.isEmpty(b)) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            List arrayList3 = new ArrayList();
            b.a(arrayList, arrayList2, arrayList3, b);
            if (arrayList.size() > 0 && arrayList2.size() > 0 && arrayList3.size() > 0) {
                obj = null;
            }
        }
        if (obj != null) {
            a.a().a(new a(this) {
                final /* synthetic */ MyFeedPreferenceActivity$1 a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (MyFeedPreferenceActivity.a(this.a.this$0) != null && !this.a.this$0.isFinishing()) {
                        if (z) {
                            MyFeedPreferenceActivity.b(this.a.this$0).sendEmptyMessage(118);
                        } else {
                            MyFeedPreferenceActivity.c(this.a.this$0).sendEmptyMessage(Opcodes.INVOKE_STATIC_RANGE);
                        }
                    }
                }
            });
        } else {
            MyFeedPreferenceActivity.d(this.this$0).sendEmptyMessage(118);
        }
    }
}
