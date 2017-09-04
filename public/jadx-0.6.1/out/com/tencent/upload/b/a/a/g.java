package com.tencent.upload.b.a.a;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class g {
    private File a;
    private String b;

    public g(File file) {
        this.a = null;
        this.b = null;
        this.a = file;
        if (file == null) {
            return;
        }
        if (!file.isFile()) {
            throw new FileNotFoundException("File is not a normal file.");
        } else if (file.canRead()) {
            this.b = file.getName();
        } else {
            throw new FileNotFoundException("File is not readable.");
        }
    }

    public g(String str, File file) {
        this(file);
        if (str != null) {
            this.b = str;
        }
    }

    public long a() {
        return this.a != null ? this.a.length() : 0;
    }

    public String b() {
        return this.b == null ? "noname" : this.b;
    }

    public InputStream c() {
        return this.a != null ? new FileInputStream(this.a) : new ByteArrayInputStream(new byte[0]);
    }
}
