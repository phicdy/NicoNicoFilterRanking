package com.phicdy.niconicofilterranking.ranking;

import android.content.Context;

import com.phicdy.niconicofilterranking.R;

public class Category {
    public static final int ALL = 0;
    public static final int ENT_MUSIC = 1;
    public static final int LIFE = 2;
    public static final int POLITICS = 3;
    public static final int SCIENCE_TEC = 4;
    public static final int ANIME_GAME = 5;
    public static final int OTHERS = 6;
    public static final int R18 = 7;
    public static final int ENT = 8;
    public static final int MUSIC = 9;
    public static final int SING = 10;
    public static final int PLAY = 11;
    public static final int DANCE = 12;
    public static final int VOCALOID = 13;
    public static final int NNI = 14;
    public static final int ANIMAL = 15;
    public static final int COOK = 16;
    public static final int NATURE = 17;
    public static final int TRAVEL = 18;
    public static final int SPORTS = 19;
    public static final int LECTURE = 20;
    public static final int DRIVE = 21;
    public static final int HISTORY = 22;
    public static final int SCIENCE = 23;
    public static final int TECHNOLOGY = 24;
    public static final int HANDCRAFT = 25;
    public static final int MAKE = 26;
    public static final int ANIME = 27;
    public static final int GAME = 28;
    public static final int TOHO = 29;
    public static final int IMAS = 30;
    public static final int RADIO = 31;
    public static final int DRAW = 32;
    public static final int REINOARE = 33;
    public static final int DIALY = 34;
    public static final int OTHER = 35;

    public static final int CATEGORY_SIZE = 35;

    /**
     *
     * @return Return the array includes all category indexes
     */
    public static int[] getAllCategories() {
        int[] indexes = new int[OTHER];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        return indexes;
    }

    /**
     *
     * @param categoryId Category ID
     * @param context
     * @return Category title of ID. If ID does not exist, return empty title
     */
    public static String getCategory(int categoryId, Context context) {
        switch (categoryId) {
            case ALL:
                return context.getString(R.string.all);
            case ENT_MUSIC:
                return context.getString(R.string.ent_music);
            case LIFE:
                return context.getString(R.string.life);
            case POLITICS:
                return context.getString(R.string.politics);
            case SCIENCE_TEC:
                return context.getString(R.string.science_tec);
            case ANIME_GAME:
                return context.getString(R.string.anime_game);
            case OTHERS:
                return context.getString(R.string.others);
            case R18:
                return context.getString(R.string.r18);
            case ENT:
                return context.getString(R.string.ent);
            case MUSIC:
                return context.getString(R.string.music);
            case SING:
                return context.getString(R.string.sing);
            case PLAY:
                return context.getString(R.string.play);
            case DANCE:
                return context.getString(R.string.dance);
            case VOCALOID:
                return context.getString(R.string.vocaloid);
            case NNI:
                return context.getString(R.string.nni);
            case ANIMAL:
                return context.getString(R.string.animal);
            case COOK:
                return context.getString(R.string.cook);
            case NATURE:
                return context.getString(R.string.nature);
            case TRAVEL:
                return context.getString(R.string.travel);
            case SPORTS:
                return context.getString(R.string.sports);
            case LECTURE:
                return context.getString(R.string.lecture);
            case DRIVE:
                return context.getString(R.string.drive);
            case HISTORY:
                return context.getString(R.string.history);
            case SCIENCE:
                return context.getString(R.string.science);
            case TECHNOLOGY:
                return context.getString(R.string.technology);
            case HANDCRAFT:
                return context.getString(R.string.handcraft);
            case MAKE:
                return context.getString(R.string.make);
            case ANIME:
                return context.getString(R.string.anime);
            case GAME:
                return context.getString(R.string.game);
            case TOHO:
                return context.getString(R.string.toho);
            case IMAS:
                return context.getString(R.string.imas);
            case RADIO:
                return context.getString(R.string.radio);
            case DRAW:
                return context.getString(R.string.draw);
            case REINOARE:
                return context.getString(R.string.reinoare);
            case DIALY:
                return context.getString(R.string.dialy);
            case OTHER:
                return context.getString(R.string.other);
        }
        return "";

    }
}
