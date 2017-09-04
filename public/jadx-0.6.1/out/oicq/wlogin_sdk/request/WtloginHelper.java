package oicq.wlogin_sdk.request;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.im_open.http;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import oicq.wlogin_sdk.a.a;
import oicq.wlogin_sdk.a.b;
import oicq.wlogin_sdk.a.d;
import oicq.wlogin_sdk.a.e;
import oicq.wlogin_sdk.a.f;
import oicq.wlogin_sdk.a.g;
import oicq.wlogin_sdk.a.h;
import oicq.wlogin_sdk.a.j;
import oicq.wlogin_sdk.b.ap;
import oicq.wlogin_sdk.b.bd;
import oicq.wlogin_sdk.b.cl;
import oicq.wlogin_sdk.b.i;
import oicq.wlogin_sdk.b.o;
import oicq.wlogin_sdk.code2d.c;
import oicq.wlogin_sdk.code2d.fetch_code;
import oicq.wlogin_sdk.code2d.fetch_code.QRCodeCustom;
import oicq.wlogin_sdk.devicelock.DevlockBase;
import oicq.wlogin_sdk.devicelock.DevlockInfo;
import oicq.wlogin_sdk.devicelock.DevlockRst;
import oicq.wlogin_sdk.devicelock.TLV_CommRsp;
import oicq.wlogin_sdk.report.report_t2;
import oicq.wlogin_sdk.sharemem.WloginLoginInfo;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;
import oicq.wlogin_sdk.tools.EcdhCrypt;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.InternationMsg;
import oicq.wlogin_sdk.tools.InternationMsg.MSG_TYPE;
import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.RSACrypt;
import oicq.wlogin_sdk.tools.cryptor;
import oicq.wlogin_sdk.tools.util;

public class WtloginHelper extends WtloginListener {
    static final Object __sync_top = new Object();
    static int __top = 0;
    private boolean isForLocal;
    private long mAysncSeq;
    private Context mContext;
    private u mG;
    private Handler mHelperHandler;
    private WtloginListener mListener;
    private int mMainSigMap;
    private int mMiscBitmap;
    private long mOpenAppid;
    private j mRegStatus;
    private int mSubSigMap;

    private static class A1SRC {
        public static final int A1SRC_PASSWORD = 1;
        public static final int A1SRC_PTSIG = 4;
        public static final int A1SRC_QUICKLOGIN = 2;
        public static final int A1SRC_SMS = 3;

        private A1SRC() {
        }
    }

    public class HelperThread extends Thread {
        boolean isSelfLooper = false;
        byte[] mAppName2;
        byte[] mAppSign2;
        byte[] mAppVer2;
        long mAppid1;
        long mAppid2;
        long mDwAppid;
        long mDwDstAppPri;
        long mDwDstAppid;
        long[] mDwDstSubAppidList;
        int mDwMainSigMap;
        long[] mDwSubAppidList;
        long mDwSubDstAppid;
        int mEncrypt;
        WFastLoginInfo mFastLoginInfo;
        Handler mHandler;
        WtloginHelper mHelper;
        boolean mIsSmslogin = false;
        String mMsgCode;
        byte[] mPictureData;
        WtTicketPromise mPromise;
        boolean mPwdMd5;
        int mReportErrType;
        TransReqContext mReqContext;
        int mReqType;
        byte[][] mReserve;
        long mRole;
        byte[][] mST;
        byte[] mST1;
        byte[] mST1Key;
        long mSmsAppid;
        long mSsoVer2;
        long mSubAppid1;
        long mSubAppid2;
        long mUIN;
        String mUserAccount;
        byte[] mUserInput;
        String mUserPasswd;
        WUserSigInfo mUserSigInfo = null;
        String ptSig;
        QuickLoginParam quickLoginParam;

