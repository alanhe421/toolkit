package com.qq.reader.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.SkinListNetTask;
import com.qq.reader.common.readertask.protocol.SkinQueryEnableTask;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.plugin.SkinManager.3.1;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import com.tencent.theme.SkinEngine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SkinManager */
public class w extends com.qq.reader.appconfig.a.b implements g {
    private static String a = "SkinManager";
    private static boolean b = false;
    private static volatile w f = null;
    private ArrayList<x> c;
    private x d;
    private HashSet<WeakReference<b>> e;
    private HashMap<String, y> g;

    /* compiled from: SkinManager */
    public interface b {
        String A();

        Handler B();
    }

    /* compiled from: SkinManager */
    class a extends AsyncTask<Object, Object, Object> {
        WeakReference<Activity> a = null;
        final /* synthetic */ w b;
        private com.qq.reader.view.c c = null;
        private x d;
        private y e;

        public a(w wVar, Activity activity, x xVar) {
            this.b = wVar;
            this.a = new WeakReference(activity);
            this.d = xVar;
        }

        protected void onPreExecute() {
            Activity activity = (Activity) this.a.get();
            if (activity != null) {
                if (this.c == null) {
                    this.c = new com.qq.reader.view.c(activity);
                    this.c.a("正在删除...");
                }
                this.c.f_();
                if (this.d != null) {
                    this.e = this.b.e(this.d);
                }
            }
            super.onPreExecute();
        }

        protected Object doInBackground(Object... objArr) {
            try {
                Looper.prepare();
                Thread.sleep(800);
                if (this.e != null && this.e.i()) {
                    this.e.k();
                }
            } catch (Exception e) {
            }
            return null;
        }

        protected void onPostExecute(Object obj) {
            if (this.c != null) {
                this.c.dismiss();
            }
            super.onPostExecute(obj);
        }
    }

    /* compiled from: SkinManager */
    private class c {
        final /* synthetic */ w a;

        private c(w wVar) {
            this.a = wVar;
        }

