package com.tencent.midas.api.request;

import android.util.Base64;
import org.json.JSONObject;

public class APPurchase {
    public String mOriginalJson;
    public String mSignature;
    public String mSku;

    public APPurchase() {
        this.mSignature = "";
        this.mOriginalJson = "";
        this.mSku = "";
    }

    public APPurchase(String str) {
        JSONObject jSONObject = new JSONObject(str);
        this.mSignature = (String) jSONObject.get("sign");
        this.mOriginalJson = (String) jSONObject.get("data");
        this.mSku = "";
    }

    public APPurchase(String str, String str2) {
        this.mOriginalJson = str;
        this.mSku = new JSONObject(new String(Base64.decode(str.getBytes(), 0))).optString("product-id");
        this.mSignature = str2;
    }

    public String getOriginalJson() {
        return this.mOriginalJson;
    }

    public String getSignature() {
        return this.mSignature;
    }

    public String getSku() {
        return this.mSku;
    }

    public String toString() {
        return "PurchaseInfo():" + this.mOriginalJson;
    }
}
