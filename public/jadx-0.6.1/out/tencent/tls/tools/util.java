package tencent.tls.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.etrump.jni.ETConverter;
import com.tencent.mid.api.MidEntity;
import com.tencent.smtt.sdk.WebView;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.zip.DeflaterOutputStream;
import tencent.tls.report.QLog;
import tencent.tls.request.req_global;

public class util {
    public static final long BUILD_TIME = 1460985272;
    public static final int D = 2;
    private static SimpleDateFormat DAYFORMAT = null;
    public static final int I = 1;
    public static String LIBWT = "wtcrypto";
    public static boolean LOGCAT_OUT = true;
    private static final String LOG_DIR = "tencent/tls";
    private static int LOG_LEVEL = 1;
    static final int MAX_CONTENT_SIZE = 4096;
    private static final int MAX_FILE_SIZE = 102400;
    public static int MAX_NAME_LEN = 128;
    @SuppressLint({"NewApi"})
    private static int MODE_MULTI_PROCESS = 0;
    public static final String SDK_VERSION = "1.1.1794";
    private static String SPFileName = "TLS_DEVICE_INFO";
    public static final int SSO_VERSION = 1;
    public static final long SVN_VER = 1532;
    public static final int S_ROLL_BACK = 180;
    private static final String TAG = "tls_sdk";
    public static final int W = 0;
    static final char[] base64_encode_chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    static final char base64_pad_url = '_';
    static final short[] base64_reverse_table_url = new short[]{(short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) 62, (short) -1, (short) -1, (short) 63, (short) -1, (short) -1, (short) 52, (short) 53, (short) 54, (short) 55, (short) 56, (short) 57, (short) 58, (short) 59, (short) 60, (short) 61, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) 0, (short) 1, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7, (short) 8, (short) 9, (short) 10, (short) 11, (short) 12, (short) 13, (short) 14, (short) 15, (short) 16, (short) 17, (short) 18, (short) 19, (short) 20, (short) 21, (short) 22, (short) 23, (short) 24, (short) 25, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) 26, (short) 27, (short) 28, (short) 29, (short) 30, (short) 31, (short) 32, (short) 33, (short) 34, (short) 35, (short) 36, (short) 37, (short) 38, (short) 39, (short) 40, (short) 41, (short) 42, (short) 43, (short) 44, (short) 45, (short) 46, (short) 47, (short) 48, (short) 49, (short) 50, (short) 51, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1, (short) -1};
    public static int connRetryTimes = 3;
    private static boolean libwtecdh_loaded = false;
    public static String soLoadPath = "";

    public static final class APNName {
        public static final String NAME_3GWAP = "3gwap";
        public static final String NAME_CMWAP = "cmwap";
        public static final String NAME_CTWAP = "ctwap";
        public static final String NAME_UNIWAP = "uniwap";
    }

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

    public static byte[] getS2(byte[] bArr, long j) {
        byte[] bArr2 = new byte[24];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        int64_to_buf(bArr2, 16, j);
        return MD5.toMD5Byte(bArr2);
    }

    public static long get_server_cur_time() {
        return req_global.get_server_cur_time();
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

    public static byte[] get_IMEI(Context context) {
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
            i = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).getInt("network_type", 0);
        } catch (Throwable th) {
        }
        return i;
    }

    public static void save_network_type(Context context, int i) {
        if (context != null) {
            Editor edit = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).edit();
            edit.putInt("network_type", i);
            edit.commit();
        }
    }

    public static void set_server_host(Context context, byte[] bArr, String str) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).edit();
            edit.putString(str, new String(bArr));
            edit.commit();
        }
    }

    public static String get_server_host(Context context, String str) {
        try {
            return context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).getString(str, "");
        } catch (Throwable th) {
            return null;
        }
    }

    public static void set_net_retry_type(Context context, int i) {
        if (context != null) {
            Editor edit = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).edit();
            edit.putInt("netretry_type", i);
            edit.commit();
        }
    }

    public static int get_net_retry_type(Context context) {
        int i = 0;
        try {
            i = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).getInt("netretry_type", 0);
        } catch (Throwable th) {
        }
        return i;
    }

    public static boolean is_wap_retry(Context context) {
        return get_net_retry_type(context) != 0;
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

    public static String get_proxy_ip() {
        return System.getProperty("http.proxyHost");
    }

    public static int get_proxy_port() {
        try {
            return Integer.parseInt(System.getProperty("http.proxyPort"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void save_imei(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).edit();
            edit.putString(MidEntity.TAG_IMEI, buf_to_string(bArr));
            edit.commit();
        }
    }

    public static byte[] get_saved_imei(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).getString(MidEntity.TAG_IMEI, ""));
        } catch (Throwable th) {
            printThrowable(th, "");
        }
        return bArr;
    }

    public static void save_cur_flag(Context context, int i) {
        if (context != null) {
            Editor edit = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).edit();
            edit.putInt("last_flag", i);
            edit.commit();
        }
    }

    public static int get_last_flag(Context context) {
        int i = 0;
        try {
            i = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).getInt("last_flag", 0);
        } catch (Throwable th) {
        }
        return i;
    }

    public static void save_cur_mac(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).edit();
            edit.putString("last_mac", buf_to_string(bArr));
            edit.commit();
        }
    }

    public static byte[] get_last_mac(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).getString("last_mac", ""));
        } catch (Throwable th) {
        }
        if (bArr == null || bArr.length <= 0) {
            return new byte[0];
        }
        return bArr;
    }

    public static void save_cur_imei(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).edit();
            edit.putString("last_imei", buf_to_string(bArr));
            edit.commit();
        }
    }

    public static byte[] get_last_imei(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).getString("last_imei", ""));
        } catch (Throwable th) {
        }
        if (bArr == null || bArr.length <= 0) {
            return new byte[0];
        }
        return bArr;
    }

    public static void save_cur_guid(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).edit();
            edit.putString("last_guid", buf_to_string(bArr));
            edit.commit();
        }
    }

    public static byte[] get_last_guid(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = string_to_buf(context.getSharedPreferences(SPFileName, MODE_MULTI_PROCESS).getString("last_guid", ""));
        } catch (Throwable th) {
        }
        if (bArr == null || bArr.length <= 0) {
            return new byte[0];
        }
        return bArr;
    }

    public static void save_file_imei(Context context, byte[] bArr) {
        Exception e;
        Throwable th;
        if (context != null && bArr != null && bArr.length > 0) {
            FileOutputStream fileOutputStream = null;
            try {
                File file = new File(context.getFilesDir().getAbsolutePath() + "/tls_device.dat");
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
            if (LOG_LEVEL >= 1 && LOGCAT_OUT) {
                Log.i("tls_sdk" + getLineInfo(2), str);
            }
        } catch (Exception e) {
        }
    }

    public static void LOGI(String str, String str2) {
        try {
            if (LOG_LEVEL >= 1) {
                if (LOGCAT_OUT) {
                    Log.i("tls_sdk" + getLineInfo(2), str);
                }
                FileTracer.writeLog(req_global._context, str2, str);
            }
        } catch (Exception e) {
        }
    }

    public static void LOGW(String str, String str2, String str3) {
        try {
            if (LOG_LEVEL >= 0) {
                if (LOGCAT_OUT) {
                    Log.w("tls_sdk" + getLineInfo(2), str + ":" + str2);
                }
                FileTracer.writeLog(req_global._context, str3, str + ":" + str2);
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

    public static String get_release_time() {
        return "2015/11/09 17:10:19";
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
        return "[1]";
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

    public static String getLogFileName(Context context, String str) {
        if (context == null || str == null || str.length() == 0) {
            return null;
        }
        try {
            if (ExistSDCard()) {
                return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + LOG_DIR + "/" + context.getPackageName() + "/" + base64_encode(str.getBytes());
            }
            return context.getFilesDir().getPath() + "/" + LOG_DIR + "/" + base64_encode(str.getBytes());
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void writeFile(java.lang.String r4, byte[] r5) {
        /*
        r1 = tencent.tls.tools.util.class;
        monitor-enter(r1);
        if (r4 == 0) goto L_0x000b;
    L_0x0005:
        r0 = r4.length();	 Catch:{ all -> 0x0042 }
        if (r0 != 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);
        return;
    L_0x000d:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0040 }
        r0.<init>(r4);	 Catch:{ Exception -> 0x0040 }
        r2 = r0.exists();	 Catch:{ Exception -> 0x0040 }
        if (r2 != 0) goto L_0x002a;
    L_0x0018:
        r2 = r0.getParentFile();	 Catch:{ Exception -> 0x0040 }
        if (r2 == 0) goto L_0x000b;
    L_0x001e:
        r3 = r2.mkdirs();	 Catch:{ Exception -> 0x0040 }
        if (r3 != 0) goto L_0x002a;
    L_0x0024:
        r2 = r2.isDirectory();	 Catch:{ Exception -> 0x0040 }
        if (r2 == 0) goto L_0x000b;
    L_0x002a:
        r2 = getFileSize(r4);	 Catch:{ Exception -> 0x0040 }
        r3 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;
        if (r2 >= r3) goto L_0x000b;
    L_0x0033:
        r2 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0040 }
        r3 = 1;
        r2.<init>(r0, r3);	 Catch:{ Exception -> 0x0040 }
        r2.write(r5);	 Catch:{ Exception -> 0x0040 }
        r2.close();	 Catch:{ Exception -> 0x0040 }
        goto L_0x000b;
    L_0x0040:
        r0 = move-exception;
        goto L_0x000b;
    L_0x0042:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: tencent.tls.tools.util.writeFile(java.lang.String, byte[]):void");
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

    public static boolean loadLibrary(String str, Context context) {
        boolean z = true;
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
        try {
            file = new File(soLoadPath + "/lib" + str + ".so");
        } catch (Exception e) {
            file = null;
        }
        if (file != null && file.exists()) {
            try {
                System.load(file.getAbsolutePath());
            } catch (UnsatisfiedLinkError e2) {
            }
            libwtecdh_loaded = z;
            QLog.i("libwtcrypto loaded " + libwtecdh_loaded);
            return z;
        }
        try {
            file2 = new File(context.getFilesDir().getParent() + "/lib/lib" + str + ".so");
        } catch (Exception e3) {
            file2 = file;
        }
        if (file2 != null && file2.exists()) {
            try {
                System.load(file2.getAbsolutePath());
            } catch (UnsatisfiedLinkError e4) {
            }
            libwtecdh_loaded = z;
            QLog.i("libwtcrypto loaded " + libwtecdh_loaded);
            return z;
        }
        try {
            System.loadLibrary(str);
        } catch (UnsatisfiedLinkError e5) {
            z = false;
        }
        libwtecdh_loaded = z;
        QLog.i("libwtcrypto loaded " + libwtecdh_loaded);
        return z;
    }

    public static boolean checkInvalid(String str) {
        return str == null || str.length() <= 0;
    }
}
