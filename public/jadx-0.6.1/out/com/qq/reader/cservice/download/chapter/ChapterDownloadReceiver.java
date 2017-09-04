package com.qq.reader.cservice.download.chapter;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.d;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.view.af;
import java.util.List;

public class ChapterDownloadReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null && !extras.getBoolean("com.qq.reader.chapter.downloadresult", true)) {
            OnlineTag onlineTag = (OnlineTag) extras.getParcelable("com.qq.reader.OnlineTag");
            if (onlineTag != null) {
                List integerArrayList = extras.getIntegerArrayList("com.qq.reader.chapter.DownloadResult");
                a aVar = new a(onlineTag, context);
                aVar.b(integerArrayList);
                aVar.e();
                af.a(context, (CharSequence) "已重新开始下载", 0).a();
                Intent intent2 = new Intent("com.qq.reader.chapter.Restart");
                intent2.putExtra("bid", onlineTag.k());
                d.a(context).a(intent2);
                ((NotificationManager) context.getSystemService("notification")).cancel(26);
            }
        }
    }
}
