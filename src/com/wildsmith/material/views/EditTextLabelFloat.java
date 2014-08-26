package com.wildsmith.material.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

import com.wildsmith.material.R;
import com.wildsmith.material.utils.StringUtils;

public class EditTextLabelFloat extends EditText {

    private static final int ANIMATION_STEPS = 6;

    private enum Animation {
        BECOME_LABEL, BECOME_HINT, NONE
    }

    private Paint mFloatingHintPaint;

    private Animation mAnimation;

    private ColorStateList mHintColors;

    private float mHintScale;

    private int mAnimationFrame;

    private boolean mWasEmpty;

    public EditTextLabelFloat(Context context, AttributeSet attrs) {
        super(context, attrs);

        mFloatingHintPaint = new Paint();
        mAnimation = Animation.NONE;
        mHintColors = getHintTextColors();
        mWasEmpty = true;

        mFloatingHintPaint.set(getPaint());
        mFloatingHintPaint.setColor(mHintColors.getColorForState(getDrawableState(), mHintColors.getDefaultColor()));

        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.floatinghintedittext_hint_scale, typedValue, true);
        mHintScale = typedValue.getFloat();
    }

    @Override
    public int getCompoundPaddingTop() {
        final FontMetricsInt metrics = getPaint().getFontMetricsInt();
        final int floatingHintHeight = (int) ((metrics.bottom - metrics.top) * mHintScale);
        return super.getCompoundPaddingTop() + floatingHintHeight;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if (isShown() == false || getText() == null) {
            return;
        }

        if (getHint() == null || StringUtils.isEmpty(getHint().toString())) {
            return;
        }

        boolean isEmpty = StringUtils.isEmpty(getText().toString());
        if (isEmpty == mWasEmpty) {
            return;
        }

        mWasEmpty = isEmpty;

        if (isEmpty) {
            mAnimation = Animation.BECOME_HINT;
            setHintTextColor(Color.TRANSPARENT);
        } else {
            mAnimation = Animation.BECOME_LABEL;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mAnimation == Animation.NONE && getText() != null && StringUtils.isEmpty(getText().toString())) {
            return;
        }

        final float hintPosX = getCompoundPaddingLeft() + getScrollX();
        final float normalHintPosY = getBaseline();
        final float floatingHintPosY = normalHintPosY + getPaint().getFontMetricsInt().top + getScrollY();
        final float normalHintSize = getTextSize();
        final float floatingHintSize = normalHintSize * mHintScale;

        switch (mAnimation) {
            case BECOME_HINT:
                onDrawHintTransition(canvas, floatingHintSize, normalHintSize, floatingHintPosY, normalHintPosY, hintPosX);
                break;
            case BECOME_LABEL:
                onDrawHintTransition(canvas, normalHintSize, floatingHintSize, normalHintPosY, floatingHintPosY, hintPosX);
                break;
            case NONE:
                onDrawLabelOrHint(canvas, floatingHintSize, floatingHintPosY, hintPosX);
                break;
            default:
                break;
        }
    }

    private void onDrawHintTransition(Canvas canvas, float fromSize, float toSize, float fromY, float toY, float hintPosX) {
        final float textSize = lerp(fromSize, toSize);
        final float hintPosY = lerp(fromY, toY);
        mFloatingHintPaint.setTextSize(textSize);
        canvas.drawText(getHint().toString(), hintPosX, hintPosY, mFloatingHintPaint);

        updateAnimationFrame();
        invalidate();
    }

    private void onDrawLabelOrHint(Canvas canvas, float floatingHintSize, float floatingHintPosY, float hintPosX) {
        mFloatingHintPaint.setTextSize(floatingHintSize);
        canvas.drawText(getHint().toString(), hintPosX, floatingHintPosY, mFloatingHintPaint);
        return;
    }

    private float lerp(float from, float to) {
        final float alpha = (float) mAnimationFrame / (ANIMATION_STEPS - 1);
        return from * (1 - alpha) + to * alpha;
    }

    private void updateAnimationFrame() {
        mAnimationFrame++;

        if (mAnimationFrame == ANIMATION_STEPS) {
            if (mAnimation == Animation.BECOME_HINT) {
                setHintTextColor(mHintColors);
            }
            mAnimation = Animation.NONE;
            mAnimationFrame = 0;
        }
    }
}