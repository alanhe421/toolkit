package com.tencent.upload.network.b;

import com.tencent.upload.common.Global;
import com.tencent.upload.common.a.a;
import com.tencent.upload.network.a.k;
import com.tencent.upload.network.b.a.b;
import com.tencent.upload.task.impl.HandshakeTask.HandShakeResult;
import com.tencent.upload.task.impl.HandshakeTask.IListener;

final class r implements IListener {
    private /* synthetic */ h a;

    r(h hVar) {
        this.a = hVar;
    }

    public final void onFailure(int i, String str) {
        a.d(this.a.j(), "Handshake Failed! ret=" + i + " msg=" + str + " sid=" + this.a.o);
        b bVar = (b) this.a.e.get();
        if (bVar != null) {
            bVar.a(this.a, i, str);
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        HandShakeResult handShakeResult = (HandShakeResult) obj;
        k redirectRoute = handShakeResult.getRedirectRoute();
        a.c(this.a.j(), "Handshake Succeed! sid=" + this.a.o + " redirect=" + (redirectRoute != null ? redirectRoute.toString() : "N/A") + " clientIP=" + handShakeResult.clientIp);
        if (handShakeResult.clientIp != null) {
            h hVar = this.a;
            String str = handShakeResult.clientIp;
            Global.clientIP = str;
            hVar.j = str;
        }
        if (!(redirectRoute == null || this.a.c == null)) {
            redirectRoute.c(this.a.c.f());
        }
        this.a.a(b.ESTABLISHED);
        this.a.d = redirectRoute;
        b bVar = (b) this.a.e.get();
        if (bVar != null) {
            bVar.a(this.a);
        }
    }
}
