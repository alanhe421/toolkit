package format.epub.common.utils;

import android.graphics.Color;

/* compiled from: ZLAndroidColorUtil */
public abstract class h {
    public static int a(k kVar) {
        return kVar.a();
    }

    public static int a(String str) {
        if (str.charAt(0) == '#' && str.length() == 4) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str.charAt(0)).append(str.charAt(1)).append(str.charAt(1)).append(str.charAt(2)).append(str.charAt(2)).append(str.charAt(3)).append(str.charAt(3));
            return Color.parseColor(stringBuilder.toString());
        } else if (!str.startsWith("rgb(") && !str.startsWith("rgba(")) {
            return Color.parseColor(str);
        } else {
            int indexOf = str.indexOf(40);
            int lastIndexOf = str.lastIndexOf(41);
            if (!(indexOf == -1 || lastIndexOf == -1)) {
                String[] split = str.substring(indexOf + 1, lastIndexOf).split(",");
                indexOf = 255;
                if (split.length >= 3) {
                    short parseShort = Short.parseShort(split[0].trim());
                    short parseShort2 = Short.parseShort(split[1].trim());
                    short parseShort3 = Short.parseShort(split[2].trim());
                    if (split.length >= 4) {
                        indexOf = (short) ((int) (255.0d * Double.parseDouble(split[3].trim())));
                    }
                    return (((indexOf << 24) + (parseShort << 16)) + (parseShort2 << 8)) + parseShort3;
                }
            }
            return 0;
        }
    }
}
