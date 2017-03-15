# ScrimInsetsLayout
ScrimInsetsFrameLayout from the design support library

[![Build Status](https://travis-ci.org/Commit451/ScrimInsetsLayout.svg?branch=master)](https://travis-ci.org/Commit451/ScrimInsetsLayout) [![](https://jitpack.io/v/Commit451/ScrimInsetsLayout.svg)](https://jitpack.io/#Commit451/ScrimInsetsLayout)

# Usage
If you want to do the typical thing where a view is pushed below the status bar:

First, you need to modify the style of the activity:
```xml
<style name="Activity" parent="AppTheme">
    <item name="android:statusBarColor" tools:targetApi="lollipop">@android:color/transparent</item>
</style>
```
Then, within your activity:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    setContentView(R.layout.activity_main);

    //All the content you want to be below the status bar
    final ViewGroup rootContent = (ViewGroup) findViewById(R.id.root_content);
    ScrimInsetsFrameLayout scrimInsetsFrameLayout = (ScrimInsetsFrameLayout) findViewById(R.id.root_insets);
    scrimInsetsFrameLayout.setOnInsetsCallback(new OnInsetsCallback() {
        @Override
        public void onInsetsChanged(Rect insets) {
            rootContent.setPadding(0, insets.top, 0, 0);
        }
    });
}
```
See the sample for a full example.

License
--------

    Copyright 2017 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
