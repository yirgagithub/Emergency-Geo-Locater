package com.the.example.amorageolocater;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.the.example.amorageolocater.health.HealthFragment;
import com.the.example.amorageolocater.police.PoliceFragment;
import com.the.example.amorageolocater.traffic.TrafficFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AmoraMainActivity extends AppCompatActivity {

   private DrawerLayout mDrawerLayout;
   private final String Language_English="en";
    private final String Language_Amharic="am-rET";
    private final String Language_Tigrigna="ti-rET-ldltr";
    private final String LANGUAGE_KEY="language_key";
    private final String PHONE_NUMBER_KEY="phoneNumber_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amora_main);
        String lang=loadLocal();
        if(lang!=null&&!lang.equals("en")) {
            changeLanguage(getApplicationContext(), lang);
        }
        boolean  isPhoneNumberStored=checkPhone();
        if(!isPhoneNumberStored)
            createDialog();
        Toolbar toolbar=(Toolbar)findViewById(R.id.mainActivity_toolbar_id) ;
        setSupportActionBar(toolbar);
        //the two lines are causing an arrow to apear in the home view.
        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.mainactivity_navigationView_id);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;

                switch (item.getItemId()) {
                    case R.id.editData_drawerlist_id:
                        intent=new Intent(AmoraMainActivity.this,ViewEditDatabase.class);
                        startActivity(intent);
                        break;
                    case R.id.phoneNumber_drawerlist_id:
                        createDialog();
                        break;
                    case R.id.tigrigna_drawerlist_id:
                        changeLanguage(getApplicationContext(),Language_Tigrigna);
                        break;
                    case R.id.amharic_drawerlist_id:
                        changeLanguage(getApplicationContext(),Language_Amharic);
                        break;
                    case R.id.english_drawerlist_id:
                        changeLanguage(getApplicationContext(),Language_English);
                        break;
                    default:
                        Log.d("navigation error","navigation drawer click");

                }
                return true;
            }

        });
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.mainActivity_collapsing_id);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);

        ViewPager viewPager=(ViewPager)findViewById(R.id.mainActivity_viewPager_id);
        setupViewPager(viewPager);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.mainActivity_tablayout_id);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void createDialog() {


           LayoutInflater layoutInflater=getLayoutInflater();
            final View view=layoutInflater.inflate(R.layout.phonenumber_dialog, null);
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setView(view);
        final EditText phoneNumber_EditText=(EditText)view.findViewById(R.id.phoneNumber_EditText_id);
            dialog
                    .setCancelable(false)
                    .setPositiveButton(R.string.ok_dialogButton_string,new DialogInterface.OnClickListener(){
        public void onClick (DialogInterface dialogInterface,int id){
            String enteredPhoneNumber = phoneNumber_EditText.getText().toString();
            if (enteredPhoneNumber.length() < 10) {
                TextView warningTextView = (TextView) view.findViewById(R.id.error_message);
                warningTextView.setText(R.string.error_message_string);
                phoneNumber_EditText.setText("");

            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("phone_prefres", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString(PHONE_NUMBER_KEY, enteredPhoneNumber).apply();
            }

        }
                })


            .setNegativeButton(R.string.cancel_dialogButton_string,new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    dialog.cancel();

                }
            });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private boolean checkPhone() {

        SharedPreferences shared=getSharedPreferences("phone_prefres",Context.MODE_PRIVATE);
        String DEFAULT_PHONE_NUMBER = "not assigned";
        String phoneNumber=shared.getString(PHONE_NUMBER_KEY, DEFAULT_PHONE_NUMBER);
        if (phoneNumber.equals(DEFAULT_PHONE_NUMBER))
            return false;
        else
            return true;

    }

    private void changeLanguage(Context context, String lang) {

        saveLocale(context,lang);
        Locale locale= new Locale(lang);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        //restart the activity if it does not work
       /* Intent intent = new Intent(AmoraMainActivity.this,AmoraMainActivity.class);
        startActivity(intent);
        */


    }

    private void saveLocale(Context context, String lang) {
        SharedPreferences sharedPreferences=getSharedPreferences("lang_prefs",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(LANGUAGE_KEY,lang).apply();

    }

    private String loadLocal() {
        SharedPreferences sharedPreferences= getSharedPreferences("lang_prefs",Context.MODE_PRIVATE);
        return sharedPreferences.getString(LANGUAGE_KEY,Language_English);

    }

    private void setupViewPager(ViewPager viewPager) {


        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.add(new PoliceFragment(),getResources().getString(R.string.police_title));
        viewPagerAdapter.add(new HealthFragment(),getResources().getString(R.string.health_title));
        viewPagerAdapter.add(new TrafficFragment(),getResources().getString(R.string.traffic_title));
        viewPager.setAdapter(viewPagerAdapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter{

        List<Fragment> listFragment= new ArrayList<>();
        List<String>   listFragmentTitle=new ArrayList<>();

         ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragmentTitle.size();
        }
        @Override
        public CharSequence getPageTitle(int position)
        {
            return listFragmentTitle.get(position);
        }
         void add(Fragment fragment,String title)
        {
            listFragment.add(fragment);
            listFragmentTitle.add(title);
        }

    }
}
