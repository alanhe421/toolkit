package com.qq.reader.plugin;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderNetTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.PluginNetTask;
import com.qq.reader.common.readertask.protocol.SkinNetTask;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.s;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SkinPluginHandler */
public class y extends a {
    private String n = null;
    private String o = null;
    private String p = null;

    protected y(Context context, l lVar, h hVar) {
        super(context, lVar, hVar);
        this.a = context;
    }

    protected synchronized String b(l lVar) {
        if (this.n == null) {
            this.n = w.b().a(lVar.i());
        }
        return this.n;
    }

    protected synchronized String a(l lVar) {
        if (this.i == null) {
            this.i = w.b().b(lVar.i());
        }
        return this.i;
    }

    private String c(l lVar) {
        if (this.o == null) {
            this.o = w.b().a(lVar.i()) + "res.zip";
        }
        return this.o;
    }

    private String d(l lVar) {
        if (this.p == null) {
            this.p = w.b().a(lVar.i()) + "res.zip" + ".temp";
        }
        return this.p;
    }

    public synchronized boolean i() {
        return w.d(a(this.j));
    }

    public boolean l() {
        return i();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void a(java.lang.String r20, android.content.Context r21) {
        /*
        r19 = this;
        r2 = 0;
        r3 = r19.i();	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        if (r3 == 0) goto L_0x0019;
    L_0x0007:
        r3 = new java.io.File;	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        r0 = r19;
        r4 = r0.j;	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        r0 = r19;
        r4 = r0.b(r4);	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        r3.<init>(r4);	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        com.qq.reader.common.utils.ao.c(r3);	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
    L_0x0019:
        r7 = new java.io.File;	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        r0 = r19;
        r3 = r0.j;	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        r0 = r19;
        r3 = r0.d(r3);	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        r7.<init>(r3);	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        r3 = com.qq.reader.common.utils.ab.c(r7);	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        if (r3 != 0) goto L_0x0041;
    L_0x002e:
        r3 = "网络连接失败，请稍后再试。";
        r0 = r19;
        r0.b(r3);	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        if (r2 == 0) goto L_0x003b;
    L_0x0038:
        r2.close();	 Catch:{ IOException -> 0x003c }
    L_0x003b:
        return;
    L_0x003c:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x003b;
    L_0x0041:
        r5 = new java.io.RandomAccessFile;	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        r3 = "rw";
        r5.<init>(r7, r3);	 Catch:{ IOException -> 0x028b, all -> 0x02ee }
        r8 = r5.length();	 Catch:{ IOException -> 0x0321, all -> 0x0318 }
        r5.seek(r8);	 Catch:{ IOException -> 0x0321, all -> 0x0318 }
        r2 = 0;
        r3 = 5120; // 0x1400 float:7.175E-42 double:2.5296E-320;
        r10 = new byte[r3];	 Catch:{ IOException -> 0x0321, all -> 0x0318 }
        r6 = 0;
        r3 = 0;
        r4 = 1;
        r11 = new org.apache.http.message.BasicHeader[r4];	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r4 = 0;
        r12 = new org.apache.http.message.BasicHeader;	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r13 = "Range";
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r14.<init>();	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r15 = "bytes=";
        r14 = r14.append(r15);	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r15 = java.lang.String.valueOf(r8);	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r14 = r14.append(r15);	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r15 = "-";
        r14 = r14.append(r15);	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r14 = r14.toString();	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r12.<init>(r13, r14);	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r11[r4] = r12;	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r4 = 0;
        r0 = r20;
        r1 = r21;
        r4 = com.qq.reader.common.conn.http.b.a(r0, r11, r1);	 Catch:{ RangeException -> 0x00d1 }
    L_0x008d:
        r11 = r4.getContentLength();	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r12 = (long) r11;	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r14 = 0;
        r11 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1));
        if (r11 <= 0) goto L_0x016f;
    L_0x0098:
        r14 = 0;
        r11 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r11 != 0) goto L_0x016f;
    L_0x009e:
        r0 = r19;
        r3 = r0.m;	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r0 = r19;
        r0.a(r7, r3);	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r3 = 0;
        r0 = r19;
        r0.c(r3);	 Catch:{ IOException -> 0x0321, all -> 0x0318 }
        if (r2 == 0) goto L_0x00b2;
    L_0x00af:
        r2.close();	 Catch:{ IOException -> 0x0302, all -> 0x0318 }
    L_0x00b2:
        if (r5 == 0) goto L_0x033d;
    L_0x00b4:
        r5.close();	 Catch:{ IOException -> 0x0305, all -> 0x0318 }
    L_0x00b7:
        r2 = 0;
    L_0x00b8:
        r3 = r19.s();	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        if (r3 == 0) goto L_0x013c;
    L_0x00be:
        r3 = 0;
        r0 = r19;
        r0.d(r3);	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
    L_0x00c4:
        if (r2 == 0) goto L_0x003b;
    L_0x00c6:
        r2.close();	 Catch:{ IOException -> 0x00cb }
        goto L_0x003b;
    L_0x00cb:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x003b;
    L_0x00d1:
        r11 = move-exception;
        r12 = 0;
        r11 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1));
        if (r11 <= 0) goto L_0x008d;
    L_0x00d8:
        r0 = r19;
        r3 = r0.m;	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r0 = r19;
        r0.a(r7, r3);	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r3 = 0;
        r0 = r19;
        r0.c(r3);	 Catch:{ IOException -> 0x0321, all -> 0x0318 }
        if (r2 == 0) goto L_0x00ec;
    L_0x00e9:
        r2.close();	 Catch:{ IOException -> 0x02fc, all -> 0x0318 }
    L_0x00ec:
        if (r5 == 0) goto L_0x0340;
    L_0x00ee:
        r5.close();	 Catch:{ IOException -> 0x02ff, all -> 0x0318 }
    L_0x00f1:
        r2 = 0;
    L_0x00f2:
        r3 = r19.s();	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        if (r3 == 0) goto L_0x010b;
    L_0x00f8:
        r3 = 0;
        r0 = r19;
        r0.d(r3);	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
    L_0x00fe:
        if (r2 == 0) goto L_0x003b;
    L_0x0100:
        r2.close();	 Catch:{ IOException -> 0x0105 }
        goto L_0x003b;
    L_0x0105:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x003b;
    L_0x010b:
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r3.a(r4);	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r3.b(r4);	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r3 = r19.a();	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.j;	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r4 = r4.i();	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r6 = r0.b;	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        r7 = 0;
        r3.a(r4, r5, r6, r7);	 Catch:{ Exception -> 0x013a, IOException -> 0x028b, all -> 0x031a }
        goto L_0x00fe;
    L_0x013a:
        r3 = move-exception;
        goto L_0x00fe;
    L_0x013c:
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r3.a(r4);	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r3.b(r4);	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r3 = r19.a();	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.j;	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r4 = r4.i();	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r6 = r0.b;	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        r7 = 0;
        r3.a(r4, r5, r6, r7);	 Catch:{ Exception -> 0x016c, IOException -> 0x028b, all -> 0x031a }
        goto L_0x00c4;
    L_0x016c:
        r3 = move-exception;
        goto L_0x00c4;
    L_0x016f:
        r4 = r4.getInputStream();	 Catch:{ Exception -> 0x0232, all -> 0x0299 }
        r2 = new android.os.Message;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r2.<init>();	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r11 = 6104; // 0x17d8 float:8.554E-42 double:3.016E-320;
        r2.what = r11;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r14 = r8 + r12;
        r11 = (int) r14;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r2.arg1 = r11;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r0 = r19;
        r11 = r0.m;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r11.sendMessage(r2);	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r2 = r3;
        r3 = r6;
    L_0x018a:
        r6 = r19.s();	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        if (r6 != 0) goto L_0x01c8;
    L_0x0190:
        r6 = r4.read(r10);	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r11 = -1;
        if (r6 == r11) goto L_0x01c8;
    L_0x0197:
        r11 = 0;
        r5.write(r10, r11, r6);	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r14 = r5.length();	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r6 = 10;
        if (r2 >= r6) goto L_0x01a9;
    L_0x01a3:
        r16 = r12 + r8;
        r6 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r6 != 0) goto L_0x01c5;
    L_0x01a9:
        r2 = (int) r14;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        if (r3 >= r2) goto L_0x033a;
    L_0x01ac:
        r3 = new android.os.Message;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r3.<init>();	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r6 = 6105; // 0x17d9 float:8.555E-42 double:3.0163E-320;
        r3.what = r6;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r3.arg1 = r2;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r0 = r19;
        r6 = r0.m;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r6.sendMessage(r3);	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
    L_0x01be:
        r3 = 0;
        r18 = r3;
        r3 = r2;
        r2 = r18;
        goto L_0x018a;
    L_0x01c5:
        r2 = r2 + 1;
        goto L_0x018a;
    L_0x01c8:
        r2 = r19.s();	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        if (r2 != 0) goto L_0x01d7;
    L_0x01ce:
        r0 = r19;
        r2 = r0.m;	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
        r0 = r19;
        r0.a(r7, r2);	 Catch:{ Exception -> 0x032d, all -> 0x0325 }
    L_0x01d7:
        r2 = 0;
        r0 = r19;
        r0.c(r2);	 Catch:{ IOException -> 0x0321, all -> 0x0318 }
        if (r4 == 0) goto L_0x01e2;
    L_0x01df:
        r4.close();	 Catch:{ IOException -> 0x0308, all -> 0x0318 }
    L_0x01e2:
        if (r5 == 0) goto L_0x0337;
    L_0x01e4:
        r5.close();	 Catch:{ IOException -> 0x030b, all -> 0x0318 }
    L_0x01e7:
        r2 = 0;
    L_0x01e8:
        r3 = r19.s();	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        if (r3 == 0) goto L_0x0201;
    L_0x01ee:
        r3 = 0;
        r0 = r19;
        r0.d(r3);	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
    L_0x01f4:
        if (r2 == 0) goto L_0x003b;
    L_0x01f6:
        r2.close();	 Catch:{ IOException -> 0x01fb }
        goto L_0x003b;
    L_0x01fb:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x003b;
    L_0x0201:
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r3.a(r4);	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r3.b(r4);	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r3 = r19.a();	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.j;	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r4 = r4.i();	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r6 = r0.b;	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        r7 = 0;
        r3.a(r4, r5, r6, r7);	 Catch:{ Exception -> 0x0230, IOException -> 0x028b, all -> 0x031a }
        goto L_0x01f4;
    L_0x0230:
        r3 = move-exception;
        goto L_0x01f4;
    L_0x0232:
        r3 = move-exception;
    L_0x0233:
        r3 = "网络连接失败，请稍后再试。";
        r0 = r19;
        r0.b(r3);	 Catch:{ all -> 0x0328 }
        r3 = 0;
        r0 = r19;
        r0.c(r3);	 Catch:{ IOException -> 0x0321, all -> 0x0318 }
        if (r2 == 0) goto L_0x0246;
    L_0x0243:
        r2.close();	 Catch:{ IOException -> 0x030e, all -> 0x0318 }
    L_0x0246:
        if (r5 == 0) goto L_0x0334;
    L_0x0248:
        r5.close();	 Catch:{ IOException -> 0x0311, all -> 0x0318 }
    L_0x024b:
        r2 = 0;
    L_0x024c:
        r3 = r19.s();	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        if (r3 == 0) goto L_0x025b;
    L_0x0252:
        r3 = 0;
        r0 = r19;
        r0.d(r3);	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        goto L_0x01f4;
    L_0x0259:
        r3 = move-exception;
        goto L_0x01f4;
    L_0x025b:
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r3.a(r4);	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r3 = r0.j;	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.b;	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r3.b(r4);	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r3 = r19.a();	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r4 = r0.j;	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r4 = r4.i();	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r0 = r19;
        r6 = r0.b;	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        r7 = 0;
        r3.a(r4, r5, r6, r7);	 Catch:{ Exception -> 0x0259, IOException -> 0x028b, all -> 0x031a }
        goto L_0x01f4;
    L_0x028b:
        r3 = move-exception;
    L_0x028c:
        if (r2 == 0) goto L_0x003b;
    L_0x028e:
        r2.close();	 Catch:{ IOException -> 0x0293 }
        goto L_0x003b;
    L_0x0293:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x003b;
    L_0x0299:
        r3 = move-exception;
        r4 = r2;
        r2 = r3;
    L_0x029c:
        r3 = 0;
        r0 = r19;
        r0.c(r3);	 Catch:{ IOException -> 0x0321, all -> 0x0318 }
        if (r4 == 0) goto L_0x02a7;
    L_0x02a4:
        r4.close();	 Catch:{ IOException -> 0x0314, all -> 0x0318 }
    L_0x02a7:
        if (r5 == 0) goto L_0x0331;
    L_0x02a9:
        r5.close();	 Catch:{ IOException -> 0x0316, all -> 0x0318 }
    L_0x02ac:
        r3 = 0;
    L_0x02ad:
        r4 = r19.s();	 Catch:{ Exception -> 0x02ec }
        if (r4 == 0) goto L_0x02bd;
    L_0x02b3:
        r4 = 0;
        r0 = r19;
        r0.d(r4);	 Catch:{ Exception -> 0x02ec }
    L_0x02b9:
        throw r2;	 Catch:{ IOException -> 0x02ba, all -> 0x031e }
    L_0x02ba:
        r2 = move-exception;
        r2 = r3;
        goto L_0x028c;
    L_0x02bd:
        r0 = r19;
        r4 = r0.j;	 Catch:{ Exception -> 0x02ec }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x02ec }
        r4.a(r5);	 Catch:{ Exception -> 0x02ec }
        r0 = r19;
        r4 = r0.j;	 Catch:{ Exception -> 0x02ec }
        r0 = r19;
        r5 = r0.b;	 Catch:{ Exception -> 0x02ec }
        r4.b(r5);	 Catch:{ Exception -> 0x02ec }
        r4 = r19.a();	 Catch:{ Exception -> 0x02ec }
        r0 = r19;
        r5 = r0.j;	 Catch:{ Exception -> 0x02ec }
        r5 = r5.i();	 Catch:{ Exception -> 0x02ec }
        r0 = r19;
        r6 = r0.b;	 Catch:{ Exception -> 0x02ec }
        r0 = r19;
        r7 = r0.b;	 Catch:{ Exception -> 0x02ec }
        r8 = 0;
        r4.a(r5, r6, r7, r8);	 Catch:{ Exception -> 0x02ec }
        goto L_0x02b9;
    L_0x02ec:
        r4 = move-exception;
        goto L_0x02b9;
    L_0x02ee:
        r3 = move-exception;
        r5 = r2;
        r2 = r3;
    L_0x02f1:
        if (r5 == 0) goto L_0x02f6;
    L_0x02f3:
        r5.close();	 Catch:{ IOException -> 0x02f7 }
    L_0x02f6:
        throw r2;
    L_0x02f7:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x02f6;
    L_0x02fc:
        r2 = move-exception;
        goto L_0x00ec;
    L_0x02ff:
        r2 = move-exception;
        goto L_0x00f1;
    L_0x0302:
        r2 = move-exception;
        goto L_0x00b2;
    L_0x0305:
        r2 = move-exception;
        goto L_0x00b7;
    L_0x0308:
        r2 = move-exception;
        goto L_0x01e2;
    L_0x030b:
        r2 = move-exception;
        goto L_0x01e7;
    L_0x030e:
        r2 = move-exception;
        goto L_0x0246;
    L_0x0311:
        r2 = move-exception;
        goto L_0x024b;
    L_0x0314:
        r3 = move-exception;
        goto L_0x02a7;
    L_0x0316:
        r3 = move-exception;
        goto L_0x02ac;
    L_0x0318:
        r2 = move-exception;
        goto L_0x02f1;
    L_0x031a:
        r3 = move-exception;
        r5 = r2;
        r2 = r3;
        goto L_0x02f1;
    L_0x031e:
        r2 = move-exception;
        r5 = r3;
        goto L_0x02f1;
    L_0x0321:
        r2 = move-exception;
        r2 = r5;
        goto L_0x028c;
    L_0x0325:
        r2 = move-exception;
        goto L_0x029c;
    L_0x0328:
        r3 = move-exception;
        r4 = r2;
        r2 = r3;
        goto L_0x029c;
    L_0x032d:
        r2 = move-exception;
        r2 = r4;
        goto L_0x0233;
    L_0x0331:
        r3 = r5;
        goto L_0x02ad;
    L_0x0334:
        r2 = r5;
        goto L_0x024c;
    L_0x0337:
        r2 = r5;
        goto L_0x01e8;
    L_0x033a:
        r2 = r3;
        goto L_0x01be;
    L_0x033d:
        r2 = r5;
        goto L_0x00b8;
    L_0x0340:
        r2 = r5;
        goto L_0x00f2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.y.a(java.lang.String, android.content.Context):void");
    }

    protected void b(String str) {
        this.m.obtainMessage(6109, str).sendToTarget();
    }

    protected void a(Bundle bundle) {
        Message obtainMessage = this.m.obtainMessage();
        obtainMessage.obj = bundle;
        obtainMessage.what = 6117;
        this.m.handleMessage(obtainMessage);
    }

    public boolean k() {
        if (this.h) {
            return false;
        }
        this.h = true;
        try {
            ao.c(new File(b(this.j)));
            this.m.sendEmptyMessage(6110);
            this.h = false;
            return true;
        } catch (Exception e) {
            f.a("SkinPluginHandler", "uninstall mPluginId = " + this.j.i() + "  " + e.toString());
            this.h = false;
            return false;
        }
    }

    protected void a(String str) {
        this.m.obtainMessage(6113, str).sendToTarget();
    }

    public boolean j() {
        if (this.j.d() != 4 || i()) {
            return true;
        }
        File file = new File(a(this.j));
        if (file.exists()) {
            file.delete();
        }
        a().a(this.j.i(), 0, 0, null, 4);
        this.j.b(0);
        return false;
    }

    private void a(File file, Handler handler) {
        File file2 = new File(c(this.j));
        if (ab.c(file2)) {
            file.renameTo(file2);
            try {
                String a = a(this.j);
                ao.f(file2.getAbsolutePath(), a);
                file.delete();
                file2.delete();
                Object w = ((x) this.j).w();
                int[] iArr = new int[]{0, 0, 0};
                if (!TextUtils.isEmpty(w)) {
                    String[] split = w.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    int i = 0;
                    while (split != null && i < split.length && i < iArr.length) {
                        Object obj = split[i];
                        if (!TextUtils.isEmpty(obj)) {
                            obj = obj.replace("#", "").replace("0x", "");
                            if (!TextUtils.isEmpty(obj)) {
                                iArr[i] = Integer.parseInt(obj, 16);
                            }
                        }
                        i++;
                    }
                }
                z.a(a, this.a, iArr[0], 1);
                z.a(a, this.a, iArr[1], 2);
                z.a(a, this.a, iArr[2], 3);
                handler.sendEmptyMessage(6108);
                if (this.j != null) {
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, this.j.i());
                    i.a("event_A165", hashMap, ReaderApplication.getApplicationImp());
                    return;
                }
                return;
            } catch (Exception e) {
                handler.sendEmptyMessage(6109);
                return;
            }
        }
        handler.sendEmptyMessage(6109);
    }

    public PluginNetTask q() {
        return new SkinNetTask(new c(this) {
            final /* synthetic */ y a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.a.a((ReaderNetTask) readerProtocolTask, str);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.b("网络连接失败，请稍后再试。");
            }
        }, this.j.i(), this.j.b());
    }
}
