package com.qq.reader.module.bookshelf;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.adv.c;
import com.qq.reader.cservice.protocol.UserProtocolRedPointManger;
import com.tencent.feedback.proguard.R;

/* compiled from: MainPageAvatarHelper */
public class j {
    public static void a(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.title_bar_avatar_reb_dot);
        if (imageView != null) {
            imageView.setVisibility(4);
            Object b = c.b();
            if (c.a((Object) "TYPE_SKIN_LIST_UPDATE") || d.t || ((com.qq.reader.common.protocol.c.a(view.getContext()) && d.a) || ((b != null && c.a(b)) || ((com.qq.reader.common.login.c.b() && d.aE(view.getContext())) || UserProtocolRedPointManger.a(view.getContext().getApplicationContext()).d())))) {
                imageView.setVisibility(0);
            }
        }
    }

    public static void b(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.title_bar_avatar);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.title_bar_avatar_cover);
        String str = "";
        if (com.qq.reader.common.login.c.b()) {
            imageView2.setVisibility(0);
            LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
            int a = ao.a(33.0f);
            layoutParams.width = a;
            layoutParams.height = a;
            imageView.setLayoutParams(layoutParams);
            try {
                com.qq.reader.common.imageloader.c.a(view.getContext()).a(com.qq.reader.common.login.c.c().b(), imageView, a.a().b());
                return;
            } catch (Exception e) {
                return;
            }
        }
        LayoutParams layoutParams2 = (LayoutParams) imageView.getLayoutParams();
        int a2 = ao.a(27.0f);
        layoutParams2.width = a2;
        layoutParams2.height = a2;
        imageView.setLayoutParams(layoutParams2);
        imageView2.setVisibility(4);
        imageView.setImageResource(R.drawable.profile_default_small_avator);
    }
}
