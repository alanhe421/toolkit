package format.epub.view;

import com.qq.reader.readengine.kernel.a.d;
import format.epub.common.text.model.h;
import java.util.List;

/* compiled from: QEPubFormatter */
public class b {
    v a = null;
    private d b;
    private c c;
    private int d;
    private byte e;
    private float f;
    private boolean g;

    public void a(c cVar) {
        this.c = cVar;
    }

    public void a(d dVar) {
        float f = 0.0f;
        this.b = dVar;
        float c = (float) c();
        float b = (float) b();
        if (!(c == dVar.h() && b == dVar.i())) {
            dVar.a(c);
            dVar.b(b);
            if (dVar.j() != 0) {
                dVar.g().clear();
                if (!dVar.e().d()) {
                    dVar.f().p();
                    dVar.a(2);
                } else if (!dVar.f().d()) {
                    dVar.e().p();
                    dVar.a(3);
                }
            }
        }
        if (dVar.j() != 0 && dVar.j() != 1) {
            dVar.a(false);
            switch (dVar.j()) {
                case 2:
                    a(dVar, dVar.e(), dVar.f());
                    break;
                case 3:
                    z a = a(dVar.f(), 0, b);
                    if (a.f()) {
                        a.l();
                    }
                    dVar.e().a(a);
                    a(dVar, dVar.e(), dVar.f());
                    break;
            }
            List g = dVar.g();
            int size = g.size();
            int i = 0;
            int i2 = 1;
            float f2 = 0.0f;
            while (i < size) {
                boolean z;
                boolean z2;
                r rVar = (r) g.get(i);
                List g2 = rVar.g();
                if (i2 != 0) {
                    a(dVar, rVar.u);
                }
                g2.clear();
                if (rVar.B != (byte) 0) {
                    f = (rVar.n + rVar.r) + f2;
                }
                if (rVar.i && rVar.C != (byte) 0) {
                    i2 = i - 1;
                    while (i2 >= 0) {
                        r rVar2 = (r) g.get(i2);
                        if (rVar2.i && rVar2.B == (byte) 0) {
                            c = f2;
                            if (rVar.E != (byte) 0 && rVar.B == (byte) 0 && f > r0) {
                                c = f;
                            }
                            a(dVar, rVar, c, g2);
                            f2 = (rVar.n + rVar.r) + c;
                            if (rVar.a()) {
                                z = false;
                            } else {
                                z = true;
                            }
                            i++;
                            z2 = z;
                        } else {
                            f2 -= rVar2.r + rVar2.n;
                            i2--;
                        }
                    }
                }
                c = f2;
                c = f;
                a(dVar, rVar, c, g2);
                f2 = (rVar.n + rVar.r) + c;
                if (rVar.a()) {
                    z = true;
                } else {
                    z = false;
                }
                i++;
                z2 = z;
            }
            dVar.m();
            dVar.a(1);
        }
    }

