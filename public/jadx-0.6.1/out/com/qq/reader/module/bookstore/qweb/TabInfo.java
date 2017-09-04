package com.qq.reader.module.bookstore.qweb;

import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import java.io.Serializable;
import java.util.HashMap;

public class TabInfo implements Serializable {
    public static final String TITLE = "title";
    public static final String WEB_URL = "web_url";
    private static final long serialVersionUID = 6696031872468154516L;
    public HashMap<String, Object> args;
    public Class cls;
    public BaseFragment mFragment;
    public String title = null;
    public String url = null;

    public TabInfo(Class cls, String str, String str2, HashMap<String, Object> hashMap) {
        this.cls = cls;
        this.url = str;
        this.title = str2;
        if (hashMap != null) {
            hashMap.put("title", this.url);
            hashMap.put(WEB_URL, this.title);
        }
        this.args = hashMap;
    }

    public TabInfo(BaseFragment baseFragment, String str, String str2, HashMap<String, Object> hashMap) {
        this.mFragment = baseFragment;
        this.url = str;
        this.title = str2;
        if (hashMap != null) {
            hashMap.put("title", this.url);
            hashMap.put(WEB_URL, this.title);
        }
        this.args = hashMap;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Class getCls() {
        return this.cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public HashMap<String, Object> getArgs() {
        return this.args;
    }

    public void setArgs(HashMap<String, Object> hashMap) {
        this.args = hashMap;
    }
}
