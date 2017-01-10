package com.commit451.scriminsetslayout;

import android.graphics.Rect;

/**
 * Allows the calling container to specify a callback for custom processing when insets change (i.e. when
 * {@link android.view.View#fitSystemWindows(Rect)} is called. This is useful for setting padding on UI elements based on
 * UI chrome insets (e.g. a Google Map or a ListView). When using with ListView or GridView, remember to set
 * clipToPadding to false.
 */
public interface OnInsetsCallback {
    void onInsetsChanged(Rect insets);
}
