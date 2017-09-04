package oicq.wlogin_sdk.request;

import java.util.List;
import oicq.wlogin_sdk.devicelock.DevlockInfo;
import oicq.wlogin_sdk.request.WtloginHelper.QuickLoginParam;
import oicq.wlogin_sdk.tools.ErrMsg;

public class WtloginListener {
    public void OnInit(int i) {
    }

    public void onGetStWithQrSig(String str, long j, int i, long j2, WUserSigInfo wUserSigInfo, int i2, ErrMsg errMsg) {
    }

    public void OnGetStWithPasswd(String str, long j, int i, long j2, long[] jArr, String str2, WUserSigInfo wUserSigInfo, byte[][] bArr, int i2, ErrMsg errMsg) {
    }

    public void OnGetStWithPasswd(String str, long j, int i, long j2, String str2, WUserSigInfo wUserSigInfo, int i2, ErrMsg errMsg) {
    }

    public void onGetA1WithA1(String str, long j, int i, long j2, byte[] bArr, long j3, long j4, long j5, byte[] bArr2, byte[] bArr3, WUserSigInfo wUserSigInfo, WFastLoginInfo wFastLoginInfo, int i2, ErrMsg errMsg) {
    }

    public void OnGetStWithoutPasswd(String str, long j, long j2, int i, long j3, long[] jArr, WUserSigInfo wUserSigInfo, byte[][] bArr, int i2, ErrMsg errMsg) {
    }

    public void OnGetStWithoutPasswd(String str, long j, long j2, int i, long j3, WUserSigInfo wUserSigInfo, int i2, ErrMsg errMsg) {
    }

    public void OnCheckPictureAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo, byte[][] bArr2, int i, ErrMsg errMsg) {
    }

    public void OnCheckPictureAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo, int i, ErrMsg errMsg) {
    }

    public void OnCheckWebsigAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo, byte[][] bArr2, int i, ErrMsg errMsg) {
    }

    public void OnCheckWebsigAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo, int i, ErrMsg errMsg) {
    }

    public void OnCheckSMSAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo, byte[][] bArr2, int i, ErrMsg errMsg) {
    }

    public void OnCheckSMSAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo, int i, ErrMsg errMsg) {
    }

    public void OnRefreshPictureData(String str, WUserSigInfo wUserSigInfo, byte[] bArr, int i, ErrMsg errMsg) {
    }

    public void OnRefreshSMSData(String str, long j, WUserSigInfo wUserSigInfo, int i, int i2, int i3, ErrMsg errMsg) {
    }

    public void OnException(ErrMsg errMsg, int i, WUserSigInfo wUserSigInfo) {
    }

    public void OnRequestTransport(String str, long j, long j2, TransReqContext transReqContext, int i) {
    }

    public void OnRequestTransport(String str, long j, long j2, TransReqContext transReqContext, WUserSigInfo wUserSigInfo, int i) {
    }

    public void OnRegError(WUserSigInfo wUserSigInfo, int i, byte[] bArr) {
    }

    public void OnRegCheckDownloadMsg(WUserSigInfo wUserSigInfo, int i, byte[] bArr) {
    }

    public void OnRegCheckWebSig(WUserSigInfo wUserSigInfo, String str, String str2) {
    }

    public void OnRegCheckUploadMsg(WUserSigInfo wUserSigInfo, String str) {
    }

    public void OnRegQueryClientSentMsgStatus(WUserSigInfo wUserSigInfo, int i, int i2, int i3, String str) {
    }

    public void OnRegCheckValidUrl(WUserSigInfo wUserSigInfo, byte[] bArr) {
    }

    public void OnRegRequestServerResendMsg(WUserSigInfo wUserSigInfo, int i, int i2, int i3) {
    }

    public void OnRegSubmitMsgChk(WUserSigInfo wUserSigInfo, int i, byte[] bArr) {
    }

    public void OnRegGetAccount(WUserSigInfo wUserSigInfo, int i, long j, byte[] bArr, byte[] bArr2, byte[] bArr3) {
    }

    public void OnRegQueryAccount(WUserSigInfo wUserSigInfo, int i, byte[] bArr) {
    }

    public void OnQuickRegisterCheckAccount(WUserSigInfo wUserSigInfo, int i, byte[] bArr) {
    }

    public void OnQuickRegisterGetAccount(WUserSigInfo wUserSigInfo, int i, byte[] bArr) {
    }

    public void OnVerifyCode(String str, byte[] bArr, long j, List<byte[]> list, WUserSigInfo wUserSigInfo, byte[] bArr2, int i) {
    }

    public void OnCloseCode(String str, byte[] bArr, long j, WUserSigInfo wUserSigInfo, byte[] bArr2, int i) {
    }

    public void OnFetchCodeSig(byte[] bArr, long j, long j2, WUserSigInfo wUserSigInfo, byte[] bArr2, int i) {
    }

    public void OnQueryCodeResult(long j, List<byte[]> list, long j2, WUserSigInfo wUserSigInfo, byte[] bArr, int i) {
    }

    public void OnCheckDevLockStatus(WUserSigInfo wUserSigInfo, DevlockInfo devlockInfo, int i, ErrMsg errMsg) {
    }

    public void OnAskDevLockSms(WUserSigInfo wUserSigInfo, DevlockInfo devlockInfo, int i, ErrMsg errMsg) {
    }

    public void OnCheckDevLockSms(WUserSigInfo wUserSigInfo, int i, ErrMsg errMsg) {
    }

    public void OnCloseDevLock(WUserSigInfo wUserSigInfo, int i, ErrMsg errMsg) {
    }

    public void OnRegGetSMSVerifyLoginAccount(WUserSigInfo wUserSigInfo, int i, long j, byte[] bArr, byte[] bArr2, byte[] bArr3) {
    }

    public void OnGetStViaSMSVerifyLogin(String str, long j, int i, long j2, WUserSigInfo wUserSigInfo, int i2, ErrMsg errMsg) {
    }

    public void OnGetStViaSMSVerifyLogin(String str, long j, int i, long j2, long[] jArr, WUserSigInfo wUserSigInfo, byte[][] bArr, int i2, ErrMsg errMsg) {
    }

    public void OnCheckSMSVerifyLoginAccount(long j, long j2, String str, String str2, int i, int i2, WUserSigInfo wUserSigInfo, int i3, ErrMsg errMsg) {
    }

    public void OnRefreshSMSVerifyLoginCode(String str, String str2, int i, int i2, WUserSigInfo wUserSigInfo, int i3, ErrMsg errMsg) {
    }

    public void OnVerifySMSVerifyLoginCode(String str, String str2, WUserSigInfo wUserSigInfo, int i, ErrMsg errMsg) {
    }

    public void onQuickLogin(String str, QuickLoginParam quickLoginParam, int i, ErrMsg errMsg) {
    }
}
