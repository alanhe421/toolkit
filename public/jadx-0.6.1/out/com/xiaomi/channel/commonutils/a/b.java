package com.xiaomi.channel.commonutils.a;

import java.io.File;
import java.io.FileFilter;

final class b implements FileFilter {
    b() {
    }

    public boolean accept(File file) {
        return file.isDirectory();
    }
}
