package com.tencent.av.mediacodec.config;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.av.sdk.AVContext;
import com.tencent.av.utils.ArrayUtils;
import com.tencent.av.utils.QLog;

public class CodecConfigParser extends ConfigBaseParser {
    static final String TAG = "CodecConfigParser";
    public static final int TEST_DISABLE = 0;
    public static final int TEST_USE_ASYNC_API = 2;
    public static final int TEST_USE_SYNC_API = 1;
    public static String key_async = (key_root + "async/");
    public static String key_async_codec;
    public static String key_async_min_sdk;
    public static String key_avc_decocoder = (key_root + "avc_decoder/");
    public static String key_avc_encocoder = (key_root + "avc_encoder/");
    public static String key_avcdec_bl_fingerprint;
    public static String key_avcdec_bl_model;
    public static String key_avcdec_bl_product;
    public static String key_avcdec_bl_sdk;
    public static String key_avcdec_bl_version;
    public static String key_avcdec_wl_min_sdk;
    public static String key_avcdec_wl_min_version;
    public static String key_avcenc_bl_fingerprint;
    public static String key_avcenc_bl_model;
    public static String key_avcenc_bl_product;
    public static String key_avcenc_bl_sdk;
    public static String key_avcenc_bl_version;
    public static String key_avcenc_wl_min_sdk;
    public static String key_avcenc_wl_min_version;
    public static String key_root = "sharp/hwcodec_new/";
    public static String key_test = (key_root + "test/");
    public static String key_test_async_min_sdk;
    public static String key_test_codec;
    public static String key_test_disable_sdk;
    public static String key_test_min_sdk;
    public static String str_black_list = "black_list/";
    public static String str_codec = "codec";
    public static String str_disable_sdk = "disable_sdk";
    public static String str_fingerprint = "fingerprint";
    public static String str_min_sdk = "min_sdk";
    public static String str_min_version = "min_version";
    public static String str_model = "model";
    public static String str_product = "product";
    public static String str_sdk = "sdk";
    public static String str_test_async_min_sdk = "async_min_sdk";
    public static String str_version = ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION;
    public static String str_white_list = "white_list/";

    static {
        key_test_min_sdk = null;
        key_test_disable_sdk = null;
        key_test_codec = null;
        key_test_async_min_sdk = null;
        key_avcdec_wl_min_sdk = null;
        key_avcdec_wl_min_version = null;
        key_avcdec_bl_model = null;
        key_avcdec_bl_product = null;
        key_avcdec_bl_fingerprint = null;
        key_avcdec_bl_sdk = null;
        key_avcdec_bl_version = null;
        key_avcenc_wl_min_sdk = null;
        key_avcenc_wl_min_version = null;
        key_avcenc_bl_model = null;
        key_avcenc_bl_product = null;
        key_avcenc_bl_fingerprint = null;
        key_avcenc_bl_sdk = null;
        key_avcenc_bl_version = null;
        key_async_min_sdk = null;
        key_async_codec = null;
        key_test_min_sdk = key_test + str_min_sdk;
        key_test_disable_sdk = key_test + str_disable_sdk;
        key_test_codec = key_test + str_codec;
        key_test_async_min_sdk = key_test + str_test_async_min_sdk;
        key_avcdec_wl_min_sdk = key_avc_decocoder + str_white_list + str_min_sdk;
        key_avcdec_wl_min_version = key_avc_decocoder + str_white_list + str_min_version;
        key_avcdec_bl_model = key_avc_decocoder + str_black_list + str_model;
        key_avcdec_bl_product = key_avc_decocoder + str_black_list + str_product;
        key_avcdec_bl_fingerprint = key_avc_decocoder + str_black_list + str_fingerprint;
        key_avcdec_bl_sdk = key_avc_decocoder + str_black_list + str_sdk;
        key_avcdec_bl_version = key_avc_decocoder + str_black_list + str_version;
        key_avcenc_wl_min_sdk = key_avc_encocoder + str_white_list + str_min_sdk;
        key_avcenc_wl_min_version = key_avc_encocoder + str_white_list + str_min_version;
        key_avcenc_bl_model = key_avc_encocoder + str_black_list + str_model;
        key_avcenc_bl_product = key_avc_encocoder + str_black_list + str_product;
        key_avcenc_bl_fingerprint = key_avc_encocoder + str_black_list + str_fingerprint;
        key_avcenc_bl_sdk = key_avc_encocoder + str_black_list + str_sdk;
        key_avcenc_bl_version = key_avc_encocoder + str_black_list + str_version;
        key_async_min_sdk = key_async + str_min_sdk;
        key_async_codec = key_async + str_codec;
    }

    public CodecConfigParser(String str) {
        super(str);
    }

    public int[] getIntValues(String str) {
        if (VERSION.SDK_INT < 16) {
            return null;
        }
        return super.getIntValues(str);
    }

    public String[] getStringValues(String str) {
        if (VERSION.SDK_INT < 16) {
            return null;
        }
        return super.getStringValues(str);
    }

