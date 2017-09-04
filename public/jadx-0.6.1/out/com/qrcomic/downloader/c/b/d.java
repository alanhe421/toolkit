package com.qrcomic.downloader.c.b;

import com.qrcomic.downloader.c.a.a;
import java.io.IOException;
import okhttp3.s;
import okhttp3.y;

/* compiled from: ProgressInterceptor */
public class d implements s {
    a a;

    public d(a aVar) {
        this.a = aVar;
    }

    public y a(s.a aVar) throws IOException {
        y a = aVar.a(aVar.a());
        return a.h().a(new e(a.g(), this.a)).a();
    }
}
