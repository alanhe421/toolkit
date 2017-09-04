package com.qrcomic.c;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.qrcomic.c.c.b;
import com.qrcomic.c.c.c;

/* compiled from: ComicInjectBase */
public class a {
    public void a(Activity activity, Bundle bundle, int i, int i2, com.qrcomic.c.b.a aVar) {
        Toast.makeText(activity, "购买的activity页面找不到了", 0).show();
    }

    public void a(Activity activity, String str, String str2, int i) {
        Toast.makeText(activity, "漫画详情页面找不到了", 0).show();
    }

    public void a(Activity activity, String str, int i, int i2) {
        Toast.makeText(activity, "漫画目录页找不到了", 0).show();
    }

    public c a() {
        return new com.qrcomic.c.a.c();
    }

    public b b() {
        return null;
    }

    public com.qrcomic.c.c.a c() {
        return null;
    }

    public void a(Activity activity, com.qrcomic.c.b.a aVar) {
        Toast.makeText(activity, "漫画下载页找不到了", 0).show();
    }

    public Dialog a(Context context, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Toast.makeText(context, "加入书架弹窗找不到了", 0).show();
        return null;
    }

    public Dialog a(Context context, String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Toast.makeText(context, "同步进度弹窗找不到了", 0).show();
        return null;
    }

    public Dialog b(Context context, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Toast.makeText(context, "开启私密阅读找不到了", 0).show();
        return null;
    }

    public void a(com.qrcomic.entity.a aVar, Activity activity) {
        Toast.makeText(activity, "自己去实现跳转评论页面哟。。", 0).show();
    }

    public void a(Activity activity) {
    }

    public void a(Intent intent, Context context) {
    }

    public void a(Context context) {
    }
}
