package com.qq.reader.common.exception;

import android.content.Context;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.open.SocialConstants;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ReaderExceptionHandler */
public class d {
    private static d d = null;
    private final long a = BuglyBroadcastRecevier.UPLOADLIMITED;
    private final int b = 10;
    private final String c = "http://";
    private long e = 0;
    private ArrayList<c> f;
    private Context g;

    private d(Context context) {
        this.g = context;
        this.f = new ArrayList();
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (d == null) {
                d = new d(context);
            }
            dVar = d;
        }
        return dVar;
    }

    public synchronized void a(c cVar) {
        if (cVar != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.e == 0) {
                this.e = currentTimeMillis;
            }
            if (currentTimeMillis - this.e >= BuglyBroadcastRecevier.UPLOADLIMITED || cVar.b() == "bookstand_open") {
                a();
                this.e = currentTimeMillis;
                this.f.clear();
            } else if (this.f == null || this.f.size() >= 10) {
                f.a("ReaderExceptionHandler", cVar.toString());
            } else {
                this.f.add(cVar);
            }
        }
    }

    private void a() {
        if (this.f != null && this.f.size() != 0) {
            String jSONArray;
            JSONArray jSONArray2 = new JSONArray();
            try {
                Iterator it = this.f.iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("exception", cVar.a());
                    jSONObject.put("type", cVar.b());
                    jSONObject.put("time", cVar.c());
                    jSONObject.put("uid", cVar.d());
                    jSONObject.put(SocialConstants.PARAM_URL, cVar.e());
                    jSONObject.put("downloadurl", cVar.f());
                    jSONArray2.put(jSONObject);
                }
                jSONArray = jSONArray2.toString();
            } catch (JSONException e) {
                e.printStackTrace();
                jSONArray = null;
            }
            g.a().a(new UploadExceptionTask(new com.qq.reader.common.readertask.ordinal.d(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void a(ReaderProtocolTask readerProtocolTask, InputStream inputStream, long j) {
                }

                public void a(ReaderProtocolTask readerProtocolTask, Exception exception) {
                }
            }, jSONArray));
        }
    }
}
