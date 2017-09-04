package com.tencent.midas.api.request;

import java.util.List;

public interface OnAPConsumeFinishedListener {
    void onConsumeFinished(List<APPurchase> list);
}
