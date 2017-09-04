package org.java_websocket;

import java.net.InetSocketAddress;
import org.java_websocket.framing.Framedata;

public interface WebSocket {
    void a(Framedata framedata);

    InetSocketAddress e();
}
