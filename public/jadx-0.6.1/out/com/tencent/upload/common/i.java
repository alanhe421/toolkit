package com.tencent.upload.common;

import com.etrump.jni.ETConverter;

public final class i {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length << 1);
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append(a[(bArr[i] & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >>> 4]);
            stringBuilder.append(a[bArr[i] & 15]);
        }
        return stringBuilder.toString();
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        String[] split = str.split("\\.");
        if (split == null || split.length != 4) {
            return false;
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            if (parseInt <= 0 || parseInt > 255) {
                return false;
            }
            parseInt = Integer.parseInt(split[1]);
            if (parseInt < 0 || parseInt > 255) {
                return false;
            }
            parseInt = Integer.parseInt(split[2]);
            if (parseInt < 0 || parseInt > 255) {
                return false;
            }
            int parseInt2 = Integer.parseInt(split[3]);
            return parseInt2 >= 0 && parseInt2 <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
