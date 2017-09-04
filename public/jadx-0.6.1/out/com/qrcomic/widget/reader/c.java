package com.qrcomic.widget.reader;

import android.graphics.Matrix;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import b.a.a.a.a.a.e;
import b.a.a.a.a.a.f;
import com.qrcomic.a.k;
import com.qrcomic.activity.reader.QRComicReadingBaseActivity;
import com.qrcomic.activity.reader.QRComicReadingVerticalActivity;
import com.qrcomic.downloader.j;
import com.qrcomic.entity.ComicRecommendPageInfo;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.RecommendComicInfo;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.util.g;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: QRComicScrollReaderHelper */
public class c implements Callback, com.qrcomic.widget.reader.QRComicScrollLoadingView.a, com.qrcomic.widget.reader.QRComicScrollReaderListView.b, com.qrcomic.widget.reader.QRComicScrollReaderListView.d {
    public QRComicReadingBaseActivity a;
    Handler b = new Handler(Looper.getMainLooper(), this);
    j c = new j(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void a(ComicSectionPicInfo comicSectionPicInfo, long j, long j2) {
            this.a.p = new a(this.a, comicSectionPicInfo);
            this.a.q.post(this.a.p);
        }

        public void a(ComicSectionPicInfo comicSectionPicInfo, int i, String str) {
            if (comicSectionPicInfo != null) {
                comicSectionPicInfo.mState = 1;
                this.a.b(comicSectionPicInfo);
            }
        }

        public void a(ComicSectionPicInfo comicSectionPicInfo, String str) {
        }
    };
    private Map<String, Boolean> d;
    private List<ComicSectionPicInfo> e = new ArrayList();
    private int f;
    private QRComicScrollReaderListView g;
    private com.qrcomic.manager.c h;
    private int i;
    private String j;
    private int k;
    private String l;
    private boolean m = false;
    private boolean n = false;
    private volatile boolean o = false;
    private a p;
    private Handler q = new Handler(Looper.getMainLooper());

    /* compiled from: QRComicScrollReaderHelper */
    private class a implements Runnable {
        ComicSectionPicInfo a;
        final /* synthetic */ c b;

        public a(c cVar, ComicSectionPicInfo comicSectionPicInfo) {
            this.b = cVar;
            this.a = comicSectionPicInfo;
        }

