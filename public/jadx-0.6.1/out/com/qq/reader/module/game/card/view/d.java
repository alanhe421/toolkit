package com.qq.reader.module.game.card.view;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.game.card.b;
import com.qq.reader.module.game.data.c;
import com.tencent.feedback.proguard.R;
import java.lang.ref.WeakReference;

/* compiled from: GameViewBasePresenter */
public abstract class d<T extends b> {
    private WeakReference<a> a;
    protected WeakReference<T> b;
    protected c c;
    protected com.qq.reader.cservice.download.game.a d;
    protected com.qq.reader.module.game.card.a e = new com.qq.reader.module.game.card.a();
    protected OnClickListener f = new OnClickListener(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void onClick(final View view) {
            if (this.a.a != null) {
                Activity fromActivity = ((a) this.a.a.get()).getFromActivity();
                if (fromActivity != null && !this.a.b(fromActivity, this.a.c)) {
                    if (this.a.c.g()) {
                        if (!TextUtils.isEmpty(this.a.c.b())) {
                            com.qq.reader.common.monitor.debug.c.e("Game", "h5 游戏连接是:" + this.a.c.b());
                            com.qq.reader.module.game.a.a(this.a.c.l() + "", "1");
                            if (com.qq.reader.common.login.c.b()) {
                                o.a(fromActivity, this.a.c.b(), false, null);
                            } else if (this.a.a != null) {
                                a aVar = (a) this.a.a.get();
                                if (aVar != null) {
                                    aVar.doFunction(com.qq.reader.module.game.a.a(this.a.c.b()));
                                }
                            }
                        }
                    } else if (TextUtils.isEmpty(this.a.c.f()) || TextUtils.isEmpty(this.a.c.c()) || TextUtils.isEmpty(this.a.c.b()) || !this.a.c.b().startsWith(com.tencent.qalsdk.core.c.d)) {
                        com.qq.reader.common.monitor.debug.c.e("Game", "类名不完整,不能下载该该游戏");
                    } else {
                        int d = this.a.c.d();
                        if (d == 5) {
                            com.qq.reader.module.game.a.a(this.a.c.l() + "", "3");
                        } else if (d == 0) {
                            com.qq.reader.module.game.a.a(this.a.c.l() + "", "2");
                        }
                        com.qq.reader.module.game.a.a(fromActivity, this.a.c.c(), this.a.c.f(), this.a.c.b(), this.a.c.e(), this.a.c.j());
                        if (d == 0) {
                            view.postDelayed(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 b;

                                public void run() {
                                    com.qq.reader.cservice.download.game.d a = this.b.a.d.a(this.b.a.d.a(), this.b.a.c.k());
                                    if (a != null) {
                                        int a2 = com.qq.reader.module.game.a.a(a.getState());
                                        this.b.a.c.a(a2);
                                        if (this.b.a.c == view.getTag(R.string.obj_tag) && (view instanceof GameOpenBtn)) {
                                            ((GameOpenBtn) view).setGameBtnStatus(a2);
                                        }
                                    }
                                }
                            }, 1000);
                        }
                    }
                }
            }
        }
    };

    protected abstract GameOpenBtn a();

    public d(a aVar) {
        this.a = new WeakReference(aVar);
    }

    public void a(T t, final c cVar) {
        if (t != null && cVar != null) {
            t.setVisiable(0);
            this.c = cVar;
            if (this.d == null) {
                this.d = (com.qq.reader.cservice.download.game.a) l.d(1006);
                this.d.a(TaskStateEnum.values(), this.e);
            }
            if (this.b == null || this.b.get() == null || !((b) this.b.get()).equals(t)) {
                this.b = new WeakReference(t);
            }
            GameOpenBtn a = a();
            if (a != null) {
                a.setTag(R.string.obj_tag, cVar);
                this.e.a(cVar, a);
                a.setOnClickListener(this.f);
                com.qq.reader.cservice.download.game.d a2 = this.d.a(this.d.a(), cVar.k());
                if (!(a2 == null || cVar.g())) {
                    a.setProgress(100, a2.getProgress());
                }
                a.setGameBtnStatus(cVar.d());
            }
            ((View) t).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ d b;

                public void onClick(View view) {
                    if (this.b.a != null) {
                        a aVar = (a) this.b.a.get();
                        if (aVar != null && !this.b.a(aVar.getFromActivity(), cVar)) {
                            try {
                                String j = cVar.j();
                                if (com.qq.reader.qurl.c.b(j)) {
                                    com.qq.reader.qurl.c.a(aVar.getFromActivity(), j);
                                } else {
                                    o.a(aVar.getFromActivity(), j, false, null);
                                }
                                com.qq.reader.common.monitor.debug.c.e("timetest", "button click");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            a aVar = (a) this.a.get();
            if (aVar != null) {
                com.qq.reader.module.game.a.b().a(aVar.getFromActivity());
            }
            com.qq.reader.module.game.a.b().a(a, cVar);
        }
    }

    public void b() {
        if (this.d != null) {
            this.d.b(TaskStateEnum.values(), this.e);
        }
        a aVar = (a) this.a.get();
        if (aVar != null) {
            com.qq.reader.module.game.a.b().b(aVar.getFromActivity());
        }
        com.qq.reader.module.game.a.b().e(this.c);
    }

    protected boolean b(Activity activity, c cVar) {
        return false;
    }

    protected boolean a(Activity activity, c cVar) {
        return false;
    }
}
