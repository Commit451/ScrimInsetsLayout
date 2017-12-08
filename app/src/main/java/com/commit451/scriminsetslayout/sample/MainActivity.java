package com.commit451.scriminsetslayout.sample;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.commit451.scriminsetslayout.OnInsetsCallback;
import com.commit451.scriminsetslayout.ScrimInsetsFrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Cool");

        final ViewGroup rootContent = findViewById(R.id.root_content);
        ScrimInsetsFrameLayout scrimInsetsFrameLayout = findViewById(R.id.root_insets);
        scrimInsetsFrameLayout.setOnInsetsCallback(new OnInsetsCallback() {
            @Override
            public void onInsetsChanged(Rect insets) {
                rootContent.setPadding(0, insets.top, 0, insets.bottom);
            }
        });

        findViewById(R.id.button_extended_appbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ToolbarActivity.class));
            }
        });

        findViewById(R.id.button_extended_scrollview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScrollAndNavigationActivity.class));
            }
        });
    }
}
