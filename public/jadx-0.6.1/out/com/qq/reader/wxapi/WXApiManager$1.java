package com.qq.reader.wxapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.framework.mark.Mark;
import com.tencent.feedback.proguard.R;
import com.tencent.mm.sdk.modelmsg.GetMessageFromWX.Resp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.sdk.modelmsg.WXAppExtendObject;
import com.tencent.tinker.android.dx.instruction.Opcodes;

class WXApiManager$1 implements Runnable {
    final /* synthetic */ Mark a;
    final /* synthetic */ String b;
    final /* synthetic */ WXAppExtendObject c;
    final /* synthetic */ WXApiManager d;

    WXApiManager$1(WXApiManager wXApiManager, Mark mark, String str, WXAppExtendObject wXAppExtendObject) {
        this.d = wXApiManager;
        this.a = mark;
        this.b = str;
        this.c = wXAppExtendObject;
    }

    public void run() {
        String bookShortName = this.a.getBookShortName();
        Bitmap a = c.a(WXApiManager.a(this.d)).a(this.a.getImagePath());
        String author = this.a.getAuthor();
        if (this.b == null) {
            WXApiManager.a(this.d, new Req(new Bundle()));
            WXApiManager.b(this.d).message.mediaObject = this.c;
            if (author.length() > 0) {
                WXApiManager.b(this.d).message.description = "作者： " + this.a.getAuthor();
            }
            WXApiManager.b(this.d).message.title = bookShortName;
            WXApiManager.b(this.d).transaction = WXApiManager.a(this.d, "reader");
            if (a != null) {
                WXApiManager.b(this.d).message.setThumbImage(WXApiManager.a(this.d, a, Opcodes.OR_INT, Opcodes.OR_INT, true));
            } else {
                WXApiManager.b(this.d).message.setThumbImage(BitmapFactory.decodeResource(WXApiManager.a(this.d).getResources(), R.drawable.icon));
            }
            this.d.getWXAPIInterface().sendReq(WXApiManager.b(this.d));
            return;
        }
        WXApiManager.a(this.d, new Resp(new Bundle()));
        WXApiManager.c(this.d).message.mediaObject = this.c;
        if (author.length() > 0) {
            WXApiManager.c(this.d).message.description = "作者： " + this.a.getAuthor();
        }
        WXApiManager.c(this.d).message.title = bookShortName;
        WXApiManager.c(this.d).transaction = this.b;
        WXApiManager.c(this.d).message.setThumbImage(BitmapFactory.decodeResource(WXApiManager.a(this.d).getResources(), R.drawable.icon));
        if (a != null) {
            WXApiManager.c(this.d).message.setThumbImage(WXApiManager.a(this.d, a, Opcodes.OR_INT, Opcodes.OR_INT, true));
        } else {
            WXApiManager.c(this.d).message.setThumbImage(BitmapFactory.decodeResource(WXApiManager.a(this.d).getResources(), R.drawable.icon));
        }
        this.d.getWXAPIInterface().sendResp(WXApiManager.c(this.d));
    }
}
