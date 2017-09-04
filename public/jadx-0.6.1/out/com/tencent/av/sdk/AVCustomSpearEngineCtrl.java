package com.tencent.av.sdk;

public class AVCustomSpearEngineCtrl {
    public int nativeObj = 0;

    public native int addParamByRole(String str, String str2);

    public native void clear();

    public native String getDefaultRole();

    public native String getParamByRole(String str);

    public native int getParamCount();

    public native String getRoleByIndex(int i);

    public native boolean hasRole(String str);

    public native int setDefaultRole(String str);
}
