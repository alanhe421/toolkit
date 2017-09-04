package com.sijla.common;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import com.sijla.e.a;
import com.sijla.e.a.b;
import com.sijla.h.i;
import com.sijla.j.c;
import com.sijla.j.f;
import com.sijla.j.j;
import com.tencent.smtt.utils.TbsLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class HBS extends Service implements b {
    public static long a = com.sijla.j.b.g();
    public static c d = b.e();
    public static long e = -1;
    public List<a> b = new ArrayList();
    public a c = null;
    public BroadcastReceiver f = new BroadcastReceiver(this) {
        final /* synthetic */ HBS a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (action.equals("android.intent.action.PACKAGE_ADDED") || action.equals("android.intent.action.PACKAGE_REMOVED") || action.equals("android.intent.action.PACKAGE_REPLACED")) {
                    com.sijla.c.c.a(new com.sijla.h.c(context, intent));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String toString() {
            return "AppReceiver";
        }
    };
    public BroadcastReceiver g = new BroadcastReceiver(this) {
        final /* synthetic */ HBS a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.TIME_TICK")) {
                if (com.sijla.j.b.i() % 5 == 0) {
                    j.a(context, "current_batter_per", Long.valueOf(HBS.e));
                }
            } else if ("android.intent.action.ACTION_SHUTDOWN".equals(action)) {
                try {
                    List arrayList = new ArrayList();
                    arrayList.add(com.sijla.j.a.a.i());
                    arrayList.add(com.sijla.j.b.g() + "");
                    b.e().a("PU", arrayList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (("android.intent.action.BOOT_COMPLETED".equals(action) || "android.net.conn.CONNECTIVITY_CHANGE".equals(action)) && com.sijla.j.a.a.b(context)) {
                f.a("NetworkAvailable change true");
                com.sijla.c.c.a(new i(context, com.sijla.d.c.a));
            }
        }
    };
    com.sijla.a.a h = new com.sijla.a.a(this) {
        final /* synthetic */ HBS a;

        {
            this.a = r1;
        }

        public String a(String str) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("appid", this.a.getApplicationContext().getPackageName());
                jSONObject.put("sdkver", 170425);
                jSONObject.put("appver", com.sijla.j.a.a.f(this.a.getApplicationContext()));
                jSONObject.put("channel", com.sijla.j.b.m(this.a.getApplicationContext()));
                jSONObject.put("sdkTaskisRunning", true);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
            if (jSONObject == null) {
                return null;
            }
            return jSONObject.toString();
        }

        public void b(String str) {
            try {
                com.sijla.c.c.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                    }
                });
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public void c(String str) {
        }
    };

    public void onCreate() {
        super.onCreate();
        if (b.b()) {
            try {
                b.a(getApplicationContext());
                String v = com.sijla.j.a.a.v(getApplicationContext());
                f.a("App ProcessName = " + v);
                if (v.contains(":")) {
                    IntentFilter intentFilter;
                    b.a(d);
                    a = com.sijla.j.b.g();
                    this.c = new a(getApplicationContext());
                    this.c.a((b) this);
                    if (!(1 != com.sijla.d.c.a.optInt("app", 1) || com.sijla.j.b.f(getApplicationContext()) || this.f == null)) {
                        intentFilter = new IntentFilter();
                        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
                        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
                        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
                        intentFilter.addDataScheme("package");
                        intentFilter.setPriority(TbsLog.TBSLOG_CODE_SDK_INIT);
                        registerReceiver(this.f, intentFilter);
                    }
                    if (this.g != null) {
                        intentFilter = new IntentFilter("android.intent.action.TIME_TICK");
                        intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
                        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
                        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                        registerReceiver(this.g, intentFilter);
                    }
                    List c = com.sijla.j.a.a.c();
                    if (c != null && c.size() == 1 && getPackageName().equals(c.get(0))) {
                        Log.i("qm", "not find s");
                        f();
                    } else {
                        Log.i("qm", "find s");
                    }
                    f.a("Service.onCreate by sdk");
                    return;
                }
                f.a("Service.onCreate by other bind");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public IBinder onBind(Intent intent) {
        return this.h;
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        f.a("HBS.onRebind");
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        f.b("HBS.onDestroy");
        try {
            if (this.c != null) {
                this.c.a();
                b.c(getApplicationContext());
            }
        } catch (Throwable th) {
        }
        try {
            if (this.f != null) {
                unregisterReceiver(this.f);
            }
        } catch (Throwable th2) {
        }
        try {
            if (this.g != null) {
                unregisterReceiver(this.g);
            }
        } catch (Throwable th3) {
        }
    }

    public void a() {
        String b = b.b(getApplicationContext());
        if (com.sijla.j.b.a(b)) {
            f();
        } else if (b.equals(getPackageName())) {
            f();
        }
    }

    private void f() {
        com.sijla.c.c.a(new Runnable(this) {
            final /* synthetic */ HBS a;

            {
                this.a = r1;
            }

            public void run() {
                com.sijla.d.a.a(this.a.getApplicationContext());
                f.c(this.a.getPackageName() + " start GS");
                this.a.b = b.d(this.a.getApplicationContext());
                if (this.a.b != null) {
                    Log.i("qm", "t, ok");
                    for (a run : this.a.b) {
                        try {
                            run.run();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                    return;
                }
                Log.i("qm", "t, none");
            }
        });
    }

    private void g() {
        final List list = this.b;
        com.sijla.c.c.a(new Runnable(this) {
            final /* synthetic */ HBS b;

            public void run() {
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        try {
                            ((a) list.get(i)).b();
                            if (i == list.size() - 1) {
                                f.c("All tasks stoped");
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void b() {
        g();
    }

    public void c() {
        com.sijla.c.c.a(new Runnable(this) {
            final /* synthetic */ HBS a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    j.a(this.a.getApplicationContext(), "onPowerConnected", Long.valueOf(com.sijla.j.b.g()));
                    j.a(this.a.getApplicationContext(), "current_batter_per", Long.valueOf(HBS.e));
                    for (a c : this.a.b) {
                        c.c();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public void d() {
        com.sijla.c.c.a(new Runnable(this) {
            final /* synthetic */ HBS a;

            {
                this.a = r1;
            }

            public void run() {
                for (a d : this.a.b) {
                    try {
                        d.d();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                try {
                    long longValue = ((Long) j.b(this.a.getApplicationContext(), "onPowerConnected", Long.valueOf(0))).longValue();
                    if (0 != longValue) {
                        long longValue2 = HBS.e - ((Long) j.b(this.a.getApplicationContext(), "current_batter_per", Long.valueOf(0))).longValue();
                        if (0 != longValue2) {
                            List arrayList = new ArrayList();
                            arrayList.add(com.sijla.j.b.p(this.a.getApplicationContext()));
                            arrayList.add(longValue + "");
                            arrayList.add(((Boolean) j.b(this.a.getApplicationContext(), "usb", Boolean.valueOf(false))).booleanValue() ? "1" : "0");
                            arrayList.add((com.sijla.j.b.g() - longValue) + "");
                            arrayList.add(longValue2 + "");
                            arrayList.add(HBS.e + "");
                            b.e().a(com.sijla.j.b.f("C"), arrayList);
                        }
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        });
    }

    public void e() {
        for (a e : this.b) {
            try {
                e.e();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(Intent intent) {
        for (a a : this.b) {
            try {
                a.a(intent);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        try {
            e = (long) ((intent.getIntExtra("level", 0) * 100) / intent.getIntExtra("scale", 100));
            j.a(getApplicationContext(), "usb", Boolean.valueOf(2 == intent.getIntExtra("plugged", 0)));
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }
}
