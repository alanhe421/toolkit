package format.archive;

import java.util.List;

/* compiled from: DecompressUtils */
public class a {
    public static List<FileItem> a(String str, String str2, boolean z, String str3) {
        if ("rar".equals(str3)) {
            return c.a(str, str2, z);
        }
        if ("zip".equals(str3)) {
            return d.a(str, str2, z);
        }
        return null;
    }

    public static String a(String str, String str2, String str3) {
        if ("rar".equals(str3)) {
            return c.a(str, str2);
        }
        if ("zip".equals(str3)) {
            return d.a(str, str2);
        }
        return null;
    }
}
