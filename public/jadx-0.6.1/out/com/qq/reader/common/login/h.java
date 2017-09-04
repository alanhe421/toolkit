package com.qq.reader.common.login;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.c.a;
import com.qq.reader.common.login.a.c;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;

/* compiled from: WVerifyDialog */
public class h extends g implements f {
    private ImageView i;
    private EditText j;
    private TextView k = null;
    private String l;

    public h(Activity activity, byte[] bArr, String str) {
        this.a = activity;
        c.a((Context) activity).a((f) this);
        if (this.f == null) {
            a(activity, null, R.layout.verifydialog, 0, false);
            this.f.setCanceledOnTouchOutside(false);
            this.i = (ImageView) this.f.findViewById(R.id.verifyImage);
            this.j = (EditText) this.f.findViewById(R.id.verifyIpnut);
            this.l = str;
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            float f = (2.0f * a.bZ) / 1.5f;
            this.i.setLayoutParams(new LayoutParams((int) (((float) decodeByteArray.getWidth()) * f), (int) (f * ((float) decodeByteArray.getHeight()))));
            this.i.setImageBitmap(decodeByteArray);
            ((Button) this.f.findViewById(R.id.verify_btn_cancel)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.cancel();
                }
            });
            this.k = (TextView) this.f.findViewById(R.id.getVerifyImage);
            this.k.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    c.a(this.a.a).a(this.a.l);
                }
            });
            ((Button) this.f.findViewById(R.id.verify_btn_sure)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    String obj = this.a.j.getText().toString();
                    if (obj == null || obj.length() == 0 || obj.length() < 4) {
                        af.a(this.a.a.getApplicationContext(), (CharSequence) "验证码格式不对", 0).a();
                        return;
                    }
                    ((InputMethodManager) this.a.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.j.getWindowToken(), 2);
                    this.a.a(this.a.a.getResources().getString(R.string.porgress_verify_message));
                    c.a(this.a.a).a(this.a.l, this.a.j.getText().toString().getBytes());
                }
            });
            a(new OnCancelListener(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.cancel();
                    ((ReaderBaseActivity) this.a.a).progressCancel();
                }
            });
        }
    }
}
