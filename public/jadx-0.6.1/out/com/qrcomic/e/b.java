package com.qrcomic.e;

import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qrcomic.a.h;
import com.qrcomic.a.i;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.QRComicBuyReqInfo;
import com.qrcomic.entity.f;
import com.qrcomic.entity.k;
import com.qrcomic.entity.l;
import com.qrcomic.entity.n;
import com.qrcomic.entity.o;
import com.qrcomic.entity.p;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.util.g;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import okhttp3.u;
import okhttp3.y;

/* compiled from: QRComicBusinessHandler */
public class b extends a {
    private static final String b = b.class.getSimpleName();
    private static int d = 0;
    private static int e = 1;
    private int c = d;
    private DisplayMetrics f;

    /* compiled from: QRComicBusinessHandler */
    public static class a {
        public int a;
        public String b;
        public ArrayList<String> c;
        public int d;
        public String e;

        public String toString() {
            return "comicId = " + this.b + " , isAutoBuy = " + this.d + " , sectionIdList = " + this.c + " , code = " + this.a;
        }
    }

    /* compiled from: QRComicBusinessHandler */
    public static class b {
        public String a;
        public String b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g = -1;
    }

    /* compiled from: QRComicBusinessHandler */
    public static class c {
        public ArrayList<QRComicBuyReqInfo> a;
        public int b;
        public Bundle c;
    }

    /* compiled from: QRComicBusinessHandler */
    public static class d {
        public List<l> a;
        public int b;
        public Bundle c;
        public boolean d;
    }

    /* compiled from: QRComicBusinessHandler */
    public static class e {
        public String a;
        public String b;
        public int c = -1;
    }

