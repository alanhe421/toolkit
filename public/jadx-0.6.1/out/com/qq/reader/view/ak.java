package com.qq.reader.view;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.x;
import com.qq.reader.module.readpage.aa;
import com.qq.reader.readengine.model.b;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ShareStyleSelectDialog */
public class ak extends BaseDialog implements OnClickListener {
    public static final String a = (com.qq.reader.common.c.a.l + "share_image.jpeg");
    private TextView b;
    private ImageView c;
    private TextView d;
    private CustomlayoutTextView e;
    private CustomlayoutTextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private ImageView m;
    private Button n;
    private Button o;
    private View p;
    private View q;
    private ImageView r;
    private ImageView s;
    private ImageView t;
    private Activity u;
    private Bitmap v;
    private b w;
    private int x;

    /* compiled from: ShareStyleSelectDialog */
    interface a {
        void a();
    }

    public ak(Activity activity, b bVar, int i) {
        this.w = bVar;
        this.x = i;
        this.u = activity;
        g();
    }

    public void f_() {
        super.f_();
        i.a("event_B196", null, b());
    }

    private void g() {
        if (this.f == null) {
            a(this.u, null, R.layout.share_image_style_select_dialog, true, false, true);
            this.f.getWindow().addFlags(2);
            this.b = (TextView) this.f.findViewById(R.id.username);
            this.d = (TextView) this.f.findViewById(R.id.date);
            this.c = (ImageView) this.f.findViewById(R.id.user_icon);
            this.e = (CustomlayoutTextView) this.f.findViewById(R.id.user_note);
            this.i = (CustomlayoutTextView) this.f.findViewById(R.id.share_context);
            this.j = (TextView) this.f.findViewById(R.id.from_bookname);
            this.k = (TextView) this.f.findViewById(R.id.from_chapter);
            this.l = (TextView) this.f.findViewById(R.id.QR_tip);
            this.m = (ImageView) this.f.findViewById(R.id.bookdetail_QR_code);
            this.o = (Button) this.f.findViewById(R.id.cancelButton);
            this.n = (Button) this.f.findViewById(R.id.sureButton);
            this.r = (ImageView) this.f.findViewById(R.id.bg_color1);
            this.s = (ImageView) this.f.findViewById(R.id.bg_color2);
            this.t = (ImageView) this.f.findViewById(R.id.bg_color3);
            this.p = this.f.findViewById(R.id.share_image_view);
            this.q = this.f.findViewById(R.id.quote_area);
            i();
            h();
        }
    }

