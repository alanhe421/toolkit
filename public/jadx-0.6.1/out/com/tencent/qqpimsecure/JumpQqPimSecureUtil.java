package com.tencent.qqpimsecure;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import com.etrump.jni.ETConverter;
import java.security.MessageDigest;

public class JumpQqPimSecureUtil {
    private static final String QQ_SECURE_CERTMD5_DEBUG = "7CC749CFC0FB5677E6ABA342EDBDBA5A";
    private static final String QQ_SECURE_CERTMD5_RELEASE = "00B1208638DE0FCD3E920886D658DAF6";
    private static final String QQ_SECURE_PACKAGE_NAME = "com.tencent.qqpimsecure";
    private static final String TOKEN_LAUNCH_PARAM_KEY = "launch_param";
    private static final String TOKEN_PLATFORM_KEY = "platform_id";

    public static final class TokenDestViewId {
        public static final int AD_BLOCKING_MAIN_VIEW = 14876673;
        public static final int DEEP_CLEAN_MAIN_VIEW = 9502721;
        public static final int GAME_BOX_MAIN_VIEW = 16449537;
        public static final int INTERCEPTOR_MAIN_VIEW = 8585217;
        public static final int MAIN_PAGE_MAIN_VIEW = 7798785;
        public static final int NETWORK_MANAGER_MAIN_VIEW = 9240577;
        public static final int PAY_SECURE_MAIN_VIEW = 8585217;
        public static final int PRIVACY_SPACE_MAIN_VIEW = 8847361;
        public static final int PROCESS_MANAGER_MAIN_VIEW = 9633793;
        public static final int SOFTWARE_MARKET_MAIN_VIEW = 9895937;
        public static final int SPACE_MANAGER_MAIN_VIEW = 11206657;
        public static final int VIRUS_KILLER_MAIN_VIEW = 8716289;
        public static final int WEIXIN_SECURE_MAIN_VIEW = 13565953;
        public static final int WIFI_MANAGER_MAIN_VIEW = 11993089;
    }

    public static void jumpToQqSecure(Context context, String str, String str2, Bundle bundle) {
        if (context != null) {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(QQ_SECURE_PACKAGE_NAME);
            if (launchIntentForPackage != null) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                if (str != null && str.length() > 0) {
                    bundle.putString(TOKEN_PLATFORM_KEY, str);
                }
                if (str2 != null) {
                    bundle.putString(TOKEN_LAUNCH_PARAM_KEY, str2);
                }
                launchIntentForPackage.putExtras(bundle);
                launchIntentForPackage.setFlags(402653184);
                context.startActivity(launchIntentForPackage);
            }
        }
    }

    public static PackageInfo getVersionInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(QQ_SECURE_PACKAGE_NAME, 16384);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    public static boolean isOfficalQqSecure(Context context) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(QQ_SECURE_PACKAGE_NAME, 64).signatures;
            MessageDigest instance = MessageDigest.getInstance("MD5");
            if (signatureArr != null && signatureArr.length > 0) {
                instance.update(signatureArr[0].toByteArray());
            }
            byte[] digest = instance.digest();
            char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
            for (int i = 0; i < digest.length; i++) {
                stringBuilder.append(cArr[(digest[i] & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >>> 4]);
                stringBuilder.append(cArr[digest[i] & 15]);
            }
            if (QQ_SECURE_CERTMD5_RELEASE.equalsIgnoreCase(stringBuilder.toString()) || QQ_SECURE_CERTMD5_DEBUG.equalsIgnoreCase(stringBuilder.toString())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void callQqSecureOnBg(Context context) {
        if (context != null) {
            String str = "com.tencent.qqpimsecure.service.TMSLiteService";
            Intent intent = new Intent();
            intent.setClassName(QQ_SECURE_PACKAGE_NAME, "com.tencent.qqpimsecure.service.TMSLiteService");
            context.startService(intent);
        }
    }
}
