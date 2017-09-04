package com.tencent;

public interface TIMSendMsgToMultiUserCallback {
    void onError(int i, String str, TIMBatchOprDetailInfo tIMBatchOprDetailInfo);

    void onSuccess();
}
