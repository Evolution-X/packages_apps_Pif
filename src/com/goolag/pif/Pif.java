/*
 * Copyright (C) 2023 The Evolution X Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.goolag.pif;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;

import com.android.internal.util.evolution.PixelPropsUtils;
import com.android.settingslib.widget.TopIntroPreference;

import com.goolag.pif.R;

public class Pif extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    private TopIntroPreference mIntroPreference;

    private Preference mProductPreference;
    private Preference mDevicePreference;
    private Preference mManufacturerPreference;
    private Preference mBrandPreference;
    private Preference mModelPreference;
    private Preference mFingerprintPreference;
    private Preference mSecurityPatchPreference;
    private Preference mFirstAPILevelPreference;
    private Preference mIDPreference;
    private Preference mTypePreference;
    private Preference mTAGSPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.main);

        String selectedArrayName = Settings.System.getString(
                getContext().getContentResolver(), Settings.System.PPU_SPOOF_BUILD_GMS_ARRAY);

        int selectedArrayResId = getResources().getIdentifier(selectedArrayName, "array", getContext().getPackageName());

        String[] selectedDeviceProps = getResources().getStringArray(selectedArrayResId);

        mIntroPreference = findPreference("device_intro");

        mProductPreference = findPreference("product");
        mDevicePreference = findPreference("device");
        mManufacturerPreference = findPreference("manufacturer");
        mBrandPreference = findPreference("brand");
        mModelPreference = findPreference("model");
        mFingerprintPreference = findPreference("fingerprint");
        mSecurityPatchPreference = findPreference("security_patch");
        mFirstAPILevelPreference = findPreference("first_api_level");
        mIDPreference = findPreference("id");
        mTypePreference = findPreference("type");
        mTAGSPreference = findPreference("tags");

        mIntroPreference.setTitle(Build.MANUFACTURER + " " + Build.MODEL);

        mProductPreference.setSummary(selectedDeviceProps[0]);
        mDevicePreference.setSummary(selectedDeviceProps[1].isEmpty() ?
                PixelPropsUtils.getDeviceName(selectedDeviceProps[5]) : selectedDeviceProps[1]);
        mManufacturerPreference.setSummary(selectedDeviceProps[2]);
        mBrandPreference.setSummary(selectedDeviceProps[3]);
        mModelPreference.setSummary(selectedDeviceProps[4]);
        mFingerprintPreference.setSummary(selectedDeviceProps[5]);
        mSecurityPatchPreference.setSummary(selectedDeviceProps[6]);
        mFirstAPILevelPreference.setSummary(selectedDeviceProps[7]);
        mIDPreference.setSummary(selectedDeviceProps[8].isEmpty() ?
                PixelPropsUtils.getBuildID(selectedDeviceProps[5]) : selectedDeviceProps[8]);
        mTypePreference.setSummary(selectedDeviceProps[9].isEmpty() ? "user" : selectedDeviceProps[9]);
        mTAGSPreference.setSummary(selectedDeviceProps[10].isEmpty() ? "release-keys" : selectedDeviceProps[10]);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return true;
    }
}
