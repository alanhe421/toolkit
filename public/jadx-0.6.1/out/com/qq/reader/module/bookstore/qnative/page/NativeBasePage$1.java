package com.qq.reader.module.bookstore.qnative.page;

import com.qq.reader.common.readertask.ordinal.ReaderIOTask;
import com.qq.reader.module.bookstore.qnative.d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

class NativeBasePage$1 extends ReaderIOTask {
    final /* synthetic */ b this$0;

    NativeBasePage$1(b bVar) {
        this.this$0 = bVar;
    }

    public void run() {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        OutputStream outputStream;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            String g = this.this$0.g();
            if (d.b().a(g) != null) {
                try {
                    if (this.this$0.q != null) {
                        this.this$0.p = this.this$0.q.toString();
                        if (g != null) {
                            try {
                                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                try {
                                    this.this$0.serialize(byteArrayOutputStream2);
                                    byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
                                } catch (Throwable th2) {
                                    th = th2;
                                    byteArrayInputStream = null;
                                    outputStream = byteArrayOutputStream2;
                                    if (byteArrayInputStream != null) {
                                        byteArrayInputStream.close();
                                    }
                                    if (byteArrayOutputStream != null) {
                                        byteArrayOutputStream.close();
                                    }
                                    throw th;
                                }
                                try {
                                    d.b().a(g, byteArrayInputStream, null);
                                    if (byteArrayInputStream != null) {
                                        byteArrayInputStream.close();
                                    }
                                    if (byteArrayOutputStream2 != null) {
                                        byteArrayOutputStream2.close();
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    outputStream = byteArrayOutputStream2;
                                    if (byteArrayInputStream != null) {
                                        byteArrayInputStream.close();
                                    }
                                    if (byteArrayOutputStream != null) {
                                        byteArrayOutputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                byteArrayInputStream = null;
                                if (byteArrayInputStream != null) {
                                    byteArrayInputStream.close();
                                }
                                if (byteArrayOutputStream != null) {
                                    byteArrayOutputStream.close();
                                }
                                throw th;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
