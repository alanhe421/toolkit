package com.qq.reader.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.TextView;
import com.tencent.av.sdk.AVError;

public class CloudUpdateStateView extends TextView {
    Animation a;
    Animation b;
    private final long c = 1000;
    private final long d = 500;
    private Handler e = new Handler(this) {
        final /* synthetic */ CloudUpdateStateView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == AVError.AV_ERR_IMSDK_TIMEOUT && this.a.getVisibility() == 0) {
                this.a.startAnimation(this.a.b);
            } else if (message.what == 7001) {
                this.a.a();
            }
        }
    };

    public CloudUpdateStateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CloudUpdateStateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CloudUpdateStateView(Context context) {
        super(context);
    }

    public synchronized void a() {
        if (getVisibility() == 0) {
            super.setVisibility(8);
            if (this.a.hasStarted() || this.b.hasStarted()) {
                setAnimation(null);
                if (this.e.hasMessages(AVError.AV_ERR_IMSDK_TIMEOUT)) {
                    this.e.removeMessages(AVError.AV_ERR_IMSDK_TIMEOUT);
                }
                if (this.e.hasMessages(7001)) {
                    this.e.removeMessages(7001);
                }
            }
        }
    }

    public void setVisibility(int i) {
    }
}
