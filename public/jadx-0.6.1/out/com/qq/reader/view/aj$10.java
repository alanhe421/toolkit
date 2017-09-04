package com.qq.reader.view;

import com.bumptech.glide.request.b.g;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* compiled from: ShareDialog */
class aj$10 extends g<File> {
    FileInputStream a = null;
    ByteArrayOutputStream b = null;
    FileOutputStream c = null;
    final /* synthetic */ aj d;

    public void a(java.io.File r5, com.bumptech.glide.request.a.c<? super java.io.File> r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0045 in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r4 = this;
        if (r5 == 0) goto L_0x00b4;
    L_0x0002:
        r0 = r5.exists();	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        if (r0 == 0) goto L_0x00b4;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
    L_0x0008:
        r0 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0.<init>(r5);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r4.a = r0;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0.<init>();	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r4.b = r0;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = new byte[r0];	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
    L_0x001a:
        r1 = r4.a;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r2 = 0;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r3 = r0.length;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1 = r1.read(r0, r2, r3);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r2 = -1;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        if (r1 == r2) goto L_0x0074;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
    L_0x0025:
        r2 = r4.b;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r3 = 0;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r2.write(r0, r3, r1);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        goto L_0x001a;
    L_0x002c:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = r4.d;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = com.qq.reader.view.aj.c(r0);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1 = 100006; // 0x186a6 float:1.40138E-40 double:4.94095E-319;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0.sendEmptyMessage(r1);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = r4.a;
        if (r0 == 0) goto L_0x0045;
    L_0x0040:
        r0 = r4.a;
        r0.close();
    L_0x0045:
        r0 = r4.c;	 Catch:{ IOException -> 0x0126 }
        if (r0 == 0) goto L_0x004e;	 Catch:{ IOException -> 0x0126 }
    L_0x0049:
        r0 = r4.c;	 Catch:{ IOException -> 0x0126 }
        r0.close();	 Catch:{ IOException -> 0x0126 }
    L_0x004e:
        r0 = r4.b;	 Catch:{ IOException -> 0x012c }
        if (r0 == 0) goto L_0x0057;	 Catch:{ IOException -> 0x012c }
    L_0x0052:
        r0 = r4.b;	 Catch:{ IOException -> 0x012c }
        r0.close();	 Catch:{ IOException -> 0x012c }
    L_0x0057:
        r0 = java.lang.Thread.interrupted();
        if (r0 != 0) goto L_0x0073;
    L_0x005d:
        r0 = r4.d;
        r0 = com.qq.reader.view.aj.b(r0);
        if (r0 == 0) goto L_0x0073;
    L_0x0065:
        r0 = r4.d;
        r0 = com.qq.reader.view.aj.b(r0);
        r1 = new com.qq.reader.view.aj$10$1;
        r1.<init>(r4);
        r0.runOnUiThread(r1);
    L_0x0073:
        return;
    L_0x0074:
        r0 = r4.b;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0.flush();	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = r4.b;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = r0.toByteArray();	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
    L_0x007f:
        r1 = r4.d;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r2 = r4.d;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = com.qq.reader.view.aj.a(r2, r0);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        com.qq.reader.view.aj.a(r1, r0);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = "wangyudong";	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1 = "getPicBitmap";	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        android.util.Log.i(r0, r1);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = r4.a;	 Catch:{ IOException -> 0x0116 }
        if (r0 == 0) goto L_0x009c;	 Catch:{ IOException -> 0x0116 }
    L_0x0097:
        r0 = r4.a;	 Catch:{ IOException -> 0x0116 }
        r0.close();	 Catch:{ IOException -> 0x0116 }
    L_0x009c:
        r0 = r4.c;	 Catch:{ IOException -> 0x011b }
        if (r0 == 0) goto L_0x00a5;	 Catch:{ IOException -> 0x011b }
    L_0x00a0:
        r0 = r4.c;	 Catch:{ IOException -> 0x011b }
        r0.close();	 Catch:{ IOException -> 0x011b }
    L_0x00a5:
        r0 = r4.b;	 Catch:{ IOException -> 0x00af }
        if (r0 == 0) goto L_0x0057;	 Catch:{ IOException -> 0x00af }
    L_0x00a9:
        r0 = r4.b;	 Catch:{ IOException -> 0x00af }
        r0.close();	 Catch:{ IOException -> 0x00af }
        goto L_0x0057;
    L_0x00af:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0057;
    L_0x00b4:
        r0 = r4.d;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = com.qq.reader.view.aj.d(r0);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1 = r4.d;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1 = com.qq.reader.view.aj.b(r1);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = com.qq.reader.common.conn.http.b.a(r0, r1);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        if (r0 == 0) goto L_0x007f;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
    L_0x00c6:
        if (r5 == 0) goto L_0x007f;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
    L_0x00c8:
        r1 = r5.exists();	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        if (r1 != 0) goto L_0x007f;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
    L_0x00ce:
        r5.createNewFile();	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1.<init>(r5);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r4.c = r1;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1 = r4.c;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1.write(r0);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1 = r4.c;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1.flush();	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        goto L_0x007f;
    L_0x00e3:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = r4.d;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = com.qq.reader.view.aj.c(r0);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r1 = 100006; // 0x186a6 float:1.40138E-40 double:4.94095E-319;	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0.sendEmptyMessage(r1);	 Catch:{ IOException -> 0x002c, Exception -> 0x00e3, all -> 0x013c }
        r0 = r4.a;	 Catch:{ IOException -> 0x0132 }
        if (r0 == 0) goto L_0x00fc;	 Catch:{ IOException -> 0x0132 }
    L_0x00f7:
        r0 = r4.a;	 Catch:{ IOException -> 0x0132 }
        r0.close();	 Catch:{ IOException -> 0x0132 }
    L_0x00fc:
        r0 = r4.c;	 Catch:{ IOException -> 0x0137 }
        if (r0 == 0) goto L_0x0105;	 Catch:{ IOException -> 0x0137 }
    L_0x0100:
        r0 = r4.c;	 Catch:{ IOException -> 0x0137 }
        r0.close();	 Catch:{ IOException -> 0x0137 }
    L_0x0105:
        r0 = r4.b;	 Catch:{ IOException -> 0x0110 }
        if (r0 == 0) goto L_0x0057;	 Catch:{ IOException -> 0x0110 }
    L_0x0109:
        r0 = r4.b;	 Catch:{ IOException -> 0x0110 }
        r0.close();	 Catch:{ IOException -> 0x0110 }
        goto L_0x0057;
    L_0x0110:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0057;
    L_0x0116:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x009c;
    L_0x011b:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00a5;
    L_0x0120:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0045;
    L_0x0126:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x004e;
    L_0x012c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0057;
    L_0x0132:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00fc;
    L_0x0137:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0105;
    L_0x013c:
        r0 = move-exception;
        r1 = r4.a;	 Catch:{ IOException -> 0x0159 }
        if (r1 == 0) goto L_0x0146;	 Catch:{ IOException -> 0x0159 }
    L_0x0141:
        r1 = r4.a;	 Catch:{ IOException -> 0x0159 }
        r1.close();	 Catch:{ IOException -> 0x0159 }
    L_0x0146:
        r1 = r4.c;	 Catch:{ IOException -> 0x015e }
        if (r1 == 0) goto L_0x014f;	 Catch:{ IOException -> 0x015e }
    L_0x014a:
        r1 = r4.c;	 Catch:{ IOException -> 0x015e }
        r1.close();	 Catch:{ IOException -> 0x015e }
    L_0x014f:
        r1 = r4.b;	 Catch:{ IOException -> 0x0163 }
        if (r1 == 0) goto L_0x0158;	 Catch:{ IOException -> 0x0163 }
    L_0x0153:
        r1 = r4.b;	 Catch:{ IOException -> 0x0163 }
        r1.close();	 Catch:{ IOException -> 0x0163 }
    L_0x0158:
        throw r0;
    L_0x0159:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0146;
    L_0x015e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x014f;
    L_0x0163:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0158;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.view.aj$10.a(java.io.File, com.bumptech.glide.request.a.c):void");
    }

    aj$10(aj ajVar) {
        this.d = ajVar;
    }
}
