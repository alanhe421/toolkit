package com.tencent;

import java.util.List;

public interface TIMMessageUpdateListener {
    boolean onMessagesUpdate(List<TIMMessage> list);
}
