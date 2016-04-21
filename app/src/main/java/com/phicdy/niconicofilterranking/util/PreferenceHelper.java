package com.phicdy.niconicofilterranking.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.phicdy.niconicofilterranking.ranking.CategorySetting;

public class PreferenceHelper {

	private static final String PREF_KEY = "FilterPref";
	private static final String KEY_CATEGORY_SETTING = "autoUpdateInterval";

	private static PreferenceHelper preMgr;
	private SharedPreferences pref = null;
	private SharedPreferences.Editor editor = null;

	private PreferenceHelper(Context context) {
		pref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
	}

	public static PreferenceHelper getInstance(Context context) {
		if (preMgr == null) {
			preMgr = new PreferenceHelper(context);
		}
		return preMgr;
	}

	public CategorySetting getCategorySetting() {
		if (pref.contains(KEY_CATEGORY_SETTING)) {
			String temp = pref.getString(KEY_CATEGORY_SETTING, "");
			if (temp.equals("")) {
				return new CategorySetting();
			}
			return new Gson().fromJson(temp, CategorySetting.class);
		}
		return new CategorySetting();
	}

	public void setCategorySetting(CategorySetting categorySetting) {
		editor = pref.edit();
		editor.putString(KEY_CATEGORY_SETTING, new Gson().toJson(categorySetting));
		editor.commit();
	}
}
	

