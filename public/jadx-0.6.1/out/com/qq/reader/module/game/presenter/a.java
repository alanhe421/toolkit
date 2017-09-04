package com.qq.reader.module.game.presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.db.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.module.game.data.b;
import com.qq.reader.module.game.loader.GameCouponCheckTask;
import com.qq.reader.module.game.presenter.GameCouponHelper.3;
import com.qq.reader.module.game.presenter.GameCouponHelper.4;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: GameCouponHelper */
public class a extends com.qq.reader.common.db.a {
    protected static c b;
    protected static String c;
    public static long d = 10800000;
    private static a i;
    private final String e;
    private List<b> f;
    private String g;
    private String h;
    private Handler j;
    private long k;

    /* compiled from: GameCouponHelper */
    public interface a {
        void a(List<b> list);
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null || !com.qq.reader.common.c.a.bu.equalsIgnoreCase(c)) {
                b = new com.qq.reader.module.game.loader.a(com.qq.reader.common.c.a.bu, null, 1);
                c = com.qq.reader.common.c.a.bu;
            }
            if (i == null) {
                i = new a(ReaderApplication.getApplicationImp().getApplicationContext());
            }
            aVar = i;
        }
        return aVar;
    }

    private a(Context context) {
        this.e = "GameCoupon";
        this.g = e.H + "image/ticketHeader.png";
        this.h = "uniteqqreader://webpage/game/" + e.H + "api/syxs/usercenter/ticks.php?type=1";
        this.k = -1;
        this.f = new ArrayList();
        this.k = d.cj(ReaderApplication.getApplicationImp());
        f();
        this.j = new Handler(Looper.getMainLooper(), new 1(this));
    }

    public void a(JSONObject jSONObject) throws Exception {
        if (jSONObject != null && jSONObject.optInt("code") == 0) {
            Object optString = jSONObject.optString("bannerUrl");
            if (!TextUtils.isEmpty(optString)) {
                this.g = optString;
            }
            optString = jSONObject.optString("ticketUrl");
            if (!TextUtils.isEmpty(optString)) {
                this.h = optString;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("tickets");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int length = optJSONArray.length() - 1; length >= 0; length--) {
                    JSONObject jSONObject2 = (JSONObject) optJSONArray.get(length);
                    b bVar = new b();
                    bVar.a(jSONObject2);
                    this.f.add(bVar);
                    a(bVar, jSONObject2.toString());
                }
            }
        }
    }

    public void a(a aVar) {
        if (this.f.size() != 0) {
            if (System.currentTimeMillis() - this.k < d) {
                aVar.a(null);
                return;
            }
            g.a().a(new GameCouponCheckTask(new 2(this, aVar), e()));
        }
    }

    private String e() {
        StringBuffer stringBuffer = new StringBuffer();
        for (b bVar : this.f) {
            if (stringBuffer.indexOf(bVar.a()) == -1) {
                stringBuffer.append(bVar.a()).append(",");
            }
        }
        return stringBuffer.toString().substring(0, stringBuffer.length() - 1);
    }

    private synchronized void f() {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            SQLiteDatabase a = b.a();
            if (a != null) {
                query = a.query("game_coupon_table", new String[]{"couponid", "time", "json"}, null, null, null, null, "time");
                try {
                    if (query.moveToFirst()) {
                        do {
                            query.getLong(1);
                            String string = query.getString(2);
                            b bVar = new b();
                            bVar.a(new JSONObject(string));
                            if (bVar.h()) {
                                this.f.add(bVar);
                            }
                        } while (query.moveToNext());
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        com.qq.reader.common.monitor.debug.c.e("GameCoupon", " loadDBData with exception : " + e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        if (b != null) {
                            b.c();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        if (b != null) {
                            b.c();
                        }
                        throw th;
                    }
                }
            }
            query = null;
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            com.qq.reader.common.monitor.debug.c.e("GameCoupon", " loadDBData with exception : " + e.getMessage());
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
            throw th;
        }
    }

    private synchronized void a(b bVar, String str) {
        g.a().a(new 3(this, bVar, str));
    }

    private synchronized void a(String str) {
        g.a().a(new 4(this, str));
    }

    public synchronized void b() {
        this.k = System.currentTimeMillis();
        d.m(ReaderApplication.getApplicationImp(), this.k);
        for (b a : this.f) {
            a(a.a());
        }
        this.f.clear();
    }

    public String c() {
        return this.g;
    }

    public String d() {
        return this.h;
    }
}
