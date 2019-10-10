package org.techtown.myrecyclerview;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.techtown.myrecyclerview.common.AddLocationDialogFragment;

import org.techtown.myrecyclerview.finedust.FineDustContract;
import org.techtown.myrecyclerview.finedust.FineDustFragment;
import org.techtown.myrecyclerview.util.GeoUtil;

import java.util.ArrayList;
import java.util.List;


public class Main4Activity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<Pair<Fragment, String>> mFragmentList;


    private FusedLocationProviderClient mFusedLocationClient;
    public static final int REQUEST_CODE_FINE_COARSE_PERMISSION = 1000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

         mViewPager = (ViewPager) findViewById(R.id.view_pager1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mFragmentList = new ArrayList<>();

        mFragmentList.add(new Pair<Fragment, String>(new FineDustFragment(), "현재 위치"));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



        AddLocationDialogFragment.newInstance(new AddLocationDialogFragment.OnClickListener() {
            @Override
            public void onOkClicked(final String city) {

                GeoUtil.getLocationFromName(Main4Activity.this, city, new GeoUtil.GeoUtilListener() {
                    @Override
                    public void onSuccess(double lat, double lng) {

                        aadNewFragment(lat, lng, city);

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(Main4Activity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).show(getSupportFragmentManager(), "dialog");
            }
        });

        setUpViewPager();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



    private void aadNewFragment(double lat, double lng, String city) {
        mFragmentList.add(new Pair<Fragment, String>(FineDustFragment.newInstance(lat, lng), city));
        mViewPager.getAdapter().notifyDataSetChanged();
    }


    private void setUpViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);



        loadDbData();
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void loadDbData() {

        mFragmentList = new ArrayList<>();
        mFragmentList.add(new Pair<Fragment, String>(new FineDustFragment(), "현재위치"));


    }

    public void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_FINE_COARSE_PERMISSION);
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            FineDustContract.View view = (FineDustContract.View) mFragmentList.get(0).first;
                            view.reload(location.getLatitude(), location.getLongitude());
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_FINE_COARSE_PERMISSION){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                getLastKnownLocation();
            }
        }

    }

    private static class MyPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Pair<Fragment,String>> mFragmentList;



        public MyPagerAdapter(FragmentManager fm, List<Pair<Fragment,String>> fragmentList) {
            super(fm);
            this.mFragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position).first;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentList.get(position).second;
        }
    }

}


