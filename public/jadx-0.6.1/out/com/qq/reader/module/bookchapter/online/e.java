package com.qq.reader.module.bookchapter.online;

import android.content.Context;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.d.a;
import org.apache.commons.compress.archivers.d.b;
import org.apache.http.message.BasicHeader;

/* compiled from: OnlineBookDirDownloader */
public class e {
    String a;
    private OnlineTag b = null;
    private Context c;

    public e(Context context, OnlineTag onlineTag) {
        this.c = context;
        this.b = onlineTag;
    }

    public void a() throws IOException, ArchiveException {
        BasicHeader[] basicHeaderArr;
        Throwable th;
        String str = d.c() + "";
        if (str.equals("0")) {
            str = "";
        }
        this.a = c.c().a(this.c);
        switch (c.c().d()) {
            case 2:
            case 50:
                this.a = c.c().a(this.c);
                basicHeaderArr = new BasicHeader[]{new BasicHeader("text_type", String.valueOf(this.b.F())), new BasicHeader("sid", str), new BasicHeader("qimei", d.h(this.c)), new BasicHeader("safekey", d.y(this.c)), new BasicHeader("channel", ao.h(this.c)), new BasicHeader("usid", this.a), new BasicHeader("timi", d.z(this.c)), new BasicHeader("nosid", "1"), new BasicHeader("uid", c.c().c()), new BasicHeader("qqnum", c.c().c()), new BasicHeader("c_platform", "android"), new BasicHeader("c_version", "qqreader_6.5.3.0888_android"), new BasicHeader("loginType", String.valueOf(r2)), new BasicHeader("ua", d.a())};
                break;
            case 10:
                basicHeaderArr = new BasicHeader[]{new BasicHeader("text_type", String.valueOf(this.b.F())), new BasicHeader("sid", str), new BasicHeader("qimei", d.h(this.c)), new BasicHeader("safekey", d.y(this.c)), new BasicHeader("channel", ao.h(this.c)), new BasicHeader("usid", c.c().a(this.c)), new BasicHeader("timi", d.z(this.c)), new BasicHeader("nosid", "1"), new BasicHeader("uid", c.c().c()), new BasicHeader("c_platform", "android"), new BasicHeader("c_version", "qqreader_6.5.3.0888_android"), new BasicHeader("loginType", String.valueOf(c.c().d())), new BasicHeader("ua", d.a())};
                break;
            default:
                this.a = c.c().a(this.c);
                basicHeaderArr = new BasicHeader[]{new BasicHeader("text_type", String.valueOf(this.b.F())), new BasicHeader("sid", str), new BasicHeader("qimei", d.h(this.c)), new BasicHeader("safekey", d.y(this.c)), new BasicHeader("channel", ao.h(this.c)), new BasicHeader("skey", this.a), new BasicHeader("timi", d.z(this.c)), new BasicHeader("qqnum", c.c().c()), new BasicHeader("nosid", "1"), new BasicHeader("c_platform", "android"), new BasicHeader("c_version", "qqreader_6.5.3.0888_android"), new BasicHeader("loginType", String.valueOf(r2)), new BasicHeader("ua", d.a())};
                break;
        }
        InputStream inputStream = null;
        b bVar = null;
        try {
            InputStream inputStream2 = com.qq.reader.common.conn.http.b.a(this.b.j(com.qq.reader.common.conn.a.c.a().b()), basicHeaderArr, this.c).getInputStream();
            try {
                File file = new File(this.b.c());
                ab.a(file);
                b bVar2 = (b) new org.apache.commons.compress.archivers.c().a("tar", inputStream2);
                try {
                    String k = this.b.k();
                    File file2 = null;
                    while (true) {
                        a aVar = (a) bVar2.c();
                        if (aVar != null) {
                            String a = aVar.a();
                            if (a != null && a.startsWith(k)) {
                                file2 = new File(file, a);
                                if (!aVar.g()) {
                                    if (file2 != null) {
                                        if (file2.exists()) {
                                            file2.delete();
                                        }
                                    }
                                    OutputStream fileOutputStream = new FileOutputStream(file2);
                                    org.apache.commons.compress.a.b.a(bVar2, fileOutputStream);
                                    fileOutputStream.close();
                                } else if (!ab.a(file2.getParentFile())) {
                                    throw new IllegalStateException(String.format("Couldn't create directory %s.", new Object[]{file2.getAbsolutePath()}));
                                }
                            }
                            file2 = file2;
                        } else {
                            File file3 = new File(this.b.d());
                            if (file3.exists()) {
                                file3.delete();
                            }
                            file2.renameTo(file3);
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (IOException e) {
                                }
                            }
                            if (bVar2 != null) {
                                try {
                                    bVar2.close();
                                    return;
                                } catch (IOException e2) {
                                    return;
                                }
                            }
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    inputStream = inputStream2;
                    b bVar3 = bVar2;
                    th = th2;
                    bVar = bVar3;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = inputStream2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                    }
                }
                if (bVar != null) {
                    try {
                        bVar.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            if (inputStream != null) {
                inputStream.close();
            }
            if (bVar != null) {
                bVar.close();
            }
            throw th;
        }
    }
}
