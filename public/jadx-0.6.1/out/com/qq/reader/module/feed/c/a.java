package com.qq.reader.module.feed.c;

import com.qq.reader.module.feed.b.d;
import com.qq.reader.module.feed.b.e;
import com.qq.reader.module.feed.b.f;
import com.qq.reader.module.feed.b.g;
import com.qq.reader.module.feed.b.h;
import com.qq.reader.module.feed.b.i;
import com.qq.reader.module.feed.b.j;
import com.qq.reader.module.feed.b.k;
import com.qq.reader.module.feed.b.l;
import com.qq.reader.module.feed.b.m;
import com.qq.reader.module.feed.b.n;
import com.qq.reader.module.feed.b.o;
import com.qq.reader.module.feed.b.p;
import org.json.JSONObject;

/* compiled from: FeedCardUIStyleUtil */
public class a {
    public static com.qq.reader.module.feed.b.a a(JSONObject jSONObject) {
        com.qq.reader.module.feed.b.a aVar = null;
        switch (jSONObject.optInt("uistyle")) {
            case 1:
                aVar = new d();
                break;
            case 2:
                aVar = new i();
                break;
            case 3:
                aVar = new j();
                break;
            case 4:
                aVar = new k();
                break;
            case 5:
                aVar = new l();
                break;
            case 6:
                aVar = new m();
                break;
            case 7:
                aVar = new n();
                break;
            case 8:
                aVar = new o();
                break;
            case 9:
                aVar = new p();
                break;
            case 10:
                aVar = new e();
                break;
            case 11:
                aVar = new f();
                break;
            case 12:
                aVar = new g();
                break;
            case 13:
                aVar = new h();
                break;
        }
        return aVar.a(jSONObject);
    }
}
