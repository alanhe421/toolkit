package com.qq.reader.common.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.util.WeakReferenceHandler;

public class ReaderRatingBar extends RatingBar implements Callback {
    private String[] a = null;
    private TextView b;
    private OnRatingBarChangeListener c;
    private OnRatingBarChangeListener d;
    private a e;
    private WeakReferenceHandler f = new WeakReferenceHandler(this);
    private int g = 0;

    public interface a {
        boolean a(RatingBar ratingBar, float f);

        void b(RatingBar ratingBar, float f);
    }

    public ReaderRatingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        b();
    }

    public ReaderRatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
        b();
    }

    public ReaderRatingBar(Context context) {
        super(context);
        a();
        b();
    }

    private void a() {
        setNumStars(5);
        setStepSize(1.0f);
        setIndeterminate(false);
    }

    public void setRatingText(TextView textView, String[] strArr) {
        this.b = textView;
        this.a = strArr;
        if (this.a.length != getNumStars() + 1) {
            throw new IllegalStateException("the starts' size is not equls the textArray's lenght plus one");
        }
        this.c.onRatingChanged(this, getRating(), false);
    }

    private void b() {
        this.c = new OnRatingBarChangeListener(this) {
            final /* synthetic */ ReaderRatingBar a;

            {
                this.a = r1;
            }

            public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                if (this.a.b != null) {
                    try {
                        this.a.b.setText(this.a.a[(int) Math.ceil((double) (((float) Math.round(this.a.getSubProgressPerStar() * f)) * this.a.getStepSize()))]);
                    } catch (Exception e) {
                        c.e("star", Math.ceil((double) (((float) Math.round(this.a.getSubProgressPerStar() * f)) * this.a.getStepSize())) + "");
                        this.a.b.setText("");
                    }
                }
                if (this.a.d != null) {
                    this.a.d.onRatingChanged(ratingBar, f, z);
                }
                if (z) {
                    if (this.a.g > 0) {
                        this.a.f.removeMessages(1001);
                    }
                    if (this.a.e == null || !this.a.e.a(this.a, f)) {
                        Message obtainMessage = this.a.f.obtainMessage();
                        obtainMessage.what = 1001;
                        Bundle bundle = new Bundle();
                        bundle.putFloat("rating", f);
                        obtainMessage.obj = bundle;
                        this.a.f.sendMessageDelayed(obtainMessage, (long) this.a.g);
                    }
                }
            }
        };
        super.setOnRatingBarChangeListener(this.c);
    }

    public void setOnRatingBarChangeListener(OnRatingBarChangeListener onRatingBarChangeListener) {
        this.d = onRatingBarChangeListener;
    }

    private float getSubProgressPerStar() {
        if (getNumStars() > 0) {
            return (1.0f * ((float) getMax())) / ((float) getNumStars());
        }
        return 1.0f;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1001:
                if (this.e != null) {
                    this.e.b(this, ((Bundle) message.obj).getFloat("rating"));
                    break;
                }
                break;
        }
        return false;
    }

    public void setOnRatingBarDelayChangedListener(a aVar) {
        this.e = aVar;
    }

    public int getRatingChangedDelaytime() {
        return this.g;
    }

    public void setRatingChangedDelaytime(int i) {
        this.g = i;
    }
}
