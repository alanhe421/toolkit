package com.qq.reader.common.widget;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.tencent.feedback.proguard.R;

/* compiled from: BackImageUtil */
public class a {
    public static ImageView a(ImageView imageView, final Activity activity) {
        if (!(imageView == null || activity == null)) {
            imageView.setImageResource(R.drawable.titlebar_icon_back);
            imageView.setBackgroundResource(R.drawable.titlebar_icon_bg_selector);
            imageView.setScaleType(ScaleType.CENTER);
            int dimensionPixelSize = activity.getResources().getDimensionPixelSize(R.dimen.common_title_back_icon_right_left_padding);
            imageView.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
            imageView.setLayoutParams(new LayoutParams(-2, activity.getResources().getDimensionPixelOffset(R.dimen.original_titlerbar_height)));
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    activity.finish();
                }
            });
            imageView.setVisibility(0);
        }
        return imageView;
    }
}
