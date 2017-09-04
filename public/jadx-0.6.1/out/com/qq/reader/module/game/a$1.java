package com.qq.reader.module.game;

import com.qq.reader.module.game.data.GameTopBannerData;
import com.qq.reader.module.game.loader.GameBannerTask.a;
import java.util.List;

/* compiled from: GameDataHelper */
class a$1 implements a {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void a(final List<GameTopBannerData> list) {
        if (a.a(this.a) != null) {
            a.a(this.a).post(new Runnable(this) {
                final /* synthetic */ a$1 b;

                public void run() {
                    if (this.b.a.b != null) {
                        this.b.a.b.a(list);
                    }
                }
            });
        }
    }
}
