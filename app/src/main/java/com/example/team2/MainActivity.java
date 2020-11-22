package com.example.team2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Logoutbutton).setOnClickListener(onclickListner);

        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            startSignActivity();
        }
        TabLayout tabLayout = findViewById(R.id.tabBar);
        TabItem tabTraining = findViewById(R.id.Trainings);
        TabItem tabHealth = findViewById(R.id.Health);
        TabItem tabChildren = findViewById(R.id.Children);
        TabItem tabRental = findViewById(R.id.Calendar);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        //this function changes the tabs view when the tab is selected
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    public void openUrl(String url)
    {
        Uri uri = Uri.parse(url);
        Intent launchWeb = new Intent(Intent.ACTION_VIEW);
        launchWeb.setData(uri);
        startActivity(launchWeb);
    }
    public void Sports_Training_Button(View view){
        openUrl("https://docs.google.com/forms/d/e/1FAIpQLSfpPlcTnK2_YvMS_YeYQOE6YnSHabxRk-YaGPNDTT5nWFCxCw/viewform");
    }
    public void Basketball_Reg(View view){
        openUrl("https://oneworldsa.leagueapps.com/leagues/basketball/1911049-lc-basketball-league");
    }
    public void Soccer_Reg(View view){
        openUrl("https://oneworldsa.leagueapps.com/classes/1862566-soccer-sessions-tuesday-nights-nov---dec");
    }

    private void startSignActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    View.OnClickListener onclickListner = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.Logoutbutton:
                    FirebaseAuth.getInstance().signOut();
                    startSignActivity();
                    break;
            }
        }
    };

}