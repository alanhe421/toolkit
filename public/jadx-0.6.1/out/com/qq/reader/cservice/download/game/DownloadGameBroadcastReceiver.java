package com.qq.reader.cservice.download.game;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.qq.reader.common.c.a;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.utils.ao;
import com.qq.reader.view.af;
import com.tencent.android.tpush.common.Constants;
import com.tencent.qalsdk.core.c;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;

public class DownloadGameBroadcastReceiver extends BroadcastReceiver {
    public static final String a = DownloadGameBroadcastReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        a aVar = (a) l.b(1006);
        if (aVar != null) {
            int intExtra = intent.getIntExtra("action", -1);
            if (intExtra == 1) {
                for (g e : aVar.a()) {
                    aVar.e(e);
                }
            } else if (intExtra == 2) {
                String stringExtra = intent.getStringExtra("gameName");
                String stringExtra2 = intent.getStringExtra("downloadUrl");
                r3 = intent.getStringExtra("iconUrl");
                String stringExtra3 = intent.getStringExtra("jumpBackUrl");
                String stringExtra4 = intent.getStringExtra(Constants.FLAG_PACKAGE_NAME);
                if (TextUtils.isEmpty(stringExtra)) {
                    af.a(context, (CharSequence) "下载数据不完整", 0).a();
                } else if (TextUtils.isEmpty(stringExtra4)) {
                    af.a(context, (CharSequence) "下载数据不完整", 0).a();
                } else {
                    File file = new File(a.bj, stringExtra.concat(ShareConstants.PATCH_SUFFIX));
                    if (file.exists()) {
                        ao.a.a(context, file);
                    } else if (TextUtils.isEmpty(stringExtra2) || !stringExtra2.startsWith(c.d)) {
                        af.a(context, (CharSequence) "下载连接不合法", 0).a();
                    } else {
                        r6 = new d(stringExtra, stringExtra2, r3, stringExtra3);
                        r6.a(stringExtra4);
                        r6.setId((long) stringExtra4.hashCode());
                        aVar.a(r6);
                    }
                }
            } else {
                long longExtra = intent.getLongExtra("gameId", -1);
                r3 = intent.getStringExtra("gameName");
                if (longExtra != 0) {
                    r6 = aVar.a(aVar.a(), longExtra);
                    if (r6 == null) {
                        try {
                            af.a(context, (CharSequence) "下载进程无效", 0).a();
                            ((NotificationManager) context.getSystemService("notification")).cancel(r3, (int) longExtra);
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    switch (intExtra) {
                        case 3:
                            aVar.c(r6);
                            return;
                        case 4:
                            aVar.e(r6);
                            return;
                        case 5:
                            aVar.d(r6);
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }
}
