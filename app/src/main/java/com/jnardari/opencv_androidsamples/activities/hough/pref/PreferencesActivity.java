package com.jnardari.opencv_androidsamples.activities.hough.pref;

//import cz.muni.fi.hough.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.jnardari.opencv_androidsamples.R;

/**
 * Application settings.
 *
 * @author Jakub Medveck�-Heretik
 */
public class PreferencesActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

    private Preference resetDialogPreference;
    private Intent startIntent;

    public PreferencesActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager()
            .beginTransaction()
            .replace(android.R.id.content,
                new MyPreferenceFragment()).commit();

        PreferenceManager.setDefaultValues(PreferencesActivity.this, R.xml.settings, false);

        //Print default values of preferences in their summary
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            initSummary(getPreferenceScreen().getPreference(i));
        }

        //Register reset dialog handler
        this.resetDialogPreference = getPreferenceScreen().findPreference("prefReset");
        this.startIntent = getIntent();

        this.resetDialogPreference.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                finish();
                startActivity(startIntent);
                return false;
            }
        });





    }

    // PreferenceFragment 클래스 사용
    public static class MyPreferenceFragment extends
        PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //Load default preferences and their values
            addPreferencesFromResource(R.xml.settings);

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    //Update summary when preference changes
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updatePrefSummary(findPreference(key));
    }

    /**
     * Loads default value of preference p and prints value in its summary
     *
     * @param p
     */
    private void initSummary(Preference p) {
        if (p instanceof PreferenceCategory) {
            PreferenceCategory pCat = (PreferenceCategory) p;
            for (int i = 0; i < pCat.getPreferenceCount(); i++) {
                initSummary(pCat.getPreference(i));
            }
        } else {
            updatePrefSummary(p);
        }
    }

    /**
     * Loads updated value of preference p and prints value in its summary
     *
     * @param p
     */
    private void updatePrefSummary(Preference p) {
        if (p instanceof ListPreference) {
            ListPreference listPref = (ListPreference) p;
            p.setSummary(listPref.getEntry());
        }
        if (p instanceof EditTextPreference) {
            EditTextPreference editTextPref = (EditTextPreference) p;
            p.setSummary(editTextPref.getText());
        }
    }

}
