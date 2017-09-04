package com.tencent.imsdk;

import com.dynamicload.Lib.DLConstants;
import com.tencent.IMFunc.RequestListener;

final class ah implements RequestListener {
    private /* synthetic */ String a;
    private /* synthetic */ String b;
    private /* synthetic */ String c;

    ah(IMMsfCoreProxy iMMsfCoreProxy, String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final void onFail(String str) {
        QLog.e("imsdk.IMMsfCoreProxy", 1, "uploadLogFile->failed: " + this.a + DLConstants.DEPENDENCY_PACKAGE_DIV + str);
        IMMsfCoreProxy.get().logReport(this.b, this.c, 6010, str);
    }

    public final void onSuccess(byte[] bArr) {
        QLog.i("imsdk.IMMsfCoreProxy", 1, "uploadLogFile->success: " + this.a + "/" + this.b);
        IMMsfCoreProxy.get().logReport(this.b, this.c, 0, "");
    }
}
