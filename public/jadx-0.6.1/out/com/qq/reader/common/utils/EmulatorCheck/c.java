package com.qq.reader.common.utils.EmulatorCheck;

import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* compiled from: Util */
public class c {
    public static String a(String str) {
        File file = new File(str);
        StringBuilder stringBuilder = new StringBuilder();
        if (file.exists()) {
            try {
                InputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append(readLine);
                    } else {
                        bufferedReader.close();
                        fileInputStream.close();
                        return stringBuilder.toString();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    public static String a(int i) {
        int i2 = i / 1000;
        if (i2 > 360 && i2 < 440) {
            return "400M";
        }
        if (i2 > 460 && i2 < 540) {
            return "500M";
        }
        if (i2 > 560 && i2 < ErrorCode.STATIC_TBS_INSTALL_MAKE_SYMBOLIC_LINK_ERR) {
            return "600M";
        }
        if (i2 > ErrorCode.STATIC_TBS_INSTALL_HAS_INSTALLED_EXCEPTION && i2 < 740) {
            return "700M";
        }
        if (i2 > 760 && i2 < 840) {
            return "800M";
        }
        if (i2 > 860 && i2 < 940) {
            return "900M";
        }
        if (i2 > 960 && i2 < 1040) {
            return "1G";
        }
        if (i2 < 1000) {
            return String.format("%dM", new Object[]{Integer.valueOf(i2)});
        }
        return String.format("%.1fG", new Object[]{Float.valueOf(((float) i2) / 1000.0f)});
    }

    public static String a(float f, int i) {
        if (f <= 0.0f) {
            return "";
        }
        if (i == 2) {
            return String.format("%.1f°F", new Object[]{Float.valueOf(((9.0f * f) + 160.0f) / 5.0f)});
        }
        return String.format("%.1f°C", new Object[]{Float.valueOf(f)});
    }

    public static String b(String str) throws Throwable {
        Process exec = Runtime.getRuntime().exec(str);
        String str2 = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null || readLine.equals("null")) {
                while (true) {
                    readLine = bufferedReader2.readLine();
                    str2 = str2 + readLine + "\n";
                }
            } else {
                str2 = str2 + readLine + "\n";
            }
        }
        while (true) {
            readLine = bufferedReader2.readLine();
            if (readLine == null || readLine.equals("null")) {
                return str2;
            }
            str2 = str2 + readLine + "\n";
        }
        return str2;
    }

    public static int c(String str) {
        try {
            if (new File(str).exists()) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int d(String str) {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                CharSequence readLine = bufferedReader.readLine();
                bufferedReader.close();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (readLine != null) {
                    readLine = readLine.trim();
                }
                if (TextUtils.isEmpty(readLine)) {
                    return 0;
                }
                return 1;
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
            bufferedReader = null;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return 0;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }

    public static int a(String str, String str2) {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                Object readLine = bufferedReader.readLine();
                bufferedReader.close();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (readLine != null) {
                    readLine = readLine.trim();
                }
                if (TextUtils.isEmpty(readLine) || !readLine.contains(str2)) {
                    return 0;
                }
                return 1;
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return 0;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
            bufferedReader = null;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return 0;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }
}
