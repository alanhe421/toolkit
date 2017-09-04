package com.tencent.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Region.Op;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

public class ShaderAnimLayout extends RelativeLayout {
    public static final long ANIM_DURATION = 200;
    float mAnimFactor = 0.0f;
    private AnimationListener mAnimListener = new AnimationListener() {
        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            ShaderAnimLayout.this.setVisibility(8);
        }
    };
    private Animation mCalcAnimation = new Animation() {
        protected void applyTransformation(float f, Transformation transformation) {
            if (ShaderAnimLayout.this.mHide) {
                ShaderAnimLayout.this.mAnimFactor = 1.0f - f;
            } else {
                ShaderAnimLayout.this.mAnimFactor = f;
            }
            ShaderAnimLayout.this.invalidate();
        }
    };
    boolean mHide = false;
    private boolean mIsInitial = false;
    private Path mPath = new Path();

    public ShaderAnimLayout(Context context) {
        super(context);
        init();
    }

    public ShaderAnimLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ShaderAnimLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        if (!this.mIsInitial) {
            this.mCalcAnimation.setDuration(200);
            this.mCalcAnimation.setInterpolator(new LinearInterpolator());
            this.mIsInitial = true;
        }
    }

    public void show() {
        if (getVisibility() != 0) {
            this.mHide = false;
            this.mCalcAnimation.setAnimationListener(null);
            clearAnimation();
            setVisibility(0);
            startAnimation(this.mCalcAnimation);
        }
    }

    public void showDirectly() {
        clearAnimation();
        this.mCalcAnimation.setAnimationListener(null);
        this.mHide = false;
        setVisibility(0);
        this.mAnimFactor = 1.0f;
    }

    public void hideDirectly() {
        clearAnimation();
        this.mCalcAnimation.setAnimationListener(null);
        this.mHide = true;
        setVisibility(8);
        this.mAnimFactor = 0.0f;
    }

    public void hide() {
        if (getVisibility() == 0) {
            this.mHide = true;
            clearAnimation();
            this.mCalcAnimation.setAnimationListener(this.mAnimListener);
            startAnimation(this.mCalcAnimation);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        this.mPath.reset();
        this.mPath.addRect(((float) getWidth()) * (1.0f - this.mAnimFactor), 0.0f, (float) getWidth(), (float) getBottom(), Direction.CW);
        try {
            canvas.clipPath(this.mPath, Op.INTERSECT);
        } catch (Exception e) {
        }
        super.dispatchDraw(canvas);
    }

    public void hideWithoutAnimation() {
        setVisibility(8);
    }
}
