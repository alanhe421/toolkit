package com.qq.reader.module.question.loader;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ab;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.loader.d.a;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* compiled from: RecordDataUploader */
public class e {
    private long a = -1;
    private AtomicBoolean b = new AtomicBoolean(true);
    private b c;

    protected e(b bVar) {
        this.c = bVar;
    }

    protected void a(File file, AudioData audioData, a aVar) {
        this.b.set(false);
        if (file == null || !file.exists()) {
            this.b.set(true);
            if (aVar != null) {
                aVar.a(audioData, 0, 1100300);
                return;
            }
            return;
        }
        final long b = ab.b(file.getAbsolutePath());
        final byte[] a = ab.a(file.getAbsolutePath());
        if (a == null) {
            this.b.set(true);
            if (aVar != null) {
                aVar.a(audioData, 0, 1100300);
                return;
            }
            return;
        }
        String a2 = ab.a(a);
        if (b == 0 || a2 == null) {
            this.b.set(true);
            if (aVar != null) {
                aVar.a(audioData, 0, 1100300);
            }
        } else if (com.qq.reader.common.utils.networkUtil.e.a(ReaderApplication.getApplicationImp())) {
            if (aVar != null) {
                aVar.b(audioData);
            }
            final a aVar2 = aVar;
            final AudioData audioData2 = audioData;
            final File file2 = file;
            g.a().a(new RecordUploadTask(new c(this) {
                final /* synthetic */ e f;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        switch (jSONObject.optInt("code")) {
                            case 0:
                                String optString = jSONObject.optString("vid");
                                String optString2 = jSONObject.optString("server");
                                String optString3 = jSONObject.optString("port");
                                String optString4 = jSONObject.optString("checkkey");
                                if (optString.length() == 0 || optString2.length() == 0 || optString3.length() == 0 || optString4.length() == 0) {
                                    this.f.b.set(true);
                                    if (aVar2 != null) {
                                        aVar2.a(audioData2, 0, 1100302);
                                        return;
                                    }
                                    return;
                                }
                                audioData2.b().a(optString);
                                this.f.a(audioData2, file2, optString2, optString3, optString4, a, b, aVar2);
                                return;
                            case 1018:
                                this.f.b.set(true);
                                if (aVar2 != null) {
                                    aVar2.a(audioData2, 0, 1100301);
                                    break;
                                }
                                break;
                        }
                        jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                        if (aVar2 != null) {
                            aVar2.a(audioData2, 0, 1100302);
                        }
                    } catch (Exception e) {
                        this.f.b.set(true);
                        if (aVar2 != null) {
                            aVar2.a(audioData2, 0, 1100305);
                        }
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.f.b.set(true);
                    if (aVar2 != null) {
                        aVar2.a(audioData2, 0, 1100302);
                    }
                }
            }, b, a2));
        } else {
            aVar.b(audioData, 0);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.qq.reader.module.question.data.AudioData r24, java.io.File r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, byte[] r29, long r30, com.qq.reader.module.question.loader.d.a r32) {
        /*
        r23 = this;
        r16 = 0;
        r18 = java.lang.System.currentTimeMillis();
        r8 = r25.getAbsolutePath();
        r4 = com.qq.reader.common.utils.ab.c(r28);
        r12 = 0;
        r6 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
        r5 = (r30 > r6 ? 1 : (r30 == r6 ? 0 : -1));
        if (r5 <= 0) goto L_0x0107;
    L_0x0017:
        r6 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
        r10 = r6;
    L_0x001b:
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "http://";
        r5 = r5.append(r6);
        r0 = r26;
        r5 = r5.append(r0);
        r6 = ":";
        r5 = r5.append(r6);
        r0 = r27;
        r5 = r5.append(r0);
        r6 = "/ftn_handler";
        r5 = r5.append(r6);
        r6 = r5.toString();
        r5 = 0;
        if (r32 == 0) goto L_0x004f;
    L_0x0048:
        r0 = r32;
        r1 = r24;
        r0.b(r1);	 Catch:{ Exception -> 0x01a4, all -> 0x01c8 }
    L_0x004f:
        r9 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x01a4, all -> 0x01c8 }
        r7 = "r";
        r9.<init>(r8, r7);	 Catch:{ Exception -> 0x01a4, all -> 0x01c8 }
        r20 = r10;
        r10 = r12;
        r12 = r20;
    L_0x005c:
        r12 = r12 - r10;
        r12 = (int) r12;
        r5 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ Exception -> 0x0233 }
        r5 = com.qq.reader.common.utils.networkUtil.e.a(r5);	 Catch:{ Exception -> 0x0233 }
        if (r5 == 0) goto L_0x0153;
    L_0x0068:
        r0 = r23;
        r5 = r0.b;	 Catch:{ Exception -> 0x0233 }
        r5 = r5.get();	 Catch:{ Exception -> 0x0233 }
        if (r5 != 0) goto L_0x0153;
    L_0x0072:
        r5 = r29;
        r7 = r30;
        r12 = com.qq.reader.module.question.loader.d.a(r4, r5, r6, r7, r9, r10, r12);	 Catch:{ Exception -> 0x0233 }
        r0 = r23;
        r0.a = r12;	 Catch:{ Exception -> 0x0233 }
        r10 = -1;
        r5 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1));
        if (r5 == 0) goto L_0x010b;
    L_0x0084:
        r5 = r23.b();	 Catch:{ Exception -> 0x0233 }
        if (r5 != 0) goto L_0x0095;
    L_0x008a:
        if (r32 == 0) goto L_0x0095;
    L_0x008c:
        r10 = r32;
        r11 = r24;
        r14 = r30;
        r10.a(r11, r12, r14);	 Catch:{ Exception -> 0x0233 }
    L_0x0095:
        r10 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
        r10 = r10 + r12;
        r5 = (r10 > r30 ? 1 : (r10 == r30 ? 0 : -1));
        if (r5 <= 0) goto L_0x014d;
    L_0x009d:
        r10 = r30;
    L_0x009f:
        r5 = (r12 > r30 ? 1 : (r12 == r30 ? 0 : -1));
        if (r5 < 0) goto L_0x023a;
    L_0x00a3:
        r4 = 1;
        if (r9 == 0) goto L_0x00a9;
    L_0x00a6:
        r9.close();	 Catch:{ IOException -> 0x0197 }
    L_0x00a9:
        r0 = r23;
        r5 = r0.b;
        r6 = 0;
        r7 = 1;
        r5 = r5.compareAndSet(r6, r7);
        if (r5 == 0) goto L_0x0222;
    L_0x00b5:
        if (r4 == 0) goto L_0x01dc;
    L_0x00b7:
        r4 = new com.qq.reader.module.question.loader.RecordUploadCallBackTask;
        r5 = new com.qq.reader.module.question.loader.e$2;
        r0 = r23;
        r1 = r32;
        r2 = r24;
        r5.<init>(r0, r1, r2);
        r6 = r24.b();
        r6 = r6.h();
        r7 = r24.a();
        r7 = r7.g();
        r8 = r24.b();
        r8 = r8.j();
        r4.<init>(r5, r6, r7, r8);
        r5 = com.qq.reader.common.readertask.g.a();
        r5.a(r4);
        r4 = "Audio";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "VideoUploadTask success time = ";
        r5 = r5.append(r6);
        r6 = java.lang.System.currentTimeMillis();
        r6 = r6 - r18;
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.qq.reader.common.monitor.debug.c.a(r4, r5);
    L_0x0106:
        return;
    L_0x0107:
        r10 = r30;
        goto L_0x001b;
    L_0x010b:
        r4 = "Audio";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0233 }
        r5.<init>();	 Catch:{ Exception -> 0x0233 }
        r6 = "VideoUploadTask failed01 time = ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0233 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0233 }
        r6 = r6 - r18;
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0233 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0233 }
        com.qq.reader.common.monitor.debug.c.a(r4, r5);	 Catch:{ Exception -> 0x0233 }
        if (r32 == 0) goto L_0x013b;
    L_0x012d:
        r0 = r23;
        r4 = r0.a;	 Catch:{ Exception -> 0x0233 }
        r6 = 1100303; // 0x10ca0f float:1.541853E-39 double:5.43622E-318;
        r0 = r32;
        r1 = r24;
        r0.a(r1, r4, r6);	 Catch:{ Exception -> 0x0233 }
    L_0x013b:
        if (r9 == 0) goto L_0x0106;
    L_0x013d:
        r9.close();	 Catch:{ IOException -> 0x0141 }
        goto L_0x0106;
    L_0x0141:
        r4 = move-exception;
        r5 = "Audio";
        r4 = r4.toString();
        com.qq.reader.common.monitor.debug.c.a(r5, r4);
        goto L_0x0106;
    L_0x014d:
        r10 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
        r10 = r10 + r12;
        goto L_0x009f;
    L_0x0153:
        r4 = "Audio";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0233 }
        r5.<init>();	 Catch:{ Exception -> 0x0233 }
        r6 = "VideoUploadTask failed02 time = ";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0233 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0233 }
        r6 = r6 - r18;
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0233 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0233 }
        com.qq.reader.common.monitor.debug.c.a(r4, r5);	 Catch:{ Exception -> 0x0233 }
        if (r32 == 0) goto L_0x0183;
    L_0x0175:
        r0 = r23;
        r4 = r0.a;	 Catch:{ Exception -> 0x0233 }
        r6 = 1100303; // 0x10ca0f float:1.541853E-39 double:5.43622E-318;
        r0 = r32;
        r1 = r24;
        r0.a(r1, r4, r6);	 Catch:{ Exception -> 0x0233 }
    L_0x0183:
        if (r9 == 0) goto L_0x0106;
    L_0x0185:
        r9.close();	 Catch:{ IOException -> 0x018a }
        goto L_0x0106;
    L_0x018a:
        r4 = move-exception;
        r5 = "Audio";
        r4 = r4.toString();
        com.qq.reader.common.monitor.debug.c.a(r5, r4);
        goto L_0x0106;
    L_0x0197:
        r5 = move-exception;
        r6 = "Audio";
        r5 = r5.toString();
        com.qq.reader.common.monitor.debug.c.a(r6, r5);
        goto L_0x00a9;
    L_0x01a4:
        r4 = move-exception;
        r9 = r5;
    L_0x01a6:
        r5 = "Audio";
        r4 = r4.toString();	 Catch:{ all -> 0x0231 }
        com.qq.reader.common.monitor.debug.c.a(r5, r4);	 Catch:{ all -> 0x0231 }
        if (r9 == 0) goto L_0x0236;
    L_0x01b2:
        r9.close();	 Catch:{ IOException -> 0x01b9 }
        r4 = r16;
        goto L_0x00a9;
    L_0x01b9:
        r4 = move-exception;
        r5 = "Audio";
        r4 = r4.toString();
        com.qq.reader.common.monitor.debug.c.a(r5, r4);
        r4 = r16;
        goto L_0x00a9;
    L_0x01c8:
        r4 = move-exception;
        r9 = r5;
    L_0x01ca:
        if (r9 == 0) goto L_0x01cf;
    L_0x01cc:
        r9.close();	 Catch:{ IOException -> 0x01d0 }
    L_0x01cf:
        throw r4;
    L_0x01d0:
        r5 = move-exception;
        r6 = "Audio";
        r5 = r5.toString();
        com.qq.reader.common.monitor.debug.c.a(r6, r5);
        goto L_0x01cf;
    L_0x01dc:
        r4 = com.qq.reader.ReaderApplication.getApplicationImp();
        r4 = com.qq.reader.common.utils.networkUtil.e.a(r4);
        if (r4 != 0) goto L_0x01eb;
    L_0x01e6:
        r4 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        java.lang.Thread.sleep(r4);	 Catch:{ InterruptedException -> 0x021d }
    L_0x01eb:
        if (r32 == 0) goto L_0x01fb;
    L_0x01ed:
        r0 = r23;
        r4 = r0.a;
        r6 = 1100303; // 0x10ca0f float:1.541853E-39 double:5.43622E-318;
        r0 = r32;
        r1 = r24;
        r0.a(r1, r4, r6);
    L_0x01fb:
        r4 = "Audio";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "VideoUploadTask failed time = ";
        r5 = r5.append(r6);
        r6 = java.lang.System.currentTimeMillis();
        r6 = r6 - r18;
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.qq.reader.common.monitor.debug.c.a(r4, r5);
        goto L_0x0106;
    L_0x021d:
        r4 = move-exception;
        r4.printStackTrace();
        goto L_0x01eb;
    L_0x0222:
        if (r32 == 0) goto L_0x0106;
    L_0x0224:
        r0 = r23;
        r4 = r0.a;
        r0 = r32;
        r1 = r24;
        r0.a(r1, r4);
        goto L_0x0106;
    L_0x0231:
        r4 = move-exception;
        goto L_0x01ca;
    L_0x0233:
        r4 = move-exception;
        goto L_0x01a6;
    L_0x0236:
        r4 = r16;
        goto L_0x00a9;
    L_0x023a:
        r20 = r10;
        r10 = r12;
        r12 = r20;
        goto L_0x005c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.question.loader.e.a(com.qq.reader.module.question.data.AudioData, java.io.File, java.lang.String, java.lang.String, java.lang.String, byte[], long, com.qq.reader.module.question.loader.d$a):void");
    }

    protected void a() {
        this.b.set(true);
    }

    private boolean b() {
        return this.b.get();
    }
}
