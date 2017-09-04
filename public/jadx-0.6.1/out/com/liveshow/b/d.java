package com.liveshow.b;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.view.ViewGroup;
import com.liveshow.view.AuthorDetailDialog;
import com.liveshow.view.MemberDetailDialog;
import com.qq.reader.appconfig.b;
import com.qq.reader.common.utils.o;
import com.qq.reader.liveshow.b.f;
import com.qq.reader.liveshow.b.i;
import com.qq.reader.liveshow.b.k;
import com.qq.reader.liveshow.utils.SxbLog.SxbLogLevel;
import com.qq.reader.liveshow.views.customviews.BaseAuthorDetailDialog;
import com.qq.reader.liveshow.views.customviews.BaseLiveEndFrame;
import com.qq.reader.liveshow.views.customviews.BaseMemberInfoDialog;
import com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.view.AlertDialog.a;
import com.tencent.feedback.proguard.R;

/* compiled from: InjectConfig */
public class d extends k {
    public boolean a() {
        return b.a;
    }

    public boolean b() {
        return b.f;
    }

    public SxbLogLevel c() {
        return SxbLogLevel.OFF;
    }

    public BaseAuthorDetailDialog a(Activity activity) {
        return new AuthorDetailDialog(activity, R.style.live_show_info_dlg);
    }

    public BaseMemberInfoDialog b(Activity activity) {
        return new MemberDetailDialog(activity, R.style.live_show_info_dlg);
    }

    public Dialog a(Context context, OnClickListener onClickListener, OnClickListener onClickListener2, String[] strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getResources().getString(R.string.live_show_no_permission)).append("\n");
        for (String append : strArr) {
            stringBuilder.append("权限：").append(append).append("\n");
        }
        Dialog a = new a(context).c(R.drawable.alert_dialog_icon).a((int) R.string.live_show_get_permission_fial_title).b(stringBuilder.toString()).a((int) R.string.alert_dialog_go_get_permission, onClickListener).b((int) R.string.alert_dialog_exit_live, onClickListener2).a();
        a.b(3);
        return a;
    }

    public Dialog a(Context context, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new a(context).c(R.drawable.alert_dialog_icon).a((int) R.string.live_show_alert_title).b((int) R.string.live_show_3g_net_alert_host).a((int) R.string.live_show_3g_start_ok_host, onClickListener).b((int) R.string.live_show_3g_start_cancel, onClickListener2).a();
    }

    public Dialog b(Context context, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new a(context).c(R.drawable.alert_dialog_icon).a((int) R.string.live_show_alert_title).b((int) R.string.live_show_3g_net_alert_user).a((int) R.string.live_show_3g_start_ok_user, onClickListener).b((int) R.string.live_show_3g_start_cancel, onClickListener2).a();
    }

    public Dialog c(Context context, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new a(context).c(R.drawable.alert_dialog_icon).a((int) R.string.live_show_end_live_title).b((int) R.string.live_show_end_live_msg).a((int) R.string.alert_dialog_ok, onClickListener).b((int) R.string.alert_dialog_cancel, onClickListener2).a();
    }

    public Dialog a(Context context, OnClickListener onClickListener, String str) {
        Dialog a = new a(context).c(R.drawable.alert_dialog_icon).a((int) R.string.live_show_alert_title).b((CharSequence) str).a((int) R.string.live_show_not_enough_money, onClickListener).a();
        a.a(-1, (int) R.drawable.live_show_charge_btn_color_selector);
        return a;
    }

    public void a(Activity activity, int i) {
        o.t(activity, new JumpActivityParameter().a(i));
    }

    public String d() {
        return "com.qq.reader.loginok";
    }

    public String e() {
        return "com.reader.live.login.cancel";
    }

    public String f() {
        return "com.reader.live.logout";
    }

    public BaseLiveEndFrame a(LiveEnterRoomDialog.a aVar, Activity activity, ViewGroup viewGroup) {
        return new com.liveshow.view.b(aVar, activity, viewGroup);
    }

    public BaseLiveEndFrame b(LiveEnterRoomDialog.a aVar, Activity activity, ViewGroup viewGroup) {
        return new com.liveshow.view.a(aVar, activity, viewGroup);
    }

    public i g() {
        return new c();
    }

    public f h() {
        return new b();
    }

    public Dialog a(Context context, OnClickListener onClickListener, OnClickListener onClickListener2, int i) {
        return new a(context).c(R.drawable.alert_dialog_icon).a(context.getString(R.string.live_show_room_detail_fail_title) + "（" + i + "）").b((int) R.string.live_show_room_detail_fail_msg).a((int) R.string.live_show_room_detail_fail_btn_ok, onClickListener).b((int) R.string.live_show_room_detail_fail_btn_cancel, onClickListener2).a();
    }

    public Dialog a(Context context, OnClickListener onClickListener, OnClickListener onClickListener2, OnCancelListener onCancelListener, String str) {
        return new a(context).c(R.drawable.alert_dialog_icon).a((CharSequence) "提示").b("确认赠送1个" + str + "吗?").a(onCancelListener).a((CharSequence) "确认赠送", onClickListener).b((CharSequence) "取消", onClickListener2).a();
    }

    public String a(int i) {
        if (b.a) {
            return "http://solomotest4.3g.qq.com/book_res/reader/common/common/unLive.html?roomId=" + i + "&tf=1";
        }
        return "http://yuedu.tingbook.com/common/common/unLive.html?roomId=" + i + "&tf=1";
    }
}
