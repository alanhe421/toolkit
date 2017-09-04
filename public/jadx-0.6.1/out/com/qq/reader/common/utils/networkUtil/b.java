package com.qq.reader.common.utils.networkUtil;

import android.net.wifi.WifiManager;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.e.a;
import com.qq.reader.common.monitor.f;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/* compiled from: DeviceIPUtil */
public class b {
    public static String a() {
        StringBuilder stringBuilder = new StringBuilder("IP : ");
        switch (a.b()) {
            case 1:
                stringBuilder.append(c());
                break;
            case 2:
            case 3:
            case 4:
                stringBuilder.append(b());
                break;
            default:
                stringBuilder.append("none");
                break;
        }
        return stringBuilder.toString();
    }

    public static String b() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
            return "none";
        } catch (SocketException e) {
            f.d("WifiPreference IpAddress", e.toString());
            return "error";
        }
    }

    private static String c() {
        WifiManager wifiManager = (WifiManager) ReaderApplication.getApplicationImp().getSystemService("wifi");
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        return a(wifiManager.getConnectionInfo().getIpAddress());
    }

    private static String a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }
}
