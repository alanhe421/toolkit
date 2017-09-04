package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.qq.reader.common.monitor.a.a;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.tencent.qalsdk.util.BaseApplication;

class ChannelHandler$1 extends ReaderDBTask {
    final /* synthetic */ j this$0;
    final /* synthetic */ a val$c;

    ChannelHandler$1(j jVar, a aVar) {
        this.this$0 = jVar;
        this.val$c = aVar;
    }

    public void run() {
        super.run();
        if (this.this$0.b(this.val$c.a()) == null) {
            try {
                SQLiteDatabase a = j.a.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("onlineid", this.val$c.a());
                contentValues.put(BaseApplication.DATA_KEY_CHANNEL_ID, this.val$c.b());
                a.replace("channel", null, contentValues);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                j.a.c();
            }
        }
    }
}
