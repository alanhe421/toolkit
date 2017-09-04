package com.tencent.imsdk;

import android.util.SparseArray;
import com.tencent.IMErrInfo;
import com.tencent.qalsdk.base.a;

final class aa extends SparseArray<IMErrInfo> {
    aa() {
        put(1001, new IMErrInfo(BaseConstants.ERR_REQUEST_FAILED, ""));
        put(1002, new IMErrInfo(6012, ""));
        put(1007, new IMErrInfo(BaseConstants.ERR_REQUEST_INVALID_REQ, ""));
        put(1008, new IMErrInfo(BaseConstants.ERR_REQUEST_OVERLOADED, ""));
        put(a.c, new IMErrInfo(BaseConstants.ERR_SERIVCE_NOT_READY, ""));
        put(a.d, new IMErrInfo(BaseConstants.ERR_REQUEST_NO_NET_ONREQ, "Network is not avaliable"));
        put(a.e, new IMErrInfo(BaseConstants.ERR_NEVER_CONNECT_AFTER_LAUNCH, ""));
        put(a.f, new IMErrInfo(BaseConstants.ERR_TLSSDK_NOT_INITIALIZED, ""));
        put(a.g, new IMErrInfo(BaseConstants.ERR_TLSSDK_FIND_NO_USER, ""));
        put(2001, new IMErrInfo(6014, ""));
        put(a.n, new IMErrInfo(BaseConstants.ERR_REQUEST_SERVICE_SUSPEND, ""));
        put(a.p, new IMErrInfo(BaseConstants.ERR_REQUEST_KICK_OFF, ""));
        put(a.q, new IMErrInfo(BaseConstants.ERR_REQUEST_KICK_OFF, ""));
        put(a.r, new IMErrInfo(BaseConstants.ERR_REQUEST_INVALID_SIGN, ""));
        put(a.s, new IMErrInfo(BaseConstants.ERR_REQUEST_INVALID_COOKIE, ""));
        put(2101, new IMErrInfo(BaseConstants.ERR_LOGIN_OPENMSG_TIMEOUT, ""));
        put(2102, new IMErrInfo(BaseConstants.ERR_LOGIN_OPENMSG_RSP_PARSE_FAILED, ""));
        put(a.b, new IMErrInfo(BaseConstants.ERR_LOGIN_KICKED_OFF_BY_OTHER, "Kicked off by other device"));
        put(-1, new IMErrInfo(BaseConstants.ERR_TLSSDK_NOT_INITIALIZED, ""));
        put(-1000, new IMErrInfo(6012, ""));
        put(-1002, new IMErrInfo(BaseConstants.ERR_LOGIN_TLS_DECRYPT_FAILED, ""));
        put(-1009, new IMErrInfo(BaseConstants.ERR_LOGIN_TLS_RSP_PARSE_FAILED, ""));
        put(-1023, new IMErrInfo(BaseConstants.ERR_REQUEST_NO_NET_ONREQ, "Network is not avaliable"));
        put(-10001, new IMErrInfo(BaseConstants.ERR_USER_SIG_EXPIRED, ""));
        put(-10003, new IMErrInfo(BaseConstants.ERR_USER_SIG_EXPIRED, ""));
        put(-10004, new IMErrInfo(BaseConstants.ERR_USER_SIG_EXPIRED, ""));
    }
}
