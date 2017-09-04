package com.qq.reader.module.readpage;

import com.qq.reader.common.db.handle.h;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.bookchapter.online.i;

public class AddLimitFreeBook2DBTask extends ReaderDBTask {
    private i mOnlineNoPayBook;

    public AddLimitFreeBook2DBTask(i iVar) {
        this.mOnlineNoPayBook = iVar;
    }

    public void run() {
        super.run();
        if (this.mOnlineNoPayBook != null) {
            h.b().a(this.mOnlineNoPayBook);
        }
    }

    public void execute() {
        g.a().a(this);
    }
}
