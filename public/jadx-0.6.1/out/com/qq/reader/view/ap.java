package com.qq.reader.view;

import android.app.Activity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.view.b.b;
import com.tencent.feedback.proguard.R;

/* compiled from: TipsManager */
public class ap {
    public static b a(int i, Activity activity) {
        boolean z = true;
        b bVar;
        switch (i) {
            case 0:
                bVar = new b(activity, i);
                bVar.a(53);
                bVar.b(activity.getResources().getDimensionPixelOffset(R.dimen.bightmodel_tip_width));
                bVar.c(activity.getResources().getDimensionPixelOffset(R.dimen.bightmodel_tip_height));
                if (d.n) {
                    z = false;
                }
                bVar.a(z);
                bVar.a("点击灯绳，\n夜间模式更护眼！");
                return bVar;
            case 1:
                bVar = new b(activity, i);
                bVar.a(53);
                bVar.b(activity.getResources().getDimensionPixelOffset(R.dimen.batdownload_tip_marginRight));
                bVar.c(activity.getResources().getDimensionPixelOffset(R.dimen.batdownload_tip_marginTop));
                if (d.n) {
                    z = false;
                }
                bVar.a(z);
                bVar.a("预先下载离线看");
                return bVar;
            case 2:
                bVar = new b(activity, i);
                bVar.a(53);
                bVar.b(activity.getResources().getDimensionPixelOffset(R.dimen.vote_tip_marginright));
                bVar.c(activity.getResources().getDimensionPixelOffset(R.dimen.vote_tip_margintop));
                if (d.n) {
                    z = false;
                }
                bVar.a(z);
                bVar.a("投票、打赏，力挺作者！");
                return bVar;
            case 7:
                bVar = new b(activity, i);
                bVar.a(83);
                bVar.b(activity.getResources().getDimensionPixelOffset(R.dimen.maintab_tip_margin_left));
                bVar.c(activity.getResources().getDimensionPixelOffset(R.dimen.maintab_tip_margin_bottom));
                if (d.n) {
                    z = false;
                }
                bVar.a(z);
                bVar.d(R.drawable.tip_common_bg_left_bottom);
                bVar.a("点此回到页面顶部");
                return bVar;
            case 9:
                bVar = new b(activity, i);
                bVar.a(53);
                bVar.b(activity.getResources().getDimensionPixelOffset(R.dimen.batdownload_tip_marginRight));
                bVar.c(activity.getResources().getDimensionPixelOffset(R.dimen.batdownload_tip_marginTop));
                if (d.n) {
                    z = false;
                }
                bVar.a(z);
                bVar.a("推荐不准确，点x号告诉我们");
                return bVar;
            default:
                return null;
        }
    }
}
