package com.qq.reader.liveshow.model.im.message.a;

import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.model.filter.a.b;
import com.qq.reader.liveshow.model.im.message.SenderProfile;

/* compiled from: BaseMessageEntity */
public abstract class a implements b {
    private SenderProfile a;
    private String b;
    private long c;

    public a(SenderProfile senderProfile) {
        this.a = senderProfile;
    }

    public SenderProfile b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public boolean d() {
        String id = this.a.getId();
        String b = c.a().b();
        if (b == null || !b.equals(id)) {
            return false;
        }
        return true;
    }

    public long a() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }
}
