package com.qq.reader.tinker;

import android.content.Context;
import android.content.Intent;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import java.io.File;

/* compiled from: SamplePatchReporter */
public class g extends DefaultPatchReporter {
    public g(Context context) {
        super(context);
    }

    public void onPatchServiceStart(Intent intent) {
        super.onPatchServiceStart(intent);
        h.b();
        l.a(this.context).a(intent);
    }

    public void onPatchDexOptFail(File file, File file2, String str, String str2, Throwable th) {
        super.onPatchDexOptFail(file, file2, str, str2, th);
        h.a(th);
    }

    public void onPatchException(File file, Throwable th) {
        super.onPatchException(file, th);
        h.b(th);
    }

    public void onPatchInfoCorrupted(File file, String str, String str2) {
        super.onPatchInfoCorrupted(file, str, str2);
        h.c();
    }

    public void onPatchPackageCheckFail(File file, int i) {
        super.onPatchPackageCheckFail(file, i);
        h.f(i);
    }

    public void onPatchResult(File file, boolean z, long j) {
        super.onPatchResult(file, z, j);
        h.a(j, z);
        l.a(this.context).b();
    }

    public void onPatchTypeExtractFail(File file, File file2, String str, int i) {
        super.onPatchTypeExtractFail(file, file2, str, i);
        h.e(i);
    }

    public void onPatchVersionCheckFail(File file, SharePatchInfo sharePatchInfo, String str) {
        super.onPatchVersionCheckFail(file, sharePatchInfo, str);
        h.d();
    }
}
