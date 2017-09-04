package org.java_websocket.drafts;

import com.tencent.connect.common.Constants;
import org.java_websocket.b.b;
import org.java_websocket.drafts.Draft.HandshakeState;
import org.java_websocket.exceptions.InvalidHandshakeException;

/* compiled from: Draft_17 */
public class a extends Draft_10 {
    public HandshakeState a(org.java_websocket.b.a aVar) throws InvalidHandshakeException {
        if (Draft_10.b(aVar) == 13) {
            return HandshakeState.MATCHED;
        }
        return HandshakeState.NOT_MATCHED;
    }

    public b a(b bVar) {
        super.a(bVar);
        bVar.a("Sec-WebSocket-Version", Constants.VIA_REPORT_TYPE_JOININ_GROUP);
        return bVar;
    }

    public Draft c() {
        return new a();
    }
}
