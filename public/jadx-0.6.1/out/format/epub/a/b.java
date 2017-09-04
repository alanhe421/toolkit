package format.epub.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.j;
import format.epub.view.a;
import format.epub.view.i;
import format.epub.view.m;
import format.epub.view.o;
import oicq.wlogin_sdk.request.WtloginHelper;
import tencent.tls.platform.SigType;

/* compiled from: ProcessHyperlinkAction */
public class b {
    public void a(Context context, a aVar, a aVar2) {
        i B = aVar.B();
        if (B instanceof o) {
            m mVar = ((o) B).d;
            switch (mVar.a) {
                case (byte) 1:
                    a(mVar.b, aVar2);
                    return;
                case (byte) 2:
                    a(mVar.b, context);
                    return;
                default:
                    return;
            }
        }
    }

    private void a(String str, Context context) {
        if (str.indexOf("qqreader://") == -1) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            intent.setFlags(SigType.TLS);
            context.startActivity(intent);
            return;
        }
        j.a(30, 0);
        String replace = str.replace("qqreader://", "http://");
        if (replace.indexOf("=") != -1) {
            replace = replace + "&" + e.b(context);
        } else {
            if (!replace.endsWith("?")) {
                replace = replace + "?";
            }
            replace = replace + e.b(context);
        }
        Intent intent2 = new Intent();
        intent2.setClass(context, WebBrowserForContents.class);
        intent2.setFlags(WtloginHelper.SigType.WLOGIN_QRPUSH);
        intent2.putExtra("com.qq.reader.WebContent", replace);
        intent2.putExtra("fromType", "readerpage");
        context.startActivity(intent2);
    }

    private void a(String str, a aVar) {
        aVar.a(str);
    }
}
