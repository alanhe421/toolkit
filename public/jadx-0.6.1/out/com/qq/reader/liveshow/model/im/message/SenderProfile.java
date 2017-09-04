package com.qq.reader.liveshow.model.im.message;

import com.qq.reader.liveshow.utils.n;
import java.io.Serializable;

public class SenderProfile implements Serializable {
    public static final String KEY_AUTHORID = "AuthorId";
    public static final String KEY_HEADICION = "HeadIcon";
    public static final String KEY_NICKNAME = "NickName";
    public static final String KEY_UID = "UserId";
    public static final String KEY_USERLEVEL = "UserLevel";
    public static final String KEY_VIPLEVEL = "VipLevel";
    private long mAuthorId = 0;
    private String mAvatar = "";
    private String mId = "";
    private String mNickName = "";
    private int mPermissionsLevel = 4;
    private int mVipLevel = 0;

    public String getId() {
        return this.mId;
    }

    public SenderProfile setId(String str) {
        this.mId = n.a(str);
        return this;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public SenderProfile setNickName(String str) {
        this.mNickName = n.a(str);
        return this;
    }

    public String getAvatar() {
        return this.mAvatar;
    }

    public SenderProfile setAvatar(String str) {
        this.mAvatar = n.a(str);
        return this;
    }

    public long getAuthorId() {
        return this.mAuthorId;
    }

    public SenderProfile setAuthorId(long j) {
        this.mAuthorId = j;
        return this;
    }

    public int getVipLevel() {
        return this.mVipLevel;
    }

    public SenderProfile setVipLevel(int i) {
        this.mVipLevel = i;
        return this;
    }

    public int getPermissionsLevel() {
        return this.mPermissionsLevel;
    }

    public SenderProfile setPermissionsLevel(int i) {
        this.mPermissionsLevel = i;
        return this;
    }
}
