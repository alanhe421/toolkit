package tencent.tls.platform;

public interface TLSPwdLoginListener {
    void OnPwdLoginFail(TLSErrInfo tLSErrInfo);

    void OnPwdLoginNeedImgcode(byte[] bArr, TLSErrInfo tLSErrInfo);

    void OnPwdLoginReaskImgcodeSuccess(byte[] bArr);

    void OnPwdLoginSuccess(TLSUserInfo tLSUserInfo);

    void OnPwdLoginTimeout(TLSErrInfo tLSErrInfo);
}
