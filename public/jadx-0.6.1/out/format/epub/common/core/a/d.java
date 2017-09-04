package format.epub.common.core.a;

import format.epub.common.b.e;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* compiled from: ZLXMLParser */
final class d {
    private static HashMap<Integer, Queue<char[]>> d = new HashMap();
    private static Queue<b> e = new LinkedList();
    private static HashMap<List<String>, HashMap<String, char[]>> m = new HashMap();
    private final Reader a;
    private final f b;
    private final boolean c;
    private final char[] f;
    private int g;
    private final b h = c();
    private final b i = c();
    private final b j = c();
    private final b k = c();
    private final b l = c();

    private static String a(Map<b, String> map, b bVar) {
        String str = (String) map.get(bVar);
        if (str == null) {
            str = bVar.toString();
            map.put(new b(bVar), str);
        }
        bVar.a();
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized char[] a(int r3) {
        /*
        r1 = format.epub.common.core.a.d.class;
        monitor-enter(r1);
        r0 = d;	 Catch:{ all -> 0x001e }
        r2 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x001e }
        r0 = r0.get(r2);	 Catch:{ all -> 0x001e }
        r0 = (java.util.Queue) r0;	 Catch:{ all -> 0x001e }
        if (r0 == 0) goto L_0x001b;
    L_0x0011:
        r0 = r0.poll();	 Catch:{ all -> 0x001e }
        r0 = (char[]) r0;	 Catch:{ all -> 0x001e }
        if (r0 == 0) goto L_0x001b;
    L_0x0019:
        monitor-exit(r1);
        return r0;
    L_0x001b:
        r0 = new char[r3];	 Catch:{ all -> 0x001e }
        goto L_0x0019;
    L_0x001e:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: format.epub.common.core.a.d.a(int):char[]");
    }

    private static synchronized void a(char[] cArr) {
        synchronized (d.class) {
            Queue queue = (Queue) d.get(Integer.valueOf(cArr.length));
            if (queue == null) {
                queue = new LinkedList();
                d.put(Integer.valueOf(cArr.length), queue);
            }
            queue.add(cArr);
        }
    }

    private static synchronized b c() {
        b bVar;
        synchronized (d.class) {
            bVar = (b) e.poll();
            if (bVar == null) {
                bVar = new b();
            }
        }
        return bVar;
    }

    private static synchronized void a(b bVar) {
        synchronized (d.class) {
            e.add(bVar);
        }
    }

    void a() {
        a(this.f);
        a(this.h);
        a(this.j);
        a(this.k);
        a(this.l);
    }

    d(f fVar, InputStream inputStream, int i) throws IOException {
        int i2;
        String trim;
        this.b = fVar;
        this.c = fVar.g();
        String str = "utf-8";
        char[] a = a(i);
        this.f = a;
        int i3 = 0;
        while (i3 < 256) {
            char read = (char) inputStream.read();
            i2 = i3 + 1;
            a[i3] = read;
            if (read == '>') {
                i3 = 1;
                break;
            }
            i3 = i2;
        }
        i2 = i3;
        i3 = 0;
        this.g = i2;
        if (i3 != 0) {
            trim = new String(a, 0, i2).trim();
            if (trim.startsWith("<?xml") && trim.endsWith("?>")) {
                this.g = 0;
                i3 = trim.indexOf("encoding");
                if (i3 > 0) {
                    i3 = trim.indexOf(34, i3);
                    if (i3 > 0) {
                        int indexOf = trim.indexOf(34, i3 + 1);
                        if (indexOf > 0) {
                            trim = trim.substring(i3 + 1, indexOf);
                            this.a = new InputStreamReader(inputStream, trim);
                        }
                    }
                }
            }
        }
        trim = str;
        this.a = new InputStreamReader(inputStream, trim);
    }

    private static char[] a(HashMap<String, char[]> hashMap, String str) {
        char[] cArr = (char[]) hashMap.get(str);
        if (cArr != null || str.length() <= 0 || str.charAt(0) != '#') {
            return cArr;
        }
        try {
            int parseInt;
            if (str.charAt(1) == 'x') {
                parseInt = Integer.parseInt(str.substring(2), 16);
            } else {
                parseInt = Integer.parseInt(str.substring(1));
            }
            Object obj = new char[]{(char) parseInt};
            try {
                hashMap.put(str, obj);
                return obj;
            } catch (Exception e) {
                return obj;
            }
        } catch (Exception e2) {
            return cArr;
        }
    }

    static synchronized HashMap<String, char[]> a(List<String> list) throws IOException {
        HashMap<String, char[]> hashMap;
        synchronized (d.class) {
            hashMap = (HashMap) m.get(list);
            if (hashMap == null) {
                HashMap<String, char[]> hashMap2 = new HashMap();
                hashMap2.put("amp", new char[]{'&'});
                hashMap2.put("apos", new char[]{'\''});
                hashMap2.put("gt", new char[]{'>'});
                hashMap2.put("lt", new char[]{'<'});
                hashMap2.put("quot", new char[]{'\"'});
                for (String a : list) {
                    InputStream i = e.a(a).i();
                    if (i != null) {
                        new a().a(i, hashMap2);
                    }
                }
                m.put(list, hashMap2);
                hashMap = hashMap2;
            }
        }
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void b() throws java.io.IOException {
        /*
        r32 = this;
        r0 = r32;
        r14 = r0.b;
        r3 = r14.e();
        r15 = a(r3);
        r14.a(r15);
        r0 = r32;
        r0 = r0.a;
        r16 = r0;
        r0 = r32;
        r0 = r0.c;
        r17 = r0;
        if (r17 == 0) goto L_0x007f;
    L_0x001d:
        r3 = new java.util.HashMap;
        r3.<init>();
    L_0x0022:
        r8 = 0;
        r18 = new java.util.ArrayList;
        r18.<init>();
        r0 = r32;
        r0 = r0.f;
        r19 = r0;
        r0 = r32;
        r0 = r0.h;
        r20 = r0;
        r0 = r32;
        r0 = r0.i;
        r21 = r0;
        r0 = r32;
        r0 = r0.j;
        r22 = r0;
        r0 = r32;
        r0 = r0.k;
        r23 = r0;
        r24 = r14.f();
        r0 = r32;
        r0 = r0.l;
        r25 = r0;
        r26 = new java.util.HashMap;
        r26.<init>();
        r27 = new format.epub.common.core.a.c;
        r27.<init>();
        r4 = 10;
        r7 = new java.lang.String[r4];
        r6 = 0;
        r5 = 0;
        r4 = 0;
        r31 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r3;
        r3 = r31;
    L_0x006a:
        r0 = r32;
        r9 = r0.g;
        if (r9 <= 0) goto L_0x0081;
    L_0x0070:
        r0 = r32;
        r10 = r0.g;
        r9 = 0;
        r0 = r32;
        r0.g = r9;
    L_0x0079:
        if (r10 > 0) goto L_0x008a;
    L_0x007b:
        r16.close();
    L_0x007e:
        return;
    L_0x007f:
        r3 = 0;
        goto L_0x0022;
    L_0x0081:
        r0 = r16;
        r1 = r19;
        r10 = r0.read(r1);
        goto L_0x0079;
    L_0x008a:
        r9 = 0;
        r0 = r19;
        r11 = r0.length;
        if (r10 >= r11) goto L_0x055f;
    L_0x0090:
        r0 = r19;
        r9 = r0.length;
        r9 = r9 - r10;
        r11 = 0;
        r0 = r19;
        r1 = r19;
        java.lang.System.arraycopy(r0, r11, r1, r9, r10);
        r0 = r19;
        r10 = r0.length;
        r11 = r10;
    L_0x00a0:
        r13 = r9 + -1;
        r12 = r3;
        r10 = r8;
        r3 = r13;
        r31 = r4;
        r4 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r5;
        r5 = r31;
    L_0x00ad:
        switch(r5) {
            case 0: goto L_0x00b5;
            case 1: goto L_0x01bb;
            case 2: goto L_0x0465;
            case 3: goto L_0x04ab;
            case 4: goto L_0x00b0;
            case 5: goto L_0x00b0;
            case 6: goto L_0x016d;
            case 7: goto L_0x0178;
            case 8: goto L_0x0187;
            case 9: goto L_0x00f6;
            case 10: goto L_0x00df;
            case 11: goto L_0x019e;
            case 12: goto L_0x01aa;
            case 13: goto L_0x00c2;
            case 14: goto L_0x026f;
            case 15: goto L_0x0329;
            case 16: goto L_0x00b0;
            case 17: goto L_0x0309;
            case 18: goto L_0x0315;
            case 19: goto L_0x0458;
            case 20: goto L_0x02d6;
            case 21: goto L_0x03ba;
            case 22: goto L_0x03e0;
            case 23: goto L_0x0406;
            case 24: goto L_0x0102;
            case 25: goto L_0x010d;
            case 26: goto L_0x011f;
            default: goto L_0x00b0;
        };
    L_0x00b0:
        r8 = r9;
        r9 = r10;
    L_0x00b2:
        r10 = r9;
        r9 = r8;
        goto L_0x00ad;
    L_0x00b5:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 60: goto L_0x00bd;
            default: goto L_0x00bc;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x00bc:
        goto L_0x00b5;
    L_0x00bd:
        r5 = 13;
        r4 = r3 + 1;
        goto L_0x00ad;
    L_0x00c2:
        r3 = r3 + 1;
        r5 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r5) {
            case 33: goto L_0x00d5;
            case 47: goto L_0x00cf;
            case 63: goto L_0x00da;
            default: goto L_0x00c9;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x00c9:
        r4 = 1;
        r5 = r4;
        r8 = r9;
        r4 = r3;
        r9 = r10;
        goto L_0x00b2;
    L_0x00cf:
        r5 = 2;
        r4 = r3 + 1;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x00d5:
        r5 = 10;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x00da:
        r5 = 11;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x00df:
        r3 = r3 + 1;
        r5 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r5) {
            case 45: goto L_0x00eb;
            case 91: goto L_0x00ef;
            default: goto L_0x00e6;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x00e6:
        r5 = 9;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x00eb:
        r5 = 6;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x00ef:
        r5 = 24;
        r4 = r3 + 1;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x00f6:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 62: goto L_0x00fe;
            default: goto L_0x00fd;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x00fd:
        goto L_0x00f6;
    L_0x00fe:
        r5 = 3;
        r4 = r3 + 1;
        goto L_0x00ad;
    L_0x0102:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 93: goto L_0x010a;
            default: goto L_0x0109;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x0109:
        goto L_0x0102;
    L_0x010a:
        r5 = 25;
        goto L_0x00ad;
    L_0x010d:
        r3 = r3 + 1;
        r5 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r8 = 93;
        if (r5 != r8) goto L_0x011a;
    L_0x0115:
        r5 = 26;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x011a:
        r5 = 24;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x011f:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r13 = 62;
        if (r8 != r13) goto L_0x0167;
    L_0x0127:
        r8 = r3 - r4;
        r0 = r21;
        r1 = r19;
        r0.a(r1, r4, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r0 = r21;
        r8 = r0.b;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r13 = 8;
        if (r8 <= r13) goto L_0x015d;
    L_0x0138:
        r0 = r21;
        r13 = r0.a;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r28 = new java.lang.String;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r29 = 0;
        r30 = 6;
        r0 = r28;
        r1 = r29;
        r2 = r30;
        r0.<init>(r13, r1, r2);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r29 = "CDATA[";
        r28 = r28.equals(r29);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r28 == 0) goto L_0x015d;
    L_0x0154:
        r28 = 6;
        r8 = r8 + -8;
        r0 = r28;
        r14.a(r13, r0, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x015d:
        r21.a();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r5 = 3;
        r4 = r3 + 1;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x0167:
        r5 = 24;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x016d:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 45: goto L_0x0175;
            default: goto L_0x0174;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x0174:
        goto L_0x016d;
    L_0x0175:
        r5 = 7;
        goto L_0x00ad;
    L_0x0178:
        r3 = r3 + 1;
        r5 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r8 = 45;
        if (r5 != r8) goto L_0x0184;
    L_0x0180:
        r5 = 8;
        goto L_0x00ad;
    L_0x0184:
        r5 = 6;
        goto L_0x00ad;
    L_0x0187:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 45: goto L_0x019a;
            case 62: goto L_0x0193;
            default: goto L_0x018e;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x018e:
        r5 = 6;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x0193:
        r5 = 3;
        r4 = r3 + 1;
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x019a:
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x019e:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 63: goto L_0x01a6;
            default: goto L_0x01a5;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x01a5:
        goto L_0x019e;
    L_0x01a6:
        r5 = 12;
        goto L_0x00ad;
    L_0x01aa:
        r3 = r3 + 1;
        r5 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r8 = 62;
        if (r5 != r8) goto L_0x01b7;
    L_0x01b2:
        r5 = 3;
        r4 = r3 + 1;
        goto L_0x00ad;
    L_0x01b7:
        r5 = 11;
        goto L_0x00ad;
    L_0x01bb:
        r13 = r3 + 1;
        r3 = r19[r13];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r3) {
            case 8: goto L_0x01c4;
            case 9: goto L_0x01c4;
            case 10: goto L_0x01c4;
            case 11: goto L_0x01c4;
            case 12: goto L_0x01c4;
            case 13: goto L_0x01c4;
            case 32: goto L_0x01c4;
            case 38: goto L_0x025d;
            case 47: goto L_0x022d;
            case 62: goto L_0x01d2;
            default: goto L_0x01c2;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x01c2:
        r3 = r13;
        goto L_0x01bb;
    L_0x01c4:
        r5 = 14;
        r3 = r13 - r4;
        r0 = r20;
        r1 = r19;
        r0.a(r1, r4, r3);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r3 = r13;
        goto L_0x00ad;
    L_0x01d2:
        r5 = 3;
        r3 = r13 - r4;
        r0 = r20;
        r1 = r19;
        r0.a(r1, r4, r3);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r0 = r26;
        r1 = r20;
        r3 = a(r0, r1);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r8 = r7.length;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r6 != r8) goto L_0x0575;
    L_0x01e7:
        r8 = r6 << 1;
        r8 = format.epub.common.utils.j.a(r7, r6, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x01ed:
        r7 = r6 + 1;
        r8[r6] = r3;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0208 }
        if (r17 == 0) goto L_0x01fb;
    L_0x01f3:
        if (r9 == 0) goto L_0x01f6;
    L_0x01f5:
        r10 = r9;
    L_0x01f6:
        r0 = r18;
        r0.add(r9);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0208 }
    L_0x01fb:
        r0 = r27;
        r3 = a(r14, r3, r0, r9);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0208 }
        if (r3 == 0) goto L_0x0225;
    L_0x0203:
        r16.close();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0208 }
        goto L_0x007e;
    L_0x0208:
        r3 = move-exception;
        r3 = r12;
        r6 = r8;
        r8 = r10;
        r31 = r7;
        r7 = r9;
        r9 = r4;
        r4 = r5;
        r5 = r31;
    L_0x0213:
        if (r11 <= r9) goto L_0x006a;
    L_0x0215:
        switch(r4) {
            case 1: goto L_0x021a;
            case 3: goto L_0x0536;
            case 20: goto L_0x050a;
            case 21: goto L_0x0515;
            case 22: goto L_0x0515;
            case 23: goto L_0x0520;
            case 24: goto L_0x052b;
            case 25: goto L_0x052b;
            case 26: goto L_0x052b;
            default: goto L_0x0218;
        };
    L_0x0218:
        goto L_0x006a;
    L_0x021a:
        r10 = r11 - r9;
        r0 = r20;
        r1 = r19;
        r0.a(r1, r9, r10);
        goto L_0x006a;
    L_0x0225:
        r9 = 0;
        r4 = r13 + 1;
        r3 = r13;
        r6 = r7;
        r7 = r8;
        goto L_0x00ad;
    L_0x022d:
        r5 = 19;
        r3 = r13 - r4;
        r0 = r20;
        r1 = r19;
        r0.a(r1, r4, r3);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r0 = r26;
        r1 = r20;
        r3 = a(r0, r1);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r0 = r27;
        r3 = a(r14, r3, r0);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r3 == 0) goto L_0x0259;
    L_0x0248:
        r16.close();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        goto L_0x007e;
    L_0x024d:
        r3 = move-exception;
        r3 = r12;
        r8 = r10;
        r31 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r9;
        r9 = r4;
        r4 = r31;
        goto L_0x0213;
    L_0x0259:
        r9 = 0;
        r3 = r13;
        goto L_0x00ad;
    L_0x025d:
        r3 = 1;
        r8 = r13 - r4;
        r0 = r20;
        r1 = r19;
        r0.a(r1, r4, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0549 }
        r5 = 23;
        r4 = r13 + 1;
        r12 = r3;
        r3 = r13;
        goto L_0x00ad;
    L_0x026f:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 8: goto L_0x02d2;
            case 9: goto L_0x02d2;
            case 10: goto L_0x02d2;
            case 11: goto L_0x02d2;
            case 12: goto L_0x02d2;
            case 13: goto L_0x02d2;
            case 32: goto L_0x02d2;
            case 47: goto L_0x02b7;
            case 62: goto L_0x027e;
            default: goto L_0x0276;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x0276:
        r4 = 20;
        r5 = r4;
        r8 = r9;
        r4 = r3;
        r9 = r10;
        goto L_0x00b2;
    L_0x027e:
        r0 = r26;
        r1 = r20;
        r13 = a(r0, r1);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r8 = r7.length;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r6 != r8) goto L_0x0572;
    L_0x0289:
        r8 = r6 << 1;
        r8 = format.epub.common.utils.j.a(r7, r6, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x028f:
        r7 = r6 + 1;
        r8[r6] = r13;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0208 }
        if (r17 == 0) goto L_0x029d;
    L_0x0295:
        if (r9 == 0) goto L_0x0298;
    L_0x0297:
        r10 = r9;
    L_0x0298:
        r0 = r18;
        r0.add(r9);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0208 }
    L_0x029d:
        r0 = r27;
        r6 = a(r14, r13, r0, r9);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0208 }
        if (r6 == 0) goto L_0x02aa;
    L_0x02a5:
        r16.close();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0208 }
        goto L_0x007e;
    L_0x02aa:
        r6 = 0;
        r5 = 3;
        r4 = r3 + 1;
        r9 = r10;
        r31 = r8;
        r8 = r6;
        r6 = r7;
        r7 = r31;
        goto L_0x00b2;
    L_0x02b7:
        r5 = 19;
        r0 = r26;
        r1 = r20;
        r8 = a(r0, r1);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r0 = r27;
        r8 = a(r14, r8, r0);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r8 == 0) goto L_0x02ce;
    L_0x02c9:
        r16.close();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        goto L_0x007e;
    L_0x02ce:
        r8 = 0;
        r9 = r10;
        goto L_0x00b2;
    L_0x02d2:
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x02d6:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 8: goto L_0x02de;
            case 9: goto L_0x02de;
            case 10: goto L_0x02de;
            case 11: goto L_0x02de;
            case 12: goto L_0x02de;
            case 13: goto L_0x02de;
            case 32: goto L_0x02de;
            case 38: goto L_0x02f8;
            case 61: goto L_0x02eb;
            default: goto L_0x02dd;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x02dd:
        goto L_0x02d6;
    L_0x02de:
        r8 = r3 - r4;
        r0 = r22;
        r1 = r19;
        r0.a(r1, r4, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r5 = 17;
        goto L_0x00ad;
    L_0x02eb:
        r8 = r3 - r4;
        r0 = r22;
        r1 = r19;
        r0.a(r1, r4, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r5 = 18;
        goto L_0x00ad;
    L_0x02f8:
        r8 = r3 - r4;
        r0 = r22;
        r1 = r19;
        r0.a(r1, r4, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r12 = 20;
        r5 = 23;
        r4 = r3 + 1;
        goto L_0x00ad;
    L_0x0309:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 61: goto L_0x0311;
            default: goto L_0x0310;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x0310:
        goto L_0x0309;
    L_0x0311:
        r5 = 18;
        goto L_0x00ad;
    L_0x0315:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 34: goto L_0x031d;
            case 39: goto L_0x0323;
            default: goto L_0x031c;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x031c:
        goto L_0x0315;
    L_0x031d:
        r5 = 21;
        r4 = r3 + 1;
        goto L_0x00ad;
    L_0x0323:
        r5 = 22;
        r4 = r3 + 1;
        goto L_0x00ad;
    L_0x0329:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 8: goto L_0x0334;
            case 9: goto L_0x0334;
            case 10: goto L_0x0334;
            case 11: goto L_0x0334;
            case 12: goto L_0x0334;
            case 13: goto L_0x0334;
            case 32: goto L_0x0334;
            case 34: goto L_0x0365;
            case 47: goto L_0x0360;
            case 62: goto L_0x0360;
            default: goto L_0x0330;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x0330:
        r5 = 20;
        goto L_0x00ad;
    L_0x0334:
        r5 = 14;
    L_0x0336:
        r0 = r26;
        r1 = r22;
        r13 = a(r0, r1);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r17 == 0) goto L_0x0373;
    L_0x0340:
        r8 = "xmlns";
        r8 = r13.equals(r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r8 == 0) goto L_0x0373;
    L_0x0349:
        if (r9 != 0) goto L_0x056f;
    L_0x034b:
        r8 = new java.util.HashMap;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r8.<init>(r10);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x0350:
        r9 = "";
        r13 = r23.toString();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x053f }
        r8.put(r9, r13);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x053f }
        r23.a();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x053f }
        r9 = r10;
        goto L_0x00b2;
    L_0x0360:
        r5 = 14;
        r3 = r3 + -1;
        goto L_0x0336;
    L_0x0365:
        if (r3 == 0) goto L_0x00ad;
    L_0x0367:
        r8 = r3 + -1;
        r13 = 1;
        r0 = r23;
        r1 = r19;
        r0.a(r1, r8, r13);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        goto L_0x00ad;
    L_0x0373:
        if (r17 == 0) goto L_0x0397;
    L_0x0375:
        r8 = "xmlns:";
        r8 = r13.startsWith(r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r8 == 0) goto L_0x0397;
    L_0x037e:
        if (r9 != 0) goto L_0x056c;
    L_0x0380:
        r8 = new java.util.HashMap;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r8.<init>(r10);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x0385:
        r9 = 6;
        r9 = r13.substring(r9);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x053f }
        r13 = r23.toString();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x053f }
        r8.put(r9, r13);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x053f }
        r23.a();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x053f }
        r9 = r10;
        goto L_0x00b2;
    L_0x0397:
        if (r24 == 0) goto L_0x03a9;
    L_0x0399:
        r8 = r23.toString();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r0 = r27;
        r0.a(r13, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r23.a();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x03a9:
        r0 = r26;
        r1 = r23;
        r8 = a(r0, r1);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r0 = r27;
        r0.a(r13, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r8 = r9;
        r9 = r10;
        goto L_0x00b2;
    L_0x03ba:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 34: goto L_0x03c2;
            case 38: goto L_0x03cf;
            default: goto L_0x03c1;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x03c1:
        goto L_0x03ba;
    L_0x03c2:
        r8 = r3 - r4;
        r0 = r23;
        r1 = r19;
        r0.a(r1, r4, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r5 = 15;
        goto L_0x00ad;
    L_0x03cf:
        r8 = r3 - r4;
        r0 = r23;
        r1 = r19;
        r0.a(r1, r4, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r12 = 21;
        r5 = 23;
        r4 = r3 + 1;
        goto L_0x00ad;
    L_0x03e0:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 38: goto L_0x03e8;
            case 39: goto L_0x03f9;
            default: goto L_0x03e7;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x03e7:
        goto L_0x03e0;
    L_0x03e8:
        r8 = r3 - r4;
        r0 = r23;
        r1 = r19;
        r0.a(r1, r4, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r12 = 22;
        r5 = 23;
        r4 = r3 + 1;
        goto L_0x00ad;
    L_0x03f9:
        r8 = r3 - r4;
        r0 = r23;
        r1 = r19;
        r0.a(r1, r4, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r5 = 15;
        goto L_0x00ad;
    L_0x0406:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 59: goto L_0x040e;
            default: goto L_0x040d;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x040d:
        goto L_0x0406;
    L_0x040e:
        r8 = r3 - r4;
        r0 = r25;
        r1 = r19;
        r0.a(r1, r4, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r4 = r3 + 1;
        r0 = r26;
        r1 = r25;
        r5 = a(r0, r1);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        r5 = a(r15, r5);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        if (r5 == 0) goto L_0x0569;
    L_0x0427:
        r8 = r5.length;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        if (r8 == 0) goto L_0x0569;
    L_0x042a:
        switch(r12) {
            case 1: goto L_0x044a;
            case 3: goto L_0x0452;
            case 20: goto L_0x0442;
            case 21: goto L_0x0430;
            case 22: goto L_0x0430;
            default: goto L_0x042d;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
    L_0x042d:
        r5 = r12;
        goto L_0x00ad;
    L_0x0430:
        r8 = 0;
        r13 = r5.length;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        r0 = r23;
        r0.a(r5, r8, r13);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        goto L_0x042d;
    L_0x0438:
        r3 = move-exception;
        r3 = r12;
        r5 = r6;
        r8 = r10;
        r6 = r7;
        r7 = r9;
        r9 = r4;
        r4 = r12;
        goto L_0x0213;
    L_0x0442:
        r8 = 0;
        r13 = r5.length;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        r0 = r22;
        r0.a(r5, r8, r13);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        goto L_0x042d;
    L_0x044a:
        r8 = 0;
        r13 = r5.length;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        r0 = r20;
        r0.a(r5, r8, r13);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        goto L_0x042d;
    L_0x0452:
        r8 = 0;
        r13 = r5.length;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        r14.a(r5, r8, r13);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0438 }
        goto L_0x042d;
    L_0x0458:
        r3 = r3 + 1;
        r8 = r19[r3];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r8) {
            case 62: goto L_0x0460;
            default: goto L_0x045f;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x045f:
        goto L_0x0458;
    L_0x0460:
        r5 = 3;
        r4 = r3 + 1;
        goto L_0x00ad;
    L_0x0465:
        r13 = r3 + 1;
        r3 = r19[r13];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        switch(r3) {
            case 62: goto L_0x046e;
            default: goto L_0x046c;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
    L_0x046c:
        r3 = r13;
        goto L_0x0465;
    L_0x046e:
        if (r6 <= 0) goto L_0x04a5;
    L_0x0470:
        if (r17 == 0) goto L_0x0491;
    L_0x0472:
        r3 = r6 + -1;
        r0 = r18;
        r3 = r0.remove(r3);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r3 == 0) goto L_0x0491;
    L_0x047c:
        r3 = r18.size();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r3 = r3 + -1;
        r8 = r3;
    L_0x0483:
        if (r8 < 0) goto L_0x0491;
    L_0x0485:
        r0 = r18;
        r3 = r0.get(r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r3 = (java.util.HashMap) r3;	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r3 == 0) goto L_0x04a0;
    L_0x048f:
        r9 = r3;
        r10 = r3;
    L_0x0491:
        r6 = r6 + -1;
        r3 = r7[r6];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        r3 = a(r14, r3, r9);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        if (r3 == 0) goto L_0x04a4;
    L_0x049b:
        r16.close();	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024d }
        goto L_0x007e;
    L_0x04a0:
        r3 = r8 + -1;
        r8 = r3;
        goto L_0x0483;
    L_0x04a4:
        r9 = 0;
    L_0x04a5:
        r5 = 3;
        r4 = r13 + 1;
        r3 = r13;
        goto L_0x00ad;
    L_0x04ab:
        r8 = 0;
        r31 = r3;
        r3 = r4;
        r4 = r31;
    L_0x04b1:
        r4 = r4 + 1;
        r13 = r19[r4];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0555 }
        switch(r13) {
            case 10: goto L_0x04d0;
            case 13: goto L_0x04d0;
            case 38: goto L_0x04f5;
            case 60: goto L_0x04e3;
            default: goto L_0x04b8;
        };	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0555 }
    L_0x04b8:
        r31 = r8;
        r8 = r3;
        r3 = r31;
    L_0x04bd:
        r28 = 13;
        r0 = r28;
        if (r13 == r0) goto L_0x0562;
    L_0x04c3:
        r28 = 10;
        r0 = r28;
        if (r13 == r0) goto L_0x0562;
    L_0x04c9:
        r3 = 1;
        r31 = r3;
        r3 = r8;
        r8 = r31;
        goto L_0x04b1;
    L_0x04d0:
        if (r8 == 0) goto L_0x04dc;
    L_0x04d2:
        if (r4 <= r3) goto L_0x04db;
    L_0x04d4:
        r8 = r4 - r3;
        r0 = r19;
        r14.b(r0, r3, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0555 }
    L_0x04db:
        r3 = r4;
    L_0x04dc:
        r8 = 0;
        r31 = r8;
        r8 = r3;
        r3 = r31;
        goto L_0x04bd;
    L_0x04e3:
        if (r4 <= r3) goto L_0x04ec;
    L_0x04e5:
        r8 = r4 - r3;
        r0 = r19;
        r14.b(r0, r3, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0555 }
    L_0x04ec:
        r5 = 13;
        r31 = r4;
        r4 = r3;
        r3 = r31;
        goto L_0x00ad;
    L_0x04f5:
        if (r4 <= r3) goto L_0x04fe;
    L_0x04f7:
        r8 = r4 - r3;
        r0 = r19;
        r14.a(r0, r3, r8);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x0555 }
    L_0x04fe:
        r12 = 3;
        r5 = 23;
        r3 = r4 + 1;
        r31 = r4;
        r4 = r3;
        r3 = r31;
        goto L_0x00ad;
    L_0x050a:
        r10 = r11 - r9;
        r0 = r22;
        r1 = r19;
        r0.a(r1, r9, r10);
        goto L_0x006a;
    L_0x0515:
        r10 = r11 - r9;
        r0 = r23;
        r1 = r19;
        r0.a(r1, r9, r10);
        goto L_0x006a;
    L_0x0520:
        r10 = r11 - r9;
        r0 = r25;
        r1 = r19;
        r0.a(r1, r9, r10);
        goto L_0x006a;
    L_0x052b:
        r10 = r11 - r9;
        r0 = r21;
        r1 = r19;
        r0.a(r1, r9, r10);
        goto L_0x006a;
    L_0x0536:
        r10 = r11 - r9;
        r0 = r19;
        r14.a(r0, r9, r10);
        goto L_0x006a;
    L_0x053f:
        r3 = move-exception;
        r9 = r4;
        r3 = r12;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r8;
        r8 = r10;
        goto L_0x0213;
    L_0x0549:
        r8 = move-exception;
        r8 = r10;
        r31 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r9;
        r9 = r4;
        r4 = r31;
        goto L_0x0213;
    L_0x0555:
        r4 = move-exception;
        r4 = r5;
        r8 = r10;
        r5 = r6;
        r6 = r7;
        r7 = r9;
        r9 = r3;
        r3 = r12;
        goto L_0x0213;
    L_0x055f:
        r11 = r10;
        goto L_0x00a0;
    L_0x0562:
        r31 = r3;
        r3 = r8;
        r8 = r31;
        goto L_0x04b1;
    L_0x0569:
        r5 = r12;
        goto L_0x00ad;
    L_0x056c:
        r8 = r9;
        goto L_0x0385;
    L_0x056f:
        r8 = r9;
        goto L_0x0350;
    L_0x0572:
        r8 = r7;
        goto L_0x028f;
    L_0x0575:
        r8 = r7;
        goto L_0x01ed;
        */
        throw new UnsupportedOperationException("Method not decompiled: format.epub.common.core.a.d.b():void");
    }

    private static boolean a(f fVar, String str, c cVar) {
        if (fVar.a(str, cVar) || fVar.c(str)) {
            return true;
        }
        cVar.b();
        return false;
    }

    private static boolean a(f fVar, String str, c cVar, HashMap<String, String> hashMap) {
        if (hashMap != null) {
            fVar.a((Map) hashMap);
        }
        if (fVar.a(str, cVar)) {
            return true;
        }
        cVar.b();
        return false;
    }

    private static boolean a(f fVar, String str, HashMap<String, String> hashMap) {
        boolean c = fVar.c(str);
        if (hashMap != null) {
            fVar.a((Map) hashMap);
        }
        return c;
    }
}
