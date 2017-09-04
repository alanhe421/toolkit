package com.qq.reader.cservice.booknews;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class BookNewsTask extends ReaderProtocolJSONTask {
    private int mDirection;

    public BookNewsTask(c cVar, int i, int i2, long j) {
        super(cVar);
        this.mDirection = i;
        switch (i) {
            case 0:
                this.mUrl = e.bo + "id=" + i2 + "&pTime=" + j + "&direction=asc";
                return;
            case 1:
                this.mUrl = e.bo + "id=" + i2 + "&pTime=" + j + "&direction=desc";
                return;
            default:
                return;
        }
    }

    public int getDirection() {
        return this.mDirection;
    }
}