        private final void a(Context context) {
            try {
                context.getCacheDir();
                SkinEngine.mIconResourceID = Integer.valueOf(R.drawable.icon);
                try {
                    if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean("com.sec.android.support.multiwindow")) {
                        SkinEngine.mIconResourceID = Integer.valueOf(R.drawable.icon);
                        com.qq.reader.common.monitor.debug.c.c(SkinEngine.TAG, "Set icon resouceID to " + R.drawable.icon, false);
                    }
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                }
                SkinEngine.init(context, z.a, com.qq.reader.c.a.class, R.color.activate_img_loading, null);
                com.qq.reader.common.monitor.debug.c.c(SkinEngine.TAG, "ignore skinEngine accered.", false);
                SkinEngine.getInstances().setSkinEngineHandler(new com.qq.reader.plugin.a.a(ReaderApplication.getApplicationImp()));
                SkinEngine.getInstances().addDrawableResource(R.drawable.main_tab_bg_shape);
                SkinEngine.getInstances().addDrawableResource(R.drawable.maintab_repeat_bg);
            } catch (Exception e2) {
                SkinEngine.getInstances().unInit();
                ReaderApplication.IS_SUPPORT_THEME = false;
            }
        }
    }

    /* compiled from: SkinManager */
    class d extends AsyncTask<Object, Object, Object> {
        WeakReference<Activity> a = null;
        final /* synthetic */ w b;
        private final int c = -1;
        private final int d = 0;
        private final int e = 1;
        private com.qq.reader.view.c f = null;

        public d(w wVar, Activity activity) {
            this.b = wVar;
            this.a = new WeakReference(activity);
        }

        protected void onPreExecute() {
            Activity activity = (Activity) this.a.get();
            if (activity != null) {
                if (this.f == null) {
                    this.f = new com.qq.reader.view.c(activity);
                    this.f.a("正在切换主题...");
                }
                this.f.f_();
            }
            super.onPreExecute();
        }

        protected Object doInBackground(Object... objArr) {
            try {
                Thread.sleep(800);
            } catch (Exception e) {
            }
            String str = (String) objArr[0];
            Context context = (Context) objArr[1];
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("skinId", str);
                jSONObject.put("ret", -1);
                jSONObject.put(SocialConstants.PARAM_SEND_MSG, "发送错误，请稍候再试");
                if (!(Constants.DEFAULT_UIN.equals(str) || w.d(this.b.b(str)))) {
                    jSONObject.put("ret", 1);
                    jSONObject.put(SocialConstants.PARAM_SEND_MSG, "主题文件丢失，请检查SDCard");
                }
                com.qq.reader.appconfig.a.d.C(context, str);
                if (this.b.e(str)) {
                    jSONObject.put("ret", 0);
                } else {
                    jSONObject.put("ret", -1);
                    jSONObject.put(SocialConstants.PARAM_SEND_MSG, "发生错误，请稍候再试");
                }
            } catch (Exception e2) {
                com.qq.reader.common.monitor.debug.c.a(w.a, e2.toString());
            }
            return jSONObject;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected void onPostExecute(java.lang.Object r5) {
            /*
            r4 = this;
            r0 = r5 instanceof org.json.JSONObject;
            if (r0 == 0) goto L_0x0027;
        L_0x0004:
            r0 = r5;
            r0 = (org.json.JSONObject) r0;
            r1 = "ret";
            r1 = r0.getInt(r1);	 Catch:{ Exception -> 0x003b }
            r2 = "msg";
            r2 = r0.getString(r2);	 Catch:{ Exception -> 0x003b }
            r3 = "skinId";
            r0 = r0.getString(r3);	 Catch:{ Exception -> 0x003b }
            switch(r1) {
                case -1: goto L_0x0048;
                case 0: goto L_0x0034;
                case 1: goto L_0x0048;
                default: goto L_0x001f;
            };	 Catch:{ Exception -> 0x003b }
        L_0x001f:
            r1 = r4.b;	 Catch:{ Exception -> 0x003b }
            r2 = "ERROR";
            r1.a(r0, r2);	 Catch:{ Exception -> 0x003b }
        L_0x0027:
            r0 = r4.f;
            if (r0 == 0) goto L_0x0030;
        L_0x002b:
            r0 = r4.f;
            r0.dismiss();
        L_0x0030:
            super.onPostExecute(r5);
            return;
        L_0x0034:
            r1 = r4.b;	 Catch:{ Exception -> 0x003b }
            r2 = 1;
            r1.a(r0, r2);	 Catch:{ Exception -> 0x003b }
            goto L_0x0027;
        L_0x003b:
            r0 = move-exception;
            r1 = com.qq.reader.plugin.w.a;
            r0 = r0.toString();
            com.qq.reader.common.monitor.debug.c.a(r1, r0);
            goto L_0x0027;
        L_0x0048:
            r1 = r4.b;	 Catch:{ Exception -> 0x003b }
            r1.a(r0, r2);	 Catch:{ Exception -> 0x003b }
            goto L_0x0027;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.w.d.onPostExecute(java.lang.Object):void");
        }
    }

    public void a() {
        synchronized (w.class) {
            f = null;
            try {
                if (this.g != null) {
                    for (Entry value : this.g.entrySet()) {
                        ((y) value.getValue()).v();
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public static w b() {
        if (f == null) {
            synchronized (w.class) {
                if (f == null) {
                    f = new w();
                }
            }
        }
        return f;
    }

    private w() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = new HashMap();
        this.e = new HashSet();
        this.c = new ArrayList();
        if (this.d == null) {
            this.d = new x(Constants.DEFAULT_UIN, Constants.DEFAULT_UIN, "系统默认", "default_1.0", "", "", "", "", "0", "0书币", "1", "default_1.0", "default_1.0");
            this.d.b(4);
        }
        k();
    }

    public void a(b bVar) {
        if (bVar != null) {
            String A = bVar.A();
            if (A != null) {
                Object obj;
                Iterator it = this.e.iterator();
                while (it.hasNext()) {
                    b bVar2 = (b) ((WeakReference) it.next()).get();
                    if (bVar2 != null && A.equals(bVar2.A())) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    this.e.add(new WeakReference(bVar));
                }
            }
        }
    }

    public void b(b bVar) {
        if (bVar != null) {
            String A = bVar.A();
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                b bVar2 = (b) weakReference.get();
                if (bVar2 != null && A.equals(bVar2.A())) {
                    this.e.remove(weakReference);
                    return;
                }
            }
        }
    }

    private Handler f(String str) {
        if (str == null) {
            return null;
        }
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            b bVar = (b) ((WeakReference) it.next()).get();
            if (bVar != null && (str.equals(bVar.A()) || "skin_all".equals(bVar.A()))) {
                return bVar.B();
            }
        }
        return null;
    }

    public synchronized void a(Context context) {
        if (!(b || context == null)) {
            b = true;
            new c().a(context);
        }
    }

    public static final String c() {
        return com.qq.reader.common.c.a.q + "skinlist.db";
    }

    public static final String d() {
        return com.qq.reader.common.c.a.q + "skinlist.version";
    }

    protected final String a(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(com.qq.reader.common.c.a.q);
        stringBuilder.append(str);
        stringBuilder.append("/");
        return stringBuilder.toString();
    }

    protected String b(String str) {
        if (str == null) {
            return null;
        }
        String a = a(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a);
        stringBuilder.append("res/");
        return stringBuilder.toString();
    }

    private void j() {
        Handler f = f("skin_all");
        if (f != null) {
            Message obtainMessage = f.obtainMessage();
            obtainMessage.what = 10000401;
            obtainMessage.obj = e();
            f.sendMessage(obtainMessage);
        }
    }

    private ArrayList<x> k() {
        ArrayList<x> arrayList;
        synchronized (this.c) {
            if (this.c == null || this.c.size() == 0) {
                this.c = v.b().d();
            }
            arrayList = this.c;
        }
        return arrayList;
    }

    public x c(String str) {
        if (Constants.DEFAULT_UIN.equals(str)) {
            return this.d;
        }
        if (str != null && str.length() > 0) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                x xVar = (x) it.next();
                if (str.equals(xVar.i())) {
                    return xVar;
                }
            }
        }
        return null;
    }

    public x b(Context context) {
        String bS = com.qq.reader.appconfig.a.d.bS(context);
        if (Constants.DEFAULT_UIN.equals(bS)) {
            return null;
        }
        return c(bS);
    }

    public void c(Context context) {
        final List arrayList = new ArrayList();
        final Hashtable hashtable = new Hashtable();
        final ArrayList arrayList2 = new ArrayList();
        final Hashtable hashtable2 = new Hashtable();
        if (this.c != null) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                x xVar = (x) it.next();
                arrayList.add(xVar.i());
                hashtable.put(xVar.i(), xVar.p());
                int d = xVar.d();
                if (d == 4 || d == 7) {
                    arrayList2.add(xVar.i());
                    hashtable2.put(xVar.i(), xVar.m());
                }
            }
        }
        if (arrayList.size() > 0) {
            final Context context2 = context;
            g.a().a(new SkinQueryEnableTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ w f;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        String str2;
                        x xVar;
                        String l;
                        JSONObject jSONObject = new JSONObject(str);
                        JSONObject optJSONObject = jSONObject.optJSONObject("enable");
                        if (optJSONObject != null) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                str2 = (String) it.next();
                                String optString = optJSONObject.optString(str2);
                                if (!((String) hashtable.get(str2)).equals(optString)) {
                                    Iterator it2 = this.f.c.iterator();
                                    while (it2.hasNext()) {
                                        xVar = (x) it2.next();
                                        if (str2.equals(xVar.i())) {
                                            xVar.d(optString);
                                            l = xVar.l();
                                            break;
                                        }
                                    }
                                    l = null;
                                    g.a().a(new SkinManager$2$1(this, str2, optString, l));
                                }
                            }
                        }
                        jSONObject = jSONObject.optJSONObject("latest_version");
                        if (jSONObject != null) {
                            Iterator it3 = arrayList2.iterator();
                            while (it3.hasNext()) {
                                str2 = (String) it3.next();
                                String optString2 = jSONObject.optString(str2);
                                l = (String) hashtable2.get(str2);
                                if (!(l == null || l.equals(optString2))) {
                                    Iterator it4 = this.f.c.iterator();
                                    while (it4.hasNext()) {
                                        xVar = (x) it4.next();
                                        if (str2.equals(xVar.i())) {
                                            xVar.b(7);
                                            xVar.b(optString2);
                                            break;
                                        }
                                    }
                                    xVar = null;
                                    g.a().a(new SkinManager$2$2(this, str2, optString2, xVar));
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        com.qq.reader.common.monitor.debug.c.a(w.a, "doQuerySkinEnableWithNet onConnectionRecieveData: " + e.toString());
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    com.qq.reader.common.monitor.debug.c.a(w.a, "doQuerySkinEnableWithNet onConnectionError: " + exception.toString());
                }
            }, arrayList));
        }
    }

    public ArrayList<x> e() {
        ArrayList<x> arrayList = new ArrayList();
        if (this.c.size() >= 0) {
            arrayList.add(this.d);
        }
        arrayList.addAll(this.c);
        return arrayList;
    }

    public String f() {
        String e;
        IOException e2;
        String str = "";
        try {
            e = ab.e(new File(d()));
            if (e != null) {
                return e;
            }
            try {
                return "0";
            } catch (Exception e3) {
                try {
                    com.qq.reader.common.monitor.debug.c.a(a, "parserSkinList : " + e3.toString());
                    return e;
                } catch (IOException e4) {
                    e2 = e4;
                    com.qq.reader.common.monitor.debug.c.a(a, "ObtionSkinList : " + e2.toString());
                    return e;
                }
            }
        } catch (IOException e5) {
            IOException iOException = e5;
            e = str;
            e2 = iOException;
            com.qq.reader.common.monitor.debug.c.a(a, "ObtionSkinList : " + e2.toString());
            return e;
        }
    }

    public void g() {
        g.a().a(new SkinListNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ w a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    com.qq.reader.common.monitor.debug.c.a(w.a, "onConnectionRecieveData : " + str);
                    if (str != null && str.length() > 0) {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.optInt("code") == 0) {
                            ArrayList arrayList = new ArrayList();
                            String a = this.a.a(jSONObject, arrayList);
                            if (a != null && a.length() > 0 && arrayList.size() >= 0) {
                                this.a.a(this.a.c, arrayList);
                                this.a.g(a);
                                g.a().a(new 1(this));
                            }
                        }
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.a(w.a, "parserSkinList : " + e.toString());
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                com.qq.reader.common.monitor.debug.c.a(w.a, "onConnectionError : " + exception.toString());
                Handler b = this.a.f("skin_all");
                if (b != null) {
                    Message obtainMessage = b.obtainMessage();
                    obtainMessage.what = 10000402;
                    b.sendMessage(obtainMessage);
                }
            }
        }, f()));
    }

    private void a(ArrayList<x> arrayList, ArrayList<x> arrayList2) {
        synchronized (this.c) {
            HashMap hashMap = new HashMap();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                x xVar = (x) it.next();
                hashMap.put(xVar.i(), xVar);
            }
            Collection arrayList3 = new ArrayList();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                xVar = (x) it2.next();
                x xVar2 = (x) hashMap.get(xVar.i());
                if (xVar2 != null) {
                    xVar2.c(xVar.c());
                    xVar2.b(xVar.b());
                    xVar2.c(xVar.e());
                    xVar2.e(xVar.t());
                    xVar2.f(xVar.v());
                    xVar2.d(xVar.p());
                    xVar2.i(xVar.w());
                    if (!(xVar2.m() == null || xVar2.m().equals(xVar.m()) || xVar2.d() != 4)) {
                        xVar2.b(7);
                    }
                    arrayList3.add(xVar2);
                } else {
                    arrayList3.add(xVar);
                }
            }
            this.c.clear();
            this.c.addAll(arrayList3);
        }
    }

    private void g(String str) {
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        File file = new File(d());
        if (file.exists()) {
            file.delete();
        }
        ab.c(file);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file, true);
            try {
                fileOutputStream.write(str.getBytes());
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e4) {
                e2 = e4;
                try {
                    e2.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e32 = e6;
                e32.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e7) {
            e2 = e7;
            fileOutputStream = null;
            e2.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e8) {
            e322 = e8;
            fileOutputStream = null;
            e322.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public String a(JSONObject jSONObject, ArrayList<x> arrayList) {
        arrayList.clear();
        try {
            String optString = jSONObject.optString("list_version");
            JSONArray optJSONArray = jSONObject.optJSONArray("list");
            if (optJSONArray == null) {
                return optString;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                x a = a(optJSONArray.getJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return optString;
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.a(a, "parserSkinList : " + e.toString());
            return "";
        }
    }

    private synchronized y e(x xVar) {
        return a(xVar, false, false, false);
    }

    private synchronized y a(x xVar, boolean z, boolean z2, boolean z3) {
        y yVar;
        if (xVar == null) {
            yVar = null;
        } else {
            String i = xVar.i();
            yVar = (y) this.g.get(i);
            if (yVar == null) {
                yVar = (y) m.c().a(ReaderApplication.getApplicationImp(), xVar);
                this.g.put(i, yVar);
            }
            if (z) {
                try {
                    yVar.a(new aa(z2, z3));
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("SkinManager", e.getMessage());
                }
            } else {
                yVar.a((g) this);
            }
        }
        return yVar;
    }

    public void a(x xVar) {
        b(xVar, false, false, false);
    }

    private void b(x xVar, boolean z, boolean z2, boolean z3) {
        y a = a(xVar, z, z2, z3);
        if (a != null) {
            if (a.i()) {
                a.k();
            }
            a.r();
        }
    }

    public void a(x xVar, Activity activity) {
        if (xVar != null) {
            new a(this, activity, xVar).execute(new Object[0]);
        }
    }

    public void b(x xVar) {
        y e = e(xVar);
        if (e != null && !e.i()) {
            e.u();
        }
    }

    public String c(x xVar) {
        y e = e(xVar);
        if (e != null) {
            return ao.a(e.d(), e.c());
        }
        return "0%";
    }

    public int d(x xVar) {
        y e = e(xVar);
        if (e == null || e.c() == 0) {
            return 0;
        }
        int d = (int) ((100 * e.d()) / e.c());
        if (d > 100) {
            return 100;
        }
        return d;
    }

    public boolean b(x xVar, Activity activity) {
        if (xVar != null) {
            if (xVar.q()) {
                new d(this, activity).execute(new Object[]{xVar.i(), activity.getApplicationContext()});
            } else {
                a(xVar.i(), "皮肤已过期");
            }
        }
        return false;
    }

    public void a(String str, boolean z) {
        if (str != null) {
            Handler f = f(str);
            if (f != null) {
                Message obtainMessage = f.obtainMessage();
                obtainMessage.what = 10000404;
                obtainMessage.obj = str;
                f.sendMessage(obtainMessage);
                return;
            }
            try {
                e(str);
            } catch (Exception e) {
            }
        }
    }

    public void a(String str, String str2) {
        if (str != null) {
            Handler f = f(str);
            if (f != null) {
                Message obtainMessage = f.obtainMessage();
                obtainMessage.what = 10000405;
                obtainMessage.obj = str2;
                f.sendMessage(obtainMessage);
            }
        }
    }

    public void a(l lVar, String str) {
        if (lVar != null) {
            Handler f = f(lVar.i());
            if (f != null) {
                Message obtainMessage = f.obtainMessage();
                obtainMessage.what = 10000407;
                obtainMessage.obj = str;
                f.sendMessage(obtainMessage);
            }
        }
    }

    public void a(l lVar) {
        if (lVar != null) {
            Handler f = f(lVar.i());
            if (f != null) {
                Message obtainMessage = f.obtainMessage();
                obtainMessage.what = 10000406;
                f.sendMessage(obtainMessage);
            }
        }
    }

    public void a(l lVar, Bundle bundle) {
        if (lVar != null) {
            Handler f = f(lVar.i());
            if (f != null) {
                Message obtainMessage = f.obtainMessage();
                obtainMessage.what = 10000408;
                obtainMessage.obj = bundle;
                f.sendMessage(obtainMessage);
            }
        }
    }

    private x a(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("id");
            String optString2 = jSONObject.optString("name");
            String optString3 = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
            String optString4 = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
            String optString5 = jSONObject.optString("size");
            String optString6 = jSONObject.optString("enable");
            String optString7 = jSONObject.optString(MessageKey.MSG_ICON);
            String optString8 = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
            String optString9 = jSONObject.optString("free");
            String optString10 = jSONObject.optString("price");
            String optString11 = jSONObject.optString("color", "");
            String optString12 = jSONObject.optString("plugin_latest_version");
            String optString13 = jSONObject.optString("plugin_all_version");
            if (optString13.length() == 0) {
                optString13 = optString12;
            }
            StringBuilder stringBuilder = new StringBuilder(com.qq.reader.common.c.a.an);
            stringBuilder.append(optString);
            stringBuilder.append("_c");
            stringBuilder.append(".p");
            String stringBuilder2 = stringBuilder.toString();
            StringBuffer stringBuffer = new StringBuffer(com.qq.reader.common.c.a.an);
            stringBuffer.append(optString);
            stringBuffer.append("_m");
            stringBuffer.append(".p");
            String stringBuffer2 = stringBuffer.toString();
            x xVar = new x(optString, Constants.DEFAULT_UIN, optString2, optString3, optString4, optString5, optString7, optString8, optString9, optString10, optString6, optString12, optString13);
            xVar.g(stringBuilder2);
            xVar.h(stringBuffer2);
            xVar.i(optString11);
            return xVar;
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.a(a, "builder Exception: " + e.toString());
            com.qq.reader.common.monitor.debug.c.a(a, "builder json is: " + jSONObject.toString());
            return null;
        }
    }

    protected static boolean d(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean e(String str) {
        String str2 = null;
        Context applicationImp = ReaderApplication.getApplicationImp();
        if (applicationImp == null || str == null) {
            return false;
        }
        boolean z;
        if (Constants.DEFAULT_UIN.equals(str)) {
            if (Constants.DEFAULT_UIN.equals(str)) {
                com.qq.reader.appconfig.a.d.C(applicationImp, Constants.DEFAULT_UIN);
            }
            z = true;
        } else {
            boolean z2;
            String b = b(str);
            if (d(b)) {
                z2 = true;
            } else {
                b = null;
                z2 = false;
            }
            if (b != null) {
                x c = c(str);
                if (!(c == null || c.q())) {
                    z = false;
                    if (str2 != null) {
                        com.qq.reader.appconfig.a.d.C(applicationImp, Constants.DEFAULT_UIN);
                        ao.v(Constants.DEFAULT_UIN);
                    } else {
                        com.qq.reader.appconfig.a.d.C(applicationImp, str);
                    }
                }
            }
            str2 = b;
            z = z2;
            if (str2 != null) {
                com.qq.reader.appconfig.a.d.C(applicationImp, str);
            } else {
                com.qq.reader.appconfig.a.d.C(applicationImp, Constants.DEFAULT_UIN);
                ao.v(Constants.DEFAULT_UIN);
            }
        }
        if (SkinEngine.getInstances().setSkinRootPath(applicationImp, str2, true) && r1) {
            return true;
        }
        return false;
    }

    public static void h() {
        g.a().a(new SkinManager$4());
    }
}
