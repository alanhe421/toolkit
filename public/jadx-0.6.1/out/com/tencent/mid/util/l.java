package com.tencent.mid.util;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

class l implements FileFilter {
    l() {
    }

    public boolean accept(File file) {
        return Pattern.matches("cpu[0-9]", file.getName());
    }
}
