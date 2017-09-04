package com.tencent.android.tpush.service.channel;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.service.channel.b.h;
import com.tencent.android.tpush.service.channel.c.d;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.Random;

/* compiled from: ProGuard */
public class o {
    private static int e = new Random().nextInt();
    public long a = Long.MAX_VALUE;
    public long b = Long.MAX_VALUE;
    public JceStruct c = null;
    public p d;
    private int f = 0;
    private short g;

    public o(JceStruct jceStruct, p pVar) {
        this.g = d.a(jceStruct.getClass());
        this.c = jceStruct;
        this.d = pVar;
    }

    public o(short s, JceStruct jceStruct, p pVar) {
        this.g = s;
        this.c = jceStruct;
        this.d = pVar;
    }

    public void a(h hVar) {
        hVar.a(this.g);
        switch (this.g & Opcodes.NEG_FLOAT) {
            case 7:
                hVar.b((short) 20);
                return;
            default:
                hVar.b((short) 1);
                com.qq.taf.jce.d dVar = new com.qq.taf.jce.d();
                dVar.a("UTF-8");
                this.c.writeTo(dVar);
                hVar.a(dVar.b());
                return;
        }
    }

    public boolean a() {
        return (this.g & Opcodes.NEG_FLOAT) == 7;
    }

    public int b() {
        int i = e + 1;
        e = i;
        this.f = i;
        return this.f;
    }

    public int c() {
        return this.f;
    }

    public String toString() {
        return this.c == null ? "null" : this.c.getClass().getSimpleName() + ":" + this.c + ", " + this.d;
    }
}
