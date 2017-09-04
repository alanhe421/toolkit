package com.tencent.android.tpush.b;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.p;
import org.json.JSONObject;

/* compiled from: ProGuard */
public abstract class a {
    protected JSONObject a = null;
    protected String b = null;
    protected String c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;

    public abstract int c();

    protected abstract void d();

    protected a(String str) {
        this.b = str;
    }

    public String a() {
        return this.c;
    }

    public void b() {
        this.a = new JSONObject(this.b);
        if (!this.a.isNull("title")) {
            this.d = this.a.getString("title");
        }
        if (!this.a.isNull(MessageKey.MSG_CONTENT)) {
            this.e = this.a.getString(MessageKey.MSG_CONTENT);
        }
        if (!this.a.isNull("custom_content")) {
            String string = this.a.getString("custom_content");
            if (!(string == null || string.trim().equals("{}"))) {
                this.f = string;
            }
        }
        if (!this.a.isNull(MessageKey.MSG_ACCEPT_TIME)) {
            this.g = this.a.getString(MessageKey.MSG_ACCEPT_TIME);
        }
        d();
        this.c = p.a(this.b).toUpperCase();
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BaseMessageHolder [msgJson=").append(this.a).append(", msgJsonStr=").append(this.b).append(", title=").append(this.d).append(", content=").append(this.e).append(", customContent=").append(this.f).append(", acceptTime=").append(this.g).append("]");
        return stringBuilder.toString();
    }
}
