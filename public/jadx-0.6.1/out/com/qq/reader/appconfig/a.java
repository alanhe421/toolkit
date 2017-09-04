package com.qq.reader.appconfig;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.BookShelfFragment;
import com.qq.reader.activity.LocalBookActivity;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.am;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.search.ISearchParamCollection;
import com.qq.reader.module.bookstore.search.SearchHotWords;
import com.qq.reader.module.feed.card.FeedRookieEntranceCard;
import com.qq.reader.module.feed.loader.h;
import com.qq.reader.module.game.data.GameTopBannerData;
import com.tencent.beacon.event.UserAction;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import qalsdk.n;

/* compiled from: Config */
public class a {
    public static Context a = null;
    public static boolean b = false;
    public static boolean c = true;
    public static boolean d = false;

    /* compiled from: Config */
    public static class a {
        public static String a;
        public static String b;

        public static void a(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("patch_reportid", str));
        }

        public static String a(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("patch_reportid", "");
        }

        public static void a(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("patch_restart", z));
        }

        public static boolean b(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("patch_restart", false);
        }

        public static void b(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("patch_clean", str));
            if (!TextUtils.isEmpty(str)) {
                a(context, true);
            }
        }

        public static String c(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("patch_clean", "");
        }

        public static void c(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("patch_disabled", str));
        }

        public static String d(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("patch_disabled", "");
        }
    }

    /* compiled from: Config */
    public static class b {
        public static void a(Context context, String str, float f) {
            context.getSharedPreferences("REDPACKET_CONFIG", 0).edit().putFloat(str, f).commit();
        }

        public static void a(Context context, String str, int i) {
            context.getSharedPreferences("REDPACKET_CONFIG", 0).edit().putInt(str, i).commit();
        }

        public static void a(Context context, String str, String str2) {
            context.getSharedPreferences("REDPACKET_CONFIG", 0).edit().putString(str, str2).commit();
        }

        public static String b(Context context, String str, String str2) {
            return context.getSharedPreferences("REDPACKET_CONFIG", 0).getString(str, str2);
        }

        public static int b(Context context, String str, int i) {
            return context.getSharedPreferences("REDPACKET_CONFIG", 0).getInt(str, i);
        }

        public static float b(Context context, String str, float f) {
            return context.getSharedPreferences("REDPACKET_CONFIG", 0).getFloat(str, f);
        }
    }

    /* compiled from: Config */
    public static class c {
        public static int a = 0;
        public static String b = null;
        public static String c = null;
        public static String d = null;
        public static String e = "";
        public static String f = "";
        public static String g = "";
        public static long h = 0;

        public static int a(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("versionCode", 0);
        }

        public static void b(Context context) {
            a.b(context.getSharedPreferences("SETTING", 1).edit().putInt("versionCode", ao.v(context)));
        }

        public static long c(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("dgradeservice_start_time", 0);
        }

        public static void a(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("dgradeservice_start_time", j));
        }

        public static String d(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("socket_ports", "");
        }

        public static void a(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("socket_ports", str));
        }

        public static String e(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("socket_ip", "");
        }

        public static void b(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("socket_ip", str));
        }

        public static long f(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("dgradeservice_end_time", 0);
        }

        public static void b(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("dgradeservice_end_time", j));
        }

        public static String g(Context context) {
            return context.getSharedPreferences("SETTING", 1).getString("VERSION", "1.0");
        }

        public static void h(Context context) {
            a.b(context.getSharedPreferences("SETTING", 1).edit().putString("VERSION", "qqreader_6.5.3.0888_android"));
        }

        public static void a(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 1).edit().putBoolean("dyn_splash", z));
        }

        public static void i(Context context) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putLong("UPDATE_DATE", Calendar.getInstance().getTimeInMillis()));
        }

        public static int j(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("UPDATE_CODE", 0);
        }

        public static void a(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("UPDATE_CODE", i));
        }

        public static String k(Context context) {
            return context.getSharedPreferences("SETTING", 1).getString("UPDATE_URL", "");
        }

        public static void c(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putString("UPDATE_URL", str));
        }

        public static String l(Context context) {
            return context.getSharedPreferences("SETTING", 1).getString("UPDATE_VERSION", "");
        }

        public static void d(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putString("UPDATE_VERSION", str));
        }

        public static String m(Context context) {
            return context.getSharedPreferences("SETTING", 1).getString("UPDATE_INFO", "");
        }

        public static void e(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putString("UPDATE_INFO", str));
        }

        public static void c(Context context, long j) {
            a.b(context.getSharedPreferences("webversion", 2).edit().putLong("versionCode", j));
        }

        public static long n(Context context) {
            return context.getSharedPreferences("webversion", 2).getLong("versionCode", 0);
        }

        public static void d(Context context, long j) {
            a.b(context.getSharedPreferences("webversion", 2).edit().putLong("UPDATE_OFFLINE_TIME", j));
        }

        public static long o(Context context) {
            return context.getSharedPreferences("webversion", 2).getLong("UPDATE_OFFLINE_TIME", 0);
        }

        public static String p(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("ChannelId", "");
        }

        public static void f(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("ChannelId", str));
        }

        public static void a(String str) {
            SharedPreferences sharedPreferences = ReaderApplication.getApplicationContext().getSharedPreferences("SETTING", 0);
            if (str != null || str.length() > 0) {
                StringBuffer stringBuffer = new StringBuffer(String.valueOf(System.currentTimeMillis() + 432000000));
                stringBuffer.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                stringBuffer.append(str);
                a.b(sharedPreferences.edit().putString("CHECK_IPS", stringBuffer.toString()));
            }
        }

        public static String a() {
            return ReaderApplication.getApplicationContext().getSharedPreferences("SETTING", 0).getString("CHECK_IPS", "");
        }
    }

    /* compiled from: Config */
    public static class d {
        public static boolean A = false;
        public static String B = "SystemFont";
        public static String C = "系统字体";
        public static String D = "file:///android_asset/systemfont.png";
        public static int E = -1;
        private static int F = -1;
        private static String G = "";
        private static int H = -1;
        private static int I = -1;
        private static String J = "feed_current_day";
        private static String K = "feed_current_day_pull_num";
        private static String L = "feed_current_day_pull_times";
        private static String M = "feed_cache_time_list";
        private static String N = "feed_column_time";
        public static boolean a = true;
        public static boolean b = false;
        public static boolean c = false;
        public static boolean d = false;
        public static boolean e = false;
        public static String f = "";
        public static String g = "";
        public static long h = 0;
        public static String i = "";
        public static String j = "androidCreateTidError";
        public static int k = -16761781;
        public static int l = -4865881;
        public static boolean m = false;
        public static volatile boolean n = false;
        public static volatile boolean o = true;
        public static int p = -1;
        public static int q = 100;
        public static int r = 100;
        public static int s = 30;
        public static boolean t = false;
        public static int u = 0;
        public static int v = 0;
        public static volatile boolean w = true;
        public static volatile int x = 4;
        public static String y = "";
        public static boolean z = false;

        public static int a(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("STACK_LAST_TAB_POSITION", -1);
        }

        public static void a(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("STACK_LAST_TAB_POSITION", i));
        }

        public static boolean b(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("showed_personalitybooks_del_tip", true);
        }

        public static void a(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("showed_personalitybooks_del_tip", z));
        }

        public static long c(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("last_interact_msg_date", 0);
        }

        public static void a(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("last_interact_msg_date", j));
        }

        public static String d(Context context) {
            if (TextUtils.isEmpty(y)) {
                y = context.getSharedPreferences("SETTING", 0).getString("per_push_date", "");
            }
            return y;
        }

        public static void a(Context context, String str) {
            y = str;
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("per_push_date", str));
        }

        public static int e(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("save_tab_index", 0);
        }

        public static void b(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("save_tab_index", i));
        }

        public static boolean c(Context context, int i) {
            if (i < 0) {
                return false;
            }
            String string = context.getSharedPreferences("SETTING", 0).getString("ADV_NEW_TIP_CONFIG", "");
            if (i < string.length()) {
                return "1".equals(string.substring(i, i + 1));
            }
            return false;
        }

        public static void a(Context context, int i, boolean z) {
            if (i >= 0) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
                String string = sharedPreferences.getString("ADV_NEW_TIP_CONFIG", "");
                if (i >= string.length()) {
                    StringBuilder stringBuilder = new StringBuilder(string);
                    for (int length = string.length() - 1; length < i; length++) {
                        stringBuilder.append("0");
                    }
                    string = stringBuilder.toString();
                }
                if (z) {
                    string = ao.a(string, i, "1");
                } else {
                    string = ao.a(string, i, "0");
                }
                a.b(sharedPreferences.edit().putString("ADV_NEW_TIP_CONFIG", string));
            }
        }

        public static boolean f(Context context) {
            return context.getSharedPreferences("USER_PROTOCOL", 0).getBoolean("USER_PROTOCOL_SP_CONFIG", false);
        }

        public static void b(Context context, boolean z) {
            a.b(context.getSharedPreferences("USER_PROTOCOL", 0).edit().putBoolean("USER_PROTOCOL_SP_CONFIG", z));
        }

        public static String g(Context context) {
            return context.getSharedPreferences("USER_PROTOCOL", 0).getString("USER_PROTOCOL_DATA_SP_CONFIG", "");
        }

        public static void b(Context context, String str) {
            a.b(context.getSharedPreferences("USER_PROTOCOL", 0).edit().putString("USER_PROTOCOL_DATA_SP_CONFIG", str));
        }

        public static String a() {
            return Build.PRODUCT + "#" + Build.DEVICE + "#" + VERSION.SDK;
        }

        public static String h(Context context) {
            String str = null;
            try {
                str = UserAction.getQIMEI();
            } catch (Throwable th) {
            }
            if (str != null && !"".equals(str)) {
                return str;
            }
            return "qqreader-" + i(context);
        }

        public static String i(Context context) {
            String deviceId;
            try {
                deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            } catch (Exception e) {
                if (e != null) {
                    com.qq.reader.common.monitor.debug.c.a("permission", e.toString());
                }
                deviceId = null;
            }
            if (deviceId == null || deviceId.trim().length() == 0) {
                return Build.ID + "#" + Build.DEVICE;
            }
            return deviceId;
        }

        private static List<SearchHotWords> a(ISearchParamCollection iSearchParamCollection) {
            try {
                return SearchHotWords.parseHotword(com.qq.reader.common.utils.ao.d.a(ao.a(iSearchParamCollection).getDefaultHotWordFilePath()));
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList();
            }
        }

        public static boolean j(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("IS_ROOKIE", false);
        }

        public static void c(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("IS_ROOKIE", z));
        }

        public static List<SearchHotWords> a(Context context, ISearchParamCollection iSearchParamCollection) {
            try {
                iSearchParamCollection = ao.a(iSearchParamCollection);
                String string = context.getSharedPreferences("SETTING", 0).getString(iSearchParamCollection.getHotWordSaveConfigPerfeName(), null);
                if (string == null || string.length() <= 0) {
                    return a(iSearchParamCollection);
                }
                byte[] bytes = string.getBytes("ISO-8859-1");
                if (bytes == null) {
                    return new ArrayList();
                }
                ArrayList arrayList = (ArrayList) new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
                if (arrayList == null || arrayList.size() <= 0 || !(arrayList.get(0) instanceof SearchHotWords)) {
                    return a(iSearchParamCollection);
                }
                return arrayList;
            } catch (Exception e) {
                e.printStackTrace();
                a(ReaderApplication.getApplicationImp(), new ArrayList(), iSearchParamCollection);
                return a(iSearchParamCollection);
            }
        }

        public static void a(Context context, List<SearchHotWords> list, ISearchParamCollection iSearchParamCollection) {
            try {
                ISearchParamCollection a = ao.a(iSearchParamCollection);
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(list);
                objectOutputStream.close();
                a.b(context.getSharedPreferences("SETTING", 0).edit().putString(a.getHotWordSaveConfigPerfeName(), new String(byteArrayOutputStream.toByteArray(), "ISO-8859-1")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void d(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("PROFILE_SHAKE", z));
        }

        public static boolean k(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("PROFILE_SHAKE", true);
        }

        public static void e(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("brand_entrance_shake", z));
        }

        public static boolean l(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("brand_entrance_shake", true);
        }

        public static void f(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("PROFILE_GIFT", z));
        }

        public static long m(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("STATISICDELAY", 120000);
        }

        public static void b(Context context, long j) {
            context.getSharedPreferences("SETTING", 0).edit().putLong("STATISICDELAY", j).commit();
        }

        public static void g(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("NEW_USER_ALARM_FIRST", z));
        }

        public static boolean n(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("NEW_USER_ALARM_FIRST", false);
        }

        public static void c(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("FONT_USED_ID", str));
            ao.b = str;
        }

        public static String o(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("FONT_USED_ID", B);
        }

        public static void d(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("FONT_USED_STR", str));
        }

        public static String p(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("FONT_USED_STR", C);
        }

        public static void d(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("FONT_LINE_SPACE", i));
        }

        public static int q(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("FONT_LINE_SPACE", context.getResources().getDimensionPixelSize(R.dimen.line_space_small));
        }

        public static void e(Context context, String str) {
            a.b(context.getSharedPreferences("PLUGIN_CLIENT_VERSION_SETTING", 0).edit().putString("PLUGIN_SERVER_SERIES", str));
        }

        public static String r(Context context) {
            return context.getSharedPreferences("PLUGIN_CLIENT_VERSION_SETTING", 0).getString("PLUGIN_SERVER_SERIES", "PLUGIN_DEFAULT_SERIES");
        }

        public static void f(Context context, String str) {
            a.b(context.getSharedPreferences("PLUGIN_CLIENT_VERSION_SETTING", 0).edit().putString("PLUGIN_CLIENT_SERIES", str));
        }

        public static String s(Context context) {
            return context.getSharedPreferences("PLUGIN_CLIENT_VERSION_SETTING", 0).getString("PLUGIN_CLIENT_SERIES", "PLUGIN_DEFAULT_SERIES");
        }

        public static void e(Context context, int i) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            if (i <= 0) {
                sharedPreferences.edit().putString("BOOK_INDEX_CLICK_ON_SHELF", "");
                return;
            }
            a.b(sharedPreferences.edit().putString("BOOK_INDEX_CLICK_ON_SHELF", t(context) + "," + i));
        }

        public static String t(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("BOOK_INDEX_CLICK_ON_SHELF", "");
        }

        public static void g(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("BOOKIDS_ON_SHELF", str));
        }

        public static String u(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("BOOKIDS_ON_SHELF", "");
        }

        public static void h(Context context, String str) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            if (str == null) {
                a.b(sharedPreferences.edit().putString("BOOKIDS_NEW", ""));
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            String v = v(context);
            if (v != null && v.length() > 0 && v.indexOf(str) == -1) {
                stringBuffer.append(v);
                stringBuffer.append(",");
            }
            stringBuffer.append(System.currentTimeMillis());
            stringBuffer.append("_");
            stringBuffer.append(str);
            a.b(sharedPreferences.edit().putString("BOOKIDS_NEW", stringBuffer.toString()));
        }

        public static String v(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("BOOKIDS_NEW", "");
        }

        public static void h(Context context, boolean z) {
            Calendar instance;
            if (z) {
                instance = Calendar.getInstance();
            } else {
                instance = e();
            }
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("ADV_NEXT_PULL_TIME", instance.getTimeInMillis()));
        }

        public static long w(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("ADV_NEXT_PULL_TIME", Long.MIN_VALUE);
        }

        private static Calendar e() {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(instance.getTimeInMillis() + 600000);
            return instance;
        }

        public static Calendar b() {
            Calendar instance = Calendar.getInstance();
            instance.set(6, instance.get(6) + 15);
            return instance;
        }

        public static String x(Context context) {
            if (i == null || i.length() == 0) {
                i = am.a(context);
            }
            return i;
        }

        public static long c() {
            if (h == 0) {
                h = am.c();
            }
            return h;
        }

        public static void a(long j) {
            if (h == 0) {
                h = j;
                am.a(j);
            }
        }

        public static void d() {
            h = 0;
        }

        private static String a(Context context, String str, int i) {
            String str2 = "";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("qqreader_6.5.3.0888_android");
            stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
            stringBuilder.append(ao.h(context));
            stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
            stringBuilder.append(str);
            stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
            stringBuilder.append(com.qq.reader.common.utils.a.b.a());
            if (i != -1) {
                stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                stringBuilder.append(i);
                stringBuilder.append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                stringBuilder.append(ao.p());
            }
            try {
                return com.qq.reader.common.utils.a.d.b(stringBuilder.toString().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        }

        public static String i(Context context, String str) {
            return a(context, str, -1);
        }

        public static String f(Context context, int i) {
            String str = "0";
            if (com.qq.reader.common.login.c.b()) {
                str = com.qq.reader.common.login.c.c().c();
            }
            if (str == null || str.trim().length() == 0) {
                str = "0";
            }
            return a(context, str, i);
        }

        public static String y(Context context) {
            String str = "0";
            if (com.qq.reader.common.login.c.b()) {
                str = com.qq.reader.common.login.c.c().c();
            }
            if (str == null || str.trim().length() == 0) {
                str = "0";
            }
            return a(context, str, -1);
        }

        public static String a(String str) {
            if (str == null || str.length() <= 0) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                if (Character.isUpperCase(str.charAt(i))) {
                    stringBuffer.append("1");
                } else {
                    stringBuffer.append("0");
                }
            }
            return stringBuffer.toString();
        }

        public static String z(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("SERVER_MIX_QQ_NUM", "");
        }

        public static void j(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("SERVER_MIX_QQ_NUM", str));
        }

        public static void k(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("USER_DIR", str));
        }

        public static String A(Context context) {
            return context.getSharedPreferences("SETTING", 1).getString("USER_DIR", LocalBookActivity.c);
        }

        public static int B(Context context) {
            if (af(context) == 3) {
                return 1;
            }
            return 2;
        }

        public static int C(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("AUTO_MODE", 1);
        }

        public static void g(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("AUTO_MODE", i));
        }

        public static float D(Context context) {
            return context.getSharedPreferences("SETTING", 1).getFloat("AUTO_SPEED_OVERLAP", 7.0f);
        }

        public static void a(Context context, float f) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putFloat("AUTO_SPEED_OVERLAP", f));
        }

        public static float E(Context context) {
            return context.getSharedPreferences("SETTING", 1).getFloat("AUTO_SPEED_SCROLL", 2.0f);
        }

        public static void b(Context context, float f) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putFloat("AUTO_SPEED_SCROLL", f));
        }

        public static float F(Context context) {
            if (com.qq.reader.common.c.a.f == null) {
                com.qq.reader.common.c.a.f = new float[12];
                TypedArray obtainTypedArray = context.getResources().obtainTypedArray(R.array.title_size_array);
                int length = obtainTypedArray.length();
                for (int i = 0; i < length; i++) {
                    com.qq.reader.common.c.a.f[i] = obtainTypedArray.getDimension(i, 50.0f);
                }
                obtainTypedArray.recycle();
            }
            return com.qq.reader.common.c.a.f[H(context)];
        }

        public static int G(Context context) {
            if (com.qq.reader.common.c.a.h == -1) {
                com.qq.reader.common.c.a.h = context.getSharedPreferences("SETTING", 1).getInt("FORMAT_STYLE", 1);
            }
            return com.qq.reader.common.c.a.h;
        }

        public static void h(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("FORMAT_STYLE", i));
            com.qq.reader.common.c.a.h = i;
        }

        public static int H(Context context) {
            if (com.qq.reader.common.c.a.g == -1) {
                com.qq.reader.common.c.a.g = context.getSharedPreferences("SETTING", 1).getInt("NEW_SIZE_LEVEL", 4);
            }
            return com.qq.reader.common.c.a.g;
        }

        public static void i(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("NEW_SIZE_LEVEL", i));
            com.qq.reader.common.c.a.g = i;
        }

        public static float I(Context context) {
            if (com.qq.reader.common.c.a.c == -1.0f) {
                float f = context.getSharedPreferences("SETTING", 1).getFloat("NEW_TEXT_SIZE", 0.0f);
                if (f == 0.0f) {
                    f = (float) context.getResources().getDimensionPixelSize(R.dimen.screen_default_font_size);
                    c(context, f);
                }
                com.qq.reader.common.c.a.c = f;
            }
            return com.qq.reader.common.c.a.c;
        }

        public static void c(Context context, float f) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putFloat("NEW_TEXT_SIZE", f));
            com.qq.reader.common.c.a.c = f;
        }

        public static float J(Context context) {
            if (com.qq.reader.common.c.a.d == -1) {
                com.qq.reader.common.c.a.d = context.getResources().getDimensionPixelSize(R.dimen.screen_min_font_size);
            }
            return (float) com.qq.reader.common.c.a.d;
        }

        public static float K(Context context) {
            if (com.qq.reader.common.c.a.e == -1) {
                com.qq.reader.common.c.a.e = context.getResources().getDimensionPixelSize(R.dimen.screen_max_font_size);
            }
            return (float) com.qq.reader.common.c.a.e;
        }

        public static int L(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt(com.qq.reader.common.c.a.k + "READING_STYLE", 0);
        }

        public static void j(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt(com.qq.reader.common.c.a.k + "READING_STYLE", i));
        }

        public static int M(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("BOOKSTORE_STYLE", 0);
        }

        public static boolean l(Context context, String str) {
            return context.getSharedPreferences("SETTING", 2).contains(str);
        }

        public static int[] N(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 1);
            return new int[]{sharedPreferences.getInt("TEXT_COLOR", -16761781), sharedPreferences.getInt("BG_COLOR", -4865881)};
        }

        public static boolean O(Context context) {
            return context.getSharedPreferences("SETTING", 1).getBoolean("IS_SETTED", false);
        }

        public static void a(Context context, int i, int i2) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 2);
            if (!m) {
                m = true;
            }
            Editor edit = sharedPreferences.edit();
            edit.putBoolean("IS_SETTED", true);
            edit.putInt("TEXT_COLOR", i);
            edit.putInt("BG_COLOR", i2);
            a.b(edit);
        }

        public static void i(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("NIGHT_MODE", z));
        }

        public static boolean P(Context context) {
            return context.getSharedPreferences("SETTING", 1).getBoolean("NIGHT_MODE", false);
        }

        public static void j(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("SHOULD_SHOW", z));
        }

        public static boolean Q(Context context) {
            return context.getSharedPreferences("SETTING", 1).getBoolean("SHOULD_SHOW", false);
        }

        @Deprecated
        public static String R(Context context) {
            return com.qq.reader.common.login.c.c().c();
        }

        public static int S(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("USER_COLOR_TOAST_TIMES", 0);
        }

        public static void T(Context context) {
            if (u < 3) {
                u++;
                a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("USER_COLOR_TOAST_TIMES", u));
            }
        }

        public static int U(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("USER_SCROLL_TOAST_TIMES", 0);
        }

        private static long f() {
            return System.currentTimeMillis() + 14400000;
        }

        public static long V(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("UPLOAD_TIME", Long.MIN_VALUE);
        }

        public static void W(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            a.b(sharedPreferences.edit().putLong("UPLOAD_TIME", f()));
        }

        public static int X(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("USE_NUM", 0);
        }

        public static void k(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("USE_NUM", i));
        }

        public static int Y(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("ORICATION_TYPE", 1);
        }

        public static void l(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("ORICATION_TYPE", i));
        }

        public static int Z(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("PDF_ORICATION_TYPE", 1);
        }

        public static void m(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("PDF_ORICATION_TYPE", i));
        }

        public static boolean aa(Context context) {
            return context.getSharedPreferences("SETTING", 1).getBoolean("follow_system_brightness", true);
        }

        public static void k(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("follow_system_brightness", z));
        }

        public static int ab(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("BRIGHTNESS_NIGHT_TYPE", 30);
        }

        public static void n(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("BRIGHTNESS_NIGHT_TYPE", i));
        }

        public static int ac(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("BRIGHTNESS_DAY_TYPE", 100);
        }

        public static void o(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("BRIGHTNESS_DAY_TYPE", i));
        }

        public static int ad(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("NUM_OF_RED_POINT", 0);
        }

        public static void p(Context context, int i) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            if (i <= 0) {
                a.b(sharedPreferences.edit().putInt("NUM_OF_RED_POINT", 0));
                return;
            }
            a.b(sharedPreferences.edit().putInt("NUM_OF_RED_POINT", ad(context) + i));
            context.getApplicationContext().sendBroadcast(new Intent(com.qq.reader.common.c.a.cg));
        }

        public static void c(Context context, long j) {
            a.b(ReaderApplication.getApplicationImp().getSharedPreferences("SETTING", 2).edit().putLong("PROFILE_UPDATA_TIME", j));
        }

        public static void d(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putLong("cloud_list_import_update_time", j));
        }

        public static boolean ae(Context context) {
            return context.getSharedPreferences("SETTING", 1).getBoolean("cloud_first_update", true);
        }

        public static void l(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("cloud_first_update", z));
        }

        public static int af(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("ANIM_MODE", -1);
        }

        public static void q(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("ANIM_MODE", i));
        }

        public static boolean ag(Context context) {
            return context.getSharedPreferences("SETTING", 1).getBoolean("DOUBLE_PAGE", false);
        }

        public static void m(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("DOUBLE_PAGE", z));
        }

        public static boolean ah(Context context) {
            return context.getSharedPreferences("SETTING", 1).getBoolean("BRIGHTNESS", false);
        }

        public static void n(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("BRIGHTNESS", z));
        }

        public static int ai(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("SCREEN_PROTECT", 5);
        }

        public static void r(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("SCREEN_PROTECT", i));
        }

        public static int aj(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("CHM_SCALE", -1);
        }

        public static void s(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("CHM_SCALE", i));
        }

        public static String ak(Context context) {
            return context.getSharedPreferences("SETTING", 1).getString("CATEGORY_INDEX", BookShelfFragment.CATEGORY_ALL);
        }

        public static void m(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putString("CATEGORY_INDEX", str));
        }

        public static void t(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("NEW_FEATURES", i));
        }

        public static int al(Context context) {
            return context.getSharedPreferences("SETTING", 2).getInt("NEW_FEATURES", 0);
        }

        public static boolean n(Context context, String str) {
            return context.getSharedPreferences("SETTING", 2).getBoolean(str, false);
        }

        public static boolean am(Context context) {
            return context.getSharedPreferences("SETTING", 2).getBoolean("VOLUMETURNPAGE", true);
        }

        public static void o(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("VOLUMETURNPAGE", z));
        }

        public static boolean an(Context context) {
            return context.getSharedPreferences("SETTING", 2).getBoolean("PRESSLEFT", false);
        }

        public static void p(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("PRESSLEFT", z));
        }

        public static boolean ao(Context context) {
            return context.getSharedPreferences("SETTING", 2).getBoolean("READFULLSCREEN", true);
        }

        public static void q(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("READFULLSCREEN", z));
        }

        public static boolean ap(Context context) {
            return context.getSharedPreferences("SETTING", 2).getBoolean("FEED_ROOKIE_ZONE_ENTRANCE_FIRST_CLICK", true);
        }

        public static void r(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("FEED_ROOKIE_ZONE_ENTRANCE_FIRST_CLICK", z));
        }

        public static boolean aq(Context context) {
            return context.getSharedPreferences("SETTING", 2).getBoolean("READSHOWNAVIGATION", true);
        }

        public static void s(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("READSHOWNAVIGATION", z));
        }

        public static boolean ar(Context context) {
            return context.getSharedPreferences("SETTING", 2).getBoolean("nightmodetip", true);
        }

        public static void t(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("nightmodetip", z));
        }

        public static int as(Context context) {
            return Math.max(context.getSharedPreferences("SETTING", 2).getInt("minbright", 25), 3);
        }

        public static void u(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("minbright", Math.max(i, 3)));
        }

        public static int at(Context context) {
            return context.getSharedPreferences("SETTING", 1).getInt("APP_STARTOVER_TIME", 1);
        }

        public static void au(Context context) {
            g.a().a(new Config$UserConfig$1(context));
        }

        public static void av(Context context) {
            if (context != null) {
                a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("APP_STARTOVER_TIME", 1));
            }
        }

        public static void u(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("NOTE_EXPORT_NEW_TIP", z));
        }

        public static boolean aw(Context context) {
            return context.getSharedPreferences("SETTING", 2).getBoolean("widget_used", false);
        }

        public static void v(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("widget_used", z));
        }

        public static void w(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("TTS_USED", z));
        }

        public static void v(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putInt("TTS_SETTING_SPEED", i));
        }

        public static int ax(Context context) {
            return context.getSharedPreferences("SETTING", 2).getInt("TTS_SETTING_SPEED", 50);
        }

        public static void o(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putString("TTS_SETTING_VOICE", str));
        }

        public static String ay(Context context) {
            return context.getSharedPreferences("SETTING", 2).getString("TTS_SETTING_VOICE", "xiaoyan");
        }

        public static void x(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("IS_UPDATE_OFFLINE", z));
        }

        public static boolean az(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("IS_UPDATE_OFFLINE", false);
        }

        public static void w(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("NEWS_ID", i));
        }

        public static int aA(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("NEWS_ID", -1);
        }

        public static void e(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("PUBLIC_TIME", j));
        }

        public static long aB(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("PUBLIC_TIME", -1);
        }

        public static void y(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("BOOKNOTICES_SWITCH", z));
        }

        public static boolean aC(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("BOOKNOTICES_SWITCH", true);
        }

        public static void z(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("AUTO_PAY", z));
        }

        public static boolean aD(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("AUTO_PAY", false);
        }

        public static void x(Context context, int i) {
            int i2;
            switch (i) {
                case 1:
                case 2:
                    i2 = 1;
                    break;
                default:
                    i2 = 0;
                    break;
            }
            if (i2 != 0) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
                a.b(sharedPreferences.edit().putInt("QQREADER_PUSH_MESSAGE_TAG", sharedPreferences.getInt("QQREADER_PUSH_MESSAGE_TAG", 0) | i));
            }
        }

        public static void y(Context context, int i) {
            int i2;
            switch (i) {
                case 1:
                case 2:
                    i2 = 1;
                    break;
                default:
                    i2 = 0;
                    break;
            }
            if (i2 != 0) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
                a.b(sharedPreferences.edit().putInt("QQREADER_PUSH_MESSAGE_TAG", sharedPreferences.getInt("QQREADER_PUSH_MESSAGE_TAG", 0) & (i ^ -1)));
            }
        }

        public static boolean z(Context context, int i) {
            int i2 = context.getSharedPreferences("SETTING", 0).getInt("QQREADER_PUSH_MESSAGE_TAG", 0);
            switch (i) {
                case 1:
                case 2:
                    if ((i2 & i) == i) {
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        }

        public static boolean aE(Context context) {
            int i = context.getSharedPreferences("SETTING", 0).getInt("QQREADER_PUSH_MESSAGE_TAG", 0);
            if ((i & 2) == 2 || (i & 1) == 1) {
                return true;
            }
            return false;
        }

        public static void p(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("act_page_url", str));
        }

        public static void A(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("first_in_message", z));
        }

        public static boolean aF(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("first_in_message", true);
        }

        public static void f(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("BOOKSHELF_CLOUDUPDATE_TIME", j));
        }

        public static long aG(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("BOOKSHELF_CLOUDUPDATE_TIME", -1);
        }

        public static void g(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("TIME_OF_DELAY_AUTO_UPDATE_SHELF_CLOUD", j));
        }

        public static long aH(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("TIME_OF_DELAY_AUTO_UPDATE_SHELF_CLOUD", -1);
        }

        public static void h(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("TIME_OF_DELAY_AUTO_UPDATE_FEED", j));
        }

        public static long aI(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("TIME_OF_DELAY_AUTO_UPDATE_FEED", -1);
        }

        public static void A(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("BOOKSHELF_SORT", i));
        }

        public static int aJ(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("BOOKSHELF_SORT", 0);
        }

        public static void q(Context context, String str) {
            if (str != null && str.length() != 0) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 2);
                String aK = aK(context);
                if (aK.length() > 0) {
                    aK = aK + ",";
                }
                a.b(sharedPreferences.edit().putString("INTERNAL_BOOKS_ID", aK + str));
            }
        }

        public static String aK(Context context) {
            return context.getSharedPreferences("SETTING", 2).getString("INTERNAL_BOOKS_ID", "");
        }

        public static void r(Context context, String str) {
            if (str != null && str.length() != 0) {
                String aK = aK(context);
                if (aK != null && aK.length() > 0) {
                    String[] split = aK.split(",");
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String str2 : split) {
                        if (!str2.equals(str)) {
                            stringBuffer.append(str2);
                            stringBuffer.append(",");
                        }
                    }
                    a.b(context.getSharedPreferences("SETTING", 2).edit().putString("INTERNAL_BOOKS_ID", stringBuffer.toString()));
                }
            }
        }

        public static int aL(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("OPEN_DATE", 0);
        }

        public static void B(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("OPEN_DATE", i));
        }

        public static int aM(Context context) {
            if (E == -1) {
                E = context.getSharedPreferences("SETTING", 0).getInt("OPEN_DAY", -1);
            }
            return E;
        }

        public static void C(Context context, int i) {
            E = i;
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("OPEN_DAY", i));
        }

        public static void aN(Context context) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("SHOW_FINGER_GUIDE", 0));
            F = 0;
        }

        public static boolean aO(Context context) {
            if (context.getSharedPreferences("SETTING", 0).getInt("PREFERENCE_FROM_NET", -1) == -1) {
                return true;
            }
            return false;
        }

        public static void D(Context context, int i) {
            if (i == -1) {
                return;
            }
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("PREFERENCE_FROM_NET", i));
            }
        }

        public static int aP(Context context) {
            int i = context.getSharedPreferences("SETTING", 0).getInt("PREFERENCE_FROM_NET", -1);
            if (i == -1) {
                return 0;
            }
            return i;
        }

        public static int i(Context context, long j) {
            return context.getSharedPreferences("SETTING", 0).getInt("READERENDPAGECOUNT" + j, 0);
        }

        public static void a(Context context, long j, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("READERENDPAGECOUNT" + j, i));
        }

        public static int aQ(Context context) {
            H = context.getSharedPreferences("SETTING", 0).getInt("WEB_USER_LIKE_RECORD", -1);
            if (H == -1) {
                return 1;
            }
            return H;
        }

        public static int aR(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("SEARCH_TOOL_SORT", 1);
        }

        public static void E(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("SEARCH_TOOL_SORT", i));
        }

        public static int aS(Context context) {
            H = context.getSharedPreferences("SETTING", 0).getInt("WEB_USER_LIKE_RECORD", -1);
            if (H == -1) {
                return 0;
            }
            return H;
        }

        public static boolean aT(Context context) {
            return context.getSharedPreferences("SETTING", 0).contains("WEB_USER_LIKE_RECORD");
        }

        public static void F(Context context, int i) {
            com.qq.reader.common.monitor.debug.c.c("gtype", "setWebUserLike " + i);
            if (i == 1 || i == 2 || i == 3) {
                if (i != aS(context)) {
                    Intent intent = new Intent();
                    intent.setAction(com.qq.reader.common.c.a.cK);
                    context.sendBroadcast(intent);
                }
                a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("WEB_USER_LIKE_RECORD", i));
                H = i;
            }
        }

        public static void G(Context context, int i) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            if (i > 0 && i <= 2) {
                a.b(sharedPreferences.edit().putInt("WEBUSER_GSELECT", i));
            }
        }

        public static int aU(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("WEBUSER_GSELECT", 0);
        }

        public static void H(Context context, int i) {
            com.qq.reader.common.monitor.debug.c.c("gtype", "setWebUserGtype " + i);
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            if (i >= 0 && i <= 5) {
                a.b(sharedPreferences.edit().putInt("WEBUSER_GTYPE", i));
            }
        }

        public static int aV(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            com.qq.reader.common.monitor.debug.c.a("gtype", "getWebUserGType " + sharedPreferences.getInt("WEBUSER_GTYPE", 0));
            return sharedPreferences.getInt("WEBUSER_GTYPE", 0);
        }

        public static boolean aW(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("READ_PAGE_MENU_RED_PACKET_ENTRANCE", true);
        }

        public static void B(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("READ_PAGE_MENU_RED_PACKET_ENTRANCE", z));
        }

        public static String aX(Context context) {
            String str = "";
            switch (aS(context)) {
                case 0:
                    return "pub";
                case 1:
                    return "boy";
                case 2:
                    return "girl";
                case 3:
                    return "pub";
                default:
                    return str;
            }
        }

        public static boolean s(Context context, String str) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("book_open_private_" + str, true);
        }

        public static void a(Context context, String str, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("book_open_private_" + str, z));
        }

        public static void I(Context context, int i) {
            if (i > 0) {
                int i2;
                if (i <= 5) {
                    i2 = 0;
                } else if (i <= 10) {
                    i2 = 1;
                } else if (i <= 20) {
                    i2 = 2;
                } else {
                    i2 = 3;
                }
                a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("IMPORT_BOOKS_NUM_LEVEL", i2));
            }
        }

        public static int aY(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("IMPORT_BOOKS_NUM_LEVEL", -1);
        }

        public static boolean aZ(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("IMPORT_READZONE_BOOKS", false);
        }

        public static void C(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("IMPORT_READZONE_BOOKS", z));
        }

        public static void ba(Context context) {
            a.b(context.getSharedPreferences("REPORT", 0).edit().putInt("REPORT_USE_DAY_OF_YEAR", Calendar.getInstance().get(6)));
        }

        public static int bb(Context context) {
            return context.getSharedPreferences("REPORT", 0).getInt("REPORT_USE_DAY_OF_YEAR", 0);
        }

        public static void D(Context context, boolean z) {
            a.b(context.getSharedPreferences("REPORT", 0).edit().putBoolean("REPORT_USE_USED", z));
        }

        public static boolean bc(Context context) {
            return context.getSharedPreferences("REPORT", 0).getBoolean("REPORT_USE_USED", false);
        }

        public static void bd(Context context) {
            a.b(context.getSharedPreferences("REPORT", 0).edit().putInt("REPORT_READER_PAGE_DAY_OF_YEAR", Calendar.getInstance().get(6)));
        }

        public static int be(Context context) {
            return context.getSharedPreferences("REPORT", 0).getInt("REPORT_READER_PAGE_DAY_OF_YEAR", 0);
        }

        public static void E(Context context, boolean z) {
            a.b(context.getSharedPreferences("REPORT", 0).edit().putBoolean("REPORT_USE_SUCCESS", z));
        }

        public static boolean bf(Context context) {
            return context.getSharedPreferences("REPORT", 0).getBoolean("REPORT_USE_SUCCESS", false);
        }

        public static void bg(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("REPORT", 0);
            a.b(sharedPreferences.edit().putInt("BACKUP_TIME_STORE_SHELF", Calendar.getInstance().get(6)));
        }

        public static int bh(Context context) {
            return context.getSharedPreferences("REPORT", 0).getInt("BACKUP_TIME_STORE_SHELF", 0);
        }

        public static void bi(Context context) {
            a.b(context.getSharedPreferences("REPORT", 0).edit().putInt("BACKUP_STORE_UNINTERRUPED_TIME", 0));
        }

        public static void bj(Context context) {
            int bk = bk(context) + 1;
            a.b(context.getSharedPreferences("REPORT", 0).edit().putInt("BACKUP_STORE_UNINTERRUPED_TIME", bk));
        }

        public static int bk(Context context) {
            return context.getSharedPreferences("REPORT", 0).getInt("BACKUP_STORE_UNINTERRUPED_TIME", 0);
        }

        public static void J(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("CLOSE_ADV_TIME_LONG", i));
        }

        public static int bl(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("CLOSE_ADV_TIME_LONG", 0);
        }

        public static void j(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("CLOSE_ADV_DATE", j));
        }

        public static long bm(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("CLOSE_ADV_DATE", 0);
        }

        public static void bn(Context context) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("tip_batdownload", true));
        }

        public static boolean bo(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("tip_batdownload", false);
        }

        public static void bp(Context context) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("tip_vote", true));
        }

        public static boolean bq(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("tip_vote", false);
        }

        public static void br(Context context) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("tip_clickTabToTop", true));
        }

        public static boolean bs(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("tip_clickTabToTop", false);
        }

        public static void bt(Context context) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("tip_manualFreshFeed", true));
        }

        public static boolean bu(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("tip_manualFreshFeed", false);
        }

        public static void F(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("has_down_epub_font", z));
        }

        public static void t(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString(J, str));
        }

        public static String bv(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString(J, "");
        }

        public static void K(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt(K, i));
        }

        public static int bw(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt(K, 0);
        }

        public static void u(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString(N, str));
        }

        public static String bx(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString(N, "");
        }

        public static boolean by(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("has_down_epub_font", false);
        }

        public static boolean bz(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("is_sys_auto_brightness", true);
        }

        public static void G(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("is_sys_auto_brightness", z));
        }

        public static void a(Context context, long j, long j2, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong(str + "#" + j, j2));
        }

        public static long a(Context context, String str, long j) {
            return context.getSharedPreferences("SETTING", 0).getLong(str + "#" + j, 0);
        }

        public static void a(Context context, boolean z, String str) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            if (z) {
                a.b(sharedPreferences.edit().putInt(str + "#signed", Calendar.getInstance().get(6)));
            } else {
                a.b(sharedPreferences.edit().putInt(str + "#signed", -1));
            }
        }

        public static void b(Context context, boolean z, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean(str + "#drawed", z));
        }

        public static void a(Context context, String str, String str2) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString(str2 + "#gifts", str));
        }

        public static boolean v(Context context, String str) {
            boolean z = true;
            try {
                return context.getSharedPreferences("SETTING", 0).getBoolean(str + "#drawed", false);
            } catch (ClassCastException e) {
                try {
                    if (z != context.getSharedPreferences("SETTING", 0).getInt(str + "#drawed", 0)) {
                        return false;
                    }
                    return z;
                } catch (Exception e2) {
                    return false;
                }
            }
        }

        public static void b(Context context, String str, String str2) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString(str2 + "#lastReward", str));
        }

        public static String w(Context context, String str) {
            return context.getSharedPreferences("SETTING", 0).getString(str + "#lastReward", "");
        }

        public static void c(Context context, boolean z, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean(str + "#commited", z));
        }

        public static boolean x(Context context, String str) {
            return context.getSharedPreferences("SETTING", 0).getBoolean(str + "#commited", false);
        }

        public static void d(Context context, boolean z, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean(str + "#alwaysshowsign", z));
        }

        public static void e(Context context, boolean z, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean(str + "#mode", z));
        }

        public static void a(Context context, int i, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt(str + "#requestday", i));
        }

        public static int y(Context context, String str) {
            return context.getSharedPreferences("SETTING", 0).getInt(str + "#requestday", 0);
        }

        public static long bA(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("ACTIVITY_START_TIME", -1);
        }

        public static void k(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("ACTIVITY_START_TIME", j));
        }

        public static String bB(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            if (!sharedPreferences.contains(com.qq.reader.common.c.a.k + "FEED_NEWEST_TIMELINE") && sharedPreferences.contains("FEED_NEWEST_TIMELINE")) {
                Object string = sharedPreferences.getString("FEED_NEWEST_TIMELINE", "");
                if (!TextUtils.isEmpty(string)) {
                    a.b(sharedPreferences.edit().putString(com.qq.reader.common.c.a.k + "FEED_NEWEST_TIMELINE", string));
                    a.b(sharedPreferences.edit().remove("FEED_NEWEST_TIMELINE"));
                }
            }
            return sharedPreferences.getString(com.qq.reader.common.c.a.k + "FEED_NEWEST_TIMELINE", h.a());
        }

        public static void z(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString(com.qq.reader.common.c.a.k + "FEED_NEWEST_TIMELINE", str));
            com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", "SAVE NewestTimeLine to CONFIG : " + str);
        }

        public static int bC(Context context) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            if (!sharedPreferences.contains(com.qq.reader.common.c.a.k + "FEED_COLDBOOT_READ_COUNT") && sharedPreferences.contains("FEED_COLDBOOT_READ_COUNT")) {
                int i = sharedPreferences.getInt("FEED_COLDBOOT_READ_COUNT", -1);
                if (i > 0) {
                    a.b(sharedPreferences.edit().putInt(com.qq.reader.common.c.a.k + "FEED_COLDBOOT_READ_COUNT", i));
                    a.b(sharedPreferences.edit().remove("FEED_COLDBOOT_READ_COUNT"));
                }
            }
            return sharedPreferences.getInt(com.qq.reader.common.c.a.k + "FEED_COLDBOOT_READ_COUNT", 0);
        }

        public static void L(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt(com.qq.reader.common.c.a.k + "FEED_COLDBOOT_READ_COUNT", i));
            com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", "setFeedColdBootReadCount : " + i);
        }

        public static void H(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean(com.qq.reader.common.c.a.k + "had_addfavor", z));
        }

        public static boolean bD(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean(com.qq.reader.common.c.a.k + "had_addfavor", false);
        }

        public static void M(Context context, int i) {
            if (!(i == 0 || i == 1)) {
                i = 0;
            }
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt(com.qq.reader.common.c.a.k + "STACK_TAB_SELECT", i));
        }

        public static void I(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean(com.qq.reader.common.c.a.k + "need_colddata", z));
        }

        public static boolean bE(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean(com.qq.reader.common.c.a.k + "need_colddata", true);
        }

        public static void N(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("user_active_days", i));
        }

        public static int bF(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("user_active_days", -1);
        }

        public static void O(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("user_active_days_account", i));
        }

        public static int bG(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("user_active_days_account", -1);
        }

        public static void A(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("rookie_gift_tab_dialog_date", str));
        }

        public static String bH(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("rookie_gift_tab_dialog_date", "");
        }

        public static void l(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong(com.qq.reader.common.c.a.k + "ACTIVATE_SHELF_LOCKED_TIME", j));
        }

        public static long bI(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong(com.qq.reader.common.c.a.k + "ACTIVATE_SHELF_LOCKED_TIME", 0);
        }

        public static void J(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean(com.qq.reader.common.c.a.k + "is_new_user", z));
        }

        public static void K(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 2).edit().putBoolean("SHOW_QUERY_DIALOG", z));
        }

        public static boolean bJ(Context context) {
            if (com.qq.reader.b.a.a.a()) {
                return context.getSharedPreferences("SETTING", 2).getBoolean("SHOW_QUERY_DIALOG", true);
            }
            return false;
        }

        public static void B(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("saved_voice_list_string", str));
        }

        public static String bK(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("saved_voice_list_string", null);
        }

        public static void C(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString(com.qq.reader.common.c.a.k + "SKIN_CUR_ID", str));
        }

        public static String bL(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("top_red_packet_list", "");
        }

        public static void D(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("top_red_packet_list", str));
        }

        public static String bM(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("red_packet_adv", "");
        }

        public static void E(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("red_packet_adv", str));
        }

        public static String bN(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("red_packet_square_book_rank", "");
        }

        public static void F(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("red_packet_square_book_rank", str));
        }

        public static String bO(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("red_packet_square_user_rank", "");
        }

        public static void G(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("red_packet_square_user_rank", str));
        }

        public static String bP(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("red_packet_square_book_rank_qurl", "");
        }

        public static void H(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("red_packet_square_book_rank_qurl", str));
        }

        public static String bQ(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("red_packet_square_user_rank_qurl", "");
        }

        public static void I(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("red_packet_square_user_rank_qurl", str));
        }

        public static boolean J(Context context, String str) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("feed_entrance_id_" + str, false);
        }

        public static void b(Context context, String str, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("feed_entrance_id_" + str, z));
        }

        public static boolean bR(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean(com.qq.reader.common.c.a.k + "USER_CENTER_INTERACTIVE_RECORD", false);
        }

        public static void L(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean(com.qq.reader.common.c.a.k + "USER_CENTER_INTERACTIVE_RECORD", z));
        }

        public static String bS(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString(com.qq.reader.common.c.a.k + "SKIN_CUR_ID", Constants.DEFAULT_UIN);
        }

        public static String bT(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString(com.qq.reader.common.c.a.k + "USER_SERVER_READ_TIME", null);
        }

        public static void K(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString(com.qq.reader.common.c.a.k + "USER_SERVER_READ_TIME", str));
        }

        public static void L(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("ROOKIE_LOGIN", str));
        }

        public static void P(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("ROOKIE_SHOW_NAVIGATIONBAR", i));
        }

        public static boolean bU(Context context) {
            if (context.getSharedPreferences("SETTING", 0).getInt("ROOKIE_SHOW_NAVIGATIONBAR", 1) == 1) {
                return true;
            }
            return false;
        }

        public static void M(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("ROOKIE_ENTRANCE", str));
        }

        public static FeedRookieEntranceCard bV(Context context) {
            try {
                JSONObject jSONObject = new JSONObject(context.getSharedPreferences("SETTING", 0).getString("ROOKIE_LOGIN", ""));
                FeedRookieEntranceCard feedRookieEntranceCard = new FeedRookieEntranceCard(null, "");
                feedRookieEntranceCard.jObj = jSONObject;
                feedRookieEntranceCard.parseData(jSONObject);
                return feedRookieEntranceCard;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return null;
        }

        public static FeedRookieEntranceCard bW(Context context) {
            Object string = context.getSharedPreferences("SETTING", 0).getString("ROOKIE_ENTRANCE", "");
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(string);
                FeedRookieEntranceCard feedRookieEntranceCard = new FeedRookieEntranceCard(null, "");
                feedRookieEntranceCard.jObj = jSONObject;
                feedRookieEntranceCard.parseData(jSONObject);
                return feedRookieEntranceCard;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public static void N(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("AB_TEST", str));
        }

        public static String bX(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("AB_TEST", "");
        }

        public static void M(Context context, boolean z) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            if (z) {
                a.b(sharedPreferences.edit().putInt("adv_showcount_forhuaweigiftpack", bY(context) - 1));
            } else {
                a.b(sharedPreferences.edit().putInt("adv_showcount_forhuaweigiftpack", bY(context) + 1));
            }
        }

        public static int bY(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("adv_showcount_forhuaweigiftpack", 0);
        }

        public static void bZ(Context context) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("huaweigiftpack_received", true));
        }

        public static boolean ca(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("huaweigiftpack_received", false);
        }

        public static void N(Context context, boolean z) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("SETTING", 0);
            if (z) {
                a.b(sharedPreferences.edit().putInt("huaweigiftpack_show_dayofyear", -1));
                return;
            }
            a.b(sharedPreferences.edit().putInt("huaweigiftpack_show_dayofyear", Calendar.getInstance().get(6)));
        }

        public static boolean cb(Context context) {
            if (context.getSharedPreferences("SETTING", 0).getInt("huaweigiftpack_show_dayofyear", -1) >= Calendar.getInstance().get(6)) {
                return true;
            }
            return false;
        }

        public static void cc(Context context) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("USED_SKIN_ID_UPLOAD", Calendar.getInstance().get(6)));
        }

        public static boolean cd(Context context) {
            if (context.getSharedPreferences("SETTING", 0).getInt("USED_SKIN_ID_UPLOAD", -1) >= Calendar.getInstance().get(6)) {
                return true;
            }
            return false;
        }

        public static boolean ce(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("CANCEL_AUTO_BUY_DIALOG", false);
        }

        public static void O(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("CANCEL_AUTO_BUY_DIALOG", z));
        }

        public static boolean cf(Context context) {
            return context.getSharedPreferences("SETTING", 0).getBoolean("TTS_MENU_POSITION_CHANGE_TIP", true);
        }

        public static void P(Context context, boolean z) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putBoolean("TTS_MENU_POSITION_CHANGE_TIP", z));
        }

        public static void O(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("OBTAIN_GIFT_URL", str));
        }

        public static String cg(Context context) {
            return context.getSharedPreferences("SETTING", 0).getString("OBTAIN_GIFT_URL", "");
        }

        public static void a(Context context, List<GameTopBannerData> list) {
            try {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(list);
                objectOutputStream.close();
                a.b(context.getSharedPreferences("SETTING", 0).edit().putString("GAME_PAGE_TOP_BANNER", new String(byteArrayOutputStream.toByteArray(), "ISO-8859-1")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static List<GameTopBannerData> ch(Context context) {
            try {
                String string = context.getSharedPreferences("SETTING", 0).getString("GAME_PAGE_TOP_BANNER", "");
                byte[] bArr = null;
                if (string.length() > 0) {
                    bArr = string.getBytes("ISO-8859-1");
                }
                return (List) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList();
            }
        }

        public static int ci(Context context) {
            return context.getSharedPreferences("SETTING", 0).getInt("GAME_MAIN_CLICK_VERSION", 0);
        }

        public static void Q(Context context, int i) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("GAME_MAIN_CLICK_VERSION", i));
        }

        public static void m(Context context, long j) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putLong("game_coupon_dialog_display_time", j));
        }

        public static long cj(Context context) {
            return context.getSharedPreferences("SETTING", 0).getLong("game_coupon_dialog_display_time", -1);
        }

        public static void a(boolean z, Context context) {
            int i = 0;
            Editor edit = context.getSharedPreferences("SETTING", 0).edit();
            String str = "BOOK_MARK_DB_MOVED";
            if (z) {
                i = 1;
            }
            a.b(edit.putInt(str, i));
        }

        public static boolean ck(Context context) {
            if (context.getSharedPreferences("SETTING", 0).getInt("BOOK_MARK_DB_MOVED", 0) == 1) {
                return true;
            }
            return false;
        }

        public static void P(Context context, String str) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putString("BOOK_MARK_LAST_3_BS", str));
            com.qq.reader.common.monitor.debug.c.e("READ_SAVE", str);
        }

        public static String cl(Context context) {
            String string = context.getSharedPreferences("SETTING", 0).getString("BOOK_MARK_LAST_3_BS", "");
            com.qq.reader.common.monitor.debug.c.e("READ_GET", string);
            return string;
        }

        public static boolean cm(Context context) {
            if (context.getSharedPreferences("SETTING", 0).getInt("ABLRD", 0) == 1) {
                return true;
            }
            return false;
        }

        public static void cn(Context context) {
            a.b(context.getSharedPreferences("SETTING", 0).edit().putInt("ABLRD", 1));
        }
    }

    /* compiled from: Config */
    public static class e {
        public static void a(Context context, String str, String str2) {
            context.getSharedPreferences("LOCAL_STORAGE", 0).edit().putString(str, str2).commit();
        }

        public static void a(Context context, String str) {
            context.getSharedPreferences("LOCAL_STORAGE", 0).edit().remove(str);
        }

        public static String b(Context context, String str) {
            return context.getSharedPreferences("LOCAL_STORAGE", 0).getString(str, null);
        }

        public static void c(Context context, String str) {
            context.getSharedPreferences("LOCAL_STORAGE", 0).edit().putString("brand_expansion_url", str).commit();
        }

        public static String a(Context context) {
            return context.getSharedPreferences("LOCAL_STORAGE", 0).getString("brand_expansion_url", null);
        }
    }

    public static void a(Context context) {
        try {
            com.qq.reader.common.c.a.j = System.getInt(context.getContentResolver(), "screen_off_timeout");
        } catch (Exception e) {
            com.qq.reader.common.c.a.j = n.f;
            f.a("initScreenOffTime", e.toString());
        }
    }

    public static boolean b(Context context) {
        return b.n;
    }

    private static void b(Editor editor) {
        editor.commit();
    }
}
