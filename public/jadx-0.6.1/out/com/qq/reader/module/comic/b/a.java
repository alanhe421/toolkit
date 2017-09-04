package com.qq.reader.module.comic.b;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.z;
import com.qq.reader.module.comic.entity.c;
import com.qq.reader.view.QRImageView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

/* compiled from: ComicColumnItem */
public class a extends z {
    private c a;
    private Context b = ReaderApplication.getApplicationContext();

    public void a(View view, int i, boolean z) {
        TextView textView = (TextView) ap.a(view, R.id.concept_content);
        TextView textView2 = (TextView) ap.a(view, R.id.concept_author);
        TextView textView3 = (TextView) ap.a(view, R.id.concept_category);
        TextView textView4 = (TextView) ap.a(view, R.id.concept_order);
        QRImageView qRImageView = (QRImageView) ap.a(view, R.id.concept_cover_img);
        ((TextView) ap.a(view, R.id.concept_title)).setText(this.a.b());
        textView.setText(this.a.d());
        textView2.setText(this.a.c());
        a(textView3);
        textView4.setText(a());
        com.qq.reader.common.imageloader.c.a(this.b).a(a(qRImageView.getLayoutParams().width, qRImageView.getLayoutParams().height), qRImageView, com.qq.reader.common.imageloader.a.a().j());
    }

    public void parseData(JSONObject jSONObject) {
        this.a = (c) new Gson().fromJson(jSONObject.toString(), c.class);
    }

    private void a(TextView textView) {
        CharSequence e = this.a.e();
        if (TextUtils.isEmpty(e)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(e);
    }

    private String a() {
        if (this.a.f() <= 0) {
            return "";
        }
        return String.format(this.a.g() ? this.b.getString(R.string.comic_column_total_chapter) : this.b.getString(R.string.comic_column_update_chapter), new Object[]{Integer.valueOf(this.a.f())});
    }

    private String a(int i, int i2) {
        return ao.a(this.a.a(), i, i2);
    }

    public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        if (aVar != null) {
            o.l(aVar.getFromActivity(), String.valueOf(this.a.a()), null);
        }
    }
}
