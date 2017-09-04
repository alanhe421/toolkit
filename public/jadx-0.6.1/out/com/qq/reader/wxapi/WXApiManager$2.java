package com.qq.reader.wxapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.framework.mark.Mark;
import com.tencent.feedback.proguard.R;
import com.tencent.mm.sdk.modelmsg.GetMessageFromWX.Resp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.tinker.android.dx.instruction.Opcodes;

class WXApiManager$2 implements Runnable {
    final /* synthetic */ Mark a;
    final /* synthetic */ String b;
    final /* synthetic */ WXWebpageObject c;
    final /* synthetic */ String d;
    final /* synthetic */ WXApiManager e;

    WXApiManager$2(WXApiManager wXApiManager, Mark mark, String str, WXWebpageObject wXWebpageObject, String str2) {
        this.e = wXApiManager;
        this.a = mark;
        this.b = str;
        this.c = wXWebpageObject;
        this.d = str2;
    }

    public void run() {
        Bitmap a = c.a(WXApiManager.a(this.e)).a(this.a.getImagePath());
        if (this.b == null) {
            WXApiManager.a(this.e, new Req(new Bundle()));
            WXApiManager.b(this.e).message.mediaObject = this.c;
            if (this.d.length() > 0) {
                WXApiManager.b(this.e).message.description = "作者： " + this.d;
            }
            WXApiManager.b(this.e).message.title = this.a.getBookName();
            WXApiManager.b(this.e).transaction = WXApiManager.a(this.e, "reader");
            if (a != null) {
                WXApiManager.b(this.e).message.setThumbImage(WXApiManager.a(this.e, a, Opcodes.OR_INT, Opcodes.OR_INT, true));
            } else {
                WXApiManager.b(this.e).message.setThumbImage(BitmapFactory.decodeResource(WXApiManager.a(this.e).getResources(), R.drawable.icon));
            }
            this.e.getWXAPIInterface().sendReq(WXApiManager.b(this.e));
            return;
        }
        WXApiManager.a(this.e, new Resp(new Bundle()));
        WXApiManager.c(this.e).message.mediaObject = this.c;
        if (this.d.length() > 0) {
            WXApiManager.c(this.e).message.description = "作者： " + this.a.getAuthor();
        }
        WXApiManager.c(this.e).message.title = this.a.getBookName();
        WXApiManager.c(this.e).transaction = this.b;
        if (a != null) {
            WXApiManager.c(this.e).message.setThumbImage(WXApiManager.a(this.e, a, Opcodes.OR_INT, Opcodes.OR_INT, true));
        } else {
            WXApiManager.c(this.e).message.setThumbImage(BitmapFactory.decodeResource(WXApiManager.a(this.e).getResources(), R.drawable.icon));
        }
        this.e.getWXAPIInterface().sendResp(WXApiManager.c(this.e));
    }
}
