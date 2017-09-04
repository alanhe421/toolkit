package com.qrcomic.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/* compiled from: NetworkUtil */
public class f {
    public static boolean a = false;
    private static int b = 0;

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static boolean b(Context context) {
        return a(context);
    }

    public static boolean c(Context context) {
        return d.a(context);
    }

    public static int d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return 0;
            }
            int i;
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                i = 1;
            } else {
                if (type == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    if (!((TelephonyManager) context.getSystemService("phone")).isNetworkRoaming()) {
                        switch (subtype) {
                            case 1:
                            case 2:
                            case 4:
                            case 7:
                            case 11:
                                i = 2;
                                break;
                            case 3:
                            case 5:
                            case 6:
                            case 8:
                            case 9:
                            case 10:
                                i = 3;
                                break;
                            case 13:
                                i = 4;
                                break;
                        }
                    }
                    i = 2;
                }
                i = 0;
            }
            return i;
        } catch (Exception e) {
            if (!g.a()) {
                return 0;
            }
            g.b("NETWORK_STATUS", g.d, e.toString());
            return 0;
        } catch (Throwable th) {
            if (!g.a()) {
                return 0;
            }
            g.b("NETWORK_STATUS", g.d, th.toString());
            return 0;
        }
    }

    public static String e(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == 0) {
                try {
                    Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                    while (networkInterfaces.hasMoreElements()) {
                        Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                            if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            } else if (activeNetworkInfo.getType() == 1) {
                return a(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress());
            }
        }
        return null;
    }

    public static String a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static String b(int i) {
        String str = "";
        switch (i) {
            case 0:
                return "unknow";
            case 1:
                return "wifi";
            case 2:
                return "2g";
            case 3:
                return "3g";
            case 4:
                return "4g";
            default:
                return str;
        }
    }
}
