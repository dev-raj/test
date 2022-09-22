package com.inmobi.testapp;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.inmobi.ads.AdMetaInfo;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.listeners.BannerAdEventListener;
import com.inmobi.sdk.InMobiSdk;
import com.inmobi.sdk.SdkInitializationListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button buttonI;
    Button buttonM;
    private static final String TAG = MainActivity.class.getName();
    //    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InMobiSdk.setLogLevel(InMobiSdk.LogLevel.DEBUG);
        JSONObject consent = new JSONObject();
        try {
            // Provide correct consent value to sdk which is obtained by User
            consent.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        InMobiSdk.init(this, "3860d711eca24ea9beb08fde67f6d4b7", consent, new SdkInitializationListener() {
            @Override
            public void onInitializationComplete(@Nullable Error error) {
                if (error == null) {
                    Log.d(TAG, "InMobi SDK Initialization Success");
                    //sdkInitSuccess();
                } else {
                    Log.e(TAG, "InMobi SDK Initialization failed: " + error.getMessage());
                    //sdkInitFailed();
                }
            }
        });


        button = (Button) findViewById(R.id.Banner);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
        buttonI= (Button) findViewById(R.id.interstitial);
        buttonI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                openNewInterstitialActivity();
            }
        });
        buttonM= (Button) findViewById(R.id.mrec);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                openNewMrecActivity();
            }
        });
    }
    public void openNewActivity() {
        Intent intent = new Intent(this, BannerActivity.class);
        startActivity(intent);
    }
    public void openNewInterstitialActivity() {
        Intent intent = new Intent(this, InterstitialActivity.class);
        startActivity(intent);
    }
    public void openNewMrecActivity() {
        Intent intent = new Intent(this, MrecActivity.class);
        startActivity(intent);
    }
}
