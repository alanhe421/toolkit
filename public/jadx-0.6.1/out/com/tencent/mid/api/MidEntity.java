package com.tencent.mid.api;

import com.tencent.mid.util.Util;
import com.tencent.mid.util.f;
import org.json.JSONException;
import org.json.JSONObject;

public class MidEntity {
    public static final String TAG_IMEI = "imei";
    public static final String TAG_IMSI = "imsi";
    public static final String TAG_MAC = "mac";
    public static final String TAG_MID = "mid";
    public static final String TAG_TIMESTAMPS = "ts";
    public static final String TAG_VER = "ver";
    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_NEW = 2;
    private static f a = Util.getLogger();
    private String b = null;
    private String c = null;
    private String d = null;
    private String e = "0";
    private long f = 0;
    private int g = 0;

    public static MidEntity parse(String str) {
        MidEntity midEntity = new MidEntity();
        if (Util.isStringValid(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull(TAG_IMEI)) {
                    midEntity.setImei(jSONObject.getString(TAG_IMEI));
                }
                if (!jSONObject.isNull(TAG_IMSI)) {
                    midEntity.setImsi(jSONObject.getString(TAG_IMSI));
                }
                if (!jSONObject.isNull(TAG_MAC)) {
                    midEntity.setMac(jSONObject.getString(TAG_MAC));
                }
                if (!jSONObject.isNull("mid")) {
                    midEntity.setMid(jSONObject.getString("mid"));
                }
                if (!jSONObject.isNull(TAG_TIMESTAMPS)) {
                    midEntity.setTimestamps(jSONObject.getLong(TAG_TIMESTAMPS));
                }
                if (!jSONObject.isNull(TAG_VER)) {
                    midEntity.g = jSONObject.optInt(TAG_VER, 0);
                }
            } catch (JSONException e) {
                a.d(e.toString());
            }
        }
        return midEntity;
    }

    JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            Util.jsonPut(jSONObject, TAG_IMEI, this.b);
            Util.jsonPut(jSONObject, TAG_IMSI, this.c);
            Util.jsonPut(jSONObject, TAG_MAC, this.d);
            Util.jsonPut(jSONObject, "mid", this.e);
            jSONObject.put(TAG_TIMESTAMPS, this.f);
        } catch (JSONException e) {
            a.d(e.toString());
        }
        return jSONObject;
    }

    public int compairTo(MidEntity midEntity) {
        return midEntity == null ? 1 : (isMidValid() && midEntity.isMidValid()) ? this.e.equals(midEntity.e) ? 0 : this.f < midEntity.f ? -1 : 1 : !isMidValid() ? -1 : 1;
    }

    public String getImei() {
        return this.b;
    }

    public String getImsi() {
        return this.c;
    }

    public String getMac() {
        return this.d;
    }

    public String getMid() {
        return this.e;
    }

    public long getTimestamps() {
        return this.f;
    }

    public int getVersion() {
        return this.g;
    }

    public boolean isMidValid() {
        return Util.isMidValid(this.e);
    }

    public void setImei(String str) {
        this.b = str;
    }

    public void setImsi(String str) {
        this.c = str;
    }

    public void setMac(String str) {
        this.d = str;
    }

    public void setMid(String str) {
        this.e = str;
    }

    public void setTimestamps(long j) {
        this.f = j;
    }

    public void setVersion(int i) {
        this.g = i;
    }

    public String toString() {
        return a().toString();
    }
}
