package com.qq.reader.module.bookshelf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.view.ProgressBar;
import com.tencent.feedback.proguard.R;

public class BookStandAdapterItem extends RelativeLayout {
    ImageView a;
    ImageView b;
    ImageView c;
    ImageView d;
    ImageView e;
    ImageView f;
    FrameLayout g;
    ImageView h;
    TextView i;
    RelativeLayout j;
    private ProgressBar k;

    public BookStandAdapterItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BookStandAdapterItem(Context context) {
        super(context);
    }

    protected View getContentView() {
        return this.b;
    }

    public void a() {
        this.j = (RelativeLayout) findViewById(R.id.cover_layout);
        this.a = (ImageView) findViewById(R.id.cover);
        this.b = (ImageView) findViewById(R.id.book_cover);
        this.d = (ImageView) findViewById(R.id.cover_checkbox);
        this.e = (ImageView) findViewById(R.id.cover_has_newcontent);
        this.f = (ImageView) findViewById(R.id.online_flag);
        this.c = (ImageView) findViewById(R.id.status);
        this.i = (TextView) findViewById(R.id.cover_name);
        this.g = (FrameLayout) findViewById(R.id.download_layout);
        this.h = (ImageView) findViewById(R.id.lastread);
        this.k = (ProgressBar) findViewById(R.id.download_progress);
    }

    public ImageView getCoverImageView() {
        return this.a;
    }

    public void setPosition(int i) {
        this.b.setTag(new Integer(i));
    }

    public void setCoverDownload(boolean z, int i, boolean z2) {
        if (!z || i < 0 || i > 100 || z2) {
            this.g.setVisibility(8);
            return;
        }
        this.g.setVisibility(0);
        this.k.setProgress((double) i);
    }

    public void setStatusChange(TaskStateEnum taskStateEnum, boolean z, boolean z2) {
        if (taskStateEnum == TaskStateEnum.Paused && !z) {
            this.c.setVisibility(0);
            if (!z2) {
                this.c.setBackgroundResource(R.drawable.bookcase_book_pause_nor);
            }
        } else if (taskStateEnum == TaskStateEnum.Failed) {
            this.c.setVisibility(0);
            if (!z2) {
                this.c.setBackgroundResource(R.drawable.bookcase_book_fail_nor);
            }
        } else if (taskStateEnum == TaskStateEnum.Started || taskStateEnum == TaskStateEnum.DeactiveStarted) {
            this.c.setVisibility(0);
            if (!z2) {
            }
        } else {
            this.c.setVisibility(8);
        }
    }

    public void setBookCoverCheckBoxStatus(int i, Drawable drawable) {
        switch (i) {
            case 0:
                this.d.setVisibility(0);
                this.d.setBackgroundDrawable(drawable);
                return;
            case 1:
                this.d.setVisibility(0);
                this.d.setBackgroundDrawable(drawable);
                return;
            default:
                this.d.setVisibility(8);
                return;
        }
    }

    public void setBookHasNewContent(boolean z) {
        if (z) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
    }

    public void setTagFlag(boolean z, boolean z2, int i) {
        if (z2) {
            this.f.setVisibility(0);
            if (z) {
                if (i == 0) {
                    this.f.setImageResource(R.drawable.bookcase_book_online_big);
                    return;
                } else {
                    this.f.setImageResource(R.drawable.bookcase_book_not_download_big);
                    return;
                }
            } else if (i == 0) {
                this.f.setImageResource(R.drawable.bookcase_book_online);
                return;
            } else {
                this.f.setImageResource(R.drawable.bookcase_book_not_download);
                return;
            }
        }
        this.f.setVisibility(8);
    }

    public void setDefaultCover(int i) {
        this.a.setVisibility(0);
        this.b.setVisibility(0);
        this.a.setImageResource(i);
    }

    public void setCover(Drawable drawable) {
        this.a.setImageDrawable(drawable);
    }

    public void setCover(Bitmap bitmap) {
        this.a.setImageBitmap(bitmap);
    }

    public void setNorCove(Drawable drawable) {
        this.a.setImageDrawable(drawable);
    }

    public void setCoverVisibility(boolean z) {
        if (z) {
            this.a.setVisibility(0);
            this.b.setVisibility(0);
            return;
        }
        this.a.setVisibility(4);
        this.b.setVisibility(4);
    }

    public void setReaded(boolean z) {
        if (z) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(4);
        }
    }

    public void setCoverName(String str) {
        this.i.setText(str);
    }
}
