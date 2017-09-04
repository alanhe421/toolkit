package com.qq.reader.liveshow.views.customviews;

import android.app.Activity;
import android.os.Message;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.j;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.utils.IMHelper;
import com.qq.reader.liveshow.utils.IMHelper.NotEnterIMException;
import com.qq.reader.liveshow.utils.SxbLog;
import com.tencent.TIMElem;
import com.tencent.TIMMessage;
import com.tencent.TIMMessagePriority;
import com.tencent.TIMValueCallBack;
import com.tencent.qalsdk.base.a;

public abstract class BaseAuthorDetailDialog extends BaseDialog {
    public static final String a = BaseAuthorDetailDialog.class.getSimpleName();
    private long b;
    private String c;
    private int d;
    private String e;
    private String f;
    private boolean g;
    private j h;

    public BaseAuthorDetailDialog(Activity activity, int i) {
        super(activity, i);
    }

    public boolean h() {
        return this.g;
    }

    public void setIsHost(boolean z) {
        this.g = z;
    }

    public String getAvatarUrl() {
        return this.e;
    }

    public void setAvatarUrl(String str) {
        this.e = str;
    }

    public String getName() {
        return this.f;
    }

    public void setName(String str) {
        this.f = str;
    }

    public int getRoomId() {
        return this.d;
    }

    public void setRoomId(int i) {
        this.d = i;
    }

    public long getAuthorId() {
        return this.b;
    }

    public void setAuthorId(long j) {
        this.b = j;
    }

    public String getUserId() {
        return this.c;
    }

    public void setUserId(String str) {
        this.c = str;
    }

    public String getMyId() {
        return c.a().b();
    }

    public void setPresenterDispatch(j jVar) {
        this.h = jVar;
    }

    public void i() {
        if (this.h != null && IMHelper.b()) {
            try {
                IMHelper.a(a.c, "", TIMMessagePriority.Normal, new TIMValueCallBack<TIMMessage>(this) {
                    final /* synthetic */ BaseAuthorDetailDialog a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onSuccess(Object obj) {
                        a((TIMMessage) obj);
                    }

                    public void onError(int i, String str) {
                        SxbLog.b(BaseAuthorDetailDialog.a, str);
                    }

                    public void a(TIMMessage tIMMessage) {
                        TIMElem element = tIMMessage.getElement(0);
                        if (element != null) {
                            try {
                                com.qq.reader.liveshow.model.im.a.a.a a = IMHelper.a(element);
                                Message obtain = Message.obtain();
                                obtain.what = 1003;
                                obtain.obj = a;
                                obtain.arg1 = 1;
                                this.a.h.a(obtain);
                            } catch (Exception e) {
                                SxbLog.e(BaseAuthorDetailDialog.a, e.getMessage());
                            }
                        }
                    }
                });
            } catch (NotEnterIMException e) {
                e.printStackTrace();
                try {
                    n.a().e().g().a(n.a().f(), "关注失败，请重新进入后重试", 0);
                } catch (NullPointerException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
