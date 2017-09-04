package com.qq.reader.common.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.tencent.feedback.proguard.R;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: QRUtil */
public class x {
    public static boolean a(int i, String str, int i2, int i3, Bitmap bitmap, String str2, int i4) {
        if (str == null) {
            return false;
        }
        try {
            if ("".equals(str)) {
                return false;
            }
            int i5;
            if (i == R.id.bg_color3) {
                i4 = -5006449;
                i5 = -15263718;
            } else {
                i5 = -6724021;
            }
            Map hashMap = new HashMap();
            hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hashMap.put(EncodeHintType.MARGIN, Integer.valueOf(0));
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i2, i3, hashMap);
            int[] iArr = new int[(i2 * i3)];
            for (int i6 = 0; i6 < i3; i6++) {
                for (int i7 = 0; i7 < i2; i7++) {
                    if (encode.get(i7, i6)) {
                        iArr[(i6 * i2) + i7] = i5;
                    } else {
                        iArr[(i6 * i2) + i7] = i4;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i2, 0, 0, i2, i3);
            if (bitmap != null) {
                createBitmap = a(createBitmap, bitmap);
            }
            boolean z = createBitmap != null && createBitmap.compress(CompressFormat.JPEG, 100, new FileOutputStream(str2));
            return z;
        } catch (WriterException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static Bitmap a(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return bitmap;
        }
        float f = ((((float) width) * 1.0f) / 5.0f) / ((float) width2);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
            canvas.scale(f, f, (float) (width / 2), (float) (height / 2));
            canvas.drawBitmap(bitmap2, (float) ((width - width2) / 2), (float) ((height - height2) / 2), null);
            canvas.save(31);
            canvas.restore();
        } catch (Exception e) {
            e.getStackTrace();
            createBitmap = null;
        }
        return createBitmap;
    }
}
