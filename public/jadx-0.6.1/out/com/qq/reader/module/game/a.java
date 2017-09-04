package com.qq.reader.module.game;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.n;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.m;
import com.qq.reader.cservice.download.game.DownloadGameBroadcastReceiver;
import com.qq.reader.cservice.download.game.d;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.game.card.view.GameOpenBtn;
import com.qq.reader.module.game.data.GameTopBannerData;
import com.qq.reader.module.game.data.c;
import com.qq.reader.module.game.loader.GameBannerTask;
import com.qq.reader.module.game.loader.GameUploadTask;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: GameDataHelper */
public class a {
    private static a d;
    GameBannerTask a;
    com.qq.reader.module.game.loader.GameBannerTask.a b;
    com.qq.reader.module.game.loader.GameBannerTask.a c = new 1(this);
    private Handler e = new Handler(Looper.getMainLooper());
    private boolean f;
    private Runnable g = new 2(this);
    private BroadcastReceiver h = new 3(this);
    private Set<Integer> i = new HashSet();
    private IntentFilter j;
    private boolean k = false;
    private HashMap<String, WeakReference<GameOpenBtn>> l = new HashMap();
    private HashMap<String, c> m = new HashMap();

    private a() {
    }

    public void a(boolean z) {
        com.qq.reader.common.monitor.debug.c.e("game_main_page", "is main page do event   " + z);
        this.f = z;
    }

    public boolean a() {
        return this.f;
    }

    public static synchronized a b() {
        a aVar;
        synchronized (a.class) {
            if (d == null) {
                d = new a();
            }
            aVar = d;
        }
        return aVar;
    }

    public static List<com.qq.reader.cservice.adv.a> a(List<GameTopBannerData> list) {
        List arrayList = new ArrayList();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            int i = 0;
            int i2 = 0;
            while (i < list.size()) {
                int i3;
                GameTopBannerData gameTopBannerData = (GameTopBannerData) list.get(i);
                if (gameTopBannerData.beginTime > currentTimeMillis || gameTopBannerData.endTime <= currentTimeMillis) {
                    i3 = i2;
                } else {
                    com.qq.reader.cservice.adv.a aVar = new com.qq.reader.cservice.adv.a(gameTopBannerData.id, "5");
                    aVar.h("{\"uitype\":\"5\"}");
                    arrayList.add(aVar);
                    aVar.f(gameTopBannerData.qurl);
                    aVar.e(gameTopBannerData.img);
                    i3 = i2 + 1;
                    if (i3 >= 5) {
                        break;
                    }
                }
                i++;
                i2 = i3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void c() {
        if (this.e != null) {
            this.e.removeCallbacksAndMessages(null);
        }
    }

    public static int a(TaskStateEnum taskStateEnum) {
        if (taskStateEnum == null) {
            return 0;
        }
        switch (5.a[taskStateEnum.ordinal()]) {
            case 1:
                return 4;
            case 2:
            case 3:
                return 3;
            case 4:
                return 1;
            case 5:
                return 2;
            default:
                return 0;
        }
    }

    public static int a(int i) {
        switch (i) {
            case 1:
                return 3;
            case 2:
                return 3;
            case 4:
                return 4;
            default:
                return 2;
        }
    }

    public static boolean a(c cVar) {
        return com.qq.reader.common.utils.ao.a.a(ReaderApplication.getInstance().getApplication(), cVar.f());
    }

    public static boolean b(c cVar) {
        return new File(cVar.i()).exists();
    }

    public static void c(c cVar) {
        int i = 0;
        if (cVar.g()) {
            cVar.a(6);
        } else if (a(cVar)) {
            cVar.a(5);
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, cVar.f());
            i.a("event_A260", hashMap, ReaderApplication.getApplicationImp());
        } else if (b(cVar)) {
            cVar.a(3);
        } else {
            d a = n.a().a(cVar.k());
            if (a == null) {
                cVar.a(0);
                return;
            }
            int a2 = a(a.getState());
            if (a2 != 3 || b(cVar)) {
                i = a2;
            }
            cVar.a(i);
        }
    }

    public static void a(Activity activity, String str, String str2, String str3, String str4, String str5) {
        long j = 0;
        if (activity == null || !com.qq.reader.common.utils.ao.a.a((Context) activity, str2)) {
            int a;
            com.qq.reader.cservice.download.game.a aVar = (com.qq.reader.cservice.download.game.a) l.d(1006);
            d a2 = aVar.a(aVar.a(), (long) str2.hashCode());
            if (a2 != null) {
                j = a2.getId();
                a = a(a(a2.getState()));
            } else {
                a = 2;
            }
            a(activity, str, str2, str3, str4, str5, a, j);
            return;
        }
        com.qq.reader.common.utils.ao.a.a(activity, str2);
    }

    public static void a(Activity activity, String str, String str2, String str3, String str4, String str5, int i, long j) {
        Intent intent = new Intent(activity, DownloadGameBroadcastReceiver.class);
        intent.putExtra("action", i);
        if (i == 2) {
            intent.putExtra("gameName", str);
            intent.putExtra(Constants.FLAG_PACKAGE_NAME, str2);
            intent.putExtra("downloadUrl", str3);
            intent.putExtra("iconUrl", str4);
            intent.putExtra("jumpBackUrl", str5);
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, str2);
            i.a("event_A258", hashMap, ReaderApplication.getApplicationImp());
        } else {
            intent.putExtra("gameId", j);
        }
        if (activity != null) {
            activity.sendBroadcast(intent);
        }
    }

