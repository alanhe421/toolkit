package com.iflytek.cloud.b;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map.Entry;

public class a {
    HashMap<String, String> a = new HashMap();

    public a(String str, String[][] strArr) {
        a(str);
        a(strArr);
    }

    public void a() {
        this.a.clear();
    }

    public void a(String str) {
        this.a.clear();
        b(str);
    }

    public void a(String str, String str2) {
        a(str, str2, true);
    }

    public void a(String str, String str2, boolean z) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (z || !this.a.containsKey(str)) {
                this.a.put(str, str2);
            }
        }
    }

    public void a(String[][] strArr) {
        if (strArr != null) {
            for (Object[] objArr : strArr) {
                if (this.a.containsKey(objArr[0])) {
                    String str = (String) this.a.get(objArr[0]);
                    this.a.remove(objArr[0]);
                    for (int i = 1; i < objArr.length; i++) {
                        this.a.put(objArr[i], str);
                    }
                }
            }
        }
    }

    public boolean a(String str, boolean z) {
        String str2 = (String) this.a.get(str);
        return str2 != null ? (str2.equals("true") || str2.equals("1")) ? true : (str2.equals("false") || str2.equals("0")) ? false : z : z;
    }

    public a b() {
        a aVar = new a();
        aVar.a = (HashMap) this.a.clone();
        return aVar;
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(",")) {
                int indexOf = str2.indexOf("=");
                if (indexOf > 0 && indexOf < str2.length()) {
                    this.a.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
                }
            }
        }
    }

    public Boolean c(String str) {
        return Boolean.valueOf(this.a.remove(str) != null);
    }

    public HashMap<String, String> c() {
        return this.a;
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    public String d(String str) {
        return (String) this.a.get(str);
    }

    public boolean e(String str) {
        return this.a.containsKey(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Entry entry : this.a.entrySet()) {
            stringBuffer.append((String) entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append((String) entry.getValue());
            stringBuffer.append(",");
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        String stringBuffer2 = stringBuffer.toString();
        com.iflytek.cloud.a.b.a.a.c(stringBuffer2);
        return stringBuffer2;
    }
}
