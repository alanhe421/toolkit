package com.tencent.timint;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.TIMCallBack;
import com.tencent.TIMConnListener;
import com.tencent.TIMLogLevel;
import com.tencent.TIMManager;
import com.tencent.TIMNetworkStatus;
import com.tencent.TIMUser;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.av.MultiVideoTinyId;
import com.tencent.imsdk.av.SharpTinyId;
import com.tencent.imsdk.userid.TIMTinyIdToUserId;
import com.tencent.imsdk.userid.TIMUserIdToTinyId;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.openqq.protocol.imsdk.quality_report.ReqBody;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TIMIntManager {
    static TIMIntManager inst = new TIMIntManager();
    private static IMMsfCoreProxy msfCoreProxy = IMMsfCoreProxy.get();
    private static final String tag = "MSF.C.TIMIntManager";
    private TIMConnListener connListener = null;
    DBHelper dbhelper = new DBHelper(msfCoreProxy.getContext(), "QualityReport.db");
    private ExecutorService pool = Executors.newFixedThreadPool(1);

    private TIMIntManager() {
    }

    public static TIMIntManager getInstance() {
        return inst;
    }

    private void resendLocalQualityReport(String str) {
        QLog.d(tag, 1, "start to resend local quality report");
        this.pool.execute(new ab(this, str));
    }

    private void storeQualityReportToLocal(ReqBody reqBody) {
        QLog.d(tag, 1, "store quality report to local");
        this.pool.execute(new aa(this, reqBody));
    }

    public TIMConnListener getConnectionListener() {
        return this.connListener;
    }

    int getDeviceType() {
        Context context = msfCoreProxy.getContext();
        if (context == null) {
            return 2;
        }
        int i = (((TelephonyManager) context.getSystemService("phone")).getPhoneType() == 0 || (context.getResources().getConfiguration().screenLayout & 15) >= 3) ? 3 : 2;
        return i;
    }

    public TIMNetworkStatus getNetworkStatus() {
        return IMMsfCoreProxy.get().getNetworkStatus();
    }

    int getNetworkType() {
        Context context = msfCoreProxy.getContext();
        if (context == null) {
            return 0;
        }
        int i;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            i = 0;
        } else {
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                i = 5;
            } else {
                if (type == 0) {
                    if (TextUtils.isEmpty(Proxy.getDefaultHost())) {
                        switch (activeNetworkInfo.getSubtype()) {
                            case 1:
                            case 2:
                            case 4:
                            case 7:
                            case 11:
                                i = 2;
                                break;
                            case 3:
                            case 5:
                            case 6:
                            case 8:
                            case 9:
                            case 10:
                            case 12:
                            case 14:
                            case 15:
                                i = 3;
                                break;
                            case 13:
                                i = 4;
                                break;
                        }
                    }
                    i = 1;
                }
                i = 6;
            }
        }
        return i;
    }

    public long getTinyId() {
        Object loginUser = TIMManager.getInstance().getLoginUser();
        QLog.i(tag, 1, "getTinyId, identifier: " + loginUser);
        if (TextUtils.isEmpty(loginUser)) {
            return 0;
        }
        IMMsfUserInfo msfUserInfo = IMMsfCoreProxy.get().getMsfUserInfo(loginUser);
        if (msfUserInfo != null && msfUserInfo.isLoggedIn()) {
            return msfUserInfo.getTinyid();
        }
        QLog.e(tag, 1, "getTinyId, user not online");
        return 0;
    }

    public void logBugly(TIMLogLevel tIMLogLevel, String str, String str2) {
        IMMsfCoreProxy.get().logBugly(tIMLogLevel, str, str2);
    }

    public void request(String str, byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack) {
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null || !anyOnLineMsfUserInfo.isLoggedIn()) {
            QLog.e(tag, 1, "TIMIntManager|request no user online");
            anyOnLineMsfUserInfo = new IMMsfUserInfo();
        }
        msfCoreProxy.request(anyOnLineMsfUserInfo.getUserId(), str, bArr, tIMValueCallBack);
    }

    public void request(String str, byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack, long j) {
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null || !anyOnLineMsfUserInfo.isLoggedIn()) {
            QLog.e(tag, 1, "TIMIntManager|request no user online");
            anyOnLineMsfUserInfo = new IMMsfUserInfo();
        }
        msfCoreProxy.request(anyOnLineMsfUserInfo.getUserId(), str, bArr, tIMValueCallBack, j);
    }

    public void requestMultiVideoApp(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack) {
        MultiVideoTinyId.get().requestMultiVideoApp(bArr, tIMValueCallBack, 0);
    }

    public void requestMultiVideoApp(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack, long j) {
        MultiVideoTinyId.get().requestMultiVideoApp(bArr, tIMValueCallBack, j);
    }

    public void requestMultiVideoInfo(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack) {
        MultiVideoTinyId.get().requestMultiVideoInfo(bArr, tIMValueCallBack, 0);
    }

    public void requestMultiVideoInfo(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack, long j) {
        MultiVideoTinyId.get().requestMultiVideoInfo(bArr, tIMValueCallBack, j);
    }

    public void requestQualityReport(int i, byte[] bArr, TIMCallBack tIMCallBack) {
        QLog.d(tag, 1, "quality report, type: " + i + ", data: " + bArr);
        if (tIMCallBack != null) {
            if (bArr == null) {
                QLog.e(tag, 1, "invalid param");
                tIMCallBack.onError(6017, "invalid param");
            } else if (msfCoreProxy.inited) {
                ReqBody reqBody = new ReqBody();
                reqBody.uint32_sdkappid.set(msfCoreProxy.getSdkAppId());
                reqBody.uint64_from_uin.set(getTinyId());
                reqBody.uint32_timestamp.set(((int) System.currentTimeMillis()) / 1000);
                reqBody.uint32_seq.set(msfCoreProxy.random.nextInt());
                reqBody.msg_common_info.setHasFlag(true);
                reqBody.msg_common_info.msg_device_info.setHasFlag(true);
                reqBody.msg_common_info.msg_device_info.enum_device_type.set(getDeviceType());
                reqBody.msg_common_info.msg_device_info.str_device_board.set(Build.BOARD);
                reqBody.msg_common_info.msg_device_info.str_device_brand.set(Build.BRAND);
                reqBody.msg_common_info.msg_device_info.str_device_model.set(Build.MODEL);
                reqBody.msg_common_info.msg_device_info.str_device_cpu_abi.set(Build.CPU_ABI);
                reqBody.msg_common_info.msg_system_info.setHasFlag(true);
                reqBody.msg_common_info.msg_system_info.enum_os_type.set(1);
                reqBody.msg_common_info.msg_system_info.str_os_version.set(VERSION.RELEASE);
                reqBody.msg_common_info.msg_network_info.setHasFlag(true);
                reqBody.msg_common_info.msg_network_info.enum_network_type.set(getNetworkType());
                reqBody.msg_report_content.setHasFlag(true);
                reqBody.msg_report_content.uint32_type.set(i);
                reqBody.msg_report_content.bytes_report_data.set(ByteStringMicro.copyFrom(bArr));
                request("AVQualityReportSvc.C2S", reqBody.toByteArray(), new ad(this, tIMCallBack, reqBody));
            } else {
                QLog.e(tag, 1, "sdk not initialized");
                tIMCallBack.onError(6013, "sdk not initialized");
            }
        }
    }

    public void requestSharpSvr(long j, byte[] bArr, TIMCallBack tIMCallBack) {
        SharpTinyId.get().requestSharpSvr(j, bArr, tIMCallBack, 0);
    }

    public void requestSharpSvr(long j, byte[] bArr, TIMCallBack tIMCallBack, long j2) {
        SharpTinyId.get().requestSharpSvr(j, bArr, tIMCallBack, j2);
    }

    public void responseSharpSvr(long j, byte[] bArr, TIMCallBack tIMCallBack) {
        SharpTinyId.get().requestSharpSvr(j, bArr, tIMCallBack, 0);
    }

    public void responseSharpSvr(long j, byte[] bArr, TIMCallBack tIMCallBack, long j2) {
        SharpTinyId.get().requestSharpSvr(j, bArr, tIMCallBack, j2);
    }

    public void setAvSDKVersionToBugly(String str, String str2) {
        IMMsfCoreProxy.get().setAvSDKVersionToBugly(str, str2);
    }

    public void setBuglyDebugMode(boolean z) {
        IMMsfCoreProxy.get().enableBuglyDebugMode(z);
    }

    public void setConnectionListener(TIMConnListener tIMConnListener) {
        this.connListener = tIMConnListener;
    }

    public void setReqTimeout(long j) {
        msfCoreProxy.setReqTimeout(j);
    }

    public void setSharpSvrPushCallBack(TIMValueCallBack<byte[]> tIMValueCallBack) {
        SharpTinyId.get().setSharpSvrPushCallBack(tIMValueCallBack);
    }

    public void setSharpSvrRspCallBack(TIMValueCallBack<byte[]> tIMValueCallBack) {
        SharpTinyId.get().setSharpSvrRspCallBack(tIMValueCallBack);
    }

    public void tinyIdToUserId(List<Long> list, TIMValueCallBack<List<TIMUser>> tIMValueCallBack) {
        tinyIdToUserId(list, tIMValueCallBack, 0);
    }

    public void tinyIdToUserId(List<Long> list, TIMValueCallBack<List<TIMUser>> tIMValueCallBack, long j) {
        TIMTinyIdToUserId.get().tinyIdToUserId(list, tIMValueCallBack, j);
    }

    public void userIdToTinyId(List<String> list, TIMValueCallBack<List<TIMUser>> tIMValueCallBack) {
        userIdToTinyId(list, tIMValueCallBack, 0);
    }

    public void userIdToTinyId(List<String> list, TIMValueCallBack<List<TIMUser>> tIMValueCallBack, long j) {
        List arrayList = new ArrayList();
        for (String str : list) {
            TIMUser tIMUser = new TIMUser();
            tIMUser.setAccountType(IMMsfCoreProxy.get().getUidType());
            tIMUser.setAppIdAt3rd(String.valueOf(IMMsfCoreProxy.get().getSdkAppId()));
            tIMUser.setIdentifier(str);
            arrayList.add(tIMUser);
        }
        TIMUserIdToTinyId.get().userIdToTinyId(arrayList, tIMValueCallBack, j);
    }
}
