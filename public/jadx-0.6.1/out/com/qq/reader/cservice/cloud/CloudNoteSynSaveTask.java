package com.qq.reader.cservice.cloud;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.l;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.framework.a.b;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudNoteSynSaveTask extends ReaderProtocolJSONTask {
    private String jsonString;

    public CloudNoteSynSaveTask(c cVar, List<b> list) {
        super(cVar);
        if (com.qq.reader.common.login.c.b()) {
            this.mUrl = e.bk;
            this.jsonString = getUpListString(list);
        }
    }

    public String getRequestContent() {
        return this.jsonString;
    }

    private String getUpListString(List<b> list) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (b bVar : list) {
                List<com.qq.reader.readengine.model.b> e = bVar.e();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("bookId", bVar.a());
                jSONObject.put("num", bVar.b());
                jSONObject.put("type", bVar.c());
                jSONObject.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, bVar.d());
                jSONObject.put("bookname", bVar.f());
                jSONObject.put("usersid", bVar.h());
                if (bVar.b() != 0) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (com.qq.reader.readengine.model.b bVar2 : e) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("startChapter", bVar2.m());
                        jSONObject2.put("startOffset", bVar2.n());
                        jSONObject2.put("endChapter", bVar2.o());
                        jSONObject2.put("endOffset", bVar2.p());
                        jSONObject2.put(MessageKey.MSG_CONTENT, bVar2.e());
                        jSONObject2.put("selectedText", bVar2.d());
                        jSONObject2.put("noteTime", bVar2.i());
                        jSONObject2.put("noteType", bVar2.r());
                        jSONArray2.put(jSONObject2);
                    }
                    jSONObject.put("notes", jSONArray2);
                } else {
                    jSONObject.put("notes", null);
                }
                jSONArray.put(jSONObject);
            }
            return jSONArray.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static d parseNoteSaveReceiveData(String str) {
        JSONObject jSONObject = new JSONObject(str);
        int i = jSONObject.getInt("ret");
        if (i >= 0) {
            long j = jSONObject.getLong("uin");
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("noteInfos");
            if (jSONArray == null || jSONArray.length() <= 0) {
                return null;
            }
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                String bookShortName;
                JSONArray jSONArray2;
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                b bVar = new b();
                long j2 = jSONObject2.getLong("bookId");
                bVar.a(j2);
                bVar.c(jSONObject2.getInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION));
                bVar.a(jSONObject2.getInt("num"));
                Mark e = i.c().e(j2 + "");
                if (e != null) {
                    bookShortName = e.getBookShortName();
                } else {
                    g b = l.b().b(j2);
                    if (b != null) {
                        bookShortName = b.v();
                    } else {
                        bookShortName = "";
                    }
                }
                bVar.a(bookShortName);
                bVar.b(0);
                bVar.b(j + "");
                bVar.d(jSONObject2.getInt("ret"));
                List arrayList2 = new ArrayList();
                try {
                    jSONArray2 = jSONObject2.getJSONArray("notes");
                } catch (JSONException e2) {
                    jSONArray2 = null;
                }
                if (jSONArray2 != null) {
                    int i3 = 0;
                    while (i3 < jSONArray2.length()) {
                        try {
                            long longValue;
                            JSONObject jSONObject3 = (JSONObject) jSONArray2.get(i3);
                            String optString = jSONObject3.optString("noteTime");
                            if (optString.length() > 0) {
                                try {
                                    longValue = Long.valueOf(optString).longValue();
                                } catch (Exception e3) {
                                    longValue = System.currentTimeMillis();
                                }
                            } else {
                                longValue = jSONObject3.optLong("noteTime");
                            }
                            List list = arrayList2;
                            list.add(new com.qq.reader.readengine.model.b(0, j2, bookShortName, "0", "0", jSONObject3.getString("selectedText"), jSONObject3.getString(MessageKey.MSG_CONTENT), longValue, jSONObject3.getInt("startChapter"), (long) jSONObject3.getInt("startOffset"), jSONObject3.getInt("endChapter"), (long) jSONObject3.getInt("endOffset"), j2, jSONObject3.getInt("noteType")));
                            i3++;
                        } catch (JSONException e4) {
                            e4.printStackTrace();
                            return null;
                        }
                    }
                    bVar.a(arrayList2);
                }
                arrayList.add(bVar);
            }
            return new d(1, i, arrayList);
        } else if (i == -1000) {
            return new d(1, i, null);
        } else {
            return null;
        }
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public String getContentType() {
        return "application/json";
    }
}
