package com.qq.reader.module.game.card;

import android.os.Handler;
import android.os.Looper;
import com.qq.reader.common.download.task.m;
import com.qq.reader.common.download.task.n;
import com.qq.reader.cservice.download.game.d;
import com.qq.reader.module.game.card.view.GameOpenBtn;
import com.qq.reader.module.game.data.c;
import com.tencent.feedback.proguard.R;
import java.lang.ref.WeakReference;

/* compiled from: GameDataDownloadStateListener */
public class a implements m {
    private c a;
    private WeakReference<GameOpenBtn> b;
    private Handler c = new Handler(Looper.myLooper());

    public void a(n nVar) {
        final d dVar = (d) nVar.d();
        if (dVar.getId() != 0 && this.a.k() != 0 && dVar.getId() == this.a.k()) {
            final int a = com.qq.reader.module.game.a.a(nVar.c());
            this.a.a(a);
            this.c.post(new Runnable(this) {
                final /* synthetic */ a c;

                public void run() {
                    if (this.c.b != null && this.c.a != null) {
                        GameOpenBtn gameOpenBtn = (GameOpenBtn) this.c.b.get();
                        if (this.c.a(gameOpenBtn)) {
                            com.qq.reader.common.monitor.debug.c.e("Game", " progress is " + dVar.getProgress());
                            if (a == 1) {
                                gameOpenBtn.setProgress(100, dVar.getProgress());
                                gameOpenBtn.setGameBtnStatus(a);
                                if (dVar.getProgress() == 100) {
                                    gameOpenBtn.setGameBtnStatus(3);
                                }
                            } else if (a == 0) {
                                gameOpenBtn.setProgress(100, 0);
                                gameOpenBtn.setGameBtnStatus(a);
                            } else {
                                gameOpenBtn.setGameBtnStatus(a);
                            }
                        }
                    }
                }
            });
        }
    }

    public void a(c cVar, GameOpenBtn gameOpenBtn) {
        this.a = cVar;
        this.b = new WeakReference(gameOpenBtn);
    }

    private boolean a(GameOpenBtn gameOpenBtn) {
        return gameOpenBtn != null && gameOpenBtn.getTag(R.string.obj_tag) == this.a;
    }
}
