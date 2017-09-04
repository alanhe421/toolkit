package jp.co.cyberagent.android.gpuimage;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.view.WindowManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.openqq.protocol.imsdk.im_common;
import com.tencent.tesla.soload.SoLoadCore;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GPUImage {
    private final Context a;
    private final b b;
    private GLSurfaceView c;
    private a d;
    private Bitmap e;
    private ScaleType f = ScaleType.CENTER_CROP;

    public enum ScaleType {
        CENTER_INSIDE,
        CENTER_CROP
    }

    private abstract class b extends AsyncTask<Void, Void, Bitmap> {
        private final GPUImage a;
        final /* synthetic */ GPUImage b;
        private int c;
        private int d;

        protected abstract int a() throws IOException;

        protected abstract Bitmap a(Options options);

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Bitmap) obj);
        }

        public b(GPUImage gPUImage, GPUImage gPUImage2) {
            this.b = gPUImage;
            this.a = gPUImage2;
        }

        protected Bitmap a(Void... voidArr) {
            if (this.b.b != null && this.b.b.b() == 0) {
                try {
                    synchronized (this.b.b.b) {
                        this.b.b.b.wait(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.c = this.b.c();
            this.d = this.b.d();
            return b();
        }

        protected void a(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            this.a.b();
            this.a.a(bitmap);
        }

        private Bitmap b() {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            a(options);
            int i = 1;
            while (true) {
                boolean z;
                if (options.outWidth / i > this.c) {
                    z = true;
                } else {
                    z = false;
                }
                if (!a(z, options.outHeight / i > this.d)) {
                    break;
                }
                i++;
            }
            i--;
            if (i < 1) {
                i = 1;
            }
            Options options2 = new Options();
            options2.inSampleSize = i;
            options2.inPreferredConfig = Config.RGB_565;
            options2.inPurgeable = true;
            options2.inTempStorage = new byte[32768];
            Bitmap a = a(options2);
            if (a == null) {
                return null;
            }
            return b(c(a));
        }

        private Bitmap b(Bitmap bitmap) {
            int[] a = a(bitmap.getWidth(), bitmap.getHeight());
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, a[0], a[1], true);
            if (createScaledBitmap != bitmap) {
                bitmap.recycle();
                bitmap = createScaledBitmap;
                System.gc();
            }
            if (this.b.f != ScaleType.CENTER_CROP) {
                return bitmap;
            }
            int i = a[0] - this.c;
            int i2 = a[1] - this.d;
            createScaledBitmap = Bitmap.createBitmap(bitmap, i / 2, i2 / 2, a[0] - i, a[1] - i2);
            if (createScaledBitmap == bitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return createScaledBitmap;
        }

        private int[] a(int i, int i2) {
            float f = ((float) i) / ((float) this.c);
            float f2 = ((float) i2) / ((float) this.d);
            int i3 = this.b.f == ScaleType.CENTER_CROP ? f > f2 ? 1 : 0 : f < f2 ? 1 : 0;
            if (i3 != 0) {
                f = (float) this.d;
                f2 = (f / ((float) i2)) * ((float) i);
            } else {
                f2 = (float) this.c;
                f = (f2 / ((float) i)) * ((float) i2);
            }
            return new int[]{Math.round(f2), Math.round(f)};
        }

        private boolean a(boolean z, boolean z2) {
            boolean z3 = false;
            if (this.b.f != ScaleType.CENTER_CROP) {
                if (z || z2) {
                    z3 = true;
                }
                return z3;
            } else if (z && z2) {
                return true;
            } else {
                return false;
            }
        }

        private Bitmap c(Bitmap bitmap) {
            IOException iOException;
            if (bitmap == null) {
                return null;
            }
            try {
                int a = a();
                if (a == 0) {
                    return bitmap;
                }
                Matrix matrix = new Matrix();
                matrix.postRotate((float) a);
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                try {
                    bitmap.recycle();
                    return createBitmap;
                } catch (IOException e) {
                    bitmap = createBitmap;
                    iOException = e;
                    iOException.printStackTrace();
                    return bitmap;
                }
            } catch (IOException e2) {
                iOException = e2;
                iOException.printStackTrace();
                return bitmap;
            }
        }
    }

    private class a extends b {
        final /* synthetic */ GPUImage a;
        private final File c;

        public a(GPUImage gPUImage, GPUImage gPUImage2, File file) {
            this.a = gPUImage;
            super(gPUImage, gPUImage2);
            this.c = file;
        }

        protected Bitmap a(Options options) {
            return BitmapFactory.decodeFile(this.c.getAbsolutePath(), options);
        }

        protected int a() throws IOException {
            switch (new ExifInterface(this.c.getAbsolutePath()).getAttributeInt("Orientation", 1)) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return im_common.WPA_QZONE;
                default:
                    return 0;
            }
        }
    }

    private class c extends b {
        final /* synthetic */ GPUImage a;
        private final Uri c;

        public c(GPUImage gPUImage, GPUImage gPUImage2, Uri uri) {
            this.a = gPUImage;
            super(gPUImage, gPUImage2);
            this.c = uri;
        }

        protected Bitmap a(Options options) {
            try {
                InputStream openStream;
                if (this.c.getScheme().startsWith(com.tencent.qalsdk.core.c.d) || this.c.getScheme().startsWith("https")) {
                    openStream = new URL(this.c.toString()).openStream();
                } else {
                    openStream = this.a.a.getContentResolver().openInputStream(this.c);
                }
                return BitmapFactory.decodeStream(openStream, null, options);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected int a() throws IOException {
            Cursor query = this.a.a.getContentResolver().query(this.c, new String[]{"orientation"}, null, null, null);
            if (query == null || query.getCount() != 1) {
                return 0;
            }
            query.moveToFirst();
            int i = query.getInt(0);
            query.close();
            return i;
        }
    }

    public GPUImage(Context context) {
        if (a(context)) {
            this.a = context;
            this.d = new a();
            this.b = new b(this.d);
            return;
        }
        throw new IllegalStateException("OpenGL ES 2.0 is not supported on this phone.");
    }

    private boolean a(Context context) {
        return ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getDeviceConfigurationInfo().reqGlEsVersion >= SoLoadCore.IF_SO_CONFIG_EXIST;
    }

    public void a(GLSurfaceView gLSurfaceView) {
        this.c = gLSurfaceView;
        this.c.setEGLContextClientVersion(2);
        this.c.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.c.getHolder().setFormat(1);
        this.c.setRenderer(this.b);
        this.c.setRenderMode(0);
        this.c.requestRender();
    }

    public void a() {
        if (this.c != null) {
            this.c.requestRender();
        }
    }

    public void a(a aVar) {
        this.d = aVar;
        this.b.a(this.d);
        a();
    }

    public void a(Bitmap bitmap) {
        this.e = bitmap;
        this.b.a(bitmap, false);
        a();
    }

    public void a(ScaleType scaleType) {
        this.f = scaleType;
        this.b.a(scaleType);
        this.b.a();
        this.e = null;
        a();
    }

    public void a(Rotation rotation) {
        this.b.a(rotation);
    }

    public void b() {
        this.b.a();
        this.e = null;
        a();
    }

    public void a(Uri uri) {
        new c(this, this, uri).execute(new Void[0]);
    }

    public void a(File file) {
        new a(this, this, file).execute(new Void[0]);
    }

    private int c() {
        if (this.b != null && this.b.b() != 0) {
            return this.b.b();
        }
        if (this.e != null) {
            return this.e.getWidth();
        }
        return ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    private int d() {
        if (this.b != null && this.b.c() != 0) {
            return this.b.c();
        }
        if (this.e != null) {
            return this.e.getHeight();
        }
        return ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay().getHeight();
    }
}
