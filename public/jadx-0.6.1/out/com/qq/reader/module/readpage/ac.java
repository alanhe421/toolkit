package com.qq.reader.module.readpage;

import com.qq.reader.readengine.fileparse.c;
import com.qq.reader.readengine.fileparse.d;
import com.qq.reader.readengine.fileparse.f;

/* compiled from: TruePageCalForTxt */
public class ac {
    public long a = 0;
    public long b = -1;
    public int c = -1;
    public long d = 0;
    public float e = 0.0f;
    public int f = 0;

    public void a() {
        this.b = -2147483647L;
    }

    public void b() {
        this.e = -1.0f;
    }

    public void a(d dVar, boolean z, float f) {
        if (!(dVar instanceof f)) {
            float f2;
            c b = dVar.b();
            int k = b.k();
            this.c = b.k;
            int i = k / this.c;
            if (i == 0) {
                i = 1;
            }
            Object obj = (this.f != ((int) f) || this.e <= 0.0f || this.b < 0) ? 1 : null;
            this.f = (int) f;
            if (obj == null) {
                f2 = this.e;
            } else if (this.b != -2147483647L || this.e <= 0.0f) {
                String encodingStr = dVar.t().getEncodingStr();
                int[] iArr = new int[i];
                int[] iArr2 = new int[i];
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (i2 < i) {
                    int i5 = 0;
                    int i6 = 0;
                    try {
                        int i7 = this.c;
                        if (k / this.c < 1) {
                            i7 = k;
                        }
                        for (int i8 = 0; i8 < i7; i8++) {
                            String d = b.d((i2 * i7) + i8);
                            i5 += d.length();
                            i6 += d.getBytes(encodingStr).length;
                        }
                        iArr[i2] = i5;
                        iArr2[i2] = i6;
                        i2++;
                        i3 += i6;
                        i4 = i5 + i4;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                f2 = (float) (i3 / i);
                if (i <= 1 && this.e > 0.0f) {
                    f2 = this.e;
                }
                this.e = -1.0f;
                this.b = -1;
            } else {
                f2 = this.e;
            }
            this.a = (long) ((int) Math.ceil((double) (((float) dVar.a((long) f2)) / f2)));
            if (obj != null) {
                this.e = f2;
                if (dVar.b(b)) {
                    this.b = 0;
                    this.d = (long) i;
                } else if (dVar.b(b, 0)) {
                    if (k == 0) {
                        this.b = this.a;
                    } else if (k <= this.c) {
                        this.b = this.a - 1;
                    } else {
                        this.b = (this.a - ((long) i)) - ((long) (k % this.c > 0 ? 1 : 0));
                    }
                    this.d = this.a;
                } else {
                    this.b = (long) ((int) (((float) ((long) ((int) dVar.p().e()))) / this.e));
                    this.d = ((long) i) + this.b;
                }
            } else if (z) {
                if (this.b < this.d) {
                    this.b = this.d;
                } else {
                    this.d = this.b;
                }
                if (dVar.b(b, 0)) {
                    this.a = ((long) i) + this.d;
                    if (k % this.c > 0) {
                        this.a++;
                    }
                    this.e = ((float) dVar.a(-1)) / ((float) this.a);
                    return;
                }
                this.d = ((long) i) + this.d;
            } else if (dVar.b(b)) {
                this.b = 0;
                this.d = (long) i;
            } else {
                this.d = this.b;
                this.b -= (long) i;
            }
        }
    }
}