    private void h() {
        CharSequence charSequence = "";
        if (this.x == 15) {
            String format = String.format(this.u.getResources().getString(R.string.share_remark_date_format), new Object[]{ao.d(this.w.i())});
            this.e.setTextindent(false);
            charSequence = this.w.e();
            if (charSequence != null && charSequence.length() > ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE) {
                charSequence = charSequence.substring(0, ErrorCode.INFO_STATIC_TBS_INSTALL_ERR_CODE_BASE) + "...";
            }
            this.e.setText(charSequence);
            charSequence = format;
        } else if (this.x == 12) {
            charSequence = String.format(this.u.getResources().getString(R.string.share_date_format), new Object[]{ao.h()});
            this.e.setVisibility(8);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, (int) this.u.getResources().getDimension(R.dimen.shareimage_quote_margintop), 0, 0);
            this.q.setLayoutParams(layoutParams);
        }
        CharSequence d = this.w.d();
        if (d.length() > 200) {
            d = d.substring(0, 200) + "...";
        }
        this.i.setTextindent(false);
        this.i.setText(d);
        this.j.setText(String.format(this.u.getResources().getString(R.string.content_frome), new Object[]{this.w.c()}));
        d = this.w.u();
        if (TextUtils.isEmpty(d)) {
            if (aa.m() != null) {
                d = aa.m().getChapterName(this.w.m());
            } else {
                d = String.format(this.u.getResources().getString(R.string.share_remark_chaptername), new Object[]{Integer.valueOf(this.w.m())});
            }
        }
        this.k.setText(d);
        this.d.setText(charSequence);
        if (!c.b() || c.c() == null) {
            this.c.setVisibility(8);
        } else {
            if (c.c().a() != null) {
                this.b.setText(c.c().a());
            }
            com.qq.reader.common.imageloader.c.a(e()).a(c.c().b(), this.c, com.qq.reader.common.imageloader.a.a().i());
        }
        a(R.id.bg_color1, this.u.getResources().getColor(R.color.shareimage_bg_white));
        this.r.setSelected(true);
    }

    private void a(int i, int i2) {
        int dimension = (int) this.u.getResources().getDimension(R.dimen.share_QR_code_size);
        if (x.a(i, a(this.w.q()), dimension, dimension, null, this.u.getFilesDir() + "share_qr_code", i2)) {
            this.v = BitmapFactory.decodeFile(this.u.getFilesDir() + "share_qr_code");
            this.m.setImageBitmap(this.v);
        }
    }

    private void i() {
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
    }

    private Bitmap a(View view) {
        if (view == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate((float) (-view.getScrollX()), (float) (-view.getScrollY()));
        view.draw(canvas);
        return createBitmap;
    }

    private void b(View view) {
        Bitmap a;
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        Bitmap bitmap = null;
        try {
            a = a(view);
            try {
                fileOutputStream = new FileOutputStream(a);
                try {
                    fileOutputStream.write(ao.a(a, 1024.0f));
                    System.out.println("saveBmp is here");
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (a != null) {
                        a.recycle();
                    }
                } catch (Exception e3) {
                    e = e3;
                    bitmap = a;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        if (bitmap == null) {
                            bitmap.recycle();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        a = bitmap;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (a != null) {
                            a.recycle();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (a != null) {
                        a.recycle();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream = null;
                bitmap = a;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (bitmap == null) {
                    bitmap.recycle();
                }
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (a != null) {
                    a.recycle();
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (bitmap == null) {
                bitmap.recycle();
            }
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
            a = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (a != null) {
                a.recycle();
            }
            throw th;
        }
    }

    private void j() {
        aj ajVar = new aj(this.u, a, 14);
        ajVar.a(new a(this) {
            final /* synthetic */ ak a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.dismiss();
            }
        });
        ajVar.f_();
    }

    private String a(long j) {
        return e.bv + j;
    }

    public void onClick(View view) {
        Resources resources = this.u.getResources();
        Map hashMap = new HashMap();
        switch (view.getId()) {
            case R.id.sureButton:
                b(this.p);
                j();
                i.a("event_B198", null, this.u);
                return;
            case R.id.cancelButton:
                cancel();
                i.a("event_B199", null, this.u);
                return;
            case R.id.bg_color1:
                b((int) R.id.bg_color1);
                this.p.setBackgroundColor(resources.getColor(R.color.shareimage_bg_white));
                b(resources);
                a(R.id.bg_color1, resources.getColor(R.color.shareimage_bg_white));
                hashMap.put("bg_color", "1");
                i.a("event_B197", hashMap, this.u);
                return;
            case R.id.bg_color2:
                b((int) R.id.bg_color2);
                this.p.setBackgroundColor(resources.getColor(R.color.shareimage_bg_pink));
                b(resources);
                a(R.id.bg_color2, resources.getColor(R.color.shareimage_bg_pink));
                hashMap.put("bg_color", "2");
                i.a("event_B197", hashMap, this.u);
                return;
            case R.id.bg_color3:
                b((int) R.id.bg_color3);
                this.p.setBackgroundColor(resources.getColor(R.color.shareimage_bg_black));
                a(resources);
                a(R.id.bg_color3, resources.getColor(R.color.shareimage_bg_black));
                hashMap.put("bg_color", "3");
                i.a("event_B197", hashMap, this.u);
                return;
            default:
                return;
        }
    }

    private void b(int i) {
        if (i == R.id.bg_color1) {
            this.r.setImageResource(R.drawable.shareimage_bg_checked);
            this.r.setSelected(true);
            this.s.setImageBitmap(null);
            this.s.setSelected(false);
            this.t.setImageBitmap(null);
            this.t.setSelected(false);
        } else if (i == R.id.bg_color2) {
            this.s.setImageResource(R.drawable.shareimage_bg_checked);
            this.s.setSelected(true);
            this.r.setImageBitmap(null);
            this.r.setSelected(false);
            this.t.setImageBitmap(null);
            this.t.setSelected(false);
        } else if (i == R.id.bg_color3) {
            this.t.setImageResource(R.drawable.shareimage_bg_checked);
            this.t.setSelected(true);
            this.s.setImageBitmap(null);
            this.s.setSelected(false);
            this.r.setImageBitmap(null);
            this.r.setSelected(false);
        }
    }

    private void a(Resources resources) {
        this.b.setTextColor(resources.getColor(R.color.text_color_c202));
        this.d.setTextColor(resources.getColor(R.color.text_color_c103));
        this.e.setTextColor(resources.getColor(R.color.text_color_c103));
        this.i.setTextColor(resources.getColor(R.color.text_color_c202));
        this.j.setTextColor(resources.getColor(R.color.text_color_c202));
        this.k.setTextColor(resources.getColor(R.color.text_color_c202));
        this.l.setTextColor(resources.getColor(R.color.text_color_c202));
    }

    private void b(Resources resources) {
        this.b.setTextColor(resources.getColor(R.color.text_color_c201));
        this.d.setTextColor(resources.getColor(R.color.text_color_c103));
        this.e.setTextColor(resources.getColor(R.color.text_color_c101));
        this.i.setTextColor(resources.getColor(R.color.text_color_c201));
        this.j.setTextColor(resources.getColor(R.color.text_color_c201));
        this.k.setTextColor(resources.getColor(R.color.text_color_c201));
        this.l.setTextColor(resources.getColor(R.color.text_color_c201));
    }
}
