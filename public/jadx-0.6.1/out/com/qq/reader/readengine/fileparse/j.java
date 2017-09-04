package com.qq.reader.readengine.fileparse;

import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.readengine.model.BookUmd;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/* compiled from: UMDDecoder */
public class j {
    RandomAccessFile a;
    BookUmd b;
    k c = null;

    /* compiled from: UMDDecoder */
    private class a extends Thread {
        ArrayList<byte[]> a;
        final /* synthetic */ j b;

        private a(j jVar) {
            this.b = jVar;
            this.a = new ArrayList(this.b.b.getChapterNumber());
        }

        public void a(ArrayList<byte[]> arrayList) {
            this.a = arrayList;
        }

        private boolean a(int i) {
            if (i != this.a.size()) {
                return false;
            }
            List arrayList = new ArrayList();
            int i2 = 0;
            while (i2 < i) {
                try {
                    arrayList.add(new String((byte[]) this.a.get(i2), "UTF-16LE"));
                    i2++;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            this.b.b.setChapterTitles(arrayList);
            return true;
        }

        public void run() {
            int chapterNumber = this.b.b.getChapterNumber();
            if (a(chapterNumber)) {
                int[] chapterOffSets = this.b.b.getChapterOffSets();
                List chapterTitles = this.b.b.getChapterTitles();
                if (chapterNumber == chapterOffSets.length && chapterNumber == chapterTitles.size()) {
                    Mark[] markArr = new Mark[chapterNumber];
                    for (int i = 0; i < chapterNumber; i++) {
                        markArr[i] = new LocalMark(this.b.b.getBookName(), this.b.b.getBookPath(), this.b.b.getLength(), 2, false);
                        markArr[i].setStartPoint((long) chapterOffSets[i]).setEncoding(this.b.b.getEncoding()).setDescriptionStr(ao.a((String) chapterTitles.get(i), false));
                    }
                    i.c().a(this.b.b.getBookPath(), markArr, true);
                    return;
                }
                f.b("UMD", "num == offsets.length && num == list.size() NOT EQUAL");
            }
        }
    }

    public j(RandomAccessFile randomAccessFile, BookUmd bookUmd) {
        this.a = randomAccessFile;
        this.b = bookUmd;
        this.c = new k(this.a);
    }

    public byte[] a() throws Exception {
        long f = this.c.f();
        if (this.c.a() == (byte) 36) {
            this.c.a(4);
            byte[] bArr = new byte[(this.c.e() - 9)];
            this.c.a(bArr);
            Inflater inflater = new Inflater();
            inflater.setInput(bArr, 0, bArr.length);
            bArr = new byte[32768];
            inflater.inflate(bArr);
            inflater.end();
            return bArr;
        }
        this.c.a(f);
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b() {
        /*
        r12 = this;
        r10 = 1;
        r2 = 1;
        r1 = 0;
        r0 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0 = r0.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r3 = -560292983; // 0xffffffffde9a9b89 float:-5.5703244E18 double:NaN;
        if (r0 == r3) goto L_0x001f;
    L_0x000f:
        r0 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0.g();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0 = new java.lang.Exception;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r2 = "It's not a umd file!";
        r0.<init>(r2);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        throw r0;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
    L_0x001d:
        r0 = move-exception;
    L_0x001e:
        return r1;
    L_0x001f:
        r0 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0 = r0.a();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r3 = r2;
        r4 = r0;
        r0 = r2;
    L_0x0028:
        if (r0 == 0) goto L_0x02eb;
    L_0x002a:
        switch(r4) {
            case 35: goto L_0x0035;
            case 36: goto L_0x02b6;
            default: goto L_0x002d;
        };	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
    L_0x002d:
        r0 = r1;
    L_0x002e:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.a();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x0028;
    L_0x0035:
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.c();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        switch(r5) {
            case 1: goto L_0x0040;
            case 2: goto L_0x005d;
            case 3: goto L_0x00b4;
            case 4: goto L_0x009f;
            case 5: goto L_0x009f;
            case 6: goto L_0x009f;
            case 7: goto L_0x009f;
            case 8: goto L_0x009f;
            case 9: goto L_0x009f;
            case 10: goto L_0x0299;
            case 11: goto L_0x00e3;
            case 12: goto L_0x0291;
            case 129: goto L_0x01d0;
            case 130: goto L_0x020b;
            case 131: goto L_0x00f7;
            case 132: goto L_0x014c;
            case 135: goto L_0x025a;
            case 241: goto L_0x02a7;
            default: goto L_0x003e;
        };	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
    L_0x003e:
        r0 = r1;
        goto L_0x002e;
    L_0x0040:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 2;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.b();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        if (r4 == r2) goto L_0x0056;
    L_0x004e:
        r0 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0.g();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x001e;
    L_0x0054:
        r0 = move-exception;
        goto L_0x001e;
    L_0x0056:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 2;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x005d:
        r5 = new java.lang.StringBuffer;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5.<init>();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = 1;
        r4.a(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r4.b();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r1;
    L_0x006f:
        r7 = r6 + -5;
        r7 = r7 / 2;
        if (r4 >= r7) goto L_0x0081;
    L_0x0075:
        r7 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r7 = r7.d();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5.append(r7);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 + 1;
        goto L_0x006f;
    L_0x0081:
        r4 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6.<init>();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.toString();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r6.append(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = ".umd";
        r5 = r5.append(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.toString();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4.setBookName(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x009f:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 1;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.b();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 + -5;
        r5.a(r4);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x00b4:
        r5 = new java.lang.StringBuffer;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5.<init>();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = 1;
        r4.a(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r4.b();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r1;
    L_0x00c6:
        r7 = r6 + -5;
        r7 = r7 / 2;
        if (r4 >= r7) goto L_0x00d8;
    L_0x00cc:
        r7 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r7 = r7.d();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5.append(r7);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 + 1;
        goto L_0x00c6;
    L_0x00d8:
        r4 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.toString();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4.setAuthor(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x00e3:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 2;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = (long) r5;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4.setLength(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x00f7:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 2;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = 1;
        r5.a(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        if (r4 == r5) goto L_0x011f;
    L_0x0111:
        r0 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0.g();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0 = new java.lang.Exception;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r2 = "It's not a umd file!";
        r0.<init>(r2);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        throw r0;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
    L_0x011f:
        r4 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5 + -9;
        r5 = r5 / 4;
        r4.setChapterNumber(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.getChapterNumber();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = new int[r4];	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r1;
    L_0x0137:
        r6 = r5.length;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        if (r4 >= r6) goto L_0x0145;
    L_0x013a:
        r6 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r6.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5[r4] = r6;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 + 1;
        goto L_0x0137;
    L_0x0145:
        r4 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4.setChapterOffSets(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x014c:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 2;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = 1;
        r5.a(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        if (r4 == r5) goto L_0x0174;
    L_0x0166:
        r0 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0.g();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0 = new java.lang.Exception;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r2 = "It's not a umd file!";
        r0.<init>(r2);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        throw r0;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
    L_0x0174:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = new java.util.ArrayList;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5.<init>();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = com.qq.reader.common.db.handle.i.c();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r6.getBookPath();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r7 = 0;
        r4 = r4.a(r6, r7);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        if (r4 == 0) goto L_0x01a6;
    L_0x018f:
        r4 = r1;
    L_0x0190:
        r5 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.getChapterNumber();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        if (r4 >= r5) goto L_0x002e;
    L_0x0198:
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.b();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 + 1;
        goto L_0x0190;
    L_0x01a6:
        r4 = r1;
    L_0x01a7:
        r6 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r6.getChapterNumber();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        if (r4 >= r6) goto L_0x01c2;
    L_0x01af:
        r6 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r6.b();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = new byte[r6];	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r7 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r7.a(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5.add(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 + 1;
        goto L_0x01a7;
    L_0x01c2:
        r4 = new com.qq.reader.readengine.fileparse.j$a;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = 0;
        r4.<init>();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4.start();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x01d0:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 2;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = 1;
        r5.a(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        if (r4 == r5) goto L_0x01f8;
    L_0x01ea:
        r0 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0.g();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0 = new java.lang.Exception;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r2 = "It's not a umd file!";
        r0.<init>(r2);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        throw r0;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
    L_0x01f8:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 + -9;
        r4 = r4 / 4;
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 * 4;
        r5.a(r4);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x020b:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 3;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = 1;
        r5.a(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        if (r4 == r5) goto L_0x0233;
    L_0x0225:
        r0 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0.g();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0 = new java.lang.Exception;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r2 = "It's not a umd file!";
        r0.<init>(r2);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        throw r0;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
    L_0x0233:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 + -9;
        r4 = new byte[r4];	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5.a(r4);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = new java.io.ByteArrayInputStream;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5.<init>(r4);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = com.qq.reader.common.imageloader.c.a(r4);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r6.getImagePath();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4.a(r6, r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x025a:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 4;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = 1;
        r5.a(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = r5.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        if (r4 == r5) goto L_0x0282;
    L_0x0274:
        r0 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0.g();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r0 = new java.lang.Exception;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r2 = "It's not a umd file!";
        r0.<init>(r2);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        throw r0;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
    L_0x0282:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 + -9;
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5.a(r4);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x0291:
        r5 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = 2;
        r5.a(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x0028;
    L_0x0299:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 2;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 4;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x02a7:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 2;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r5 = 16;
        r4.a(r5);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x02b6:
        if (r3 == 0) goto L_0x02c5;
    L_0x02b8:
        r3 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.f();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 - r10;
        r3.setContentStartPoint(r4);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r3 = r1;
    L_0x02c5:
        r4 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4.f();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r4 = r4 - r10;
        r6 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r7 = 4;
        r6.a(r7);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r6.e();	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r6 = r6 + -9;
        r7 = r12.c;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r7.a(r6);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r7 = r12.b;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r8 = new com.qq.reader.readengine.model.Chunk;	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r8.<init>(r6, r4);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        r7.addChunks(r8);	 Catch:{ EOFException -> 0x001d, Exception -> 0x0054 }
        goto L_0x002e;
    L_0x02eb:
        r1 = r2;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.readengine.fileparse.j.b():boolean");
    }
}
