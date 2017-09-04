package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Log;
import com.bumptech.glide.g.h;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.c;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser.ImageType;
import com.tencent.openqq.protocol.imsdk.im_common;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

/* compiled from: Downsampler */
public abstract class f implements a<InputStream> {
    public static final f a = new f() {
        protected int a(int i, int i2, int i3, int i4) {
            return Math.min(i2 / i4, i / i3);
        }

        public String a() {
            return "AT_LEAST.com.bumptech.glide.load.data.bitmap";
        }
    };
    public static final f b = new f() {
        protected int a(int i, int i2, int i3, int i4) {
            int i5 = 1;
            int ceil = (int) Math.ceil((double) Math.max(((float) i2) / ((float) i4), ((float) i) / ((float) i3)));
            int max = Math.max(1, Integer.highestOneBit(ceil));
            if (max >= ceil) {
                i5 = 0;
            }
            return max << i5;
        }

        public String a() {
            return "AT_MOST.com.bumptech.glide.load.data.bitmap";
        }
    };
    public static final f c = new f() {
        protected int a(int i, int i2, int i3, int i4) {
            return 0;
        }

        public String a() {
            return "NONE.com.bumptech.glide.load.data.bitmap";
        }
    };
    private static final Set<ImageType> d = EnumSet.of(ImageType.JPEG, ImageType.PNG_A, ImageType.PNG);
    private static final Queue<Options> e = h.a(0);

    private static android.graphics.Bitmap.Config a(java.io.InputStream r6, com.bumptech.glide.load.DecodeFormat r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x007a in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r5 = 5;
        r0 = com.bumptech.glide.load.DecodeFormat.ALWAYS_ARGB_8888;
        if (r7 == r0) goto L_0x000f;
    L_0x0005:
        r0 = com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;
        if (r7 == r0) goto L_0x000f;
    L_0x0009:
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 16;
        if (r0 != r1) goto L_0x0012;
    L_0x000f:
        r0 = android.graphics.Bitmap.Config.ARGB_8888;
    L_0x0011:
        return r0;
    L_0x0012:
        r1 = 0;
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r6.mark(r0);
        r0 = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r0.<init>(r6);	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r0 = r0.a();	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r6.reset();	 Catch:{ IOException -> 0x0029 }
    L_0x0024:
        if (r0 == 0) goto L_0x0095;
    L_0x0026:
        r0 = android.graphics.Bitmap.Config.ARGB_8888;
        goto L_0x0011;
    L_0x0029:
        r1 = move-exception;
        r2 = "Downsampler";
        r2 = android.util.Log.isLoggable(r2, r5);
        if (r2 == 0) goto L_0x0024;
    L_0x0033:
        r2 = "Downsampler";
        r3 = "Cannot reset the input stream";
        android.util.Log.w(r2, r3, r1);
        goto L_0x0024;
    L_0x003d:
        r0 = move-exception;
        r2 = "Downsampler";	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r3 = 5;	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r2 = android.util.Log.isLoggable(r2, r3);	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        if (r2 == 0) goto L_0x0062;	 Catch:{ IOException -> 0x003d, all -> 0x007c }
    L_0x0048:
        r2 = "Downsampler";	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r3.<init>();	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r4 = "Cannot determine whether the image has alpha or not from header for format ";	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r3 = r3.append(r7);	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        r3 = r3.toString();	 Catch:{ IOException -> 0x003d, all -> 0x007c }
        android.util.Log.w(r2, r3, r0);	 Catch:{ IOException -> 0x003d, all -> 0x007c }
    L_0x0062:
        r6.reset();
        r0 = r1;
        goto L_0x0024;
    L_0x0067:
        r0 = move-exception;
        r2 = "Downsampler";
        r2 = android.util.Log.isLoggable(r2, r5);
        if (r2 == 0) goto L_0x007a;
    L_0x0071:
        r2 = "Downsampler";
        r3 = "Cannot reset the input stream";
        android.util.Log.w(r2, r3, r0);
    L_0x007a:
        r0 = r1;
        goto L_0x0024;
    L_0x007c:
        r0 = move-exception;
        r6.reset();	 Catch:{ IOException -> 0x0081 }
    L_0x0080:
        throw r0;
    L_0x0081:
        r1 = move-exception;
        r2 = "Downsampler";
        r2 = android.util.Log.isLoggable(r2, r5);
        if (r2 == 0) goto L_0x0080;
    L_0x008b:
        r2 = "Downsampler";
        r3 = "Cannot reset the input stream";
        android.util.Log.w(r2, r3, r1);
        goto L_0x0080;
    L_0x0095:
        r0 = android.graphics.Bitmap.Config.RGB_565;
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.f.a(java.io.InputStream, com.bumptech.glide.load.DecodeFormat):android.graphics.Bitmap$Config");
    }

