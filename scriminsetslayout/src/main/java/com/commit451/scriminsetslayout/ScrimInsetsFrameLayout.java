/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.commit451.scriminsetslayout;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * ScrimInsetsFrameLayout copied from the Design support library
 */
public class ScrimInsetsFrameLayout extends FrameLayout {

    private Drawable insetForeground;

    private Rect insets;

    private Rect tempRect = new Rect();

    private OnInsetsCallback onInsetsCallback;

    public ScrimInsetsFrameLayout(Context context) {
        this(context, null);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ScrimInsetsFrameLayout, defStyleAttr,
                R.style.Widget_Design_ScrimInsetsFrameLayout);
        insetForeground = a.getDrawable(R.styleable.ScrimInsetsFrameLayout_insetForeground);
        a.recycle();
        setWillNotDraw(true); // No need to draw until the insets are adjusted

        ViewCompat.setOnApplyWindowInsetsListener(this,
                new androidx.core.view.OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(View v,
                                                                  WindowInsetsCompat insets) {
                        if (null == ScrimInsetsFrameLayout.this.insets) {
                            ScrimInsetsFrameLayout.this.insets = new Rect();
                        }
                        ScrimInsetsFrameLayout.this.insets.set(insets.getSystemWindowInsetLeft(),
                                insets.getSystemWindowInsetTop(),
                                insets.getSystemWindowInsetRight(),
                                insets.getSystemWindowInsetBottom());
                        onInsetsChanged(insets);
                        if (onInsetsCallback != null) {
                            onInsetsCallback.onInsetsChanged(ScrimInsetsFrameLayout.this.insets);
                        }
                        setWillNotDraw(!insets.hasSystemWindowInsets() || insetForeground == null);
                        ViewCompat.postInvalidateOnAnimation(ScrimInsetsFrameLayout.this);
                        return insets.consumeSystemWindowInsets();
                    }
                });
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);

        int width = getWidth();
        int height = getHeight();
        if (insets != null && insetForeground != null) {
            int sc = canvas.save();
            canvas.translate(getScrollX(), getScrollY());

            // Top
            tempRect.set(0, 0, width, insets.top);
            insetForeground.setBounds(tempRect);
            insetForeground.draw(canvas);

            // Bottom
            tempRect.set(0, height - insets.bottom, width, height);
            insetForeground.setBounds(tempRect);
            insetForeground.draw(canvas);

            // Left
            tempRect.set(0, insets.top, insets.left, height - insets.bottom);
            insetForeground.setBounds(tempRect);
            insetForeground.draw(canvas);

            // Right
            tempRect.set(width - insets.right, insets.top, width, height - insets.bottom);
            insetForeground.setBounds(tempRect);
            insetForeground.draw(canvas);

            canvas.restoreToCount(sc);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (insetForeground != null) {
            insetForeground.setCallback(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (insetForeground != null) {
            insetForeground.setCallback(null);
        }
    }

    /**
     * Override to provide your own behavior when insets change.
     * @param insets insets
     */
    @SuppressWarnings("unused")
    protected void onInsetsChanged(WindowInsetsCompat insets) {
    }

    /**
     * Allows the calling container to specify a callback for custom processing when insets change (i.e. when
     * {@link #fitSystemWindows(Rect)} is called. This is useful for setting padding on UI elements based on
     * UI chrome insets (e.g. a Google Map or a ListView). When using with ListView or GridView, remember to set
     * clipToPadding to false.
     */
    public void setOnInsetsCallback(OnInsetsCallback onInsetsCallback) {
        this.onInsetsCallback = onInsetsCallback;
    }
}
