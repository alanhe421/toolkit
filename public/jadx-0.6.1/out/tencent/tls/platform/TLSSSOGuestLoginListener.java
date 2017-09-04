package tencent.tls.platform;

public interface TLSSSOGuestLoginListener {
    void OnGuestLoginFail(TLSErrInfo tLSErrInfo);

    void OnGuestLoginSuccess(TLSUserInfo tLSUserInfo);

    void OnGuestLoginTimeout(TLSErrInfo tLSErrInfo);
}
