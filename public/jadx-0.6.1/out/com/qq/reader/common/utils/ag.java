package com.qq.reader.common.utils;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.g;

/* compiled from: RichTextHtmlSimpleParser */
public class ag {

    /* compiled from: RichTextHtmlSimpleParser */
    public interface b {
        int a();
    }

    /* compiled from: RichTextHtmlSimpleParser */
    public static class a implements b {
        public String a;
        public int b;
        public int c;

        public void a(g gVar) {
            if (gVar != null) {
                try {
                    String q = gVar.q("data-large");
                    if (TextUtils.isEmpty(q)) {
                        q = gVar.q("src");
                    }
                    this.a = q;
                    q = gVar.q("data-large-width");
                    String q2 = gVar.q("data-large-height");
                    q = q.replace("\"", "").replace("\\", "").trim();
                    q2 = q2.replace("\"", "").replace("\\", "").trim();
                    this.b = Integer.parseInt(q);
                    this.c = Integer.parseInt(q2);
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                }
            }
        }

        public int a() {
            return 1;
        }
    }

    /* compiled from: RichTextHtmlSimpleParser */
    public static class c implements b {
        String a;

        public void a(g gVar) {
            this.a = gVar.w();
        }

        public int a() {
            return 2;
        }
    }

    private static String b(String str) {
        Document a = org.jsoup.a.a(str);
        Iterator it = a.v().iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (!SocialConstants.PARAM_IMG_URL.equalsIgnoreCase(gVar.a())) {
                ArrayList arrayList = new ArrayList();
                Iterator it2 = gVar.F().iterator();
                while (it2.hasNext()) {
                    arrayList.add(((org.jsoup.nodes.a) it2.next()).a());
                }
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    gVar.s((String) it3.next());
                }
            }
        }
        String c = a.c();
        if (TextUtils.isEmpty(c)) {
            return c;
        }
        return c.replace("<span>", "").replace("</span>", "").replace("<br />", "$$break$$").replace("<br/>", "$$break$$").replace("<br>", "$$break$$").replace("<div>", "").replace("</div>", "").replace("<strong>", "").replace("</strong>", "").replace("<b>", "").replace("</b>", "");
    }

    public static ArrayList<b> a(String str) {
        ArrayList<b> arrayList = new ArrayList();
        CharSequence b = b(str);
        g gVar = null;
        Iterator it = org.jsoup.a.a(b).v().iterator();
        while (it.hasNext()) {
            g gVar2 = (g) it.next();
            if (SocialConstants.PARAM_IMG_URL.equalsIgnoreCase(gVar2.a())) {
                a aVar = new a();
                aVar.a(gVar2);
                arrayList.add(aVar);
                gVar2 = gVar;
            } else {
                ArrayList arrayList2 = new ArrayList();
                Iterator it2 = gVar2.F().iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((org.jsoup.nodes.a) it2.next()).a());
                }
                Iterator it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    gVar2.s((String) it3.next());
                }
                String a = gVar2.a();
                if (("p".equalsIgnoreCase(a) || "b".equalsIgnoreCase(a) || "br".equalsIgnoreCase(a) || "span".equalsIgnoreCase(a)) && !TextUtils.isEmpty(gVar2.w())) {
                    c cVar = new c();
                    cVar.a(gVar2);
                    arrayList.add(cVar);
                }
                if (!"body".equalsIgnoreCase(a)) {
                    gVar2 = gVar;
                }
            }
            gVar = gVar2;
        }
        if (!(arrayList == null || arrayList.size() != 0 || TextUtils.isEmpty(b) || gVar == null)) {
            c cVar2 = new c();
            cVar2.a(gVar);
            arrayList.add(cVar2);
        }
        return arrayList;
    }

    public static View a(Context context, ArrayList<b> arrayList) {
        View linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        linearLayout.setGravity(49);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int a = bVar.a();
            if (a == 1) {
                ImageView imageView = new ImageView(context);
                a aVar = (a) bVar;
                a = com.qq.reader.common.c.a.bU - (ao.a(16.0f) * 2);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(a, (aVar.c * a) / aVar.b));
                ((LinearLayout.LayoutParams) imageView.getLayoutParams()).setMargins(0, ao.a(12.0f), 0, ao.a(12.0f));
                com.qq.reader.common.imageloader.c.a(context).a(aVar.a, imageView, com.qq.reader.common.imageloader.a.a().j());
                linearLayout.addView(imageView);
            } else if (a == 2) {
                c cVar = (c) bVar;
                if (!("null".equalsIgnoreCase(cVar.a) || TextUtils.isEmpty(cVar.a) || "$$break$$".equals(cVar.a.trim()))) {
                    View textView = new TextView(context);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                    textView.setTextColor(context.getResources().getColor(R.color.text_color_c102));
                    textView.setTextSize(0, (float) context.getResources().getDimensionPixelOffset(R.dimen.text_size_class_3));
                    textView.setText(Html.fromHtml(cVar.a.replace("$$break$$", "<br />")));
                    textView.setLineSpacing((float) ao.a(4.0f), 1.0f);
                    linearLayout.addView(textView);
                }
            }
        }
        return linearLayout;
    }
}
