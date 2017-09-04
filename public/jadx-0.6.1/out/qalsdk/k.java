package qalsdk;

import com.tencent.qalsdk.util.MsfSocketInputBuffer;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpVersion;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

/* compiled from: MsfHttpRespParse */
public class k {
    protected final LineParser a;
    private final int b;
    private final int c;
    private final CharArrayBuffer d;
    private j e;

    public k(MsfSocketInputBuffer msfSocketInputBuffer, LineParser lineParser, int i, int i2) throws IOException {
        this.e = new j(msfSocketInputBuffer);
        this.b = i;
        this.c = i2;
        this.a = BasicLineParser.DEFAULT;
        this.d = new CharArrayBuffer(128);
    }

    public k(MsfSocketInputBuffer msfSocketInputBuffer) throws IOException {
        this(msfSocketInputBuffer, new BasicLineParser(HttpVersion.HTTP_1_1), -1, -1);
    }

    protected Header[] a(int i, int i2, LineParser lineParser) throws HttpException, IOException {
        Header[] headerArr;
        int i3 = 0;
        if (lineParser == null) {
            lineParser = BasicLineParser.DEFAULT;
        }
        ArrayList arrayList = new ArrayList();
        CharArrayBuffer charArrayBuffer = null;
        CharArrayBuffer charArrayBuffer2 = null;
        while (true) {
            if (charArrayBuffer2 == null) {
                charArrayBuffer2 = new CharArrayBuffer(64);
            } else {
                charArrayBuffer2.clear();
            }
            if (this.e.i().readLine(charArrayBuffer2) == -1 || charArrayBuffer2.length() < 1) {
                headerArr = new Header[arrayList.size()];
            } else {
                CharArrayBuffer charArrayBuffer3;
                if ((charArrayBuffer2.charAt(0) == ' ' || charArrayBuffer2.charAt(0) == '\t') && charArrayBuffer != null) {
                    int i4 = 0;
                    while (i4 < charArrayBuffer2.length()) {
                        char charAt = charArrayBuffer2.charAt(i4);
                        if (charAt != ' ' && charAt != '\t') {
                            break;
                        }
                        i4++;
                    }
                    if (i2 <= 0 || ((charArrayBuffer.length() + 1) + charArrayBuffer2.length()) - i4 <= i2) {
                        charArrayBuffer.append(' ');
                        charArrayBuffer.append(charArrayBuffer2, i4, charArrayBuffer2.length() - i4);
                        charArrayBuffer3 = charArrayBuffer2;
                        charArrayBuffer2 = charArrayBuffer;
                    } else {
                        throw new IOException("Maximum line length limit exceeded");
                    }
                }
                arrayList.add(charArrayBuffer2);
                charArrayBuffer3 = null;
                if (i <= 0 || arrayList.size() < i) {
                    charArrayBuffer = charArrayBuffer2;
                    charArrayBuffer2 = charArrayBuffer3;
                } else {
                    throw new IOException("Maximum header count exceeded");
                }
            }
        }
        headerArr = new Header[arrayList.size()];
        while (i3 < arrayList.size()) {
            try {
                headerArr[i3] = lineParser.parseHeader((CharArrayBuffer) arrayList.get(i3));
                i3++;
            } catch (ParseException e) {
                throw new ProtocolException(e.getMessage());
            }
        }
        return headerArr;
    }

    private void c() throws IOException, HttpException, ParseException {
        this.d.clear();
        if (this.e.i().readLine(this.d) == -1) {
            throw new NoHttpResponseException("The target server failed to respond");
        }
        this.e.a(this.a.parseStatusLine(this.d, new ParserCursor(0, this.d.length())));
    }

    public j a() throws IOException, HttpException {
        try {
            c();
            this.e.a(a(this.b, this.c, this.a));
            return this.e;
        } catch (Throwable e) {
            throw new ProtocolException(e.getMessage(), e);
        }
    }

    public static boolean a(j jVar) {
        int statusCode = jVar.b().getStatusCode();
        return (statusCode < 200 || statusCode == 204 || statusCode == 304 || statusCode == 205) ? false : true;
    }

    public static MsfSocketInputBuffer a(Socket socket, int i) throws IOException {
        return new MsfSocketInputBuffer(socket, i, "US-ASCII", -1);
    }

    public boolean b() {
        return false;
    }
}
