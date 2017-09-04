package com.nineoldandroids.animation;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ValueAnimator extends Animator {
    static final int ANIMATION_FRAME = 1;
    static final int ANIMATION_START = 0;
    private static final long DEFAULT_FRAME_DELAY = 10;
    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    static final int RUNNING = 1;
    static final int SEEKED = 2;
    static final int STOPPED = 0;
    private static ThreadLocal<AnimationHandler> sAnimationHandler = new ThreadLocal();
    private static final ThreadLocal<ArrayList<ValueAnimator>> sAnimations = new ThreadLocal<ArrayList<ValueAnimator>>() {
        protected ArrayList<ValueAnimator> initialValue() {
            return new ArrayList();
        }
    };
    private static final Interpolator sDefaultInterpolator = new AccelerateDecelerateInterpolator();
    private static final ThreadLocal<ArrayList<ValueAnimator>> sDelayedAnims = new ThreadLocal<ArrayList<ValueAnimator>>() {
        protected ArrayList<ValueAnimator> initialValue() {
            return new ArrayList();
        }
    };
    private static final ThreadLocal<ArrayList<ValueAnimator>> sEndingAnims = new ThreadLocal<ArrayList<ValueAnimator>>() {
        protected ArrayList<ValueAnimator> initialValue() {
            return new ArrayList();
        }
    };
    private static final TypeEvaluator sFloatEvaluator = new FloatEvaluator();
    private static long sFrameDelay = DEFAULT_FRAME_DELAY;
    private static final TypeEvaluator sIntEvaluator = new IntEvaluator();
    private static final ThreadLocal<ArrayList<ValueAnimator>> sPendingAnimations = new ThreadLocal<ArrayList<ValueAnimator>>() {
        protected ArrayList<ValueAnimator> initialValue() {
            return new ArrayList();
        }
    };
    private static final ThreadLocal<ArrayList<ValueAnimator>> sReadyAnims = new ThreadLocal<ArrayList<ValueAnimator>>() {
        protected ArrayList<ValueAnimator> initialValue() {
            return new ArrayList();
        }
    };
    private float mCurrentFraction = 0.0f;
    private int mCurrentIteration = 0;
    private long mDelayStartTime;
    private long mDuration = 300;
    boolean mInitialized = false;
    private Interpolator mInterpolator = sDefaultInterpolator;
    private boolean mPlayingBackwards = false;
    int mPlayingState = 0;
    private int mRepeatCount = 0;
    private int mRepeatMode = 1;
    private boolean mRunning = false;
    long mSeekTime = -1;
    private long mStartDelay = 0;
    long mStartTime;
    private boolean mStarted = false;
    private boolean mStartedDelay = false;
    private ArrayList<AnimatorUpdateListener> mUpdateListeners = null;
    PropertyValuesHolder[] mValues;
    HashMap<String, PropertyValuesHolder> mValuesMap;

    public interface AnimatorUpdateListener {
        void onAnimationUpdate(ValueAnimator valueAnimator);
    }

    private static class AnimationHandler extends Handler {
        private AnimationHandler() {
        }

        public void handleMessage(Message message) {
            ArrayList arrayList;
            Object obj;
            ArrayList arrayList2;
            int size;
            int i;
            ValueAnimator valueAnimator;
            ArrayList arrayList3 = (ArrayList) ValueAnimator.sAnimations.get();
            ArrayList arrayList4 = (ArrayList) ValueAnimator.sDelayedAnims.get();
            switch (message.what) {
                case 0:
                    arrayList = (ArrayList) ValueAnimator.sPendingAnimations.get();
                    if (arrayList3.size() > 0 || arrayList4.size() > 0) {
                        obj = null;
                    } else {
                        int i2 = 1;
                    }
                    while (arrayList.size() > 0) {
                        arrayList2 = (ArrayList) arrayList.clone();
                        arrayList.clear();
                        size = arrayList2.size();
                        for (i = 0; i < size; i++) {
                            valueAnimator = (ValueAnimator) arrayList2.get(i);
                            if (valueAnimator.mStartDelay == 0) {
                                valueAnimator.startAnimation();
                            } else {
                                arrayList4.add(valueAnimator);
                            }
                        }
                    }
                    break;
                case 1:
                    obj = 1;
                    break;
                default:
                    return;
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            arrayList = (ArrayList) ValueAnimator.sReadyAnims.get();
            arrayList2 = (ArrayList) ValueAnimator.sEndingAnims.get();
            size = arrayList4.size();
            for (i = 0; i < size; i++) {
                valueAnimator = (ValueAnimator) arrayList4.get(i);
                if (valueAnimator.delayedAnimationFrame(currentAnimationTimeMillis)) {
                    arrayList.add(valueAnimator);
                }
            }
            size = arrayList.size();
            if (size > 0) {
                for (i = 0; i < size; i++) {
                    valueAnimator = (ValueAnimator) arrayList.get(i);
                    valueAnimator.startAnimation();
                    valueAnimator.mRunning = true;
                    arrayList4.remove(valueAnimator);
                }
                arrayList.clear();
            }
            i = arrayList3.size();
            int i3 = 0;
            while (i3 < i) {
                int i4;
                ValueAnimator valueAnimator2 = (ValueAnimator) arrayList3.get(i3);
                if (valueAnimator2.animationFrame(currentAnimationTimeMillis)) {
                    arrayList2.add(valueAnimator2);
                }
                if (arrayList3.size() == i) {
                    i4 = i3 + 1;
                    i3 = i;
                } else {
                    i--;
                    arrayList2.remove(valueAnimator2);
                    i4 = i3;
                    i3 = i;
                }
                i = i3;
                i3 = i4;
            }
            if (arrayList2.size() > 0) {
                for (i3 = 0; i3 < arrayList2.size(); i3++) {
                    ((ValueAnimator) arrayList2.get(i3)).endAnimation();
                }
                arrayList2.clear();
            }
            if (obj == null) {
                return;
            }
            if (!arrayList3.isEmpty() || !arrayList4.isEmpty()) {
                sendEmptyMessageDelayed(1, Math.max(0, ValueAnimator.sFrameDelay - (AnimationUtils.currentAnimationTimeMillis() - currentAnimationTimeMillis)));
            }
        }
    }

    public static ValueAnimator ofInt(int... iArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(iArr);
        return valueAnimator;
    }

    public static ValueAnimator ofFloat(float... fArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(fArr);
        return valueAnimator;
    }

    public static ValueAnimator ofPropertyValuesHolder(PropertyValuesHolder... propertyValuesHolderArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setValues(propertyValuesHolderArr);
        return valueAnimator;
    }

    public static ValueAnimator ofObject(TypeEvaluator typeEvaluator, Object... objArr) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setObjectValues(objArr);
        valueAnimator.setEvaluator(typeEvaluator);
        return valueAnimator;
    }

    public void setIntValues(int... iArr) {
        if (iArr != null && iArr.length != 0) {
            if (this.mValues == null || this.mValues.length == 0) {
                setValues(PropertyValuesHolder.ofInt("", iArr));
            } else {
                this.mValues[0].setIntValues(iArr);
            }
            this.mInitialized = false;
        }
    }

    public void setFloatValues(float... fArr) {
        if (fArr != null && fArr.length != 0) {
            if (this.mValues == null || this.mValues.length == 0) {
                setValues(PropertyValuesHolder.ofFloat("", fArr));
            } else {
                this.mValues[0].setFloatValues(fArr);
            }
            this.mInitialized = false;
        }
    }

    public void setObjectValues(Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            if (this.mValues == null || this.mValues.length == 0) {
                setValues(PropertyValuesHolder.ofObject("", (TypeEvaluator) null, objArr));
            } else {
                this.mValues[0].setObjectValues(objArr);
            }
            this.mInitialized = false;
        }
    }

    public void setValues(PropertyValuesHolder... propertyValuesHolderArr) {
        this.mValues = propertyValuesHolderArr;
        this.mValuesMap = new HashMap(r2);
        for (PropertyValuesHolder propertyValuesHolder : propertyValuesHolderArr) {
            this.mValuesMap.put(propertyValuesHolder.getPropertyName(), propertyValuesHolder);
        }
        this.mInitialized = false;
    }

    public PropertyValuesHolder[] getValues() {
        return this.mValues;
    }

    void initAnimation() {
        if (!this.mInitialized) {
            for (PropertyValuesHolder init : this.mValues) {
                init.init();
            }
            this.mInitialized = true;
        }
    }

    public ValueAnimator setDuration(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.mDuration = j;
        return this;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public void setCurrentPlayTime(long j) {
        initAnimation();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.mPlayingState != 1) {
            this.mSeekTime = j;
            this.mPlayingState = 2;
        }
        this.mStartTime = currentAnimationTimeMillis - j;
        animationFrame(currentAnimationTimeMillis);
    }

    public long getCurrentPlayTime() {
        if (!this.mInitialized || this.mPlayingState == 0) {
            return 0;
        }
        return AnimationUtils.currentAnimationTimeMillis() - this.mStartTime;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public void setStartDelay(long j) {
        this.mStartDelay = j;
    }

    public static long getFrameDelay() {
        return sFrameDelay;
    }

    public static void setFrameDelay(long j) {
        sFrameDelay = j;
    }

    public Object getAnimatedValue() {
        if (this.mValues == null || this.mValues.length <= 0) {
            return null;
        }
        return this.mValues[0].getAnimatedValue();
    }

    public Object getAnimatedValue(String str) {
        PropertyValuesHolder propertyValuesHolder = (PropertyValuesHolder) this.mValuesMap.get(str);
        if (propertyValuesHolder != null) {
            return propertyValuesHolder.getAnimatedValue();
        }
        return null;
    }

    public void setRepeatCount(int i) {
        this.mRepeatCount = i;
    }

    public int getRepeatCount() {
        return this.mRepeatCount;
    }

    public void setRepeatMode(int i) {
        this.mRepeatMode = i;
    }

    public int getRepeatMode() {
        return this.mRepeatMode;
    }

    public void addUpdateListener(AnimatorUpdateListener animatorUpdateListener) {
        if (this.mUpdateListeners == null) {
            this.mUpdateListeners = new ArrayList();
        }
        this.mUpdateListeners.add(animatorUpdateListener);
    }

    public void removeAllUpdateListeners() {
        if (this.mUpdateListeners != null) {
            this.mUpdateListeners.clear();
            this.mUpdateListeners = null;
        }
    }

    public void removeUpdateListener(AnimatorUpdateListener animatorUpdateListener) {
        if (this.mUpdateListeners != null) {
            this.mUpdateListeners.remove(animatorUpdateListener);
            if (this.mUpdateListeners.size() == 0) {
                this.mUpdateListeners = null;
            }
        }
    }

    public void setInterpolator(Interpolator interpolator) {
        if (interpolator != null) {
            this.mInterpolator = interpolator;
        } else {
            this.mInterpolator = new LinearInterpolator();
        }
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    public void setEvaluator(TypeEvaluator typeEvaluator) {
        if (typeEvaluator != null && this.mValues != null && this.mValues.length > 0) {
            this.mValues[0].setEvaluator(typeEvaluator);
        }
    }

    private void start(boolean z) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.mPlayingBackwards = z;
        this.mCurrentIteration = 0;
        this.mPlayingState = 0;
        this.mStarted = true;
        this.mStartedDelay = false;
        ((ArrayList) sPendingAnimations.get()).add(this);
        if (this.mStartDelay == 0) {
            setCurrentPlayTime(getCurrentPlayTime());
            this.mPlayingState = 0;
            this.mRunning = true;
            if (this.mListeners != null) {
                ArrayList arrayList = (ArrayList) this.mListeners.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((AnimatorListener) arrayList.get(i)).onAnimationStart(this);
                }
            }
        }
        AnimationHandler animationHandler = (AnimationHandler) sAnimationHandler.get();
        if (animationHandler == null) {
            animationHandler = new AnimationHandler();
            sAnimationHandler.set(animationHandler);
        }
        animationHandler.sendEmptyMessage(0);
    }

    public void start() {
        start(false);
    }

    public void cancel() {
        if (this.mPlayingState != 0 || ((ArrayList) sPendingAnimations.get()).contains(this) || ((ArrayList) sDelayedAnims.get()).contains(this)) {
            if (this.mRunning && this.mListeners != null) {
                Iterator it = ((ArrayList) this.mListeners.clone()).iterator();
                while (it.hasNext()) {
                    ((AnimatorListener) it.next()).onAnimationCancel(this);
                }
            }
            endAnimation();
        }
    }

    public void end() {
        if (!((ArrayList) sAnimations.get()).contains(this) && !((ArrayList) sPendingAnimations.get()).contains(this)) {
            this.mStartedDelay = false;
            startAnimation();
        } else if (!this.mInitialized) {
            initAnimation();
        }
        if (this.mRepeatCount <= 0 || (this.mRepeatCount & 1) != 1) {
            animateValue(1.0f);
        } else {
            animateValue(0.0f);
        }
        endAnimation();
    }

    public boolean isRunning() {
        return this.mPlayingState == 1 || this.mRunning;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    public void reverse() {
        this.mPlayingBackwards = !this.mPlayingBackwards;
        if (this.mPlayingState == 1) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.mStartTime = currentAnimationTimeMillis - (this.mDuration - (currentAnimationTimeMillis - this.mStartTime));
            return;
        }
        start(true);
    }

    private void endAnimation() {
        ((ArrayList) sAnimations.get()).remove(this);
        ((ArrayList) sPendingAnimations.get()).remove(this);
        ((ArrayList) sDelayedAnims.get()).remove(this);
        this.mPlayingState = 0;
        if (this.mRunning && this.mListeners != null) {
            ArrayList arrayList = (ArrayList) this.mListeners.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((AnimatorListener) arrayList.get(i)).onAnimationEnd(this);
            }
        }
        this.mRunning = false;
        this.mStarted = false;
    }

    private void startAnimation() {
        initAnimation();
        ((ArrayList) sAnimations.get()).add(this);
        if (this.mStartDelay > 0 && this.mListeners != null) {
            ArrayList arrayList = (ArrayList) this.mListeners.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((AnimatorListener) arrayList.get(i)).onAnimationStart(this);
            }
        }
    }

    private boolean delayedAnimationFrame(long j) {
        if (this.mStartedDelay) {
            long j2 = j - this.mDelayStartTime;
            if (j2 > this.mStartDelay) {
                this.mStartTime = j - (j2 - this.mStartDelay);
                this.mPlayingState = 1;
                return true;
            }
        }
        this.mStartedDelay = true;
        this.mDelayStartTime = j;
        return false;
    }

    boolean animationFrame(long j) {
        boolean z = false;
        if (this.mPlayingState == 0) {
            this.mPlayingState = 1;
            if (this.mSeekTime < 0) {
                this.mStartTime = j;
            } else {
                this.mStartTime = j - this.mSeekTime;
                this.mSeekTime = -1;
            }
        }
        switch (this.mPlayingState) {
            case 1:
            case 2:
                float f;
                float f2 = this.mDuration > 0 ? ((float) (j - this.mStartTime)) / ((float) this.mDuration) : 1.0f;
                if (f2 < 1.0f) {
                    f = f2;
                } else if (this.mCurrentIteration < this.mRepeatCount || this.mRepeatCount == -1) {
                    if (this.mListeners != null) {
                        int size = this.mListeners.size();
                        for (int i = 0; i < size; i++) {
                            ((AnimatorListener) this.mListeners.get(i)).onAnimationRepeat(this);
                        }
                    }
                    if (this.mRepeatMode == 2) {
                        this.mPlayingBackwards = !this.mPlayingBackwards;
                    }
                    this.mCurrentIteration += (int) f2;
                    f = f2 % 1.0f;
                    this.mStartTime += this.mDuration;
                } else {
                    f = Math.min(f2, 1.0f);
                    z = true;
                }
                if (this.mPlayingBackwards) {
                    f = 1.0f - f;
                }
                animateValue(f);
                break;
        }
        return z;
    }

    public float getAnimatedFraction() {
        return this.mCurrentFraction;
    }

    void animateValue(float f) {
        int i;
        float interpolation = this.mInterpolator.getInterpolation(f);
        this.mCurrentFraction = interpolation;
        for (PropertyValuesHolder calculateValue : this.mValues) {
            calculateValue.calculateValue(interpolation);
        }
        if (this.mUpdateListeners != null) {
            int size = this.mUpdateListeners.size();
            for (i = 0; i < size; i++) {
                ((AnimatorUpdateListener) this.mUpdateListeners.get(i)).onAnimationUpdate(this);
            }
        }
    }

    public ValueAnimator clone() {
        int i = 0;
        ValueAnimator valueAnimator = (ValueAnimator) super.clone();
        if (this.mUpdateListeners != null) {
            ArrayList arrayList = this.mUpdateListeners;
            valueAnimator.mUpdateListeners = new ArrayList();
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                valueAnimator.mUpdateListeners.add(arrayList.get(i2));
            }
        }
        valueAnimator.mSeekTime = -1;
        valueAnimator.mPlayingBackwards = false;
        valueAnimator.mCurrentIteration = 0;
        valueAnimator.mInitialized = false;
        valueAnimator.mPlayingState = 0;
        valueAnimator.mStartedDelay = false;
        PropertyValuesHolder[] propertyValuesHolderArr = this.mValues;
        if (propertyValuesHolderArr != null) {
            int length = propertyValuesHolderArr.length;
            valueAnimator.mValues = new PropertyValuesHolder[length];
            valueAnimator.mValuesMap = new HashMap(length);
            while (i < length) {
                PropertyValuesHolder clone = propertyValuesHolderArr[i].clone();
                valueAnimator.mValues[i] = clone;
                valueAnimator.mValuesMap.put(clone.getPropertyName(), clone);
                i++;
            }
        }
        return valueAnimator;
    }

    public static int getCurrentAnimationsCount() {
        return ((ArrayList) sAnimations.get()).size();
    }

    public static void clearAllAnimations() {
        ((ArrayList) sAnimations.get()).clear();
        ((ArrayList) sPendingAnimations.get()).clear();
        ((ArrayList) sDelayedAnims.get()).clear();
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.mValues != null) {
            for (PropertyValuesHolder propertyValuesHolder : this.mValues) {
                str = str + "\n    " + propertyValuesHolder.toString();
            }
        }
        return str;
    }
}
