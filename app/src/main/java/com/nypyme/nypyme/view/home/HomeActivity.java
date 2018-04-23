package com.nypyme.nypyme.view.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.nypyme.nypyme.R;
import com.nypyme.nypyme.util.Constants;
import com.nypyme.nypyme.util.PreferencesHelper;
import com.nypyme.nypyme.view.home.adapter.HomeAdapter;
import com.nypyme.nypyme.view.login.LoginActivity;

/*
 * Created by Acer on 24/03/2018.
 */

public class HomeActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    TabLayout tabLayout = findViewById(R.id.tab_layout);
    tabLayout.addTab(tabLayout.newTab().setText(Constants.FRAGMENT_POINTS));
    //tabLayout.addTab(tabLayout.newTab().setText(Constants.FRAGMENT_CONSULT));
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    final ViewPager viewPager = findViewById(R.id.pager);
    final PagerAdapter adapter =
        new HomeAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
    viewPager.setAdapter(adapter);
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        if (tab.getText() == Constants.FRAGMENT_POINTS) {
          getSupportActionBar().setTitle(Constants.FRAGMENT_POINTS);
        } else {
          getSupportActionBar().setTitle(Constants.FRAGMENT_CONSULT);
        }
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });
    getSupportActionBar().setTitle(Constants.FRAGMENT_POINTS);
  }

  @SuppressLint("RestrictedApi")
  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);

    if (menu instanceof MenuBuilder) {
      MenuBuilder m = (MenuBuilder) menu;
      m.setOptionalIconsVisible(true);
    }

    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.menu_logout) {
      logout();
    }

    return super.onOptionsItemSelected(item);
  }

  private void logout() {
    PreferencesHelper preferencesHelper;
    preferencesHelper = new PreferencesHelper(this);
    preferencesHelper.removeToken();
    startActivity(new Intent(this, LoginActivity.class));
    finish();
  }
}
