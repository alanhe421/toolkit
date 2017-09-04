package com.qq.reader.plugin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.monitor.f;
import java.util.ArrayList;

/* compiled from: SkinDatebaseHandle */
public class v extends b implements h {
    private static volatile v b = null;
    private static volatile u c = null;
    String a = "SkinListHandle";

    public boolean a(java.lang.String r9, long r10, int r12, java.lang.String r13, int r14) {
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
        r8 = this;
        r7 = 4;
        r6 = 2;
        r0 = 1;
        r1 = 0;
        if (r9 == 0) goto L_0x0012;
    L_0x0006:
        r2 = r9.length();
        if (r2 == 0) goto L_0x0012;
    L_0x000c:
        r2 = 0;
        r2 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x0014;
    L_0x0012:
        r0 = r1;
    L_0x0013:
        return r0;
    L_0x0014:
        r2 = c;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        if (r2 != 0) goto L_0x0026;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x0018:
        r2 = new com.qq.reader.plugin.u;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3 = com.qq.reader.plugin.w.c();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = 0;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r5 = 13;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r2.<init>(r3, r4, r5);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        c = r2;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x0026:
        r2 = c;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r2 = r2.a();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3.<init>();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = "update plugin_table_name set ";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3.append(r4);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r14 & 4;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        if (r4 != r7) goto L_0x0052;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x003b:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4.<init>();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r5 = "plugin_max_size = ";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r10);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3.append(r4);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x0052:
        r4 = r14 & 2;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        if (r4 != r6) goto L_0x0077;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x0056:
        r4 = r14 & 4;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        if (r4 != r7) goto L_0x0060;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x005a:
        r4 = ",";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3.append(r4);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x0060:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4.<init>();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r5 = "plugin_download_status = ";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r12);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3.append(r4);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x0077:
        if (r13 == 0) goto L_0x00a4;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x0079:
        r4 = r13.length();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        if (r4 <= 0) goto L_0x00a4;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x007f:
        r4 = r14 & 1;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        if (r4 != r0) goto L_0x00a4;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x0083:
        r4 = r14 & 2;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        if (r4 != r6) goto L_0x008d;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x0087:
        r4 = ",";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3.append(r4);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x008d:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4.<init>();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r5 = "plugin_enable = ";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r13);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3.append(r4);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
    L_0x00a4:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4.<init>();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r5 = " where plugin_id= '";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r9);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r5 = "' and ";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r5 = "plugin_type_id";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r5 = "!= '";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r5 = "";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r5 = "'";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3.append(r4);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r2.execSQL(r3);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r1 = c;
        if (r1 == 0) goto L_0x0013;
    L_0x00e9:
        r1 = c;
        r1.c();
        goto L_0x0013;
    L_0x00f0:
        r0 = move-exception;
        r2 = "PlugInDB";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3.<init>();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r4 = "error in updatePlugins : ";	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        com.qq.reader.common.monitor.f.a(r2, r0);	 Catch:{ Exception -> 0x00f0, all -> 0x011b }
        r0 = c;
        if (r0 == 0) goto L_0x0118;
    L_0x0113:
        r0 = c;
        r0.c();
    L_0x0118:
        r0 = r1;
        goto L_0x0013;
    L_0x011b:
        r0 = move-exception;
        r1 = c;
        if (r1 == 0) goto L_0x0125;
    L_0x0120:
        r1 = c;
        r1.c();
    L_0x0125:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.v.a(java.lang.String, long, int, java.lang.String, int):boolean");
    }

    public boolean a(java.util.ArrayList<com.qq.reader.plugin.x> r9) {
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
        r8 = this;
        r1 = 0;
        r0 = c;	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r1 = r0.a();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.<init>();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r3 = r9.iterator();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
    L_0x0010:
        r0 = r3.hasNext();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        if (r0 == 0) goto L_0x0103;	 Catch:{ Exception -> 0x00db, all -> 0x010c }
    L_0x0016:
        r0 = r3.next();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r0 = (com.qq.reader.plugin.x) r0;	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_id";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.i();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_type_id";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.k();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_name";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.l();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_version";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.m();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_info";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.n();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_str_size";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.o();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "icon_url";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.g();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "image_url";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.h();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_free";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.t();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_price";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.v();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_enable";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.p();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_max_size";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r6 = r0.f();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_download_status";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.d();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_can_download";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.e();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_latest_version";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.b();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_all_version";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r5 = r0.c();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "plugin_skin_color";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r0 = r0.w();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r2.put(r4, r0);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r0 = "plugin_table_name";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = 0;	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r1.insert(r0, r4, r2);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        goto L_0x0010;
    L_0x00db:
        r0 = move-exception;
        r2 = "PlugInDB";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r3.<init>();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r4 = "getAll with exception : ";	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        com.qq.reader.common.monitor.f.a(r2, r0);	 Catch:{ Exception -> 0x00db, all -> 0x010c }
        if (r1 == 0) goto L_0x0101;
    L_0x00fc:
        r0 = c;
        r0.c();
    L_0x0101:
        r0 = 0;
    L_0x0102:
        return r0;
    L_0x0103:
        r0 = 1;
        if (r1 == 0) goto L_0x0102;
    L_0x0106:
        r1 = c;
        r1.c();
        goto L_0x0102;
    L_0x010c:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0114;
    L_0x010f:
        r1 = c;
        r1.c();
    L_0x0114:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.v.a(java.util.ArrayList):boolean");
    }

    public boolean c() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0014 in list [B:5:0x000f]
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
        r5 = this;
        r1 = 0;
        r0 = c;	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r1 = r0.a();	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r0 = c;	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r0.c(r1);	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r0 = 1;
        if (r1 == 0) goto L_0x0014;
    L_0x000f:
        r1 = c;
        r1.c();
    L_0x0014:
        return r0;
    L_0x0015:
        r0 = move-exception;
        r2 = "PlugInDB";	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r3.<init>();	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r4 = "clearPluginDatebase exception : ";	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        com.qq.reader.common.monitor.f.a(r2, r0);	 Catch:{ Exception -> 0x0015, all -> 0x003d }
        if (r1 == 0) goto L_0x003b;
    L_0x0036:
        r0 = c;
        r0.c();
    L_0x003b:
        r0 = 0;
        goto L_0x0014;
    L_0x003d:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0045;
    L_0x0040:
        r1 = c;
        r1.c();
    L_0x0045:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.v.c():boolean");
    }

    public static synchronized v b() {
        v vVar;
        synchronized (v.class) {
            if (b == null) {
                b = new v();
            }
            if (c == null) {
                c = new u(w.c(), null, 13);
            }
            vVar = b;
        }
        return vVar;
    }

    public ArrayList<x> d() {
        Cursor query;
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Cursor cursor;
        Throwable th;
        ArrayList<x> arrayList = new ArrayList();
        try {
            SQLiteDatabase a = c.a();
            try {
                query = a.query("plugin_table_name", new String[]{"plugin_id", "plugin_type_id", "plugin_version", "plugin_name", "plugin_info", "plugin_str_size", "icon_url", "image_url", "plugin_free", "plugin_price", "plugin_enable", "plugin_max_size", "plugin_download_status", "plugin_can_download", "plugin_skin_color", "plugin_latest_version", "plugin_all_version"}, "plugin_type_id!= ''", null, null, null, null);
            } catch (Exception e) {
                sQLiteDatabase = a;
                exception = e;
                cursor = null;
                try {
                    f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                    if (sQLiteDatabase != null) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        c.c();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    query = cursor;
                    if (sQLiteDatabase != null) {
                        if (query != null) {
                            query.close();
                        }
                        c.c();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                query = null;
                sQLiteDatabase = a;
                th = th3;
                if (sQLiteDatabase != null) {
                    if (query != null) {
                        query.close();
                    }
                    c.c();
                }
                throw th;
            }
            try {
                if (query.moveToFirst()) {
                    String str = "";
                    str = "";
                    str = "";
                    str = "";
                    str = "";
                    str = "";
                    str = "";
                    str = "";
                    str = "";
                    str = "";
                    str = "";
                    str = "";
                    str = "";
                    str = "";
                    do {
                        String string = query.getString(0);
                        String string2 = query.getString(1);
                        String string3 = query.getString(2);
                        String string4 = query.getString(3);
                        String string5 = query.getString(4);
                        String string6 = query.getString(5);
                        String string7 = query.getString(6);
                        String string8 = query.getString(7);
                        String string9 = query.getString(8);
                        String string10 = query.getString(9);
                        String string11 = query.getString(10);
                        long j = query.getLong(11);
                        int i = query.getInt(12);
                        int i2 = query.getInt(13);
                        String string12 = query.getString(14);
                        x xVar = new x(string, string2, string4, string3, string5, string6, string7, string8, string9, string10, string11, query.getString(15), query.getString(16));
                        xVar.a(j);
                        xVar.b(i);
                        xVar.c(i2);
                        xVar.i(string12);
                        arrayList.add(xVar);
                    } while (query.moveToNext());
                }
                if (a != null) {
                    if (query != null) {
                        query.close();
                    }
                    c.c();
                }
            } catch (Exception e2) {
                sQLiteDatabase = a;
                exception = e2;
                cursor = query;
                f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                if (sQLiteDatabase != null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    c.c();
                }
                return arrayList;
            } catch (Throwable th32) {
                sQLiteDatabase = a;
                th = th32;
                if (sQLiteDatabase != null) {
                    if (query != null) {
                        query.close();
                    }
                    c.c();
                }
                throw th;
            }
        } catch (Exception e3) {
            exception = e3;
            sQLiteDatabase = null;
            cursor = null;
            f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
            if (sQLiteDatabase != null) {
                if (cursor != null) {
                    cursor.close();
                }
                c.c();
            }
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            sQLiteDatabase = null;
            if (sQLiteDatabase != null) {
                if (query != null) {
                    query.close();
                }
                c.c();
            }
            throw th;
        }
        return arrayList;
    }

    public boolean a(String str, String str2, String str3, String str4) {
        boolean z = false;
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            SQLiteDatabase a = c.a();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("update plugin_table_name set ");
            if (str2 != null) {
                stringBuffer.append("plugin_version = '" + str2 + "'");
            }
            if (str3 != null) {
                stringBuffer.append(" , ");
                stringBuffer.append("plugin_latest_version = '" + str3 + "'");
            }
            if (str4 != null) {
                stringBuffer.append(" , ");
                stringBuffer.append("plugin_all_version = '" + str4 + "'");
            }
            stringBuffer.append(" where plugin_id= '" + str + "' and " + "plugin_type_id" + "!= '" + "" + "'");
            a.execSQL(stringBuffer.toString());
            z = true;
            return z;
        } catch (Exception e) {
            f.a("PlugInDB", "error in updatePluginVersions : " + e.toString());
            return z;
        } finally {
            c.c();
        }
    }

    public boolean a(String str, String str2, String str3) {
        return false;
    }

    public void a() {
        synchronized (v.class) {
            b = null;
            c = null;
        }
    }
}
