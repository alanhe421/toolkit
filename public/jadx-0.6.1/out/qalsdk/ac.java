package qalsdk;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.l;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.util.QLog;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;
import qalsdk.w.a;

/* compiled from: WifiDetectImpl */
public class ac extends ag {
    public static final int a = 0;
    public static final int b = -1;
    public static final int c = -2;
    public static final int d = -3;
    public static final int e = -4;
    public static AtomicBoolean f = new AtomicBoolean(false);
    private static final String h = "WifiDetector";
    private static final String i = "_wifi_detect_history";
    private static final long j = 7200000;
    private static final long k = 60000;
    private static final long l = 86400000;
    private static final int t = 1000;
    private j m;
    private AtomicBoolean n = new AtomicBoolean(false);
    private volatile int o = 0;
    private int p = -1;
    private String q;
    private String r;
    private HashMap<String, af> s = new HashMap();
    private Handler u = new ad(this);
    private a v = new ae(this);

    public ac(j jVar) {
        this.m = jVar;
        g();
        e();
    }

    public void a(String str) {
        QLog.d(h, 1, "WIFI detect onWifiConnected!");
        af afVar = (af) this.s.get(str);
        long currentTimeMillis = System.currentTimeMillis();
        if (afVar == null) {
            a(str, false);
            c(str);
        } else if (!afVar.c) {
            c(str);
        } else if (currentTimeMillis - afVar.b >= j) {
            c(str);
        } else {
            b(str, j - (currentTimeMillis - afVar.b));
        }
    }

    public void a() {
        QLog.d(h, 1, "WIFI detect onWifiDisconnected!");
        this.u.removeMessages(1000);
    }

    public void b() {
        QLog.d(h, 1, "WIFI detect onWifiAllConnFailed!");
        c(m.h());
    }

    public void c() {
        QLog.d(h, 1, "WIFI detect onWifiConnFake!");
        c(m.h());
    }

    public void b(String str) {
        f.set(false);
        QLog.d(h, 1, "WIFI detect onWifiConnSucc!");
        a(str, true);
    }

    public void d() {
        int i = this.p;
        String str = this.q;
        if (!m.e()) {
            QLog.d(h, 1, "WIFI detect result, WIFI_NONE");
        } else if (i == 0) {
            QLog.d(h, 1, "WIFI detect result, WIFI_OK");
        } else if (i == -1) {
            QLog.d(h, 1, "WIFI detect result, WIFI_EXCEPTION");
        } else if (i == -2 && !this.m.d.a.a() && d(this.r)) {
            QLog.d(h, 1, "WIFI detect result, WIFI_NEED_AUTH");
            FromServiceMsg fromServiceMsg = new FromServiceMsg(m.b.i(), j.f(), "0", com.tencent.qalsdk.base.a.ac);
            fromServiceMsg.setMsgSuccess();
            fromServiceMsg.setMsfCommand(MsfCommand.onNetNeedSignon);
            fromServiceMsg.addAttribute("signonurl", this.r);
            MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
            f.set(true);
            m.b.a(null, fromServiceMsg);
        } else {
            QLog.d(h, 1, "WIFI detect result, WIFI_OTHER");
        }
        f();
    }

    private void b(String str, long j) {
        this.u.removeMessages(1000);
        Message obtainMessage = this.u.obtainMessage();
        obtainMessage.obj = str;
        this.u.sendMessageDelayed(obtainMessage, j);
    }

    private void e() {
        if (m.e() && d(m.h())) {
            a(m.h());
        }
    }

    private synchronized void c(String str) {
        synchronized (this) {
            if (!m.e()) {
                QLog.d(h, 1, "WIFI detect start failed, wifi is not connected!");
            } else if (!d(str)) {
                QLog.d(h, 1, "WIFI detect start failed, ssid is invalid!");
            } else if (this.n.compareAndSet(false, true)) {
                QLog.d(h, 1, "WIFI detect started!");
                this.r = str;
                a(this.r, System.currentTimeMillis());
                b(str, (long) j);
                for (int i = 0; i < this.g.length; i++) {
                    this.o |= 1 << i;
                    Runnable a = a(this.g[i], i, this.v);
                    if (a != null) {
                        Thread thread = new Thread(a);
                        thread.setName("WifiDetectEchoThread");
                        thread.start();
                    }
                }
            } else {
                QLog.d(h, 1, "WIFI detect start failed, there is detect running!");
                b(str, 60000);
            }
        }
    }

    private void f() {
        this.n.set(false);
        this.o = 0;
        this.p = -1;
        this.q = null;
        this.r = "";
    }

    private int a(int i) {
        switch (i) {
            case -3:
                return -2;
            case 0:
                return 0;
            default:
                return -1;
        }
    }

    private void a(int i, String str) {
        if (this.p != -2 || i == 0) {
            this.p = i;
        }
        if (this.p == -2) {
            this.q = str;
        }
    }

    private boolean d(String str) {
        if (TextUtils.isEmpty(str) || !str.equals(m.h())) {
            return false;
        }
        return true;
    }

    private void g() {
        String config = l.a().getConfig(i);
        if (config != null) {
            try {
                JSONArray jSONArray = new JSONArray(config);
                for (int i = 0; i < jSONArray.length(); i++) {
                    af a = af.a(jSONArray.getJSONObject(i));
                    if (a != null) {
                        this.s.put(a.a, a);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(String str, boolean z) {
        af afVar = (af) this.s.get(str);
        if (afVar != null) {
            a(str, afVar.b, z);
        }
    }

    public void a(String str, long j) {
        af afVar = (af) this.s.get(str);
        if (afVar != null) {
            a(str, j, afVar.c);
        } else {
            a(str, j, false);
        }
    }

    private void a(String str, long j, boolean z) {
        this.s.put(str, new af(str, j, z));
        try {
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : this.s.entrySet()) {
                String str2 = (String) entry.getKey();
                af afVar = (af) entry.getValue();
                if (afVar.a.equals(str) || System.currentTimeMillis() - afVar.b <= 86400000) {
                    JSONObject a = afVar.a();
                    if (a != null) {
                        jSONArray.put(a);
                    }
                } else {
                    this.s.remove(str2);
                }
            }
            QLog.d(h, 4, "updatewifi:" + jSONArray.toString());
            l.a().setConfig(i, jSONArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
