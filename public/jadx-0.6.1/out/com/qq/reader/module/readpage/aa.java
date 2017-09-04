package com.qq.reader.module.readpage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.zxing.common.StringUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.db.handle.s;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.ReadPageCorrectTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.readengine.kernel.g;
import com.qq.reader.readengine.model.Chapter;
import com.qq.reader.readengine.model.IBook;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.MarkView;
import com.qq.reader.view.PagePopupWindow;
import com.qq.reader.view.ac;
import com.qq.reader.view.af;
import com.qq.reader.view.aj;
import com.qq.reader.view.ak;
import com.qq.reader.view.animation.AnimationProvider;
import com.qq.reader.view.animation.i;
import com.tencent.feedback.proguard.R;
import com.tencent.midas.data.APMidasPluginInfo;
import com.tencent.open.SocialConstants;
import com.tencent.theme.SkinnableBitmapDrawable;
import format.epub.view.ZLRectNoteArrayList;
import format.epub.view.ZLTextElementAreaArrayList;
import format.epub.view.h;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SelectionController */
public class aa implements x {
    private static IBook K;
    private static int L = -1;
    private static long M = 0;
    private static int N = 0;
    private static int O = 0;
    public static int m = 0;
    public static int n = 1;
    public static int o = 2;
    public static int p = 1;
    public static int q = 200;
    public static int r = 50;
    private d A;
    private PagePopupWindow B;
    private PopupWindow C;
    private com.qq.reader.readengine.model.b D;
    private MarkView E;
    private MarkView F;
    private boolean G;
    private j H;
    private Activity I;
    private Handler J = null;
    private String P = null;
    private int Q = -13395457;
    private final int R = 0;
    private final int S = 1;
    private String T = "";
    private String U = "";
    private String V = "http://api.iciba.com/hanyu/hanzi.php?";
    private String W = "http://api.iciba.com/hanyu/ci.php?";
    private String X = "http://api.iciba.com/qqreader/search.php?";
    private String Y = "[①②③④⑤⑥⑦⑧⑨⑩]";
    private String Z = "联网失败，请稍后再试";
    format.epub.view.u.c a = null;
    private String aa = "搜索无结果，请使用“爱词霸”或“soso百科”获取更多内容";
    private int ab;
    private List<MarkView> ac;
    private af ad;
    private float ae = 0.0f;
    private float af = 0.0f;
    private int ag = -1;
    private float ah = 0.0f;
    private int[] ai = new int[2];
    private boolean aj = false;
    Context b;
    View c;
    float d;
    float e;
    float f;
    float g;
    i h;
    final String i = new String("~|!|@|#|$|%|_|`|-|=|[|]|\\|:|\"|;|'|<|>|,|！|@|#|-|=|｛|｝|【|】|、|：|；|‘|《|》|？|，|。|、");
    final String[] j = new String[]{"?", "？", ")", "）", "}", "(", "（", "}", "%", "“", "”", "."};
    af k;
    public int l = m;
    private a s;
    private a t;
    private a u;
    private g v;
    private g w;
    private c x;
    private c y;
    private b z;

    /* compiled from: SelectionController */
    private class a extends View {
        aa a;
        Bitmap b;
        Drawable c;
        Paint d;
        boolean e = false;
        boolean f = false;
        int g = 0;
        final /* synthetic */ aa h;
        private int i;
        private h j = null;
        private float k = 0.0f;
        private float l;
        private float m;
        private final int n = 60;

        protected void onDraw(Canvas canvas) {
            if (this.e && !this.f) {
                canvas.drawBitmap(this.b, this.l, this.m, this.d);
            }
            super.onDraw(canvas);
        }

        public void a() {
            if (this.i == 0) {
                this.c = getResources().getDrawable(R.drawable.select_handle_start);
            } else {
                this.c = getResources().getDrawable(R.drawable.select_handle_end);
            }
            this.b = null;
            if (this.c instanceof BitmapDrawable) {
                this.b = ((BitmapDrawable) this.c).getBitmap();
            } else if (this.c instanceof SkinnableBitmapDrawable) {
                this.b = ((SkinnableBitmapDrawable) this.c).getBitmap();
            }
            this.d = new Paint();
        }

        public synchronized boolean onTouchEvent(MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.h.l();
                    this.k = motionEvent.getRawY();
                    this.h.x.c();
                    break;
                case 1:
                case 3:
                    this.h.l = aa.m;
                    this.h.x.b();
                    break;
                case 2:
                    float f;
                    float rawX = motionEvent.getRawX();
                    float rawY = motionEvent.getRawY();
                    AnimationProvider animationProvider = ((ReaderTextPageView) this.h.c).getAnimationProvider();
                    if (this.i != 0 || rawY < this.h.t.j.d) {
                        if (this.i != 1 || rawY > this.h.s.j.c) {
                            f = rawY;
                        } else if (animationProvider instanceof i) {
                            this.k = rawY;
                            this.h.l = aa.m;
                            this.h.u = null;
                            this.h.aj = true;
                            break;
                        } else {
                            f = this.h.s.j.d;
                            this.h.u = null;
                            this.h.aj = true;
                        }
                    } else if (animationProvider instanceof i) {
                        this.k = rawY;
                        this.h.l = aa.m;
                        this.h.u = null;
                        this.h.aj = true;
                        break;
                    } else {
                        f = this.h.t.j.c;
                        this.h.u = null;
                        this.h.aj = true;
                    }
                    if (animationProvider instanceof i) {
                        aa.p = this.i;
                        if (f > ((float) ((this.h.H.b - this.h.H.l()) - aa.r))) {
                            f = (float) ((this.h.H.b - this.h.H.l()) - aa.r);
                        } else {
                            this.h.H;
                            if (f < ((float) (j.k() + aa.r))) {
                                this.h.H;
                                f = (float) (j.k() + aa.r);
                            }
                        }
                        float[] a = this.a.a(this.i, rawX, f, "onTouchEvent move 1");
                        if (a[0] == -1.0f && a[1] == -1.0f) {
                            this.k = f;
                            this.h.l = aa.m;
                            break;
                        }
                        boolean z;
                        rawY = a[1];
                        if (rawY - this.k < 0.0f || ((float) (this.h.H.b - this.h.H.l())) - rawY >= ((float) aa.q)) {
                            if (rawY - this.k <= 0.0f) {
                                this.h.H;
                                if (rawY - ((float) j.k()) < ((float) aa.q) && b(false)) {
                                    this.h.l = aa.o;
                                    this.h.k();
                                    this.k = rawY;
                                    z = true;
                                }
                            }
                            z = false;
                        } else {
                            if (b(true)) {
                                this.h.l = aa.n;
                                this.h.k();
                                this.k = rawY;
                                z = true;
                            }
                            z = false;
                        }
                        if (!z) {
                            this.k = rawY;
                            this.h.l();
                        }
                    } else {
                        this.a.a(this.i, rawX, f, "onTouchEvent move 2");
                    }
                    this.h.c.invalidate();
                    break;
            }
            return true;
        }

        private boolean b(boolean z) {
            int i;
            if (z) {
                i = this.g + 1;
            } else {
                i = this.g - 1;
            }
            if (Math.abs(i) <= 60) {
                this.g = i;
                return true;
            }
            this.h.ad.a();
            return false;
        }

        public a(aa aaVar, aa aaVar2, int i) {
            this.h = aaVar;
            super(aaVar.b);
            this.a = aaVar2;
            this.i = i;
            a();
            this.g = 0;
        }

        public int b() {
            return this.i;
        }

        public void a(boolean z) {
            this.h.E.setIsOutOfScreen(this.i, z);
            this.f = z;
        }

        public void c() {
            this.e = true;
            invalidate();
        }

        public void d() {
            if (this.e) {
                this.e = false;
                invalidate();
            }
        }

        public void a(float f, float f2, h hVar, String str) {
            this.j = hVar;
            if (this.i == 0) {
                this.l = (float) Math.round(f - ((float) this.c.getMinimumWidth()));
                this.m = (float) Math.round(f2 - ((float) this.c.getMinimumHeight()));
                return;
            }
            this.l = (float) Math.round(f);
            this.m = (float) Math.round(f2 - ((float) (this.c.getMinimumHeight() / 5)));
        }

