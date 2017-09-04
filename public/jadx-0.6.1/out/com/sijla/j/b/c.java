package com.sijla.j.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.sijla.j.a.a;
import com.sijla.j.b;
import com.sijla.j.f;
import com.sijla.j.g;
import com.sijla.j.i;
import com.sijla.j.j;
import com.tencent.mid.api.MidEntity;
import com.tencent.open.SocialConstants;
import com.tencent.qalsdk.im_open.http;
import com.tencent.qalsdk.sdk.v;
import java.util.Date;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public class c {
    private static boolean c(Context context) {
        String b = b.b();
        if (b.equals((String) j.b(context, "xdd", ""))) {
            return false;
        }
        j.a(context, "xdd", b);
        return true;
    }

    public static void a(final Context context, final JSONObject jSONObject) {
        final String optString = jSONObject.optString("xurl");
        Object obj = (!b.a(optString) && c(context) && a.b(context)) ? 1 : null;
        if (obj != null) {
            com.sijla.c.c.a(new Runnable() {
                public void run() {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("appid", context.getPackageName());
                        jSONObject.put("appver", a.f(context));
                        jSONObject.put("did", a.i(context));
                        jSONObject.put("model", Build.MODEL);
                        g.a(optString, jSONObject, new g.a(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void a(String str, JSONObject jSONObject) {
                                if (jSONObject != null) {
                                    try {
                                        int i = jSONObject.getInt("status");
                                        f.a("xapp status = " + i);
                                        if (1 == i) {
                                            long optLong = jSONObject.optLong(MidEntity.TAG_TIMESTAMPS, System.currentTimeMillis());
                                            JSONArray jSONArray = jSONObject.getJSONArray("data");
                                            if (jSONArray != null) {
                                                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                                    JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                                    String optString = jSONObject2.optString("xver", "");
                                                    String string = jSONObject2.getString("appid");
                                                    int optInt = jSONObject2.optInt("mode", 0);
                                                    if ("".equals(optString)) {
                                                        optString = jSONObject2.getString("appkey");
                                                        String string2 = jSONObject2.getString(SocialConstants.PARAM_URL);
                                                        String string3 = jSONObject2.getString("appver");
                                                        if ((a.e(context, string) || 1 == optInt) && c.a(string2, "p=" + optString + "&etype=2&msg=" + c.c(context, string, optString, optLong, string3))) {
                                                            c.b(context, jSONObject, optString, string, string3);
                                                        }
                                                    } else if ("xp2".equals(optString)) {
                                                        f.c("--------> xp2 xp2");
                                                        if (a.e(context, string) || 1 == optInt) {
                                                            if (new d(context, jSONObject2).b()) {
                                                                c.b(context, jSONObject, "xp2", string, jSONObject2.optString("appver"));
                                                            } else {
                                                                f.a("xAppAgent2.uploadData() fail");
                                                            }
                                                        }
                                                    } else {
                                                        continue;
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            public void a(String str) {
                            }
                        }, true);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    private static void b(Context context, JSONObject jSONObject, String str, String str2, String str3) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            String[] m = b.m();
            jSONObject3.put(MidEntity.TAG_TIMESTAMPS, m[1]);
            jSONObject3.put("time", b.a());
            jSONObject3.put("tappkey", str);
            String str4 = "tappver";
            if ("0".equals(str3)) {
                str3 = a.a(str2, context);
            }
            jSONObject3.put(str4, str3);
            jSONObject3.put("tappid", str2);
            jSONObject3.put("appid", context.getPackageName());
            jSONObject3.put("appver", a.a(context.getPackageName(), context));
            jSONObject3.put("qid", i.b(context));
            jSONObject3.put("did", a.i(context));
            jSONObject2.put("ln", "ar");
            jSONObject2.put("s1", m[1]);
            jSONObject2.put("s2", com.sijla.f.b.a(m[0], jSONObject3.toString()));
            String optString = jSONObject.optString("xdata");
            if (!b.a(optString)) {
                g.a(optString, jSONObject2, new g.a() {
                    public void a(String str, JSONObject jSONObject) {
                        f.a("report xapp success");
                    }

                    public void a(String str) {
                        f.a("report xapp error:" + str);
                    }
                });
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static String c(Context context, String str, String str2, long j, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            jSONObject.put("event_list", jSONArray);
            jSONObject.put("header", a(context, str, str2, j, str3));
            jSONObject.put("open_count", 1);
            jSONObject.put("page_count", 1);
            jSONObject.put("run_time", b.a(20, http.Internal_Server_Error));
            jSONObject.put("page_list", jSONArray);
            jSONObject.put("lat", "");
            jSONObject.put("lng", "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return b.a(jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static JSONObject a(Context context, String str, String str2, long j, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            jSONObject.put("app_key", str2);
            jSONObject.put("uid", " " + i.c(context));
            jSONObject.put("uidtype", Build.MANUFACTURER + " " + Build.MODEL);
            jSONObject.put("ip", "");
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            jSONObject.put(MidEntity.TAG_IMEI, a.i(context));
            jSONObject.put("appid", str);
            String str4 = "appver";
            if ("0".equals(str3)) {
                str3 = a.a(str, context);
            }
            jSONObject.put(str4, str3);
            jSONObject.put("mac_hash", "");
            jSONObject.put("network", b(context));
            str4 = a(context);
            if (str4.equals("")) {
                jSONObject.put("carrier", "--");
                jSONObject.put("country", "--");
            } else {
                jSONObject.put("carrier", str4.substring(3));
                jSONObject.put("country", str4.substring(0, 3));
            }
            jSONObject.put("city", displayMetrics.heightPixels + v.n + displayMetrics.widthPixels);
            jSONObject.put("timezone", a());
            jSONObject.put("os_name", "Android");
            jSONObject.put("os_ver", VERSION.RELEASE);
            jSONObject.put("sdk_ver", "2.3.3");
            jSONObject.put("channel", "");
            jSONObject.put("col1", Secure.getString(context.getContentResolver(), "android_id"));
            jSONObject.put("col2", deviceId);
            jSONObject.put("col3", ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress());
            jSONObject.put(MidEntity.TAG_TIMESTAMPS, (j / 1000) + "");
            jSONObject.put("dd", "3" + b.a(j).substring(1));
            jSONObject.put("lac_cid", "2_2");
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public static String a(Context context) {
        String str = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (5 == telephonyManager.getSimState()) {
                return telephonyManager.getSimOperator();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String a() {
        return (TimeZone.getDefault().getOffset(new Date().getTime()) / 1000) + "";
    }

    public static String b(Context context) {
        String str = "UNKNOWN";
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            State state = connectivityManager.getNetworkInfo(0).getState();
            State state2 = connectivityManager.getNetworkInfo(1).getState();
            if (state == State.CONNECTED || state == State.CONNECTING) {
                return "3G";
            }
            if (state2 == State.CONNECTED || state2 == State.CONNECTING) {
                return "WIFI";
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r7, java.lang.String r8) {
        /*
        r1 = 0;
        r3 = 0;
        r2 = 1;
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x005d, all -> 0x006f }
        r0.<init>(r7);	 Catch:{ Exception -> 0x005d, all -> 0x006f }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x005d, all -> 0x006f }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x005d, all -> 0x006f }
        r4 = 1;
        r0.setDoInput(r4);	 Catch:{ Exception -> 0x0091, all -> 0x0080 }
        r4 = 1;
        r0.setDoOutput(r4);	 Catch:{ Exception -> 0x0091, all -> 0x0080 }
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setConnectTimeout(r4);	 Catch:{ Exception -> 0x0091, all -> 0x0080 }
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setReadTimeout(r4);	 Catch:{ Exception -> 0x0091, all -> 0x0080 }
        if (r8 == 0) goto L_0x002b;
    L_0x0022:
        r4 = "";
        r4 = r4.equals(r8);	 Catch:{ Exception -> 0x0091, all -> 0x0080 }
        if (r4 == 0) goto L_0x004a;
    L_0x002b:
        r4 = "GET";
        r0.setRequestMethod(r4);	 Catch:{ Exception -> 0x0091, all -> 0x0080 }
        r4 = r1;
    L_0x0032:
        r1 = r0.getResponseCode();	 Catch:{ Exception -> 0x0093, all -> 0x008c }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 != r5) goto L_0x0096;
    L_0x003a:
        r1 = r2;
    L_0x003b:
        r5 = new java.io.Closeable[r2];
        r5[r3] = r4;
        com.sijla.j.b.a(r5);
        r2 = new java.net.HttpURLConnection[r2];
        r2[r3] = r0;
        com.sijla.j.b.a(r2);
    L_0x0049:
        return r1;
    L_0x004a:
        r4 = "POST";
        r0.setRequestMethod(r4);	 Catch:{ Exception -> 0x0091, all -> 0x0080 }
        r1 = r0.getOutputStream();	 Catch:{ Exception -> 0x0091, all -> 0x0080 }
        r4 = r8.getBytes();	 Catch:{ Exception -> 0x0091, all -> 0x0086 }
        r1.write(r4);	 Catch:{ Exception -> 0x0091, all -> 0x0086 }
        r4 = r1;
        goto L_0x0032;
    L_0x005d:
        r0 = move-exception;
        r0 = r1;
    L_0x005f:
        r4 = new java.io.Closeable[r2];
        r4[r3] = r1;
        com.sijla.j.b.a(r4);
        r1 = new java.net.HttpURLConnection[r2];
        r1[r3] = r0;
        com.sijla.j.b.a(r1);
        r1 = r3;
        goto L_0x0049;
    L_0x006f:
        r0 = move-exception;
        r4 = r1;
    L_0x0071:
        r5 = new java.io.Closeable[r2];
        r5[r3] = r4;
        com.sijla.j.b.a(r5);
        r2 = new java.net.HttpURLConnection[r2];
        r2[r3] = r1;
        com.sijla.j.b.a(r2);
        throw r0;
    L_0x0080:
        r4 = move-exception;
        r6 = r4;
        r4 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0071;
    L_0x0086:
        r4 = move-exception;
        r6 = r4;
        r4 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0071;
    L_0x008c:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0071;
    L_0x0091:
        r4 = move-exception;
        goto L_0x005f;
    L_0x0093:
        r1 = move-exception;
        r1 = r4;
        goto L_0x005f;
    L_0x0096:
        r1 = r3;
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sijla.j.b.c.a(java.lang.String, java.lang.String):boolean");
    }
}
