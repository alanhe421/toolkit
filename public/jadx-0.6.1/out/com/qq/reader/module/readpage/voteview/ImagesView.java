package com.qq.reader.module.readpage.voteview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType;
import com.qq.reader.module.readpage.voteview.VoteViewGroup.b;
import com.qq.reader.module.readpage.voteview.a.a;
import com.qq.reader.widget.UserCircleImageView;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.theme.SkinnableBitmapDrawable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImagesView extends LinearLayout {
    public static int c = ((int) o);
    private static float j = ((float) ao.a(13.5f));
    private static float m = 0.0f;
    private static float n = (j * 2.0f);
    private static float o = (j * 2.0f);
    private static float p = 3.0f;
    private static float q = 1.0f;
    public List<a> a = new ArrayList();
    public Bitmap b;
    private final String d = getClass().getSimpleName();
    private Context e;
    private Paint f;
    private Paint g;
    private Paint h;
    private Matrix i;
    private Map<String, Bitmap> k = new HashMap();
    private int l;
    private int r = Color.parseColor("#ffffff");
    private int s = Color.parseColor("#e5e5e5");
    private int t = 6;
    private int u = ao.a(6.0f);
    private int v = 0;
    private ViewType w;
    private String x;
    private b y;

    public ImagesView(Context context) {
        super(context);
        this.e = context;
        c();
    }

    public ImagesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context;
        c();
    }

    public ImagesView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = context;
        c();
    }

    private void c() {
        this.i = new Matrix();
        this.g = new Paint();
        this.g.setAntiAlias(true);
        this.g.setColor(this.r);
        this.g.setStrokeWidth(p);
        this.g.setStyle(Style.STROKE);
        this.h = new Paint();
        this.h.setAntiAlias(true);
        this.h.setColor(this.s);
        this.h.setStrokeWidth(q);
        this.h.setStyle(Style.STROKE);
        this.f = new Paint();
        this.f.setAntiAlias(true);
        this.b = a(getResources().getDrawable(R.drawable.vote_default_icon));
        setGravity(17);
    }

    public void setIconOnlickListener(b bVar) {
        this.y = bVar;
    }

    private void getPadding() {
        WindowManager windowManager = (WindowManager) this.e.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = 0;
        this.l = displayMetrics.widthPixels;
        if (this.k != null) {
            i = this.t > this.k.size() ? this.k.size() : this.t;
        }
        m = (((float) (this.l - ((this.u * i) * 2))) - (((float) i) * n)) / 2.0f;
    }

    protected float getRealMeasuredHeight() {
        if (this.k == null || this.k.size() == 0) {
            return (((float) ((int) Math.ceil((double) (1.0f / ((float) this.t))))) * (o + ((float) this.v))) - ((float) this.v);
        }
        return (((float) ((int) Math.ceil((double) (((float) this.k.size()) / ((float) this.t))))) * (o + ((float) this.v))) - ((float) this.v);
    }

    public void setDrawables(Map<String, Bitmap> map) {
        this.k = map;
        if (map == null) {
            this.a.clear();
        }
        getPadding();
    }

    public void setViewType(ViewType viewType) {
        this.w = viewType;
    }

    public void setBid(String str) {
        this.x = str;
    }

    public void a() {
        removeAllViews();
        setOrientation(0);
        int i = 0;
        while (this.a != null && i < this.a.size()) {
            final a aVar = (a) this.a.get(i);
            if (aVar != null) {
                Object obj = aVar.b;
                if (!TextUtils.isEmpty(obj)) {
                    View inflate = View.inflate(ReaderApplication.getApplicationImp(), R.layout.vote_group_icon_item_layout, null);
                    LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(0, 0, ao.a(12.0f), 0);
                    inflate.setLayoutParams(layoutParams);
                    addView(inflate);
                    UserCircleImageView userCircleImageView = (UserCircleImageView) inflate.findViewById(R.id.img_icon);
                    ImageView imageView = (ImageView) inflate.findViewById(R.id.img_icon_mask);
                    c.a(getContext()).a(obj, userCircleImageView, com.qq.reader.common.imageloader.a.a().e());
                    userCircleImageView.setBorderWidth(ao.a(2.0f));
                    userCircleImageView.setBorderColor(-1);
                    imageView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ImagesView b;

                        public void onClick(View view) {
                            if (this.b.y != null) {
                                this.b.y.a(aVar);
                            }
                        }
                    });
                }
            }
            i++;
        }
    }

    private Bitmap a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof SkinnableBitmapDrawable) {
            return ((SkinnableBitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public String b() {
        if (!com.qq.reader.common.login.c.b()) {
            return "";
        }
        String b;
        String str = "";
        String str2 = "";
        if (com.qq.reader.common.login.c.c() != null) {
            b = com.qq.reader.common.login.c.c().b();
            str = com.qq.reader.common.login.c.c().c();
        } else {
            b = str;
            str = str2;
        }
        if (str == null || "".equals(str)) {
            return "";
        }
        if (this.a == null) {
            this.a = new ArrayList();
        }
        a aVar;
        if (this.a.size() <= 0) {
            aVar = new a();
            aVar.a = str;
            aVar.b = b;
            this.a.add(0, aVar);
            com.qq.reader.module.readpage.voteview.net.b.c("1", this.w, this.x);
        } else if (str.equals(((a) this.a.get(0)).a)) {
            return "";
        } else {
            if (a(str) != -1) {
                aVar = (a) this.a.get(a(str));
                this.a.remove(a(str));
                this.a.add(0, aVar);
            } else {
                aVar = new a();
                aVar.a = str;
                aVar.b = b;
                this.a.add(0, aVar);
                if (this.a.size() > this.t) {
                    this.k.remove(((a) this.a.get(this.t)).b);
                    this.a.remove(this.t);
                }
                try {
                    com.qq.reader.module.readpage.voteview.net.b.c(String.valueOf(Integer.parseInt(com.qq.reader.module.readpage.voteview.net.b.c(this.w, this.x)) + 1), this.w, this.x);
                } catch (NumberFormatException e) {
                    f.a(this.d, e.getMessage());
                }
            }
        }
        str2 = getData();
        com.qq.reader.module.readpage.voteview.net.b.a(str2, this.w, this.x);
        return str2;
    }

    private int a(String str) {
        for (int i = 0; i < this.a.size(); i++) {
            if (str.equals(((a) this.a.get(i)).a)) {
                return i;
            }
        }
        return -1;
    }

    private String getData() {
        JSONArray jSONArray = new JSONArray();
        for (a aVar : this.a) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("uid", aVar.a);
                jSONObject.put(MessageKey.MSG_ICON, aVar.b);
                jSONObject.put("time", aVar.c);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray.toString();
    }
}
