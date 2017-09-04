package com.tencent.av;

import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

final class aa implements Runnable {
    private /* synthetic */ Selector a;
    private /* synthetic */ List b;
    private /* synthetic */ PingUtil c;

    aa(PingUtil pingUtil, Selector selector, List list) {
        this.c = pingUtil;
        this.a = selector;
        this.b = list;
    }

    public final void run() {
        while (this.a.isOpen()) {
            try {
                if (this.a.select() != 0) {
                    Iterator it = this.a.selectedKeys().iterator();
                    while (it.hasNext()) {
                        if (((SelectionKey) it.next()).isReadable()) {
                            ByteBuffer allocate = ByteBuffer.allocate(ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE);
                            this.c.channel.read(allocate);
                            allocate.flip();
                            allocate.get();
                            allocate.getShort();
                            Calendar calendar = (Calendar) this.c.timeRecord.get(Integer.valueOf(allocate.getInt()));
                            if (calendar != null) {
                                this.b.add(Long.valueOf(Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis()));
                            }
                            it.remove();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
