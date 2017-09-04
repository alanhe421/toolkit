package tencent.tls.platform;

public interface TLSRefreshUserSigListener {
    void OnRefreshUserSigFail(TLSErrInfo tLSErrInfo);

    void OnRefreshUserSigSuccess(TLSUserInfo tLSUserInfo);

    void OnRefreshUserSigTimeout(TLSErrInfo tLSErrInfo);
}
