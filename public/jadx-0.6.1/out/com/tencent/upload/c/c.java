package com.tencent.upload.c;

import com.qq.jce.wup.d;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.common.a.a;
import com.tencent.upload.log.b;

public final class c {
    public int a;
    public String b;
    private d c;
    private JceStruct d;
    private String e;
    private int f;
    private int g;

    public final JceStruct a() {
        return this.d;
    }

    public final void a(int i) {
        this.f = i;
    }

    public final boolean a(byte[] bArr) {
        try {
            this.c = new d();
            this.c.a("UTF-8");
            if (bArr != null) {
                this.c.a(bArr);
                this.g = this.c.c();
                this.e = b.c(Integer.valueOf(this.c.b()).intValue());
                this.d = (JceStruct) this.c.b("RSP");
            }
        } catch (OutOfMemoryError e) {
            a.d("NetworkResponse", "decode response oom!  gc, then retry.");
            System.gc();
            this.c.a(bArr);
        } catch (Throwable th) {
            b.c("NetworkResponse", "decode response exception!", th);
            return false;
        }
        return true;
    }

    public final String b() {
        return this.e;
    }

    public final int c() {
        return this.f;
    }

    public final int d() {
        return this.g;
    }
}
