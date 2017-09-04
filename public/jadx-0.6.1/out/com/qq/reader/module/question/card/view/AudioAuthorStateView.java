package com.qq.reader.module.question.card.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.question.data.a;
import com.tencent.feedback.proguard.R;

public class AudioAuthorStateView extends RelativeLayout {
    int a = 0;
    private TextView b;
    private ImageView c;
    private ImageView d;
    private TextView e;
    private ImageView f;
    private TextView g;

    public AudioAuthorStateView(Context context) {
        super(context);
        a(context);
    }

    public AudioAuthorStateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public AudioAuthorStateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.author_state_layout, this, true);
        a();
    }

    private void a() {
        this.b = (TextView) findViewById(R.id.author_name);
        this.c = (ImageView) findViewById(R.id.author_avatar);
        this.d = (ImageView) findViewById(R.id.author_level);
        this.e = (TextView) findViewById(R.id.info_text);
        this.f = (ImageView) findViewById(R.id.author_title);
        this.g = (TextView) findViewById(R.id.ask_button);
    }

    public void setType(int i) {
        this.a = i;
    }

    public void a(a aVar) {
        View findViewById = findViewById(R.id.author_name_layout);
        if ((this.a & 4) != 0) {
            findViewById.setVisibility(8);
            this.e.setTextSize(14.0f);
            this.e.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c102));
        } else {
            this.b.setText(aVar.a());
            findViewById.setVisibility(0);
        }
        findViewById = findViewById(R.id.author_avatar_layout);
        if ((this.a & 2) != 0) {
            findViewById.setVisibility(8);
        } else {
            c.a(getContext()).a(aVar.b(), this.c, com.qq.reader.common.imageloader.a.a().f());
            findViewById.setVisibility(0);
            this.c.setVisibility(0);
        }
        if (aVar.c() != 0) {
            this.d.setVisibility(0);
            this.d.setImageResource(ao.e(aVar.c()));
        } else {
            this.d.setVisibility(8);
        }
        if ((this.a & 8) != 0) {
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
        if (aVar.j() > 0) {
            this.e.setText(b(aVar));
        } else if (TextUtils.isEmpty(aVar.e())) {
            this.e.setText(ReaderApplication.getApplicationImp().getString(R.string.default_author_intro_text));
        } else {
            this.e.setText(aVar.e());
        }
        if ((this.a & 1) != 0) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
        }
    }

    public void setIconListener(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public void setButttonListener(OnClickListener onClickListener) {
        this.g.setOnClickListener(onClickListener);
    }

    public void a(int i, int i2) {
        this.b.setTextColor(i);
        this.e.setTextColor(i2);
    }

    private SpannableString b(a aVar) {
        Object format = String.format(ReaderApplication.getApplicationImp().getString(R.string.readendpage_author_answer_state), new Object[]{Integer.valueOf(aVar.j())});
        if (aVar.k() > 0) {
            Object obj = format + "  " + String.format(ReaderApplication.getApplicationImp().getString(R.string.readendpage_author_listen_state), new Object[]{Integer.valueOf(aVar.k())});
            SpannableString spannableString = new SpannableString(obj);
            spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_1)), 0, 2, 33);
            spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_3)), 2, String.valueOf(aVar.j()).length() + 2, 33);
            spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_1)), String.valueOf(aVar.j()).length() + 2, format.length() + 3, 33);
            spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_3)), format.length() + 3, (format.length() + 3) + String.valueOf(aVar.k()).length(), 33);
            spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_1)), (format.length() + 3) + String.valueOf(aVar.k()).length(), obj.length(), 33);
            return spannableString;
        }
        spannableString = new SpannableString(format);
        spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_1)), 0, 2, 33);
        spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_3)), 2, String.valueOf(aVar.j()).length() + 2, 33);
        spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_1)), String.valueOf(aVar.j()).length() + 2, format.length(), 33);
        return spannableString;
    }
}
