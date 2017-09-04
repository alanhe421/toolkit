package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.service.b.j;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;

/* compiled from: ProGuard */
class s implements p {
    final /* synthetic */ String a;
    final /* synthetic */ p b;

    s(p pVar, String str) {
        this.b = pVar;
        this.a = str;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.c("PushServiceNetworkHandler", "Report uninstall with pkgName = " + this.a + ", reponseCode = " + i);
        }
        if (i == 0) {
            CacheManager.UninstallInfoSuccessByPkgName(this.a);
            j.a().a(m.e(), this.a);
            com.tencent.android.tpush.service.b.a.a().d(m.e(), this.a);
            return;
        }
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", " uninstall report fail responseCode=" + i);
        this.b.a(i, "服务器处理失败，返回错误", this.a, (TpnsUnregisterReq) jceStruct, aVar);
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        this.b.a(channelException.errorCode, channelException.getMessage(), this.a, (TpnsUnregisterReq) jceStruct, aVar);
    }

    public void a(JceStruct jceStruct, a aVar) {
    }
}
