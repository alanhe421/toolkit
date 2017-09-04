package com.tencent;

import com.tencent.imcore.IBatchOprCallback.BatchOprDetailInfo.ErrInfo;

public class TIMBatchOprDetailInfo$ErrInfo {
    private int errCode;
    private String errMsg = "";
    private String id = "";
    final /* synthetic */ TIMBatchOprDetailInfo this$0;

    public TIMBatchOprDetailInfo$ErrInfo(TIMBatchOprDetailInfo tIMBatchOprDetailInfo, ErrInfo errInfo) {
        this.this$0 = tIMBatchOprDetailInfo;
        if (errInfo != null) {
            this.id = errInfo.getId();
            this.errCode = errInfo.getErr_code();
            this.errMsg = errInfo.getErr_msg();
        }
    }

    public int getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public String getId() {
        return this.id;
    }
}