    private void a(d dVar, v vVar) {
        if (vVar instanceof format.epub.view.style.b) {
            format.epub.view.style.b bVar = (format.epub.view.style.b) vVar;
            if (!dVar.g.contains(vVar)) {
                dVar.g.add(bVar);
                dVar.f.add(bVar);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.qq.reader.readengine.kernel.a.d r40, format.epub.view.r r41, float r42, java.util.List<format.epub.view.h> r43) {
        /*
        r39 = this;
        r0 = r41;
        r2 = r0.n;
        r13 = r42 + r2;
        r0 = r41;
        r4 = r0.a;
        r0 = r39;
        r2 = r0.c;
        r0 = r41;
        r3 = r0.u;
        r2.a(r3);
        r0 = r41;
        r0 = r0.s;
        r31 = r0;
        r3 = 0;
        r34 = r41.a();
        r7 = 0;
        r19 = 1;
        r0 = r39;
        r2 = r0.c;
        r2 = r2.a;
        r2 = r2.k();
        r0 = r41;
        r5 = r0.k;
        r2 = r2 + r5;
        r2 = (float) r2;
        r0 = r41;
        r5 = r0.C;
        r6 = 1;
        if (r5 != r6) goto L_0x0628;
    L_0x003a:
        r0 = r41;
        r5 = r0.k;
        r5 = (float) r5;
        r2 = r2 - r5;
        r0 = r41;
        r5 = r0.j;
        r5 = (float) r5;
        r2 = r2 + r5;
        r0 = r41;
        r5 = r0.D;
        r2 = r2 + r5;
        r0 = r39;
        r5 = r0.c;
        r5 = r5.h();
        r5 = r5.q();
        r5 = (float) r5;
        r2 = r2 + r5;
        r0 = r39;
        r5 = r0.c;
        r5 = r5.h();
        r5 = r5.m();
        r5 = (float) r5;
        r2 = r2 + r5;
        r0 = r39;
        r5 = r0.c;
        r5 = r5.h();
        r5 = r5.s();
        r5 = (float) r5;
        r2 = r2 + r5;
        r28 = r2;
    L_0x0077:
        r2 = r39.c();
        r2 = (float) r2;
        r5 = new android.graphics.RectF;
        r5.<init>();
        r0 = r28;
        r5.left = r0;
        r0 = r41;
        r6 = r0.C;
        if (r6 == 0) goto L_0x0624;
    L_0x008b:
        r0 = r41;
        r6 = r0.D;
        r2 = r2 - r6;
        r29 = r2;
    L_0x0092:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.h();
        r2 = r2.k();
        switch(r2) {
            case 0: goto L_0x0213;
            case 1: goto L_0x0213;
            case 2: goto L_0x018e;
            case 3: goto L_0x01ac;
            case 4: goto L_0x01e0;
            default: goto L_0x00a1;
        };
    L_0x00a1:
        r2 = r28;
    L_0x00a3:
        r0 = r41;
        r4 = r0.B;
        r5 = 1;
        if (r4 != r5) goto L_0x0283;
    L_0x00aa:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.h();
        r2 = r2.k();
        switch(r2) {
            case 2: goto L_0x022a;
            case 3: goto L_0x0251;
            default: goto L_0x00b9;
        };
    L_0x00b9:
        r2 = r28;
    L_0x00bb:
        r0 = r41;
        r0 = r0.a;
        r35 = r0;
        r0 = r35;
        r0 = r0.a;
        r36 = r0;
        r0 = r41;
        r0 = r0.g;
        r37 = r0;
        r0 = r41;
        r6 = r0.f;
        r5 = 0;
        r0 = r41;
        r4 = r0.e;
        r4 = r37 - r4;
        r4 = r4 + -1;
        r0 = r41;
        r8 = r0.t;
        r8 = r4 - r8;
        r4 = 0;
        if (r8 <= 0) goto L_0x0620;
    L_0x00e3:
        r0 = r41;
        r9 = r0.w;
        if (r9 == 0) goto L_0x0620;
    L_0x00e9:
        r4 = (float) r8;
        r3 = r3 / r4;
        r30 = r3;
    L_0x00ed:
        r0 = r41;
        r3 = r0.e;
        r4 = r39.c();
        r0 = r39;
        r8 = r0.c;
        r8 = r8.a;
        r8 = r8.k();
        r4 = r4 + r8;
        r0 = (float) r4;
        r32 = r0;
        r33 = r3;
        r10 = r2;
        r3 = r5;
        r2 = r7;
        r7 = r6;
    L_0x0109:
        r0 = r33;
        r1 = r37;
        if (r0 == r1) goto L_0x0574;
    L_0x010f:
        r0 = r35;
        r1 = r33;
        r9 = r0.c(r1);
        r0 = r39;
        r4 = r0.c;
        r16 = r4.a(r9, r7);
        r4 = format.epub.view.g.c;
        if (r9 != r4) goto L_0x0306;
    L_0x0123:
        if (r2 == 0) goto L_0x0604;
    L_0x0125:
        if (r31 <= 0) goto L_0x0604;
    L_0x0127:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.a;
        r2 = r2.n();
        r3 = r10 + r2;
        r3 = r3 + r16;
        r4 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = r3 - r4;
        r3 = (r3 > r32 ? 1 : (r3 == r32 ? 0 : -1));
        if (r3 < 0) goto L_0x02fc;
    L_0x013c:
        r11 = r32;
    L_0x013e:
        r4 = new com.qq.reader.readengine.kernel.g;
        r4.<init>();
        r3 = 0;
        r0 = r36;
        r1 = r33;
        r6 = com.qq.reader.common.utils.ao.a(r0, r1, r3);
        r4.a(r6);
        r3 = new format.epub.view.h;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r0 = r39;
        r8 = r0.c;
        r8 = r8.h();
        r0 = r41;
        r12 = r0.n;
        r12 = r13 - r12;
        r0 = r41;
        r14 = r0.q;
        r14 = (float) r14;
        r12 = r12 + r14;
        r14 = 0;
        r0 = r36;
        r1 = r33;
        r14 = com.qq.reader.common.utils.ao.a(r0, r1, r14);
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);
        r4 = 1;
        r3.a(r4);
        r10 = r10 + r2;
        r2 = 0;
        r4 = r31 + -1;
        r5 = r4;
        r4 = r2;
        r2 = r16;
    L_0x0180:
        r2 = r2 + r10;
        r10 = r2 + r30;
        r2 = r33 + 1;
        r6 = 0;
        r33 = r2;
        r7 = r6;
        r31 = r5;
        r2 = r4;
        goto L_0x0109;
    L_0x018e:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.h();
        r4 = r39.a();
        r2 = r2.e(r4);
        r2 = (float) r2;
        r2 = r29 - r2;
        r0 = r41;
        r4 = r0.m;
        r2 = r2 - r4;
        r2 = r2 + r28;
        r5.right = r2;
        goto L_0x00a3;
    L_0x01ac:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.h();
        r4 = r39.a();
        r2 = r2.e(r4);
        r2 = (float) r2;
        r2 = r29 - r2;
        r0 = r41;
        r4 = r0.m;
        r2 = r2 - r4;
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r4;
        r2 = r2 + r28;
        r0 = r39;
        r4 = r0.c;
        r4 = r4.h();
        r6 = r39.a();
        r4 = r4.e(r6);
        r4 = (float) r4;
        r4 = r29 - r4;
        r5.right = r4;
        goto L_0x00a3;
    L_0x01e0:
        if (r34 != 0) goto L_0x01ee;
    L_0x01e2:
        r0 = r41;
        r2 = r0.g;
        r2 = r4.c(r2);
        r4 = format.epub.view.g.d;
        if (r2 != r4) goto L_0x01f6;
    L_0x01ee:
        r0 = r41;
        r2 = r0.m;
        r2 = (r2 > r29 ? 1 : (r2 == r29 ? 0 : -1));
        if (r2 <= 0) goto L_0x00a1;
    L_0x01f6:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.h();
        r3 = r39.a();
        r2 = r2.e(r3);
        r2 = (float) r2;
        r2 = r29 - r2;
        r0 = r41;
        r3 = r0.m;
        r2 = r2 - r3;
        r3 = r2;
        r2 = r28;
        goto L_0x00a3;
    L_0x0213:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.h();
        r4 = r39.a();
        r2 = r2.e(r4);
        r2 = (float) r2;
        r2 = r29 - r2;
        r5.right = r2;
        goto L_0x00a1;
    L_0x022a:
        r0 = r41;
        r2 = r0.k;
        r2 = (float) r2;
        r2 = r28 - r2;
        r0 = r41;
        r4 = r0.D;
        r2 = r2 + r4;
        r0 = r39;
        r4 = r0.c;
        r4 = r4.h();
        r4 = r4.i();
        r4 = (float) r4;
        r2 = r2 - r4;
        r0 = r41;
        r4 = r0.m;
        r0 = r41;
        r5 = r0.k;
        r5 = (float) r5;
        r4 = r4 - r5;
        r2 = r2 - r4;
        goto L_0x00bb;
    L_0x0251:
        r0 = r41;
        r2 = r0.k;
        r2 = (float) r2;
        r2 = r28 - r2;
        r0 = r41;
        r4 = r0.D;
        r2 = r2 + r4;
        r0 = r41;
        r4 = r0.D;
        r0 = r41;
        r5 = r0.k;
        r5 = (float) r5;
        r4 = r4 - r5;
        r0 = r41;
        r5 = r0.m;
        r0 = r41;
        r6 = r0.k;
        r6 = (float) r6;
        r5 = r5 - r6;
        r4 = r4 - r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r4 = r4 / r5;
        r2 = r2 - r4;
        r0 = r41;
        r4 = r0.m;
        r0 = r41;
        r5 = r0.k;
        r5 = (float) r5;
        r4 = r4 - r5;
        r2 = r2 - r4;
        goto L_0x00bb;
    L_0x0283:
        r0 = r41;
        r4 = r0.B;
        r5 = 2;
        if (r4 != r5) goto L_0x00bb;
    L_0x028a:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.h();
        r2 = r2.k();
        switch(r2) {
            case 2: goto L_0x02b7;
            case 3: goto L_0x02d3;
            default: goto L_0x0299;
        };
    L_0x0299:
        r0 = r41;
        r2 = r0.k;
        r2 = (float) r2;
        r2 = r28 - r2;
        r2 = r2 + r29;
        r0 = r41;
        r4 = r0.D;
        r2 = r2 - r4;
        r0 = r39;
        r4 = r0.c;
        r4 = r4.h();
        r4 = r4.h();
        r4 = (float) r4;
        r2 = r2 + r4;
        goto L_0x00bb;
    L_0x02b7:
        r0 = r41;
        r2 = r0.k;
        r2 = (float) r2;
        r2 = r28 - r2;
        r2 = r2 + r29;
        r0 = r41;
        r4 = r0.l;
        r4 = (float) r4;
        r2 = r2 - r4;
        r0 = r41;
        r4 = r0.m;
        r0 = r41;
        r5 = r0.k;
        r5 = (float) r5;
        r4 = r4 - r5;
        r2 = r2 - r4;
        goto L_0x00bb;
    L_0x02d3:
        r0 = r41;
        r2 = r0.k;
        r2 = (float) r2;
        r2 = r28 - r2;
        r2 = r2 + r29;
        r0 = r41;
        r4 = r0.D;
        r2 = r2 - r4;
        r0 = r41;
        r4 = r0.D;
        r0 = r41;
        r5 = r0.l;
        r5 = (float) r5;
        r4 = r4 - r5;
        r0 = r41;
        r5 = r0.m;
        r0 = r41;
        r6 = r0.k;
        r6 = (float) r6;
        r5 = r5 - r6;
        r4 = r4 - r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r4 = r4 / r5;
        r2 = r2 + r4;
        goto L_0x00bb;
    L_0x02fc:
        r3 = r10 + r2;
        r3 = r3 + r16;
        r4 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r11 = r3 - r4;
        goto L_0x013e;
    L_0x0306:
        r4 = r9 instanceof format.epub.view.y;
        if (r4 != 0) goto L_0x030e;
    L_0x030a:
        r4 = r9 instanceof format.epub.view.p;
        if (r4 == 0) goto L_0x0543;
    L_0x030e:
        r0 = r39;
        r2 = r0.c;
        r8 = r2.c(r9);
        r2 = r9 instanceof format.epub.view.y;
        if (r2 == 0) goto L_0x04e2;
    L_0x031a:
        r2 = r9;
        r2 = (format.epub.view.y) r2;
        r2 = r2.h;
        r6 = r2;
    L_0x0320:
        if (r3 == 0) goto L_0x061d;
    L_0x0322:
        r0 = r41;
        r2 = r0.A;
        if (r2 == 0) goto L_0x032c;
    L_0x0328:
        r2 = 1;
        r3.b(r2);
    L_0x032c:
        r0 = r43;
        r0.add(r3);
        r2 = new format.epub.view.z;
        r2.<init>();
        r0 = r35;
        r2.a(r0);
        r4 = r3.b();
        r5 = r3.c();
        r2.a(r4, r5);
        r4 = r40.l();
        r4 = r4.searchNode(r2);
        if (r4 == 0) goto L_0x0385;
    L_0x0350:
        r2 = r4.b;
        if (r2 == 0) goto L_0x0385;
    L_0x0354:
        r2 = r4.b;
        r2 = r2 instanceof format.epub.view.style.b;
        if (r2 == 0) goto L_0x0385;
    L_0x035a:
        r5 = r3.a;
        r11 = r3.c;
        r12 = r3.b;
        r2 = r3.d;
        r3 = r9 instanceof format.epub.view.y;
        if (r3 == 0) goto L_0x0379;
    L_0x0366:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.f(r9);
        r2 = r11 - r2;
        r0 = r39;
        r3 = r0.c;
        r3 = r3.e(r9);
        r2 = r2 + r3;
    L_0x0379:
        r3 = new android.graphics.RectF;
        r3.<init>(r5, r11, r12, r2);
        r2 = r4.b;
        r2 = (format.epub.view.style.b) r2;
        r2.a(r3);
    L_0x0385:
        r4 = 0;
    L_0x0386:
        r2 = r9 instanceof format.epub.view.p;
        if (r2 == 0) goto L_0x0619;
    L_0x038a:
        r2 = r9;
        r2 = (format.epub.view.p) r2;
        r2 = r2.m;
        switch(r2) {
            case 1: goto L_0x0501;
            case 2: goto L_0x04e6;
            default: goto L_0x0392;
        };
    L_0x0392:
        r2 = r9;
        r2 = (format.epub.view.p) r2;
        r2 = r2.j;
        if (r2 == 0) goto L_0x0505;
    L_0x0399:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.a;
        r2 = r2.i();
        r2 = (float) r2;
        r2 = r2 - r16;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r10 = r2 / r3;
        r22 = r10;
    L_0x03ac:
        r0 = r41;
        r2 = r0.n;
        r2 = r13 - r2;
        r0 = r41;
        r3 = r0.q;
        r3 = (float) r3;
        r3 = r3 + r2;
        r2 = r9 instanceof format.epub.view.p;
        if (r2 == 0) goto L_0x03d5;
    L_0x03bc:
        r2 = r9;
        r2 = (format.epub.view.p) r2;
        r2 = r2.i;
        if (r2 != 0) goto L_0x03d5;
    L_0x03c3:
        r2 = r9;
        r2 = (format.epub.view.p) r2;
        r0 = r39;
        r5 = r0.c;
        r5 = r5.h();
        r5 = r5.o();
        r2.b(r5);
    L_0x03d5:
        r2 = r9 instanceof format.epub.view.p;
        if (r2 == 0) goto L_0x0615;
    L_0x03d9:
        r2 = r9;
        r2 = (format.epub.view.p) r2;
        r2 = r2.j;
        if (r2 == 0) goto L_0x0615;
    L_0x03e0:
        r0 = r39;
        r2 = r0.d;
        r5 = 2;
        if (r2 != r5) goto L_0x0615;
    L_0x03e7:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.a;
        r2 = r2.j();
        r2 = (float) r2;
        r2 = r2 - r8;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r2 / r3;
        r2 = r3 + r8;
        r5 = r3;
        r3 = r2;
    L_0x03fb:
        r2 = r9 instanceof format.epub.view.p;
        if (r2 == 0) goto L_0x060f;
    L_0x03ff:
        r2 = r9;
        r2 = (format.epub.view.p) r2;
        r2 = r2.d();
        if (r2 == 0) goto L_0x060f;
    L_0x0408:
        r5 = 0;
        r3 = r5 + r8;
        r25 = r3;
        r24 = r5;
    L_0x040f:
        r2 = r22 + r16;
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r2 = r2 - r3;
        r2 = (r2 > r32 ? 1 : (r2 == r32 ? 0 : -1));
        if (r2 < 0) goto L_0x052d;
    L_0x0418:
        r23 = r32;
    L_0x041a:
        r2 = r9 instanceof format.epub.view.y;
        if (r2 == 0) goto L_0x060b;
    L_0x041e:
        r2 = r9;
        r2 = (format.epub.view.y) r2;
        r2 = r2.d();
        if (r2 == 0) goto L_0x060b;
    L_0x0427:
        r2 = r9;
        r2 = (format.epub.view.y) r2;
        r2 = r2.c();
        r3 = com.qq.reader.readengine.a.c.b(r2);
        if (r3 == 0) goto L_0x0535;
    L_0x0434:
        r2 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r2 = r16 / r2;
        r3 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r16 = r2 * r3;
        r3 = r16;
    L_0x043e:
        r16 = new com.qq.reader.readengine.kernel.g;
        r16.<init>();
        r2 = 0;
        r0 = r36;
        r1 = r33;
        r10 = com.qq.reader.common.utils.ao.a(r0, r1, r2);
        r0 = r16;
        r0.a(r10);
        r15 = new format.epub.view.h;
        r17 = r6 - r7;
        r18 = 0;
        r0 = r39;
        r2 = r0.c;
        r20 = r2.h();
        r0 = r36;
        r1 = r33;
        r26 = com.qq.reader.common.utils.ao.a(r0, r1, r7);
        r21 = r9;
        r15.<init>(r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26);
        r2 = 1;
        r15.a(r2);
        r0 = r41;
        r2 = r0.A;
        if (r2 == 0) goto L_0x047a;
    L_0x0476:
        r2 = 1;
        r15.b(r2);
    L_0x047a:
        r0 = r43;
        r0.add(r15);
        r2 = new format.epub.view.z;
        r2.<init>();
        r0 = r35;
        r2.a(r0);
        r5 = r15.b();
        r6 = r15.c();
        r2.a(r5, r6);
        r5 = r40.l();
        r5 = r5.searchNode(r2);
        if (r5 == 0) goto L_0x04d3;
    L_0x049e:
        r2 = r5.b;
        if (r2 == 0) goto L_0x04d3;
    L_0x04a2:
        r2 = r5.b;
        r2 = r2 instanceof format.epub.view.style.b;
        if (r2 == 0) goto L_0x04d3;
    L_0x04a8:
        r6 = r15.a;
        r7 = r15.c;
        r8 = r15.b;
        r2 = r15.d;
        r10 = r9 instanceof format.epub.view.y;
        if (r10 == 0) goto L_0x04c7;
    L_0x04b4:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.f(r9);
        r2 = r7 - r2;
        r0 = r39;
        r10 = r0.c;
        r9 = r10.e(r9);
        r2 = r2 + r9;
    L_0x04c7:
        r9 = new android.graphics.RectF;
        r9.<init>(r6, r7, r8, r2);
        r2 = r5.b;
        r2 = (format.epub.view.style.b) r2;
        r2.a(r9);
    L_0x04d3:
        r19 = 0;
        r2 = 1;
        r10 = r22;
        r5 = r31;
        r38 = r3;
        r3 = r4;
        r4 = r2;
        r2 = r38;
        goto L_0x0180;
    L_0x04e2:
        r2 = 0;
        r6 = r2;
        goto L_0x0320;
    L_0x04e6:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.h();
        r3 = r39.a();
        r2 = r2.e(r3);
        r2 = (float) r2;
        r2 = r29 - r2;
        r0 = r41;
        r3 = r0.m;
        r10 = r2 - r3;
        goto L_0x0392;
    L_0x0501:
        r10 = r28;
        goto L_0x0392;
    L_0x0505:
        r2 = r9;
        r2 = (format.epub.view.p) r2;
        r2 = r2.b();
        if (r2 == 0) goto L_0x0513;
    L_0x050e:
        r10 = 0;
        r22 = r10;
        goto L_0x03ac;
    L_0x0513:
        r2 = r9;
        r2 = (format.epub.view.p) r2;
        r2 = r2.c();
        if (r2 == 0) goto L_0x0619;
    L_0x051c:
        r0 = r39;
        r2 = r0.c;
        r2 = r2.a;
        r2 = r2.i();
        r2 = (float) r2;
        r10 = r2 - r16;
        r22 = r10;
        goto L_0x03ac;
    L_0x052d:
        r2 = r22 + r16;
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r23 = r2 - r3;
        goto L_0x041a;
    L_0x0535:
        r2 = com.qq.reader.readengine.a.c.c(r2);
        if (r2 == 0) goto L_0x060b;
    L_0x053b:
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r16 = r16 / r2;
        r3 = r16;
        goto L_0x043e;
    L_0x0543:
        r0 = r39;
        r4 = r0.c;
        r4 = r4.a(r9);
        if (r4 == 0) goto L_0x0604;
    L_0x054d:
        r0 = r39;
        r4 = r0.c;
        r4.b(r9);
        r4 = r9 instanceof format.epub.view.x;
        if (r4 == 0) goto L_0x056b;
    L_0x0558:
        r0 = r39;
        r4 = r0.c;
        r4 = r4.h();
        r5 = r4 instanceof format.epub.view.style.b;
        if (r5 == 0) goto L_0x056b;
    L_0x0564:
        r0 = r39;
        r1 = r40;
        r0.a(r1, r4);
    L_0x056b:
        r19 = 1;
        r4 = r2;
        r5 = r31;
        r2 = r16;
        goto L_0x0180;
    L_0x0574:
        if (r34 != 0) goto L_0x05e8;
    L_0x0576:
        r0 = r41;
        r5 = r0.h;
        if (r5 <= 0) goto L_0x05e8;
    L_0x057c:
        r0 = r41;
        r2 = r0.g;
        r0 = r35;
        r9 = r0.c(r2);
        r9 = (format.epub.view.y) r9;
        r3 = r9.a;
        r4 = r9.b;
        r4 = r4 + r5;
        r4 = r4 + -1;
        r3 = r3[r4];
        r4 = 45;
        if (r3 == r4) goto L_0x0602;
    L_0x0595:
        r6 = 1;
    L_0x0596:
        r0 = r39;
        r3 = r0.c;
        r4 = 0;
        r7 = r3.a(r9, r4, r5, r6);
        r0 = r41;
        r12 = r0.n;
        r4 = new com.qq.reader.readengine.kernel.g;
        r4.<init>();
        r3 = 0;
        r0 = r36;
        r14 = com.qq.reader.common.utils.ao.a(r0, r2, r3);
        r4.a(r14);
        r3 = new format.epub.view.h;
        r0 = r39;
        r8 = r0.c;
        r8 = r8.h();
        r7 = r7 + r10;
        r11 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r11 = r7 - r11;
        r7 = r13 - r12;
        r0 = r41;
        r12 = r0.v;
        r12 = r7 - r12;
        r7 = 0;
        r0 = r36;
        r14 = com.qq.reader.common.utils.ao.a(r0, r2, r7);
        r7 = r19;
        r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);
        r2 = 1;
        r3.a(r2);
        r0 = r41;
        r2 = r0.A;
        if (r2 == 0) goto L_0x05e3;
    L_0x05df:
        r2 = 1;
        r3.b(r2);
    L_0x05e3:
        r0 = r43;
        r0.add(r3);
    L_0x05e8:
        if (r43 == 0) goto L_0x0601;
    L_0x05ea:
        r2 = r43.size();
        if (r2 <= 0) goto L_0x0601;
    L_0x05f0:
        r2 = r43.size();
        r2 = r2 + -1;
        r0 = r43;
        r2 = r0.get(r2);
        r2 = (format.epub.view.h) r2;
        r3 = 1;
        r2.p = r3;
    L_0x0601:
        return;
    L_0x0602:
        r6 = 0;
        goto L_0x0596;
    L_0x0604:
        r4 = r2;
        r5 = r31;
        r2 = r16;
        goto L_0x0180;
    L_0x060b:
        r3 = r16;
        goto L_0x043e;
    L_0x060f:
        r25 = r3;
        r24 = r5;
        goto L_0x040f;
    L_0x0615:
        r5 = r3;
        r3 = r13;
        goto L_0x03fb;
    L_0x0619:
        r22 = r10;
        goto L_0x03ac;
    L_0x061d:
        r4 = r3;
        goto L_0x0386;
    L_0x0620:
        r30 = r4;
        goto L_0x00ed;
    L_0x0624:
        r29 = r2;
        goto L_0x0092;
    L_0x0628:
        r28 = r2;
        goto L_0x0077;
        */
        throw new UnsupportedOperationException("Method not decompiled: format.epub.view.b.a(com.qq.reader.readengine.kernel.a.d, format.epub.view.r, float, java.util.List):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.qq.reader.readengine.kernel.a.d r20, format.epub.view.z r21, format.epub.view.z r22) {
        /*
        r19 = this;
        r0 = r22;
        r1 = r21;
        r0.a(r1);
        r2 = r19.b();
        r12 = (float) r2;
        r2 = r20.g();
        r2.clear();
        r11 = 0;
        r9 = 0;
        r10 = 0;
        r5 = 0;
        r8 = 0;
    L_0x0018:
        r0 = r19;
        r2 = r0.c;
        r2.g();
        r4 = r22.j();
        r14 = r22.b();
        r0 = r19;
        r2 = r0.c;
        r6 = r2.h();
        r2 = 0;
        r13 = r2;
    L_0x0031:
        if (r13 >= r14) goto L_0x01fd;
    L_0x0033:
        r3 = r4.c(r13);
        r2 = format.epub.view.g.g;
        if (r3 != r2) goto L_0x0078;
    L_0x003b:
        r3 = r6.a;
        r0 = r20;
        r2 = r0.e;
        r2 = r2.size();
        r2 = r2 + -1;
        r6 = r2;
    L_0x0048:
        if (r6 < 0) goto L_0x006f;
    L_0x004a:
        r0 = r20;
        r2 = r0.e;
        r2 = r2.get(r6);
        r2 = (format.epub.view.e) r2;
    L_0x0054:
        r7 = r2.f;
        if (r7 == 0) goto L_0x005b;
    L_0x0058:
        r2 = r2.f;
        goto L_0x0054;
    L_0x005b:
        r7 = r2.c();
        if (r7 != 0) goto L_0x0074;
    L_0x0061:
        r2.b();
        r6 = r2.d;
        r6.a(r4);
        r2 = r2.d;
        r6 = 0;
        r2.a(r13, r6);
    L_0x006f:
        r2 = r13 + 1;
        r13 = r2;
        r6 = r3;
        goto L_0x0031;
    L_0x0074:
        r2 = r6 + -1;
        r6 = r2;
        goto L_0x0048;
    L_0x0078:
        r2 = r3 instanceof format.epub.view.x;
        if (r2 == 0) goto L_0x01aa;
    L_0x007c:
        r2 = r3;
        r2 = (format.epub.view.x) r2;
        r2 = r2.a;
        r2 = r2 instanceof format.epub.common.text.model.a.a;
        if (r2 == 0) goto L_0x01aa;
    L_0x0085:
        r15 = new format.epub.view.e;
        r15.<init>();
        r2 = r15.c;
        r2.a(r4);
        r2 = r15.c;
        r7 = 0;
        r2.a(r13, r7);
        r3 = (format.epub.view.x) r3;
        r0 = r3.a;
        r16 = r0;
        r3 = new format.epub.view.style.c;
        r0 = r16;
        r3.<init>(r6, r0);
        r2 = r3;
        r2 = (format.epub.view.style.c) r2;
        r6 = r19.a();
        r2.p(r6);
        r0 = r19;
        r2 = r0.c;
        r2 = r2.e();
        r2 = r2.k();
        r6 = r19.a();
        r6 = r3.b(r6);
        r2 = r2 + r6;
        r2 = (float) r2;
        r3.a(r2);
        r0 = r19;
        r2 = r0.c;
        r2 = r2.e();
        r2 = r2.i();
        r0 = r19;
        r6 = r0.c;
        r6 = r6.e();
        r6 = r6.l();
        r2 = r2 - r6;
        r6 = r19.a();
        r6 = r3.c(r6);
        r2 = r2 - r6;
        r2 = (float) r2;
        r3.b(r2);
        r15.b = r3;
        r0 = r16;
        r15.a = r0;
        r0 = r20;
        r2 = r0.e;
        r2 = r2.size();
        if (r2 != 0) goto L_0x0104;
    L_0x00fb:
        r0 = r20;
        r2 = r0.e;
        r2.add(r15);
        goto L_0x006f;
    L_0x0104:
        r6 = 1;
        r2 = r2 + -1;
        r7 = r2;
    L_0x0108:
        if (r7 < 0) goto L_0x0484;
    L_0x010a:
        r0 = r20;
        r2 = r0.e;
        r2 = r2.get(r7);
        r2 = (format.epub.view.e) r2;
        r0 = r16;
        r0 = r0.a;
        r17 = r0;
        r18 = r2.a();
        r0 = r18;
        r0 = r0.a;
        r18 = r0;
        r0 = r17;
        r1 = r18;
        if (r0 != r1) goto L_0x0173;
    L_0x012a:
        r6 = r2.f;
        if (r6 == 0) goto L_0x0131;
    L_0x012e:
        r2 = r2.f;
        goto L_0x012a;
    L_0x0131:
        r6 = r2.c();
        if (r6 == 0) goto L_0x016c;
    L_0x0137:
        r6 = r2.g;
        r2.f = r15;
        r15.e = r2;
        if (r6 == 0) goto L_0x015e;
    L_0x013f:
        if (r7 <= 0) goto L_0x015e;
    L_0x0141:
        r0 = r20;
        r2 = r0.e;
        r6 = r7 + -1;
        r2 = r2.get(r6);
        r2 = (format.epub.view.e) r2;
    L_0x014d:
        r6 = r2.f;
        if (r6 == 0) goto L_0x0154;
    L_0x0151:
        r2 = r2.f;
        goto L_0x014d;
    L_0x0154:
        if (r2 == 0) goto L_0x015e;
    L_0x0156:
        r6 = r2.c();
        if (r6 != 0) goto L_0x015e;
    L_0x015c:
        r15.g = r2;
    L_0x015e:
        r2 = 0;
    L_0x015f:
        if (r2 == 0) goto L_0x006f;
    L_0x0161:
        if (r7 < 0) goto L_0x006f;
    L_0x0163:
        r0 = r20;
        r2 = r0.e;
        r2.add(r7, r15);
        goto L_0x006f;
    L_0x016c:
        r2.b = r3;
        r0 = r16;
        r2.a = r0;
        goto L_0x015e;
    L_0x0173:
        r0 = r16;
        r0 = r0.a;
        r17 = r0;
        r18 = r2.a();
        r0 = r18;
        r0 = r0.a;
        r18 = r0;
        r0 = r17;
        r1 = r18;
        if (r0 <= r1) goto L_0x01a5;
    L_0x0189:
        r6 = r2.f;
        if (r6 == 0) goto L_0x0190;
    L_0x018d:
        r2 = r2.f;
        goto L_0x0189;
    L_0x0190:
        if (r2 == 0) goto L_0x019a;
    L_0x0192:
        r6 = r2.c();
        if (r6 != 0) goto L_0x019a;
    L_0x0198:
        r15.g = r2;
    L_0x019a:
        r0 = r20;
        r2 = r0.e;
        r6 = r7 + 1;
        r2.add(r6, r15);
        r2 = 0;
        goto L_0x015f;
    L_0x01a5:
        r2 = r7 + -1;
        r7 = r2;
        goto L_0x0108;
    L_0x01aa:
        r2 = r3 instanceof format.epub.view.f;
        if (r2 == 0) goto L_0x0481;
    L_0x01ae:
        r3 = (format.epub.view.f) r3;
        r2 = r3.b;
        if (r2 == 0) goto L_0x01f9;
    L_0x01b4:
        r2 = format.epub.view.style.f.a();
        r7 = r3.a;
        r7 = r2.a(r7);
        r2 = com.qq.reader.appconfig.a.d.B;
        r15 = com.qq.reader.common.utils.ao.b;
        r2 = r2.equals(r15);
        if (r2 == 0) goto L_0x01ee;
    L_0x01c8:
        r2 = r7.b;
        r15 = format.epub.view.style.f.a();
        r15 = r15.c();
        r15 = r15.n;
        r15 = r15.c();
        r2.c(r15);
    L_0x01db:
        r2 = r3 instanceof format.epub.view.n;
        if (r2 == 0) goto L_0x01f6;
    L_0x01df:
        r3 = (format.epub.view.n) r3;
        r2 = r3.h;
        r3 = r2;
    L_0x01e4:
        if (r7 == 0) goto L_0x047e;
    L_0x01e6:
        r2 = new format.epub.view.style.d;
        r2.<init>(r6, r7, r3);
    L_0x01eb:
        r3 = r2;
        goto L_0x006f;
    L_0x01ee:
        r2 = r7.b;
        r15 = com.qq.reader.common.utils.ao.b;
        r2.c(r15);
        goto L_0x01db;
    L_0x01f6:
        r2 = 0;
        r3 = r2;
        goto L_0x01e4;
    L_0x01f9:
        r3 = r6.a;
        goto L_0x006f;
    L_0x01fd:
        r0 = r19;
        r2 = r0.c;
        r3 = 0;
        r2.a(r4, r3, r14);
        r2 = 0;
        r3 = r2;
    L_0x0207:
        if (r3 >= r14) goto L_0x0241;
    L_0x0209:
        r2 = r4.c(r3);
        r2 = r2 instanceof format.epub.view.x;
        if (r2 == 0) goto L_0x023d;
    L_0x0211:
        r2 = r4.c(r3);
        r2 = (format.epub.view.x) r2;
        r2 = r2.a;
        r2 = r2 instanceof format.epub.common.text.model.a.a;
        if (r2 == 0) goto L_0x023d;
    L_0x021d:
        r2 = r4.c(r3);
        r2 = (format.epub.view.x) r2;
        r2 = r2.a;
        r6 = r2.e();
        if (r6 == 0) goto L_0x023d;
    L_0x022b:
        r6 = r2.e();
        r0 = r19;
        r7 = r0.e;
        if (r6 == r7) goto L_0x023d;
    L_0x0235:
        r2 = r2.e();
        r0 = r19;
        r0.e = r2;
    L_0x023d:
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x0207;
    L_0x0241:
        r0 = r19;
        r2 = r0.c;
        r2.a();
        r6 = new format.epub.view.r;
        r2 = r22.c();
        r0 = r19;
        r3 = r0.c;
        r3 = r3.h();
        r6.<init>(r4, r14, r2, r3);
        r7 = r6.b;
        r13 = r11;
        r14 = r12;
        r11 = r5;
        r12 = r9;
    L_0x025f:
        r2 = r6.g;
        if (r2 == r7) goto L_0x0499;
    L_0x0263:
        r2 = r6.g;
        if (r2 != 0) goto L_0x02c1;
    L_0x0267:
        r2 = r6.h;
        if (r2 != 0) goto L_0x02c1;
    L_0x026b:
        r2 = 1;
        r15 = r2;
    L_0x026d:
        r5 = r6.g;
        r6 = r6.h;
        r9 = 1;
        r2 = r19;
        r3 = r20;
        r6 = r2.a(r3, r4, r5, r6, r7, r8, r9, r10);
        r2 = r6.B;
        if (r2 == 0) goto L_0x03b5;
    L_0x027e:
        r0 = r19;
        r2 = r0.e;
        if (r2 == 0) goto L_0x0292;
    L_0x0284:
        r2 = r6.z;
        if (r2 != 0) goto L_0x0292;
    L_0x0288:
        r2 = 0;
        r0 = r19;
        r0.e = r2;
        r2 = 0;
        r0 = r19;
        r0.f = r2;
    L_0x0292:
        r3 = r6.D;
        r2 = r20.g();
        r2 = r2.size();
        r2 = r2 + -1;
        r5 = r2;
    L_0x029f:
        if (r5 < 0) goto L_0x02cc;
    L_0x02a1:
        r2 = r20.g();
        r2 = r2.get(r5);
        r2 = (format.epub.view.r) r2;
        r9 = r2.i;
        if (r9 == 0) goto L_0x02ca;
    L_0x02af:
        r9 = r2.B;
        if (r9 == 0) goto L_0x02cc;
    L_0x02b3:
        r9 = r2.D;
        r9 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1));
        if (r9 <= 0) goto L_0x02c4;
    L_0x02b9:
        r2.D = r3;
        r2 = r3;
    L_0x02bc:
        r3 = r5 + -1;
        r5 = r3;
        r3 = r2;
        goto L_0x029f;
    L_0x02c1:
        r2 = 0;
        r15 = r2;
        goto L_0x026d;
    L_0x02c4:
        r3 = r2.D;
        r2 = r2.D;
        r6.D = r2;
    L_0x02ca:
        r2 = r3;
        goto L_0x02bc;
    L_0x02cc:
        r2 = 0;
        r10 = r3 + r2;
        r9 = r6.B;
        r2 = r6.n;
        r3 = r6.r;
        r2 = r2 + r3;
        r11 = r12 + r2;
        r0 = r19;
        r2 = r0.c;
        r2 = r2.a;
        r2 = r2.i();
        r2 = (float) r2;
        r2 = r10 / r2;
        r2 = (double) r2;
        r16 = 4605380978949069210; // 0x3fe999999999999a float:-1.5881868E-23 double:0.8;
        r2 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1));
        if (r2 <= 0) goto L_0x02f5;
    L_0x02ef:
        r2 = 1;
        r0 = r19;
        r0.g = r2;
        r14 = r14 - r11;
    L_0x02f5:
        r0 = r19;
        r2 = r0.g;
        if (r2 == 0) goto L_0x02fd;
    L_0x02fb:
        r11 = 0;
        r10 = 0;
    L_0x02fd:
        r2 = r14 - r11;
        r3 = 0;
        r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r2 >= 0) goto L_0x035b;
    L_0x0304:
        r2 = 0;
        r0 = r19;
        r0.g = r2;
    L_0x0309:
        r2 = r22.f();
        if (r2 == 0) goto L_0x0324;
    L_0x030f:
        r2 = r22.l();
        if (r2 == 0) goto L_0x0324;
    L_0x0315:
        r2 = r22.j();
        r2 = r2.e();
        if (r2 != 0) goto L_0x0324;
    L_0x031f:
        r2 = 0;
        r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x0487;
    L_0x0324:
        r2 = r22.j();
        r2 = r2.e();
        if (r2 == 0) goto L_0x0334;
    L_0x032e:
        r2 = r22.l();
        if (r2 != 0) goto L_0x0324;
    L_0x0334:
        r0 = r19;
        r2 = r0.c;
        r2.g();
        r2 = r20.c();
        if (r2 == 0) goto L_0x035a;
    L_0x0341:
        r2 = r20.g();
        r2 = r2.size();
        if (r2 <= 0) goto L_0x035a;
    L_0x034b:
        r3 = r20.g();
        r2 = r2 + -1;
        r2 = r3.get(r2);
        r2 = (format.epub.view.r) r2;
        r3 = 1;
        r2.J = r3;
    L_0x035a:
        return;
    L_0x035b:
        r5 = r14;
    L_0x035c:
        if (r8 == 0) goto L_0x038c;
    L_0x035e:
        r2 = r8.B;
        if (r2 == 0) goto L_0x038c;
    L_0x0362:
        if (r15 == 0) goto L_0x038c;
    L_0x0364:
        r2 = r6.B;
        if (r2 != 0) goto L_0x038c;
    L_0x0368:
        r3 = r6.u;
    L_0x036a:
        r2 = r3 instanceof format.epub.view.style.c;
        if (r2 == 0) goto L_0x0402;
    L_0x036e:
        r2 = r3;
        r2 = (format.epub.view.style.c) r2;
        r2 = r2.J();
        if (r2 == 0) goto L_0x0402;
    L_0x0377:
        r3 = (format.epub.view.style.b) r3;
        r2 = r19.a();
        r2 = r3.n(r2);
        r3 = r6.q;
        r3 = r3 + r2;
        r6.q = r3;
        r3 = r6.n;
        r2 = (float) r2;
        r2 = r2 + r3;
        r6.n = r2;
    L_0x038c:
        r2 = 0;
        r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x0448;
    L_0x0391:
        if (r13 <= 0) goto L_0x0448;
    L_0x0393:
        r2 = r20.g();
        r12 = r2.size();
        r2 = 0;
        r4 = r2;
        r3 = r12;
    L_0x039e:
        if (r4 >= r12) goto L_0x040b;
    L_0x03a0:
        r2 = r20.g();
        r2 = r2.get(r4);
        r2 = (format.epub.view.r) r2;
        r2 = r2.i;
        if (r2 != 0) goto L_0x04a9;
    L_0x03ae:
        r2 = r3 + -1;
    L_0x03b0:
        r3 = r4 + 1;
        r4 = r3;
        r3 = r2;
        goto L_0x039e;
    L_0x03b5:
        r0 = r19;
        r2 = r0.g;
        if (r2 == 0) goto L_0x03c0;
    L_0x03bb:
        r2 = 0;
        r0 = r19;
        r0.g = r2;
    L_0x03c0:
        r2 = r6.n;
        r3 = r14 - r2;
        r2 = 0;
        r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x03cb;
    L_0x03c9:
        r6.C = r11;
    L_0x03cb:
        r2 = r6.d();
        if (r2 == 0) goto L_0x03db;
    L_0x03d1:
        r5 = r6.n;
        r2 = r6.z;
        if (r2 == 0) goto L_0x0400;
    L_0x03d7:
        r2 = r6.r;
    L_0x03d9:
        r2 = r2 + r5;
        r12 = r12 - r2;
    L_0x03db:
        r2 = r6.E;
        if (r2 == 0) goto L_0x0495;
    L_0x03df:
        r2 = r6.E;
        r5 = 3;
        if (r2 == r5) goto L_0x03e8;
    L_0x03e4:
        r2 = r6.E;
        if (r2 != r11) goto L_0x0495;
    L_0x03e8:
        r2 = 0;
        r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0492;
    L_0x03ed:
        r2 = r3 - r12;
    L_0x03ef:
        r12 = 0;
        r10 = 0;
        r3 = 0;
        r6.C = r3;
        r5 = r2;
        r2 = r12;
    L_0x03f6:
        r3 = 0;
        r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r3 > 0) goto L_0x048e;
    L_0x03fb:
        r11 = 0;
        r10 = 0;
        r9 = 0;
        goto L_0x035c;
    L_0x0400:
        r2 = 0;
        goto L_0x03d9;
    L_0x0402:
        r2 = r3.a;
        if (r3 == r2) goto L_0x038c;
    L_0x0406:
        if (r2 == 0) goto L_0x038c;
    L_0x0408:
        r3 = r2;
        goto L_0x036a;
    L_0x040b:
        r2 = 0;
        r4 = 1;
        if (r3 <= r4) goto L_0x0412;
    L_0x040f:
        r4 = r6.n;
        r4 = r4 + r5;
    L_0x0412:
        r0 = r19;
        r4 = r0.d;
        r7 = 1;
        if (r4 != r7) goto L_0x04a6;
    L_0x0419:
        r2 = 0;
        r8 = r2;
    L_0x041b:
        r2 = 0;
        r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x04a3;
    L_0x0420:
        r4 = 0;
        r2 = 0;
        r7 = r2;
    L_0x0423:
        if (r7 >= r12) goto L_0x0445;
    L_0x0425:
        r2 = r20.g();
        r2 = r2.get(r7);
        r2 = (format.epub.view.r) r2;
        r14 = r2.i;
        if (r14 == 0) goto L_0x04a1;
    L_0x0433:
        r14 = r3 + -1;
        if (r4 >= r14) goto L_0x04a1;
    L_0x0437:
        r2.v = r8;
        r14 = r2.n;
        r14 = r14 + r8;
        r2.n = r14;
        r2 = r4 + 1;
    L_0x0440:
        r4 = r7 + 1;
        r7 = r4;
        r4 = r2;
        goto L_0x0423;
    L_0x0445:
        r14 = r5;
        goto L_0x0309;
    L_0x0448:
        r2 = r6.B;
        if (r2 != 0) goto L_0x049f;
    L_0x044c:
        r2 = r6.r;
        r2 = r5 - r2;
    L_0x0450:
        r3 = r6.g;
        r5 = r6.h;
        r0 = r22;
        r0.a(r3, r5);
        r3 = r6.H;
        r0 = r22;
        r3.a(r0);
        r3 = r20.g();
        r3.add(r6);
        r3 = r6.y;
        if (r3 == 0) goto L_0x049d;
    L_0x046b:
        r3 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
    L_0x046d:
        r2 = 0;
        r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x0475;
    L_0x0472:
        r14 = r3;
        goto L_0x0309;
    L_0x0475:
        r2 = r13 + 1;
        r8 = r6;
        r12 = r11;
        r13 = r2;
        r14 = r3;
        r11 = r9;
        goto L_0x025f;
    L_0x047e:
        r2 = r6;
        goto L_0x01eb;
    L_0x0481:
        r3 = r6;
        goto L_0x006f;
    L_0x0484:
        r2 = r6;
        goto L_0x015f;
    L_0x0487:
        r8 = r6;
        r5 = r9;
        r12 = r14;
        r9 = r11;
        r11 = r13;
        goto L_0x0018;
    L_0x048e:
        r9 = r11;
        r11 = r2;
        goto L_0x035c;
    L_0x0492:
        r2 = r3;
        goto L_0x03ef;
    L_0x0495:
        r2 = r12;
        r5 = r3;
        goto L_0x03f6;
    L_0x0499:
        r9 = r11;
        r11 = r12;
        goto L_0x0309;
    L_0x049d:
        r3 = r2;
        goto L_0x046d;
    L_0x049f:
        r2 = r5;
        goto L_0x0450;
    L_0x04a1:
        r2 = r4;
        goto L_0x0440;
    L_0x04a3:
        r14 = r5;
        goto L_0x0309;
    L_0x04a6:
        r8 = r2;
        goto L_0x041b;
    L_0x04a9:
        r2 = r3;
        goto L_0x03b0;
        */
        throw new UnsupportedOperationException("Method not decompiled: format.epub.view.b.a(com.qq.reader.readengine.kernel.a.d, format.epub.view.z, format.epub.view.z):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private format.epub.view.r a(com.qq.reader.readengine.kernel.a.d r36, format.epub.view.s r37, int r38, int r39, int r40, format.epub.view.r r41, boolean r42, float r43) {
        /*
        r35 = this;
        r9 = 0;
        r7 = 0;
        r0 = r35;
        r4 = r0.c;
        r31 = r4.e();
        r27 = new format.epub.view.r;
        r0 = r35;
        r4 = r0.c;
        r4 = r4.h();
        r0 = r27;
        r1 = r37;
        r2 = r38;
        r3 = r39;
        r0.<init>(r1, r2, r3, r4);
        if (r38 != 0) goto L_0x0109;
    L_0x0021:
        if (r39 != 0) goto L_0x0109;
    L_0x0023:
        r4 = 1;
        r8 = r4;
    L_0x0025:
        r0 = r35;
        r4 = r0.c;
        r6 = r4.h();
        r4 = 0;
        if (r42 == 0) goto L_0x1085;
    L_0x0030:
        r4 = r36.e();
        r5 = r27.e();
        r4 = r4.b(r5);
        if (r4 != 0) goto L_0x010d;
    L_0x003e:
        r4 = 1;
    L_0x003f:
        r5 = 0;
        r5 = (r43 > r5 ? 1 : (r43 == r5 ? 0 : -1));
        if (r5 <= 0) goto L_0x1085;
    L_0x0044:
        if (r41 == 0) goto L_0x1085;
    L_0x0046:
        if (r4 != 0) goto L_0x1085;
    L_0x0048:
        r4 = r36.e();
        r5 = r41.e();
        r4 = r4.b(r5);
        if (r4 != 0) goto L_0x0110;
    L_0x0056:
        r4 = 1;
    L_0x0057:
        r30 = r4;
    L_0x0059:
        r4 = 0;
        if (r8 == 0) goto L_0x1079;
    L_0x005c:
        r5 = r37.c(r38);
        r10 = r7;
        r11 = r9;
        r7 = r4;
        r9 = r38;
    L_0x0065:
        r0 = r35;
        r4 = r0.c;
        r4 = r4.a(r5);
        if (r4 != 0) goto L_0x009a;
    L_0x006f:
        r4 = format.epub.view.g.c;
        if (r5 == r4) goto L_0x009a;
    L_0x0073:
        r4 = r5 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x0141;
    L_0x0077:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r4 = r4.c();
        r12 = 58853; // 0xe5e5 float:8.247E-41 double:2.9077E-319;
        if (r4 == r12) goto L_0x009a;
    L_0x0083:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r4 = r4.c();
        r12 = 58865; // 0xe5f1 float:8.2487E-41 double:2.9083E-319;
        if (r4 == r12) goto L_0x009a;
    L_0x008f:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r4 = r4.c();
        r12 = 12288; // 0x3000 float:1.7219E-41 double:6.071E-320;
        if (r4 != r12) goto L_0x0141;
    L_0x009a:
        r0 = r35;
        r4 = r0.c;
        r4 = r4.a(r5);
        if (r4 == 0) goto L_0x1073;
    L_0x00a4:
        r4 = 0;
        r0 = r35;
        r12 = r0.c;
        r12 = r12.h();
        r12 = r12 instanceof format.epub.view.style.c;
        if (r12 == 0) goto L_0x00b2;
    L_0x00b1:
        r4 = 1;
    L_0x00b2:
        r0 = r35;
        r12 = r0.c;
        r12.b(r5);
        if (r42 == 0) goto L_0x1073;
    L_0x00bb:
        if (r36 == 0) goto L_0x1073;
    L_0x00bd:
        r12 = format.epub.view.g.g;
        if (r5 != r12) goto L_0x0241;
    L_0x00c1:
        r0 = r35;
        r5 = r0.e;
        if (r5 == 0) goto L_0x00e9;
    L_0x00c7:
        r0 = r35;
        r5 = r0.c;
        r5 = r5.h();
        if (r4 != 0) goto L_0x00d3;
    L_0x00d1:
        r5 = r5.a;
    L_0x00d3:
        r4 = r5 instanceof format.epub.view.style.c;
        if (r4 == 0) goto L_0x011d;
    L_0x00d7:
        r4 = r5;
        r4 = (format.epub.view.style.c) r4;
        r4 = r4.H();
        r4 = r4.e();
        r0 = r35;
        r10 = r0.e;
        if (r4 != r10) goto L_0x0113;
    L_0x00e8:
        r10 = 0;
    L_0x00e9:
        r6 = r6.a;
        r0 = r36;
        r4 = r0.e;
        r4 = r4.size();
        r4 = r4 + -1;
        r5 = r4;
    L_0x00f6:
        if (r5 < 0) goto L_0x0136;
    L_0x00f8:
        r0 = r36;
        r4 = r0.e;
        r4 = r4.get(r5);
        r4 = (format.epub.view.e) r4;
    L_0x0102:
        r12 = r4.f;
        if (r12 == 0) goto L_0x0120;
    L_0x0106:
        r4 = r4.f;
        goto L_0x0102;
    L_0x0109:
        r4 = 0;
        r8 = r4;
        goto L_0x0025;
    L_0x010d:
        r4 = 0;
        goto L_0x003f;
    L_0x0110:
        r4 = 0;
        goto L_0x0057;
    L_0x0113:
        r4 = 1;
        r10 = r4;
    L_0x0115:
        r4 = r5.a;
        if (r5 == r4) goto L_0x00e9;
    L_0x0119:
        if (r4 == 0) goto L_0x00e9;
    L_0x011b:
        r5 = r4;
        goto L_0x00d3;
    L_0x011d:
        r4 = 1;
        r10 = r4;
        goto L_0x0115;
    L_0x0120:
        r12 = r4.c();
        if (r12 != 0) goto L_0x023c;
    L_0x0126:
        r4.b();
        r5 = r4.d;
        r0 = r37;
        r5.a(r0);
        r4 = r4.d;
        r5 = 0;
        r4.a(r9, r5);
    L_0x0136:
        r4 = r7;
    L_0x0137:
        r39 = 0;
        r7 = r9 + 1;
        r0 = r40;
        if (r7 != r0) goto L_0x03ea;
    L_0x013f:
        r9 = r7;
        r7 = r4;
    L_0x0141:
        r0 = r35;
        r4 = r0.c;
        r4 = r4.h();
        r0 = r27;
        r0.u = r4;
        r0 = r27;
        r0.e = r9;
        r0 = r39;
        r1 = r27;
        r1.f = r0;
        r15 = r6;
        r16 = r39;
        r17 = r9;
        r18 = r10;
        r19 = r11;
    L_0x0160:
        if (r30 == 0) goto L_0x03f9;
    L_0x0162:
        r0 = r27;
        r5 = r0.u;
    L_0x0166:
        r4 = r5 instanceof format.epub.view.style.b;
        if (r4 == 0) goto L_0x017e;
    L_0x016a:
        r0 = r27;
        r6 = r0.q;
        r4 = r5;
        r4 = (format.epub.view.style.b) r4;
        r9 = r35.a();
        r4 = r4.n(r9);
        r4 = r4 + r6;
        r0 = r27;
        r0.q = r4;
    L_0x017e:
        r4 = r5.a;
        if (r5 != r4) goto L_0x03f4;
    L_0x0182:
        r0 = r35;
        r4 = r0.c;
        r14 = r4.h();
        r0 = r35;
        r4 = r0.e;
        if (r4 == 0) goto L_0x04b6;
    L_0x0190:
        r4 = r14.l();
        if (r4 <= 0) goto L_0x04a6;
    L_0x0196:
        r4 = r14.l();
        r4 = (float) r4;
    L_0x019b:
        r29 = r4;
    L_0x019d:
        if (r7 == 0) goto L_0x01a4;
    L_0x019f:
        r4 = 1;
        r0 = r27;
        r0.A = r4;
    L_0x01a4:
        r4 = r35.a();
        r4 = r14.d(r4);
        r0 = r27;
        r0.k = r4;
        r4 = r35.a();
        r4 = r14.e(r4);
        r0 = r27;
        r0.l = r4;
        if (r8 == 0) goto L_0x01eb;
    L_0x01be:
        r0 = r27;
        r4 = r0.e;
        r0 = r37;
        r5 = r0.c(r4);
        r4 = r5 instanceof format.epub.view.p;
        if (r4 != 0) goto L_0x0518;
    L_0x01cc:
        r0 = r35;
        r4 = r0.e;
        if (r4 != 0) goto L_0x01eb;
    L_0x01d2:
        r4 = r35.a();
        r4 = r14.l(r4);
        r0 = r27;
        r0.j = r4;
        r0 = r27;
        r4 = r0.k;
        r0 = r27;
        r5 = r0.j;
        r4 = r4 + r5;
        r0 = r27;
        r0.k = r4;
    L_0x01eb:
        r0 = r27;
        r4 = r0.k;
        r4 = (float) r4;
        r5 = 1101004800; // 0x41a00000 float:20.0 double:5.439686476E-315;
        r5 = r29 - r5;
        r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1));
        if (r4 <= 0) goto L_0x0203;
    L_0x01f8:
        r0 = r29;
        r4 = (int) r0;
        r4 = r4 * 3;
        r4 = r4 / 4;
        r0 = r27;
        r0.k = r4;
    L_0x0203:
        r0 = r27;
        r4 = r0.k;
        r4 = (float) r4;
        r0 = r27;
        r0.m = r4;
        r0 = r27;
        r4 = r0.e;
        r0 = r40;
        if (r4 != r0) goto L_0x0597;
    L_0x0214:
        r0 = r27;
        r4 = r0.e;
        r0 = r27;
        r0.g = r4;
        r0 = r27;
        r4 = r0.f;
        r0 = r27;
        r0.h = r4;
        r0 = r27;
        r4 = r0.i;
        if (r4 != 0) goto L_0x0239;
    L_0x022a:
        r4 = 0;
        r0 = r27;
        r0.B = r4;
        r4 = 0;
        r0 = r35;
        r0.e = r4;
        r4 = 0;
        r0 = r35;
        r0.f = r4;
    L_0x0239:
        r4 = r27;
    L_0x023b:
        return r4;
    L_0x023c:
        r4 = r5 + -1;
        r5 = r4;
        goto L_0x00f6;
    L_0x0241:
        r4 = r5 instanceof format.epub.view.x;
        if (r4 == 0) goto L_0x038e;
    L_0x0245:
        r4 = r5;
        r4 = (format.epub.view.x) r4;
        r4 = r4.a;
        r4 = r4 instanceof format.epub.common.text.model.a.a;
        if (r4 == 0) goto L_0x038e;
    L_0x024e:
        r4 = r5;
        r4 = (format.epub.view.x) r4;
        r4 = r4.a;
        r12 = r4.e();
        if (r12 == 0) goto L_0x026c;
    L_0x0259:
        r12 = r4.e();
        r0 = r35;
        r13 = r0.e;
        if (r12 == r13) goto L_0x026c;
    L_0x0263:
        r11 = r4.e();
        r0 = r35;
        r0.e = r11;
        r11 = 1;
    L_0x026c:
        r12 = r4.f();
        if (r12 == 0) goto L_0x027a;
    L_0x0272:
        r4 = r4.f();
        r0 = r27;
        r0.E = r4;
    L_0x027a:
        r13 = new format.epub.view.e;
        r13.<init>();
        r4 = r13.c;
        r0 = r37;
        r4.a(r0);
        r4 = r13.c;
        r12 = 0;
        r4.a(r9, r12);
        r5 = (format.epub.view.x) r5;
        r14 = r5.a;
        r5 = new format.epub.view.style.c;
        r5.<init>(r6, r14);
        r4 = r5;
        r4 = (format.epub.view.style.c) r4;
        r6 = r35.a();
        r4.p(r6);
        r0 = r35;
        r4 = r0.c;
        r4 = r4.e();
        r4 = r4.k();
        r6 = r35.a();
        r6 = r5.b(r6);
        r4 = r4 + r6;
        r4 = (float) r4;
        r5.a(r4);
        r0 = r35;
        r4 = r0.c;
        r4 = r4.e();
        r4 = r4.i();
        r0 = r35;
        r6 = r0.c;
        r6 = r6.e();
        r6 = r6.l();
        r4 = r4 - r6;
        r6 = r35.a();
        r6 = r5.c(r6);
        r4 = r4 - r6;
        r4 = (float) r4;
        r5.b(r4);
        r13.b = r5;
        r13.a = r14;
        r0 = r36;
        r4 = r0.e;
        r4 = r4.size();
        if (r4 != 0) goto L_0x02f7;
    L_0x02ec:
        r0 = r36;
        r4 = r0.e;
        r4.add(r13);
    L_0x02f3:
        r4 = r7;
        r6 = r5;
        goto L_0x0137;
    L_0x02f7:
        r6 = 1;
        r4 = r4 + -1;
        r12 = r4;
    L_0x02fb:
        if (r12 < 0) goto L_0x1076;
    L_0x02fd:
        r0 = r36;
        r4 = r0.e;
        r4 = r4.get(r12);
        r4 = (format.epub.view.e) r4;
        r15 = r14.a;
        r16 = r4.a();
        r0 = r16;
        r0 = r0.a;
        r16 = r0;
        r0 = r16;
        if (r15 != r0) goto L_0x035d;
    L_0x0317:
        r6 = r4.f;
        if (r6 == 0) goto L_0x031e;
    L_0x031b:
        r4 = r4.f;
        goto L_0x0317;
    L_0x031e:
        r6 = r4.c();
        if (r6 == 0) goto L_0x0358;
    L_0x0324:
        r6 = r4.g;
        r4.f = r13;
        r13.e = r4;
        if (r6 == 0) goto L_0x034b;
    L_0x032c:
        if (r12 <= 0) goto L_0x034b;
    L_0x032e:
        r0 = r36;
        r4 = r0.e;
        r6 = r12 + -1;
        r4 = r4.get(r6);
        r4 = (format.epub.view.e) r4;
    L_0x033a:
        r6 = r4.f;
        if (r6 == 0) goto L_0x0341;
    L_0x033e:
        r4 = r4.f;
        goto L_0x033a;
    L_0x0341:
        if (r4 == 0) goto L_0x034b;
    L_0x0343:
        r6 = r4.c();
        if (r6 != 0) goto L_0x034b;
    L_0x0349:
        r13.g = r4;
    L_0x034b:
        r4 = 0;
    L_0x034c:
        if (r4 == 0) goto L_0x02f3;
    L_0x034e:
        if (r12 < 0) goto L_0x02f3;
    L_0x0350:
        r0 = r36;
        r4 = r0.e;
        r4.add(r12, r13);
        goto L_0x02f3;
    L_0x0358:
        r4.b = r5;
        r4.a = r14;
        goto L_0x034b;
    L_0x035d:
        r15 = r14.a;
        r16 = r4.a();
        r0 = r16;
        r0 = r0.a;
        r16 = r0;
        r0 = r16;
        if (r15 <= r0) goto L_0x0389;
    L_0x036d:
        r6 = r4.f;
        if (r6 == 0) goto L_0x0374;
    L_0x0371:
        r4 = r4.f;
        goto L_0x036d;
    L_0x0374:
        if (r4 == 0) goto L_0x037e;
    L_0x0376:
        r6 = r4.c();
        if (r6 != 0) goto L_0x037e;
    L_0x037c:
        r13.g = r4;
    L_0x037e:
        r0 = r36;
        r4 = r0.e;
        r6 = r12 + 1;
        r4.add(r6, r13);
        r4 = 0;
        goto L_0x034c;
    L_0x0389:
        r4 = r12 + -1;
        r12 = r4;
        goto L_0x02fb;
    L_0x038e:
        r4 = r5 instanceof format.epub.view.f;
        if (r4 == 0) goto L_0x1073;
    L_0x0392:
        r5 = (format.epub.view.f) r5;
        r4 = r5.b;
        if (r4 == 0) goto L_0x03e5;
    L_0x0398:
        r4 = r5.a;
        r12 = 9;
        if (r4 != r12) goto L_0x039f;
    L_0x039e:
        r7 = 1;
    L_0x039f:
        r4 = format.epub.view.style.f.a();
        r12 = r5.a;
        r12 = r4.a(r12);
        r4 = com.qq.reader.appconfig.a.d.B;
        r13 = com.qq.reader.common.utils.ao.b;
        r4 = r4.equals(r13);
        if (r4 == 0) goto L_0x03da;
    L_0x03b3:
        r4 = r12.b;
        r13 = format.epub.view.style.f.a();
        r13 = r13.c();
        r13 = r13.n;
        r13 = r13.c();
        r4.c(r13);
    L_0x03c6:
        r4 = r5 instanceof format.epub.view.n;
        if (r4 == 0) goto L_0x03e2;
    L_0x03ca:
        r5 = (format.epub.view.n) r5;
        r4 = r5.h;
        r5 = r4;
    L_0x03cf:
        if (r12 == 0) goto L_0x1070;
    L_0x03d1:
        r4 = new format.epub.view.style.d;
        r4.<init>(r6, r12, r5);
    L_0x03d6:
        r6 = r4;
        r4 = r7;
        goto L_0x0137;
    L_0x03da:
        r4 = r12.b;
        r13 = com.qq.reader.common.utils.ao.b;
        r4.c(r13);
        goto L_0x03c6;
    L_0x03e2:
        r4 = 0;
        r5 = r4;
        goto L_0x03cf;
    L_0x03e5:
        r6 = r6.a;
        r4 = r7;
        goto L_0x0137;
    L_0x03ea:
        r0 = r37;
        r5 = r0.c(r7);
        r9 = r7;
        r7 = r4;
        goto L_0x0065;
    L_0x03f4:
        if (r4 == 0) goto L_0x0182;
    L_0x03f6:
        r5 = r4;
        goto L_0x0166;
    L_0x03f9:
        if (r8 == 0) goto L_0x0182;
    L_0x03fb:
        r0 = r27;
        r5 = r0.u;
        r4 = 0;
        r6 = r4;
    L_0x0401:
        r4 = r5 instanceof format.epub.view.style.c;
        if (r4 == 0) goto L_0x106d;
    L_0x0405:
        r4 = r5;
        r4 = (format.epub.view.style.c) r4;
        r4 = r4.J();
        if (r4 != 0) goto L_0x106a;
    L_0x040e:
        r0 = r27;
        r6 = r0.q;
        r4 = r5;
        r4 = (format.epub.view.style.b) r4;
        r9 = r35.a();
        r4 = r4.n(r9);
        r4 = r4 + r6;
        r0 = r27;
        r0.q = r4;
        r4 = r5;
        r4 = (format.epub.view.style.c) r4;
        r4 = r4.I();
    L_0x0429:
        r6 = r5.a;
        if (r5 != r6) goto L_0x047a;
    L_0x042d:
        r0 = r27;
        r5 = r0.q;
        if (r5 <= 0) goto L_0x0182;
    L_0x0433:
        if (r36 == 0) goto L_0x0182;
    L_0x0435:
        if (r4 <= 0) goto L_0x0182;
    L_0x0437:
        r5 = 0;
        r6 = r36.l();
        r4 = r6.getRightNode(r4);
        r6 = r4;
    L_0x0441:
        if (r6 == 0) goto L_0x1067;
    L_0x0443:
        r4 = r6.e;
        if (r4 == 0) goto L_0x0455;
    L_0x0447:
        r9 = r4.a;
        if (r9 == 0) goto L_0x0455;
    L_0x044b:
        r9 = r4.a;
        r9 = r9.e();
        if (r9 == 0) goto L_0x0455;
    L_0x0453:
        r4 = r4.e;
    L_0x0455:
        if (r4 == 0) goto L_0x048b;
    L_0x0457:
        r9 = r4.g;
        r10 = r6.g;
        if (r9 != r10) goto L_0x048b;
    L_0x045d:
        r4 = r4.b;
        r4 = r4.o();
        r5 = r6.b;
        r5 = r5.p();
        if (r4 >= 0) goto L_0x0481;
    L_0x046b:
        if (r5 >= 0) goto L_0x047f;
    L_0x046d:
        r4 = r4 + r5;
        r4 = -r4;
    L_0x046f:
        r0 = r27;
        r5 = r0.q;
        r4 = r4 + r5;
        r0 = r27;
        r0.q = r4;
        goto L_0x0182;
    L_0x047a:
        if (r6 == 0) goto L_0x042d;
    L_0x047c:
        r5 = r6;
        r6 = r4;
        goto L_0x0401;
    L_0x047f:
        r4 = -r4;
        goto L_0x046f;
    L_0x0481:
        if (r5 >= 0) goto L_0x0485;
    L_0x0483:
        r4 = -r5;
        goto L_0x046f;
    L_0x0485:
        r4 = java.lang.Math.min(r4, r5);
        r4 = -r4;
        goto L_0x046f;
    L_0x048b:
        r4 = r6.g;
        if (r4 == r6) goto L_0x1067;
    L_0x048f:
        if (r4 == 0) goto L_0x04a4;
    L_0x0491:
        r6 = r4.a();
        if (r6 == 0) goto L_0x04a4;
    L_0x0497:
        r6 = r4.a();
        r6 = r6.p();
        r9 = 1;
        if (r6 != r9) goto L_0x04a4;
    L_0x04a2:
        r4 = r5;
        goto L_0x046f;
    L_0x04a4:
        r6 = r4;
        goto L_0x0441;
    L_0x04a6:
        r4 = r35.c();
        r5 = r35.a();
        r5 = r14.e(r5);
        r4 = r4 - r5;
        r4 = (float) r4;
        goto L_0x019b;
    L_0x04b6:
        r0 = r27;
        r4 = r0.E;
        if (r4 == 0) goto L_0x04be;
    L_0x04bc:
        r43 = 0;
    L_0x04be:
        r0 = r43;
        r1 = r27;
        r1.D = r0;
        r4 = 0;
        r4 = (r43 > r4 ? 1 : (r43 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x04ed;
    L_0x04c9:
        r4 = r14.l();
        if (r4 <= 0) goto L_0x04e5;
    L_0x04cf:
        r4 = r14.l();
        r5 = r14.a;
        r6 = r35.a();
        r5 = r5.d(r6);
        r4 = r4 + r5;
        r4 = (float) r4;
        r4 = r4 - r43;
    L_0x04e1:
        r29 = r4;
        goto L_0x019d;
    L_0x04e5:
        r4 = r35.c();
        r4 = (float) r4;
        r4 = r4 - r43;
        goto L_0x04e1;
    L_0x04ed:
        r4 = r14.l();
        if (r4 <= 0) goto L_0x0507;
    L_0x04f3:
        r4 = r14.l();
        r5 = r35.a();
        r5 = r14.d(r5);
        r4 = r4 + r5;
        r4 = (float) r4;
        r4 = r4 - r43;
    L_0x0503:
        r29 = r4;
        goto L_0x019d;
    L_0x0507:
        r4 = r35.c();
        r5 = r35.a();
        r5 = r14.e(r5);
        r4 = r4 - r5;
        r4 = (float) r4;
        r4 = r4 - r43;
        goto L_0x0503;
    L_0x0518:
        r4 = r5;
        r4 = (format.epub.view.p) r4;
        r4 = r4.e();
        r0 = r35;
        r0.e = r4;
        r0 = r35;
        r4 = r0.e;
        r0 = r27;
        r0.B = r4;
        r0 = r35;
        r4 = r0.e;
        if (r4 == 0) goto L_0x01eb;
    L_0x0531:
        r0 = r27;
        r4 = r0.B;
        r6 = 1;
        if (r4 != r6) goto L_0x056c;
    L_0x0538:
        r4 = r14.l();
        if (r4 <= 0) goto L_0x055c;
    L_0x053e:
        r4 = r14.l();
        r0 = r27;
        r5 = r0.k;
        r4 = r4 + r5;
        r4 = (float) r4;
    L_0x0548:
        r5 = r14.i();
        r5 = (float) r5;
        r4 = r4 + r5;
        r0 = r35;
        r0.f = r4;
    L_0x0552:
        r0 = r35;
        r4 = r0.f;
        r0 = r27;
        r0.D = r4;
        goto L_0x01eb;
    L_0x055c:
        r0 = r35;
        r4 = r0.c;
        r6 = 0;
        r4 = r4.a(r5, r6);
        r0 = r27;
        r5 = r0.k;
        r5 = (float) r5;
        r4 = r4 + r5;
        goto L_0x0548;
    L_0x056c:
        r4 = r14.l();
        if (r4 <= 0) goto L_0x0587;
    L_0x0572:
        r4 = r14.l();
        r0 = r27;
        r5 = r0.l;
        r4 = r4 + r5;
        r4 = (float) r4;
    L_0x057c:
        r5 = r14.h();
        r5 = (float) r5;
        r4 = r4 + r5;
        r0 = r35;
        r0.f = r4;
        goto L_0x0552;
    L_0x0587:
        r0 = r35;
        r4 = r0.c;
        r6 = 0;
        r4 = r4.a(r5, r6);
        r0 = r27;
        r5 = r0.l;
        r5 = (float) r5;
        r4 = r4 + r5;
        goto L_0x057c;
    L_0x0597:
        r0 = r27;
        r13 = r0.m;
        r0 = r27;
        r12 = r0.n;
        r0 = r27;
        r11 = r0.o;
        r0 = r27;
        r10 = r0.p;
        r9 = 0;
        r7 = 0;
        r6 = 0;
        r5 = 0;
        r4 = 0;
        r20 = r18;
        r21 = r19;
        r18 = r16;
        r19 = r17;
        r17 = r14;
        r14 = r13;
        r13 = r9;
        r9 = r4;
        r4 = r10;
        r10 = r5;
        r33 = r15;
        r15 = r12;
        r12 = r7;
        r7 = r33;
        r34 = r6;
        r6 = r11;
        r11 = r34;
    L_0x05c6:
        r0 = r37;
        r1 = r19;
        r5 = r0.c(r1);
        r0 = r35;
        r0 = r0.c;
        r16 = r0;
        r0 = r16;
        r1 = r18;
        r23 = r0.a(r5, r1);
        r22 = r14 + r23;
        r0 = r35;
        r14 = r0.c;
        r16 = r14.c(r5);
        r14 = r5 instanceof format.epub.view.y;
        if (r14 == 0) goto L_0x1064;
    L_0x05ea:
        r0 = r35;
        r14 = r0.c;
        r14 = r14.d(r5);
        r14 = r16 - r14;
        r24 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1));
        if (r24 >= 0) goto L_0x1064;
    L_0x05f8:
        r4 = (r15 > r16 ? 1 : (r15 == r16 ? 0 : -1));
        if (r4 >= 0) goto L_0x1060;
    L_0x05fc:
        r4 = r5 instanceof format.epub.view.p;
        if (r4 == 0) goto L_0x0640;
    L_0x0600:
        r4 = r5;
        r4 = (format.epub.view.p) r4;
        r4 = r4.j;
        if (r4 == 0) goto L_0x0640;
    L_0x0607:
        r4 = 1;
        r0 = r27;
        r0.i = r4;
        r0 = r35;
        r4 = r0.c;
        r4 = r4.a;
        r4 = r4.i();
        r4 = (float) r4;
        r0 = r27;
        r0.m = r4;
        r0 = r27;
        r4 = r0.n;
        r4 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1));
        if (r4 >= 0) goto L_0x0627;
    L_0x0623:
        r0 = r27;
        r0.n = r15;
    L_0x0627:
        r0 = r27;
        r4 = r0.o;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 >= 0) goto L_0x0633;
    L_0x062f:
        r0 = r27;
        r0.o = r6;
    L_0x0633:
        r4 = 1;
        r0 = r27;
        r0.y = r4;
        r0 = r35;
        r4 = r0.b;
        r15 = 1;
        r4.a(r15);
    L_0x0640:
        r4 = r5 instanceof format.epub.view.p;
        if (r4 == 0) goto L_0x0644;
    L_0x0644:
        r0 = r35;
        r4 = r0.c;
        r15 = r4.e(r5);
        r4 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1));
        if (r4 >= 0) goto L_0x105d;
    L_0x0650:
        r4 = format.epub.view.g.c;
        if (r5 != r4) goto L_0x0784;
    L_0x0654:
        if (r13 == 0) goto L_0x104e;
    L_0x0656:
        r13 = 0;
        r10 = r10 + 1;
        r11 = r31.n();
        r4 = r22 + r11;
        r6 = r4;
        r33 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r7;
        r7 = r10;
        r10 = r33;
    L_0x0668:
        r0 = r27;
        r4 = r0.e;
        r0 = r19;
        if (r4 != r0) goto L_0x067e;
    L_0x0670:
        r21 = 0;
        r0 = r35;
        r4 = r0.c;
        r4 = r4.h();
        r0 = r35;
        r0.a = r4;
    L_0x067e:
        r0 = r27;
        r4 = r0.e;
        r0 = r37;
        r0.c(r4);
        if (r21 == 0) goto L_0x0a11;
    L_0x0689:
        r4 = r19 + 1;
        r0 = r27;
        r0.g = r4;
        r0 = r35;
        r4 = r0.c;
        r17 = r4.h();
        r0 = r35;
        r4 = r0.c;
        r4 = r4.h();
        r0 = r35;
        r0.a = r4;
    L_0x06a3:
        if (r9 == 0) goto L_0x06b8;
    L_0x06a5:
        r0 = r27;
        r4 = r0.m;
        r4 = r4 - r10;
        r0 = r27;
        r0.m = r4;
        r0 = r27;
        r4 = r0.s;
        r4 = r4 + -1;
        r0 = r27;
        r0.s = r4;
    L_0x06b8:
        r0 = r35;
        r4 = r0.c;
        r0 = r17;
        r4.a(r0);
        if (r8 != 0) goto L_0x06c5;
    L_0x06c3:
        if (r30 == 0) goto L_0x06e4;
    L_0x06c5:
        r0 = r27;
        r4 = r0.n;
        r0 = r27;
        r5 = r0.q;
        r5 = (float) r5;
        r4 = r4 + r5;
        r0 = r27;
        r0.n = r4;
        r0 = r27;
        r4 = r0.n;
        r5 = r35.b();
        r5 = (float) r5;
        r4 = java.lang.Math.min(r4, r5);
        r0 = r27;
        r0.n = r4;
    L_0x06e4:
        r4 = r27.a();
        if (r4 == 0) goto L_0x0780;
    L_0x06ea:
        r6 = 0;
        if (r36 == 0) goto L_0x0fcd;
    L_0x06ed:
        r0 = r27;
        r4 = r0.u;
    L_0x06f1:
        r5 = r4 instanceof format.epub.view.style.c;
        if (r5 == 0) goto L_0x0fba;
    L_0x06f5:
        r5 = 0;
        r7 = r4 instanceof format.epub.view.style.c;
        if (r7 == 0) goto L_0x0fd0;
    L_0x06fa:
        r4 = (format.epub.view.style.c) r4;
        r4 = r4.I();
    L_0x0700:
        if (r4 == 0) goto L_0x0fcd;
    L_0x0702:
        r5 = r36.l();
        r4 = r5.getRightNode(r4);
        if (r4 == 0) goto L_0x0fc9;
    L_0x070c:
        r5 = r4.c();
        if (r5 == 0) goto L_0x0fc9;
    L_0x0712:
        r5 = r4.b;
        r0 = r35;
        r7 = r0.c;
        r7 = r7.f();
        r5 = r5.o(r7);
        r5 = (float) r5;
        r0 = r27;
        r0.r = r5;
        r5 = r4.b;
        r7 = 6;
        r5 = r5.a(r7);
        if (r5 == 0) goto L_0x0fc9;
    L_0x072e:
        r6 = 1;
        r5 = r4;
        r4 = r6;
    L_0x0731:
        if (r5 == 0) goto L_0x0765;
    L_0x0733:
        r6 = r5.g;
        if (r6 == 0) goto L_0x0765;
    L_0x0737:
        r6 = r5.g;
        r6 = r6.c();
        if (r6 == 0) goto L_0x0765;
    L_0x073f:
        r0 = r27;
        r6 = r0.r;
        r7 = r5.g;
        r7 = r7.b;
        r8 = r35.a();
        r7 = r7.o(r8);
        r7 = (float) r7;
        r6 = r6 + r7;
        r0 = r27;
        r0.r = r6;
        r6 = r5.g;
        r6 = r6.b;
        r7 = 6;
        r6 = r6.a(r7);
        if (r6 == 0) goto L_0x0761;
    L_0x0760:
        r4 = 1;
    L_0x0761:
        r6 = r5.g;
        if (r5 != r6) goto L_0x0fc6;
    L_0x0765:
        if (r4 != 0) goto L_0x0780;
    L_0x0767:
        r0 = r27;
        r4 = r0.r;
        r0 = r35;
        r5 = r0.c;
        r5 = r5.e();
        r5 = r5.o();
        r6 = 1050253722; // 0x3e99999a float:0.3 double:5.188942835E-315;
        r5 = r5 * r6;
        r4 = r4 + r5;
        r0 = r27;
        r0.r = r4;
    L_0x0780:
        r4 = r27;
        goto L_0x023b;
    L_0x0784:
        r4 = r5 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x079b;
    L_0x0788:
        r13 = 1;
        r4 = 1;
        r0 = r27;
        r0.z = r4;
        r12 = 1;
        r6 = r22;
        r33 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r7;
        r7 = r10;
        r10 = r33;
        goto L_0x0668;
    L_0x079b:
        r0 = r35;
        r4 = r0.c;
        r4 = r4.a(r5);
        if (r4 == 0) goto L_0x09f0;
    L_0x07a5:
        r4 = 0;
        r0 = r35;
        r6 = r0.c;
        r6 = r6.h();
        r6 = r6 instanceof format.epub.view.style.c;
        if (r6 == 0) goto L_0x07b3;
    L_0x07b2:
        r4 = 1;
    L_0x07b3:
        r0 = r35;
        r6 = r0.c;
        r6.b(r5);
        if (r42 == 0) goto L_0x0838;
    L_0x07bc:
        if (r36 == 0) goto L_0x0838;
    L_0x07be:
        r6 = format.epub.view.g.g;
        if (r5 != r6) goto L_0x0852;
    L_0x07c2:
        r0 = r35;
        r6 = r0.e;
        if (r6 == 0) goto L_0x07ef;
    L_0x07c8:
        r0 = r35;
        r6 = r0.c;
        r6 = r6.h();
        if (r4 != 0) goto L_0x07d4;
    L_0x07d2:
        r6 = r6.a;
    L_0x07d4:
        r4 = r6 instanceof format.epub.view.style.c;
        if (r4 == 0) goto L_0x081c;
    L_0x07d8:
        r4 = r6;
        r4 = (format.epub.view.style.c) r4;
        r4 = r4.H();
        r4 = r4.e();
        r0 = r35;
        r0 = r0.e;
        r20 = r0;
        r0 = r20;
        if (r4 != r0) goto L_0x0811;
    L_0x07ed:
        r20 = 0;
    L_0x07ef:
        r7 = r7.a;
        r0 = r36;
        r4 = r0.e;
        r4 = r4.size();
        r4 = r4 + -1;
        r6 = r4;
    L_0x07fc:
        if (r6 < 0) goto L_0x0838;
    L_0x07fe:
        r0 = r36;
        r4 = r0.e;
        r4 = r4.get(r6);
        r4 = (format.epub.view.e) r4;
    L_0x0808:
        r0 = r4.f;
        r24 = r0;
        if (r24 == 0) goto L_0x0820;
    L_0x080e:
        r4 = r4.f;
        goto L_0x0808;
    L_0x0811:
        r4 = 1;
        r20 = r4;
    L_0x0814:
        r4 = r6.a;
        if (r6 == r4) goto L_0x07ef;
    L_0x0818:
        if (r4 == 0) goto L_0x07ef;
    L_0x081a:
        r6 = r4;
        goto L_0x07d4;
    L_0x081c:
        r4 = 1;
        r20 = r4;
        goto L_0x0814;
    L_0x0820:
        r24 = r4.c();
        if (r24 != 0) goto L_0x084e;
    L_0x0826:
        r4.b();
        r6 = r4.d;
        r0 = r37;
        r6.a(r0);
        r4 = r4.d;
        r6 = 0;
        r0 = r19;
        r4.a(r0, r6);
    L_0x0838:
        r0 = r27;
        r4 = r0.t;
        r4 = r4 + 1;
        r0 = r27;
        r0.t = r4;
        r6 = r22;
        r33 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r7;
        r7 = r10;
        r10 = r33;
        goto L_0x0668;
    L_0x084e:
        r4 = r6 + -1;
        r6 = r4;
        goto L_0x07fc;
    L_0x0852:
        r4 = r5 instanceof format.epub.view.x;
        if (r4 == 0) goto L_0x0988;
    L_0x0856:
        r4 = r5;
        r4 = (format.epub.view.x) r4;
        r4 = r4.a;
        r4 = r4 instanceof format.epub.common.text.model.a.a;
        if (r4 == 0) goto L_0x0988;
    L_0x085f:
        r4 = r7 instanceof format.epub.view.style.c;
        if (r4 == 0) goto L_0x0888;
    L_0x0863:
        r4 = r7;
        r4 = (format.epub.view.style.c) r4;
        r4 = r4.H();
        r6 = r4.e();
        if (r6 == 0) goto L_0x0888;
    L_0x0870:
        r6 = r4.e();
        r0 = r35;
        r0 = r0.e;
        r24 = r0;
        r0 = r24;
        if (r6 == r0) goto L_0x0888;
    L_0x087e:
        r4 = r4.e();
        r0 = r35;
        r0.e = r4;
        r21 = 1;
    L_0x0888:
        r25 = new format.epub.view.e;
        r25.<init>();
        r0 = r25;
        r4 = r0.c;
        r0 = r37;
        r4.a(r0);
        r0 = r25;
        r4 = r0.c;
        r6 = 0;
        r0 = r19;
        r4.a(r0, r6);
        r4 = r5;
        r4 = (format.epub.view.x) r4;
        r0 = r4.a;
        r26 = r0;
        r6 = new format.epub.view.style.c;
        r0 = r26;
        r6.<init>(r7, r0);
        r4 = r6;
        r4 = (format.epub.view.style.c) r4;
        r7 = r35.a();
        r4.p(r7);
        r0 = r25;
        r0.b = r6;
        r0 = r26;
        r1 = r25;
        r1.a = r0;
        r0 = r36;
        r4 = r0.e;
        r4 = r4.size();
        if (r4 != 0) goto L_0x08d8;
    L_0x08cc:
        r0 = r36;
        r4 = r0.e;
        r0 = r25;
        r4.add(r0);
    L_0x08d5:
        r7 = r6;
        goto L_0x0838;
    L_0x08d8:
        r7 = 1;
        r4 = r4 + -1;
        r24 = r4;
    L_0x08dd:
        if (r24 < 0) goto L_0x105a;
    L_0x08df:
        r0 = r36;
        r4 = r0.e;
        r0 = r24;
        r4 = r4.get(r0);
        r4 = (format.epub.view.e) r4;
        r0 = r26;
        r0 = r0.a;
        r28 = r0;
        r32 = r4.a();
        r0 = r32;
        r0 = r0.a;
        r32 = r0;
        r0 = r28;
        r1 = r32;
        if (r0 != r1) goto L_0x094c;
    L_0x0901:
        r7 = r4.f;
        if (r7 == 0) goto L_0x0908;
    L_0x0905:
        r4 = r4.f;
        goto L_0x0901;
    L_0x0908:
        r7 = r4.c();
        if (r7 == 0) goto L_0x093b;
    L_0x090e:
        r7 = r4.g;
        r0 = r25;
        r4.f = r0;
        r0 = r25;
        r0.e = r4;
        if (r7 == 0) goto L_0x093b;
    L_0x091a:
        if (r24 <= 0) goto L_0x093b;
    L_0x091c:
        r0 = r36;
        r4 = r0.e;
        r7 = r24 + -1;
        r4 = r4.get(r7);
        r4 = (format.epub.view.e) r4;
    L_0x0928:
        r7 = r4.f;
        if (r7 == 0) goto L_0x092f;
    L_0x092c:
        r4 = r4.f;
        goto L_0x0928;
    L_0x092f:
        if (r4 == 0) goto L_0x093b;
    L_0x0931:
        r7 = r4.c();
        if (r7 != 0) goto L_0x093b;
    L_0x0937:
        r0 = r25;
        r0.g = r4;
    L_0x093b:
        r4 = 0;
    L_0x093c:
        if (r4 == 0) goto L_0x08d5;
    L_0x093e:
        if (r24 < 0) goto L_0x08d5;
    L_0x0940:
        r0 = r36;
        r4 = r0.e;
        r0 = r24;
        r1 = r25;
        r4.add(r0, r1);
        goto L_0x08d5;
    L_0x094c:
        r0 = r26;
        r0 = r0.a;
        r28 = r0;
        r32 = r4.a();
        r0 = r32;
        r0 = r0.a;
        r32 = r0;
        r0 = r28;
        r1 = r32;
        if (r0 <= r1) goto L_0x0982;
    L_0x0962:
        r7 = r4.f;
        if (r7 == 0) goto L_0x0969;
    L_0x0966:
        r4 = r4.f;
        goto L_0x0962;
    L_0x0969:
        if (r4 == 0) goto L_0x0975;
    L_0x096b:
        r7 = r4.c();
        if (r7 != 0) goto L_0x0975;
    L_0x0971:
        r0 = r25;
        r0.g = r4;
    L_0x0975:
        r0 = r36;
        r4 = r0.e;
        r7 = r24 + 1;
        r0 = r25;
        r4.add(r7, r0);
        r4 = 0;
        goto L_0x093c;
    L_0x0982:
        r4 = r24 + -1;
        r24 = r4;
        goto L_0x08dd;
    L_0x0988:
        r4 = r5 instanceof format.epub.view.f;
        if (r4 == 0) goto L_0x0838;
    L_0x098c:
        r4 = r5;
        r4 = (format.epub.view.f) r4;
        r6 = r4.b;
        if (r6 == 0) goto L_0x09ec;
    L_0x0993:
        r6 = format.epub.view.style.f.a();
        r0 = r4.a;
        r24 = r0;
        r0 = r24;
        r24 = r6.a(r0);
        r6 = com.qq.reader.appconfig.a.d.B;
        r25 = com.qq.reader.common.utils.ao.b;
        r0 = r25;
        r6 = r6.equals(r0);
        if (r6 == 0) goto L_0x09dd;
    L_0x09ad:
        r0 = r24;
        r6 = r0.b;
        r25 = format.epub.view.style.f.a();
        r25 = r25.c();
        r0 = r25;
        r0 = r0.n;
        r25 = r0;
        r25 = r25.c();
        r0 = r25;
        r6.c(r0);
    L_0x09c8:
        r6 = r4 instanceof format.epub.view.n;
        if (r6 == 0) goto L_0x09e9;
    L_0x09cc:
        r4 = (format.epub.view.n) r4;
        r4 = r4.h;
        r6 = r4;
    L_0x09d1:
        if (r24 == 0) goto L_0x0838;
    L_0x09d3:
        r4 = new format.epub.view.style.d;
        r0 = r24;
        r4.<init>(r7, r0, r6);
        r7 = r4;
        goto L_0x0838;
    L_0x09dd:
        r0 = r24;
        r6 = r0.b;
        r25 = com.qq.reader.common.utils.ao.b;
        r0 = r25;
        r6.c(r0);
        goto L_0x09c8;
    L_0x09e9:
        r4 = 0;
        r6 = r4;
        goto L_0x09d1;
    L_0x09ec:
        r7 = r7.a;
        goto L_0x0838;
    L_0x09f0:
        r4 = r5 instanceof format.epub.view.p;
        if (r4 == 0) goto L_0x104e;
    L_0x09f4:
        r13 = 1;
        r12 = 1;
        r4 = r5;
        r4 = (format.epub.view.p) r4;
        r6 = r4.b();
        if (r6 == 0) goto L_0x104e;
    L_0x09ff:
        r4 = r4.c();
        if (r4 == 0) goto L_0x104e;
    L_0x0a05:
        r4 = 0;
        r6 = r4;
        r33 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r7;
        r7 = r10;
        r10 = r33;
        goto L_0x0668;
    L_0x0a11:
        if (r20 == 0) goto L_0x0ab1;
    L_0x0a13:
        r4 = r19 + 1;
        r0 = r27;
        r0.g = r4;
        r0 = r35;
        r4 = r0.c;
        r17 = r4.h();
        r0 = r35;
        r4 = r0.e;
        r0 = r27;
        r0.B = r4;
        r0 = r27;
        r4 = r0.B;
        r5 = 1;
        if (r4 != r5) goto L_0x0a79;
    L_0x0a30:
        r0 = r35;
        r4 = r0.a;
        r4 = r4.l();
        if (r4 <= 0) goto L_0x0a74;
    L_0x0a3a:
        r0 = r35;
        r4 = r0.a;
        r4 = r4.l();
        r0 = r27;
        r5 = r0.k;
        r4 = r4 + r5;
        r4 = (float) r4;
    L_0x0a48:
        r0 = r35;
        r5 = r0.a;
        r5 = r5.i();
        r5 = (float) r5;
        r4 = r4 + r5;
        r0 = r35;
        r0.f = r4;
    L_0x0a56:
        r0 = r35;
        r4 = r0.f;
        r0 = r27;
        r0.D = r4;
        r4 = 0;
        r0 = r27;
        r0.w = r4;
        r4 = 0;
        r0 = r35;
        r0.e = r4;
        r4 = 0;
        r0 = r35;
        r0.f = r4;
        r4 = 0;
        r0 = r35;
        r0.a = r4;
        goto L_0x06a3;
    L_0x0a74:
        r0 = r27;
        r4 = r0.m;
        goto L_0x0a48;
    L_0x0a79:
        r0 = r35;
        r4 = r0.a;
        r4 = r4.l();
        if (r4 <= 0) goto L_0x0aa0;
    L_0x0a83:
        r0 = r35;
        r4 = r0.a;
        r4 = r4.l();
        r0 = r27;
        r5 = r0.l;
        r4 = r4 + r5;
        r4 = (float) r4;
    L_0x0a91:
        r0 = r35;
        r5 = r0.a;
        r5 = r5.h();
        r5 = (float) r5;
        r4 = r4 + r5;
        r0 = r35;
        r0.f = r4;
        goto L_0x0a56;
    L_0x0aa0:
        r0 = r27;
        r4 = r0.m;
        r0 = r27;
        r5 = r0.k;
        r5 = (float) r5;
        r4 = r4 - r5;
        r0 = r27;
        r5 = r0.l;
        r5 = (float) r5;
        r4 = r4 + r5;
        goto L_0x0a91;
    L_0x0ab1:
        r4 = (r6 > r29 ? 1 : (r6 == r29 ? 0 : -1));
        if (r4 <= 0) goto L_0x1045;
    L_0x0ab5:
        r0 = r27;
        r4 = r0.g;
        r0 = r38;
        if (r4 == r0) goto L_0x1045;
    L_0x0abd:
        r0 = r27;
        r4 = r0.y;
        if (r4 != 0) goto L_0x1045;
    L_0x0ac3:
        r0 = r27;
        r4 = r0.A;
        if (r4 != 0) goto L_0x1045;
    L_0x0ac9:
        r0 = r35;
        r4 = r0.e;
        r0 = r27;
        r0.B = r4;
        r0 = r27;
        r4 = r0.B;
        if (r4 == 0) goto L_0x0b07;
    L_0x0ad7:
        r0 = r27;
        r4 = r0.B;
        r22 = 1;
        r0 = r22;
        if (r4 != r0) goto L_0x0ba1;
    L_0x0ae1:
        r0 = r27;
        r4 = r0.m;
        r0 = r35;
        r0 = r0.a;
        r22 = r0;
        r22 = r22.i();
        r0 = r22;
        r0 = (float) r0;
        r22 = r0;
        r4 = r4 + r22;
        r0 = r35;
        r0.f = r4;
    L_0x0afa:
        r0 = r35;
        r4 = r0.f;
        r0 = r27;
        r0.D = r4;
        r4 = 0;
        r0 = r27;
        r0.w = r4;
    L_0x0b07:
        r0 = r35;
        r4 = r0.c;
        r4 = r4.h();
        r4 = r4.l();
        if (r4 > 0) goto L_0x0b28;
    L_0x0b15:
        r0 = r27;
        r4 = r0.e;
        r0 = r37;
        r4 = r0.c(r4);
        r4 = r4 instanceof format.epub.view.p;
        if (r4 != 0) goto L_0x0b28;
    L_0x0b23:
        r4 = 1;
        r0 = r27;
        r0.F = r4;
    L_0x0b28:
        r4 = r5 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x1039;
    L_0x0b2c:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r4 = r4.c();
        r22 = 0;
        r24 = 32;
        r0 = r24;
        if (r4 <= r0) goto L_0x1031;
    L_0x0b3b:
        r24 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r0 = r24;
        if (r4 >= r0) goto L_0x1031;
    L_0x0b41:
        r25 = 0;
        r0 = r27;
        r0 = r0.m;
        r28 = r0;
        r24 = r19 + -1;
        r26 = r24;
        r24 = r6;
        r6 = r25;
        r25 = r4;
    L_0x0b53:
        r0 = r26;
        r1 = r38;
        if (r0 <= r1) goto L_0x1027;
    L_0x0b59:
        r0 = r37;
        r1 = r26;
        r6 = r0.c(r1);
        r4 = r6 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x0d24;
    L_0x0b65:
        r4 = r6;
        r4 = (format.epub.view.y) r4;
        r25 = r4.c();
        r4 = 32;
        r0 = r25;
        if (r0 <= r4) goto L_0x0b78;
    L_0x0b72:
        r4 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r0 = r25;
        if (r0 < r4) goto L_0x0d06;
    L_0x0b78:
        r4 = com.qq.reader.readengine.a.c.d(r25);
        if (r4 == 0) goto L_0x0bd6;
    L_0x0b7e:
        r0 = r27;
        r4 = r0.m;
        r0 = r35;
        r0 = r0.c;
        r24 = r0;
        r0 = r24;
        r1 = r18;
        r24 = r0.a(r6, r1);
        r4 = r4 - r24;
        r0 = r27;
        r0.m = r4;
        r0 = r27;
        r4 = r0.m;
    L_0x0b9a:
        r24 = r26 + -1;
        r26 = r24;
        r24 = r4;
        goto L_0x0b53;
    L_0x0ba1:
        r0 = r27;
        r4 = r0.m;
        r0 = r27;
        r0 = r0.k;
        r22 = r0;
        r0 = r22;
        r0 = (float) r0;
        r22 = r0;
        r4 = r4 - r22;
        r0 = r27;
        r0 = r0.l;
        r22 = r0;
        r0 = r22;
        r0 = (float) r0;
        r22 = r0;
        r4 = r4 + r22;
        r0 = r35;
        r0 = r0.a;
        r22 = r0;
        r22 = r22.h();
        r0 = r22;
        r0 = (float) r0;
        r22 = r0;
        r4 = r4 + r22;
        r0 = r35;
        r0.f = r4;
        goto L_0x0afa;
    L_0x0bd6:
        r22 = r26 + 1;
        r0 = r22;
        r1 = r27;
        r1.g = r0;
        r4 = 1;
        r19 = r6;
        r6 = r25;
    L_0x0be3:
        if (r4 != 0) goto L_0x0d33;
    L_0x0be5:
        r0 = r28;
        r1 = r27;
        r1.m = r0;
        r0 = r27;
        r0 = r0.m;
        r19 = r0;
        r33 = r4;
        r4 = r6;
        r6 = r19;
        r19 = r33;
    L_0x0bf8:
        if (r19 == 0) goto L_0x1022;
    L_0x0bfa:
        r0 = r37;
        r1 = r22;
        r5 = r0.c(r1);
        r0 = r35;
        r4 = r0.c;
        r0 = r18;
        r4 = r4.a(r5, r0);
        r6 = r6 + r4;
        r4 = r5 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x0d4a;
    L_0x0c11:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r4 = r4.c();
        r19 = r6;
        r6 = r4;
    L_0x0c1b:
        r4 = com.qq.reader.readengine.a.c.a(r6);
        if (r4 == 0) goto L_0x1016;
    L_0x0c21:
        r4 = (r19 > r29 ? 1 : (r19 == r29 ? 0 : -1));
        if (r4 <= 0) goto L_0x1016;
    L_0x0c25:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r24 = 1;
        r0 = r24;
        r4.a(r0);
        r4 = 1;
        r0 = r27;
        r0.x = r4;
        r0 = r35;
        r4 = r0.c;
        r0 = r18;
        r4 = r4.a(r5, r0);
        r18 = com.qq.reader.readengine.a.c.b(r6);
        if (r18 == 0) goto L_0x0d52;
    L_0x0c44:
        r6 = 1082130432; // 0x40800000 float:4.0 double:5.34643471E-315;
        r25 = r4 / r6;
    L_0x0c48:
        r26 = r22 + 1;
        r28 = 0;
        r0 = r26;
        r1 = r40;
        if (r0 != r1) goto L_0x0d62;
    L_0x0c52:
        r4 = 1;
    L_0x0c53:
        if (r4 != 0) goto L_0x1013;
    L_0x0c55:
        r0 = r37;
        r1 = r26;
        r6 = r0.c(r1);
        r4 = r6 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x0c65;
    L_0x0c61:
        r4 = r5 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x0d65;
    L_0x0c65:
        r4 = r6 instanceof format.epub.view.p;
        if (r4 == 0) goto L_0x0c70;
    L_0x0c69:
        r4 = r6;
        r4 = (format.epub.view.p) r4;
        r4 = r4.i;
        if (r4 != 0) goto L_0x0d65;
    L_0x0c70:
        r4 = 1;
    L_0x0c71:
        r24 = 0;
        if (r4 == 0) goto L_0x100a;
    L_0x0c75:
        r0 = r27;
        r0.i = r11;
        if (r5 == r6) goto L_0x1002;
    L_0x0c7b:
        r4 = r6 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x1002;
    L_0x0c7f:
        r4 = r6;
        r4 = (format.epub.view.y) r4;
        r4 = r4.c();
        r4 = com.qq.reader.readengine.a.c.a(r4);
        if (r4 != 0) goto L_0x0c96;
    L_0x0c8c:
        r4 = r19 - r29;
        r18 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r18 = r23 / r18;
        r4 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1));
        if (r4 <= 0) goto L_0x1002;
    L_0x0c96:
        r18 = r26 + -1;
        r5 = (format.epub.view.y) r5;
        r4 = 0;
        r5.a(r4);
        r4 = 0;
        r22 = r23;
        r33 = r4;
        r4 = r18;
        r18 = r33;
    L_0x0ca7:
        r23 = r4 + -1;
        r0 = r37;
        r1 = r23;
        r5 = r0.c(r1);
        r4 = r5 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x0cbf;
    L_0x0cb5:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r32 = 0;
        r0 = r32;
        r4.a(r0);
    L_0x0cbf:
        r0 = r35;
        r4 = r0.c;
        r32 = 0;
        r0 = r32;
        r4 = r4.a(r5, r0);
        r22 = r22 + r4;
        r4 = r5 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x0cde;
    L_0x0cd1:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r4 = r4.c();
        r4 = com.qq.reader.readengine.a.c.a(r4);
        if (r4 != 0) goto L_0x0ffe;
    L_0x0cde:
        r0 = r35;
        r4 = r0.c;
        r4 = r4.a(r5);
        if (r4 == 0) goto L_0x0d68;
    L_0x0ce8:
        r0 = r27;
        r4 = r0.t;
        r4 = r4 + -1;
        r0 = r27;
        r0.t = r4;
        r4 = r18;
    L_0x0cf4:
        r5 = r38 + 5;
        r0 = r23;
        if (r0 > r5) goto L_0x0ff8;
    L_0x0cfa:
        r4 = r24;
        r5 = r25;
        r18 = r26;
    L_0x0d00:
        if (r4 != 0) goto L_0x0d97;
    L_0x0d02:
        r4 = r18 + -1;
        goto L_0x06a3;
    L_0x0d06:
        r0 = r27;
        r4 = r0.m;
        r0 = r35;
        r0 = r0.c;
        r24 = r0;
        r0 = r24;
        r1 = r18;
        r24 = r0.a(r6, r1);
        r4 = r4 - r24;
        r0 = r27;
        r0.m = r4;
        r0 = r27;
        r4 = r0.m;
        goto L_0x0b9a;
    L_0x0d24:
        r22 = r26 + 1;
        r0 = r22;
        r1 = r27;
        r1.g = r0;
        r4 = 1;
        r19 = r6;
        r6 = r25;
        goto L_0x0be3;
    L_0x0d33:
        r25 = format.epub.view.g.c;
        r0 = r19;
        r1 = r25;
        if (r0 != r1) goto L_0x0d43;
    L_0x0d3b:
        r19 = 0;
        r0 = r19;
        r1 = r27;
        r1.w = r0;
    L_0x0d43:
        r19 = r4;
        r4 = r6;
        r6 = r24;
        goto L_0x0bf8;
    L_0x0d4a:
        r4 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r19 = r6;
        r6 = r4;
        goto L_0x0c1b;
    L_0x0d52:
        r6 = com.qq.reader.readengine.a.c.c(r6);
        if (r6 == 0) goto L_0x0d5e;
    L_0x0d58:
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r25 = r4 / r6;
        goto L_0x0c48;
    L_0x0d5e:
        r25 = 0;
        goto L_0x0c48;
    L_0x0d62:
        r4 = 0;
        goto L_0x0c53;
    L_0x0d65:
        r4 = 0;
        goto L_0x0c71;
    L_0x0d68:
        r4 = r5 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x0d85;
    L_0x0d6c:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r4 = r4.c();
        r32 = 32;
        r0 = r32;
        if (r4 <= r0) goto L_0x0d85;
    L_0x0d79:
        r32 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r0 = r32;
        if (r4 >= r0) goto L_0x0d85;
    L_0x0d7f:
        r18 = 1;
        r4 = r18;
        goto L_0x0cf4;
    L_0x0d85:
        if (r18 == 0) goto L_0x0d90;
    L_0x0d87:
        r4 = format.epub.view.g.c;
        if (r5 != r4) goto L_0x0d90;
    L_0x0d8b:
        r4 = 0;
        r0 = r27;
        r0.w = r4;
    L_0x0d90:
        r4 = 1;
        r5 = r22;
        r18 = r23;
        goto L_0x0d00;
    L_0x0d97:
        r9 = r18;
    L_0x0d99:
        r5 = r19 - r5;
        r0 = r27;
        r0.m = r5;
        r0 = r27;
        r0 = r0.m;
        r19 = r0;
        r0 = r27;
        r5 = r0.n;
        r5 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1));
        if (r5 >= 0) goto L_0x0db3;
    L_0x0dad:
        r0 = r16;
        r1 = r27;
        r1.n = r0;
    L_0x0db3:
        r0 = r27;
        r5 = r0.o;
        r5 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1));
        if (r5 >= 0) goto L_0x0dbf;
    L_0x0dbb:
        r0 = r27;
        r0.o = r15;
    L_0x0dbf:
        r0 = r27;
        r5 = r0.p;
        r5 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1));
        if (r5 >= 0) goto L_0x0dcb;
    L_0x0dc7:
        r0 = r27;
        r0.p = r14;
    L_0x0dcb:
        r0 = r27;
        r0.g = r9;
        r0 = r28;
        r1 = r27;
        r1.h = r0;
        r0 = r27;
        r0.s = r7;
        r0 = r35;
        r5 = r0.c;
        r17 = r5.h();
        if (r12 != 0) goto L_0x0e01;
    L_0x0de3:
        if (r7 <= 0) goto L_0x0e01;
    L_0x0de5:
        r5 = 1;
    L_0x0de6:
        r24 = r4;
        r4 = r5;
        r5 = r19;
        r33 = r17;
        r17 = r9;
        r9 = r33;
    L_0x0df1:
        if (r24 != 0) goto L_0x0e03;
    L_0x0df3:
        r6 = r9;
        r9 = r28;
    L_0x0df6:
        r0 = r17;
        r1 = r40;
        if (r0 != r1) goto L_0x0fd6;
    L_0x0dfc:
        r9 = r4;
        r17 = r6;
        goto L_0x06a3;
    L_0x0e01:
        r5 = 0;
        goto L_0x0de6;
    L_0x0e03:
        r19 = r6;
        r22 = r5;
        r18 = r9;
        r9 = r17;
        r17 = r4;
    L_0x0e0d:
        r0 = r38;
        if (r9 <= r0) goto L_0x0e8b;
    L_0x0e11:
        r4 = r9 + -1;
        r0 = r37;
        r5 = r0.c(r4);
        r4 = 0;
        r0 = r35;
        r6 = r0.c;
        r6 = r6.a(r5);
        if (r6 == 0) goto L_0x0ff5;
    L_0x0e24:
        r6 = r9 + -1;
        r0 = r38;
        if (r6 <= r0) goto L_0x0ff5;
    L_0x0e2a:
        r4 = 1;
        r5 = r9 + -2;
        r0 = r37;
        r5 = r0.c(r5);
        r6 = r4;
    L_0x0e34:
        r4 = r5 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x0e8b;
    L_0x0e38:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r4 = r4.c();
    L_0x0e3f:
        r4 = com.qq.reader.readengine.a.c.d(r4);
        if (r4 == 0) goto L_0x0e8b;
    L_0x0e45:
        if (r6 == 0) goto L_0x0e54;
    L_0x0e47:
        r0 = r27;
        r4 = r0.t;
        r4 = r4 + -1;
        r0 = r27;
        r0.t = r4;
        r9 = r9 + -1;
        r6 = 0;
    L_0x0e54:
        r9 = r9 + -1;
        r0 = r27;
        r4 = r0.m;
        r0 = r35;
        r0 = r0.c;
        r23 = r0;
        r24 = 0;
        r0 = r23;
        r1 = r24;
        r5 = r0.a(r5, r1);
        r4 = r4 - r5;
        r0 = r27;
        r0.m = r4;
        r0 = r27;
        r0.g = r9;
        r0 = r38;
        if (r9 <= r0) goto L_0x0e8b;
    L_0x0e77:
        r4 = r9 + -1;
        r0 = r37;
        r5 = r0.c(r4);
        r4 = r5 instanceof format.epub.view.y;
        if (r4 == 0) goto L_0x0e8b;
    L_0x0e83:
        r4 = r5;
        r4 = (format.epub.view.y) r4;
        r4 = r4.c();
        goto L_0x0e3f;
    L_0x0e8b:
        r0 = r35;
        r4 = r0.c;
        r0 = r19;
        r4 = r4.a(r0);
        if (r4 != 0) goto L_0x0e9d;
    L_0x0e97:
        r9 = r17;
        r17 = r18;
        goto L_0x06a3;
    L_0x0e9d:
        r5 = r17;
        r6 = r22;
        r4 = r9;
        r9 = r18;
    L_0x0ea4:
        r18 = r4 + 1;
        r17 = 0;
        r0 = r18;
        r1 = r40;
        if (r0 != r1) goto L_0x0f74;
    L_0x0eae:
        r4 = 1;
    L_0x0eaf:
        if (r4 != 0) goto L_0x0ed4;
    L_0x0eb1:
        r0 = r37;
        r1 = r18;
        r4 = r0.c(r1);
        r0 = r4 instanceof format.epub.view.y;
        r22 = r0;
        if (r22 == 0) goto L_0x0ec7;
    L_0x0ebf:
        r0 = r19;
        r0 = r0 instanceof format.epub.view.y;
        r19 = r0;
        if (r19 == 0) goto L_0x0f77;
    L_0x0ec7:
        r0 = r4 instanceof format.epub.view.p;
        r19 = r0;
        if (r19 == 0) goto L_0x0ed3;
    L_0x0ecd:
        r4 = (format.epub.view.p) r4;
        r4 = r4.i;
        if (r4 != 0) goto L_0x0f77;
    L_0x0ed3:
        r4 = 1;
    L_0x0ed4:
        if (r4 == 0) goto L_0x0fec;
    L_0x0ed6:
        r0 = r35;
        r4 = r0.e;
        r0 = r27;
        r0.B = r4;
        r0 = r27;
        r4 = r0.B;
        if (r4 == 0) goto L_0x0f1e;
    L_0x0ee4:
        r0 = r27;
        r4 = r0.B;
        r5 = 1;
        if (r4 != r5) goto L_0x0f7f;
    L_0x0eeb:
        r0 = r35;
        r4 = r0.a;
        r4 = r4.l();
        if (r4 <= 0) goto L_0x0f7a;
    L_0x0ef5:
        r0 = r35;
        r4 = r0.a;
        r4 = r4.l();
        r0 = r27;
        r5 = r0.k;
        r4 = r4 + r5;
        r4 = (float) r4;
    L_0x0f03:
        r0 = r35;
        r5 = r0.a;
        r5 = r5.i();
        r5 = (float) r5;
        r4 = r4 + r5;
        r0 = r35;
        r0.f = r4;
    L_0x0f11:
        r0 = r35;
        r4 = r0.f;
        r0 = r27;
        r0.D = r4;
        r4 = 0;
        r0 = r27;
        r0.w = r4;
    L_0x0f1e:
        r0 = r27;
        r0.i = r11;
        r0 = r27;
        r0.m = r6;
        r0 = r27;
        r4 = r0.n;
        r4 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1));
        if (r4 >= 0) goto L_0x0f34;
    L_0x0f2e:
        r0 = r16;
        r1 = r27;
        r1.n = r0;
    L_0x0f34:
        r0 = r27;
        r4 = r0.o;
        r4 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1));
        if (r4 >= 0) goto L_0x0f40;
    L_0x0f3c:
        r0 = r27;
        r0.o = r15;
    L_0x0f40:
        r0 = r27;
        r4 = r0.p;
        r4 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1));
        if (r4 >= 0) goto L_0x0f4c;
    L_0x0f48:
        r0 = r27;
        r0.p = r14;
    L_0x0f4c:
        r0 = r18;
        r1 = r27;
        r1.g = r0;
        r0 = r17;
        r1 = r27;
        r1.h = r0;
        r0 = r27;
        r0.s = r7;
        r0 = r35;
        r4 = r0.c;
        r5 = r4.h();
        if (r12 != 0) goto L_0x0fb8;
    L_0x0f66:
        if (r7 <= 0) goto L_0x0fb8;
    L_0x0f68:
        r4 = 1;
    L_0x0f69:
        r9 = r17;
        r17 = r18;
        r33 = r6;
        r6 = r5;
        r5 = r33;
        goto L_0x0df6;
    L_0x0f74:
        r4 = 0;
        goto L_0x0eaf;
    L_0x0f77:
        r4 = 0;
        goto L_0x0ed4;
    L_0x0f7a:
        r0 = r27;
        r4 = r0.m;
        goto L_0x0f03;
    L_0x0f7f:
        r0 = r35;
        r4 = r0.a;
        r4 = r4.l();
        if (r4 <= 0) goto L_0x0fa7;
    L_0x0f89:
        r0 = r35;
        r4 = r0.a;
        r4 = r4.l();
        r0 = r27;
        r5 = r0.l;
        r4 = r4 + r5;
        r4 = (float) r4;
    L_0x0f97:
        r0 = r35;
        r5 = r0.a;
        r5 = r5.h();
        r5 = (float) r5;
        r4 = r4 + r5;
        r0 = r35;
        r0.f = r4;
        goto L_0x0f11;
    L_0x0fa7:
        r0 = r27;
        r4 = r0.m;
        r0 = r27;
        r5 = r0.k;
        r5 = (float) r5;
        r4 = r4 - r5;
        r0 = r27;
        r5 = r0.l;
        r5 = (float) r5;
        r4 = r4 + r5;
        goto L_0x0f97;
    L_0x0fb8:
        r4 = 0;
        goto L_0x0f69;
    L_0x0fba:
        r5 = r4.a;
        if (r4 != r5) goto L_0x0fc1;
    L_0x0fbe:
        r4 = r5;
        goto L_0x06f5;
    L_0x0fc1:
        if (r5 != 0) goto L_0x0fd3;
    L_0x0fc3:
        r4 = r5;
        goto L_0x06f5;
    L_0x0fc6:
        r5 = r6;
        goto L_0x0731;
    L_0x0fc9:
        r5 = r4;
        r4 = r6;
        goto L_0x0731;
    L_0x0fcd:
        r4 = r6;
        goto L_0x0765;
    L_0x0fd0:
        r4 = r5;
        goto L_0x0700;
    L_0x0fd3:
        r4 = r5;
        goto L_0x06f1;
    L_0x0fd6:
        r18 = r9;
        r19 = r17;
        r17 = r6;
        r9 = r4;
        r4 = r14;
        r6 = r15;
        r15 = r16;
        r14 = r5;
        r33 = r12;
        r12 = r11;
        r11 = r10;
        r10 = r7;
        r7 = r13;
        r13 = r33;
        goto L_0x05c6;
    L_0x0fec:
        r4 = r5;
        r5 = r6;
        r6 = r9;
        r9 = r17;
        r17 = r18;
        goto L_0x0df6;
    L_0x0ff5:
        r6 = r4;
        goto L_0x0e34;
    L_0x0ff8:
        r18 = r4;
        r4 = r23;
        goto L_0x0ca7;
    L_0x0ffe:
        r4 = r18;
        goto L_0x0cf4;
    L_0x1002:
        r4 = r24;
        r5 = r25;
        r9 = r26;
        goto L_0x0d99;
    L_0x100a:
        r4 = r9;
        r5 = r19;
        r9 = r17;
        r17 = r26;
        goto L_0x0df1;
    L_0x1013:
        r6 = r5;
        goto L_0x0c71;
    L_0x1016:
        r18 = r17;
        r17 = r9;
        r9 = r22;
        r22 = r19;
        r19 = r5;
        goto L_0x0e0d;
    L_0x1022:
        r19 = r6;
        r6 = r4;
        goto L_0x0c1b;
    L_0x1027:
        r4 = r22;
        r22 = r19;
        r19 = r6;
        r6 = r25;
        goto L_0x0be3;
    L_0x1031:
        r33 = r22;
        r22 = r19;
        r19 = r33;
        goto L_0x0bf8;
    L_0x1039:
        r22 = r6;
        r18 = r17;
        r17 = r9;
        r9 = r19;
        r19 = r5;
        goto L_0x0e0d;
    L_0x1045:
        r4 = r19;
        r19 = r5;
        r5 = r9;
        r9 = r17;
        goto L_0x0ea4;
    L_0x104e:
        r6 = r22;
        r33 = r11;
        r11 = r12;
        r12 = r13;
        r13 = r7;
        r7 = r10;
        r10 = r33;
        goto L_0x0668;
    L_0x105a:
        r4 = r7;
        goto L_0x093c;
    L_0x105d:
        r15 = r6;
        goto L_0x0650;
    L_0x1060:
        r16 = r15;
        goto L_0x0644;
    L_0x1064:
        r14 = r4;
        goto L_0x05f8;
    L_0x1067:
        r4 = r5;
        goto L_0x046f;
    L_0x106a:
        r4 = r6;
        goto L_0x042d;
    L_0x106d:
        r4 = r6;
        goto L_0x0429;
    L_0x1070:
        r4 = r6;
        goto L_0x03d6;
    L_0x1073:
        r4 = r7;
        goto L_0x0137;
    L_0x1076:
        r4 = r6;
        goto L_0x034c;
    L_0x1079:
        r15 = r6;
        r16 = r39;
        r17 = r38;
        r18 = r7;
        r19 = r9;
        r7 = r4;
        goto L_0x0160;
    L_0x1085:
        r30 = r4;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: format.epub.view.b.a(com.qq.reader.readengine.kernel.a.d, format.epub.view.s, int, int, int, format.epub.view.r, boolean, float):format.epub.view.r");
    }

    private void b(z zVar, int i, float f) {
        s j = zVar.j();
        if (j != null) {
            int f2 = j.f();
            this.c.g();
            this.c.a(j, 0, zVar.b());
            float f3 = 0.0f;
            float f4 = 0.0f;
            r rVar = null;
            while (!zVar.f() && f > 0.0f) {
                rVar = a(null, j, zVar.b(), zVar.c(), f2, rVar, false, f4);
                zVar.a(rVar.g, rVar.h);
                if (rVar.B != (byte) 0) {
                    f4 = rVar.m;
                    f3 = rVar.n;
                } else {
                    f -= a(rVar, i);
                    float f5 = f3 - rVar.n;
                    if (f5 <= 0.0f) {
                        f3 = 0.0f;
                        f4 = 0.0f;
                    } else {
                        f3 = f5;
                    }
                }
            }
        }
    }

    public z a(z zVar, int i, float f) {
        z zVar2 = new z(zVar);
        float a = f - a(zVar2, true, i);
        boolean z = !zVar2.e();
        zVar2.n();
        while (a >= 0.0f && ((!r0 || !zVar2.j().e()) && zVar2.m())) {
            if (!zVar2.j().e()) {
                z = true;
            }
            float a2 = a(zVar2, false, i);
            if (a == 0.0f) {
                if (a2 != 0.0f) {
                    zVar2.l();
                    break;
                }
                a -= a2;
            } else {
                a -= a2;
            }
        }
        b(zVar2, i, -a);
        if (i == 0) {
            z = zVar2.a(zVar);
            if (!z && zVar2.f() && zVar.e()) {
                zVar2.l();
                z = zVar2.a(zVar);
            }
            if (z) {
                zVar2.a(a(zVar, 1, 1.0f));
            }
        }
        return zVar2;
    }

    private float a(z zVar, boolean z, int i) {
        s j = zVar.j();
        if (j == null) {
            return 0.0f;
        }
        int b = z ? zVar.b() : j.f();
        this.c.g();
        float f = 0.0f;
        int i2 = 0;
        int i3 = 0;
        r rVar = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (i2 != b) {
            float f4;
            float f5;
            rVar = a(null, j, i2, i3, b, rVar, false, f2);
            i2 = rVar.g;
            i3 = rVar.h;
            if (rVar.B != (byte) 0) {
                f2 = rVar.m;
                f4 = rVar.n;
                f5 = f;
            } else {
                f5 = f + a(rVar, i);
                f4 = f3 - rVar.n;
                if (f4 <= 0.0f) {
                    f4 = 0.0f;
                    f2 = 0.0f;
                }
            }
            f3 = f4;
            f = f5;
        }
        return f;
    }

    private float a(r rVar, int i) {
        if (i == 0) {
            return rVar.n + rVar.r;
        }
        return (float) (rVar.i ? 1 : 0);
    }

    private int b() {
        return this.c.a.c();
    }

    private int c() {
        return this.c.a.b();
    }

    public void a(int i) {
        this.d = i;
    }

    public h a() {
        return this.c.f();
    }
}
