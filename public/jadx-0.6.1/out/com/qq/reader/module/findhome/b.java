package com.qq.reader.module.findhome;

import com.qq.reader.module.findhome.a.c;
import com.qq.reader.module.findhome.a.d;
import com.qq.reader.module.findhome.base.a;
import org.json.JSONObject;

/* compiled from: FindHomeExpandItemFactory */
public class b {
    public static a a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        a bVar;
        switch (jSONObject.optInt("type")) {
            case 0:
                bVar = new com.qq.reader.module.findhome.a.b();
                if (bVar.a(jSONObject)) {
                    return bVar;
                }
                return null;
            case 1:
                bVar = new c();
                if (bVar.a(jSONObject)) {
                    return bVar;
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                bVar = new a();
                if (bVar.a(jSONObject)) {
                    return bVar;
                }
                return null;
            default:
                return null;
        }
        bVar = new com.qq.reader.module.findhome.a.a();
        if (bVar.a(jSONObject)) {
            return bVar;
        }
        bVar = new d();
        if (bVar.a(jSONObject)) {
            return bVar;
        }
        return null;
    }
}
