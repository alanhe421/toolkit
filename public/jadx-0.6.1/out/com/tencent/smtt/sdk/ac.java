package com.tencent.smtt.sdk;

import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FileFilter;

class ac implements FileFilter {
    final /* synthetic */ ab a;

    ac(ab abVar) {
        this.a = abVar;
    }

    public boolean accept(File file) {
        return file.getName().endsWith(ShareConstants.DEX_SUFFIX);
    }
}
