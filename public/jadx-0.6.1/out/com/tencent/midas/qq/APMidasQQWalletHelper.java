package com.tencent.midas.qq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.midas.comm.APLog;
import com.tencent.mobileqq.openpay.api.IOpenApi;
import com.tencent.mobileqq.openpay.api.IOpenApiListener;
import com.tencent.mobileqq.openpay.api.OpenApiFactory;
import com.tencent.mobileqq.openpay.constants.OpenConstants;
import com.tencent.mobileqq.openpay.data.base.BaseResponse;
import com.tencent.mobileqq.openpay.data.pay.PayApi;
import com.tencent.mobileqq.openpay.data.pay.PayResponse;
import java.util.ArrayList;
import java.util.Iterator;

public class APMidasQQWalletHelper implements IOpenApiListener {
    private static volatile APMidasQQWalletHelper c = null;
    IOpenApi a = null;
    PayApi b = null;
    private ArrayList<Handler> d = new ArrayList(1);

    private APMidasQQWalletHelper(Context context, String str) {
        this.a = OpenApiFactory.getInstance(context, str);
        this.b = new PayApi();
    }

    public static APMidasQQWalletHelper getInstance(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (c == null) {
            synchronized (APMidasQQWalletHelper.class) {
                if (c == null) {
                    c = new APMidasQQWalletHelper(context.getApplicationContext(), str);
                }
            }
        }
        return c;
    }

    public static APMidasQQWalletHelper getPayHelper(Context context) {
        return c;
    }

    public void addObserver(Handler handler) {
        APLog.i("APMidasQQPayHelper", "addObserver observer:" + handler);
        synchronized (this.d) {
            if (!this.d.contains(handler)) {
                this.d.add(handler);
            }
        }
    }

    public void handleIntent(Intent intent) {
        APLog.i("APMidasQQPayHelper", "handleIntent intent:" + intent);
        this.a.handleIntent(intent, this);
    }

    public boolean isQQPaySupport() {
        APLog.i("APMidasQQPayHelper", "isQQPaySupport openApi:" + this.a);
        return this.a.isMobileQQSupportApi(OpenConstants.API_NAME_PAY);
    }

    public void onOpenResponse(BaseResponse baseResponse) {
        if (baseResponse == null) {
            String str = "response is null.";
            return;
        }
        String str2;
        if (baseResponse instanceof PayResponse) {
            PayResponse payResponse = (PayResponse) baseResponse;
            str = " apiName:" + payResponse.apiName + " serialnumber:" + payResponse.serialNumber + " isSucess:" + payResponse.isSuccess() + " retCode:" + payResponse.retCode + " retMsg:" + payResponse.retMsg;
            str2 = !payResponse.isPayByWeChat() ? str + " transactionId:" + payResponse.transactionId + " payTime:" + payResponse.payTime + " callbackUrl:" + payResponse.callbackUrl + " totalFee:" + payResponse.totalFee + " spData:" + payResponse.spData : str;
            synchronized (this.d) {
                Iterator it = this.d.iterator();
                while (it.hasNext()) {
                    Handler handler = (Handler) it.next();
                    Message message = new Message();
                    message.what = 10;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isSuccess", payResponse.isSuccess());
                    bundle.putInt("retCode", payResponse.retCode);
                    bundle.putString("retMsg", payResponse.retMsg);
                    bundle.putString("apiName", payResponse.apiName);
                    bundle.putString("serialNumber", payResponse.serialNumber);
                    bundle.putString("transaction", payResponse.transactionId);
                    bundle.putString("payTime", payResponse.payTime);
                    bundle.putString("callbackUrl", payResponse.callbackUrl);
                    bundle.putString("totalFee", payResponse.totalFee);
                    bundle.putString("spData", payResponse.spData);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            }
        } else {
            str2 = "response is not PayResponse.";
        }
        APLog.i("APMidasQQPayHelper", "onOpenResponse message = " + str2);
    }

    public void removeObserver(Handler handler) {
        APLog.i("APMidasQQPayHelper", "removeObserver observer:" + handler);
        synchronized (this.d) {
            if (this.d.contains(handler)) {
                this.d.remove(handler);
            }
        }
    }

    public void toPay(Bundle bundle) {
        String string = bundle.getString("appId");
        String string2 = bundle.getString("tokenId");
        String string3 = bundle.getString("nonce");
        String string4 = bundle.getString("bargainorId");
        String string5 = bundle.getString("sig");
        String string6 = bundle.getString("sigType");
        long j = bundle.getLong("timeStamp");
        int i = bundle.getInt("serialNumber");
        String string7 = bundle.getString("pubAcc");
        String string8 = bundle.getString("pubAccHint");
        long currentTimeMillis = System.currentTimeMillis();
        APLog.i("APMidasQQPayHelper", "toPay tokenId = " + string2);
        APLog.i("APMidasQQPayHelper", "toPay appId = " + string);
        APLog.i("APMidasQQPayHelper", "toPay paySerial = " + i);
        APLog.i("APMidasQQPayHelper", "toPay pubAcc = " + string7);
        APLog.i("APMidasQQPayHelper", "toPay pubAccHint = " + string8);
        APLog.i("APMidasQQPayHelper", "toPay sig = " + string5);
        APLog.i("APMidasQQPayHelper", "toPay sigType = " + string6);
        APLog.i("APMidasQQPayHelper", "toPay nonce = " + string3);
        APLog.i("APMidasQQPayHelper", "toPay bargainorId = " + string4);
        long currentTimeMillis2 = System.currentTimeMillis();
        APLog.i("APMidasQQPayHelper", "toPay logtime = " + (currentTimeMillis2 - currentTimeMillis));
        this.b = new PayApi();
        this.b.appId = string;
        int i2 = i + 1;
        this.b.serialNumber = "" + i;
        this.b.callbackScheme = "qwallet" + string;
        this.b.tokenId = string2;
        this.b.pubAcc = string7;
        this.b.pubAccHint = string8;
        this.b.nonce = string3;
        this.b.timeStamp = j;
        this.b.bargainorId = string4;
        this.b.sig = string5;
        this.b.sigType = string6;
        this.a.execApi(this.b);
    }
}
