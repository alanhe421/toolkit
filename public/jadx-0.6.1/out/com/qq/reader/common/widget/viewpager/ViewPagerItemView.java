package com.qq.reader.common.widget.viewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.tencent.feedback.proguard.R;

public class ViewPagerItemView extends FrameLayout {
    ImageView a;
    Options b;
    private Bitmap c;

    public ViewPagerItemView(Context context) {
        super(context);
        b();
    }

    public ViewPagerItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    private void b() {
        this.a = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.help_view_pager_item, null);
        addView(this.a);
        this.b = new Options();
        this.b.inPreferredConfig = Config.RGB_565;
        this.b.inPurgeable = true;
        this.b.inInputShareable = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setData(int r5) {
        /*
        r4 = this;
        r0 = 0;
        r1 = r4.getContext();	 Catch:{ Exception -> 0x0027, all -> 0x0030 }
        r1 = r1.getResources();	 Catch:{ Exception -> 0x0027, all -> 0x0030 }
        r0 = r1.openRawResource(r5);	 Catch:{ Exception -> 0x0027, all -> 0x0030 }
        r1 = 0;
        r2 = r4.b;	 Catch:{ Exception -> 0x0027, all -> 0x003e }
        r1 = android.graphics.BitmapFactory.decodeStream(r0, r1, r2);	 Catch:{ Exception -> 0x0027, all -> 0x003e }
        r4.c = r1;	 Catch:{ Exception -> 0x0027, all -> 0x003e }
        if (r0 == 0) goto L_0x001b;
    L_0x0018:
        r0.close();	 Catch:{ IOException -> 0x003a }
    L_0x001b:
        r0 = r4.c;
        if (r0 == 0) goto L_0x0026;
    L_0x001f:
        r0 = r4.a;
        r1 = r4.c;
        r0.setImageBitmap(r1);
    L_0x0026:
        return;
    L_0x0027:
        r1 = move-exception;
        if (r0 == 0) goto L_0x001b;
    L_0x002a:
        r0.close();	 Catch:{ IOException -> 0x002e }
        goto L_0x001b;
    L_0x002e:
        r0 = move-exception;
        goto L_0x001b;
    L_0x0030:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
    L_0x0034:
        if (r1 == 0) goto L_0x0039;
    L_0x0036:
        r1.close();	 Catch:{ IOException -> 0x003c }
    L_0x0039:
        throw r0;
    L_0x003a:
        r0 = move-exception;
        goto L_0x001b;
    L_0x003c:
        r1 = move-exception;
        goto L_0x0039;
    L_0x003e:
        r1 = move-exception;
        r3 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.widget.viewpager.ViewPagerItemView.setData(int):void");
    }

    public void a() {
        this.a.setImageBitmap(null);
        if (this.c != null && !this.c.isRecycled()) {
            this.c.recycle();
            this.c = null;
            System.gc();
        }
    }
}
