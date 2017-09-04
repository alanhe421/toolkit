package oicq.wlogin_sdk.report;

import com.tencent.android.tpush.common.MessageKey;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.TreeMap;
import oicq.wlogin_sdk.tools.util;
import org.json.JSONArray;
import org.json.JSONObject;

public class report_t2 extends report_t {
    private static final long serialVersionUID = 1;
    public long _app;
    public long[] _app_list;
    public TreeMap<Integer, report_t3> _log;
    public String _name;
    public String _oper;
    public int _rlen;
    public int _rst1;
    public int _rst2;
    public int _slen;
    public long _start;
    public long _sub_app;
    public String _type;
    public long _uin;
    public int _used;
    public int attr;

    public report_t2(String str, String str2, long j, long j2, long j3, long[] jArr) {
        this._type = new String("");
        this._oper = new String("");
        this._start = 0;
        this._used = 0;
        this._uin = 0;
        this._name = new String("");
        this._app = 0;
        this._rst1 = 0;
        this._rst2 = 0;
        this._sub_app = 0;
        this._app_list = null;
        this._rlen = 0;
        this._slen = 0;
        this._log = new TreeMap();
        this._type = str;
        this._oper = str2;
        this._start = j;
        this._app = j2;
        this._sub_app = j3;
        this._app_list = jArr;
    }

    public report_t2(int i) {
        this._type = new String("");
        this._oper = new String("");
        this._start = 0;
        this._used = 0;
        this._uin = 0;
        this._name = new String("");
        this._app = 0;
        this._rst1 = 0;
        this._rst2 = 0;
        this._sub_app = 0;
        this._app_list = null;
        this._rlen = 0;
        this._slen = 0;
        this._log = new TreeMap();
        this._type = "login";
        this._oper = "null";
        this._start = System.currentTimeMillis();
        this._app = 0;
        this._sub_app = 0;
        this._app_list = null;
        this.attr = i;
    }

    public void commit(long j, String str, int i, int i2) {
        this._uin = j;
        this._name = str;
        this._rst1 = i;
        this._rst2 = i2;
        this._used = (int) (System.currentTimeMillis() - this._start);
    }

    public void add_t3(report_t3 oicq_wlogin_sdk_report_report_t3) {
        this._rlen += oicq_wlogin_sdk_report_report_t3._rlen;
        this._slen += oicq_wlogin_sdk_report_report_t3._slen;
        this._log.put(Integer.valueOf(this._log.size()), oicq_wlogin_sdk_report_report_t3);
    }

    public void clear_t3() {
        this._log.clear();
    }

    public JSONObject toJasonObj() {
        JSONObject jSONObject;
        Exception exception;
        Writer stringWriter;
        PrintWriter printWriter;
        try {
            JSONObject jSONObject2 = new JSONObject();
            try {
                int i;
                jSONObject2.put("type", this._type);
                jSONObject2.put("oper", this._oper);
                jSONObject2.put(MessageKey.MSG_ACCEPT_TIME_START, String.format("%d", new Object[]{Long.valueOf((this._start / 1000) & 4294967295L)}));
                jSONObject2.put("used", String.format("%d", new Object[]{Long.valueOf(((long) this._used) & 4294967295L)}));
                jSONObject2.put("uin", String.format("%d", new Object[]{Long.valueOf(this._uin & 4294967295L)}));
                jSONObject2.put("app", String.format("%d", new Object[]{Long.valueOf(this._app & 4294967295L)}));
                jSONObject2.put("subapp", String.format("%d", new Object[]{Long.valueOf(this._sub_app & 4294967295L)}));
                jSONObject2.put("email", this._name);
                jSONObject2.put("attr", this.attr);
                Object obj = "";
                if (this._app_list != null) {
                    for (i = 0; i < this._app_list.length; i++) {
                        if (i == this._app_list.length - 1) {
                            obj = obj + String.format("%d", new Object[]{Long.valueOf(this._app_list[i] & 4294967295L)});
                        } else {
                            obj = obj + String.format("%d,", new Object[]{Long.valueOf(this._app_list[i] & 4294967295L)});
                        }
                    }
                }
                jSONObject2.put("applist", obj);
                jSONObject2.put("rst1", String.format("%d", new Object[]{Long.valueOf(((long) this._rst1) & 4294967295L)}));
                jSONObject2.put("rst2", String.format("%d", new Object[]{Long.valueOf(((long) this._rst2) & 4294967295L)}));
                JSONArray jSONArray = new JSONArray();
                i = 0;
                for (Object obj2 : this._log.keySet()) {
                    jSONArray.put(i, ((report_t3) this._log.get(obj2)).toJasonObj());
                    i++;
                }
                jSONObject2.put("log", jSONArray);
                return jSONObject2;
            } catch (Exception e) {
                Exception exception2 = e;
                jSONObject = jSONObject2;
                exception = exception2;
                stringWriter = new StringWriter();
                printWriter = new PrintWriter(stringWriter, true);
                exception.printStackTrace(printWriter);
                printWriter.flush();
                stringWriter.flush();
                util.LOGD("exception", stringWriter.toString());
                return jSONObject;
            }
        } catch (Exception e2) {
            exception = e2;
            jSONObject = null;
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter, true);
            exception.printStackTrace(printWriter);
            printWriter.flush();
            stringWriter.flush();
            util.LOGD("exception", stringWriter.toString());
            return jSONObject;
        }
    }
}
