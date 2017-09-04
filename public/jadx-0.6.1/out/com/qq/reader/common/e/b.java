package com.qq.reader.common.e;

import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* compiled from: OSProperties */
public class b {
    private final Properties a = new Properties();

    private b() throws IOException {
        this.a.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
    }

    public String a(String str, String str2) {
        return this.a.getProperty(str, str2);
    }

    public static b a() throws IOException {
        return new b();
    }
}