        public void run() {
            if (this.a != null && this.a.bitmap != null && !this.a.bitmap.isRecycled()) {
                this.a.mState = 0;
                if (this.b.g.getVisibility() == 0) {
                    if (g.a()) {
                        g.a("QRComicScrollReaderHelper", g.d, "LOADPIC 图片加载成功。。。加载的图片是 ： " + this.a);
                    }
                    int childCount = this.b.g.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        int intValue;
                        View childAt = this.b.g.getChildAt(i);
                        Object tag = childAt.getTag(b.a.a.a.a.a.g.key_id);
                        if (tag instanceof Integer) {
                            intValue = ((Integer) tag).intValue();
                        } else {
                            intValue = 0;
                        }
                        c cVar = (c) childAt.getTag();
                        if (cVar.d.mComicRecommendPageInfo == null && cVar.d.equals(this.a) && this.a.listIndex == intValue) {
                            this.b.a.Z.a(this.a);
                            if (this.a.bitmap == null || this.a.bitmap.isRecycled()) {
                                this.b.b(this.a);
                                cVar.a.setVisibility(8);
                                cVar.b.setVisibility(0);
                                return;
                            }
                            if (g.a()) {
                                g.a("QRComicScrollReaderHelper", g.d, " LOADPIC 满足条件,刷新 图片存在 find index = " + intValue + " picIndex = " + this.a.listIndex);
                            }
                            this.b.a.a(this.a, null, cVar.a);
                            cVar.a.setVisibility(0);
                            cVar.b.setVisibility(8);
                            cVar.a.setLayoutParams(new LayoutParams(this.b.f, this.a.dstHeight));
                            return;
                        }
                    }
                }
            }
        }
    }

    /* compiled from: QRComicScrollReaderHelper */
    class b extends BaseAdapter implements OnClickListener {
        final /* synthetic */ c a;
        private QRComicReadingBaseActivity b;
        private int c;

        public b(c cVar, QRComicReadingBaseActivity qRComicReadingBaseActivity, int i) {
            this.a = cVar;
            this.b = qRComicReadingBaseActivity;
            this.c = i;
        }

        public void a(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }

        public int getCount() {
            return this.a.e.size();
        }

        public Object getItem(int i) {
            if (this.a.e == null || i < 0 || i >= this.a.e.size()) {
                return null;
            }
            return this.a.e.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemViewType(int i) {
            if (this.a.e == null || i != this.a.e.size() - 1) {
                return 0;
            }
            ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) getItem(i);
            if (comicSectionPicInfo == null || comicSectionPicInfo.mComicRecommendPageInfo == null) {
                return 0;
            }
            return 1;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            int itemViewType = getItemViewType(i);
            if (view == null) {
                View inflate;
                c cVar2 = new c();
                if (itemViewType == 1) {
                    cVar2.f = new d();
                    if (this.b.Z.H == 1) {
                        inflate = LayoutInflater.from(this.b).inflate(f.reader_recommend_layout_portrait, null);
                    } else {
                        View inflate2 = LayoutInflater.from(this.b).inflate(f.reader_recommend_layout_land, null);
                        if (this.b.aE) {
                            try {
                                ViewGroup.LayoutParams layoutParams = inflate2.getLayoutParams();
                                if (layoutParams == null) {
                                    layoutParams = new ViewGroup.LayoutParams(this.c, -2);
                                }
                                inflate2.setLayoutParams(layoutParams);
                                inflate = inflate2;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        inflate = inflate2;
                    }
                    cVar2.f.a = (TextView) inflate.findViewById(e.finish_info);
                    cVar2.f.b = (TextView) inflate.findViewById(e.collection_info);
                    cVar2.f.c = (Button) inflate.findViewById(e.collection_button);
                    cVar2.f.c.setOnClickListener(this);
                    cVar2.f.g = (ImageView) inflate.findViewById(e.recommend_comic_left_image);
                    cVar2.f.d = (TextView) inflate.findViewById(e.recommend_comic_left_title);
                    cVar2.f.h = (ImageView) inflate.findViewById(e.recommend_comic_middle_image);
                    cVar2.f.e = (TextView) inflate.findViewById(e.recommend_comic_middle_title);
                    cVar2.f.i = (ImageView) inflate.findViewById(e.recommend_comic_right_image);
                    cVar2.f.f = (TextView) inflate.findViewById(e.recommend_comic_right_title);
                    cVar2.f.g.setOnClickListener(this);
                    cVar2.f.h.setOnClickListener(this);
                    cVar2.f.i.setOnClickListener(this);
                    if (this.b instanceof QRComicReadingVerticalActivity) {
                        int round = Math.round((((float) this.c) - (com.qrcomic.screenshot.a.b.b(12.0f) * 4.0f)) / 3.0f);
                        if (cVar2.f.g.getLayoutParams() != null) {
                            cVar2.f.g.getLayoutParams().width = round;
                        }
                        if (cVar2.f.d.getLayoutParams() != null) {
                            cVar2.f.d.getLayoutParams().width = round;
                        }
                        if (cVar2.f.h != null) {
                            cVar2.f.h.getLayoutParams().width = round;
                        }
                        if (cVar2.f.e != null) {
                            cVar2.f.e.getLayoutParams().width = round;
                        }
                        if (cVar2.f.i != null) {
                            cVar2.f.i.getLayoutParams().width = round;
                        }
                        if (cVar2.f.f != null) {
                            cVar2.f.f.getLayoutParams().width = round;
                        }
                    }
                } else {
                    inflate = LayoutInflater.from(this.b).inflate(f.qr_comic_reader_item, null);
                    cVar2.a = (ImageView) inflate.findViewById(e.comic_img);
                    cVar2.b = (QRComicScrollLoadingView) inflate.findViewById(e.loading_view);
                    cVar2.b.setOnClickForSubText(this.a);
                    cVar2.b.setTag(cVar2);
                    cVar2.b.setAttachedActivity(this.b);
                }
                inflate.setTag(cVar2);
                view = inflate;
                cVar = cVar2;
            } else {
                cVar = (c) view.getTag();
            }
            ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) getItem(i);
            if (comicSectionPicInfo != null) {
                comicSectionPicInfo.listIndex = i;
                view.setTag(b.a.a.a.a.a.g.key_id, Integer.valueOf(i));
                if (itemViewType == 1) {
                    this.a.a(cVar.f, comicSectionPicInfo.mComicRecommendPageInfo, (OnClickListener) this);
                } else {
                    cVar.a.setVisibility(8);
                    cVar.b.setVisibility(8);
                    ViewGroup.LayoutParams layoutParams2 = new LayoutParams(this.c, comicSectionPicInfo.dstHeight);
                    if (comicSectionPicInfo.bitmap == null || comicSectionPicInfo.bitmap.isRecycled()) {
                        CharSequence charSequence = "第" + (comicSectionPicInfo.index + 1) + "页";
                        CharSequence charSequence2 = "图片加载中";
                        com.qrcomic.entity.f b = this.b.Z.b(comicSectionPicInfo.sectionId);
                        if (b != null) {
                            if (b.t == 1) {
                                charSequence = "正在购买" + b.c;
                            } else if (b.t == 2) {
                                charSequence = b.c + "购买失败";
                                charSequence2 = "付费失败, 重新购买";
                            } else if (b.t == 3) {
                                charSequence = b.c + "购买已取消";
                                charSequence2 = "付费失败, 重新购买";
                            } else if (comicSectionPicInfo.mState == 1) {
                                charSequence2 = "加载失败, 点击重试";
                                if (g.a()) {
                                    g.a("QRComicScrollReaderHelper", g.d, "LOADPIC 图片加载的时候已经被回收了吧？ ");
                                }
                            }
                        } else if (g.a()) {
                            g.a("QRComicScrollReaderHelper", g.d, "getView section = null, sectionId=" + comicSectionPicInfo.sectionId + ", comicId = " + this.b.Z.n);
                        }
                        cVar.b.setLayoutParams(layoutParams2);
                        cVar.b.setMainText(charSequence);
                        cVar.b.setSubText(charSequence2);
                        cVar.b.setVisibility(0);
                    } else {
                        cVar.a.setVisibility(0);
                        cVar.a.setLayoutParams(layoutParams2);
                        this.b.a(comicSectionPicInfo, null, cVar.a);
                    }
                }
                cVar.d = comicSectionPicInfo;
                cVar.c = i;
                cVar.e = true;
            }
            return view;
        }

        public void onClick(View view) {
            int id = view.getId();
            if (id == e.collection_button) {
                Button button = (Button) view;
                button.setText(this.b.getResources().getString(b.a.a.a.a.a.g.reader_has_fav));
                button.setEnabled(false);
                this.b.d();
                com.qrcomic.manager.b.a().a(this.b.getResources().getString(b.a.a.a.a.a.g.reader_add_to_fav_tips), k.a);
            } else if (id == e.collection_info) {
                if (this.b != null && this.b.Z != null) {
                    QRComicManager.a(this.b, this.b.Z.n, "read", this.b.Z.i.m);
                }
            } else if ((id == e.recommend_comic_left_image || id == e.recommend_comic_middle_image || id == e.recommend_comic_right_image) && view.getTag() != null && (view.getTag() instanceof RecommendComicInfo)) {
                RecommendComicInfo recommendComicInfo = (RecommendComicInfo) view.getTag();
                QRComicManager.a(this.b, recommendComicInfo.a, "read", recommendComicInfo.d);
                if (this.b.Z.L != null) {
                    String str = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
                    if (recommendComicInfo.e == 1) {
                        str = "2";
                    } else if (recommendComicInfo.e == 2) {
                        str = "1";
                    }
                    str = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
                    String str2;
                    if (recommendComicInfo.f == 1) {
                        str2 = "1";
                    } else if (recommendComicInfo.f == 2) {
                        str2 = "2";
                    }
                }
            }
        }
    }

    /* compiled from: QRComicScrollReaderHelper */
    public static class c {
        public ImageView a;
        public QRComicScrollLoadingView b;
        public int c;
        public ComicSectionPicInfo d;
        public boolean e;
        public d f;
    }

    /* compiled from: QRComicScrollReaderHelper */
    public static class d {
        public TextView a;
        public TextView b;
        public Button c;
        public TextView d;
        public TextView e;
        public TextView f;
        public ImageView g;
        public ImageView h;
        public ImageView i;
    }

    public c(QRComicScrollReaderListView qRComicScrollReaderListView, QRComicReadingBaseActivity qRComicReadingBaseActivity, int i) {
        this.g = qRComicScrollReaderListView;
        this.g.a(this);
        this.g.setOnComicFlingListener(this);
        this.g.setOnLayoutListener(this);
        this.a = qRComicReadingBaseActivity;
        this.d = new android.support.v4.util.a();
        this.f = i;
        this.h = (com.qrcomic.manager.c) com.qrcomic.manager.b.a().b().a(3);
    }

    public void a(List<ComicSectionPicInfo> list, String str, int i) {
        a((List) list);
        com.qrcomic.activity.reader.a aVar = this.a.Z;
        if (!(aVar.q == null || !aVar.d(aVar.q) || aVar.t == null)) {
            b(this.a.Z.t);
        }
        if (!(aVar.p == null || !aVar.d(aVar.p) || aVar.s == null)) {
            c(this.a.Z.s);
        }
        a(str, i);
    }

    public void a(List<ComicSectionPicInfo> list) {
        if (list != null && list.size() > 0) {
            this.d.clear();
            this.o = false;
            this.e = new ArrayList();
            this.d.put(((ComicSectionPicInfo) list.get(0)).sectionId, Boolean.valueOf(true));
            for (ComicSectionPicInfo comicSectionPicInfo : list) {
                comicSectionPicInfo.dstHeight = a(comicSectionPicInfo);
                this.e.add(comicSectionPicInfo);
            }
            if (this.a.O()) {
                a(false, "setList");
            }
        }
    }

    public void a(String str, int i) {
        if (this.e != null) {
            int i2;
            int size = this.e.size();
            for (int i3 = 0; i3 < size; i3++) {
                ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) this.e.get(i3);
                if (comicSectionPicInfo.sectionId.equals(str) && comicSectionPicInfo.index == i) {
                    this.g.d = i3;
                    this.l = comicSectionPicInfo.sectionId;
                    this.k = comicSectionPicInfo.index;
                    i2 = i3;
                    break;
                }
            }
            i2 = -1;
            if (i2 >= 0) {
                this.g.b = i2;
                if (this.g.getAdapter() == null) {
                    this.g.setAdapter(new b(this, this.a, this.f));
                }
                size = this.g.b;
                this.g.setSelection(size);
                this.h.a(this.a.Z, (ComicSectionPicInfo) this.e.get(size), size, this.e, this.c, 2);
            }
        }
    }

    public void a(ComicSectionPicInfo comicSectionPicInfo, int i, int i2, int i3, int i4) {
        if (this.g.c && i2 >= 0 && i <= this.g.getHeight()) {
            this.g.c = false;
            this.g.d = i3;
            if (comicSectionPicInfo.mComicRecommendPageInfo != null) {
                return;
            }
            if (!comicSectionPicInfo.sectionId.equals(this.l) || comicSectionPicInfo.index != this.k) {
                a((ComicSectionPicInfo) this.e.get(i3), i3, i4 > 0 ? 1 : 2);
                this.l = comicSectionPicInfo.sectionId;
                this.k = comicSectionPicInfo.index;
                this.n = true;
            }
        }
    }

    public void a(View view) {
        try {
            if ((view.getTag() instanceof c) && (view instanceof QRComicScrollLoadingView)) {
                c cVar = (c) view.getTag();
                QRComicScrollLoadingView qRComicScrollLoadingView = (QRComicScrollLoadingView) view;
                if (cVar.d != null) {
                    if (this.a == null || this.a.Z == null) {
                        if (this.a != null) {
                            Toast.makeText(this.a, "出现错误，请退出重试", 0).show();
                        }
                    } else if (this.a.Z.b(cVar.d.sectionId).t == 2 || this.a.Z.b(cVar.d.sectionId).t == 3) {
                        if (this.a.Z.i.F <= 8) {
                            this.a.a.f().d().a(this.a, b.a.a.a.a.a.g.pay_fail_by_permission, 0);
                        } else {
                            this.a.a(this.a.Z.b(cVar.d.sectionId), 0);
                        }
                    } else if (cVar.d.mComicRecommendPageInfo == null && cVar.d.mState == 1) {
                        a(cVar.d, cVar.c, 2);
                        qRComicScrollLoadingView.setMainText("第" + (cVar.d.index + 1) + "页");
                        qRComicScrollLoadingView.setSubText("图片加载中");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.a != null) {
                Toast.makeText(this.a, "出现错误，请退出重试", 0).show();
            }
        }
    }

    private void a(ComicSectionPicInfo comicSectionPicInfo, int i, int i2) {
        g.a("QRComicScrollReaderHelper", g.d, " LOADPIC preloadPics。。。： " + comicSectionPicInfo + " currentItemIndex=" + i + " scrollDirection=" + i2);
        if (d()) {
            Message obtain = Message.obtain(this.b);
            obtain.obj = new Object[]{comicSectionPicInfo, Integer.valueOf(i), Integer.valueOf(i2)};
            obtain.what = 0;
            obtain.sendToTarget();
        }
    }

    public void a() {
        if (this.g.d >= 0 && this.g.d < this.e.size()) {
            a((ComicSectionPicInfo) this.e.get(this.g.d), this.g.d, 2);
        }
    }

    public int b() {
        return this.a.at;
    }

    public void b(List<ComicSectionPicInfo> list) {
        try {
            if (this.e != null && list != null && list.size() > 0 && this.d != null && this.d.get(((ComicSectionPicInfo) list.get(0)).sectionId) == null) {
                this.d.put(((ComicSectionPicInfo) list.get(0)).sectionId, Boolean.valueOf(true));
                for (ComicSectionPicInfo comicSectionPicInfo : list) {
                    comicSectionPicInfo.dstHeight = a(comicSectionPicInfo);
                    this.e.add(comicSectionPicInfo);
                }
                if (this.g.getAdapter() != null) {
                    this.g.getAdapter().notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void a(boolean z, String str) {
        try {
            if (g.a()) {
                g.a("QRComicScrollReaderHelper", g.d, "appendRecommendPageInScrollMode , from " + str);
            }
            if (!(this.e == null || this.e.size() <= 0 || this.o || this.a == null || this.a.Z == null || this.a.Z.J)) {
                Integer num;
                ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) this.e.get(this.e.size() - 1);
                if (comicSectionPicInfo.sectionId == null) {
                    num = null;
                } else {
                    num = (Integer) this.a.Z.v.get(comicSectionPicInfo.sectionId);
                }
                if (num != null && num.intValue() == this.a.Z.u.size() - 1) {
                    comicSectionPicInfo = this.a.Z.i();
                    if (comicSectionPicInfo != null) {
                        this.e.add(comicSectionPicInfo);
                        this.o = true;
                        if (z && this.g.getAdapter() != null) {
                            this.g.getAdapter().notifyDataSetChanged();
                        }
                        if (g.a()) {
                            g.a("QRComicScrollReaderHelper", g.d, "appendComicRecommendPage , real from " + str);
                        }
                    } else if (this.a.Z.L == null) {
                        this.a.Z.l();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c(List<ComicSectionPicInfo> list) {
        int i = 0;
        if (list != null && list.size() > 0 && this.d != null && this.d.get(((ComicSectionPicInfo) list.get(0)).sectionId) == null) {
            this.d.put(((ComicSectionPicInfo) list.get(0)).sectionId, Boolean.valueOf(true));
            while (i < list.size()) {
                ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) list.get(i);
                comicSectionPicInfo.dstHeight = a(comicSectionPicInfo);
                this.e.add(i, comicSectionPicInfo);
                i++;
            }
            if (this.g.getAdapter() != null) {
                this.g.getAdapter().notifyDataSetChanged();
            }
        }
    }

    public int c() {
        return this.a.Z.H;
    }

    public ComicSectionPicInfo a(int i) {
        if (this.e == null || i < 0 || i >= this.e.size()) {
            return null;
        }
        return (ComicSectionPicInfo) this.e.get(i);
    }

    public boolean d() {
        if (Math.abs(this.g.getCurrentVelocity()) < 4) {
            return true;
        }
        return false;
    }

    public void e() {
        if (!d()) {
        }
    }

    public int a(ComicSectionPicInfo comicSectionPicInfo) {
        return (int) (((float) this.f) / (((float) comicSectionPicInfo.width) / ((float) comicSectionPicInfo.height)));
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                if (message.obj instanceof Object[]) {
                    Object[] objArr = (Object[]) message.obj;
                    final ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) objArr[0];
                    final int intValue = ((Integer) objArr[1]).intValue();
                    final int intValue2 = ((Integer) objArr[2]).intValue();
                    com.qrcomic.a.j.a().a(new com.qrcomic.a.d(this) {
                        final /* synthetic */ c d;

                        public void run() {
                            this.d.h.a(this.d.a.Z, comicSectionPicInfo, intValue, this.d.e, this.d.c, intValue2);
                            g.a("QRComicScrollReaderHelper", g.d, "LOADPIC preloadPicsInScrollPager。。。： " + comicSectionPicInfo + " reqItemIndex=" + intValue + " final_Scroll_direction=" + intValue2);
                        }
                    }, 3, null, false);
                    break;
                }
                break;
        }
        return true;
    }

    public void b(ComicSectionPicInfo comicSectionPicInfo) {
        if (comicSectionPicInfo != null) {
            if (g.a()) {
                g.a("QRComicScrollReaderHelper", g.d, "LOADPIC FAIL " + comicSectionPicInfo);
            }
            if (this.g.getVisibility() == 0) {
                int childCount = this.g.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    c cVar = (c) this.g.getChildAt(i).getTag();
                    if (cVar.d.mComicRecommendPageInfo == null && cVar.d.equals(comicSectionPicInfo) && cVar.b != null && cVar.b.getVisibility() == 0) {
                        cVar.b.setLayoutParams(new LayoutParams(this.f, comicSectionPicInfo.dstHeight));
                        cVar.b.setSubText("加载失败, 点击重试");
                        return;
                    }
                }
            }
        }
    }

    public void f() {
        this.i = this.k;
        this.j = this.l;
    }

    public void g() {
    }

    public void h() {
        try {
            this.m = false;
            if (this.e.size() > this.g.d) {
                ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) this.e.get(this.g.d);
                if (this.j == null) {
                    this.i = comicSectionPicInfo.index;
                    this.j = comicSectionPicInfo.sectionId;
                } else if (!this.j.equals(comicSectionPicInfo.sectionId) || this.i != comicSectionPicInfo.index) {
                    a(comicSectionPicInfo, this.g.d, 2);
                    this.i = comicSectionPicInfo.index;
                    this.j = comicSectionPicInfo.sectionId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(View view) {
        if (view.getTag() != null && (view.getTag() instanceof c)) {
            c cVar = (c) view.getTag();
            this.a.a(null, cVar.d, cVar.a);
        }
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        if (view.getTag() != null && (view.getTag() instanceof c)) {
            c cVar = (c) view.getTag();
            if (cVar != null && cVar.d != null) {
                if (i2 > this.g.getHeight() || i4 < 0) {
                    if (cVar.d.isVisible) {
                        cVar.d.isVisible = false;
                        if (cVar.b != null) {
                            cVar.b.b();
                        }
                    }
                } else if (!cVar.d.isVisible) {
                    cVar.d.isVisible = true;
                    if (cVar.b != null) {
                        cVar.b.a();
                    }
                }
                cVar.d.top = i2;
                if (cVar.e) {
                    cVar.e = false;
                }
            }
        }
    }

    public void i() {
        if (this.n) {
            this.n = false;
        }
    }

    public void a(Matrix matrix) {
    }

    public void b(int i) {
    }

    public void a(int i, int i2, int i3, int i4) {
        if (this.a != null && this.a.aE) {
            if (i > this.f) {
                this.f = i;
            }
            if (i > this.a.as) {
                this.a.as = i;
            }
            if (i2 > this.a.at) {
                if (this.a.at > 0 && (this.a instanceof QRComicReadingVerticalActivity)) {
                    QRComicReadingVerticalActivity qRComicReadingVerticalActivity = (QRComicReadingVerticalActivity) this.a;
                    int i5 = i2 - this.a.at;
                    if (g.a()) {
                        g.a("QRComicScrollReaderHelper", g.d, "mNavigationBarHeight = " + qRComicReadingVerticalActivity.aD + " , now height = " + i5);
                    }
                    if (qRComicReadingVerticalActivity.aD != i5) {
                        qRComicReadingVerticalActivity.c(i5);
                    }
                }
                this.a.at = i2;
            }
            if (this.g != null && (this.g.getAdapter() instanceof b)) {
                b bVar = (b) this.g.getAdapter();
                if (i > bVar.a()) {
                    bVar.a(i);
                }
                this.g.requestLayout();
            }
        }
        if (g.a()) {
            g.a("QRComicScrollReaderHelper", g.d, "new width = " + i + " , new height = " + i2 + " , old width = " + i3 + " , old height = " + i4);
        }
    }

    public void a(d dVar, ComicRecommendPageInfo comicRecommendPageInfo, OnClickListener onClickListener) {
        if (dVar != null && comicRecommendPageInfo != null) {
            if (TextUtils.isEmpty(comicRecommendPageInfo.d)) {
                dVar.a.setText("");
            } else {
                dVar.a.setText(comicRecommendPageInfo.d);
            }
            if (comicRecommendPageInfo.c == 2) {
                dVar.c.setVisibility(8);
                dVar.b.setVisibility(0);
                dVar.b.setText(this.a.getString(b.a.a.a.a.a.g.recommend_page_to_comment));
                dVar.b.setTextColor(-19456);
                if (onClickListener != null) {
                    dVar.b.setOnClickListener(onClickListener);
                }
            } else {
                dVar.b.setOnClickListener(null);
                if (this.a.c()) {
                    dVar.b.setVisibility(8);
                    dVar.c.setVisibility(8);
                } else {
                    dVar.b.setVisibility(0);
                    dVar.c.setVisibility(0);
                    dVar.b.setText(this.a.getString(b.a.a.a.a.a.g.collection_tip));
                    dVar.b.setTextColor(-8947849);
                    dVar.c.setEnabled(true);
                    dVar.c.setText(this.a.getString(b.a.a.a.a.a.g.reader_add_fav));
                    dVar.c.setText(this.a.getResources().getString(b.a.a.a.a.a.g.reader_add_fav));
                }
            }
            if (comicRecommendPageInfo.e != null && comicRecommendPageInfo.e.size() > 0) {
                this.a.a(dVar.d, dVar.g, (RecommendComicInfo) comicRecommendPageInfo.e.get(0), 0);
                if (comicRecommendPageInfo.e.size() > 1) {
                    this.a.a(dVar.e, dVar.h, (RecommendComicInfo) comicRecommendPageInfo.e.get(1), 1);
                }
                if (comicRecommendPageInfo.e.size() > 2) {
                    this.a.a(dVar.f, dVar.i, (RecommendComicInfo) comicRecommendPageInfo.e.get(2), 2);
                }
            }
        }
    }

    public boolean j() {
        return this.a.l();
    }

    public void a(String str) {
        com.qrcomic.activity.reader.a aVar = this.a.Z;
        if (aVar != null && aVar.o != null) {
            String str2;
            if (aVar.H == 2) {
                str2 = "0";
            } else if (this.a.aF == 0) {
                str2 = "2";
            } else {
                str2 = "1";
            }
        }
    }

    public boolean k() {
        com.qrcomic.activity.reader.a aVar = this.a.Z;
        if (aVar == null || aVar.H != 2) {
            return false;
        }
        return true;
    }
}
