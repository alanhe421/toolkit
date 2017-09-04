package com.tencent.midas.wx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.comm.APLog;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelbiz.AddCardToWXCardPackage.Req;
import com.tencent.mm.sdk.modelbiz.AddCardToWXCardPackage.WXCardItem;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.modelpay.PayReq.Options;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class APMidasWXPayHelper implements IWXAPIEventHandler {
    private static Object a = new Object();
    private static APMidasWXPayHelper b = null;
    private ArrayList<Handler> c = new ArrayList(1);
    private IWXAPI d;

    private APMidasWXPayHelper(Context context) {
        this.d = WXAPIFactory.createWXAPI(context, null);
    }

    public static APMidasWXPayHelper getInstance(Context context) {
        if (b == null) {
            synchronized (a) {
                if (b == null) {
                    b = new APMidasWXPayHelper(context.getApplicationContext());
                }
            }
        }
        return b;
    }

    public void addCardCoupons(String str, String str2) {
        APLog.i("APMidawxPayHelper", "addCardCoupons");
        APLog.i("微信支付领取话费券", "cardId=" + str + ";wxsign=" + str2);
        APLog.i("微信支付领取话费券cardExtMsg", str2);
        List arrayList = new ArrayList();
        WXCardItem wXCardItem = new WXCardItem();
        wXCardItem.cardId = str;
        wXCardItem.cardExtMsg = str2;
        arrayList.add(wXCardItem);
        BaseReq req = new Req();
        req.cardArrary = arrayList;
        req.transaction = APMidasPayAPI.WX_COUPONS;
        APLog.i("APMidawxPayHelper", "sendMsg ret:" + this.d.sendReq(req));
    }

    public void addObserver(Handler handler) {
        APLog.i("APMidawxPayHelper", "addObserver observer:" + handler);
        synchronized (this.c) {
            if (!this.c.contains(handler)) {
                this.c.add(handler);
            }
        }
    }

    public int getWXAppSupportAPI() {
        return this.d.getWXAppSupportAPI();
    }

    public void handleIntent(Intent intent) {
        APLog.i("APMidawxPayHelper", "handleIntent intent:" + intent);
        if (this.d != null) {
            this.d.handleIntent(intent, this);
        }
    }

    public boolean isWXinstalled() {
        if (this.d == null) {
            return false;
        }
        boolean isWXAppInstalled = this.d.isWXAppInstalled();
        APLog.i("APMidaWXPayHelper", "isWXinstalled:" + isWXAppInstalled);
        return isWXAppInstalled;
    }

    public boolean isWXsupportApi() {
        return this.d == null ? false : this.d.isWXAppSupportAPI();
    }

    public boolean isWXsupportPayApi() {
        return this.d.getWXAppSupportAPI() >= 570425345;
    }

    public void onReq(BaseReq baseReq) {
    }

    public void onResp(BaseResp baseResp) {
        APLog.i("APMidawxPayHelper", "Helper onResp:" + baseResp.getType());
        APLog.i("APMidawxPayHelper", "Helper onResp, errCode = " + baseResp.errCode);
        synchronized (this.c) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                Handler handler = (Handler) it.next();
                Message message = new Message();
                message.what = 10;
                Bundle bundle = new Bundle();
                bundle.putInt("errCode", baseResp.errCode);
                bundle.putString("errStr", baseResp.errStr);
                bundle.putString("openId", baseResp.openId);
                bundle.putString("transaction", baseResp.transaction);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }
    }

    public void registerApp(String str) {
        if (this.d != null) {
            this.d.registerApp(str);
        }
    }

    public void removeObserver(Handler handler) {
        APLog.i("APMidawxPayHelper", "removeObserver observer:" + handler);
        synchronized (this.c) {
            if (this.c.contains(handler)) {
                this.c.remove(handler);
            }
        }
    }

    public void sendReq(Bundle bundle) {
        BaseReq payReq = new PayReq();
        payReq.appId = bundle.getString("wxAppId");
        payReq.partnerId = bundle.getString("partnerId");
        payReq.prepayId = bundle.getString("prepayId");
        payReq.nonceStr = bundle.getString("nonceStr");
        payReq.timeStamp = bundle.getString("timeStamp");
        payReq.packageValue = bundle.getString("package");
        payReq.sign = bundle.getString("sign");
        payReq.options = new Options();
        payReq.options.callbackClassName = "com.tencent.midas.wx.APMidasWXPayActivity";
        APLog.i("APMidawxPayHelper", "sendReq params:");
        APLog.i("APMidawxPayHelper", "appId:" + payReq.appId);
        APLog.i("APMidawxPayHelper", "partnerId:" + payReq.partnerId);
        APLog.i("APMidawxPayHelper", "prepayId:" + payReq.prepayId);
        APLog.i("APMidawxPayHelper", "nonceStr:" + payReq.nonceStr);
        APLog.i("APMidawxPayHelper", "timeStamp:" + payReq.timeStamp);
        APLog.i("APMidawxPayHelper", "packageValue:" + payReq.packageValue);
        APLog.i("APMidawxPayHelper", "sign:" + payReq.sign);
        this.d.registerApp(payReq.appId);
        this.d.sendReq(payReq);
    }

    public void unRegisterApp() {
        if (this.d != null) {
            this.d.unregisterApp();
        }
    }
}
