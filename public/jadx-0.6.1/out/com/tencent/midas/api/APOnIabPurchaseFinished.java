package com.tencent.midas.api;

import com.tencent.midas.api.request.APIabResult;
import com.tencent.midas.api.request.APPurchase;

public interface APOnIabPurchaseFinished {
    void onIabPurchaseFinished(APIabResult aPIabResult, APPurchase aPPurchase);

    void onIabyNeedLogin();
}
