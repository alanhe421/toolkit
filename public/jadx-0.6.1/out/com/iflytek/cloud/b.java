package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.a.a.a;
import com.iflytek.speech.SpeechRecognizerAidl;

public final class b extends a {
    private static b a = null;
    private SpeechRecognizerAidl c;
    private a d;

    public static b a() {
        return a;
    }

    public String a(String str) {
        return super.a(str);
    }

    protected void a(Context context) {
        e a = e.a();
        if (a != null && a.b() && a.d() != a.a.MSC) {
            if (!(this.c == null || this.c.isAvailable())) {
                this.c.destory();
                this.c = null;
            }
            this.c = new SpeechRecognizerAidl(context.getApplicationContext(), this.d);
        } else if (this.d != null && this.c != null) {
            this.c.destory();
            this.c = null;
        }
    }

    public boolean a(String str, String str2) {
        return super.a(str, str2);
    }
}
