package com.example.zingmp3.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrayFragmemt=new ArrayList<>();//dung de add vao cac fragmemt
    private ArrayList<String> arrayTitle = new ArrayList<>();  //dung de luu title cua moi fragmemt o tren
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayFragmemt.get(position);
    }

    @Override
    public int getCount() {
        return arrayFragmemt.size();
    }

    public void addFragment(Fragment fragment, String title){
        arrayFragmemt.add(fragment);
        arrayTitle.add(title);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
}
