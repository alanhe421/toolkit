package com.qq.reader.liveshow.c;

import android.app.Activity;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.b;
import com.qq.reader.liveshow.b.m;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.b.e;
import com.qq.reader.liveshow.c.b.f;
import com.qq.reader.liveshow.model.a;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.utils.LogConstants;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.l;
import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;
import java.io.IOException;
import java.util.Map;

/* compiled from: LoginHelper */
public class k {
    private static final String b = k.class.getSimpleName();
    private Activity a;
    private e c;
    private f d;
    private boolean e;

    public boolean a() {
        return this.e;
    }

    public k(Activity activity, e eVar) {
        this.a = activity;
        this.c = eVar;
    }

    public boolean a(final boolean z) {
        if (a()) {
            return false;
        }
        c.a().a(this.a);
        this.e = true;
        SxbLog.a(b, "---------------------       start login         ------------------------------");
        com.qq.reader.liveshow.b.e c = n.a().c();
        Map map = null;
        if (c != null) {
            map = c.a();
        }
        try {
            l.a().a(l.a(a.i()), new m<com.qq.reader.liveshow.model.e>(this) {
                final /* synthetic */ k b;

                public void a(int i, com.qq.reader.liveshow.model.e eVar) {
                    int i2 = 1;
                    SxbLog.b(k.b, "login imlogin " + z + " loginSuccess");
                    if (eVar != null) {
                        SxbLog.b(k.b, eVar.toString());
                        switch (eVar.a) {
                            case -201:
                                if (this.b.c != null) {
                                    this.b.c.c(eVar.a);
                                }
                                if (this.b.a != null) {
                                    m.a(this.b.a, h.tip_black_name_can_not_watch_live, 0);
                                    break;
                                }
                                break;
                            case DLConstants.LOAD_ERR_IO_FAIL /*-103*/:
                                if (this.b.c != null) {
                                    this.b.c.c(eVar.a);
                                }
                                if (eVar.f != null) {
                                    c.a().a(eVar.f.b);
                                    c.a().d(eVar.f.a);
                                    c.a().d(eVar.f.c);
                                    c.a().c(eVar.f.d);
                                    break;
                                }
                                break;
                            case DLConstants.LOAD_ERR_SIGNATURES /*-101*/:
                                if (this.b.c != null) {
                                    this.b.c.c(eVar.a);
                                    break;
                                }
                                break;
                            case -100:
                                if (this.b.c != null) {
                                    this.b.c.c(eVar.a);
                                }
                                if (this.b.a != null) {
                                    m.a(this.b.a, h.tip_live_home_not_exits, 0);
                                    break;
                                }
                                break;
                            case 0:
                            case 3:
                                boolean z;
                                boolean z2 = eVar.e == 2;
                                c.a().a(eVar.d);
                                c.a().b(eVar.c);
                                c a = c.a();
                                if (eVar.a == 3) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                a.c(z);
                                c.a().c(eVar.e);
                                c a2 = c.a();
                                if (!z2) {
                                    i2 = 0;
                                }
                                a2.b(i2);
                                c.a().a(z2 ? a.i() : -1);
                                c.a().b(z2);
                                if (eVar.f != null) {
                                    c.a().a(eVar.f.b);
                                    c.a().d(eVar.f.a);
                                    c.a().d(eVar.f.c);
                                    c.a().c(eVar.f.d);
                                }
                                if (TextUtils.isEmpty(eVar.h)) {
                                    a.e(a.i() + "");
                                } else {
                                    a.e(eVar.h);
                                }
                                if (!TextUtils.isEmpty(eVar.g)) {
                                    a.b(eVar.g);
                                }
                                if (this.b.c != null) {
                                    this.b.c.c(eVar.a);
                                }
                                if (z) {
                                    this.b.a(c.a().b(), c.a().c());
                                    break;
                                }
                                break;
                            default:
                                if (this.b.c != null) {
                                    this.b.c.c(eVar.a);
                                }
                                if (this.b.a != null) {
                                    m.a(this.b.a, this.b.a.getString(h.live_enter_error) + eVar.a, 0);
                                    break;
                                }
                                break;
                        }
                        this.b.e = false;
                    }
                }

                public void a(int i, String str) {
                    this.b.e = false;
                    if (this.b.a != null) {
                        m.a(this.b.a, this.b.a.getString(h.live_normal_error), 0);
                    }
                    if (this.b.c != null) {
                        this.b.c.l();
                    }
                }

                public void a(Exception exception) {
                    this.b.e = false;
                    if (this.b.a != null) {
                        m.a(this.b.a, this.b.a.getString(h.live_net_error), 0);
                    }
                    if (this.b.c != null) {
                        this.b.c.l();
                    }
                }
            }, map);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    public k(Activity activity, e eVar, f fVar) {
        this.a = activity;
        this.c = eVar;
        this.d = fVar;
    }

    public void a(final String str, String str2) {
        TIMUser tIMUser = new TIMUser();
        tIMUser.setAccountType(String.valueOf(8167));
        tIMUser.setAppIdAt3rd(String.valueOf(com.qq.reader.liveshow.utils.e.c));
        tIMUser.setIdentifier(str);
        TIMManager.getInstance().login(com.qq.reader.liveshow.utils.e.c, tIMUser, str2, new TIMCallBack(this) {
            final /* synthetic */ k b;

            public void onError(int i, String str) {
                SxbLog.e(k.b, "IMLogin fail ：" + i + " msg " + str);
                if (this.b.a != null) {
                    m.a(this.b.a, this.b.a.getString(h.live_enter_error) + i, 0);
                }
                if (this.b.c != null) {
                    this.b.c.l();
                }
                this.b.e = false;
            }

            public void onSuccess() {
                SxbLog.b(k.b, "keypath IMLogin succ !");
                SxbLog.c(k.b, LogConstants.b + LogConstants.a + str + LogConstants.a + "request room id");
                b b = n.a().b();
                if (b != null) {
                    b.a(this.b.a, new com.qq.reader.liveshow.b.a<Map<String, String>>(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void a(Map<String, String> map) {
                            if (map != null) {
                                if (TextUtils.isEmpty(c.a().e()) && c.a().m() == 0) {
                                    c.a().d((String) map.get("user_avatar"));
                                }
                                if (TextUtils.isEmpty(c.a().d())) {
                                    c.a().c((String) map.get("user_nick"));
                                }
                                try {
                                    c.a().e(Integer.valueOf((String) map.get("user_balance")).intValue());
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (!com.qq.reader.liveshow.utils.a.a.a().g()) {
                                this.a.b.f();
                            }
                        }
                    });
                } else {
                    if (this.b.a != null) {
                        m.a(this.b.a, this.b.a.getString(h.live_enter_error) + ":remote", 0);
                    }
                    if (this.b.c != null) {
                        this.b.c.l();
                    }
                }
                this.b.e = true;
            }
        });
    }

    public void b() {
        SxbLog.a(b, "start im logout");
        TIMManager.getInstance().logout(new TIMCallBack(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void onError(int i, String str) {
                SxbLog.e(k.b, "IMLogout fail ：" + i + " msg " + str);
                if (this.a.d != null) {
                    this.a.d.n();
                }
                SxbLog.b(k.b, "stop avsdk on logout fail");
                if (!com.qq.reader.liveshow.utils.a.a.a().i()) {
                    this.a.c();
                }
            }

            public void onSuccess() {
                SxbLog.b(k.b, "IMLogout succ !");
                SxbLog.b(k.b, "stop avsdk on logout success");
                this.a.c();
            }
        });
    }

    private void f() {
        com.qq.reader.liveshow.avcontrollers.c.a().a(com.qq.reader.liveshow.utils.e.c, "8167", c.a().b(), c.a().c());
        int d = com.qq.reader.liveshow.avcontrollers.c.a().d();
        if (d != 0) {
            com.qq.reader.liveshow.avcontrollers.c.a().e();
            if (this.a != null) {
                m.a(this.a, this.a.getString(h.init_av_context_dead_error) + "：" + d, 0);
            }
            if (this.c != null) {
                this.c.l();
            }
        } else if (this.c != null) {
            this.c.k();
        }
    }

    public void c() {
        com.qq.reader.liveshow.avcontrollers.c.a().e();
        this.e = false;
        if (this.d != null) {
            this.d.m();
        }
        com.qq.reader.liveshow.utils.a.a.a().c().a(true, this, " close sdk ");
    }

    public void d() {
        this.c = null;
        this.d = null;
        this.a = null;
    }
}
