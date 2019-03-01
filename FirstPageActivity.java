package com.omninos.zeemail.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.omninos.zeemail.Model_Classes.Maildemo;
import com.omninos.zeemail.R;
import com.omninos.zeemail.Util.CommonUtil;
import com.omninos.zeemail.fragments.ConnectedApps;
import com.omninos.zeemail.fragments.SendLaterFragment;
import com.omninos.zeemail.fragments.SnoozeFragment;
import com.omninos.zeemail.fragments.ZeeMailFragment;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FirstPageActivity extends FragmentActivity {

    public static final String TAG="FirstPage";

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    private static final int NUM_PAGES = 4;

    private TextView lastText;
    private ViewPager mPager;
    LinearLayout dots;

    private Button login, register;
    private PagerAdapter mPagerAdapter;


    //googleIntegration
    GoogleSignInClient mgoogleSignInClient;
    int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        
       dots = findViewById(R.id.dots);

        lastText = findViewById(R.id.belowtextData);
        lastText.setText("Try ZeeMail in all its glory, free for 14 days,\n" +
                "on all your device.No string attached.\n" +
                "Subscribe ony if you wish to continue.");

        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });

        addDotsIndicator(0);
        mPager = (ViewPager) findViewById(R.id.viewPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                addDotsIndicator(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

//        CommonUtil.Mail m = new Mail("example123@gmail.com", "password");
//
//// get inbox
//        m.getInbox();



    }

    private void RegisterUser() {
//        startActivity(new Intent(FirstPageActivity.this, RegisterActivity.class));
        //finishAffinity();
        startActivity(new Intent(FirstPageActivity.this,AddEmailAccountActivity.class));

        finishAffinity();

    }

    private void LoginUser() {
        startActivity(new Intent(FirstPageActivity.this,LoginActivity.class));

         finishAffinity();

//        GoogleIntegration();

    }



    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:

                    Log.d("getItem: ", String.valueOf(i));
                    return new ZeeMailFragment();

                case 1:
//
                    Log.d("getItem: ", String.valueOf(i));
                    return new ConnectedApps();

                case 2:
//
                    Log.d("getItem: ", String.valueOf(i));
                    return new SendLaterFragment();

                case 3:
//
                    Log.d("getItem: ", String.valueOf(i));
                    return new SnoozeFragment();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private void addDotsIndicator(int position) {

        TextView[] tv_dots = new TextView[4];
        dots.removeAllViews();
        for (int i = 0; i < tv_dots.length; i++) {
            tv_dots[i] = new TextView(getApplicationContext());
            tv_dots[i].setText(Html.fromHtml("•"));
            tv_dots[i].setTextSize(35);
            tv_dots[i].setTextColor(getResources().getColor(R.color.gray));
            dots.addView(tv_dots[i]);
        }

        tv_dots[position].setTextColor(getResources().getColor(R.color.white));
    }

}
