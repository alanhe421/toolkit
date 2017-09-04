package tencent.tls.platform;

public interface TLSGuestRegListener {
    void OnGuestRegFail(TLSErrInfo tLSErrInfo);

    void OnGuestRegSuccess(TLSUserInfo tLSUserInfo);

    void OnGuestRegTimeout(TLSErrInfo tLSErrInfo);
}
