package com.tencent.midas.data;

import android.text.TextUtils;
import com.pay.http.APBaseHttpAns;
import com.pay.http.APNetworkManager;
import com.pay.http.IAPHttpAnsObserver;
import com.pay.tool.APMidasTools;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.api.request.APMidasBaseRequest;
import com.tencent.midas.comm.APLog;
import java.util.ArrayList;

public class APPluginReportManager {
    public static final String MIDASPLUGIN_FORMAT_APKLOAD_ERROR = "sdk.loadapk_error";
    public static final String MIDASPLUGIN_FORMAT_APKLOAD_FAIL = "sdk.loadapk_fail";
    public static final String MIDASPLUGIN_FORMAT_TIME = "sdk.plugin.time";
    public static final String MIDASPLUGIN_TIMENAME_GET_FILELIST_FROM_ASSETS = "sdk.plugin.init.getFileListFromAssets.time";
    public static final String MIDASPLUGIN_TIMENAME_INIT = "timename.init";
    public static final String MIDASPLUGIN_TIMENAME_INIT_KERNEL = "sdk.plugin.init.kernel.totaltime";
    public static final String MIDASPLUGIN_TIMENAME_INIT_TOTALTIME = "sdk.plugin.init.totaltime";
    public static final String MIDASPLUGIN_TIMENAME_INSTALL_FROM_ASSETS = "sdk.plugin.init.installFromAssets.time";
    public static final String MIDASPLUGIN_TIMENAME_INSTALL_FROM_LOCAL = "sdk.plugin.init.installFromLocal.time";
    public static final String MIDASPLUGIN_TIMENAME_IS_NEED_ASSETS_UPDATE = "sdk.plugin.init.isNeedAssetsUpdate.time";
    public static final String MIDASPLUGIN_TIMENAME_IS_NEED_LOCAL_UPDATE = "sdk.plugin.init.isNeedLocalUpdate.time";
    public static final String MIDASPLUGIN_TIMENAME_LAUNCHINFO = "timename.launchinfo";
    public static final String MIDASPLUGIN_TIMENAME_LAUNCHNET = "timename.launchnet";
    public static final String MIDASPLUGIN_TIMENAME_LAUNCHPAY = "timename.launchpay";
    public static final String MIDASPLUGIN_TIMENAME_LAUNCHPAY_WAIT_INIT = "sdk.plugin.launchPay.wait.init.time";
    public static final String MIDASPLUGIN_TIMENAME_LAUNCHWEB = "timename.launchweb";
    public static final String MIDASPLUGIN_TIMENAME_LOAD_DEX = "sdk.plugin.init.loadDex.time";
    public static final String MIDASPLUGIN_TIMENAME_PLUGIN_VALID = "sdk.plugin.init.pluginvalid.time";
    public static final String MIDASPLUGIN_TIMENAME_READ_FILE_FROM_ASSETS = "sdk.plugin.init.readFileFromAssets.time";
    public static final String MIDASPLUGIN_TIMENAME_UNZIP_SO = "sdk.plugin.init.unzip.so.time";
    public static final String MIDASPLUGIN_TIMENAME_WRITE_FILE_TO_DATA = "sdk.plugin.init.writeFileToData.time";
    private static APPluginReportManager c = null;
    ArrayList<APClickStreamParams> a;
    ArrayList<APClickStreamParams> b;

    private APPluginReportManager() {
        this.a = null;
        this.b = null;
        this.a = new ArrayList();
        this.b = new ArrayList();
    }

    private String a(String str, long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("name=");
        stringBuffer.append(str);
        stringBuffer.append("&");
        stringBuffer.append("times=");
        stringBuffer.append(j);
        stringBuffer.append("&");
        return stringBuffer.toString();
    }

    private String a(ArrayList<APClickStreamParams> arrayList) {
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3 = i2 + 1;
            stringBuffer.append("record" + i + "=");
            stringBuffer.append(a((APClickStreamParams) arrayList.get(i)));
            stringBuffer.append("&");
            i++;
            i2 = i3;
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("num=");
        stringBuffer2.append(i2);
        stringBuffer2.append("&");
        stringBuffer2.append(stringBuffer.toString());
        stringBuffer.setLength(0);
        return stringBuffer2.toString();
    }

