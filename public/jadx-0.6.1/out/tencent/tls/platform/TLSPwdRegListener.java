package tencent.tls.platform;

public interface TLSPwdRegListener {
    void OnPwdRegAskCodeSuccess(int i, int i2);

    void OnPwdRegCommitSuccess(TLSUserInfo tLSUserInfo);

    void OnPwdRegFail(TLSErrInfo tLSErrInfo);

    void OnPwdRegReaskCodeSuccess(int i, int i2);

    void OnPwdRegTimeout(TLSErrInfo tLSErrInfo);

    void OnPwdRegVerifyCodeSuccess();
}