    public int getAVCDecoderTestFlag() {
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "getAVCDecoderTestFlag sdk: " + VERSION.SDK_INT);
        }
        if (VERSION.SDK_INT < 16) {
            return 0;
        }
        try {
            int[] intValues = getIntValues(key_test_codec);
            if (intValues == null || (intValues[0] & 1) != 1) {
                return 0;
            }
            intValues = getIntValues(key_test_min_sdk);
            if (intValues == null || VERSION.SDK_INT < intValues[0] || ArrayUtils.contains(getIntValues(key_test_disable_sdk), VERSION.SDK_INT)) {
                return 0;
            }
            intValues = getIntValues(key_test_async_min_sdk);
            if (VERSION.SDK_INT < 21 || intValues == null || VERSION.SDK_INT < intValues[0]) {
                return 1;
            }
            return 2;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getAVCEncoderTestFlag() {
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "getAVCEncoderTestFlag sdk: " + VERSION.SDK_INT);
        }
        if (VERSION.SDK_INT < 16) {
            return 0;
        }
        try {
            int[] intValues = getIntValues(key_test_codec);
            if (intValues == null || (intValues[0] & 2) != 2) {
                return 0;
            }
            intValues = getIntValues(key_test_min_sdk);
            if (intValues == null || VERSION.SDK_INT < intValues[0] || ArrayUtils.contains(getIntValues(key_test_disable_sdk), VERSION.SDK_INT)) {
                return 0;
            }
            intValues = getIntValues(key_test_async_min_sdk);
            if (VERSION.SDK_INT < 21 || intValues == null || VERSION.SDK_INT < intValues[0]) {
                return 1;
            }
            return 2;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean getAVCDecoderAbility() {
        if (VERSION.SDK_INT < 16) {
            return false;
        }
        try {
            int[] intValues = getIntValues(key_avcdec_wl_min_sdk);
            if (intValues == null || VERSION.SDK_INT < intValues[0]) {
                return false;
            }
            Object version = AVContext.getVersion();
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "sdk version: " + version);
            }
            if (!TextUtils.isEmpty(version)) {
                if (version.compareTo(getStringValue(key_avcdec_wl_min_version, "")) < 0) {
                    return false;
                }
                Object[] stringValues = getStringValues(key_avcdec_bl_version);
                if (stringValues != null && ArrayUtils.contains(stringValues, version)) {
                    return false;
                }
            }
            int[] intValues2 = getIntValues(key_avcdec_bl_sdk);
            if (intValues2 != null) {
                for (int i : intValues2) {
                    if (VERSION.SDK_INT == i) {
                        return false;
                    }
                }
            }
            String[] stringValues2 = getStringValues(key_avcdec_bl_model);
            if (stringValues2 != null) {
                for (String equalsIgnoreCase : stringValues2) {
                    if (Build.MODEL.equalsIgnoreCase(equalsIgnoreCase)) {
                        return false;
                    }
                }
            }
            stringValues2 = getStringValues(key_avcdec_bl_product);
            if (stringValues2 != null) {
                for (String equalsIgnoreCase2 : stringValues2) {
                    if (Build.PRODUCT.equalsIgnoreCase(equalsIgnoreCase2)) {
                        return false;
                    }
                }
            }
            stringValues2 = getStringValues(key_avcdec_bl_fingerprint);
            if (stringValues2 != null) {
                for (String equalsIgnoreCase22 : stringValues2) {
                    if (Build.PRODUCT.equalsIgnoreCase(equalsIgnoreCase22)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean getAVCEncoderAbility() {
        if (VERSION.SDK_INT < 19) {
            return false;
        }
        try {
            int[] intValues = getIntValues(key_avcenc_wl_min_sdk);
            if (intValues == null || VERSION.SDK_INT < intValues[0]) {
                return false;
            }
            Object version = AVContext.getVersion();
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "sdk version: " + version);
            }
            if (!TextUtils.isEmpty(version)) {
                if (version.compareTo(getStringValue(key_avcenc_wl_min_version, "")) < 0) {
                    return false;
                }
                Object[] stringValues = getStringValues(key_avcenc_bl_version);
                if (stringValues != null && ArrayUtils.contains(stringValues, version)) {
                    return false;
                }
            }
            int[] intValues2 = getIntValues(key_avcenc_bl_sdk);
            if (intValues2 != null) {
                for (int i : intValues2) {
                    if (VERSION.SDK_INT == i) {
                        return false;
                    }
                }
            }
            String[] stringValues2 = getStringValues(key_avcenc_bl_model);
            if (stringValues2 != null) {
                for (String equalsIgnoreCase : stringValues2) {
                    if (Build.MODEL.equalsIgnoreCase(equalsIgnoreCase)) {
                        return false;
                    }
                }
            }
            stringValues2 = getStringValues(key_avcenc_bl_product);
            if (stringValues2 != null) {
                for (String equalsIgnoreCase2 : stringValues2) {
                    if (Build.PRODUCT.equalsIgnoreCase(equalsIgnoreCase2)) {
                        return false;
                    }
                }
            }
            stringValues2 = getStringValues(key_avcenc_bl_fingerprint);
            if (stringValues2 != null) {
                for (String equalsIgnoreCase22 : stringValues2) {
                    if (Build.PRODUCT.equalsIgnoreCase(equalsIgnoreCase22)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEnableAsyncApi(int i) {
        try {
            int[] intValues = getIntValues(key_async_min_sdk);
            if (intValues == null || VERSION.SDK_INT < 21 || VERSION.SDK_INT < intValues[0]) {
                return false;
            }
            intValues = getIntValues(key_async_codec);
            if (intValues == null || !ArrayUtils.contains(intValues, i)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
