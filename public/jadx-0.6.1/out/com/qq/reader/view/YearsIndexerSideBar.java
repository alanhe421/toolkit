package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.view.IndexerSideBar.a;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class YearsIndexerSideBar extends View {
    private a a;
    private int b = -1;
    private Paint c = new Paint();
    private ArrayList<String> d;
    private TextView e;
    private int f;

    public void setTextView(TextView textView) {
        this.e = textView;
    }

    public YearsIndexerSideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public YearsIndexerSideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = (int) context.getResources().getDimension(R.dimen.common_dp_8);
    }

    public YearsIndexerSideBar(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();
        if (this.d != null) {
            int size = (height - (this.f * 2)) / this.d.size();
            for (int i = 0; i < this.d.size(); i++) {
                this.c.setColor(getResources().getColor(R.color.common_textcolor_primary));
                this.c.setTypeface(Typeface.DEFAULT);
                this.c.setAntiAlias(true);
                this.c.setTextSize(getResources().getDimension(R.dimen.text_size_class_3));
                if (i == this.b) {
                    this.c.setColor(getResources().getColor(R.color.text_color_c301));
                    this.c.setFakeBoldText(true);
                }
                canvas.drawText(((String) this.d.get(i)).substring(2), ((float) (width / 2)) - (this.c.measureText(((String) this.d.get(i)).substring(2)) / 2.0f), this.c.getTextSize() + ((((((float) size) - this.c.getTextSize()) / 2.0f) + ((float) (size * i))) + ((float) this.f)), this.c);
                this.c.reset();
            }
        }
        super.onDraw(canvas);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.b;
        a aVar = this.a;
        int height = (int) ((y / ((float) getHeight())) * ((float) this.d.size()));
        switch (action) {
            case 1:
                this.b = -1;
                invalidate();
                if (this.e != null) {
                    this.e.setVisibility(4);
                    break;
                }
                break;
            default:
                if (i != height && height >= 0 && height < this.d.size()) {
                    if (aVar != null) {
                        aVar.a((String) this.d.get(height));
                    }
                    if (this.e != null) {
                        this.e.setText((CharSequence) this.d.get(height));
                        this.e.setVisibility(0);
                    }
                    this.b = height;
                    invalidate();
                    break;
                }
        }
        return true;
    }

    public void setYearsletters(ArrayList<String> arrayList) {
        this.d = arrayList;
    }

    public void setOnTouchingLetterChangedListener(a aVar) {
        this.a = aVar;
    }
}
