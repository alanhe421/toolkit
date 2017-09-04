package tencent.tls.platform;

public interface TLSSmsRegListener {
    void OnSmsRegAskCodeSuccess(int i, int i2);

    void OnSmsRegCommitSuccess(TLSUserInfo tLSUserInfo);

    void OnSmsRegFail(TLSErrInfo tLSErrInfo);

    void OnSmsRegReaskCodeSuccess(int i, int i2);

    void OnSmsRegTimeout(TLSErrInfo tLSErrInfo);

    void OnSmsRegVerifyCodeSuccess();
}
