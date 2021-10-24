package com.example.evernote;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.evernote.userentity.Account;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity  implements SignupTabFragment.SendMessage{

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton google,apple;

    float vel = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       //this.getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        google = findViewById(R.id.fab_google);
        apple = findViewById(R.id.fab_apple);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Animation
        google.setTranslationY(300);
        apple.setTranslationY(300);
        tabLayout.setTranslationY(300);

        google.setAlpha(vel);
        apple.setAlpha(vel);
        tabLayout.setAlpha(vel);

        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        apple.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(300).start();



    }

    @Override
    public void sendData(Account message) {
        String tag = "android:switcher:" + R.id.view_pager + ":" + 0;
        LoginTabFragment f = (LoginTabFragment) getSupportFragmentManager().findFragmentByTag(tag);
        f.DisplayData(message);
        viewPager.setCurrentItem(0);
    }
}
