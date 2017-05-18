package com.nikola.exampleactivities.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.nikola.exampleactivities.R;

/**
 * Created by Dzoni on 5/18/2017.
 */

public class SettingsActivity extends PreferenceActivity {
    // onCreate method is a lifecycle method called when he activity is starting

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Replaces activity's content with a an instance of PreferenceFragment
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PrefsFragment()).commit();
    }


    // PreferenceFragment is used to automatically load preference GUI from an XML resource and
    // save preferences into preferences.xml
    public static class PrefsFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Loads preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}




