package com.qq.reader.module.audio.a;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.common.login.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.common.web.js.JSDownLoad;
import com.qq.reader.common.web.js.JSLogin;
import com.qq.reader.common.web.js.JSReadOnline;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.text.SimpleDateFormat;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AudioDetailBookHandler */
public class b {
    private int A;
    private long B;
    private int C;
    private int D;
    private int E;
    private int F;
    private com.qq.reader.module.bookstore.qnative.b.b G;
    private String H;
    private boolean I = true;
    private int J;
    private int a;
    private int b;
    private String c;
    private int d;
    private Activity e;
    private JSLogin f;
    private JSReadOnline g;
    private JSDownLoad h;
    private JSAddToBookShelf i;
    private long j;
    private String k;
    private boolean l = false;
    private a m;
    private String n;
    private String o;
    private String p;
    private Handler q;
    private boolean r;
    private String s;
    private String t;
    private int u;
    private int v;
    private String w;
    private int x;
    private String y;
    private String z;

    public b(Activity activity, Handler handler) {
        this.e = activity;
        this.q = handler;
        this.G = new com.qq.reader.module.bookstore.qnative.b.b();
        y();
    }

    public int a() {
        return this.F;
    }

    public void a(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject != null) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject("audio");
                if (!TextUtils.isEmpty(b(jSONObject))) {
                    optJSONObject.put("discountendtime", b(jSONObject));
                }
                this.c = optJSONObject.toString();
                this.j = Long.valueOf(optJSONObject.optString("adid")).longValue();
                this.a = optJSONObject.optInt("downloadtype");
                this.k = optJSONObject.optString("downloadurl");
                this.n = optJSONObject.optString(s.STATPARAM_KEY);
                this.o = optJSONObject.optString("bookfrom");
                this.p = optJSONObject.optString("categoryInfoV4SlaveId");
                this.b = optJSONObject.optInt("chargeType", 1);
                if (jSONObject.optInt("isBuyed", 0) > 0) {
                    z = true;
                }
                this.l = z;
                this.B = optJSONObject.optLong("cpid", 0);
                if (this.a == 0 || this.a == 1) {
                    this.G.a(optJSONObject.optJSONObject("downloadinfo"));
                }
                this.r = optJSONObject.optBoolean("isOrdered");
                this.s = optJSONObject.optString("audioName", "");
                this.t = optJSONObject.optString("anchorName", "");
                this.u = optJSONObject.optInt("money", 0);
                JSONObject optJSONObject2 = jSONObject.optJSONObject("discountInfo");
                if (optJSONObject2 != null) {
                    this.v = optJSONObject2.optInt("discount", 100);
                    this.w = optJSONObject2.optString(SocialConstants.PARAM_APP_DESC);
                    this.z = optJSONObject2.optString("endTime");
                }
                this.x = optJSONObject.optInt("ltimedisprice", 0);
                this.A = this.x;
                this.y = optJSONObject.optString("ltimedismsg", "");
                this.D = optJSONObject.optInt("status", 1);
                this.E = optJSONObject.optInt("checkLevel", 0);
                this.C = optJSONObject.optInt("allAudioChapters", 0);
                optJSONObject2 = optJSONObject.optJSONObject("xmlyExtra");
                if (optJSONObject2 != null) {
                    this.J = optJSONObject2.optInt("estimatedTrackCount");
                    this.F = optJSONObject2.optInt("discountedPrice", 0);
                }
                z();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String b(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("discountInfo");
        if (optJSONObject == null) {
            return null;
        }
        String optString = optJSONObject.optString("endTime");
        this.z = optString;
        this.v = optJSONObject.optInt("discount", 100);
        return optString;
    }

