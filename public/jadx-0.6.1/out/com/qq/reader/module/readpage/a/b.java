package com.qq.reader.module.readpage.a;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.g;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ai;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.OnlineChapterComment;
import com.qq.reader.module.question.card.view.AudioAuthorStateView;
import com.qq.reader.module.question.card.view.AudioComItemView;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.readpage.PopupLayerView;
import com.qq.reader.module.readpage.j;
import com.qq.reader.module.readpage.s;
import com.qq.reader.readengine.kernel.e;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ReaderPageLayerChapterComment */
public class b extends s {
    List<OnlineChapterComment> f = new ArrayList();
    private String g;
    private TextView h;
    private View i;
    private PopupLayerView j;
    private int k;
    private AudioComItemView l;
    private AudioAuthorStateView m;
    private View n;
    private View o;

    /* compiled from: ReaderPageLayerChapterComment */
    public class a implements com.qq.reader.module.readpage.PopupLayerView.a {
        final /* synthetic */ b a;
        private List<OnlineChapterComment> b;
        private int c;

        public a(b bVar, List<OnlineChapterComment> list, int i) {
            this.a = bVar;
            this.b = list;
            this.c = i;
        }

        public int a() {
            return this.c;
        }

        public int b() {
            if (this.b != null) {
                return this.b.size();
            }
            return 0;
        }

        public int c() {
            return R.layout.read_page_end_popup_comment_item;
        }

        public Object a(int i) {
            if (this.b == null || i >= this.b.size()) {
                return null;
            }
            return this.b.get(i);
        }

        public void a(View view, int i, Object obj) {
            if (d.n) {
                view.setBackgroundResource(R.drawable.read_pager_end_popup_comment_bg_night_mode);
            } else {
                view.setBackgroundResource(R.drawable.read_pager_end_popup_comment_bg);
            }
            OnlineChapterComment onlineChapterComment = (OnlineChapterComment) obj;
            String userHeadIconUrl = onlineChapterComment.getUserHeadIconUrl();
            CharSequence obj2 = Html.fromHtml(onlineChapterComment.getCommentText()).toString();
            c.a(this.a.f()).a(userHeadIconUrl, (ImageView) view.findViewById(R.id.img_avatar), com.qq.reader.common.imageloader.a.a().s());
            TextView textView = (TextView) view.findViewById(R.id.tv_comment_content);
            CharSequence a = com.qq.reader.common.emotion.b.a(ReaderApplication.getApplicationImp(), obj2, textView.getTextSize());
            if (textView != null) {
                try {
                    if (!TextUtils.isEmpty(a)) {
                        textView.setText(a);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            textView.setText("");
        }
    }

    public b(final Context context) {
        this.a = LayoutInflater.from(context).inflate(R.layout.readerpage_chapter_comment_layer, null);
        this.a.setLayoutParams(new LayoutParams(-1, -2));
        this.h = (TextView) this.a.findViewById(R.id.chapter_discuss);
        this.i = this.a.findViewById(R.id.chapter_comment_area);
        this.j = (PopupLayerView) this.a.findViewById(R.id.comment_layout);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Handler e = this.a.e();
                if (e != null) {
                    e.sendEmptyMessage(1240);
                }
            }
        });
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b b;

