package com.hmt.analytics.common;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.location.LocationManager;
import android.os.Build;
import com.hmt.analytics.dao.GetInfoFromFile;
import com.hmt.analytics.dao.SaveInfoExec;
import com.hmt.analytics.interfaces.HMTCallback;
import com.hmt.analytics.objects.LatitudeAndLongitude;
import com.hmt.analytics.objects.ParamList;
import com.hmt.analytics.objects.SCell;
import com.hmt.analytics.util.HParams;
import com.hmt.analytics.util.SPUtils;
import com.hmt.analytics.util.ThreadPoolUtils;
import com.tencent.mid.api.MidEntity;
import com.tencent.qalsdk.core.c;
import com.tencent.qalsdk.sdk.v;
import com.tencent.util.TimeFormatterUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HMTTool {
    public static boolean isTrackedurl(String str) {
        if (HMTConstants.k == null) {
            return false;
        }
        String domain = getDomain(str);
        for (int i = 0; i < HMTConstants.k.length; i++) {
            if (HMTConstants.k[i].equals(v.n)) {
                return true;
            }
            if (domain.equals(HMTConstants.k[i])) {
                return true;
            }
            if (domain.endsWith(HMTConstants.k[i])) {
                int lastIndexOf = domain.lastIndexOf(HMTConstants.k[i]);
                if (domain.substring(lastIndexOf - 1, lastIndexOf).equals(".")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void setTrackedurl(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null) {
                    String string = applicationInfo.metaData.getString("HMT_TRACKEDURL");
                    if (string != null) {
                        HMTConstants.k = string.split(",");
                    } else {
                        CommonUtil.printLog("HMTAgent", "Could not read HMT_TRACKEDURL meta-data from AndroidManifest.xml.");
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public static String getDomain(String str) {
        String str2 = "";
        if (str.startsWith(c.d)) {
            try {
                str2 = new URL(str).getHost();
            } catch (MalformedURLException e) {
                CommonUtil.printLog("HMTTool", new StringBuilder(String.valueOf(str)).append(" domain 获取失败").toString());
            }
        }
        return str2;
    }

    public static void sendData(Context context, JSONObject jSONObject, String str, String str2, String str3, String str4, HMTCallback hMTCallback) {
        saveInfoToFile(str2, jSONObject, context, str3);
        if (1 == CommonUtil.getReportPolicyMode(context) && CommonUtil.isNetworkAvailable(context)) {
            ThreadPoolUtils.getSingleInstance().execute(new GetInfoFromFile(context));
        }
        if (hMTCallback != null) {
            hMTCallback.callback();
        }
    }

    public static void saveInfoToFile(String str, JSONObject jSONObject, Context context, String str2) {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(str, jSONArray);
            SaveInfoExec.saveExec(context, jSONObject2, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject addProperty(JSONObject jSONObject, HParams hParams) {
        if (hParams != null) {
            JSONObject params = hParams.getParams();
            Iterator keys = params.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                try {
                    Object obj = params.get(str);
                    if (obj instanceof JSONArray) {
                        jSONObject.put("p_" + str, obj);
                    } else {
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.put(obj);
                        jSONObject.put("p_" + str, jSONArray);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONObject;
    }

    public static void preSendCallback(String str) {
        if (HMTConstants.t != null) {
            HMTConstants.t.preSend(str);
        }
    }

    public static void sendSuccessCallback(String str) {
        if (HMTConstants.t != null) {
            HMTConstants.t.sendSuccess(str);
        }
    }

    public static void sendFailCallback(String str, int i) {
        if (HMTConstants.t != null) {
            HMTConstants.t.sendFail(str, i);
        }
    }

    private static String generateIRSUID(Context context, String str) {
        int i = 0;
        String str2 = "";
        String md5Appkey = MD5Utility.md5Appkey(CommonUtil.get_imei(context) + CommonUtil.get_mac(context) + CommonUtil.getMuid(context) + str + new Random().nextInt(100000000));
        char[] toCharArray = md5Appkey.toCharArray();
        int i2 = 0;
        while (i < toCharArray.length) {
            i2 += toCharArray[i];
            i++;
        }
        Object toHexString = Integer.toHexString(i2 % 256);
        if (toHexString.length() < 2) {
            toHexString = "0" + toHexString;
        }
        String stringBuilder = new StringBuilder(String.valueOf(toHexString)).append(md5Appkey).toString();
        SPUtils.put(context, "hmt_irsuid", "irsuid_id", stringBuilder);
        SPUtils.put(context, "hmt_irsuid", "irsuid_save_time", str);
        return stringBuilder;
    }

    public static String getIRSUID(Context context) {
        return (String) SPUtils.get(context, "hmt_irsuid", "irsuid_id", "");
    }

    public static synchronized void isCreateNewIRSUID(Context context) {
        synchronized (HMTTool.class) {
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String str = (String) SPUtils.get(context, "hmt_irsuid", "irsuid_save_time", "");
            if (!format.equals(str) || str.equals("")) {
                generateIRSUID(context, format);
            }
        }
    }

    public static synchronized boolean isSendIRSUID(Context context) {
        boolean z;
        synchronized (HMTTool.class) {
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String str = (String) SPUtils.get(context, "hmt_irsuid", "irsuid_send_time", "");
            if (!format.equals(str) || str.equals("")) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public static boolean isToDay(long j, long j2) {
        long j3 = j - j2;
        return j3 < TimeFormatterUtils.ONE_DAY && j3 > -86400000 && toDay(j) == toDay(j2);
    }

    private static long toDay(long j) {
        return (((long) TimeZone.getDefault().getOffset(j)) + j) / TimeFormatterUtils.ONE_DAY;
    }

    public static boolean isSendEveryDayClientData(Context context) {
        return !isToDay(System.currentTimeMillis(), ((Long) SPUtils.get(context, "hmt_init_savetime", "upload_save_time", Long.valueOf(0))).longValue());
    }

    public static JSONObject getClientDataJSONObject(Context context) {
        JSONException jSONException;
        JSONObject jSONObject;
        JSONException jSONException2;
        Exception exception;
        Exception exception2;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2 = ParamList.getParamList(context, "client_data");
            try {
                String str;
                boolean z;
                jSONObject2.put("producer", Build.PRODUCT);
                jSONObject2.put("manufacturer", Build.MANUFACTURER);
                jSONObject2.put("model", Build.MODEL);
                String[] unTracked = CommonUtil.getUnTracked(context);
                if (!CommonUtil.isUnTracked(unTracked, "package_name").booleanValue()) {
                    jSONObject2.put("package_name", context.getPackageName());
                }
                if (!CommonUtil.isUnTracked(unTracked, "_openudid").booleanValue()) {
                    jSONObject2.put("_openudid", CommonUtil.getOpenUdId(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, "_imei").booleanValue()) {
                    jSONObject2.put("_imei", CommonUtil.get_imei(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, "_androidid").booleanValue()) {
                    jSONObject2.put("_androidid", CommonUtil.getAndroidId(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, "_mac").booleanValue()) {
                    jSONObject2.put("_mac", CommonUtil.get_mac(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, "mccmnc").booleanValue()) {
                    SCell cellInfo = CommonUtil.getCellInfo(context);
                    if (!CommonUtil.isUnTracked(unTracked, "mccmnc").booleanValue()) {
                        Object obj;
                        str = "mccmnc";
                        if (cellInfo != null) {
                            obj = cellInfo.b;
                        } else {
                            obj = "";
                        }
                        jSONObject2.put(str, obj);
                    }
                }
                if (!CommonUtil.isUnTracked(unTracked, "phone_type").booleanValue()) {
                    jSONObject2.put("phone_type", CommonUtil.getPhoneType(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, "have_bt").booleanValue()) {
                    str = "have_bt";
                    if (BluetoothAdapter.getDefaultAdapter() == null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    jSONObject2.put(str, z);
                }
                if (!CommonUtil.isUnTracked(unTracked, "have_gps").booleanValue()) {
                    str = "have_gps";
                    if (((LocationManager) context.getSystemService("location")) == null) {
                        z = false;
                    } else {
                        z = true;
                    }
                    jSONObject2.put(str, z);
                }
                if (!CommonUtil.isUnTracked(unTracked, "have_gravity").booleanValue()) {
                    jSONObject2.put("have_gravity", CommonUtil.isHaveGravity(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, MidEntity.TAG_IMSI).booleanValue()) {
                    jSONObject2.put(MidEntity.TAG_IMSI, CommonUtil.getImsi(context));
                }
                if (!CommonUtil.isUnTracked(unTracked, "is_mobile_device").booleanValue()) {
                    jSONObject2.put("is_mobile_device", true);
                }
                if (!CommonUtil.isUnTracked(unTracked, "is_jail_break").booleanValue()) {
                    jSONObject2.put("is_jail_break", CommonUtil.isRoot());
                }
                try {
                    if (!(CommonUtil.isUnTracked(unTracked, "cell_id").booleanValue() && CommonUtil.isUnTracked(unTracked, "mccmnc").booleanValue() && CommonUtil.isUnTracked(unTracked, "lac").booleanValue())) {
                        SCell cellInfo2 = CommonUtil.getCellInfo(context);
                        if (!CommonUtil.isUnTracked(unTracked, "cell_id").booleanValue()) {
                            jSONObject2.put("cell_id", cellInfo2 != null ? new StringBuilder(String.valueOf(cellInfo2.e)).toString() : "");
                        }
                        if (!CommonUtil.isUnTracked(unTracked, "lac").booleanValue()) {
                            jSONObject2.put("lac", cellInfo2 != null ? new StringBuilder(String.valueOf(cellInfo2.d)).toString() : "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                    jSONException = e2;
                    jSONObject = jSONObject2;
                    jSONException2 = jSONException;
                    jSONException2.printStackTrace();
                    return jSONObject;
                }
                if (!(CommonUtil.isUnTracked(unTracked, "lon").booleanValue() && CommonUtil.isUnTracked(unTracked, "lat").booleanValue())) {
                    LatitudeAndLongitude latitudeAndLongitude = CommonUtil.getLatitudeAndLongitude(context, HMTConstants.v);
                    if (!CommonUtil.isUnTracked(unTracked, "lon").booleanValue()) {
                        jSONObject2.put("lon", latitudeAndLongitude.b);
                    }
                    if (!CommonUtil.isUnTracked(unTracked, "lat").booleanValue()) {
                        jSONObject2.put("lat", latitudeAndLongitude.a);
                    }
                }
                if (!CommonUtil.isUnTracked(unTracked, "network").booleanValue()) {
                    jSONObject2.put("network", CommonUtil.getNetworkTypeWIFI2G3G(context));
                }
                if (CommonUtil.isUnTracked(unTracked, "have_wifi").booleanValue()) {
                    return jSONObject2;
                }
                jSONObject2.put("have_wifi", CommonUtil.isWiFiActive(context));
                return jSONObject2;
            } catch (JSONException e22) {
                jSONException = e22;
                jSONObject = jSONObject2;
                jSONException2 = jSONException;
                jSONException2.printStackTrace();
                return jSONObject;
            } catch (Exception e3) {
                exception = e3;
                jSONObject = jSONObject2;
                exception2 = exception;
                exception2.printStackTrace();
                return jSONObject;
            }
        } catch (JSONException e222) {
            jSONException = e222;
            jSONObject = jSONObject2;
            jSONException2 = jSONException;
            jSONException2.printStackTrace();
            return jSONObject;
        } catch (Exception e32) {
            exception = e32;
            jSONObject = jSONObject2;
            exception2 = exception;
            exception2.printStackTrace();
            return jSONObject;
        }
    }
}
