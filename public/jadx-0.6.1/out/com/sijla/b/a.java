package com.sijla.b;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class a {
    public static void a(String str, Object... objArr) {
    }

    public static void a(Throwable th, String str, Object... objArr) {
    }

    public static List<String> a(Context context) {
        List<String> arrayList = new ArrayList();
        for (File file : new File("/proc").listFiles()) {
            if (file.isDirectory()) {
                try {
                    int parseInt = Integer.parseInt(file.getName());
                    if (parseInt >= 50) {
                        try {
                            com.sijla.b.a.a aVar = new com.sijla.b.a.a(parseInt);
                            if (!(!aVar.a || !aVar.c.contains(".") || aVar.b <= 9999 || aVar.c.contains(":") || aVar.c.contains("/") || aVar.c.contains("[") || aVar.c.contains("=") || aVar.c.contains("<") || aVar.c.contains(">"))) {
                                arrayList.add(aVar.a());
                            }
                        } catch (com.sijla.b.a.a.a e) {
                        } catch (Throwable e2) {
                            a(e2, "Error reading from /proc/%d.", Integer.valueOf(parseInt));
                        }
                    }
                } catch (NumberFormatException e3) {
                }
            }
        }
        return arrayList;
    }
}
