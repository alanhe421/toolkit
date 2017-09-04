package com.tencent.android.tpush.data;

import java.io.Serializable;

/* compiled from: ProGuard */
public class CachedMessageIntent implements Serializable {
    private static final long serialVersionUID = 1724218633838690967L;
    public String intent = "";
    public long msgId;
    public String pkgName = "";

    public boolean equals(Object obj) {
        if (obj instanceof CachedMessageIntent) {
            return ((CachedMessageIntent) obj).pkgName.equals(this.pkgName) && ((CachedMessageIntent) obj).intent.equals(this.intent);
        } else {
            return super.equals(obj);
        }
    }

    public int hashCode() {
        return super.hashCode();
    }
}
