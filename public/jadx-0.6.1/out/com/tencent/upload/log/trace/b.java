package com.tencent.upload.log.trace;

import java.io.File;
import java.io.FileFilter;

final class b implements FileFilter {
    private /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final boolean accept(File file) {
        boolean z = true;
        String name = file.getName();
        boolean z2 = name.endsWith(TracerConfig.DEF_TRACE_FILEEXT) && name.startsWith(TracerConfig.getLogFilePre(this.a.a));
        if (!z2) {
            return false;
        }
        if (this.a.d(file) == -1) {
            z = false;
        }
        return z;
    }
}
