package com.sina.weibo.sdk.api;

import android.os.Bundle;

/* compiled from: WeiboMultiMessage */
public final class a {
    public static int d = 1;
    public static int e = 2;
    public TextObject a;
    public ImageObject b;
    public BaseMediaObject c;

    public Bundle a(Bundle bundle) {
        if (this.a != null) {
            bundle.putParcelable("_weibo_message_text", this.a);
            bundle.putString("_weibo_message_text_extra", this.a.a());
        }
        if (this.b != null) {
            bundle.putParcelable("_weibo_message_image", this.b);
            bundle.putString("_weibo_message_image_extra", this.b.a());
        }
        if (this.c != null) {
            bundle.putParcelable("_weibo_message_media", this.c);
            bundle.putString("_weibo_message_media_extra", this.c.a());
        }
        return bundle;
    }

    public a b(Bundle bundle) {
        this.a = (TextObject) bundle.getParcelable("_weibo_message_text");
        if (this.a != null) {
            this.a.a(bundle.getString("_weibo_message_text_extra"));
        }
        this.b = (ImageObject) bundle.getParcelable("_weibo_message_image");
        if (this.b != null) {
            this.b.a(bundle.getString("_weibo_message_image_extra"));
        }
        this.c = (BaseMediaObject) bundle.getParcelable("_weibo_message_media");
        if (this.c != null) {
            this.c.a(bundle.getString("_weibo_message_media_extra"));
        }
        return this;
    }
}
