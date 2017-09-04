package org.java_websocket.framing;

import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;

/* compiled from: CloseFrame */
public interface a extends Framedata {
    int a() throws InvalidFrameException;

    String b() throws InvalidDataException;
}
