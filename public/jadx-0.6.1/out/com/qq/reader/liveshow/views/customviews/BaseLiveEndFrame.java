package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import android.view.ViewGroup;
import com.qq.reader.liveshow.views.roomdialog.LiveEnterRoomDialog;
import com.qq.reader.liveshow.views.roomdialog.a.a.a;

public abstract class BaseLiveEndFrame extends a {
    private LiveEnterRoomDialog.a d;
    private int e;
    private String f;
    private String g;

    public BaseLiveEndFrame(LiveEnterRoomDialog.a aVar, Activity activity, ViewGroup viewGroup) {
        super(aVar, activity, viewGroup);
        this.d = aVar;
    }

    public String getAvatarUrl() {
        return this.f;
    }

    public void setAvatarUrl(String str) {
        this.f = str;
    }

    public String getName() {
        return this.g;
    }

    public void setName(String str) {
        this.g = str;
    }

    public int getRoomId() {
        return this.e;
    }

    public void setRoomId(int i) {
        this.e = i;
    }

    public void d() {
        super.d();
        if (this.d != null) {
            this.d.a(7, null);
        }
    }
}
