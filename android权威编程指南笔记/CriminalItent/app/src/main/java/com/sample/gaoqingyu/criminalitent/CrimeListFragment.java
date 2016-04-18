package com.sample.gaoqingyu.criminalitent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by GaoqingYu on 2016/4/16.
 */
public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置View
        getActivity().setTitle(R.string.crime_title);

        // 获取Model
        mCrimes = CrimeLab.get(getActivity()).getmCrimes();

        // 绑定Model
        CrimeAdapter crimeAdapter = new CrimeAdapter(mCrimes);
        setListAdapter(crimeAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // 由于和CrimeFragment用的是同一个model=>CrimeLab,所以只要重新刷新notifyDataSetChanged()，并不需要重新加载，同理网络请求也可参考这个做法
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime crime = ((CrimeAdapter)getListAdapter()).getItem(position);
        // 使用单个fragment
//        Intent intent = new Intent(getActivity(), CrimeActivity.class);
        // 使用viewpager
        Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getmId());
        startActivity(intent);

    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
                super(getActivity(),0,crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            Crime c = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getmTitle());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            DateFormat dateFormat = new DateFormat();

            dateTextView.setText(dateFormat.format("EEEE, MMMM dd, yyyy kk:mm",c.getmDate()));
            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.ismSolved());

            return convertView;
        }
    }

}
