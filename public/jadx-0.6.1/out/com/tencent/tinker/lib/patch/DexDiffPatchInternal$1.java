package com.tencent.tinker.lib.patch;

import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerParallelDexOptimizer.ResultCallback;
import java.io.File;

class DexDiffPatchInternal$1 implements ResultCallback {
    long startTime;

    DexDiffPatchInternal$1() {
    }

    public void onStart(File file, File file2) {
        this.startTime = System.currentTimeMillis();
        TinkerLog.i("Tinker.DexDiffPatchInternal", "start to parallel optimize dex %s, size: %d", new Object[]{file.getPath(), Long.valueOf(file.length())});
    }

    public void onSuccess(File file, File file2, File file3) {
        TinkerLog.i("Tinker.DexDiffPatchInternal", "success to parallel optimize dex %s, opt file size: %d, use time %d", new Object[]{file.getPath(), Long.valueOf(file3.length()), Long.valueOf(System.currentTimeMillis() - this.startTime)});
    }

    public void onFailed(File file, File file2, Throwable th) {
        TinkerLog.i("Tinker.DexDiffPatchInternal", "fail to parallel optimize dex %s use time %d", new Object[]{file.getPath(), Long.valueOf(System.currentTimeMillis() - this.startTime)});
        DexDiffPatchInternal.access$000().add(file);
    }
}
