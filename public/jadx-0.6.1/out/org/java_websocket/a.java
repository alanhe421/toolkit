package org.java_websocket;

import java.net.InetSocketAddress;
import org.java_websocket.b.e;
import org.java_websocket.b.h;
import org.java_websocket.b.i;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.framing.d;

/* compiled from: WebSocketAdapter */
public abstract class a implements c {
    public i a(WebSocket webSocket, Draft draft, org.java_websocket.b.a aVar) throws InvalidDataException {
        return new e();
    }

    public void a(WebSocket webSocket, org.java_websocket.b.a aVar, h hVar) throws InvalidDataException {
    }

    public void a(WebSocket webSocket, org.java_websocket.b.a aVar) throws InvalidDataException {
    }

    public void c(WebSocket webSocket, Framedata framedata) {
    }

    public void b(WebSocket webSocket, Framedata framedata) {
        Framedata dVar = new d(framedata);
        dVar.a(Opcode.PONG);
        webSocket.a(dVar);
    }

    public void a(WebSocket webSocket, Framedata framedata) {
    }

    public String a(WebSocket webSocket) throws InvalidDataException {
        InetSocketAddress e = webSocket.e();
        if (e == null) {
            throw new InvalidHandshakeException("socket not bound");
        }
        StringBuffer stringBuffer = new StringBuffer(90);
        stringBuffer.append("<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"");
        stringBuffer.append(e.getPort());
        stringBuffer.append("\" /></cross-domain-policy>\u0000");
        return stringBuffer.toString();
    }
}
