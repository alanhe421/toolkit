package com.qqcomic.bitmaphelper;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Log;
import com.qqcomic.bitmaphelper.references.a;
import com.qqcomic.bitmaphelper.references.d;
import com.qrcomic.a.h;
import com.qrcomic.manager.b;
import com.qrcomic.util.g;
import com.qrcomic.util.j;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CompactBitmapFactory {
    public static boolean a = (VERSION.SDK_INT >= 11);
    private static final Set<SoftReference<Bitmap>> b;
    private static final d<Bitmap> c = new d<Bitmap>() {
        public void a(Bitmap bitmap) {
            if (h.b && bitmap != null) {
                if (g.a()) {
                    g.b("CompactBitmapFactory", g.d, "No bitmap reuse, call recycle() directly.");
                }
                bitmap.recycle();
            } else if (CompactBitmapFactory.a && CompactBitmapFactory.b != null && CompactBitmapFactory.b.size() <= 10) {
                synchronized (CompactBitmapFactory.b) {
                    CompactBitmapFactory.b.add(new SoftReference(bitmap));
                }
            } else if (bitmap != null) {
                if (g.a()) {
                    g.b("CompactBitmapFactory", g.d, "bitmap is closed ?");
                }
                bitmap.recycle();
            }
        }
    };

    public static native synchronized int nativePinBitmap(Bitmap bitmap);

    static {
        System.loadLibrary("skbitmap_helper");
        if (a) {
            b = Collections.synchronizedSet(new HashSet());
        } else {
            b = null;
        }
    }

    public static b a(byte[] bArr, int i, int i2, Options options) throws OutOfMemoryError {
        Throwable th;
        b bVar = null;
        a b;
        try {
            b = b(bArr, i, i2, options);
            if (b != null) {
                try {
                    if (!b.a().b().h.d()) {
                        bVar = new b(b);
                        if (b != null) {
                            b.close();
                        }
                        return bVar;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (b != null) {
                        b.close();
                    }
                    throw th;
                }
            }
            if (b != null) {
                b.close();
            }
            return bVar;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            b = null;
            th = th4;
            if (b != null) {
                b.close();
            }
            throw th;
        }
    }

    @TargetApi(11)
    private static a<Bitmap> b(byte[] bArr, int i, int i2, Options options) {
        Bitmap bitmap = null;
        if (bArr == null) {
            if (!g.a()) {
                return bitmap;
            }
            g.b("CompactBitmapFactory", g.d, "data == null");
            return bitmap;
        } else if (bArr.length >= i + i2) {
            if (options == null) {
                options = new Options();
            }
            Object decodeByteArray;
            if (h.b) {
                if (g.a()) {
                    g.b("CompactBitmapFactory", g.d, "Skip bitmap reuse.");
                }
                try {
                    decodeByteArray = BitmapFactory.decodeByteArray(bArr, i, i2, options);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
                return a.a(decodeByteArray, c);
            }
            if (g.a()) {
                g.b("CompactBitmapFactory", g.d, "Try bitmap reuse.");
            }
            Bitmap a;
            if (a) {
                options.inMutable = true;
                options.inJustDecodeBounds = true;
                if (g.a()) {
                    g.b("CompactBitmapFactory", g.d, "data.length=" + bArr.length + ",offset = " + i + ",length=" + i2);
                }
                BitmapFactory.decodeByteArray(bArr, i, i2, options);
                if (g.a()) {
                    g.b("CompactBitmapFactory", g.d, "opts.inPreferredConfig=" + options.inPreferredConfig + ",opts.outHeight = " + options.outHeight + ",opts.outWidth=" + options.outWidth);
                }
                a = a(options);
                if (a != null) {
                    if (!a.isRecycled()) {
                        options.inBitmap = a;
                        if (g.a()) {
                            g.b("CompactBitmapFactory", g.d, "CompactBitmapFactory decodeBitmap form inBitmap");
                        }
                    } else if (g.a()) {
                        g.b("CompactBitmapFactory", g.d, "inBimtap is recycled");
                    }
                } else if (g.a()) {
                    g.b("CompactBitmapFactory", g.d, "inBimtap == null");
                }
                options.inJustDecodeBounds = false;
                try {
                    decodeByteArray = BitmapFactory.decodeByteArray(bArr, i, i2, options);
                } catch (Throwable th) {
                    if (g.a()) {
                        g.b("CompactBitmapFactory", g.d, bArr.toString() + ":" + j.a(bArr));
                    }
                    th.printStackTrace();
                }
            } else {
                options.inDither = true;
                options.inPurgeable = true;
                if (VERSION.SDK_INT >= 11) {
                    options.inMutable = true;
                }
                try {
                    a = BitmapFactory.decodeByteArray(bArr, i, i2, options);
                } catch (OutOfMemoryError e2) {
                    a = bitmap;
                }
                if (a != null) {
                    try {
                        if (b.a().b().h.d()) {
                            Log.e("NATIVEPIN", "native pin bitmap not shut down");
                            return bitmap;
                        }
                        nativePinBitmap(a);
                        Log.e("NATIVEPIN", "native pin bitmap not shut down");
                        bitmap = a;
                    } catch (Exception e3) {
                        a.recycle();
                        e3.printStackTrace();
                    }
                } else if (g.a()) {
                    g.a("CompactBitmapFactory", g.d, "CompactBitmapFactory: decodeByteArray is null");
                    bitmap = a;
                }
                bitmap = a;
            }
            return a.a(decodeByteArray, c);
        } else if (!g.a()) {
            return bitmap;
        } else {
            g.b("CompactBitmapFactory", g.d, "data.length < (offset + length)");
            return bitmap;
        }
    }

    public static Bitmap a(Options options) {
        if (b == null || b.isEmpty()) {
            return null;
        }
        Bitmap bitmap;
        synchronized (b) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                bitmap = (Bitmap) ((SoftReference) it.next()).get();
                if (bitmap == null || !bitmap.isMutable()) {
                    it.remove();
                } else if (a(bitmap, options)) {
                    it.remove();
                    break;
                }
            }
            bitmap = null;
        }
        return bitmap;
    }

    @TargetApi(19)
    private static boolean a(Bitmap bitmap, Options options) {
        if (VERSION.SDK_INT >= 19) {
            if (((options.outWidth / options.inSampleSize) * (options.outHeight / options.inSampleSize)) * a(bitmap.getConfig()) <= bitmap.getAllocationByteCount()) {
                return true;
            }
            return false;
        } else if (bitmap.getWidth() == options.outWidth && bitmap.getHeight() == options.outHeight && options.inSampleSize == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static int a(Config config) {
        if (config == Config.ARGB_8888) {
            return 4;
        }
        if (config == Config.RGB_565 || config == Config.ARGB_4444) {
            return 2;
        }
        if (config == Config.ALPHA_8) {
            return 1;
        }
        return 1;
    }

    public static void a() {
        if (b != null) {
            synchronized (b) {
                for (SoftReference softReference : b) {
                    Bitmap bitmap = (Bitmap) softReference.get();
                    if (!(bitmap == null || bitmap.isRecycled())) {
                        bitmap.recycle();
                    }
                }
                b.clear();
            }
        }
    }
}
