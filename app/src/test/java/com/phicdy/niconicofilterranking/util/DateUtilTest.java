package com.phicdy.niconicofilterranking.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class DateUtilTest {

    @Test
    public void testConvertNicoChartDate() {
        assertThat(DateUtil.convertNicoChartDate("2016-03-13T20:33:40+09:00"),
                is("2016年03月13日 20:33:40"));
    }
}
