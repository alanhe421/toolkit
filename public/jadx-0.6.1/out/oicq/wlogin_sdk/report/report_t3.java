package oicq.wlogin_sdk.report;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import oicq.wlogin_sdk.tools.util;
import org.json.JSONObject;

public class report_t3 extends report_t {
    private static final long serialVersionUID = 1;
    public int _cmd = 0;
    public int _conn = 0;
    public String _host = new String("");
    public String _ip = new String("");
    public int _net = 0;
    public int _port = 0;
    public int _rlen = 0;
    public int _rst2 = 0;
    public int _slen = 0;
    public String _str = new String("");
    public int _sub = 0;
    public int _try = 0;
    public int _used = 0;
    public int _wap = 0;

    public JSONObject toJasonObj() {
        JSONObject jSONObject;
        Exception e;
        Writer stringWriter;
        PrintWriter printWriter;
        try {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("cmd", String.format("0x%x", new Object[]{Integer.valueOf(this._cmd)}));
                jSONObject.put("sub", String.format("0x%x", new Object[]{Integer.valueOf(this._sub)}));
                jSONObject.put("rst2", String.format("%d", new Object[]{Integer.valueOf(this._rst2)}));
                jSONObject.put("used", String.format("%d", new Object[]{Integer.valueOf(this._used)}));
                jSONObject.put("try", String.format("%d", new Object[]{Integer.valueOf(this._try)}));
                jSONObject.put("host", this._host);
                jSONObject.put("ip", this._ip);
                jSONObject.put("port", String.format("%d", new Object[]{Integer.valueOf(this._port)}));
                jSONObject.put("conn", String.format("%d", new Object[]{Integer.valueOf(this._conn)}));
                jSONObject.put("net", String.format("%d", new Object[]{Integer.valueOf(this._net)}));
                jSONObject.put("str", this._str);
                jSONObject.put("rlen", String.format("%d", new Object[]{Integer.valueOf(this._rlen)}));
                jSONObject.put("slen", String.format("%d", new Object[]{Integer.valueOf(this._slen)}));
                jSONObject.put("wap", String.format("%d", new Object[]{Integer.valueOf(this._wap)}));
            } catch (Exception e2) {
                e = e2;
                stringWriter = new StringWriter();
                printWriter = new PrintWriter(stringWriter, true);
                e.printStackTrace(printWriter);
                printWriter.flush();
                stringWriter.flush();
                util.LOGD("exception", stringWriter.toString());
                return jSONObject;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            jSONObject = null;
            e = exception;
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter, true);
            e.printStackTrace(printWriter);
            printWriter.flush();
            stringWriter.flush();
            util.LOGD("exception", stringWriter.toString());
            return jSONObject;
        }
        return jSONObject;
    }
}
