package com.qq.reader.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import com.qq.reader.b;
import com.qq.reader.common.c.a;
import com.qq.reader.common.download.task.l;
import com.qq.reader.common.monitor.j;
import com.qq.reader.cservice.bookfollow.FollowBroadcastReceiver;
import com.qq.reader.cservice.download.book.e;
import com.tencent.tesla.soload.SoLoadCore;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class SwitchViewActivity extends Activity {
    public static Intent a;

    public void onCreate(Bundle bundle) {
        try {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            a.a(false);
            super.onCreate(bundle);
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            byte byteExtra = intent.getByteExtra("notification_tag", (byte) 0);
            Intent intent2 = new Intent();
            intent2.putExtras(extras);
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            switch (byteExtra) {
                case (byte) 2:
                    intent2.setClass(this, ReaderPageActivity.class);
                    intent2.setFlags(SigType.WLOGIN_QRPUSH);
                    notificationManager.cancel(1);
                    ((e) l.b(1001)).e();
                    b.a(intent2, this);
                    break;
                case (byte) 3:
                    intent2.setClass(this, MainActivity.class);
                    intent2.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
                    intent2.setAction("com.qq.reader.SwitchViewActivity");
                    BookShelfFragment.resetScrollType = (byte) 2;
                    notificationManager.cancel(1);
                    ((e) l.b(1001)).e();
                    startActivity(intent2);
                    break;
                case (byte) 22:
                    intent2.setClass(this, ReaderPageActivity.class);
                    intent2.setFlags(SigType.WLOGIN_QRPUSH);
                    notificationManager.cancel(24);
                    FollowBroadcastReceiver.b(this);
                    startActivity(intent2);
                    break;
                case (byte) 23:
                    j.a(108, 0);
                    intent2.setClass(this, MainActivity.class);
                    intent2.setFlags(SigType.WLOGIN_QRPUSH);
                    intent2.putExtra("IS_GOTO_BOOKSHELF", true);
                    BookShelfFragment.resetScrollType = (byte) 2;
                    notificationManager.cancel(24);
                    startActivity(intent2);
                    break;
                case (byte) 25:
                    intent2.setClass(this, ReaderPageActivity.class);
                    intent2.setFlags(SigType.WLOGIN_QRPUSH);
                    BookShelfFragment.resetScrollType = (byte) 2;
                    notificationManager.cancel(25);
                    startActivity(intent2);
                    break;
            }
            if (intent2 != null) {
                finish();
            }
            setIntent(null);
        } catch (Exception e) {
            finish();
        }
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