    private static boolean a(java.io.InputStream r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x004c in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r3 = 5;
        r0 = 19;
        r1 = android.os.Build.VERSION.SDK_INT;
        if (r0 > r1) goto L_0x0009;
    L_0x0007:
        r0 = 1;
    L_0x0008:
        return r0;
    L_0x0009:
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r4.mark(r0);
        r0 = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
        r0.<init>(r4);	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
        r0 = r0.b();	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
        r1 = d;	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
        r0 = r1.contains(r0);	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
        r4.reset();	 Catch:{ IOException -> 0x0021 }
        goto L_0x0008;
    L_0x0021:
        r1 = move-exception;
        r2 = "Downsampler";
        r2 = android.util.Log.isLoggable(r2, r3);
        if (r2 == 0) goto L_0x0008;
    L_0x002b:
        r2 = "Downsampler";
        r3 = "Cannot reset the input stream";
        android.util.Log.w(r2, r3, r1);
        goto L_0x0008;
    L_0x0035:
        r0 = move-exception;
        r1 = "Downsampler";	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
        r2 = 5;	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
        r1 = android.util.Log.isLoggable(r1, r2);	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
        if (r1 == 0) goto L_0x0049;	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
    L_0x0040:
        r1 = "Downsampler";	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
        r2 = "Cannot determine the image type from header";	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
        android.util.Log.w(r1, r2, r0);	 Catch:{ IOException -> 0x0035, all -> 0x0062 }
    L_0x0049:
        r4.reset();
    L_0x004c:
        r0 = 0;
        goto L_0x0008;
    L_0x004e:
        r0 = move-exception;
        r1 = "Downsampler";
        r1 = android.util.Log.isLoggable(r1, r3);
        if (r1 == 0) goto L_0x004c;
    L_0x0058:
        r1 = "Downsampler";
        r2 = "Cannot reset the input stream";
        android.util.Log.w(r1, r2, r0);
        goto L_0x004c;
    L_0x0062:
        r0 = move-exception;
        r4.reset();	 Catch:{ IOException -> 0x0067 }
    L_0x0066:
        throw r0;
    L_0x0067:
        r1 = move-exception;
        r2 = "Downsampler";
        r2 = android.util.Log.isLoggable(r2, r3);
        if (r2 == 0) goto L_0x0066;
    L_0x0071:
        r2 = "Downsampler";
        r3 = "Cannot reset the input stream";
        android.util.Log.w(r2, r3, r1);
        goto L_0x0066;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.f.a(java.io.InputStream):boolean");
    }

    protected abstract int a(int i, int i2, int i3, int i4);

