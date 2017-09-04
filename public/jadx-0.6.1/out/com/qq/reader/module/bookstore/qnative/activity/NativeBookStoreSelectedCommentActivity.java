package com.qq.reader.module.bookstore.qnative.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.page.impl.ba;
import com.qq.reader.view.aj;
import com.tencent.feedback.proguard.R;

public class NativeBookStoreSelectedCommentActivity extends NativeBookStoreTwoLevelActivity {
    private aj k;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void j() {
        ImageView imageView = (ImageView) findViewById(R.id.profile_header_right_image);
        imageView.setImageResource(R.drawable.titlebar_icon_share);
        imageView.setVisibility(0);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeBookStoreSelectedCommentActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.k == null) {
                    ba baVar = (ba) this.a.m;
                    this.a.k = new aj(this.a, e.e + "topicV2.html?tf=1&tid=" + baVar.x + "&share=1", ao.g(baVar.e), baVar.z, baVar.y, baVar.x);
                }
                this.a.k.f_();
            }
        });
    }
}
