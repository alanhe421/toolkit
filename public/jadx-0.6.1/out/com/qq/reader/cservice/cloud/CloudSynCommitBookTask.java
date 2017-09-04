package com.qq.reader.cservice.cloud;

import android.text.TextUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.db.handle.j;
import com.qq.reader.common.monitor.l;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.cloud.a.a;
import com.qq.reader.cservice.cloud.a.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudSynCommitBookTask extends ReaderProtocolJSONTask {
    private static final long serialVersionUID = 1;
    private transient String jsonString;

    public CloudSynCommitBookTask(c cVar, long j, List<g> list, long j2) {
        super(cVar);
        setTid(j);
        this.mUrl = e.ba + j;
        this.jsonString = getUpListString(list, j2);
        setFailedType(1);
    }

    public HashMap<String, String> getBasicHeader() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        this.mHeaders.put("qrsy", d.f(this.mContext, currentTimeMillis));
        this.mHeaders.put("qrtm", String.valueOf(currentTimeMillis));
        this.mHeaders.put("qrem", ao.p());
        return super.getBasicHeader();
    }

    protected boolean interuptNoConn() {
        return true;
    }

    public String getRequestMethod() {
        return Constants.HTTP_POST;
    }

    public String getRequestContent() {
        return this.jsonString;
    }

    public String getContentType() {
        return "application/json";
    }

    private String getUpListString(List<g> list, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (g gVar : list) {
                Object obj;
                JSONObject jSONObject2;
                if (gVar instanceof com.qq.reader.cservice.cloud.a.c) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("method", gVar.n());
                    JSONArray jSONArray2 = new JSONArray();
                    JSONArray jSONArray3 = new JSONArray();
                    List a = ((com.qq.reader.cservice.cloud.a.c) gVar).a();
                    for (int i = 0; i < a.size(); i++) {
                        a aVar = (a) a.get(i);
                        jSONArray2.put(aVar.a());
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("id", aVar.a());
                        jSONObject3.put("resType", aVar.b());
                        jSONArray3.put(jSONObject3);
                    }
                    jSONObject2.put("bookids", jSONArray2);
                    jSONObject2.put("resbookids", jSONArray3);
                    obj = jSONObject2;
                } else {
                    JSONObject jSONObject4;
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("method", gVar.n());
                    jSONObject2.put("bookid", gVar.b());
                    jSONObject2.put("updatetime", gVar.f());
                    jSONObject2.put("offset", gVar.i());
                    jSONObject2.put("chapterid", gVar.h());
                    jSONObject2.put("pictureid", gVar.l());
                    jSONObject2.put("resType", gVar.m());
                    if (gVar.n().equals("add")) {
                        com.qq.reader.common.monitor.a.a b = j.a().b(String.valueOf(gVar.b()));
                        if (b != null) {
                            jSONObject2.put(s.ORIGIN, b.b());
                            CharSequence b2 = b.b();
                            if (TextUtils.isEmpty(b2)) {
                                obj = l.a("" + gVar.b());
                            } else {
                                CharSequence charSequence = b2;
                            }
                            jSONObject2.put(s.STATPARAM_KEY, obj);
                            jSONObject4 = jSONObject2;
                        } else {
                            jSONObject2.put(s.STATPARAM_KEY, l.a("" + gVar.b()));
                        }
                    }
                    jSONObject4 = jSONObject2;
                }
                jSONArray.put(obj);
            }
            jSONObject.put("books", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected boolean isNeedSaveFailedTaskToDisk() {
        return true;
    }

    public boolean isResponseGzip() {
        return true;
    }
}
