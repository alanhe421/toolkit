package com.qq.reader.plugin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.TypedValue;
import com.tencent.theme.SkinEngine;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

/* compiled from: SkinResUtil */
public class z {
    public static int[] a;

    static {
        a = new int[0];
        int length = q.a.length;
        int length2 = r.a.length;
        int length3 = t.a.length;
        int length4 = s.a.length;
        a = Arrays.copyOf(a, ((length + length2) + length3) + length4);
        System.arraycopy(q.a, 0, a, 0, length);
        length += 0;
        System.arraycopy(r.a, 0, a, length, length2);
        length += length2;
        System.arraycopy(t.a, 0, a, length, length3);
        length += length3;
        System.arraycopy(s.a, 0, a, length, length4);
        length += length4;
    }

    private static Bitmap a(Bitmap bitmap, int i) {
        float f = (float) (i & 255);
        float f2 = (float) ((i >> 8) & 255);
        float f3 = (float) ((i >> 16) & 255);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap);
        bitmap.eraseColor(0);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{0.0f, 0.0f, 0.0f, 0.0f, f3, 0.0f, 0.0f, 0.0f, 0.0f, f2, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
        createBitmap.recycle();
        return bitmap;
    }

    public static void a(String str, Context context, int i, int i2) {
        int i3 = 0;
        if (i > 0) {
            int[] iArr = null;
            switch (i2) {
                case 1:
                    iArr = q.a;
                    break;
                case 2:
                    iArr = r.a;
                    break;
                case 3:
                    iArr = t.a;
                    break;
            }
            String[] densityPathArray = SkinEngine.getInstances().getDensityPathArray();
            int[] standardDensityArray = SkinEngine.getInstances().getStandardDensityArray();
            if (iArr != null && iArr.length > 0 && densityPathArray != null && densityPathArray.length > 0) {
                String str2 = densityPathArray[0];
                int i4 = standardDensityArray[0];
                try {
                    Resources resources = context.getResources();
                    while (i3 < iArr.length) {
                        TypedValue typedValue = new TypedValue();
                        resources.getValue(iArr[i3], typedValue, true);
                        String charSequence = typedValue.string.toString();
                        String str3 = str + "/";
                        charSequence = charSequence.substring(charSequence.lastIndexOf("/") + 1);
                        str3 = str3 + str2;
                        File file = new File(str3);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        file = new File(str3, charSequence);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        Options options = new Options();
                        options.inMutable = true;
                        options.inTargetDensity = i4;
                        Bitmap a = a(BitmapFactory.decodeResource(resources, iArr[i3], options), i);
                        OutputStream fileOutputStream = new FileOutputStream(file, false);
                        a.compress(CompressFormat.PNG, 100, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        if (!(a == null || a.isRecycled())) {
                            a.recycle();
                        }
                        i3++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
