package com.qq.reader.liveshow.model.filter.queue;

import com.qq.reader.liveshow.model.filter.a.b;

/* compiled from: IMessageQueue */
public interface a {
    boolean add(b bVar);

    boolean addToFirst(b bVar);

    void clear();

    b get();
}
