package format.epub;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.tencent.tinker.android.dex.DexFormat;
import format.epub.b.c;
import format.epub.common.b.b;
import format.epub.common.utils.h;
import format.epub.common.utils.k;

public class ImageViewActivity extends Activity {
    private Bitmap a;
    private k b;

    private class a extends View {
        final /* synthetic */ ImageViewActivity a;
        private final Paint b = new Paint();
        private volatile int c = 0;
        private volatile int d = 0;
        private volatile float e = 1.0f;
        private int f = ViewConfiguration.get(getContext().getApplicationContext()).getScaledTouchSlop();
        private boolean g;
        private int h;
        private int i;
        private boolean j = false;
        private float k = -1.0f;
        private float l;

        a(ImageViewActivity imageViewActivity) {
            this.a = imageViewActivity;
            super(imageViewActivity);
        }

        protected void onDraw(Canvas canvas) {
            this.b.setColor(h.a(this.a.b));
            int width = getWidth();
            int height = getHeight();
            canvas.drawRect(0.0f, 0.0f, (float) width, (float) height, this.b);
            if (this.a.a != null && !this.a.a.isRecycled()) {
                int width2 = (int) (((float) this.a.a.getWidth()) * this.e);
                int height2 = (int) (((float) this.a.a.getHeight()) * this.e);
                Rect rect = new Rect(0, 0, (int) (((float) width) / this.e), (int) (((float) height) / this.e));
                Rect rect2 = new Rect(0, 0, width, height);
                if (width2 <= width) {
                    rect.left = 0;
                    rect.right = this.a.a.getWidth();
                    rect2.left = (width - width2) / 2;
                    rect2.right = width2 + rect2.left;
                } else {
                    width2 = this.a.a.getWidth();
                    int i = (int) (((float) width) / this.e);
                    rect.left = Math.min(width2 - i, Math.max(((width2 - i) / 2) - this.c, 0));
                    rect.right += rect.left;
                }
                if (height2 <= height) {
                    rect.top = 0;
                    rect.bottom = this.a.a.getHeight();
                    rect2.top = (height - height2) / 2;
                    rect2.bottom = rect2.top + height2;
                } else {
                    width2 = this.a.a.getHeight();
                    height2 = (int) (((float) height) / this.e);
                    rect.top = Math.min(width2 - height2, Math.max(((width2 - height2) / 2) - this.d, 0));
                    rect.bottom += rect.top;
                }
                canvas.drawBitmap(this.a.a, rect, rect2, this.b);
            }
        }

        private void a(int i, int i2) {
            if (this.a.a != null && !this.a.a.isRecycled()) {
                int width = (int) (((float) getWidth()) / this.e);
                int height = (int) (((float) getHeight()) / this.e);
                int width2 = this.a.a.getWidth();
                int height2 = this.a.a.getHeight();
                if (width < width2) {
                    width = (width2 - width) / 2;
                    width2 = Math.max(-width, Math.min(width, this.c + i));
                } else {
                    width2 = this.c;
                }
                if (height < height2) {
                    width = (height2 - height) / 2;
                    width = Math.max(-width, Math.min(width, this.d + i2));
                } else {
                    width = this.d;
                }
                if (width2 != this.c || width != this.d) {
                    this.c = width2;
                    this.d = width;
                    postInvalidate();
                }
            }
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            switch (motionEvent.getPointerCount()) {
                case 1:
                    return a(motionEvent);
                case 2:
                    return b(motionEvent);
                default:
                    return false;
            }
        }

        private boolean a(MotionEvent motionEvent) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            switch (motionEvent.getAction()) {
                case 0:
                    this.g = true;
                    this.h = x;
                    this.i = y;
                    this.j = false;
                    break;
                case 1:
                    this.g = false;
                    if (!this.j) {
                        this.a.finish();
                        break;
                    }
                    break;
                case 2:
                    if (this.g) {
                        a((int) (((float) (x - this.h)) / this.e), (int) (((float) (y - this.i)) / this.e));
                    }
                    if ((!this.j && Math.abs(x - this.h) >= this.f) || Math.abs(y - this.i) >= this.f) {
                        this.j = true;
                    }
                    this.g = true;
                    this.h = x;
                    this.i = y;
                    break;
            }
            return true;
        }

        private boolean b(MotionEvent motionEvent) {
            float x;
            float y;
            switch (motionEvent.getAction() & 255) {
                case 2:
                    try {
                        x = motionEvent.getX(0) - motionEvent.getX(1);
                        y = motionEvent.getY(0) - motionEvent.getY(1);
                        x = Math.max((x * x) + (y * y), 10.0f);
                        if (this.k >= 0.0f) {
                            y = this.e;
                            this.e = ((float) Math.sqrt((double) (x / this.k))) * this.l;
                            if ((y <= 0.5f && this.e <= y) || (y >= 2.0f && this.e > y)) {
                                this.e = y;
                                break;
                            }
                            postInvalidate();
                            break;
                        }
                        this.k = x;
                        this.l = this.e;
                        break;
                    } catch (Exception e) {
                        Log.e("ImageViewActivity", e.toString());
                        break;
                    }
                    break;
                case 5:
                    try {
                        x = motionEvent.getX(0) - motionEvent.getX(1);
                        y = motionEvent.getY(0) - motionEvent.getY(1);
                        this.k = Math.max((x * x) + (y * y), 10.0f);
                        this.l = this.e;
                        break;
                    } catch (Exception e2) {
                        Log.e("ImageViewActivity", e2.toString());
                        break;
                    }
                case 6:
                    this.k = -1.0f;
                    break;
            }
            return true;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(new a(this));
        Intent intent = getIntent();
        this.b = new k(intent.getIntExtra("bgColor", new k(0, 0, 0).a()));
        Uri data = intent.getData();
        if ("imagefile".equals(data.getScheme())) {
            try {
                String[] split = data.getPath().split(DexFormat.MAGIC_SUFFIX);
                this.a = c.b().b(new format.epub.common.image.a("image/auto", b.b(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]))).a();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                finish();
                return;
            }
        }
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.recycle();
        }
        this.a = null;
    }
}
