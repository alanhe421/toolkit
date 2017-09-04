package com.qq.reader.common.web;

import android.app.Activity;
import android.os.Handler;
import com.qq.reader.common.login.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.common.web.js.JSDownLoad;
import com.qq.reader.common.web.js.JSLogin;
import com.qq.reader.common.web.js.JSReadOnline;
import com.qq.reader.module.bookstore.qnative.item.s;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: WebPageBookHandle */
public class d {
    private int a;
    private String b;
    private int c;
    private Activity d;
    private JSLogin e;
    private JSReadOnline f;
    private JSDownLoad g;
    private JSAddToBookShelf h;
    private long i;
    private String j;
    private a k;
    private String l;
    private String m;
    private Handler n;
    private String o;
    private int p;
    private int q;
    private String r;

    public d(Activity activity, Handler handler, JSONObject jSONObject) {
        this.d = activity;
        this.n = handler;
        try {
            String optString = jSONObject.optString("bookinfo");
            this.b = optString;
            JSONObject jSONObject2 = new JSONObject(optString);
            this.i = jSONObject2.optLong("id");
            this.a = jSONObject2.optInt("downloadtype");
            this.j = jSONObject2.optString("downloadurl");
            this.l = jSONObject2.optString(s.ORIGIN);
            this.m = jSONObject2.optString("bookfrom");
            this.o = jSONObject2.optString("title", "");
            this.p = jSONObject2.optInt("bookprice", 0);
            this.q = jSONObject2.optInt("discount", 100);
            this.r = jSONObject2.optString("dismsg", "");
            n();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m();
    }

    private void m() {
        this.e = new JSLogin(this.d);
        this.f = new JSReadOnline(this.d);
        this.g = new JSDownLoad(this.d);
        this.h = new JSAddToBookShelf(this.d);
    }

    private void n() {
        if (this.a == 1 || this.a == 3) {
            this.c = 1;
        } else {
            this.c = 0;
        }
    }

    public boolean a() {
        if (this.a != 4) {
            return true;
        }
        return false;
    }

    public int b() {
        return this.c;
    }

    public String c() {
        return this.l;
    }

    public long d() {
        return this.i;
    }

    public int e() {
        return this.p;
    }

    public int f() {
        return this.q;
    }

    public String g() {
        return this.r;
    }

    public String h() {
        return this.o;
    }

    public void a(String str) {
        this.j = str;
        try {
            JSONObject jSONObject = new JSONObject(this.b);
            jSONObject.put("downloadurl", str);
            this.b = jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean o() {
        return c.b();
    }

    public void i() {
        this.f.readbook(this.b);
    }

    public void j() {
        if (this.a == 0 || o()) {
            switch (this.a) {
                case 0:
                    this.g.download(this.b);
                    return;
                case 1:
                    if (this.j == null || this.j.trim().length() <= 0) {
                        this.n.sendEmptyMessage(1217);
                        return;
                    } else {
                        this.g.download(this.b);
                        return;
                    }
                case 2:
                case 3:
                    this.g.batdownload(this.b);
                    return;
                default:
                    return;
            }
        }
        this.k = new a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.j();
                        return;
                    default:
                        return;
                }
            }
        };
        this.e.setNextLoginTask(new a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.j();
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void k() {
        if (o()) {
            this.h.add(this.b);
            return;
        }
        this.k = new a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.k();
                        return;
                    default:
                        return;
                }
            }
        };
        this.e.setNextLoginTask(new a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.k();
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public a l() {
        return this.k;
    }
}
