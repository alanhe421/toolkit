package com.pay.api;

public interface IAPPayGameServiceCallBack {
    void PayGameNeedLogin();

    void PayGameServiceCallBack(APPayResponseInfo aPPayResponseInfo);
}
