package com.tencent.beacon.event;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* compiled from: ProGuard */
class n$a implements FileFilter {
    n$a() {
    }

    public final boolean accept(File file) {
        if (Pattern.matches("cpu[0-9]", file.getName())) {
            return true;
        }
        return false;
    }
}
