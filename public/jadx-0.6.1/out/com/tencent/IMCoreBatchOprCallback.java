package com.tencent;

import com.tencent.imcore.IBatchOprCallback;
import com.tencent.imcore.IBatchOprCallback.BatchOprDetailInfo;

public class IMCoreBatchOprCallback extends IBatchOprCallback {
    TIMSendMsgToMultiUserCallback cb;

    public IMCoreBatchOprCallback(TIMSendMsgToMultiUserCallback tIMSendMsgToMultiUserCallback) {
        this.cb = tIMSendMsgToMultiUserCallback;
        swigReleaseOwnership();
    }

    public void done() {
        if (this.cb != null) {
            this.cb.onSuccess();
        }
        swigTakeOwnership();
    }

    public void fail(int i, String str, BatchOprDetailInfo batchOprDetailInfo) {
        if (this.cb != null) {
            this.cb.onError(i, str, new TIMBatchOprDetailInfo(batchOprDetailInfo));
        }
        swigTakeOwnership();
    }
}
