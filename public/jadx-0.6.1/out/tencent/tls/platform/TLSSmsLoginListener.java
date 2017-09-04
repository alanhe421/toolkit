package tencent.tls.platform;

public interface TLSSmsLoginListener {
    void OnSmsLoginAskCodeSuccess(int i, int i2);

    void OnSmsLoginFail(TLSErrInfo tLSErrInfo);

    void OnSmsLoginReaskCodeSuccess(int i, int i2);

    void OnSmsLoginSuccess(TLSUserInfo tLSUserInfo);

    void OnSmsLoginTimeout(TLSErrInfo tLSErrInfo);

    void OnSmsLoginVerifyCodeSuccess();
}
