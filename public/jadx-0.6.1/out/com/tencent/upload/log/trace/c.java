package com.tencent.upload.log.trace;

import java.io.File;
import java.io.FileFilter;
import java.text.ParseException;

final class c implements FileFilter {
    private /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final boolean accept(File file) {
        if (!file.isDirectory()) {
            return false;
        }
        try {
            this.a.b().parse(file.getName());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