    private StringBuffer a(APClickStreamParams aPClickStreamParams) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("3=" + aPClickStreamParams.openid);
        stringBuffer.append("|7=0");
        stringBuffer.append("|13=" + aPClickStreamParams.dataId);
        stringBuffer.append("|24=" + aPClickStreamParams.offerid);
        if (!TextUtils.isEmpty(aPClickStreamParams.payid)) {
            stringBuffer.append("|4=" + aPClickStreamParams.payid);
        }
        if (!TextUtils.isEmpty(aPClickStreamParams.isBindQQ)) {
            stringBuffer.append("|55=" + aPClickStreamParams.isBindQQ);
        }
        stringBuffer.append("|21=" + aPClickStreamParams.format);
        stringBuffer.append("|26=" + aPClickStreamParams.pf);
        if (!TextUtils.isEmpty(aPClickStreamParams.token)) {
            stringBuffer.append("|56=" + aPClickStreamParams.token);
        }
        APLog.i("getLogRecord extend pre", aPClickStreamParams.extend);
        if (!TextUtils.isEmpty(aPClickStreamParams.extend)) {
            stringBuffer.append("|8=" + aPClickStreamParams.extend);
        }
        if (!TextUtils.isEmpty(aPClickStreamParams.from)) {
            stringBuffer.append("|20=" + aPClickStreamParams.from);
        }
        if (!TextUtils.isEmpty(aPClickStreamParams.savetype)) {
            stringBuffer.append("|47=" + aPClickStreamParams.savetype);
        }
        stringBuffer.append("|29=" + aPClickStreamParams.guid);
        stringBuffer.append("|31=" + aPClickStreamParams.device);
        stringBuffer.append("|38=" + aPClickStreamParams.currentTimeMillis);
        stringBuffer.append("|34=" + aPClickStreamParams.uinTypeFromSvr);
        stringBuffer.append("|35=" + aPClickStreamParams.uinFromSvr);
        stringBuffer.append("|37=" + aPClickStreamParams.SessionId);
        stringBuffer.append("|43=" + aPClickStreamParams.SessionType);
        if (!TextUtils.isEmpty(aPClickStreamParams.PayLevel)) {
            stringBuffer.append("|54=" + aPClickStreamParams.PayLevel);
        }
        if (!TextUtils.isEmpty(aPClickStreamParams.VipFlags)) {
            stringBuffer.append("|53=" + aPClickStreamParams.VipFlags);
        }
        return stringBuffer;
    }

    private void a(String str, String str2, String str3, String str4) {
        APClickStreamParams aPClickStreamParams = new APClickStreamParams();
        aPClickStreamParams.device = "android_v" + APMidasPayAPI.getMidasPluginVersion();
        aPClickStreamParams.openid = APPluginDataInterface.singleton().getOpenId();
        aPClickStreamParams.format = str2;
        aPClickStreamParams.from = str3;
        aPClickStreamParams.offerid = APPluginDataInterface.singleton().getOfferId();
        aPClickStreamParams.pf = APPluginDataInterface.singleton().getPf();
        aPClickStreamParams.SessionId = APPluginDataInterface.singleton().getSessionId();
        aPClickStreamParams.SessionType = APPluginDataInterface.singleton().getSessionType();
        if (!TextUtils.isEmpty(str4)) {
            aPClickStreamParams.extend = APMidasTools.urlEncode(str4, 3);
        }
        switch (APPluginDataInterface.singleton().getSaveType()) {
            case 0:
                aPClickStreamParams.savetype = "game";
                break;
            case 1:
                aPClickStreamParams.savetype = "goods";
                break;
            case 2:
            case 3:
                aPClickStreamParams.savetype = "acct";
                break;
            case 4:
                aPClickStreamParams.savetype = "month";
                break;
            case 5:
                aPClickStreamParams.savetype = "subscribe";
                break;
            default:
                aPClickStreamParams.savetype = "game";
                break;
        }
        aPClickStreamParams.currentTimeMillis = String.valueOf(System.currentTimeMillis());
        if (str.equals(APMidasPluginInfo.LAUNCH_INTERFACE_INIT)) {
            aPClickStreamParams.dataId = APDataId.getInstance().getInitDataId();
            aPClickStreamParams.VipFlags = APInitData.singleton().getInitGUID();
            this.a.add(aPClickStreamParams);
            return;
        }
        aPClickStreamParams.dataId = APDataId.getInstance().getDataId();
        aPClickStreamParams.VipFlags = APPluginDataInterface.singleton().getGuid();
        this.b.add(aPClickStreamParams);
    }

    public static APPluginReportManager getInstance() {
        if (c == null) {
            c = new APPluginReportManager();
        }
        return c;
    }

    public static void initDataRelease() {
        try {
            if (c != null) {
                c.a.clear();
            }
        } catch (Exception e) {
        }
    }

    public static void payDataRelease() {
        try {
            if (c != null) {
                c.b.clear();
            }
        } catch (Exception e) {
        }
    }

    public void dataReport(String str) {
        String str2 = "";
        Object a = str.equals(APMidasPluginInfo.LAUNCH_INTERFACE_INIT) ? a(this.a) : a(this.b);
        if (!TextUtils.isEmpty(a)) {
            APNetworkManager.getInstance().dataReport(a, new IAPHttpAnsObserver(this) {
                final /* synthetic */ APPluginReportManager a;

                {
                    this.a = r1;
                }

                public void onError(APBaseHttpAns aPBaseHttpAns) {
                }

                public void onFinish(APBaseHttpAns aPBaseHttpAns) {
                }

                public void onStop(APBaseHttpAns aPBaseHttpAns) {
                }
            });
        }
    }

    public void initInterfaceInit(String str, APMidasBaseRequest aPMidasBaseRequest) {
        APInitData.init();
        APInitData.singleton().setInitGUID(APMidasTools.getUUID());
        APInitData.singleton().setInitInterfaceTime(System.currentTimeMillis());
        APPluginDataInterface.init();
        APPluginDataInterface.singleton().setLaunchInterface(str);
        APMidasAnalyzeParams.getInstance().AnalyzeParams(aPMidasBaseRequest);
    }

    public void insertData(String str, String str2, String str3, String str4) {
        APLog.i("insertTimeData interfaceName=", str + " format=" + str2 + " action=" + str3 + " extend=" + str4);
        a(str, str2, str3, str4);
    }

    public void insertTimeData(String str, String str2) {
        APLog.i("insertTimeData interfaceName=", str);
        APLog.i("insertTimeData timeName=", str2);
        if (!TextUtils.isEmpty(APPluginDataInterface.singleton().getOfferId())) {
            long currentTimeMillis;
            if (str == APMidasPluginInfo.LAUNCH_INTERFACE_INIT) {
                currentTimeMillis = System.currentTimeMillis() - APInitData.singleton().getInitInterfaceTime();
                APLog.i("时耗", "insertTimeData timeName=" + str2 + ",initTime:" + currentTimeMillis);
                a(str, MIDASPLUGIN_FORMAT_TIME, "", a(str2, currentTimeMillis));
                return;
            }
            currentTimeMillis = System.currentTimeMillis() - APPluginDataInterface.singleton().getPayInterfaceTime();
            APLog.i("时耗", "insertTimeData timeName=" + str2 + ",payTime:" + currentTimeMillis);
            a(str, MIDASPLUGIN_FORMAT_TIME, "", a(str2, currentTimeMillis));
        }
    }

    public void insertTimeData(String str, String str2, long j) {
        APLog.d("insertTimeData interfaceName=", str + " timeName=" + str2 + " time=" + String.valueOf(j));
        if (!TextUtils.isEmpty(APPluginDataInterface.singleton().getOfferId())) {
            if (str == APMidasPluginInfo.LAUNCH_INTERFACE_INIT) {
                a(str, MIDASPLUGIN_FORMAT_TIME, "", a(str2, j));
            } else {
                a(str, MIDASPLUGIN_FORMAT_TIME, "", a(str2, j));
            }
        }
    }

    public void insertTimeDataEx(String str, String str2, long j) {
        long timeInterval = APMidasTools.getTimeInterval(j, System.currentTimeMillis());
        APLog.d("insertTimeDataEx", "timeName:" + str2 + ",time" + timeInterval);
        insertTimeData(str, str2, timeInterval);
    }

    public void payInterfaceInit(APMidasBaseRequest aPMidasBaseRequest, String str) {
        APPluginDataInterface.init();
        APPluginDataInterface.singleton().setGuid(APMidasTools.getUUID());
        APPluginDataInterface.singleton().setPayInterfaceTime(System.currentTimeMillis());
        APPluginDataInterface.singleton().setLaunchInterface(str);
        APMidasAnalyzeParams.getInstance().setSaveType(aPMidasBaseRequest);
        APMidasAnalyzeParams.getInstance().AnalyzeParams(aPMidasBaseRequest);
    }
}
