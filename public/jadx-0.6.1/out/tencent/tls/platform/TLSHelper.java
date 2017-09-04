package tencent.tls.platform;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Looper;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.KeyAgreement;
import tencent.tls.account.TLSOpenAccountInfo;
import tencent.tls.account.TLSOpenAccountInfo.OpenAccountStatus;
import tencent.tls.account.acc_ask_code;
import tencent.tls.account.acc_commit;
import tencent.tls.account.acc_guest;
import tencent.tls.account.acc_reask_code;
import tencent.tls.account.acc_request;
import tencent.tls.account.acc_status;
import tencent.tls.account.acc_verify_code;
import tencent.tls.oidb.Oidb0x483_request;
import tencent.tls.oidb.Oidb0x483_response;
import tencent.tls.oidb.Oidb0x601_request;
import tencent.tls.oidb.Oidb0x601_response;
import tencent.tls.oidb.Oidb0x602_request;
import tencent.tls.oidb.Oidb0x602_response;
import tencent.tls.oidb.Oidb0xa0b_request;
import tencent.tls.oidb.Oidb0xa0b_response;
import tencent.tls.report.QLog;
import tencent.tls.request.SigInfo;
import tencent.tls.request.Ticket;
import tencent.tls.request.TinyInfo;
import tencent.tls.request.TinyInfo.UserType;
import tencent.tls.request.TransReqContext;
import tencent.tls.request.WorkThread;
import tencent.tls.request.WorkThread.When;
import tencent.tls.request.WorkThread.Worker;
import tencent.tls.request.async_context;
import tencent.tls.request.oicq_request;
import tencent.tls.request.req_global;
import tencent.tls.request.req_imgcode_reask;
import tencent.tls.request.req_imgcode_verify;
import tencent.tls.request.req_smslogin_ask;
import tencent.tls.request.req_smslogin_reask;
import tencent.tls.request.req_smslogin_verify;
import tencent.tls.request.req_tgtgt_nopicsig;
import tencent.tls.request.req_transport;
import tencent.tls.tlvs.tlv_t;
import tencent.tls.tools.EcdhCrypt;
import tencent.tls.tools.I18nMsg;
import tencent.tls.tools.I18nMsg.MSG_TYPE;
import tencent.tls.tools.MD5;
import tencent.tls.tools.util;

public class TLSHelper {
    private static TLSHelper __helper = null;
    private static String guest = null;
    private static String ssoGuest = null;
    private int DummyGetSig = -1;
    private int mAccType = 0;
    private String mAppVer = "";
    private long mAsyncSeq = 0;
    private Context mContext;
    private int mCountry = 86;
    private req_global mG = new req_global(null);
    private int mMainSigMap = 262208;
    private int mMiscBitmap = 16252;
    private acc_status mRegStatus = new acc_status();
    private long mSdkAppid;
    private long mSubAppid = 1;
    private int mSubSigMap = 66560;
    private TLSOpenAccountInfo openAccountInfo;

    private TLSHelper() {
    }

    public static TLSHelper getInstance() {
        TLSHelper tLSHelper;
        synchronized (TLSHelper.class) {
            if (__helper == null) {
                __helper = new TLSHelper();
            }
            tLSHelper = __helper;
        }
        return tLSHelper;
    }

    public TLSHelper init(Context context, long j) {
        QLog.initQAL();
        this.mContext = context;
        this.mSdkAppid = j;
        this.mAccType = 0;
        this.mAppVer = "";
        req_global._acc_type = 0;
        req_global.sdkappid = j;
        acc_status.apk_id = util.get_apk_id(this.mContext);
        acc_status.apk_sig = util.getPkgSigFromApkName(this.mContext, this.mContext.getPackageName());
        this.mG.set_context(this.mContext);
        shareKeyInit();
        requestInit();
        return this;
    }

    private int ShareKeyInitDefault() {
        this.mG._pub_key = util.string_to_buf("020b03cf3d99541f29ffec281bebbd4ea211292ac1f53d7128");
        this.mG._share_key = util.string_to_buf("4da0f614fc9f29c2054c77048a6566d7");
        StringBuilder append = new StringBuilder().append("android sdk ");
        req_global tencent_tls_request_req_global = this.mG;
        QLog.w(append.append(req_global._android_sdk).append(" using DEFAULT key").toString());
        return 0;
    }

    private int ShareKeyInitOpenSSL() {
        if (VERSION.SDK_INT >= 23) {
            return -1;
        }
        EcdhCrypt ecdhCrypt = new EcdhCrypt(this.mContext);
        if (ecdhCrypt.GenereateKey() != 0) {
            return -1;
        }
        Object obj = ecdhCrypt.get_c_pub_key();
        Object obj2 = ecdhCrypt.get_g_share_key();
        if (obj == null || obj.length <= 0 || obj2 == null || obj2.length <= 0) {
            QLog.w("get client public key or shared key FAILED");
            return -2;
        }
        this.mG._pub_key = (byte[]) obj.clone();
        this.mG._share_key = (byte[]) obj2.clone();
        QLog.i("create key pair and shared key with OpenSSL OK");
        return 0;
    }

