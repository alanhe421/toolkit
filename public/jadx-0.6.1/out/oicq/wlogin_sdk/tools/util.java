package oicq.wlogin_sdk.tools;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.etrump.jni.ETConverter;
import com.qq.taf.jce.JceStruct;
import com.tencent.connect.common.Constants;
import com.tencent.mid.api.MidEntity;
import com.tencent.open.utils.SystemUtils;
import com.tencent.smtt.sdk.WebView;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.upload.log.trace.TracerConfig;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import javax.crypto.Cipher;
import oicq.wlogin_sdk.request.u;
import oicq.wlogin_sdk.tools.InternationMsg.MSG_TYPE;
import tencent.tls.tools.util.APNName;

public class util {
    public static final int ASYN_CHECK_IMAGE = 2;
    public static final int ASYN_CHECK_SMS = 4;
    public static final int ASYN_EXCEPTION = 11;
    public static final int ASYN_GET_A1_WITH_A1 = 6;
    public static final int ASYN_GET_ST_WITHOUT_PWD = 5;
    public static final int ASYN_GET_ST_WITH_PWD = 0;
    public static final int ASYN_QUICKLOG_WITH_PTSIG = 16;
    public static final int ASYN_QUICKLOG_WITH_QQSIG = 15;
    public static final int ASYN_QUICKLOG_WITH_QRSIG = 17;
    public static final int ASYN_REFLUSH_IMAGE = 1;
    public static final int ASYN_REFLUSH_SMS = 3;
    public static final int ASYN_REPORT = 7;
    public static final int ASYN_REPORT_ERROR = 8;
    public static final int ASYN_SMSLOGIN_CHECK = 12;
    public static final int ASYN_SMSLOGIN_REFRESH = 14;
    public static final int ASYN_SMSLOGIN_VERIFY = 13;
    public static final int ASYN_TRANSPORT = 9;
    public static final int ASYN_TRANSPORT_MSF = 10;
    public static final long BUILD_TIME = 1483427861;
    public static final int D = 2;
    private static SimpleDateFormat DAYFORMAT = null;
    public static final int E_A1_DECRYPT = -1014;
    public static final int E_A1_FORMAT = -1016;
    public static final int E_ADVANCE_NOTICE = 257;
    public static final int E_APK_CHK_ERR = -1021;
    public static final int E_BUFFER_OVERFLOW = -1023;
    public static final int E_DECRYPT = -1002;
    public static final int E_ENCODING = -1013;
    public static final int E_ENCRYPTION_METHOD = -1024;
    public static final int E_INPUT = -1017;
    public static final int E_NAME_INVALID = -1008;
    public static final int E_NO_KEY = -1004;
    public static final int E_NO_REG_CMD = -1010;
    public static final int E_NO_RET = -1000;
    public static final int E_NO_TGTKEY = -1006;
    public static final int E_NO_UIN = -1003;
    public static final int E_PENDING = -1001;
    public static final int E_PK_LEN = -1009;
    public static final int E_PUSH_REG = -1011;
    public static final int E_RESOLVE_ADDR = -1007;
    public static final int E_SAVE_TICKET_ERROR = -1022;
    public static final int E_SHARE_SERVICE_EXCEPTION = -1020;
    public static final int E_SHARE_SERVICE_PARAM = -1019;
    public static final int E_SHARE_SERVICE_UNCHECK = -1018;
    public static final int E_SYSTEM = -1012;
    public static final int E_TLV_DECRYPT = -1015;
    public static final int E_TLV_VERIFY = -1005;
    public static final String FILE_DIR = "wtlogin";
    private static int HONEYCOMB = 11;
    public static final int I = 1;
    public static LogCallBack LCB = null;
    public static boolean LOGCAT_OUT = false;
    public static final String LOG_DIR = "tencent/wtlogin";
    public static int LOG_LEVEL = 1;
    public static int MAX_APPID = 65535;
    public static final int MAX_CONTENT_SIZE = 25600;
    public static final int MAX_FILE_SIZE = 524288;
    public static int MAX_NAME_LEN = 128;
    @SuppressLint({"NewApi"})
    private static int MODE_MULTI_PROCESS = 0;
    public static final String SDK_VERSION = "6.0.0.2003";
    public static final int SSO_VERSION = 5;
    public static final long SVN_VER = 2003;
    public static final int S_BABYLH_EXPIRED = 116;
    public static final int S_GET_IMAGE = 2;
    public static final int S_GET_SMS = 160;
    public static final int S_LH_EXPIRED = 41;
    public static final int S_PWD_WRONG = 1;
    public static final int S_ROLL_BACK = 180;
    public static final int S_SEC_GUID = 204;
    public static final int S_SUCCESS = 0;
    public static final String TAG = "wlogin_sdk";
    public static final int W = 0;
    static final char[] base64_encode_chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    static final char base64_pad_url = '_';
    static final short[] base64_reverse_table_url = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) 62, (short) -1, (short) -1, (short) 63, (short) -1, (short) -1, (short) 52, (short) 53, (short) 54, (short) 55, (short) 56, (short) 57, (short) 58, (short) 59, (short) 60, (short) 61, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) 0, (short) 1, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7, (short) 8, (short) 9, (short) 10, (short) 11, (short) 12, (short) 13, (short) 14, (short) 15, (short) 16, (short) 17, (short) 18, (short) 19, (short) 20, (short) 21, (short) 22, (short) 23, (short) 24, (short) 25, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) 26, (short) 27, (short) 28, (short) 29, (short) 30, (short) 31, (short) 32, (short) 33, (short) 34, (short) 35, (short) 36, (short) 37, (short) 38, (short) 39, (short) 40, (short) 41, (short) 42, (short) 43, (short) 44, (short) 45, (short) 46, (short) 47, (short) 48, (short) 49, (short) 50, (short) 51, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1};
    private static boolean libwtecdh_loaded = false;
    public static boolean loadEncryptSo = true;
    public static String logContent = "";

    static {
        int i = 0;
        if (VERSION.SDK_INT > 9) {
            i = 4;
        }
        MODE_MULTI_PROCESS = i;
    }

    public static byte[] get_os_type() {
        return "android".getBytes();
    }

    public static byte[] get_os_version() {
        return VERSION.RELEASE.getBytes();
    }

    public static void int8_to_buf(byte[] bArr, int i, int i2) {
        bArr[i + 0] = (byte) (i2 >> 0);
    }

    public static void int16_to_buf(byte[] bArr, int i, int i2) {
        bArr[i + 1] = (byte) (i2 >> 0);
        bArr[i + 0] = (byte) (i2 >> 8);
    }

    public static void int32_to_buf(byte[] bArr, int i, int i2) {
        bArr[i + 3] = (byte) (i2 >> 0);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 0] = (byte) (i2 >> 24);
    }

    public static void int64_to_buf(byte[] bArr, int i, long j) {
        bArr[i + 7] = (byte) ((int) (j >> null));
        bArr[i + 6] = (byte) ((int) (j >> 8));
        bArr[i + 5] = (byte) ((int) (j >> 16));
        bArr[i + 4] = (byte) ((int) (j >> 24));
        bArr[i + 3] = (byte) ((int) (j >> 32));
        bArr[i + 2] = (byte) ((int) (j >> 40));
        bArr[i + 1] = (byte) ((int) (j >> 48));
        bArr[i + 0] = (byte) ((int) (j >> 56));
    }

    public static void int64_to_buf32(byte[] bArr, int i, long j) {
        bArr[i + 3] = (byte) ((int) (j >> null));
        bArr[i + 2] = (byte) ((int) (j >> 8));
        bArr[i + 1] = (byte) ((int) (j >> 16));
        bArr[i + 0] = (byte) ((int) (j >> 24));
    }

    public static int buf_to_int8(byte[] bArr, int i) {
        return bArr[i] & 255;
    }

    public static int buf_to_int16(byte[] bArr, int i) {
        return ((bArr[i] << 8) & 65280) + ((bArr[i + 1] << 0) & 255);
    }

    public static int buf_to_int32(byte[] bArr, int i) {
        return ((((bArr[i] << 24) & WebView.NIGHT_MODE_COLOR) + ((bArr[i + 1] << 16) & 16711680)) + ((bArr[i + 2] << 8) & 65280)) + ((bArr[i + 3] << 0) & 255);
    }

    public static long buf_to_int64(byte[] bArr, int i) {
        return (((((((0 + ((((long) bArr[i]) << 56) & -72057594037927936L)) + ((((long) bArr[i + 1]) << 48) & 71776119061217280L)) + ((((long) bArr[i + 2]) << 40) & 280375465082880L)) + ((((long) bArr[i + 3]) << 32) & 1095216660480L)) + ((((long) bArr[i + 4]) << 24) & 4278190080L)) + ((((long) bArr[i + 5]) << 16) & 16711680)) + ((((long) bArr[i + 6]) << 8) & 65280)) + ((((long) bArr[i + 7]) << null) & 255);
    }

    public static int get_rand_32() {
        return (int) (Math.random() * 2.147483647E9d);
    }

    public static byte[] get_rand_16byte(byte[] bArr) {
        try {
            Object seed = SecureRandom.getSeed(16);
            byte[] bArr2 = new byte[(seed.length + bArr.length)];
            System.arraycopy(seed, 0, bArr2, 0, seed.length);
            System.arraycopy(bArr, 0, bArr2, seed.length, bArr.length);
            return MD5.toMD5Byte(bArr2);
        } catch (Throwable th) {
            return get_prand_16byte();
        }
    }

    public static byte[] get_rand_16byte(SecureRandom secureRandom) {
        try {
            byte[] bArr = new byte[16];
            secureRandom.nextBytes(bArr);
            return bArr;
        } catch (Throwable th) {
            return get_prand_16byte();
        }
    }

    public static byte[] get_prand_16byte() {
        try {
            byte[] bArr = new byte[16];
            int32_to_buf(bArr, 0, (int) (Math.random() * 2.147483647E9d));
            int32_to_buf(bArr, 4, (int) (Math.random() * 2.147483647E9d));
            int32_to_buf(bArr, 8, (int) (Math.random() * 2.147483647E9d));
            int32_to_buf(bArr, 12, (int) (Math.random() * 2.147483647E9d));
            return MD5.toMD5Byte(bArr);
        } catch (Throwable th) {
            return new byte[16];
        }
    }

    public static String get_mpasswd() {
        try {
            byte[] seed = SecureRandom.getSeed(16);
            int i = 0;
            String str = "";
            while (i < seed.length) {
                boolean nextBoolean = new Random().nextBoolean();
                int abs = Math.abs(seed[i] % 26);
                i++;
                str = str + String.valueOf((char) ((nextBoolean ? 97 : 65) + abs));
            }
            return str;
        } catch (Throwable th) {
            return "1234567890123456";
        }
    }

    public static long get_server_cur_time() {
        return u.g();
    }

    public static Boolean check_uin_account(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong < TracerConfig.LOG_FLUSH_DURATION || parseLong > 4000000000L) {
                return Boolean.valueOf(false);
            }
            return Boolean.valueOf(true);
        } catch (NumberFormatException e) {
            return Boolean.valueOf(false);
        }
    }

    public static String buf_to_string(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            str = (str + Integer.toHexString((bArr[i] >> 4) & 15)) + Integer.toHexString(bArr[i] & 15);
        }
        return str;
    }

    public static String buf_to_string(byte[] bArr, int i) {
        if (bArr == null) {
            return "";
        }
        if (i > bArr.length) {
            i = bArr.length;
        }
        String str = "";
        int i2 = 0;
        while (i2 < i) {
            String str2 = (str + Integer.toHexString((bArr[i2] >> 4) & 15)) + Integer.toHexString(bArr[i2] & 15);
            i2++;
            str = str2;
        }
        return str;
    }

    public static long buf_len(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        return (long) bArr.length;
    }

    public static byte get_char(byte b) {
        if (b >= (byte) 48 && b <= (byte) 57) {
            return (byte) (b - 48);
        }
        if (b >= (byte) 97 && b <= (byte) 102) {
            return (byte) ((b - 97) + 10);
        }
        if (b < (byte) 65 || b > (byte) 70) {
            return (byte) 0;
        }
        return (byte) ((b - 65) + 10);
    }

    public static byte[] string_to_buf(String str) {
        int i = 0;
        if (str == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[(str.length() / 2)];
        while (i < str.length() / 2) {
            bArr[i] = (byte) ((get_char((byte) str.charAt(i * 2)) << 4) + get_char((byte) str.charAt((i * 2) + 1)));
            i++;
        }
        return bArr;
    }

    public static byte[] get_mac_addr(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    String macAddress = connectionInfo.getMacAddress();
                    if (macAddress != null) {
                        return macAddress.getBytes();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return new byte[0];
    }

    public static byte[] get_bssid_addr(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    String bssid = connectionInfo.getBSSID();
                    if (bssid != null) {
                        return bssid.toLowerCase().getBytes();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return new byte[0];
    }

    public static byte[] get_ssid_addr(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    String ssid = connectionInfo.getSSID();
                    if (ssid != null) {
                        return ssid.getBytes();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return new byte[0];
    }

    public static byte[] get_imei_id(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                String deviceId = telephonyManager.getDeviceId();
                if (deviceId != null) {
                    return deviceId.getBytes();
                }
            }
        } catch (Throwable th) {
        }
        return new byte[0];
    }

    @SuppressLint({"NewApi"})
    public static byte[] get_android_id(Context context) {
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string != null) {
                return string.getBytes();
            }
        } catch (Throwable th) {
        }
        return new byte[0];
    }

    public static byte[] get_IMSI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                String subscriberId = telephonyManager.getSubscriberId();
                if (subscriberId != null) {
                    return subscriberId.getBytes();
                }
            }
        } catch (Throwable th) {
        }
        return new byte[0];
    }

    public static byte[] generateGuid(Context context) {
        String str = null;
        try {
            String deviceId;
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                deviceId = telephonyManager.getDeviceId();
            } else {
                deviceId = null;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    str = connectionInfo.getMacAddress();
                }
            }
            String str2 = "";
            if (deviceId != null) {
                str2 = str2 + deviceId;
            }
            if (str != null) {
                str2 = str2 + str;
            }
            if (str2.length() <= 0) {
                return new byte[0];
            }
            return MD5.toMD5Byte(str2.getBytes());
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static byte[] get_sim_operator_name(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager.getSimState() == 5) {
                return telephonyManager.getSimOperatorName().getBytes();
            }
        } catch (Throwable th) {
        }
        return new byte[0];
    }

    public static int get_network_type(Context context) {
        int type;
        try {
            type = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().getType();
        } catch (Throwable th) {
            type = 0;
        }
        if (type == 0) {
            return 1;
        }
        return type == 1 ? 2 : 0;
    }

    public static int get_saved_network_type(Context context) {
        int i = 0;
        try {
            i = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getInt("network_type", 0);
        } catch (Throwable th) {
        }
        return i;
    }

    public static void save_network_type(Context context, int i) {
        if (context != null) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putInt("network_type", i);
            edit.commit();
        }
    }

    public static void set_server_host1(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putString("host1", new String(bArr));
            edit.commit();
        }
    }

    public static void set_server_host2(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putString("host2", new String(bArr));
            edit.commit();
        }
    }

    public static byte[] get_server_host1(Context context) {
        try {
            return context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString("host1", "").getBytes();
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static byte[] get_server_host2(Context context) {
        try {
            return context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString("host2", "").getBytes();
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static void set_wap_server_host1(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putString("wap-host1", new String(bArr));
            edit.commit();
        }
    }

    public static void set_wap_server_host2(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putString("wap-host2", new String(bArr));
            edit.commit();
        }
    }

    public static byte[] get_wap_server_host1(Context context) {
        try {
            return context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString("wap-host1", "").getBytes();
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static byte[] get_wap_server_host2(Context context) {
        try {
            return context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString("wap-host2", "").getBytes();
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static void set_net_retry_type(Context context, int i) {
        if (context != null) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putInt("netretry_type", i);
            edit.commit();
        }
    }

    public static int get_net_retry_type(Context context) {
        int i = 0;
        try {
            i = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getInt("netretry_type", 0);
        } catch (Throwable th) {
        }
        return i;
    }

    public static boolean is_wap_retry(Context context) {
        if (get_net_retry_type(context) == 0) {
            return false;
        }
        return true;
    }

    public static String get_apn_string(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo.getType() != 0) {
                return "wifi";
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (extraInfo != null) {
                return extraInfo;
            }
            return "wifi";
        } catch (Throwable th) {
        }
    }

    public static boolean is_wap_proxy_retry(Context context) {
        try {
            String str = get_apn_string(context);
            if (str != null && (str.equalsIgnoreCase(APNName.NAME_CMWAP) || str.equalsIgnoreCase(APNName.NAME_UNIWAP) || str.equalsIgnoreCase(APNName.NAME_CTWAP) || str.equalsIgnoreCase(APNName.NAME_3GWAP))) {
                return true;
            }
        } catch (Throwable th) {
        }
        return false;
    }

    public static void chg_retry_type(Context context) {
        if (get_net_retry_type(context) == 0) {
            set_net_retry_type(context, 1);
        } else {
            set_net_retry_type(context, 0);
        }
    }

    @SuppressLint({"NewApi"})
    @TargetApi(4)
    public static String get_proxy_ip() {
        if (VERSION.SDK_INT < HONEYCOMB) {
            return Proxy.getDefaultHost();
        }
        return System.getProperty("http.proxyHost");
    }

    @SuppressLint({"NewApi", "NewApi"})
    @TargetApi(4)
    public static int get_proxy_port() {
        if (VERSION.SDK_INT < HONEYCOMB) {
            return Proxy.getDefaultPort();
        }
        try {
            return Integer.parseInt(System.getProperty("http.proxyPort"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void set_ksid(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            byte[] bArr2 = get_ksid(context);
            if (bArr2 == null || bArr2.length <= 0) {
                Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
                edit.putString("ksid", buf_to_string(bArr));
                edit.commit();
            }
        }
    }

    public static byte[] get_ksid(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString("ksid", new String("")));
        } catch (Throwable th) {
        }
        return bArr;
    }

    public static void save_imei(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putString(MidEntity.TAG_IMEI, buf_to_string(bArr));
            edit.commit();
        }
    }

    public static byte[] get_saved_imei(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString(MidEntity.TAG_IMEI, new String("")));
        } catch (Throwable th) {
            printThrowable(th, "");
        }
        return bArr;
    }

    public static void save_cur_flag(Context context, int i) {
        if (context != null) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putInt("last_flag", i);
            edit.commit();
        }
    }

    public static int get_last_flag(Context context) {
        int i = 0;
        try {
            i = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getInt("last_flag", 0);
        } catch (Throwable th) {
        }
        return i;
    }

    public static void save_cur_mac(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putString("last_mac", buf_to_string(bArr));
            edit.commit();
        }
    }

    public static byte[] get_last_mac(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString("last_mac", new String("")));
        } catch (Throwable th) {
        }
        if (bArr == null || bArr.length <= 0) {
            return new byte[0];
        }
        return bArr;
    }

    public static void save_cur_imei(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putString("last_imei", buf_to_string(bArr));
            edit.commit();
        }
    }

    public static byte[] get_last_imei(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString("last_imei", new String("")));
        } catch (Throwable th) {
        }
        if (bArr == null || bArr.length <= 0) {
            return new byte[0];
        }
        return bArr;
    }

    public static void save_cur_guid(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putString("last_guid", buf_to_string(bArr));
            edit.commit();
        }
    }

    public static byte[] get_last_guid(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString("last_guid", new String("")));
        } catch (Throwable th) {
        }
        if (bArr == null || bArr.length <= 0) {
            return new byte[0];
        }
        return bArr;
    }

    public static void save_rsa_pubkey(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", MODE_MULTI_PROCESS).edit();
            edit.putString("rsa_pubkey", buf_to_string(bArr));
            edit.commit();
        }
    }

    public static byte[] get_rsa_pubkey(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences("WLOGIN_DEVICE_INFO", MODE_MULTI_PROCESS).getString("rsa_pubkey", ""));
        } catch (Throwable th) {
        }
        if (bArr == null || bArr.length <= 0) {
            return new byte[0];
        }
        return bArr;
    }

    public static byte[] getGuidFromFile(Context context) {
        Exception e;
        Throwable th;
        byte[] bArr = null;
        if (context != null) {
            byte[] bArr2 = new byte[0];
            FileInputStream fileInputStream;
            try {
                File file = new File(context.getFilesDir().getAbsolutePath() + "/wlogin_device.dat");
                if (file == null || !file.exists()) {
                    fileInputStream = null;
                } else {
                    fileInputStream = new FileInputStream(file);
                    try {
                        int available = fileInputStream.available();
                        if (available < 1024) {
                            bArr2 = new byte[available];
                            fileInputStream.read(bArr2);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            u.Y |= 65536;
                            printException(e, "");
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                    bArr = bArr2;
                                } catch (Exception e3) {
                                    printException(e3, "");
                                    bArr = bArr2;
                                }
                                bArr = get_saved_imei(context);
                                saveGuidToFile(context, bArr);
                                return bArr;
                            }
                            bArr = bArr2;
                            bArr = get_saved_imei(context);
                            saveGuidToFile(context, bArr);
                            return bArr;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e4) {
                                    printException(e4, "");
                                }
                            }
                            throw th;
                        }
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                        bArr = bArr2;
                    } catch (Exception e32) {
                        printException(e32, "");
                        bArr = bArr2;
                    }
                    if (bArr == null || bArr.length <= 0) {
                        bArr = get_saved_imei(context);
                        if (bArr != null && bArr.length > 0) {
                            saveGuidToFile(context, bArr);
                        }
                    }
                }
            } catch (Exception e42) {
                Exception exception = e42;
                fileInputStream = null;
                e32 = exception;
                u.Y |= 65536;
                printException(e32, "");
                if (fileInputStream != null) {
                    fileInputStream.close();
                    bArr = bArr2;
                    bArr = get_saved_imei(context);
                    saveGuidToFile(context, bArr);
                    return bArr;
                }
                bArr = bArr2;
                bArr = get_saved_imei(context);
                saveGuidToFile(context, bArr);
                return bArr;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream = null;
                th = th4;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
            bArr = bArr2;
            bArr = get_saved_imei(context);
            saveGuidToFile(context, bArr);
        }
        return bArr;
    }

    public static void saveGuidToFile(Context context, byte[] bArr) {
        Exception e;
        Throwable th;
        if (context != null && bArr != null && bArr.length > 0) {
            FileOutputStream fileOutputStream = null;
            try {
                File file = new File(context.getFilesDir().getAbsolutePath() + "/wlogin_device.dat");
                if (!file.exists()) {
                    file.createNewFile();
                }
                if (file.exists() && file.canWrite()) {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                    try {
                        fileOutputStream2.write(bArr);
                        fileOutputStream = fileOutputStream2;
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        try {
                            printException(e, "");
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e3) {
                                    printException(e3, "");
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e4) {
                                    printException(e4, "");
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                }
                save_imei(context, bArr);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e32) {
                        printException(e32, "");
                    }
                }
            } catch (Exception e5) {
                e32 = e5;
                printException(e32, "");
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        }
    }

    public static boolean set_cp_pubkey(Context context, long j, long j2) {
        Exception e;
        Cursor cursor;
        Throwable th;
        if (context == null) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        Uri parse = Uri.parse("content://" + "oicq.wlogin_sdk.WloginProvider" + "/rsa_pubkey");
        LOGI(parse.toString());
        Cursor query;
        try {
            query = contentResolver.query(parse, new String[]{"id", "appid", "subappid", "pubkey", "pubkey_md5"}, "appid=? and subappid=?", new String[]{"" + j, "" + j2}, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                }
                return false;
            }
            try {
                String buf_to_string = buf_to_string(get_rsa_pubkey(context));
                if (buf_to_string.length() == 0) {
                    if (query != null) {
                        query.close();
                    }
                    return false;
                }
                String toMD5 = MD5.toMD5(buf_to_string);
                if (query.getCount() > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("pubkey", buf_to_string);
                    contentValues.put("pubkey_md5", toMD5);
                    query.moveToNext();
                } else {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("appid", Long.valueOf(j));
                    contentValues2.put("subappid", Long.valueOf(j2));
                    contentValues2.put("pubkey", buf_to_string);
                    contentValues2.put("pubkey_md5", toMD5);
                    LOGD("inserted uri: " + contentResolver.insert(parse, contentValues2) + ", with appid=" + j + ", subappid=" + j2);
                }
                if (query != null) {
                    query.close();
                }
                return true;
            } catch (Exception e2) {
                e = e2;
                cursor = query;
                try {
                    printException(e, "");
                    if (cursor != null) {
                        cursor.close();
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    query = cursor;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
            printException(e, "");
            if (cursor != null) {
                cursor.close();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public static byte[] get_cp_pubkey(Context context, long j, long j2) {
        Cursor query;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        if (context == null) {
            return new byte[0];
        }
        ContentResolver contentResolver = context.getContentResolver();
        Uri parse = Uri.parse("content://" + "oicq.wlogin_sdk.WloginProvider" + "/rsa_pubkey");
        try {
            query = contentResolver.query(parse, new String[]{"id", "appid", "subappid", "pubkey", "pubkey_md5"}, "appid=? and subappid=?", new String[]{"" + j, "" + j2}, null);
            if (query != null) {
                try {
                    if (query.moveToNext()) {
                        String string = query.getString(query.getColumnIndex("pubkey"));
                        if (MD5.toMD5(string).equals(query.getString(query.getColumnIndex("pubkey_md5")))) {
                            byte[] string_to_buf = string_to_buf(string);
                            if (query == null) {
                                return string_to_buf;
                            }
                            query.close();
                            return string_to_buf;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        printException(e, "");
                        if (query != null) {
                            query.close();
                        }
                        return string_to_buf(RSACrypt.DEFAULT_PUB_KEY);
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            printException(e, "");
            if (query != null) {
                query.close();
            }
            return string_to_buf(RSACrypt.DEFAULT_PUB_KEY);
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return string_to_buf(RSACrypt.DEFAULT_PUB_KEY);
    }

    public static void save_rsa_privkey(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", MODE_MULTI_PROCESS).edit();
            edit.putString("rsa_privkey", buf_to_string(bArr));
            edit.commit();
        }
    }

    public static byte[] get_rsa_privkey(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences("WLOGIN_DEVICE_INFO", MODE_MULTI_PROCESS).getString("rsa_privkey", ""));
        } catch (Throwable th) {
        }
        if (bArr == null || bArr.length <= 0) {
            return new byte[0];
        }
        return bArr;
    }

    public static byte[] get_apk_id(Context context) {
        try {
            return context.getPackageName().getBytes();
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static byte[] get_apk_v(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName.getBytes();
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static int format_ret_code(int i) {
        switch (i) {
            case E_TLV_DECRYPT /*-1015*/:
            case -1014:
            case E_NO_TGTKEY /*-1006*/:
            case -1002:
                return 5;
            case -1000:
                return 1;
            case 0:
                return 0;
            case 2:
                return 2;
            default:
                return 17;
        }
    }

    public static String get_error_msg(int i) {
        String str = "";
        switch (i) {
            case -1000:
                return InternationMsg.a(MSG_TYPE.MSG_4);
            default:
                return InternationMsg.a(MSG_TYPE.MSG_3);
        }
    }

    public static String getLineInfo(int i) {
        if (i < 0) {
            return "";
        }
        try {
            StackTraceElement stackTraceElement = new Throwable().getStackTrace()[i];
            return "[" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + "]";
        } catch (Throwable th) {
            return "";
        }
    }

    public static void LOGI(String str) {
        try {
            if (LOG_LEVEL < 1) {
                return;
            }
            if (LCB != null) {
                LCB.OnLog(str);
            } else if (LOGCAT_OUT) {
                Log.i(TAG + getLineInfo(2), str);
            }
        } catch (Exception e) {
        }
    }

    public static void LOGI(String str, String str2) {
        try {
            if (LOG_LEVEL >= 1) {
                if (LCB != null) {
                    LCB.OnLog(str);
                } else if (LOGCAT_OUT) {
                    Log.i(TAG + getLineInfo(2), str);
                }
                b.a(u.t, str2, str);
            }
        } catch (Exception e) {
        }
    }

    public static void LOGD(String str) {
        try {
            if (LOG_LEVEL < 2) {
                return;
            }
            if (LCB != null) {
                LCB.OnLog(str);
            } else if (LOGCAT_OUT) {
                Log.d(TAG + getLineInfo(2), str);
            }
        } catch (Exception e) {
        }
    }

    public static void LOGD(String str, String str2) {
        try {
            if (LOG_LEVEL < 2) {
                return;
            }
            if (LCB != null) {
                LCB.OnLog(str, str2);
            } else if (LOGCAT_OUT) {
                Log.d(TAG + getLineInfo(2), str + ":" + str2);
            }
        } catch (Exception e) {
        }
    }

    public static void LOGW(String str, String str2) {
        try {
            if (LOG_LEVEL < 0) {
                return;
            }
            if (LCB != null) {
                LCB.OnLog(str, str2);
            } else if (LOGCAT_OUT) {
                Log.w(TAG + getLineInfo(2), str + ":" + str2);
            }
        } catch (Exception e) {
        }
    }

    public static void LOGW(String str, String str2, String str3) {
        try {
            if (LOG_LEVEL >= 0) {
                if (LCB != null) {
                    LCB.OnLog(str, str2);
                } else if (LOGCAT_OUT) {
                    Log.w(TAG + getLineInfo(2), str + ":" + str2);
                }
                b.a(u.t, str3, str + ":" + str2);
            }
        } catch (Exception e) {
        }
    }

    public static String base64_encode(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(base64_encode_chars[i3 >>> 2]);
                stringBuffer.append(base64_encode_chars[(i3 & 3) << 4]);
                break;
            }
            int i4 = i2 + 1;
            i2 = bArr[i2] & 255;
            if (i4 == length) {
                stringBuffer.append(base64_encode_chars[i3 >>> 2]);
                stringBuffer.append(base64_encode_chars[((i3 & 3) << 4) | ((i2 & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >>> 4)]);
                stringBuffer.append(base64_encode_chars[(i2 & 15) << 2]);
                break;
            }
            i = i4 + 1;
            i4 = bArr[i4] & 255;
            stringBuffer.append(base64_encode_chars[i3 >>> 2]);
            stringBuffer.append(base64_encode_chars[((i3 & 3) << 4) | ((i2 & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >>> 4)]);
            stringBuffer.append(base64_encode_chars[((i2 & 15) << 2) | ((i4 & Opcodes.AND_LONG_2ADDR) >>> 6)]);
            stringBuffer.append(base64_encode_chars[i4 & 63]);
        }
        return stringBuffer.toString();
    }

    public static byte[] base64_decode_url(byte[] bArr, int i) {
        byte[] bArr2 = new byte[24];
        int i2 = 0;
        int i3 = 0;
        byte b = (byte) 0;
        int i4 = 0;
        while (true) {
            int i5;
            int i6 = i - 1;
            if (i > 0) {
                int i7 = i4 + 1;
                i5 = bArr[i4];
                if (!(i5 == 0 || i5 == 95)) {
                    if (i5 == (byte) 32) {
                        i5 = 42;
                    }
                    byte b2 = base64_reverse_table_url[i5];
                    if (b2 < (byte) 0) {
                        b = b2;
                        i = i6;
                        i4 = i7;
                    } else {
                        switch (i3 % 4) {
                            case 0:
                                bArr2[i2] = (byte) (b2 << 2);
                                i5 = i2;
                                break;
                            case 1:
                                i5 = i2 + 1;
                                bArr2[i2] = (byte) (bArr2[i2] | (b2 >> 4));
                                bArr2[i5] = (byte) ((b2 & 15) << 4);
                                break;
                            case 2:
                                i5 = i2 + 1;
                                bArr2[i2] = (byte) (bArr2[i2] | (b2 >> 2));
                                bArr2[i5] = (byte) ((b2 & 3) << 6);
                                break;
                            case 3:
                                i5 = i2 + 1;
                                bArr2[i2] = (byte) (bArr2[i2] | b2);
                                break;
                            default:
                                i5 = i2;
                                break;
                        }
                        i3++;
                        i = i6;
                        i2 = i5;
                        b = b2;
                        i4 = i7;
                    }
                }
            }
            if (b == (byte) 95) {
                switch (i3 % 4) {
                    case 0:
                    case 1:
                        return null;
                    case 2:
                        i2++;
                        break;
                    case 3:
                        break;
                }
                i5 = i2 + 1;
                bArr2[i2] = (byte) 0;
            }
            return bArr2;
        }
    }

    public static void printException(Exception exception) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        exception.printStackTrace(printWriter);
        printWriter.flush();
        stringWriter.flush();
        LOGW("exception:", stringWriter.toString());
    }

    public static void printException(Exception exception, String str) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        exception.printStackTrace(printWriter);
        printWriter.flush();
        stringWriter.flush();
        LOGW("exception", stringWriter.toString(), str);
    }

    public static void printThrowable(Throwable th, String str) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        th.printStackTrace(printWriter);
        printWriter.flush();
        stringWriter.flush();
        LOGW("throwable", stringWriter.toString(), str);
    }

    public static byte[] getPkgSigFromApkName(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo.signatures == null || packageInfo.signatures.length <= 0 || packageInfo.signatures[0] == null) {
                return new byte[0];
            }
            return MD5.toMD5Byte(packageInfo.signatures[0].toByteArray());
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static byte[] getAppName(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            if (applicationInfo != null) {
                String charSequence = context.getPackageManager().getApplicationLabel(applicationInfo).toString();
                if (charSequence != null) {
                    return charSequence.getBytes();
                }
            }
        } catch (Throwable th) {
        }
        return new byte[0];
    }

    public static String get_release_time() {
        return "2017/01/03 15:17:41";
    }

    public static String getDate() {
        String str = "";
        try {
            str = "[" + System.currentTimeMillis() + "]";
        } catch (Exception e) {
        }
        return str;
    }

    public static String getThreadId() {
        return "[" + Thread.currentThread().getId() + "]";
    }

    public static String getSdkVersion() {
        return "[5]";
    }

    public static String getUser(String str) {
        if (str != null) {
            return "[" + str + "]";
        }
        return "[]";
    }

    public static boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    public static String getCurrentDay() {
        try {
            if (DAYFORMAT == null) {
                DAYFORMAT = new SimpleDateFormat("yyyyMMdd");
            }
            return DAYFORMAT.format(new Date());
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isFileExist(String str) {
        try {
            return new File(str).exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static int getFileSize(String str) {
        try {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                return (int) file.length();
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public static byte[] compress(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
            deflaterOutputStream.write(bArr);
            deflaterOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }

    public static void decompress(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            LOGI("data len:" + bArr.length);
            int i = 0;
            int i2 = 0;
            while (bArr.length > i2 + 3) {
                int buf_to_int32 = buf_to_int32(bArr, i2);
                if (bArr.length > (i2 + buf_to_int32) + 3) {
                    Object obj = new byte[buf_to_int32];
                    System.arraycopy(bArr, i2 + 4, obj, 0, buf_to_int32);
                    i2 = (i2 + 4) + buf_to_int32;
                    i++;
                    LOGI("len:" + buf_to_int32);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        InflaterInputStream inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(obj));
                        byte[] bArr2 = new byte[1024];
                        while (true) {
                            int read = inflaterInputStream.read(bArr2);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, read);
                        }
                        LOGI(i + byteArrayOutputStream.toString());
                    } catch (IOException e) {
                    }
                } else {
                    return;
                }
            }
        }
    }

    public static String getLogFileName(Context context, String str) {
        if (context == null || str == null || str.length() == 0) {
            return null;
        }
        try {
            if (ExistSDCard()) {
                return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + LOG_DIR + "/" + context.getPackageName() + "/" + str + ".log";
            }
            return context.getFilesDir().getPath() + "/" + LOG_DIR + "/" + str + ".log";
        } catch (Exception e) {
            return "";
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void writeFile(java.lang.String r4, byte[] r5) {
        /*
        r1 = oicq.wlogin_sdk.tools.util.class;
        monitor-enter(r1);
        if (r4 == 0) goto L_0x000b;
    L_0x0005:
        r0 = r4.length();	 Catch:{ all -> 0x0041 }
        if (r0 != 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);
        return;
    L_0x000d:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x003f }
        r0.<init>(r4);	 Catch:{ Exception -> 0x003f }
        r2 = r0.exists();	 Catch:{ Exception -> 0x003f }
        if (r2 != 0) goto L_0x002a;
    L_0x0018:
        r2 = r0.getParentFile();	 Catch:{ Exception -> 0x003f }
        if (r2 == 0) goto L_0x000b;
    L_0x001e:
        r3 = r2.mkdirs();	 Catch:{ Exception -> 0x003f }
        if (r3 != 0) goto L_0x002a;
    L_0x0024:
        r2 = r2.isDirectory();	 Catch:{ Exception -> 0x003f }
        if (r2 == 0) goto L_0x000b;
    L_0x002a:
        r2 = getFileSize(r4);	 Catch:{ Exception -> 0x003f }
        r3 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
        if (r2 >= r3) goto L_0x000b;
    L_0x0032:
        r2 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x003f }
        r3 = 1;
        r2.<init>(r0, r3);	 Catch:{ Exception -> 0x003f }
        r2.write(r5);	 Catch:{ Exception -> 0x003f }
        r2.close();	 Catch:{ Exception -> 0x003f }
        goto L_0x000b;
    L_0x003f:
        r0 = move-exception;
        goto L_0x000b;
    L_0x0041:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: oicq.wlogin_sdk.tools.util.writeFile(java.lang.String, byte[]):void");
    }

    public static byte[] readFile(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            File file = new File(str);
            if (!file.exists() || !file.isFile()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(str);
            int available = fileInputStream.available();
            if (available > 528384) {
                fileInputStream.close();
                return null;
            }
            byte[] bArr = new byte[available];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] readLog(Context context, String str) {
        if (context == null || str == null || str.length() == 0) {
            return null;
        }
        return readFile(getLogFileName(context, str));
    }

    public static long getFileModifyTime(String str) {
        long j = 0;
        if (!(str == null || str.length() == 0)) {
            try {
                File file = new File(str);
                if (file.exists() && file.isFile()) {
                    j = file.lastModified();
                }
            } catch (Exception e) {
            }
        }
        return j;
    }

    public static long getLogModifyTime(Context context, String str) {
        if (context == null || str == null || str.length() == 0) {
            return 0;
        }
        return getFileModifyTime(getLogFileName(context, str));
    }

    public static void deleteExpireFile(String str, int i) {
        if (str != null && str.length() != 0) {
            LOGI("file path:" + str);
            try {
                File file = new File(str);
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        int length = listFiles.length;
                        int i2 = 0;
                        while (i2 < length) {
                            if (!listFiles[i2].isDirectory() && (System.currentTimeMillis() - listFiles[i2].lastModified()) / 1000 > ((long) i)) {
                                listFiles[i2].delete();
                            }
                            i2++;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public static void deleteExpireLog(Context context) {
        if (context != null) {
            try {
                if (ExistSDCard()) {
                    deleteExpireFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + LOG_DIR + "/" + context.getPackageName(), 691200);
                    return;
                }
                deleteExpireFile(context.getFilesDir().getPath() + "/" + LOG_DIR, 259200);
            } catch (Exception e) {
            }
        }
    }

    public static KeyPair generateRSAKeyPair() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
            instance.initialize(1024);
            return instance.generateKeyPair();
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] RSAEncrypt(byte[] bArr, Key key) {
        if (bArr == null || key == null) {
            return null;
        }
        try {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, key);
            int length = bArr.length;
            int round = (int) Math.round(((double) (length / Opcodes.INVOKE_SUPER_RANGE)) + 0.5d);
            byte[] bArr2 = new byte[(128 * round)];
            int i = 0;
            int i2 = Opcodes.INVOKE_SUPER_RANGE;
            while (i < round) {
                if (length < Opcodes.INVOKE_SUPER_RANGE) {
                    i2 = length;
                }
                Object obj = new byte[i2];
                System.arraycopy(bArr, i * Opcodes.INVOKE_SUPER_RANGE, obj, 0, i2);
                System.arraycopy(instance.doFinal(obj), 0, bArr2, 128 * i, 128);
                i++;
                length -= i2;
            }
            return bArr2;
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] RSADecrypt(byte[] bArr, Key key) {
        int i = 0;
        if (bArr == null || key == null) {
            return null;
        }
        try {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, key);
            int length = bArr.length;
            Object obj = new byte[length];
            if (length % 128 != 0) {
                return null;
            }
            int i2 = 0;
            while (i < length / 128) {
                Object obj2 = new byte[128];
                System.arraycopy(bArr, i * 128, obj2, 0, 128);
                obj2 = instance.doFinal(obj2);
                System.arraycopy(obj2, 0, obj, i2, obj2.length);
                i2 += obj2.length;
                i++;
            }
            Object obj3 = new byte[i2];
            System.arraycopy(obj, 0, obj3, 0, i2);
            return obj3;
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] RSAPubKeyFromJava(byte[] bArr) {
        byte[] bArr2 = null;
        if (bArr == null) {
            return bArr2;
        }
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr));
            Object obj = new byte[]{(byte) 48, (byte) -127, (byte) -97, (byte) 48, JceStruct.SIMPLE_LIST, (byte) 6, (byte) 9, (byte) 42, (byte) -122, (byte) 72, (byte) -122, (byte) -9, JceStruct.SIMPLE_LIST, (byte) 1, (byte) 1, (byte) 1, (byte) 5, (byte) 0, (byte) 3, (byte) -127, (byte) -115, (byte) 0};
            Object encoded = generatePublic.getEncoded();
            bArr2 = new byte[(encoded.length - obj.length)];
            System.arraycopy(encoded, obj.length, bArr2, 0, bArr2.length);
            return bArr2;
        } catch (Exception e) {
            printException(e, "");
            return bArr2;
        }
    }

    public static Key RSAPubKeyFromJNI(byte[] bArr) {
        Key key = null;
        if (bArr != null) {
            Object obj = new byte[]{(byte) 48, (byte) -127, (byte) -97, (byte) 48, JceStruct.SIMPLE_LIST, (byte) 6, (byte) 9, (byte) 42, (byte) -122, (byte) 72, (byte) -122, (byte) -9, JceStruct.SIMPLE_LIST, (byte) 1, (byte) 1, (byte) 1, (byte) 5, (byte) 0, (byte) 3, (byte) -127, (byte) -115, (byte) 0};
            Object obj2 = new byte[(obj.length + bArr.length)];
            System.arraycopy(obj, 0, obj2, 0, obj.length);
            System.arraycopy(bArr, 0, obj2, obj.length, bArr.length);
            try {
                key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(obj2));
            } catch (Exception e) {
                printException(e, "");
            }
        }
        return key;
    }

    public static byte[] RSAPrivKeyFromJava(byte[] bArr) {
        byte[] bArr2 = null;
        if (bArr == null) {
            return bArr2;
        }
        try {
            PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bArr));
            Object obj = new byte[]{(byte) 48, (byte) -126, (byte) 2, (byte) 117, (byte) 2, (byte) 1, (byte) 0, (byte) 48, JceStruct.SIMPLE_LIST, (byte) 6, (byte) 9, (byte) 42, (byte) -122, (byte) 72, (byte) -122, (byte) -9, JceStruct.SIMPLE_LIST, (byte) 1, (byte) 1, (byte) 1, (byte) 5, (byte) 0, (byte) 4, (byte) -126, (byte) 2, (byte) 95};
            Object encoded = generatePrivate.getEncoded();
            bArr2 = new byte[(encoded.length - obj.length)];
            System.arraycopy(encoded, obj.length, bArr2, 0, bArr2.length);
            return bArr2;
        } catch (Exception e) {
            printException(e, "");
            return bArr2;
        }
    }

    public static Key RSAPrivKeyFromJNI(byte[] bArr) {
        Key key = null;
        if (bArr != null) {
            Object obj = new byte[]{(byte) 48, (byte) -126, (byte) 2, (byte) 117, (byte) 2, (byte) 1, (byte) 0, (byte) 48, JceStruct.SIMPLE_LIST, (byte) 6, (byte) 9, (byte) 42, (byte) -122, (byte) 72, (byte) -122, (byte) -9, JceStruct.SIMPLE_LIST, (byte) 1, (byte) 1, (byte) 1, (byte) 5, (byte) 0, (byte) 4, (byte) -126, (byte) 2, (byte) 95};
            int length = bArr.length - 607;
            obj[3] = (byte) (obj[3] + length);
            obj[25] = (byte) (length + obj[25]);
            Object obj2 = new byte[(obj.length + bArr.length)];
            System.arraycopy(obj, 0, obj2, 0, obj.length);
            System.arraycopy(bArr, 0, obj2, obj.length, bArr.length);
            try {
                key = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(obj2));
            } catch (Exception e) {
                printException(e, "");
            }
        }
        return key;
    }

    public static boolean CheckQQMiniHD(Context context) {
        if (context == null) {
            return false;
        }
        try {
            LOGI(context.getPackageManager().getPackageInfo(Constants.PACKAGE_QQ_PAD, 0).versionName, "");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean CheckMayFastLogin(Context context) {
        if (context == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.tencent.mobileqq", 0);
            if (packageInfo == null) {
                return false;
            }
            String str = packageInfo.versionName;
            if (str.compareTo(SystemUtils.QQ_VERSION_NAME_4_6_0) >= 0) {
                return true;
            }
            try {
                if (Integer.parseInt(str.substring(0, str.indexOf("."))) >= 10) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                printException(e, "");
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    public static boolean loadLibrary(String str, Context context) {
        Exception e;
        if (!loadEncryptSo) {
            return false;
        }
        if (VERSION.SDK_INT >= 23) {
            return false;
        }
        if (libwtecdh_loaded) {
            return true;
        }
        if (context == null || str == null || str.length() == 0) {
            return false;
        }
        File file;
        File file2;
        boolean z;
        try {
            file = new File(context.getFilesDir().getParent() + "/txlib/lib" + str + ".so");
            try {
                if (!file.exists()) {
                    file2 = new File(context.getFilesDir().getParent() + "/lib/lib" + str + ".so");
                    if (file2 != null && file2.exists()) {
                        System.load(file2.getAbsolutePath());
                        z = true;
                        if (!z) {
                            try {
                                System.loadLibrary(str);
                                z = true;
                            } catch (UnsatisfiedLinkError e2) {
                                LOGI("loadLibrary failed " + e2.getMessage(), "");
                            }
                        }
                        if (z) {
                            libwtecdh_loaded = z;
                            LOGI("libwtecdh loaded " + libwtecdh_loaded, "");
                        } else {
                            libwtecdh_loaded = z;
                            LOGI("libwtecdh loaded " + libwtecdh_loaded, "");
                        }
                        return z;
                    }
                    z = false;
                    if (z) {
                        System.loadLibrary(str);
                        z = true;
                    }
                    if (z) {
                        libwtecdh_loaded = z;
                        LOGI("libwtecdh loaded " + libwtecdh_loaded, "");
                    } else {
                        libwtecdh_loaded = z;
                        LOGI("libwtecdh loaded " + libwtecdh_loaded, "");
                    }
                    return z;
                }
            } catch (Exception e3) {
                e = e3;
                printException(e);
                file2 = file;
                System.load(file2.getAbsolutePath());
                z = true;
                if (z) {
                    System.loadLibrary(str);
                    z = true;
                }
                if (z) {
                    libwtecdh_loaded = z;
                    LOGI("libwtecdh loaded " + libwtecdh_loaded, "");
                } else {
                    libwtecdh_loaded = z;
                    LOGI("libwtecdh loaded " + libwtecdh_loaded, "");
                }
                return z;
            }
        } catch (Exception e4) {
            e = e4;
            file = null;
            printException(e);
            file2 = file;
            System.load(file2.getAbsolutePath());
            z = true;
            if (z) {
                System.loadLibrary(str);
                z = true;
            }
            if (z) {
                libwtecdh_loaded = z;
                LOGI("libwtecdh loaded " + libwtecdh_loaded, "");
            } else {
                libwtecdh_loaded = z;
                LOGI("libwtecdh loaded " + libwtecdh_loaded, "");
            }
            return z;
        }
        file2 = file;
        try {
            System.load(file2.getAbsolutePath());
            z = true;
        } catch (UnsatisfiedLinkError e5) {
            LOGI("loadLibrary failed " + e5.getMessage(), "");
        }
        if (z) {
            System.loadLibrary(str);
            z = true;
        }
        if (z) {
            libwtecdh_loaded = z;
            LOGI("libwtecdh loaded " + libwtecdh_loaded, "");
        } else {
            libwtecdh_loaded = z;
            LOGI("libwtecdh loaded " + libwtecdh_loaded, "");
        }
        return z;
    }

    public static Bundle packBundle(byte[][] bArr) {
        Bundle bundle = new Bundle();
        if (bArr != null && bArr.length > 0) {
            bundle.putInt("len", bArr.length);
            for (int i = 0; i < bArr.length; i++) {
                bundle.putByteArray(String.valueOf(i), bArr[i]);
            }
        }
        if (bundle.isEmpty()) {
            return null;
        }
        return bundle;
    }

    public static byte[][] unpackBundle(Bundle bundle) {
        int i = 0;
        if (bundle == null) {
            return (byte[][]) null;
        }
        int i2 = bundle.getInt("len");
        byte[][] bArr = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{i2, 0});
        while (i < i2) {
            bArr[i] = bundle.getByteArray(String.valueOf(i));
            i++;
        }
        return bArr;
    }

    public static String getChannelId(Context context, String str) {
        int i = 0;
        if (str == null || str.length() == 0) {
            str = context.getPackageName();
        }
        String str2 = "";
        String str3 = "";
        try {
            str3 = context.getPackageManager().getPackageInfo(str, 0).applicationInfo.sourceDir;
            LOGI("pkg " + str + " path " + (str3 == null ? "null" : str3), "");
            if (!(str3 == null || str3.length() == 0)) {
                long j;
                byte[] bArr = new byte[]{(byte) 80, (byte) 75, (byte) 5, (byte) 6};
                FileInputStream fileInputStream = new FileInputStream(str3);
                long available = (long) fileInputStream.available();
                Object obj = new byte[((int) (available - fileInputStream.skip(available - 256)))];
                fileInputStream.read(obj);
                fileInputStream.close();
                while (i < obj.length - 4) {
                    if (obj[i] == bArr[0] && obj[i + 1] == bArr[1] && obj[i + 2] == bArr[2] && obj[i + 3] == bArr[3]) {
                        j = (long) i;
                        break;
                    }
                    i++;
                }
                j = -1;
                if (j != -1) {
                    i = ((int) j) + 20;
                    i = (obj[i + 1] << 8) + obj[i];
                    if (i != 0) {
                        Object obj2 = new byte[i];
                        System.arraycopy(obj, (int) (j + 20), obj2, 0, i);
                        Matcher matcher = Pattern.compile("channelId=(\\S+)").matcher(new String(obj2));
                        if (matcher.find()) {
                            str2 = matcher.group(1);
                        }
                        LOGI("found comment " + new String(obj2) + " channelId:" + str2, "");
                    }
                }
            }
        } catch (Exception e) {
            printException(e, "");
        }
        return str2;
    }

    public static boolean shouldKick(int i) {
        return (i == -1000 || i == 257) ? false : true;
    }

    public static long constructSalt() {
        return (((long) get_rand_32()) << 32) + ((long) get_rand_32());
    }

    public static byte[] getRequestInitTime() {
        byte[] bArr = new byte[4];
        int64_to_buf32(bArr, 0, (System.currentTimeMillis() / 1000) + u.ac);
        return bArr;
    }

    public static String getLanguage(Context context) {
        String country = context.getResources().getConfiguration().locale.getCountry();
        if (country.equals("CN")) {
            return "CN";
        }
        if (country.equals("TW")) {
            return "TW";
        }
        return "EN";
    }
}
