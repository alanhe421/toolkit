package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.view.metro.MetroItem;
import com.tencent.feedback.proguard.R;
import java.util.List;

public class VoiceTabs extends HorizontalScrollView implements OnClickListener {
    LinearLayout a;
    Context b;
    int c;
    float d;
    a e;
    private List<MetroItem> f;
    private LayoutInflater g;
    private int h = -1;

    public interface a {
        boolean a(int i);
    }

    public VoiceTabs(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        this.g = (LayoutInflater) context.getSystemService("layout_inflater");
        this.d = context.getResources().getDisplayMetrics().density;
        this.c = (int) context.getResources().getDimension(R.dimen.metro_h_w);
    }

    public void setDataset(List<MetroItem> list) {
        list.add(new MetroItem(-100, ""));
        this.f = list;
    }

    public void a() {
        if (this.a != null) {
            this.a.removeAllViews();
        }
        this.h = -1;
        for (MetroItem a : this.f) {
            a(a);
        }
    }

    private void a(MetroItem metroItem) {
        float intrinsicWidth;
        if (this.a == null || this.a.getParent() != this) {
            this.a = new LinearLayout(this.b);
            addView(this.a, new LayoutParams(-2, -1));
        }
        VoiceTabItem voiceTabItem = (VoiceTabItem) this.g.inflate(R.layout.ttsvoice_item, null);
        voiceTabItem.a();
        if (metroItem.getId() == -100) {
            if (d.n) {
                voiceTabItem.setBackgroundRes(R.drawable.voice_add_selector_night);
            } else {
                voiceTabItem.setBackgroundRes(R.drawable.voice_add_selector);
            }
            voiceTabItem.setText("");
            intrinsicWidth = ((float) this.b.getResources().getDrawable(R.drawable.voice_add).getIntrinsicWidth()) + (this.b.getResources().getDimension(R.dimen.tts_voice_select_maring) * 2.0f);
            voiceTabItem.setmDividerVisiable(8);
        } else {
            voiceTabItem.setBackgroundRes(0);
            if (d.n) {
                voiceTabItem.setTextColor(R.color.tts_voice_item_textcolor_night);
            } else {
                voiceTabItem.setTextColor(R.color.tts_voice_item_textcolor);
            }
            voiceTabItem.setText(metroItem.getDisplayName());
            intrinsicWidth = voiceTabItem.a(metroItem.getDisplayName()) + (this.b.getResources().getDimension(R.dimen.tts_voice_select_maring) * 2.0f);
            voiceTabItem.setmDividerVisiable(0);
        }
        voiceTabItem.setOnClickListener(this);
        this.a.addView(voiceTabItem, new LinearLayout.LayoutParams((int) intrinsicWidth, -1));
    }

    public MetroItem a(int i) {
        try {
            return (MetroItem) this.f.get(i);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onClick(View view) {
        int i = -1;
        for (int i2 = 0; i2 < this.a.getChildCount(); i2++) {
            if (this.a.getChildAt(i2) == view) {
                i = i2;
            }
        }
        if (this.e != null && this.a.getChildCount() > i && this.h != i) {
            this.e.a(i);
        }
    }

    private void b(int i) {
        if (i != this.h) {
            if (this.h != -1) {
                ((VoiceTabItem) this.a.getChildAt(this.h)).setSelect(false, -1);
            }
            int childCount = this.a.getChildCount();
            if (i >= childCount) {
                this.h = childCount - 1;
            } else {
                this.h = i;
            }
            ((VoiceTabItem) this.a.getChildAt(this.h)).setSelect(true, this.h % 5);
        }
    }

    public void b() {
        post(new Runnable(this) {
            final /* synthetic */ VoiceTabs a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.scrollTo(this.a.c * this.a.h, 0);
            }
        });
    }

    public void setSelectedIndex(int i) {
        if (this.h != i) {
            b(i);
        }
    }

    public void setOnTabsChangedListener(a aVar) {
        this.e = aVar;
    }
}
