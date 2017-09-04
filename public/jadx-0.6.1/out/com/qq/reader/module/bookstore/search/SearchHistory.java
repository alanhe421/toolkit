package com.qq.reader.module.bookstore.search;

import org.json.JSONObject;

public class SearchHistory extends AbsSearchWords {
    private String mId = null;
    private long mKeyTime;

    public SearchHistory(long j, String str, int i) {
        super(str, i);
        this.mKeyTime = j;
    }

    public SearchHistory(String str, int i) {
        super(str, i);
    }

    public AbsSearchWords parseJson(JSONObject jSONObject) {
        return null;
    }

    public long getTime() {
        return this.mKeyTime;
    }

    public String getId() {
        return this.mId == null ? "" : this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }
}
