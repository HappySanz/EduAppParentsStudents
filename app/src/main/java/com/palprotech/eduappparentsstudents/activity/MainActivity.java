package com.palprotech.eduappparentsstudents.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.palprotech.eduappparentsstudents.R;
import com.palprotech.eduappparentsstudents.adapter.NavDrawerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ListView navDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter<String> navListAdapter;


    private String[] values = {"PROFILE","ATTENDANCE","CLASS TEST & HOMEWORK","EXAM & RESULT","TIME TABLE", "CALENDAR", "EVENT", "COMMUNICATION", "SETTINGS", "SIGN OUT"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_drawer);


        initializeNavigationDrawer();
    }

    private void initializeNavigationDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {

            }

            public void onDrawerOpened(View drawerView) {
                //String userName = PreferenceStorage.getUserName(getApplicationContext());
                //String userCity = PreferenceStorage.getUserCity(getApplicationContext());
                //String url = PreferenceStorage.getProfileUrl(MainActivity.this);
                //int loginMode = PreferenceStorage.getLoginMode(MainActivity.this);
//                if ((url == null) || (url.isEmpty())) {
//                    if ((loginMode == 1) || (loginMode == 3)) {
//                        url = PreferenceStorage.getSocialNetworkProfileUrl(MainActivity.this);
//                    }
//
//                }
//                Log.d(TAG, "user name value" + userName);
//                if ((userName != null) && !userName.isEmpty()) {
//                    String[] splitStr = userName.split("\\s+");
//                    navUserName.setText(splitStr[0]);
//
//                }

//                if ((userCity != null) && !userCity.isEmpty()) {
//                    navUserCity.setText(userCity);
//                }
//                if (((url != null) && !(url.isEmpty())) && !(url.equalsIgnoreCase(mCurrentUserProfileUrl))) {
//                    Log.d(TAG, "image url is " + url);
//                    mCurrentUserProfileUrl = url;
//                    Picasso.with(LandingActivity.this).load(url).noPlaceholder().error(R.drawable.ab_hobbistan_logo).into(imgNavProfileImage);
//                }
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        // enable ActionBar app icon to behave as action to toggle nav drawer
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        // Initialize header and listview
        navDrawerList = (ListView) findViewById(R.id.nav_drawer_options_list);

//        if (PreferenceStorage.getUserType(getApplicationContext()).equalsIgnoreCase("1")) {
            NavDrawerAdapter navDrawerAdapter = new NavDrawerAdapter(getApplicationContext(), R.layout.nav_list_item, values);
            navListAdapter = new ArrayAdapter<String>(this, R.layout.nav_list_item, values);
            navDrawerList.setAdapter(navDrawerAdapter);
//        } else {
//
//            NavDrawerAdapter navDrawerAdapter = new NavDrawerAdapter(getApplicationContext(), R.layout.nav_list_item, values_guest);
//            navListAdapter = new ArrayAdapter<String>(this, R.layout.nav_list_item, values_guest);
//            navDrawerList.setAdapter(navDrawerAdapter);
//        }
        // imgNavHeaderBg = (ImageView) findViewById(R.id.img_nav_header_background);
//        imgNavProfileImage = (ImageView) findViewById(R.id.img_profile_image);
        navDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                onNavigationMenuSelected(position);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        //populateNavDrawerHeaderView();
    }
}
