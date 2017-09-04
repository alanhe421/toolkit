package com.qq.reader.common.utils;

import com.qq.reader.common.c.a;
import java.io.File;
import java.io.FilenameFilter;

/* compiled from: BookImporter */
public class g {
    public static File[] a() {
        File file = new File(a.v);
        if (file.exists()) {
            return file.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    if (str != null && (str.toLowerCase().endsWith(".qb") || str.toLowerCase().endsWith(".txt"))) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(file);
                        stringBuilder.append("/");
                        stringBuilder.append(str);
                        if (!com.qq.reader.readengine.model.a.e(stringBuilder.toString())) {
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        return null;
    }
}
