package com.qq.taf;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.b;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import com.qq.taf.jce.e;
import java.util.HashMap;
import java.util.Map;

public final class RequestPacket extends JceStruct {
    static final /* synthetic */ boolean $assertionsDisabled;
    static Map<String, String> cache_context = null;
    static byte[] cache_sBuffer = null;
    public byte cPacketType = (byte) 0;
    public Map<String, String> context;
    public int iMessageType = 0;
    public int iRequestId = 0;
    public int iTimeout = 0;
    public short iVersion = (short) 0;
    public byte[] sBuffer;
    public String sFuncName = null;
    public String sServantName = null;
    public Map<String, String> status;

    static {
        boolean z;
        if (RequestPacket.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        $assertionsDisabled = z;
    }

    public RequestPacket(short s, byte b, int i, int i2, String str, String str2, byte[] bArr, int i3, Map<String, String> map, Map<String, String> map2) {
        this.iVersion = s;
        this.cPacketType = b;
        this.iMessageType = i;
        this.iRequestId = i2;
        this.sServantName = str;
        this.sFuncName = str2;
        this.sBuffer = bArr;
        this.iTimeout = i3;
        this.context = map;
        this.status = map2;
    }

    public boolean equals(Object obj) {
        RequestPacket requestPacket = (RequestPacket) obj;
        if (e.a(1, requestPacket.iVersion) && e.a(1, requestPacket.cPacketType) && e.a(1, requestPacket.iMessageType) && e.a(1, requestPacket.iRequestId) && e.a(Integer.valueOf(1), requestPacket.sServantName) && e.a(Integer.valueOf(1), requestPacket.sFuncName) && e.a(Integer.valueOf(1), requestPacket.sBuffer) && e.a(1, requestPacket.iTimeout) && e.a(Integer.valueOf(1), requestPacket.context) && e.a(Integer.valueOf(1), requestPacket.status)) {
            return true;
        }
        return false;
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            if (!$assertionsDisabled) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void writeTo(d dVar) {
        dVar.a(this.iVersion, 1);
        dVar.b(this.cPacketType, 2);
        dVar.a(this.iMessageType, 3);
        dVar.a(this.iRequestId, 4);
        dVar.a(this.sServantName, 5);
        dVar.a(this.sFuncName, 6);
        dVar.a(this.sBuffer, 7);
        dVar.a(this.iTimeout, 8);
        dVar.a(this.context, 9);
        dVar.a(this.status, 10);
    }

    public void readFrom(c cVar) {
        try {
            this.iVersion = cVar.a(this.iVersion, 1, true);
            this.cPacketType = cVar.a(this.cPacketType, 2, true);
            this.iMessageType = cVar.a(this.iMessageType, 3, true);
            this.iRequestId = cVar.a(this.iRequestId, 4, true);
            this.sServantName = cVar.a(5, true);
            this.sFuncName = cVar.a(6, true);
            if (cache_sBuffer == null) {
                cache_sBuffer = new byte[]{(byte) 0};
            }
            this.sBuffer = cVar.a(cache_sBuffer, 7, true);
            this.iTimeout = cVar.a(this.iTimeout, 8, true);
            if (cache_context == null) {
                cache_context = new HashMap();
                cache_context.put("", "");
            }
            this.context = (Map) cVar.a(cache_context, 9, true);
            if (cache_context == null) {
                cache_context = new HashMap();
                cache_context.put("", "");
            }
            this.status = (Map) cVar.a(cache_context, 10, true);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("RequestPacket decode error " + com.qq.jce.wup.e.a(this.sBuffer));
            throw new RuntimeException(e);
        }
    }

    public void display(StringBuilder stringBuilder, int i) {
        b bVar = new b(stringBuilder, i);
        bVar.a(this.iVersion, "iVersion");
        bVar.a(this.cPacketType, "cPacketType");
        bVar.a(this.iMessageType, "iMessageType");
        bVar.a(this.iRequestId, "iRequestId");
        bVar.a(this.sServantName, "sServantName");
        bVar.a(this.sFuncName, "sFuncName");
        bVar.a(this.sBuffer, "sBuffer");
        bVar.a(this.iTimeout, "iTimeout");
        bVar.a(this.context, "context");
        bVar.a(this.status, "status");
    }
}
