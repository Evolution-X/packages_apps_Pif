/*
 * Copyright (C) 2023-2024 The Evolution X Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.goolag.pif;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.android.internal.util.evolution.PixelPropsUtils;
import com.android.settingslib.widget.TopIntroPreference;

import com.goolag.pif.R;

public class Pif extends PreferenceFragmentCompat
        implements Preference.OnPreferenceChangeListener {

    private TopIntroPreference mIntroPreference;

    private Preference mManufacturerPreference;
    private Preference mModelPreference;
    private Preference mFingerprintPreference;
    private Preference mBrandPreference;
    private Preference mProductPreference;
    private Preference mDevicePreference;
    private Preference mReleasePreference;
    private Preference mIDPreference;
    private Preference mIncrementalPreference;
    private Preference mTypePreference;
    private Preference mTAGSPreference;
    private Preference mSecurityPatchPreference;
    private Preference mDeviceInitialSdkIntPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.main, rootKey);

        String selectedArrayName = Settings.System.getString(
                getContext().getContentResolver(), Settings.System.PPU_SPOOF_BUILD_GMS_ARRAY);

        int selectedArrayResId = getResources().getIdentifier(selectedArrayName, "array", getContext().getPackageName());

        String[] selectedDeviceProps = getResources().getStringArray(selectedArrayResId);

        mIntroPreference = findPreference("device_intro");

        mManufacturerPreference = findPreference("manufacturer");
        mModelPreference = findPreference("model");
        mFingerprintPreference = findPreference("fingerprint");
        mBrandPreference = findPreference("brand");
        mProductPreference = findPreference("product");
        mDevicePreference = findPreference("device");
        mReleasePreference = findPreference("release");
        mIDPreference = findPreference("id");
        mIncrementalPreference = findPreference("incremental");
        mTypePreference = findPreference("type");
        mTAGSPreference = findPreference("tags");
        mSecurityPatchPreference = findPreference("security_patch");
        mDeviceInitialSdkIntPreference = findPreference("device_initial_sdk_int");

        mIntroPreference.setTitle(Build.MANUFACTURER + " " + Build.MODEL);

        mManufacturerPreference.setSummary(selectedDeviceProps[0]);
        mModelPreference.setSummary(selectedDeviceProps[1]);
        mFingerprintPreference.setSummary(selectedDeviceProps[2]);
        mBrandPreference.setSummary(selectedDeviceProps[3]);
        mProductPreference.setSummary(selectedDeviceProps[4]);
        mDevicePreference.setSummary(selectedDeviceProps[5].isEmpty() ?
                PixelPropsUtils.getDeviceName(selectedDeviceProps[2]) : selectedDeviceProps[5]);
        mReleasePreference.setSummary(selectedDeviceProps[6]);
        mIDPreference.setSummary(selectedDeviceProps[7].isEmpty() ?
                PixelPropsUtils.getBuildID(selectedDeviceProps[5]) : selectedDeviceProps[7]);
        mIncrementalPreference.setSummary(selectedDeviceProps[8]);
        mTypePreference.setSummary(selectedDeviceProps[9].isEmpty() ? "user" : selectedDeviceProps[9]);
        mTAGSPreference.setSummary(selectedDeviceProps[10].isEmpty() ? "release-keys" : selectedDeviceProps[10]);
        mSecurityPatchPreference.setSummary(selectedDeviceProps[11]);
        mDeviceInitialSdkIntPreference.setSummary(selectedDeviceProps[12]);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return true;
    }
}
