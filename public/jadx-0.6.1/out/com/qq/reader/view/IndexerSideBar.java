package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;
import com.tencent.imsdk.QLogImpl;

public class IndexerSideBar extends View {
    public static String[] a = new String[]{"A", "B", "C", QLogImpl.TAG_REPORTLEVEL_DEVELOPER, QLogImpl.TAG_REPORTLEVEL_USER, "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", QLogImpl.TAG_REPORTLEVEL_COLORUSER, "X", "Y", "Z", "#"};
    private a b;
    private int c = -1;
    private Paint d = new Paint();
    private TextView e;

    public interface a {
        void a(String str);
    }

    public void setTextView(TextView textView) {
        this.e = textView;
    }

    public IndexerSideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public IndexerSideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public IndexerSideBar(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int length = height / a.length;
        for (height = 0; height < a.length; height++) {
            this.d.setColor(getResources().getColor(R.color.common_textcolor_primary));
            this.d.setTypeface(Typeface.DEFAULT);
            this.d.setAntiAlias(true);
            this.d.setTextSize(getResources().getDimension(R.dimen.text_size_class_3));
            if (height == this.c) {
                this.d.setColor(getResources().getColor(R.color.common_textcolor_link));
                this.d.setFakeBoldText(true);
            }
            canvas.drawText(a[height], ((float) (width / 2)) - (this.d.measureText(a[height]) / 2.0f), (float) ((length * height) + length), this.d);
            this.d.reset();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.c;
        a aVar = this.b;
        int height = (int) ((y / ((float) getHeight())) * ((float) a.length));
        switch (action) {
            case 1:
                setBackgroundColor(0);
                this.c = -1;
                invalidate();
                if (this.e != null) {
                    this.e.setVisibility(4);
                    break;
                }
                break;
            default:
                if (i != height && height >= 0 && height < a.length) {
                    if (aVar != null) {
                        aVar.a(a[height]);
                    }
                    if (this.e != null) {
                        this.e.setText(a[height]);
                        this.e.setVisibility(0);
                        setBackgroundResource(R.drawable.sidebar_press);
                    }
                    this.c = height;
                    invalidate();
                    break;
                }
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(a aVar) {
        this.b = aVar;
    }
}
