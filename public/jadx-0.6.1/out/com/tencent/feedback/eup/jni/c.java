package com.tencent.feedback.eup.jni;

import android.content.Context;
import com.tencent.feedback.common.e;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RQDSRC */
public class c {
    public byte a;
    public int b;

    private static Map<String, Integer> b(String str) {
        if (str == null) {
            return null;
        }
        try {
            Map<String, Integer> hashMap = new HashMap();
            for (String split : str.split(",")) {
                String[] split2 = split.split(":");
                if (split2.length != 2) {
                    e.d("error format at %s", new Object[]{split});
                    return null;
                }
                hashMap.put(split2[0], Integer.valueOf(Integer.parseInt(split2[1])));
            }
            return hashMap;
        } catch (Throwable e) {
            e.d("error format intStateStr %s", new Object[]{str});
            if (e.a(e)) {
                return null;
            }
            e.printStackTrace();
            return null;
        }
    }

    private static com.tencent.feedback.eup.e a(Context context, Map<String, String> map) {
        if (map == null) {
            return null;
        }
        if (com.tencent.feedback.common.c.a(context) == null) {
            e.d("abnormal com info not created", new Object[0]);
            return null;
        }
        String str = (String) map.get("intStateStr");
        if (str == null || str.trim().length() <= 0) {
            e.d("no intStateStr", new Object[0]);
            return null;
        }
        Map b = b(str);
        if (b == null) {
            e.d("parse intSateMap fail", new Object[]{Integer.valueOf(map.size())});
            return null;
        }
        try {
            ((Integer) b.get("ep")).intValue();
            ((Integer) b.get("et")).intValue();
            ((Integer) b.get("sino")).intValue();
            int intValue = ((Integer) b.get("sico")).intValue();
            int intValue2 = ((Integer) b.get("spd")).intValue();
            ((Integer) b.get("sud")).intValue();
            long intValue3 = (long) ((Integer) b.get("ets")).intValue();
            long intValue4 = (long) ((Integer) b.get("etms")).intValue();
            String str2 = (String) map.get("soVersion");
            if (str2 == null) {
                e.d("error format at version", new Object[0]);
                return null;
            }
            String str3;
            String str4;
            String str5;
            String str6;
            str = (String) map.get("errorAddr");
            String str7 = str == null ? "unknown2" : str;
            str = (String) map.get("codeMsg");
            if (str == null) {
                str3 = "unknown2";
            } else {
                str3 = str;
            }
            str = (String) map.get("tombPath");
            if (str == null) {
                str4 = "unknown2";
            } else {
                str4 = str;
            }
            str = (String) map.get("signalName");
            if (str == null) {
                str5 = "unknown2";
            } else {
                str5 = str;
            }
            String str8 = (String) map.get("errnoMsg");
            str = (String) map.get("stack");
            if (str == null) {
                str6 = "unknown2";
            } else {
                str6 = str;
            }
            str = (String) map.get("jstack");
            if (str != null) {
                str6 = str6 + "java:\n" + str;
            }
            com.tencent.feedback.eup.e a = b.a(context, (intValue4 / 1000) + (1000 * intValue3), str5, str7, str6, str4, intValue, str3, -intValue2, str8, null, null, str2);
            if (a == null) {
                return a;
            }
            a.c(true);
            return a;
        } catch (Throwable th) {
            e.d("error format", new Object[0]);
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static String a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            if (read == 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append((char) read);
        }
    }

    public static com.tencent.feedback.eup.e a(Context context, String str) {
        FileInputStream fileInputStream;
        Throwable e;
        Throwable th;
        com.tencent.feedback.eup.e eVar = null;
        File file = new File(str, "rqd_record.eup");
        if (file.exists() && file.canRead()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    String a = a((InputStream) fileInputStream);
                    if (a == null || !a.equals("NATIVE_RQD_REPORT")) {
                        e.d("record read fail! %s", new Object[]{a});
                        try {
                            fileInputStream.close();
                        } catch (Throwable e2) {
                            if (!e.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                    } else {
                        Map hashMap = new HashMap();
                        Object obj = eVar;
                        while (true) {
                            String a2 = a((InputStream) fileInputStream);
                            if (a2 == null) {
                                break;
                            } else if (obj == null) {
                                obj = a2;
                            } else {
                                hashMap.put(obj, a2);
                                obj = eVar;
                            }
                        }
                        if (obj != null) {
                            e.d("record not pair! drop! %s", new Object[]{obj});
                            try {
                                fileInputStream.close();
                            } catch (Throwable e22) {
                                if (!e.a(e22)) {
                                    e22.printStackTrace();
                                }
                            }
                        } else {
                            eVar = a(context, hashMap);
                            try {
                                fileInputStream.close();
                            } catch (Throwable e222) {
                                if (!e.a(e222)) {
                                    e222.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (IOException e3) {
                    e222 = e3;
                    try {
                        if (!e.a(e222)) {
                            e222.printStackTrace();
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e2222) {
                                if (!e.a(e2222)) {
                                    e2222.printStackTrace();
                                }
                            }
                        }
                        return eVar;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e22222) {
                                if (!e.a(e22222)) {
                                    e22222.printStackTrace();
                                }
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e22222 = e4;
                fileInputStream = eVar;
                if (e.a(e22222)) {
                    e22222.printStackTrace();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return eVar;
            } catch (Throwable e222222) {
                fileInputStream = eVar;
                th = e222222;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return eVar;
    }

    public static void a(String str) {
        File file = new File(str, "rqd_record.eup");
        if (file.exists() && file.canWrite()) {
            file.delete();
            e.b("delete record file %s", new Object[]{file.getAbsoluteFile()});
        }
    }
}
