package com.tencent.av;

import com.tencent.TIMValueCallBack;

final class ac implements TIMValueCallBack<byte[]> {
    private /* synthetic */ TIMPingCallBack a;
    private /* synthetic */ TIMAvManager b;

    ac(TIMAvManager tIMAvManager, TIMPingCallBack tIMPingCallBack) {
        this.b = tIMAvManager;
        this.a = tIMPingCallBack;
    }

    public final void onError(int i, String str) {
        if (this.a != null) {
            this.a.onError(i, str);
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        PingUtil.getInstance().init((byte[]) obj, this.a);
        PingUtil.getInstance().setIdentifer(this.b.identifier);
        PingUtil.getInstance().start();
    }
}
