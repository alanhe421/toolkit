package com.qq.reader.module.redpacket.square.data;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.RedPacketSquareTask;
import com.qq.reader.module.redpacket.model.RedPacket;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.util.WeakReferenceHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RedPacketSquareDataManager */
public class d extends com.qq.reader.appconfig.a.b {
    private static WeakReferenceHandler b;
    public HashMap<Long, RedPacket> a;
    private long c;
    private long d;

    /* compiled from: RedPacketSquareDataManager */
    public static class a {
        public static d a = new d();
    }

    /* compiled from: RedPacketSquareDataManager */
    public static class b implements Comparator<RedPacket> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((RedPacket) obj, (RedPacket) obj2);
        }

        public int a(RedPacket redPacket, RedPacket redPacket2) {
            long d = redPacket.d();
            long d2 = redPacket2.d();
            if (redPacket.k() < redPacket2.k() || d < d2) {
                return 1;
            }
            if (d > d2) {
                return -1;
            }
            return 0;
        }

        public boolean equals(Object obj) {
            return false;
        }
    }

    private d() {
        this.a = new HashMap();
        this.c = -1;
        this.d = -1;
    }

    public static d b() {
        return a.a;
    }

    public void a(WeakReferenceHandler weakReferenceHandler) {
        b = weakReferenceHandler;
    }

    public void a() {
        if (this.a != null) {
            this.a.clear();
        }
        this.c = -1;
        this.d = -1;
    }

    public void a(com.qq.reader.module.redpacket.square.b.a aVar) {
        if (aVar != null) {
            ArrayList a = aVar.a();
            int b = aVar.b();
            int c = aVar.c();
            com.qq.reader.module.redpacket.square.b.a aVar2 = new com.qq.reader.module.redpacket.square.b.a();
            Collection g = g();
            Object bM = com.qq.reader.appconfig.a.d.bM(ReaderApplication.getApplicationImp().getApplicationContext());
            Message obtain;
            if ((a == null || a.size() == 0) && (g == null || g.isEmpty())) {
                obtain = Message.obtain();
                obtain.what = 12345013;
                obtain.arg1 = b;
                b.sendMessage(obtain);
                return;
            }
            boolean z;
            if (a == null || a.size() <= 0) {
                z = false;
            } else {
                Iterator it = a.iterator();
                z = false;
                while (it.hasNext()) {
                    boolean z2;
                    RedPacket redPacket = (RedPacket) it.next();
                    this.a.put(Long.valueOf(redPacket.d()), redPacket);
                    if (z || redPacket == null || redPacket.v() != 1) {
                        z2 = z;
                    } else {
                        z2 = true;
                    }
                    z = z2;
                }
            }
            aVar2.a(z);
            ArrayList f = f();
            if (!TextUtils.isEmpty(bM)) {
                try {
                    JSONObject jSONObject = new JSONObject(bM);
                    if (jSONObject != null) {
                        f.add(0, com.qq.reader.module.redpacket.b.b.a(jSONObject));
                    }
                } catch (JSONException e) {
                }
            }
            if (!(g == null || g.isEmpty())) {
                f.addAll(0, g);
            }
            aVar2.a(f);
            aVar2.a(b);
            aVar2.b(c);
            obtain = Message.obtain();
            obtain.what = 8000001;
            obtain.obj = aVar2;
            b.sendMessage(obtain);
            g.a().a(new RedPacketSquareDataManager$1(this, a));
        }
    }

    private ArrayList<RedPacket> f() {
        List arrayList = new ArrayList();
        if (this.a == null || this.a.size() == 0) {
            return arrayList;
        }
        Set h = h();
        for (Entry value : this.a.entrySet()) {
            RedPacket redPacket = (RedPacket) value.getValue();
            if (h == null || !h.contains(Long.valueOf(redPacket.d()))) {
                long d = redPacket.d();
                if (d > this.c) {
                    this.c = d;
                }
                if (this.d == -1) {
                    this.d = d;
                } else if (d < this.d) {
                    this.d = d;
                }
                if (redPacket.v() == 1) {
                    arrayList.add(redPacket);
                }
            }
        }
        if (arrayList.size() > 0) {
            Collections.sort(arrayList, new b());
        }
        return arrayList;
    }

    private List<RedPacket> g() {
        Object bL = com.qq.reader.appconfig.a.d.bL(ReaderApplication.getApplicationImp().getApplicationContext());
        if (!TextUtils.isEmpty(bL)) {
            try {
                JSONArray jSONArray = new JSONArray(bL);
                if (jSONArray.length() > 0) {
                    return com.qq.reader.module.redpacket.b.b.a(jSONArray, 1);
                }
            } catch (JSONException e) {
            }
        }
        return null;
    }

    private Set<Long> h() {
        List g = g();
        if (g == null || g.isEmpty()) {
            return null;
        }
        Set<Long> hashSet = new HashSet();
        for (int i = 0; i < g.size(); i++) {
            RedPacket redPacket = (RedPacket) g.get(i);
            if (!(redPacket == null || redPacket.d() == 0)) {
                hashSet.add(Long.valueOf(redPacket.d()));
            }
        }
        return hashSet;
    }

    private void a(long j) {
        this.c = -1;
        this.d = -1;
        Iterator it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            if (((Long) ((Entry) it.next()).getKey()).longValue() <= j) {
                it.remove();
            }
        }
    }

    public void a(int i, long j) {
        if (i == 1) {
            b(i, j);
            return;
        }
        if (i == 2) {
            g.a().a(new RedPacketSquareDataManager$2(this));
        }
        c(i, j);
    }

    private void c(final int i, final long j) {
        ReaderTask redPacketSquareLoadDiskDataTask = new RedPacketSquareLoadDiskDataTask(i, j);
        redPacketSquareLoadDiskDataTask.setLoadListener(new com.qq.reader.module.bookstore.qnative.storage.disk.a(this) {
            final /* synthetic */ d c;

            public void onLoadSucess(Object obj) {
                ArrayList arrayList;
                if (i == 0) {
                    arrayList = (ArrayList) obj;
                    if (arrayList == null || arrayList.size() < 20) {
                        this.c.b(i, j);
                    } else {
                        com.qq.reader.module.redpacket.square.b.a aVar = new com.qq.reader.module.redpacket.square.b.a();
                        aVar.a(arrayList);
                        aVar.a(i);
                        this.c.a(aVar);
                    }
                    g.a().a(new RedPacketSquareDataManager$3$1(this));
                    return;
                }
                arrayList = (ArrayList) obj;
                if (arrayList == null || arrayList.size() < 1) {
                    this.c.b(1, j);
                    return;
                }
                aVar = new com.qq.reader.module.redpacket.square.b.a();
                aVar.a(arrayList);
                aVar.a(i);
                this.c.a(aVar);
                this.c.b(1, j);
            }

            public void onLoadFailed(Object obj) {
            }
        });
        g.a().a(redPacketSquareLoadDiskDataTask);
    }

    private synchronized void i() {
        if (b != null) {
            Message obtain = Message.obtain();
            obtain.what = 8000002;
            b.sendMessage(obtain);
        }
    }

    public void b(final int i, final long j) {
        g.a().a(new RedPacketSquareTask(new c(this) {
            final /* synthetic */ d c;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                boolean z = false;
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject != null && jSONObject.optInt("code", -1) == 0) {
                            Object optString;
                            JSONArray optJSONArray;
                            ArrayList arrayList;
                            int i;
                            HashMap hashMap = new HashMap();
                            JSONObject optJSONObject = jSONObject.optJSONObject("bookrank");
                            if (optJSONObject != null) {
                                optString = optJSONObject.optString("qurl");
                                if (!TextUtils.isEmpty(optString)) {
                                    com.qq.reader.appconfig.a.d.H(ReaderApplication.getApplicationImp().getApplicationContext(), optString);
                                }
                                optJSONArray = optJSONObject.optJSONArray("item");
                                if (optJSONArray != null && optJSONArray.length() > 0) {
                                    arrayList = new ArrayList();
                                    for (i = 0; i < optJSONArray.length(); i++) {
                                        arrayList.add(optJSONArray.optJSONObject(i).optString(MessageKey.MSG_ICON));
                                    }
                                    if (arrayList.size() > 0) {
                                        hashMap.put("bookrank", arrayList);
                                        com.qq.reader.appconfig.a.d.F(ReaderApplication.getApplicationImp().getApplicationContext(), optJSONArray.toString());
                                    }
                                }
                            }
                            optJSONObject = jSONObject.optJSONObject("userrank");
                            if (optJSONObject != null) {
                                optString = optJSONObject.optString("qurl");
                                if (!TextUtils.isEmpty(optString)) {
                                    com.qq.reader.appconfig.a.d.I(ReaderApplication.getApplicationImp().getApplicationContext(), optString);
                                }
                                optJSONArray = optJSONObject.optJSONArray("item");
                                if (optJSONArray != null && optJSONArray.length() > 0) {
                                    arrayList = new ArrayList();
                                    for (i = 0; i < optJSONArray.length(); i++) {
                                        arrayList.add(optJSONArray.optJSONObject(i).optString(MessageKey.MSG_ICON));
                                    }
                                    if (arrayList.size() > 0) {
                                        hashMap.put("userrank", arrayList);
                                        com.qq.reader.appconfig.a.d.G(ReaderApplication.getApplicationImp().getApplicationContext(), optJSONArray.toString());
                                    }
                                }
                            }
                            Message obtain = Message.obtain();
                            obtain.what = 12345012;
                            obtain.obj = hashMap;
                            obtain.arg1 = i;
                            d.b.sendMessage(obtain);
                            this.c.a(jSONObject.optJSONArray("ads"));
                            JSONArray optJSONArray2 = jSONObject.optJSONArray("top");
                            if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                                com.qq.reader.appconfig.a.d.D(ReaderApplication.getApplicationImp().getApplicationContext(), "");
                            } else {
                                com.qq.reader.appconfig.a.d.D(ReaderApplication.getApplicationImp().getApplicationContext(), optJSONArray2.toString());
                            }
                            JSONArray optJSONArray3 = jSONObject.optJSONArray("list");
                            ArrayList arrayList2 = new ArrayList();
                            if (optJSONArray3 != null && optJSONArray3.length() > 0) {
                                ArrayList a = com.qq.reader.module.redpacket.b.b.a(optJSONArray3, 0);
                                if (i == 1) {
                                    int i2 = 0;
                                    while (i2 < a.size()) {
                                        boolean z2;
                                        if (((RedPacket) a.get(i2)).d() == j) {
                                            z2 = true;
                                        } else {
                                            z2 = z;
                                        }
                                        i2++;
                                        z = z2;
                                    }
                                    com.qq.reader.common.monitor.debug.c.e("redpacket", "ishasRepeatid : " + z);
                                    if (z) {
                                        arrayList2 = a;
                                    } else {
                                        g.a().a(new RedPacketSquareDataManager$4$1(this));
                                        if (this.c.a != null && this.c.a.size() > 0) {
                                            this.c.a(j);
                                        }
                                    }
                                }
                                arrayList2 = a;
                            }
                            int optInt = jSONObject.optInt("hasnext");
                            com.qq.reader.module.redpacket.square.b.a aVar = new com.qq.reader.module.redpacket.square.b.a();
                            aVar.a(arrayList2);
                            aVar.a(i);
                            if (i != 1 || j == -1) {
                                aVar.b(optInt);
                            } else {
                                aVar.b(-1);
                            }
                            this.c.a(aVar);
                        }
                    } catch (Exception e) {
                        this.c.i();
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.c.i();
            }
        }, i, j));
    }

    private void a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            com.qq.reader.appconfig.a.d.E(ReaderApplication.getApplicationImp().getApplicationContext(), "");
            return;
        }
        JSONObject optJSONObject = jSONArray.optJSONObject(0);
        if (optJSONObject != null) {
            com.qq.reader.appconfig.a.d.E(ReaderApplication.getApplicationImp().getApplicationContext(), optJSONObject.toString());
        }
    }

    private void j() {
        int i = 0;
        Object bN = com.qq.reader.appconfig.a.d.bN(k());
        Object bO = com.qq.reader.appconfig.a.d.bO(k());
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(bN)) {
            try {
                JSONArray jSONArray = new JSONArray(bN);
                if (jSONArray != null && jSONArray.length() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        arrayList.add(jSONArray.optJSONObject(i2).optString(MessageKey.MSG_ICON));
                    }
                    if (arrayList.size() > 0) {
                        hashMap.put("bookrank", arrayList);
                    }
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(bO)) {
            try {
                JSONArray jSONArray2 = new JSONArray(bO);
                if (jSONArray2 != null && jSONArray2.length() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    while (i < jSONArray2.length()) {
                        arrayList2.add(jSONArray2.optJSONObject(i).optString(MessageKey.MSG_ICON));
                        i++;
                    }
                    if (arrayList2.size() > 0) {
                        hashMap.put("userrank", arrayList2);
                    }
                }
            } catch (Exception e2) {
            }
        }
        Message obtain = Message.obtain();
        obtain.what = 12345012;
        obtain.obj = hashMap;
        obtain.arg1 = 2;
        b.sendMessage(obtain);
    }

    private Context k() {
        return ReaderApplication.getApplicationImp().getApplicationContext();
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }
}
