package com.sample.gaoqingyu.criminalitent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by GaoqingYu on 2016/4/17.
 */
public class CrimePagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mCrimes = CrimeLab.get(this).getmCrimes();

        FragmentManager fm = getSupportFragmentManager();
        // 定制预加载相邻页面的数目，默认为1
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }
        });
//        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
//
//
//            @Override
//            public Fragment getItem(int position) {
//               Crime crime = mCrimes.get(position);
//                return CrimeFragment.newInstance(crime.getmId());
//            }
//
//            @Override
//            public int getCount() {
//                return mCrimes.size();
//            }
//        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Crime crime = mCrimes.get(position);
                if ( crime.getmId() != null) {
                    setTitle(crime.getmTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        UUID uuid = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getmId().equals(uuid)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
