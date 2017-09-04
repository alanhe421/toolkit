package com.qq.reader.plugin.audiobook;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.tencent.feedback.proguard.R;

public class MusicBookListChapterItem extends RelativeLayout {
    private TextView a = null;
    private TextView b = null;
    private TextView c = null;
    private TextView d = null;
    private TextView e = null;
    private ImageView f = null;
    private ProgressBar g = null;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[TaskStateEnum.values().length];

        static {
            try {
                a[TaskStateEnum.Prepared.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[TaskStateEnum.DeactivePrepared.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[TaskStateEnum.DeactiveStarted.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[TaskStateEnum.Started.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[TaskStateEnum.Paused.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[TaskStateEnum.Failed.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public MusicBookListChapterItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MusicBookListChapterItem(Context context) {
        super(context);
    }

    public void a() {
        this.a = (TextView) findViewById(R.id.music_book_list_item_title);
        this.b = (TextView) findViewById(R.id.music_book_list_item_status);
        this.c = (TextView) findViewById(R.id.music_book_list_item_processtext);
        this.d = (TextView) findViewById(R.id.music_book_list_item_time);
        this.f = (ImageView) findViewById(R.id.music_book_chapter_choose);
        this.e = (TextView) findViewById(R.id.music_book_list_item_size);
        this.g = (ProgressBar) findViewById(R.id.music_book_list_item_processbar);
    }

    public void setTitle(String str) {
        this.a.setText(str);
    }

    public void setStatus(TaskStateEnum taskStateEnum) {
        this.b.setVisibility(0);
        this.c.setVisibility(0);
        switch (AnonymousClass1.a[taskStateEnum.ordinal()]) {
            case 1:
            case 2:
            case 3:
                this.b.setText("等待下载");
                return;
            case 4:
                this.b.setText("正在下载");
                return;
            case 5:
                this.b.setText("暂停下载");
                this.g.setVisibility(8);
                return;
            case 6:
                this.b.setText("下载失败");
                this.g.setVisibility(8);
                return;
            default:
                this.b.setVisibility(8);
                this.c.setVisibility(8);
                this.g.setVisibility(8);
                return;
        }
    }

    public void setProgress(String str) {
        this.c.setText(str);
    }

    public void setDuration(String str) {
        this.d.setText(str);
    }

    public void setFileLength(String str) {
        this.e.setText(str);
    }

    public void setIsPlaying(boolean z) {
        this.f.setVisibility(z ? 0 : 8);
    }
}
