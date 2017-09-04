package com.qq.jce.wup;

import com.qq.taf.RequestPacket;
import com.qq.taf.jce.c;
import com.qq.taf.jce.e;
import java.nio.ByteBuffer;
import java.util.HashMap;

/* compiled from: UniPacket */
public class d extends c {
    static HashMap<String, byte[]> h = null;
    static HashMap<String, HashMap<String, byte[]>> i = null;
    protected RequestPacket g;
    private int j;

    public d() {
        this.g = new RequestPacket();
        this.j = 0;
        this.g.iVersion = (short) 2;
    }

    public <T> void a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.a(str, t);
    }

    public byte[] a() {
        if (this.g.iVersion != (short) 2) {
            if (this.g.sServantName == null) {
                this.g.sServantName = "";
            }
            if (this.g.sFuncName == null) {
                this.g.sFuncName = "";
            }
        } else if (this.g.sServantName == null || this.g.sServantName.equals("")) {
            throw new IllegalArgumentException("servantName can not is null");
        } else if (this.g.sFuncName == null || this.g.sFuncName.equals("")) {
            throw new IllegalArgumentException("funcName can not is null");
        }
        com.qq.taf.jce.d dVar = new com.qq.taf.jce.d(0);
        dVar.a(this.c);
        if (this.g.iVersion == (short) 2 || this.g.iVersion == (short) 1) {
            dVar.a(this.a, 0);
        } else {
            dVar.a(this.e, 0);
        }
        this.g.sBuffer = e.a(dVar.a());
        dVar = new com.qq.taf.jce.d(0);
        dVar.a(this.c);
        this.g.writeTo(dVar);
        byte[] a = e.a(dVar.a());
        int length = a.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        allocate.putInt(length + 4).put(a).flip();
        return allocate.array();
    }

    private void d() {
        c cVar = new c(this.g.sBuffer);
        cVar.a(this.c);
        if (h == null) {
            h = new HashMap();
            h.put("", new byte[0]);
        }
        this.e = cVar.a(h, 0, false);
    }

    private void e() {
        c cVar = new c(this.g.sBuffer);
        cVar.a(this.c);
        if (i == null) {
            i = new HashMap();
            HashMap hashMap = new HashMap();
            hashMap.put("", new byte[0]);
            i.put("", hashMap);
        }
        this.a = cVar.a(i, 0, false);
        this.b = new HashMap();
    }

    public void a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            c cVar = new c(bArr, 4);
            cVar.a(this.c);
            this.g.readFrom(cVar);
            if (this.g.iVersion == (short) 3) {
                d();
                return;
            }
            this.e = null;
            e();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void c(String str) {
        this.g.sServantName = str;
    }

    public String b() {
        return this.g.sFuncName;
    }

    public void d(String str) {
        this.g.sFuncName = str;
    }

    public int c() {
        return this.g.iRequestId;
    }

    public void a(int i) {
        this.g.iRequestId = i;
    }
}
