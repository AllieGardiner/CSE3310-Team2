package com.example.team2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_about_us);
    }

    public void openUrl(String url)
    {
        Uri uri = Uri.parse(url);
        Intent launchWeb = new Intent(Intent.ACTION_VIEW);
        launchWeb.setData(uri);
        startActivity(launchWeb);
    }
    public void facebook_link(View view){
        openUrl("https://www.facebook.com/lcsportsandrec");
    }
    public void instagram_link(View view){
        openUrl("https://www.instagram.com/lcsportsandrec/");
    }
    public void twitter_link(View view){
        openUrl("https://twitter.com/rec_lc");
    }
    public void youtube_link(View view){
        openUrl("https://www.youtube.com/channel/UCethy1Te3BKGVEFwItnRNYA");
    }
}