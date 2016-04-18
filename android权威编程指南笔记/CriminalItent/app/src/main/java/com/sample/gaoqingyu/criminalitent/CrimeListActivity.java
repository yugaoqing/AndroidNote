package com.sample.gaoqingyu.criminalitent;

import android.support.v4.app.Fragment;

/**
 * Created by GaoqingYu on 2016/4/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}
