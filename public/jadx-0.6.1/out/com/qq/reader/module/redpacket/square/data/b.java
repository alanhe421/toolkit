package com.qq.reader.module.redpacket.square.data;

import java.util.HashSet;

/* compiled from: RedPacketSquareClickStateManager */
public class b extends com.qq.reader.appconfig.a.b {
    private HashSet<Long> a;

    /* compiled from: RedPacketSquareClickStateManager */
    public static class a {
        public static b a = new b();
    }

    private b() {
    }

    public static b b() {
        return a.a;
    }

    public boolean a(long j) {
        if (this.a == null || this.a.size() == 0) {
            return false;
        }
        return this.a.contains(Long.valueOf(j));
    }

    public void b(long j) {
        if (this.a == null) {
            this.a = new HashSet();
        }
        if (!this.a.contains(Long.valueOf(j))) {
            this.a.add(Long.valueOf(j));
        }
    }

    public void a() {
        if (this.a != null) {
            this.a.clear();
        }
    }
}
