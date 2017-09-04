package oicq.wlogin_sdk.report;

import java.util.TreeMap;
import oicq.wlogin_sdk.tools.util;
import org.json.JSONArray;
import org.json.JSONObject;

public class report_t1 extends report_t {
    private static final long serialVersionUID = 1;
    public String _app_n = new String("");
    public String _app_sig = new String("");
    public String _app_v = new String("");
    public String _btime = util.get_release_time();
    public String _bver = util.SDK_VERSION;
    public String _device = new String("");
    public String _disp_name = new String("");
    public String _ksid = new String("");
    public TreeMap<Integer, report_t2> _lst = new TreeMap();
    public int _os = 2;
    public String _os_v = new String("");
    public String _sdk_v = new Integer(5).toString();

    public synchronized void commit(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this._os_v = str;
        this._app_v = str2;
        this._ksid = str4;
        this._app_n = str5;
        this._disp_name = str6;
        this._device = str7;
        this._app_sig = str8;
        this._btime = str9;
        this._bver = str10;
    }

    public synchronized void add_t2(report_t2 oicq_wlogin_sdk_report_report_t2) {
        if (this._lst.size() >= 10) {
            this._lst.remove(Integer.valueOf(this._lst.size() - 1));
        }
        this._lst.put(Integer.valueOf(this._lst.size()), oicq_wlogin_sdk_report_report_t2);
    }

    public synchronized void commit_t2(long j, String str, int i, int i2) {
        if (this._lst.size() > 0) {
            ((report_t2) this._lst.get(Integer.valueOf(this._lst.size() - 1))).commit(j, str, i, i2);
            if (util.LCB != null) {
                util.LCB.OnLog(((report_t2) this._lst.get(Integer.valueOf(this._lst.size() - 1))).toJasonObj());
            }
        }
    }

    public synchronized void add_t3(report_t3 oicq_wlogin_sdk_report_report_t3) {
        if (this._lst.size() > 0) {
            ((report_t2) this._lst.get(Integer.valueOf(this._lst.size() - 1))).add_t3(oicq_wlogin_sdk_report_report_t3);
        }
    }

    public synchronized void clear_t2() {
        for (Object obj : this._lst.keySet()) {
            ((report_t2) this._lst.get(obj)).clear_t3();
        }
        this._lst.clear();
    }

    public synchronized void attr_api(int i) {
        add_t2(new report_t2(i));
    }

    public synchronized JSONObject toJasonObj() {
        JSONObject jSONObject;
        int i = 0;
        synchronized (this) {
            jSONObject = null;
            try {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("os", String.format("%d", new Object[]{Integer.valueOf(this._os)}));
                    jSONObject2.put("os_v", this._os_v);
                    jSONObject2.put("app_v", this._app_v);
                    jSONObject2.put("sdk_v", this._sdk_v);
                    jSONObject2.put("ksid", this._ksid);
                    jSONObject2.put("app_n", this._app_n);
                    jSONObject2.put("disp_name", this._disp_name);
                    jSONObject2.put("device", this._device);
                    jSONObject2.put("app_sig", this._app_sig);
                    jSONObject2.put("btime", this._btime);
                    jSONObject2.put("bver", this._bver);
                    JSONArray jSONArray = new JSONArray();
                    for (Object obj : this._lst.keySet()) {
                        jSONArray.put(i, ((report_t2) this._lst.get(obj)).toJasonObj());
                        i++;
                    }
                    jSONObject2.put("lst", jSONArray);
                    jSONObject = jSONObject2;
                } catch (Exception e) {
                    jSONObject = jSONObject2;
                }
            } catch (Exception e2) {
            } catch (Throwable th) {
            }
        }
        return jSONObject;
    }
}
