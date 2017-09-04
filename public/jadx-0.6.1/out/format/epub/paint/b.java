package format.epub.paint;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import format.epub.common.c.a.g;
import format.epub.common.text.model.n;
import format.epub.common.utils.a;
import format.epub.common.utils.h;
import format.epub.common.utils.k;
import format.epub.paint.ZLPaintContext.ScalingType;
import format.epub.view.p;
import format.epub.view.style.c;
import format.epub.view.v;
import java.io.File;
import java.nio.CharBuffer;
import java.util.HashMap;

/* compiled from: ZLAndroidPaintContext */
public final class b extends ZLPaintContext {
    private final Paint a = new Paint();
    private final Paint b = new Paint();
    private final Paint c = new Paint();
    private final Paint d = new Paint();
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;
    private final int k;
    private HashMap<String, Typeface[]> l = new HashMap();
    private Xfermode m = new PorterDuffXfermode(Mode.DARKEN);
    private float n;
    private CharBuffer o = null;
    private Drawable p;

    public b(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.e = i - i3;
        this.f = i2;
        this.g = i4;
        this.h = i5;
        this.i = i3;
        this.j = i6;
        this.k = i7;
        this.a.setLinearText(false);
        this.a.setAntiAlias(true);
        this.a.setSubpixelText(false);
        this.d.setColor(Color.rgb(255, Opcodes.NEG_FLOAT, 0));
        this.d.setAntiAlias(true);
        this.d.setDither(true);
        this.d.setStrokeWidth(4.0f);
        this.d.setStyle(Style.STROKE);
        this.d.setPathEffect(new CornerPathEffect(5.0f));
        this.d.setMaskFilter(new EmbossMaskFilter(new float[]{1.0f, 1.0f, 1.0f}, 0.4f, 6.0f, 3.5f));
        this.c.setFilterBitmap(true);
    }

    public void a(boolean z) {
        if (!z) {
            this.c.setXfermode(null);
        }
    }

    public boolean a() {
        return this.c.getXfermode() != null;
    }

    public void a(k kVar, Canvas canvas) {
        this.c.setColor(h.a(kVar));
        canvas.drawRect(0.0f, 0.0f, (float) this.g, (float) this.h, this.c);
    }

    protected void a(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, format.epub.common.c.a.g.b bVar) {
        Typeface typeface = null;
        for (String a : str.split(",")) {
            int i2;
            int i3;
            String a2 = a(a);
            if (z) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (z2) {
                i3 = 2;
            } else {
                i3 = 0;
            }
            int i4 = i2 | i3;
            Typeface[] typefaceArr = (Typeface[]) this.l.get(a2);
            if (typefaceArr == null) {
                typefaceArr = new Typeface[4];
                this.l.put(a2, typefaceArr);
            }
            Typeface[] typefaceArr2 = typefaceArr;
            Typeface typeface2 = typefaceArr2[i4];
            if (typeface2 == null) {
                File file;
                File k;
                File[] fileArr = (File[]) a.a(false).get(a2);
                if (fileArr != null) {
                    try {
                        if (fileArr[i4] != null) {
                            typeface = a.a(fileArr[i4]);
                        } else {
                            int i5 = 0;
                            while (i5 < 4) {
                                if (fileArr[i5] != null) {
                                    typeface = typefaceArr2[i5] != null ? typefaceArr2[i5] : a.a(fileArr[i5]);
                                    try {
                                        typefaceArr2[i5] = typeface;
                                    } catch (Throwable th) {
                                        typeface2 = typeface;
                                        typeface = typeface2;
                                        if (typeface == null) {
                                            file = new File(ao.m(a2));
                                            k = ao.k();
                                            if (file.exists()) {
                                                typeface = Typeface.createFromFile(file);
                                            } else {
                                                if (k == null) {
                                                }
                                                typeface = Typeface.create(a2, i4);
                                            }
                                        }
                                        typefaceArr2[i4] = typeface;
                                        if (typeface == null) {
                                        } else {
                                            this.a.setTypeface(typeface);
                                            this.a.setTextSize((float) i);
                                            this.a.setUnderlineText(z3);
                                            this.a.setStrikeThruText(z4);
                                            this.a.setFakeBoldText(false);
                                            if (z) {
                                                this.a.setFakeBoldText(true);
                                            }
                                            if (bVar == null) {
                                                this.a.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
                                            } else {
                                                this.a.setShadowLayer((float) bVar.j, (float) bVar.h, (float) bVar.i, bVar.k);
                                            }
                                            this.n = this.a.measureText("中");
                                        }
                                    }
                                }
                                i5++;
                            }
                        }
                    } catch (Throwable th2) {
                    }
                    if (typeface == null) {
                        file = new File(ao.m(a2));
                        k = ao.k();
                        if (file.exists()) {
                            typeface = Typeface.createFromFile(file);
                        } else if (k == null && k.exists()) {
                            typeface = Typeface.createFromFile(k);
                        } else {
                            typeface = Typeface.create(a2, i4);
                        }
                    }
                    typefaceArr2[i4] = typeface;
                }
                typeface = typeface2;
                if (typeface == null) {
                    file = new File(ao.m(a2));
                    k = ao.k();
                    if (file.exists()) {
                        if (k == null) {
                        }
                        typeface = Typeface.create(a2, i4);
                    } else {
                        typeface = Typeface.createFromFile(file);
                    }
                }
                typefaceArr2[i4] = typeface;
            } else {
                typeface = typeface2;
            }
            if (typeface == null) {
                break;
            }
        }
        this.a.setTypeface(typeface);
        this.a.setTextSize((float) i);
        this.a.setUnderlineText(z3);
        this.a.setStrikeThruText(z4);
        this.a.setFakeBoldText(false);
        if (z) {
            this.a.setFakeBoldText(true);
        }
        if (bVar == null) {
            this.a.setShadowLayer((float) bVar.j, (float) bVar.h, (float) bVar.i, bVar.k);
        } else {
            this.a.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        }
        this.n = this.a.measureText("中");
    }

