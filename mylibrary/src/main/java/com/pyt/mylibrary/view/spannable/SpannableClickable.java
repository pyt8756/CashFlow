package com.pyt.mylibrary.view.spannable;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.pyt.mylibrary.R;
import com.pyt.mylibrary.utils.ResourcesUtils;


public abstract class SpannableClickable extends ClickableSpan implements View.OnClickListener {

    private static final int DEFAULT_COLOR_ID = R.color.bu_a;
    /**
     * text颜色
     */
    private int textColor;

    public SpannableClickable() {
        this.textColor = ResourcesUtils.getColor(DEFAULT_COLOR_ID);
    }

    public SpannableClickable(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(textColor);
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}
