package com.tencent.upload.log.trace;

import android.content.Context;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public final class a extends g {
    private Context a;
    private String b = "yyyy-MM-dd";
    private OutputStreamWriter c;
    private File d;
    private FileFilter e = new b(this);
    private FileFilter f = new c(this);
    private Comparator<? super File> g = new d(this);
    private Comparator<? super File> h = new e(this);

    public a(Context context) {
        this.a = context;
        c();
    }

    private void a(long j) {
        int i = 0;
        String format = b().format(Long.valueOf(j));
        ArrayList arrayList = new ArrayList();
        File file = new File(TracerConfig.getLogDir(this.a));
        if (file.exists()) {
            File[] listFiles = file.listFiles(this.f);
            if (listFiles != null) {
                Arrays.sort(listFiles, this.g);
                int i2 = 0;
                while (i2 < listFiles.length) {
                    arrayList.add(listFiles[i2]);
                    if (listFiles[i2].getName().equals(format)) {
                        break;
                    }
                    i2++;
                }
                i2 = 0;
                for (i2++; i2 < listFiles.length; i2++) {
                    a(listFiles[i2]);
                }
                long size = ((long) arrayList.size()) - 7;
                while (((long) i) < size) {
                    a((File) arrayList.get(i));
                    i++;
                }
            }
        }
    }

    private boolean a(File file) {
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list == null) {
                return false;
            }
            for (String file2 : list) {
                if (!a(new File(file, file2))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    private File[] a(File[] fileArr) {
        Arrays.sort(fileArr, this.h);
        return fileArr;
    }

    private File b(File file) {
        File[] c = c(file);
        if (c == null || c.length == 0) {
            return new File(file, TracerConfig.getLogFilePre(this.a) + "1.upload.log");
        }
        a(c);
        File file2 = c[c.length - 1];
        int length = c.length - 36;
        if (((int) file2.length()) > 524288) {
            file2 = new File(file, TracerConfig.getLogFilePre(this.a) + (d(file2) + 1) + TracerConfig.DEF_TRACE_FILEEXT);
            length++;
        }
        for (int i = 0; i < length; i++) {
            c[i].delete();
        }
        return file2;
    }

    private SimpleDateFormat b() {
        return new SimpleDateFormat(this.b);
    }

    private Writer c() {
        Object obj = null;
        long currentTimeMillis = System.currentTimeMillis();
        File file = new File(TracerConfig.getLogDir(this.a), b().format(Long.valueOf(currentTimeMillis)));
        if (!file.exists()) {
            file.mkdirs();
            a(currentTimeMillis);
        }
        File b = b(file);
        if (!(this.d == null || (this.d.exists() && this.d.canWrite()))) {
            obj = 1;
        }
        if (!(obj == null && (b == null || b.equals(this.d)))) {
            this.d = b;
            try {
                if (this.c != null) {
                    this.c.flush();
                    this.c.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                this.c = new OutputStreamWriter(new FileOutputStream(this.d, true));
            } catch (IOException e2) {
                return null;
            }
        }
        return this.c;
    }

    private File[] c(File file) {
        return file.listFiles(this.e);
    }

    private int d(File file) {
        try {
            String name = file.getName();
            int length = TracerConfig.getLogFilePre(this.a).length();
            return Integer.parseInt(name.substring(length, name.indexOf(46, length + 1)));
        } catch (Exception e) {
            return -1;
        }
    }

    public final String a(Date date) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Throwable th;
        String format = b().format(date);
        String logDir = TracerConfig.getLogDir(this.a);
        String str = logDir + File.separator + format;
        File file = new File(logDir, "log");
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file, format + TracerConfig.DEF_TRACE_FILEEXT);
        if (file2.exists()) {
            file2.delete();
        }
        File[] c = c(new File(str));
        if (c == null) {
            return null;
        }
        a(c);
        byte[] bArr = new byte[1024];
        try {
            fileOutputStream = new FileOutputStream(file2);
            try {
                for (File fileInputStream2 : c) {
                    fileInputStream = new FileInputStream(fileInputStream2);
                    while (fileInputStream.read(bArr) != -1) {
                        try {
                            fileOutputStream.write(bArr);
                        } catch (Exception e) {
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    fileInputStream.close();
                }
                try {
                    fileOutputStream.close();
                } catch (Exception e2) {
                }
                return file2.getPath();
            } catch (Exception e3) {
                fileInputStream = null;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e4) {
                    }
                }
                if (fileInputStream != null) {
                    return null;
                }
                try {
                    fileInputStream.close();
                    return null;
                } catch (Exception e5) {
                    return null;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream = null;
                th = th4;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e6) {
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e7) {
                    }
                }
                throw th;
            }
        } catch (Exception e8) {
            fileInputStream = null;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (fileInputStream != null) {
                return null;
            }
            fileInputStream.close();
            return null;
        } catch (Throwable th32) {
            fileOutputStream = null;
            th = th32;
            fileInputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    protected final void a(List<f> list) {
        if (list != null && list.size() > 0) {
            try {
                String stringBuilder;
                String str = "";
                try {
                    StringBuilder stringBuilder2 = new StringBuilder(32768);
                    for (f a : list) {
                        a.a(stringBuilder2);
                    }
                    stringBuilder = stringBuilder2.toString();
                } catch (OutOfMemoryError e) {
                    stringBuilder = str;
                }
                Writer c = c();
                if (c != null) {
                    c.write(stringBuilder);
                    c.flush();
                }
            } catch (Exception e2) {
            }
        }
    }
}
