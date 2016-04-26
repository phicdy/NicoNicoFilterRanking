package com.phicdy.niconicofilterranking.ranking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class CategorySettingTest {

    @Test
    public void setAndGetAvailablility() {
        CategorySetting setting = new CategorySetting();
        setting.setAvailability(0, true);
        assertTrue(setting.getAvailability(0));
    }

    @Test
    public void compareSetting() {
        CategorySetting setting = new CategorySetting();
        setting.setAvailability(0, true);
        setting.setAvailability(1, true);
        CategorySetting otherSetting = new CategorySetting();
        otherSetting.setAvailability(2, true);
        otherSetting.setAvailability(3, true);
        assertFalse(setting.equals(otherSetting));

        CategorySetting sameSetting = new CategorySetting();
        sameSetting.setAvailability(0, true);
        sameSetting.setAvailability(1, true);
        assertTrue(setting.equals(sameSetting));
    }
}
