package com.qq.reader.plugin.audiobook;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class MusicBookListGroupItem extends RelativeLayout {
    private TextView a = null;
    private ImageView b = null;
    private TextView c;
    private TextView d;
    private ImageView e = null;

    public MusicBookListGroupItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MusicBookListGroupItem(Context context) {
        super(context);
    }

    public void a() {
        this.a = (TextView) findViewById(R.id.music_book_list_group_item_text);
        this.b = (ImageView) findViewById(R.id.music_book_list_group_item_image);
        this.c = (TextView) findViewById(R.id.music_book_list_group_item_author);
        this.d = (TextView) findViewById(R.id.music_book_list_group_item_download);
        this.e = (ImageView) findViewById(R.id.music_book_list_choose);
    }

    public void setBookName(String str) {
        this.a.setText(str);
    }

    public void setBookAuthor(String str) {
        this.c.setText(str);
    }

    public void setBookDownload(long j) {
        if (j == -1000) {
            this.d.setText("");
        } else if (j == -1001) {
            this.d.setText("等待下载");
        } else {
            this.d.setText("正在下载第" + j + "章");
        }
    }

    public void setIsExpanded(boolean z) {
        this.b.setBackgroundResource(z ? R.drawable.music_book_arrow_down : R.drawable.music_book_arrow_right);
    }

    public void setIsPlaying(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }
}
