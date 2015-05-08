package com.aoty.matinalnew.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aoty.matinalnew.api.MatinalApi;
import com.aoty.matinalnew.fragment.NewFragment;
import com.aoty.matinalnew.model.Category;

/**
 * Created by AotY on 2014/12/24.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private final String[] titles = {"头条", "体育", "科技", "财经", "娱乐", "贵阳"};

    /*
    各个页面的Fragment
     */
    private NewFragment headlineFragment;
    private NewFragment sportFragment;
    private NewFragment technologyFragment;
    private NewFragment economicFragment;
    private NewFragment entertainmentFragment;
    private NewFragment guiYnagFragment;


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (headlineFragment == null)
                    headlineFragment = new NewFragment(Category.headline, MatinalApi.GETHEADLINEURL);
                return headlineFragment;
            case 1:
                if (sportFragment == null)
                    sportFragment = new NewFragment(Category.sport, MatinalApi.GETSPORTURL);
                return sportFragment;
            case 2:
                if (technologyFragment == null)
                    technologyFragment = new NewFragment(Category.technology, MatinalApi.GETTECHNOLOGYURL);
                return technologyFragment;
            case 3:
                if (economicFragment == null)
                    economicFragment = new NewFragment(Category.economic, MatinalApi.GETECONOMICURL);
                return economicFragment;
            case 4:
                if (entertainmentFragment == null)
                    entertainmentFragment = new NewFragment(Category.entertainment, MatinalApi.GETENTERTAINMENTURL);
                return entertainmentFragment;
            case 5:
                if (guiYnagFragment == null)
                    guiYnagFragment = new NewFragment(Category.guiyang, MatinalApi.GETGUIYANGURL);
                return guiYnagFragment;
        }
        return headlineFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

//    private void initData() {
//        headlineFragment = new NewFragment(Category.headline, MatinalApi.GETHEADLINEURL);
//        fragments.add(headlineFragment);
//        sportFragment = new NewFragment(Category.sport, MatinalApi.GETSPORTURL);
//        fragments.add(sportFragment);
//        technologyFragment = new NewFragment(Category.technology, MatinalApi.GETTECHNOLOGYURL);
//        fragments.add(technologyFragment);
//        economicFragment = new NewFragment(Category.economic, MatinalApi.GETECONOMICURL);
//        fragments.add(economicFragment);
//        entertainmentFragment = new NewFragment(Category.entertainment, MatinalApi.GETENTERTAINMENTURL);
//        fragments.add(entertainmentFragment);
//        guiYnagFragment = new NewFragment(Category.guiyang, MatinalApi.GETGUIYANGURL);
//        fragments.add(guiYnagFragment);
//    }
}
