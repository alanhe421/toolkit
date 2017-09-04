package com.tencent.qalsdk.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public abstract class ZLibUtils {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] compress(byte[] r5) {
        /*
        r0 = 0;
        r0 = new byte[r0];
        r1 = new java.util.zip.Deflater;
        r1.<init>();
        r1.reset();
        r1.setInput(r5);
        r1.finish();
        r2 = new java.io.ByteArrayOutputStream;
        r0 = r5.length;
        r2.<init>(r0);
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r0];	 Catch:{ Exception -> 0x002a }
    L_0x001b:
        r3 = r1.finished();	 Catch:{ Exception -> 0x002a }
        if (r3 != 0) goto L_0x0035;
    L_0x0021:
        r3 = r1.deflate(r0);	 Catch:{ Exception -> 0x002a }
        r4 = 0;
        r2.write(r0, r4, r3);	 Catch:{ Exception -> 0x002a }
        goto L_0x001b;
    L_0x002a:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0047 }
        r2.close();	 Catch:{ IOException -> 0x0042 }
    L_0x0031:
        r1.end();
        return r5;
    L_0x0035:
        r5 = r2.toByteArray();	 Catch:{ Exception -> 0x002a }
        r2.close();	 Catch:{ IOException -> 0x003d }
        goto L_0x0031;
    L_0x003d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0031;
    L_0x0042:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0031;
    L_0x0047:
        r0 = move-exception;
        r2.close();	 Catch:{ IOException -> 0x004c }
    L_0x004b:
        throw r0;
    L_0x004c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.qalsdk.util.ZLibUtils.compress(byte[]):byte[]");
    }

    public static void compress(byte[] bArr, OutputStream outputStream) throws Exception {
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream);
        try {
            deflaterOutputStream.write(bArr, 0, bArr.length);
            deflaterOutputStream.finish();
            deflaterOutputStream.flush();
        } catch (IOException e) {
            throw new Exception(e.toString());
        }
    }

    public static byte[] decompress(byte[] bArr) throws Exception {
        byte[] bArr2 = new byte[0];
        Inflater inflater = new Inflater();
        inflater.reset();
        inflater.setInput(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        try {
            byte[] bArr3 = new byte[1024];
            while (!inflater.finished()) {
                byteArrayOutputStream.write(bArr3, 0, inflater.inflate(bArr3));
            }
            bArr3 = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                inflater.end();
                return bArr3;
            } catch (IOException e) {
                throw new Exception(e.toString());
            }
        } catch (Exception e2) {
            throw new Exception(e2.toString());
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e3) {
                throw new Exception(e3.toString());
            }
        }
    }

    public static byte[] decompress(InputStream inputStream) {
        int i = 1024;
        InflaterInputStream inflaterInputStream = new InflaterInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                i = inflaterInputStream.read(bArr, 0, i);
                if (i <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
