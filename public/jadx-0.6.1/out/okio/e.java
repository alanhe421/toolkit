package okio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* compiled from: BufferedSource */
public interface e extends p {
    long a(byte b) throws IOException;

    String a(Charset charset) throws IOException;

    void a(long j) throws IOException;

    boolean a(long j, ByteString byteString) throws IOException;

    ByteString c(long j) throws IOException;

    c c();

    boolean e() throws IOException;

    InputStream f();

    byte[] f(long j) throws IOException;

    void g(long j) throws IOException;

    byte h() throws IOException;

    short i() throws IOException;

    int j() throws IOException;

    short k() throws IOException;

    int l() throws IOException;

    long m() throws IOException;

    String p() throws IOException;
}
