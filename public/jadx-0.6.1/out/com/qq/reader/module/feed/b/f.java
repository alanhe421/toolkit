package com.qq.reader.module.feed.b;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FeedOperationModelStyle11 */
public class f extends a {
    private ArrayList<r> c;
    private String d;
    private ArrayList<String> e;
    private int f = 0;
    private int g = 0;
    private int h = 1;
    private int i = 2;

    public a a(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        this.a = jSONObject.optInt("uistyle");
        this.b = jSONObject.optString("positionId");
        if (this.a != 11) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(MessageKey.MSG_CONTENT);
        if (optJSONObject == null) {
            return null;
        }
        a fVar = new f();
        fVar.d = optJSONObject.optString("qurl");
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
            fVar.c = arrayList;
        }
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("bids");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            ArrayList arrayList2 = new ArrayList();
            while (i < optJSONArray2.length()) {
                arrayList2.add(optJSONArray2.optString(i));
                i++;
            }
            fVar.e = arrayList2;
        }
        fVar.a = this.a;
        fVar.b = this.b;
        return fVar;
    }

    public a a(a aVar) {
        int i = 0;
        if (aVar instanceof f) {
            int size;
            aVar = (f) aVar;
            int d = aVar.d();
            ArrayList a = aVar.a();
            if (a != null) {
                size = a.size();
                if (size > 1) {
                    d++;
                    if (d == size) {
                        d = 0;
                    }
                    aVar.a(d);
                }
            }
            ArrayList c = aVar.c();
            if (c != null) {
                int size2 = c.size();
                if (size2 >= 3) {
                    d = aVar.g;
                    int i2 = aVar.h;
                    int i3 = aVar.i;
                    if (size2 == 3) {
                        size = b(d);
                        d = b(i2);
                        i = b(i3);
                    } else {
                        i2 = i3 + 1;
                        if (i2 == size2) {
                            i2 = 0;
                        }
                        size = i2 + 1;
                        if (size == size2) {
                            size = 0;
                        }
                        d = size + 1;
                        if (d == size2) {
                            d = size;
                            size = i2;
                        } else {
                            i = d;
                            d = size;
                            size = i2;
                        }
                    }
                    aVar.g = size;
                    aVar.h = d;
                    aVar.i = i;
                }
            }
        }
        return aVar;
    }

    private int b(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        return 0;
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

    public int d() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public int e() {
        return this.g;
    }

    public int f() {
        return this.h;
    }

    public int g() {
        return this.i;
    }
}