    public void a(k kVar) {
        this.a.setColor(h.a(kVar));
    }

    public void a(k kVar, int i) {
        this.c.setColor(h.a(kVar));
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public float a(char[] cArr, int i, int i2) {
        if (i2 == 1) {
            char c = cArr[i];
            if (c > 'ÿ' && c != '“' && c != '”' && c != '‘' && c != '’' && c != '…') {
                return this.n;
            }
            return this.a.measureText(new char[]{c}, 0, 1);
        } else if (!com.qq.reader.common.c.a.x) {
            return this.a.measureText(cArr, i, i2);
        } else {
            if (this.o == null) {
                this.o = CharBuffer.allocate(1);
            }
            if (i2 > this.o.capacity()) {
                this.o = CharBuffer.allocate(i2);
            }
            this.o.position(0);
            this.o.limit(this.o.capacity());
            this.o.put(cArr, i, i2);
            this.o.flip();
            return this.a.measureText(this.o, 0, i2);
        }
    }

    protected float d() {
        return this.a.measureText(" ", 0, 1);
    }

    protected float e() {
        return com.qq.reader.readengine.a.a.a(this.a);
    }

    protected float f() {
        return this.a.descent() - this.a.ascent();
    }

    protected float g() {
        return this.a.descent();
    }

    public void a(float f, float f2, char[] cArr, int i, int i2, Canvas canvas) {
        if (com.qq.reader.common.c.a.z) {
            canvas.drawText(new String(cArr, i, i2), f, f2, this.a);
            return;
        }
        canvas.drawText(cArr, i, i2, f, f2, this.a);
    }

    public int a(p pVar, format.epub.common.text.model.h hVar, ScalingType scalingType, v vVar) {
        int a;
        Exception exception;
        int[] b;
        int i = this.e;
        int i2 = this.f;
        if (scalingType == ScalingType.FULLSCREEN) {
            i2 = this.g;
            i = i2;
            i2 = this.h;
        } else if (scalingType == ScalingType.SCALEWIDTH) {
            String str = pVar.n;
            if (str != null) {
                if (pVar.r != 0) {
                    i = pVar.r;
                } else {
                    try {
                        short[] sArr = new short[1];
                        byte[] bArr = new byte[1];
                        g.a(str, sArr, bArr);
                        n.a aVar = new n.a(sArr[0], bArr[0]);
                        int i3 = hVar.b;
                        v vVar2 = vVar.a;
                        if ((vVar instanceof c) && ((c) vVar).H().a(11)) {
                            i = 1;
                        } else {
                            i = 0;
                        }
                        if (i == 0 || vVar2.l() == 0) {
                            hVar.b = i3;
                        } else {
                            hVar.b = vVar2.l();
                        }
                        i = n.a(aVar, hVar, vVar.a(hVar), 11);
                        hVar.b = i3;
                    } catch (Exception e) {
                        e.printStackTrace();
                        i = (int) Double.parseDouble(str);
                    }
                    pVar.r = i;
                }
            }
            String str2 = pVar.o;
            if (!TextUtils.isEmpty(str2)) {
                if (pVar.s != 0) {
                    i2 = pVar.s;
                    if (str != null) {
                        scalingType = ScalingType.SCALEWH;
                    } else {
                        scalingType = ScalingType.SCALEHEIGHT;
                    }
                } else {
                    try {
                        short[] sArr2 = new short[1];
                        byte[] bArr2 = new byte[1];
                        g.a(str2, sArr2, bArr2);
                        a = n.a(new n.a(sArr2[0], bArr2[0]), hVar, vVar.a(hVar), 5);
                        try {
                            ScalingType scalingType2;
                            pVar.s = a;
                            if (str != null) {
                                scalingType2 = ScalingType.SCALEWH;
                            } else {
                                scalingType2 = ScalingType.SCALEHEIGHT;
                            }
                            scalingType = scalingType2;
                            i2 = a;
                        } catch (Exception e2) {
                            Exception exception2 = e2;
                            i2 = a;
                            exception = exception2;
                            exception.printStackTrace();
                            if (pVar.b()) {
                            }
                            a = i;
                            b = ((format.epub.b.b) pVar.b).b(a, i2, scalingType);
                            if (b != null) {
                                return b[0];
                            }
                            return 0;
                        }
                    } catch (Exception e3) {
                        exception = e3;
                        exception.printStackTrace();
                        if (pVar.b()) {
                        }
                        a = i;
                        b = ((format.epub.b.b) pVar.b).b(a, i2, scalingType);
                        if (b != null) {
                            return 0;
                        }
                        return b[0];
                    }
                }
            }
        }
        if (pVar.b() || !pVar.c()) {
            a = i;
        } else {
            a = this.g;
        }
        b = ((format.epub.b.b) pVar.b).b(a, i2, scalingType);
        if (b != null) {
            return b[0];
        }
        return 0;
    }

    public int a(p pVar, format.epub.common.text.model.h hVar, ScalingType scalingType) {
        int a;
        Exception exception;
        int[] b;
        int i = this.e;
        int i2 = this.f;
        if (scalingType == ScalingType.FULLSCREEN) {
            i = this.g;
            i2 = this.h;
        } else if (scalingType == ScalingType.SCALEWIDTH) {
            String str = pVar.n;
            if (str != null) {
                if (pVar.r != 0) {
                    i = pVar.r;
                } else {
                    try {
                        short[] sArr = new short[1];
                        byte[] bArr = new byte[1];
                        g.a(str, sArr, bArr);
                        i = n.a(new n.a(sArr[0], bArr[0]), hVar, hVar.d, 11);
                    } catch (Exception e) {
                        e.printStackTrace();
                        i = (int) Double.parseDouble(str);
                    }
                }
            }
            String str2 = pVar.o;
            if (!TextUtils.isEmpty(str2)) {
                if (pVar.s != 0) {
                    i2 = pVar.s;
                    if (str != null) {
                        scalingType = ScalingType.SCALEWH;
                    } else {
                        scalingType = ScalingType.SCALEHEIGHT;
                    }
                } else {
                    try {
                        short[] sArr2 = new short[1];
                        byte[] bArr2 = new byte[1];
                        g.a(str2, sArr2, bArr2);
                        a = n.a(new n.a(sArr2[0], bArr2[0]), hVar, hVar.d, 5);
                        try {
                            ScalingType scalingType2;
                            pVar.s = a;
                            if (str != null) {
                                scalingType2 = ScalingType.SCALEWH;
                            } else {
                                scalingType2 = ScalingType.SCALEHEIGHT;
                            }
                            scalingType = scalingType2;
                            i2 = a;
                        } catch (Exception e2) {
                            Exception exception2 = e2;
                            i2 = a;
                            exception = exception2;
                            exception.printStackTrace();
                            if (pVar.b()) {
                            }
                            a = i;
                            b = ((format.epub.b.b) pVar.b).b(a, i2, scalingType);
                            return b != null ? b[1] : 0;
                        }
                    } catch (Exception e3) {
                        exception = e3;
                        exception.printStackTrace();
                        if (pVar.b()) {
                        }
                        a = i;
                        b = ((format.epub.b.b) pVar.b).b(a, i2, scalingType);
                        if (b != null) {
                        }
                    }
                }
            }
        }
        if (pVar.b() || !pVar.c()) {
            a = i;
        } else {
            a = this.g;
        }
        b = ((format.epub.b.b) pVar.b).b(a, i2, scalingType);
        if (b != null) {
        }
    }

    public void a(float f, float f2, p pVar, format.epub.common.text.model.h hVar, ScalingType scalingType, Canvas canvas) {
        ScalingType scalingType2;
        int i;
        int i2 = this.e;
        int i3 = this.f;
        if (scalingType == ScalingType.FULLSCREEN) {
            i2 = this.g;
            i3 = this.h;
            scalingType2 = scalingType;
        } else {
            if (scalingType == ScalingType.SCALEWIDTH) {
                String str = pVar.n;
                if (str != null) {
                    if (pVar.r != 0) {
                        i2 = pVar.r;
                    } else {
                        try {
                            short[] sArr = new short[1];
                            byte[] bArr = new byte[1];
                            g.a(str, sArr, bArr);
                            i2 = n.a(new n.a(sArr[0], bArr[0]), hVar, hVar.d, 11);
                        } catch (Exception e) {
                            e.printStackTrace();
                            i2 = (int) Double.parseDouble(str);
                        }
                    }
                }
                String str2 = pVar.o;
                if (!TextUtils.isEmpty(str2)) {
                    if (pVar.s != 0) {
                        i3 = pVar.s;
                        if (str != null) {
                            scalingType2 = ScalingType.SCALEWH;
                        } else {
                            scalingType2 = ScalingType.SCALEHEIGHT;
                        }
                    } else {
                        try {
                            ScalingType scalingType3;
                            short[] sArr2 = new short[1];
                            byte[] bArr2 = new byte[1];
                            g.a(str2, sArr2, bArr2);
                            i3 = n.a(new n.a(sArr2[0], bArr2[0]), hVar, hVar.d, 5);
                            pVar.s = i3;
                            if (str != null) {
                                scalingType3 = ScalingType.SCALEWH;
                            } else {
                                scalingType3 = ScalingType.SCALEHEIGHT;
                            }
                            scalingType2 = scalingType3;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            scalingType2 = scalingType;
        }
        if (pVar.b() && pVar.c()) {
            i = this.g;
        } else {
            i = i2;
        }
        Bitmap a = ((format.epub.b.b) pVar.b).a(i, i3, scalingType2);
        if (a != null && !a.isRecycled()) {
            Bitmap createScaledBitmap;
            Rect a2;
            Rect rect;
            if (pVar.l.trim().length() > 0) {
                TextPaint textPaint = new TextPaint();
                textPaint.setTextSize(d.I(ReaderApplication.getApplicationImp()));
                int b = (int) ao.b('中', textPaint);
                int width = a.getWidth();
                i2 = a.getHeight();
                if (width > b) {
                    createScaledBitmap = Bitmap.createScaledBitmap(a, b, (int) (((float) i2) * (((float) b) / ((float) width))), true);
                    if (createScaledBitmap != null) {
                        f += (float) ((width - b) / 2);
                        a2 = a.a(createScaledBitmap, i, i3, (int) f, (int) f2, scalingType2);
                        if (pVar.k) {
                            canvas.drawBitmap(createScaledBitmap, null, a2, this.c);
                            return;
                        }
                        canvas.drawBitmap(createScaledBitmap, null, a2, this.c);
                        if (this.p == null) {
                            this.p = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.img_border);
                        }
                        rect = new Rect(a2);
                        rect.left -= ao.a(5.0f);
                        rect.top -= ao.a(4.0f);
                        rect.right += ao.a(5.0f);
                        rect.bottom += ao.a(7.0f);
                        this.p.setBounds(rect);
                        this.p.draw(canvas);
                    }
                }
            }
            createScaledBitmap = a;
            a2 = a.a(createScaledBitmap, i, i3, (int) f, (int) f2, scalingType2);
            if (pVar.k) {
                canvas.drawBitmap(createScaledBitmap, null, a2, this.c);
                return;
            }
            canvas.drawBitmap(createScaledBitmap, null, a2, this.c);
            if (this.p == null) {
                this.p = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.img_border);
            }
            rect = new Rect(a2);
            rect.left -= ao.a(5.0f);
            rect.top -= ao.a(4.0f);
            rect.right += ao.a(5.0f);
            rect.bottom += ao.a(7.0f);
            this.p.setBounds(rect);
            this.p.draw(canvas);
        }
    }

    public void a(float f, float f2, float f3, float f4, Canvas canvas) {
        float f5;
        float f6;
        if (f3 < f) {
            f5 = f3;
        } else {
            f5 = f;
            f = f3;
        }
        if (f4 < f2) {
            f6 = f4;
        } else {
            f6 = f2;
            f2 = f4;
        }
        canvas.drawRect(f5, f6, f + 1.0f, 1.0f + f2, this.c);
    }

    public String a(String str) {
        return a.a(str);
    }

    public void h() {
        this.l.clear();
    }

    public int i() {
        return this.g;
    }

    public int j() {
        return this.h;
    }

    public int k() {
        return this.j;
    }

    public int l() {
        return this.k;
    }

    protected float m() {
        return this.a.ascent();
    }
}
