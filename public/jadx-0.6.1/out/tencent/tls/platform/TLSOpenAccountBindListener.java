package tencent.tls.platform;

import tencent.tls.account.TLSOpenAccountInfo;

public interface TLSOpenAccountBindListener {
    void onBindFailure(TLSOpenAccountInfo tLSOpenAccountInfo);

    void onBindSuccess(TLSOpenAccountInfo tLSOpenAccountInfo);

    void onFailure(TLSErrInfo tLSErrInfo);

    void onTimeOut(TLSErrInfo tLSErrInfo);
}
