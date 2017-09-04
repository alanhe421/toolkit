package com.sijla.h;

import java.io.File;
import java.io.FileFilter;

class c$1 implements FileFilter {
    final /* synthetic */ String a;
    final /* synthetic */ c b;

    c$1(c cVar, String str) {
        this.b = cVar;
        this.a = str;
    }

    public boolean accept(File file) {
        long currentTimeMillis = System.currentTimeMillis() - file.lastModified();
        String name = file.getName();
        if (!name.contains(this.a) || file == null || !name.startsWith("AS")) {
            return false;
        }
        if ((name.endsWith("_0") || name.endsWith("_1")) && currentTimeMillis < 1500) {
            return true;
        }
        return false;
    }
}
