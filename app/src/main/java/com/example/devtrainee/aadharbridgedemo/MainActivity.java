package com.example.devtrainee.aadharbridgedemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager= findViewById(R.id.viewpager);
        if(viewPager!=null){
            setUpViewPager(viewPager);
        }

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    private void setUpViewPager(ViewPager viewPager) {

        CustomPagerAdapter customPagerAdapter= new CustomPagerAdapter(getSupportFragmentManager());
        customPagerAdapter.addFragment(new DemographicFragment(), "Demographic");
        viewPager.setAdapter(customPagerAdapter);

    }
}
