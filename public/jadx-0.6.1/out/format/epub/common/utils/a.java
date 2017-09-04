package format.epub.common.utils;

import android.graphics.Typeface;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* compiled from: AndroidFontUtil */
public final class a {
    private static Method a;
    private static Map<String, File[]> b;
    private static File[] c;
    private static long d;

    static {
        try {
            a = Typeface.class.getMethod("createFromFile", new Class[]{File.class});
        } catch (NoSuchMethodException e) {
            a = null;
        }
    }

    public static Typeface a(File file) {
        if (a == null) {
            return null;
        }
        try {
            return (Typeface) a.invoke(null, new Object[]{file});
        } catch (IllegalAccessException e) {
            return null;
        } catch (InvocationTargetException e2) {
            return null;
        }
    }

    public static Map<String, File[]> a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        if (z && currentTimeMillis < d + 1000) {
            z = false;
        }
        d = currentTimeMillis;
        if (b == null || r10) {
            int i = b == null ? 1 : 0;
            if (a != null) {
                File file = new File(format.epub.options.a.a().a());
                ab.a(file);
                Object listFiles = file.listFiles(new FilenameFilter() {
                    public boolean accept(File file, String str) {
                        if (str.startsWith(".")) {
                            return false;
                        }
                        String toLowerCase = str.toLowerCase();
                        if (toLowerCase.endsWith(".ttf") || toLowerCase.endsWith(".otf")) {
                            return true;
                        }
                        return false;
                    }
                });
                if (listFiles == null && c != null) {
                    c = null;
                    i = 1;
                }
                if (!(listFiles == null || listFiles.equals(c))) {
                    c = listFiles;
                    i = 1;
                }
                if (i != 0) {
                    b = new HashMap();
                    for (File name : c) {
                        String name2 = name.getName();
                        b.put(name2.substring(0, name2.length() - 4), new File[]{name, name, name, name});
                    }
                }
            } else if (i != 0) {
                b = new HashMap();
            }
        }
        return b;
    }

    public static String a(String str) {
        for (String str2 : a(false).keySet()) {
            if (str2.equalsIgnoreCase(str)) {
                return str2;
            }
        }
        if ("serif".equalsIgnoreCase(str) || "droid serif".equalsIgnoreCase(str)) {
            return "serif";
        }
        if ("sans-serif".equalsIgnoreCase(str) || "sans serif".equalsIgnoreCase(str) || "droid sans".equalsIgnoreCase(str)) {
            return "sans-serif";
        }
        if ("monospace".equalsIgnoreCase(str) || "droid mono".equalsIgnoreCase(str)) {
            return "monospace";
        }
        if (ao.b.equalsIgnoreCase(str)) {
            return ao.b;
        }
        return "方正兰亭黑_GBK".equalsIgnoreCase(str) ? "方正兰亭黑" : str;
    }
}
