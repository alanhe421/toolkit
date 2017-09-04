package com.qq.reader.liveshow.c;

import android.content.Context;
import android.graphics.Bitmap;
import com.qq.reader.liveshow.a.c;
import com.qq.reader.liveshow.b.e;
import com.qq.reader.liveshow.c.b.g;
import com.qq.reader.liveshow.model.RoomDetail;
import com.qq.reader.liveshow.model.b;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.l;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: RoomDetailHelper */
public class n {
    public static final String a = n.class.getSimpleName();
    private g b;
    private Context c;

    public n(g gVar, Context context) {
        this.b = gVar;
        this.c = context;
    }

    public void a(int i) {
        try {
            e c = com.qq.reader.liveshow.b.n.a().c();
            Map map = null;
            if (c != null) {
                map = c.b();
            }
            l.a().a(l.b(i), new m<RoomDetail>(this) {
                final /* synthetic */ n a;

                {
                    this.a = r1;
                }

                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void a(int r5, com.qq.reader.liveshow.model.RoomDetail r6) {
                    /*
                    r4 = this;
                    r3 = 0;
                    r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                    if (r5 != r0) goto L_0x000c;
                L_0x0005:
                    if (r6 == 0) goto L_0x000c;
                L_0x0007:
                    r0 = r6.mCode;	 Catch:{ Exception -> 0x00e8 }
                    switch(r0) {
                        case 0: goto L_0x001e;
                        default: goto L_0x000c;
                    };
                L_0x000c:
                    r0 = r4.a;
                    r0 = r0.b;
                    if (r0 == 0) goto L_0x001d;
                L_0x0014:
                    r0 = r4.a;
                    r0 = r0.b;
                    r0.a(r3);
                L_0x001d:
                    return;
                L_0x001e:
                    r0 = r4.a;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.b;	 Catch:{ Exception -> 0x00e8 }
                    if (r0 == 0) goto L_0x000c;
                L_0x0026:
                    r0 = 0;
                    r2 = r0;
                    r1 = r3;
                L_0x0029:
                    r0 = r6.gift;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.size();	 Catch:{ Exception -> 0x00e8 }
                    if (r2 >= r0) goto L_0x004a;
                L_0x0031:
                    r0 = r6.gift;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.get(r2);	 Catch:{ Exception -> 0x00e8 }
                    r0 = (com.qq.reader.liveshow.model.im.viewdata.GiftItem) r0;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mType;	 Catch:{ Exception -> 0x00e8 }
                    if (r0 != 0) goto L_0x0104;
                L_0x003d:
                    r0 = r6.gift;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.get(r2);	 Catch:{ Exception -> 0x00e8 }
                    r0 = (com.qq.reader.liveshow.model.im.viewdata.GiftItem) r0;	 Catch:{ Exception -> 0x00e8 }
                L_0x0045:
                    r1 = r2 + 1;
                    r2 = r1;
                    r1 = r0;
                    goto L_0x0029;
                L_0x004a:
                    if (r1 == 0) goto L_0x0054;
                L_0x004c:
                    r0 = r6.gift;	 Catch:{ Exception -> 0x00e8 }
                    r0.remove(r1);	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.a(r1);	 Catch:{ Exception -> 0x00e8 }
                L_0x0054:
                    r0 = r6.gift;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.a(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r4.a;	 Catch:{ Exception -> 0x00e8 }
                    r1 = r6.gift;	 Catch:{ Exception -> 0x00e8 }
                    r0.a(r1);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.rank;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.b(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    if (r0 == 0) goto L_0x00dd;
                L_0x0069:
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mRoomId;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.e(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mHostUid;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.b(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mTitle;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.e(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mCover;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.f(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mHostAvatar;	 Catch:{ Exception -> 0x00e8 }
                    r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x00e8 }
                    if (r0 != 0) goto L_0x00f4;
                L_0x008f:
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mHostAvatar;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.d(r0);	 Catch:{ Exception -> 0x00e8 }
                L_0x0096:
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mHostUserName;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.c(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mAdmireCount;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.c(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mTotalWatchCount;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.b(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mTimeSpan;	 Catch:{ Exception -> 0x00e8 }
                    r0 = (long) r0;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.a(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mState;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.g(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mAuthorId;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.a(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.showVipLevel;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.i(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.authorLevel;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.h(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mRtmpLink;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.h(r0);	 Catch:{ Exception -> 0x00e8 }
                    r0 = r6.roomInfo;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.mHlsLink;	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.g(r0);	 Catch:{ Exception -> 0x00e8 }
                L_0x00dd:
                    r0 = r4.a;	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.b;	 Catch:{ Exception -> 0x00e8 }
                    r0.a(r6);	 Catch:{ Exception -> 0x00e8 }
                    goto L_0x001d;
                L_0x00e8:
                    r0 = move-exception;
                    r1 = com.qq.reader.liveshow.c.n.a;
                    r0 = r0.getMessage();
                    com.qq.reader.liveshow.utils.SxbLog.e(r1, r0);
                    goto L_0x000c;
                L_0x00f4:
                    r0 = com.qq.reader.liveshow.b.n.a();	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.e();	 Catch:{ Exception -> 0x00e8 }
                    r0 = r0.i();	 Catch:{ Exception -> 0x00e8 }
                    com.qq.reader.liveshow.model.a.d(r0);	 Catch:{ Exception -> 0x00e8 }
                    goto L_0x0096;
                L_0x0104:
                    r0 = r1;
                    goto L_0x0045;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.liveshow.c.n.1.a(int, com.qq.reader.liveshow.model.RoomDetail):void");
                }

                public void a(int i, String str) {
                    if (this.a.b != null) {
                        this.a.b.a(null);
                    }
                }

                public void a(Exception exception) {
                    if (this.a.b != null) {
                        this.a.b.a(null);
                    }
                }
            }, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void a(final List<GiftItem> list) {
        int i = 0;
        while (list != null && i < list.size()) {
            com.bumptech.glide.g.b(this.c).a(((GiftItem) list.get(i)).mImgUrl).j().b().a(this.c.getResources().getDimensionPixelOffset(c.chat_list_gift_height), this.c.getResources().getDimensionPixelOffset(c.chat_list_gift_height)).b(new com.bumptech.glide.request.b.g<Bitmap>(this) {
                final /* synthetic */ n c;

                public void a(Bitmap bitmap, com.bumptech.glide.request.a.c<? super Bitmap> cVar) {
                    SxbLog.b("gift", ((GiftItem) list.get(i)).mImgUrl);
                    if (bitmap != null) {
                        b.a().put(Integer.valueOf(((GiftItem) list.get(i)).mId), new com.qq.reader.liveshow.utils.c(this.c.c, bitmap));
                    }
                }
            });
            i++;
        }
    }

    public void a() {
        this.b = null;
        this.c = null;
    }
}
