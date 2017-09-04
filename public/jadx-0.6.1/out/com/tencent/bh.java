package com.tencent;

import com.tencent.imcore.IApplyDownloadFileCallback;
import com.tencent.imcore.StrVec;
import com.tencent.imsdk.IMMsfCoreProxy;
import java.util.ArrayList;
import java.util.List;

abstract class bh<T> extends IApplyDownloadFileCallback {
    public T a;

    public bh(T t) {
        swigReleaseOwnership();
        this.a = t;
    }

    public abstract void a(int i, String str);

    public abstract void a(List<String> list);

    public void done(StrVec strVec) {
        List arrayList = new ArrayList();
        for (int i = 0; ((long) i) < strVec.size(); i++) {
            arrayList.add(strVec.get(i));
        }
        IMMsfCoreProxy.mainHandler.post(new bi(this, arrayList));
        swigTakeOwnership();
    }

    public void fail(int i, String str) {
        IMMsfCoreProxy.mainHandler.post(new bj(this, i, str));
        swigTakeOwnership();
    }
}
