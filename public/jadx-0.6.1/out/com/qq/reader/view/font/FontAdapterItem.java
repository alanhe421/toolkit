package com.qq.reader.view.font;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class FontAdapterItem extends RelativeLayout {
    TextView a;
    ImageView b = null;

    public FontAdapterItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FontAdapterItem(Context context) {
        super(context);
    }

    public void setText(String str) {
        if (str != null) {
            this.a.setText(str);
        }
    }

    public void setChoice(boolean z) {
        if (z) {
            this.b.setBackgroundResource(R.drawable.checkbox_on);
        } else {
            this.b.setBackgroundResource(R.drawable.sort_list_add_button);
        }
    }
}
