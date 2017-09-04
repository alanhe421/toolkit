package com.qq.reader.liveshow.utils;

import android.app.Activity;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.j;
import com.qq.reader.liveshow.model.a;
import com.qq.reader.liveshow.views.customviews.BaseAuthorDetailDialog;
import com.qq.reader.liveshow.views.customviews.BaseMemberInfoDialog;
import java.lang.ref.WeakReference;

/* compiled from: UserCardHelper */
public class q {
    private static WeakReference<BaseMemberInfoDialog> a;
    private static WeakReference<BaseAuthorDetailDialog> b;

    public static void a(Activity activity, long j, String str, String str2, String str3, j jVar, boolean z) {
        if (j <= 0) {
            a(activity, str, str2, str3);
        } else {
            b(activity, j, str, str2, str3, jVar, z);
        }
    }

    public static void a(Activity activity, String str, String str2, String str3) {
        BaseMemberInfoDialog baseMemberInfoDialog;
        if (a != null) {
            baseMemberInfoDialog = (BaseMemberInfoDialog) a.get();
            if (baseMemberInfoDialog == null) {
                baseMemberInfoDialog = n.a().e().b(activity);
                a = new WeakReference(baseMemberInfoDialog);
            }
        } else {
            baseMemberInfoDialog = n.a().e().b(activity);
            a = new WeakReference(baseMemberInfoDialog);
        }
        baseMemberInfoDialog.setUserId(str);
        baseMemberInfoDialog.setRoomId(a.i());
        baseMemberInfoDialog.setName(str2);
        baseMemberInfoDialog.setAvatarUrl(str3);
        baseMemberInfoDialog.show();
    }

    public static void b(Activity activity, long j, String str, String str2, String str3, j jVar, boolean z) {
        BaseAuthorDetailDialog baseAuthorDetailDialog;
        if (b != null) {
            baseAuthorDetailDialog = (BaseAuthorDetailDialog) b.get();
            if (baseAuthorDetailDialog == null) {
                baseAuthorDetailDialog = n.a().e().a(activity);
                b = new WeakReference(baseAuthorDetailDialog);
            }
        } else {
            baseAuthorDetailDialog = n.a().e().a(activity);
            b = new WeakReference(baseAuthorDetailDialog);
        }
        baseAuthorDetailDialog.setIsHost(true);
        baseAuthorDetailDialog.setPresenterDispatch(jVar);
        baseAuthorDetailDialog.setAuthorId(j);
        baseAuthorDetailDialog.setRoomId(a.i());
        baseAuthorDetailDialog.setUserId(str);
        baseAuthorDetailDialog.setName(str2);
        baseAuthorDetailDialog.setAvatarUrl(str3);
        baseAuthorDetailDialog.show();
    }
}
