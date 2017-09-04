package com.qq.reader.liveshow.model.im.message.a;

import com.qq.reader.liveshow.model.a;
import com.qq.reader.liveshow.model.im.message.SenderProfile;

/* compiled from: GiftMessageEntity */
public class c extends a {
    private int a;
    private int b;

    public c(SenderProfile senderProfile) {
        super(senderProfile);
    }

    public void a(int i) {
        this.a = i;
    }

    public String e() {
        return a.f(this.a) == null ? null : a.f(this.a).mName;
    }

    public String f() {
        return a.f(this.a) == null ? null : a.f(this.a).mImgUrl;
    }

    public int g() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }
}
