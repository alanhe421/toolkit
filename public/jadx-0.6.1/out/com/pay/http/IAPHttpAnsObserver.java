package com.pay.http;

public interface IAPHttpAnsObserver {
    void onError(APBaseHttpAns aPBaseHttpAns);

    void onFinish(APBaseHttpAns aPBaseHttpAns);

    void onStop(APBaseHttpAns aPBaseHttpAns);
}
