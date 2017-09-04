package com.tencent.mid.util;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

class k {
    k() {
    }

    static int a() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new l()).length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    static int b() {
        int i = 0;
        InputStream inputStream = null;
        try {
            String str = "";
            inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"}).start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            str = str.trim();
            if (str.length() > 0) {
                i = Integer.valueOf(str).intValue();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            j.d.b(e2);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e3) {
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e4) {
                }
            }
        }
        return i * 1000;
    }

    static int c() {
        int i = 0;
        InputStream inputStream = null;
        try {
            String str = "";
            inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"}).start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            str = str.trim();
            if (str.length() > 0) {
                i = Integer.valueOf(str).intValue();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            j.d.b(e2);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e3) {
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e4) {
                }
            }
        }
        return i * 1000;
    }

    static String d() {
        BufferedReader bufferedReader;
        Throwable th;
        Object readLine;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
            try {
                readLine = bufferedReader.readLine();
                if (!TextUtils.isEmpty(readLine)) {
                    String[] split = readLine.split(":\\s+", 2);
                    if (split.length > 0) {
                        String str = split[1];
                        if (bufferedReader == null) {
                            return str;
                        }
                        try {
                            bufferedReader.close();
                            return str;
                        } catch (Exception e) {
                            return str;
                        }
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e2) {
                    }
                }
            } catch (Throwable th2) {
                readLine = th2;
                try {
                    j.d.f(readLine);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e3) {
                        }
                    }
                    return "";
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return "";
    }
}
