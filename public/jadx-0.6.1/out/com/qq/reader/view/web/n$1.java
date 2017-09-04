package com.qq.reader.view.web;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.tencent.feedback.proguard.R;

/* compiled from: QRPopupMenu */
class n$1 implements OnTouchListener {
    final /* synthetic */ n a;

    n$1(n nVar) {
        this.a = nVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (((int) motionEvent.getY()) >= this.a.e().getResources().getDimensionPixelOffset(R.dimen.common_dp_48)) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                return true;
            case 1:
            case 3:
                if (n.a(this.a) == null) {
                    return false;
                }
                n.b(this.a).cancel();
                return false;
            default:
                return false;
        }
    }
}
