package com.qq.reader.module.feed.b;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FeedOperationModelStyle5 */
public class l extends a {
    private ArrayList<q> c;
    private String d;
    private int e;

    public a a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        this.a = jSONObject.optInt("uistyle");
        this.b = jSONObject.optString("positionId");
        if (this.a != 5) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(MessageKey.MSG_CONTENT);
        if (optJSONObject == null) {
            return null;
        }
        a lVar = new l();
        lVar.d = optJSONObject.optString("qurl");
        JSONArray optJSONArray = optJSONObject.optJSONArray("info");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                q qVar = new q();
                qVar.d = optJSONObject2.optString("title");
                qVar.e = optJSONObject2.optString(SocialConstants.PARAM_APP_DESC);
                qVar.c = optJSONObject2.optString("image");
                arrayList.add(qVar);
            }
            lVar.c = arrayList;
        }
        lVar.a = this.a;
        lVar.b = this.b;
        return lVar;
    }

    public ArrayList<q> a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public a a(a aVar) {
        if (!(aVar instanceof l)) {
            return aVar;
        }
        a aVar2 = (l) aVar;
        int c = aVar2.c();
        ArrayList a = aVar2.a();
        if (a == null) {
            return aVar;
        }
        int size = a.size();
        if (size <= 1) {
            return aVar;
        }
        c++;
        if (c == size) {
            c = 0;
        }
        aVar2.a(c);
        return aVar2;
    }

    public int c() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }
}
