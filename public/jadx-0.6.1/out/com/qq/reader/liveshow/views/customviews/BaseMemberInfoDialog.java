package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import com.qq.reader.liveshow.model.c;

public abstract class BaseMemberInfoDialog extends BaseDialog {
    private String a;
    private int b;
    private String c;
    private String d;

    public BaseMemberInfoDialog(Activity activity, int i) {
        super(activity, i);
    }

    public String getAvatarUrl() {
        return this.c;
    }

    public void setAvatarUrl(String str) {
        this.c = str;
    }

    public String getName() {
        return this.d;
    }

    public void setName(String str) {
        this.d = str;
    }

    public int getRoomId() {
        return this.b;
    }

    public void setRoomId(int i) {
        this.b = i;
    }

    public String getUserId() {
        return this.a;
    }

    public String getMyId() {
        return c.a().b();
    }

    public void setUserId(String str) {
        this.a = str;
    }
}
