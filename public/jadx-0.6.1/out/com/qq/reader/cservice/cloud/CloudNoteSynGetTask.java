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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudNoteSynGetTask extends ReaderProtocolJSONTask {
    public CloudNoteSynGetTask(c cVar) {
        super(cVar);
        this.mUrl = e.bj;
    }

    public CloudNoteSynGetTask(c cVar, long j) {
        super(cVar);
        if (j != 0) {
            this.mUrl = String.format(e.bj + "?bookId=%d", new Object[]{Long.valueOf(j)});
            return;
        }
        this.mUrl = e.bj;
    }

    public HashMap<String, String> getBasicHeader() {
        return super.getBasicHeader();
    }

    public static d parseNoteGetReceiveData(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        int i = jSONObject.getInt("ret");
        long j = jSONObject.getLong("uin");
        if (i < 0) {
            return null;
        }
        JSONArray jSONArray;
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray2 = null;
        try {
            jSONArray = jSONObject.getJSONArray("noteInfos");
        } catch (JSONException e) {
            jSONArray = jSONArray2;
        }
        if (jSONArray == null) {
            return null;
        }
        if (jSONArray.length() <= 0) {
            return null;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            String bookShortName;
            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
            long j2 = jSONObject2.getLong("bookId");
            Mark e2 = i.c().e(j2 + "");
            if (e2 != null) {
                bookShortName = e2.getBookShortName();
            } else {
                try {
                    g b = l.b().b(j2);
                    if (b != null) {
                        bookShortName = b.v();
                    } else {
                        bookShortName = "";
                    }
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
            b bVar = new b();
            bVar.a(j2);
            bVar.c(jSONObject2.getInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION));
            bVar.a(jSONObject2.getInt("num"));
            bVar.a(bookShortName);
            bVar.b(j + "");
            bVar.b(0);
            List arrayList2 = new ArrayList();
            JSONArray jSONArray3 = jSONObject2.getJSONArray("notes");
            if (jSONArray3 != null) {
                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                    long longValue;
                    JSONObject jSONObject3 = (JSONObject) jSONArray3.get(i3);
                    String optString = jSONObject3.optString("noteTime");
                    if (optString.length() > 0) {
                        try {
                            longValue = Long.valueOf(optString).longValue();
                        } catch (Exception e4) {
                            try {
                                longValue = System.currentTimeMillis();
                            } catch (JSONException e5) {
                            }
                        }
                    } else {
                        longValue = jSONObject3.optLong("noteTime");
                    }
                    List list = arrayList2;
                    list.add(new com.qq.reader.readengine.model.b(0, j2, bookShortName, "0", "0", jSONObject3.getString("selectedText"), jSONObject3.getString(MessageKey.MSG_CONTENT), longValue, jSONObject3.getInt("startChapter"), (long) jSONObject3.getInt("startOffset"), jSONObject3.getInt("endChapter"), (long) jSONObject3.getInt("endOffset"), j2, jSONObject3.getInt("noteType")));
                }
                bVar.a(arrayList2);
            }
            arrayList.add(bVar);
        }
        return new d(2, i, arrayList);
    }
}
