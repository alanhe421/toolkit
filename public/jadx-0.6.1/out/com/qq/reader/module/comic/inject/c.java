package com.qq.reader.module.comic.inject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.common.utils.ad;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.comic.activity.NativeBookStoreComicDirActivity;
import com.qq.reader.module.comic.entity.ComicShelfInfo;
import com.qq.reader.module.comic.inject.d.b;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.view.AlertDialog;
import com.qrcomic.c.a;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: ComicInject */
public class c extends a {
    public void a(Activity activity, Bundle bundle, int i, int i2, com.qrcomic.c.b.a aVar) {
        ComicShelfInfo comicShelfInfo = new ComicShelfInfo();
        comicShelfInfo.a(aVar);
        o.a(activity, bundle, i, i2, comicShelfInfo);
    }

    public void a(Activity activity, String str, String str2, int i) {
        o.l(activity, str, null);
    }

    public void a(Activity activity, String str, int i, int i2) {
        NativeBookStoreComicDirActivity.a(activity, str, i, i2);
    }

    public com.qrcomic.c.c.c a() {
        return new b();
    }

    public com.qrcomic.c.c.b b() {
        return new com.qq.reader.module.comic.inject.d.c();
    }

    public com.qrcomic.c.c.a c() {
        return new d.a();
    }

    public void a(Activity activity, com.qrcomic.c.b.a aVar) {
        ComicShelfInfo comicShelfInfo = new ComicShelfInfo();
        comicShelfInfo.a(aVar);
        o.a(activity, comicShelfInfo, aVar.g(), null, true);
    }

    public Dialog a(Context context, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new AlertDialog.a(context).a("提示").b("喜欢就加入书架吧").a(true).b("取消", onClickListener2).a("加入书架", onClickListener).a();
    }

    public Dialog a(Context context, String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new AlertDialog.a(context).a("提示").b("是否同步上次阅读进度：" + str).b("取消", onClickListener).a("同步", onClickListener2).a();
    }

    public Dialog b(Context context, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new AlertDialog.a(context).a("开启私密阅读").b("开启后，所选书籍的阅读记录将不会对外公开").a(true).b("取消", onClickListener2).a("开启", onClickListener).a();
    }

    public void a(com.qrcomic.entity.a aVar, Activity activity) {
        o.a(activity, Long.valueOf(aVar.e()), aVar.f(), 9, new JumpActivityParameter());
    }

    public void a(Activity activity) {
        super.a(activity);
        Intent intent = new Intent();
        intent.setClass(activity, MainActivity.class);
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        activity.startActivity(intent);
    }

    public void a(Intent intent, Context context) {
        super.a(intent, context);
        intent.putExtra("type", "READING_SAVE_COMIC");
        ad.a(intent, context);
    }

    public void a(Context context) {
        super.a(context);
        ad.b(context);
    }
}
