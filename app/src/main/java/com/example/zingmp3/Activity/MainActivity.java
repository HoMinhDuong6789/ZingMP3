package com.example.zingmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.zingmp3.Adapter.MainViewPagerAdapter;
import com.example.zingmp3.Fragment.Fragment_TimKiem;
import com.example.zingmp3.Fragment.Fragment_TrangChu;
import com.example.zingmp3.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
        
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter= new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_TrangChu(), "Home Page");
        mainViewPagerAdapter.addFragment(new Fragment_TimKiem(), "Search Page");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);

    }

    private void anhxa() {
        tabLayout= findViewById(R.id.mytabLayout);
        viewPager= findViewById(R.id.myViewPager);

    }
}
