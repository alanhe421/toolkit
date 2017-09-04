package com.qq.reader.module.feed.b;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FeedOperationModelStyle2 */
public class i extends a {
    private ArrayList<r> c;
    private String d;
    private String e;
    private ArrayList<String> f;
    private int g = 0;
    private int h = 0;

    public a a(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        this.a = jSONObject.optInt("uistyle");
        this.b = jSONObject.optString("positionId");
        if (this.a != 2) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(MessageKey.MSG_CONTENT);
        if (optJSONObject == null) {
            return null;
        }
        a iVar = new i();
        iVar.d = optJSONObject.optString("qurl");
        iVar.e = optJSONObject.optString(MessageKey.MSG_DATE);
        JSONArray optJSONArray = optJSONObject.optJSONArray("info");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                r rVar = new r();
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                rVar.d = optJSONObject2.optString("title");
                rVar.e = optJSONObject2.optString(SocialConstants.PARAM_APP_DESC);
                arrayList.add(rVar);
            }
            iVar.c = arrayList;
        }
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("bids");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            ArrayList arrayList2 = new ArrayList();
            while (i < optJSONArray2.length()) {
                arrayList2.add(optJSONArray2.optString(i));
                i++;
            }
            iVar.f = arrayList2;
        }
        iVar.a = this.a;
        iVar.b = this.b;
        return iVar;
    }

    public ArrayList<r> a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public ArrayList<String> d() {
        return this.f;
    }

    public a a(a aVar) {
        int i = 0;
        if (aVar instanceof i) {
            int size;
            aVar = (i) aVar;
            int e = aVar.e();
            ArrayList a = aVar.a();
            if (a != null) {
                size = a.size();
                if (size > 1) {
                    e++;
                    if (e == size) {
                        e = 0;
                    }
                    aVar.a(e);
                }
            }
            ArrayList d = aVar.d();
            if (d != null) {
                size = d.size();
                e = aVar.f();
                if (size > 1) {
                    e++;
                    if (e != size) {
                        i = e;
                    }
                    aVar.b(i);
                }
            }
        }
        return aVar;
    }

    public int e() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public int f() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }
}
