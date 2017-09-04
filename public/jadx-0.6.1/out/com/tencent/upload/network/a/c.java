package com.tencent.upload.network.a;

import com.tencent.upload.b.a.a.b;
import com.tencent.upload.common.a.a;
import java.net.InetAddress;
import java.net.UnknownHostException;

final class c implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ byte[] b;
    private /* synthetic */ b c;

    c(String str, byte[] bArr, b bVar) {
        this.a = str;
        this.b = bArr;
        this.c = bVar;
    }

    public final void run() {
        String str = null;
        try {
            str = InetAddress.getByName(this.a).getHostAddress();
        } catch (UnknownHostException e) {
            a.c("DomainParser", "parse: UnknownHostException:" + e);
        } catch (Exception e2) {
            a.c("DomainParser", "parse: Exception:" + e2);
        } catch (Error e3) {
            a.c("DomainParser", "parse: Error:" + e3);
        }
        synchronized (this.b) {
            this.c.a = str;
            this.b.notify();
        }
    }
}
