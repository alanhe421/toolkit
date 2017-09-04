package com.qq.reader.common.conn.socket;

import org.java_websocket.WebSocket;
import org.java_websocket.framing.Framedata;

/* compiled from: PushClientListener */
public interface c {
    void a();

    void a(int i, String str, boolean z, long j);

    void a(Exception exception, long j);

    void a(String str);

    void a(WebSocket webSocket, Framedata framedata);

    void b(WebSocket webSocket, Framedata framedata);
}
