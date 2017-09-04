package com.qq.reader.module.comic.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.entity.e;
import com.qq.reader.module.comic.entity.i;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ComicDetailIntroduceCard extends ComicDetailPageBaseCard<i> {
    private int COLLAPSED_MAX_LINES = 3;
    private int EXPAND_MAX_LINES = 100;
    private boolean isCollapsed = false;

    public ComicDetailIntroduceCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.comic_detail_item_introduce_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = (e) new Gson().fromJson(jSONObject.toString(), new TypeToken<e<i>>(this) {
            final /* synthetic */ ComicDetailIntroduceCard a;

            {
                this.a = r1;
            }
        }.getType());
        return (this.item == null || this.item.c == null || TextUtils.isEmpty(((i) this.item.c).b)) ? false : true;
    }

    public void attachView() {
        final ImageView imageView = (ImageView) ap.a(getRootView(), R.id.comic_book_intro_arrow_down);
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_detail_intro_update_tag);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_detail_intro_sub_title);
        final TextView textView3 = (TextView) ap.a(getRootView(), R.id.tv_detail_intro_content);
        if (TextUtils.isEmpty(((i) this.item.c).a)) {
            textView2.setVisibility(8);
            textView.setVisibility(8);
        } else {
            textView2.setVisibility(0);
            textView.setVisibility(0);
            textView2.setText(((i) this.item.c).a);
        }
        textView3.setText(((i) this.item.c).b);
        textView3.setEnabled(false);
        textView3.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ComicDetailIntroduceCard c;

            public void onClick(View view) {
                Exception exception;
                Exception exception2;
                CharSequence charSequence;
                if (this.c.isCollapsed) {
                    textView3.setMaxLines(this.c.EXPAND_MAX_LINES);
                    Object obj = ((i) this.c.item.c).b;
                    textView3.setText(obj);
                    imageView.setBackgroundResource(R.drawable.editor_comment_fold);
                    int lineCount = textView3.getLayout().getLineCount() - 1;
                    if (lineCount >= 0) {
                        textView3.setText(obj + this.c.getExLine(textView3.getLayout().getLineRight(lineCount), (float) textView3.getLayout().getLineBottom(lineCount), (float) imageView.getLeft(), (float) imageView.getTop()));
                    }
                    this.c.isCollapsed = false;
                    return;
                }
                textView3.setMaxLines(this.c.COLLAPSED_MAX_LINES);
                CharSequence charSequence2 = null;
                try {
                    int lineStart = textView3.getLayout().getLineStart(0);
                    int lineEnd = textView3.getLayout().getLineEnd(Math.min(textView3.getLineCount() - 1, this.c.COLLAPSED_MAX_LINES - 1));
                    String charSequence3 = textView3.getText().toString();
                    if (lineEnd > 10) {
                        try {
                            if (charSequence3.length() > 10) {
                                charSequence3 = charSequence3.substring(lineStart, lineEnd - 10);
                            }
                        } catch (Exception e) {
                            exception = e;
                            obj = charSequence3;
                            exception2 = exception;
                            exception2.printStackTrace();
                            textView3.setText(charSequence);
                            imageView.setBackgroundResource(R.drawable.editor_comment_unfold);
                            this.c.isCollapsed = true;
                        }
                    }
                    charSequence = charSequence3 + "...";
                } catch (Exception e2) {
                    exception = e2;
                    charSequence = charSequence2;
                    exception2 = exception;
                    exception2.printStackTrace();
                    textView3.setText(charSequence);
                    imageView.setBackgroundResource(R.drawable.editor_comment_unfold);
                    this.c.isCollapsed = true;
                }
                textView3.setText(charSequence);
                imageView.setBackgroundResource(R.drawable.editor_comment_unfold);
                this.c.isCollapsed = true;
            }
        });
        textView3.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ ComicDetailIntroduceCard c;

            public void onGlobalLayout() {
                if (textView3.getViewTreeObserver().isAlive()) {
                    if (textView3.getLineCount() > this.c.COLLAPSED_MAX_LINES) {
                        textView3.setMaxLines(this.c.COLLAPSED_MAX_LINES);
                        int lineStart = textView3.getLayout().getLineStart(0);
                        int lineEnd = textView3.getLayout().getLineEnd(this.c.COLLAPSED_MAX_LINES - 1);
                        String charSequence = textView3.getText().toString();
                        if (charSequence.length() > 10) {
                            charSequence = charSequence.substring(lineStart, lineEnd - 10);
                        }
                        textView3.setText(charSequence + "...");
                        imageView.setVisibility(0);
                        this.c.isCollapsed = true;
                        textView3.setEnabled(true);
                    }
                    textView3.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    private String getExLine(float f, float f2, float f3, float f4) {
        if (f <= f3 || f2 <= f4) {
            return "";
        }
        return "\n";
    }
}
