package com.qq.reader.module.redpacket.square.data;

import android.content.Context;
import com.qq.reader.module.redpacket.view.RedPacketMessageView;
import java.util.ArrayList;

/* compiled from: RedPacketMessageViewPool */
public class a {
    private ArrayList<RedPacketMessageView> a = new ArrayList();
    private final int b = 1;
    private final int c = 2;
    private int d = 0;
    private Context e;

    public a(Context context) {
        this.e = context;
        e();
    }

    private void e() {
        for (int i = 0; i < 1; i++) {
            this.a.add(new RedPacketMessageView(this.e));
        }
    }

    public void a() {
        this.d = 0;
    }

    public void b() {
        if (this.a != null) {
            this.a.clear();
        }
        this.d = 0;
    }

    public RedPacketMessageView c() {
        this.d++;
        if (this.d > this.a.size() - 1) {
            for (int i = 0; i < 2; i++) {
                this.a.add(new RedPacketMessageView(this.e));
            }
        }
        return (RedPacketMessageView) this.a.get(this.d);
    }

    public RedPacketMessageView d() {
        return (RedPacketMessageView) this.a.get(0);
    }
}
