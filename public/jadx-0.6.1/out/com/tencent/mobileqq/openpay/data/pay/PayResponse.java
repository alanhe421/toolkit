package com.tencent.mobileqq.openpay.data.pay;

import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mobileqq.openpay.data.base.BaseResponse;

public class PayResponse extends BaseResponse {
    private String a;
    public String callbackUrl;
    public String payTime;
    public String serialNumber;
    public String spData;
    public String totalFee;
    public String transactionId;

    public boolean checkParams() {
        return this.retCode == -9999999 ? false : (isSuccess() && !isPayByWeChat() && (TextUtils.isEmpty(this.transactionId) || TextUtils.isEmpty(this.payTime) || TextUtils.isEmpty(this.totalFee))) ? false : true;
    }

    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.a = bundle.getString("_mqqpay_payresp_paychanneltype");
        this.transactionId = bundle.getString("_mqqpay_payresp_transactionid");
        this.payTime = bundle.getString("_mqqpay_payresp_paytime");
        this.totalFee = bundle.getString("_mqqpay_payresp_totalfee");
        this.callbackUrl = bundle.getString("_mqqpay_payresp_callbackurl");
        this.spData = bundle.getString("_mqqpay_payresp_spdata");
        this.serialNumber = bundle.getString("_mqqpay_payapi_serialnumber");
    }

    public boolean isPayByWeChat() {
        return !TextUtils.isEmpty(this.a) && this.a.compareTo("1") == 0;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putString("_mqqpay_payresp_paychanneltype", this.a);
        bundle.putString("_mqqpay_payresp_transactionid", this.transactionId);
        bundle.putString("_mqqpay_payresp_paytime", this.payTime);
        bundle.putString("_mqqpay_payresp_totalfee", this.totalFee);
        bundle.putString("_mqqpay_payresp_callbackurl", this.callbackUrl);
        bundle.putString("_mqqpay_payresp_spdata", this.spData);
        bundle.putString("_mqqpay_payapi_serialnumber", this.serialNumber);
    }
}
