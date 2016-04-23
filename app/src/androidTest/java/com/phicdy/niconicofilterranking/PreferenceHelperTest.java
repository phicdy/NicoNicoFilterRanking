package com.phicdy.niconicofilterranking;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.phicdy.niconicofilterranking.ranking.Category;
import com.phicdy.niconicofilterranking.ranking.CategorySetting;
import com.phicdy.niconicofilterranking.util.PreferenceHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class PreferenceHelperTest {

    @Test
    public void setAndGetCategorySetting() {
        // Test default value
        PreferenceHelper helper = PreferenceHelper.getInstance(InstrumentationRegistry.getContext());
        CategorySetting defaultSetting = helper.getCategorySetting();
        assertTrue(defaultSetting.equals(new CategorySetting()));

        // Test saved value
        CategorySetting settingAllAvailable = new CategorySetting();
        settingAllAvailable.setAvailability(Category.ALL, true);
        helper.setCategorySetting(settingAllAvailable);
        CategorySetting savedSetting = helper.getCategorySetting();
        assertTrue(savedSetting.equals(settingAllAvailable));
    }
}
