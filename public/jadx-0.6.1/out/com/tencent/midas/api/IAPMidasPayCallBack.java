package com.tencent.midas.api;

public interface IAPMidasPayCallBack {
    void MidasPayCallBack(APMidasResponse aPMidasResponse);

    void MidasPayNeedLogin();
}