        public boolean a(float f, float f2) {
            float minimumWidth = this.l + ((float) (this.c.getMinimumWidth() / 2));
            float minimumHeight = this.m + ((float) (this.c.getMinimumHeight() / 2));
            float minimumWidth2 = minimumWidth - ((float) (this.c.getMinimumWidth() * 2));
            minimumWidth += (float) (this.c.getMinimumWidth() * 2);
            float minimumHeight2 = minimumHeight - ((float) (this.c.getMinimumHeight() * 2));
            minimumHeight += (float) (this.c.getMinimumHeight() * 2);
            if (f < minimumWidth2 || f > minimumWidth || f2 < minimumHeight2 || f2 > minimumHeight) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: SelectionController */
    private class b extends View {
        final /* synthetic */ aa a;
        private View b = null;
        private PopupWindow c = null;
        private EditText d = null;
        private ImageButton e = null;
        private ImageButton f = null;
        private TextView g = null;
        private TextView h = null;
        private TextView i = null;
        private ProgressBar j = null;
        private LinearLayout k = null;
        private ScrollView l = null;
        private Thread m = null;
        private String n = null;
        private ForegroundColorSpan o = new ForegroundColorSpan(-11121339);

        public b(aa aaVar, Context context) {
            this.a = aaVar;
            super(context);
            f();
        }

        private void f() {
            this.b = LayoutInflater.from(this.a.I.getApplicationContext()).inflate(R.layout.dict_popup_window, null, false);
            this.c = new PopupWindow(this.b, this.a.b.getResources().getDimensionPixelOffset(R.dimen.common_dp_290), this.a.b.getResources().getDimensionPixelOffset(R.dimen.common_dp_340), true);
            this.c.setInputMethodMode(16);
            this.c.setOutsideTouchable(true);
            this.c.setBackgroundDrawable(new BitmapDrawable());
            this.c.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onDismiss() {
                    this.a.j.setVisibility(4);
                    if (this.a.m != null) {
                        this.a.m.interrupt();
                        this.a.m = null;
                    }
                }
            });
            this.d = (EditText) this.b.findViewById(R.id.dict_search_edittext);
            this.e = (ImageButton) this.b.findViewById(R.id.dict_search_button);
            this.f = (ImageButton) this.b.findViewById(R.id.clear_text_btn);
            this.g = (TextView) this.b.findViewById(R.id.dict_soso_textview);
            this.g.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.g();
                }
            });
            this.h = (TextView) this.b.findViewById(R.id.dict_iciba_textview);
            this.h.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.h();
                }
            });
            this.l = (ScrollView) this.b.findViewById(R.id.dict_popup_window_scrollview);
            this.k = (LinearLayout) this.b.findViewById(R.id.dict_popup_window_scrollview_root);
            this.j = (ProgressBar) this.b.findViewById(R.id.dict_popup_window_processbar);
            this.i = (TextView) this.b.findViewById(R.id.dict_popup_window_net_error_tip);
            if (this.a.k == null) {
                this.a.k = af.a(this.a.b, "请先输入翻译内容", 0);
            }
        }

        public void a(Thread thread) {
            if (thread != null) {
                this.m = thread;
                this.m.start();
            }
        }

        private void g() {
            com.qq.reader.common.monitor.i.a("event_B36", null, this.a.b);
            try {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("http://wap.soso.com/s.q?key=");
                stringBuffer.append(URLEncoder.encode(this.n.trim(), "UTF-8"));
                Intent intent = new Intent();
                intent.setClass(this.a.b, WebBrowserForContents.class);
                intent.putExtra("com.qq.reader.WebContent", stringBuffer.toString());
                this.a.I.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void h() {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                if (this.a.b(this.n.trim())) {
                    stringBuffer.append("http://wap.iciba.com/cword/" + this.n.trim());
                } else {
                    stringBuffer.append("http://wap.iciba.com/cword/" + this.n.trim());
                }
                Intent intent = new Intent();
                intent.setClass(this.a.b, WebBrowserForContents.class);
                intent.putExtra("com.qq.reader.WebContent", stringBuffer.toString());
                this.a.I.startActivity(intent);
                com.qq.reader.common.monitor.i.a("event_B52", null, this.a.b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public boolean a() {
            return this.j.getVisibility() == 0;
        }

        private void a(String str, String str2) {
            if (str != null || str2 != null) {
                int dimensionPixelOffset = this.a.b.getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
                int dimensionPixelOffset2 = this.a.b.getResources().getDimensionPixelOffset(R.dimen.common_dp_15);
                int dimensionPixelOffset3 = this.a.b.getResources().getDimensionPixelOffset(R.dimen.common_dp_6);
                if (str2 != null && str2.length() > 0) {
                    View textView = new TextView(getContext());
                    textView.setTextSize(18.0f);
                    textView.setPadding(dimensionPixelOffset2, dimensionPixelOffset3, dimensionPixelOffset2, 0);
                    textView.setTextColor(this.a.Q);
                    textView.setText(str2);
                    this.k.addView(textView);
                }
                String[] split = str.replaceAll(this.a.Y, "#").split("#");
                if (split != null) {
                    dimensionPixelOffset3 = 0;
                    int i = 1;
                    while (dimensionPixelOffset3 < split.length) {
                        if (split[dimensionPixelOffset3] != null && split[dimensionPixelOffset3].length() > 0) {
                            Object replace;
                            int i2;
                            String str3 = split[dimensionPixelOffset3];
                            View textView2 = new TextView(getContext());
                            if (str3.indexOf("&") != -1) {
                                replace = str3.replace("&", "");
                                textView2.setPadding(dimensionPixelOffset2, 0, dimensionPixelOffset2, 0);
                                i2 = 1;
                            } else {
                                textView2.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset2, 0);
                                String str4 = i + ".  " + split[dimensionPixelOffset3];
                                i2 = i + 1;
                                String str5 = str4;
                            }
                            textView2.setTextSize(16.0f);
                            CharSequence spannableString = new SpannableString(replace);
                            textView2.setText(replace);
                            spannableString.setSpan(this.o, 0, replace.length(), 33);
                            textView2.setText(spannableString);
                            this.k.addView(textView2);
                            i = i2;
                        }
                        dimensionPixelOffset3++;
                    }
                    this.l.scrollTo(0, 0);
                }
            }
        }

        public void a(String str) {
            this.k.setVisibility(8);
            this.j.setVisibility(8);
            this.i.setText(str);
            this.i.setVisibility(0);
            c();
        }

        public void b() {
            this.j.setVisibility(8);
        }

        private void i() {
            if (this.f != null) {
                if (this.d.getText().toString().length() > 0) {
                    this.f.setVisibility(0);
                } else {
                    this.f.setVisibility(4);
                }
            }
        }

        public void a(String str, String str2, boolean z, boolean z2, String str3) {
            this.a.b(str);
            this.n = str;
            this.d.setText(str);
            this.d.setOnFocusChangeListener(new OnFocusChangeListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        this.a.d.setSelection(this.a.d.getText().length());
                    }
                }
            });
            this.d.addTextChangedListener(new TextWatcher(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    this.a.i();
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                }
            });
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    String obj = this.a.d.getText().toString();
                    if (obj.length() > 0) {
                        this.a.a.b(obj, true);
                        this.a.c.update();
                    } else if (this.a.a.k != null) {
                        this.a.a.k.a();
                    }
                }
            });
            this.f.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        this.a.d.setText("");
                    }
                    return false;
                }
            });
            String str4 = "";
            if (str2 != null && str2.length() > 0) {
                StringBuffer stringBuffer = new StringBuffer();
                if (str2.indexOf("[") == -1) {
                    stringBuffer.append("[");
                    stringBuffer.append(str2);
                    stringBuffer.append("]");
                } else {
                    stringBuffer.append(str2);
                }
                str4 = stringBuffer.toString();
            }
            if (z) {
                this.j.setVisibility(8);
                this.i.setVisibility(8);
                this.k.removeAllViews();
                a(str3, str4);
                this.k.setVisibility(0);
            } else if (z2) {
                this.k.setVisibility(8);
                this.i.setVisibility(8);
                this.j.setVisibility(0);
            } else {
                this.i.setVisibility(8);
                this.j.setVisibility(8);
                this.k.removeAllViews();
                a(str3, str4);
                this.k.setVisibility(0);
            }
        }

        public void c() {
            this.c.showAtLocation(this.a.c, 17, 0, com.qq.reader.common.c.a.ca);
        }

        public void d() {
            this.c.update();
        }

        public void e() {
            this.d.clearFocus();
            this.c.dismiss();
        }
    }

    /* compiled from: SelectionController */
    private class c extends View {
        PopupWindow a = null;
        int b = 0;
        final /* synthetic */ aa c;
        private View d = null;
        private Drawable e;
        private int f;
        private int g;
        private TextView h;
        private TextView i;
        private TextView j;
        private TextView k;
        private TextView l;
        private TextView m;
        private TextView n;
        private int o = 0;
        private RelativeLayout p;
        private ImageView q;
        private int r;
        private int s;

        public c(aa aaVar, int i) {
            this.c = aaVar;
            super(aaVar.b);
            switch (i) {
                case 0:
                    this.o = 0;
                    this.b = 6;
                    break;
                case 1:
                    this.o = 1;
                    this.b = 3;
                    break;
            }
            d();
        }

        private void d() {
            this.p = new RelativeLayout(this.c.b);
            this.q = new ImageView(this.c.b);
            this.q.setId(1002);
            if (this.o == 0) {
                this.d = LayoutInflater.from(this.c.I.getApplicationContext()).inflate(R.layout.paopao_alert_dialog_0, null, false);
                this.d.setId(1001);
            } else {
                this.d = LayoutInflater.from(this.c.I.getApplicationContext()).inflate(R.layout.paopao_alert_dialog_1, null, false);
                this.d.setId(1001);
            }
            this.f = (int) getResources().getDimension(R.dimen.readpage_selection_width);
            this.g = getResources().getDrawable(R.drawable.select_handle_start).getIntrinsicHeight();
            this.d.setBackgroundResource(R.drawable.select_btn_down);
            this.e = this.c.I.getApplicationContext().getResources().getDrawable(R.drawable.select_btn_arrow);
            switch (this.o) {
                case 0:
                    this.h = (Button) this.d.findViewById(R.id.pop_copy);
                    this.h.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            com.qq.reader.common.monitor.i.a("event_B26", null, this.a.c.b);
                            ((ClipboardManager) this.a.c.b.getSystemService("clipboard")).setText(this.a.a());
                            this.a.c.e();
                            this.a.c.d();
                            ReaderTextPageView.r();
                            ((ReaderTextPageView) this.a.c.c).k();
                            this.a.c.c.invalidate();
                            af.a(this.a.c.b.getApplicationContext(), "复制成功", 0).a();
                        }
                    });
                    this.i = (Button) this.d.findViewById(R.id.pop_highlight);
                    this.i.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.c.b(this.a.a(), null);
                            com.qq.reader.common.monitor.i.a("event_B28", null, this.a.c.b);
                        }
                    });
                    this.j = (Button) this.d.findViewById(R.id.pop_remark);
                    this.j.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            com.qq.reader.common.monitor.i.a("event_B29", null, this.a.c.b);
                            this.a.c.e();
                            this.a.c.d();
                            this.a.c.c.invalidate();
                            ((ReaderPageActivity) this.a.c.I).a(this.a.a(), "", this.a.c);
                        }
                    });
                    this.k = (Button) this.d.findViewById(R.id.pop_share);
                    this.k.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.c.e();
                            this.a.c.d();
                            this.a.c.c.invalidate();
                            com.qq.reader.common.monitor.i.a("event_B27", null, this.a.c.b);
                            this.a.c.D = this.a.a(this.a.a());
                            ReaderTextPageView.r();
                            ((ReaderTextPageView) this.a.c.c).k();
                            this.a.c.r();
                        }
                    });
                    this.l = (Button) this.d.findViewById(R.id.pop_dic);
                    this.l.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            com.qq.reader.common.monitor.i.a("event_B31", null, this.a.c.b);
                            this.a.c.e();
                            this.a.c.d();
                            ReaderTextPageView.r();
                            ((ReaderTextPageView) this.a.c.c).k();
                            this.a.c.c.invalidate();
                            this.a.c.b(this.a.a(), false);
                        }
                    });
                    this.n = (Button) this.d.findViewById(R.id.pop_report_bug);
                    this.n.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.c.e();
                            this.a.c.d();
                            ReaderTextPageView.r();
                            ((ReaderTextPageView) this.a.c.c).k();
                            this.a.c.c.invalidate();
                            this.a.c.x();
                        }
                    });
                    return;
                case 1:
                    this.k = (Button) this.d.findViewById(R.id.pop_share);
                    this.k.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            com.qq.reader.common.monitor.i.a("event_B27", null, this.a.c.b);
                            this.a.c.e();
                            this.a.c.d();
                            ReaderTextPageView.r();
                            this.a.c.c.invalidate();
                            if (aa.O == 1 || (aa.K != null && aa.K.getBookNetId() > 0)) {
                                int i;
                                if (this.a.c.D.e() == null || this.a.c.D.e().length() <= 0) {
                                    i = 12;
                                } else {
                                    i = 15;
                                }
                                new ak(this.a.c.I, this.a.c.D, i).f_();
                                return;
                            }
                            this.a.c.s();
                        }
                    });
                    this.m = (Button) this.d.findViewById(R.id.pop_del);
                    this.m.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.c.e();
                            ReaderTextPageView.r();
                            if (this.a.c.D.e().length() == 0) {
                                this.a.e();
                            } else {
                                new com.qq.reader.view.AlertDialog.a(this.a.c.I).c(R.drawable.alert_dialog_icon).a(R.string.note_del_dialog_title).b(R.string.note_del_dialog_text).a(R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
                                    final /* synthetic */ AnonymousClass8 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        this.a.a.e();
                                    }
                                }).b(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
                                    final /* synthetic */ AnonymousClass8 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).a().show();
                            }
                            com.qq.reader.common.monitor.i.a("event_B25", null, this.a.c.b);
                        }
                    });
                    this.j = (Button) this.d.findViewById(R.id.pop_remark);
                    this.j.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            this.a.c.e();
                            this.a.c.d();
                            this.a.c.c.invalidate();
                            com.qq.reader.common.monitor.i.a("event_B29", null, this.a.c.b);
                            ((ReaderPageActivity) this.a.c.I).a(this.a.c.D.e(), this.a.c);
                            ReaderTextPageView.r();
                        }
                    });
                    return;
                default:
                    return;
            }
        }

        private void e() {
            String R;
            if (com.qq.reader.common.login.c.b()) {
                R = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
            } else {
                R = null;
            }
            if (s.a().a(R, this.c.D.f(), this.c.D.q())) {
                s.a().a(R, this.c.D.q(), 0, System.currentTimeMillis(), false);
                if (IBook.mRemarksList != null) {
                    for (Object obj : IBook.mRemarksList) {
                        if (obj.f() == this.c.D.f()) {
                            break;
                        }
                    }
                    Object obj2 = null;
                    if (obj2 != null) {
                        IBook.mRemarksList.remove(obj2);
                    }
                }
                ((ReaderTextPageView) this.c.c).k();
                this.c.c.invalidate();
                return;
            }
            af.a(this.c.b.getApplicationContext(), "删除失败", 0).a();
        }

        public synchronized String a() {
            String a;
            try {
                a = this.c.H.a(this.c.s.j.d(), this.c.t.j.d());
            } catch (Exception e) {
                a = "";
            }
            return a;
        }

        public void b() {
            Point f = f();
            if (this.a == null) {
                this.a = new PopupWindow(this.p, this.r, this.s);
            }
            this.a.showAtLocation(this.c.c, 0, f.x, f.y);
            com.qq.reader.common.monitor.i.a("event_B141", null, this.c.b);
        }

        public void c() {
            if (this.a != null) {
                this.a.dismiss();
            }
        }

        private com.qq.reader.readengine.model.b a(String str) {
            int f;
            long g;
            int f2;
            long g2;
            g d = this.c.s.j.d();
            g d2 = this.c.t.j.d();
            if (aa.O == 1) {
                f = d.f();
                g = d.g();
                f2 = d2.f();
                g2 = d2.g();
            } else {
                int[] a = ((ReaderPageActivity) this.c.I).a(this.c.s.j.q, false);
                int[] a2 = ((ReaderPageActivity) this.c.I).a(this.c.t.j.q, false);
                f = a[0];
                g = (long) a[1];
                f2 = a2[0];
                g2 = (long) a2[1];
            }
            String str2 = str;
            com.qq.reader.readengine.model.b bVar = new com.qq.reader.readengine.model.b(-1, this.c.v(), this.c.w(), this.c.s.j.q + "", this.c.t.j.q + "", str2, "", System.currentTimeMillis(), f, g, f2, g2, aa.M, aa.N);
            if (aa.O == 1) {
                bVar.a(true);
            }
            return bVar;
        }

        private Point f() {
            this.d.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            this.r = this.d.getMeasuredWidth();
            int measuredHeight = this.d.getMeasuredHeight();
            this.p.removeAllViews();
            switch (this.o) {
                case 0:
                    return a(this.c.E, (int) this.c.e, (int) this.c.g, measuredHeight);
                case 1:
                    return a(this.c.F, (int) this.c.F.getFirstLineStartPoint().y, ((int) this.c.F.getLastLineStartPoint().y) + ((int) com.qq.reader.appconfig.a.d.I(this.c.I)), measuredHeight);
                default:
                    return new Point((this.c.c.getWidth() - this.r) / 2, (this.c.c.getHeight() - measuredHeight) / 2);
            }
        }

        private Point a(MarkView markView, int i, int i2, int i3) {
            float f;
            int I;
            int i4;
            g();
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            float e = ao.e(this.c.I);
            if ((i2 + i3) + this.g < this.c.c.getBottom()) {
                if (com.qq.reader.appconfig.a.d.n) {
                    this.d.setBackgroundResource(R.drawable.select_btn_down_night);
                    this.q.setBackgroundResource(R.drawable.select_arrow_up_night);
                } else {
                    this.d.setBackgroundResource(R.drawable.select_btn_down);
                    this.q.setBackgroundResource(R.drawable.select_arrow_up);
                }
                layoutParams2.addRule(10);
                layoutParams.addRule(3, 1002);
                layoutParams.topMargin = (int) (e * -1.0f);
                f = (markView.getLastLineStartPoint().x + markView.getLastLineEndPoint().x) / 2.0f;
                if (this.o == 1) {
                    I = ((int) com.qq.reader.appconfig.a.d.I(this.c.I)) + i2;
                } else {
                    I = this.g + i2;
                }
                this.s = this.e.getIntrinsicHeight() + i3;
            } else if ((i - i3) - this.g > this.c.c.getTop()) {
                layoutParams.addRule(10);
                if (com.qq.reader.appconfig.a.d.n) {
                    this.d.setBackgroundResource(R.drawable.select_btn_down_night);
                    this.q.setBackgroundResource(R.drawable.select_arrow_down_night);
                } else {
                    this.d.setBackgroundResource(R.drawable.select_btn_down);
                    this.q.setBackgroundResource(R.drawable.select_arrow_down);
                }
                layoutParams2.addRule(3, 1001);
                layoutParams2.topMargin = (int) (e * -23.5f);
                f = (markView.getFirstLineStartPoint().x + markView.getFirstLineEndPoint().x) / 2.0f;
                if (this.o == 1) {
                    I = i - i3;
                } else {
                    I = (i - i3) - this.g;
                }
                this.s = i3;
            } else {
                if (com.qq.reader.appconfig.a.d.n) {
                    this.d.setBackgroundResource(R.drawable.select_btn_down_night);
                    this.q.setBackgroundResource(R.drawable.select_arrow_down_night);
                } else {
                    this.d.setBackgroundResource(R.drawable.select_btn_down);
                    this.q.setBackgroundResource(R.drawable.select_arrow_down);
                }
                layoutParams.addRule(10);
                layoutParams2.addRule(3, 1001);
                layoutParams2.topMargin = (int) (e * -23.5f);
                f = (float) (this.c.c.getWidth() / 2);
                I = (this.c.c.getHeight() - i3) / 2;
                this.s = i3;
            }
            if (f > ((float) (this.r / 2)) && ((float) this.c.c.getWidth()) - f > ((float) (this.r / 2))) {
                layoutParams2.addRule(14);
                i4 = ((int) f) - (this.r / 2);
            } else if (f < ((float) (this.r / 2))) {
                layoutParams2.leftMargin = (int) f;
                i4 = 10;
            } else if (((float) this.c.c.getWidth()) - f < ((float) (this.r / 2))) {
                int width = (this.c.c.getWidth() - this.r) - 10;
                layoutParams2.leftMargin = ((int) f) - width;
                i4 = width;
            } else {
                i4 = 0;
            }
            this.p.addView(this.q, layoutParams2);
            this.p.addView(this.d, layoutParams);
            return new Point(i4, I);
        }

        private void g() {
            ColorStateList colorStateList;
            if (com.qq.reader.appconfig.a.d.n) {
                colorStateList = getResources().getColorStateList(R.color.select_btn_textcolor_night);
                this.d.findViewById(R.id.line0).setBackgroundResource(R.color.paopao_dlg_divider_night);
                this.d.findViewById(R.id.line1).setBackgroundResource(R.color.paopao_dlg_divider_night);
                if (this.o == 0) {
                    this.d.findViewById(R.id.line2).setBackgroundResource(R.color.paopao_dlg_divider_night);
                    this.d.findViewById(R.id.line3).setBackgroundResource(R.color.paopao_dlg_divider_night);
                    this.h.setTextColor(colorStateList);
                    this.l.setTextColor(colorStateList);
                    this.i.setTextColor(colorStateList);
                    this.n.setTextColor(colorStateList);
                } else {
                    this.m.setTextColor(colorStateList);
                }
                this.k.setTextColor(colorStateList);
                this.j.setTextColor(colorStateList);
                return;
            }
            colorStateList = getResources().getColorStateList(R.color.select_btn_textcolor_day);
            this.d.findViewById(R.id.line0).setBackgroundResource(R.color.common_textcolor_primary);
            this.d.findViewById(R.id.line1).setBackgroundResource(R.color.common_textcolor_primary);
            if (this.o == 0) {
                this.d.findViewById(R.id.line2).setBackgroundResource(R.color.common_textcolor_primary);
                this.d.findViewById(R.id.line3).setBackgroundResource(R.color.common_textcolor_primary);
                this.h.setTextColor(colorStateList);
                this.l.setTextColor(colorStateList);
                this.i.setTextColor(colorStateList);
                this.n.setTextColor(colorStateList);
            } else {
                this.m.setTextColor(colorStateList);
            }
            this.k.setTextColor(colorStateList);
            this.j.setTextColor(colorStateList);
        }
    }

    /* compiled from: SelectionController */
    private class d extends View {
        RadioGroup a;
        final /* synthetic */ aa b;
        private View c = null;
        private BaseDialog d = null;
        private RadioButton[] e = new RadioButton[5];
        private Integer[] f = new Integer[]{Integer.valueOf(R.id.bug_report_0), Integer.valueOf(R.id.bug_report_1), Integer.valueOf(R.id.bug_report_2), Integer.valueOf(R.id.bug_report_3), Integer.valueOf(R.id.bug_report_4)};
        private String[] g = new String[]{"错别字", "章节错乱", "内容缺失", "图片缺失", "排版混乱", "其他"};

        public d(aa aaVar, Context context) {
            this.b = aaVar;
            super(context);
            c();
        }

        private void c() {
            this.c = View.inflate(this.b.I.getApplicationContext(), R.layout.linear_readermenu, null);
            this.d = new ac(this.c, this.b.I);
            ListView listView = (ListView) this.c;
            listView.setAdapter(new BaseAdapter(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public int getCount() {
                    return 5;
                }

                public Object getItem(int i) {
                    return null;
                }

                public long getItemId(int i) {
                    return 0;
                }

                public View getView(int i, View view, ViewGroup viewGroup) {
                    View inflate = View.inflate(this.a.b.I.getApplicationContext(), R.layout.linear_menuitem, null);
                    TextView textView = (TextView) inflate.findViewById(R.id.linear_menu_name);
                    if (i >= 0 && i < 5 && textView != null) {
                        textView.setText(this.a.g[i]);
                    }
                    return inflate;
                }
            });
            listView.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (!this.a.b.I.isFinishing()) {
                        this.a.d.dismiss();
                        if (i >= 0 && i < this.a.g.length) {
                            this.a.b.a(i + 1, this.a.g[i]);
                            Map hashMap = new HashMap();
                            hashMap.put(com.qq.reader.module.bookstore.qnative.item.s.ORIGIN, i + "");
                            com.qq.reader.common.monitor.i.a("event_B143", hashMap, this.a.b.b);
                        }
                    }
                }
            });
        }

        public void a() {
            this.d.f_();
        }

        public void b() {
            for (RadioButton radioButton : this.e) {
                if (radioButton != null) {
                    radioButton.setChecked(false);
                }
            }
            if (this.a != null) {
                this.a.clearCheck();
            }
        }
    }

    aa(Context context, Activity activity, View view, i iVar, j jVar) {
        this.b = context;
        this.I = activity;
        this.c = view;
        this.h = iVar;
        this.s = new a(this, this, 0);
        this.t = new a(this, this, 1);
        this.H = jVar;
        this.E = new MarkView(this.b, true, false, 0);
        this.x = new c(this, 0);
        this.y = new c(this, 1);
        this.z = new b(this, context);
        this.A = new d(this, context);
        t();
        this.ab = ViewConfiguration.get(this.b.getApplicationContext()).getScaledTouchSlop();
        this.ac = new ArrayList();
        this.ad = af.a(this.I.getApplicationContext(), "已经划取到最大范围", 0);
    }

    private void r() {
        if (O == 1 || (K != null && K.getBookNetId() > 0)) {
            new ak(this.I, this.D, 12).f_();
        } else {
            s();
        }
    }

    private void s() {
        if (K != null) {
            M = K.getBookNetId();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("《");
            stringBuffer.append(w());
            stringBuffer.append("》笔记");
            new aj(this.I, "", stringBuffer.toString(), this.T, 12).f_();
        }
    }

    private void t() {
        this.J = new Handler(this) {
            final /* synthetic */ aa a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                String str = null;
                switch (message.what) {
                    case 40002:
                        try {
                            String str2 = (String) message.obj;
                            if (str2.toLowerCase().indexOf("error_code") != -1) {
                                this.a.z.a(this.a.aa);
                                return;
                            }
                            String string;
                            StringBuffer stringBuffer = new StringBuffer();
                            if (str2.toLowerCase().indexOf("Warning".toLowerCase()) != -1) {
                                str2 = str2.split("\n")[0];
                            }
                            JSONObject jSONObject = new JSONObject(str2);
                            int i;
                            if (str2.toLowerCase().indexOf("word_name") != -1) {
                                JSONObject jSONObject2 = jSONObject.getJSONObject("word_result").getJSONObject("simple_means");
                                string = jSONObject2.getString("word_name");
                                jSONObject2 = jSONObject2.getJSONArray("symbols").getJSONObject(0);
                                str = jSONObject2.getString("ph_en");
                                JSONArray jSONArray = jSONObject2.getJSONArray("parts");
                                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                    jSONObject2 = jSONArray.getJSONObject(i2);
                                    stringBuffer.append("&" + jSONObject2.getString("part"));
                                    stringBuffer.append("#");
                                    JSONArray jSONArray2 = jSONObject2.getJSONArray("means");
                                    for (i = 0; i < jSONArray2.length(); i++) {
                                        stringBuffer.append(jSONArray2.getString(i));
                                        stringBuffer.append("#");
                                    }
                                }
                            } else {
                                Iterator keys = jSONObject.keys();
                                String str3 = null;
                                while (keys != null && keys.hasNext()) {
                                    str2 = (String) keys.next();
                                    if (!(str2 == null || str2.length() == 0)) {
                                        string = jSONObject.getString(str2);
                                        if (!(string == null || string.length() == 0)) {
                                            if (str2.equalsIgnoreCase("hanzi") || str2.equalsIgnoreCase("chengyu") || str2.equalsIgnoreCase("ciyu")) {
                                                str3 = string;
                                            }
                                            if (!str2.equalsIgnoreCase(SocialConstants.PARAM_URL)) {
                                                if (str2.equalsIgnoreCase("pinyin")) {
                                                    str2 = string;
                                                } else if (str2.equalsIgnoreCase("jieshi")) {
                                                    i = string.indexOf("①");
                                                    if (i > 0) {
                                                        stringBuffer.append(string.subSequence(i, string.length()));
                                                    } else {
                                                        stringBuffer.append(string);
                                                    }
                                                    stringBuffer.append("\n");
                                                    str2 = str;
                                                } else if (str2.equalsIgnoreCase("shiyi")) {
                                                    stringBuffer.append(string);
                                                    stringBuffer.append("\n");
                                                    str2 = str;
                                                } else if (str2.equalsIgnoreCase("ciyi")) {
                                                    stringBuffer.append(string);
                                                    stringBuffer.append("\n");
                                                }
                                                str = str2;
                                            }
                                            str2 = str;
                                            str = str2;
                                        }
                                    }
                                }
                                string = str3;
                            }
                            if (this.a.z != null && this.a.z.a()) {
                                if (string == null) {
                                    this.a.z.a(this.a.aa);
                                    return;
                                }
                                this.a.z.b();
                                this.a.z.a(string, str, true, false, stringBuffer.toString());
                                this.a.z.c();
                                return;
                            }
                            return;
                        } catch (Exception e) {
                            if (this.a.z != null && this.a.z.a()) {
                                this.a.z.a(this.a.aa);
                                return;
                            }
                            return;
                        }
                    case 40003:
                        if (this.a.z != null && this.a.z.a()) {
                            this.a.z.a(this.a.Z);
                            break;
                        }
                    case 40004:
                        if (this.a.z != null && this.a.z.a()) {
                            this.a.z.a(this.a.Z);
                            return;
                        }
                        return;
                    case 40005:
                        break;
                    case 40006:
                        af.a(ReaderApplication.getApplicationImp(), this.a.I.getResources().getString(R.string.upload_success), 0).a();
                        return;
                    case 40007:
                        if (this.a.C != null && this.a.C.isShowing()) {
                            this.a.C.dismiss();
                            return;
                        }
                        return;
                    default:
                        return;
                }
                af.a(ReaderApplication.getApplicationImp(), this.a.I.getResources().getString(R.string.net_err_busy), 0).a();
            }
        };
    }

    public void a() {
        if (!IBook.mRemarksList.isEmpty()) {
            IBook.mRemarksList.clear();
        }
        if (K != null) {
            try {
                L = Math.abs(K.getBookPath().hashCode());
                M = K.getBookNetId();
                String str = null;
                if (com.qq.reader.common.login.c.b()) {
                    str = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
                }
                if (O == 1) {
                    IBook.mRemarksList = s.a().a(str, M, N);
                    for (com.qq.reader.readengine.model.b a : IBook.mRemarksList) {
                        a.a(true);
                    }
                } else if (M != 0) {
                    IBook.mRemarksList = s.a().b(str, M, N);
                } else {
                    IBook.mRemarksList = s.a().a((long) L, N);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(IBook iBook, int i, int i2) {
        K = iBook;
        L = -1;
        M = 0;
        N = i2;
        O = i;
        if (!IBook.mRemarksList.isEmpty()) {
            IBook.mRemarksList.clear();
        }
    }

    public void b() {
        K = null;
        L = -1;
        M = 0;
        N = 0;
        O = 0;
        if (!IBook.mRemarksList.isEmpty()) {
            IBook.mRemarksList.clear();
        }
    }

    public void c() {
        this.G = true;
    }

    public void d() {
        this.s.d();
        this.t.d();
        this.v = null;
        this.w = null;
        this.E.b();
        this.G = false;
    }

    public void e() {
        this.x.c();
        this.y.c();
        this.z.e();
        if (this.B != null) {
            this.B.a();
            this.B = null;
        }
        if (this.C != null && this.C.isShowing()) {
            this.C.dismiss();
        }
    }

    public void f() {
        if (this.C != null && this.C.isShowing()) {
            this.C.dismiss();
        }
    }

    public void a(int i) {
        switch (i) {
            case 0:
                this.x.b();
                return;
            case 1:
                this.y.b();
                return;
            default:
                return;
        }
    }

    public void g() {
        if (this.B != null) {
            this.B.a(this.c, new l(this) {
                final /* synthetic */ aa a;

                {
                    this.a = r1;
                }

                public void a(String str) {
                    ((ReaderPageActivity) this.a.I).a(str, this.a);
                    ReaderTextPageView.r();
                }
            });
        }
    }

    public boolean h() {
        return this.G;
    }

    public boolean a(float f, float f2) {
        if (f2 < this.e || f2 > this.g) {
            return false;
        }
        return true;
    }

    public int i() {
        this.a = this.H.f().a();
        if (this.a == null) {
            return -1;
        }
        return a(this.a.a, this.a.b);
    }

    public void j() {
        if (ReaderTextPageView.d == 1 && this.v != null && this.w != null) {
            ZLTextElementAreaArrayList b = this.h.b();
            if (b != null) {
                h hVar;
                int i = -1;
                int i2 = -1;
                for (int i3 = 0; i3 < b.size(); i3++) {
                    hVar = (h) b.get(i3);
                    if (hVar.d().equals(this.v)) {
                        i2 = i3;
                    }
                    if (hVar.d().equals(this.w)) {
                        i = i3;
                    }
                }
                if (p == 1) {
                    if (this.l == n) {
                        if (i == -1) {
                            i = b.size() - 1;
                            this.t.a(true);
                        } else {
                            this.t.a(false);
                        }
                    } else if (this.l == o) {
                        if (i == -1) {
                            this.t.a(true);
                            i = 0;
                        } else {
                            this.t.a(false);
                        }
                    }
                    if (i2 == -1) {
                        hVar = (h) b.get(0);
                        this.d = hVar.a;
                        this.e = hVar.c;
                        this.s.a(true);
                    } else {
                        hVar = (h) b.get(i2);
                        this.d = hVar.a;
                        this.e = hVar.c;
                        this.s.a(false);
                        this.s.a(this.d, this.e, hVar, "initScrolledDrawDate");
                        a(0, this.d, this.e, "initScrolledDrawDate");
                    }
                    hVar = (h) b.get(i);
                    this.f = hVar.b;
                    this.g = hVar.d;
                    this.t.a(this.f, this.g, hVar, "initScrolledDrawDate");
                    a(1, this.f, this.g, "initScrolledDrawDate");
                } else {
                    if (this.l == n) {
                        if (i2 == -1) {
                            i2 = b.size() - 1;
                            this.s.a(true);
                        } else {
                            this.s.a(false);
                        }
                    } else if (this.l == o) {
                        if (i2 == -1) {
                            this.s.a(true);
                            i2 = 0;
                        } else {
                            this.s.a(false);
                        }
                    }
                    if (i == -1) {
                        hVar = (h) b.get(b.size() - 1);
                        this.f = hVar.b;
                        this.g = hVar.d;
                        this.t.d();
                        this.t.a(true);
                    } else {
                        hVar = (h) b.get(i);
                        this.f = hVar.b;
                        this.g = hVar.d;
                        this.t.a(false);
                        this.t.a(this.f, this.g, hVar, "initScrolledDrawDate");
                        a(1, this.f, this.g, "initScrolledDrawDate");
                    }
                    hVar = (h) b.get(i2);
                    this.d = hVar.a;
                    this.e = hVar.c;
                    this.s.a(this.d, this.e, hVar, "initScrolledDrawDate");
                    a(0, this.d, this.e, "initScrolledDrawDate");
                }
                this.E.a(this.d, this.e, this.f, this.g, b);
            }
        }
    }

    private int b(float f, float f2) {
        Iterator it = this.h.c().iterator();
        while (it.hasNext()) {
            format.epub.view.d dVar = (format.epub.view.d) it.next();
            MarkView b = dVar.b();
            if (b.a()) {
                Rect noteTagBound = b.getNoteTagBound();
                int i = noteTagBound.right - noteTagBound.left;
                if (f >= ((float) (noteTagBound.left - i)) && f < ((float) (noteTagBound.right + i)) && f2 >= ((float) (noteTagBound.top - i)) && f2 < ((float) (i + noteTagBound.bottom))) {
                    this.B = new PagePopupWindow(this.I, this.c);
                    PagePopupWindow pagePopupWindow = this.B;
                    int i2 = this.H.a;
                    j jVar = this.H;
                    i2 -= j.j();
                    jVar = this.H;
                    i2 -= j.i();
                    int i3 = this.H.b;
                    j jVar2 = this.H;
                    pagePopupWindow.setParentViewDate(i2, (i3 - j.k()) - this.H.l());
                    this.B.setBaseRect(noteTagBound.left, noteTagBound.top, noteTagBound.right, noteTagBound.bottom);
                    this.D = dVar.a();
                    this.F = dVar.b();
                    this.B.setShowStr(this.D.e());
                    return 2;
                }
            }
            if (b.a(f, f2)) {
                this.e = b.getStartY();
                this.g = b.getEndY();
                this.D = dVar.a();
                this.F = dVar.b();
                return 1;
            }
        }
        return -1;
    }

    private int a(format.epub.view.u.b bVar, format.epub.view.u.b bVar2) {
        int i = -1;
        int i2 = -1;
        g gVar = null;
        ZLTextElementAreaArrayList b = this.h.b();
        if (b == null) {
            return -1;
        }
        g d;
        g gVar2;
        int i3;
        for (int i4 = 0; i4 < b.size(); i4++) {
            h hVar = (h) b.get(i4);
            if (hVar.d().equals(bVar.d())) {
                gVar = hVar.d();
                i = i4;
            }
            if (hVar.d().equals(bVar2.d())) {
                d = hVar.d();
                gVar2 = gVar;
                i2 = i4;
                i3 = i;
                break;
            }
        }
        d = null;
        gVar2 = gVar;
        i3 = i;
        if (!(gVar2 == null || r8 == null || IBook.mRemarksList == null || IBook.mRemarksList.size() <= 0)) {
            for (com.qq.reader.readengine.model.b bVar3 : IBook.mRemarksList) {
                try {
                    g s = bVar3.s();
                    g t = bVar3.t();
                    if ((gVar2.b(s) >= 0 && gVar2.b(t) <= 0) || (r8.b(s) >= 0 && r8.b(t) <= 0)) {
                        int i5;
                        Object obj = null;
                        Object obj2 = null;
                        for (i5 = i3; i5 >= 0; i5--) {
                            hVar = (h) b.get(i5);
                            if (hVar.d().equals(bVar3.s())) {
                                this.d = hVar.a;
                                this.e = hVar.c;
                                obj = 1;
                                break;
                            }
                        }
                        for (i5 = i2; i5 < b.size(); i5++) {
                            hVar = (h) b.get(i5);
                            if (hVar.d().equals(bVar3.t())) {
                                this.f = hVar.b;
                                this.g = hVar.d;
                                obj2 = 1;
                                break;
                            }
                        }
                        if (obj == null) {
                            hVar = (h) b.get(0);
                            this.d = hVar.a;
                            this.e = hVar.c;
                        }
                        if (obj2 == null) {
                            hVar = (h) b.get(b.size() - 1);
                            this.f = hVar.b;
                            this.g = hVar.d;
                        }
                        this.D = bVar3;
                        return 2;
                    }
                } catch (Exception e) {
                    f.b("SELECTION", e.toString());
                }
            }
        }
        if (i3 == -1 || i2 == -1) {
            return -1;
        }
        hVar = (h) b.get(i3);
        this.d = hVar.a;
        this.e = hVar.c;
        this.s.a(this.d, this.e, hVar, APMidasPluginInfo.LAUNCH_INTERFACE_INIT);
        hVar = (h) b.get(i2);
        this.f = ((h) b.get(i2)).b;
        this.g = ((h) b.get(i2)).d;
        this.t.a(this.f, this.g, hVar, APMidasPluginInfo.LAUNCH_INTERFACE_INIT);
        this.E.a(this.d, this.e, this.f, this.g, b);
        return 1;
    }

    public float[] a(int i, float f, float f2, String str) {
        a aVar;
        float[] fArr = new float[]{-1.0f, -1.0f};
        if (i == 0) {
            aVar = this.s;
        } else {
            aVar = this.t;
        }
        float[] a = a(aVar, f, f2);
        h a2 = a(i, this.H.f().a(i, a[0], a[1]));
        if (a2 != null) {
            if (i == 0) {
                this.d = a2.a;
                this.e = a2.c;
                fArr[0] = this.d;
                fArr[1] = this.e;
                aVar.a(fArr[0], fArr[1], a2, str);
            } else {
                this.f = a2.b;
                this.g = a2.d;
                fArr[0] = a2.b;
                fArr[1] = a2.d;
                aVar.a(fArr[0], fArr[1], a2, str);
            }
            this.E.a(this.d, this.e, this.f, this.g, this.h.b());
            aVar.c();
        }
        return fArr;
    }

    private h a(int i, h hVar) {
        if (hVar != null) {
            ZLTextElementAreaArrayList b = this.h.b();
            int i2 = 0;
            while (i2 < b.size()) {
                i2 = ((h) b.get(i2)).d().b(hVar.d()) == 0 ? i2 + 1 : i2 + 1;
            }
        }
        return hVar;
    }

    private float[] a(a aVar, float f, float f2) {
        float f3;
        h a;
        if (aVar.b() == 0) {
            a = this.t.j;
            if (f2 >= a.c) {
                f3 = a.c;
            } else {
                f3 = f2;
            }
            if (f2 >= a.c && f >= a.a) {
                f = a.a;
            }
        } else {
            a = this.s.j;
            if (f2 <= a.d) {
                f3 = a.d;
            } else {
                f3 = f2;
            }
            if (f2 <= a.d) {
                if (f <= a.b) {
                    f = a.b;
                }
            } else if (a(a) && f <= a.b) {
                f = a.b;
            }
        }
        return new float[]{f, f3};
    }

    private boolean a(h hVar) {
        if (this.s.j != this.t.j) {
            return false;
        }
        float f = hVar.d;
        if (this.H == null || ((float) ((this.H.b - this.H.l()) - r)) >= f + (hVar.d - hVar.c)) {
            return false;
        }
        return true;
    }

    public void a(Canvas canvas) {
        if (h()) {
            j jVar = this.H;
            float k = (float) j.k();
            float o = (float) (this.H.o() - this.H.l());
            jVar = this.H;
            float j = (float) j.j();
            int i = this.H.a;
            j jVar2 = this.H;
            float i2 = (float) (i - j.i());
            this.E.a(canvas, k, o, j, i2, this.H.h.descent());
            this.t.c();
            this.t.draw(canvas);
            this.s.c();
            this.s.draw(canvas);
        }
    }

    public void b(Canvas canvas) {
        if (this.ac.size() > 0) {
            j jVar = this.H;
            float k = (float) j.k();
            float o = (float) (this.H.o() - this.H.l());
            jVar = this.H;
            float j = (float) j.j();
            int i = this.H.a;
            j jVar2 = this.H;
            float i2 = (float) (i - j.i());
            for (MarkView a : this.ac) {
                a.a(canvas, k, o, j, i2, this.H.h.descent());
            }
        }
    }

    public boolean k() {
        this.v = this.s.j.d();
        this.w = this.t.j.d();
        return ((ReaderTextPageView) this.c).a(this.l);
    }

    public void l() {
        this.l = m;
        this.v = null;
        this.w = null;
    }

    private long u() {
        try {
            if (K.getReadType() == 1) {
                Chapter chapterInfo = K.getMulitFile().getChapterInfo(this.s.j.d().f());
                if (chapterInfo instanceof OnlineChapter) {
                    return ((OnlineChapter) chapterInfo).getUUID();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void a(int i, String str) {
        if (this.s != null && this.s.j != null && this.t != null && this.t.j != null) {
            long j = M;
            long u = u();
            long f = (long) this.s.j.d().f();
            long f2 = (long) this.t.j.d().f();
            long g = this.s.j.d().g();
            long g2 = this.t.j.d().g();
            com.qq.reader.common.readertask.ordinal.c anonymousClass3 = new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ aa a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    this.a.J.sendEmptyMessage(40006);
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.a.J.sendEmptyMessage(40005);
                }
            };
            com.qq.reader.common.readertask.protocol.ReadPageCorrectTask.a aVar = new com.qq.reader.common.readertask.protocol.ReadPageCorrectTask.a();
            aVar.a(j).b(f).c(f2).d(g).e(g2).a(i).f(u).a(str);
            com.qq.reader.common.readertask.g.a().a(new ReadPageCorrectTask(anonymousClass3, aVar));
        }
    }

    private com.qq.reader.readengine.model.b b(String str, String str2) {
        int f;
        long g;
        int f2;
        long g2;
        g d = this.s.j.d();
        g d2 = this.t.j.d();
        a(d, d2);
        String str3 = null;
        if (com.qq.reader.common.login.c.b()) {
            str3 = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
        }
        if (O == 1) {
            f = d.f();
            g = d.g();
            f2 = d2.f();
            g2 = d2.g();
        } else {
            int[] a = ((ReaderPageActivity) this.I).a(this.s.j.q, false);
            int[] a2 = ((ReaderPageActivity) this.I).a(this.t.j.q, false);
            f = a[0];
            g = (long) a[1];
            f2 = a2[0];
            g2 = (long) a2[1];
        }
        long currentTimeMillis = System.currentTimeMillis();
        long a3 = s.a().a(str3, this.s.j.q, this.t.j.q, str, str2, v(), w(), currentTimeMillis, (long) f, g, (long) f2, g2, M, N);
        com.qq.reader.readengine.model.b bVar = null;
        if (a3 > 0) {
            bVar = new com.qq.reader.readengine.model.b(a3, v(), w(), this.s.j.q + "", this.t.j.q + "", str, str2, currentTimeMillis, f, g, f2, g2, M, N);
            if (O == 1) {
                bVar.a(true);
            }
            IBook.mRemarksList.add(bVar);
            s.a().a(str3, M, 0, currentTimeMillis, false);
        }
        e();
        d();
        ReaderTextPageView.r();
        ((ReaderTextPageView) this.c).k();
        this.c.invalidate();
        return bVar;
    }

    private boolean a(g gVar, g gVar2) {
        if (IBook.mRemarksList == null || IBook.mRemarksList.size() <= 0) {
            return false;
        }
        String str = null;
        if (com.qq.reader.common.login.c.b()) {
            str = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
        }
        List<com.qq.reader.readengine.model.b> arrayList = new ArrayList();
        for (com.qq.reader.readengine.model.b bVar : IBook.mRemarksList) {
            try {
                g s = bVar.s();
                g t = bVar.t();
                if (s.equals(gVar) || t.equals(gVar2) || t.equals(gVar) || s.equals(gVar2) || ((s.b(gVar) > 0 && s.b(gVar2) < 0) || ((t.b(gVar) > 0 && t.b(gVar2) < 0) || (s.b(gVar) < 0 && t.b(gVar2) > 0)))) {
                    if (!s.a().a(str, bVar.f(), bVar.q())) {
                        return false;
                    }
                    s.a().a(str, bVar.q(), 0, System.currentTimeMillis(), false);
                    arrayList.add(bVar);
                }
            } catch (Exception e) {
                return false;
            }
        }
        for (com.qq.reader.readengine.model.b remove : arrayList) {
            IBook.mRemarksList.remove(remove);
        }
        return true;
    }

    private long v() {
        if (M != 0) {
            return M;
        }
        return (long) L;
    }

    private String w() {
        if (this.P == null) {
            if (K != null) {
                this.P = K.getBookShortName();
            } else {
                this.P = "";
            }
        }
        return this.P;
    }

    private void a(String str, boolean z) {
        String str2;
        URL url;
        if (z) {
            try {
                str2 = this.X + ("word=" + str.trim()) + "&callback=showiciba_0&authkey=" + com.qq.reader.common.utils.a.d.b((URLEncoder.encode(str, "UTF-8") + "Iciba.QQREADER_api_EFnpdkR82eMmTxyhsuWb$").getBytes()).toLowerCase();
            } catch (Exception e) {
                e.printStackTrace();
                url = null;
            }
        } else if (str.trim().length() == 1 || str.trim().length() == 4 || str.trim().length() == 6 || str.trim().length() == 9) {
            str2 = this.V + "c=qq&key=" + URLEncoder.encode(str.trim(), "UTF-8") + "&sign=" + com.qq.reader.common.utils.a.d.b((str.trim() + "hanzi788@#RaT1").getBytes("UTF-8")).toLowerCase();
        } else {
            str2 = this.W + "c=qq&key=" + URLEncoder.encode(str.trim(), "UTF-8") + "&sign=" + com.qq.reader.common.utils.a.d.b((str.trim() + "hanzi788@#RaT1").getBytes("UTF-8")).toLowerCase();
        }
        url = new URL(str2);
        if (url != null) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        byteArrayOutputStream.flush();
                        Spanned fromHtml = Html.fromHtml(new String(byteArrayOutputStream.toByteArray(), "UTF-8"));
                        inputStream.close();
                        httpURLConnection.disconnect();
                        Message obtain = Message.obtain();
                        obtain.what = 40002;
                        obtain.obj = fromHtml.toString();
                        this.J.sendMessage(obtain);
                        return;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                this.J.sendEmptyMessage(40004);
            }
        }
    }

    private void x() {
        this.A.b();
        this.A.a();
        com.qq.reader.common.monitor.i.a("event_B142", null, this.b);
    }

    private void b(String str, boolean z) {
        if (str != null && str.length() > 0) {
            try {
                String str2 = "";
                if (z) {
                    str2 = str;
                } else {
                    str2 = str.replaceAll(this.i, "");
                    for (CharSequence replace : this.j) {
                        str2 = str2.replace(replace, "");
                    }
                    if (str2.length() == 0) {
                        str2 = str;
                    }
                }
                if (str2 == null || str2.length() <= 0) {
                    this.z.a(this.aa);
                    return;
                }
                this.z.a(str2, null, false, true, null);
                if (this.z.isShown()) {
                    this.z.d();
                } else {
                    this.z.c();
                }
                this.z.a(new Thread(this) {
                    final /* synthetic */ aa b;

                    public void run() {
                        this.b.a(str2, this.b.b(str2));
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean b(String str) {
        try {
            new String(new byte[]{(byte) -75, (byte) -83}, StringUtils.GB2312).trim().getBytes("UTF-8");
            if (str.length() == str.trim().getBytes("UTF-8").length) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void a(String str) {
        try {
            String R;
            com.qq.reader.readengine.model.b bVar;
            long currentTimeMillis = System.currentTimeMillis();
            if (com.qq.reader.common.login.c.b()) {
                R = com.qq.reader.appconfig.a.d.R(ReaderApplication.getApplicationImp());
            } else {
                R = null;
            }
            if (s.a().a(R, str, currentTimeMillis + "", this.D.f(), this.D.q()) > 0) {
                s.a().a(R, this.D.q(), 0, System.currentTimeMillis(), false);
                this.D.a(str);
                com.qq.reader.readengine.model.b bVar2 = null;
                for (com.qq.reader.readengine.model.b bVar3 : IBook.mRemarksList) {
                    if (bVar3.f() == this.D.f()) {
                        bVar3.a(str);
                        bVar3.b(currentTimeMillis);
                        bVar2 = bVar3;
                        break;
                    }
                    bVar2 = bVar3;
                }
                ((ReaderTextPageView) this.c).k();
                this.c.invalidate();
                bVar3 = bVar2;
            } else {
                bVar3 = null;
            }
            if (!TextUtils.isEmpty(str)) {
                a(bVar3, true);
            }
        } catch (Exception e) {
        }
    }

    private void a(final com.qq.reader.readengine.model.b bVar, boolean z) {
        View inflate = LayoutInflater.from(this.I).inflate(R.layout.share_image_tip_layout, null);
        int dimension = (int) this.I.getResources().getDimension(R.dimen.share_tip_window_height);
        int dimension2 = (int) this.I.getResources().getDimension(R.dimen.share_tip_window_width);
        if (com.qq.reader.appconfig.a.d.n) {
            inflate.setBackgroundResource(R.drawable.share_img_tips_night);
            ((TextView) inflate.findViewById(R.id.share_text)).setTextColor(this.I.getResources().getColor(R.color.paopao_dlg_textcolor_night));
        }
        this.C = new PopupWindow(inflate, dimension2, dimension);
        this.C.setOutsideTouchable(true);
        bVar.h();
        Point a = a(z);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ aa b;

            public void onClick(View view) {
                this.b.C.dismiss();
                if (bVar != null) {
                    new ak(this.b.I, bVar, 15).f_();
                    com.qq.reader.common.monitor.i.a("event_B193", null, this.b.b);
                }
            }
        });
        this.C.showAtLocation(this.c, 0, a.x, a.y);
        this.J.removeMessages(40007);
        this.J.sendEmptyMessageDelayed(40007, 3000);
        com.qq.reader.common.monitor.i.a("event_B192", null, this.b);
    }

    public void a(String str, String str2) {
        com.qq.reader.readengine.model.b b = b(str, str2);
        if (b != null) {
            a(b, false);
        }
    }

    private Point a(boolean z) {
        PointF lastLineEndPoint;
        int i = 0;
        if (z) {
            lastLineEndPoint = this.F.getLastLineEndPoint();
        } else {
            lastLineEndPoint = this.E.getLastLineEndPoint();
        }
        int dimension = (int) this.I.getResources().getDimension(R.dimen.share_tip_window_height);
        int dimension2 = (int) this.I.getResources().getDimension(R.dimen.share_tip_window_width);
        if (lastLineEndPoint.y >= ((float) (com.qq.reader.common.c.a.bT / 2))) {
            dimension = (int) (lastLineEndPoint.y - ((float) dimension));
        } else if (lastLineEndPoint.y < ((float) (com.qq.reader.common.c.a.bT / 2))) {
            dimension = ((int) lastLineEndPoint.y) + (((int) com.qq.reader.appconfig.a.d.I(this.I)) * 2);
        } else {
            dimension = 0;
        }
        if (lastLineEndPoint.x - ((float) ao.a(10.0f)) > ((float) (dimension2 / 2)) && (((float) this.c.getWidth()) - lastLineEndPoint.x) - ((float) ao.a(10.0f)) > ((float) (dimension2 / 2))) {
            i = ((int) com.qq.reader.appconfig.a.d.I(this.I)) + (((int) lastLineEndPoint.x) - (dimension2 / 2));
        } else if (lastLineEndPoint.x - ((float) ao.a(10.0f)) <= ((float) (dimension2 / 2))) {
            i = ao.a(10.0f);
        } else if ((((float) this.c.getWidth()) - lastLineEndPoint.x) - ((float) ao.a(10.0f)) <= ((float) (dimension2 / 2))) {
            i = (this.c.getWidth() - dimension2) - ao.a(10.0f);
        }
        return new Point(i, dimension);
    }

    private void y() {
        this.ag = -1;
    }

    public boolean a(MotionEvent motionEvent) {
        switch (ReaderTextPageView.d) {
            case 1:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (motionEvent.getAction() == 0) {
                    this.ah = motionEvent.getY();
                    this.ai[0] = 0;
                    this.ai[1] = 0;
                    if (this.s.a(x, y)) {
                        this.ai[0] = 1;
                    }
                    if (this.t.a(x, y)) {
                        this.ai[1] = 1;
                    }
                    if (this.ai[0] == 1 && this.ai[1] == 1) {
                        this.u = null;
                        this.aj = true;
                    } else if (this.ai[0] == 1) {
                        this.u = this.s;
                        this.aj = false;
                    } else if (this.ai[1] == 1) {
                        this.u = this.t;
                        this.aj = false;
                    } else {
                        this.u = null;
                        this.aj = false;
                    }
                }
                if (this.u != null) {
                    this.u.onTouchEvent(motionEvent);
                    return true;
                }
                switch (motionEvent.getAction()) {
                    case 0:
                        if (a(motionEvent.getX(), motionEvent.getY())) {
                            e();
                            return true;
                        }
                        ReaderTextPageView.r();
                        e();
                        d();
                        this.c.invalidate();
                        return false;
                    case 1:
                    case 3:
                        a(0);
                        this.ah = 0.0f;
                        return true;
                    case 2:
                        if (!this.aj || this.u != null) {
                            return false;
                        }
                        if (motionEvent.getY() < this.ah) {
                            this.u = this.s;
                        } else {
                            this.u = this.t;
                        }
                        this.aj = false;
                        if (this.u == null) {
                            return true;
                        }
                        this.u.onTouchEvent(motionEvent);
                        return true;
                    default:
                        return true;
                }
            case 2:
                switch (motionEvent.getAction()) {
                    case 1:
                    case 3:
                        ReaderTextPageView.r();
                        e();
                        return true;
                    default:
                        return true;
                }
            default:
                return true;
        }
    }

    public boolean[] b(MotionEvent motionEvent) {
        boolean[] zArr = new boolean[]{false, false};
        if (ReaderTextPageView.d != -1 && (ReaderTextPageView.d == 2 || ReaderTextPageView.d == 1)) {
            zArr[1] = a(motionEvent);
            zArr[0] = true;
        }
        return zArr;
    }

    public boolean c(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                y();
                this.ae = motionEvent.getX();
                this.af = motionEvent.getY();
                this.ag = -1;
                this.ag = b(this.ae, this.af);
                if (this.ag != -1) {
                    return true;
                }
                break;
            case 1:
            case 3:
                if (this.ag != -1) {
                    ReaderTextPageView.d = 2;
                    if (this.ag == 1) {
                        a(1);
                    } else if (this.ag == 2) {
                        g();
                    }
                    this.ag = -1;
                    return true;
                }
                break;
            case 2:
                if (this.ag != -1) {
                    float x = motionEvent.getX();
                    int y = (int) (motionEvent.getY() - this.af);
                    if (Math.abs((int) (x - this.ae)) < this.ab && Math.abs(y) < this.ab) {
                        return true;
                    }
                    this.ag = -1;
                    return false;
                }
                break;
        }
        return false;
    }

    public boolean a(View view, float f, float f2) {
        int a = a((int) f, (int) f2);
        if (a == 1) {
            ReaderTextPageView.d = 1;
            this.s.a(false);
            this.t.a(false);
            this.s.g = 0;
            this.t.g = 0;
            this.c.invalidate();
            this.aj = true;
            this.ah = f2;
            return true;
        } else if (a != 2) {
            return false;
        } else {
            return true;
        }
    }

    public int a(int i, int i2) {
        if (h()) {
            d();
        }
        if (!this.H.c(i, i2)) {
            return -1;
        }
        int i3 = i();
        if (i3 != 1) {
            return i3;
        }
        c();
        return i3;
    }

    public void a(ZLTextElementAreaArrayList zLTextElementAreaArrayList, ZLRectNoteArrayList zLRectNoteArrayList) {
        this.ac.clear();
        b(zLTextElementAreaArrayList, zLRectNoteArrayList);
        a(zLTextElementAreaArrayList);
    }

    private void a(ZLTextElementAreaArrayList zLTextElementAreaArrayList) {
        if (IBook.mSearchList != null && IBook.mSearchList.size() > 0 && zLTextElementAreaArrayList != null && zLTextElementAreaArrayList.size() > 0) {
            h hVar = (h) zLTextElementAreaArrayList.get(zLTextElementAreaArrayList.size() - 1);
            g d = ((h) zLTextElementAreaArrayList.get(0)).d();
            g d2 = hVar.d();
            for (com.qq.reader.readengine.model.b bVar : IBook.mSearchList) {
                h hVar2;
                h hVar3;
                g s = bVar.s();
                g t = bVar.t();
                if (s.b(d) >= 0 && s.b(d2) <= 0) {
                    for (int i = 0; i < zLTextElementAreaArrayList.size(); i++) {
                        hVar2 = (h) zLTextElementAreaArrayList.get(i);
                        if (hVar2.d().equals(s)) {
                            hVar = hVar2;
                            break;
                        }
                    }
                }
                hVar = null;
                if (t.b(d) >= 0 && t.b(d2) <= 0) {
                    for (int i2 = 0; i2 < zLTextElementAreaArrayList.size(); i2++) {
                        hVar2 = (h) zLTextElementAreaArrayList.get(i2);
                        if (hVar2.d().equals(t)) {
                            hVar3 = hVar2;
                            break;
                        }
                    }
                }
                hVar3 = null;
                if (!(hVar == null && hVar3 == null && (s.b(d) >= 0 || t.b(d2) <= 0))) {
                    float f;
                    float f2;
                    float f3;
                    float f4;
                    if (hVar == null) {
                        f = ((h) zLTextElementAreaArrayList.get(0)).a;
                        f2 = ((h) zLTextElementAreaArrayList.get(0)).c;
                    } else {
                        float f5 = hVar.a;
                        f2 = hVar.c;
                        f = f5;
                    }
                    if (hVar3 == null) {
                        f3 = ((h) zLTextElementAreaArrayList.get(zLTextElementAreaArrayList.size() - 1)).b;
                        f4 = ((h) zLTextElementAreaArrayList.get(zLTextElementAreaArrayList.size() - 1)).d;
                    } else {
                        f3 = hVar3.b;
                        f4 = hVar3.d;
                    }
                    MarkView markView = new MarkView(this.b, false, false, 1);
                    markView.a(f, f2, f3, f4, zLTextElementAreaArrayList);
                    this.ac.add(markView);
                }
            }
        }
    }

    private void b(ZLTextElementAreaArrayList zLTextElementAreaArrayList, ZLRectNoteArrayList zLRectNoteArrayList) {
        if (IBook.mRemarksList != null && IBook.mRemarksList.size() > 0 && zLTextElementAreaArrayList != null && zLTextElementAreaArrayList.size() > 0) {
            h hVar = (h) zLTextElementAreaArrayList.get(zLTextElementAreaArrayList.size() - 1);
            g d = ((h) zLTextElementAreaArrayList.get(0)).d();
            g d2 = hVar.d();
            for (com.qq.reader.readengine.model.b bVar : IBook.mRemarksList) {
                h hVar2;
                h hVar3;
                g s = bVar.s();
                g t = bVar.t();
                if (s.b(d) >= 0 && s.b(d2) <= 0) {
                    for (int i = 0; i < zLTextElementAreaArrayList.size(); i++) {
                        hVar2 = (h) zLTextElementAreaArrayList.get(i);
                        if (hVar2.d().equals(s)) {
                            hVar = hVar2;
                            break;
                        }
                    }
                }
                hVar = null;
                if (t.b(d) >= 0 && t.b(d2) <= 0) {
                    for (int i2 = 0; i2 < zLTextElementAreaArrayList.size(); i2++) {
                        hVar2 = (h) zLTextElementAreaArrayList.get(i2);
                        if (hVar2.d().equals(t)) {
                            hVar3 = hVar2;
                            break;
                        }
                    }
                }
                hVar3 = null;
                if (!(hVar == null && hVar3 == null && (s.b(d) >= 0 || t.b(d2) <= 0))) {
                    float f;
                    float f2;
                    float f3;
                    float f4;
                    boolean z;
                    if (hVar == null) {
                        f = ((h) zLTextElementAreaArrayList.get(0)).a;
                        f2 = ((h) zLTextElementAreaArrayList.get(0)).c;
                    } else {
                        float f5 = hVar.a;
                        f2 = hVar.c;
                        f = f5;
                    }
                    if (hVar3 == null) {
                        f3 = ((h) zLTextElementAreaArrayList.get(zLTextElementAreaArrayList.size() - 1)).b;
                        f4 = ((h) zLTextElementAreaArrayList.get(zLTextElementAreaArrayList.size() - 1)).d;
                    } else {
                        f3 = hVar3.b;
                        f4 = hVar3.d;
                    }
                    if (bVar.e().length() > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    MarkView markView = new MarkView(this.b, false, z, 0);
                    markView.a(f, f2, f3, f4, zLTextElementAreaArrayList);
                    this.ac.add(markView);
                    zLRectNoteArrayList.add(new format.epub.view.d(bVar, markView));
                }
            }
        }
    }

    public static IBook m() {
        return K;
    }
}
