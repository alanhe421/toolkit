package com.qq.reader.liveshow.model.im.message.a;

import com.qq.reader.liveshow.model.im.message.SenderProfile;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;

/* compiled from: CommonMessageEntity */
public class b extends a {
    private GiftItem a;
    private int b;
    private int c;

    public b(SenderProfile senderProfile) {
        super(senderProfile);
    }

    public int e() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public GiftItem f() {
        return this.a;
    }

    public void a(GiftItem giftItem) {
        this.a = giftItem;
    }

    public int g() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }
}
