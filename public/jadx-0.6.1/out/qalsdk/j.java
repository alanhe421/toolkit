package qalsdk;

import com.tencent.qalsdk.util.MsfSocketInputBuffer;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;

/* compiled from: MsfHttpResp */
public class j {
    private final MsfSocketInputBuffer a;
    private InputStream b;
    private StatusLine c;
    private Header[] d;
    private String e;
    private String f;
    private int g = -1;
    private String h;
    private String i;

    public j(MsfSocketInputBuffer msfSocketInputBuffer) {
        this.a = msfSocketInputBuffer;
    }

    public ProtocolVersion a() {
        return this.c.getProtocolVersion();
    }

    public StatusLine b() {
        return this.c;
    }

    public String toString() {
        return b() + " contentLen:" + f() + " transfer:" + this.i;
    }

    public void a(Header[] headerArr) {
        this.d = headerArr;
        for (int length = headerArr.length - 1; length >= 0; length--) {
            Header header = headerArr[length];
            if (header.getName().equalsIgnoreCase("Transfer-Encoding")) {
                this.i = header.getValue();
            } else if (header.getName().equalsIgnoreCase("Content-Length")) {
                this.g = Integer.parseInt(header.getValue());
            } else if (header.getName().equalsIgnoreCase("Connection")) {
                this.e = header.getValue();
            } else if (header.getName().equalsIgnoreCase("Content-Encoding")) {
                this.h = header.getValue();
            } else if (header.getName().equalsIgnoreCase("Content-Type")) {
                this.f = header.getValue();
            }
        }
    }

    public Header[] c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public int f() {
        return this.g;
    }

    public String g() {
        return this.h;
    }

    public String h() {
        return this.i;
    }

    public MsfSocketInputBuffer i() {
        return this.a;
    }

    public StatusLine j() {
        return this.c;
    }

    public void a(StatusLine statusLine) {
        this.c = statusLine;
    }

    public InputStream k() {
        return this.b;
    }

    public void a(InputStream inputStream) {
        this.b = inputStream;
    }
}
