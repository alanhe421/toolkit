package com.qrcomic.entity;

import com.google.gson.annotations.SerializedName;
import com.qrcomic.util.g;

/* compiled from: QRSectionBuyStatus */
public class n {
    @SerializedName("sectionId")
    public String a;
    @SerializedName("payStatus")
    public int b;

    public n(String str, int i) {
        this.a = str;
        this.b = i;
        if (i != 1 && i != 2 && g.a()) {
            g.a("QRComicBuyInfo", g.d, "payStatus error , = " + i);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof n) {
            n nVar = (n) obj;
            if (nVar.a != null && nVar.a.equals(this.a) && nVar.b == this.b) {
                return true;
            }
        }
        return false;
    }
}
