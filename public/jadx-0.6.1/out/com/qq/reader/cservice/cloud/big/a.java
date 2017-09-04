package com.qq.reader.cservice.cloud.big;

import android.content.Context;
import com.qq.reader.common.db.handle.e;
import com.qq.reader.common.db.handle.m;
import com.qq.reader.cservice.cloud.g;
import com.qq.reader.cservice.cloud.h;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CloudGetListHandleBig */
public class a {
    private static ArrayList<g> a = new ArrayList();

    public static ArrayList<g> a(Context context, long j) {
        h.b(j);
        m.b().a(a);
        ArrayList<g> arrayList = new ArrayList();
        for (int size = a.size() - 1; size >= 0; size--) {
            arrayList.add(a.get(size));
        }
        a();
        return arrayList;
    }

    public static ArrayList<g> a(Context context) {
        ArrayList<g> arrayList = new ArrayList();
        for (int size = a.size() - 1; size >= 0; size--) {
            arrayList.add(a.get(size));
        }
        return arrayList;
    }

    public static void a() {
        a.clear();
    }

    public static void a(Context context, long j, JSONArray jSONArray) {
        try {
            int length = jSONArray.length();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    long optLong = jSONObject.optLong("bookid");
                    String optString = jSONObject.optString("title");
                    if (optString.length() != 0) {
                        String optString2 = jSONObject.optString("format");
                        String optString3 = jSONObject.optString("author");
                        String optString4 = jSONObject.optString("coverurl");
                        int optInt = jSONObject.optInt("offset");
                        long optLong2 = jSONObject.optLong("chapterid");
                        int optInt2 = jSONObject.optInt("maxchapter");
                        String optString5 = jSONObject.optString("chaptertitle");
                        int optInt3 = jSONObject.optInt("isfinished");
                        int optInt4 = jSONObject.optInt("downloadable");
                        int optInt5 = jSONObject.optInt("drm");
                        int optInt6 = jSONObject.optInt("type");
                        long optLong3 = jSONObject.optLong("lastuploadtime");
                        String optString6 = jSONObject.optString("lastcname");
                        jSONObject.optString(s.ORIGIN);
                        String optString7 = jSONObject.optString("bookfrom");
                        String optString8 = jSONObject.optString("downloadinfo");
                        String optString9 = jSONObject.optString("comicPicture");
                        int optInt7 = jSONObject.optInt("bookType");
                        e.a().a(String.valueOf(optLong), optString7);
                        g gVar = new g(optLong, 0, optInt7);
                        gVar.a(optString8);
                        gVar.a(optString, optString2);
                        gVar.d(optString);
                        gVar.h(optString2);
                        gVar.e(optString3);
                        gVar.g(optString4);
                        gVar.d(optLong2);
                        gVar.a(optInt);
                        gVar.d(optInt2);
                        gVar.i(optString5);
                        gVar.f(optInt3);
                        gVar.c(optInt4);
                        gVar.b(optInt5);
                        gVar.e(optInt6);
                        gVar.a(optLong3);
                        gVar.b(optString6);
                        gVar.j(optString9);
                        a.add(gVar);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
