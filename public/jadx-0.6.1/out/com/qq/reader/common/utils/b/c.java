package com.qq.reader.common.utils.b;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

/* compiled from: CSVWriter */
public class c implements Closeable {
    private Writer a;
    private PrintWriter b;
    private char c;
    private char d;
    private char e;
    private String f;

    public c(Writer writer, char c, char c2, char c3) {
        this(writer, c, c2, c3, "\n");
    }

    public c(Writer writer, char c, char c2, char c3, String str) {
        this.a = writer;
        this.b = new PrintWriter(writer);
        this.c = c;
        this.d = c2;
        this.e = c3;
        this.f = str;
    }

    public void a(List<String[]> list) {
        for (String[] a : list) {
            a(a);
        }
    }

    public void a(String[] strArr) {
        if (strArr != null) {
            StringBuilder stringBuilder = new StringBuilder(128);
            for (int i = 0; i < strArr.length; i++) {
                if (i != 0) {
                    stringBuilder.append(this.c);
                }
                String str = strArr[i];
                if (str != null) {
                    if (this.d != '\u0000') {
                        stringBuilder.append(this.d);
                    }
                    if (b(str)) {
                        str = a(str);
                    }
                    stringBuilder.append(str);
                    if (this.d != '\u0000') {
                        stringBuilder.append(this.d);
                    }
                }
            }
            stringBuilder.append(this.f);
            this.b.write(stringBuilder.toString());
        }
    }

    private boolean b(String str) {
        return (str.indexOf(this.d) == -1 && str.indexOf(this.e) == -1) ? false : true;
    }

    protected StringBuilder a(String str) {
        StringBuilder stringBuilder = new StringBuilder(128);
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (this.e != '\u0000' && charAt == this.d) {
                stringBuilder.append(this.e).append(charAt);
            } else if (this.e == '\u0000' || charAt != this.e) {
                stringBuilder.append(charAt);
            } else {
                stringBuilder.append(this.e).append(charAt);
            }
        }
        return stringBuilder;
    }

    public void a() throws IOException {
        this.b.flush();
    }

    public void close() throws IOException {
        a();
        this.b.close();
        this.a.close();
    }
}
