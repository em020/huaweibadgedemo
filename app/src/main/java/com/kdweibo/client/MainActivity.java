package com.kdweibo.client;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int foo;
    int baz = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** set badge number*/
    public void setBadgeNum(int num){
        try{
            Bundle bunlde =new Bundle();
            bunlde.putString("package", getPackageName()); // com.test.badge is your package name
            bunlde.putString("class", "com.kdweibo.client.MainActivity"); // com.test. badge.MainActivity is your apk main activity
            bunlde.putInt("badgenumber", num);
            getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bunlde);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setBadgeNumWrongWay(int num) {
        Bundle localBundle = new Bundle();
        localBundle.putStringArrayList("app_shortcut_custom_id", null);
        localBundle.putInt("app_badge_count", num);
        Uri localUri = Uri.parse("content://com.android.badge/badge");
        try {
            getContentResolver().call(localUri, "setAppBadgeCount", null, localBundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickCorrectWay(View view) {
        setBadgeNum(++foo);
    }

    public void onClickEvilWay(View view) {
        setBadgeNumWrongWay(++baz);
    }
}