    private int ShareKeyInitBC() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("EC", "BC");
            instance.initialize(new ECGenParameterSpec("secp192k1"));
            KeyPair genKeyPair = instance.genKeyPair();
            Key key = genKeyPair.getPrivate();
            Object encoded = genKeyPair.getPublic().getEncoded();
            Key generatePublic = KeyFactory.getInstance("EC", "BC").generatePublic(new X509EncodedKeySpec(util.string_to_buf("3046301006072A8648CE3D020106052B8104001F03320004928D8850673088B343264E0C6BACB8496D697799F37211DEB25BB73906CB089FEA9639B4E0260498B51A992D50813DA8")));
            KeyAgreement instance2 = KeyAgreement.getInstance("ECDH", "BC");
            instance2.init(key);
            instance2.doPhase(generatePublic, true);
            byte[] generateSecret = instance2.generateSecret();
            Object obj = new byte[49];
            System.arraycopy(encoded, 23, obj, 0, 49);
            byte[] toMD5Byte = MD5.toMD5Byte(generateSecret);
            this.mG._pub_key = obj;
            this.mG._share_key = toMD5Byte;
            QLog.i("create key pair and shared key with bouncycastle OK");
            return 0;
        } catch (InvalidAlgorithmParameterException e) {
            QLog.w("create key pair and shared key failed, " + e.getMessage());
            return -1;
        } catch (NoSuchAlgorithmException e2) {
            QLog.w("create key pair and shared key failed, " + e2.getMessage());
            return -2;
        } catch (NoSuchProviderException e3) {
            QLog.w("create key pair and shared key failed, " + e3.getMessage());
            return -3;
        } catch (InvalidKeySpecException e4) {
            QLog.w("create key pair and shared key failed, " + e4.getMessage());
            return -4;
        } catch (InvalidKeyException e5) {
            QLog.w("create key pair and shared key failed, " + e5.getMessage());
            return -5;
        }
    }

    private int shareKeyInit() {
        QLog.i("Generate Shared Key Begin ...");
        if (ShareKeyInitOpenSSL() == 0 || ShareKeyInitBC() == 0) {
            return 0;
        }
        return ShareKeyInitDefault();
    }

    private int requestInit() {
        synchronized (this) {
            int i = util.get_saved_network_type(this.mContext);
            String str = VERSION.RELEASE;
            if (str == null) {
                str = "";
            }
            req_global.init();
            QLog.i("android version:" + str + " saved_network_type:" + i + " network_type:" + req_global._network_type + " release time:" + util.get_release_time() + " svn ver:" + util.SVN_VER);
        }
        return 0;
    }

    public void setTestHost(String str, boolean z) {
        oicq_request.set_test(1, str);
        this.mG._use_sso_channel = z;
    }

    public void setLocalId(int i) {
        acc_status.lang = i;
        req_global._local_id = i;
    }

    public void setTimeOut(int i) {
        this.mG._time_out = i;
    }

    public static void setLogcat(boolean z) {
        util.LOGCAT_OUT = z;
    }

    public String getSDKVersion() {
        return util.SDK_VERSION;
    }

    public void setCountry(int i) {
        this.mCountry = i;
    }

    public List<TLSUserInfo> getAllUserInfo() {
        return this.mG.get_all_logined_account();
    }

    public TLSUserInfo getLastUserInfo() {
        TLSUserInfo tLSUserInfo = null;
        List<TLSUserInfo> list = this.mG.get_all_logined_account();
        if (list != null) {
            for (TLSUserInfo tLSUserInfo2 : list) {
                TLSUserInfo tLSUserInfo3;
                if (tLSUserInfo != null && tLSUserInfo2.createTime <= tLSUserInfo.createTime) {
                    tLSUserInfo3 = tLSUserInfo;
                }
                tLSUserInfo = tLSUserInfo3;
            }
        }
        return tLSUserInfo;
    }

    public boolean needSmsVerify(String str) {
        return acc_status.mpasswd.length() == 0;
    }

    public boolean needLogin(String str) {
        if (str == null) {
            return true;
        }
        SigInfo sigInfo;
        long tinyId = this.mG.getTinyId(str);
        if (tinyId == 0) {
            sigInfo = null;
        } else {
            sigInfo = this.mG.get_siginfo(tinyId, this.mSdkAppid);
            if (sigInfo == null) {
            }
        }
        if (sigInfo == null || sigInfo._en_A1 == null || sigInfo._en_A1.length <= 0) {
            QLog.i("userAccount:" + str + " dwAppid:" + this.mSdkAppid + " IsUserHaveA1 false");
            return true;
        }
        QLog.i("userAccount:" + str + " dwAppid:" + this.mSdkAppid + " IsUserHaveA1 true");
        return false;
    }

    public Map<String, Object> getSSOTicket(String str) {
        Map<String, Object> hashMap = new HashMap();
        TLSUserInfo GetLocalSig = GetLocalSig(str, this.mSdkAppid);
        Ticket userTicket = getUserTicket(GetLocalSig, 64);
        if (userTicket == null) {
            hashMap.put("A2", new byte[0]);
            hashMap.put("A2Key", new byte[0]);
        } else {
            hashMap.put("A2", userTicket.Sig);
            hashMap.put("A2Key", userTicket.SigKey);
        }
        userTicket = getUserTicket(GetLocalSig, 262144);
        if (userTicket == null) {
            hashMap.put("D2", new byte[0]);
            hashMap.put("D2Key", new byte[0]);
        } else {
            hashMap.put("D2", userTicket.Sig);
            hashMap.put("D2Key", userTicket.SigKey);
        }
        long j = 0;
        if (GetLocalSig != null) {
            j = GetLocalSig.tinyid;
            str = GetLocalSig.identifier;
        }
        hashMap.put("tinyID", Long.valueOf(j));
        hashMap.put("identifier", str);
        return hashMap;
    }

    public String getUserSig(String str) {
        Ticket localTicket = getLocalTicket(str, SigType.TLS);
        String str2 = "";
        if (localTicket == null || localTicket.Sig == null || localTicket.Sig.length <= 0) {
            return str2;
        }
        str2 = new String(localTicket.Sig);
        QLog.i("ticket not null " + str2.length());
        return str2;
    }

    public String getGuestIdentifier() {
        if (guest != null) {
            return guest;
        }
        List<TLSUserInfo> list = this.mG.get_all_logined_account();
        if (list == null) {
            return null;
        }
        for (TLSUserInfo tLSUserInfo : list) {
            if (tLSUserInfo.userType == UserType.USER_TYPE_GUEST) {
                String str = tLSUserInfo.identifier;
                guest = str;
                return str;
            }
        }
        return null;
    }

    public String getSSOGuestIdentifier() {
        if (ssoGuest != null) {
            return ssoGuest;
        }
        List<TLSUserInfo> list = this.mG.get_all_logined_account();
        if (list == null) {
            return null;
        }
        for (TLSUserInfo tLSUserInfo : list) {
            if (tLSUserInfo.userType == UserType.USER_TYPE_SSO_GUEST) {
                String str = tLSUserInfo.identifier;
                ssoGuest = str;
                return str;
            }
        }
        return null;
    }

    public int TLSPwdResetAskCode(String str, TLSPwdResetListener tLSPwdResetListener) {
        QLog.i("TLSPwdResetAskCode ..." + str);
        return askCode(str, 64, false, tLSPwdResetListener);
    }

    public int TLSPwdRegAskCode(String str, TLSPwdRegListener tLSPwdRegListener) {
        QLog.i("TLSPwdRegAskCode ..." + str);
        return askCode(str, 49, false, tLSPwdRegListener);
    }

    public int TLSSmsRegAskCode(String str, TLSSmsRegListener tLSSmsRegListener) {
        QLog.i("TLSSmsRegAskCode ..." + str);
        return askCode(str, 33, false, tLSSmsRegListener);
    }

    private int askCode(String str, int i, boolean z, Object obj) {
        if (str == null || str.length() == 0) {
            return -1017;
        }
        if (z && (this.openAccountInfo.checkInvalid() || this.openAccountInfo.status == OpenAccountStatus.UNKNOWN || this.openAccountInfo.status == OpenAccountStatus.USED_BINDED)) {
            QLog.i("openAccountInfo invalid or status == UNKNOW or status == LOGINED_BINDED");
            return -1017;
        }
        this.mRegStatus.userID = str;
        acc_ask_code tencent_tls_account_acc_ask_code = new acc_ask_code(i);
        TransReqContext transReqContext = new TransReqContext(obj);
        this.mRegStatus.sdkAppid = this.mSdkAppid;
        transReqContext.set_register_req();
        transReqContext.set_subcmd(tencent_tls_account_acc_ask_code.get_cmd());
        if (z) {
            transReqContext._body = tencent_tls_account_acc_ask_code.get_request(this.mAccType, str, req_global._apk_id, this.mAppVer, this.mCountry, this.mSdkAppid, req_global._IMEI, this.openAccountInfo);
        } else {
            transReqContext._body = tencent_tls_account_acc_ask_code.get_request(this.mAccType, str, req_global._apk_id, this.mAppVer, this.mCountry, this.mSdkAppid, req_global._IMEI);
        }
        return RequestTransport(null, 0, str, null, (long) this.mRegStatus.role, transReqContext);
    }

    public int TLSPwdResetReaskCode(TLSPwdResetListener tLSPwdResetListener) {
        QLog.i("TLSPwdResetReaskCode ...");
        return reaskCode(65, tLSPwdResetListener);
    }

    public int TLSPwdRegReaskCode(TLSPwdRegListener tLSPwdRegListener) {
        QLog.i("TLSPwdRegReaskCode ...");
        return reaskCode(52, tLSPwdRegListener);
    }

    public int TLSSmsRegReaskCode(TLSSmsRegListener tLSSmsRegListener) {
        QLog.i("TLSSmsRegReaskCode ...");
        return reaskCode(36, tLSSmsRegListener);
    }

    private int reaskCode(int i, Object obj) {
        acc_reask_code tencent_tls_account_acc_reask_code = new acc_reask_code(i);
        TransReqContext transReqContext = new TransReqContext(obj);
        transReqContext.set_register_req();
        transReqContext.set_subcmd(tencent_tls_account_acc_reask_code.get_cmd());
        transReqContext._body = tencent_tls_account_acc_reask_code.get_request(this.mRegStatus.token);
        return RequestTransport(null, 0, this.mRegStatus.userID, null, (long) this.mRegStatus.role, transReqContext);
    }

    public int TLSPwdResetVerifyCode(String str, TLSPwdResetListener tLSPwdResetListener) {
        QLog.i("TLSPwdResetVerifyCode ...");
        return verifyCode(str, 66, tLSPwdResetListener);
    }

    public int TLSPwdRegVerifyCode(String str, TLSPwdRegListener tLSPwdRegListener) {
        QLog.i("TLSPwdRegVerifyCode ...");
        return verifyCode(str, 53, tLSPwdRegListener);
    }

    public int TLSSmsRegVerifyCode(String str, TLSSmsRegListener tLSSmsRegListener) {
        QLog.i("TLSSmsRegVerifyCode ...");
        return verifyCode(str, 37, tLSSmsRegListener);
    }

    private int verifyCode(String str, int i, Object obj) {
        if (str == null || str.length() == 0) {
            return -1017;
        }
        acc_verify_code tencent_tls_account_acc_verify_code = new acc_verify_code(i);
        TransReqContext transReqContext = new TransReqContext(obj);
        this.mRegStatus.msgcode = str.getBytes();
        transReqContext.set_register_req();
        transReqContext.set_subcmd(tencent_tls_account_acc_verify_code.get_cmd());
        transReqContext._body = tencent_tls_account_acc_verify_code.get_request(this.mRegStatus.token, str.getBytes());
        return RequestTransport(null, 0, this.mRegStatus.userID, null, (long) this.mRegStatus.role, transReqContext);
    }

    public int TLSPwdResetCommit(String str, TLSPwdResetListener tLSPwdResetListener) {
        QLog.i("TLSPwdResetCommit ...");
        return commit(str, 67, tLSPwdResetListener);
    }

    public int TLSPwdRegCommit(String str, TLSPwdRegListener tLSPwdRegListener) {
        QLog.i("TLSPwdRegCommit ...");
        return commit(str, 54, tLSPwdRegListener);
    }

    public int TLSSmsRegCommit(TLSSmsRegListener tLSSmsRegListener) {
        QLog.i("TLSSmsRegCommit No Password...");
        acc_status.mpasswd = util.get_mpasswd();
        return commit(acc_status.mpasswd, 38, tLSSmsRegListener);
    }

    private int commit(String str, int i, Object obj) {
        acc_commit tencent_tls_account_acc_commit = new acc_commit(i);
        TransReqContext transReqContext = new TransReqContext(obj);
        transReqContext.set_register_req();
        transReqContext.set_subcmd(tencent_tls_account_acc_commit.get_cmd());
        transReqContext._body = tencent_tls_account_acc_commit.get_request(this.mRegStatus.token, str, this.mRegStatus.msgcode);
        return RequestTransport(null, 0, this.mRegStatus.userID, null, (long) this.mRegStatus.role, transReqContext);
    }

    public int TLSStrAccReg(String str, String str2, TLSStrAccRegListener tLSStrAccRegListener) {
        if (str.length() == 0 || str.getBytes().length > 24) {
            return -1017;
        }
        if (str2.length() == 0 || str2.getBytes().length > 16 || str2.getBytes().length < 8) {
            return -1017;
        }
        Object obj = 1;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != '.' && charAt != '_' && !Character.isLetterOrDigit(charAt)) {
                return -1017;
            }
            if (!Character.isDigit(charAt)) {
                obj = null;
            }
        }
        if (obj != null) {
            return -1017;
        }
        acc_ask_code tencent_tls_account_acc_ask_code = new acc_ask_code(97);
        TransReqContext transReqContext = new TransReqContext(tLSStrAccRegListener);
        this.mRegStatus.userID = str;
        this.mRegStatus.password = str2;
        transReqContext.set_register_req();
        transReqContext.set_subcmd(tencent_tls_account_acc_ask_code.get_cmd());
        transReqContext._body = tencent_tls_account_acc_ask_code.get_request(this.mAccType, str, req_global._apk_id, this.mAppVer, this.mCountry, this.mSdkAppid, req_global._IMEI);
        return RequestTransport(null, 0, str, null, (long) this.mRegStatus.role, transReqContext);
    }

    private int TLSStrAccRegCommit(TLSStrAccRegListener tLSStrAccRegListener) {
        acc_commit tencent_tls_account_acc_commit = new acc_commit(102);
        TransReqContext transReqContext = new TransReqContext(tLSStrAccRegListener);
        transReqContext.set_register_req();
        transReqContext.set_subcmd(tencent_tls_account_acc_commit.get_cmd());
        transReqContext._body = tencent_tls_account_acc_commit.get_request(this.mRegStatus.token, this.mRegStatus.password, null);
        return RequestTransport(null, 0, this.mRegStatus.userID, null, (long) this.mRegStatus.role, transReqContext);
    }

    private int TLSGuestReg(TLSGuestRegListener tLSGuestRegListener) {
        acc_guest tencent_tls_account_acc_guest = new acc_guest();
        TransReqContext transReqContext = new TransReqContext(tLSGuestRegListener);
        transReqContext.set_register_req();
        transReqContext.set_subcmd(tencent_tls_account_acc_guest.get_cmd());
        transReqContext._body = tencent_tls_account_acc_guest.get_request(this.mAccType, req_global._apk_id, this.mAppVer, this.mCountry, this.mSdkAppid, req_global._IMEI);
        return RequestTransport(null, 0, null, null, (long) this.mRegStatus.role, transReqContext);
    }

    private int TLSSSOGuestReg(TLSSSORegListener tLSSSORegListener) {
        acc_guest tencent_tls_account_acc_guest = new acc_guest();
        TransReqContext transReqContext = new TransReqContext(tLSSSORegListener);
        transReqContext.set_register_req();
        transReqContext.set_subcmd(tencent_tls_account_acc_guest.get_cmd());
        transReqContext._body = tencent_tls_account_acc_guest.get_request(this.mAccType, req_global._apk_id, this.mAppVer, this.mCountry, this.mSdkAppid, req_global._IMEI);
        return RequestTransport(null, 0, null, null, (long) this.mRegStatus.role, transReqContext);
    }

    public int TLSRefreshUserSig(String str, final TLSRefreshUserSigListener tLSRefreshUserSigListener) {
        TLSOpenAccountInfo GetLocalOpenAccountInfo = GetLocalOpenAccountInfo(str, this.mSdkAppid);
        if (GetLocalOpenAccountInfo == null || !GetLocalOpenAccountInfo.openid.equals(str)) {
            QLog.i("Account RefreshUserSig");
            return GetStWithoutPasswd(str, this.mSdkAppid, tLSRefreshUserSigListener);
        }
        QLog.i("OpenAccount RefreshUserSig");
        final TLSOpenAccountInfo tLSOpenAccountInfo = this.openAccountInfo;
        this.openAccountInfo = GetLocalOpenAccountInfo;
        TLSOpenAccountLogin(new TLSExchangeTicketListener() {
            public void OnExchangeTicketSuccess(TLSUserInfo tLSUserInfo) {
                tLSRefreshUserSigListener.OnRefreshUserSigSuccess(tLSUserInfo);
                TLSHelper.this.openAccountInfo = tLSOpenAccountInfo;
            }

            public void OnExchangeTicketFail(TLSErrInfo tLSErrInfo) {
                tLSRefreshUserSigListener.OnRefreshUserSigFail(tLSErrInfo);
                TLSHelper.this.openAccountInfo = tLSOpenAccountInfo;
            }

            public void OnExchangeTicketTimeout(TLSErrInfo tLSErrInfo) {
                tLSRefreshUserSigListener.OnRefreshUserSigTimeout(tLSErrInfo);
                TLSHelper.this.openAccountInfo = tLSOpenAccountInfo;
            }
        });
        return -1001;
    }

    private int GetStWithoutPasswd(String str, long j, final Object obj) {
        if (str == null) {
            return -1017;
        }
        final TLSUserInfo tLSUserInfo = new TLSUserInfo();
        final req_global clone = this.mG.getClone(0);
        final String str2 = str;
        final long j2 = j;
        new WorkThread(Looper.myLooper(), new Worker() {
            public int work() {
                int i;
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                QLog.i("wtlogin login with GetStWithoutPasswd:user:" + str2 + " dwSrcAppid:" + TLSHelper.this.mSdkAppid + " dwDstAppid:" + j2 + " dwMainSigMap:" + TLSHelper.this.mMainSigMap + " dwSubDstAppid:" + TLSHelper.this.mSubAppid + " Seq:" + clone._seq + " ...");
                int i2 = util.get_saved_network_type(TLSHelper.this.mContext);
                req_global._network_type = util.get_network_type(TLSHelper.this.mContext);
                if (i2 != req_global._network_type) {
                    util.set_net_retry_type(TLSHelper.this.mContext, 0);
                    util.save_network_type(TLSHelper.this.mContext, req_global._network_type);
                }
                req_global._apn = util.get_apn_string(TLSHelper.this.mContext).getBytes();
                tLSUserInfo.identifier = str2;
                req_global tencent_tls_request_req_global = clone;
                String str = str2;
                tencent_tls_request_req_global._userid = str;
                tencent_tls_request_async_context._userid = str;
                clone._uin = 0;
                tencent_tls_request_async_context._tinyid = 0;
                tencent_tls_request_async_context._src_appid = TLSHelper.this.mSdkAppid;
                tencent_tls_request_async_context._appid = j2;
                tencent_tls_request_async_context._sub_appid = TLSHelper.this.mSubAppid;
                tencent_tls_request_async_context._main_sigmap = TLSHelper.this.mMainSigMap;
                tencent_tls_request_async_context._last_err_msg = new TLSErrInfo();
                long tinyId = clone.getTinyId(str2);
                if (tinyId == 0) {
                    QLog.i("user:" + str2 + " have not found uin record.");
                    i = -1003;
                } else {
                    clone._uin = tinyId;
                    byte[] access$500 = TLSHelper.this.GetA1ByAccount(str2, TLSHelper.this.mSdkAppid);
                    byte[] access$600 = TLSHelper.this.GetNoPicSigByAccount(str2, TLSHelper.this.mSdkAppid);
                    if (access$500 == null || access$500.length <= 0 || access$600 == null || access$600.length <= 0) {
                        i = -1004;
                    } else {
                        QLog.i("user:" + str2 + " exchange A2 from A1.", clone._uin);
                        tencent_tls_request_async_context._tmp_pwd = access$500;
                        tencent_tls_request_async_context._tmp_no_pic_sig = access$600;
                        i = new req_tgtgt_nopicsig(clone).make_request(j2, 1, clone._uin, 0, req_global._ip_addr, access$500, access$600, TLSHelper.this.mMiscBitmap, TLSHelper.this.mSubSigMap, null, TLSHelper.this.mMainSigMap, TLSHelper.this.mSubAppid, 1, req_global._pic_type, 0, 0, 1, TLSHelper.this.mSdkAppid, tLSUserInfo);
                    }
                    if (i == 0) {
                        SigInfo sigInfo = clone.get_siginfo(tinyId, j2);
                        if (sigInfo == null) {
                            i = -1004;
                        } else {
                            tLSUserInfo.get_clone(sigInfo);
                        }
                    }
                }
                clone.close_connect();
                QLog.i("wtlogin login with GetStWithoutPasswd:user:" + str2 + " dwSrcAppid:" + TLSHelper.this.mSdkAppid + " dwDstAppid:" + j2 + " dwMainSigMap:0x" + Integer.toHexString(TLSHelper.this.mMainSigMap) + " dwSubDstAppid:" + TLSHelper.this.mSubAppid + " Seq:" + clone._seq + " ret=" + i, clone._uin);
                return i;
            }
        }, new When() {
            public void done(int i) {
                TLSErrInfo tLSErrInfo = req_global.get_async_data(clone._seq)._last_err_msg;
                req_global.remove_async_data(clone._seq);
                TLSRefreshUserSigListener tLSRefreshUserSigListener = (TLSRefreshUserSigListener) obj;
                if (i == 0) {
                    tLSRefreshUserSigListener.OnRefreshUserSigSuccess(tLSUserInfo);
                } else if (i == -1000) {
                    tLSRefreshUserSigListener.OnRefreshUserSigTimeout(tLSErrInfo);
                } else {
                    tLSErrInfo.ErrCode = i;
                    tLSRefreshUserSigListener.OnRefreshUserSigFail(tLSErrInfo);
                }
            }
        }).start();
        return -1001;
    }

    private int GetStWithPasswd(String str, byte[] bArr, boolean z, final Object obj) {
        if (str == null) {
            return -1017;
        }
        final req_global clone = this.mG.getClone(this.mAsyncSeq);
        final TLSUserInfo tLSUserInfo = new TLSUserInfo();
        final byte[] bArr2 = bArr;
        final String str2 = str;
        final boolean z2 = z;
        new WorkThread(Looper.myLooper(), new Worker() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public int work() {
                /*
                r28 = this;
                r0 = r28;
                r2 = r2;
                r0 = r28;
                r3 = r3;
                r4 = r3._seq;
                r15 = tencent.tls.request.req_global.get_async_data(r4);
                r3 = new java.lang.StringBuilder;
                r3.<init>();
                r4 = "wtlogin login with GetStWithPasswd:user:";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = r4;
                r3 = r3.append(r4);
                r4 = " accType: ";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = tencent.tls.platform.TLSHelper.this;
                r4 = r4.mAccType;
                r3 = r3.append(r4);
                r4 = " dwAppid:";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = tencent.tls.platform.TLSHelper.this;
                r4 = r4.mSdkAppid;
                r3 = r3.append(r4);
                r4 = " dwMainSigMap:0x";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = tencent.tls.platform.TLSHelper.this;
                r4 = r4.mMainSigMap;
                r4 = java.lang.Integer.toHexString(r4);
                r3 = r3.append(r4);
                r4 = " dwSubAppid:";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = tencent.tls.platform.TLSHelper.this;
                r4 = r4.mSubAppid;
                r3 = r3.append(r4);
                r4 = " Seq:";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = r3;
                r4 = r4._seq;
                r3 = r3.append(r4);
                r4 = " ...";
                r3 = r3.append(r4);
                r3 = r3.toString();
                tencent.tls.report.QLog.i(r3);
                if (r2 == 0) goto L_0x0096;
            L_0x0093:
                r3 = r2.length;
                if (r3 != 0) goto L_0x009c;
            L_0x0096:
                r2 = r15._mpasswd;
                r2 = r2.getBytes();
            L_0x009c:
                r3 = "";
                tencent.tls.account.acc_status.mpasswd = r3;
                r4 = tencent.tls.account.acc_status.msalt;
                r6 = 0;
                r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
                if (r3 == 0) goto L_0x00b1;
            L_0x00a9:
                r4 = tencent.tls.account.acc_status.msalt;
                r15._msalt = r4;
                r4 = 0;
                tencent.tls.account.acc_status.msalt = r4;
            L_0x00b1:
                r3 = r2.length;
                r4 = 16;
                if (r3 <= r4) goto L_0x00bd;
            L_0x00b6:
                r3 = 0;
                r4 = 0;
                r5 = 16;
                java.lang.System.arraycopy(r2, r3, r2, r4, r5);
            L_0x00bd:
                r0 = r28;
                r3 = tencent.tls.platform.TLSHelper.this;
                r3 = r3.mContext;
                r3 = tencent.tls.tools.util.get_saved_network_type(r3);
                r0 = r28;
                r4 = tencent.tls.platform.TLSHelper.this;
                r4 = r4.mContext;
                r4 = tencent.tls.tools.util.get_network_type(r4);
                tencent.tls.request.req_global._network_type = r4;
                r4 = tencent.tls.request.req_global._network_type;
                if (r3 == r4) goto L_0x00f4;
            L_0x00db:
                r0 = r28;
                r3 = tencent.tls.platform.TLSHelper.this;
                r3 = r3.mContext;
                r4 = 0;
                tencent.tls.tools.util.set_net_retry_type(r3, r4);
                r0 = r28;
                r3 = tencent.tls.platform.TLSHelper.this;
                r3 = r3.mContext;
                r4 = tencent.tls.request.req_global._network_type;
                tencent.tls.tools.util.save_network_type(r3, r4);
            L_0x00f4:
                r0 = r28;
                r3 = tencent.tls.platform.TLSHelper.this;
                r3 = r3.mContext;
                r3 = tencent.tls.tools.util.get_apn_string(r3);
                r3 = r3.getBytes();
                tencent.tls.request.req_global._apn = r3;
                r0 = r28;
                r3 = r5;
                r0 = r28;
                r4 = r4;
                r3.identifier = r4;
                r0 = r28;
                r3 = r3;
                r0 = r28;
                r4 = r4;
                r3._userid = r4;
                r15._userid = r4;
                r0 = r28;
                r3 = r3;
                r4 = 0;
                r3._uin = r4;
                r15._tinyid = r4;
                r0 = r28;
                r3 = tencent.tls.platform.TLSHelper.this;
                r4 = r3.mSdkAppid;
                r15._src_appid = r4;
                r0 = r28;
                r3 = tencent.tls.platform.TLSHelper.this;
                r4 = r3.mSdkAppid;
                r15._appid = r4;
                r0 = r28;
                r3 = tencent.tls.platform.TLSHelper.this;
                r4 = r3.mSubAppid;
                r15._sub_appid = r4;
                r0 = r28;
                r3 = tencent.tls.platform.TLSHelper.this;
                r3 = r3.mMainSigMap;
                r15._main_sigmap = r3;
                r3 = new tencent.tls.platform.TLSErrInfo;
                r3.<init>();
                r15._last_err_msg = r3;
                r3 = r2.length;
                if (r3 <= 0) goto L_0x0161;
            L_0x0158:
                r2 = tencent.tls.tools.MD5.toMD5Byte(r2);
                r15._tmp_pwd = r2;
                r2 = 0;
                r15._tmp_pwd_type = r2;
            L_0x0161:
                r0 = r28;
                r2 = r4;
                r2 = r2.length();
                r3 = tencent.tls.tools.util.MAX_NAME_LEN;
                if (r2 <= r3) goto L_0x01ed;
            L_0x016d:
                r2 = -1008; // 0xfffffffffffffc10 float:NaN double:NaN;
            L_0x016f:
                r0 = r28;
                r3 = r3;
                r3.close_connect();
                r3 = new java.lang.StringBuilder;
                r3.<init>();
                r4 = "wtlogin login with GetStWithPasswd:user:";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = r4;
                r3 = r3.append(r4);
                r4 = " dwAppid:";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = tencent.tls.platform.TLSHelper.this;
                r4 = r4.mSdkAppid;
                r3 = r3.append(r4);
                r4 = " dwMainSigMap:";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = tencent.tls.platform.TLSHelper.this;
                r4 = r4.mMainSigMap;
                r3 = r3.append(r4);
                r4 = " dwSubAppid:";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = tencent.tls.platform.TLSHelper.this;
                r4 = r4.mSubAppid;
                r3 = r3.append(r4);
                r4 = " Seq:";
                r3 = r3.append(r4);
                r0 = r28;
                r4 = r3;
                r4 = r4._seq;
                r3 = r3.append(r4);
                r4 = " ret=";
                r3 = r3.append(r4);
                r3 = r3.append(r2);
                r3 = r3.toString();
                r0 = r28;
                r4 = r3;
                r4 = r4._uin;
                tencent.tls.report.QLog.i(r3, r4);
                return r2;
            L_0x01ed:
                r2 = r15._msalt;
                r4 = 0;
                r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                if (r2 != 0) goto L_0x0229;
            L_0x01f5:
                r3 = new tencent.tls.request.req_getsalt;
                r0 = r28;
                r2 = r3;
                r3.<init>(r2);
                r0 = r28;
                r2 = tencent.tls.platform.TLSHelper.this;
                r4 = r2.mSdkAppid;
                r0 = r28;
                r2 = tencent.tls.platform.TLSHelper.this;
                r6 = r2.mSubAppid;
                r0 = r28;
                r2 = tencent.tls.platform.TLSHelper.this;
                r8 = r2.mMainSigMap;
                r0 = r28;
                r9 = r4;
                r10 = tencent.tls.request.req_global._pic_type;
                r11 = 0;
                r12 = 0;
                r13 = 1;
                r0 = r28;
                r14 = r5;
                r2 = r3.make_request(r4, r6, r8, r9, r10, r11, r12, r13, r14);
                if (r2 != 0) goto L_0x016f;
            L_0x0229:
                r2 = new java.lang.StringBuilder;
                r2.<init>();
                r3 = "get salt: ";
                r3 = r2.append(r3);
                r4 = r15._msalt;
                r6 = 0;
                r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
                if (r2 == 0) goto L_0x02f4;
            L_0x023d:
                r2 = 1;
            L_0x023e:
                r2 = r3.append(r2);
                r2 = r2.toString();
                tencent.tls.report.QLog.i(r2);
                r2 = r15._tmp_pwd_type;
                if (r2 == 0) goto L_0x02f7;
            L_0x024d:
                r2 = new java.lang.StringBuilder;
                r2.<init>();
                r3 = "user:";
                r2 = r2.append(r3);
                r0 = r28;
                r3 = r4;
                r2 = r2.append(r3);
                r3 = " login with saved A1.";
                r2 = r2.append(r3);
                r2 = r2.toString();
                r0 = r28;
                r3 = r3;
                r4 = r3._uin;
                tencent.tls.report.QLog.i(r2, r4);
                r2 = new tencent.tls.request.req_TGTGT;
                r0 = r28;
                r3 = r3;
                r2.<init>(r3);
                r0 = r28;
                r3 = tencent.tls.platform.TLSHelper.this;
                r3 = r3.mSdkAppid;
                r0 = r28;
                r5 = tencent.tls.platform.TLSHelper.this;
                r5 = r5.mSubAppid;
                r7 = 1;
                r0 = r28;
                r8 = r3;
                r8 = r8._uin;
                r10 = 0;
                r11 = tencent.tls.request.req_global._ip_addr;
                r12 = r15._tmp_pwd;
                r13 = r15._tmp_no_pic_sig;
                r0 = r28;
                r14 = tencent.tls.platform.TLSHelper.this;
                r14 = r14.mMiscBitmap;
                r0 = r28;
                r15 = tencent.tls.platform.TLSHelper.this;
                r15 = r15.mSubSigMap;
                r16 = 0;
                r0 = r28;
                r0 = tencent.tls.platform.TLSHelper.this;
                r17 = r0;
                r17 = r17.mMainSigMap;
                r0 = r28;
                r0 = tencent.tls.platform.TLSHelper.this;
                r18 = r0;
                r18 = r18.mSubAppid;
                r20 = 1;
                r21 = tencent.tls.request.req_global._pic_type;
                r22 = 0;
                r23 = 0;
                r24 = 1;
                r0 = r28;
                r0 = r5;
                r25 = r0;
                r2 = r2.make_request(r3, r5, r7, r8, r10, r11, r12, r13, r14, r15, r16, r17, r18, r20, r21, r22, r23, r24, r25);
            L_0x02d6:
                if (r2 != 0) goto L_0x016f;
            L_0x02d8:
                r0 = r28;
                r3 = r3;
                r0 = r28;
                r4 = r3;
                r4 = r4._uin;
                r0 = r28;
                r6 = tencent.tls.platform.TLSHelper.this;
                r6 = r6.mSdkAppid;
                r3 = r3.get_siginfo(r4, r6);
                if (r3 != 0) goto L_0x03a9;
            L_0x02f0:
                r2 = -1004; // 0xfffffffffffffc14 float:NaN double:NaN;
                goto L_0x016f;
            L_0x02f4:
                r2 = 0;
                goto L_0x023e;
            L_0x02f7:
                r2 = new java.lang.StringBuilder;
                r2.<init>();
                r3 = "user:";
                r2 = r2.append(r3);
                r0 = r28;
                r3 = r4;
                r2 = r2.append(r3);
                r3 = " login with input password.";
                r2 = r2.append(r3);
                r2 = r2.toString();
                r0 = r28;
                r3 = r3;
                r4 = r3._uin;
                tencent.tls.report.QLog.i(r2, r4);
                r2 = 4;
                r12 = new byte[r2];
                r2 = java.lang.System.currentTimeMillis();
                r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
                r2 = r2 / r4;
                r4 = tencent.tls.request.req_global._l_init_time;
                r2 = r2 + r4;
                r4 = 0;
                r6 = tencent.tls.account.acc_status.regtime;
                r8 = 0;
                r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
                if (r5 != 0) goto L_0x03a6;
            L_0x0335:
                tencent.tls.tools.util.int64_to_buf32(r12, r4, r2);
                r2 = 0;
                tencent.tls.account.acc_status.regtime = r2;
                r2 = new tencent.tls.request.req_TGTGT;
                r0 = r28;
                r3 = r3;
                r2.<init>(r3);
                r0 = r28;
                r3 = tencent.tls.platform.TLSHelper.this;
                r3 = r3.mSdkAppid;
                r0 = r28;
                r5 = tencent.tls.platform.TLSHelper.this;
                r5 = r5.mSubAppid;
                r7 = 1;
                r0 = r28;
                r8 = r3;
                r8 = r8._uin;
                r10 = 0;
                r11 = tencent.tls.request.req_global._ip_addr;
                r13 = 0;
                r14 = r15._tmp_pwd;
                r0 = r28;
                r15 = r6;
                r0 = r28;
                r0 = tencent.tls.platform.TLSHelper.this;
                r16 = r0;
                r16 = r16.mMiscBitmap;
                r0 = r28;
                r0 = tencent.tls.platform.TLSHelper.this;
                r17 = r0;
                r17 = r17.mSubSigMap;
                r18 = 0;
                r0 = r28;
                r0 = tencent.tls.platform.TLSHelper.this;
                r19 = r0;
                r19 = r19.mMainSigMap;
                r0 = r28;
                r0 = tencent.tls.platform.TLSHelper.this;
                r20 = r0;
                r20 = r20.mSubAppid;
                r22 = 1;
                r23 = tencent.tls.request.req_global._pic_type;
                r24 = 0;
                r25 = 0;
                r26 = 1;
                r0 = r28;
                r0 = r5;
                r27 = r0;
                r2 = r2.make_request(r3, r5, r7, r8, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r22, r23, r24, r25, r26, r27);
                goto L_0x02d6;
            L_0x03a6:
                r2 = tencent.tls.account.acc_status.regtime;
                goto L_0x0335;
            L_0x03a9:
                r0 = r28;
                r4 = r5;
                r4.get_clone(r3);
                goto L_0x016f;
                */
                throw new UnsupportedOperationException("Method not decompiled: tencent.tls.platform.TLSHelper.4.work():int");
            }
        }, new When() {
            public void done(int i) {
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                TLSErrInfo tLSErrInfo = tencent_tls_request_async_context._last_err_msg;
                if (obj instanceof TLSSmsLoginListener) {
                    TLSSmsLoginListener tLSSmsLoginListener = (TLSSmsLoginListener) obj;
                    if (i == 0) {
                        tLSSmsLoginListener.OnSmsLoginSuccess(tLSUserInfo);
                    } else if (i == -1000) {
                        tLSSmsLoginListener.OnSmsLoginTimeout(tLSErrInfo);
                    } else {
                        tLSErrInfo.ErrCode = i;
                        tLSSmsLoginListener.OnSmsLoginFail(tLSErrInfo);
                    }
                } else if (obj instanceof TLSPwdLoginListener) {
                    TLSPwdLoginListener tLSPwdLoginListener = (TLSPwdLoginListener) obj;
                    if (i == 0) {
                        tLSPwdLoginListener.OnPwdLoginSuccess(tLSUserInfo);
                    } else if (i == 2) {
                        TLSHelper.this.mAsyncSeq = clone._seq;
                        tLSPwdLoginListener.OnPwdLoginNeedImgcode(tencent_tls_request_async_context._t105.get_pic(), tLSErrInfo);
                    } else if (i == -1000) {
                        tLSPwdLoginListener.OnPwdLoginTimeout(tLSErrInfo);
                    } else {
                        tLSErrInfo.ErrCode = i;
                        tLSPwdLoginListener.OnPwdLoginFail(tLSErrInfo);
                    }
                } else if (obj instanceof TLSGuestLoginListener) {
                    TLSGuestLoginListener tLSGuestLoginListener = (TLSGuestLoginListener) obj;
                    if (i == 0) {
                        clone.put_guest(clone._userid, clone._uin);
                        TLSHelper.guest = clone._userid;
                        tLSGuestLoginListener.OnGuestLoginSuccess(tLSUserInfo);
                    } else if (i == -1000) {
                        tLSGuestLoginListener.OnGuestLoginTimeout(tLSErrInfo);
                    } else {
                        tLSGuestLoginListener.OnGuestLoginFail(tLSErrInfo);
                    }
                } else if (obj instanceof TLSSSOGuestLoginListener) {
                    TLSSSOGuestLoginListener tLSSSOGuestLoginListener = (TLSSSOGuestLoginListener) obj;
                    if (i == 0) {
                        clone.put_sso_guest(clone._userid, clone._uin);
                        TLSHelper.ssoGuest = clone._userid;
                        tLSSSOGuestLoginListener.OnGuestLoginSuccess(tLSUserInfo);
                    } else if (i == -1000) {
                        tLSSSOGuestLoginListener.OnGuestLoginTimeout(tLSErrInfo);
                    } else {
                        tLSSSOGuestLoginListener.OnGuestLoginFail(tLSErrInfo);
                    }
                }
            }
        }).start();
        return -1001;
    }

    public int TLSSmsLogin(String str, TLSSmsLoginListener tLSSmsLoginListener) {
        QLog.i("user:" + str + " TLSSmsLogin ..." + this.mSubAppid);
        String str2 = acc_status.mpasswd;
        if (str2.length() > 0) {
            this.mAsyncSeq = 0;
        }
        QLog.i("has mpasswd? " + str2.length());
        return GetStWithPasswd(str, str2.getBytes(), true, tLSSmsLoginListener);
    }

    public int TLSPwdLogin(String str, byte[] bArr, TLSPwdLoginListener tLSPwdLoginListener) {
        QLog.i("user:" + str + " TLSPwdLogin ..." + this.mSdkAppid);
        this.mAsyncSeq = 0;
        return GetStWithPasswd(str, bArr, false, tLSPwdLoginListener);
    }

    public int TLSGuestLogin(final TLSGuestLoginListener tLSGuestLoginListener) {
        TLSGuestReg(new TLSGuestRegListener() {
            public void OnGuestRegSuccess(TLSUserInfo tLSUserInfo) {
                TLSHelper.this.clearUserInfo(TLSHelper.this.getGuestIdentifier());
                QLog.i("user:" + tLSUserInfo.identifier + " TLSGuestLogin ..." + TLSHelper.this.mSdkAppid);
                TLSHelper.this.mAsyncSeq = 0;
                TLSHelper.this.GetStWithPasswd(tLSUserInfo.identifier, acc_status.mpasswd.getBytes(), false, tLSGuestLoginListener);
            }

            public void OnGuestRegFail(TLSErrInfo tLSErrInfo) {
                tLSGuestLoginListener.OnGuestLoginFail(tLSErrInfo);
            }

            public void OnGuestRegTimeout(TLSErrInfo tLSErrInfo) {
                tLSGuestLoginListener.OnGuestLoginTimeout(tLSErrInfo);
            }
        });
        return -1001;
    }

    public int TLSSSOGuestLogin(final TLSSSOGuestLoginListener tLSSSOGuestLoginListener) {
        TLSSSOGuestReg(new TLSSSORegListener() {
            public void OnGuestRegSuccess(TLSUserInfo tLSUserInfo) {
                TLSHelper.this.clearUserInfo(TLSHelper.this.getSSOGuestIdentifier());
                QLog.i("user:" + tLSUserInfo.identifier + " TLSGuestLogin ..." + TLSHelper.this.mSdkAppid);
                TLSHelper.this.mAsyncSeq = 0;
                TLSHelper.this.GetStWithPasswd(tLSUserInfo.identifier, acc_status.mpasswd.getBytes(), false, tLSSSOGuestLoginListener);
            }

            public void OnGuestRegFail(TLSErrInfo tLSErrInfo) {
                tLSSSOGuestLoginListener.OnGuestLoginFail(tLSErrInfo);
            }

            public void OnGuestRegTimeout(TLSErrInfo tLSErrInfo) {
                tLSSSOGuestLoginListener.OnGuestLoginTimeout(tLSErrInfo);
            }
        });
        return -1001;
    }

    public int TLSSmsLoginAskCode(final String str, final TLSSmsLoginListener tLSSmsLoginListener) {
        acc_status.msalt = 0;
        acc_status.mpasswd = "";
        if (str == null) {
            return -1017;
        }
        final TLSUserInfo tLSUserInfo = new TLSUserInfo();
        final req_global clone = this.mG.getClone(0);
        this.mAsyncSeq = clone._seq;
        new WorkThread(Looper.myLooper(), new Worker() {
            public int work() {
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                clone._userid = str;
                QLog.i("user:" + str + " Seq:" + clone._seq + " TLSSmsLoginAskCode ...");
                tencent_tls_request_async_context._last_err_msg = new TLSErrInfo();
                int make_request = new req_smslogin_ask(clone).make_request(TLSHelper.this.mSdkAppid, TLSHelper.this.mSubAppid, TLSHelper.this.DummyGetSig, str, TLSHelper.this.mMiscBitmap, TLSHelper.this.mSubSigMap, tLSUserInfo);
                QLog.i("user:" + clone._userid + " Seq:" + clone._seq + " TLSSmsLoginAskCode ret=" + (make_request > 0 ? Integer.toHexString(make_request) : Integer.valueOf(make_request)), clone._uin);
                return make_request;
            }
        }, new When() {
            public void done(int i) {
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                TLSErrInfo tLSErrInfo = tencent_tls_request_async_context._last_err_msg;
                if (i == 0) {
                    tLSSmsLoginListener.OnSmsLoginAskCodeSuccess(tencent_tls_request_async_context._smslogin_reask, tencent_tls_request_async_context._smslogin_expire);
                } else if (i == -1000) {
                    tLSSmsLoginListener.OnSmsLoginTimeout(tLSErrInfo);
                } else {
                    tLSSmsLoginListener.OnSmsLoginFail(tLSErrInfo);
                }
            }
        }).start();
        return -1001;
    }

    public int TLSSmsLoginReaskCode(final String str, final TLSSmsLoginListener tLSSmsLoginListener) {
        if (str == null || str.length() == 0) {
            return -1017;
        }
        final TLSUserInfo tLSUserInfo = new TLSUserInfo();
        final req_global clone = this.mG.getClone(this.mAsyncSeq);
        new WorkThread(Looper.myLooper(), new Worker() {
            public int work() {
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                QLog.i("user:" + str + " Seq:" + clone._seq + " TLSSmsLoginReaskCode ...");
                clone._userid = str;
                tencent_tls_request_async_context._last_err_msg = new TLSErrInfo();
                int make_request = new req_smslogin_reask(clone).make_request(TLSHelper.this.mMiscBitmap, TLSHelper.this.mSubSigMap, null, tLSUserInfo);
                QLog.i("user:" + clone._userid + " Seq:" + clone._seq + " TLSSmsLoginReaskCode ret=" + (make_request > 0 ? Integer.toHexString(make_request) : Integer.valueOf(make_request)));
                return make_request;
            }
        }, new When() {
            public void done(int i) {
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                TLSErrInfo tLSErrInfo = tencent_tls_request_async_context._last_err_msg;
                if (i == 0) {
                    tLSSmsLoginListener.OnSmsLoginReaskCodeSuccess(tencent_tls_request_async_context._smslogin_reask, tencent_tls_request_async_context._smslogin_expire);
                } else if (i == -1000) {
                    tLSSmsLoginListener.OnSmsLoginTimeout(tLSErrInfo);
                } else {
                    tLSSmsLoginListener.OnSmsLoginFail(tLSErrInfo);
                }
            }
        }).start();
        return -1001;
    }

    public int TLSPwdLoginReaskImgcode(final TLSPwdLoginListener tLSPwdLoginListener) {
        TLSUserInfo tLSUserInfo = new TLSUserInfo();
        final req_global clone = this.mG.getClone(this.mAsyncSeq);
        new WorkThread(Looper.myLooper(), new Worker() {
            public int work() {
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                QLog.i("user:" + clone._userid + " Seq:" + clone._seq + " TLSPwdLoginReaskImgcode ...");
                clone._userid = tencent_tls_request_async_context._userid;
                clone._uin = tencent_tls_request_async_context._tinyid;
                tencent_tls_request_async_context._last_err_msg = new TLSErrInfo();
                int make_request = new req_imgcode_reask(clone).make_request();
                QLog.i("user:" + clone._userid + " Seq:" + clone._seq + " TLSPwdLoginReaskImgcode ret=" + (make_request > 0 ? Integer.toHexString(make_request) : Integer.valueOf(make_request)));
                return make_request;
            }
        }, new When() {
            public void done(int i) {
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                TLSErrInfo tLSErrInfo = tencent_tls_request_async_context._last_err_msg;
                if (i == 2) {
                    TLSHelper.this.mAsyncSeq = clone._seq;
                    tLSPwdLoginListener.OnPwdLoginReaskImgcodeSuccess(tencent_tls_request_async_context._t105.get_pic());
                } else if (i == -1000) {
                    tLSPwdLoginListener.OnPwdLoginTimeout(tLSErrInfo);
                } else {
                    tLSPwdLoginListener.OnPwdLoginFail(tLSErrInfo);
                }
            }
        }).start();
        return -1001;
    }

    public int TLSSmsLoginVerifyCode(final String str, final TLSSmsLoginListener tLSSmsLoginListener) {
        if (str == null || str.length() == 0) {
            return -1017;
        }
        final TLSUserInfo tLSUserInfo = new TLSUserInfo();
        final req_global clone = this.mG.getClone(this.mAsyncSeq);
        new WorkThread(Looper.myLooper(), new Worker() {
            public int work() {
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                QLog.i("user:" + clone._userid + " code:" + str + " Seq:" + clone._seq + " TLSSmsLoginVerifyCode ...");
                tencent_tls_request_async_context._last_err_msg = new TLSErrInfo();
                tencent_tls_request_async_context._mpasswd = util.get_mpasswd();
                int make_request = new req_smslogin_verify(clone).make_request(str, TLSHelper.this.mMiscBitmap, TLSHelper.this.mSubSigMap, null, tLSUserInfo);
                QLog.i("user:" + clone._userid + " code:" + str + " Seq:" + clone._seq + " TLSSmsLoginVerifyCode ret=" + Integer.toHexString(make_request));
                return make_request;
            }
        }, new When() {
            public void done(int i) {
                TLSErrInfo tLSErrInfo = req_global.get_async_data(clone._seq)._last_err_msg;
                if (i == 0) {
                    tLSSmsLoginListener.OnSmsLoginVerifyCodeSuccess();
                } else if (i == -1000) {
                    tLSSmsLoginListener.OnSmsLoginTimeout(tLSErrInfo);
                } else {
                    tLSSmsLoginListener.OnSmsLoginFail(tLSErrInfo);
                }
            }
        }).start();
        return -1001;
    }

    public int TLSPwdLoginVerifyImgcode(final String str, final TLSPwdLoginListener tLSPwdLoginListener) {
        final TLSUserInfo tLSUserInfo = new TLSUserInfo();
        final req_global clone = this.mG.getClone(this.mAsyncSeq);
        new WorkThread(Looper.myLooper(), new Worker() {
            public int work() {
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                QLog.i("user:" + clone._userid + " Seq:" + clone._seq + " TLSPwdLoginVerifyImgcode ...");
                TLSUserInfo tLSUserInfo = tLSUserInfo;
                req_global tencent_tls_request_req_global = clone;
                String str = tencent_tls_request_async_context._userid;
                tencent_tls_request_req_global._userid = str;
                tLSUserInfo.identifier = str;
                clone._uin = tencent_tls_request_async_context._tinyid;
                tencent_tls_request_async_context._last_err_msg = new TLSErrInfo();
                int make_request = new req_imgcode_verify(clone).make_request(str);
                QLog.i("user:" + clone._userid + " Seq:" + clone._seq + " TLSPwdLoginVerifyImgcode ret=" + (make_request > 0 ? Integer.toHexString(make_request) : Integer.valueOf(make_request)));
                return make_request;
            }
        }, new When() {
            public void done(int i) {
                async_context tencent_tls_request_async_context = req_global.get_async_data(clone._seq);
                TLSErrInfo tLSErrInfo = tencent_tls_request_async_context._last_err_msg;
                if (i == 0) {
                    tLSPwdLoginListener.OnPwdLoginSuccess(tLSUserInfo);
                } else if (i == 2) {
                    TLSHelper.this.mAsyncSeq = clone._seq;
                    tLSPwdLoginListener.OnPwdLoginNeedImgcode(tencent_tls_request_async_context._t105.get_pic(), tLSErrInfo);
                } else if (i == -1000) {
                    tLSPwdLoginListener.OnPwdLoginTimeout(tLSErrInfo);
                } else {
                    tLSPwdLoginListener.OnPwdLoginFail(tLSErrInfo);
                }
            }
        }).start();
        return -1001;
    }

    public void clearUserInfo(String str) {
        QLog.i("user:" + str + " sdkAppid:" + this.mSdkAppid + " clearUserInfo");
        if (str != null && str.length() > 0) {
            if (str.equals(guest)) {
                guest = null;
            }
            long tinyId = this.mG.getTinyId(str);
            if (tinyId != 0) {
                this.mG.clear_sig(tinyId, this.mSdkAppid);
            }
            this.mG.remove_account(str);
        }
    }

    public byte[] getGUID() {
        if (req_global._IMEI == null || req_global._IMEI.length <= 0) {
            return null;
        }
        Object obj = new byte[req_global._IMEI.length];
        System.arraycopy(req_global._IMEI, 0, obj, 0, req_global._IMEI.length);
        return obj;
    }

    public int TLSExchangeTicket(String str, String str2, TLSExchangeTicketListener tLSExchangeTicketListener) {
        return TLSExchangeTicket(this.mSdkAppid, str, str2, tLSExchangeTicketListener);
    }

    public int TLSExchangeTicket(long j, String str, String str2, TLSExchangeTicketListener tLSExchangeTicketListener) {
        if (util.checkInvalid(str) || util.checkInvalid(str2)) {
            return -1017;
        }
        this.mSdkAppid = j;
        Oidb0xa0b_request oidb0xa0b_request = new Oidb0xa0b_request(2);
        TransReqContext transReqContext = new TransReqContext(tLSExchangeTicketListener);
        transReqContext.set_subcmd(oidb0xa0b_request.getCmd());
        transReqContext._body = oidb0xa0b_request.get_request(this.mSdkAppid, 0, str, null, str2);
        return RequestTransport(null, 0, str, str2, 6269, transReqContext);
    }

    public void setOpenAccountInfo(int i, String str, String str2, String str3) {
        this.openAccountInfo = new TLSOpenAccountInfo(i, str, str2, str3);
        this.openAccountInfo.sdkAppid = this.mSdkAppid;
        this.openAccountInfo.userAccountType = this.mAccType;
    }

    public void setOpenAccountInfo(TLSOpenAccountInfo tLSOpenAccountInfo) {
        this.openAccountInfo = tLSOpenAccountInfo;
        this.openAccountInfo.sdkAppid = this.mSdkAppid;
        this.openAccountInfo.userAccountType = this.mAccType;
    }

    public TLSOpenAccountInfo getOpenAccountInfo() {
        return this.openAccountInfo;
    }

    public int TLSOpenAccountQuery(TLSOpenAccountQueryListener tLSOpenAccountQueryListener) {
        int i = this.openAccountInfo.openAccountType;
        String str = this.openAccountInfo.openid;
        String str2 = this.openAccountInfo.access_token;
        String str3 = this.openAccountInfo.openappid;
        if (i <= 0 || util.checkInvalid(str) || util.checkInvalid(str2)) {
            return -1017;
        }
        Oidb0x602_request oidb0x602_request = new Oidb0x602_request();
        TransReqContext transReqContext = new TransReqContext(tLSOpenAccountQueryListener);
        transReqContext.set_subcmd(oidb0x602_request.getCmd());
        transReqContext._body = oidb0x602_request.get_request((int) this.mSdkAppid, i, str3, str, str2);
        return RequestTransport(str3, i, str, str2, 6161, transReqContext);
    }

    public int TLSOpenAccountBind(String str, String str2, TLSOpenAccountBindListener tLSOpenAccountBindListener) {
        int i = this.openAccountInfo.openAccountType;
        String str3 = this.openAccountInfo.openid;
        String str4 = this.openAccountInfo.access_token;
        String str5 = this.openAccountInfo.openappid;
        this.openAccountInfo.identifer = str;
        this.openAccountInfo.usersig = str2;
        if (i <= 0 || util.checkInvalid(str) || util.checkInvalid(str2) || util.checkInvalid(str3) || util.checkInvalid(str4)) {
            return -1017;
        }
        Oidb0x601_request oidb0x601_request = new Oidb0x601_request();
        TransReqContext transReqContext = new TransReqContext(tLSOpenAccountBindListener);
        transReqContext.set_subcmd(oidb0x601_request.getCmd());
        transReqContext._body = oidb0x601_request.get_request((int) this.openAccountInfo.sdkAppid, this.openAccountInfo.userAccountType, str, str2, i, this.openAccountInfo.openappid, str3, str4);
        return RequestTransport(str5, i, str3, str4, 6161, transReqContext);
    }

    public int TLSOpenAccessToken(int i, String str, TLSOpenAccessTokenListener tLSOpenAccessTokenListener) {
        if (i <= 0 || util.checkInvalid(str)) {
            return -1017;
        }
        Oidb0x483_request oidb0x483_request = new Oidb0x483_request();
        TransReqContext transReqContext = new TransReqContext(tLSOpenAccessTokenListener);
        transReqContext.set_subcmd(oidb0x483_request.getCmd());
        transReqContext._body = oidb0x483_request.get_request(this.mSdkAppid, i, str);
        return RequestTransport(null, i, null, null, 5970, transReqContext);
    }

    public int TLSSmsRegAskCodeWithOA(String str, TLSSmsRegListener tLSSmsRegListener) {
        QLog.i("TLSSmsRegAskCodeWithOA ..." + str);
        return askCode(str, 33, true, tLSSmsRegListener);
    }

    public int TLSPwdRegAskCodeWithOA(String str, TLSPwdRegListener tLSPwdRegListener) {
        QLog.i("TLSPwdRegAskCodeWithOA ..." + str);
        return askCode(str, 49, true, tLSPwdRegListener);
    }

    public int TLSStrAccRegWithOA(String str, String str2, TLSStrAccRegListener tLSStrAccRegListener) {
        if (str.length() == 0 || str.getBytes().length > 24) {
            return -1017;
        }
        if (str2.length() == 0 || str2.getBytes().length > 16 || str2.getBytes().length < 8) {
            return -1017;
        }
        if (this.openAccountInfo.checkInvalid()) {
            return -1017;
        }
        Object obj = 1;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != '.' && charAt != '_' && !Character.isLetterOrDigit(charAt)) {
                return -1017;
            }
            if (!Character.isDigit(charAt)) {
                obj = null;
            }
        }
        if (obj != null) {
            return -1017;
        }
        acc_ask_code tencent_tls_account_acc_ask_code = new acc_ask_code(97);
        TransReqContext transReqContext = new TransReqContext(tLSStrAccRegListener);
        this.mRegStatus.userID = str;
        this.mRegStatus.password = str2;
        transReqContext.set_register_req();
        transReqContext.set_subcmd(tencent_tls_account_acc_ask_code.get_cmd());
        transReqContext._body = tencent_tls_account_acc_ask_code.get_request(this.mAccType, str, req_global._apk_id, this.mAppVer, this.mCountry, this.mSdkAppid, req_global._IMEI, this.openAccountInfo);
        return RequestTransport(null, 0, str, null, (long) this.mRegStatus.role, transReqContext);
    }

    public int TLSOpenAccountLogin(TLSExchangeTicketListener tLSExchangeTicketListener) {
        if (this.openAccountInfo.checkInvalid()) {
            return -1017;
        }
        int i = this.openAccountInfo.openAccountType;
        String str = this.openAccountInfo.openid;
        String str2 = this.openAccountInfo.openappid;
        String str3 = this.openAccountInfo.access_token;
        Oidb0xa0b_request oidb0xa0b_request = new Oidb0xa0b_request(1);
        TransReqContext transReqContext = new TransReqContext(tLSExchangeTicketListener);
        transReqContext.set_subcmd(oidb0xa0b_request.getCmd());
        transReqContext._body = oidb0xa0b_request.get_request(this.mSdkAppid, i, str, str2, str3);
        return RequestTransport(str2, i, str, str3, 6269, transReqContext);
    }

    private int RequestTransport(String str, int i, String str2, String str3, long j, TransReqContext transReqContext) {
        final TLSUserInfo tLSUserInfo = new TLSUserInfo();
        tLSUserInfo.identifier = str2;
        final req_global clone = this.mG.getClone(0);
        final String str4 = str2;
        final long j2 = j;
        final TransReqContext transReqContext2 = transReqContext;
        final TransReqContext transReqContext3 = transReqContext;
        final TLSUserInfo tLSUserInfo2 = tLSUserInfo;
        final req_global tencent_tls_request_req_global = clone;
        final String str5 = str;
        final int i2 = i;
        final String str6 = str3;
        new WorkThread(Looper.myLooper(), new Worker() {
            public int work() {
                QLog.i("user:" + str4 + " sdkAppid:" + TLSHelper.this.mSdkAppid + " role:" + j2 + " Seq:" + clone._seq + " RequestTransport...");
                clone._userid = str4;
                int make_request = new req_transport(clone).make_request(0, transReqContext2, null, null, TLSHelper.this.mSdkAppid, j2, tLSUserInfo);
                clone.close_transport_connect();
                QLog.i("user:" + str4 + " sdkAppid:" + TLSHelper.this.mSdkAppid + " role:" + j2 + " Seq:" + clone._seq + " RequestTransport ret=" + make_request);
                return make_request;
            }
        }, new When() {
            public void done(int i) {
                int i2 = transReqContext3.get_subcmd();
                if (transReqContext3.is_register_req()) {
                    TLSHelper.this.OnTLSRequestRegister(transReqContext3, tLSUserInfo2, i);
                } else if (i2 == Oidb0x602_request.CMD) {
                    TLSHelper.this.OnTLSOpenAccountQuery(transReqContext3, i);
                } else if (i2 == Oidb0x601_request.CMD) {
                    TLSHelper.this.OnTLSOpenAccountBind(transReqContext3, i);
                } else if (i2 == Oidb0xa0b_request.CMD) {
                    TLSHelper.this.OnTLSExchangeTicket(transReqContext3, tencent_tls_request_req_global, str5, i2, str6, i);
                } else if (i2 == Oidb0x483_request.CMD) {
                    TLSHelper.this.OnTLSOpenAccessToken(transReqContext3, i);
                } else {
                    QLog.i("命令字不一致!");
                }
            }
        }).start();
        return -1001;
    }

    private void OnTLSOpenAccessToken(TransReqContext transReqContext, int i) {
        TLSOpenAccessTokenListener tLSOpenAccessTokenListener = (TLSOpenAccessTokenListener) transReqContext.listener;
        TLSErrInfo tLSErrInfo = new TLSErrInfo(i, "", I18nMsg.getMsg(MSG_TYPE.MSG_3));
        if (i != 0) {
            whenError(tLSErrInfo, tLSOpenAccessTokenListener);
            return;
        }
        Oidb0x483_response oidb0x483_response = new Oidb0x483_response(transReqContext.get_body());
        if (oidb0x483_response.getResult() != 0) {
            tLSErrInfo.ErrCode = oidb0x483_response.getResult();
            tLSErrInfo.Msg = "解析包出错";
            whenError(tLSErrInfo, tLSOpenAccessTokenListener);
        } else if (oidb0x483_response.getHeadResult() == 0) {
            TLSAccessTokenInfo tLSAccessTokenInfo = new TLSAccessTokenInfo();
            tLSAccessTokenInfo.access_token = oidb0x483_response.getAccessToken();
            tLSAccessTokenInfo.expireTime = oidb0x483_response.getExpireTime();
            tLSAccessTokenInfo.refresh_token = oidb0x483_response.getRefreshToken();
            tLSAccessTokenInfo.openid = oidb0x483_response.getOpenid();
            tLSAccessTokenInfo.scope = oidb0x483_response.getScope();
            tLSAccessTokenInfo.unionid = oidb0x483_response.getUnionid();
            tLSOpenAccessTokenListener.OnOpenAccessTokenSuccess(tLSAccessTokenInfo);
        } else {
            QLog.i("rsp.getHeadResult() = " + oidb0x483_response.getHeadResult());
            tLSErrInfo.ErrCode = oidb0x483_response.getHeadResult();
            tLSErrInfo.Msg = oidb0x483_response.getErrHint();
            tLSOpenAccessTokenListener.OnOpenAccessTokenFail(tLSErrInfo);
        }
    }

    private void OnTLSRequestRegister(TransReqContext transReqContext, TLSUserInfo tLSUserInfo, int i) {
        int i2 = transReqContext.get_subcmd();
        Object obj = transReqContext.listener;
        TLSErrInfo tLSErrInfo = new TLSErrInfo(i, "", I18nMsg.getMsg(MSG_TYPE.MSG_3));
        if (i != 0) {
            whenError(tLSErrInfo, obj);
            return;
        }
        int parse_checkvalid_rsp = acc_request.parse_checkvalid_rsp(i2, transReqContext.get_body(), this.mRegStatus);
        if (parse_checkvalid_rsp != 0) {
            tLSErrInfo.ErrCode = parse_checkvalid_rsp;
            whenError(tLSErrInfo, obj);
            return;
        }
        QLog.i("reg cmd:" + i2 + " ret:" + this.mRegStatus.sec_ctrl_code);
        tLSErrInfo.ErrCode = this.mRegStatus.sec_ctrl_code;
        tLSErrInfo.Msg = new String(this.mRegStatus.promptinfo);
        if (tLSErrInfo.ErrCode != 0) {
            whenError(tLSErrInfo, obj);
            return;
        }
        QLog.i("Reg Success " + tLSErrInfo.ErrCode + ", msg: " + tLSErrInfo.Msg);
        switch (i2) {
            case 33:
                QLog.i("OnSmsRegAskCodeSuccess ");
                ((TLSSmsRegListener) obj).OnSmsRegAskCodeSuccess(this.mRegStatus.next_resend_time, this.mRegStatus.total_time_over);
                return;
            case 36:
                QLog.i("OnSmsRegReaskCodeSuccess ");
                ((TLSSmsRegListener) obj).OnSmsRegReaskCodeSuccess(this.mRegStatus.next_resend_time, this.mRegStatus.total_time_over);
                return;
            case 37:
                QLog.i("OnSmsRegVerifyCodeSuccess ");
                ((TLSSmsRegListener) obj).OnSmsRegVerifyCodeSuccess();
                return;
            case 38:
                QLog.i("OnSmsRegCommitSuccess ");
                ((TLSSmsRegListener) obj).OnSmsRegCommitSuccess(tLSUserInfo);
                return;
            case 49:
                QLog.i("OnPwdRegAskCodeSuccess ");
                ((TLSPwdRegListener) obj).OnPwdRegAskCodeSuccess(this.mRegStatus.next_resend_time, this.mRegStatus.total_time_over);
                return;
            case 52:
                QLog.i("OnPwdRegReaskCodeSuccess ");
                ((TLSPwdRegListener) obj).OnPwdRegReaskCodeSuccess(this.mRegStatus.next_resend_time, this.mRegStatus.total_time_over);
                return;
            case 53:
                QLog.i("OnPwdRegVerifyCodeSuccess ");
                ((TLSPwdRegListener) obj).OnPwdRegVerifyCodeSuccess();
                return;
            case 54:
                QLog.i("OnPwdRegCommitSuccess ");
                ((TLSPwdRegListener) obj).OnPwdRegCommitSuccess(tLSUserInfo);
                return;
            case 64:
                QLog.i("OnPwdResetAskCodeSuccess ");
                ((TLSPwdResetListener) obj).OnPwdResetAskCodeSuccess(this.mRegStatus.next_resend_time, this.mRegStatus.total_time_over);
                return;
            case 65:
                QLog.i("OnPwdResetReaskCodeSuccess ");
                ((TLSPwdResetListener) obj).OnPwdResetReaskCodeSuccess(this.mRegStatus.next_resend_time, this.mRegStatus.total_time_over);
                return;
            case 66:
                QLog.i("OnPwdResetVerifyCodeSuccess ");
                ((TLSPwdResetListener) obj).OnPwdResetVerifyCodeSuccess();
                return;
            case acc_request.CMD_RST_CM /*67*/:
                QLog.i("OnPwdResetCommitSuccess ");
                ((TLSPwdResetListener) obj).OnPwdResetCommitSuccess(tLSUserInfo);
                return;
            case 97:
                QLog.i("OnStrAccRegQuerySuccess ");
                TLSStrAccRegCommit((TLSStrAccRegListener) obj);
                return;
            case 102:
                QLog.i("OnStrAccRegCommitSuccess ");
                ((TLSStrAccRegListener) obj).OnStrAccRegSuccess(tLSUserInfo);
                return;
            case 118:
                QLog.i("OnGuestRegSuccess ");
                tLSUserInfo.identifier = this.mRegStatus.userID;
                if (obj instanceof TLSGuestRegListener) {
                    ((TLSGuestRegListener) obj).OnGuestRegSuccess(tLSUserInfo);
                    return;
                } else if (obj instanceof TLSSSORegListener) {
                    ((TLSSSORegListener) obj).OnGuestRegSuccess(tLSUserInfo);
                    return;
                } else {
                    return;
                }
            default:
                QLog.w("OnTLSRequestRegister unhandle cmd:" + i2);
                return;
        }
    }

    private void OnTLSExchangeTicket(TransReqContext transReqContext, req_global tencent_tls_request_req_global, String str, int i, String str2, int i2) {
        TLSExchangeTicketListener tLSExchangeTicketListener = (TLSExchangeTicketListener) transReqContext.listener;
        TLSErrInfo tLSErrInfo = new TLSErrInfo(i2, "", I18nMsg.getMsg(MSG_TYPE.MSG_3));
        if (i2 != 0) {
            whenError(tLSErrInfo, tLSExchangeTicketListener);
            return;
        }
        Oidb0xa0b_response oidb0xa0b_response = new Oidb0xa0b_response(transReqContext.get_body());
        if (oidb0xa0b_response.getResult() != 0) {
            tLSErrInfo.ErrCode = oidb0xa0b_response.getResult();
            tLSErrInfo.Msg = "解析包出错";
            whenError(tLSErrInfo, tLSExchangeTicketListener);
        } else if (oidb0xa0b_response.getHeadResult() == 0) {
            long tinyID = oidb0xa0b_response.getTinyID();
            byte[] a2 = oidb0xa0b_response.getA2();
            byte[] d2 = oidb0xa0b_response.getD2();
            byte[] d2Key = oidb0xa0b_response.getD2Key();
            int adminFlag = oidb0xa0b_response.getAdminFlag();
            String identifier = oidb0xa0b_response.getIdentifier();
            if (i <= 0) {
                identifier = tencent_tls_request_req_global._userid;
            }
            String userSig = oidb0xa0b_response.getUserSig();
            String str3 = tencent_tls_request_req_global._userid;
            if (util.checkInvalid(identifier)) {
                identifier = tencent_tls_request_req_global._userid;
            }
            tencent_tls_request_req_global._uin = tinyID;
            tencent_tls_request_req_global._admin = adminFlag;
            tencent_tls_request_req_global.put_open_account(tencent_tls_request_req_global._userid, identifier, tencent_tls_request_req_global._uin);
            tencent_tls_request_req_global._userid = identifier;
            tinyID = req_global.get_cur_time();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Ticket(64, a2, new tlv_t(269).get_data(), tinyID, 2160000 + tinyID));
            ArrayList arrayList2 = arrayList;
            arrayList2.add(new Ticket(262144, d2, d2Key, tinyID, tinyID + 1728000));
            arrayList.add(new Ticket(SigType.TLS, userSig.getBytes(), null, tinyID, tinyID + 2160000));
            if (i > 0) {
                arrayList2 = arrayList;
                arrayList2.add(new Ticket(305419896, i, str, str2.getBytes(), str3.getBytes(), tinyID, 0));
            }
            tencent_tls_request_req_global.put_siginfo(tencent_tls_request_req_global._uin, this.mSdkAppid, new byte[0], new byte[0], this.mSdkAppid, 4294967295L, tinyID, arrayList, 0);
            TLSExchangeTicketListener tLSExchangeTicketListener2 = tLSExchangeTicketListener;
            tLSExchangeTicketListener2.OnExchangeTicketSuccess(new TLSUserInfo(i, tencent_tls_request_req_global._userid, tencent_tls_request_req_global._uin, tinyID, UserType.USER_TYPE_NORMAL));
        } else {
            QLog.i("rsp.getHeadResult() = " + oidb0xa0b_response.getHeadResult());
            tLSErrInfo.ErrCode = oidb0xa0b_response.getHeadResult();
            tLSErrInfo.Msg = oidb0xa0b_response.getErrMsg();
            if (tLSErrInfo.Msg.length() == 0) {
                tLSErrInfo.Msg = "内部错误，请访问 http://bbs.qcloud.com/thread-8309-1-1.html 或者联系 QQ 3268519604";
            }
            whenError(tLSErrInfo, tLSExchangeTicketListener);
        }
    }

    private void OnTLSOpenAccountQuery(TransReqContext transReqContext, int i) {
        TLSOpenAccountQueryListener tLSOpenAccountQueryListener = (TLSOpenAccountQueryListener) transReqContext.listener;
        TLSErrInfo tLSErrInfo = new TLSErrInfo(i, "", I18nMsg.getMsg(MSG_TYPE.MSG_3));
        if (i != 0) {
            whenError(tLSErrInfo, tLSOpenAccountQueryListener);
            return;
        }
        Oidb0x602_response oidb0x602_response = new Oidb0x602_response(transReqContext.get_body());
        if (oidb0x602_response.getResult() != 0) {
            tLSErrInfo.ErrCode = oidb0x602_response.getResult();
            tLSErrInfo.Msg = "解析包出错";
            whenError(tLSErrInfo, tLSOpenAccountQueryListener);
        } else if (oidb0x602_response.getHeadResult() == 0) {
            switch (oidb0x602_response.getOpenAccountStatus()) {
                case UNUSED:
                    this.openAccountInfo.status = OpenAccountStatus.UNUSED;
                    tLSOpenAccountQueryListener.onUnlogined(this.openAccountInfo);
                    return;
                case USED_UNBINDED:
                    this.openAccountInfo.status = OpenAccountStatus.USED_UNBINDED;
                    tLSOpenAccountQueryListener.onLoginedButUnbinded(this.openAccountInfo);
                    return;
                case USED_BINDED:
                    this.openAccountInfo.status = OpenAccountStatus.USED_BINDED;
                    tLSOpenAccountQueryListener.onLoginedAndBinded(this.openAccountInfo);
                    return;
                default:
                    QLog.i("后台返回异常");
                    return;
            }
        } else {
            QLog.i("rsp.getHeadResult() = " + oidb0x602_response.getHeadResult());
            tLSErrInfo.ErrCode = oidb0x602_response.getHeadResult();
            tLSErrInfo.Msg = "出错啦";
            whenError(tLSErrInfo, tLSOpenAccountQueryListener);
        }
    }

    private void OnTLSOpenAccountBind(TransReqContext transReqContext, int i) {
        TLSOpenAccountBindListener tLSOpenAccountBindListener = (TLSOpenAccountBindListener) transReqContext.listener;
        TLSErrInfo tLSErrInfo = new TLSErrInfo(i, "", I18nMsg.getMsg(MSG_TYPE.MSG_3));
        if (i != 0) {
            whenError(tLSErrInfo, tLSOpenAccountBindListener);
            return;
        }
        Oidb0x601_response oidb0x601_response = new Oidb0x601_response(transReqContext.get_body());
        if (oidb0x601_response.getResult() != 0) {
            tLSErrInfo.ErrCode = oidb0x601_response.getResult();
            tLSErrInfo.Msg = "解析包出错";
            whenError(tLSErrInfo, tLSOpenAccountBindListener);
        } else if (oidb0x601_response.getHeadResult() == 0) {
            tLSOpenAccountBindListener.onBindSuccess(this.openAccountInfo);
        } else {
            QLog.i("rsp.getHeadResult() = " + oidb0x601_response.getHeadResult());
            tLSOpenAccountBindListener.onBindFailure(this.openAccountInfo);
        }
    }

    private void whenError(TLSErrInfo tLSErrInfo, Object obj) {
        if (tLSErrInfo.ErrCode == -1000) {
            if (obj instanceof TLSPwdRegListener) {
                ((TLSPwdRegListener) obj).OnPwdRegTimeout(tLSErrInfo);
            } else if (obj instanceof TLSPwdResetListener) {
                ((TLSPwdResetListener) obj).OnPwdResetTimeout(tLSErrInfo);
            } else if (obj instanceof TLSSmsRegListener) {
                ((TLSSmsRegListener) obj).OnSmsRegTimeout(tLSErrInfo);
            } else if (obj instanceof TLSStrAccRegListener) {
                ((TLSStrAccRegListener) obj).OnStrAccRegTimeout(tLSErrInfo);
            } else if (obj instanceof TLSGuestRegListener) {
                ((TLSGuestRegListener) obj).OnGuestRegTimeout(tLSErrInfo);
            } else if (obj instanceof TLSOpenAccountQueryListener) {
                ((TLSOpenAccountQueryListener) obj).onTimeOut(tLSErrInfo);
            } else if (obj instanceof TLSOpenAccountBindListener) {
                ((TLSOpenAccountBindListener) obj).onTimeOut(tLSErrInfo);
            } else if (obj instanceof TLSExchangeTicketListener) {
                ((TLSExchangeTicketListener) obj).OnExchangeTicketTimeout(tLSErrInfo);
            } else if (obj instanceof TLSOpenAccessTokenListener) {
                ((TLSOpenAccessTokenListener) obj).OnOpenAccessTokenTimeout(tLSErrInfo);
            }
        } else if (obj instanceof TLSPwdRegListener) {
            ((TLSPwdRegListener) obj).OnPwdRegFail(tLSErrInfo);
        } else if (obj instanceof TLSPwdResetListener) {
            ((TLSPwdResetListener) obj).OnPwdResetFail(tLSErrInfo);
        } else if (obj instanceof TLSSmsRegListener) {
            ((TLSSmsRegListener) obj).OnSmsRegFail(tLSErrInfo);
        } else if (obj instanceof TLSStrAccRegListener) {
            ((TLSStrAccRegListener) obj).OnStrAccRegFail(tLSErrInfo);
        } else if (obj instanceof TLSGuestRegListener) {
            ((TLSGuestRegListener) obj).OnGuestRegFail(tLSErrInfo);
        } else if (obj instanceof TLSOpenAccountQueryListener) {
            ((TLSOpenAccountQueryListener) obj).onFailure(tLSErrInfo);
        } else if (obj instanceof TLSOpenAccountBindListener) {
            ((TLSOpenAccountBindListener) obj).onFailure(tLSErrInfo);
        } else if (obj instanceof TLSExchangeTicketListener) {
            ((TLSExchangeTicketListener) obj).OnExchangeTicketFail(tLSErrInfo);
        } else if (obj instanceof TLSOpenAccessTokenListener) {
            ((TLSOpenAccessTokenListener) obj).OnOpenAccessTokenFail(tLSErrInfo);
        }
        QLog.i("whenError " + tLSErrInfo.ErrCode + ", msg: " + tLSErrInfo.Msg);
    }

    private TLSOpenAccountInfo GetLocalOpenAccountInfo(String str, long j) {
        SigInfo sigInfo;
        long tinyId = this.mG.getTinyId(str);
        if (tinyId == 0) {
            sigInfo = null;
        } else {
            SigInfo sigInfo2 = this.mG.get_siginfo(tinyId, j);
            sigInfo = sigInfo2 == null ? sigInfo2 : sigInfo2;
        }
        if (sigInfo == null || sigInfo._access_token == null || sigInfo._access_token.length <= 0) {
            QLog.i("userAccount:" + str + " dwAppid:" + j + " GetLocalOpenAccountInfo return: null");
            return null;
        }
        QLog.i("userAccount:" + str + " dwAppid:" + j + " GetLocalOpenAccountInfo return: not null");
        return new TLSOpenAccountInfo(sigInfo.accountType, new String(sigInfo.appidAt3rd), new String((byte[]) sigInfo.openid.clone()), new String((byte[]) sigInfo._access_token.clone()));
    }

    private byte[] GetA1ByAccount(String str, long j) {
        SigInfo sigInfo;
        long tinyId = this.mG.getTinyId(str);
        if (tinyId == 0) {
            sigInfo = null;
        } else {
            sigInfo = this.mG.get_siginfo(tinyId, j);
            if (sigInfo == null) {
            }
        }
        if (sigInfo == null || sigInfo._en_A1 == null || sigInfo._en_A1.length <= 0) {
            QLog.i("userAccount:" + str + " dwAppid:" + j + " GetA1ByAccount return: null");
            return null;
        }
        QLog.i("userAccount:" + str + " dwAppid:" + j + " GetA1ByAccount return: not null");
        return (byte[]) sigInfo._en_A1.clone();
    }

    private byte[] GetNoPicSigByAccount(String str, long j) {
        SigInfo sigInfo;
        long tinyId = this.mG.getTinyId(str);
        if (tinyId == 0) {
            sigInfo = null;
        } else {
            sigInfo = this.mG.get_siginfo(tinyId, j);
            if (sigInfo == null) {
            }
        }
        if (sigInfo == null || sigInfo._noPicSig == null || sigInfo._noPicSig.length <= 0) {
            QLog.i("userAccount:" + str + " dwAppid:" + j + " GetNoPicSigByAccount return: null");
            return null;
        }
        QLog.i("userAccount:" + str + " dwAppid:" + j + " GetNoPicSigByAccount return: not null");
        return (byte[]) sigInfo._noPicSig.clone();
    }

    private TLSUserInfo GetLocalSig(String str, long j) {
        QLog.i("GetLocalSig name:" + str);
        TinyInfo tinyInfo = this.mG.getTinyInfo(str);
        if (tinyInfo == null) {
            return null;
        }
        this.mG._uin = tinyInfo._tinyid;
        SigInfo sigInfo = this.mG.get_siginfo(tinyInfo._tinyid, j);
        if (sigInfo == null) {
            return null;
        }
        long j2 = sigInfo._A1_create_time;
        if (j2 <= 0) {
            j2 = sigInfo._TLS_create_time;
        }
        TLSUserInfo tLSUserInfo = new TLSUserInfo(tinyInfo._acc_type, tinyInfo._userid, tinyInfo._tinyid, j2, tinyInfo.userType);
        tLSUserInfo.get_clone(sigInfo);
        return tLSUserInfo;
    }

    private Ticket getLocalTicket(String str, int i) {
        return getUserTicket(GetLocalSig(str, this.mSdkAppid), i);
    }

    private static Ticket getUserTicket(TLSUserInfo tLSUserInfo, int i) {
        if (!(tLSUserInfo == null || tLSUserInfo._tickets == null)) {
            for (int i2 = 0; i2 < tLSUserInfo._tickets.size(); i2++) {
                Ticket ticket = (Ticket) tLSUserInfo._tickets.get(i2);
                if (ticket.Type == i) {
                    QLog.i(" type:" + Integer.toHexString(i) + " sig:" + util.buf_len(ticket.Sig) + " key:" + util.buf_len(ticket.SigKey) + " create time:" + ticket.CreateTime + " expire time:" + ticket.ExpireTime);
                    return ticket;
                }
            }
        }
        return null;
    }

    public long TLSGetLastRefreshTime(String str) {
        if (util.checkInvalid(str)) {
            return 0;
        }
        QLog.i("TLSGetLastRefreshTime identifier:" + str);
        TinyInfo tinyInfo = this.mG.getTinyInfo(str);
        if (tinyInfo == null) {
            return 0;
        }
        SigInfo sigInfo = this.mG.get_siginfo(tinyInfo._tinyid, this.mSdkAppid);
        if (sigInfo != null) {
            return sigInfo._A2_create_time;
        }
        return 0;
    }

    public static void setSoLoadPath(String str) {
        util.soLoadPath = str;
    }
}
