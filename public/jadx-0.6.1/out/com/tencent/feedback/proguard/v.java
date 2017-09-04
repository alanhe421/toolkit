package com.tencent.feedback.proguard;

import android.content.Context;
import com.tencent.feedback.common.e;
import com.tencent.feedback.upload.AbstractUploadDatas;

/* compiled from: RQDSRC */
public final class v extends AbstractUploadDatas {
    private S d = null;

    public v(Context context, int i, int i2, S s) {
        super(context, 1111, 540);
        this.d = s;
    }

    public final void done(boolean z) {
    }

    public final N a() {
        try {
            return AbstractUploadDatas.a(this.c, this.a, this.d == null ? null : this.d.a());
        } catch (Throwable e) {
            if (e.a(e)) {
                return null;
            }
            e.printStackTrace();
            return null;
        }
    }
}
