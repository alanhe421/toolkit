package com.qq.reader.tinker;

import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import java.io.File;

class PatchUtil$2 extends ReaderIOTask {
    PatchUtil$2() {
    }

    public void run() {
        super.run();
        try {
            SharePatchFileUtil.deleteDir(new File(d.a));
        } catch (Throwable th) {
            c.e("Tinker.PatchUtil", th.getMessage());
        }
    }
}
