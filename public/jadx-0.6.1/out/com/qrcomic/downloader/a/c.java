package com.qrcomic.downloader.a;

import android.os.Environment;
import android.text.TextUtils;
import com.qrcomic.a.f;
import com.qrcomic.a.h;
import com.qrcomic.downloader.b;
import com.qrcomic.entity.ComicSectionPicInfo;
import java.io.File;
import java.lang.reflect.Field;
import java.security.MessageDigest;

/* compiled from: QRComicFileUtil */
public class c {
    public static b a = b.a;
    private static int b = 8192;
    private static final char[] c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static Field d;
    private static Boolean e = Boolean.valueOf(true);
    private static ThreadLocal<char[]> f = new ThreadLocal<char[]>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected char[] a() {
            return new char[1024];
        }
    };

    public static StringBuilder a() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (e.booleanValue()) {
                d = StringBuilder.class.getSuperclass().getDeclaredField("value");
                d.setAccessible(true);
                e = Boolean.valueOf(false);
            }
            if (d != null) {
                d.set(stringBuilder, f.get());
            }
        } catch (Exception e) {
        }
        return stringBuilder;
    }

    public static String b() {
        String str = "";
        if (!d()) {
            return str;
        }
        StringBuilder a = a();
        a.append(f.b(com.qrcomic.manager.b.a().b().b())).append("offline/");
        return a.toString();
    }

    public static String a(String str, String str2, String str3) {
        String str4 = "";
        Object b = b();
        if (TextUtils.isEmpty(b) || str2 == null || str3 == null) {
            return str4;
        }
        StringBuilder a = a();
        a.append(b);
        a.append(str).append(File.separator);
        a.append(str2).append(File.separator);
        a.append(str3).append(File.separator);
        return a.toString();
    }

    public static String a(String str, String str2) {
        String str3 = "";
        Object b = b();
        if (TextUtils.isEmpty(b) || str2 == null) {
            return str3;
        }
        StringBuilder a = a();
        a.append(b);
        a.append(str).append(File.separator);
        a.append(str2).append(File.separator);
        return a.toString();
    }

    public static String b(String str, String str2, String str3) {
        String str4 = "";
        Object b = b();
        if (TextUtils.isEmpty(b) || str2 == null || str3 == null) {
            return str4;
        }
        StringBuilder a = a();
        a.append(b);
        a.append(str).append(File.separator);
        a.append(str2).append(File.separator);
        a.append(str3).append(File.separator);
        return a.toString();
    }

    public static String c() {
        String str = "";
        h b = com.qrcomic.manager.b.a().b();
        File cacheDir = b.b().getCacheDir();
        if (cacheDir != null) {
            return cacheDir.getAbsolutePath() + "/comic/piccache/";
        }
        if (!d()) {
            return str;
        }
        File externalCacheDir = b.b().getExternalCacheDir();
        if (externalCacheDir != null) {
            return externalCacheDir.getAbsolutePath() + "/comic/piccache/";
        }
        StringBuilder a = a();
        a.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        a.append(f.a(com.qrcomic.manager.b.a().b().b())).append("piccache/");
        return a.toString();
    }

    public static String a(ComicSectionPicInfo comicSectionPicInfo) {
        String str = "";
        if (!(comicSectionPicInfo == null || TextUtils.isEmpty(comicSectionPicInfo.comicId) || TextUtils.isEmpty(comicSectionPicInfo.sectionId) || TextUtils.isEmpty(comicSectionPicInfo.picId))) {
            Object a = a("comicid=" + comicSectionPicInfo.comicId + ",section=" + comicSectionPicInfo.sectionId + ",picId=" + comicSectionPicInfo.picId);
            if (!TextUtils.isEmpty(a)) {
                return a;
            }
        }
        return str;
    }

    public static byte[] a(ComicSectionPicInfo comicSectionPicInfo, byte[] bArr) throws NullPointerException {
        if (comicSectionPicInfo == null || TextUtils.isEmpty(comicSectionPicInfo.comicId) || TextUtils.isEmpty(comicSectionPicInfo.sectionId) || TextUtils.isEmpty(comicSectionPicInfo.picId)) {
            throw new NullPointerException("argument picInfo or comicId,sectionId,picId is null error");
        }
        String str = "comicid=" + comicSectionPicInfo.comicId + ",section=" + comicSectionPicInfo.sectionId + ",picId=" + comicSectionPicInfo.picId;
        String str2 = "@#%359$^418!*&";
        if (bArr != null && bArr.length > 0) {
            Object a = a(str2 + str);
            if (!TextUtils.isEmpty(a)) {
                byte[] bytes = a.getBytes();
                int length = bArr.length;
                int length2 = bytes.length;
                for (int i = 0; i < length; i += 512) {
                    bArr[i] = (byte) (bArr[i] ^ bytes[i % length2]);
                }
            }
        }
        return bArr;
    }

    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return a(instance.digest());
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean d() {
        return Environment.getExternalStorageState().equals("mounted") && Environment.getExternalStorageDirectory().exists();
    }

    public static String a(byte[] bArr) {
        StringBuilder a = a();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            int i = b & 255;
            a.append(c[i / 16]);
            a.append(c[i % 16]);
        }
        return a.toString();
    }
}
