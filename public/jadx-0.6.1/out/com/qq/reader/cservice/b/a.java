package com.qq.reader.cservice.b;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.qq.reader.ReaderApplication;
import com.tencent.feedback.proguard.R;
import java.io.File;
import tencent.tls.platform.SigType;

/* compiled from: BookSnsComm */
public class a {
    public static void a(Context context, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("我正在用QQ阅读看一本书《");
        stringBuffer.append(str);
        stringBuffer.append("》，觉得不错，与大家一起分享。");
        stringBuffer.append("http://ubook.qq.com");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", ReaderApplication.getApplicationContext().getString(R.string.app_name) + "分享");
        intent.putExtra("android.intent.extra.TEXT", stringBuffer.toString());
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }

    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", context.getResources().getString(R.string.app_name) + "分享");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("我正在用QQ阅读看一本书《");
        stringBuffer.append(str);
        stringBuffer.append("》，觉得不错，与大家一起分享。");
        stringBuffer.append(str2);
        intent.putExtra("android.intent.extra.TEXT", stringBuffer.toString());
        intent.setType("text/plain");
        intent.setFlags(SigType.TLS);
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }

    public static void b(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", context.getResources().getString(R.string.app_name) + "分享");
        intent.putExtra("android.intent.extra.TEXT", str2);
        if (str == null || str.length() <= 0) {
            intent.setType("text/plain");
        } else {
            File file = new File(str);
            intent.setType("image/*");
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
        }
        intent.setFlags(SigType.TLS);
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }
}
