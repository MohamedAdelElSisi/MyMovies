package com.example.sos.mymovies;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Setting extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        bindPreferencesSummartToValue(findPreference("sortby"));


    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String stringvalue =newValue.toString();

        if (preference instanceof ListPreference){

            ListPreference listPreference =(ListPreference) preference;
            int prefindex = listPreference.findIndexOfValue(stringvalue);
            if (prefindex==0) {

                    preference.setSummary(listPreference.getEntries()[prefindex]);
                MainActivityFragment.sortByPop=true;

            }else if (prefindex==1) {

                preference.setSummary(listPreference.getEntries()[prefindex]);
                MainActivityFragment.sortByPop=false;


            }
        }

        else {
            preference.setSummary(stringvalue);
        }
        return true;
    }

    private  void bindPreferencesSummartToValue (Preference preference ){
        preference.setOnPreferenceChangeListener(this);
        onPreferenceChange(preference , PreferenceManager.getDefaultSharedPreferences
                (preference.getContext()).getString(preference.getKey(),""));

    }
}
