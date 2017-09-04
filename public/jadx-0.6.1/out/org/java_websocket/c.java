package org.java_websocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import org.java_websocket.b.a;
import org.java_websocket.b.f;
import org.java_websocket.b.h;
import org.java_websocket.b.i;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;

/* compiled from: WebSocketListener */
public interface c {
    String a(WebSocket webSocket) throws InvalidDataException;

    i a(WebSocket webSocket, Draft draft, a aVar) throws InvalidDataException;

    void a(WebSocket webSocket, int i, String str);

    void a(WebSocket webSocket, int i, String str, boolean z);

    void a(WebSocket webSocket, Exception exception);

    void a(WebSocket webSocket, String str);

    void a(WebSocket webSocket, ByteBuffer byteBuffer);

    void a(WebSocket webSocket, a aVar) throws InvalidDataException;

    void a(WebSocket webSocket, a aVar, h hVar) throws InvalidDataException;

    void a(WebSocket webSocket, f fVar);

    void a(WebSocket webSocket, Framedata framedata);

    void b(WebSocket webSocket);

    void b(WebSocket webSocket, int i, String str, boolean z);

    void b(WebSocket webSocket, Framedata framedata);

    InetSocketAddress c(WebSocket webSocket);

    void c(WebSocket webSocket, Framedata framedata);
}
