package com.qq.reader.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.module.bookshelf.signup.SignupManager;
import com.qq.reader.module.bookshelf.signup.SignupManager.SignItem;
import com.qq.reader.module.bookshelf.signup.SignupManager.a;
import com.tencent.feedback.proguard.R;
import com.tencent.upload.log.trace.TracerConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class LuckyDrawDialog extends LeakFixDialog {
    private View a;
    private Button b;
    private TextView c;
    private TextView d;
    private Context e;
    private ArrayList<View> f = new ArrayList();
    private String g;
    private String h;

    public LuckyDrawDialog(Context context, int i) {
        super(context, i);
        this.e = context;
        this.a = getLayoutInflater().inflate(R.layout.bookshelf_sign_lucky_draw, null);
        requestWindowFeature(1);
        setContentView(this.a);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.dimAmount = 0.7f;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(2);
        setCanceledOnTouchOutside(true);
    }

    public void a(OnClickListener onClickListener) {
        if (this.b != null) {
            this.b.setOnClickListener(onClickListener);
        }
    }

    public void a(boolean z, SignItem signItem, ArrayList<a> arrayList) {
        ViewGroup viewGroup = (ViewGroup) this.a.findViewById(R.id.row1);
        ViewGroup viewGroup2 = (ViewGroup) this.a.findViewById(R.id.row2);
        this.c = (TextView) this.a.findViewById(R.id.lucky_draw_tips);
        this.d = (TextView) this.a.findViewById(R.id.lucky_draw_tips_classes);
        this.b = (Button) this.a.findViewById(R.id.fill_connect_way_btn);
        this.f = new ArrayList();
        a((ArrayList) arrayList);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            this.f.add(childAt);
            a aVar = (a) arrayList.get(i);
            ImageView imageView = (ImageView) childAt.findViewById(R.id.image);
            TextView textView = (TextView) childAt.findViewById(R.id.text);
            childAt.setTag(aVar);
            c.a(this.e).a(aVar.d, imageView, com.qq.reader.common.imageloader.a.a().u());
            textView.setText(a(this.e, aVar));
        }
        for (int i2 = 0; i2 < viewGroup2.getChildCount(); i2++) {
            View childAt2 = viewGroup2.getChildAt((viewGroup2.getChildCount() - 1) - i2);
            this.f.add(childAt2);
            a aVar2 = (a) arrayList.get(i2 + 3);
            ImageView imageView2 = (ImageView) childAt2.findViewById(R.id.image);
            TextView textView2 = (TextView) childAt2.findViewById(R.id.text);
            childAt2.setTag(aVar2);
            c.a(this.e).a(aVar2.d, imageView2, com.qq.reader.common.imageloader.a.a().u());
            textView2.setText(a(this.e, aVar2));
        }
        if (z) {
            this.c.setGravity(17);
            this.c.setTextSize(12.0f);
            this.c.setText(R.string.sign_lucky_draw_tips);
            this.a.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ LuckyDrawDialog a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    this.a.dismiss();
                    return false;
                }
            });
        } else if (signItem != null) {
            int i3;
            this.a.setOnTouchListener(null);
            this.c.setGravity(17);
            if (VERSION.SDK_INT > 10) {
                this.c.setAlpha(0.7f);
            }
            this.c.setText(R.string.sign_lucky_draw_getting);
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                if (a(signItem, (a) arrayList.get(i4))) {
                    i3 = i4 + 54;
                    break;
                }
            }
            i3 = 0;
            a(i3, signItem.mNeedAddress);
            a(signItem);
        }
    }

    private void a(ArrayList<a> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                String optString = new JSONObject(((a) arrayList.get(i)).f).optString("order");
                if (TextUtils.isEmpty(optString)) {
                    optString = "0";
                }
                ((a) arrayList.get(i)).e = Integer.parseInt(optString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(arrayList, new Comparator<a>(this) {
            final /* synthetic */ LuckyDrawDialog a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((a) obj, (a) obj2);
            }

            public int a(a aVar, a aVar2) {
                return aVar2.e > aVar.e ? -1 : 1;
            }
        });
    }

    private boolean a(SignItem signItem, a aVar) {
        String str = "";
        Object obj = "";
        if (signItem.mItemId == 105) {
            try {
                JSONObject jSONObject = new JSONObject(signItem.mExtInfo);
                if (jSONObject != null) {
                    str = jSONObject.optString("bookType");
                }
                jSONObject = new JSONObject(aVar.f);
                if (jSONObject != null) {
                    obj = jSONObject.optString("bookType");
                }
                return str.equals(obj);
            } catch (JSONException e) {
                return false;
            }
        } else if (signItem.mItemId == aVar.b) {
            return true;
        } else {
            return false;
        }
    }

    private void a(SignItem signItem) {
        if (signItem.mItemId == 103) {
            try {
                this.g = "《" + new JSONObject(signItem.mExtInfo).optString("title") + "》";
                this.h = "解锁收费章节" + String.format("+%d", new Object[]{Integer.valueOf(signItem.mCount)});
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (signItem.mItemId == 104) {
            try {
                r0 = new JSONObject(signItem.mExtInfo);
                this.g = TextUtils.isEmpty(r0.optString("title")) ? "" : "《" + r0.getString("title") + "》";
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.h = String.format("限免%d小时", new Object[]{Integer.valueOf(signItem.mCount)});
        } else if (signItem.mItemId == 105) {
            String str = "";
            str = "";
            str = "";
            try {
                r0 = new JSONObject(signItem.mExtInfo);
                if (r0 != null) {
                    this.h = SignupManager.a().a(this.e, r0.optString("bookType"), r0.optString("deductionType"), r0.optString("intro"));
                }
                if (signItem.mCount > 0) {
                    this.g = String.format(this.e.getString(R.string.sign_lucky_draw_get) + this.e.getString(R.string.sign_exp_inc), new Object[]{this.e.getString(R.string.sign_deduction_default_intro), Integer.valueOf(signItem.mCount)});
                }
            } catch (JSONException e22) {
                e22.printStackTrace();
            }
        } else {
            this.g = String.format(this.e.getString(R.string.sign_lucky_draw_get) + this.e.getString(R.string.sign_exp_inc), new Object[]{signItem.mPrize, Integer.valueOf(signItem.mCount)});
        }
    }

    private void a(int i, final boolean z) {
        if (this.f.size() != 0) {
            try {
                if (VERSION.SDK_INT > 10) {
                    Iterator it = this.f.iterator();
                    while (it.hasNext()) {
                        ((View) it.next()).setAlpha(0.3f);
                    }
                    ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, i});
                    ofInt.setDuration(TracerConfig.LOG_FLUSH_DURATION);
                    ofInt.addUpdateListener(new AnimatorUpdateListener(this) {
                        final /* synthetic */ LuckyDrawDialog a;

                        {
                            this.a = r1;
                        }

                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue() % 6;
                            ((View) this.a.f.get(intValue)).setAlpha(1.0f);
                            ((View) this.a.f.get((intValue + 5) % 6)).setAlpha(0.3f);
                        }
                    });
                    ofInt.addListener(new AnimatorListener(this) {
                        final /* synthetic */ LuckyDrawDialog b;

                        public void onAnimationStart(Animator animator) {
                        }

                        public void onAnimationEnd(Animator animator) {
                            if (VERSION.SDK_INT > 10) {
                                this.b.c.setAlpha(1.0f);
                            }
                            this.b.c.setText(this.b.g);
                            this.b.d.setText(this.b.h);
                            com.qq.reader.common.login.c.c().c();
                            if (z) {
                                this.b.d.setVisibility(8);
                                this.b.b.setVisibility(0);
                                return;
                            }
                            this.b.d.setVisibility(0);
                            this.b.b.setVisibility(8);
                        }

                        public void onAnimationCancel(Animator animator) {
                        }

                        public void onAnimationRepeat(Animator animator) {
                        }
                    });
                    ofInt.start();
                    return;
                }
                this.c.setText(this.g);
                this.d.setText(this.h);
                if (z) {
                    this.b.setVisibility(0);
                } else {
                    this.b.setVisibility(8);
                }
            } catch (NullPointerException e) {
                af.a(this.e, "抽奖异常", 0).a();
                e.printStackTrace();
            }
        }
    }

    public String a(Context context, a aVar) {
        String str = "";
        if (aVar == null) {
            return str;
        }
        if (aVar.b != 105) {
            return aVar.c;
        }
        try {
            JSONObject jSONObject = new JSONObject(aVar.f);
            if (jSONObject == null) {
                return aVar.c;
            }
            str = jSONObject.optString("bookType");
            if ("1".equals(str)) {
                return context.getString(R.string.sign_deduction_classes_book);
            }
            if ("2".equals(str)) {
                return context.getString(R.string.sign_deduction_classes_comic);
            }
            return context.getString(R.string.sign_deduction_classes_listen);
        } catch (Exception e) {
            return aVar.c;
        }
    }
}
