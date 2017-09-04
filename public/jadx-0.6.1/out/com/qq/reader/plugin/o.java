package com.qq.reader.plugin;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderNetTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.PluginNetListTask;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PluginListUpdateHandler */
public class o {
    private a a;
    private transient boolean b = false;

    /* compiled from: PluginListUpdateHandler */
    public interface a {
        void a();

        void a(Exception exception);

        void b();
    }

    public o(a aVar) {
        this.a = aVar;
    }

    public void a() {
        g.a().a(new PluginNetListTask(new c(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.a.a(readerProtocolTask, str);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (this.a.a != null) {
                    this.a.a.a(exception);
                }
            }
        }));
    }

    public void b() {
        this.b = true;
    }

    private void a(ReaderNetTask readerNetTask, String str) {
        try {
            if (!this.b) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt("code") == 0) {
                    int i;
                    JSONObject jSONObject2;
                    String string;
                    l lVar;
                    String string2 = jSONObject.getString("pluginseries");
                    if (string2 != null && string2.length() > 0) {
                        d.f(ReaderApplication.getApplicationImp(), string2);
                        d.e(ReaderApplication.getApplicationImp(), string2);
                    }
                    JSONArray jSONArray = jSONObject.getJSONArray("categorylist");
                    ArrayList arrayList = new ArrayList();
                    for (i = 0; i < jSONArray.length(); i++) {
                        jSONObject2 = (JSONObject) jSONArray.get(i);
                        ArrayList arrayList2 = arrayList;
                        arrayList2.add(new l(jSONObject2.getString("cid"), "", jSONObject2.getString("cname"), "", jSONObject2.getString("cdesc"), "", jSONObject2.getString(MessageKey.MSG_ICON), "", "0", "", "1", "", ""));
                    }
                    JSONArray jSONArray2 = jSONObject.getJSONArray("pluginlist");
                    for (i = 0; i < jSONArray2.length(); i++) {
                        jSONObject2 = (JSONObject) jSONArray2.get(i);
                        String string3 = jSONObject2.getString("id");
                        String string4 = jSONObject2.getString(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
                        string = jSONObject2.getString("name");
                        String string5 = jSONObject2.getString(SocialConstants.PARAM_APP_DESC);
                        String string6 = jSONObject2.getString("size");
                        String string7 = jSONObject2.getString("enable");
                        String string8 = jSONObject2.getString("cid");
                        String string9 = jSONObject2.getString(MessageKey.MSG_ICON);
                        String string10 = jSONObject2.getString("free");
                        String string11 = jSONObject2.getString("price");
                        String string12 = jSONObject2.getString(SocialConstants.PARAM_IMG_URL);
                        String string13 = jSONObject2.getString("plugin_latest_version");
                        String string14 = jSONObject2.getString("plugin_all_version");
                        int i2 = jSONObject2.getInt("purchased");
                        lVar = new l(string3, string8, string, string4, string5, string6, string9, string12, string10, string11, string7, string13, string14);
                        lVar.a(i2);
                        arrayList.add(lVar);
                    }
                    if (!this.b) {
                        l lVar2;
                        ArrayList e = k.b().e();
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            lVar = (l) it.next();
                            if (!lVar.k().equals("")) {
                                Iterator it2 = e.iterator();
                                while (it2.hasNext()) {
                                    lVar2 = (l) it2.next();
                                    if (lVar.i().equals(lVar2.i())) {
                                        lVar.a(lVar2.f());
                                        lVar.b(lVar2.d());
                                        lVar.c(lVar2.e());
                                        lVar.a(lVar2.m());
                                        if (lVar.b() == null || lVar.b().trim().length() == 0) {
                                            lVar.b(lVar2.b());
                                        }
                                        if (lVar.c() == null || lVar.c().trim().length() == 0) {
                                            lVar.c(lVar2.c());
                                        }
                                    }
                                }
                            }
                        }
                        lVar2 = null;
                        string = d.o(ReaderApplication.getApplicationImp());
                        Iterator it3 = e.iterator();
                        while (it3.hasNext()) {
                            lVar = (l) it3.next();
                            if (lVar2 != null || !string.equals(lVar.i())) {
                                lVar = lVar2;
                            }
                            lVar2 = lVar;
                        }
                        if (lVar2 != null) {
                            Object obj;
                            it = arrayList.iterator();
                            while (it.hasNext()) {
                                lVar = (l) it.next();
                                if (!lVar.k().equals("") && lVar.i().equals(lVar2.i())) {
                                    obj = 1;
                                    break;
                                }
                            }
                            obj = null;
                            if (obj == null) {
                                arrayList.add(lVar2);
                            }
                        }
                        k b = k.b();
                        b.c();
                        b.a(arrayList);
                        if (this.a != null) {
                            this.a.a();
                        }
                    } else if (this.a != null) {
                        this.a.b();
                    }
                }
            } else if (this.a != null) {
                this.a.b();
            }
        } catch (Exception e2) {
            f.a("PlugInListActivity", "onConnectionRecieveData " + e2.toString());
            if (this.a != null) {
                this.a.a(e2);
            }
        }
    }
}
