package com.jnardari.opencv_androidsamples.activities.hough.pref;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import com.jnardari.opencv_androidsamples.R;

/**
 * Dialog confirming reset of settings.
 *
 * @author Jakub Medveck�-Heretik
 */
public class ResetPreference extends DialogPreference {

    protected Context context;

    public ResetPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);

        //Dialog closed by clicking 'Yes' button
        if (which == DialogInterface.BUTTON_POSITIVE) {

            //Reset settings to default values
            SharedPreferences.Editor preferencesEditor = PreferenceManager.getDefaultSharedPreferences(this.context).edit();
            preferencesEditor.clear();
            PreferenceManager.setDefaultValues(context, R.xml.settings, true);
            preferencesEditor.commit();

            //Fire PreferenceChange event
            getOnPreferenceChangeListener().onPreferenceChange(this, true);
        }
    }
}
