package com.commit451.scriminsetslayout.sample;

import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.commit451.scriminsetslayout.OnInsetsCallback;
import com.commit451.scriminsetslayout.ScrimInsetsFrameLayout;


public class ScrollAndNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        );
        setContentView(R.layout.activity_scroll_and_navigation);

        final NestedScrollView nestedScrollView = findViewById(R.id.scrollview);

        final AppBarLayout appBarLayout = findViewById(R.id.appbar);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Wow");

        ScrimInsetsFrameLayout scrimInsetsFrameLayout = findViewById(R.id.root_insets);
        scrimInsetsFrameLayout.setOnInsetsCallback(new OnInsetsCallback() {
            @Override
            public void onInsetsChanged(Rect insets) {
                appBarLayout.setPadding(0, insets.top, 0, 0);
                nestedScrollView.setPadding(0, 0, 0, insets.bottom);
            }
        });
    }
}
