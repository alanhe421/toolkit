package com.qq.reader.common.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.qq.reader.common.c.a;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: ReadingSaver */
public class ad {
    public static void a(Intent intent, Context context) {
        Exception e;
        Throwable th;
        Parcel obtain = Parcel.obtain();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            extras.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            File c = c(context);
            OutputStream outputStream = null;
            try {
                if (c.exists()) {
                    c.delete();
                }
                c.getParentFile().mkdirs();
                c.createNewFile();
                OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(c));
                try {
                    bufferedOutputStream.write(marshall);
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    outputStream = bufferedOutputStream;
                    try {
                        e.printStackTrace();
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = bufferedOutputStream;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                e.printStackTrace();
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Intent a(android.content.Context r6) {
        /*
        r0 = 0;
        r1 = 0;
        r2 = c(r6);	 Catch:{ Exception -> 0x008a, all -> 0x0077 }
        r3 = r2.exists();	 Catch:{ Exception -> 0x008a, all -> 0x0077 }
        if (r3 != 0) goto L_0x0017;
    L_0x000c:
        if (r0 == 0) goto L_0x0011;
    L_0x000e:
        r1.close();	 Catch:{ IOException -> 0x0012 }
    L_0x0011:
        return r0;
    L_0x0012:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0011;
    L_0x0017:
        r3 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x008a, all -> 0x0077 }
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x008a, all -> 0x0077 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x008a, all -> 0x0077 }
        r3.<init>(r1);	 Catch:{ Exception -> 0x008a, all -> 0x0077 }
        r1 = r3.available();	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r1 = new byte[r1];	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r3.read(r1);	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r2 = android.os.Parcel.obtain();	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r4 = 0;
        r5 = r1.length;	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r2.unmarshall(r1, r4, r5);	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r1 = 0;
        r2.setDataPosition(r1);	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r1 = new android.content.Intent;	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r1.<init>();	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r4 = new android.os.Bundle;	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r4.<init>();	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        r5 = com.qq.reader.cservice.onlineread.OnlineTag.class;
        r5 = r5.getClassLoader();	 Catch:{ Exception -> 0x0057, all -> 0x0085 }
        r4.setClassLoader(r5);	 Catch:{ Exception -> 0x0057, all -> 0x0085 }
        r4.readFromParcel(r2);	 Catch:{ Exception -> 0x0057, all -> 0x0085 }
    L_0x004d:
        r1.putExtras(r4);	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        if (r3 == 0) goto L_0x0055;
    L_0x0052:
        r3.close();	 Catch:{ IOException -> 0x0072 }
    L_0x0055:
        r0 = r1;
        goto L_0x0011;
    L_0x0057:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ Exception -> 0x005c, all -> 0x0085 }
        goto L_0x004d;
    L_0x005c:
        r1 = move-exception;
        r2 = r3;
    L_0x005e:
        r3 = "readSavedIntent";
        r4 = "error";
        com.qq.reader.common.monitor.f.a(r3, r4, r1);	 Catch:{ all -> 0x0087 }
        if (r2 == 0) goto L_0x0011;
    L_0x0069:
        r2.close();	 Catch:{ IOException -> 0x006d }
        goto L_0x0011;
    L_0x006d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0011;
    L_0x0072:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0055;
    L_0x0077:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
    L_0x007a:
        if (r3 == 0) goto L_0x007f;
    L_0x007c:
        r3.close();	 Catch:{ IOException -> 0x0080 }
    L_0x007f:
        throw r0;
    L_0x0080:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007f;
    L_0x0085:
        r0 = move-exception;
        goto L_0x007a;
    L_0x0087:
        r0 = move-exception;
        r3 = r2;
        goto L_0x007a;
    L_0x008a:
        r1 = move-exception;
        r2 = r0;
        goto L_0x005e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.utils.ad.a(android.content.Context):android.content.Intent");
    }

    public static void b(Context context) {
        File c = c(context);
        if (c.exists()) {
            c.delete();
        }
    }

    private static File c(Context context) {
        return new File(context.getFilesDir() + a.bd);
    }
}
