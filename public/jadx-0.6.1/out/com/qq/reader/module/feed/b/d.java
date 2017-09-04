package com.qq.reader.module.feed.b;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FeedOperationModelStyle1 */
public class d extends a {
    private ArrayList<b> c;
    private int d = 0;

    public a a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        this.a = jSONObject.optInt("uistyle");
        this.b = jSONObject.optString("positionId");
        if (this.a != 1) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(MessageKey.MSG_CONTENT);
        if (optJSONArray == null || optJSONArray.length() < 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                b bVar = new b();
                bVar.d = optJSONObject.optString("title");
                bVar.e = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
                bVar.a = optJSONObject.optString("qurl");
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("bids");
                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        arrayList2.add(optJSONArray2.optString(i2));
                    }
                    bVar.b = arrayList2;
                }
                arrayList.add(bVar);
            }
        }
        a dVar = new d();
        dVar.c = arrayList;
        dVar.a = this.a;
        dVar.b = this.b;
        return dVar;
    }

    public ArrayList<b> a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public a a(a aVar) {
        if (!(aVar instanceof d)) {
            return aVar;
        }
        a aVar2 = (d) aVar;
        int b = aVar2.b();
        ArrayList a = aVar2.a();
        if (a == null || a.size() == 0) {
            return aVar;
        }
        int size = a.size();
        if (size <= 1) {
            return aVar;
        }
        b++;
        if (b == size) {
            b = 0;
        }
        aVar2.a(b);
        return aVar2;
    }
}