            public void onClick(View view) {
                i.a("event_B200", null, context);
                Handler e = this.b.e();
                if (e != null) {
                    e.sendEmptyMessage(1240);
                }
            }
        });
        this.a.setVisibility(8);
        this.m = (AudioAuthorStateView) this.a.findViewById(R.id.author_state_layout);
        this.l = (AudioComItemView) this.a.findViewById(R.id.answer_body_layout);
        this.n = this.a.findViewById(R.id.audio_divider);
        this.o = this.a.findViewById(R.id.author_divider);
        this.g = context.getResources().getString(R.string.chapter_comment_tip);
    }

    public boolean a(Message message) {
        switch (message.what) {
            case 10000508:
                h();
                return true;
            case 10000509:
                g();
                return true;
            case 10000510:
                i();
                return true;
            default:
                return super.a(message);
        }
    }

    public boolean a(Canvas canvas, e eVar) {
        this.j.a = true;
        boolean a = super.a(canvas, eVar);
        this.j.a = false;
        return a;
    }

    protected boolean a(int i) {
        return i == 105;
    }

    public void d() {
        super.d();
        if (this.a.getVisibility() == 4) {
            this.j.b();
        }
    }

    protected void a(com.qq.reader.readengine.kernel.c.d dVar) {
        if (this.a != null) {
            e h = dVar.h();
            if (h instanceof com.qq.reader.readengine.kernel.b.b) {
                float k = ((float) j.k()) + h.f();
                OnlineChapter k2 = ((com.qq.reader.readengine.kernel.b.b) h).k();
                CharSequence charSequence = "加入讨论吧>";
                this.f.clear();
                j();
                if (k2 != null) {
                    this.k = k2.getChapterId();
                    if (k2.getCommentCount() > 0) {
                        charSequence = String.format(this.g, new Object[]{Integer.valueOf(k2.getCommentCount())});
                        if (!(((com.qq.reader.readengine.kernel.b.b) h).l() || b(this.k))) {
                            this.f.addAll(k2.getHotCommentList());
                        }
                    } else if (!((com.qq.reader.readengine.kernel.b.b) h).l()) {
                        b(this.k);
                    }
                }
                l();
                if (this.f.size() > 0) {
                    this.j.setVisibility(0);
                    this.j.setAdapter(new a(this, this.f, this.k));
                    i.a("event_B152", null, ReaderApplication.getApplicationImp());
                } else {
                    this.j.setVisibility(8);
                }
                this.h.setText(charSequence);
                this.a.setPadding(j.j(), (int) k, j.i(), 0);
                this.a.requestLayout();
                if (!ai.a()) {
                    this.a.layout(0, 0, 0, 0);
                }
            }
        }
    }

    private void j() {
        this.l.setVisibility(8);
        this.n.setVisibility(8);
        this.m.setVisibility(8);
        this.o.setVisibility(8);
    }

    private boolean b(int i) {
        int[] k;
        boolean z;
        final AudioData a = g.a().a(i);
        if (a != null) {
            k = k();
            if (k != null && k.length > 1) {
                this.l.a(k[0], k[1]);
                this.n.setBackgroundColor(k[1]);
                this.n.setAlpha(0.2f);
            }
            this.l.setType(2);
            this.l.a(a);
            this.l.setSupportPlay(false);
            this.l.setVisibility(0);
            switch (a.b().e()) {
                case 1:
                    if (!com.qq.reader.module.question.b.d(a.b().m())) {
                        i.a("event_D176", null, ReaderApplication.getApplicationImp());
                        break;
                    }
                    i.a("event_D179", null, ReaderApplication.getApplicationImp());
                    break;
                case 2:
                    if (a.b().n() != 1) {
                        i.a("event_D179", null, ReaderApplication.getApplicationImp());
                        break;
                    }
                    i.a("event_D184", null, ReaderApplication.getApplicationImp());
                    break;
                case 3:
                    i.a("event_D184", null, ReaderApplication.getApplicationImp());
                    break;
                default:
                    i.a("event_D179", null, ReaderApplication.getApplicationImp());
                    break;
            }
            this.l.setOnClickListener(a(a, false));
            this.l.setOnPlayBtnClickListener(a(a, true));
            this.n.setVisibility(0);
            z = true;
        } else {
            k = null;
            z = false;
        }
        final com.qq.reader.module.question.data.a c = g.a().c();
        if (c == null) {
            return z;
        }
        if (a != null) {
            this.m.setType(2);
            this.o.setVisibility(0);
        } else {
            i.a("event_D182", null, ReaderApplication.getApplicationImp());
            this.m.setType(8);
            this.m.setIconListener(new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(View view) {
                    o.c(this.b.f(), String.valueOf(c.d()), c.a(), c.b(), null);
                }
            });
        }
        if (k == null || k.length < 2) {
            k = k();
        }
        if (k != null && k.length > 1) {
            this.m.a(k[0], k[1]);
            this.o.setBackgroundColor(k[1]);
            this.o.setAlpha(0.2f);
        }
        this.m.a(c);
        OnClickListener anonymousClass4 = new OnClickListener(this) {
            final /* synthetic */ b c;

            public void onClick(View view) {
                o.a(this.c.f(), c.d());
                if (a != null) {
                    switch (a.b().e()) {
                        case 1:
                            if (com.qq.reader.module.question.b.d(a.b().m())) {
                                i.a("event_D181", null, ReaderApplication.getApplicationImp());
                                return;
                            } else {
                                i.a("event_D178", null, ReaderApplication.getApplicationImp());
                                return;
                            }
                        case 2:
                            if (a.b().n() == 1) {
                                i.a("event_D190", null, ReaderApplication.getApplicationImp());
                                return;
                            } else {
                                i.a("event_D181", null, ReaderApplication.getApplicationImp());
                                return;
                            }
                        case 3:
                            i.a("event_D190", null, ReaderApplication.getApplicationImp());
                            return;
                        default:
                            i.a("event_D181", null, ReaderApplication.getApplicationImp());
                            return;
                    }
                }
                i.a("event_D183", null, ReaderApplication.getApplicationImp());
            }
        };
        this.m.setButttonListener(anonymousClass4);
        this.m.setOnClickListener(anonymousClass4);
        this.m.setVisibility(0);
        return true;
    }

    private int[] k() {
        int L = d.L(f());
        int[] iArr = new int[2];
        if (d.n) {
            iArr[0] = -8815488;
            iArr[1] = -10066330;
        } else if (L < 8) {
            Resources resources = f().getResources();
            TypedArray obtainTypedArray = resources.obtainTypedArray(R.array.textStyles);
            TypedArray obtainTypedArray2 = resources.obtainTypedArray(R.array.infoStyles);
            iArr[0] = obtainTypedArray.getColor(L, 0);
            iArr[1] = obtainTypedArray2.getColor(L, 0);
            obtainTypedArray.recycle();
            obtainTypedArray2.recycle();
        } else {
            int[] N = d.N(f());
            iArr[0] = N[0];
            iArr[1] = N[0];
        }
        return iArr;
    }

    private OnClickListener a(final AudioData audioData, final boolean z) {
        return new OnClickListener(this) {
            final /* synthetic */ b c;

            public void onClick(View view) {
                com.qq.reader.module.question.b.a(this.c.f(), audioData, z);
                switch (audioData.b().e()) {
                    case 1:
                        if (com.qq.reader.module.question.b.d(audioData.b().m())) {
                            i.a("event_D180", null, ReaderApplication.getApplicationImp());
                            return;
                        } else {
                            i.a("event_D177", null, ReaderApplication.getApplicationImp());
                            return;
                        }
                    case 2:
                        if (audioData.b().n() == 1) {
                            i.a("event_D189", null, ReaderApplication.getApplicationImp());
                            return;
                        } else {
                            i.a("event_D180", null, ReaderApplication.getApplicationImp());
                            return;
                        }
                    case 3:
                        i.a("event_D189", null, ReaderApplication.getApplicationImp());
                        return;
                    default:
                        i.a("event_D180", null, ReaderApplication.getApplicationImp());
                        return;
                }
            }
        };
    }

    public void g() {
        if (this.j != null) {
            this.j.c();
        }
    }

    public void h() {
        if (this.j.getVisibility() == 0 && this.j != null) {
            this.j.d();
        }
    }

    public void i() {
        if (this.j != null) {
            this.j.a();
        }
        l();
    }

    private void l() {
        if (this.h == null) {
            return;
        }
        if (d.n) {
            this.h.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.reader_endpage_discuss_textcolor_night_mode));
            this.h.setCompoundDrawablesWithIntrinsicBounds(ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.readpage_chapter_comment_icon_night_mode), null, null, null);
            return;
        }
        this.h.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.reader_endpage_discuss_textcolor));
        this.h.setCompoundDrawablesWithIntrinsicBounds(ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.readpage_chapter_comment_icon), null, null, null);
    }
}
