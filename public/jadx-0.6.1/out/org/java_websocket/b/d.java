package org.java_websocket.b;

import com.tencent.qalsdk.sdk.v;

/* compiled from: HandshakeImpl1Client */
public class d extends g implements b {
    private String a = v.n;

    public void a(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("http resource descriptor must not be null");
        }
        this.a = str;
    }

    public String a() {
        return this.a;
    }
}
