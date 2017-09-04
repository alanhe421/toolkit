package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata.Opcode;

/* compiled from: FrameBuilder */
public interface c extends Framedata {
    void a(ByteBuffer byteBuffer) throws InvalidDataException;

    void a(Opcode opcode);

    void a(boolean z);

    void b(boolean z);
}
