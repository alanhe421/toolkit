package tencent.tls.platform;

public interface TLSGuestLoginListener {
    void OnGuestLoginFail(TLSErrInfo tLSErrInfo);

    void OnGuestLoginSuccess(TLSUserInfo tLSUserInfo);

    void OnGuestLoginTimeout(TLSErrInfo tLSErrInfo);
}
