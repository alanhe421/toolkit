package com.pay.api;

import com.pay.AndroidPay;
import com.pay.http.APHttpHandle;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.network.model.APMpAns;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.api.APMidasResponse;
import com.tencent.midas.api.IAPMidasNetCallBack;
import com.tencent.midas.api.IAPMidasPayCallBack;
import com.tencent.midas.api.request.APMidasBaseRequest;
import com.tencent.midas.api.request.APMidasGameRequest;
import com.tencent.midas.api.request.APMidasGoodsRequest;
import com.tencent.midas.api.request.APMidasNetRequest;
import com.tencent.midas.comm.APBeanUtil;
import com.tencent.midas.comm.APLog;
import java.util.HashMap;

public class APPayGameService {
    public static final String ACCOUNT_TYPE_COMMON = "common";
    public static final String ACCOUNT_TYPE_SECURITY = "secrety";
    public static final int LOGINPLATFORM_MOBILEQQ = 2;
    public static final int LOGINPLATFORM_WECHAT = 1;
    public static final String PAY_CHANNEL_BANK = "bank";
    public static final String PAY_CHANNEL_WECHAT = "wechat";
    private static IAPPayGameServiceCallBack a = null;
    private static IAPMidasPayCallBack b = new IAPMidasPayCallBack() {
        public void MidasPayCallBack(APMidasResponse aPMidasResponse) {
            if (APPayGameService.a != null) {
                APPayResponseInfo aPPayResponseInfo = new APPayResponseInfo();
                try {
                    APBeanUtil.copyProperties(aPMidasResponse, aPPayResponseInfo);
                } catch (Exception e) {
                    APLog.i("APPayGameService", "midas callBack copyProperties error:" + e.toString());
                }
                APLog.i("midasCallBack", "MidasPayCallBack");
                APPayGameService.a.PayGameServiceCallBack(aPPayResponseInfo);
            }
        }

        public void MidasPayNeedLogin() {
            if (APPayGameService.a != null) {
                APLog.i("midasCallBack", "MidasPayNeedLogin");
                APPayGameService.a.PayGameNeedLogin();
            }
        }
    };

    public static IAPPayGameServiceCallBack GetDelegate() {
        return a;
    }

