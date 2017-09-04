package com.qq.reader.widget.picbrowser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dex.DexFormat;
import format.epub.b.c;
import format.epub.common.image.a;
import format.epub.common.image.b;

public class PictureActivity extends Activity implements AnimationListener {
    private AnimationImageView a;
    private String b = "/120";
    private String c = "/460";
    private boolean d = false;
    private String e = null;
    private int[] f = null;
    private int g = 0;
    private boolean h = false;
    private long i = 0;
    private View j;
    private Bitmap k;
    private boolean l = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.orginal_picture_view);
        this.f = getIntent().getIntArrayExtra("EXTRA_IMAGE_VIEW_POSITION");
        this.j = findViewById(R.id.cover);
        a();
        this.a = (AnimationImageView) findViewById(R.id.img);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PictureActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        c();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    private void a(View view, boolean z) {
        if (!this.l) {
            this.l = true;
            if (z) {
                this.a.a(this.f);
            }
        }
    }

    public void finish() {
        b();
        if (!this.a.a((AnimationListener) this)) {
            super.finish();
            overridePendingTransition(0, 0);
        }
    }

    private void a() {
        this.j.setVisibility(0);
    }

    private void b() {
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(350);
        this.j.startAnimation(alphaAnimation);
    }

    public void onAnimationEnd(Animation animation) {
        this.j.setVisibility(8);
        super.finish();
        overridePendingTransition(0, 0);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }

    private void c() {
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data == null || !"imagefile".equals(data.getScheme())) {
            finish();
            return;
        }
        try {
            String[] split = data.getPath().split(DexFormat.MAGIC_SUFFIX);
            b aVar = new a("image/auto", format.epub.common.b.b.b(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            com.qq.reader.common.drm.a.a = intent.getStringExtra("zipkey");
            this.k = c.b().b(aVar).a();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
        this.d = false;
        if (this.k != null) {
            this.a.setImageBitmap(this.k);
            a(this.a, true);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.k != null) {
            this.k.recycle();
        }
        this.k = null;
    }
}
