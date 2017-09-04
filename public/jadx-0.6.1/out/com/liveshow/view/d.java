package com.liveshow.view;

import com.tencent.feedback.proguard.R;

/* compiled from: UserInfoHelper */
public class d {
    public static int a(int i) {
        switch (i) {
            case 4:
                return R.drawable.card_platinum;
            case 5:
                return R.drawable.card_god;
            case 6:
                return R.drawable.card_star;
            case 7:
                return R.drawable.card_auther;
            default:
                return 0;
        }
    }

    public static void a(c cVar, int i, String str, String str2, int i2) {
        if (str2.equals(str)) {
            cVar.e().setVisibility(8);
            cVar.f().setVisibility(8);
            cVar.g().setVisibility(8);
        } else if (i2 == 4) {
            cVar.e().setVisibility(0);
            cVar.f().setVisibility(8);
            cVar.g().setVisibility(8);
        } else if (i2 == 3) {
            if (i >= 4) {
                cVar.f().setVisibility(0);
            } else {
                cVar.f().setVisibility(8);
            }
            cVar.e().setVisibility(0);
            cVar.g().setVisibility(8);
        } else if (i2 == 2) {
            if (i >= 3) {
                cVar.f().setVisibility(0);
            } else {
                cVar.f().setVisibility(8);
            }
            cVar.e().setVisibility(0);
            cVar.g().setVisibility(8);
        } else if (i2 == 1) {
            cVar.e().setVisibility(0);
            if (i > 2) {
                cVar.f().setVisibility(0);
                cVar.g().setVisibility(0);
            } else if (i == 2) {
                cVar.f().setVisibility(0);
                cVar.g().setVisibility(8);
            } else {
                cVar.f().setVisibility(8);
                cVar.g().setVisibility(8);
            }
        } else {
            cVar.e().setVisibility(0);
            cVar.f().setVisibility(8);
            cVar.g().setVisibility(8);
        }
    }
}
