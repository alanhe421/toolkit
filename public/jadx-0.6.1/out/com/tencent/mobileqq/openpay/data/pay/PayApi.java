package com.tencent.mobileqq.openpay.data.pay;

import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import com.tencent.mobileqq.openpay.data.base.BaseApi;

public class PayApi extends BaseApi {
    public String bargainorId;
    public String callbackScheme;
    public String nonce;
    public String pubAcc;
    public String pubAccHint;
    public String serialNumber;
    public String sig;
    public String sigType;
    public long timeStamp;
    public String tokenId;

    public boolean checkParams() {
        return (TextUtils.isEmpty(this.appId) || TextUtils.isEmpty("native") || "native".compareTo("native") != 0 || TextUtils.isEmpty(this.callbackScheme) || TextUtils.isEmpty(this.tokenId) || TextUtils.isEmpty(this.bargainorId) || TextUtils.isEmpty(this.nonce) || TextUtils.isEmpty(this.sig) || TextUtils.isEmpty(this.sigType) || this.timeStamp <= 0 || TextUtils.isEmpty(this.serialNumber)) ? false : true;
    }

    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.serialNumber = bundle.getString("_mqqpay_payapi_serialnumber");
        this.callbackScheme = bundle.getString("_mqqpay_payapi_callbackscheme");
        this.pubAcc = bundle.getString("_mqqpay_payapi_pubacc");
        this.pubAccHint = bundle.getString("_mqqpay_payapi_pubacchint");
        this.tokenId = bundle.getString("_mqqpay_payapi_tokenid");
        this.nonce = bundle.getString("_mqqpay_payapi_nonce");
        this.timeStamp = bundle.getLong("_mqqpay_payapi_timeStamp");
        this.bargainorId = bundle.getString("_mqqpay_payapi_bargainorId");
        this.sigType = bundle.getString("_mqqpay_payapi_sigType");
        this.sig = bundle.getString("_mqqpay_payapi_sig");
    }

    public int getApiMark() {
        return 1;
    }

    public String getApiName() {
        return OpenConstants.API_NAME_PAY;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putString("_mqqpay_payapi_serialnumber", this.serialNumber);
        bundle.putString("_mqqpay_payapi_callbackscheme", this.callbackScheme);
        bundle.putString("_mqqpay_payapi_pubacc", this.pubAcc);
        bundle.putString("_mqqpay_payapi_pubacchint", this.pubAccHint);
        bundle.putString("_mqqpay_payapi_tokenid", this.tokenId);
        bundle.putString("_mqqpay_payapi_nonce", this.nonce);
        bundle.putLong("_mqqpay_payapi_timeStamp", this.timeStamp);
        bundle.putString("_mqqpay_payapi_bargainorId", this.bargainorId);
        bundle.putString("_mqqpay_payapi_sigType", this.sigType);
        bundle.putString("_mqqpay_payapi_sig", this.sig);
    }
}
