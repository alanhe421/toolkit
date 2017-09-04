package com.qrcomic.downloader.c.b;

import android.os.Handler;
import android.os.Looper;
import com.qrcomic.downloader.c.a.a;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.o;
import com.qrcomic.manager.b;
import com.qrcomic.util.g;
import com.tencent.open.SocialConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.e;
import okhttp3.f;
import okhttp3.w;

/* compiled from: PicInfoNetReq */
public class c {
    public final String a = "Authorization";
    public a b;
    public com.qrcomic.f.c c;
    public String d;
    public ComicSectionPicInfo e;
    public boolean f;
    public int g;
    public Map<String, String> h = new HashMap();
    private Handler i = new Handler(Looper.getMainLooper());
    private e j;
    private e k;
    private int l = 1;
    private int m = 1;
    private int n = 0;
    private int o = 0;

    public void a(Map<String, String> map) throws IOException {
        b(map);
    }

    private void b(Map<String, String> map) {
        final long currentTimeMillis = System.currentTimeMillis();
        final boolean z = this.n < this.l;
        if (g.a()) {
            g.a("DOWNLOAD_PIC", g.d, "tryRequestPic " + this.n + " pic info:" + this.e.picId + " | pic index :" + this.e.index);
        }
        this.n++;
        w.a aVar = new w.a();
        this.h.putAll(map);
        f.a(aVar, this.h);
        this.j = b.a().b().f().e().a(null).a(aVar.a(this.d).b());
        final Map<String, String> map2 = map;
        this.j.a(new f(this) {
            final /* synthetic */ c d;

            public void a(okhttp3.e r11, okhttp3.y r12) throws java.io.IOException {
                /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x003a in list [B:15:0x002f]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                /*
                r10 = this;
                r9 = new com.qrcomic.downloader.c.b.a;
                r9.<init>();
                r2 = r11.c();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 != 0) goto L_0x0013;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x000b:
                r2 = r12.b();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 == r3) goto L_0x00a4;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x0013:
                r2 = r4;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 == 0) goto L_0x003b;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x0017:
                r2 = r11.c();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 != 0) goto L_0x003b;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x001d:
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = r5;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.b(r3);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x0024:
                if (r12 == 0) goto L_0x0029;
            L_0x0026:
                r12.close();
            L_0x0029:
                r2 = com.qrcomic.util.g.a();
                if (r2 == 0) goto L_0x003a;
            L_0x002f:
                r2 = "downloadpic";
                r3 = com.qrcomic.util.g.d;
                r4 = "closed buffer";
                com.qrcomic.util.g.b(r2, r3, r4);
            L_0x003a:
                return;
            L_0x003b:
                r2 = r12.b();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r9.b = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r12.d();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r9.a = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.a(r9);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 != 0) goto L_0x0024;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x004f:
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.b;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.a(r9);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                goto L_0x0024;
            L_0x0057:
                r3 = move-exception;
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r6 = 0;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r7 = "4";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.a(r3, r4, r6, r7);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.a(r9);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 != 0) goto L_0x008d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x006b:
                r2 = r4;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 != 0) goto L_0x0203;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x006f:
                r2 = "downloadpic";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = com.qrcomic.util.g.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = "PicInfoNetReq onResponse exception";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                com.qrcomic.util.g.b(r2, r4, r5);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3.printStackTrace();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = com.qrcomic.downloader.c.b.a.a.b;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r9.c = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = "下载图片发生异常";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r9.a = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.b;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.a(r9);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x008d:
                if (r12 == 0) goto L_0x0092;
            L_0x008f:
                r12.close();
            L_0x0092:
                r2 = com.qrcomic.util.g.a();
                if (r2 == 0) goto L_0x003a;
            L_0x0098:
                r2 = "downloadpic";
                r3 = com.qrcomic.util.g.d;
                r4 = "closed buffer";
                com.qrcomic.util.g.b(r2, r3, r4);
                goto L_0x003a;
            L_0x00a4:
                r2 = r12.g();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.e();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = com.qrcomic.util.g.a();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r3 == 0) goto L_0x00ba;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x00b2:
                r3 = "picRes";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = com.qrcomic.util.g.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                com.qrcomic.util.g.b(r3, r4, r2);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x00ba:
                r3 = new com.google.gson.Gson;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3.<init>();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = new com.qrcomic.downloader.c.b.c$1$1;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4.<init>(r10);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = r4.getType();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r3.fromJson(r2, r4);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r0 = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r0 = (com.qrcomic.entity.o) r0;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r8 = r0;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r8.c;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = com.qrcomic.entity.o.a;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 != r3) goto L_0x0135;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x00d6:
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.e;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = r2.comicId;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r8.e;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = (com.qrcomic.entity.o.a) r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.a;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r3.equals(r2);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 == 0) goto L_0x0135;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x00e8:
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.e;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = r2.sectionId;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r8.e;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = (com.qrcomic.entity.o.a) r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.b;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r3.equals(r2);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 == 0) goto L_0x0135;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x00fa:
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.e;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = r2.picId;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r8.e;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = (com.qrcomic.entity.o.a) r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.c;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r3.equals(r2);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 == 0) goto L_0x0135;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x010c:
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = 0;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r6 = 1;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r7 = "2";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.a(r3, r4, r6, r7);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.a(r8, r9);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x011d:
                if (r12 == 0) goto L_0x0122;
            L_0x011f:
                r12.close();
            L_0x0122:
                r2 = com.qrcomic.util.g.a();
                if (r2 == 0) goto L_0x003a;
            L_0x0128:
                r2 = "downloadpic";
                r3 = com.qrcomic.util.g.d;
                r4 = "closed buffer";
                com.qrcomic.util.g.b(r2, r3, r4);
                goto L_0x003a;
            L_0x0135:
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = new java.lang.RuntimeException;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = "信息不匹配";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3.<init>(r4);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r6 = 0;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r7 = "3";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.a(r3, r4, r6, r7);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r4;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 != 0) goto L_0x01fa;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x014c:
                r2 = r8.c;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = com.qrcomic.entity.o.a;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 != r3) goto L_0x01ab;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x0152:
                r2 = com.qrcomic.downloader.c.b.a.a.a;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r9.c = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = "图片信息无法匹配";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r9.a = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x015b:
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.b;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.a(r9);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x0162:
                r2 = com.qrcomic.util.g.a();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 == 0) goto L_0x011d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x0168:
                r2 = "download_pic";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = com.qrcomic.util.g.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4.<init>();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = r5.e;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = r5.toString();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = r4.append(r5);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = " ||||||| ";	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = r4.append(r5);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = r8.e;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = r4.append(r5);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = r4.toString();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                com.qrcomic.util.g.c(r2, r3, r4);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                goto L_0x011d;
            L_0x0193:
                r2 = move-exception;
                if (r12 == 0) goto L_0x0199;
            L_0x0196:
                r12.close();
            L_0x0199:
                r3 = com.qrcomic.util.g.a();
                if (r3 == 0) goto L_0x01aa;
            L_0x019f:
                r3 = "downloadpic";
                r4 = com.qrcomic.util.g.d;
                r5 = "closed buffer";
                com.qrcomic.util.g.b(r3, r4, r5);
            L_0x01aa:
                throw r2;
            L_0x01ab:
                r2 = r8.c;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = 1005; // 0x3ed float:1.408E-42 double:4.965E-321;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                if (r2 != r3) goto L_0x01f0;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x01b1:
                r2 = com.qrcomic.manager.b.a();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.b();	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = 1;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r2.b(r3);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = (com.qrcomic.e.b) r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = 2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = 0;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = 47;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3[r4] = r5;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = 1;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = 3;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r6 = 0;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r7 = r8.c;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5[r6] = r7;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r6 = 1;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r7 = r8.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5[r6] = r7;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r6 = 2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r7 = 0;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r7 = java.lang.Boolean.valueOf(r7);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5[r6] = r7;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3[r4] = r5;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r4 = 47;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r5 = 0;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.a(r4, r5, r3);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                goto L_0x015b;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x01f0:
                r2 = r8.c;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r9.c = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2 = r8.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r9.a = r2;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                goto L_0x015b;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
            L_0x01fa:
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = r5;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.b(r3);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                goto L_0x0162;
            L_0x0203:
                r2 = r10.d;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r3 = r5;	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                r2.b(r3);	 Catch:{ Exception -> 0x0057, all -> 0x0193 }
                goto L_0x008d;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.downloader.c.b.c.1.a(okhttp3.e, okhttp3.y):void");
            }

            public void a(e eVar, IOException iOException) {
                this.d.a(iOException, currentTimeMillis, false, "1");
                a aVar = new a();
                if (!this.d.a(aVar)) {
                    if (z) {
                        this.d.b(map2);
                        return;
                    }
                    aVar.c = a.a.b;
                    aVar.a = "请求图片链接失败";
                    this.d.b.a(aVar);
                }
            }
        });
    }

    private void a(o<o.a> oVar, a aVar) {
        Throwable e;
        final long currentTimeMillis = System.currentTimeMillis();
        final boolean z = this.o < this.m;
        if (g.a()) {
            g.a("DOWNLOAD_PIC", g.d, "tryDownloadBitmap " + this.o + " pic info:" + this.e.picId + " |pic index：" + this.e.index);
        }
        this.o++;
        try {
            w.a aVar2 = new w.a();
            aVar2.a();
            if (((o.a) oVar.e).e != null && ((o.a) oVar.e).e.size() > 0) {
                f.a(aVar2, ((o.a) oVar.e).e);
            }
            this.k = b.a().b().f().e().b(new d(this.b)).a(aVar2.a(((o.a) oVar.e).d).b());
            if (g.a()) {
                g.b("DOWNLOAD_PIC", "DEV", "开始获取腾讯云图片 picIndex=" + this.e.index + " picId=" + this.e.picId);
            }
            final a aVar3 = aVar;
            final o<o.a> oVar2 = oVar;
            this.k.a(new f(this) {
                final /* synthetic */ c e;

                public void a(okhttp3.e r10, okhttp3.y r11) throws java.io.IOException {
                    /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x005a in list [B:54:0x01cb]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
                    /*
                    r9 = this;
                    r0 = com.qrcomic.util.g.a();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r0 == 0) goto L_0x003a;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x0006:
                    r0 = "DOWNLOAD_PIC";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = "DEV";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2.<init>();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r3 = "成功收到腾讯云响应 picIndex=";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = r2.append(r3);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r3 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r3 = r3.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r3 = r3.index;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = r2.append(r3);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r3 = " picId=";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = r2.append(r3);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r3 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r3 = r3.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r3 = r3.picId;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = r2.append(r3);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = r2.toString();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    com.qrcomic.util.g.b(r0, r1, r2);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x003a:
                    r0 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = 0;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = r2;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = 1;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = "comic_download_pic_first_data";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = 0;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r8 = "1";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0.a(r1, r2, r4, r5, r6, r8);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = r4;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = r0.a(r1);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r0 == 0) goto L_0x005b;
                L_0x0055:
                    if (r11 == 0) goto L_0x005a;
                L_0x0057:
                    r11.close();
                L_0x005a:
                    return;
                L_0x005b:
                    r0 = r11.b();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r0 == r1) goto L_0x0076;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x0063:
                    r0 = r5;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r0 == 0) goto L_0x0076;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x0067:
                    r0 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = r6;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = r4;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0.a(r1, r2);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r11 == 0) goto L_0x005a;
                L_0x0072:
                    r11.close();
                    goto L_0x005a;
                L_0x0076:
                    r0 = r11.g();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = r0.d();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = r11.g();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = r0.b();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = com.qrcomic.util.g.a();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r0 == 0) goto L_0x00dc;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x008c:
                    r0 = "downloadPIC";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4.<init>();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = "THREAD ";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = r4.append(r5);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = java.lang.Thread.currentThread();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = r5.getName();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = r4.append(r5);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = com.qrcomic.util.g.d;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = r4.append(r5);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = r4.toString();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5.<init>();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r11.b();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = r5.append(r6);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = " ";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = r5.append(r6);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r11.g();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r6.d();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r6.available();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = r5.append(r6);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = r5.toString();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    com.qrcomic.util.g.b(r0, r4, r5);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x00dc:
                    r0 = 0;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = r4.c;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r4 == 0) goto L_0x013e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x00e3:
                    r4 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = new byte[r4];	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x00e7:
                    r5 = r1.read(r4);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = -1;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r5 == r6) goto L_0x00f8;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x00ee:
                    r6 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r6.c;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r7 = 0;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6.write(r4, r7, r5);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = r0 + r5;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    goto L_0x00e7;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x00f8:
                    r1 = com.qrcomic.util.g.a();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r1 == 0) goto L_0x013e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x00fe:
                    r1 = "downloadPIC";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4.<init>();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = "pic length =";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = r4.append(r5);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = com.qrcomic.util.g.d;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = r4.append(r5);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = r4.toString();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5.<init>();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = java.lang.Thread.currentThread();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = r5.append(r6);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = " ";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = r5.append(r6);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r6.c;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r6.size();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = r5.append(r6);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = r5.toString();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    com.qrcomic.util.g.b(r1, r4, r5);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x013e:
                    r0 = (long) r0;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r0 == 0) goto L_0x0143;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x0143:
                    r0 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = 0;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = r2;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r4 = 1;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r5 = "event_download_comic_pic";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r6.c;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r6 == 0) goto L_0x0195;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x0152:
                    r6 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r6.c;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = r6.size();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r6 = (long) r6;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x015b:
                    r8 = "2";	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0.a(r1, r2, r4, r5, r6, r8);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = r4;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = r11.b();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r0 != r2) goto L_0x0198;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x016b:
                    r0 = com.qrcomic.entity.o.a;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                L_0x016d:
                    r1.c = r0;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = r4;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = r11.d();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0.a = r1;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = r4;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0.d = r1;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = r4;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = r11.b();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0.b = r1;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = r9.e;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0 = r0.b;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r1 = r4;	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    r0.a(r1);	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    if (r11 == 0) goto L_0x005a;
                L_0x0190:
                    r11.close();
                    goto L_0x005a;
                L_0x0195:
                    r6 = 0;
                    goto L_0x015b;
                L_0x0198:
                    r0 = r11.b();	 Catch:{ Exception -> 0x019d, OutOfMemoryError -> 0x01ff }
                    goto L_0x016d;
                L_0x019d:
                    r0 = move-exception;
                    r1 = r0;
                L_0x019f:
                    r1.printStackTrace();	 Catch:{ all -> 0x01f3 }
                    r0 = r9.e;	 Catch:{ all -> 0x01f3 }
                    r2 = r2;	 Catch:{ all -> 0x01f3 }
                    r4 = 0;	 Catch:{ all -> 0x01f3 }
                    r5 = "event_download_comic_pic";	 Catch:{ all -> 0x01f3 }
                    r6 = 0;	 Catch:{ all -> 0x01f3 }
                    r8 = "3";	 Catch:{ all -> 0x01f3 }
                    r0.a(r1, r2, r4, r5, r6, r8);	 Catch:{ all -> 0x01f3 }
                    r0 = r9.e;	 Catch:{ all -> 0x01f3 }
                    r1 = r4;	 Catch:{ all -> 0x01f3 }
                    r0 = r0.a(r1);	 Catch:{ all -> 0x01f3 }
                    if (r0 != 0) goto L_0x01c9;	 Catch:{ all -> 0x01f3 }
                L_0x01bc:
                    r0 = r5;	 Catch:{ all -> 0x01f3 }
                    if (r0 == 0) goto L_0x01d0;	 Catch:{ all -> 0x01f3 }
                L_0x01c0:
                    r0 = r9.e;	 Catch:{ all -> 0x01f3 }
                    r1 = r6;	 Catch:{ all -> 0x01f3 }
                    r2 = r4;	 Catch:{ all -> 0x01f3 }
                    r0.a(r1, r2);	 Catch:{ all -> 0x01f3 }
                L_0x01c9:
                    if (r11 == 0) goto L_0x005a;
                L_0x01cb:
                    r11.close();
                    goto L_0x005a;
                L_0x01d0:
                    r0 = r4;	 Catch:{ all -> 0x01f3 }
                    r1 = -2;	 Catch:{ all -> 0x01f3 }
                    r0.c = r1;	 Catch:{ all -> 0x01f3 }
                    r0 = r4;	 Catch:{ all -> 0x01f3 }
                    r1 = "请求异常";	 Catch:{ all -> 0x01f3 }
                    r0.a = r1;	 Catch:{ all -> 0x01f3 }
                    r0 = r4;	 Catch:{ all -> 0x01f3 }
                    r1 = r9.e;	 Catch:{ all -> 0x01f3 }
                    r0.d = r1;	 Catch:{ all -> 0x01f3 }
                    r1 = r4;	 Catch:{ all -> 0x01f3 }
                    if (r11 != 0) goto L_0x01fa;	 Catch:{ all -> 0x01f3 }
                L_0x01e6:
                    r0 = -2;	 Catch:{ all -> 0x01f3 }
                L_0x01e7:
                    r1.b = r0;	 Catch:{ all -> 0x01f3 }
                    r0 = r9.e;	 Catch:{ all -> 0x01f3 }
                    r0 = r0.b;	 Catch:{ all -> 0x01f3 }
                    r1 = r4;	 Catch:{ all -> 0x01f3 }
                    r0.a(r1);	 Catch:{ all -> 0x01f3 }
                    goto L_0x01c9;
                L_0x01f3:
                    r0 = move-exception;
                    if (r11 == 0) goto L_0x01f9;
                L_0x01f6:
                    r11.close();
                L_0x01f9:
                    throw r0;
                L_0x01fa:
                    r0 = r11.b();	 Catch:{ all -> 0x01f3 }
                    goto L_0x01e7;
                L_0x01ff:
                    r0 = move-exception;
                    r1 = r0;
                    goto L_0x019f;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.downloader.c.b.c.2.a(okhttp3.e, okhttp3.y):void");
                }

                public void a(e eVar, IOException iOException) {
                    if (g.a()) {
                        g.b("DOWNLOAD_PIC", "DEV", "成功收到腾讯云响应 picIndex=" + this.e.e.index + " picId=" + this.e.e.picId);
                    }
                    this.e.a(iOException, currentTimeMillis, false, "event_download_comic_pic", 0, "4");
                    if (!this.e.a(aVar3)) {
                        if (z) {
                            this.e.a(oVar2, aVar3);
                            return;
                        }
                        aVar3.c = -2;
                        aVar3.a = "请求异常";
                        aVar3.d = this.e;
                        aVar3.b = -2;
                        this.e.b.a(aVar3);
                    }
                }
            });
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            a(e, currentTimeMillis, false, "event_download_comic_pic", 0, "5");
        } catch (OutOfMemoryError e3) {
            e = e3;
            e.printStackTrace();
            a(e, currentTimeMillis, false, "event_download_comic_pic", 0, "5");
        }
    }

    private void a(Throwable th, long j, boolean z, String str, long j2, String str2) {
        Object obj = null;
        if (!z) {
            String message = th.getMessage();
            if (message == null || !(message.contains("CertificateException") || message.contains("CertPathValidatorException"))) {
                message = th.toString();
                if (message != null && (message.contains("CertificateException") || message.contains("CertPathValidatorException"))) {
                    obj = 1;
                    this.i.post(new Runnable(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            b.a().a("手机时间与网络时间不一致，\n请调整手机时间", 0);
                        }
                    });
                }
            } else {
                obj = 1;
                this.i.post(new Runnable(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        b.a().a("手机时间与网络时间不一致，\n请调整手机时间", 0);
                    }
                });
            }
        }
        if (b() && !c()) {
            long currentTimeMillis = System.currentTimeMillis();
            Map hashMap = new HashMap();
            hashMap.put("expensive", String.valueOf(currentTimeMillis - j));
            hashMap.put("picId", this.e.picId);
            if (th != null) {
                hashMap.put(SocialConstants.PARAM_SEND_MSG, th.getMessage() + " || " + th.toString());
            }
            hashMap.put("location", str2);
            hashMap.put("reTry", this.o > 1 ? "1" : "0");
            if ("event_download_comic_pic".equals(str) && r2 == null) {
                b.a().b().f().c().a("event_comic_download_pic_exclude_time", hashMap, b.a().b().b(), z, currentTimeMillis - j, j2);
            }
            b.a().b().f().c().a(str, hashMap, b.a().b().b(), z, currentTimeMillis - j, j2);
        }
    }

    private boolean b() {
        try {
            if (com.qrcomic.util.f.a(b.a().b().b())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private void a(Throwable th, long j, boolean z, String str) {
        if (b() && !c()) {
            long currentTimeMillis = System.currentTimeMillis();
            Map hashMap = new HashMap();
            hashMap.put("picId", this.e.picId);
            if (th != null) {
                hashMap.put(SocialConstants.PARAM_SEND_MSG, th.getMessage() + " || " + th.toString());
            }
            hashMap.put("location", str);
            hashMap.put("reTry", this.n > 1 ? "1" : "0");
            hashMap.put("expensive", String.valueOf(currentTimeMillis - j));
            b.a().b().f().c().a("event_comic_get_real_url", hashMap, b.a().b().b(), z, currentTimeMillis - j, 0);
        }
    }

    private boolean c() {
        if ((this.j == null || !this.j.c()) && (this.k == null || !this.k.c())) {
            return false;
        }
        return true;
    }

    private boolean a(a aVar) {
        if ((this.j == null || !this.j.c()) && (this.k == null || !this.k.c())) {
            return false;
        }
        aVar.c = a.a.c;
        aVar.a = "下载任务被取消";
        this.b.a(aVar);
        return true;
    }

    public void a() {
        if (this.j != null) {
            this.j.b();
        }
        if (this.k != null) {
            this.k.b();
        }
    }
}
