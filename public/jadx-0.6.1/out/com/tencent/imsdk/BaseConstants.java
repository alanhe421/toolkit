package com.tencent.imsdk;

import android.util.SparseArray;
import com.tencent.IMErrInfo;

public class BaseConstants {
    public static final int APPID = 537044915;
    public static final String BUGLY_APP_ID = "900011355";
    public static final long DEFAULT_MSG_TIMEOUT = 0;
    public static final int ERR_DATABASE_OPERATE_FAILED = 6019;
    public static final int ERR_FILE_TRANS_AUTH_FAILED = 6006;
    public static final int ERR_FILE_TRANS_DOWNLOAD_FAILED = 6009;
    public static final int ERR_FILE_TRANS_NO_SERVER = 6007;
    public static final int ERR_FILE_TRANS_UPLOAD_FAILED = 6008;
    public static final int ERR_HTTP_REQ_FAILED = 6010;
    public static final int ERR_INIT_CORE_FAIL = 6018;
    public static final int ERR_INVALID_CONVERSATION = 6004;
    public static final int ERR_INVALID_MSG_ELEM = 6016;
    public static final int ERR_INVALID_PARAMETERS = 6017;
    public static final int ERR_INVALID_SDK_OBJECT = 6021;
    public static final int ERR_IN_PROGESS = 6015;
    public static final int ERR_IO_OPERATION_FAILED = 6022;
    public static final int ERR_LOADMSG_FAILED = 6005;
    public static final int ERR_LOGGED_OUT_BEFORE_LOGIN_FINISHED = 6023;
    public static final int ERR_LOGIN_KICKED_OFF_BY_OTHER = 6208;
    public static final int ERR_LOGIN_OPENMSG_RSP_PARSE_FAILED = 6219;
    public static final int ERR_LOGIN_OPENMSG_TIMEOUT = 6218;
    public static final int ERR_LOGIN_TLS_DECRYPT_FAILED = 6220;
    public static final int ERR_LOGIN_TLS_RSP_PARSE_FAILED = 6217;
    public static final int ERR_NEVER_CONNECT_AFTER_LAUNCH = 6209;
    public static final int ERR_NO_SUCC_RESULT = 6003;
    public static final int ERR_PARSE_RESPONSE_FAILED = 6001;
    public static final int ERR_REQUEST_FAILED = 6210;
    public static final int ERR_REQUEST_INVALID_COOKIE = 6216;
    public static final int ERR_REQUEST_INVALID_REQ = 6211;
    public static final int ERR_REQUEST_INVALID_SIGN = 6215;
    public static final int ERR_REQUEST_KICK_OFF = 6213;
    public static final int ERR_REQUEST_NO_NET_ONREQ = 6200;
    public static final int ERR_REQUEST_NO_NET_ONRSP = 6201;
    public static final int ERR_REQUEST_OVERLOADED = 6212;
    public static final int ERR_REQUEST_SERVICE_SUSPEND = 6214;
    public static final int ERR_REQUEST_TIMEOUT = 6012;
    public static final int ERR_SDK_NOT_INITIALIZED = 6013;
    public static final int ERR_SDK_NOT_LOGGED_IN = 6014;
    public static final int ERR_SERIALIZE_REQ_FAILED = 6002;
    public static final int ERR_SERIVCE_NOT_READY = 6205;
    public static final int ERR_SUCC = 0;
    public static final int ERR_TLSSDK_FIND_NO_USER = 6025;
    public static final int ERR_TLSSDK_NOT_INITIALIZED = 6024;
    public static final int ERR_TO_USER_INVALID = 6011;
    public static final int ERR_USER_SIG_EXPIRED = 6206;
    public static final String GO_UNSEARCHABLE = "UnSearchability";
    public static final String GO_VISIBLE = "Visibility";
    public static final int IMCORE_REVISION = 8551;
    public static final String IMCORE_VERSION = "2.5.0";
    public static final int IMSDK_REVISION = 8552;
    public static final int IMSDK_TIMEOUT = 0;
    public static final String IMSDK_TYPE = "openim";
    public static final long MEGA = 1048576;
    public static final String OFFLINE_C2C_SOUND_TAG = "_C2C_SOUND";
    public static final String OFFLINE_GRP_SOUND_TAG = "_GRP_SOUND";
    public static final String OFFLINE_IS_ENABLED_TAG = "_IS_ENABLED";
    public static final String OFFLINE_PUSH_SETTINGS_FILE = "OfflinePushSettings";
    public static String betaServerAddress = "socket://14.17.32.135:14000#46001:0:1";
    public static int checkExpiresLogScreenOffCount = 2;
    private static final SparseArray<IMErrInfo> netErrCode = new aa();
    private static final String tag = "imsdk.BaseConstants";
    public static String testServerAddress = "socket://59.37.96.173:80#46001:0:1";

    public static void covertErrorCode(IMErrInfo iMErrInfo) {
        if (iMErrInfo != null) {
            QLog.d(tag, 1, "Original error info, code: " + iMErrInfo.getCode() + "|msg: " + iMErrInfo.getMsg());
            IMErrInfo iMErrInfo2 = (IMErrInfo) netErrCode.get(iMErrInfo.getCode());
            if (iMErrInfo2 != null) {
                iMErrInfo.setCode(iMErrInfo2.getCode());
                iMErrInfo.setMsg(iMErrInfo2.getMsg());
            }
        }
    }
}
