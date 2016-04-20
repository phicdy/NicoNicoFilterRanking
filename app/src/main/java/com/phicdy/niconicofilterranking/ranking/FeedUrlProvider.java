package com.phicdy.niconicofilterranking.ranking;

public class FeedUrlProvider {
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

    public static String getUrl(int category) {
        String url = "http://now.nicochart.jp/hourly/";
        switch (category) {
            case ALL:
                return url + "feed/";
            case ENT_MUSIC:
                return url + "g_ent2/feed";
            case LIFE:
                return url + "g_life2/feed/";
            case POLITICS:
                return url + "g_politics/feed/";
            case SCIENCE_TEC:
                return url + "g_tech/feed/";
            case ANIME_GAME:
                return url + "g_culture2/feed/";
            case OTHERS:
                return url + "g_other/feed/";
            case R18:
                return url + "g_r18/feed/";
            case ENT:
                return url + "ent/feed/";
            case MUSIC:
                return url + "music/feed/";
            case SING:
                return url + "sing/feed/";
            case PLAY:
                return url + "play/feed/";
            case DANCE:
                return url + "dance/feed/";
            case VOCALOID:
                return url + "vocaloid/feed/";
            case NNI:
                return url + "nicoindies/feed/";
            case ANIMAL:
                return url + "animal/feed/";
            case COOK:
                return url + "cooking/feed/";
            case NATURE:
                return url + "nature/feed/";
            case TRAVEL:
                return url + "travel/feed/";
            case SPORTS:
                return url + "sport/feed/";
            case LECTURE:
                return url + "lecture/feed/";
            case DRIVE:
                return url + "drive/feed/";
            case HISTORY:
                return url + "history/feed/";
            case SCIENCE:
                return url + "science/feed/";
            case TECHNOLOGY:
                return url + "tech/feed/";
            case HANDCRAFT:
                return url + "handcraft/feed/";
            case MAKE:
                return url + "make/feed/";
            case ANIME:
                return url + "anime/feed/";
            case GAME:
                return url + "game/feed/";
            case TOHO:
                return url + "toho/feed/";
            case IMAS:
                return url + "imas/feed/";
            case RADIO:
                return url + "radio/feed/";
            case DRAW:
                return url + "draw/feed/";
            case REINOARE:
                return url + "are/feed/";
            case DIALY:
                return url + "dialy/feed/";
            case OTHER:
                return url + "other/feed/";
            default:
                return "";
        }
    }
}