    public static void LauchVmallView(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        APMidasBaseRequest aPMidasGoodsRequest = new APMidasGoodsRequest();
        a(aPMidasGoodsRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasGoodsRequest.mallType = 2;
        APMidasPayAPI.launchPay(AndroidPay.singleton().fromActivity, aPMidasGoodsRequest, b);
    }

    public static void LaunchGroupBuyView(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        APMidasBaseRequest aPMidasGoodsRequest = new APMidasGoodsRequest();
        a(aPMidasGoodsRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasGoodsRequest.mallType = 1;
        APMidasPayAPI.launchPay(AndroidPay.singleton().fromActivity, aPMidasGoodsRequest, b);
    }

    public static void LaunchMPSaveCurrencyView(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, String str10, String str11, String str12, String str13) {
        APMidasBaseRequest aPMidasGameRequest = new APMidasGameRequest();
        a(aPMidasGameRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasGameRequest.acctType = str8;
        aPMidasGameRequest.saveValue = str9;
        aPMidasGameRequest.resId = i;
        aPMidasGameRequest.mpInfo.payChannel = str10;
        aPMidasGameRequest.mpInfo.discountType = str11;
        aPMidasGameRequest.mpInfo.discountUrl = str12;
        aPMidasGameRequest.mpInfo.extras = str13;
        APMidasPayAPI.launchPay(AndroidPay.singleton().fromActivity, aPMidasGameRequest, b);
    }

    public static void LaunchMPSaveGoodsView(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10, String str11, String str12) {
        APMidasBaseRequest aPMidasGoodsRequest = new APMidasGoodsRequest();
        aPMidasGoodsRequest.tokenType = 1;
        a(aPMidasGoodsRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasGoodsRequest.goodsTokenUrl = str8;
        aPMidasGoodsRequest.resId = i;
        aPMidasGoodsRequest.mpInfo.payChannel = str9;
        aPMidasGoodsRequest.mpInfo.discountType = str10;
        aPMidasGoodsRequest.mpInfo.discountUrl = str11;
        aPMidasGoodsRequest.mpInfo.extras = str12;
        APMidasPayAPI.launchPay(AndroidPay.singleton().fromActivity, aPMidasGoodsRequest, b);
    }

    public static void LaunchMp(String str, String str2, String str3, String str4, String str5, String str6, String str7, IAPHttpAnsObserver iAPHttpAnsObserver) {
        a(str, str2, str3, str4, str5, str6, str7, "", iAPHttpAnsObserver);
    }

    public static void LaunchMp(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, IAPHttpAnsObserver iAPHttpAnsObserver) {
        a(str, str2, str3, str4, str5, str6, str7, str8, iAPHttpAnsObserver);
    }

    public static void LaunchSaveCurrencyView(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        APMidasBaseRequest aPMidasGameRequest = new APMidasGameRequest();
        a(aPMidasGameRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasGameRequest.acctType = str8;
        aPMidasGameRequest.saveValue = "";
        aPMidasGameRequest.isCanChange = true;
        aPMidasGameRequest.resId = i;
        APMidasPayAPI.launchPay(AndroidPay.singleton().fromActivity, aPMidasGameRequest, b);
    }

    public static void LaunchSaveCurrencyView(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, String str9, String str10) {
        APMidasBaseRequest aPMidasGameRequest = new APMidasGameRequest();
        a(aPMidasGameRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasGameRequest.acctType = str8;
        aPMidasGameRequest.saveValue = "";
        aPMidasGameRequest.isCanChange = true;
        aPMidasGameRequest.resId = i;
        aPMidasGameRequest.mpInfo.drmInfo = str9;
        aPMidasGameRequest.mpInfo.discoutId = str10;
        APMidasPayAPI.launchPay(AndroidPay.singleton().fromActivity, aPMidasGameRequest, b);
    }

    public static void LaunchSaveCurrencyView(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, int i) {
        APMidasBaseRequest aPMidasGameRequest = new APMidasGameRequest();
        a(aPMidasGameRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasGameRequest.acctType = str8;
        aPMidasGameRequest.saveValue = str9;
        aPMidasGameRequest.isCanChange = z;
        aPMidasGameRequest.resId = i;
        APMidasPayAPI.launchPay(AndroidPay.singleton().fromActivity, aPMidasGameRequest, b);
    }

    public static void LaunchSaveCurrencyView(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, int i, String str10, String str11) {
        APMidasBaseRequest aPMidasGameRequest = new APMidasGameRequest();
        a(aPMidasGameRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasGameRequest.acctType = str8;
        aPMidasGameRequest.saveValue = str9;
        aPMidasGameRequest.isCanChange = z;
        aPMidasGameRequest.resId = i;
        aPMidasGameRequest.mpInfo.drmInfo = str10;
        aPMidasGameRequest.mpInfo.discoutId = str11;
        APMidasPayAPI.launchPay(AndroidPay.singleton().fromActivity, aPMidasGameRequest, b);
    }

    public static void LaunchSaveGoodsView(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        APMidasBaseRequest aPMidasGoodsRequest = new APMidasGoodsRequest();
        aPMidasGoodsRequest.tokenType = 1;
        a(aPMidasGoodsRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasGoodsRequest.goodsTokenUrl = str8;
        aPMidasGoodsRequest.resId = i;
        APMidasPayAPI.launchPay(AndroidPay.singleton().fromActivity, aPMidasGoodsRequest, b);
    }

    public static void LaunchSaveGoodsViewWithoutToken(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z, String str10) {
        APMidasBaseRequest aPMidasGoodsRequest = new APMidasGoodsRequest();
        aPMidasGoodsRequest.tokenType = 2;
        a(aPMidasGoodsRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasGoodsRequest.prodcutId = str8;
        aPMidasGoodsRequest.saveValue = str9;
        aPMidasGoodsRequest.isCanChange = z;
        APMidasPayAPI.launchPay(AndroidPay.singleton().fromActivity, aPMidasGoodsRequest, b);
    }

    public static void SetDelegate(IAPPayGameServiceCallBack iAPPayGameServiceCallBack) {
        a = iAPPayGameServiceCallBack;
    }

    @Deprecated
    public static void SetNeedReloginInSDK(boolean z) {
    }

    private static void a(APMidasBaseRequest aPMidasBaseRequest, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        aPMidasBaseRequest.offerId = AndroidPay.singleton().offerId;
        aPMidasBaseRequest.openId = str;
        aPMidasBaseRequest.openKey = str2;
        aPMidasBaseRequest.sessionId = str3;
        aPMidasBaseRequest.sessionType = str4;
        aPMidasBaseRequest.zoneId = str5;
        aPMidasBaseRequest.pf = str6;
        aPMidasBaseRequest.pfKey = str7;
        aPMidasBaseRequest.extendInfo.unit = AndroidPay.singleton().unit;
        aPMidasBaseRequest.extendInfo.isShowNum = AndroidPay.singleton().isShowNum;
        aPMidasBaseRequest.resData = AndroidPay.singleton().resdata;
        aPMidasBaseRequest.extendInfo.isShowListOtherNum = AndroidPay.singleton().isShowListOtherNum;
    }

    private static void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, IAPHttpAnsObserver iAPHttpAnsObserver) {
        APMidasBaseRequest aPMidasNetRequest = new APMidasNetRequest();
        a(aPMidasNetRequest, str, str2, str3, str4, str5, str6, str7);
        aPMidasNetRequest.reqType = APMidasNetRequest.NET_REQ_MP;
        if (str8 != null || "".equals(str8)) {
            aPMidasNetRequest.mpInfo.drmInfo = str8;
        }
        final APMpAns aPMpAns = new APMpAns(APHttpHandle.getIntanceHandel(), iAPHttpAnsObserver, new HashMap(), APMidasNetRequest.NET_REQ_MP);
        final IAPHttpAnsObserver iAPHttpAnsObserver2 = iAPHttpAnsObserver;
        APMidasPayAPI.launchNet(AndroidPay.singleton().fromActivity, aPMidasNetRequest, new IAPMidasNetCallBack() {
            public void MidasNetError(String str, int i, String str2) {
                aPMpAns.resultCode = i;
                aPMpAns.resultMsg = str2;
                iAPHttpAnsObserver2.onError(aPMpAns);
            }

            public void MidasNetFinish(String str, String str2) {
                aPMpAns.onFinishAns(str2.getBytes(), null);
                iAPHttpAnsObserver2.onFinish(aPMpAns);
            }

            public void MidasNetStop(String str) {
                iAPHttpAnsObserver2.onStop(aPMpAns);
            }
        });
    }

    public static void release() {
        a = null;
    }

    @Deprecated
    public static void reportCrashApLog(Throwable th) {
    }
}
