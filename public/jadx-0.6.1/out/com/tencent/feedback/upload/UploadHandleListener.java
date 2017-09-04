package com.tencent.feedback.upload;

/* compiled from: RQDSRC */
public interface UploadHandleListener {
    void onUploadEnd(int i, int i2, long j, long j2, boolean z, String str);

    void onUploadStart(int i);
}
