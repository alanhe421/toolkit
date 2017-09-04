package com.qq.reader.common.utils;

import android.content.Context;
import com.bumptech.glide.g;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.imageloader.a.a.a;
import com.qq.reader.common.imageloader.d.b;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ReaderDiskCacheManager */
public class aa {
    public static long a(boolean z) {
        return (b(z) + c(z)) + d(z);
    }

    private static long b(boolean z) {
        long a = a(ReaderApplication.getApplicationImp().getApplicationContext());
        if (z) {
            g.a(ReaderApplication.getApplicationImp().getApplicationContext()).j();
        }
        return a;
    }

    public static long a(Context context) {
        try {
            return a(b.a(context, a.d));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static long a(File file) throws Exception {
        long j;
        Exception e;
        try {
            File[] listFiles = file.listFiles();
            int length = listFiles.length;
            j = 0;
            int i = 0;
            while (i < length) {
                try {
                    File file2 = listFiles[i];
                    if (file2.isDirectory()) {
                        j += a(file2);
                    } else {
                        j += file2.length();
                    }
                    i++;
                } catch (Exception e2) {
                    e = e2;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            j = 0;
            e = exception;
            e.printStackTrace();
            return j;
        }
        return j;
    }

    private static long c(boolean z) {
        long j = 0;
        File file = new File(com.qq.reader.common.c.a.bm);
        if (!file.exists()) {
            return 0;
        }
        List g = i.c().g();
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                try {
                    Object obj;
                    long j2;
                    long longValue = Long.valueOf(file2.getName()).longValue();
                    for (int i = 0; i < g.size(); i++) {
                        if (((Mark) g.get(i)).getBookId() == longValue) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    if (obj != null) {
                        j2 = j;
                    } else if (z) {
                        j2 = ab.a(new File(v.c(file2.getName())), true) + j;
                    } else {
                        j2 = ab.d(new File(v.c(file2.getName()))) + j;
                    }
                    j = j2;
                } catch (Exception e) {
                }
            }
        }
        return j;
    }

    private static long d(boolean z) {
        if (!new File(com.qq.reader.common.c.a.bm).exists()) {
            return 0;
        }
        List h = i.c().h();
        long j = 0;
        for (int i = 0; i < h.size(); i++) {
            OnlineTag f = v.b().f(String.valueOf(((Mark) h.get(i)).getBookId()));
            if (f != null) {
                int g = f.g() - 6;
                if (g > 0) {
                    File file = new File(v.c(f.k()));
                    if (file.exists()) {
                        for (File file2 : file.listFiles()) {
                            if (file2.getName().endsWith(".qct")) {
                                try {
                                    int intValue = Integer.valueOf(file2.getName().split("\\.")[0]).intValue();
                                    if (intValue <= g) {
                                        if (z) {
                                            j = ab.a(new File(v.a(f.k(), intValue)), true);
                                        } else {
                                            j += new File(v.a(f.k(), intValue)).length();
                                        }
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                }
            }
        }
        return j;
    }

    public static void a() {
        List h = i.c().h();
        int size = h.size();
        List<File> arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            arrayList.add(a.b(1, ((Mark) h.get(i)).getImageURI(), null));
        }
        File a = a.a(1);
        if (a.exists() && a.isDirectory()) {
            for (File file : a.listFiles()) {
                int i2;
                for (File a2 : arrayList) {
                    if (file.getAbsolutePath().equals(a2.getAbsolutePath())) {
                        i2 = 1;
                        break;
                    }
                }
                i2 = 0;
                if (i2 == 0) {
                    file.delete();
                }
            }
        }
    }
}
