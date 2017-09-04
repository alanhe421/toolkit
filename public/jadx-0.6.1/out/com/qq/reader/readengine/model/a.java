package com.qq.reader.readengine.model;

import com.qq.reader.common.monitor.f;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: BookType */
public final class a {
    public static int a(String str) {
        if (str == null || (!str.toLowerCase().endsWith(".epub") && !str.toLowerCase().endsWith(".teb") && !str.toLowerCase().endsWith(".qteb") && !str.toLowerCase().endsWith(".trial"))) {
            return 0;
        }
        return 1;
    }

    public static boolean b(String str) {
        return str != null && (str.equals("rar") || str.equals("zip"));
    }

    public static boolean c(String str) {
        return str != null && (str.toLowerCase().endsWith(".zip") || str.toLowerCase().endsWith(".rar"));
    }

    public static File d(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(".del");
        return new File(stringBuilder.toString());
    }

    public static boolean e(String str) {
        return d(str).exists();
    }

    public static String a(File file) {
        String name = file.getName();
        String str = "";
        if (name.toLowerCase().endsWith(".umd")) {
            return "umd";
        }
        if (name.toLowerCase().endsWith(".epub")) {
            return "epub";
        }
        if (name.toLowerCase().endsWith(".pdf")) {
            return "pdf";
        }
        if (name.toLowerCase().endsWith(".chm")) {
            return "chm";
        }
        if (name.toLowerCase().endsWith(".teb")) {
            return "teb";
        }
        if (name.toLowerCase().endsWith(".trial")) {
            return "trial";
        }
        if (name.toLowerCase().endsWith(".qteb")) {
            return "qteb";
        }
        if (name.toLowerCase().endsWith(".rar")) {
            return "rar";
        }
        if (name.toLowerCase().endsWith(".zip")) {
            return "zip";
        }
        if (name.toLowerCase().endsWith(".doc") || name.toLowerCase().endsWith(".docx")) {
            return "word";
        }
        if (name.toLowerCase().endsWith(".ppt") || name.toLowerCase().endsWith(".pptx")) {
            return "ppt";
        }
        if (name.toLowerCase().endsWith(".xlsx") || name.toLowerCase().endsWith(".xls")) {
            return "excel";
        }
        return "txt";
    }

    public static String f(String str) {
        String str2 = "";
        if (str.toLowerCase().endsWith(".umd")) {
            return "umd";
        }
        if (str.toLowerCase().endsWith(".epub")) {
            return "epub";
        }
        if (str.toLowerCase().endsWith(".pdf")) {
            return "pdf";
        }
        if (str.toLowerCase().endsWith(".chm")) {
            return "chm";
        }
        if (str.toLowerCase().endsWith(".teb")) {
            return "teb";
        }
        if (str.toLowerCase().endsWith(".trial")) {
            return "trial";
        }
        if (str.toLowerCase().endsWith(".qteb")) {
            return "qteb";
        }
        if (str.toLowerCase().endsWith(".rar")) {
            return "rar";
        }
        if (str.toLowerCase().endsWith(".zip")) {
            return "zip";
        }
        if (str.toLowerCase().endsWith(".zip")) {
            return "zip";
        }
        if (str.toLowerCase().endsWith(".doc") || str.toLowerCase().endsWith(".docx")) {
            return "word";
        }
        if (str.toLowerCase().endsWith(".ppt") || str.toLowerCase().endsWith(".pptx")) {
            return "ppt";
        }
        if (str.toLowerCase().endsWith(".xlsx") || str.toLowerCase().endsWith(".xls")) {
            return "excel";
        }
        return "txt";
    }

    public static int g(String str) {
        InputStream fileInputStream;
        Exception e;
        Throwable th;
        int i = -1;
        File file = new File(str);
        InputStream inputStream = null;
        try {
            if (str.toLowerCase().endsWith(".epub") || str.toLowerCase().endsWith(".qteb") || str.toLowerCase().endsWith(".teb") || str.toLowerCase().endsWith(".trial")) {
                i = 101;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                    }
                }
                return i;
            }
            fileInputStream = new FileInputStream(file);
            try {
                int read = fileInputStream.read();
                int read2 = fileInputStream.read();
                int read3 = fileInputStream.read();
                int read4 = fileInputStream.read();
                if ((((read4 | read3) | read2) | read) < 0) {
                    throw new EOFException();
                }
                if ((read << 0) + ((read2 << 8) + ((read3 << 16) + (read4 << 24))) == -560292983) {
                    i = 100;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                } else if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                    }
                }
                return i;
            } catch (Exception e5) {
                e = e5;
                try {
                    f.a("checkEncodeType", "checkEncodeType failed!", e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                        }
                    }
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = fileInputStream;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e7) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e8) {
            e = e8;
            fileInputStream = inputStream;
            f.a("checkEncodeType", "checkEncodeType failed!", e);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return i;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static boolean h(String str) {
        if (str.endsWith(".txt") || str.endsWith(".teb") || str.endsWith(".trial") || str.endsWith(".qteb") || str.endsWith(".epub") || str.endsWith(".pdf") || str.endsWith(".umd")) {
            return true;
        }
        return false;
    }

    public static boolean i(String str) {
        if (str.equalsIgnoreCase("epub") || str.equalsIgnoreCase("teb") || str.equalsIgnoreCase("trial") || str.equalsIgnoreCase("qteb")) {
            return true;
        }
        return false;
    }

    public static boolean j(String str) {
        if (str.endsWith(".teb") || str.endsWith(".trial") || str.endsWith(".qteb")) {
            return true;
        }
        return false;
    }

    public static boolean k(String str) {
        if (str.endsWith(".trial")) {
            return true;
        }
        return false;
    }

    public static boolean l(String str) {
        if (str.endsWith(".trial") || str.endsWith(".qteb")) {
            return true;
        }
        return false;
    }

    public static boolean m(String str) {
        if (str == null) {
            return false;
        }
        if (str.equalsIgnoreCase("trial") || str.equalsIgnoreCase("qteb")) {
            return true;
        }
        return false;
    }

    public static boolean n(String str) {
        if (str.endsWith(".qteb")) {
            return true;
        }
        return false;
    }

    public static String o(String str) {
        return str.substring(0, str.indexOf(".qteb")) + ".trial";
    }

    public static String p(String str) {
        return str.substring(0, str.indexOf(".trial")) + ".qteb";
    }

    public static String q(String str) {
        if (str == null || !str.endsWith("qteb")) {
            return null;
        }
        return "trial";
    }
}
