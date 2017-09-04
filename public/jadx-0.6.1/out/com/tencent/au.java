package com.tencent;

import com.tencent.imcore.IInit;

abstract class au extends IInit {
    public TIMCallBack a;

    au(TIMCallBack tIMCallBack) {
        this.a = tIMCallBack;
        swigReleaseOwnership();
    }

    public abstract void a();

    public abstract void a(int i, String str);

    public final void done() {
        a();
        swigTakeOwnership();
    }

    public final void fail(int i, String str) {
        a(i, str);
        swigTakeOwnership();
    }
}
