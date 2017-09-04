package tencent.tls.request;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.smtt.sdk.WebView;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import tencent.tls.account.acc_status;
import tencent.tls.platform.TLSUserInfo;
import tencent.tls.report.QLog;
import tencent.tls.request.TinyInfo.UserType;
import tencent.tls.tlvs.tlv_t150;
import tencent.tls.tools.I18nMsg;
import tencent.tls.tools.MD5;
import tencent.tls.tools.util;

public class req_global {
    public static byte[] _IMEI = new byte[0];
    static byte[] _IMEI_KEY = new byte[0];
    public static SecureRandom _SR = new SecureRandom();
    public static int _acc_type = 0;
    public static account_sig_info_map _account_sig_info_map = null;
    public static int _android_sdk;
    public static byte[] _apk_id = new byte[0];
    static byte[] _apk_sig = new byte[0];
    static byte[] _apk_v = new byte[0];
    public static byte[] _apn = new byte[0];
    static int _app_client_version = 0;
    public static TreeMap<Long, async_context> _async_data = new TreeMap();
    static byte[] _brand = new byte[0];
    public static Context _context = null;
    public static long _cur_sequence = 0;
    static int _dev_chg = 0;
    static int _dev_report = 0;
    static byte[] _device = new byte[0];
    static int _guid_chg = 0;
    static int _guid_src = 0;
    static int _img_type = 1;
    public static byte[] _ip_addr = new byte[4];
    static int _isroot = 0;
    public static long _l_init_time = 0;
    public static String _last_date = "";
    public static int _local_id = I18nMsg.ZH_CN;
    static byte[] _mac = new byte[0];
    public static int _network_type = 0;
    static int _new_install = 0;
    public static int _pic_type = 0;
    static int _read_guid = 0;
    static byte[] _sim_operator_name = new byte[0];
    static long _time_difference = 0;
    private static final Object data_lock = new Object();
    public static long sdkappid;
    public int _admin = 0;
    public int _cancel = 0;
    public int _encrypt_type = 0;
    public byte[] _pub_key = new byte[16];
    public byte[] _rand_key = new byte[16];
    public long _seq = 0;
    public byte[] _share_key = new byte[16];
    Socket _sk = null;
    public int _sso_seq = 0;
    public tlv_t150 _t150 = null;
    public byte[] _t172_data = new byte[0];
    public byte[] _tgt_key = null;
    public int _time_out = Constants.ERRORCODE_UNKNOWN;
    Socket _transport_sk = null;
    public long _uin = 0;
    public boolean _use_sso_channel = true;
    public String _userid = "";

    public req_global(Context context) {
    }

    public req_global getClone(long j) {
        req_global tencent_tls_request_req_global = new req_global(null);
        tencent_tls_request_req_global._use_sso_channel = this._use_sso_channel;
        tencent_tls_request_req_global._time_out = this._time_out;
        if (this._rand_key != null) {
            tencent_tls_request_req_global._rand_key = (byte[]) this._rand_key.clone();
        }
        if (!(this._pub_key == null || this._share_key == null)) {
            tencent_tls_request_req_global._pub_key = (byte[]) this._pub_key.clone();
            tencent_tls_request_req_global._share_key = (byte[]) this._share_key.clone();
        }
        if (j <= 0) {
            tencent_tls_request_req_global._seq = allocSequence();
        } else {
            tencent_tls_request_req_global._seq = j;
        }
        return tencent_tls_request_req_global;
    }

    public static synchronized long allocSequence() {
        long j;
        synchronized (req_global.class) {
            if (_cur_sequence > 200) {
                _cur_sequence = 0;
            }
            j = _cur_sequence + 1;
            _cur_sequence = j;
        }
        return j;
    }

    public static async_context get_async_data(long j) {
        async_context tencent_tls_request_async_context;
        synchronized (data_lock) {
            tencent_tls_request_async_context = (async_context) _async_data.get(Long.valueOf(j));
            if (tencent_tls_request_async_context == null) {
                tencent_tls_request_async_context = new async_context();
                _async_data.put(Long.valueOf(j), tencent_tls_request_async_context);
            }
        }
        return tencent_tls_request_async_context;
    }

    public static void remove_async_data(long j) {
        synchronized (data_lock) {
            _async_data.remove(Long.valueOf(j));
        }
    }

    public static void clear_sdk_log() {
        try {
            String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
            if (format.compareTo(_last_date) != 0) {
                _last_date = format;
                new delete_expire_log(_context).start();
            }
        } catch (Exception e) {
        }
    }

