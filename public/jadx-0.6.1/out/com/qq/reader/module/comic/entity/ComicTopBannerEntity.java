package com.qq.reader.module.comic.entity;

import java.io.Serializable;

public class ComicTopBannerEntity implements Serializable {
    private String action;
    private String actiontag;
    private String category;
    private int count;
    private String descr;
    private int expire_date;
    private a extInfo;
    private int id;
    private String image_url;
    private String link_url;
    private String read_online;
    private int start_date;
    private String title;
    private int type;
    private int valuetype;

    public static class a {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getValuetype() {
        return this.valuetype;
    }

    public void setValuetype(int i) {
        this.valuetype = i;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescr() {
        return this.descr;
    }

    public void setDescr(String str) {
        this.descr = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getActiontag() {
        return this.actiontag;
    }

    public void setActiontag(String str) {
        this.actiontag = str;
    }

    public String getLink_url() {
        return this.link_url;
    }

    public void setLink_url(String str) {
        this.link_url = str;
    }

    public String getRead_online() {
        return this.read_online;
    }

    public void setRead_online(String str) {
        this.read_online = str;
    }

    public int getExpire_date() {
        return this.expire_date;
    }

    public void setExpire_date(int i) {
        this.expire_date = i;
    }

    public int getStart_date() {
        return this.start_date;
    }

    public void setStart_date(int i) {
        this.start_date = i;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String str) {
        this.image_url = str;
    }

    public a getExtInfo() {
        return this.extInfo;
    }

    public void setExtInfo(a aVar) {
        this.extInfo = aVar;
    }
}
