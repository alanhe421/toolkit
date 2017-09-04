package com.qq.reader.module.bookchapter.online;

import java.io.Serializable;

public class OnlineChapterComment implements Serializable {
    private String mCommentText;
    private String mUserHeadIconUrl;

    public OnlineChapterComment() {
        this("", "");
    }

    public OnlineChapterComment(String str, String str2) {
        this.mUserHeadIconUrl = str;
        this.mCommentText = str2;
    }

    public String getUserHeadIconUrl() {
        return this.mUserHeadIconUrl;
    }

    public void setUserHeadIconUrl(String str) {
        this.mUserHeadIconUrl = str;
    }

    public String getCommentText() {
        return this.mCommentText;
    }

    public void setCommentText(String str) {
        this.mCommentText = str;
    }
}
