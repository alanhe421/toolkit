package com.tencent;

import java.util.List;

public interface TIMMessageReceiptListener {
    void onRecvReceipt(List<TIMMessageReceipt> list);
}
