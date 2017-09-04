package tencent.tls.platform;

import tencent.tls.account.TLSOpenAccountInfo;

public interface TLSOpenAccountQueryListener {
    void onFailure(TLSErrInfo tLSErrInfo);

    void onLoginedAndBinded(TLSOpenAccountInfo tLSOpenAccountInfo);

    void onLoginedButUnbinded(TLSOpenAccountInfo tLSOpenAccountInfo);

    void onTimeOut(TLSErrInfo tLSErrInfo);

    void onUnlogined(TLSOpenAccountInfo tLSOpenAccountInfo);
}