    public boolean b() {
        if (!TextUtils.isEmpty(this.z)) {
            try {
                if (System.currentTimeMillis() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.z).getTime() && this.v == 0) {
                    return true;
                }
            } catch (Exception e) {
                c.e("Error", e.getMessage());
            }
        }
        return false;
    }

    public int c() {
        return this.b;
    }

    private void y() {
        this.f = new JSLogin(this.e);
        this.g = new JSReadOnline(this.e);
        this.h = new JSDownLoad(this.e);
        this.i = new JSAddToBookShelf(this.e);
    }

    private void z() {
        if (this.a == 1 || this.a == 3) {
            this.d = 1;
        } else {
            this.d = 0;
        }
    }

    public int d() {
        return this.J;
    }

    public int e() {
        return this.C;
    }

    public String f() {
        return this.n;
    }

    public long g() {
        return this.j;
    }

    public int h() {
        return this.u;
    }

    public int i() {
        return this.A;
    }

    public int j() {
        return this.v;
    }

    public String k() {
        return this.w;
    }

    public int l() {
        return this.x;
    }

    public String m() {
        return this.y;
    }

    public String n() {
        return this.s;
    }

    public String o() {
        return this.t;
    }

    public long p() {
        return this.B;
    }

    public String q() {
        return this.p;
    }

    public String r() {
        if (this.G.i() == 1) {
            return TextUtils.isEmpty(this.H) ? "" : this.H;
        } else {
            return "";
        }
    }

    public String s() {
        if (r().equalsIgnoreCase(this.G.c())) {
            return this.G.d();
        }
        if (r().equalsIgnoreCase(this.G.e())) {
            return this.G.g();
        }
        return "";
    }

    public void a(String str) {
        this.k = str;
        try {
            JSONObject jSONObject = new JSONObject(this.c);
            jSONObject.put("downloadurl", str);
            jSONObject.put("payed", 1);
            this.c = jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(boolean z) {
        this.l = z;
    }

    public boolean t() {
        return this.l;
    }

    private boolean A() {
        return com.qq.reader.common.login.c.b();
    }

    public void u() {
        if (this.a == 0 || A()) {
            Message obtainMessage;
            switch (this.a) {
                case 0:
                    this.h.download(this.c);
                    return;
                case 1:
                    if ("trial".equals(r())) {
                        a("");
                        this.h.download(this.c, r(), s());
                        this.k = null;
                        this.q.sendMessage(this.q.obtainMessage(1237));
                        return;
                    } else if (this.k == null || this.k.trim().length() <= 0) {
                        obtainMessage = this.q.obtainMessage(1217);
                        obtainMessage.obj = r();
                        this.q.sendMessage(obtainMessage);
                        return;
                    } else {
                        this.h.download(this.c, r(), s());
                        this.k = null;
                        return;
                    }
                case 2:
                case 3:
                    if (!"19200".equals(this.p) || this.l) {
                        this.h.batdownload(this.c);
                        return;
                    }
                    obtainMessage = this.q.obtainMessage(1217);
                    obtainMessage.obj = r();
                    this.q.sendMessage(obtainMessage);
                    return;
                case 4:
                    af.a(this.e.getApplicationContext(), (int) R.string.online_download_error, 0).a();
                    return;
                default:
                    return;
            }
        }
        this.m = new a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.u();
                        if (this.a.q != null) {
                            this.a.q.sendEmptyMessage(500007);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.f.setNextLoginTask(new a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.u();
                        this.a.q.sendEmptyMessage(500007);
                        return;
                    default:
                        return;
                }
            }
        });
        this.f.login();
    }

    public void v() {
        if (A()) {
            this.i.audioBookAdd(this.c);
            return;
        }
        this.m = new a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.v();
                        this.a.q.sendEmptyMessage(500008);
                        return;
                    default:
                        return;
                }
            }
        };
        this.f.setNextLoginTask(new a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        this.a.v();
                        this.a.q.sendEmptyMessage(500008);
                        return;
                    default:
                        return;
                }
            }
        });
        this.f.login();
    }

    public a w() {
        return this.m;
    }

    public boolean x() {
        return this.I;
    }
}
