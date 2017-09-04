package com.qq.reader.widget.cloudtag;

import com.dynamicload.Lib.DLConstants;
import com.qq.reader.common.monitor.f;

/* compiled from: RotateUtil */
public class c {
    public static float[] a(float f, float[] fArr) {
        r2 = new float[4][];
        r2[0] = new float[]{(float) Math.cos((double) f), 0.0f, (float) (-Math.sin((double) f)), 0.0f};
        r2[1] = new float[]{0.0f, 1.0f, 0.0f, 0.0f};
        r2[2] = new float[]{(float) Math.sin((double) f), 0.0f, (float) Math.cos((double) f), 0.0f};
        r2[3] = new float[]{0.0f, 0.0f, 0.0f, 1.0f};
        float[] fArr2 = new float[]{fArr[0], fArr[1], fArr[2], fArr[3]};
        for (int i = 0; i < 4; i++) {
            fArr[i] = (((fArr2[0] * r2[0][i]) + (fArr2[1] * r2[1][i])) + (fArr2[2] * r2[2][i])) + (fArr2[3] * r2[3][i]);
        }
        return fArr;
    }

    public static float[] a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12) {
        f.d("tx,ty", f + DLConstants.DEPENDENCY_PACKAGE_DIV + f2);
        float f13 = (((f - (f3 / 2.0f)) * 2.0f) * f5) / f3;
        float f14 = ((((f4 / 2.0f) - f2) * 2.0f) * f6) / f4;
        f.d("kNear,pNear", f13 + DLConstants.DEPENDENCY_PACKAGE_DIV + f14);
        float f15 = f8 / f7;
        float f16 = f15 * f13;
        f15 *= f14;
        f.d("kFar,pFar", f16 + DLConstants.DEPENDENCY_PACKAGE_DIV + f15);
        f14 += 0.0f;
        float f17 = -f7;
        f16 += 0.0f;
        f15 += 0.0f;
        float f18 = -f8;
        float[] a = a((float) Math.toRadians((double) f9), new float[]{f13 + 0.0f, f14, f17, 1.0f});
        float[] a2 = a((float) Math.toRadians((double) f9), new float[]{f16, f15, f18, 1.0f});
        return new float[]{a[0] + f10, a[1] + f11, a[2] + f12, a2[0] + f10, a2[1] + f11, a2[2] + f12};
    }
}
