package com.qrcomic.screenshot.c;

import com.qrcomic.screenshot.b.a;
import com.qrcomic.screenshot.b.b;

/* compiled from: QRDoodleCommandFactory */
public class d {
    private static volatile d a;

    private d() {
    }

    public static d a() {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    public f a(a aVar, boolean z) {
        if (aVar == null || !(aVar instanceof b)) {
            return null;
        }
        b bVar = (b) aVar;
        if (z) {
            return new b(bVar, bVar.g());
        }
        return new b(bVar);
    }
}
