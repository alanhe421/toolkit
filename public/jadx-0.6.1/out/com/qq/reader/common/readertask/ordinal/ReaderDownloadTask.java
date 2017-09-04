package com.qq.reader.common.readertask.ordinal;

import android.content.Context;
import java.lang.ref.WeakReference;

public class ReaderDownloadTask extends ReaderNetTask {
    public static final String TASKNAME = "ReaderDownloadTask";
    private String downloadfilePath;
    private Context mContext;
    private WeakReference<a> mListenerWeakRef;
    private a mStrongRefListener;

    public ReaderDownloadTask(Context context, String str, String str2) {
        setPriority(1);
        this.mContext = context;
        this.downloadfilePath = str;
        this.mUrl = str2;
    }

    public void setListener(a aVar) {
        this.mListenerWeakRef = new WeakReference(aVar);
    }

    public void setStrongRefListener(a aVar) {
        this.mStrongRefListener = aVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r10 = this;
        r8 = 1;
        r7 = 0;
        r1 = 0;
        r0 = r10.mListenerWeakRef;
        if (r0 == 0) goto L_0x0014;
    L_0x0007:
        r0 = r10.mListenerWeakRef;
        r0 = r0.get();
        r0 = (com.qq.reader.common.readertask.ordinal.a) r0;
        if (r0 == 0) goto L_0x0014;
    L_0x0011:
        r0.a();
    L_0x0014:
        r0 = 0;
        r3 = 0;
        r2 = r10.downloadfilePath;	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        if (r2 == 0) goto L_0x004c;
    L_0x001a:
        r2 = r10.downloadfilePath;	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        r2 = r2.length();	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        if (r2 == 0) goto L_0x004c;
    L_0x0022:
        r2 = r10.mUrl;	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        if (r2 == 0) goto L_0x004c;
    L_0x0026:
        r2 = r10.mUrl;	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        r2 = r2.length();	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        if (r2 == 0) goto L_0x004c;
    L_0x002e:
        r2 = r10.mUrl;	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        r2 = r2.toLowerCase();	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        r4 = "http://";
        r2 = r2.startsWith(r4);	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        if (r2 != 0) goto L_0x0086;
    L_0x003d:
        r2 = r10.mUrl;	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        r2 = r2.toLowerCase();	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        r4 = "https://";
        r2 = r2.startsWith(r4);	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        if (r2 != 0) goto L_0x0086;
    L_0x004c:
        if (r1 == 0) goto L_0x0051;
    L_0x004e:
        r0.close();	 Catch:{ IOException -> 0x007c }
    L_0x0051:
        if (r1 == 0) goto L_0x0056;
    L_0x0053:
        r3.close();	 Catch:{ IOException -> 0x0081 }
    L_0x0056:
        if (r1 == 0) goto L_0x0061;
    L_0x0058:
        r0 = r1.exists();
        if (r0 == 0) goto L_0x0061;
    L_0x005e:
        r1.delete();
    L_0x0061:
        r0 = r10.mStrongRefListener;
        if (r0 == 0) goto L_0x006a;
    L_0x0065:
        r0 = r10.mStrongRefListener;
        r0.a(r7);
    L_0x006a:
        r0 = r10.mListenerWeakRef;
        if (r0 == 0) goto L_0x007b;
    L_0x006e:
        r0 = r10.mListenerWeakRef;
        r0 = r0.get();
        r0 = (com.qq.reader.common.readertask.ordinal.a) r0;
        if (r0 == 0) goto L_0x007b;
    L_0x0078:
        r0.a(r7);
    L_0x007b:
        return;
    L_0x007c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0051;
    L_0x0081:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0056;
    L_0x0086:
        r2 = new java.io.File;	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        r4 = r10.downloadfilePath;	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        r2.<init>(r4);	 Catch:{ Exception -> 0x023d, all -> 0x01ec }
        r4 = r2.exists();	 Catch:{ Exception -> 0x0243, all -> 0x01ec }
        if (r4 == 0) goto L_0x00cd;
    L_0x0093:
        if (r1 == 0) goto L_0x0098;
    L_0x0095:
        r0.close();	 Catch:{ IOException -> 0x00c3 }
    L_0x0098:
        if (r1 == 0) goto L_0x009d;
    L_0x009a:
        r3.close();	 Catch:{ IOException -> 0x00c8 }
    L_0x009d:
        if (r1 == 0) goto L_0x00a8;
    L_0x009f:
        r0 = r1.exists();
        if (r0 == 0) goto L_0x00a8;
    L_0x00a5:
        r1.delete();
    L_0x00a8:
        r0 = r10.mStrongRefListener;
        if (r0 == 0) goto L_0x00b1;
    L_0x00ac:
        r0 = r10.mStrongRefListener;
        r0.a(r8);
    L_0x00b1:
        r0 = r10.mListenerWeakRef;
        if (r0 == 0) goto L_0x007b;
    L_0x00b5:
        r0 = r10.mListenerWeakRef;
        r0 = r0.get();
        r0 = (com.qq.reader.common.readertask.ordinal.a) r0;
        if (r0 == 0) goto L_0x007b;
    L_0x00bf:
        r0.a(r8);
        goto L_0x007b;
    L_0x00c3:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0098;
    L_0x00c8:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x009d;
    L_0x00cd:
        r3 = new java.io.File;	 Catch:{ Exception -> 0x0243, all -> 0x01ec }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0243, all -> 0x01ec }
        r0.<init>();	 Catch:{ Exception -> 0x0243, all -> 0x01ec }
        r4 = r10.downloadfilePath;	 Catch:{ Exception -> 0x0243, all -> 0x01ec }
        r0 = r0.append(r4);	 Catch:{ Exception -> 0x0243, all -> 0x01ec }
        r4 = ".temp";
        r0 = r0.append(r4);	 Catch:{ Exception -> 0x0243, all -> 0x01ec }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0243, all -> 0x01ec }
        r3.<init>(r0);	 Catch:{ Exception -> 0x0243, all -> 0x01ec }
        r0 = r3.exists();	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        if (r0 == 0) goto L_0x00f1;
    L_0x00ee:
        r3.delete();	 Catch:{ Exception -> 0x0118, all -> 0x022b }
    L_0x00f1:
        r0 = r10.mUrl;	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        r4 = 0;
        r5 = r10.mContext;	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        r0 = com.qq.reader.common.conn.http.b.a(r0, r4, r5);	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        if (r0 != 0) goto L_0x0178;
    L_0x00fc:
        r0 = new java.io.IOException;	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        r4.<init>();	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        r5 = "Connection cannot be established to : ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        r5 = r10.mUrl;	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        r0.<init>(r4);	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        throw r0;	 Catch:{ Exception -> 0x0118, all -> 0x022b }
    L_0x0118:
        r0 = move-exception;
        r4 = r1;
        r9 = r3;
        r3 = r1;
        r1 = r2;
        r2 = r9;
    L_0x011e:
        if (r1 == 0) goto L_0x0129;
    L_0x0120:
        r5 = r1.exists();	 Catch:{ all -> 0x0237 }
        if (r5 == 0) goto L_0x0129;
    L_0x0126:
        r1.delete();	 Catch:{ all -> 0x0237 }
    L_0x0129:
        r1 = "net";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0237 }
        r5.<init>();	 Catch:{ all -> 0x0237 }
        r6 = "ReaderDownloadTask get Icon Failed";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0237 }
        r0 = r0.toString();	 Catch:{ all -> 0x0237 }
        r0 = r5.append(r0);	 Catch:{ all -> 0x0237 }
        r0 = r0.toString();	 Catch:{ all -> 0x0237 }
        com.qq.reader.common.monitor.f.a(r1, r0);	 Catch:{ all -> 0x0237 }
        if (r4 == 0) goto L_0x014c;
    L_0x0149:
        r4.close();	 Catch:{ IOException -> 0x01e0 }
    L_0x014c:
        if (r3 == 0) goto L_0x0151;
    L_0x014e:
        r3.close();	 Catch:{ IOException -> 0x01e6 }
    L_0x0151:
        if (r2 == 0) goto L_0x015c;
    L_0x0153:
        r0 = r2.exists();
        if (r0 == 0) goto L_0x015c;
    L_0x0159:
        r2.delete();
    L_0x015c:
        r0 = r10.mStrongRefListener;
        if (r0 == 0) goto L_0x0165;
    L_0x0160:
        r0 = r10.mStrongRefListener;
        r0.a(r7);
    L_0x0165:
        r0 = r10.mListenerWeakRef;
        if (r0 == 0) goto L_0x007b;
    L_0x0169:
        r0 = r10.mListenerWeakRef;
        r0 = r0.get();
        r0 = (com.qq.reader.common.readertask.ordinal.a) r0;
        if (r0 == 0) goto L_0x007b;
    L_0x0173:
        r0.a(r7);
        goto L_0x007b;
    L_0x0178:
        r4 = 20480; // 0x5000 float:2.8699E-41 double:1.01185E-319;
        r6 = new byte[r4];	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        r5 = r0.getInputStream();	 Catch:{ Exception -> 0x0118, all -> 0x022b }
        com.qq.reader.common.utils.ab.c(r3);	 Catch:{ Exception -> 0x024b, all -> 0x0230 }
        r4 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x024b, all -> 0x0230 }
        r0 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x024b, all -> 0x0230 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x024b, all -> 0x0230 }
        r4.<init>(r0);	 Catch:{ Exception -> 0x024b, all -> 0x0230 }
    L_0x018d:
        if (r5 == 0) goto L_0x01a2;
    L_0x018f:
        r0 = r5.read(r6);	 Catch:{ Exception -> 0x019b, all -> 0x0234 }
        r1 = -1;
        if (r0 == r1) goto L_0x01a2;
    L_0x0196:
        r1 = 0;
        r4.write(r6, r1, r0);	 Catch:{ Exception -> 0x019b, all -> 0x0234 }
        goto L_0x018d;
    L_0x019b:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r5;
        goto L_0x011e;
    L_0x01a2:
        r3.renameTo(r2);	 Catch:{ Exception -> 0x019b, all -> 0x0234 }
        if (r5 == 0) goto L_0x01aa;
    L_0x01a7:
        r5.close();	 Catch:{ IOException -> 0x01d6 }
    L_0x01aa:
        if (r4 == 0) goto L_0x01af;
    L_0x01ac:
        r4.close();	 Catch:{ IOException -> 0x01db }
    L_0x01af:
        if (r3 == 0) goto L_0x01ba;
    L_0x01b1:
        r0 = r3.exists();
        if (r0 == 0) goto L_0x01ba;
    L_0x01b7:
        r3.delete();
    L_0x01ba:
        r0 = r10.mStrongRefListener;
        if (r0 == 0) goto L_0x01c3;
    L_0x01be:
        r0 = r10.mStrongRefListener;
        r0.a(r8);
    L_0x01c3:
        r0 = r10.mListenerWeakRef;
        if (r0 == 0) goto L_0x007b;
    L_0x01c7:
        r0 = r10.mListenerWeakRef;
        r0 = r0.get();
        r0 = (com.qq.reader.common.readertask.ordinal.a) r0;
        if (r0 == 0) goto L_0x007b;
    L_0x01d1:
        r0.a(r8);
        goto L_0x007b;
    L_0x01d6:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01aa;
    L_0x01db:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01af;
    L_0x01e0:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x014c;
    L_0x01e6:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0151;
    L_0x01ec:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
        r5 = r1;
        r1 = r0;
    L_0x01f1:
        if (r5 == 0) goto L_0x01f6;
    L_0x01f3:
        r5.close();	 Catch:{ IOException -> 0x0221 }
    L_0x01f6:
        if (r4 == 0) goto L_0x01fb;
    L_0x01f8:
        r4.close();	 Catch:{ IOException -> 0x0226 }
    L_0x01fb:
        if (r3 == 0) goto L_0x0206;
    L_0x01fd:
        r0 = r3.exists();
        if (r0 == 0) goto L_0x0206;
    L_0x0203:
        r3.delete();
    L_0x0206:
        r0 = r10.mStrongRefListener;
        if (r0 == 0) goto L_0x020f;
    L_0x020a:
        r0 = r10.mStrongRefListener;
        r0.a(r7);
    L_0x020f:
        r0 = r10.mListenerWeakRef;
        if (r0 == 0) goto L_0x0220;
    L_0x0213:
        r0 = r10.mListenerWeakRef;
        r0 = r0.get();
        r0 = (com.qq.reader.common.readertask.ordinal.a) r0;
        if (r0 == 0) goto L_0x0220;
    L_0x021d:
        r0.a(r7);
    L_0x0220:
        throw r1;
    L_0x0221:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01f6;
    L_0x0226:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01fb;
    L_0x022b:
        r0 = move-exception;
        r4 = r1;
        r5 = r1;
        r1 = r0;
        goto L_0x01f1;
    L_0x0230:
        r0 = move-exception;
        r4 = r1;
        r1 = r0;
        goto L_0x01f1;
    L_0x0234:
        r0 = move-exception;
        r1 = r0;
        goto L_0x01f1;
    L_0x0237:
        r0 = move-exception;
        r1 = r0;
        r5 = r4;
        r4 = r3;
        r3 = r2;
        goto L_0x01f1;
    L_0x023d:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r4 = r1;
        goto L_0x011e;
    L_0x0243:
        r0 = move-exception;
        r3 = r1;
        r4 = r1;
        r9 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x011e;
    L_0x024b:
        r0 = move-exception;
        r4 = r5;
        r9 = r3;
        r3 = r1;
        r1 = r2;
        r2 = r9;
        goto L_0x011e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.readertask.ordinal.ReaderDownloadTask.run():void");
    }
}
