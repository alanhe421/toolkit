package tencent.tls.platform;

import android.content.Context;
import java.util.List;
import java.util.Map;

public class TLSLoginHelper {
    private static TLSLoginHelper __helper = null;
    private static TLSHelper __tlsHelper = null;

    public static TLSLoginHelper getInstance() {
        synchronized (TLSLoginHelper.class) {
            if (__helper == null) {
                __helper = new TLSLoginHelper();
            }
        }
        return __helper;
    }

    public TLSLoginHelper init(Context context, long j, int i, String str) {
        __tlsHelper.init(context, j);
        return this;
    }

    private TLSLoginHelper() {
        __tlsHelper = TLSHelper.getInstance();
    }

    public void setTestHost(String str, boolean z) {
        __tlsHelper.setTestHost(str, z);
    }

    public void setLocalId(int i) {
        __tlsHelper.setLocalId(i);
    }

    public void setTimeOut(int i) {
        __tlsHelper.setTimeOut(i);
    }

    public static void setLogcat(boolean z) {
        TLSHelper tLSHelper = __tlsHelper;
        TLSHelper.setLogcat(z);
    }

    public String getSDKVersion() {
        return __tlsHelper.getSDKVersion();
    }

    public List<TLSUserInfo> getAllUserInfo() {
        return __tlsHelper.getAllUserInfo();
    }

    public TLSUserInfo getLastUserInfo() {
        return __tlsHelper.getLastUserInfo();
    }

    public boolean needSmsVerify(String str) {
        return __tlsHelper.needSmsVerify(str);
    }

    public boolean needLogin(String str) {
        return __tlsHelper.needLogin(str);
    }

    public Map<String, Object> getSSOTicket(String str) {
        return __tlsHelper.getSSOTicket(str);
    }

    public String getUserSig(String str) {
        return __tlsHelper.getUserSig(str);
    }

    public String getGuestIdentifier() {
        return __tlsHelper.getGuestIdentifier();
    }

    public int TLSRefreshUserSig(String str, TLSRefreshUserSigListener tLSRefreshUserSigListener) {
        return __tlsHelper.TLSRefreshUserSig(str, tLSRefreshUserSigListener);
    }

    public int TLSExchangeTicket(long j, String str, String str2, TLSExchangeTicketListener tLSExchangeTicketListener) {
        return __tlsHelper.TLSExchangeTicket(j, str, str2, tLSExchangeTicketListener);
    }

    public int TLSSmsLogin(String str, TLSSmsLoginListener tLSSmsLoginListener) {
        return __tlsHelper.TLSSmsLogin(str, tLSSmsLoginListener);
    }

    public int TLSPwdLogin(String str, byte[] bArr, TLSPwdLoginListener tLSPwdLoginListener) {
        return __tlsHelper.TLSPwdLogin(str, bArr, tLSPwdLoginListener);
    }

    public int TLSGuestLogin(TLSGuestLoginListener tLSGuestLoginListener) {
        return __tlsHelper.TLSGuestLogin(tLSGuestLoginListener);
    }

    public int TLSSmsLoginAskCode(String str, TLSSmsLoginListener tLSSmsLoginListener) {
        return __tlsHelper.TLSSmsLoginAskCode(str, tLSSmsLoginListener);
    }

    public int TLSSmsLoginReaskCode(String str, TLSSmsLoginListener tLSSmsLoginListener) {
        return __tlsHelper.TLSSmsLoginReaskCode(str, tLSSmsLoginListener);
    }

    public int TLSPwdLoginReaskImgcode(TLSPwdLoginListener tLSPwdLoginListener) {
        return __tlsHelper.TLSPwdLoginReaskImgcode(tLSPwdLoginListener);
    }

    public int TLSSmsLoginVerifyCode(String str, TLSSmsLoginListener tLSSmsLoginListener) {
        return __tlsHelper.TLSSmsLoginVerifyCode(str, tLSSmsLoginListener);
    }

    public int TLSPwdLoginVerifyImgcode(String str, TLSPwdLoginListener tLSPwdLoginListener) {
        return __tlsHelper.TLSPwdLoginVerifyImgcode(str, tLSPwdLoginListener);
    }

    public void clearUserInfo(String str) {
        __tlsHelper.clearUserInfo(str);
    }

    public byte[] getGUID() {
        return __tlsHelper.getGUID();
    }

    public long TLSGetLastRefreshTime(String str) {
        return __tlsHelper.TLSGetLastRefreshTime(str);
    }
}
