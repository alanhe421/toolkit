package com.tencent.upload.network.b;

import android.os.Looper;
import com.tencent.upload.c.c;
import com.tencent.upload.network.a.k;

public interface a {

    public interface a {
        void onRequestError(com.tencent.upload.c.a aVar, int i, String str);

        void onRequestSended(com.tencent.upload.c.a aVar);

        void onRequestTimeout(com.tencent.upload.c.a aVar);

        void onResponse(com.tencent.upload.c.a aVar, c cVar);
    }

    public enum b {
        UNKONWN(-1, "UnKnown"),
        NO_CONNECT(0, "NoConnect"),
        CONNECTING(1, "Connecting"),
        HANDSHAKE(2, "HandShake"),
        ESTABLISHED(3, "Establish");
        
        private int f;
        private String g;

        static {
            UNKONWN = new b("UNKONWN", 0, -1, "UnKnown");
            NO_CONNECT = new b("NO_CONNECT", 1, 0, "NoConnect");
            CONNECTING = new b("CONNECTING", 2, 1, "Connecting");
            HANDSHAKE = new b("HANDSHAKE", 3, 2, "HandShake");
            ESTABLISHED = new b("ESTABLISHED", 4, 3, "Establish");
            b[] bVarArr = new b[]{UNKONWN, NO_CONNECT, CONNECTING, HANDSHAKE, ESTABLISHED};
        }

        private b(int i, String str) {
            this.f = i;
            this.g = str;
        }

        public final String toString() {
            return "[" + this.f + "," + this.g + "]";
        }
    }

    Looper a();

    void a(Looper looper);

    boolean a(com.tencent.upload.c.a aVar, a aVar2);

    boolean a(k kVar);

    void b();

    k c();

    k d();

    String e();

    String f();

    b g();

    boolean h();

    boolean i();
}
