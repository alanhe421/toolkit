package com.pay.api;

public interface IAPPayOpenServiceCallBack {
    void PayOpenServiceCallBack(APPayResponseInfo aPPayResponseInfo);

    void PayOpenServiceNeedLogin();
}
