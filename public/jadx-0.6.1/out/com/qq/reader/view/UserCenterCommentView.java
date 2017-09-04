package com.qq.reader.view;

import android.content.Context;
import android.widget.RelativeLayout;

public class UserCenterCommentView extends RelativeLayout {
    private a a;

    public interface a {
    }

    public UserCenterCommentView(Context context) {
        super(context);
    }

    public void setCommentQurlInterface(a aVar) {
        this.a = aVar;
    }
}
