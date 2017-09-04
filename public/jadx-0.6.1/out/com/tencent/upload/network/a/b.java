package com.tencent.upload.network.a;

import com.tencent.upload.common.a.a;
import com.tencent.upload.common.j;

public class b {
    public static void a(String str, com.tencent.upload.b.a.a.b bVar) {
        if (str != null) {
            Object obj = new byte[0];
            synchronized (obj) {
                new Thread(new c(str, obj, bVar), "upload_domainParser").start();
                try {
                    j.j();
                    obj.wait(20000);
                } catch (InterruptedException e) {
                    a.c("DomainParser", "parse: wait:" + e);
                }
            }
        }
    }
}
