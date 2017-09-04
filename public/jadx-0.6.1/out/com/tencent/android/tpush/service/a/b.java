package com.tencent.android.tpush.service.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.Log;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.service.d.f;

/* compiled from: ProGuard */
public class b {
    public static void a(Context context, String str) {
        if (context != null && !f.a(str)) {
            for (String split : str.split(";;")) {
                try {
                    String[] split2 = split.split(",");
                    String str2 = split2[0];
                    String str3 = split2[1];
                    if (split2.length == 4 && str3.length() == 1) {
                        SharedPreferences defaultSharedPreferences;
                        String str4 = split2[2];
                        String str5 = split2[3];
                        if (f.a(str2)) {
                            defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        } else if (VERSION.SDK_INT >= 11) {
                            defaultSharedPreferences = context.getSharedPreferences(str2, 4);
                        } else {
                            defaultSharedPreferences = context.getSharedPreferences(str2, 0);
                        }
                        Editor edit = defaultSharedPreferences.edit();
                        if (!(f.a(str4) || f.a(str5))) {
                            if ("S".equals(str3)) {
                                edit.putString(str4, str5);
                            } else if ("L".equals(str3)) {
                                edit.putLong(str4, Long.valueOf(str5).longValue());
                            } else if ("I".equals(str3)) {
                                edit.putInt(str4, Integer.valueOf(str5).intValue());
                            } else if ("F".equals(str3)) {
                                edit.putFloat(str4, Float.valueOf(str5).floatValue());
                            } else if ("B".equals(str3)) {
                                edit.putBoolean(str4, Boolean.valueOf(str5).booleanValue());
                            }
                        }
                        edit.commit();
                        Log.e(Constants.LogTag, defaultSharedPreferences + "," + str4 + "," + str5);
                    }
                } catch (Throwable th) {
                    Log.e(Constants.LogTag, "eeee", th);
                }
            }
        }
    }

    public static void b(Context context, String str) {
        if (context != null && !f.a(str)) {
            for (String split : str.split(";;")) {
                try {
                    String split2;
                    String[] split3 = split2.split(",");
                    String str2 = split3[0];
                    if (split3.length == 3 && str2.length() == 1) {
                        String str3 = split3[1];
                        split2 = split3[2];
                        if (!(f.a(str3) || f.a(split2))) {
                            if ("S".equals(str2)) {
                                com.tencent.android.tpush.service.channel.c.f.a(context).a(str3, split2);
                            } else if ("L".equals(str2)) {
                                com.tencent.android.tpush.service.channel.c.f.a(context).a(str3, Long.valueOf(split2).longValue());
                            } else if ("I".equals(str2)) {
                                com.tencent.android.tpush.service.channel.c.f.a(context).a(str3, Integer.valueOf(split2).intValue());
                            } else if ("F".equals(str2)) {
                                com.tencent.android.tpush.service.channel.c.f.a(context).a(str3, Float.valueOf(split2).floatValue());
                            }
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
    }
}
