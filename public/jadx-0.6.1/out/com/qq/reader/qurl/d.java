package com.qq.reader.qurl;

import android.app.Activity;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.Map;

/* compiled from: URLServer */
public abstract class d {
    private String a = "";
    private String b = "";
    private Map<String, String> c;
    private String d = null;
    private WeakReference<Activity> e;
    private WeakReference<b> f = null;
    private int g = -1;
    private JumpActivityParameter h;

    public abstract void f() throws Exception;

    public d(Activity activity, String str, String str2) {
        this.a = str2;
        this.b = str;
        this.e = new WeakReference(activity);
        this.c = c.c(this.a);
        this.h = new JumpActivityParameter();
    }

    public void a(JumpActivityParameter jumpActivityParameter) {
        if (jumpActivityParameter != null) {
            this.h = jumpActivityParameter;
        }
    }

    public JumpActivityParameter a() {
        return this.h;
    }

    protected Activity b() {
        return (Activity) this.e.get();
    }

    public void a(b bVar) {
        if (bVar != null) {
            this.f = new WeakReference(bVar);
        }
    }

    protected boolean a(Message message) {
        if (this.f == null) {
            return false;
        }
        b bVar = (b) this.f.get();
        return bVar != null ? bVar.a(message) : false;
    }

    public void a(String str) {
        this.d = str;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.b;
    }

    public Map<String, String> e() {
        return this.c;
    }
}