    public android.graphics.Bitmap a(java.io.InputStream r21, com.bumptech.glide.load.engine.bitmap_recycle.c r22, int r23, int r24, com.bumptech.glide.load.DecodeFormat r25) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x00c7 in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r20 = this;
        r16 = com.bumptech.glide.g.a.a();
        r17 = r16.b();
        r18 = r16.b();
        r9 = b();
        r8 = new com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
        r0 = r21;
        r1 = r18;
        r8.<init>(r0, r1);
        r19 = com.bumptech.glide.g.c.a(r8);
        r10 = new com.bumptech.glide.g.f;
        r0 = r19;
        r10.<init>(r0);
        r2 = 5242880; // 0x500000 float:7.34684E-39 double:2.590327E-317;
        r0 = r19;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r0.mark(r2);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r3 = 0;
        r2 = new com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
        r0 = r19;	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
        r2.<init>(r0);	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
        r2 = r2.c();	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
        r19.reset();	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r15 = r2;
    L_0x003b:
        r0 = r17;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r9.inTempStorage = r0;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r0 = r20;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r2 = r0.a(r10, r8, r9);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r3 = 0;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r4 = r2[r3];	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r3 = 1;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r5 = r2[r3];	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r3 = com.bumptech.glide.load.resource.bitmap.p.a(r15);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r2 = r20;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r6 = r23;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r7 = r24;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r13 = r2.a(r3, r4, r5, r6, r7);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r6 = r20;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r7 = r10;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r10 = r22;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r11 = r4;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r12 = r5;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r14 = r25;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r3 = r6.a(r7, r8, r9, r10, r11, r12, r13, r14);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r2 = r19.a();	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        if (r2 == 0) goto L_0x00e4;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x006c:
        r3 = new java.lang.RuntimeException;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r3.<init>(r2);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        throw r3;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x0072:
        r2 = move-exception;
        r16.a(r17);
        r0 = r16;
        r1 = r18;
        r0.a(r1);
        r19.b();
        a(r9);
        throw r2;
    L_0x0084:
        r3 = move-exception;
        r4 = "Downsampler";	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r5 = 5;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r4 = android.util.Log.isLoggable(r4, r5);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        if (r4 == 0) goto L_0x0098;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x008f:
        r4 = "Downsampler";	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r5 = "Cannot reset the input stream";	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        android.util.Log.w(r4, r5, r3);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x0098:
        r15 = r2;
        goto L_0x003b;
    L_0x009a:
        r2 = move-exception;
        r4 = "Downsampler";	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
        r5 = 5;	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
        r4 = android.util.Log.isLoggable(r4, r5);	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
        if (r4 == 0) goto L_0x00ae;	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
    L_0x00a5:
        r4 = "Downsampler";	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
        r5 = "Cannot determine the image orientation from header";	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
        android.util.Log.w(r4, r5, r2);	 Catch:{ IOException -> 0x009a, all -> 0x00ca }
    L_0x00ae:
        r19.reset();
        r15 = r3;
        goto L_0x003b;
    L_0x00b3:
        r2 = move-exception;
        r4 = "Downsampler";
        r5 = 5;
        r4 = android.util.Log.isLoggable(r4, r5);
        if (r4 == 0) goto L_0x00c7;
    L_0x00be:
        r4 = "Downsampler";
        r5 = "Cannot reset the input stream";
        android.util.Log.w(r4, r5, r2);
    L_0x00c7:
        r15 = r3;
        goto L_0x003b;
    L_0x00ca:
        r2 = move-exception;
        r19.reset();	 Catch:{ IOException -> 0x00cf }
    L_0x00ce:
        throw r2;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x00cf:
        r3 = move-exception;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r4 = "Downsampler";	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r5 = 5;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r4 = android.util.Log.isLoggable(r4, r5);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        if (r4 == 0) goto L_0x00ce;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x00da:
        r4 = "Downsampler";	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r5 = "Cannot reset the input stream";	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        android.util.Log.w(r4, r5, r3);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        goto L_0x00ce;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x00e4:
        r2 = 0;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        if (r3 == 0) goto L_0x00fe;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x00e7:
        r0 = r22;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r2 = com.bumptech.glide.load.resource.bitmap.p.a(r3, r0, r15);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r4 = r3.equals(r2);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        if (r4 != 0) goto L_0x00fe;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x00f3:
        r0 = r22;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        r4 = r0.a(r3);	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
        if (r4 != 0) goto L_0x00fe;	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x00fb:
        r3.recycle();	 Catch:{ IOException -> 0x0084, all -> 0x0072 }
    L_0x00fe:
        r16.a(r17);
        r0 = r16;
        r1 = r18;
        r0.a(r1);
        r19.b();
        a(r9);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.f.a(java.io.InputStream, com.bumptech.glide.load.engine.bitmap_recycle.c, int, int, com.bumptech.glide.load.DecodeFormat):android.graphics.Bitmap");
    }

    private int a(int i, int i2, int i3, int i4, int i5) {
        int a;
        if (i5 == Integer.MIN_VALUE) {
            i5 = i3;
        }
        if (i4 == Integer.MIN_VALUE) {
            i4 = i2;
        }
        if (i == 90 || i == im_common.WPA_QZONE) {
            a = a(i3, i2, i4, i5);
        } else {
            a = a(i2, i3, i4, i5);
        }
        return Math.max(1, a == 0 ? 0 : Integer.highestOneBit(a));
    }

    private Bitmap a(com.bumptech.glide.g.f fVar, RecyclableBufferedInputStream recyclableBufferedInputStream, Options options, c cVar, int i, int i2, int i3, DecodeFormat decodeFormat) {
        Config a = a((InputStream) fVar, decodeFormat);
        options.inSampleSize = i3;
        options.inPreferredConfig = a;
        if ((options.inSampleSize == 1 || 19 <= VERSION.SDK_INT) && a((InputStream) fVar)) {
            a(options, cVar.b((int) Math.ceil(((double) i) / ((double) i3)), (int) Math.ceil(((double) i2) / ((double) i3)), a));
        }
        return b(fVar, recyclableBufferedInputStream, options);
    }

    public int[] a(com.bumptech.glide.g.f fVar, RecyclableBufferedInputStream recyclableBufferedInputStream, Options options) {
        options.inJustDecodeBounds = true;
        b(fVar, recyclableBufferedInputStream, options);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static Bitmap b(com.bumptech.glide.g.f fVar, RecyclableBufferedInputStream recyclableBufferedInputStream, Options options) {
        if (options.inJustDecodeBounds) {
            fVar.mark(5242880);
        } else {
            recyclableBufferedInputStream.a();
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(fVar, null, options);
        try {
            if (options.inJustDecodeBounds) {
                fVar.reset();
            }
        } catch (Throwable e) {
            if (Log.isLoggable("Downsampler", 6)) {
                Log.e("Downsampler", "Exception loading inDecodeBounds=" + options.inJustDecodeBounds + " sample=" + options.inSampleSize, e);
            }
        }
        return decodeStream;
    }

    @TargetApi(11)
    private static void a(Options options, Bitmap bitmap) {
        if (11 <= VERSION.SDK_INT) {
            options.inBitmap = bitmap;
        }
    }

    @TargetApi(11)
    private static synchronized Options b() {
        Options options;
        synchronized (f.class) {
            synchronized (e) {
                options = (Options) e.poll();
            }
            if (options == null) {
                options = new Options();
                b(options);
            }
        }
        return options;
    }

    private static void a(Options options) {
        b(options);
        synchronized (e) {
            e.offer(options);
        }
    }

    @TargetApi(11)
    private static void b(Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        if (11 <= VERSION.SDK_INT) {
            options.inBitmap = null;
            options.inMutable = true;
        }
    }
}
