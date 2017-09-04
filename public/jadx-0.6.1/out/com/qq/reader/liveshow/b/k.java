package com.qq.reader.liveshow.b;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.a.i;
import com.qq.reader.liveshow.utils.SxbLog.SxbLogLevel;
import com.qq.reader.liveshow.views.customviews.BaseAuthorDetailDialog;
import com.qq.reader.liveshow.views.customviews.BaseDialog;
import com.qq.reader.liveshow.views.customviews.BaseLiveEndFrame;
import com.qq.reader.liveshow.views.customviews.BaseMemberInfoDialog;
import com.qq.reader.liveshow.views.customviews.DefaultAuthorDetailDialog;
import com.qq.reader.liveshow.views.customviews.DefaultLiveEndFrame;
import com.qq.reader.liveshow.views.customviews.DefaultMemberInfoDialog;
import com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog.a;
import java.util.Arrays;

/* compiled from: InjectConfigBase */
public class k {
    public boolean a() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public SxbLogLevel c() {
        return SxbLogLevel.OFF;
    }

    public BaseAuthorDetailDialog a(Activity activity) {
        return new DefaultAuthorDetailDialog(activity, i.host_info_dlg);
    }

    public BaseMemberInfoDialog b(Activity activity) {
        return new DefaultMemberInfoDialog(activity, i.host_info_dlg);
    }

    public Dialog a(Context context, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new Builder(context).setTitle("提示").setMessage("当前网络无wifi，可能产生流量费用").setCancelable(false).setPositiveButton("继续", onClickListener).setNegativeButton("取消", onClickListener2).create();
    }

    public Dialog b(Context context, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new Builder(context).setTitle("提示").setMessage("当前网络无wifi，观看可能产生流量费用").setCancelable(false).setPositiveButton("继续观看", onClickListener).setNegativeButton("取消", onClickListener2).create();
    }

    public Dialog a(Context context, OnClickListener onClickListener, OnClickListener onClickListener2, String[] strArr) {
        return new Builder(context).setTitle("提示").setMessage("你现在缺少权限:\n" + Arrays.toString(strArr)).setPositiveButton("开启权限", onClickListener).setNegativeButton("取消", onClickListener2).create();
    }

    public Dialog c(Context context, final OnClickListener onClickListener, final OnClickListener onClickListener2) {
        final Dialog baseDialog = new BaseDialog(context, i.dialog);
        baseDialog.setContentView(g.dialog_end_live);
        ((TextView) baseDialog.findViewById(e.btn_sure)).setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ k c;

            public void onClick(View view) {
                onClickListener.onClick(baseDialog, 0);
            }
        });
        ((TextView) baseDialog.findViewById(e.btn_cancel)).setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ k c;

            public void onClick(View view) {
                onClickListener2.onClick(baseDialog, 1);
            }
        });
        return baseDialog;
    }

    public Dialog a(Context context, OnClickListener onClickListener, String str) {
        return new Builder(context).setTitle("提示").setCancelable(true).setPositiveButton("余额不足,清先充值", onClickListener).setMessage(str + "").create();
    }

    public void a(Activity activity, int i) {
        m.a((Context) activity, "各平台自己去实现跳转授权的设置页面", 0);
    }

    public String a(int i) {
        return "http://yuedu.tingbook.com/common/common/unLive.html?roomId=" + i + "&tf=1";
    }

    public String d() {
        return "com.reader.live.login.success";
    }

    public String e() {
        return "com.reader.live.login.cancel";
    }

    public String f() {
        return "com.reader.live.logout";
    }

    public BaseLiveEndFrame a(a aVar, Activity activity, ViewGroup viewGroup) {
        return new DefaultLiveEndFrame(aVar, activity, viewGroup);
    }

    public BaseLiveEndFrame b(a aVar, Activity activity, ViewGroup viewGroup) {
        return new DefaultLiveEndFrame(aVar, activity, viewGroup);
    }

    public i g() {
        return null;
    }

    public f h() {
        return null;
    }

    public Dialog a(Context context, OnClickListener onClickListener, OnClickListener onClickListener2, int i) {
        Builder builder = new Builder(context);
        builder.setPositiveButton("重试", onClickListener).setNegativeButton("取消", onClickListener2).setTitle("加载异常（" + i + "）").setMessage("加载异常，请重试或退出房间重新进入");
        return builder.create();
    }

    public Dialog a(Context context, OnClickListener onClickListener, OnClickListener onClickListener2, OnCancelListener onCancelListener, String str) {
        return new Builder(context).setTitle("提示").setCancelable(true).setPositiveButton("确认赠送", onClickListener).setNegativeButton("取消", onClickListener2).setOnCancelListener(onCancelListener).setMessage("确认赠送1个" + str + "吗?").create();
    }

    public String i() {
        return "https://yuedu.tingbook.com/common/common/images/defaultface.png";
    }

    public String a(Context context, String str) {
        return String.format(context.getResources().getString(h.share_summary), new Object[]{str});
    }

    public String b(Context context, String str) {
        return TextUtils.isEmpty(str) ? context.getString(h.live_share_default_title) : str;
    }
}
