package format.epub.b;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import com.qq.reader.ReaderApplication;
import com.tencent.android.tpush.common.Constants;
import com.tencent.imsdk.BaseConstants;
import format.epub.common.image.b;
import format.epub.common.image.d;
import format.epub.common.image.g;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ZLAndroidImageManager */
public final class c extends d {
    static final int b = a(ReaderApplication.getApplicationImp());
    private static c e;
    private List<b> c = new ArrayList();
    private int d;

    public /* synthetic */ format.epub.common.image.c a(b bVar) {
        return b(bVar);
    }

    public static synchronized c b() {
        c cVar;
        synchronized (c.class) {
            if (e == null) {
                synchronized (c.class) {
                    if (e == null) {
                        e = new c();
                    }
                }
            }
            cVar = e;
        }
        return cVar;
    }

    public b b(b bVar) {
        if (bVar instanceof b) {
            return (b) bVar;
        }
        if (!(bVar instanceof g)) {
            return null;
        }
        g gVar = (g) bVar;
        if ("image/palm".equals(gVar.d())) {
            return null;
        }
        return new a(gVar);
    }

    public void a(b bVar) {
        if (!this.c.contains(bVar) || this.c.indexOf(bVar) == this.c.size() - 1) {
            this.c.add(bVar);
            this.d += a(bVar.b());
        } else {
            this.c.remove(bVar);
            this.c.add(bVar);
        }
        if (this.c.size() > 1 && this.d > b) {
            b bVar2 = (b) this.c.get(0);
            if (bVar2 != null) {
                bVar2.c();
            }
            this.d -= a(((b) this.c.remove(0)).b());
        }
    }

    public void c() {
        for (b bVar : this.c) {
            if (bVar != null) {
                bVar.c();
            }
        }
        this.d = 0;
        this.c.clear();
    }

    private static int a(Context context) {
        ActivityManager activityManager = (ActivityManager) a(context, Constants.FLAG_ACTIVITY_NAME);
        return (int) ((((long) (((context.getApplicationInfo().flags & 1048576) != 0 ? 1 : null) != null ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass())) * BaseConstants.MEGA) / 7);
    }

    private static <T> T a(Context context, String str) {
        return context.getSystemService(str);
    }

    private static int a(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        int allocationByteCount = VERSION.SDK_INT >= 19 ? bitmap.getAllocationByteCount() : bitmap.getByteCount();
        if (allocationByteCount >= 0) {
            return allocationByteCount;
        }
        throw new IllegalStateException("Negative size: " + bitmap);
    }
}
