package com.tencent.qalhttp;

import com.tencent.qalsdk.im_open.http.ResponsePrivate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class QALHttpResponse {
    private JSONObject JSONResp = null;
    private long age = 0;
    private byte[] body = new byte[0];
    public int bodyLen = 0;
    private List<String> cache_control = null;
    private String content_type = null;
    private String date = null;
    private String etag = null;
    private String expires = null;
    private String last_modified = null;
    private String location = null;
    private Map<String, String> otherHeaders = null;
    private String pragma = null;
    public ResponsePrivate responsePrivate;
    private String server = null;
    private List<String> set_cookie = null;
    private int status;
    private String strResp = null;
    private String via = null;
    private List<String> x_cache = null;
    private List<String> x_cache_lookup = null;

    void setStatus(int i) {
        this.status = i;
    }

    public int getStatus() {
        return this.status;
    }

    void setOtherHeaders(Map<String, String> map) {
        this.otherHeaders = map;
    }

    public Map<String, String> getOtherHeaders() {
        return this.otherHeaders;
    }

    private String listToString(List<String> list) {
        if (list == null) {
            return null;
        }
        String str = new String();
        int i = 0;
        while (i < list.size()) {
            i++;
            str = (str + ((String) list.get(i))) + ",";
        }
        if (str.length() <= 2) {
            return null;
        }
        str.substring(0, str.length() - 1);
        return str;
    }

    public Map<String, String> getAllHeaders() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("Content-Type", this.content_type);
        hashMap.put("Location", this.location);
        hashMap.put("Date", this.date);
        hashMap.put("Server", this.server);
        hashMap.put("Via", this.via);
        hashMap.put("X-Cache", listToString(this.x_cache));
        hashMap.put("X-Cache-Lookup", listToString(this.x_cache_lookup));
        hashMap.put("Age", String.valueOf(this.age));
        hashMap.put("Last_Modified", this.last_modified);
        hashMap.put("Etag", this.etag);
        hashMap.put("Cache-Control", listToString(this.cache_control));
        hashMap.put("Expires", this.expires);
        hashMap.put("Pragma", this.pragma);
        hashMap.put("Set-Cookie", listToString(this.set_cookie));
        if (this.otherHeaders != null) {
            hashMap.putAll(this.otherHeaders);
        }
        return hashMap;
    }

    void setBody(byte[] bArr) {
        if (bArr != null) {
            this.body = bArr;
        }
    }

    public byte[] getBody() {
        return this.body;
    }

    void setContentType(String str) {
        this.content_type = str;
    }

    public String getContentType() {
        return this.content_type;
    }

    void setLocation(String str) {
        this.location = str;
    }

    public String getLocation() {
        return this.location;
    }

    void setDate(String str) {
        this.date = str;
    }

    public String getDate() {
        return this.date;
    }

    void setServer(String str) {
        this.server = str;
    }

    public String getServer() {
        return this.server;
    }

    void setVia(String str) {
        this.via = str;
    }

    public String getVia() {
        return this.via;
    }

    void setXCache(List<String> list) {
        this.x_cache = list;
    }

    public List<String> getXCache() {
        return this.x_cache;
    }

    void setXCacheLookup(List<String> list) {
        this.x_cache_lookup = list;
    }

    public List<String> getXCacheLookup() {
        return this.x_cache_lookup;
    }

    void setAge(long j) {
        this.age = j;
    }

    public long getAge() {
        return this.age;
    }

    void setLastModified(String str) {
        this.last_modified = str;
    }

    public String getLastModified() {
        return this.last_modified;
    }

    void setEtag(String str) {
        this.etag = str;
    }

    public String getEtag() {
        return this.etag;
    }

    void setCacheControl(List<String> list) {
        this.cache_control = list;
    }

    public List<String> getCacheControl() {
        return this.cache_control;
    }

    void setExpires(String str) {
        this.expires = str;
    }

    public String getExpires() {
        return this.expires;
    }

    void setPragma(String str) {
        this.pragma = str;
    }

    public String getPragma() {
        return this.pragma;
    }

    void setSetCookie(List<String> list) {
        this.set_cookie = list;
    }

    public List<String> getSetCookie() {
        return this.set_cookie;
    }

    void setStringResp(String str) {
        this.strResp = str;
    }

    public String getStringResp() {
        return this.strResp;
    }

    void setJSONObjectResp(JSONObject jSONObject) {
        this.JSONResp = jSONObject;
    }

    public JSONObject getJSONObjectResp() {
        return this.JSONResp;
    }
}
