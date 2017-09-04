package format.epub.b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import com.bumptech.glide.g;
import com.qq.reader.ReaderApplication;
import format.epub.common.image.c;
import format.epub.paint.ZLPaintContext.ScalingType;

/* compiled from: ZLAndroidImageData */
public abstract class b implements c {
    private Bitmap a;
    private int b;
    private int c;
    private int d = -1;
    private int e = -1;
    private ScalingType f = ScalingType.OriginalSize;

    protected abstract Bitmap a(Options options);

    protected b() {
    }

    public Bitmap a() {
        return a(0, 0, ScalingType.OriginalSize);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int[] b(int r7, int r8, format.epub.paint.ZLPaintContext.ScalingType r9) {
        /*
        r6 = this;
        r0 = 1;
        monitor-enter(r6);
        r1 = format.epub.paint.ZLPaintContext.ScalingType.OriginalSize;	 Catch:{ all -> 0x011c }
        if (r9 == r1) goto L_0x000d;
    L_0x0006:
        if (r7 == 0) goto L_0x000a;
    L_0x0008:
        if (r8 != 0) goto L_0x000d;
    L_0x000a:
        r0 = 0;
    L_0x000b:
        monitor-exit(r6);
        return r0;
    L_0x000d:
        r1 = 2;
        r1 = new int[r1];	 Catch:{ all -> 0x011c }
        r3 = new android.graphics.BitmapFactory$Options;	 Catch:{ OutOfMemoryError -> 0x010d }
        r3.<init>();	 Catch:{ OutOfMemoryError -> 0x010d }
        r2 = r6.b;	 Catch:{ OutOfMemoryError -> 0x010d }
        if (r2 > 0) goto L_0x0027;
    L_0x0019:
        r2 = 1;
        r3.inJustDecodeBounds = r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        r6.a(r3);	 Catch:{ OutOfMemoryError -> 0x010d }
        r2 = r3.outWidth;	 Catch:{ OutOfMemoryError -> 0x010d }
        r6.b = r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        r2 = r3.outHeight;	 Catch:{ OutOfMemoryError -> 0x010d }
        r6.c = r2;	 Catch:{ OutOfMemoryError -> 0x010d }
    L_0x0027:
        r2 = 0;
        r3.inJustDecodeBounds = r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        r2 = format.epub.paint.ZLPaintContext.ScalingType.IntegerCoefficient;	 Catch:{ OutOfMemoryError -> 0x010d }
        if (r9 != r2) goto L_0x012b;
    L_0x002e:
        r2 = r0;
    L_0x002f:
        r4 = r6.c;	 Catch:{ OutOfMemoryError -> 0x010d }
        r5 = r8 * r2;
        if (r4 > r5) goto L_0x003b;
    L_0x0035:
        r4 = r6.b;	 Catch:{ OutOfMemoryError -> 0x010d }
        r5 = r7 * r2;
        if (r4 <= r5) goto L_0x003e;
    L_0x003b:
        r2 = r2 + 1;
        goto L_0x002f;
    L_0x003e:
        r2 = r2 + -1;
    L_0x0040:
        r3.inSampleSize = r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        if (r2 > 0) goto L_0x0128;
    L_0x0044:
        r2 = r6.b;	 Catch:{ OutOfMemoryError -> 0x010d }
        r3 = r2 / r0;
        r2 = r6.c;	 Catch:{ OutOfMemoryError -> 0x010d }
        r2 = r2 / r0;
        r0 = format.epub.b.b.1.a;	 Catch:{ OutOfMemoryError -> 0x010d }
        r4 = r9.ordinal();	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = r0[r4];	 Catch:{ OutOfMemoryError -> 0x010d }
        switch(r0) {
            case 1: goto L_0x0060;
            case 2: goto L_0x0063;
            case 3: goto L_0x006e;
            case 4: goto L_0x0079;
            case 5: goto L_0x007e;
            case 6: goto L_0x00a6;
            case 7: goto L_0x00cc;
            default: goto L_0x0056;
        };	 Catch:{ OutOfMemoryError -> 0x010d }
    L_0x0056:
        r8 = r2;
        r7 = r3;
    L_0x0058:
        r0 = 0;
        r1[r0] = r7;	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = 1;
        r1[r0] = r8;	 Catch:{ OutOfMemoryError -> 0x010d }
    L_0x005e:
        r0 = r1;
        goto L_0x000b;
    L_0x0060:
        r8 = r2;
        r7 = r3;
        goto L_0x0058;
    L_0x0063:
        if (r3 <= 0) goto L_0x0056;
    L_0x0065:
        if (r2 <= 0) goto L_0x0056;
    L_0x0067:
        r0 = (float) r7;	 Catch:{ OutOfMemoryError -> 0x010d }
        r3 = (float) r3;	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = r0 / r3;
        r2 = (float) r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = r0 * r2;
        r8 = (int) r0;	 Catch:{ OutOfMemoryError -> 0x010d }
        goto L_0x0058;
    L_0x006e:
        if (r3 <= 0) goto L_0x0056;
    L_0x0070:
        if (r2 <= 0) goto L_0x0056;
    L_0x0072:
        r0 = (float) r8;	 Catch:{ OutOfMemoryError -> 0x010d }
        r2 = (float) r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = r0 / r2;
        r2 = (float) r3;	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = r0 * r2;
        r7 = (int) r0;	 Catch:{ OutOfMemoryError -> 0x010d }
        goto L_0x0058;
    L_0x0079:
        if (r3 <= 0) goto L_0x0056;
    L_0x007b:
        if (r2 <= 0) goto L_0x0056;
    L_0x007d:
        goto L_0x0058;
    L_0x007e:
        if (r3 <= 0) goto L_0x0056;
    L_0x0080:
        if (r2 <= 0) goto L_0x0056;
    L_0x0082:
        r0 = com.qq.reader.common.utils.ao.l();	 Catch:{ OutOfMemoryError -> 0x010d }
        if (r0 != 0) goto L_0x0097;
    L_0x0088:
        r0 = (float) r3;	 Catch:{ OutOfMemoryError -> 0x010d }
        r4 = (float) r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = a(r7, r0, r4);	 Catch:{ OutOfMemoryError -> 0x010d }
        if (r0 <= r8) goto L_0x0125;
    L_0x0090:
        r0 = (float) r3;	 Catch:{ OutOfMemoryError -> 0x010d }
        r2 = (float) r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        r7 = b(r8, r0, r2);	 Catch:{ OutOfMemoryError -> 0x010d }
        goto L_0x0058;
    L_0x0097:
        r0 = (float) r3;	 Catch:{ OutOfMemoryError -> 0x010d }
        r4 = (float) r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = b(r8, r0, r4);	 Catch:{ OutOfMemoryError -> 0x010d }
        if (r0 <= r7) goto L_0x011f;
    L_0x009f:
        r0 = (float) r3;	 Catch:{ OutOfMemoryError -> 0x010d }
        r2 = (float) r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        r8 = a(r7, r0, r2);	 Catch:{ OutOfMemoryError -> 0x010d }
        goto L_0x0058;
    L_0x00a6:
        if (r3 <= 0) goto L_0x0056;
    L_0x00a8:
        if (r2 <= 0) goto L_0x0056;
    L_0x00aa:
        if (r3 == r7) goto L_0x0056;
    L_0x00ac:
        if (r2 == r8) goto L_0x0056;
    L_0x00ae:
        if (r3 > r7) goto L_0x00b5;
    L_0x00b0:
        if (r2 > r8) goto L_0x00b5;
    L_0x00b2:
        r8 = r2;
        r7 = r3;
        goto L_0x0058;
    L_0x00b5:
        r0 = r3 * r8;
        r4 = r2 * r7;
        if (r0 <= r4) goto L_0x00c3;
    L_0x00bb:
        r0 = 1;
        r2 = r2 * r7;
        r2 = r2 / r3;
        r8 = java.lang.Math.max(r0, r2);	 Catch:{ OutOfMemoryError -> 0x010d }
        goto L_0x0058;
    L_0x00c3:
        r0 = 1;
        r3 = r3 * r8;
        r2 = r3 / r2;
        r7 = java.lang.Math.max(r0, r2);	 Catch:{ OutOfMemoryError -> 0x010d }
        goto L_0x0058;
    L_0x00cc:
        if (r3 <= 0) goto L_0x0122;
    L_0x00ce:
        if (r2 <= 0) goto L_0x0122;
    L_0x00d0:
        if (r3 > r7) goto L_0x00d4;
    L_0x00d2:
        if (r2 <= r8) goto L_0x0122;
    L_0x00d4:
        r0 = r3 * r8;
        r4 = r2 * r7;
        if (r0 <= r4) goto L_0x0104;
    L_0x00da:
        r0 = 1;
        r2 = r2 * r7;
        r2 = r2 / r3;
        r8 = java.lang.Math.max(r0, r2);	 Catch:{ OutOfMemoryError -> 0x010d }
    L_0x00e1:
        r0 = r7;
    L_0x00e2:
        r2 = new android.text.TextPaint;	 Catch:{ OutOfMemoryError -> 0x010d }
        r2.<init>();	 Catch:{ OutOfMemoryError -> 0x010d }
        r3 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ OutOfMemoryError -> 0x010d }
        r3 = com.qq.reader.appconfig.a.d.I(r3);	 Catch:{ OutOfMemoryError -> 0x010d }
        r2.setTextSize(r3);	 Catch:{ OutOfMemoryError -> 0x010d }
        r3 = 20013; // 0x4e2d float:2.8044E-41 double:9.8877E-320;
        r2 = com.qq.reader.common.utils.ao.b(r3, r2);	 Catch:{ OutOfMemoryError -> 0x010d }
        r7 = (int) r2;	 Catch:{ OutOfMemoryError -> 0x010d }
        if (r0 >= r7) goto L_0x011f;
    L_0x00fb:
        r2 = (float) r7;	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = (float) r0;	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = r2 / r0;
        r2 = (float) r8;	 Catch:{ OutOfMemoryError -> 0x010d }
        r0 = r0 * r2;
        r8 = (int) r0;	 Catch:{ OutOfMemoryError -> 0x010d }
        goto L_0x0058;
    L_0x0104:
        r0 = 1;
        r3 = r3 * r8;
        r2 = r3 / r2;
        r7 = java.lang.Math.max(r0, r2);	 Catch:{ OutOfMemoryError -> 0x010d }
        goto L_0x00e1;
    L_0x010d:
        r0 = move-exception;
        java.lang.System.gc();	 Catch:{ all -> 0x011c }
        r2 = "OutOfMemoryError";
        r3 = "";
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x011c }
        goto L_0x005e;
    L_0x011c:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x011f:
        r7 = r0;
        goto L_0x0058;
    L_0x0122:
        r8 = r2;
        r0 = r3;
        goto L_0x00e2;
    L_0x0125:
        r8 = r0;
        goto L_0x0058;
    L_0x0128:
        r0 = r2;
        goto L_0x0044;
    L_0x012b:
        r2 = r0;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: format.epub.b.b.b(int, int, format.epub.paint.ZLPaintContext$ScalingType):int[]");
    }

    private static int a(int i, float f, float f2) {
        return (int) ((((float) i) / f) * f2);
    }

    private static int b(int i, float f, float f2) {
        return (int) ((((float) i) / f2) * f);
    }

    public synchronized Bitmap a(int i, int i2, ScalingType scalingType) {
        return a(i, i2, scalingType, false);
    }

    private Bitmap a(int i, int i2, ScalingType scalingType, boolean z) {
        if (scalingType != ScalingType.OriginalSize && (i == 0 || i2 == 0)) {
            return null;
        }
        if (!(i == this.d && i2 == this.e && scalingType == this.f)) {
            if (this.a != null) {
                this.a.recycle();
                this.a = null;
            }
            try {
                Options options = new Options();
                options.outWidth = i;
                options.outHeight = i2;
                options.inInputShareable = true;
                options.inPurgeable = true;
                this.a = a(options);
                if (this.a != null) {
                    this.d = i;
                    this.e = i2;
                    this.f = scalingType;
                }
            } catch (Throwable e) {
                if (z) {
                    Log.e("OutOfMemoryError", "", e);
                } else {
                    c.b().c();
                    System.gc();
                    a(i, i2, scalingType, true);
                }
            }
        }
        return this.a;
    }

    public Bitmap b() {
        return this.a;
    }

    public void c() {
        if (this.a != null && !this.a.isRecycled()) {
            if (!g.a(ReaderApplication.getApplicationImp()).a().a(this.a)) {
                this.a.recycle();
            }
            this.a = null;
            this.d = -1;
            this.e = -1;
            this.f = ScalingType.OriginalSize;
        }
    }

    public static void d() {
    }
}
