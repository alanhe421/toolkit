package com.tencent.upload.c;

import FileCloud.stAuth;
import com.qq.jce.wup.c;
import com.qq.taf.jce.JceStruct;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.network.b.a.a;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class b implements a {
    private static final AtomicInteger a = new AtomicInteger(0);
    private static Map<String, Integer> h = new HashMap();
    private static Map<String, String> i = new HashMap();
    private int b;
    private stAuth c;
    private FileType d;
    private final String e;
    private final int f = a.incrementAndGet();
    private a g;

    public b(String str) {
        this.e = str;
    }

    public static Object a(byte[] bArr, String str) {
        try {
            c cVar = new c();
            cVar.a("UTF-8");
            cVar.a(bArr);
            return cVar.b(str);
        } catch (Throwable th) {
            com.tencent.upload.log.b.c("NetworkRequest", "decode exception.", th);
            return null;
        }
    }

    public static byte[] a(JceStruct jceStruct) {
        byte[] bArr = null;
        try {
            c cVar = new c();
            cVar.a("REQ", jceStruct);
            cVar.a("UTF-8");
            bArr = cVar.a();
        } catch (Throwable e) {
            com.tencent.upload.log.b.c("NetworkRequest", "encode jce exception.", e);
        }
        return bArr;
    }

    public static int b(FileType fileType) {
        switch (fileType) {
            case Photo:
                return 1;
            case Audio:
                return 2;
            case Video:
                return 3;
            case File:
                return 4;
            default:
                return 0;
        }
    }

    public static FileType b(int i) {
        switch (i) {
            case 1:
                return FileType.Photo;
            case 2:
                return FileType.Audio;
            case 3:
                return FileType.Video;
            case 4:
                return FileType.File;
            default:
                return FileType.Other;
        }
    }

    public static String c(int i) {
        k();
        String valueOf = String.valueOf(i);
        return i.containsKey(valueOf) ? (String) i.get(valueOf) : "CMD_UNKNOWN";
    }

    private static void k() {
        if (h.size() <= 0) {
            h.put("CMD_HANDSHAKE", Integer.valueOf(1));
            h.put("CMD_SPEED", Integer.valueOf(2));
            h.put("CMD_AUTH", Integer.valueOf(3));
            h.put("CMD_FILE_CONTROL", Integer.valueOf(11));
            h.put("CMD_FILE_UPLOAD", Integer.valueOf(12));
            h.put("CMD_FILE_CANCEL", Integer.valueOf(13));
            h.put("CMD_FILE_COMMIT", Integer.valueOf(14));
            h.put("CMD_FILE_MOVE", Integer.valueOf(101));
            h.put("CMD_FILE_DELETE", Integer.valueOf(102));
            h.put("CMD_FILE_STAT", Integer.valueOf(103));
            h.put("CMD_DIR_CREATE", Integer.valueOf(1001));
            h.put("CMD_DIR_FILE_DELETE", Integer.valueOf(1002));
            h.put("CMD_DIR_FILE_STAT", Integer.valueOf(1003));
            h.put("CMD_DIR_FILE_UPDATE", Integer.valueOf(1004));
            h.put("CMD_DIR_LIST", Integer.valueOf(1005));
            h.put("CMD_FTN_SEARCH_NAME", Integer.valueOf(com.tencent.qalsdk.base.a.d));
        }
        if (i.size() <= 0) {
            i.put(String.valueOf(1), "CMD_HANDSHAKE");
            i.put(String.valueOf(2), "CMD_SPEED");
            i.put(String.valueOf(3), "CMD_AUTH");
            i.put(String.valueOf(11), "CMD_FILE_CONTROL");
            i.put(String.valueOf(12), "CMD_FILE_UPLOAD");
            i.put(String.valueOf(13), "CMD_FILE_CANCEL");
            i.put(String.valueOf(14), "CMD_FILE_COMMIT");
            i.put(String.valueOf(101), "CMD_FILE_MOVE");
            i.put(String.valueOf(102), "CMD_FILE_DELETE");
            i.put(String.valueOf(103), "CMD_FILE_STAT");
            i.put(String.valueOf(1001), "CMD_DIR_CREATE");
            i.put(String.valueOf(1002), "CMD_DIR_FILE_DELETE");
            i.put(String.valueOf(1003), "CMD_DIR_FILE_STAT");
            i.put(String.valueOf(1004), "CMD_DIR_FILE_UPDATE");
            i.put(String.valueOf(1005), "CMD_DIR_LIST");
            i.put(String.valueOf(com.tencent.qalsdk.base.a.d), "CMD_FTN_SEARCH_NAME");
        }
    }

    public final a a(stAuth FileCloud_stAuth) {
        this.c = FileCloud_stAuth;
        return this;
    }

    public final a a(FileType fileType) {
        this.d = fileType;
        return this;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final void a(a aVar) {
        this.g = aVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] a() {
        /*
        r5 = this;
        r0 = 0;
        r1 = new com.qq.jce.wup.d;	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r1.<init>();	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r2 = "UTF-8";
        r1.a(r2);	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r2 = r5.f;	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r1.a(r2);	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r2 = "TX";
        r1.c(r2);	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r2 = r5.e();	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r1.d(r2);	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r2 = r5.h();	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r3 = "REQ";
        r1.a(r3, r2);	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r2 = r5.e();	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r3 = 12;
        if (r2 == r3) goto L_0x0042;
    L_0x0034:
        r2 = com.tencent.upload.common.Global.getEnv();	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r3 = "ENV";
        r2 = a(r2);	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r1.a(r3, r2);	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
    L_0x0042:
        r0 = r1.a();	 Catch:{ OutOfMemoryError -> 0x0047, IOException -> 0x0059, Throwable -> 0x008d }
    L_0x0046:
        return r0;
    L_0x0047:
        r2 = move-exception;
        r2 = "NetworkRequest";
        r3 = "encode request OOM. gc, then retry";
        com.tencent.upload.common.a.a.d(r2, r3);	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        java.lang.System.gc();	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        r0 = r1.a();	 Catch:{ IOException -> 0x0059, OutOfMemoryError -> 0x0073, Throwable -> 0x008d }
        goto L_0x0046;
    L_0x0059:
        r0 = move-exception;
        r1 = "NetworkRequest";
        r2 = new java.lang.StringBuilder;
        r3 = "encode exception. reqId=";
        r2.<init>(r3);
        r3 = r5.f;
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.tencent.upload.log.b.c(r1, r2, r0);
        throw r0;
    L_0x0073:
        r0 = move-exception;
        r1 = "NetworkRequest";
        r2 = new java.lang.StringBuilder;
        r3 = "encode exception. reqId=";
        r2.<init>(r3);
        r3 = r5.f;
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.tencent.upload.log.b.c(r1, r2, r0);
        throw r0;
    L_0x008d:
        r1 = move-exception;
        r2 = "NetworkRequest";
        r3 = new java.lang.StringBuilder;
        r4 = "encode exception. reqId=";
        r3.<init>(r4);
        r4 = r5.f;
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.tencent.upload.log.b.c(r2, r3, r1);
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.upload.c.b.a():byte[]");
    }

    public final int b() {
        return this.b;
    }

    public final int c() {
        return this.f;
    }

    public final String d() {
        return this.e;
    }

    public final int e() {
        String str = this.e;
        k();
        return h.containsKey(str) ? ((Integer) h.get(str)).intValue() : -1;
    }

    public boolean f() {
        return true;
    }

    public final a g() {
        return this.g;
    }

    protected abstract JceStruct h();

    public final stAuth i() {
        return this.c;
    }

    public final FileType j() {
        return this.d;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("taskId=").append(this.b).append(" reqId=").append(this.f).append(" cmd=").append(this.e);
        return stringBuilder.toString();
    }
}
