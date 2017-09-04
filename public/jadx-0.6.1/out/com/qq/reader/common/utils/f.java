package com.qq.reader.common.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;

/* compiled from: BitmapFactoryUtil */
public class f {
    public static Bitmap a(ArrayList<Drawable> arrayList, int i, int i2, int i3, int i4) {
        if (arrayList == null || arrayList.isEmpty() || i2 == 0 || i == 0 || i4 == 0 || i3 == 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        int i5 = (i4 / i2) + 1;
        int i6 = (i3 / i) + 1;
        for (int i7 = 0; i7 < i5; i7++) {
            for (int i8 = 0; i8 < i6; i8++) {
                Drawable drawable = (Drawable) arrayList.get(((i7 * i6) + i8) % arrayList.size());
                if (i7 % 2 == 1) {
                    drawable.setBounds(i3 - ((i8 + 1) * i), i2 * i7, i3 - (i * i8), (i7 + 1) * i2);
                } else {
                    drawable.setBounds(i * i8, i2 * i7, (i8 + 1) * i, (i7 + 1) * i2);
                }
                drawable.draw(canvas);
            }
        }
        return createBitmap;
    }
}