    public static void dev_compare() {
        byte[] bArr = util.get_mac_addr(_context);
        if (bArr != null && bArr.length > 0) {
            bArr = MD5.toMD5Byte(bArr);
        }
        byte[] bArr2 = util.get_imei_id(_context);
        if (bArr2 != null && bArr2.length > 0) {
            bArr2 = MD5.toMD5Byte(bArr2);
        }
        byte[] _imei = util.get_IMEI(_context);
        if (_imei != null && _imei.length > 0) {
            _imei = MD5.toMD5Byte(_imei);
        }
        if (util.get_last_flag(_context) != 0) {
            byte[] bArr3 = util.get_last_mac(_context);
            byte[] bArr4 = util.get_last_imei(_context);
            byte[] bArr5 = util.get_last_guid(_context);
            if (!Arrays.equals(bArr, bArr3)) {
                _dev_chg |= 1;
            }
            if (!Arrays.equals(bArr2, bArr4)) {
                _dev_chg |= 2;
            }
            if (!Arrays.equals(_imei, bArr5)) {
                _dev_chg |= 4;
            }
        }
        util.save_cur_flag(_context, 1);
        util.save_cur_mac(_context, bArr);
        util.save_cur_imei(_context, bArr2);
        util.save_cur_guid(_context, _imei);
    }

