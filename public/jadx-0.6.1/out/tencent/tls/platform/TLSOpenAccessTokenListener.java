package tencent.tls.platform;

public interface TLSOpenAccessTokenListener {
    void OnOpenAccessTokenFail(TLSErrInfo tLSErrInfo);

    void OnOpenAccessTokenSuccess(TLSAccessTokenInfo tLSAccessTokenInfo);

    void OnOpenAccessTokenTimeout(TLSErrInfo tLSErrInfo);
}
