package com.commit451.scriminsetslayout.sample;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.commit451.scriminsetslayout.OnInsetsCallback;
import com.commit451.scriminsetslayout.ScrimInsetsFrameLayout;


public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        setContentView(R.layout.activity_toolbar);

        final AppBarLayout appBarLayout = findViewById(R.id.appbar);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Wow");

        ScrimInsetsFrameLayout scrimInsetsFrameLayout = findViewById(R.id.root_insets);
        scrimInsetsFrameLayout.setOnInsetsCallback(new OnInsetsCallback() {
            @Override
            public void onInsetsChanged(Rect insets) {
                appBarLayout.setPadding(0, insets.top, 0, 0);
            }
        });
    }
}
