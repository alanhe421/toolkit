package com.qq.reader.module.question.card;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class HeaderCardOfAuthorQA extends a {
    com.qq.reader.module.question.data.a authorData;
    OnClickListener intoEditAct = new OnClickListener(this) {
        final /* synthetic */ HeaderCardOfAuthorQA a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            o.a(this.a.getEvnetListener().getFromActivity(), this.a.authorData.b(), this.a.authorData.c(), this.a.authorData.e(), this.a.authorData.f(), String.valueOf(this.a.authorData.d()));
        }
    };
    int pageStatus;

    public com.qq.reader.module.question.data.a getAuthorData() {
        return this.authorData;
    }

    public void setPageStatus(int i) {
        this.pageStatus = i;
    }

    public HeaderCardOfAuthorQA(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_author_qa_list_head_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.authorData = new com.qq.reader.module.question.data.a();
        this.authorData.a(jSONObject);
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.author_info_introduction);
        textView.setText(getTextIntro(this.authorData.e()));
        textView.setOnClickListener(this.intoEditAct);
        textView = (TextView) ap.a(getRootView(), R.id.author_info_question_cost);
        textView.setText(getCostText());
        textView.setOnClickListener(this.intoEditAct);
        ((TextView) ap.a(getRootView(), R.id.author_qa_income_des)).setText(getTotalTxt());
        c.a(getEvnetListener().getFromActivity()).a(this.authorData.b(), (ImageView) ap.a(getRootView(), R.id.author_avatar_icon), com.qq.reader.common.imageloader.a.a().o());
        ((ImageView) ap.a(getRootView(), R.id.author_type_icon)).setImageResource(ao.e(this.authorData.c()));
        ((TextView) ap.a(getRootView(), R.id.tv_answer_listen_status)).setText(getQuestionTxt());
        final TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_wait);
        final TextView textView3 = (TextView) ap.a(getRootView(), R.id.tv_all);
        final ImageView imageView = (ImageView) ap.a(getRootView(), R.id.iv_all_selector);
        final ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.iv_waiting_selector);
        if (this.pageStatus == 2) {
            textView2.setSelected(true);
            imageView2.setSelected(true);
            imageView.setSelected(false);
            textView3.setSelected(false);
        } else {
            textView3.setSelected(true);
            imageView.setSelected(true);
            textView2.setSelected(false);
            imageView2.setSelected(false);
        }
        textView2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HeaderCardOfAuthorQA e;

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("function_type", 8);
                this.e.getEvnetListener().doFunction(bundle);
                textView3.setSelected(false);
                imageView.setSelected(false);
                textView2.setSelected(true);
                imageView2.setSelected(true);
            }
        });
        textView3.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HeaderCardOfAuthorQA e;

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("function_type", 7);
                this.e.getEvnetListener().doFunction(bundle);
                textView3.setSelected(true);
                imageView.setSelected(true);
                textView2.setSelected(false);
                imageView2.setSelected(false);
            }
        });
        textView = (TextView) ap.a(getRootView(), R.id.tv_wxcharge);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.tv_go_income_detail);
        textView.setVisibility(8);
        textView4.setVisibility(8);
        textView.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ HeaderCardOfAuthorQA a;

            {
                this.a = r1;
            }

            public void a(View view) {
                if (this.a.authorData != null) {
                    o.f(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.authorData.d()));
                }
            }
        });
        textView4.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
            final /* synthetic */ HeaderCardOfAuthorQA a;

            {
                this.a = r1;
            }

            public void a(View view) {
                if (this.a.authorData != null) {
                    o.g(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.authorData.d()));
                }
            }
        });
        if (this.authorData == null) {
            textView.setVisibility(8);
            textView4.setVisibility(8);
        } else if (this.authorData.l()) {
            textView.setVisibility(8);
            textView4.setVisibility(0);
        } else {
            textView.setVisibility(0);
            textView4.setVisibility(8);
        }
    }

    private CharSequence getCostText() {
        CharSequence spannableStringBuilder = new SpannableStringBuilder(ReaderApplication.getApplicationImp().getString(R.string.qa_edit_ask_to).concat(":"));
        Object format = String.format(" %d书币", new Object[]{Integer.valueOf(this.authorData.f())});
        spannableStringBuilder.append(format);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c401)), spannableStringBuilder.length() - format.length(), spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    private CharSequence getTextIntro(String str) {
        if (TextUtils.isEmpty(str)) {
            str = ReaderApplication.getApplicationImp().getString(R.string.author_center_header_hint).concat("  ");
        }
        CharSequence spannableStringBuilder = new SpannableStringBuilder(str.trim() + "  ");
        CharSequence spannableString = new SpannableString("##");
        spannableString.setSpan(new ImageSpan(ReaderApplication.getApplicationImp(), R.drawable.personalhomepage_edit, 1), 0, spannableString.length(), 33);
        spannableStringBuilder.append(spannableString);
        return spannableStringBuilder;
    }

    private CharSequence getQuestionTxt() {
        return String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.author_center_fenda_income_detail), new Object[]{Integer.valueOf(this.authorData.g()), Integer.valueOf(this.authorData.h())});
    }

    private CharSequence getTotalTxt() {
        int color = ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c401);
        CharSequence format = String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.common_qa_cost), new Object[]{Integer.valueOf(this.authorData.i())});
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(ReaderApplication.getApplicationImp().getResources().getString(R.string.author_center_fenda_month_income));
        spannableStringBuilder.append(format);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(color), 4, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public void addIncomeByAnswer(int i) {
        this.authorData.b(i);
        attachView();
    }

    public void refresh() {
        super.refresh();
        if (getRootView() != null) {
            attachView(getRootView());
        }
    }
}
