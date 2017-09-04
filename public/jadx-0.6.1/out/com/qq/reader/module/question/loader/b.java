package com.qq.reader.module.question.loader;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a.a.b.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* compiled from: RecordDataCache */
public class b {
    private com.qq.reader.common.imageloader.a.a.b a;

    public b() {
        a();
    }

    private void a() {
        try {
            this.a = a.a(ReaderApplication.getApplicationImp(), a.a(), 52428800, 0, new File(com.qq.reader.common.c.a.am).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(String str, FileInputStream fileInputStream, com.qq.reader.common.imageloader.d.a.a aVar) {
        try {
            this.a.a(str, fileInputStream, aVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected File a(String str) {
        return this.a.a(str);
    }

    protected boolean b(String str) {
        return this.a.b(str);
    }

    protected String c(String str) {
        if (str == null || str.length() == 0) {
            str = String.valueOf(System.currentTimeMillis());
        }
        return com.qq.reader.common.c.a.al + str;
    }
}
