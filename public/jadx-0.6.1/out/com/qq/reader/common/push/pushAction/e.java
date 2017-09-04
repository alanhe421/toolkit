package com.qq.reader.common.push.pushAction;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.d;
import android.util.SparseArray;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.push.a;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.f;
import com.qq.reader.module.bookchapter.online.g;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveException;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ContentUpdateAction */
public class e extends i {
    public e(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if ((a.a.equals(this.b) || a.b.equals(this.b)) && jSONObject != null) {
            Serializable hashMap = new HashMap();
            try {
                int i;
                String optString;
                long optLong;
                long optLong2;
                JSONArray optJSONArray = jSONObject.optJSONArray("clear");
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    if (length > 0) {
                        for (i = 0; i < length; i++) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            optString = jSONObject2.optString("bid");
                            optLong = jSONObject2.optLong("lasttime", 0);
                            optLong2 = jSONObject2.optLong("lastcid", 0);
                            OnlineTag a = v.b().a(optString);
                            if (a != null && a.H() < optLong) {
                                a.d(optLong);
                                if (a.F() == 3) {
                                    com.qq.reader.module.comic.e.e.a(Long.parseLong(optString), true);
                                } else {
                                    a(a, hashMap, optLong2);
                                    v.b().b(optString);
                                }
                            }
                        }
                    }
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("del");
                if (optJSONArray2 != null) {
                    int length2 = optJSONArray2.length();
                    if (length2 > 0) {
                        for (i = 0; i < length2; i++) {
                            JSONObject jSONObject3 = optJSONArray2.getJSONObject(i);
                            optString = jSONObject3.optString("bid");
                            optLong = jSONObject3.optLong("lasttime", 0);
                            optLong2 = jSONObject3.optLong("lastcid", 0);
                            OnlineTag a2 = v.b().a(optString);
                            JSONArray optJSONArray3 = jSONObject3.optJSONArray("cids");
                            if (a2 != null && a2.H() < optLong) {
                                a2.d(optLong);
                                List arrayList = new ArrayList();
                                if (optJSONArray3 != null) {
                                    int length3 = optJSONArray3.length();
                                    if (length3 > 0) {
                                        for (int i2 = 0; i2 < length3; i2++) {
                                            arrayList.add(optJSONArray3.getString(i2));
                                        }
                                    }
                                }
                                if (a2.F() == 3) {
                                    com.qq.reader.module.comic.e.e.a(optString, arrayList);
                                } else {
                                    a(a2, hashMap, optLong2);
                                    v.b().a(optString, arrayList);
                                }
                            }
                        }
                    }
                }
                if (hashMap.size() > 0) {
                    Intent intent = new Intent();
                    intent.setAction(com.qq.reader.common.c.a.cf);
                    intent.putExtra("errorbookmap", hashMap);
                    d.a(a()).a(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(OnlineTag onlineTag, Map<String, Long> map, long j) throws IOException, ArchiveException {
        long intValue;
        Object obj;
        i.a("event_chapter_content_update", null, ReaderApplication.getApplicationImp());
        g gVar = new g(a(), onlineTag);
        f e = gVar.e();
        e.b();
        List e2 = e.e();
        int g = onlineTag.g();
        long j2 = (long) g;
        if (e2 != null && e2.size() > 0 && g <= e2.size() && g > 0) {
            int uuid = (int) ((OnlineChapter) e2.get(g - 1)).getUUID();
            gVar.a();
            e.b();
            SparseArray c = e.y().c();
            if (c != null) {
                Object obj2 = c.get(uuid) != null ? 1 : null;
                if (obj2 != null) {
                    intValue = (long) ((Integer) c.get(uuid)).intValue();
                    obj = obj2;
                } else {
                    intValue = j2;
                    obj = obj2;
                }
                if (obj != null) {
                    intValue = Math.min(intValue, j);
                } else if (intValue > j) {
                    intValue = j;
                }
                if (intValue != ((long) g)) {
                    map.put(onlineTag.k(), Long.valueOf(intValue));
                    onlineTag.c((int) intValue);
                }
                onlineTag.d((int) j);
                v.b().b(onlineTag);
            }
        }
        long j3 = j2;
        obj = null;
        intValue = j3;
        if (obj != null) {
            intValue = Math.min(intValue, j);
        } else if (intValue > j) {
            intValue = j;
        }
        if (intValue != ((long) g)) {
            map.put(onlineTag.k(), Long.valueOf(intValue));
            onlineTag.c((int) intValue);
        }
        onlineTag.d((int) j);
        v.b().b(onlineTag);
    }
}
