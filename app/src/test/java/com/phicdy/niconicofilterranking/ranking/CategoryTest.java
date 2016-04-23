package com.phicdy.niconicofilterranking.ranking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class CategoryTest {

    @Test
    public void getAllCategories() {
        int[] allCategories = Category.getAllCategories();
        assertThat(allCategories.length, is(Category.CATEGORY_SIZE));
    }
}
