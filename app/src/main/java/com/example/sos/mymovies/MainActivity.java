package com.example.sos.mymovies;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    private boolean mTwoPane;
    private static final String DETAILFRAGMENT_TAG = "DFTAG";

    public static boolean TABLET=false;
    public boolean isTablet (Context context){
        boolean xlarge=((context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK)==4);
        boolean large=((context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK)==Configuration.SCREENLAYOUT_SIZE_LARGE);
      return  (xlarge||large);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TABLET=isTablet(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if (findViewById(R.id.containerDetail) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerDetail, new DetailFragment(), DETAILFRAGMENT_TAG)
                        .commit();

            }
        } else {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
        }

        /*
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new MainActivityFragment())
                    .commit();

        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent =new Intent(this,Setting.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
