package com.phicdy.niconicofilterranking.ranking;

import java.util.HashMap;
import java.util.Map;

public class CategorySetting {
    Map<Integer, Boolean> availabilityMap = new HashMap<>();

    public CategorySetting() {
        int[] allCategories = Category.getAllCategories();
        for (int category : allCategories) {
            availabilityMap.put(category, false);
        }
    }

    public void setAvailability(int category, boolean enabled) {
        availabilityMap.put(category, enabled);
    }

    public boolean getAvailability(int category) {
        return availabilityMap.get(category);
    }

    /**
     *
     * @param o Instance of CategorySetting class
     * @return Return {@code true} if o is instance of CategorySetting class and
     * all of the category availability equals myself. Else return {@code false}
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CategorySetting)) {
            return false;
        }
        int[] allCategories = Category.getAllCategories();
        for (int category : allCategories) {
            if (((CategorySetting) o).getAvailability(category) != getAvailability(category)) {
                return false;
            }
        }
        return true;
    }
}
