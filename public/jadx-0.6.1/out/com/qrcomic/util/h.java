package com.qrcomic.util;

import android.content.SharedPreferences;
import com.qrcomic.a.e;

/* compiled from: ReaderConfig */
public class h {

    /* compiled from: ReaderConfig */
    public static class a {
        public static boolean a(String str, String str2) {
            return a(str).getBoolean("preview_auto_buy_" + str2, true);
        }

        public static void a(String str, String str2, boolean z) {
            a(str).edit().putBoolean("preview_auto_buy_" + str2, z).commit();
        }

        public static boolean b(String str, String str2) {
            return a(str).getInt(new StringBuilder().append("auto_pay_next_section_").append(str2).toString(), 1) == 0;
        }

        public static void a(boolean z, String str, String str2) {
            a(str).edit().putInt("auto_pay_next_section_" + str2, z ? 0 : 1).commit();
        }

        private static SharedPreferences a(String str) {
            return com.qrcomic.manager.b.a().b().b().getSharedPreferences(e.a(str), 4);
        }

        public static void a(int i, boolean z, String str) {
            long j = (long) (1 << i);
            long j2 = a(str).getLong("read_red_dot", 0);
            if (z) {
                j |= j2;
            } else {
                j = (j ^ -1) & j2;
            }
            a(str).edit().putLong("read_red_dot", j).commit();
        }

        public static boolean a(int i, String str) {
            return (a(str).getLong("read_red_dot", -1) & (1 << i)) == 1;
        }
    }

    /* compiled from: ReaderConfig */
    public static class b {
        public static int a = 1;
    }

    /* compiled from: ReaderConfig */
    public static class c {
        private static SharedPreferences d() {
            return com.qrcomic.manager.b.a().b().b().getSharedPreferences(e.b(), 4);
        }

        public static int a() {
            return d().getInt("media_reader_brightness", 85);
        }

        public static void a(int i) {
            d().edit().putInt("media_reader_brightness", i).commit();
        }

        public static void a(boolean z) {
            d().edit().putInt("media_reader_brightness_fit_sys", z ? 1 : 0).commit();
        }

        public static boolean b() {
            return d().getInt("media_reader_brightness_fit_sys", 1) == 1;
        }

        public static void b(int i) {
            d().edit().putInt("comic_watch_orien", i).commit();
        }

        public static void c(int i) {
            d().edit().putInt("comic_watch_mode", i).commit();
        }

        public static int c() {
            return d().getInt("comic_watch_mode", 1);
        }
    }
}
