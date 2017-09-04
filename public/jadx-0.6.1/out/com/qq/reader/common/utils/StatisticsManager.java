package com.qq.reader.common.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.readertask.protocol.StatisticsUVTask;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.av.sdk.AVError;
import com.tencent.connect.common.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StatisticsManager {
    private static StatisticsManager e = null;
    a a;
    ReadWriteLock b;
    public long c;
    private Handler d;
    private Set<String> f;
    private int g;
    private long h;
    private long i;
    private LinkedList<String> j;
    private ArrayList<Node> k;
    private Map<String, Object> l;
    private Node m;

    public static class Node implements Serializable {
        String lmf;
        public JSONObject other;
        JSONObject stat_params;

        public Node(Node node) {
            this.lmf = node.lmf;
            this.stat_params = node.stat_params;
            this.other = node.other;
        }

        public Node() {
            this.other = new JSONObject();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("optime", System.currentTimeMillis());
                this.other.put("exstring", jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void putExtra(Map<String, String> map) {
            if (map != null && map.size() != 0) {
                try {
                    JSONObject jSONObject = new JSONObject(this.other.optString("exstring"));
                    if (jSONObject != null) {
                        for (String str : map.keySet()) {
                            jSONObject.put(str, (String) map.get(str));
                        }
                    }
                    this.other.put("exstring", jSONObject.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                Iterator keys;
                String str;
                jSONObject.put("lm_f", this.lmf);
                if (this.stat_params != null) {
                    keys = this.stat_params.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        jSONObject.put(str, this.stat_params.get(str));
                    }
                }
                if (this.other != null) {
                    keys = this.other.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        jSONObject.put(str, this.other.get(str));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jSONObject.toString();
        }
    }

    public static class UploadStatiticsTask extends ReaderProtocolJSONTask {
        private JSONObject mJsonObject;

        public UploadStatiticsTask(c cVar, JSONObject jSONObject) {
            super(cVar);
            this.mJsonObject = jSONObject;
            this.mUrl = e.I + "common/log";
            setFailedType(1);
        }

        protected void doConnectionSuccessToRDM(boolean z) {
            i.a("event_stat_event_upload", true, 0, 0, null, false, true, ReaderApplication.getApplicationImp().getApplicationContext());
        }

        protected void doConnectionErrorToRDM(boolean z, Exception exception) {
            if (z) {
                Map hashMap = new HashMap();
                if (exception != null) {
                    hashMap.put("Exception", exception.toString() + " || " + exception.getMessage());
                }
                i.a("event_stat_event_upload", false, 0, 0, hashMap, true, true, ReaderApplication.getApplicationImp().getApplicationContext());
            }
        }

        public String getRequestMethod() {
            return Constants.HTTP_POST;
        }

        public String getRequestContent() {
            return this.mJsonObject.toString();
        }

        public String getContentType() {
            return "application/json";
        }

        protected boolean interuptNoConn() {
            return true;
        }

        public boolean isRequestGzip() {
            return true;
        }

        protected String generateTaskKey() {
            String requestContent = getRequestContent();
            return TextUtils.isEmpty(requestContent) ? String.valueOf(System.currentTimeMillis()) : requestContent;
        }
    }

    class a extends HandlerThread implements Callback {
        final /* synthetic */ StatisticsManager a;

        public a(StatisticsManager statisticsManager, String str) {
            this.a = statisticsManager;
            super(str);
        }

        public boolean handleMessage(Message message) {
            boolean z = false;
            String str;
            switch (message.what) {
                case 10001:
                case com.tencent.android.tpush.common.Constants.CODE_LOGIC_REGISTER_IN_PROCESS /*10002*/:
                    String str2 = "" + System.currentTimeMillis() + this.a.k.hashCode();
                    if (this.a.k.size() > 0) {
                        this.a.l.put(str2, (ArrayList) this.a.k.clone());
                    }
                    this.a.k.clear();
                    if (message.what == 10001) {
                        for (Entry entry : this.a.l.entrySet()) {
                            str2 = (String) entry.getKey();
                            ArrayList arrayList = (ArrayList) entry.getValue();
                            if (!this.a.f.contains(str2) && this.a.a(str2, arrayList, true)) {
                                this.a.f.add(str2);
                            }
                        }
                        break;
                    }
                    break;
                case 10003:
                    if (message.obj != null && (message.obj instanceof String)) {
                        str = (String) message.obj;
                        this.a.l.remove(str);
                        this.a.f.remove(str);
                        break;
                    }
                case 10004:
                    StatisticsManager statisticsManager = this.a;
                    if (message.arg1 == 1) {
                        z = true;
                    }
                    statisticsManager.b(z);
                    break;
                case AVError.AV_ERR_SERVER_CONNECT_ROOM_FAIL /*10005*/:
                    this.a.c(false);
                    break;
                case 10006:
                    if (message.obj != null && (message.obj instanceof String)) {
                        this.a.f.remove((String) message.obj);
                    }
                    g.a().a(new StatisticsUVTask());
                    break;
                case com.tencent.android.tpush.common.Constants.CODE_SERVICE_DISABLED /*10007*/:
                    this.a.j.clear();
                    break;
                case 10008:
                    str = (String) message.obj;
                    if (!TextUtils.isEmpty(str)) {
                        this.a.j.add(str);
                        break;
                    }
                    break;
            }
            return true;
        }
    }

    class b implements c {
        String a;
        final /* synthetic */ StatisticsManager b;

        public b(StatisticsManager statisticsManager, String str) {
            this.b = statisticsManager;
            this.a = str;
        }

        public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.b.g = jSONObject.optInt("code");
                if (!jSONObject.isNull("rt")) {
                    long optInt = (long) (jSONObject.optInt("rt") * 1000);
                    if (optInt > 120000) {
                        this.b.h = optInt;
                        d.b(ReaderApplication.getApplicationImp(), this.b.h);
                    }
                }
                if (this.b.g == 0) {
                    Message obtainMessage = this.b.d.obtainMessage();
                    obtainMessage.what = 10003;
                    obtainMessage.obj = this.a;
                    this.b.d.sendMessage(obtainMessage);
                    return;
                }
                a();
            } catch (Exception e) {
                e.printStackTrace();
                a();
            }
        }

        public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            com.qq.reader.common.monitor.debug.c.c("stat", "UploadError  " + exception + " key：  " + this.a, true);
            a();
        }

        private void a() {
            Message obtainMessage = this.b.d.obtainMessage();
            obtainMessage.what = 10006;
            obtainMessage.obj = this.a;
            this.b.d.sendMessage(obtainMessage);
        }
    }

    public static synchronized StatisticsManager a() {
        StatisticsManager statisticsManager;
        synchronized (StatisticsManager.class) {
            if (e == null) {
                e = new StatisticsManager();
            }
            statisticsManager = e;
        }
        return statisticsManager;
    }

    private StatisticsManager() {
        this.f = Collections.synchronizedSet(new HashSet());
        this.b = new ReentrantReadWriteLock();
        this.h = -1;
        this.i = 0;
        this.j = new LinkedList();
        this.c = 0;
        this.k = new ArrayList();
        this.l = Collections.synchronizedMap(new LocalHashMap(3) {
            public String decode(Object obj) {
                ArrayList arrayList = (ArrayList) obj;
                JSONArray jSONArray = new JSONArray();
                int i = 0;
                while (arrayList != null && i < arrayList.size()) {
                    jSONArray.put(((Node) arrayList.get(i)).toString());
                    i++;
                }
                return jSONArray.toString();
            }

            public Object encode(String str) {
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    while (jSONArray != null && i < jSONArray.length()) {
                        String str2 = (String) jSONArray.opt(i);
                        if (str2 != null) {
                            Node node = new Node();
                            node.other = new JSONObject(str2);
                            arrayList.add(node);
                        }
                        i++;
                    }
                    return arrayList;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        this.m = null;
        this.h = d.m(ReaderApplication.getApplicationImp());
        this.a = new a(this, "StatisticsThread");
        this.a.setPriority(1);
        this.a.start();
        this.d = new Handler(this.a.getLooper(), this.a);
    }

    public void b() {
        this.d.sendMessage(this.d.obtainMessage(com.tencent.android.tpush.common.Constants.CODE_SERVICE_DISABLED));
    }

    public void a(String str) {
        Message obtainMessage = this.d.obtainMessage();
        this.d.sendMessage(obtainMessage);
        obtainMessage.obj = str;
    }

    public StatisticsManager a(int i) {
        try {
            e().other.put("type", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public StatisticsManager b(String str) {
        try {
            e().other.put("pagename", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public StatisticsManager c(String str) {
        try {
            e().other.put("lm_f", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public StatisticsManager d(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                e().stat_params = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public void a(String str, Map<String, String> map) {
        a(str, (Map) map, false);
    }

    public void a(String str, Map<String, String> map, boolean z) {
        a(str, (Map) map, 100, z);
    }

    public void a(String str, Map<String, String> map, int i, boolean z) {
        final Map hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        final String str2 = str;
        final int i2 = i;
        final boolean z2 = z;
        g.a().a(new ReaderShortTask() {
            public void run() {
                super.run();
                StatisticsManager.this.a("event", str2);
                StatisticsManager.this.a(i2);
                StatisticsManager.this.a(hashMap);
                StatisticsManager.this.a(z2);
            }
        });
    }

    private void a(Map<String, String> map) {
        e().putExtra(map);
    }

    public StatisticsManager a(String str, Object obj) {
        try {
            e().other.putOpt(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public StatisticsManager e(String str) {
        try {
            e().other.put("bid", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public StatisticsManager f(String str) {
        try {
            e().other.put("searchkey", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void c() {
        a(false);
    }

    public void a(boolean z) {
        int i = 1;
        com.qq.reader.common.monitor.debug.c.c(s.ORIGIN, "stat commit  :  " + e().toString(), true);
        Message obtainMessage = this.d.obtainMessage();
        obtainMessage.what = 10004;
        if (!z) {
            i = 0;
        }
        obtainMessage.arg1 = i;
        this.d.sendMessage(obtainMessage);
    }

    public void d() {
        this.d.sendEmptyMessage(AVError.AV_ERR_SERVER_CONNECT_ROOM_FAIL);
    }

    private void b(boolean z) {
        if (this.h <= 0) {
            this.h = 120000;
        }
        if (this.j.size() > 0) {
            c((String) this.j.getLast());
            e().lmf = (String) this.j.getFirst();
            this.j.clear();
        }
        Object obj = null;
        try {
            obj = e().other.optString("pagename");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.k.add(new Node(e()));
        try {
            this.j.add(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        f();
        c(z);
    }

    private synchronized Node e() {
        if (this.m == null) {
            this.m = new Node();
        }
        return this.m;
    }

    private synchronized void f() {
        this.m = null;
    }

    private void c(boolean z) {
        if (ao.e(ReaderApplication.getApplicationImp())) {
            if (ao.f(ReaderApplication.getApplicationImp()) || z) {
                this.d.removeMessages(10001);
                this.d.sendMessage(this.d.obtainMessage(10001));
            } else if (this.k.size() > 12) {
                this.d.removeMessages(10001);
                this.d.sendMessage(this.d.obtainMessage(10001));
            } else if (!this.d.hasMessages(10001)) {
                this.d.sendMessageDelayed(this.d.obtainMessage(10001), this.h);
            }
        } else if (this.k.size() > 12 || z) {
            this.d.removeMessages(10001);
            this.d.sendMessage(this.d.obtainMessage(com.tencent.android.tpush.common.Constants.CODE_LOGIC_REGISTER_IN_PROCESS));
        } else if (!this.d.hasMessages(10001)) {
            this.d.sendMessageDelayed(this.d.obtainMessage(10001), this.h);
        }
    }

    public List<Node> b(int i) {
        List<Node> arrayList = new ArrayList();
        int size = this.k.size();
        for (int i2 = 0; i2 < size; i2++) {
            Node node = (Node) this.k.get(i2);
            if (node != null && node.other.optInt("type") == i) {
                arrayList.add(node);
            }
        }
        for (Entry value : this.l.entrySet()) {
            ArrayList arrayList2 = (ArrayList) value.getValue();
            if (arrayList2 != null) {
                int size2 = arrayList2.size();
                for (size = 0; size < size2; size++) {
                    node = (Node) arrayList2.get(size);
                    if (node.other.optInt("type") == i) {
                        arrayList.add(node);
                    }
                }
            }
        }
        return arrayList;
    }

    public StatisticsManager a(Bundle bundle) {
        if (bundle != null) {
            Object string = bundle.getString("KEY_JUMP_PAGENAME");
            if (string == null || string.length() == 0) {
                string = com.qq.reader.module.bookstore.qnative.c.a(bundle.getString("KEY_ACTION"));
            }
            Object string2 = bundle.getString("KEY_ACTIONTAG");
            if (string2 != null && string2.length() > 0) {
                a().a("act_tag", string2);
            }
            string2 = bundle.getString("KEY_ACTIONID");
            if (string2 != null && string2.length() > 0) {
                a().a("act_id", string2);
            }
            long j = bundle.getLong("URL_BUILD_PERE_BOOK_ID", 0);
            if (j != 0) {
                a().a("bid", Long.valueOf(j));
            }
            int i = bundle.getInt("KEY_PAGEINDEX", 1);
            if (i > 0) {
                a().a("pageindex", Integer.valueOf(i));
            }
            string2 = bundle.getString("KEY_CARD_ID");
            if (!TextUtils.isEmpty(string2)) {
                a().a("c_id", string2);
            }
            j = bundle.getLong("frombid");
            if (j > 0) {
                a().a("frombid", Long.valueOf(j));
            }
            string2 = bundle.getString(s.ORIGIN);
            if (!TextUtils.isEmpty(string2)) {
                a().a(s.ORIGIN, string2);
            }
            string2 = bundle.getString("advid");
            if (!TextUtils.isEmpty(string2)) {
                a().a("advid", string2);
            }
            if (!TextUtils.isEmpty(string)) {
                a().a("pagename", string);
            }
            string = bundle.getString(s.ALG);
            if (!TextUtils.isEmpty(string)) {
                a().a(s.ALG, string);
            }
            string = bundle.getString("itemid");
            if (!TextUtils.isEmpty(string)) {
                a().a("itemid", string);
            }
            String string3 = bundle.getString("searchkey");
            if (!TextUtils.isEmpty(string3)) {
                a().f(string3);
            }
            a().d(bundle.getString(s.STATPARAM_KEY));
            string = bundle.getString("key");
            if (!TextUtils.isEmpty(string)) {
                a().a("key", string);
            }
            string = bundle.getString("page");
            if (!TextUtils.isEmpty(string)) {
                a().a("page", string);
            }
            string = bundle.getString("index");
            if (!TextUtils.isEmpty(string)) {
                a().a("index", string);
            }
        }
        return this;
    }

    private boolean a(String str, ArrayList<Node> arrayList, boolean z) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Iterator keys;
            Node node = (Node) it.next();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("lm_f", node.lmf);
            com.qq.reader.common.monitor.debug.c.e("stat", "build " + node.stat_params);
            if (node.stat_params != null) {
                keys = node.stat_params.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    jSONObject2.put(str2, node.stat_params.get(str2));
                }
            }
            try {
                if (node.other != null) {
                    keys = node.other.keys();
                    while (keys.hasNext()) {
                        str2 = (String) keys.next();
                        jSONObject2.put(str2, node.other.get(str2));
                    }
                }
                if (!jSONObject2.has("type")) {
                    jSONObject2.put("type", 1);
                }
                if (!jSONObject2.has("frombid")) {
                    jSONObject2.put("frombid", 0);
                }
                jSONArray.put(jSONObject2);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        jSONObject.put("rc", jSONArray);
        if (!z) {
            return false;
        }
        com.qq.reader.common.monitor.debug.c.a("stat", "total = " + jSONObject.toString());
        a(jSONObject, str);
        return true;
    }

    private void a(JSONObject jSONObject, String str) {
        ReaderTask uploadStatiticsTask = new UploadStatiticsTask(new b(this, str), jSONObject);
        uploadStatiticsTask.setPriority(1);
        g.a().a(uploadStatiticsTask);
    }
}
