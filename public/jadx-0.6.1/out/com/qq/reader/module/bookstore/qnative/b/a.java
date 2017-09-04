package com.qq.reader.module.bookstore.qnative.b;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.login.c;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.common.web.js.JSDownLoad;
import com.qq.reader.common.web.js.JSLogin;
import com.qq.reader.common.web.js.JSReadOnline;
import com.qq.reader.cservice.cloud.a.g;
import com.qq.reader.cservice.cloud.b;
import com.qq.reader.cservice.download.book.e;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DetailBookHandler */
public class a {
    private boolean A = true;
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
    private boolean k = false;
    private com.qq.reader.common.login.a l;
    private String m;
    private String n;
    private String o;
    private Handler p;
    private boolean q;
    private String r;
    private int s;
    private int t;
    private String u;
    private int v;
    private String w;
    private int x;
    private b y;
    private String z;

    public a(Activity activity, Handler handler) {
        this.d = activity;
        this.p = handler;
        this.y = new b();
        y();
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject("book");
                if (!TextUtils.isEmpty(c(jSONObject))) {
                    optJSONObject.put("discountendtime", c(jSONObject));
                }
                this.b = optJSONObject.toString();
                this.i = optJSONObject.optLong("id");
                this.a = optJSONObject.optInt("downloadtype");
                this.j = optJSONObject.optString("downloadurl");
                this.m = optJSONObject.optString(s.STATPARAM_KEY);
                this.n = optJSONObject.optString("bookfrom");
                this.o = optJSONObject.optString("categoryInfoV4SlaveId");
                if (this.a == 0 || this.a == 1) {
                    this.y.a(optJSONObject.optJSONObject("downloadinfo"));
                }
                if (o()) {
                    this.j = "";
                }
                this.q = optJSONObject.optBoolean("isOrdered");
                this.r = optJSONObject.optString("title", "");
                this.s = optJSONObject.optInt("bookprice", 0);
                this.t = optJSONObject.optInt("discount", 100);
                this.u = optJSONObject.optString("dismsg", "");
                this.v = optJSONObject.optInt("ltimedisprice", 0);
                this.x = this.v;
                this.w = optJSONObject.optString("ltimedismsg", "");
                z();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String c(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("detailmsg");
        if (optJSONObject != null) {
            return optJSONObject.optString("discountendtime");
        }
        return null;
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.b = jSONObject.toString();
            this.i = jSONObject.optLong("id");
            this.a = jSONObject.optInt("downloadtype");
            this.j = jSONObject.optString("downloadurl");
            this.m = jSONObject.optString(s.STATPARAM_KEY);
            this.n = jSONObject.optString("bookfrom");
            if (this.a == 0 || this.a == 1) {
                this.y.a(jSONObject.optJSONObject("downloadinfo"));
            }
            if (o()) {
                this.j = "";
            }
            this.q = jSONObject.optBoolean("isOrdered");
            this.r = jSONObject.optString("title", "");
            this.s = jSONObject.optInt("bookprice", 0);
            this.t = jSONObject.optInt("discount", 100);
            this.u = jSONObject.optString("dismsg", "");
            this.v = jSONObject.optInt("ltimedisprice", 0);
            this.w = jSONObject.optString("ltimedismsg", "");
            z();
        }
    }

    private void y() {
        this.e = new JSLogin(this.d);
        this.f = new JSReadOnline(this.d);
        this.g = new JSDownLoad(this.d);
        this.h = new JSAddToBookShelf(this.d);
    }

    private void z() {
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
        return this.m;
    }

    public long d() {
        return this.i;
    }

    public int e() {
        return this.s;
    }

    public int f() {
        return this.x;
    }

    public int g() {
        return this.t;
    }

    public String h() {
        return this.u;
    }

    public int i() {
        return this.v;
    }

    public String j() {
        return this.w;
    }

    public String k() {
        return this.r;
    }

    public String l() {
        return this.o;
    }

    public boolean m() {
        return this.q;
    }

    public String n() {
        if (this.y.i() == 1) {
            return TextUtils.isEmpty(this.z) ? "" : this.z;
        } else {
            return "";
        }
    }

    public boolean o() {
        return this.y.a();
    }

    @Deprecated
    public boolean p() {
        return this.y.b();
    }

    public String q() {
        if (n().equalsIgnoreCase(this.y.c())) {
            return this.y.d();
        }
        if (n().equalsIgnoreCase(this.y.e())) {
            return this.y.g();
        }
        return "";
    }

