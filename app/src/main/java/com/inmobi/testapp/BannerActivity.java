package com.inmobi.testapp;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.inmobi.ads.AdMetaInfo;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.listeners.BannerAdEventListener;

import java.util.Map;

public class BannerActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private InMobiBanner mBannerAd;
    EditText inputAdTagEditText;
    View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        mBannerAd = new InMobiBanner(BannerActivity.this, 1616919797552L);
        RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.ad_container);
        mBannerAd.setAnimationType(InMobiBanner.AnimationType.ROTATE_HORIZONTAL_AXIS);
        mBannerAd.setListener(new BannerAdEventListener() {
            @Override
            public void onAdLoadSucceeded(@NonNull InMobiBanner inMobiBanner,
                                          @NonNull AdMetaInfo adMetaInfo) {
                Log.d(TAG, "onAdLoadSucceeded with bid " + adMetaInfo.getBid());
            }

            @Override
            public void onAdLoadFailed(@NonNull InMobiBanner inMobiBanner, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                Log.d(TAG, "Banner ad failed to load with error: " +
                        inMobiAdRequestStatus.getMessage());
            }

            @Override
            public void onAdClicked(@NonNull InMobiBanner inMobiBanner, @NonNull Map<Object, Object> map) {
                Log.d(TAG, "onAdClicked");
            }

            @Override
            public void onAdDisplayed(@NonNull InMobiBanner inMobiBanner) {
                Log.d(TAG, "onAdDisplayed");
            }

            @Override
            public void onAdDismissed(@NonNull InMobiBanner inMobiBanner) {
                Log.d(TAG, "onAdDismissed");
            }

            @Override
            public void onUserLeftApplication(@NonNull InMobiBanner inMobiBanner) {
                Log.d(TAG, "onUserLeftApplication");
            }

            @Override
            public void onRewardsUnlocked(@NonNull InMobiBanner inMobiBanner, @NonNull Map<Object, Object> map) {
                Log.d(TAG, "onRewardsUnlocked");
            }

            @Override
            public void onAdImpression(@NonNull InMobiBanner inMobiBanner) {
                Log.d(TAG, "onAdImpression");
            }

        });
        setBannerLayoutParams();
        adContainer.addView(mBannerAd);
        mBannerAd.load();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setBannerLayoutParams() {
        int width = toPixelUnits(320);
        int height = toPixelUnits(50);
        RelativeLayout.LayoutParams bannerLayoutParams = new RelativeLayout.LayoutParams(width, height);
        bannerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bannerLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        mBannerAd.setLayoutParams(bannerLayoutParams);
    }
    private int toPixelUnits(int dipUnit) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dipUnit * density);
    }
}
