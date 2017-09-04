package com.tencent.android.tpush.service.channel.c;

import android.util.SparseArray;
import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.tencent.android.tpush.service.channel.exception.CommandMappingException;
import com.tencent.android.tpush.service.channel.protocol.TpnsClientReportReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsClientReportRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClickReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClickRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRedirectRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsTokenTagReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsTokenTagRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterRsp;
import com.tencent.android.tpush.service.channel.protocol.TpnsUpdateTokenReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsUpdateTokenRsp;
import java.util.HashMap;

/* compiled from: ProGuard */
public class d {
    public static final Integer a = Integer.valueOf(0);
    public static final Integer b = Integer.valueOf(128);
    public static final SparseArray c = new SparseArray();
    public static final HashMap d = new HashMap();

    static {
        a(a, Byte.valueOf((byte) 1), TpnsPushClientReq.class);
        a(a, Byte.valueOf((byte) 2), TpnsGetApListReq.class);
        a(b, Byte.valueOf((byte) 2), TpnsGetApListRsp.class);
        a(a, Byte.valueOf((byte) 3), TpnsConfigReq.class);
        a(b, Byte.valueOf((byte) 3), TpnsConfigRsp.class);
        a(a, Byte.valueOf((byte) 4), TpnsRegisterReq.class);
        a(b, Byte.valueOf((byte) 4), TpnsRegisterRsp.class);
        a(a, Byte.valueOf((byte) 5), TpnsUnregisterReq.class);
        a(b, Byte.valueOf((byte) 5), TpnsUnregisterRsp.class);
        a(a, Byte.valueOf((byte) 6), TpnsReconnectReq.class);
        a(b, Byte.valueOf((byte) 6), TpnsReconnectRsp.class);
        a(a, Byte.valueOf((byte) 9), TpnsClientReportReq.class);
        a(b, Byte.valueOf((byte) 9), TpnsClientReportRsp.class);
        a(a, Byte.valueOf((byte) 10), TpnsRedirectReq.class);
        a(b, Byte.valueOf((byte) 10), TpnsRedirectRsp.class);
        a(a, Byte.valueOf(JceStruct.STRUCT_END), TpnsPushVerifyReq.class);
        a(b, Byte.valueOf(JceStruct.STRUCT_END), TpnsPushVerifyRsp.class);
        a(a, Byte.valueOf((byte) 15), TpnsTokenTagReq.class);
        a(b, Byte.valueOf((byte) 15), TpnsTokenTagRsp.class);
        a(a, Byte.valueOf((byte) 16), TpnsPushClickReq.class);
        a(b, Byte.valueOf((byte) 16), TpnsPushClickRsp.class);
        a(a, Byte.valueOf((byte) 17), TpnsUpdateTokenReq.class);
        a(b, Byte.valueOf((byte) 17), TpnsUpdateTokenRsp.class);
    }

    public static void a(Integer num, Byte b, Class cls) {
        c.put(num.intValue() | b.byteValue(), cls);
        d.put(cls, Integer.valueOf(num.intValue() | b.byteValue()));
    }

    public static short a(Class cls) {
        return ((Integer) d.get(cls)).shortValue();
    }

    public static Class a(short s) {
        return (Class) c.get(s);
    }

    public static JceStruct a(short s, byte[] bArr) {
        Class a = a(s);
        if (a == null || bArr == null) {
            return null;
        }
        try {
            JceStruct jceStruct = (JceStruct) a.newInstance();
            c cVar = new c(bArr);
            cVar.a("UTF-8");
            jceStruct.readFrom(cVar);
            return jceStruct;
        } catch (Throwable e) {
            throw new CommandMappingException(e.getMessage(), e);
        }
    }
}