    public void a(String str) {
        this.j = str;
        try {
            JSONObject jSONObject = new JSONObject(this.b);
            jSONObject.put("downloadurl", str);
            jSONObject.put("payed", 1);
            this.b = jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(boolean z) {
        this.k = z;
    }

    private boolean A() {
        return c.b();
    }

    public void r() {
        this.f.readbook(this.b);
    }

    public com.qq.reader.common.login.a s() {
        switch (this.y.i()) {
            case -1:
                af.a(this.d, this.d.getResources().getString(R.string.bookinfo_client_needupdate, new Object[]{this.d.getResources().getString(R.string.app_name)}), 0).a();
                break;
            case 0:
            case 1:
                this.z = this.y.e();
                u();
                return w();
        }
        return null;
    }

    public com.qq.reader.common.login.a t() {
        switch (this.y.h()) {
            case -1:
                af.a(this.d, this.d.getResources().getString(R.string.bookinfo_client_needupdate, new Object[]{this.d.getResources().getString(R.string.app_name)}), 0).a();
                break;
            case 1:
                r();
                break;
            case 2:
                Mark e = i.c().e(String.valueOf(this.i));
                if ((e instanceof DownloadMark) && e.isHardCoverBook()) {
                    i.c().c(e.getId());
                    e eVar = (e) l.d(1001);
                    eVar.d(eVar.a(this.i));
                    List arrayList = new ArrayList(1);
                    com.qq.reader.cservice.cloud.a.a aVar = new com.qq.reader.cservice.cloud.a.a();
                    aVar.a(this.i);
                    aVar.a(com.qq.reader.cservice.cloud.a.a.a(e));
                    arrayList.add(aVar);
                    g cVar = new com.qq.reader.cservice.cloud.a.c(arrayList);
                    cVar.b(hashCode());
                    b.a(this.d.getApplicationContext()).a(cVar, false, null);
                }
                this.z = this.y.c();
                u();
                return w();
        }
        return null;
    }

    public void u() {
        if (this.a == 0 || A()) {
            Message obtainMessage;
            switch (this.a) {
                case 0:
                    if (o()) {
                        this.a = 1;
                        u();
                        return;
                    }
                    this.g.download(this.b);
                    return;
                case 1:
                    if ("trial".equals(n())) {
                        a("");
                        this.g.download(this.b, n(), q());
                        this.j = null;
                        this.p.sendMessage(this.p.obtainMessage(1237));
                        return;
                    } else if (this.j == null || this.j.trim().length() <= 0) {
                        obtainMessage = this.p.obtainMessage(1217);
                        obtainMessage.obj = n();
                        this.p.sendMessage(obtainMessage);
                        return;
                    } else {
                        this.g.download(this.b, n(), q());
                        this.j = null;
                        return;
                    }
                case 2:
                case 3:
                    if (!"19200".equals(this.o) || this.k) {
                        this.g.batdownload(this.b);
                        return;
                    }
                    obtainMessage = this.p.obtainMessage(1217);
                    obtainMessage.obj = n();
                    this.p.sendMessage(obtainMessage);
                    return;
                case 4:
                    af.a(this.d.getApplicationContext(), (int) R.string.online_download_error, 0).a();
                    return;
                default:
                    return;
            }
        }
        this.l = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.u();
                        if (this.a.p != null) {
                            this.a.p.sendEmptyMessage(500007);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.e.setNextLoginTask(new com.qq.reader.common.login.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.u();
                        this.a.p.sendEmptyMessage(500007);
                        return;
                    default:
                        return;
                }
            }
        });
        this.e.login();
    }

    public void v() {
        if (A()) {
            this.h.add(this.b);
            return;
        }
        this.l = new com.qq.reader.common.login.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.v();
                        this.a.p.sendEmptyMessage(500008);
                        return;
                    default:
                        return;
                }
            }
        };
        this.e.setNextLoginTask(new com.qq.reader.common.login.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.v();
                        this.a.p.sendEmptyMessage(500008);
                        return;
                    default:
                        return;
                }
            }
        });
        this.e.login();
    }

    public com.qq.reader.common.login.a w() {
        return this.l;
    }

    public boolean x() {
        return this.A;
    }

    public void b(boolean z) {
        try {
            JSONObject jSONObject = new JSONObject(this.b);
            if (z) {
                jSONObject.put("needtoast", 1);
            } else {
                jSONObject.put("needtoast", 0);
            }
            this.b = jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
