package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stAuth extends JceStruct {
    public String appid = "";
    public String sign = "";
    public String text = "";
    public String token = "";

    public stAuth(String str, String str2, String str3, String str4) {
        this.appid = str;
        this.sign = str2;
        this.text = str3;
        this.token = str4;
    }

    public final void readFrom(c cVar) {
        this.appid = cVar.a(1, true);
        this.sign = cVar.a(3, false);
        this.text = cVar.a(4, false);
        this.token = cVar.a(5, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.appid, 1);
        if (this.sign != null) {
            dVar.a(this.sign, 3);
        }
        if (this.text != null) {
            dVar.a(this.text, 4);
        }
        if (this.token != null) {
            dVar.a(this.token, 5);
        }
    }
}
