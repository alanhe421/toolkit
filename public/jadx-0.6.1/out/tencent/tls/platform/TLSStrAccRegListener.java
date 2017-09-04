package tencent.tls.platform;

public interface TLSStrAccRegListener {
    void OnStrAccRegFail(TLSErrInfo tLSErrInfo);

    void OnStrAccRegSuccess(TLSUserInfo tLSUserInfo);

    void OnStrAccRegTimeout(TLSErrInfo tLSErrInfo);
}