    public synchronized void d() {
        if (this.a != null) {
            g.a().b(this.a);
        }
        if (this.e != null) {
            this.e.removeCallbacksAndMessages(null);
            if (m.a(ReaderApplication.getApplicationImp())) {
                this.e.postDelayed(this.g, 2400000);
            } else {
                this.e.post(this.g);
            }
        }
    }

    public synchronized void a(com.qq.reader.module.game.loader.GameBannerTask.a aVar) {
        this.b = aVar;
    }

    private void a(String str, int i) {
        WeakReference weakReference = (WeakReference) this.l.get(str);
        if (weakReference != null) {
            GameOpenBtn gameOpenBtn = (GameOpenBtn) weakReference.get();
            if (gameOpenBtn != null) {
                c cVar;
                Object tag = gameOpenBtn.getTag(R.string.obj_tag);
                if (tag instanceof c) {
                    cVar = (c) tag;
                } else {
                    cVar = null;
                }
                if (i == 1) {
                    if (cVar != null && str.equals(cVar.f())) {
                        gameOpenBtn.setGameBtnStatus(5);
                        cVar.a(5);
                    }
                } else if (i == -1 && cVar != null && str.equals(cVar.f())) {
                    cVar.a(0);
                    gameOpenBtn.setGameBtnStatus(0);
                }
            } else {
                this.l.remove(str);
            }
        }
        c cVar2 = (c) this.m.get(str);
        if (cVar2 == null) {
            return;
        }
        if (i == 1) {
            cVar2.a(5);
        } else if (i == -1) {
            cVar2.a(0);
        }
    }

    public void a(Activity activity) {
        if (activity != null) {
            this.i.add(Integer.valueOf(activity.hashCode()));
            if (this.i.size() > 0 && !this.k) {
                if (this.j == null) {
                    this.j = new IntentFilter();
                    this.j.addAction("android.intent.action.PACKAGE_ADDED");
                    this.j.addAction("android.intent.action.PACKAGE_REMOVED");
                    this.j.addDataScheme("package");
                }
                ReaderApplication.getApplicationImp().registerReceiver(this.h, this.j);
                this.k = true;
            }
        }
    }

    public void b(Activity activity) {
        if (activity != null) {
            this.i.remove(Integer.valueOf(activity.hashCode()));
            if (this.i.size() == 0 && this.k) {
                ReaderApplication.getApplicationImp().unregisterReceiver(this.h);
                this.k = false;
            }
        }
    }

    public void d(c cVar) {
        String f = cVar.f();
        if (((c) this.m.get(f)) == null) {
            this.m.put(f, cVar);
        }
    }

    public void a(GameOpenBtn gameOpenBtn, c cVar) {
        if (cVar != null && !cVar.g()) {
            CharSequence f = cVar.f();
            if (!TextUtils.isEmpty(f)) {
                d(cVar);
                WeakReference weakReference = (WeakReference) this.l.get(f);
                if (weakReference == null || weakReference.get() == null) {
                    this.l.put(f, new WeakReference(gameOpenBtn));
                }
            }
        }
    }

    public void e(c cVar) {
        if (cVar != null && !cVar.g()) {
            CharSequence f = cVar.f();
            if (!TextUtils.isEmpty(f)) {
                this.l.remove(f);
                this.m.remove(f);
            }
        }
    }

    public static void a(String str, String str2) {
        g.a().a(new GameUploadTask(str, str2));
    }

    public static Bundle a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("function_type", "go_h5_game");
        bundle.putString("URL_DATA_QURL", str);
        return bundle;
    }

    public static com.qq.reader.common.login.a a(Bundle bundle, Activity activity) {
        return new 4(activity, bundle.getString("URL_DATA_QURL"));
    }
}
