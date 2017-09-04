package com.tencent.qalsdk.sdk;

import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;

/* compiled from: PushUtil */
public class ai {
    public static c a(ToServiceMsg toServiceMsg) {
        c cVar = new c((byte[]) toServiceMsg.getAttribute(v.e));
        c cVar2 = new c();
        cVar2.readFrom(cVar);
        return cVar2;
    }

    public static void a(ToServiceMsg toServiceMsg, c cVar) {
        d dVar = new d();
        cVar.writeTo(dVar);
        toServiceMsg.addAttribute(v.e, dVar.b());
    }

    public static ah b(ToServiceMsg toServiceMsg) {
        c cVar = new c((byte[]) toServiceMsg.getAttribute(v.f));
        ah ahVar = new ah();
        ahVar.readFrom(cVar);
        return ahVar;
    }

    public static void a(ToServiceMsg toServiceMsg, ah ahVar) {
        d dVar = new d();
        ahVar.writeTo(dVar);
        toServiceMsg.addAttribute(v.f, dVar.b());
    }

    public static void a(ToServiceMsg toServiceMsg, a aVar) {
        d dVar = new d();
        aVar.writeTo(dVar);
        toServiceMsg.addAttribute(v.g, dVar.b());
    }

    public static a c(ToServiceMsg toServiceMsg) {
        c cVar = new c((byte[]) toServiceMsg.getAttribute(v.g));
        a aVar = new a();
        aVar.readFrom(cVar);
        return aVar;
    }

    public static void a(FromServiceMsg fromServiceMsg, d dVar) {
        d dVar2 = new d();
        dVar.writeTo(dVar2);
        fromServiceMsg.addAttribute(v.h, dVar2.b());
    }

    public static d a(FromServiceMsg fromServiceMsg) {
        c cVar = new c((byte[]) fromServiceMsg.getAttribute(v.h));
        d dVar = new d();
        dVar.readFrom(cVar);
        return dVar;
    }
}
