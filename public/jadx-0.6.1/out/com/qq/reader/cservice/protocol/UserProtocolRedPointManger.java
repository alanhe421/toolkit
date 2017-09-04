package com.qq.reader.cservice.protocol;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserProtocolRedPointManger {
    public static UserProtocolRedPointManger a;
    private static String b = "http://common.reader.bookcs.3g.qq.com/common/v6_5_3/lawAgreements";
    private UserProtocolInfo c = null;
    private Context d;
    private String e;

    public static class UserProtocol implements Serializable {
        public int agreementId;
        public String title;
        public int versionId;

        public String toString() {
            return this.title + ":" + this.agreementId + ":" + this.versionId;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            UserProtocol userProtocol = (UserProtocol) obj;
            if (this.agreementId == userProtocol.agreementId && this.versionId == userProtocol.versionId) {
                return true;
            }
            return false;
        }
    }

    public static class UserProtocolInfo implements Serializable {
        public ArrayList<UserProtocol> protocols = new ArrayList();
    }

    public static UserProtocolRedPointManger a(Context context) {
        if (a == null) {
            a = new UserProtocolRedPointManger(context);
        }
        return a;
    }

    public UserProtocolRedPointManger(Context context) {
        this.d = context;
    }

    public void a() {
        ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask(new c(this) {
            final /* synthetic */ UserProtocolRedPointManger a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    this.a.e = str;
                    JSONObject jSONObject = new JSONObject(str);
                    if ("0".equals(jSONObject.optString("code"))) {
                        this.a.c = new UserProtocolInfo();
                        JSONArray optJSONArray = jSONObject.optJSONArray("lawArgeements");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                                UserProtocol userProtocol = new UserProtocol();
                                userProtocol.title = jSONObject2.optString("title");
                                userProtocol.agreementId = jSONObject2.getInt("agreementId");
                                userProtocol.versionId = jSONObject2.getInt("versionId");
                                this.a.c.protocols.add(userProtocol);
                            }
                            d.b(this.a.d, this.a.e());
                        }
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("UserProtocolRedPointManger", e.getMessage());
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        });
        readerProtocolJSONTask.setUrl(b);
        g.a().a(readerProtocolJSONTask);
    }

    public void b() {
        if (this.c != null && this.c.protocols != null && this.c.protocols != null && this.c.protocols.size() > 0) {
            d.b(this.d, TextUtils.isEmpty(this.e) ? "" : this.e);
        }
    }

    public ArrayList<UserProtocol> c() {
        ArrayList arrayList = new ArrayList();
        return a(d.g(this.d));
    }

    private ArrayList<UserProtocol> a(String str) {
        ArrayList<UserProtocol> arrayList = null;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if ("0".equals(jSONObject.optString("code"))) {
                    UserProtocolInfo userProtocolInfo = new UserProtocolInfo();
                    JSONArray optJSONArray = jSONObject.optJSONArray("lawArgeements");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            UserProtocol userProtocol = new UserProtocol();
                            userProtocol.title = jSONObject2.optString("title");
                            userProtocol.agreementId = jSONObject2.getInt("agreementId");
                            userProtocol.versionId = jSONObject2.getInt("versionId");
                            userProtocolInfo.protocols.add(userProtocol);
                        }
                        arrayList = userProtocolInfo.protocols;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public boolean d() {
        try {
            return d.f(this.d);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean e() {
        try {
            boolean z;
            ArrayList c = c();
            if (c != null || this.c == null) {
                z = false;
            } else {
                z = true;
            }
            if (c == null && this.c == null) {
                z = false;
            }
            if (c != null && this.c == null) {
                z = false;
            }
            if (c == null || this.c == null) {
                return z;
            }
            if (a(this.c.protocols, c)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean a(ArrayList<UserProtocol> arrayList, ArrayList<UserProtocol> arrayList2) {
        if (arrayList.size() != arrayList2.size()) {
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!arrayList2.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public void f() {
        if (this.c != null) {
            this.c = null;
        }
        this.e = "";
    }
}
