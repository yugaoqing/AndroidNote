package com.sample.gaoqingyu.criminalitent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by GaoqingYu on 2016/4/16.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mAPPContext;
    private ArrayList<Crime> mCrimes;

    public CrimeLab(Context mAPPContext) {
        this.mAPPContext = mAPPContext;
        this.mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setmTitle("Crime # " + i);
            c.setmSolved(i % 2 == 0);
            mCrimes.add(c);
        }
    }

    public ArrayList<Crime> getmCrimes() {
        return mCrimes;
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context.getApplicationContext());
        }
        return sCrimeLab;
    }

    public Crime getCrime(UUID id) {
        for (Crime c:mCrimes) {
            if (c.getmId().equals(id)) {
                return c;
            }
        }
        return null;
    }
}
