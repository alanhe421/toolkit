package tencent.tls.platform;

public interface TLSPwdResetListener {
    void OnPwdResetAskCodeSuccess(int i, int i2);

    void OnPwdResetCommitSuccess(TLSUserInfo tLSUserInfo);

    void OnPwdResetFail(TLSErrInfo tLSErrInfo);

    void OnPwdResetReaskCodeSuccess(int i, int i2);

    void OnPwdResetTimeout(TLSErrInfo tLSErrInfo);

    void OnPwdResetVerifyCodeSuccess();
}
