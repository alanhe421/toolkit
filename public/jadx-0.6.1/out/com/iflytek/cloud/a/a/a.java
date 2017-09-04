package com.iflytek.cloud.a.a;

import android.text.TextUtils;
import com.iflytek.speech.ISpeechModule;

public abstract class a {
    protected com.iflytek.cloud.b.a b = new com.iflytek.cloud.b.a();

    public enum a {
        MSC,
        PLUS,
        AUTO
    }

    protected a a(String str, ISpeechModule iSpeechModule) {
        return a.PLUS;
    }

    public String a(String str) {
        return "params".equals(str) ? this.b.toString() : this.b.d(str);
    }

    public boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equals("params")) {
            if (TextUtils.isEmpty(str2)) {
                this.b.a();
                return true;
            }
            this.b.b(str2);
            return true;
        } else if (TextUtils.isEmpty(str2)) {
            return this.b.c(str).booleanValue();
        } else {
            this.b.a(str, str2);
            return true;
        }
    }
}
