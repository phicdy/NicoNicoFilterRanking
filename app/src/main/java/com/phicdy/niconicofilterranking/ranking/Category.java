package com.phicdy.niconicofilterranking.ranking;

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

    /**
     *
     * @return Return the array includes all category indexes
     */
    public static int[] getAllCategories() {
        int[] indexes = new int[OTHERS];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        return indexes;
    }
}