        HelperThread(WtloginHelper wtloginHelper, Handler handler) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, byte[] bArr, byte[] bArr2, long j, long j2, int i, String str) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mST1 = bArr;
            this.mST1Key = bArr2;
            this.mUIN = j;
            this.mDwAppid = j2;
            this.mReportErrType = i;
            setName(str);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, byte[] bArr, byte[] bArr2, long j, long j2, String str) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mST1 = bArr;
            this.mST1Key = bArr2;
            this.mUIN = j;
            this.mDwAppid = j2;
            setName(str);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, int i, String str, long j, long j2, TransReqContext transReqContext, String str2) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mEncrypt = i;
            this.mUserAccount = str;
            this.mDwAppid = j;
            this.mRole = j2;
            this.mReqContext = transReqContext;
            setName(str2);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, int i, String str, long j, long j2, TransReqContext transReqContext, WUserSigInfo wUserSigInfo, String str2) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mEncrypt = i;
            this.mUserAccount = str;
            this.mDwAppid = j;
            this.mRole = j2;
            this.mReqContext = transReqContext;
            this.mUserSigInfo = wUserSigInfo;
            setName(str2);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, String str, long j, int i, long j2, long[] jArr, boolean z, String str2, WUserSigInfo wUserSigInfo, byte[][] bArr, boolean z2, String str3) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mUserAccount = str;
            this.mDwAppid = j;
            this.mDwMainSigMap = i;
            this.mDwSubDstAppid = j2;
            this.mDwSubAppidList = jArr;
            this.mPwdMd5 = z;
            this.mUserPasswd = str2;
            this.mUserSigInfo = wUserSigInfo;
            this.mST = bArr;
            this.mIsSmslogin = z2;
            setName(str3);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, WtTicketPromise wtTicketPromise, String str, long j, long j2, long j3, int i, long j4, long[] jArr, WUserSigInfo wUserSigInfo, byte[][] bArr, byte[][] bArr2, String str2) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mPromise = wtTicketPromise;
            this.mUserAccount = str;
            this.mDwAppid = j;
            this.mDwDstAppid = j2;
            this.mDwDstAppPri = j3;
            this.mDwMainSigMap = i;
            this.mDwSubDstAppid = j4;
            this.mDwDstSubAppidList = jArr;
            this.mUserSigInfo = wUserSigInfo;
            this.mST = bArr;
            this.mReserve = bArr2;
            setName(str2);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, String str, long j, long j2, int i, byte[] bArr, long j3, long j4, long j5, byte[] bArr2, byte[] bArr3, WUserSigInfo wUserSigInfo, WFastLoginInfo wFastLoginInfo, String str2) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mUserAccount = str;
            this.mAppid1 = j;
            this.mSubAppid1 = j2;
            this.mDwMainSigMap = i;
            this.mAppName2 = bArr;
            this.mSsoVer2 = j4;
            this.mAppid2 = j4;
            this.mSubAppid2 = j5;
            this.mAppVer2 = bArr2;
            this.mAppSign2 = bArr3;
            this.mUserSigInfo = wUserSigInfo;
            this.mFastLoginInfo = wFastLoginInfo;
            setName(str2);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, String str, WUserSigInfo wUserSigInfo, String str2) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mUserAccount = str;
            this.mUserSigInfo = wUserSigInfo;
            setName(str2);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, String str, long j, WUserSigInfo wUserSigInfo, String str2) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mUserAccount = str;
            this.mSmsAppid = j;
            this.mUserSigInfo = wUserSigInfo;
            setName(str2);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, String str, String str2, WUserSigInfo wUserSigInfo, String str3) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mUserAccount = str;
            this.mMsgCode = str2;
            this.mUserSigInfo = wUserSigInfo;
            setName(str3);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, String str, byte[] bArr, WUserSigInfo wUserSigInfo, byte[][] bArr2, String str2) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mUserAccount = str;
            this.mUserInput = bArr;
            this.mUserSigInfo = wUserSigInfo;
            this.mST = bArr2;
            setName(str2);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, long j, long j2, String str, WUserSigInfo wUserSigInfo, String str2) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mUserAccount = str;
            this.mAppid1 = j;
            this.mSubAppid1 = j2;
            this.mUserSigInfo = wUserSigInfo;
            setName(str2);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, String str, QuickLoginParam quickLoginParam, String str2) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mUserAccount = str;
            this.quickLoginParam = quickLoginParam;
            this.mUserSigInfo = quickLoginParam.userSigInfo;
            setName(str2);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, String str, String str2, QuickLoginParam quickLoginParam, String str3) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mUserAccount = str;
            this.ptSig = str2;
            this.quickLoginParam = quickLoginParam;
            this.mUserSigInfo = quickLoginParam.userSigInfo;
            setName(str3);
        }

        HelperThread(WtloginHelper wtloginHelper, Handler handler, String str, long j, int i, long j2, WUserSigInfo wUserSigInfo, String str2) {
            this.mHelper = wtloginHelper;
            this.mHandler = handler;
            this.mUserAccount = str;
            this.mDwAppid = j;
            this.mDwMainSigMap = i;
            this.mDwSubDstAppid = j2;
            this.mUserSigInfo = wUserSigInfo;
            setName(str2);
        }

        public void RunReq(int i) {
            this.mReqType = i;
            if (this.mReqType == 7) {
                start();
                return;
            }
            synchronized (WtloginHelper.__sync_top) {
                Timer timer = new Timer();
                TimerTask 1 = new 1(this);
                int i2 = WtloginHelper.__top;
                WtloginHelper.__top = i2 + 1;
                timer.schedule(1, (long) (i2 * http.Internal_Server_Error));
                util.LOGI("push queue " + WtloginHelper.__top, "");
            }
        }

        public void run() {
            if (this.mHelper.mListener != null || this.mPromise != null) {
                int i = this.mHelper.mG.s;
                this.isSelfLooper = this.mHandler == null;
                if (this.isSelfLooper) {
                    Looper.prepare();
                    this.mHandler = WtloginHelper.this.newHelperHandler();
                }
                try {
                    if (this.mHandler == null) {
                        throw new Exception("Handler should not be null!");
                    }
                    if (this.mReqType == 0) {
                        this.mHandler.post(new 2(this, i, this.mHelper.GetStWithPasswd(this.mUserAccount, this.mDwAppid, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwSubAppidList, this.mPwdMd5, this.mUserPasswd, this.mUserSigInfo, this.mST, this.mIsSmslogin, 1)));
                    } else if (this.mReqType == 1) {
                        this.mHandler.post(new 3(this, i, this.mHelper.RefreshPictureData(this.mUserAccount, this.mUserSigInfo, 1)));
                    } else if (this.mReqType == 2) {
                        this.mHandler.post(new 4(this, i, this.mHelper.CheckPictureAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, 1)));
                    } else if (this.mReqType == 3) {
                        this.mHandler.post(new 5(this, i, this.mHelper.RefreshSMSData(this.mUserAccount, this.mSmsAppid, this.mUserSigInfo, 1)));
                    } else if (this.mReqType == 4) {
                        this.mHandler.post(new 6(this, i, this.mHelper.CheckSMSAndGetSt(this.mUserAccount, this.mUserInput, this.mUserSigInfo, this.mST, 1)));
                    } else if (this.mReqType == 5) {
                        this.mHandler.post(new 7(this, i, this.mHelper.GetStWithoutPasswd(this.mUserAccount, this.mDwAppid, this.mDwDstAppid, this.mDwDstAppPri, this.mDwMainSigMap, this.mDwSubDstAppid, this.mDwDstSubAppidList, this.mUserSigInfo, this.mST, this.mReserve, 1, this.mPromise)));
                    } else if (this.mReqType == 6) {
                        this.mHandler.post(new 8(this, i, this.mHelper.GetA1WithA1(this.mUserAccount, this.mAppid1, this.mSubAppid1, this.mDwMainSigMap, this.mAppName2, this.mSsoVer2, this.mAppid2, this.mSubAppid2, this.mAppVer2, this.mAppSign2, this.mUserSigInfo, this.mFastLoginInfo, 1)));
                    } else if (this.mReqType == 7) {
                        this.mHelper.RequestReport(1, this.mST1, this.mST1Key, this.mUIN, this.mDwAppid);
                    } else if (this.mReqType == 9) {
                        this.mHandler.post(new 9(this, i, this.mHelper.RequestTransport(1, this.mEncrypt, this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext, this.mUserSigInfo)));
                    } else if (this.mReqType == 10) {
                        this.mHandler.post(new 10(this, i, this.mHelper.RequestTransportMsf(1, this.mEncrypt, this.mUserAccount, this.mDwAppid, this.mRole, this.mReqContext)));
                    } else if (this.mReqType == 12) {
                        this.mHandler.post(new 11(this, i, this.mHelper.CheckSMSVerifyLoginAccount(this.mAppid1, this.mSubAppid1, this.mUserAccount, this.mUserSigInfo, 1)));
                    } else if (this.mReqType == 13) {
                        this.mHandler.post(new 12(this, i, this.mHelper.VerifySMSVerifyLoginCode(this.mUserAccount, this.mMsgCode, this.mUserSigInfo, 1)));
                    } else if (this.mReqType == 14) {
                        this.mHandler.post(new 13(this, i, this.mHelper.RefreshSMSVerifyLoginCode(this.mUserAccount, this.mUserSigInfo, 1)));
                    } else if (this.mReqType == 8) {
                        this.mHelper.RequestReportError(1, this.mST1, this.mST1Key, this.mUIN, this.mDwAppid, this.mReportErrType);
                    } else if (this.mReqType == 15) {
                        this.mHandler.post(new 14(this, i, this.mHelper.getStWithQQSig(this.mUserAccount, this.quickLoginParam, 1)));
                    } else if (this.mReqType == 16) {
                        this.mHandler.post(new 15(this, i, this.mHelper.getStWithPtSig(this.mUserAccount, this.ptSig, this.quickLoginParam, 1)));
                    } else if (this.mReqType == 17) {
                        this.mHandler.post(new 16(this, i, this.mHelper.getStWithQrSig(this.mUserAccount, this.mDwAppid, this.mDwSubDstAppid, this.mDwMainSigMap, this.mUserSigInfo, 1)));
                    }
                    if (this.isSelfLooper) {
                        Looper.loop();
                    }
                    if (this.mReqType != 7) {
                        synchronized (WtloginHelper.__sync_top) {
                            if (WtloginHelper.__top > 0) {
                                WtloginHelper.__top--;
                            }
                            util.LOGI("pop queue " + WtloginHelper.__top, "");
                        }
                    }
                } catch (Exception e) {
                    util.printException(e, "");
                    this.mHandler.post(new 17(this, i));
                    if (this.isSelfLooper) {
                        Looper.loop();
                    }
                    if (this.mReqType != 7) {
                        synchronized (WtloginHelper.__sync_top) {
                            if (WtloginHelper.__top > 0) {
                                WtloginHelper.__top--;
                            }
                            util.LOGI("pop queue " + WtloginHelper.__top, "");
                        }
                    }
                } catch (Throwable th) {
                    if (this.isSelfLooper) {
                        Looper.loop();
                    }
                    if (this.mReqType != 7) {
                        synchronized (WtloginHelper.__sync_top) {
                            if (WtloginHelper.__top > 0) {
                                WtloginHelper.__top--;
                            }
                            util.LOGI("pop queue " + WtloginHelper.__top, "");
                        }
                    }
                }
            }
        }

        private void quitSelfLooper() {
            try {
                if (this.isSelfLooper) {
                    Looper myLooper = Looper.myLooper();
                    if (myLooper != null) {
                        myLooper.quit();
                    }
                    this.mHandler = null;
                }
            } catch (Exception e) {
                util.printException(e, "");
            }
        }
    }

    public static class QuickLoginParam {
        public long appid;
        public long dstAppid;
        public long[] dstSubAppidList;
        public boolean forceWebLogin = false;
        public boolean isUserAccountLocked = false;
        public int sigMap;
        public long subAppid = 1;
        public String userAccount;
        public WUserSigInfo userSigInfo = new WUserSigInfo();
        public String webViewActivityClassName;
    }

    public class QuickLoginRequestCode {
        public static final int REQUEST_PT_LOGIN = 1202;
        public static final int REQUEST_QQ_LOGIN = 1201;
    }

    public static class RegTLVType {
        public static final int ADDRESS_BOOK = 7;
        public static final int APPID = 2;
        public static final int APPNAME = 14;
        public static final int CALL_BACK_URL = 5;
        public static final int EMAIL_URL = 4;
        public static final int GUID = 10;
        public static final int HAS_ADDRESS_BOOK = 8;
        public static final int ID = 1;
        public static final int LANGUAGE = 3;
        public static final int MPASSWD = 11;
        public static final int MSALT = 12;
        public static final int NEW_UIN = 31;
        public static final int NICK_NAME = 6;
        public static final int NOPASSWD_REG = 9;
        public static final int PHONE_NUMBER = 30;
        public static final int PHONE_NUMBER_BOUND_UIN = 36;
        public static final int PICTURE_ID = 17;
        public static final int PICTURE_URL = 40;
        public static final int REGISTER_SIG = 16;
        public static final int REG_SIG = 33;
        public static final int SET_DEVLOCK_FLG = 15;
        public static final int SUBAPPID = 13;
    }

    public static final class SigType {
        public static final int WLOGIN_A2 = 64;
        public static final int WLOGIN_A5 = 2;
        static final int WLOGIN_A8_RESERVED = 16;
        public static final int WLOGIN_AQSIG = 2097152;
        public static final int WLOGIN_D2 = 262144;
        public static final int WLOGIN_DA2 = 33554432;
        public static final int WLOGIN_LHSIG = 4194304;
        public static final int WLOGIN_LSKEY = 512;
        public static final int WLOGIN_OPENKEY = 16384;
        public static final int WLOGIN_PAYTOKEN = 8388608;
        public static final int WLOGIN_PF = 16777216;
        public static final int WLOGIN_PSKEY = 1048576;
        public static final int WLOGIN_PT4Token = 134217728;
        public static final int WLOGIN_QRPUSH = 67108864;
        static final int WLOGIN_SID_RESERVED = 524288;
        public static final int WLOGIN_SIG64 = 8192;
        public static final int WLOGIN_SKEY = 4096;
        public static final int WLOGIN_ST = 128;
        public static final int WLOGIN_STWEB = 32;
        public static final int WLOGIN_TOKEN = 32768;
        static final int WLOGIN_VKEY_RESERVED = 131072;
    }

    public WtloginHelper(Context context) {
        this.mG = new u(null);
        this.mHelperHandler = newHelperHandler();
        this.mContext = null;
        this.mListener = null;
        this.mRegStatus = new j();
        this.mMainSigMap = 16724722;
        this.mSubSigMap = 66560;
        this.mMiscBitmap = 3669884;
        this.mOpenAppid = 715019303;
        this.mAysncSeq = 0;
        this.isForLocal = false;
        this.isForLocal = false;
        this.mContext = context;
        this.mG.a(context);
        RequestInit();
    }

    public WtloginHelper(Context context, boolean z) {
        this.mG = new u(null);
        this.mHelperHandler = newHelperHandler();
        this.mContext = null;
        this.mListener = null;
        this.mRegStatus = new j();
        this.mMainSigMap = 16724722;
        this.mSubSigMap = 66560;
        this.mMiscBitmap = 3669884;
        this.mOpenAppid = 715019303;
        this.mAysncSeq = 0;
        this.isForLocal = false;
        localInit(context, z);
    }

    public WtloginHelper(Context context, Object obj) {
        this.mG = new u(null);
        this.mHelperHandler = newHelperHandler();
        this.mContext = null;
        this.mListener = null;
        this.mRegStatus = new j();
        this.mMainSigMap = 16724722;
        this.mSubSigMap = 66560;
        this.mMiscBitmap = 3669884;
        this.mOpenAppid = 715019303;
        this.mAysncSeq = 0;
        this.isForLocal = false;
        WtloginMsfListener.TicketMgr = obj;
        localInit(context, this.isForLocal);
    }

    private void localInit(Context context, boolean z) {
        this.isForLocal = z;
        try {
            this.mContext = context.getApplicationContext();
        } catch (Throwable th) {
            this.mContext = context;
            util.printThrowable(th, "");
        }
        this.mG.a(context);
        RequestInit();
    }

    private Handler newHelperHandler() {
        try {
            if (Looper.myLooper() == null) {
                return null;
            }
            return new Handler();
        } catch (Throwable th) {
            return null;
        }
    }

    public void CancelRequest() {
        this.mG.s = 1;
    }

    public WtloginListener SetListener(WtloginListener wtloginListener) {
        WtloginListener wtloginListener2 = this.mListener;
        this.mListener = wtloginListener;
        return wtloginListener2;
    }

    public void SetTestHost(int i, String str) {
        oicq_request.a(i, str);
    }

    public void setForQCall() {
        u.ap = true;
        WtloginMsfListener.CLIENT_CLASSNAME = "com.tencent.lightalk.msf.core.auth.WtProvider";
    }

    public static void setLoadSoFlg(boolean z) {
        u.aq = z;
    }

    public void SetAppClientVersion(int i) {
        u.w = i;
    }

    public void SetMsfTransportFlag(int i) {
        this.mG.k = i;
        if (i != 0) {
            u.ad = new byte[4];
            u.ac = 0;
            this.mG.l = 45000;
        }
    }

    public void SetSigMap(int i) {
        this.mMainSigMap = i | Opcodes.AND_LONG_2ADDR;
    }

    public void SetImgType(int i) {
        u.x = i;
        this.mMiscBitmap |= 128;
    }

    public void SetPicType(int i) {
        u.y = i;
    }

    public void SetLocalId(int i) {
        u.u = i;
    }

    public long GetTimeDifference() {
        return u.ab;
    }

    public void SetTimeOut(int i) {
        this.mG.l = i;
    }

    public void setCallSource(int i) {
        u.af = i;
    }

    public void setBabyQFlg(boolean z) {
        u.ag = z;
    }

    public void SetCanWebVerify(boolean z) {
        l.I = z;
    }

    public int SetNeedForPayToken(String str, String str2, byte[] bArr) {
        if (str == null || str.length() <= 0) {
            return -1;
        }
        l.J = str.getBytes();
        if (bArr != null) {
            l.L = bArr;
        }
        if (str2 == null || str2.length() == 0) {
            str2 = util.getChannelId(this.mContext, null);
        }
        l.K = str2.getBytes();
        if (str2 == null || str2.length() == 0) {
            return -2;
        }
        return 0;
    }

    public Boolean IsNeedLoginWithPasswd(String str, long j) {
        boolean z = false;
        int i = 1;
        if (str == null) {
            return Boolean.valueOf(true);
        }
        synchronized (this) {
            long parseLong;
            WloginSigInfo a;
            if (util.check_uin_account(str).booleanValue()) {
                parseLong = Long.parseLong(str);
                a = this.mG.a(parseLong, j);
                if (a == null) {
                }
                if (a != null) {
                }
                z = true;
                i = 3;
            } else {
                parseLong = this.mG.b(str);
                if (parseLong == 0) {
                    z = true;
                }
                a = this.mG.a(parseLong, j);
                if (a == null && a._en_A1 != null && a._en_A1.length != 0 && a._noPicSig != null && a._noPicSig.length != 0) {
                    i = 2;
                } else if (a != null || a._TGT == null || a._TGT.length == 0 || a.iSExpireA2(u.f())) {
                    z = true;
                    i = 3;
                } else {
                    i = 0;
                }
            }
        }
        util.LOGI("user:" + str + " appid:" + j + " need password:" + z + " flag=" + i, str);
        return Boolean.valueOf(z);
    }

    public List<WloginLoginInfo> GetAllLoginInfo() {
        return this.mG.k();
    }

    public WloginLastLoginInfo GetLastLoginInfo() {
        List<WloginLoginInfo> k = this.mG.k();
        if (k == null) {
            return null;
        }
        WloginLoginInfo wloginLoginInfo = null;
        for (WloginLoginInfo wloginLoginInfo2 : k) {
            WloginLoginInfo wloginLoginInfo22;
            if (wloginLoginInfo == null) {
                wloginLoginInfo = wloginLoginInfo22;
            } else {
                if (wloginLoginInfo22.mCreateTime <= wloginLoginInfo.mCreateTime) {
                    wloginLoginInfo22 = wloginLoginInfo;
                }
                wloginLoginInfo = wloginLoginInfo22;
            }
        }
        if (wloginLoginInfo == null) {
            return null;
        }
        if (wloginLoginInfo.mAccount == null || wloginLoginInfo.mAccount.length() <= 0) {
            return new WloginLastLoginInfo(String.valueOf(wloginLoginInfo.mUin), wloginLoginInfo.mUin);
        }
        return new WloginLastLoginInfo(wloginLoginInfo.mAccount, wloginLoginInfo.mUin);
    }

    public Boolean IsUserHaveA1(String str, long j) {
        if (str == null) {
            return Boolean.valueOf(false);
        }
        WloginSigInfo a;
        long parseLong;
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
            a = this.mG.a(parseLong, j);
            if (a == null) {
            }
        } else {
            parseLong = this.mG.b(str);
            if (parseLong == 0) {
                a = null;
            }
            a = this.mG.a(parseLong, j);
            if (a == null) {
            }
        }
        if (a == null || a._en_A1 == null || a._en_A1.length <= 0) {
            util.LOGI("userAccount:" + str + " dwAppid:" + j + " IsUserHaveA1 return: null", str);
            return Boolean.valueOf(false);
        }
        util.LOGI("userAccount:" + str + " dwAppid:" + j + " IsUserHaveA1 return: not null", str);
        return Boolean.valueOf(true);
    }

    private byte[] GetA1ByAccount(String str, long j) {
        if (str == null) {
            return null;
        }
        WloginSigInfo a;
        long parseLong;
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
            a = this.mG.a(parseLong, j);
            if (a == null) {
            }
        } else {
            parseLong = this.mG.b(str);
            if (parseLong == 0) {
                a = null;
            }
            a = this.mG.a(parseLong, j);
            if (a == null) {
            }
        }
        if (a == null || a._en_A1 == null || a._en_A1.length <= 0) {
            util.LOGI("userAccount:" + str + " dwAppid:" + j + " GetA1ByAccount return: null", str);
            return null;
        }
        util.LOGI("userAccount:" + str + " dwAppid:" + j + " GetA1ByAccount return: not null", str);
        return (byte[]) a._en_A1.clone();
    }

    private byte[] GetNoPicSigByAccount(String str, long j) {
        if (str == null) {
            return null;
        }
        WloginSigInfo a;
        long parseLong;
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
            a = this.mG.a(parseLong, j);
            if (a == null) {
            }
        } else {
            parseLong = this.mG.b(str);
            if (parseLong == 0) {
                a = null;
            }
            a = this.mG.a(parseLong, j);
            if (a == null) {
            }
        }
        if (a == null || a._noPicSig == null || a._noPicSig.length <= 0) {
            util.LOGI("userAccount:" + str + " dwAppid:" + j + " GetNoPicSigByAccount return: null", str);
            return null;
        }
        util.LOGI("userAccount:" + str + " dwAppid:" + j + " GetNoPicSigByAccount return: not null", str);
        return (byte[]) a._noPicSig.clone();
    }

    public int GetOpenKeyWithoutPasswd(String str, long j, long j2, int i, WUserSigInfo wUserSigInfo) {
        return GetStWithoutPasswd(str, j, this.mOpenAppid, -1, i, j2, null, wUserSigInfo, (byte[][]) null, (byte[][]) null, 0, null);
    }

    public int GetStWithoutPasswd(byte[] bArr, long j, int i, WUserSigInfo wUserSigInfo) {
        String str = "";
        if (bArr == null || bArr.length <= 0) {
            return -1017;
        }
        Object decrypt = cryptor.decrypt(bArr, 0, bArr.length, u.B);
        if (decrypt == null || decrypt.length <= 0) {
            return -1017;
        }
        if (2 > decrypt.length) {
            return -1017;
        }
        int buf_to_int16 = util.buf_to_int16(decrypt, 0);
        if (buf_to_int16 <= 0 || buf_to_int16 + 2 > decrypt.length) {
            return -1017;
        }
        String str2 = new String(decrypt, 2, buf_to_int16);
        buf_to_int16 += 2;
        if (buf_to_int16 + 8 > decrypt.length) {
            return -1017;
        }
        long buf_to_int64 = util.buf_to_int64(decrypt, buf_to_int16);
        buf_to_int16 += 8;
        if (buf_to_int16 + 2 > decrypt.length) {
            return -1017;
        }
        int buf_to_int162 = util.buf_to_int16(decrypt, buf_to_int16);
        buf_to_int16 += 2;
        if (buf_to_int162 <= 0 || buf_to_int16 + buf_to_int162 > decrypt.length) {
            return -1017;
        }
        Object obj = new byte[buf_to_int162];
        System.arraycopy(decrypt, buf_to_int16, obj, 0, obj.length);
        buf_to_int16 += buf_to_int162;
        if (buf_to_int16 + 2 > decrypt.length) {
            return -1017;
        }
        buf_to_int162 = util.buf_to_int16(decrypt, buf_to_int16);
        buf_to_int16 += 2;
        if (buf_to_int162 <= 0 || buf_to_int16 + buf_to_int162 > decrypt.length) {
            return -1017;
        }
        Object obj2 = new byte[buf_to_int162];
        System.arraycopy(decrypt, buf_to_int16, obj2, 0, obj2.length);
        buf_to_int16 += buf_to_int162;
        byte[][] bArr2 = new byte[][]{new byte[1], (byte) 2, obj};
        bArr2[2] = obj2;
        return GetStWithoutPasswd(str2, buf_to_int64, j, -1, i, 1, null, wUserSigInfo, (byte[][]) null, bArr2, 0, null);
    }

    public int GetStWithoutPasswd(String str, long j, long j2, long j3, int i, byte[] bArr, byte[] bArr2, byte[] bArr3, WUserSigInfo wUserSigInfo) {
        byte[][] bArr4 = new byte[][]{new byte[1], (byte) 1, bArr, bArr2};
        bArr4[3] = bArr3;
        return GetStWithoutPasswd(str, j, j2, -1, i, j3, null, wUserSigInfo, (byte[][]) null, bArr4, 0, null);
    }

    public int GetStWithoutPasswd(String str, long j, long j2, long j3, int i, WUserSigInfo wUserSigInfo) {
        return GetStWithoutPasswd(str, j, j2, -1, i, j3, null, wUserSigInfo, (byte[][]) null, (byte[][]) null, 0, null);
    }

    private int GetStWithoutPasswd(String str, long j, long j2, long j3, int i, WUserSigInfo wUserSigInfo, WtTicketPromise wtTicketPromise) {
        return GetStWithoutPasswd(str, j, j2, -1, i, j3, null, wUserSigInfo, (byte[][]) null, (byte[][]) null, 0, wtTicketPromise);
    }

    @Deprecated
    public int GetStWithoutPasswd(String str, long j, long j2, WUserSigInfo wUserSigInfo) {
        return GetStWithoutPasswd(str, j, j2, -1, this.mMainSigMap, 1, null, wUserSigInfo, (byte[][]) null, (byte[][]) null, 0, null);
    }

    public int GetStWithoutPasswd(String str, long j, long j2, long j3, int i, long j4, long[] jArr, WUserSigInfo wUserSigInfo, byte[][] bArr, byte[][] bArr2) {
        return GetStWithoutPasswd(str, j, j2, j3, i, j4, jArr, wUserSigInfo, bArr, bArr2, 0, null);
    }

    private void printTicket(WloginSigInfo wloginSigInfo) {
        util.LOGI("a1 " + wloginSigInfo._en_A1.length);
        util.LOGI("a2 " + wloginSigInfo._TGT.length);
        util.LOGI("skey " + wloginSigInfo._sKey.length);
        util.LOGI("pskey " + wloginSigInfo._psKey.length);
        util.LOGI("superkey " + wloginSigInfo._superKey.length);
        util.LOGI("d2 " + wloginSigInfo._D2.length);
        util.LOGI("d2key " + wloginSigInfo._D2Key.length);
    }

    private int GetStWithoutPasswd(String str, long j, long j2, long j3, int i, long j4, long[] jArr, WUserSigInfo wUserSigInfo, byte[][] bArr, byte[][] bArr2, int i2, WtTicketPromise wtTicketPromise) {
        if (str == null || wUserSigInfo == null) {
            return -1017;
        }
        int i3 = i | Opcodes.AND_LONG_2ADDR;
        if (2 == j4) {
            i3 &= -33554433;
        }
        if (i2 == 0) {
            new HelperThread(this, this.mHelperHandler, wtTicketPromise, str, j, j2, j3, i3, j4, jArr, wUserSigInfo, bArr, bArr2, "GetStWithoutPasswd").RunReq(5);
            return -1001;
        }
        int i4;
        u a = this.mG.a(0);
        wUserSigInfo._seqence = a.h;
        async_context b = u.b(a.h);
        util.LOGI("start GetStWithoutPasswd:user:" + str + " dwSrcAppid:" + j + " dwDstAppid:" + j2 + " dwDstAppPri:" + j3 + " dwMainSigMap:0x" + Integer.toHexString(i3) + " dwSubDstAppid:" + j4 + " Seq:" + a.h, str);
        int i5 = util.get_saved_network_type(this.mContext);
        u.D = util.get_network_type(this.mContext);
        if (i5 != u.D) {
            util.set_net_retry_type(this.mContext, 0);
            util.save_network_type(this.mContext, u.D);
        }
        u.F = util.get_apn_string(this.mContext).getBytes();
        a.g = str;
        a.f = 0;
        b._sappid = j;
        b._appid = j2;
        b._sub_appid = j4;
        b._main_sigmap = i3;
        b._last_err_msg = new ErrMsg();
        if (jArr != null) {
            b._sub_appid_list = (long[]) jArr.clone();
        }
        if (wUserSigInfo._reserveData == null || wUserSigInfo._reserveData.length <= 3) {
            a.i = 0;
        } else {
            a.i = util.buf_to_int32(wUserSigInfo._reserveData, 0);
            util.LOGI("MSF SSO SEQ:" + a.i, str);
        }
        u.al.add_t2(new report_t2("exchg", new String(u.C), System.currentTimeMillis(), j2, j4, jArr));
        long parseLong;
        byte[] GetA1ByAccount;
        byte[] GetNoPicSigByAccount;
        WloginSigInfo a2;
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
            a.f = parseLong;
            wUserSigInfo.uin = "" + parseLong;
            if (bArr2 == null) {
            }
            if (bArr2 == null) {
            }
            a.j();
            GetA1ByAccount = GetA1ByAccount(String.valueOf(a.f), j);
            GetNoPicSigByAccount = GetNoPicSigByAccount(String.valueOf(a.f), j);
            if (GetA1ByAccount != null) {
            }
            util.LOGI("user:" + str + " exchange A2 from A2.", "" + a.f);
            a2 = a.a(parseLong, j);
            if (a2 != null) {
            }
            i4 = -1004;
        } else {
            int a3;
            parseLong = a.b(str);
            if (parseLong == 0) {
                util.LOGI("user:" + str + " have not found uin record.", str);
                i4 = -1003;
            }
            a.f = parseLong;
            wUserSigInfo.uin = "" + parseLong;
            if (bArr2 == null && bArr2.length == 4 && bArr2[0] != null && bArr2[0].length == 1 && bArr2[0][0] == (byte) 1) {
                util.LOGI("user:" + str + " exchange A2 from A2/D2/KEY.", "" + a.f);
                if (bArr2[1] == null || bArr2[1].length == 0 || bArr2[2] == null || bArr2[2].length == 0 || bArr2[3] == null || bArr2[3].length == 0) {
                    i4 = -1004;
                } else {
                    a.b = MD5.toMD5Byte(bArr2[3]);
                    a3 = new n(a).a(parseLong, j2, j4, 1, i3, bArr2[1], this.mMiscBitmap, this.mSubSigMap, jArr, bArr2[2], wUserSigInfo);
                }
            } else if (bArr2 == null && bArr2.length == 3 && bArr2[0] != null && bArr2[0].length == 1 && bArr2[0][0] == (byte) 2) {
                util.LOGI("user:" + str + " exchange A2 from A2/A2KEY.", "" + a.f);
                if (bArr2[1] == null || bArr2[1].length == 0 || bArr2[2] == null || bArr2[2].length == 0) {
                    i4 = -1004;
                } else {
                    a.b = bArr2[2];
                    a3 = new n(a).a(parseLong, j2, j4, 1, i3, bArr2[1], this.mMiscBitmap, this.mSubSigMap, jArr, null, wUserSigInfo);
                }
            } else {
                a.j();
                GetA1ByAccount = GetA1ByAccount(String.valueOf(a.f), j);
                GetNoPicSigByAccount = GetNoPicSigByAccount(String.valueOf(a.f), j);
                if (GetA1ByAccount != null || GetA1ByAccount.length <= 0 || GetNoPicSigByAccount == null || GetNoPicSigByAccount.length <= 0) {
                    util.LOGI("user:" + str + " exchange A2 from A2.", "" + a.f);
                    a2 = a.a(parseLong, j);
                    if (a2 != null || a2._TGT == null || a2._TGT.length == 0 || a2.iSExpireA2(u.f())) {
                        i4 = -1004;
                    } else {
                        util.LOGI("user:" + str + " exchange A2 from A2 without Priority.", "" + a.f);
                        printTicket(a2);
                        a.b = a2._TGTKey;
                        a3 = new n(a).a(parseLong, j2, j4, 1, i3, a2._TGT, this.mMiscBitmap, this.mSubSigMap, jArr, null, wUserSigInfo);
                    }
                } else {
                    byte[] bArr3;
                    util.LOGI("user:" + str + " exchange A2 from A1.", "" + a.f);
                    b._tmp_pwd = GetA1ByAccount;
                    b._tmp_no_pic_sig = GetNoPicSigByAccount;
                    if (wUserSigInfo._in_ksid == null || wUserSigInfo._in_ksid.length <= 0) {
                        bArr3 = u.aa;
                    } else {
                        bArr3 = (byte[]) wUserSigInfo._in_ksid.clone();
                    }
                    z zVar = new z(a);
                    a2 = FindUserSig(parseLong, j);
                    if (a2 != null) {
                        zVar.a(a2);
                    }
                    i5 = zVar.a(j2, 1, a.f, 0, u.ad, GetA1ByAccount, GetNoPicSigByAccount, this.mMiscBitmap, this.mSubSigMap, jArr, i3, j4, 1, u.y, 0, 0, 1, bArr3, j, wUserSigInfo);
                    if (i5 == 204) {
                        i5 = new q(a).a(this.mMiscBitmap, this.mSubSigMap, jArr, wUserSigInfo);
                    }
                    a3 = i5;
                }
            }
            if (a3 == 0) {
                a2 = a.a(parseLong, j2);
                if (a2 == null) {
                    i4 = -1004;
                } else {
                    wUserSigInfo.get_clone(a2);
                    if (!(jArr == null || bArr == null || jArr.length * 2 != bArr.length)) {
                        for (int i6 = 0; i6 < jArr.length; i6++) {
                            WloginSigInfo a4 = a.a(parseLong, jArr[i6]);
                            if (a4 != null) {
                                bArr[i6 * 2] = (byte[]) a4._userSt_Key.clone();
                                bArr[(i6 * 2) + 1] = (byte[]) a4._userStSig.clone();
                            }
                        }
                    }
                }
            }
            i4 = a3;
        }
        Ticket GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, 128);
        if (GetUserSigInfoTicket == null) {
            GetUserSigInfoTicket = new Ticket();
        }
        u.al.commit_t2(a.f, a.g, util.format_ret_code(i4), i4);
        if (i4 != 0) {
            RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid, 0);
        } else if (!(GetUserSigInfoTicket._sig == null || GetUserSigInfoTicket._sig.length == 0)) {
            RequestReport(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid);
        }
        if (!(a.d == null || a.d.a() == 0)) {
            this.mG.d = a.d;
            RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid, 1);
        }
        u.b();
        a.h();
        util.LOGI("end GetStWithoutPasswd:user:" + str + " dwSrcAppid:" + j + " dwDstAppid:" + j2 + " dwDstAppPri:" + j3 + " dwMainSigMap:0x" + Integer.toHexString(i3) + " dwSubDstAppid:" + j4 + " Seq:" + a.h + " ret=" + i4, "" + a.f);
        return i4;
    }

    public void RefreshMemorySig() {
        this.mG.j();
    }

    public WUserSigInfo GetLocalSig(String str, long j) {
        WUserSigInfo wUserSigInfo;
        Exception e;
        if (str == null) {
            util.LOGI("userAccount null", "");
            return null;
        }
        try {
            long parseLong;
            if (util.check_uin_account(str).booleanValue()) {
                parseLong = Long.parseLong(str);
            } else {
                parseLong = this.mG.b(str);
                if (parseLong == 0) {
                    return null;
                }
            }
            WloginSigInfo a = this.mG.a(parseLong, j);
            if (a != null) {
                wUserSigInfo = new WUserSigInfo();
                try {
                    wUserSigInfo.uin = str;
                    wUserSigInfo.get_clone(a);
                } catch (Exception e2) {
                    e = e2;
                    util.printException(e, str);
                    return wUserSigInfo;
                }
            }
            wUserSigInfo = null;
        } catch (Exception e3) {
            Exception exception = e3;
            wUserSigInfo = null;
            e = exception;
            util.printException(e, str);
            return wUserSigInfo;
        }
        return wUserSigInfo;
    }

    public Ticket GetLocalTicket(String str, long j, int i) {
        util.LOGI("GetLocalTicket appid=" + j, str);
        if (str != null) {
            return GetUserSigInfoTicket(GetLocalSig(str, j), i);
        }
        util.LOGI("userAccount null", "");
        return null;
    }

    public Ticket GetPskey(String str, long j, String[] strArr, WtTicketPromise wtTicketPromise) {
        Bundle bundle = new Bundle();
        bundle.putStringArray("domains", strArr);
        String str2 = "";
        for (String str3 : strArr) {
            str2 = str2 + str3 + ",";
        }
        util.LOGI("GetPskey appid " + j + " domains " + str2, str);
        return GetTicket(str, j, 1048576, wtTicketPromise, bundle);
    }

    public Ticket GetSkey(String str, long j, WtTicketPromise wtTicketPromise) {
        return GetTicket(str, j, 4096, wtTicketPromise, null);
    }

    public Ticket GetTicket(String str, long j, int i, WtTicketPromise wtTicketPromise, Bundle bundle) {
        Object obj;
        long f;
        StringBuilder append = new StringBuilder().append("GetTicket ").append(str).append(", ").append(j).append(" sig ").append(Integer.toHexString(i)).append(" ");
        if (bundle == null) {
            obj = "null";
        } else {
            obj = Integer.valueOf(bundle.size());
        }
        util.LOGI(append.append(obj).toString(), "");
        String[] strArr = null;
        int i2 = 2;
        do {
            int isPskeyExpired;
            WUserSigInfo GetLocalSig = GetLocalSig(str, j);
            if (GetLocalSig != null) {
                Ticket GetUserSigInfoTicket = GetUserSigInfoTicket(GetLocalSig, i);
                if (GetUserSigInfoTicket == null || GetUserSigInfoTicket._sig == null || GetUserSigInfoTicket._sig.length == 0) {
                    if (i2 == 1) {
                        break;
                    }
                    RefreshMemorySig();
                    i2--;
                    continue;
                } else {
                    f = u.f();
                    if (bundle != null && i == 1048576) {
                        strArr = bundle.getStringArray("domains");
                    }
                    isPskeyExpired = isPskeyExpired(i, strArr, GetUserSigInfoTicket, f, i2);
                    if (isPskeyExpired == 1) {
                        break;
                    } else if (isPskeyExpired == 2) {
                        i2--;
                        continue;
                    } else if (isPskeyExpired == 3) {
                        return GetUserSigInfoTicket;
                    } else {
                        util.LOGI("GetTicket sigType:0x" + Integer.toHexString(i) + " expires in " + (((GetUserSigInfoTicket._expire_time - f) / 60) / 60) + "h", "");
                        if (f >= GetUserSigInfoTicket._expire_time) {
                            if (i2 == 1) {
                                break;
                            }
                            RefreshMemorySig();
                            i2--;
                            continue;
                        } else {
                            return GetUserSigInfoTicket;
                        }
                    }
                }
            } else if (i2 == 1) {
                break;
            } else {
                RefreshMemorySig();
                i2--;
                continue;
            }
        } while (i2 > 0);
        if (IsNeedLoginWithPasswd(str, j).booleanValue()) {
            ErrMsg errMsg = new ErrMsg();
            errMsg.setType(-1004);
            if (wtTicketPromise != null) {
                wtTicketPromise.Failed(errMsg);
            }
        } else {
            int i3;
            WUserSigInfo wUserSigInfo = new WUserSigInfo();
            if (bundle != null) {
                i3 = bundle.getInt("subappid", 1);
            } else {
                i3 = 1;
            }
            if (strArr != null) {
                int min = Math.min(20, strArr.length);
                for (isPskeyExpired = 0; isPskeyExpired < min; isPskeyExpired++) {
                    String str2 = strArr[isPskeyExpired];
                    if (str2 != null && str2.length() > 0) {
                        wUserSigInfo._domains.add(str2);
                    }
                }
            }
            final WtTicketPromise wtTicketPromise2 = wtTicketPromise;
            final String str3 = str;
            f = j;
            i2 = i;
            final Bundle bundle2 = bundle;
            GetStWithoutPasswd(str, j, j, (long) i3, i, wUserSigInfo, new WtTicketPromise() {
                public void Done(Ticket ticket) {
                    if (wtTicketPromise2 != null) {
                        wtTicketPromise2.Done(WtloginHelper.this.GetTicket(str3, f, i2, null, bundle2));
                    }
                }

                public void Failed(ErrMsg errMsg) {
                    if (wtTicketPromise2 != null) {
                        wtTicketPromise2.Failed(errMsg);
                    }
                }

                public void Timeout(ErrMsg errMsg) {
                    if (wtTicketPromise2 != null) {
                        wtTicketPromise2.Timeout(errMsg);
                    }
                }
            });
        }
        return null;
    }

    private int isPskeyExpired(int i, String[] strArr, Ticket ticket, long j, int i2) {
        if (i != 1048576 || strArr == null || strArr.length <= 0) {
            return 0;
        }
        int i3 = 0;
        for (String str : strArr) {
            if (!(str == null || str.length() == 0)) {
                boolean z;
                boolean z2;
                String str2;
                int intValue;
                int indexOf = str.indexOf(40);
                int indexOf2 = str.indexOf(41);
                if (indexOf != 0 || indexOf2 <= 0) {
                    z = false;
                    z2 = true;
                    str2 = str;
                } else {
                    intValue = Integer.valueOf(str.substring(indexOf + 1, indexOf2)).intValue();
                    z = (intValue & SigType.WLOGIN_PT4Token) > 0;
                    z2 = (1048576 & intValue) > 0;
                    str2 = str.substring(indexOf2 + 1);
                }
                boolean z3 = z2 && (ticket._pskey_map.get(str2) == null || Ticket.isPskeyExpired(((Long) ticket._pskey_expire.get(str2)).longValue()));
                boolean z4 = z && (ticket._pt4token_map.get(str2) == null || Ticket.isPskeyExpired(((Long) ticket._pt4token_expire.get(str2)).longValue()));
                if (z3 || z4) {
                    intValue = i3 + 1;
                    strArr[i3] = str;
                    util.LOGI("isPskeyExpired refresh " + str2 + " need refresh pskey:" + z3 + " and pt4token:" + z4, "");
                } else {
                    intValue = i3;
                }
                util.LOGI("isPskeyExpired domain " + str2 + " get pskey:" + z2 + " get pt4token:" + z, "");
                i3 = intValue;
            }
        }
        if (i3 == 0) {
            return 3;
        }
        while (i3 < strArr.length) {
            util.LOGI("isPskeyExpired domain " + strArr[i3] + " cleared", "");
            strArr[i3] = null;
            i3++;
        }
        if (i2 == 1) {
            return 1;
        }
        RefreshMemorySig();
        return 2;
    }

    public static Ticket GetUserSigInfoTicket(WUserSigInfo wUserSigInfo, int i) {
        if (i == SigType.WLOGIN_LHSIG) {
            util.LOGI("get lhsig", "");
            return new Ticket((int) SigType.WLOGIN_LHSIG, WloginSigInfo._LHSig, null, u.f(), 0);
        } else if (i == SigType.WLOGIN_QRPUSH) {
            util.LOGI("get qrpushsig", "");
            return new Ticket(SigType.WLOGIN_QRPUSH, WloginSigInfo._QRPUSHSig, null, u.f(), 0);
        } else if (wUserSigInfo == null) {
            util.LOGI("userInfo is null " + Integer.toHexString(i), "");
            return null;
        } else if (wUserSigInfo._tickets == null) {
            util.LOGI("tickets is null " + Integer.toHexString(i), wUserSigInfo.uin);
            return null;
        } else {
            util.LOGI("GetUserSigInfoTicket ticket type:0x" + Integer.toHexString(i), "");
            if (wUserSigInfo._tickets != null) {
                for (int i2 = 0; i2 < wUserSigInfo._tickets.size(); i2++) {
                    Ticket ticket = (Ticket) wUserSigInfo._tickets.get(i2);
                    if (ticket._type == i) {
                        util.LOGI("GetUserSigInfoTicket type:0x" + Integer.toHexString(i) + " sig:" + util.buf_len(ticket._sig) + " key:" + util.buf_len(ticket._sig_key) + " create time:" + ticket._create_time + " expire time:" + ticket._expire_time, "");
                        return ticket;
                    }
                }
            }
            return null;
        }
    }

    public static byte[] GetTicketSig(WUserSigInfo wUserSigInfo, int i) {
        Ticket GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, i);
        if (GetUserSigInfoTicket != null) {
            return GetUserSigInfoTicket._sig;
        }
        return new byte[0];
    }

    public static byte[] GetTicketSigKey(WUserSigInfo wUserSigInfo, int i) {
        if (i == 64 || i == 262144 || i == 128 || i == 16384 || i == 32768 || i == SigType.WLOGIN_PF) {
            Ticket GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, i);
            if (GetUserSigInfoTicket != null) {
                return GetUserSigInfoTicket._sig_key;
            }
            return new byte[0];
        }
        throw null;
    }

    public byte[] GetA2A2KeyBuf(String str, long j) {
        Ticket GetLocalTicket = GetLocalTicket(str, j, 64);
        if (GetLocalTicket == null || GetLocalTicket._sig == null || GetLocalTicket._sig.length <= 0 || GetLocalTicket._sig_key == null || GetLocalTicket._sig_key.length <= 0 || u.B == null || u.B.length <= 0) {
            return null;
        }
        Object obj = new byte[((((((str.getBytes().length + 2) + 8) + 2) + GetLocalTicket._sig.length) + 2) + GetLocalTicket._sig_key.length)];
        util.int16_to_buf(obj, 0, str.getBytes().length);
        System.arraycopy(str.getBytes(), 0, obj, 2, str.getBytes().length);
        int length = str.getBytes().length + 2;
        util.int64_to_buf(obj, length, j);
        length += 8;
        util.int16_to_buf(obj, length, GetLocalTicket._sig.length);
        length += 2;
        System.arraycopy(GetLocalTicket._sig, 0, obj, length, GetLocalTicket._sig.length);
        length += GetLocalTicket._sig.length;
        util.int16_to_buf(obj, length, GetLocalTicket._sig_key.length);
        length += 2;
        System.arraycopy(GetLocalTicket._sig_key, 0, obj, length, GetLocalTicket._sig_key.length);
        int length2 = GetLocalTicket._sig_key.length + length;
        return cryptor.encrypt(obj, 0, obj.length, u.B);
    }

    public int GetA1WithA1(String str, long j, long j2, byte[] bArr, long j3, long j4, long j5, byte[] bArr2, byte[] bArr3, WUserSigInfo wUserSigInfo, WFastLoginInfo wFastLoginInfo) {
        return GetA1WithA1(str, j, j2, this.mMainSigMap, bArr, j3, j4, j5, bArr2, bArr3, wUserSigInfo, wFastLoginInfo, 0);
    }

    private int GetA1WithA1(String str, long j, long j2, int i, byte[] bArr, long j3, long j4, long j5, byte[] bArr2, byte[] bArr3, WUserSigInfo wUserSigInfo, WFastLoginInfo wFastLoginInfo, int i2) {
        if (str == null || bArr == null || bArr2 == null || bArr3 == null || wUserSigInfo == null || wFastLoginInfo == null) {
            return -1017;
        }
        int i3 = i | Opcodes.AND_LONG_2ADDR;
        if (i2 == 0) {
            new HelperThread(this, this.mHelperHandler, str, j, j2, i3, bArr, j3, j4, j5, bArr2, bArr3, wUserSigInfo, wFastLoginInfo, "GetA1WithA1").RunReq(6);
            return -1001;
        }
        int i4;
        u a = this.mG.a(0);
        wUserSigInfo._seqence = a.h;
        async_context b = u.b(a.h);
        util.LOGI("wtlogin login with GetA1WithA1:" + str + " dwSrcAppid:" + j + " dwMainSigMap:" + i3 + " dwSubSrcAppid:" + j2 + " dstAppName:" + new String(bArr) + " dwDstAppid:" + j4 + " dwSubDstAppid:" + j5 + " Seq:" + a.h + " ...", str);
        int i5 = util.get_saved_network_type(this.mContext);
        u.D = util.get_network_type(this.mContext);
        if (i5 != u.D) {
            util.set_net_retry_type(this.mContext, 0);
            util.save_network_type(this.mContext, u.D);
        }
        u.F = util.get_apn_string(this.mContext).getBytes();
        a.g = str;
        a.f = 0;
        b._sappid = j;
        b._appid = j;
        b._sub_appid = j2;
        b._main_sigmap = i3;
        b._last_err_msg = new ErrMsg();
        u.al.add_t2(new report_t2("login", new String(u.C), System.currentTimeMillis(), j4, j5, null));
        long parseLong;
        byte[] GetA1ByAccount;
        byte[] GetNoPicSigByAccount;
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
            a.f = parseLong;
            a.j();
            GetA1ByAccount = GetA1ByAccount(str, j);
            GetNoPicSigByAccount = GetNoPicSigByAccount(str, j);
            if (GetA1ByAccount != null) {
            }
            util.LOGI("user:" + str + " have no a1 or pic_sig.", str);
            i4 = util.E_A1_FORMAT;
        } else {
            parseLong = a.b(str);
            if (parseLong == 0) {
                util.LOGI("user:" + str + " have not found uin record.", str);
                i4 = -1003;
            }
            a.f = parseLong;
            a.j();
            GetA1ByAccount = GetA1ByAccount(str, j);
            GetNoPicSigByAccount = GetNoPicSigByAccount(str, j);
            if (GetA1ByAccount != null || GetA1ByAccount.length <= 0) {
                util.LOGI("user:" + str + " have no a1 or pic_sig.", str);
                i4 = util.E_A1_FORMAT;
            } else {
                util.LOGI("user:" + str + " login with A1 exchange A1.", str);
                i4 = new m(a).a(parseLong, j, j2, 1, i3, GetA1ByAccount, GetNoPicSigByAccount, this.mMiscBitmap, this.mSubSigMap, null, bArr, j3, j4, j5, bArr2, bArr3, wUserSigInfo);
                if (i4 == 0) {
                    WloginSigInfo a2 = a.a(parseLong, j);
                    if (a2 == null) {
                        i4 = -1004;
                    } else {
                        wUserSigInfo.get_clone(a2);
                        wFastLoginInfo.get_clone(a.j);
                    }
                }
            }
        }
        Ticket GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, 128);
        if (GetUserSigInfoTicket == null) {
            GetUserSigInfoTicket = new Ticket();
        }
        u.al.commit_t2(a.f, a.g, util.format_ret_code(i4), i4);
        if (i4 != 0) {
            RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, j, 0);
        } else if (!(GetUserSigInfoTicket._sig == null || GetUserSigInfoTicket._sig.length == 0)) {
            RequestReport(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, j);
        }
        if (!(a.d == null || a.d.a() == 0)) {
            this.mG.d = a.d;
            RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, j, 1);
        }
        u.b();
        a.h();
        util.LOGI("wtlogin login with GetA1WithA1:" + str + " dwSrcAppid:" + j + " dwMainSigMap:" + i3 + " dwSubSrcAppid:" + j2 + " dstAppName:" + new String(bArr) + " dwDstAppid:" + j4 + " dwSubDstAppid:" + j5 + " Seq:" + a.h + " ret=" + i4, str);
        return i4;
    }

    public int getStWithQrSig(String str, long j, long j2, int i, WUserSigInfo wUserSigInfo) {
        return getStWithQrSig(str, j, j2, i, wUserSigInfo, 0);
    }

    private int getStWithQrSig(String str, long j, long j2, int i, WUserSigInfo wUserSigInfo, int i2) {
        if (str == null || wUserSigInfo == null) {
            return -1017;
        }
        int i3 = i | Opcodes.AND_LONG_2ADDR;
        if (i2 == 0) {
            new HelperThread(this, this.mHelperHandler, str, j, i3, j2, wUserSigInfo, "getStWithQrSig").RunReq(17);
            return -1001;
        }
        int i4 = 0;
        u a = this.mG.a(0);
        wUserSigInfo._seqence = a.h;
        this.mAysncSeq = a.h;
        j.z = "";
        async_context b = u.b(a.h);
        util.LOGI("start getStWithQrSig:user:" + str + " appid:" + j + " sigMap:0x" + Integer.toHexString(i3) + " subAppid:" + j2 + " Seq:" + a.h, str);
        int i5 = util.get_saved_network_type(this.mContext);
        u.D = util.get_network_type(this.mContext);
        if (i5 != u.D) {
            util.set_net_retry_type(this.mContext, 0);
            util.save_network_type(this.mContext, u.D);
        }
        u.F = util.get_apn_string(this.mContext).getBytes();
        if (util.check_uin_account(str).booleanValue()) {
            long parseLong = Long.parseLong(str);
            wUserSigInfo.uin = str;
            a.g = str;
            a.f = parseLong;
            b._sappid = j;
            b._appid = j;
            b._sub_appid_list = null;
            b._sub_appid = j2;
            b._main_sigmap = i3;
            b._login_bitmap = wUserSigInfo._login_bitmap;
            b._last_err_msg = new ErrMsg();
            if (wUserSigInfo._reserveData == null || wUserSigInfo._reserveData.length <= 3) {
                a.i = 0;
            } else {
                a.i = util.buf_to_int32(wUserSigInfo._reserveData, 0);
                util.LOGI("MSF SSO SEQ:" + a.i, str);
            }
            u.al.add_t2(new report_t2("login", new String(u.C), System.currentTimeMillis(), j, j2, null));
            if (c.q != null && c.q.length > 0) {
                b._tmp_pwd = c.q;
                b._tmp_no_pic_sig = c.r;
                c.q = null;
                c.r = null;
            }
            if (b._tmp_pwd == null || b._tmp_pwd.length < 16) {
                i4 = util.E_A1_FORMAT;
            } else {
                byte[] bArr;
                b._tmp_pwd_type = 1;
                if (wUserSigInfo._in_ksid == null || wUserSigInfo._in_ksid.length <= 0) {
                    bArr = u.aa;
                } else {
                    bArr = (byte[]) wUserSigInfo._in_ksid.clone();
                }
                if (b._tmp_pwd_type != 0) {
                    util.LOGI("user:" + str + " login with qrsig", str);
                    l lVar = new l(a, this.mContext);
                    lVar.g();
                    i4 = lVar.a(j, j2, a.f, 0, u.ad, b._tmp_pwd, b._tmp_no_pic_sig, this.mMiscBitmap, this.mSubSigMap, null, i3, j2, u.y, 0, 0, 1, bArr, wUserSigInfo);
                }
                if (i4 == 0 || i4 == 160) {
                    WloginSigInfo a2 = a.a(parseLong, j);
                    if (a2 == null) {
                        i4 = -1004;
                    } else {
                        wUserSigInfo.get_clone(a2);
                    }
                }
            }
            Ticket GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, 128);
            if (GetUserSigInfoTicket == null) {
                GetUserSigInfoTicket = new Ticket();
            }
            u.al.commit_t2(a.f, a.g, util.format_ret_code(i4), i4);
            if (i4 == 0) {
                if (!(GetUserSigInfoTicket._sig == null || GetUserSigInfoTicket._sig.length == 0)) {
                    RequestReport(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid);
                }
            } else if (!(i4 == 2 || i4 == 160)) {
                RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid, 0);
            }
            if (!(a.d == null || a.d.a() == 0)) {
                this.mG.d = a.d;
                RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid, 1);
            }
            u.b();
            a.h();
            util.LOGI("end getStWithQrSig user:" + str + " appid:" + j + " sigMap:0x" + Integer.toHexString(i3) + " subAppid:" + j2 + " Seq:" + a.h + " ret=" + i4, "" + a.f);
            return i4;
        }
        util.LOGI("userAccount " + str + " isn't valid", "");
        return -1017;
    }

    public int GetStWithPasswdReserved(String str, long j, long j2, int i, String str2, WUserSigInfo wUserSigInfo) {
        return GetStWithPasswd(str, j, i, j2, null, false, str2, wUserSigInfo, (byte[][]) null, false, 0);
    }

    public int GetStWithPasswdMd5(String str, long j, long j2, int i, String str2, WUserSigInfo wUserSigInfo) {
        return GetStWithPasswd(str, j, i, j2, null, true, str2, wUserSigInfo, (byte[][]) null, false, 0);
    }

    @Deprecated
    public int GetStWithPasswdReserved(String str, long j, String str2, WUserSigInfo wUserSigInfo) {
        return GetStWithPasswd(str, j, this.mMainSigMap, 1, null, false, str2, wUserSigInfo, (byte[][]) null, false, 0);
    }

    @Deprecated
    public int GetStWithPasswdMd5(String str, long j, String str2, WUserSigInfo wUserSigInfo) {
        return GetStWithPasswd(str, j, this.mMainSigMap, 1, null, true, str2, wUserSigInfo, (byte[][]) null, false, 0);
    }

    public int GetStWithPasswdReserved(String str, long j, int i, long j2, long[] jArr, boolean z, String str2, WUserSigInfo wUserSigInfo, byte[][] bArr) {
        return GetStWithPasswd(str, j, i, j2, jArr, z, str2, wUserSigInfo, bArr, false, 0);
    }

    private int GetFastLoginInfo(byte[] bArr, async_context oicq_wlogin_sdk_request_async_context) {
        if (bArr == null || bArr.length <= 3 || oicq_wlogin_sdk_request_async_context == null) {
            return -1017;
        }
        i iVar = new i();
        o oVar = new o();
        bd bdVar = new bd();
        ap apVar = new ap();
        int length = bArr.length;
        if (iVar.c(bArr, 3, length) < 0) {
            util.LOGI("fast login info no tgtgt data", "");
            return -1017;
        } else if (oVar.c(bArr, 3, length) < 0) {
            util.LOGI("fast login info no gtkey data", "");
            return -1017;
        } else if (bdVar.c(bArr, 3, length) < 0) {
            util.LOGI("fast login info no nopicsig data", "");
            return -1017;
        } else {
            if (apVar.c(bArr, 3, length) > 0) {
                Object c = apVar.c();
                byte[] bArr2 = u.A;
                util.LOGD("new guid:" + util.buf_to_string(c) + " old guid:" + util.buf_to_string(bArr2));
                if (!Arrays.equals(c, bArr2)) {
                    util.LOGI("fast login info guid not equal", "");
                    util.saveGuidToFile(u.t, c);
                    u.A = (byte[]) c.clone();
                    u.B = (byte[]) c.clone();
                }
            }
            oicq_wlogin_sdk_request_async_context._tmp_pwd = oicq_request.b(iVar.c(), oVar.c());
            oicq_wlogin_sdk_request_async_context._tmp_no_pic_sig = bdVar.c();
            return 0;
        }
    }

    private int GetStWithPasswd(String str, long j, int i, long j2, long[] jArr, boolean z, String str2, WUserSigInfo wUserSigInfo, byte[][] bArr, boolean z2, int i2) {
        if (str == null || wUserSigInfo == null) {
            return -1017;
        }
        int i3 = i | Opcodes.AND_LONG_2ADDR;
        if (i2 == 0) {
            new HelperThread(this, this.mHelperHandler, str, j, i3, j2, jArr, z, str2, wUserSigInfo, bArr, z2, "GetStWithPasswd").RunReq(0);
            return -1001;
        }
        u uVar;
        long parseLong;
        int i4;
        Ticket GetUserSigInfoTicket;
        long longValue;
        Object obj;
        byte[] bArr2;
        Object obj2 = 1;
        u a;
        if (!z2 || j.x) {
            a = this.mG.a(0);
            wUserSigInfo._seqence = a.h;
            this.mAysncSeq = a.h;
            uVar = a;
        } else {
            if (wUserSigInfo._seqence == 0) {
                wUserSigInfo._seqence = this.mAysncSeq;
            }
            a = this.mG.a(wUserSigInfo._seqence);
            wUserSigInfo._seqence = a.h;
            uVar = a;
        }
        async_context b = u.b(uVar.h);
        util.LOGI("start GetStWithPasswd:user:" + str + " dwAppid:" + j + " dwMainSigMap:0x" + Integer.toHexString(i3) + " dwSubAppid:" + j2 + " Seq:" + uVar.h + " ...", str);
        b._isSmslogin = z2;
        if (z2 && str2.length() == 0) {
            str2 = b._mpasswd;
        }
        j.x = false;
        j.z = "";
        if (str2 != null && str2.length() > 16) {
            str2 = str2.substring(0, 16);
        }
        int i5 = util.get_saved_network_type(this.mContext);
        u.D = util.get_network_type(this.mContext);
        if (i5 != u.D) {
            util.set_net_retry_type(this.mContext, 0);
            util.save_network_type(this.mContext, u.D);
        }
        u.F = util.get_apn_string(this.mContext).getBytes();
        uVar.g = str;
        uVar.f = 0;
        b._sappid = j;
        b._appid = j;
        b._sub_appid_list = null;
        b._sub_appid = j2;
        b._main_sigmap = i3;
        b._login_bitmap = wUserSigInfo._login_bitmap;
        b._last_err_msg = new ErrMsg();
        if (jArr != null) {
            b._sub_appid_list = (long[]) jArr.clone();
        }
        if (wUserSigInfo._reserveData == null || wUserSigInfo._reserveData.length <= 3) {
            uVar.i = 0;
        } else {
            uVar.i = util.buf_to_int32(wUserSigInfo._reserveData, 0);
            util.LOGI("MSF SSO SEQ:" + uVar.i, str);
        }
        u.al.add_t2(new report_t2("login", new String(u.C), System.currentTimeMillis(), j, j2, jArr));
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
        } else {
            parseLong = uVar.b(str);
            if (parseLong == 0) {
                obj2 = null;
            }
        }
        long j3;
        if (str2 == null || str2.length() <= 0) {
            if (wUserSigInfo._fastLoginBuf != null && wUserSigInfo._fastLoginBuf.length > 0) {
                util.LOGI("GetFastLoginInfo ...", str);
                if (GetFastLoginInfo(wUserSigInfo._fastLoginBuf, b) < 0) {
                    util.LOGI("GetFastLoginInfo failed", str);
                    i4 = -1017;
                    GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, 128);
                    if (GetUserSigInfoTicket == null) {
                        GetUserSigInfoTicket = new Ticket();
                    }
                    u.al.commit_t2(uVar.f, uVar.g, util.format_ret_code(i4), i4);
                    if (i4 != 0) {
                        if (!(GetUserSigInfoTicket._sig == null || GetUserSigInfoTicket._sig.length == 0)) {
                            RequestReport(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid);
                        }
                    } else if (!(i4 == 2 || i4 == 160)) {
                        RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid, 0);
                    }
                    if (!(uVar.d == null || uVar.d.a() == 0)) {
                        this.mG.d = uVar.d;
                        RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid, 1);
                    }
                    u.b();
                    uVar.h();
                    util.LOGI("end GetStWithPasswd:user:" + str + " dwAppid:" + j + " dwMainSigMap:0x" + Integer.toHexString(i3) + " dwSubAppid:" + j2 + " Seq:" + uVar.h + " ret=" + i4, "" + uVar.f);
                    return i4;
                }
                String str3 = "([0-9]{5,10})@qq\\.com";
                if (str.matches(str3)) {
                    longValue = Long.valueOf(str.replaceAll(str3, "$1")).longValue();
                    obj = 1;
                    uVar.a(str, Long.valueOf(longValue));
                } else {
                    j3 = parseLong;
                    obj = obj2;
                    longValue = j3;
                }
                obj2 = obj;
                parseLong = longValue;
            } else if (c.q == null || c.q.length <= 0) {
                b._tmp_pwd = GetA1ByAccount(str, j);
                b._tmp_no_pic_sig = GetNoPicSigByAccount(str, j);
            } else {
                b._tmp_pwd = c.q;
                b._tmp_no_pic_sig = c.r;
                c.q = null;
                c.r = null;
            }
            if (b._tmp_pwd == null || b._tmp_pwd.length < 16) {
                i4 = util.E_A1_FORMAT;
                GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, 128);
                if (GetUserSigInfoTicket == null) {
                    GetUserSigInfoTicket = new Ticket();
                }
                u.al.commit_t2(uVar.f, uVar.g, util.format_ret_code(i4), i4);
                if (i4 != 0) {
                    RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid, 0);
                } else {
                    RequestReport(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid);
                }
                this.mG.d = uVar.d;
                RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid, 1);
                u.b();
                uVar.h();
                util.LOGI("end GetStWithPasswd:user:" + str + " dwAppid:" + j + " dwMainSigMap:0x" + Integer.toHexString(i3) + " dwSubAppid:" + j2 + " Seq:" + uVar.h + " ret=" + i4, "" + uVar.f);
                return i4;
            }
            b._tmp_pwd_type = 1;
            j3 = parseLong;
            obj = obj2;
            longValue = j3;
        } else {
            if (z) {
                try {
                    b._tmp_pwd = (byte[]) str2.getBytes("ISO-8859-1").clone();
                } catch (Exception e) {
                    i4 = util.E_ENCODING;
                }
            } else {
                b._tmp_pwd = MD5.toMD5Byte(str2);
            }
            b._tmp_pwd_type = 0;
            j3 = parseLong;
            obj = obj2;
            longValue = j3;
        }
        if (obj == null) {
            if (str.length() > util.MAX_NAME_LEN) {
                i4 = -1008;
            } else {
                i4 = new t(uVar).a(j, j2, 1, i3, str.getBytes(), u.y, 0, 0, 1, this.mMiscBitmap, this.mSubSigMap, jArr, wUserSigInfo);
                if (i4 == 0) {
                    longValue = uVar.b(str);
                    if (b._msalt == 0 && longValue == 0) {
                        i4 = -1003;
                    }
                }
            }
            GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, 128);
            if (GetUserSigInfoTicket == null) {
                GetUserSigInfoTicket = new Ticket();
            }
            u.al.commit_t2(uVar.f, uVar.g, util.format_ret_code(i4), i4);
            if (i4 != 0) {
                RequestReport(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid);
            } else {
                RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid, 0);
            }
            this.mG.d = uVar.d;
            RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid, 1);
            u.b();
            uVar.h();
            util.LOGI("end GetStWithPasswd:user:" + str + " dwAppid:" + j + " dwMainSigMap:0x" + Integer.toHexString(i3) + " dwSubAppid:" + j2 + " Seq:" + uVar.h + " ret=" + i4, "" + uVar.f);
            return i4;
        }
        parseLong = longValue;
        if (j.y != 0) {
            b._msalt = j.y;
            j.y = 0;
        }
        uVar.f = parseLong;
        wUserSigInfo.uin = "" + parseLong;
        if (wUserSigInfo._in_ksid == null || wUserSigInfo._in_ksid.length <= 0) {
            bArr2 = u.aa;
        } else {
            bArr2 = (byte[]) wUserSigInfo._in_ksid.clone();
        }
        if (b._tmp_pwd_type != 0) {
            util.LOGI("user:" + str + " login with saved A1.", "" + uVar.f);
            l lVar = new l(uVar, this.mContext);
            lVar.g();
            i4 = lVar.a(j, j2, uVar.f, 0, u.ad, b._tmp_pwd, b._tmp_no_pic_sig, this.mMiscBitmap, this.mSubSigMap, jArr, i3, j2, u.y, 0, 0, 1, bArr2, wUserSigInfo);
        } else {
            util.LOGI("user:" + str + " login with input password.", "" + uVar.f);
            byte[] bArr3 = new byte[4];
            util.int64_to_buf32(bArr3, 0, (System.currentTimeMillis() / 1000) + u.ac);
            int i6 = z2 ? 3 : 1;
            l lVar2 = new l(uVar, this.mContext);
            lVar2.g();
            i4 = lVar2.a(j, j2, uVar.f, 0, u.ad, bArr3, b._tmp_pwd, i6, this.mMiscBitmap, this.mSubSigMap, jArr, i3, j2, u.y, 0, 0, 1, bArr2, wUserSigInfo);
        }
        if (i4 == 204) {
            i4 = new q(uVar).a(this.mMiscBitmap, this.mSubSigMap, jArr, wUserSigInfo);
        }
        if (i4 == 0 || i4 == 160) {
            if (parseLong == 0) {
                parseLong = uVar.b(str);
                uVar.f = parseLong;
                wUserSigInfo.uin = "" + parseLong;
            }
            if (i4 != 160) {
                WloginSigInfo a2 = uVar.a(parseLong, j);
                if (a2 == null) {
                    i4 = -1004;
                } else {
                    wUserSigInfo.get_clone(a2);
                    if (!(jArr == null || bArr == null || jArr.length * 2 != bArr.length)) {
                        int i7 = 0;
                        while (jArr != null && i7 < jArr.length) {
                            WloginSigInfo a3 = uVar.a(parseLong, jArr[i7]);
                            if (a3 != null) {
                                bArr[i7 * 2] = (byte[]) a3._userSt_Key.clone();
                                bArr[(i7 * 2) + 1] = (byte[]) a3._userStSig.clone();
                            }
                            i7++;
                        }
                    }
                }
            }
        }
        GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, 128);
        if (GetUserSigInfoTicket == null) {
            GetUserSigInfoTicket = new Ticket();
        }
        u.al.commit_t2(uVar.f, uVar.g, util.format_ret_code(i4), i4);
        if (i4 != 0) {
            RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid, 0);
        } else {
            RequestReport(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid);
        }
        this.mG.d = uVar.d;
        RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, uVar.f, b._appid, 1);
        u.b();
        uVar.h();
        util.LOGI("end GetStWithPasswd:user:" + str + " dwAppid:" + j + " dwMainSigMap:0x" + Integer.toHexString(i3) + " dwSubAppid:" + j2 + " Seq:" + uVar.h + " ret=" + i4, "" + uVar.f);
        return i4;
    }

    public byte[] GetPictureData(String str) {
        return GetPictureData(str, 0);
    }

    public byte[] GetPictureData(String str, long j) {
        if (j <= 0) {
            j = this.mAysncSeq;
        }
        return u.b(j)._t105.a();
    }

    @Deprecated
    public byte[] GetPicturePrompt(String str) {
        return GetPicturePrompt(str, 0);
    }

    public String GetPicturePromptValue(String str) {
        return GetPicturePromptValue(str, 0);
    }

    public String GetPicturePromptValue(String str, long j) {
        byte[] GetPicturePrompt = GetPicturePrompt(str, j);
        String str2 = "";
        if (GetPicturePrompt == null || GetPicturePrompt.length <= 3) {
            return str2;
        }
        int buf_to_int32 = util.buf_to_int32(GetPicturePrompt, 0);
        int i = 4;
        int i2 = 0;
        while (i2 < buf_to_int32 && GetPicturePrompt.length >= i + 1) {
            int buf_to_int8 = util.buf_to_int8(GetPicturePrompt, i);
            i++;
            if (GetPicturePrompt.length < i + buf_to_int8) {
                return str2;
            }
            String str3 = new String(GetPicturePrompt, i, buf_to_int8);
            i += buf_to_int8;
            if (GetPicturePrompt.length < i + 2) {
                return str2;
            }
            buf_to_int8 = util.buf_to_int32(GetPicturePrompt, i);
            int i3 = i + 4;
            if (GetPicturePrompt.length < i3 + buf_to_int8) {
                return str2;
            }
            String str4 = new String(GetPicturePrompt, i3, buf_to_int8);
            buf_to_int8 += i3;
            if (str3.equals("pic_reason")) {
                return str4;
            }
            i2++;
            i = buf_to_int8;
        }
        return str2;
    }

    @Deprecated
    public byte[] GetPicturePrompt(String str, long j) {
        if (j <= 0) {
            j = this.mAysncSeq;
        }
        return u.b(j)._t165.c();
    }

    public DevlockInfo GetDevLockInfo(String str) {
        return GetDevLockInfo(str, 0);
    }

    public DevlockInfo GetDevLockInfo(String str, long j) {
        if (j <= 0) {
            j = this.mAysncSeq;
        }
        return u.b(j)._devlock_info;
    }

    public int RefreshPictureData(String str, WUserSigInfo wUserSigInfo) {
        if (wUserSigInfo == null) {
            wUserSigInfo = new WUserSigInfo();
        }
        return RefreshPictureData(str, wUserSigInfo, 0);
    }

    private int RefreshPictureData(String str, WUserSigInfo wUserSigInfo, int i) {
        int i2 = 0;
        if (str == null || wUserSigInfo == null) {
            return -1017;
        }
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, str, wUserSigInfo, "RefreshPictureData").RunReq(1);
            return -1001;
        }
        long parseLong;
        int i3;
        if (wUserSigInfo._seqence == 0) {
            wUserSigInfo._seqence = this.mAysncSeq;
        }
        u a = this.mG.a(wUserSigInfo._seqence);
        wUserSigInfo._seqence = a.h;
        async_context b = u.b(a.h);
        util.LOGI("user:" + str + " Seq:" + a.h + " RefreshPictureData ...", "" + str);
        a.g = str;
        b._last_err_msg = new ErrMsg();
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
            i3 = 1;
        } else {
            parseLong = a.b(str);
            if (parseLong != 0) {
                i3 = 1;
            } else {
                i3 = 0;
            }
        }
        if (i3 == 1) {
            a.f = parseLong;
        }
        i3 = new r(a).a(this.mMiscBitmap, this.mSubSigMap, b._sub_appid_list, wUserSigInfo);
        if (i3 != 2) {
            i2 = i3;
        }
        util.LOGI("user:" + str + " Seq:" + a.h + " RefreshPictureData ret=" + i2, "" + str);
        return i2;
    }

    public int CheckWebsigAndGetSt(String str, String str2, WUserSigInfo wUserSigInfo) {
        o.I = true;
        return CheckPictureAndGetSt(str, str2.getBytes(), wUserSigInfo, (byte[][]) null, 0);
    }

    public int CheckWebsigAndGetSt(String str, String str2, WUserSigInfo wUserSigInfo, byte[][] bArr) {
        o.I = true;
        return CheckPictureAndGetSt(str, str2.getBytes(), wUserSigInfo, bArr, 0);
    }

    public int CheckPictureAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo) {
        o.I = false;
        return CheckPictureAndGetSt(str, bArr, wUserSigInfo, (byte[][]) null, 0);
    }

    public int CheckPictureAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo, byte[][] bArr2) {
        o.I = false;
        return CheckPictureAndGetSt(str, bArr, wUserSigInfo, bArr2, 0);
    }

    private int CheckPictureAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo, byte[][] bArr2, int i) {
        if (str == null || bArr == null || wUserSigInfo == null) {
            return -1017;
        }
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, str, bArr, wUserSigInfo, bArr2, "CheckPictureAndGetSt").RunReq(2);
            return -1001;
        }
        long parseLong;
        int i2 = 0;
        if (wUserSigInfo._seqence == 0) {
            wUserSigInfo._seqence = this.mAysncSeq;
        }
        u a = this.mG.a(wUserSigInfo._seqence);
        wUserSigInfo._seqence = a.h;
        async_context b = u.b(wUserSigInfo._seqence);
        util.LOGI("user:" + str + " CheckPictureAndGetSt Seq:" + a.h + " ...", str);
        a.g = str;
        b._last_err_msg = new ErrMsg();
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
            i2 = 1;
        } else {
            parseLong = a.b(str);
            if (parseLong != 0) {
                i2 = 1;
            }
        }
        if (i2 == 1) {
            a.f = parseLong;
            wUserSigInfo.uin = parseLong + "";
        }
        int a2 = new o(a).a(bArr, this.mMiscBitmap, this.mSubSigMap, b._sub_appid_list, wUserSigInfo);
        if (a2 == 204) {
            a2 = new q(a).a(this.mMiscBitmap, this.mSubSigMap, b._sub_appid_list, wUserSigInfo);
        }
        if (a2 == 0 || a2 == 160) {
            long parseLong2;
            Object obj;
            if (util.check_uin_account(str).booleanValue()) {
                parseLong2 = Long.parseLong(str);
                obj = 1;
            } else {
                parseLong2 = a.b(str);
                if (parseLong2 != 0) {
                    obj = 1;
                } else {
                    int i3 = i2;
                }
            }
            if (b._msalt == 0 && r2 == null) {
                i2 = -1003;
            } else {
                a.f = parseLong2;
                wUserSigInfo.uin = parseLong2 + "";
                if (a2 == 160) {
                    i2 = a2;
                } else {
                    if (wUserSigInfo._reserveData == null || wUserSigInfo._reserveData.length <= 3) {
                        a.i = 0;
                    } else {
                        a.i = util.buf_to_int32(wUserSigInfo._reserveData, 0);
                        util.LOGI("MSF SSO SEQ:" + a.i, str);
                    }
                    WloginSigInfo a3 = a.a(parseLong2, b._appid);
                    WloginSigInfo a4;
                    if (a3 != null) {
                        wUserSigInfo.get_clone(a3);
                        if (!(b._sub_appid_list == null || bArr2 == null || b._sub_appid_list.length * 2 != bArr2.length)) {
                            a2 = 0;
                            while (b._sub_appid_list != null && a2 < b._sub_appid_list.length) {
                                a4 = a.a(parseLong2, b._sub_appid_list[a2]);
                                if (a4 != null) {
                                    bArr2[a2 * 2] = (byte[]) a4._userSt_Key.clone();
                                    bArr2[(a2 * 2) + 1] = (byte[]) a4._userStSig.clone();
                                }
                                a2++;
                            }
                        }
                        i2 = 0;
                    } else {
                        byte[] bArr3;
                        if (wUserSigInfo._in_ksid == null || wUserSigInfo._in_ksid.length <= 0) {
                            bArr3 = u.aa;
                        } else {
                            bArr3 = (byte[]) wUserSigInfo._in_ksid.clone();
                        }
                        if (b._tmp_pwd_type != 0) {
                            l lVar = new l(a, this.mContext);
                            lVar.g();
                            i2 = lVar.a(b._appid, b._sub_appid, a.f, 0, u.ad, b._tmp_pwd, null, this.mMiscBitmap, this.mSubSigMap, b._sub_appid_list, b._main_sigmap, b._sub_appid, u.y, 0, 0, 1, bArr3, wUserSigInfo);
                        } else {
                            byte[] bArr4 = new byte[4];
                            util.int64_to_buf32(bArr4, 0, (System.currentTimeMillis() / 1000) + u.ac);
                            int i4 = b._isSmslogin ? 3 : 1;
                            l lVar2 = new l(a, this.mContext);
                            lVar2.g();
                            i2 = lVar2.a(b._appid, b._sub_appid, a.f, 0, u.ad, bArr4, b._tmp_pwd, i4, this.mMiscBitmap, this.mSubSigMap, b._sub_appid_list, b._main_sigmap, b._sub_appid, u.y, 0, 0, 1, bArr3, wUserSigInfo);
                        }
                        if (i2 == 204) {
                            i2 = new q(a).a(this.mMiscBitmap, this.mSubSigMap, b._sub_appid_list, wUserSigInfo);
                        }
                        if (i2 == 0 || i2 == 160) {
                            parseLong2 = a.b(str);
                            wUserSigInfo.uin = parseLong2 + "";
                            if (i2 != 160) {
                                a3 = a.a(parseLong2, b._appid);
                                if (a3 == null) {
                                    i2 = -1004;
                                } else {
                                    wUserSigInfo.get_clone(a3);
                                    if (!(b._sub_appid_list == null || bArr2 == null || b._sub_appid_list.length * 2 != bArr2.length)) {
                                        a2 = 0;
                                        while (b._sub_appid_list != null && a2 < b._sub_appid_list.length) {
                                            a4 = a.a(parseLong2, b._sub_appid_list[a2]);
                                            if (a4 != null) {
                                                bArr2[a2 * 2] = (byte[]) a4._userSt_Key.clone();
                                                bArr2[(a2 * 2) + 1] = (byte[]) a4._userStSig.clone();
                                            }
                                            a2++;
                                        }
                                    }
                                    i2 = 0;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            i2 = a2;
        }
        Ticket GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, 128);
        if (GetUserSigInfoTicket == null) {
            GetUserSigInfoTicket = new Ticket();
        }
        u.al.commit_t2(a.f, a.g, util.format_ret_code(i2), i2);
        if (i2 != 0) {
            RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid, 0);
        } else if (!(GetUserSigInfoTicket._sig == null || GetUserSigInfoTicket._sig.length == 0)) {
            RequestReport(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid);
        }
        if (!(a.d == null || a.d.a() == 0)) {
            this.mG.d = a.d;
            RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid, 1);
        }
        u.b();
        a.h();
        util.LOGI("user:" + str + " CheckPictureAndGetSt Seq:" + a.h + " ret=" + i2, "" + a.f);
        return i2;
    }

    public void SetDevlockMobileType(int i) {
        s.I = i;
    }

    public int RefreshSMSData(String str, long j, WUserSigInfo wUserSigInfo) {
        WUserSigInfo wUserSigInfo2;
        if (wUserSigInfo == null) {
            wUserSigInfo2 = new WUserSigInfo();
        } else {
            wUserSigInfo2 = wUserSigInfo;
        }
        return RefreshSMSData(str, j, wUserSigInfo2, 0);
    }

    private int RefreshSMSData(String str, long j, WUserSigInfo wUserSigInfo, int i) {
        int i2 = 0;
        if (str == null || wUserSigInfo == null) {
            return -1017;
        }
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, str, j, wUserSigInfo, "RefreshSMSData").RunReq(3);
            return -1001;
        }
        long parseLong;
        int i3;
        if (wUserSigInfo._seqence == 0) {
            wUserSigInfo._seqence = this.mAysncSeq;
        }
        u a = this.mG.a(wUserSigInfo._seqence);
        wUserSigInfo._seqence = a.h;
        async_context b = u.b(a.h);
        util.LOGI("user:" + str + " smsAppid:" + j + " Seq:" + a.h + " RefreshSMSData ...", "" + str);
        a.g = str;
        b._last_err_msg = new ErrMsg();
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
            i3 = 1;
        } else {
            parseLong = a.b(str);
            if (parseLong != 0) {
                i3 = 1;
            } else {
                i3 = 0;
            }
        }
        if (i3 == 1) {
            a.f = parseLong;
        }
        i3 = new s(a).a(j, this.mMiscBitmap, this.mSubSigMap, b._sub_appid_list, wUserSigInfo);
        if (i3 != 160) {
            i2 = i3;
        }
        util.LOGI("user:" + str + " smsAppid:" + j + " Seq:" + a.h + " RefreshSMSData ret=" + i2, "" + str);
        return i2;
    }

    public int CheckSMSAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo) {
        return CheckSMSAndGetSt(str, bArr, wUserSigInfo, (byte[][]) null, 0);
    }

    public int CheckSMSAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo, byte[][] bArr2) {
        return CheckSMSAndGetSt(str, bArr, wUserSigInfo, bArr2, 0);
    }

    private int CheckSMSAndGetSt(String str, byte[] bArr, WUserSigInfo wUserSigInfo, byte[][] bArr2, int i) {
        if (str == null || bArr == null || wUserSigInfo == null) {
            return -1017;
        }
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, str, bArr, wUserSigInfo, bArr2, "CheckSMSAndGetSt").RunReq(4);
            return -1001;
        }
        int i2;
        if (wUserSigInfo._seqence == 0) {
            wUserSigInfo._seqence = this.mAysncSeq;
        }
        u a = this.mG.a(wUserSigInfo._seqence);
        wUserSigInfo._seqence = a.h;
        async_context b = u.b(wUserSigInfo._seqence);
        util.LOGI("user:" + str + " CheckSMSAndGetSt Seq:" + a.h + " ...", str);
        a.g = str;
        a.f = 0;
        b._last_err_msg = new ErrMsg();
        if (wUserSigInfo._reserveData == null || wUserSigInfo._reserveData.length <= 3) {
            a.i = 0;
        } else {
            a.i = util.buf_to_int32(wUserSigInfo._reserveData, 0);
            util.LOGI("MSF SSO SEQ:" + a.i, str);
        }
        long parseLong;
        int a2;
        WloginSigInfo a3;
        int i3;
        WloginSigInfo a4;
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
            a.f = parseLong;
            wUserSigInfo.uin = parseLong + "";
            a2 = new p(a).a(bArr, this.mMiscBitmap, this.mSubSigMap, b._sub_appid_list, wUserSigInfo);
            if (a2 == 0) {
                a3 = a.a(parseLong, b._appid);
                if (a3 != null) {
                    wUserSigInfo.get_clone(a3);
                    i3 = 0;
                    while (b._sub_appid_list != null) {
                        a4 = a.a(parseLong, b._sub_appid_list[i3]);
                        if (a4 != null) {
                            bArr2[i3 * 2] = (byte[]) a4._userSt_Key.clone();
                            bArr2[(i3 * 2) + 1] = (byte[]) a4._userStSig.clone();
                        }
                        i3++;
                    }
                    i2 = 0;
                } else {
                    i2 = -1004;
                }
            } else {
                i2 = a2;
            }
        } else {
            long b2 = a.b(str);
            if (b2 == 0) {
                util.LOGI("user:" + str + " have not found uin record.", str);
                i2 = -1003;
            } else {
                parseLong = b2;
                a.f = parseLong;
                wUserSigInfo.uin = parseLong + "";
                a2 = new p(a).a(bArr, this.mMiscBitmap, this.mSubSigMap, b._sub_appid_list, wUserSigInfo);
                if (a2 == 0) {
                    i2 = a2;
                } else {
                    a3 = a.a(parseLong, b._appid);
                    if (a3 != null) {
                        i2 = -1004;
                    } else {
                        wUserSigInfo.get_clone(a3);
                        if (!(b._sub_appid_list == null || bArr2 == null || b._sub_appid_list.length * 2 != bArr2.length)) {
                            i3 = 0;
                            while (b._sub_appid_list != null && i3 < b._sub_appid_list.length) {
                                a4 = a.a(parseLong, b._sub_appid_list[i3]);
                                if (a4 != null) {
                                    bArr2[i3 * 2] = (byte[]) a4._userSt_Key.clone();
                                    bArr2[(i3 * 2) + 1] = (byte[]) a4._userStSig.clone();
                                }
                                i3++;
                            }
                        }
                        i2 = 0;
                    }
                }
            }
        }
        Ticket GetUserSigInfoTicket = GetUserSigInfoTicket(wUserSigInfo, 128);
        if (GetUserSigInfoTicket == null) {
            GetUserSigInfoTicket = new Ticket();
        }
        u.al.commit_t2(a.f, a.g, util.format_ret_code(i2), i2);
        if (i2 != 0) {
            RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid, 0);
        } else if (!(GetUserSigInfoTicket._sig == null || GetUserSigInfoTicket._sig.length == 0)) {
            RequestReport(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid);
        }
        if (!(a.d == null || a.d.a() == 0)) {
            this.mG.d = a.d;
            RequestReportError(0, GetUserSigInfoTicket._sig, GetUserSigInfoTicket._sig_key, a.f, b._appid, 1);
        }
        u.b();
        a.h();
        util.LOGI("user:" + str + " CheckSMSAndGetSt Seq:" + a.h + " ret=" + i2, "" + a.f);
        return i2;
    }

    public void setHasPassword(long j, boolean z) {
        String e = this.mG.e(j);
        util.LOGI("setHasPasswd ..." + String.valueOf(e), "");
        if (e != null) {
            this.mG.a(e, Long.valueOf(j), z);
            util.LOGI("setHasPasswd userAccount: " + e + ", uin: " + j + " hasPassword:" + z, "");
        }
    }

    public boolean getHasPassword(long j) {
        String e = this.mG.e(j);
        util.LOGI("getHasPasswd ..." + String.valueOf(e), "" + j);
        if (e == null) {
            return true;
        }
        UinInfo c = this.mG.c(e);
        if (c == null) {
            return true;
        }
        boolean hasPassword = c.getHasPassword();
        util.LOGI("getHasPasswd userAccount: " + e + ", uin: " + j + " hasPasswd: " + hasPassword, "");
        return hasPassword;
    }

    public int RegGetSMSVerifyLoginAccount(byte[] bArr, byte[] bArr2, byte[] bArr3, WUserSigInfo wUserSigInfo) {
        util.LOGI("RegGetSMSVerifyLoginAccount ...", "");
        d dVar = new d();
        TransReqContext transReqContext = new TransReqContext();
        j jVar = this.mRegStatus;
        if (bArr != null) {
            jVar.j = (byte[]) bArr.clone();
        } else {
            jVar.j = new byte[0];
        }
        j.x = true;
        j.z = util.get_mpasswd();
        transReqContext.set_register_req();
        transReqContext.set_subcmd(dVar.a());
        transReqContext._body = dVar.a(jVar.e, bArr, j.z.getBytes(), bArr3, 1, jVar.b.getBytes(), bArr2, true, GetGuid(), jVar.h, u.E, u.z);
        return RequestTransport(0, 1, null, 0, (long) jVar.i, transReqContext, wUserSigInfo);
    }

    public int quickRegisterCheckAccount(long j, long j2, int i, int i2, byte[] bArr, WUserSigInfo wUserSigInfo) {
        if (bArr == null || wUserSigInfo == null) {
            return -1017;
        }
        util.LOGI("quickRegisterCheckAccount " + j + " appid " + j2, "" + j);
        Ticket GetLocalTicket = GetLocalTicket("" + j, j2, 64);
        if (GetLocalTicket == null) {
            util.LOGI("quickRegisterCheckAccount no key", "" + j);
            return -1004;
        } else if (GetLocalTicket._sig == null || GetLocalTicket._sig.length == 0) {
            util.LOGI("quickRegisterCheckAccount no key", "" + j);
            return -1004;
        } else if (GetLocalTicket._sig_key == null || GetLocalTicket._sig_key.length == 0) {
            util.LOGI("quickRegisterCheckAccount no key", "" + j);
            return -1004;
        } else {
            a aVar = new a();
            TransReqContext transReqContext = new TransReqContext();
            this.mRegStatus.l = GetLocalTicket._sig_key;
            j jVar = this.mRegStatus;
            jVar.g = j2;
            jVar.h = (long) i2;
            transReqContext.set_register_req();
            transReqContext.set_subcmd(aVar.a());
            WloginSigInfo FindUserSig = FindUserSig(j, j2);
            transReqContext.setSTEncryptMethod();
            transReqContext.setWtST(FindUserSig);
            transReqContext._body = aVar.a(j, (int) j2, (byte) 8, GetLocalTicket._sig, GetLocalTicket._sig_key, (byte) i, GetGuid(), i2, u.E, bArr, util.generateGuid(this.mContext), util.get_IMSI(this.mContext));
            if (transReqContext._body == null) {
                util.LOGI("req_con._body is null", "" + j);
                return -1017;
            }
            return RequestTransport(0, 1, null, 0, (long) jVar.i, transReqContext, wUserSigInfo);
        }
    }

    public int quickRegisterGetAccount(long j, long j2, int i, int i2, byte[] bArr, byte[] bArr2, String str, WUserSigInfo wUserSigInfo) {
        if (bArr2 == null || bArr == null || str == null || wUserSigInfo == null) {
            return -1017;
        }
        if (6 > str.length()) {
            return -1017;
        }
        util.LOGI("quickRegisterGetAccount " + j + " appid " + j2, "" + j);
        Ticket GetLocalTicket = GetLocalTicket("" + j, j2, 64);
        if (GetLocalTicket == null) {
            util.LOGI("quickRegisterCheckAccount " + j + " appid " + j2 + " no key", "" + j);
            return -1004;
        } else if (GetLocalTicket._sig == null || GetLocalTicket._sig.length == 0) {
            util.LOGI("quickRegisterCheckAccount no key", "" + j);
            return -1004;
        } else if (GetLocalTicket._sig_key == null || GetLocalTicket._sig_key.length == 0) {
            util.LOGI("quickRegisterCheckAccount no key", "" + j);
            return -1004;
        } else {
            b bVar = new b();
            TransReqContext transReqContext = new TransReqContext();
            this.mRegStatus.l = GetLocalTicket._sig_key;
            j jVar = this.mRegStatus;
            jVar.g = j2;
            jVar.h = (long) i2;
            transReqContext.set_register_req();
            transReqContext.set_subcmd(bVar.a());
            WloginSigInfo FindUserSig = FindUserSig(j, j2);
            transReqContext.setSTEncryptMethod();
            transReqContext.setWtST(FindUserSig);
            transReqContext._body = bVar.a(j, (int) j2, (byte) 8, GetLocalTicket._sig, GetLocalTicket._sig_key, (byte) i, bArr2, GetGuid(), i2, u.E, bArr, util.generateGuid(this.mContext), util.get_IMSI(this.mContext), str.getBytes(), this.mRegStatus.e);
            if (transReqContext._body == null) {
                util.LOGI("req_con._body is null", "" + j);
                return -1017;
            }
            return RequestTransport(0, 1, null, 0, (long) jVar.i, transReqContext, wUserSigInfo);
        }
    }

    public int GetStViaSMSVerifyLogin(String str, long j, long j2, int i, WUserSigInfo wUserSigInfo) {
        util.LOGI("user:" + str + " GetStViaSMSVerifyLogin ...", str);
        return GetStWithPasswd(str, j, i, j2, null, false, j.x ? j.z : "", wUserSigInfo, (byte[][]) null, true, 0);
    }

    public int GetStViaSMSVerifyLogin(String str, long j, long j2, long[] jArr, int i, WUserSigInfo wUserSigInfo) {
        byte[][] bArr;
        byte[][] bArr2 = (byte[][]) null;
        if (jArr == null || jArr.length <= 0) {
            bArr = bArr2;
        } else {
            bArr = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{jArr.length, 0});
        }
        util.LOGI("user:" + str + " GetStViaSMSVerifyLogin ...", str);
        return GetStWithPasswd(str, j, i, j2, jArr, false, j.x ? j.z : "", wUserSigInfo, bArr, true, 0);
    }

    public int CheckSMSVerifyLoginAccount(long j, long j2, String str, WUserSigInfo wUserSigInfo) {
        return CheckSMSVerifyLoginAccount(j, j2, str, wUserSigInfo, 0);
    }

    private int CheckSMSVerifyLoginAccount(long j, long j2, String str, WUserSigInfo wUserSigInfo, int i) {
        j.x = false;
        j.y = 0;
        if (str == null || wUserSigInfo == null) {
            return -1017;
        }
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, j, j2, str, wUserSigInfo, "CheckSMSVerifyLoginAccount").RunReq(12);
            return -1001;
        }
        u a = this.mG.a(0);
        wUserSigInfo._seqence = a.h;
        this.mAysncSeq = a.h;
        async_context b = u.b(a.h);
        a.g = str;
        util.LOGI("user:" + str + " Seq:" + a.h + " CheckSMSVerifyLoginAccount ...", str);
        b._login_bitmap = wUserSigInfo._login_bitmap;
        b._last_err_msg = new ErrMsg();
        int a2 = new w(a).a(j, j2, this.mMainSigMap, u.aa, str, this.mMiscBitmap, this.mSubSigMap, null, wUserSigInfo);
        if (a2 == 208) {
            a2 = 0;
        }
        util.LOGI("user:" + a.g + " Seq:" + a.h + " CheckSMSVerifyLoginAccount ret=" + (a2 > 0 ? Integer.toHexString(a2) : Integer.valueOf(a2)), str);
        return a2;
    }

    public int RefreshSMSVerifyLoginCode(String str, WUserSigInfo wUserSigInfo) {
        return RefreshSMSVerifyLoginCode(str, wUserSigInfo, 0);
    }

    private int RefreshSMSVerifyLoginCode(String str, WUserSigInfo wUserSigInfo, int i) {
        if (str == null || str.length() == 0) {
            return -1017;
        }
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, str, wUserSigInfo, "RefreshSMSVerifyLoginCode").RunReq(14);
            return -1001;
        }
        if (wUserSigInfo._seqence == 0) {
            wUserSigInfo._seqence = this.mAysncSeq;
        }
        u a = this.mG.a(wUserSigInfo._seqence);
        wUserSigInfo._seqence = a.h;
        async_context b = u.b(a.h);
        util.LOGI("user:" + str + " Seq:" + a.h + " RefreshSMSVerifyLoginCode ...", str);
        a.g = str;
        b._last_err_msg = new ErrMsg();
        int a2 = new x(a).a(this.mMiscBitmap, this.mSubSigMap, null, wUserSigInfo);
        util.LOGI("user:" + a.g + " Seq:" + a.h + " RefreshSMSVerifyLoginCode ret=" + (a2 > 0 ? Integer.toHexString(a2) : Integer.valueOf(a2)), str);
        return a2;
    }

    public int VerifySMSVerifyLoginCode(String str, String str2, WUserSigInfo wUserSigInfo) {
        return VerifySMSVerifyLoginCode(str, str2, wUserSigInfo, 0);
    }

    private int VerifySMSVerifyLoginCode(String str, String str2, WUserSigInfo wUserSigInfo, int i) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return -1017;
        }
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, str, str2, wUserSigInfo, "VerifySMSVerifyLoginCode").RunReq(13);
            return -1001;
        }
        if (wUserSigInfo._seqence == 0) {
            wUserSigInfo._seqence = this.mAysncSeq;
        }
        u a = this.mG.a(wUserSigInfo._seqence);
        wUserSigInfo._seqence = a.h;
        async_context b = u.b(a.h);
        util.LOGI("user:" + a.g + " code:" + str2 + " Seq:" + a.h + " VerifySMSVerifyLoginCode ...", str);
        a.g = str;
        b._last_err_msg = new ErrMsg();
        b._mpasswd = util.get_mpasswd();
        int a2 = new y(a).a(str2, this.mMiscBitmap, this.mSubSigMap, null, wUserSigInfo);
        util.LOGI("user:" + str + " code:" + str2 + " Seq:" + a.h + " VerifySMSVerifyLoginAccount ret=" + (a2 > 0 ? Integer.toHexString(a2) : Integer.valueOf(a2)), str);
        return a2;
    }

    public Boolean ClearUserLoginData(String str, long j) {
        util.LOGI("user:" + str + " appid:" + j + " ClearUserLoginData ...", str);
        if (str == null || str.length() <= 0) {
            return Boolean.valueOf(true);
        }
        synchronized (this) {
            long parseLong;
            boolean z;
            if (util.check_uin_account(str).booleanValue()) {
                parseLong = Long.parseLong(str);
                z = true;
            } else {
                parseLong = this.mG.b(str);
                if (parseLong == 0) {
                    int i = 0;
                } else {
                    this.mG.d(str);
                    z = true;
                }
            }
            if (i == 1) {
                this.mG.d(parseLong, j);
            }
        }
        WloginSigInfo._QRPUSHSig = new byte[0];
        WloginSigInfo._LHSig = new byte[0];
        return Boolean.valueOf(true);
    }

    public void ClearPSkey(String str, long j) {
        util.LOGI("user:" + str + " appid:" + j + " ClearPSkey ...", str);
        if (str != null && str.length() > 0) {
            Object obj = 1;
            synchronized (this) {
                long parseLong;
                if (util.check_uin_account(str).booleanValue()) {
                    parseLong = Long.parseLong(str);
                } else {
                    parseLong = this.mG.b(str);
                    if (parseLong == 0) {
                        obj = null;
                    }
                }
                if (obj != null) {
                    this.mG.c(parseLong, j);
                }
            }
        }
    }

    public Boolean GetBasicUserInfo(String str, WloginSimpleInfo wloginSimpleInfo) {
        boolean z = true;
        if (str == null) {
            return Boolean.valueOf(false);
        }
        long parseLong;
        boolean z2;
        boolean z3;
        if (util.check_uin_account(str).booleanValue()) {
            parseLong = Long.parseLong(str);
            z2 = true;
        } else {
            parseLong = this.mG.b(str);
            if (parseLong == 0) {
                z2 = false;
            } else {
                z2 = true;
            }
        }
        if (z2) {
            WloginSimpleInfo d = this.mG.d(parseLong);
            if (d == null) {
                z3 = false;
                if (!z3) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (wloginSimpleInfo != null) {
                wloginSimpleInfo.get_clone(new WloginSimpleInfo(d._uin, d._face, d._age, d._gender, d._nick, d._img_type, d._img_format, d._img_url));
            }
        }
        z3 = z2;
        if (!z3) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public byte[] GetGuid() {
        if (u.A == null || u.A.length <= 0) {
            return null;
        }
        Object obj = new byte[u.A.length];
        System.arraycopy(u.A, 0, obj, 0, u.A.length);
        return obj;
    }

    public static WFastLoginInfo GetFastLoginUrl(String str, long j) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    String str2;
                    util.LOGI("packageName:" + str + " uin:" + j, "");
                    String str3 = "http://imgcache.qq.com/wtlogin";
                    if (j == 1689053018) {
                        str2 = str3 + "/test";
                    } else {
                        str2 = str3 + "/app";
                    }
                    for (String str4 : str.split("\\.")) {
                        str2 = (str2 + "/") + str4;
                    }
                    WFastLoginInfo wFastLoginInfo = new WFastLoginInfo();
                    wFastLoginInfo.iconUrl = str2 + "/icon.png";
                    wFastLoginInfo.adUrl = str2 + "/ad_img.png";
                    wFastLoginInfo.profileUrl = str2 + "/profile.js";
                    return wFastLoginInfo;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private int RequestInit() {
        int ShareKeyInit;
        synchronized (this) {
            int i = util.get_saved_network_type(this.mContext);
            u.d();
            ShareKeyInit = ShareKeyInit();
            AsyncGenRSAKey();
            util.LOGI("init ok  ret:" + ShareKeyInit + " os ver:" + new String(u.J) + " saved_network_type:" + i + " network_type:" + u.D + " svn " + util.SVN_VER + " at " + u.l(), "");
        }
        return ShareKeyInit;
    }

    private int ShareKeyInit() {
        util.LOGI("start ShareKeyInit", "");
        EcdhCrypt ecdhCrypt = new EcdhCrypt(this.mContext);
        if (this.isForLocal) {
            return ecdhCrypt.initShareKeyByDefault();
        }
        int initShareKey = ecdhCrypt.initShareKey();
        util.LOGI("end ShareKeyInit", "");
        this.mG.n = ecdhCrypt.get_c_pub_key();
        this.mG.p = ecdhCrypt.get_g_share_key();
        return initShareKey;
    }

    private void AsyncGenRSAKey() {
        if (!this.isForLocal) {
            new Thread("AsyncGenRSAKey") {
                public void run() {
                    new RSACrypt(WtloginHelper.this.mContext).GenRSAKey();
                }
            }.start();
        }
    }

    private WloginSigInfo FindUserSig(long j, long j2) {
        return this.mG.a(j, j2);
    }

    private int RequestReportError(int i, byte[] bArr, byte[] bArr2, long j, long j2, int i2) {
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, bArr, bArr2, j, j2, i2, "RequestReportError").RunReq(8);
            return -1001;
        }
        u a = this.mG.a(0);
        a.d = this.mG.d;
        a.f = j;
        util.LOGI("user:" + j + " appid:" + j2 + " Seq:" + a.h + " RequestReportError...", "" + j);
        int a2 = new v(a).a(j, null, bArr, bArr2, j2, i2);
        util.LOGI("user:" + j + " appid:" + j2 + " Seq:" + a.h + " RequestReportError ret=" + a2, "" + j);
        return a2;
    }

    private int RequestReport(int i, byte[] bArr, byte[] bArr2, long j, long j2) {
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, bArr, bArr2, j, j2, "RequestReport").RunReq(7);
            return -1001;
        }
        u a = this.mG.a(0);
        a.f = j;
        util.LOGI("user:" + j + " appid:" + j2 + " Seq:" + a.h + " RequestReport...", "" + j);
        int a2 = new aa(a).a(j, null, bArr, bArr2, j2, new WUserSigInfo());
        a.i();
        util.LOGI("user:" + j + " appid:" + j2 + " Seq:" + a.h + " RequestReport ret=" + a2, "" + j);
        return a2;
    }

    public int RequestTransport(int i, int i2, String str, long j, long j2, TransReqContext transReqContext, WUserSigInfo wUserSigInfo) {
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, i2, str, j, j2, transReqContext, wUserSigInfo, "RequestTransport").RunReq(9);
            return -1001;
        }
        int a;
        u a2 = this.mG.a(0);
        util.LOGI("user:" + str + " encrypt:" + i2 + " appid:" + j + " role:" + j2 + " Seq:" + a2.h + " RequestTransport...", str);
        a2.g = str;
        if (i2 == 0) {
            a2.f = 0;
            a = new aa(a2).a(a2.f, transReqContext, null, null, j, j2, wUserSigInfo);
        } else if (str == null) {
            a2.m = 0;
            a = new aa(a2).a(0, transReqContext, null, null, j, j2, wUserSigInfo);
        } else {
            WloginSimpleInfo wloginSimpleInfo = new WloginSimpleInfo();
            if (str == null || !GetBasicUserInfo(str, wloginSimpleInfo).booleanValue()) {
                a = -1003;
            } else {
                WloginSigInfo a3 = a2.a(wloginSimpleInfo._uin, j);
                if (a3 == null) {
                    a = -1004;
                } else {
                    a2.f = wloginSimpleInfo._uin;
                    a = new aa(a2).a(wloginSimpleInfo._uin, transReqContext, a3._userStSig, a3._userSt_Key, j, j2, wUserSigInfo);
                }
            }
        }
        a2.i();
        util.LOGI("user:" + str + " encrypt:" + i2 + " appid:" + j + " role:" + j2 + " Seq:" + a2.h + " RequestTransport ret=" + a, str);
        return a;
    }

    public int RequestTransportMsf(int i, int i2, String str, long j, long j2, TransReqContext transReqContext) {
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, i2, str, j, j2, transReqContext, "RequestTransportMsf").RunReq(10);
            return -1001;
        }
        int i3;
        u a = this.mG.a(0);
        util.LOGI("user:" + str + " encrypt:" + i2 + " appid:" + j + " role:" + j2 + " Seq:" + a.h + " RequestTransportMsf...", str);
        a.g = str;
        WloginSimpleInfo wloginSimpleInfo;
        WloginSigInfo a2;
        if (i2 != 0) {
            wloginSimpleInfo = new WloginSimpleInfo();
            if (str == null || !GetBasicUserInfo(str, wloginSimpleInfo).booleanValue()) {
                i3 = -1003;
            } else {
                a2 = a.a(wloginSimpleInfo._uin, j);
                if (a2 == null) {
                    i3 = -1004;
                } else {
                    a.f = wloginSimpleInfo._uin;
                    i3 = new aa(a).a(wloginSimpleInfo._uin, transReqContext, a2._userStSig, a2._userSt_Key, a2._TGT, j, j2, new WUserSigInfo());
                }
            }
        } else if (util.check_uin_account(str).booleanValue() && Long.parseLong(str) == 0) {
            a.f = 0;
            i3 = new aa(a).a(0, transReqContext, null, null, new byte[0], j, j2, new WUserSigInfo());
        } else {
            wloginSimpleInfo = new WloginSimpleInfo();
            if (str == null || !GetBasicUserInfo(str, wloginSimpleInfo).booleanValue()) {
                i3 = -1003;
            } else {
                a2 = a.a(wloginSimpleInfo._uin, j);
                if (a2 == null) {
                    i3 = -1004;
                } else {
                    a.f = wloginSimpleInfo._uin;
                    i3 = new aa(a).a(wloginSimpleInfo._uin, transReqContext, null, null, a2._TGT, j, j2, new WUserSigInfo());
                }
            }
        }
        a.i();
        util.LOGI("user:" + str + " encrypt:" + i2 + " appid:" + j + " role:" + j2 + " Seq:" + a.h + " RequestTransportMsf ret=" + i3, str);
        return i3;
    }

    private void OnRequestRegister(String str, long j, long j2, TransReqContext transReqContext, WUserSigInfo wUserSigInfo, int i) {
        if (this.mListener != null) {
            String a = InternationMsg.a(MSG_TYPE.MSG_3);
            if (i == 0) {
                j jVar = this.mRegStatus;
                int a2;
                switch (transReqContext.get_subcmd()) {
                    case 3:
                        a2 = oicq.wlogin_sdk.a.c.a(transReqContext.get_body(), jVar);
                        if (a2 == 0) {
                            util.LOGI("reg cmd:" + transReqContext.get_subcmd() + " ret:" + jVar.d, "");
                            if (jVar.d == 0 || jVar.d == 4 || jVar.d == 31 || jVar.d == 118) {
                                this.mListener.OnRegQueryClientSentMsgStatus(wUserSigInfo, jVar.d, jVar.s, jVar.t, new String(jVar.f));
                                return;
                            } else if (jVar.d != 3) {
                                util.LOGW("OnRequestRegister 0x3 return code:", String.valueOf(jVar.d), str);
                                if (this.mListener != null) {
                                    this.mListener.OnRegError(wUserSigInfo, jVar.d, jVar.f);
                                    return;
                                }
                                return;
                            } else if (this.mListener != null) {
                                this.mListener.OnRegCheckValidUrl(wUserSigInfo, jVar.r);
                                return;
                            } else {
                                return;
                            }
                        } else if (this.mListener != null) {
                            this.mListener.OnRegError(wUserSigInfo, a2, a.getBytes());
                            return;
                        } else {
                            return;
                        }
                    case 4:
                        a2 = oicq.wlogin_sdk.a.c.a(transReqContext.get_body(), jVar);
                        if (a2 == 0) {
                            util.LOGI("reg cmd:" + transReqContext.get_subcmd() + " ret:" + jVar.d, "");
                            if (jVar.d == 0) {
                                if (this.mListener != null) {
                                    this.mListener.OnRegRequestServerResendMsg(wUserSigInfo, jVar.d, jVar.s, jVar.t);
                                    return;
                                }
                                return;
                            } else if (jVar.d == 3) {
                                if (this.mListener != null) {
                                    this.mListener.OnRegCheckValidUrl(wUserSigInfo, jVar.r);
                                    return;
                                }
                                return;
                            } else if (jVar.d != 5) {
                                util.LOGW("OnRequestRegister 0x4 return code:", String.valueOf(jVar.d), str);
                                if (this.mListener != null) {
                                    this.mListener.OnRegError(wUserSigInfo, jVar.d, jVar.f);
                                    return;
                                }
                                return;
                            } else if (this.mListener != null) {
                                this.mListener.OnRegRequestServerResendMsg(wUserSigInfo, jVar.d, jVar.s, jVar.t);
                                return;
                            } else {
                                return;
                            }
                        } else if (this.mListener != null) {
                            this.mListener.OnRegError(wUserSigInfo, a2, a.getBytes());
                            return;
                        } else {
                            return;
                        }
                    case 5:
                        a2 = oicq.wlogin_sdk.a.c.b(transReqContext.get_body(), jVar);
                        if (a2 == 0) {
                            wUserSigInfo.regTLVMap = jVar.B;
                            jVar.B = new HashMap();
                            util.LOGI("reg cmd:" + transReqContext.get_subcmd() + " ret:" + jVar.d, "");
                            if (this.mListener != null) {
                                this.mListener.OnRegSubmitMsgChk(wUserSigInfo, jVar.d, jVar.f);
                                return;
                            }
                            return;
                        } else if (this.mListener != null) {
                            this.mListener.OnRegError(wUserSigInfo, a2, a.getBytes());
                            return;
                        } else {
                            return;
                        }
                    case 6:
                        a2 = oicq.wlogin_sdk.a.c.c(transReqContext.get_body(), jVar);
                        if (a2 == 0) {
                            util.LOGI("reg cmd:" + transReqContext.get_subcmd() + " ret:" + jVar.d, "");
                            if (this.mListener != null) {
                                if (jVar.b != null && jVar.b.indexOf("-") > 0) {
                                    int indexOf = jVar.b.indexOf("-");
                                    String substring = jVar.b.substring(0, indexOf);
                                    if (substring.equals("86")) {
                                        jVar.b = jVar.b.substring(indexOf + 1);
                                    } else {
                                        jVar.b = "00" + substring + jVar.b.substring(indexOf + 1);
                                    }
                                }
                                if (!(jVar.b == null || jVar.b.length() == 0)) {
                                    this.mG.d(jVar.b);
                                    this.mG.a(jVar.b, Long.valueOf(jVar.u));
                                }
                                util.LOGI("reg userAccount: " + jVar.b, jVar.u + "");
                                if (j.z.length() > 0) {
                                    this.mListener.OnRegGetSMSVerifyLoginAccount(wUserSigInfo, jVar.d, jVar.u, jVar.v, jVar.w, jVar.f);
                                    return;
                                }
                                this.mListener.OnRegGetAccount(wUserSigInfo, jVar.d, jVar.u, jVar.v, jVar.w, jVar.f);
                                return;
                            }
                            return;
                        } else if (this.mListener != null) {
                            this.mListener.OnRegError(wUserSigInfo, a2, a.getBytes());
                            return;
                        } else {
                            return;
                        }
                    case 7:
                        a2 = oicq.wlogin_sdk.a.c.d(transReqContext.get_body(), jVar);
                        if (a2 == 0) {
                            util.LOGI("reg cmd:" + transReqContext.get_subcmd() + " ret:" + jVar.d, "");
                            if (this.mListener != null) {
                                this.mListener.OnRegQueryAccount(wUserSigInfo, jVar.d, jVar.f);
                                return;
                            }
                            return;
                        } else if (this.mListener != null) {
                            this.mListener.OnRegError(wUserSigInfo, a2, a.getBytes());
                            return;
                        } else {
                            return;
                        }
                    case 10:
                        a2 = oicq.wlogin_sdk.a.c.a(transReqContext.get_body(), jVar);
                        if (a2 == 0) {
                            util.LOGI("reg cmd:" + transReqContext.get_subcmd() + " ret:" + jVar.d, "");
                            if (jVar.d == 0) {
                                if (this.mListener != null) {
                                    this.mListener.OnRegCheckDownloadMsg(wUserSigInfo, jVar.m, jVar.n);
                                    return;
                                }
                                return;
                            } else if (jVar.d == 2) {
                                if (this.mListener != null) {
                                    this.mListener.OnRegCheckUploadMsg(wUserSigInfo, new String(jVar.q));
                                    return;
                                }
                                return;
                            } else if (jVar.d == 3) {
                                if (this.mListener != null) {
                                    this.mListener.OnRegCheckValidUrl(wUserSigInfo, jVar.r);
                                    return;
                                }
                                return;
                            } else if (jVar.d == 6 || jVar.d == 44) {
                                if (this.mListener != null) {
                                    this.mListener.OnRegCheckWebSig(wUserSigInfo, new String(jVar.r), new String(jVar.f));
                                }
                                jVar.r = new byte[0];
                                return;
                            } else {
                                util.LOGW("OnRequestRegister 0xa return code:", String.valueOf(jVar.d), str);
                                if (this.mListener != null) {
                                    this.mListener.OnRegError(wUserSigInfo, jVar.d, jVar.f);
                                    return;
                                }
                                return;
                            }
                        } else if (this.mListener != null) {
                            this.mListener.OnRegError(wUserSigInfo, a2, a.getBytes());
                            return;
                        } else {
                            return;
                        }
                    case 16:
                        a2 = oicq.wlogin_sdk.a.c.e(transReqContext.get_body(), jVar);
                        if (!(a2 == 0 || this.mListener == null)) {
                            this.mListener.OnRegError(wUserSigInfo, a2, a.getBytes());
                        }
                        util.LOGI("reg cmd:" + transReqContext.get_subcmd() + " ret:" + jVar.d, "");
                        this.mListener.OnQuickRegisterCheckAccount(wUserSigInfo, jVar.d, jVar.f);
                        return;
                    case 17:
                        a2 = oicq.wlogin_sdk.a.c.f(transReqContext.get_body(), jVar);
                        if (!(a2 == 0 || this.mListener == null)) {
                            this.mListener.OnRegError(wUserSigInfo, a2, a.getBytes());
                        }
                        util.LOGI("reg cmd:" + transReqContext.get_subcmd() + " ret:" + jVar.d, "");
                        wUserSigInfo.regTLVMap = jVar.B;
                        jVar.B = new HashMap();
                        this.mListener.OnQuickRegisterGetAccount(wUserSigInfo, jVar.d, jVar.f);
                        return;
                    default:
                        util.LOGW("OnRequestRegister unhandle cmd:" + transReqContext.get_subcmd(), "", str);
                        if (this.mListener != null) {
                            this.mListener.OnRegError(wUserSigInfo, util.E_NO_REG_CMD, a.getBytes());
                            return;
                        }
                        return;
                }
            } else if (this.mListener != null) {
                this.mListener.OnRegError(wUserSigInfo, i, a.getBytes());
            }
        }
    }

    public void SetRegDevLockFlag(int i) {
        u.z = i;
    }

    public int RegSubmitMobile(String str, byte[] bArr, byte[] bArr2, int i, int i2, int i3, long j, long j2, WUserSigInfo wUserSigInfo) {
        return RegSubmitMobile(str == null ? new byte[0] : str.getBytes(), bArr, null, bArr2, i, i2, i3, j, j2, wUserSigInfo);
    }

    public int RegSubmitMobile(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, int i3, long j, long j2, WUserSigInfo wUserSigInfo) {
        return RegSubmitMobile(null, bArr, bArr2, bArr3, i, i2, i3, j, j2, wUserSigInfo);
    }

    private int RegSubmitMobile(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, int i2, int i3, long j, long j2, WUserSigInfo wUserSigInfo) {
        if (bArr2 == null || bArr2.length <= 0 || bArr4 == null) {
            return -1017;
        }
        byte[] bArr5;
        if (bArr == null) {
            bArr5 = new byte[0];
        } else {
            bArr5 = bArr;
        }
        byte[] bArr6 = u.E;
        long j3 = 0;
        byte[] bArr7 = new byte[0];
        WloginLastLoginInfo GetLastLoginInfo = GetLastLoginInfo();
        if (GetLastLoginInfo != null) {
            j3 = GetLastLoginInfo.mUin;
            Ticket GetLocalTicket = GetLocalTicket(GetLastLoginInfo.mAccount, j, 64);
            if (!(GetLocalTicket == null || GetLocalTicket._sig == null)) {
                bArr7 = GetLocalTicket._sig;
            }
        }
        util.LOGI("has uin? " + j3 + ", a2: " + bArr7.length);
        util.LOGI("RegSubmitMobile mobile ..." + new String(bArr2) + " appname: " + new String(bArr6) + "...", "");
        this.mRegStatus.c = new String(bArr2);
        h hVar = new h();
        TransReqContext transReqContext = new TransReqContext();
        j jVar = this.mRegStatus;
        jVar.k = bArr2;
        jVar.g = j;
        jVar.h = j2;
        transReqContext.set_register_req();
        transReqContext.set_subcmd(hVar.a());
        transReqContext._body = hVar.a(bArr2, bArr6, bArr4, i, i2, i3, j, j2, null, util.generateGuid(this.mContext), util.get_IMSI(this.mContext), u.aa, j3, bArr7, GetGuid(), bArr5);
        return RequestTransport(0, 1, null, 0, (long) jVar.i, transReqContext, wUserSigInfo);
    }

    public int RegQueryClientSentMsgStatus(WUserSigInfo wUserSigInfo) {
        util.LOGI("RegQueryClientSentMsgStatus ...", "");
        f fVar = new f();
        TransReqContext transReqContext = new TransReqContext();
        j jVar = this.mRegStatus;
        transReqContext.set_register_req();
        transReqContext.set_subcmd(fVar.a());
        transReqContext._body = fVar.b(jVar.e, this.mRegStatus.p);
        return RequestTransport(0, 1, null, 0, (long) jVar.i, transReqContext, wUserSigInfo);
    }

    public int RegRequestServerResendMsg(WUserSigInfo wUserSigInfo) {
        util.LOGI("RegRequestServerResendMsg ...", "");
        g gVar = new g();
        TransReqContext transReqContext = new TransReqContext();
        j jVar = this.mRegStatus;
        transReqContext.set_register_req();
        transReqContext.set_subcmd(gVar.a());
        transReqContext._body = gVar.b(jVar.e, null);
        return RequestTransport(0, 1, null, 0, (long) jVar.i, transReqContext, wUserSigInfo);
    }

    public int RegSubmitMsgChk(byte[] bArr, WUserSigInfo wUserSigInfo) {
        if (bArr == null) {
            return -1017;
        }
        util.LOGI("RegSubmitMsgChk ...", "");
        oicq.wlogin_sdk.a.i iVar = new oicq.wlogin_sdk.a.i();
        TransReqContext transReqContext = new TransReqContext();
        j jVar = this.mRegStatus;
        transReqContext.set_register_req();
        transReqContext.set_subcmd(iVar.a());
        transReqContext._body = iVar.b(jVar.e, bArr);
        return RequestTransport(0, 1, null, 0, (long) jVar.i, transReqContext, wUserSigInfo);
    }

    public int RegGetAccount(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, WUserSigInfo wUserSigInfo) {
        if (bArr3 == null || bArr3.length <= 0) {
            return -1017;
        }
        util.LOGI("RegGetAccount ...", "");
        d dVar = new d();
        TransReqContext transReqContext = new TransReqContext();
        j jVar = this.mRegStatus;
        if (bArr != null) {
            jVar.j = (byte[]) bArr.clone();
        } else {
            jVar.j = new byte[0];
        }
        if (i == 4) {
            jVar.b = "";
        }
        transReqContext.set_register_req();
        transReqContext.set_subcmd(dVar.a());
        transReqContext._body = dVar.a(jVar.e, bArr, bArr3, bArr4, i, jVar.b.getBytes(), bArr2, false, null, 0, u.E, u.z);
        return RequestTransport(0, 1, null, 0, (long) jVar.i, transReqContext, wUserSigInfo);
    }

    public int RegQueryAccount(int i, byte[] bArr, long j, WUserSigInfo wUserSigInfo) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        util.LOGI("RegQueryAccount ...", "");
        this.mRegStatus = new j();
        this.mRegStatus.b = new String(bArr);
        e eVar = new e();
        TransReqContext transReqContext = new TransReqContext();
        j jVar = this.mRegStatus;
        transReqContext.set_register_req();
        transReqContext.set_subcmd(eVar.a());
        transReqContext._body = eVar.a(i, bArr, j);
        return RequestTransport(0, 1, null, 0, (long) jVar.i, transReqContext, wUserSigInfo);
    }

    private void OnRequestCode2d(String str, long j, long j2, TransReqContext transReqContext, WUserSigInfo wUserSigInfo, int i) {
        if (this.mListener != null) {
            c cVar = oicq.wlogin_sdk.code2d.b._status;
            if (i != 0) {
                util.LOGI("OnRequestCode2d ret:" + i, str);
            }
            int a;
            switch (transReqContext.get_subcmd()) {
                case 18:
                    if (i == 0) {
                        a = new oicq.wlogin_sdk.code2d.d().a(transReqContext.get_body());
                        util.LOGI("QueryCodeResult ret:" + cVar.b, str);
                    } else {
                        a = i;
                    }
                    this.mListener.OnQueryCodeResult(cVar.a, cVar.e, cVar.c, wUserSigInfo, cVar.f, a);
                    return;
                case 19:
                    if (i != 0) {
                        this.mListener.OnVerifyCode(str, cVar.d, cVar.c, cVar.e, wUserSigInfo, cVar.f, i);
                        return;
                    }
                    cVar.b = new oicq.wlogin_sdk.code2d.e().a(transReqContext.get_body());
                    util.LOGI("VerifyCode ret:" + cVar.b, str);
                    if (cVar.b == 0 && cVar.g != null && cVar.g.length > 0) {
                        this.mG.a(cVar.a, j, cVar.g);
                    }
                    this.mListener.OnVerifyCode(str, cVar.d, cVar.c, cVar.e, wUserSigInfo, cVar.f, cVar.b);
                    return;
                case 20:
                    if (i != 0) {
                        this.mListener.OnCloseCode(str, cVar.d, cVar.c, wUserSigInfo, cVar.f, i);
                        return;
                    }
                    cVar.b = new oicq.wlogin_sdk.code2d.a().a(transReqContext.get_body(), j, u.t);
                    util.LOGI("CloseCode ret:" + cVar.b, str);
                    c.s = false;
                    this.mListener.OnCloseCode(str, cVar.d, cVar.c, wUserSigInfo, cVar.f, cVar.b);
                    return;
                case 49:
                    if (i == 0) {
                        a = new fetch_code().get_response(transReqContext.get_body());
                        util.LOGI("FetchCodeSig ret:" + cVar.b, str);
                    } else {
                        a = i;
                    }
                    this.mListener.OnFetchCodeSig(cVar.j, cVar.k, (long) cVar.l, wUserSigInfo, cVar.f, a);
                    return;
                default:
                    util.LOGW("OnRequestName unhandle cmd", "", str);
                    this.mListener.OnException(new ErrMsg(), 9, wUserSigInfo);
                    return;
            }
        }
    }

    private void tlvCommRsp2ErrMsg(TLV_CommRsp tLV_CommRsp, ErrMsg errMsg) {
        if (tLV_CommRsp != null && tLV_CommRsp.get_data_len() != 0) {
            errMsg.setType(tLV_CommRsp.ErrInfoType);
            errMsg.setOtherinfo(new String(tLV_CommRsp.ErrInfo));
            errMsg.setTitle(new String(tLV_CommRsp.ErrTitle));
            errMsg.setMessage(new String(tLV_CommRsp.ErrMsg));
        }
    }

    private void OnDeviceLockRequest(String str, long j, long j2, TransReqContext transReqContext, WUserSigInfo wUserSigInfo, int i) {
        DevlockRst devlockRst = DevlockBase.rst;
        devlockRst.commRsp = new TLV_CommRsp();
        DevlockInfo devlockInfo = new DevlockInfo();
        ErrMsg errMsg = new ErrMsg(0, "", "", "");
        if (i != 0) {
            util.LOGI("OnDeviceLockRequest ret:" + i, str);
            errMsg.setMessage(util.get_error_msg(i));
            errMsg.setTitle(InternationMsg.a(MSG_TYPE.MSG_5));
        }
        switch (transReqContext.get_subcmd()) {
            case 5:
                if (i == 0) {
                    i = new oicq.wlogin_sdk.devicelock.a().parse_rsp(transReqContext.get_body());
                    util.LOGI("CheckDevLockStatus ret:" + i, str);
                    if (i != -1009) {
                        tlvCommRsp2ErrMsg(devlockRst.commRsp, errMsg);
                        if (devlockRst.devSetupInfo != null && devlockRst.devSetupInfo.get_data_len() > 0) {
                            devlockInfo.DevSetup = devlockRst.devSetupInfo.a;
                            devlockInfo.AllowSet = devlockRst.devSetupInfo.b;
                            if (devlockRst.devGuideInfo == null || devlockRst.devGuideInfo.get_data_len() <= 0) {
                                devlockInfo.ProtectIntro = new String(devlockRst.devSetupInfo.d);
                            } else {
                                devlockInfo.ProtectIntro = new String(devlockRst.devGuideInfo.a);
                            }
                            devlockInfo.WarningInfo = new String(devlockRst.devSetupInfo.g);
                            devlockInfo.WarningTitle = new String(devlockRst.devSetupInfo.e);
                            devlockInfo.WarningMsg = new String(devlockRst.devSetupInfo.f);
                            devlockInfo.WarningInfoType = devlockRst.devSetupInfo.c;
                        }
                        if (devlockRst.mbMobileInfo != null && devlockRst.mbMobileInfo.get_data_len() > 0) {
                            devlockInfo.CountryCode = new String(devlockRst.mbMobileInfo.a);
                            devlockInfo.Mobile = new String(devlockRst.mbMobileInfo.b);
                            devlockInfo.MbItemSmsCodeStatus = devlockRst.mbMobileInfo.c;
                            devlockInfo.AvailableMsgCount = devlockRst.mbMobileInfo.d;
                            devlockInfo.TimeLimit = devlockRst.mbMobileInfo.e;
                        }
                        if (devlockRst.mbGuideInfo != null && devlockRst.mbGuideInfo.get_data_len() > 0) {
                            devlockInfo.MbGuideType = devlockRst.mbGuideInfo.c;
                            devlockInfo.MbGuideInfoType = devlockRst.mbGuideInfo.d;
                            devlockInfo.MbGuideInfo = new String(devlockRst.mbGuideInfo.b);
                            devlockInfo.MbGuideMsg = new String(devlockRst.mbGuideInfo.a);
                        }
                        if (devlockRst.transferInfo != null && devlockRst.transferInfo.get_data_len() > 0) {
                            devlockInfo.TransferInfo = devlockRst.transferInfo.get_data();
                        }
                    }
                }
                if (this.mListener != null) {
                    this.mListener.OnCheckDevLockStatus(wUserSigInfo, devlockInfo, i, errMsg);
                    return;
                }
                return;
            case 7:
                if (i == 0) {
                    i = new oicq.wlogin_sdk.devicelock.d().parse_rsp(transReqContext.get_body());
                    util.LOGI("AskDevLockSms ret:" + i, str);
                    if (i != -1009) {
                        tlvCommRsp2ErrMsg(devlockRst.commRsp, errMsg);
                        if (devlockRst.smsInfo != null) {
                            devlockInfo.AvailableMsgCount = devlockRst.smsInfo.a;
                            devlockInfo.TimeLimit = devlockRst.smsInfo.b;
                        }
                    }
                }
                if (this.mListener != null) {
                    this.mListener.OnAskDevLockSms(wUserSigInfo, devlockInfo, i, errMsg);
                    return;
                }
                return;
            case 8:
                if (i == 0) {
                    i = new oicq.wlogin_sdk.devicelock.f().parse_rsp(transReqContext.get_body());
                    util.LOGI("CheckDevLockSms ret:" + i, str);
                    if (i != -1009) {
                        tlvCommRsp2ErrMsg(devlockRst.commRsp, errMsg);
                    }
                }
                if (this.mListener != null) {
                    this.mListener.OnCheckDevLockSms(wUserSigInfo, i, errMsg);
                    return;
                }
                return;
            case 12:
                if (i == 0) {
                    i = new oicq.wlogin_sdk.devicelock.b().parse_rsp(transReqContext.get_body());
                    util.LOGI("CloseDevLock ret:" + i, str);
                    if (i != -1009) {
                        tlvCommRsp2ErrMsg(devlockRst.commRsp, errMsg);
                        try {
                            long parseLong;
                            if (util.check_uin_account(str).booleanValue()) {
                                parseLong = Long.parseLong(str);
                            } else {
                                parseLong = this.mG.b(str);
                            }
                            this.mG.b(parseLong, j);
                            for (int i2 = 0; i2 < wUserSigInfo._tickets.size(); i2++) {
                                if (((Ticket) wUserSigInfo._tickets.get(i2))._type == SigType.WLOGIN_DA2) {
                                    wUserSigInfo._tickets.remove(i2);
                                }
                            }
                        } catch (Exception e) {
                            util.printException(e);
                        }
                    }
                }
                if (this.mListener != null) {
                    this.mListener.OnCloseDevLock(wUserSigInfo, i, errMsg);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public byte[] PickupQRCode(String str) {
        if (str == null) {
            return null;
        }
        String str2 = ".*[?&]k=([^&$]+).*";
        if (!str.matches(str2)) {
            return null;
        }
        String replaceAll = str.replaceAll(str2, "$1");
        return util.base64_decode_url(replaceAll.getBytes(), replaceAll.length());
    }

    public boolean IsWtLoginUrl(String str) {
        if (str == null) {
            return false;
        }
        int indexOf = str.indexOf("?k=");
        if (indexOf == -1 || (indexOf + 3) + 32 > str.length()) {
            return false;
        }
        indexOf += 3;
        String substring = str.substring(indexOf, indexOf + 32);
        if (util.base64_decode_url(substring.getBytes(), substring.length()) != null) {
            return true;
        }
        return false;
    }

    public long GetAppidFromUrl(String str) {
        if (str == null) {
            return -1;
        }
        int indexOf = str.indexOf("f=");
        if (indexOf == -1 || indexOf + 2 >= str.length()) {
            return -1;
        }
        int i = indexOf + 2;
        String str2 = "";
        while (i < str.length() && str.charAt(i) != '&') {
            str2 = str2 + str.charAt(i);
            i++;
        }
        try {
            return Long.parseLong(str2);
        } catch (Exception e) {
            return -1;
        }
    }

    public int VerifyCode(String str, long j, boolean z, byte[] bArr, int[] iArr, int i, WUserSigInfo wUserSigInfo) {
        WloginSimpleInfo wloginSimpleInfo = new WloginSimpleInfo();
        if (!GetBasicUserInfo(str, wloginSimpleInfo).booleanValue()) {
            return -1003;
        }
        long j2 = wloginSimpleInfo._uin;
        WloginSigInfo FindUserSig = FindUserSig(j2, j);
        if (FindUserSig == null) {
            return -1004;
        }
        cl clVar = new cl();
        byte[] bArr2 = new byte[0];
        if (FindUserSig._G != null && FindUserSig._G.length > 0 && FindUserSig._dpwd != null && FindUserSig._dpwd.length > 0 && FindUserSig._randseed != null && FindUserSig._randseed.length > 0) {
            clVar.a(FindUserSig._G, j2, u.A, FindUserSig._dpwd, j, 1, FindUserSig._randseed);
            bArr2 = clVar.c();
        }
        util.LOGI("user:" + str + " VerifyCode ...", str);
        oicq.wlogin_sdk.code2d.e eVar = new oicq.wlogin_sdk.code2d.e();
        TransReqContext transReqContext = new TransReqContext();
        transReqContext.set_code2d_func_req();
        transReqContext.set_subcmd(eVar.get_cmd());
        transReqContext.setSTEncryptMethod();
        transReqContext.setWtST(FindUserSig);
        transReqContext._body = eVar.a(j2, j, z, bArr, iArr, FindUserSig._userStSig, u.A, u.E, i, bArr2);
        return RequestTransport(0, 1, str, j, (long) eVar._role, transReqContext, wUserSigInfo);
    }

    public int CloseCode(String str, long j, byte[] bArr, int i, List<byte[]> list, WUserSigInfo wUserSigInfo) {
        WloginSimpleInfo wloginSimpleInfo = new WloginSimpleInfo();
        if (!GetBasicUserInfo(str, wloginSimpleInfo).booleanValue()) {
            return -1003;
        }
        long j2 = wloginSimpleInfo._uin;
        this.mG.j();
        WloginSigInfo FindUserSig = FindUserSig(j2, j);
        if (FindUserSig == null) {
            return -1004;
        }
        util.LOGI("user:" + str + " CloseCode ...", str);
        oicq.wlogin_sdk.code2d.a aVar = new oicq.wlogin_sdk.code2d.a();
        TransReqContext transReqContext = new TransReqContext();
        transReqContext.set_code2d_func_req();
        transReqContext.set_subcmd(aVar.get_cmd());
        transReqContext.setSTEncryptMethod();
        transReqContext.setWtST(FindUserSig);
        transReqContext._body = aVar.a(j2, j, 1, bArr, FindUserSig._userStSig, u.A, i, list, FindUserSig._en_A1, FindUserSig._noPicSig, (long) this.mMiscBitmap, 0);
        return RequestTransport(0, 1, str, j, (long) aVar._role, transReqContext, wUserSigInfo);
    }

    public int FetchCodeSig(long j, long j2, QRCodeCustom qRCodeCustom, WUserSigInfo wUserSigInfo) {
        util.LOGI(" FetchCodeSig ...", "");
        fetch_code oicq_wlogin_sdk_code2d_fetch_code = new fetch_code();
        TransReqContext transReqContext = new TransReqContext();
        transReqContext.set_code2d_func_req();
        transReqContext.set_subcmd(oicq_wlogin_sdk_code2d_fetch_code.get_cmd());
        transReqContext._body = oicq_wlogin_sdk_code2d_fetch_code.get_request(0, j, j2, new byte[0], qRCodeCustom, (long) this.mMiscBitmap, 0, WloginSigInfo._QRPUSHSig);
        return RequestTransport(0, 1, null, j, (long) oicq_wlogin_sdk_code2d_fetch_code._role, transReqContext, wUserSigInfo);
    }

    public int QueryCodeResult(long j, WUserSigInfo wUserSigInfo) {
        util.LOGI(" QueryCodeResult ...", "");
        oicq.wlogin_sdk.code2d.d dVar = new oicq.wlogin_sdk.code2d.d();
        TransReqContext transReqContext = new TransReqContext();
        transReqContext.set_code2d_func_req();
        transReqContext.set_subcmd(dVar.get_cmd());
        util.LOGI("qrsig " + util.buf_to_string(c.i));
        transReqContext._body = dVar.a(0, j, c.i, new byte[0]);
        return RequestTransport(0, 1, null, j, (long) dVar._role, transReqContext, wUserSigInfo);
    }

    public void setMsgType(int i, int i2, int i3) {
        DevlockBase.a.a = i;
        DevlockBase.a.b = i2;
        DevlockBase.a.c = i3;
    }

    public int CheckDevLockStatus(String str, long j, long j2, WUserSigInfo wUserSigInfo) {
        if (str == null) {
            return -1017;
        }
        WloginSimpleInfo wloginSimpleInfo = new WloginSimpleInfo();
        if (!GetBasicUserInfo(str, wloginSimpleInfo).booleanValue()) {
            return -1003;
        }
        long j3 = wloginSimpleInfo._uin;
        WloginSigInfo FindUserSig = FindUserSig(j3, j);
        if (FindUserSig == null) {
            return -1004;
        }
        util.LOGI("CheckDevLockStatus ...", str);
        DevlockBase.rst = new DevlockRst();
        oicq.wlogin_sdk.devicelock.a aVar = new oicq.wlogin_sdk.devicelock.a();
        TransReqContext transReqContext = new TransReqContext();
        transReqContext.set_devlock_req();
        transReqContext.set_subcmd(aVar.get_msgType());
        transReqContext.setSTEncryptMethod();
        transReqContext.setWtST(FindUserSig);
        transReqContext._body = aVar.a(j3, j, j2, FindUserSig._TGT, u.A, u.E, util.SDK_VERSION.getBytes(), u.K, u.J);
        if (transReqContext._body == null || transReqContext._body.length == 0) {
            return -1017;
        }
        return RequestTransport(0, 1, str, j, (long) aVar.Role, transReqContext, wUserSigInfo);
    }

    public int CloseDevLock(String str, long j, long j2, WUserSigInfo wUserSigInfo) {
        if (str == null) {
            return -1017;
        }
        WloginSimpleInfo wloginSimpleInfo = new WloginSimpleInfo();
        if (!GetBasicUserInfo(str, wloginSimpleInfo).booleanValue()) {
            return -1003;
        }
        long j3 = wloginSimpleInfo._uin;
        WloginSigInfo FindUserSig = FindUserSig(j3, j);
        if (FindUserSig == null) {
            return -1004;
        }
        util.LOGI("CloseDevLock ...", str);
        oicq.wlogin_sdk.devicelock.b bVar = new oicq.wlogin_sdk.devicelock.b();
        TransReqContext transReqContext = new TransReqContext();
        String str2 = VERSION.RELEASE;
        if (str2 == null) {
            str2 = "";
        }
        transReqContext.set_devlock_req();
        transReqContext.set_subcmd(bVar.get_msgType());
        transReqContext.setSTEncryptMethod();
        transReqContext.setWtST(FindUserSig);
        transReqContext._body = bVar.a(j3, j, j2, FindUserSig._TGT, u.A, u.E, util.SDK_VERSION.getBytes(), "android".getBytes(), str2.getBytes());
        if (transReqContext._body == null || transReqContext._body.length == 0) {
            return -1017;
        }
        return RequestTransport(0, 1, str, j, (long) bVar.Role, transReqContext, wUserSigInfo);
    }

    public int AskDevLockSms(String str, long j, long j2, WUserSigInfo wUserSigInfo) {
        if (str == null) {
            return -1017;
        }
        WloginSimpleInfo wloginSimpleInfo = new WloginSimpleInfo();
        if (!GetBasicUserInfo(str, wloginSimpleInfo).booleanValue()) {
            return -1003;
        }
        long j3 = wloginSimpleInfo._uin;
        util.LOGI("AskDevLockSms ...", str);
        oicq.wlogin_sdk.devicelock.d dVar = new oicq.wlogin_sdk.devicelock.d();
        TransReqContext transReqContext = new TransReqContext();
        transReqContext.set_devlock_req();
        transReqContext.set_subcmd(dVar.get_msgType());
        transReqContext._body = dVar.a(j3, j, j2);
        if (transReqContext._body == null || transReqContext._body.length == 0) {
            return -1017;
        }
        return RequestTransport(0, 1, str, j, (long) dVar.Role, transReqContext, wUserSigInfo);
    }

    public int CheckDevLockSms(String str, long j, long j2, String str2, byte[] bArr, WUserSigInfo wUserSigInfo) {
        if (str == null) {
            return -1017;
        }
        WloginSimpleInfo wloginSimpleInfo = new WloginSimpleInfo();
        if (!GetBasicUserInfo(str, wloginSimpleInfo).booleanValue()) {
            return -1003;
        }
        long j3 = wloginSimpleInfo._uin;
        WloginSigInfo FindUserSig = FindUserSig(j3, j);
        if (FindUserSig == null) {
            return -1004;
        }
        if (bArr != null && bArr.length > 0) {
            DevlockBase.rst.setSppKey(bArr);
        }
        util.LOGI("CheckDevLockSms ...", str);
        oicq.wlogin_sdk.devicelock.f fVar = new oicq.wlogin_sdk.devicelock.f();
        TransReqContext transReqContext = new TransReqContext();
        String str3 = VERSION.RELEASE;
        if (str3 == null) {
            str3 = "";
        }
        transReqContext.set_devlock_req();
        transReqContext.set_subcmd(fVar.get_msgType());
        transReqContext.setSTEncryptMethod();
        transReqContext.setWtST(FindUserSig);
        transReqContext._body = fVar.a(j3, j, j2, FindUserSig._TGT, u.A, u.E, util.SDK_VERSION.getBytes(), "android".getBytes(), str3.getBytes(), str2 == null ? null : str2.getBytes());
        if (transReqContext._body == null || transReqContext._body.length == 0) {
            return -1017;
        }
        return RequestTransport(0, 1, str, j, (long) fVar.Role, transReqContext, wUserSigInfo);
    }

    private Intent PrepareQloginIntent(long j, long j2, String str) {
        String str2 = "com.tencent.mobileqq";
        boolean CheckMayFastLogin = util.CheckMayFastLogin(this.mContext);
        boolean CheckQQMiniHD = util.CheckQQMiniHD(this.mContext);
        if (!CheckMayFastLogin) {
            if (!CheckQQMiniHD) {
                return null;
            }
            str2 = Constants.PACKAGE_QQ_PAD;
        }
        byte[] bArr = util.get_rsa_pubkey(this.mContext);
        if (bArr == null || bArr.length == 0) {
            bArr = util.string_to_buf(RSACrypt.DEFAULT_PUB_KEY);
        }
        Intent intent = new Intent();
        intent.setClassName(str2, "com.tencent.open.agent.AgentActivity");
        Bundle bundle = new Bundle();
        bundle.putLong("dstSsoVer", 1);
        bundle.putLong("dstAppid", j);
        bundle.putLong("subDstAppid", j2);
        bundle.putByteArray("dstAppVer", str.getBytes());
        bundle.putByteArray("publickey", bArr);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        intent.putExtra(Constants.KEY_ACTION, "action_quick_login");
        return intent;
    }

    public Intent PrepareSilenceLoginIntent(long j, long j2, String str) {
        byte[] bArr = util.get_rsa_pubkey(this.mContext);
        if (bArr == null || bArr.length == 0) {
            bArr = util.string_to_buf(RSACrypt.DEFAULT_PUB_KEY);
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putLong("dstSsoVer", 1);
        bundle.putLong("dstAppid", j);
        bundle.putLong("subDstAppid", j2);
        bundle.putByteArray("dstAppVer", str.getBytes());
        bundle.putByteArray("publickey", bArr);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        intent.putExtra(Constants.KEY_ACTION, "action_quick_login");
        return intent;
    }

    public Intent PrepareQloginResult(String str, long j, long j2, int i, WFastLoginInfo wFastLoginInfo) {
        Intent intent = new Intent();
        intent.putExtra("quicklogin_uin", str);
        byte[] bArr = (byte[]) wFastLoginInfo._outA1.clone();
        if (bArr != null && bArr.length > 0) {
            intent.putExtra("quicklogin_buff", new RSACrypt(this.mContext).EncryptData(util.get_cp_pubkey(this.mContext, j, j2), bArr));
        }
        intent.putExtra("quicklogin_ret", i);
        return intent;
    }

    private WUserSigInfo ResolveQloginIntentReserved(Intent intent) {
        if (intent == null || intent.getExtras().getInt("quicklogin_ret") != 0) {
            return null;
        }
        String string = intent.getExtras().getString("quicklogin_uin");
        byte[] byteArray = intent.getExtras().getByteArray("quicklogin_buff");
        if (string == null || byteArray == null) {
            return null;
        }
        WUserSigInfo wUserSigInfo = new WUserSigInfo();
        wUserSigInfo._fastLoginBuf = new RSACrypt(this.mContext).DecryptData(null, byteArray);
        if (wUserSigInfo._fastLoginBuf == null) {
            util.LOGI("rsa decrypt failed", "");
            return null;
        }
        wUserSigInfo.uin = string;
        return wUserSigInfo;
    }

    public static void setLoadEncryptSo(boolean z) {
        util.loadEncryptSo = z;
    }

    public static byte[] getRegTlvValue(WUserSigInfo wUserSigInfo, int i) {
        oicq.wlogin_sdk.b.b bVar = (oicq.wlogin_sdk.b.b) wUserSigInfo.regTLVMap.get(new Integer(i));
        if (bVar != null) {
            return bVar.c();
        }
        return null;
    }

    public static void setExtraLoginTlvValue(WUserSigInfo wUserSigInfo, int i, byte[] bArr) {
        Integer num = new Integer(i);
        oicq.wlogin_sdk.b.b bVar = new oicq.wlogin_sdk.b.b(i);
        bVar.b(bArr, bArr.length);
        wUserSigInfo.extraLoginTLVMap.put(num, bVar);
    }

    private int getStWithQQSig(String str, QuickLoginParam quickLoginParam) {
        return getStWithQQSig(str, quickLoginParam, 0);
    }

    private int getStWithQQSig(String str, QuickLoginParam quickLoginParam, int i) {
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, str, quickLoginParam, "getStWithQQSig").RunReq(15);
            return -1001;
        }
        WUserSigInfo wUserSigInfo = quickLoginParam.userSigInfo;
        wUserSigInfo.uin = str;
        u a = this.mG.a(0);
        wUserSigInfo._seqence = a.h;
        this.mAysncSeq = a.h;
        async_context b = u.b(a.h);
        int i2 = util.get_saved_network_type(this.mContext);
        u.D = util.get_network_type(this.mContext);
        if (i2 != u.D) {
            util.set_net_retry_type(this.mContext, 0);
            util.save_network_type(this.mContext, u.D);
        }
        u.F = util.get_apn_string(this.mContext).getBytes();
        quickLoginParam.sigMap |= Opcodes.AND_LONG_2ADDR;
        a.g = str;
        a.f = Long.parseLong(str);
        b._sappid = quickLoginParam.appid;
        b._appid = quickLoginParam.appid;
        b._sub_appid = quickLoginParam.subAppid;
        b._main_sigmap = quickLoginParam.sigMap;
        b._login_bitmap = wUserSigInfo._login_bitmap;
        b._last_err_msg = new ErrMsg();
        if (quickLoginParam.dstSubAppidList != null) {
            b._sub_appid_list = (long[]) quickLoginParam.dstSubAppidList.clone();
        } else {
            b._sub_appid_list = null;
        }
        if (wUserSigInfo._reserveData == null || wUserSigInfo._reserveData.length <= 3) {
            a.i = 0;
        } else {
            a.i = util.buf_to_int32(wUserSigInfo._reserveData, 0);
            util.LOGI("MSF SSO SEQ:" + a.i, str);
        }
        if (wUserSigInfo._fastLoginBuf == null || wUserSigInfo._fastLoginBuf.length == 0) {
            util.LOGI("fast login buff is null seq:" + a.i, str);
            return -1017;
        }
        if (GetFastLoginInfo(wUserSigInfo._fastLoginBuf, b) < 0) {
            util.LOGI("GetFastLoginInfo fast login buff is failed seq:" + a.i, str);
            return -1017;
        }
        z zVar = new z(a);
        zVar.g();
        int a2 = zVar.a(quickLoginParam.appid, 1, a.f, 0, u.ad, b._tmp_pwd, b._tmp_no_pic_sig, this.mMiscBitmap, this.mSubSigMap, quickLoginParam.dstSubAppidList, quickLoginParam.sigMap, quickLoginParam.subAppid, 1, u.y, 0, 0, 1, u.aa, quickLoginParam.appid, wUserSigInfo);
        if (a2 != 0) {
            util.LOGI("getStWithQQSig seq:" + a.h + " ret:" + a2, str);
            return a2;
        }
        WloginSigInfo a3 = a.a(a.f, quickLoginParam.appid);
        if (a3 == null) {
            return -1004;
        }
        wUserSigInfo.get_clone(a3);
        if (quickLoginParam.dstSubAppidList != null) {
            wUserSigInfo.stList = new byte[(quickLoginParam.dstSubAppidList.length * 2)][];
            for (int i3 = 0; i3 < quickLoginParam.dstSubAppidList.length; i3++) {
                WloginSigInfo a4 = a.a(a.f, quickLoginParam.dstSubAppidList[i3]);
                if (a4 != null) {
                    wUserSigInfo.stList[i3 * 2] = (byte[]) a4._userSt_Key.clone();
                    wUserSigInfo.stList[(i3 * 2) + 1] = (byte[]) a4._userStSig.clone();
                }
            }
        }
        util.LOGI("getStWithQQSig seq:" + a.h + " ret:" + a2, str);
        return a2;
    }

    private int getStWithPtSig(String str, String str2, QuickLoginParam quickLoginParam) {
        return getStWithPtSig(str, str2, quickLoginParam, 0);
    }

    private int getStWithPtSig(String str, String str2, QuickLoginParam quickLoginParam, int i) {
        if (i == 0) {
            new HelperThread(this, this.mHelperHandler, str, str2, quickLoginParam, "getStWithPtSig").RunReq(16);
            return -1001;
        }
        WUserSigInfo wUserSigInfo = quickLoginParam.userSigInfo;
        wUserSigInfo.uin = str;
        u a = this.mG.a(0);
        a.f = Long.parseLong(str);
        wUserSigInfo._seqence = a.h;
        async_context b = u.b(a.h);
        util.LOGI("getStWithPtSig seq:" + a.h, str);
        b._last_err_msg = new ErrMsg();
        quickLoginParam.sigMap |= Opcodes.AND_LONG_2ADDR;
        b._mpasswd = util.get_mpasswd();
        b._msalt = util.constructSalt();
        b._appid = quickLoginParam.appid;
        b._sub_appid = quickLoginParam.subAppid;
        b._main_sigmap = quickLoginParam.sigMap;
        b._sub_appid_list = quickLoginParam.dstSubAppidList;
        int a2 = new c(a, str2).a(this.mMiscBitmap, this.mSubSigMap, wUserSigInfo);
        if (a2 != 0) {
            util.LOGI("VerifyPTSig seq " + a.h + " ret " + a2, str);
            return a2;
        }
        b._tmp_pwd = MD5.toMD5Byte(b._mpasswd.getBytes());
        l lVar = new l(a, this.mContext);
        lVar.g();
        int a3 = lVar.a(quickLoginParam.appid, quickLoginParam.subAppid, a.f, 0, u.ad, util.getRequestInitTime(), b._tmp_pwd, 4, this.mMiscBitmap, this.mSubSigMap, quickLoginParam.dstSubAppidList, quickLoginParam.sigMap, quickLoginParam.subAppid, u.y, 0, 0, 1, u.aa, wUserSigInfo);
        if (a3 != 0) {
            util.LOGI("getStWithPtSig seq:" + a.h + " ret:" + a3, str);
            return a3;
        }
        WloginSigInfo a4 = a.a(a.f, quickLoginParam.appid);
        if (a4 == null) {
            return -1004;
        }
        wUserSigInfo.get_clone(a4);
        if (quickLoginParam.dstSubAppidList != null) {
            wUserSigInfo.stList = new byte[(quickLoginParam.dstSubAppidList.length * 2)][];
            for (int i2 = 0; i2 < quickLoginParam.dstSubAppidList.length; i2++) {
                WloginSigInfo a5 = a.a(a.f, quickLoginParam.dstSubAppidList[i2]);
                if (a5 != null) {
                    wUserSigInfo.stList[i2 * 2] = (byte[]) a5._userSt_Key.clone();
                    wUserSigInfo.stList[(i2 * 2) + 1] = (byte[]) a5._userStSig.clone();
                }
            }
        }
        util.LOGI("request_TGTGT seq:" + a.h + " ret " + a3, str);
        return a3;
    }

    public int quickLogin(Activity activity, long j, long j2, String str, QuickLoginParam quickLoginParam) {
        return oicq.wlogin_sdk.quicklogin.a.a(this.mContext, activity, j, j2, str, quickLoginParam);
    }

    public String getUserAccountFromQuickLoginResultData(Intent intent) {
        String stringExtra;
        boolean booleanExtra = intent.getBooleanExtra("isRetFromWeb", false);
        util.LOGI("getUserAccountFromQuickLoginResultData isRetFromWeb " + booleanExtra);
        if (booleanExtra) {
            stringExtra = intent.getStringExtra("uin");
        } else {
            stringExtra = intent.getStringExtra("quicklogin_uin");
        }
        if (stringExtra == null) {
            return "";
        }
        return stringExtra;
    }

    public int onQuickLoginActivityResultData(QuickLoginParam quickLoginParam, Intent intent) {
        if (intent == null) {
            util.LOGI("onActivityResultData data is null", "");
            return -1017;
        } else if (quickLoginParam == null) {
            util.LOGI("onActivityResultData quickLoginParam is null", "");
            return -1017;
        } else {
            boolean booleanExtra = intent.getBooleanExtra("isRetFromWeb", false);
            util.LOGI("onQuickLoginActivityResultData isRetFromWeb " + booleanExtra);
            if (booleanExtra) {
                return getStWithPtSig(intent.getStringExtra("uin"), intent.getStringExtra("sig"), quickLoginParam);
            }
            WUserSigInfo ResolveQloginIntentReserved = ResolveQloginIntentReserved(intent);
            if (ResolveQloginIntentReserved == null) {
                util.LOGI("onActivityResultData ResolveQloginIntent failed", "");
                return -1017;
            }
            quickLoginParam.userSigInfo.uin = ResolveQloginIntentReserved.uin;
            quickLoginParam.userSigInfo._fastLoginBuf = ResolveQloginIntentReserved._fastLoginBuf;
            return getStWithQQSig("" + ResolveQloginIntentReserved.uin, quickLoginParam);
        }
    }
}
