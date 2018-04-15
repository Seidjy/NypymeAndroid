package com.nypyme.nypyme.view.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.nypyme.nypyme.view.home.fragments.MakeTransactionFragment;

/*
 * Created by Acer on 15/04/2018.
 */

public class HomeAdapter extends FragmentStatePagerAdapter {
    int numberOfTabs;

    public HomeAdapter(FragmentManager fm, int numberOfTabs) {
      super(fm);
      this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

      switch (position) {
        case 0:
          return new MakeTransactionFragment();
        case 1:
          return new MakeTransactionFragment();
        default:
          return new MakeTransactionFragment();
      }
    }

    @Override
    public int getCount() {
      return numberOfTabs;
    }

}
