package com.qrcomic.screenshot.a;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.util.HashMap;
import java.util.Map;

/* compiled from: QRBubbleBasicInfo */
public class a {
    private static Map<String, Bitmap> g = new HashMap();
    public String a = "";
    public String b = "";
    public String c = "";
    public int d = Color.parseColor("#ff000000");
    public boolean e = false;
    public int f = Color.parseColor("#ffffffff");

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (!this.a.equals(aVar.b) || (((this.b != null || aVar.b != null) && (this.b == null || !this.b.equals(aVar.b))) || (((this.c != null || aVar.c != null) && (this.c == null || !this.c.equals(aVar.c))) || this.d != aVar.d || this.e != aVar.e || this.f != aVar.f))) {
            return false;
        }
        return true;
    }
}
