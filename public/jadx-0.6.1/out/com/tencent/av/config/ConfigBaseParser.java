package com.tencent.av.config;

import android.text.TextUtils;

public class ConfigBaseParser {
    public static final String DEFAULT_VALUE = "unknown";
    protected String mData = null;

    public native String findConfigValue(String str, String str2, String str3);

    public native String getConfig();

    public ConfigBaseParser(String str) {
        this.mData = str;
    }

    public void setConfig(String str) {
        this.mData = str;
    }

    protected int[] getIntValues(String str) {
        int[] iArr = null;
        if (TextUtils.isEmpty(this.mData)) {
            this.mData = getConfig();
        }
        String findConfigValue = findConfigValue(this.mData, str, DEFAULT_VALUE);
        if (!(findConfigValue == null || findConfigValue.equalsIgnoreCase(DEFAULT_VALUE))) {
            String[] split = findConfigValue.split(",");
            if (split != null) {
                int length = split.length;
                iArr = new int[length];
                for (int i = 0; i < length; i++) {
                    try {
                        iArr[i] = Integer.parseInt(split[i].trim());
                    } catch (Exception e) {
                        e.printStackTrace();
                        iArr[i] = 0;
                    }
                }
            }
        }
        return iArr;
    }

    protected String[] getStringValues(String str) {
        if (TextUtils.isEmpty(this.mData)) {
            this.mData = getConfig();
        }
        String findConfigValue = findConfigValue(this.mData, str, DEFAULT_VALUE);
        if (findConfigValue == null || findConfigValue.equalsIgnoreCase(DEFAULT_VALUE)) {
            return null;
        }
        return findConfigValue.trim().split(",");
    }

    protected int getIntValue(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        int[] intValues = getIntValues(str);
        if (intValues == null || intValues.length <= 0) {
            return i;
        }
        return intValues[0];
    }

    protected String getStringValue(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String[] stringValues = getStringValues(str);
        if (stringValues == null || stringValues.length <= 0) {
            return str2;
        }
        return stringValues[0];
    }
}
