package com.hmt.analytics.objects;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.hmt.analytics.common.CommonUtil;
import com.hmt.analytics.common.HMTConstants;
import com.tencent.mid.api.MidEntity;
import com.tencent.qalsdk.util.BaseApplication;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class ParamList {
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private double F;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public String getV() {
        return this.s;
    }

    public void setV(String str) {
        this.s = str;
    }

    public String getOs() {
        return this.a;
    }

    public void setOs(String str) {
        this.a = str;
    }

    public String getImei() {
        return this.b;
    }

    public void setImei(String str) {
        this.b = str;
    }

    public String getAndroidid() {
        return this.c;
    }

    public void setAndroidid(String str) {
        this.c = str;
    }

    public String getAndroidid1() {
        return this.d;
    }

    public void setAndroidid1(String str) {
        this.d = str;
    }

    public String getMac() {
        return this.e;
    }

    public void setMac(String str) {
        this.e = str;
    }

    public String getMac1() {
        return this.f;
    }

    public void setMac1(String str) {
        this.f = str;
    }

    public String getOsVersion() {
        return this.g;
    }

    public void setOsVersion(String str) {
        this.g = str;
    }

    public String getUseragent() {
        return this.h;
    }

    public void setUseragent(String str) {
        this.h = str;
    }

    public String getDeviceName() {
        return this.i;
    }

    public void setDeviceName(String str) {
        this.i = str;
    }

    public String getAppCode() {
        return this.j;
    }

    public void setAppCode(String str) {
        this.j = str;
    }

    public String getAaid() {
        return this.k;
    }

    public void setAaid(String str) {
        this.k = str;
    }

    public String getAppName() {
        return this.l;
    }

    public void setAppName(String str) {
        this.l = str;
    }

    public String getUa() {
        return this.m;
    }

    public void setUa(String str) {
        this.m = str;
    }

    public String getType() {
        return this.n;
    }

    public void setType(String str) {
        this.n = str;
    }

    public String getDeviceId() {
        return this.o;
    }

    public void setDeviceId(String str) {
        this.o = str;
    }

    public String getTs() {
        return this.r;
    }

    public void setTs(String str) {
        this.r = str;
    }

    public String getChannelId() {
        return this.p;
    }

    public void setChannelId(String str) {
        this.p = str;
    }

    public String getAppVersion() {
        return this.q;
    }

    public void setAppVersion(String str) {
        this.q = str;
    }

    public String getMuid() {
        return this.t;
    }

    public void setMuid(String str) {
        this.t = str;
    }

    public double getSize() {
        return this.F;
    }

    public String getSr() {
        return this.u;
    }

    public void setSr(String str) {
        this.u = str;
    }

    public String getLang() {
        return this.v;
    }

    public void setLang(String str) {
        this.v = str;
    }

    public String getSv() {
        return this.w;
    }

    public void setSv(String str) {
        this.w = str;
    }

    public String getSd() {
        return this.x;
    }

    public void setSd(String str) {
        this.x = str;
    }

    public String getMchar() {
        return this.y;
    }

    public void setMchar(String str) {
        this.y = str;
    }

    public void setSize(double d) {
        this.F = d;
    }

    public String getCell_id() {
        return this.z;
    }

    public void setCell_id(String str) {
        this.z = str;
    }

    public String getLac() {
        return this.A;
    }

    public void setLac(String str) {
        this.A = str;
    }

    public String getHave_wifi() {
        return this.B;
    }

    public void setHave_wifi(String str) {
        this.B = str;
    }

    public String getNetwork() {
        return this.C;
    }

    public void setNetwork(String str) {
        this.C = str;
    }

    public String getLat() {
        return this.D;
    }

    public void setLat(String str) {
        this.D = str;
    }

    public String getLon() {
        return this.E;
    }

    public void setLon(String str) {
        this.E = str;
    }

    public static JSONObject getParamList(Context context, String str) throws JSONException {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("os", "0");
        jSONObject.put("_ua", CommonUtil.getAppKey(context));
        jSONObject.put("type", str);
        jSONObject.put("device_id", CommonUtil.getDeviceID(context));
        jSONObject.put(BaseApplication.DATA_KEY_CHANNEL_ID, CommonUtil.getChannel(context));
        jSONObject.put(MidEntity.TAG_TIMESTAMPS, CommonUtil.getTime());
        jSONObject.put("v", HMTConstants.i);
        jSONObject.put("muid", CommonUtil.getMuid(context));
        jSONObject.put("sr", displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
        jSONObject.put("sv", CommonUtil.getSV());
        jSONObject.put("sd", CommonUtil.getSD());
        jSONObject.put("char", CommonUtil.getChar());
        String[] unTracked = CommonUtil.getUnTracked(context);
        if (!CommonUtil.isUnTracked(unTracked, MidEntity.TAG_IMEI).booleanValue()) {
            jSONObject.put(MidEntity.TAG_IMEI, CommonUtil.getImei(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, "androidid").booleanValue()) {
            jSONObject.put("androidid", CommonUtil.getAndroidIdMd5(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, "androidid1").booleanValue()) {
            jSONObject.put("androidid1", CommonUtil.getAndroidId(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, "aaid").booleanValue()) {
            jSONObject.put("aaid", CommonUtil.getAaid(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, MidEntity.TAG_MAC).booleanValue()) {
            jSONObject.put(MidEntity.TAG_MAC, CommonUtil.getMac(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, "mac1").booleanValue()) {
            jSONObject.put("mac1", CommonUtil.getMac1(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, "os_version").booleanValue()) {
            jSONObject.put("os_version", CommonUtil.getOsVersion(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, "app_name").booleanValue()) {
            jSONObject.put("app_name", CommonUtil.getAppName(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, "app_version").booleanValue()) {
            jSONObject.put("app_version", CommonUtil.getAppVersion(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, "app_code").booleanValue()) {
            jSONObject.put("app_code", CommonUtil.getAppCode(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, "useragent").booleanValue()) {
            jSONObject.put("useragent", CommonUtil.getUseragent(context));
        }
        if (!CommonUtil.isUnTracked(unTracked, "device_name").booleanValue()) {
            jSONObject.put("device_name", CommonUtil.getDeviceName());
        }
        if (!CommonUtil.isUnTracked(unTracked, "lang").booleanValue()) {
            jSONObject.put("lang", Locale.getDefault().getLanguage());
        }
        return jSONObject;
    }

    public static JSONObject getReqParamList(Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("_ua", CommonUtil.getAppKey(context));
        jSONObject.put("type", "req");
        jSONObject.put("device_id", CommonUtil.getDeviceID(context));
        jSONObject.put(BaseApplication.DATA_KEY_CHANNEL_ID, CommonUtil.getChannel(context));
        jSONObject.put("app_version", CommonUtil.getAppVersion(context));
        jSONObject.put(MidEntity.TAG_TIMESTAMPS, CommonUtil.getTime());
        return jSONObject;
    }
}
