package com.qq.reader.module.rookie.presenter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import com.qq.reader.module.rookie.dataloader.RookieGiftTask;
import com.qq.reader.module.rookie.presenter.RookieGiftHelper.10;
import com.qq.reader.module.rookie.presenter.RookieGiftHelper.6;
import com.qq.reader.module.rookie.presenter.RookieGiftHelper.7;
import com.qq.reader.module.rookie.presenter.RookieGiftHelper.8;
import com.qq.reader.module.rookie.presenter.RookieGiftHelper.9;
import com.qq.reader.qurl.b;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RookieGiftHelper */
public class a extends com.qq.reader.common.db.a implements b {
    protected static c d;
    private static a p;
    public boolean b = false;
    public boolean c = false;
    public String[] e = new String[]{"p1", "p2", "p3", "p4", "p5"};
    private final String f = "RookieGift";
    private int g = APPluginErrorCode.ERROR_APP_SYSTEM;
    private StringBuffer h = new StringBuffer();
    private int i = -1001;
    private List<a> j;
    private boolean k;
    private boolean l;
    private SimpleDateFormat m = new SimpleDateFormat("yyyyMMdd");
    private HashMap<Integer, com.qq.reader.module.rookie.a.c> n;
    private HashMap<String, List<Integer>> o;
    private Handler q;
    private int r = -1;
    private String s = "";
    private String t = "";
    private Boolean u = Boolean.valueOf(false);
    private long v = 0;
    private BroadcastReceiver w = new 1(this);

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (d == null) {
                d = new com.qq.reader.module.rookie.dataloader.a(com.qq.reader.common.c.a.bt, null, 1);
            }
            if (p == null) {
                p = new a(ReaderApplication.getApplicationImp().getApplicationContext());
            }
            aVar = p;
        }
        return aVar;
    }

    private a(Context context) {
        int bF;
        boolean z = true;
        int bG = d.bG(ReaderApplication.getApplicationImp());
        if (bG < 0) {
            bF = d.bF(ReaderApplication.getApplicationImp());
        } else {
            bF = bG;
        }
        boolean z2 = bF >= 1 && bF <= 30;
        this.b = z2;
        if (bF < 1 || bF > 60) {
            z = false;
        }
        this.c = z;
        this.t = d.bH(ReaderApplication.getApplicationImp());
        this.j = new ArrayList();
        this.n = new HashMap();
        this.o = new HashMap();
        if (this.c) {
            g();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.qq.reader.common.c.a.cI);
        context.registerReceiver(this.w, intentFilter);
        this.q = new Handler(Looper.getMainLooper(), new 4(this));
        if (com.qq.reader.appconfig.b.i) {
            a(" debug rookie start ");
            this.q.sendEmptyMessageDelayed(this.i, (long) this.g);
        }
    }

    public void b() {
        a("开始从服务器刷新礼物列表");
        g.a().a(new RookieGiftTask(new 5(this)));
    }

    private void a(JSONObject jSONObject) throws Exception {
        if (jSONObject.optInt("code") == 0) {
            int optInt = jSONObject.optInt("activeDays");
            a("parseWebData刷新新手天数 = " + optInt);
            d.O(ReaderApplication.getApplicationImp(), optInt);
            if (optInt < 1 || optInt > 30) {
                this.b = false;
            } else {
                this.b = true;
            }
            a("isRookie = " + this.b);
            if (optInt < 1 || optInt > 60) {
                this.c = false;
            } else {
                this.c = true;
            }
            a("parseWebData刷新新礼物isGiftActive = " + this.c);
            if (this.n == null || this.n.size() == 0) {
                g();
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("giftlist");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                a("服务器返回礼物列表json: \n" + optJSONArray.toString());
                this.o.clear();
                Set hashSet = new HashSet();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                    com.qq.reader.module.rookie.a.c cVar = new com.qq.reader.module.rookie.a.c();
                    cVar.a(jSONObject2);
                    hashSet.add(Integer.valueOf(cVar.a()));
                    if (this.n.containsKey(Integer.valueOf(cVar.a()))) {
                        ((com.qq.reader.module.rookie.a.c) this.n.get(Integer.valueOf(cVar.a()))).a(cVar);
                        b((com.qq.reader.module.rookie.a.c) this.n.get(Integer.valueOf(cVar.a())), jSONObject2.toString());
                    } else {
                        this.n.put(Integer.valueOf(cVar.a()), cVar);
                        a(cVar, jSONObject2.toString());
                    }
                    a(cVar);
                }
                Iterator it = this.n.entrySet().iterator();
                while (it.hasNext()) {
                    Integer num = (Integer) ((Entry) it.next()).getKey();
                    if (!hashSet.contains(num)) {
                        it.remove();
                        b(num.intValue());
                    }
                }
            }
        }
    }

    public com.qq.reader.module.rookie.a.b a(String str, long j, boolean z) {
        a("getDisplayGift " + str + VoiceWakeuperAidl.PARAMS_SEPARATE + j + VoiceWakeuperAidl.PARAMS_SEPARATE + z);
        if (this.u.booleanValue() || !this.o.containsKey(str)) {
            return null;
        }
        if (str.equals("p1") && this.m.format(new Date()).equals(this.t)) {
            a("4tab 今天弹过");
            return null;
        }
        com.qq.reader.module.rookie.a.c cVar;
        List<Integer> list = (List) this.o.get(str);
        a("mGiftIds = " + this.o.toString());
        if (list != null) {
            cVar = null;
            for (Integer num : list) {
                com.qq.reader.module.rookie.a.c cVar2 = (com.qq.reader.module.rookie.a.c) this.n.get(num);
                a("tempGift id:" + cVar2.a());
                if (cVar2.f() && a(cVar2.a())) {
                    com.qq.reader.module.rookie.a.a aVar = (com.qq.reader.module.rookie.a.a) cVar2.d().get(str);
                    a("检查显示规则:" + aVar.toString());
                    if (!aVar.e() && aVar.a(z) && !aVar.a(j) && a(aVar.c())) {
                        if (cVar == null) {
                            a("获取到第一个temp数据");
                        } else if (aVar.b() < ((com.qq.reader.module.rookie.a.a) cVar.d().get(str)).b()) {
                            a("优先级交换:" + aVar.toString());
                        } else if (aVar.b() == ((com.qq.reader.module.rookie.a.a) cVar.d().get(str)).b()) {
                            a("优先级相同:" + aVar.toString());
                            cVar2 = cVar;
                        }
                        cVar = cVar2;
                    }
                }
                cVar2 = cVar;
                cVar = cVar2;
            }
        } else {
            cVar = null;
        }
        if (cVar != null) {
            com.qq.reader.common.monitor.debug.c.e("RookieGift", "getDisplayGift id = " + cVar.a());
            com.qq.reader.module.rookie.a.b a = a(cVar, str, j);
            a("已经找到需要显示的礼物:gift=" + a.toString());
            return a;
        }
        a("没有找到需要显示的礼物");
        return null;
    }

    public com.qq.reader.module.rookie.a.b a(int i, String str) {
        try {
            a("点击获取弹窗 " + str + VoiceWakeuperAidl.PARAMS_SEPARATE + i);
            return a((com.qq.reader.module.rookie.a.c) this.n.get(Integer.valueOf(i)), str, -1);
        } catch (Exception e) {
            return null;
        }
    }

    private com.qq.reader.module.rookie.a.b a(com.qq.reader.module.rookie.a.c cVar, String str, long j) {
        com.qq.reader.module.rookie.a.b bVar = new com.qq.reader.module.rookie.a.b();
        bVar.a = cVar.a();
        bVar.b = str;
        bVar.e = cVar.c();
        String a = cVar.a(str);
        if (j > 0 && cVar.c() == 1 && !str.equals("p3") && !TextUtils.isEmpty(a)) {
            a = a + GetVoteUserIconsTask.BID + j;
        }
        bVar.c = a;
        if (str != "p4") {
            bVar.d = cVar.b(str);
        }
        return bVar;
    }

    public boolean c() {
        for (Entry value : this.n.entrySet()) {
            com.qq.reader.module.rookie.a.c cVar = (com.qq.reader.module.rookie.a.c) value.getValue();
            if (cVar.f() && !cVar.d().containsKey("p5")) {
                return true;
            }
        }
        return false;
    }

    public void a(int i, boolean z) {
        if (this.n.containsKey(Integer.valueOf(i))) {
            a("本地修改礼物领取状态 需要同步获取网络数据giftId=" + i + " isObtained=" + z);
            ((com.qq.reader.module.rookie.a.c) this.n.get(Integer.valueOf(i))).a(z);
            a(i, "status", ((com.qq.reader.module.rookie.a.c) this.n.get(Integer.valueOf(i))).b());
            if (z) {
                b();
            }
        }
    }

    public void a(Activity activity) {
        if (com.qq.reader.common.login.c.b() && this.o.containsKey("p4")) {
            List<Integer> list = (List) this.o.get("p4");
            if (list != null && list.size() > 0) {
                for (Integer num : list) {
                    com.qq.reader.module.rookie.a.c cVar = (com.qq.reader.module.rookie.a.c) this.n.get(num);
                    if (cVar.f()) {
                        a(activity, ((com.qq.reader.module.rookie.a.a) cVar.d().get("p4")).d());
                        return;
                    }
                }
            }
        }
    }

    public void b(Activity activity) {
        com.qq.reader.common.monitor.debug.c.e("RookieGift", "autoObtainGiftReaderpage" + (System.currentTimeMillis() - this.v));
        if (this.v <= 0 || System.currentTimeMillis() - this.v >= 600000) {
            this.v = System.currentTimeMillis();
            if (com.qq.reader.common.login.c.b()) {
                com.qq.reader.module.rookie.a.b a = a("p5", 0, false);
                if (a != null) {
                    a(activity, a.d);
                }
            }
        }
    }

    private boolean a(int i) {
        a("isTodayGift:tempId=" + i);
        if (!this.m.format(new Date()).equals(this.s)) {
            a("isTodayGift:日期不同");
            return true;
        } else if (this.r == -1 || i == this.r) {
            a("isTodayGift:今天还没显示礼物或者显示同一种礼物");
            return true;
        } else {
            a("isTodayGift:失败");
            return false;
        }
    }

    private boolean a(List<com.qq.reader.module.rookie.a.a.a> list) {
        if (list != null && list.size() > 0) {
            a("判断前置礼物是否都满足条件" + list.toString());
            for (com.qq.reader.module.rookie.a.a.a aVar : list) {
                a("判断:gift" + aVar.toString());
                com.qq.reader.module.rookie.a.c cVar = (com.qq.reader.module.rookie.a.c) this.n.get(Integer.valueOf(aVar.a));
                if (cVar != null && cVar.f() && cVar.d().containsKey(aVar.b) && !((com.qq.reader.module.rookie.a.a) cVar.d().get(aVar.b)).e()) {
                    a("判断失败" + aVar.toString());
                    return false;
                }
            }
        }
        a("判断成功");
        return true;
    }

    public int a(Activity activity, com.qq.reader.module.rookie.a.b bVar) {
        if (com.qq.reader.common.login.c.b()) {
            a(activity, bVar.d);
        } else {
            ((ReaderBaseActivity) activity).setLoginNextTask(new 6(this, activity, bVar));
            ((ReaderBaseActivity) activity).startLogin();
        }
        return 0;
    }

    private void a(Activity activity, String str) {
        com.qq.reader.common.monitor.debug.c.a("RookieGift", "obtainRookieGift " + str);
        try {
            a("领取礼物 礼物跳转qurl=" + str);
            com.qq.reader.qurl.c.a(activity, str, (b) this, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(com.qq.reader.module.rookie.a.c cVar) {
        for (Object obj : this.e) {
            if (cVar.d().containsKey(obj)) {
                if (this.o.get(obj) == null) {
                    this.o.put(obj, new ArrayList());
                }
                ((List) this.o.get(obj)).add(Integer.valueOf(cVar.a()));
            }
        }
    }

    private synchronized void g() {
        Cursor query;
        Exception e;
        Throwable th;
        try {
            SQLiteDatabase a = d.a();
            if (a != null) {
                query = a.query("rookie_gift_table", new String[]{"giftid", "status", "json", "show_status", "show_date"}, null, null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        this.n.clear();
                        this.o.clear();
                        String format = this.m.format(new Date());
                        do {
                            int i = query.getInt(1);
                            String string = query.getString(2);
                            String string2 = query.getString(3);
                            String string3 = query.getString(4);
                            com.qq.reader.module.rookie.a.c cVar = new com.qq.reader.module.rookie.a.c();
                            cVar.a(new JSONObject(string));
                            cVar.a(i);
                            cVar.c(string2);
                            if (format.equals(string3)) {
                                this.r = cVar.a();
                                this.s = string3;
                            }
                            this.n.put(Integer.valueOf(cVar.a()), cVar);
                            a(cVar);
                        } while (query.moveToNext());
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        com.qq.reader.common.monitor.debug.c.e("RookieGift", " loadDBData with exception : " + e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        if (d != null) {
                            d.c();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        if (d != null) {
                            d.c();
                        }
                        throw th;
                    }
                }
            }
            query = null;
            a("LOAD FROM DB mGiftData = " + this.n.toString() + "\n mGiftId = " + this.o.toString());
            if (query != null) {
                query.close();
            }
            if (d != null) {
                d.c();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            com.qq.reader.common.monitor.debug.c.e("RookieGift", " loadDBData with exception : " + e.getMessage());
            if (query != null) {
                query.close();
            }
            if (d != null) {
                d.c();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            if (d != null) {
                d.c();
            }
            throw th;
        }
    }

    private synchronized void a(com.qq.reader.module.rookie.a.c cVar, String str) {
        g.a().a(new 6(this, cVar, str));
    }

    private synchronized void b(com.qq.reader.module.rookie.a.c cVar, String str) {
        g.a().a(new 7(this, cVar, str));
    }

    private synchronized void a(int i, String str, int i2) {
        g.a().a(new 8(this, str, i2, i));
    }

    private synchronized void a(int i, String str, String str2) {
        a("update gift id=" + i + " column=" + str + " value=" + str2);
        g.a().a(new 9(this, str, str2, i));
    }

    private synchronized void b(int i) {
        a("delete gift id=" + i);
        g.a().a(new 10(this, i));
    }

    public boolean a(Message message) {
        try {
            a("领取结果:" + message.toString());
            JSONObject jSONObject = new JSONObject((String) message.obj);
            int optInt = jSONObject.optInt("code");
            this.q.post(new 2(this, jSONObject.optString("rookieMsg")));
            switch (optInt) {
                case -20002:
                case -20001:
                case 0:
                    a(jSONObject);
                    c(true);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized void a(int i, String str, long j) {
        a("礼物已经显示到相应位置 giftId:" + i + " position = " + str + " bid=" + j);
        a("开始同步数据库");
        com.qq.reader.module.rookie.a.c cVar = (com.qq.reader.module.rookie.a.c) this.n.get(Integer.valueOf(i));
        if (cVar == null) {
            a("error:showedGift = null");
        } else {
            this.r = i;
            this.s = this.m.format(new Date());
            if (str.equals("p1")) {
                this.t = this.s;
                d.A(ReaderApplication.getApplicationImp(), this.t);
            }
            a(i, "show_date", this.s);
            com.qq.reader.module.rookie.a.a aVar = (com.qq.reader.module.rookie.a.a) cVar.d().get(str);
            if (!(aVar == null || aVar.e())) {
                aVar.b(j);
                a(cVar.a(), "show_status", cVar.e());
            }
        }
    }

    public boolean d() {
        return this.l || this.k;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.j.add(aVar);
        }
    }

    public void b(a aVar) {
        if (aVar != null) {
            this.j.remove(aVar);
        }
    }

    public boolean e() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public boolean f() {
        return this.l;
    }

    public void c(boolean z) {
        a("礼物列表刷新,是否是领取后刷新 :" + z);
        this.q.post(new 3(this, z));
    }

    private void h() {
        if (this.h.length() > 0) {
            Intent intent = new Intent("com.qq.reader._rookie_debug_update_info");
            intent.putExtra("info", this.h.toString());
            ReaderApplication.getApplicationImp().sendBroadcast(intent);
            com.qq.reader.common.monitor.debug.c.e("ROOKIE_DEBUG", this.h.toString());
            this.h.delete(0, this.h.length());
        }
    }

    public void a(String str) {
        if (com.qq.reader.appconfig.b.i) {
            this.h.append("\n").append(str);
        }
    }
}
