package com.tencent.feedback.eup;

import android.os.Bundle;

/* compiled from: RQDSRC */
public interface ProcessMoniterHandler {
    void onOtherAppProcessCrash(String str, String str2, String str3, int i, long j, long j2, long j3, String str4, String str5, String str6, boolean z, long j4, Bundle bundle);

    void onOtherAppProcessLaunched(String str, String str2, boolean z, Bundle bundle);
}
