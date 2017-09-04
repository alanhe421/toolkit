package com.qq.reader.module.readpage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.qq.reader.view.AlertDialog.a;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.im_open.http;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;

/* compiled from: ReaderPageDialogFactory */
public class q {
    public static Dialog a(Context context, int i, Bundle bundle) {
        switch (i) {
            case 100:
                return new a(context).c(R.drawable.alert_dialog_icon).a(R.string.history_dialog_tip).a();
            case 300:
                return new a(context).c(17301543).a(R.string.dialog_vip).a();
            case 301:
                return new a(context).c(17301543).a(R.string.dialog_vip).b(R.string.dialog_online_need_login_vip).a();
            case 302:
                return new a(context).c(17301543).a(R.string.dialog_drm).a();
            case 303:
                return new a(context).c(17301543).a(R.string.dialog_readfailed_title).a();
            case 304:
            case 305:
            case ErrorCode.ERROR_TBSINSTALLER_ISTBSCORELEGAL_01 /*321*/:
                return new a(context).c(17301543).a(R.string.history_dialog_tip).b(R.string.history_to_shelf).a();
            case 306:
                return new a(context).c(17301543).a(R.string.dialog_identify_no_login_title).b(R.string.dialog_identify_no_login_msg).a();
            case 307:
                return new a(context).c(17301543).a(R.string.dialog_drm_font_download_title).b(R.string.dialog_drm_font_download_text).a();
            case 308:
                return new a(context).c(17301543).a(R.string.dialog_shut_down_autopay).b(R.string.dialog_shut_down_notify).a();
            case ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE /*320*/:
                return new a(context).c(17301543).a(R.string.download_skin_title).a();
            case 400:
                return new a(context).c(17301543).a(R.string.dialog_change_title).b(R.string.dialog_change_str).a();
            case http.Internal_Server_Error /*500*/:
                return new a(context).c(17301543).a(R.string.dialog_readfailed_title).a();
            case 501:
                return new a(context).c(17301543).a(R.string.dialog_readfailed_title).a();
            case ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE /*600*/:
                return new a(context).c(17301543).a(R.string.dialog_readfailed_title).b(R.string.dialog_identify_common_error).a();
            case 601:
                return new a(context).c(17301543).a(R.string.dialog_identify_net_error_title).b(R.string.dialog_identify_net_error_msg).a();
            case 602:
                return new a(context).c(17301543).a(R.string.dialog_identify_imei_full_title).b(R.string.dialog_identify_imei_full_msg).a();
            case 603:
                return new a(context).c(17301543).a(R.string.dialog_identify_imei_uin_no_buy_title).b(R.string.dialog_identify_imei_uin_no_buy_msg).a();
            case 604:
                return new a(context).c(17301543).a(R.string.dialog_identify_no_login_title).b(R.string.dialog_identify_no_login_msg).a();
            case 605:
                return new a(context).c(17301543).a("同步").a();
            case 606:
            case 609:
                return new a(context).c(R.drawable.alert_dialog_icon).a(R.string.history_dialog_tip).a();
            case 607:
                return new a(context).c(R.drawable.alert_dialog_icon).a(R.string.history_dialog_tip).a();
            case 608:
                return new a(context).c(R.drawable.alert_dialog_icon).a(R.string.history_dialog_tip).a();
            case ErrorCode.STATIC_TBS_INSTALL_SMART_INSTALL_TBS_FINAL_EXCEPTION /*610*/:
                return new a(context).c(R.drawable.alert_dialog_icon).a(R.string.history_dialog_tip).a();
            case 611:
                return new a(context).c(R.drawable.alert_dialog_icon).a(R.string.dialog_shortcut_title).b(R.string.net_switch_note).a();
            case 802:
                return new a(context).c(R.drawable.alert_dialog_icon).a(R.string.history_dialog_tip).b(R.string.chapter_load_failed).a();
            default:
                return null;
        }
    }
}