    public void a(java.lang.String r14, java.lang.String r15, int r16, int r17, int r18, int r19, boolean r20) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1439)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1461)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
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
        r13 = this;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "queryComicDetail , comicId = ";
        r1 = r1.append(r2);
        r1 = r1.append(r14);
        r2 = " , page_type = ";
        r1 = r1.append(r2);
        r0 = r18;
        r1 = r1.append(r0);
        r2 = " , count = ";
        r1 = r1.append(r2);
        r0 = r16;
        r1 = r1.append(r0);
        r2 = " , begin = ";
        r1 = r1.append(r2);
        r1 = r1.append(r15);
        r2 = " isForceRequest = ";
        r1 = r1.append(r2);
        r0 = r20;
        r1 = r1.append(r0);
        r1 = r1.toString();
        r13.b(r1);
        r1 = android.text.TextUtils.isEmpty(r14);
        if (r1 != 0) goto L_0x0053;
    L_0x004f:
        if (r18 < 0) goto L_0x0053;
    L_0x0051:
        if (r16 >= 0) goto L_0x0054;
    L_0x0053:
        return;
    L_0x0054:
        r9 = new com.qrcomic.e.b$b;
        r9.<init>();
        r6 = 0;
        if (r20 != 0) goto L_0x0211;
    L_0x005c:
        r1 = r13.a;
        r2 = 1;
        r1 = r1.a(r2);
        r1 = (com.qrcomic.manager.QRComicManager) r1;
        r7 = new com.qrcomic.entity.o;
        r7.<init>();
        r8 = r1.a(r14);
        if (r8 == 0) goto L_0x0211;
    L_0x0070:
        r2 = r8.o;
        r5 = 0;
        r3 = 0;
        r4 = r2.indexOf(r15);
        r2 = 0;
        r10 = 1;
        r0 = r17;
        if (r0 != r10) goto L_0x01d2;
    L_0x007e:
        r3 = r4 + r16;
        r2 = r16 + 1;
    L_0x0082:
        r1 = r1.a(r14, r4, r3);
        if (r1 == 0) goto L_0x01f0;
    L_0x0088:
        r3 = r1.size();
        if (r3 != r2) goto L_0x01f0;
    L_0x008e:
        r8.p = r1;
        r8.z = r15;
        r1 = 1;
        r8.E = r1;
        r0 = r17;
        r8.B = r0;
        r0 = r16;
        r8.A = r0;
        r0 = r18;
        r8.C = r0;
        r0 = r19;
        r8.D = r0;
        r7.e = r8;
        r1 = com.qrcomic.entity.o.a;
        r7.c = r1;
        r1 = com.qrcomic.entity.o.b;
        r7.d = r1;
        r1 = r7.e;
        r1 = (com.qrcomic.entity.a) r1;
        r2 = 1;
        r13.a(r9, r1, r2);
        r1 = com.qrcomic.util.g.a();
        if (r1 == 0) goto L_0x00c7;
    L_0x00bd:
        r1 = b;
        r2 = com.qrcomic.util.g.d;
        r3 = " 请求漫画话别信息,本地数据库文件";
        com.qrcomic.util.g.a(r1, r2, r3);
    L_0x00c7:
        r1 = 1;
        r8 = r1;
    L_0x00c9:
        r1 = com.qrcomic.util.g.a();
        if (r1 == 0) goto L_0x00ea;
    L_0x00cf:
        r1 = b;
        r2 = com.qrcomic.util.g.d;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = " 开始拉取服务器的漫画信息。。。 本地数据回调状态 = ";
        r3 = r3.append(r4);
        r3 = r3.append(r8);
        r3 = r3.toString();
        com.qrcomic.util.g.a(r1, r2, r3);
    L_0x00ea:
        r1 = 0;
        r10 = com.qrcomic.downloader.c.b.f.a(r1);
        r11 = new okhttp3.w$a;
        r11.<init>();
        r1 = r13.c;
        r2 = d;
        if (r1 != r2) goto L_0x011e;
    L_0x00fa:
        r1 = r13.a;
        r2 = r13.a;
        r2 = r2.b();
        r1 = r1.c(r2);
        r2 = 0;
        r7 = r1[r2];
        r1 = r14;
        r2 = r15;
        r3 = r16;
        r4 = r17;
        r5 = r18;
        r6 = r19;
        r1 = com.qrcomic.a.i.a(r1, r2, r3, r4, r5, r6, r7);
        r1 = r11.a(r1);
        r1.a();
    L_0x011e:
        com.qrcomic.downloader.c.b.f.a(r11);
        r1 = r11.b();
        r1 = r10.a(r1);
        r2 = 0;
        r9.a = r14;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r0 = r19;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r9.f = r0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r9.b = r15;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r0 = r16;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r9.c = r0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r0 = r17;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r9.d = r0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r0 = r18;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r9.e = r0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r2 = r1.a();	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r2.b();	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        if (r1 != r3) goto L_0x0216;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x014a:
        r1 = new com.google.gson.Gson;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.<init>();	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r3 = r2.g();	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r3 = r3.e();	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r4 = new com.qrcomic.e.b$1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r4.<init>(r13);	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r4 = r4.getType();	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r1.fromJson(r3, r4);	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.o) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        if (r1 != 0) goto L_0x0255;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x0168:
        r1 = new com.qrcomic.entity.o;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.<init>();	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r3 = r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x016e:
        r1 = r3.c;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        if (r1 == 0) goto L_0x01c1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x0172:
        r1 = new com.qrcomic.entity.a;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.<init>();	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r3.e = r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r3.e;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.a) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.a = r14;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r3.e;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.a) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.z = r15;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r3.e;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.a) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r0 = r16;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.A = r0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r3.e;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.a) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r0 = r17;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.r = r0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r3.e;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.a) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r0 = r18;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.C = r0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r3.e;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.a) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r0 = r19;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.D = r0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r3.e;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.a) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r4 = r3.c;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.y = r4;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r3.e;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.a) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r0 = r17;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.B = r0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r3.e;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.a) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r4 = r3.d;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1.x = r4;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = r3.c;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r9.g = r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x01c1:
        r1 = r3.e;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = (com.qrcomic.entity.a) r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        if (r8 != 0) goto L_0x0214;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x01c7:
        r3 = 1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x01c8:
        r13.a(r9, r1, r3);	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x01cb:
        if (r2 == 0) goto L_0x0053;
    L_0x01cd:
        r2.close();
        goto L_0x0053;
    L_0x01d2:
        r10 = 2;
        r0 = r17;
        if (r0 != r10) goto L_0x01e0;
    L_0x01d7:
        r3 = r4 - r16;
        r2 = r16 + 1;
        r12 = r4;
        r4 = r3;
        r3 = r12;
        goto L_0x0082;
    L_0x01e0:
        r10 = 3;
        r0 = r17;
        if (r0 != r10) goto L_0x0258;
    L_0x01e5:
        r5 = r4 - r16;
        r3 = r4 + r16;
        r2 = r16 * 2;
        r2 = r2 + 1;
        r4 = r5;
        goto L_0x0082;
    L_0x01f0:
        r1 = com.qrcomic.util.g.a();
        if (r1 == 0) goto L_0x0211;
    L_0x01f6:
        r1 = b;
        r2 = com.qrcomic.util.g.d;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = " 获取本地漫画信息存在。但是 校验失败 ：";
        r3 = r3.append(r4);
        r3 = r3.append(r8);
        r3 = r3.toString();
        com.qrcomic.util.g.a(r1, r2, r3);
    L_0x0211:
        r8 = r6;
        goto L_0x00c9;
    L_0x0214:
        r3 = 0;
        goto L_0x01c8;
    L_0x0216:
        r3 = 0;
        if (r8 != 0) goto L_0x0233;
    L_0x0219:
        r1 = 1;
    L_0x021a:
        r13.a(r9, r3, r1);	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        goto L_0x01cb;
    L_0x021e:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = -6;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r9.g = r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r3 = 0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        if (r8 != 0) goto L_0x0235;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x0228:
        r1 = 1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x0229:
        r13.a(r9, r3, r1);	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        if (r2 == 0) goto L_0x0053;
    L_0x022e:
        r2.close();
        goto L_0x0053;
    L_0x0233:
        r1 = 0;
        goto L_0x021a;
    L_0x0235:
        r1 = 0;
        goto L_0x0229;
    L_0x0237:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r1 = -2;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r9.g = r1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        r3 = 0;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        if (r8 != 0) goto L_0x024c;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x0241:
        r1 = 1;	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
    L_0x0242:
        r13.a(r9, r3, r1);	 Catch:{ IOException -> 0x021e, Exception -> 0x0237, all -> 0x024e }
        if (r2 == 0) goto L_0x0053;
    L_0x0247:
        r2.close();
        goto L_0x0053;
    L_0x024c:
        r1 = 0;
        goto L_0x0242;
    L_0x024e:
        r1 = move-exception;
        if (r2 == 0) goto L_0x0254;
    L_0x0251:
        r2.close();
    L_0x0254:
        throw r1;
    L_0x0255:
        r3 = r1;
        goto L_0x016e;
    L_0x0258:
        r4 = r5;
        goto L_0x0082;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.e.b.a(java.lang.String, java.lang.String, int, int, int, int, boolean):void");
    }

    public b(h hVar) {
        super(hVar);
        this.f = hVar.b().getResources().getDisplayMetrics();
    }

    public static Point a(int i, int i2, int i3) {
        Point point = new Point();
        switch (i) {
            case 1:
                point.x = i3;
                point.y = i3 + i2;
                break;
            case 2:
                point.x = i3 - i2;
                point.y = i3;
                break;
            case 3:
                point.x = i3 - i2;
                point.y = i3 + i2;
                break;
        }
        return point;
    }

    public void a(b bVar, com.qrcomic.entity.a aVar, boolean z) {
        if (a(aVar)) {
            if (z) {
                a(1, true, aVar);
            } else if (g.a()) {
                g.a(b, g.d, " 同步服务器的漫画信息,没有提示UI,只是更新了数据库");
            }
            int i = aVar.D;
            if (!aVar.E && aVar.y == 0) {
                QRComicManager qRComicManager = (QRComicManager) this.a.a(1);
                boolean a = qRComicManager.a(aVar);
                boolean z2 = i == 0 ? qRComicManager.a(aVar.p) && a : a;
                g.a(b, g.d, "SAVE COMIC INFO RESULT = " + z2);
            }
        } else if (z) {
            a(2, true, bVar);
        }
    }

    private boolean a(com.qrcomic.entity.a aVar) {
        if (aVar == null || TextUtils.isEmpty(aVar.a)) {
            return false;
        }
        return true;
    }

    public void a(String str, String str2, boolean z) {
        boolean z2;
        u a;
        okhttp3.w.a aVar;
        y a2;
        b("querySectionDetail , comicId = " + str + " , sectionId = " + str2 + " isForceRequest = " + z);
        e eVar = new e();
        if (!z) {
            o oVar = new o();
            f a3 = ((QRComicManager) this.a.a(1)).a(str, str2);
            if (a3 != null && a3.q != null && a3.q.size() == a3.e) {
                a3.v = true;
                oVar.e = a3;
                oVar.c = o.a;
                oVar.d = o.b;
                a(eVar, (f) oVar.e, oVar.c, "", true);
                if (g.a()) {
                    g.a(b, g.d, "本地获取图片详情信息");
                }
                z2 = true;
                if (g.a()) {
                    g.a(b, g.d, " 开始拉取服务器的图片信息。。。 本地数据回调状态 = " + z2);
                }
                a = com.qrcomic.downloader.c.b.f.a(null);
                aVar = new okhttp3.w.a();
                aVar.a().a(i.a(str, str2, this.a.c(this.a.b())[0]));
                com.qrcomic.downloader.c.b.f.a(aVar);
                a2 = a.a(aVar.b()).a();
                try {
                    eVar.a = str;
                    eVar.b = str2;
                    if (a2 == null && a2.b() == 200) {
                        o oVar2 = (o) new Gson().fromJson(a2.g().e(), new TypeToken<o<f>>(this) {
                            final /* synthetic */ b a;

                            {
                                this.a = r1;
                            }
                        }.getType());
                        int i;
                        String str3;
                        if (oVar2 == null || oVar2.e == null) {
                            i = -2;
                            str3 = "网络异常";
                            if (oVar2 != null) {
                                if (oVar2.c != 0) {
                                    i = oVar2.c;
                                }
                                if (!TextUtils.isEmpty(oVar2.d)) {
                                    str3 = oVar2.d;
                                }
                            }
                            a(eVar, null, i, str3, !z2);
                        } else {
                            boolean z3;
                            eVar.c = oVar2.c;
                            f fVar = (f) oVar2.e;
                            i = oVar2.c;
                            str3 = a2.d();
                            if (z2) {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            a(eVar, fVar, i, str3, z3);
                        }
                    } else {
                        a(eVar, null, -2, "", z2);
                    }
                    if (a2 != null) {
                        a2.close();
                    }
                } catch (Exception e) {
                    e = e;
                    try {
                        Exception e2;
                        e2.printStackTrace();
                        a(eVar, null, -2, "", z2);
                        if (a2 != null) {
                            a2.close();
                        }
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        if (a2 != null) {
                            a2.close();
                        }
                        throw th2;
                    }
                }
            } else if (g.a()) {
                g.a(b, g.d, " 获取本地图片数据。 校验失败 ：" + a3);
            }
        }
        z2 = false;
        if (g.a()) {
            g.a(b, g.d, " 开始拉取服务器的图片信息。。。 本地数据回调状态 = " + z2);
        }
        a = com.qrcomic.downloader.c.b.f.a(null);
        aVar = new okhttp3.w.a();
        aVar.a().a(i.a(str, str2, this.a.c(this.a.b())[0]));
        com.qrcomic.downloader.c.b.f.a(aVar);
        try {
            a2 = a.a(aVar.b()).a();
            eVar.a = str;
            eVar.b = str2;
            if (a2 == null) {
            }
            if (z2) {
            }
            a(eVar, null, -2, "", z2);
            if (a2 != null) {
                a2.close();
            }
        } catch (Exception e3) {
            e2 = e3;
            a2 = null;
            e2.printStackTrace();
            if (z2) {
            }
            a(eVar, null, -2, "", z2);
            if (a2 != null) {
                a2.close();
            }
        } catch (Throwable th3) {
            th2 = th3;
            a2 = null;
            if (a2 != null) {
                a2.close();
            }
            throw th2;
        }
    }

    public void a(e eVar, f fVar, int i, String str, boolean z) {
        if (i == o.a && a(fVar)) {
            List list = fVar.q;
            if (z) {
                a(3, true, list);
            } else if (g.a()) {
                g.a(b, g.d, " 同步服务器的漫画话别详细信息, 没有更新UI,只更新了数据库: " + fVar.toString());
            }
            String str2 = fVar.a;
            String str3 = fVar.b;
            if (!fVar.v && i == 0) {
                if (!((QRComicManager) this.a.a(1)).a(str2, str3, fVar)) {
                    b("updateComicSectionPicInfo fail, comicId " + str2 + ", sectionId " + str3 + "  may not exist");
                    return;
                } else if (g.a()) {
                    g.a(b, g.d, " 储存  section piclist" + fVar);
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        if (z) {
            a(4, true, eVar);
        } else if (g.a() && fVar != null) {
            g.a(b, g.d, " 同步服务器的漫画话别详细信息, 没有更新UI,失败了: " + fVar.toString());
        }
        Object obj;
        if (i == 1005 || i == 1001) {
            obj = new Object[2];
            obj[0] = Integer.valueOf(5);
            obj[1] = new Object[]{Integer.valueOf(i), str, Boolean.valueOf(false)};
            a(47, false, obj);
        } else if (i == 1004) {
            obj = new Object[2];
            obj[0] = Integer.valueOf(5);
            obj[1] = new Object[]{Integer.valueOf(i), str, Boolean.valueOf(true)};
            a(47, false, obj);
        }
    }

    private boolean a(f fVar) {
        if (fVar == null || TextUtils.isEmpty(fVar.a) || TextUtils.isEmpty(fVar.b)) {
            return false;
        }
        if (fVar.q != null) {
            if (fVar.q.isEmpty()) {
                return false;
            }
            for (int i = 0; i < fVar.q.size(); i++) {
                ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) fVar.q.get(i);
                if (comicSectionPicInfo.height <= 0) {
                    comicSectionPicInfo.height = 1018;
                }
                if (comicSectionPicInfo.width <= 0) {
                    comicSectionPicInfo.width = 720;
                }
            }
        }
        return true;
    }

    public void a(String str, String str2, String str3, int i, long j, int i2, boolean z) {
        a(null, 0);
    }

    public void a(com.qrcomic.entity.e eVar, int i) {
    }

    public void a(ArrayList<QRComicBuyReqInfo> arrayList, Bundle bundle, boolean z) {
        y yVar;
        Exception exception;
        Exception e;
        y yVar2;
        c cVar;
        u a;
        okhttp3.w.a aVar;
        String e2;
        Gson gson;
        o oVar;
        boolean z2;
        Object obj;
        y yVar3;
        y yVar4 = null;
        QRComicManager qRComicManager = (QRComicManager) this.a.a(1);
        if (z) {
            yVar = null;
        } else {
            l h = qRComicManager.h(((QRComicBuyReqInfo) arrayList.get(0)).a, this.a.a());
            if (h != null) {
                try {
                    Gson gson2 = new Gson();
                    String str = h.d;
                    h = (l) gson2.fromJson(h.d, new TypeToken<l>(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }
                    }.getType());
                    h.d = str;
                    h.f = true;
                } catch (Exception e3) {
                    exception = e3;
                    yVar2 = null;
                    exception.printStackTrace();
                    yVar = yVar2;
                    if (g.a()) {
                        g.a(b, g.d, " 请求漫画话付费信息, 直接 访问服务器");
                    }
                    cVar = new c();
                    a = com.qrcomic.downloader.c.b.f.a(null);
                    aVar = new okhttp3.w.a();
                    com.qrcomic.downloader.c.b.f.a(aVar);
                    aVar.a();
                    aVar.a(i.a(arrayList));
                    yVar4 = a.a(aVar.b()).a();
                    e2 = yVar4.g().e();
                    if (yVar4.b() == 200) {
                        gson = new Gson();
                        oVar = (o) gson.fromJson(e2, new TypeToken<o<l>>(this) {
                            final /* synthetic */ b a;

                            {
                                this.a = r1;
                            }
                        }.getType());
                        try {
                            if (oVar.c == o.a) {
                            }
                            z2 = false;
                        } catch (Exception e4) {
                            r10 = e4;
                            Object obj2 = oVar;
                            e3 = r10;
                            try {
                                g.a(e3);
                                if (yVar4 == null) {
                                    obj = yVar3;
                                    z2 = false;
                                } else {
                                    yVar4.close();
                                    obj = yVar3;
                                    z2 = false;
                                }
                                a(cVar, oVar, z2, false);
                            } catch (Throwable th) {
                                if (yVar4 != null) {
                                    yVar4.close();
                                }
                            }
                        }
                    } else {
                        z2 = false;
                        obj = yVar;
                    }
                    if (yVar4 != null) {
                        yVar4.close();
                    }
                    a(cVar, oVar, z2, false);
                }
            }
            if (h == null || TextUtils.isEmpty(h.d)) {
                yVar = null;
            } else {
                if (g.a()) {
                    g.a(b, g.d, " 请求本地漫画付费信息 buyInfoData = " + h);
                }
                o oVar2 = new o();
                try {
                    oVar2.c = o.a;
                    oVar2.d = o.b;
                    oVar2.e = h;
                    a(null, oVar2, true, true);
                    if (g.a()) {
                        g.a(b, g.d, " 请求漫画话付费信息,本地数据库文件,并且不再继续访问服务器");
                        return;
                    }
                    return;
                } catch (Exception e32) {
                    Exception exception2;
                    exception2 = e32;
                    obj = oVar2;
                    exception = exception2;
                    exception.printStackTrace();
                    yVar = yVar2;
                    if (g.a()) {
                        g.a(b, g.d, " 请求漫画话付费信息, 直接 访问服务器");
                    }
                    cVar = new c();
                    a = com.qrcomic.downloader.c.b.f.a(null);
                    aVar = new okhttp3.w.a();
                    com.qrcomic.downloader.c.b.f.a(aVar);
                    aVar.a();
                    aVar.a(i.a(arrayList));
                    yVar4 = a.a(aVar.b()).a();
                    e2 = yVar4.g().e();
                    if (yVar4.b() == 200) {
                        z2 = false;
                        obj = yVar;
                    } else {
                        gson = new Gson();
                        oVar = (o) gson.fromJson(e2, /* anonymous class already generated */.getType());
                        if (oVar.c == o.a) {
                        }
                        z2 = false;
                    }
                    if (yVar4 != null) {
                        yVar4.close();
                    }
                    a(cVar, oVar, z2, false);
                }
            }
        }
        if (g.a()) {
            g.a(b, g.d, " 请求漫画话付费信息, 直接 访问服务器");
        }
        cVar = new c();
        try {
            a = com.qrcomic.downloader.c.b.f.a(null);
            aVar = new okhttp3.w.a();
            com.qrcomic.downloader.c.b.f.a(aVar);
            aVar.a();
            aVar.a(i.a(arrayList));
            yVar4 = a.a(aVar.b()).a();
            e2 = yVar4.g().e();
            if (yVar4.b() == 200) {
                gson = new Gson();
                oVar = (o) gson.fromJson(e2, /* anonymous class already generated */.getType());
                if (oVar.c == o.a || oVar.e == null) {
                    z2 = false;
                } else {
                    ((l) oVar.e).d = gson.toJson(oVar.e, new TypeToken<l>(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }
                    }.getType());
                    ((l) oVar.e).c(this.a.a());
                    if (!((l) oVar.e).f) {
                        qRComicManager.a((l) oVar.e, this.a.a());
                    }
                    z2 = true;
                }
            } else {
                z2 = false;
                obj = yVar;
            }
            if (yVar4 != null) {
                yVar4.close();
            }
        } catch (Exception e42) {
            e32 = e42;
            yVar3 = yVar;
            g.a(e32);
            if (yVar4 == null) {
                yVar4.close();
                obj = yVar3;
                z2 = false;
            } else {
                obj = yVar3;
                z2 = false;
            }
            a(cVar, oVar, z2, false);
        }
        a(cVar, oVar, z2, false);
    }

    private void a(c cVar, o<l> oVar, boolean z, boolean z2) {
        if (oVar == null || oVar.e == null || !z) {
            a(28, true, cVar);
            return;
        }
        d dVar = new d();
        dVar.b = QRComicManager.a;
        List arrayList = new ArrayList();
        arrayList.add(oVar.e);
        dVar.a = arrayList;
        dVar.c = null;
        dVar.d = z2;
        try {
            List arrayList2 = new ArrayList();
            p pVar = new p();
            pVar.a = ((l) oVar.e).a;
            pVar.b = ((l) oVar.e).b;
            pVar.d = new HashSet();
            if (((l) oVar.e).e != null) {
                for (n nVar : ((l) oVar.e).e) {
                    if (nVar.b == 1) {
                        pVar.d.add(nVar.a);
                    }
                }
            }
            arrayList2.add(pVar);
            a(27, true, dVar);
        } catch (Exception e) {
            a(28, true, cVar);
        }
    }

    public void a(String str, ArrayList<String> arrayList, int i) {
        y a;
        Exception e;
        Throwable th;
        y yVar = null;
        okhttp3.w.a aVar = new okhttp3.w.a();
        aVar.a().a(i.a(str, arrayList, i, 1, 0));
        com.qrcomic.downloader.c.b.f.a(aVar);
        u a2 = this.a.f().e().a(null);
        a aVar2 = new a();
        aVar2.b = str;
        aVar2.d = i;
        aVar2.c = arrayList;
        try {
            a = a2.a(aVar.b()).a();
            try {
                o oVar = (o) new Gson().fromJson(a.g().e(), new TypeToken<o<k>>(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }
                }.getType());
                aVar2.e = oVar.d;
                aVar2.a = oVar.c;
                a(aVar2, (k) oVar.e, oVar.c);
                if (a != null) {
                    a.close();
                }
            } catch (Exception e2) {
                e = e2;
                yVar = a;
                try {
                    e.printStackTrace();
                    aVar2.a = -2;
                    a(aVar2, null, -2);
                    if (yVar != null) {
                        yVar.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    a = yVar;
                    if (a != null) {
                        a.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            aVar2.a = -2;
            a(aVar2, null, -2);
            if (yVar != null) {
                yVar.close();
            }
        } catch (Throwable th4) {
            th = th4;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    public void a(String str) {
        a(44, true, null);
    }

    public void a(a aVar, k kVar, int i) {
        if (i != 0) {
            a(30, true, aVar);
        } else if (kVar != null) {
            a(29, true, kVar);
            p pVar = new p();
            pVar.a = kVar.a;
            pVar.b = -1;
            pVar.d = new HashSet(kVar.d);
            if (kVar.c == 2) {
                pVar.c = true;
            }
        } else {
            aVar.a = -1022;
            a(30, true, aVar);
        }
    }

    public void a(List<com.qrcomic.entity.b> list, boolean z) {
    }

    public void d() {
    }

    private void b(String str) {
        if (g.a()) {
            g.a(b, g.d, str);
        }
    }

    protected Class<? extends com.qrcomic.a.a> a() {
        return c.class;
    }

    public String toString() {
        return "QRComicBusinessHandler{comicDetailVersion=" + this.c + ", displayMetrics=" + this.f + '}';
    }
}
