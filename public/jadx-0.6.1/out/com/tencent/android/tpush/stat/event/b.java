package com.tencent.android.tpush.stat.event;

import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class b {
    public String a;
    public JSONArray b;
    public JSONObject c = null;

    public b(String str, String[] strArr, Properties properties) {
        this.a = str;
        if (properties != null) {
            this.c = new JSONObject(properties);
        } else if (strArr != null) {
            this.b = new JSONArray();
            for (Object put : strArr) {
                this.b.put(put);
            }
        } else {
            this.c = new JSONObject();
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append(this.a).append(",");
        if (this.b != null) {
            stringBuilder.append(this.b.toString());
        }
        if (this.c != null) {
            stringBuilder.append(this.c.toString());
        }
        return stringBuilder.toString();
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        return toString().equals(((b) obj).toString());
    }
}
