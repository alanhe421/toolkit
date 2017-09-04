package FileCloud;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

public final class stEnvironment extends JceStruct {
    static int cache_net;
    static int cache_source;
    public String device = "";
    public int net = 0;
    public String operators = "";
    public String os_version = "";
    public String qua = "";
    public String refer = "";
    public String sdk_version = "";
    public int source = 0;

    public stEnvironment(int i, String str, String str2, String str3, int i2, String str4, String str5, String str6) {
        this.source = i;
        this.refer = str;
        this.qua = str2;
        this.device = str3;
        this.net = i2;
        this.operators = str4;
        this.sdk_version = str5;
        this.os_version = str6;
    }

    public final void readFrom(c cVar) {
        this.source = cVar.a(this.source, 1, true);
        this.refer = cVar.a(2, true);
        this.qua = cVar.a(3, true);
        this.device = cVar.a(4, true);
        this.net = cVar.a(this.net, 5, true);
        this.operators = cVar.a(6, true);
        this.sdk_version = cVar.a(7, false);
        this.os_version = cVar.a(8, false);
    }

    public final void writeTo(d dVar) {
        dVar.a(this.source, 1);
        dVar.a(this.refer, 2);
        dVar.a(this.qua, 3);
        dVar.a(this.device, 4);
        dVar.a(this.net, 5);
        dVar.a(this.operators, 6);
        if (this.sdk_version != null) {
            dVar.a(this.sdk_version, 7);
        }
        if (this.os_version != null) {
            dVar.a(this.os_version, 8);
        }
    }
}
