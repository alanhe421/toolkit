package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class NetworkInfo extends JceStruct {
    public int ip = 0;
    public byte network = (byte) 0;
    public byte op = (byte) 0;

    public NetworkInfo(int i, byte b, byte b2) {
        this.ip = i;
        this.network = b;
        this.op = b2;
    }

    public void writeTo(d dVar) {
        dVar.a(this.ip, 0);
        dVar.b(this.network, 1);
        dVar.b(this.op, 2);
    }

    public void readFrom(c cVar) {
        this.ip = cVar.a(this.ip, 0, true);
        this.network = cVar.a(this.network, 1, true);
        this.op = cVar.a(this.op, 2, false);
    }
}
