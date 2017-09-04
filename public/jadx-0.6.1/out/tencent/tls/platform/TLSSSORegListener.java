package tencent.tls.platform;

public interface TLSSSORegListener {
    void OnGuestRegFail(TLSErrInfo tLSErrInfo);

    void OnGuestRegSuccess(TLSUserInfo tLSUserInfo);

    void OnGuestRegTimeout(TLSErrInfo tLSErrInfo);
}
