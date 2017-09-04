package com.qq.reader.module.feed.b;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FeedOperationModelStyle8 */
public class o extends a {
    private ArrayList<r> c;
    private String d;
    private ArrayList<String> e;
    private int f = 0;
    private int g = 0;

    public a a(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        this.a = jSONObject.optInt("uistyle");
        this.b = jSONObject.optString("positionId");
        if (this.a != 8) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(MessageKey.MSG_CONTENT);
        if (optJSONObject == null) {
            return null;
        }
        a oVar = new o();
        oVar.d = optJSONObject.optString("qurl");
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
            oVar.c = arrayList;
        }
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("bids");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            ArrayList arrayList2 = new ArrayList();
            while (i < optJSONArray2.length()) {
                arrayList2.add(optJSONArray2.optString(i));
                i++;
            }
            oVar.e = arrayList2;
        }
        oVar.a = this.a;
        oVar.b = this.b;
        return oVar;
    }

    public ArrayList<r> a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public ArrayList<String> c() {
        return this.e;
    }

    public a a(a aVar) {
        int i = 0;
        if (aVar instanceof o) {
            int d;
            int i2;
            aVar = (o) aVar;
            ArrayList a = aVar.a();
            if (a != null) {
                d = aVar.d();
                int size = a.size();
                if (size > 1) {
                    i2 = d + 1;
                    if (i2 == size) {
                        i2 = 0;
                    }
                    aVar.a(i2);
                }
            }
            a = aVar.c();
            if (a != null) {
                d = a.size();
                i2 = aVar.e();
                if (d > 1) {
                    i2++;
                    if (i2 != d) {
                        i = i2;
                    }
                    aVar.b(i);
                }
            }
        }
        return aVar;
    }

    public int d() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public int e() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }
}
