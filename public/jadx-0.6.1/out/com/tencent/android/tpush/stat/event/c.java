package com.tencent.android.tpush.stat.event;

import android.content.Context;
import com.tencent.android.tpush.stat.a.a;
import com.tencent.android.tpush.stat.a.h;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class c extends d {
    private String a;
    private int k;
    private int l = 100;
    private Thread m = null;

    public c(Context context, int i, int i2, Throwable th, Thread thread, long j) {
        super(context, i, j);
        a(i2, th);
        this.m = thread;
    }

    private void a(int i, Throwable th) {
        if (th != null) {
            Writer stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            this.a = stringWriter.toString();
            this.k = i;
            printWriter.close();
        }
    }

    public EventType b() {
        return EventType.ERROR;
    }

    public boolean a(JSONObject jSONObject) {
        h.a(jSONObject, "er", this.a);
        jSONObject.put("ea", this.k);
        if (this.k == 2 || this.k == 3) {
            new a(this.j, this.c).a(jSONObject, this.m);
        }
        return true;
    }
}
