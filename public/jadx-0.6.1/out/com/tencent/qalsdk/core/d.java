package com.tencent.qalsdk.core;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.tencent.mid.api.MidEntity;
import com.tencent.qalsdk.util.MD5;
import com.tencent.qalsdk.util.QLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: GuidUtil */
public class d {
    static int a = 0;
    static int b = 0;
    static int c = 0;
    static int d = 0;
    static byte[] e = new byte[0];
    public static Context f = null;
    private static final String g = "imsdk.GuidUtil";

    public static void a(Context context) {
        f = context;
        Object d = d(context);
        Object b = b(context);
        if (d == null || d.length <= 0) {
            if (b == null || b.length <= 0) {
                b = new String("%4;7t>;28<fc.5*6").getBytes();
                b = 0;
                d = 20;
            } else {
                b = 1;
                d = 17;
            }
            b(context, b);
            c = 0;
            a = 1;
        } else {
            byte[] bytes;
            if (b == null || b.length <= 0) {
                bytes = new String("%4;7t>;28<fc.5*6").getBytes();
            }
            if (Arrays.equals(bytes, d)) {
                c = 0;
            } else {
                c = 1;
            }
            b = 1;
            a = 0;
            d = 1;
            b = d;
        }
        e = (byte[]) b.clone();
    }

    public static byte[] a() {
        if (e == null || e.length <= 0) {
            return null;
        }
        Object obj = new byte[e.length];
        System.arraycopy(e, 0, obj, 0, e.length);
        return obj;
    }

    public static byte[] b(Context context) {
        String str = null;
        try {
            String deviceId;
            WifiManager wifiManager;
            WifiInfo connectionInfo;
            String str2;
            if (VERSION.SDK_INT < 23 || context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                    wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager != null) {
                        connectionInfo = wifiManager.getConnectionInfo();
                        if (connectionInfo != null) {
                            str = connectionInfo.getMacAddress();
                        }
                    }
                    str2 = "";
                    if (deviceId != null) {
                        str2 = str2 + deviceId;
                    }
                    if (str != null) {
                        str2 = str2 + str;
                    }
                    return str2.length() > 0 ? new byte[0] : MD5.toMD5Byte(str2.getBytes());
                }
            }
            deviceId = null;
            wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    str = connectionInfo.getMacAddress();
                }
            }
            str2 = "";
            if (deviceId != null) {
                str2 = str2 + deviceId;
            }
            if (str != null) {
                str2 = str2 + str;
            }
            if (str2.length() > 0) {
            }
        } catch (Throwable th) {
            return new byte[0];
        }
    }

    public static byte a(byte b) {
        if (b >= (byte) 48 && b <= (byte) 57) {
            return (byte) (b - 48);
        }
        if (b < (byte) 97 || b > (byte) 102) {
            return (b < (byte) 65 || b > (byte) 70) ? (byte) 0 : (byte) ((b - 65) + 10);
        } else {
            return (byte) ((b - 97) + 10);
        }
    }

    public static byte[] a(String str) {
        int i = 0;
        if (str == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[(str.length() / 2)];
        while (i < str.length() / 2) {
            bArr[i] = (byte) ((a((byte) str.charAt(i * 2)) << 4) + a((byte) str.charAt((i * 2) + 1)));
            i++;
        }
        return bArr;
    }

    public static byte[] c(Context context) {
        byte[] bArr = new byte[0];
        try {
            bArr = a(context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).getString(MidEntity.TAG_IMEI, new String("")));
        } catch (Throwable th) {
            QLog.e(g, 1, "exception: " + th.toString());
        }
        return bArr;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            str = (str + Integer.toHexString((bArr[i] >> 4) & 15)) + Integer.toHexString(bArr[i] & 15);
        }
        return str;
    }

    public static void a(Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            Editor edit = context.getSharedPreferences("WLOGIN_DEVICE_INFO", 0).edit();
            edit.putString(MidEntity.TAG_IMEI, a(bArr));
            edit.commit();
        }
    }

    public static void b(Context context, byte[] bArr) {
        Exception e;
        Throwable th;
        if (context != null && bArr != null && bArr.length > 0) {
            FileOutputStream fileOutputStream = null;
            try {
                File file = new File(context.getFilesDir().getAbsolutePath() + "/" + "wlogin_device.dat");
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
                            QLog.e(g, 1, "exception: " + e.toString());
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                    QLog.e(g, 1, "exception: " + e3.toString());
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4) {
                                    QLog.e(g, 1, "exception: " + e4.toString());
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
                a(context, bArr);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e32) {
                        QLog.e(g, 1, "exception: " + e32.toString());
                    }
                }
            } catch (Exception e5) {
                e = e5;
                QLog.e(g, 1, "exception: " + e.toString());
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        }
    }

    public static byte[] d(Context context) {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        byte[] bArr = null;
        if (context != null) {
            byte[] bArr2 = new byte[0];
            try {
                File file = new File(context.getFilesDir().getAbsolutePath() + "/" + "wlogin_device.dat");
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
                            QLog.e(g, 1, "exception: " + e.toString());
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                    bArr = bArr2;
                                } catch (IOException e3) {
                                    QLog.e(g, 1, "exception: " + e3.toString());
                                    bArr = bArr2;
                                }
                                bArr = c(context);
                                b(context, bArr);
                                return bArr;
                            }
                            bArr = bArr2;
                            bArr = c(context);
                            b(context, bArr);
                            return bArr;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e4) {
                                    QLog.e(g, 1, "exception: " + e4.toString());
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
                    } catch (IOException e32) {
                        QLog.e(g, 1, "exception: " + e32.toString());
                        bArr = bArr2;
                    }
                    if (bArr == null || bArr.length <= 0) {
                        bArr = c(context);
                        if (bArr != null && bArr.length > 0) {
                            b(context, bArr);
                        }
                    }
                }
            } catch (Exception e5) {
                Exception exception = e5;
                fileInputStream = null;
                e = exception;
                QLog.e(g, 1, "exception: " + e.toString());
                if (fileInputStream != null) {
                    fileInputStream.close();
                    bArr = bArr2;
                    bArr = c(context);
                    b(context, bArr);
                    return bArr;
                }
                bArr = bArr2;
                bArr = c(context);
                b(context, bArr);
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
            bArr = c(context);
            b(context, bArr);
        }
        return bArr;
    }
}
