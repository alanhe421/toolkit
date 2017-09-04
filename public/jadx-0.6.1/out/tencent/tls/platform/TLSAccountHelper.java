package tencent.tls.platform;

import android.content.Context;

public class TLSAccountHelper {
    private static TLSAccountHelper __helper = null;
    private static TLSHelper __tlsHelper = null;

    public static TLSAccountHelper getInstance() {
        synchronized (TLSAccountHelper.class) {
            if (__helper == null) {
                __helper = new TLSAccountHelper();
            }
        }
        return __helper;
    }

    public TLSAccountHelper init(Context context, long j, int i, String str) {
        __tlsHelper.init(context, j);
        return this;
    }

    private TLSAccountHelper() {
        __tlsHelper = TLSHelper.getInstance();
    }

    public void setTimeOut(int i) {
        __tlsHelper.setTimeOut(i);
    }

    public void setLocalId(int i) {
        __tlsHelper.setLocalId(i);
    }

    public void setCountry(int i) {
        __tlsHelper.setCountry(i);
    }

    public String getSDKVersion() {
        return __tlsHelper.getSDKVersion();
    }

    public void setTestHost(String str, boolean z) {
        __tlsHelper.setTestHost(str, z);
    }

    public int TLSPwdResetAskCode(String str, TLSPwdResetListener tLSPwdResetListener) {
        return __tlsHelper.TLSPwdResetAskCode(str, tLSPwdResetListener);
    }

    public int TLSPwdRegAskCode(String str, TLSPwdRegListener tLSPwdRegListener) {
        return __tlsHelper.TLSPwdRegAskCode(str, tLSPwdRegListener);
    }

    public int TLSSmsRegAskCode(String str, TLSSmsRegListener tLSSmsRegListener) {
        return __tlsHelper.TLSSmsRegAskCode(str, tLSSmsRegListener);
    }

    public int TLSPwdResetReaskCode(TLSPwdResetListener tLSPwdResetListener) {
        return __tlsHelper.TLSPwdResetReaskCode(tLSPwdResetListener);
    }

    public int TLSPwdRegReaskCode(TLSPwdRegListener tLSPwdRegListener) {
        return __tlsHelper.TLSPwdRegReaskCode(tLSPwdRegListener);
    }

    public int TLSSmsRegReaskCode(TLSSmsRegListener tLSSmsRegListener) {
        return __tlsHelper.TLSSmsRegReaskCode(tLSSmsRegListener);
    }

    public int TLSPwdResetVerifyCode(String str, TLSPwdResetListener tLSPwdResetListener) {
        return __tlsHelper.TLSPwdResetVerifyCode(str, tLSPwdResetListener);
    }

    public int TLSPwdRegVerifyCode(String str, TLSPwdRegListener tLSPwdRegListener) {
        return __tlsHelper.TLSPwdRegVerifyCode(str, tLSPwdRegListener);
    }

    public int TLSSmsRegVerifyCode(String str, TLSSmsRegListener tLSSmsRegListener) {
        return __tlsHelper.TLSSmsRegVerifyCode(str, tLSSmsRegListener);
    }

    public int TLSPwdResetCommit(String str, TLSPwdResetListener tLSPwdResetListener) {
        return __tlsHelper.TLSPwdResetCommit(str, tLSPwdResetListener);
    }

    public int TLSPwdRegCommit(String str, TLSPwdRegListener tLSPwdRegListener) {
        return __tlsHelper.TLSPwdRegCommit(str, tLSPwdRegListener);
    }

    public int TLSSmsRegCommit(TLSSmsRegListener tLSSmsRegListener) {
        return __tlsHelper.TLSSmsRegCommit(tLSSmsRegListener);
    }

    public int TLSStrAccReg(String str, String str2, TLSStrAccRegListener tLSStrAccRegListener) {
        return __tlsHelper.TLSStrAccReg(str, str2, tLSStrAccRegListener);
    }
}
