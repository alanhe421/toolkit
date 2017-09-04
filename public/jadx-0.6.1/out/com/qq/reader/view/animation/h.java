package com.qq.reader.view.animation;

import android.content.Context;
import android.graphics.Canvas;
import com.qq.reader.module.readpage.i;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.view.animation.AnimationProvider.Mode;
import com.qq.reader.view.animation.AnimationProvider.a;

/* compiled from: TtsAnimationProvider */
public class h extends AnimationProvider {
    private d p;

    public h(i iVar, Context context, a aVar) {
        super(iVar, context);
        this.p = new d(iVar, context, aVar);
    }

    public void b(int i, int i2) {
        this.p.b(i, i2);
    }

    public void a(int i, int i2, int i3, int i4, Mode mode, int i5) {
        this.p.a(i, i2, i3, i4, mode, i5);
    }

    public PageIndex a(float f, float f2) {
        return this.p.a(f, f2);
    }

    public void b(float f, float f2) {
        this.p.b(f, f2);
    }

    public boolean e() {
        return this.p.e();
    }

    public boolean f() {
        return this.p.f();
    }

    public boolean g() {
        return this.p.g();
    }

    public boolean a(Canvas canvas) {
        return this.p.a(canvas);
    }

    public int a(b bVar) {
        return this.p.a(bVar);
    }

    public int b(b bVar) {
        return this.p.b(bVar);
    }
}
