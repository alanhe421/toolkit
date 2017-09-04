package com.qq.reader.view.web;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.cservice.download.game.d;
import com.qq.reader.module.game.a;
import com.qq.reader.module.game.card.view.GameOpenBtn;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.core.c;

/* compiled from: PopNativeGameDialog */
class j$3 implements OnClickListener {
    final /* synthetic */ j a;

    j$3(j jVar) {
        this.a = jVar;
    }

    public void onClick(final View view) {
        if (j.b(this.a) != null) {
            Activity activity = (Activity) j.b(this.a).get();
            if (activity != null && !this.a.a(activity, this.a.a)) {
                if (this.a.a.h()) {
                    if (TextUtils.isEmpty(this.a.a.f()) || TextUtils.isEmpty(this.a.a.c()) || TextUtils.isEmpty(this.a.a.b()) || !this.a.a.b().startsWith(c.d)) {
                        com.qq.reader.common.monitor.debug.c.e("Game", "类名不完整,不能下载该该游戏");
                        return;
                    }
                    int d = this.a.a.d();
                    a.a(activity, this.a.a.c(), this.a.a.f(), this.a.a.b(), this.a.a.e(), this.a.a.j());
                    if (d == 0) {
                        view.postDelayed(new Runnable(this) {
                            final /* synthetic */ j$3 b;

                            public void run() {
                                d a = this.b.a.c.a(this.b.a.c.a(), this.b.a.a.k());
                                if (a != null) {
                                    int a2 = a.a(a.getState());
                                    this.b.a.a.a(a2);
                                    if (this.b.a.a == view.getTag(R.string.obj_tag) && (view instanceof GameOpenBtn)) {
                                        ((GameOpenBtn) view).setGameBtnStatus(a2);
                                    }
                                }
                            }
                        }, 1000);
                    }
                } else if (!TextUtils.isEmpty(this.a.a.b())) {
                    com.qq.reader.common.monitor.debug.c.e("Game", "h5 游戏连接是:" + this.a.a.b());
                    if (!this.a.a.g() || com.qq.reader.common.login.c.b()) {
                        j.a(this.a, this.a.a.b());
                        return;
                    }
                    ((ReaderBaseActivity) activity).setLoginNextTask(new com.qq.reader.common.login.a(this) {
                        final /* synthetic */ j$3 a;

                        {
                            this.a = r1;
                        }

                        public void a(int i) {
                            switch (i) {
                                case 1:
                                    j.a(this.a.a, this.a.a.a.b());
                                    return;
                                default:
                                    return;
                            }
                        }
                    });
                    ((ReaderBaseActivity) activity).startLogin();
                }
            }
        }
    }
}
