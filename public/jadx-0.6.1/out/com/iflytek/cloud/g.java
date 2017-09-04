package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.a.a.a;
import com.iflytek.speech.TextUnderstanderAidl;

public class g extends a {
    private static g c = null;
    private TextUnderstanderAidl a;
    private a d;

    public static g a() {
        return c;
    }

    public String a(String str) {
        return super.a(str);
    }

    protected void a(Context context) {
        e a = e.a();
        if (a != null && a.b() && a.d() != a.a.MSC) {
            if (!(this.a == null || this.a.isAvailable())) {
                this.a.destory();
                this.a = null;
            }
            this.a = new TextUnderstanderAidl(context.getApplicationContext(), this.d);
        } else if (this.d != null && this.a != null) {
            this.a.destory();
            this.a = null;
        }
    }

    public boolean a(String str, String str2) {
        return super.a(str, str2);
    }
}
