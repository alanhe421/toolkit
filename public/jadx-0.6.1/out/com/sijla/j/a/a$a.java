package com.sijla.j.a;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

class a$a implements FileFilter {
    a$a() {
    }

    public boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }
}