    @SuppressLint({"NewApi"})
    private static byte[] get_guid(Context context) {
        String str = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                str = telephonyManager.getDeviceId();
            }
            String string = Secure.getString(context.getContentResolver(), "android_id");
            String str2 = "";
            String str3 = Build.MODEL;
            if (str != null) {
                str2 = str2 + str;
            }
            if (str3 != null) {
                str2 = str2 + str3;
            }
            if (string != null) {
                str2 = str2 + string;
            }
            if (str2.length() == 0) {
                return new byte[0];
            }
            return MD5.toMD5Byte(str2.getBytes());
        } catch (Exception e) {
            return new byte[0];
        }
    }

    private static byte[] get_file_guid(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        Throwable e;
        FileInputStream fileInputStream2;
        Throwable th2;
        byte[] bArr = null;
        if (context != null) {
            byte[] bArr2 = new byte[0];
            try {
                File file = new File(context.getFilesDir().getAbsolutePath() + "/tls_device.dat");
                if (file == null || !file.exists()) {
                    fileInputStream = null;
                    bArr = bArr2;
                } else {
                    fileInputStream = new FileInputStream(file);
                    try {
                        int available = fileInputStream.available();
                        if (available < 1024) {
                            bArr2 = new byte[available];
                            fileInputStream.read(bArr2);
                        }
                        bArr = bArr2;
                    } catch (Throwable e2) {
                        th = e2;
                        bArr = bArr2;
                        fileInputStream2 = fileInputStream;
                        th2 = th;
                        try {
                            _dev_report |= 65536;
                            QLog.e(th2);
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Throwable th22) {
                                    QLog.e(th22);
                                }
                            }
                            return bArr;
                        } catch (Throwable th3) {
                            e2 = th3;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable th222) {
                                    QLog.e(th222);
                                }
                            }
                            throw e2;
                        }
                    } catch (Throwable th4) {
                        e2 = th4;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw e2;
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2222) {
                        QLog.e(th2222);
                    }
                }
            } catch (Exception e3) {
                th2222 = e3;
                bArr = bArr2;
                fileInputStream2 = null;
                _dev_report |= 65536;
                QLog.e(th2222);
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                return bArr;
            } catch (Throwable th22222) {
                th = th22222;
                fileInputStream = null;
                e2 = th;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw e2;
            }
        }
        return bArr;
    }

    public static void init() {
        int i;
        int i2 = util.get_saved_network_type(_context);
        _dev_report = 0;
        Object obj = get_file_guid(_context);
        byte[] bArr = get_guid(_context);
        if (obj == null || obj.length <= 0) {
            Object bytes;
            if (bArr == null || bArr.length <= 0) {
                bytes = "%4;7t>;28<fc.5*6".getBytes();
                _read_guid = 0;
                _guid_src = 20;
            } else {
                _read_guid = 1;
                _guid_src = 17;
            }
            util.save_file_imei(_context, bytes);
            _guid_chg = 0;
            _new_install = 1;
            obj = bytes;
        } else {
            if (bArr == null || bArr.length <= 0) {
                bArr = "%4;7t>;28<fc.5*6".getBytes();
            }
            if (Arrays.equals(bArr, obj)) {
                _guid_chg = 0;
            } else {
                _guid_chg = 1;
            }
            _read_guid = 1;
            _new_install = 0;
            _guid_src = 1;
        }
        dev_compare();
        _dev_report |= (_guid_src << 24) & WebView.NIGHT_MODE_COLOR;
        _dev_report |= (_dev_chg << 8) & 65280;
        _IMEI = (byte[]) obj.clone();
        _IMEI_KEY = (byte[]) obj.clone();
        _android_sdk = VERSION.SDK_INT;
        _sim_operator_name = util.get_sim_operator_name(_context);
        _network_type = util.get_network_type(_context);
        if (i2 != _network_type) {
            util.set_net_retry_type(_context, 0);
            util.save_network_type(_context, _network_type);
        }
        _apn = util.get_apn_string(_context).getBytes();
        bArr = util.get_apk_id(_context);
        _apk_id = bArr;
        acc_status.apk_id = bArr;
        _apk_v = util.get_apk_v(_context, new String(_apk_id));
        _apk_sig = util.getPkgSigFromApkName(_context, _context.getPackageName());
        String str = Build.MODEL;
        if (str == null) {
            _device = new byte[0];
        } else {
            _device = str.getBytes();
        }
        if (util.isFileExist("/system/bin/su") || util.isFileExist("/system/xbin/su") || util.isFileExist("/sbin/su")) {
            i = 1;
        } else {
            i = 0;
        }
        if (i == 1) {
            i = 1;
        } else {
            i = 0;
        }
        _isroot = i;
    }

    public static long get_cur_time() {
        return System.currentTimeMillis() / 1000;
    }

    public static long get_server_cur_time() {
        return (System.currentTimeMillis() / 1000) + _l_init_time;
    }

    public void set_time_ip(byte[] bArr, byte[] bArr2) {
        _time_difference = (((long) util.buf_to_int32(bArr, 0)) & 4294967295L) - (System.currentTimeMillis() / 1000);
        _l_init_time = _time_difference;
        _ip_addr = bArr2;
    }

    public void clear_time_ip() {
        _ip_addr = null;
        _time_difference = 0;
        _l_init_time = 0;
    }

    public void set_context(Context context) {
        _context = context;
        _account_sig_info_map = new account_sig_info_map(context);
        Object obj = new byte[16];
        _SR.nextBytes(obj);
        System.arraycopy(obj, 0, this._rand_key, 0, obj.length);
    }

    public void close_connect() {
        QLog.d("close_connect");
        if (this._sk != null) {
            try {
                QLog.d("close_connect" + this._sk.toString());
                this._sk.close();
            } catch (Throwable e) {
                QLog.e(e);
            }
            this._sk = null;
        }
    }

    public void close_transport_connect() {
        QLog.d("close_transport_connect");
        if (this._transport_sk != null) {
            try {
                QLog.d("close_transport_connect" + this._transport_sk.toString());
                this._transport_sk.close();
            } catch (Throwable e) {
                QLog.e(e);
            }
            this._transport_sk = null;
        }
    }

    public synchronized SigInfo get_siginfo(long j, long j2) {
        SigInfo sigInfo;
        QLog.i("get_siginfo", j);
        sigInfo = _account_sig_info_map.get_siginfo(j, j2);
        if (sigInfo != null) {
        }
        return sigInfo;
    }

    public synchronized void remove_account(String str) {
        _account_sig_info_map.remove_account(str);
    }

    public synchronized long getTinyId(String str) {
        long j = 0;
        synchronized (this) {
            if (str != null) {
                TinyInfo tinyInfo = _account_sig_info_map.getTinyInfo(str);
                if (tinyInfo != null) {
                    j = tinyInfo._tinyid;
                }
            }
        }
        return j;
    }

    public synchronized TinyInfo getTinyInfo(String str) {
        TinyInfo tinyInfo;
        if (str == null) {
            tinyInfo = null;
        } else {
            tinyInfo = _account_sig_info_map.getTinyInfo(str);
        }
        return tinyInfo;
    }

    public synchronized List<TLSUserInfo> get_all_logined_account() {
        List<TLSUserInfo> list;
        try {
            list = _account_sig_info_map.get_all_logined_account();
        } catch (NullPointerException e) {
            QLog.w("_account_sig_info_map null " + e.getMessage());
            list = null;
        }
        return list;
    }

    public synchronized int put_siginfo(long j, long j2, byte[] bArr, byte[] bArr2, long j3, long j4, long j5, ArrayList<Ticket> arrayList, int i) {
        return _account_sig_info_map.put_siginfo(j, j2, bArr, bArr2, j3, j4, j5, arrayList, i);
    }

    public synchronized void clear_sig(long j, long j2) {
        _account_sig_info_map.clear_sig(j, j2);
    }

    public synchronized void put_account(String str, long j) {
        _account_sig_info_map.putTinyInfo(str, str, j, UserType.USER_TYPE_NORMAL);
    }

    public synchronized void put_open_account(String str, String str2, long j) {
        _account_sig_info_map.putTinyInfo(str, str2, j, UserType.USER_TYPE_NORMAL);
    }

    public void put_guest(String str, long j) {
        _account_sig_info_map.putTinyInfo(str, str, j, UserType.USER_TYPE_GUEST);
    }

    public void put_sso_guest(String str, long j) {
        _account_sig_info_map.putTinyInfo(str, str, j, UserType.USER_TYPE_SSO_GUEST);
    }

    public static String long2string(long j) {
        BigInteger valueOf = BigInteger.valueOf(j);
        if (valueOf.signum() < 0) {
            valueOf = valueOf.add(BigInteger.ONE.shiftLeft(64));
        }
        return valueOf.toString();
    }

    public String uin2string() {
        return long2string(this._uin);
    }
}
