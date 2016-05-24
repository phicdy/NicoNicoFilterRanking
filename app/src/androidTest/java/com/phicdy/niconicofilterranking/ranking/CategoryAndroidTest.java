package com.phicdy.niconicofilterranking.ranking;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class CategoryAndroidTest {

    @Test
    public void getCategory() {
        Context context = InstrumentationRegistry.getTargetContext();
        assertThat(Category.getCategory(Category.ALL, context), is("カテゴリ合算"));
        assertThat(Category.getCategory(Category.ENT_MUSIC, context), is("エンタメ・音楽"));
        assertThat(Category.getCategory(Category.LIFE, context), is("生活"));
        assertThat(Category.getCategory(Category.POLITICS, context), is("政治"));
        assertThat(Category.getCategory(Category.SCIENCE_TEC, context), is("科学・技術"));
        assertThat(Category.getCategory(Category.ANIME_GAME, context), is("アニメ・ゲーム"));
        assertThat(Category.getCategory(Category.OTHERS, context), is("その他"));
        assertThat(Category.getCategory(Category.R18, context), is("R-18"));
        assertThat(Category.getCategory(Category.ENT, context), is("エンタメ"));
        assertThat(Category.getCategory(Category.MUSIC, context), is("音楽"));
        assertThat(Category.getCategory(Category.SING, context), is("歌ってみた"));
        assertThat(Category.getCategory(Category.PLAY, context), is("演奏してみた"));
        assertThat(Category.getCategory(Category.DANCE, context), is("踊ってみた"));
        assertThat(Category.getCategory(Category.VOCALOID, context), is("VOCALOID"));
        assertThat(Category.getCategory(Category.NNI, context), is("ニコニコインディーズ"));
        assertThat(Category.getCategory(Category.ANIMAL, context), is("動物"));
        assertThat(Category.getCategory(Category.COOK, context), is("料理"));
        assertThat(Category.getCategory(Category.NATURE, context), is("自然"));
        assertThat(Category.getCategory(Category.TRAVEL, context), is("旅行"));
        assertThat(Category.getCategory(Category.SPORTS, context), is("スポーツ"));
        assertThat(Category.getCategory(Category.LECTURE, context), is("ニコニコ動画講座"));
        assertThat(Category.getCategory(Category.DRIVE, context), is("車載動画"));
        assertThat(Category.getCategory(Category.HISTORY, context), is("歴史"));
        assertThat(Category.getCategory(Category.SCIENCE, context), is("科学"));
        assertThat(Category.getCategory(Category.TECHNOLOGY, context), is("ニコニコ技術部"));
        assertThat(Category.getCategory(Category.HANDCRAFT, context), is("ニコニコ手芸部"));
        assertThat(Category.getCategory(Category.MAKE, context), is("作ってみた"));
        assertThat(Category.getCategory(Category.ANIME, context), is("アニメ"));
        assertThat(Category.getCategory(Category.GAME, context), is("ゲーム"));
        assertThat(Category.getCategory(Category.TOHO, context), is("東方"));
        assertThat(Category.getCategory(Category.IMAS, context), is("アイドルマスター"));
        assertThat(Category.getCategory(Category.RADIO, context), is("ラジオ"));
        assertThat(Category.getCategory(Category.DRAW, context), is("描いてみた"));
        assertThat(Category.getCategory(Category.REINOARE, context), is("例のアレ"));
        assertThat(Category.getCategory(Category.DIALY, context), is("日記"));
        assertThat(Category.getCategory(Category.OTHER, context), is("その他"));
        assertThat(Category.getCategory(9999999, context), is(""));
    }
}
