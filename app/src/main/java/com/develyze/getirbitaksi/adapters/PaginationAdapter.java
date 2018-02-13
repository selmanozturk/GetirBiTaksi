package com.develyze.getirbitaksi.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;


import com.develyze.getirbitaksi.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created selman | 08.12.2017.
 */

public class PaginationAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private FragmentTransaction mCurTransaction = null;
    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();



    public PaginationAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return mFragmentTitleList.get(position);
        //return null to show only icons
        return null;
    }
    @Override
    public void destroyItem(ViewGroup collection, int position, Object view){
        mCurTransaction = fm.beginTransaction();
        if (mCurTransaction == null) {
            mCurTransaction = fm.beginTransaction();
        }
        mCurTransaction.detach((Fragment)view);
    }
}
