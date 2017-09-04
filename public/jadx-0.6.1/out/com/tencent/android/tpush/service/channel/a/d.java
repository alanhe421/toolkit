package com.tencent.android.tpush.service.channel.a;

import com.tencent.android.tpush.service.channel.b.b;
import java.nio.channels.SocketChannel;

/* compiled from: ProGuard */
public class d extends c {
    public d(SocketChannel socketChannel, b bVar, String str, int i) {
        super(socketChannel, bVar, str, i, "http://" + str + (i == 80 ? "" : ":" + i) + "/");
    }

    protected boolean a() {
        return super.a();
    }

    protected boolean b() {
        if (this.f == null && super.b()) {
            ((b) this.f).a("X-Online-Host", this.m);
        }
        return this.f != null;
    }
}
