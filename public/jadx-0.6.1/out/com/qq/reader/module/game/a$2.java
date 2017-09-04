package com.qq.reader.module.game;

import com.qq.reader.common.readertask.g;
import com.qq.reader.module.game.loader.GameBannerTask;

/* compiled from: GameDataHelper */
class a$2 implements Runnable {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void run() {
        if (this.a.a == null) {
            this.a.a = new GameBannerTask();
            this.a.a.setCallback(this.a.c);
        }
        g.a().a(this.a.a);
        if (a.a(this.a) != null) {
            a.a(this.a).postDelayed(this, 2400000);
        }
    }
}
