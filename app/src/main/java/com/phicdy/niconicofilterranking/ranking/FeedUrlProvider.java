package com.phicdy.niconicofilterranking.ranking;

public class FeedUrlProvider {

    public static String getUrl(int category) {
        String url = "http://now.nicochart.jp/hourly/";
        switch (category) {
            case Category.ALL:
                return url + "feed/";
            case Category.ENT_MUSIC:
                return url + "g_ent2/feed";
            case Category.LIFE:
                return url + "g_life2/feed/";
            case Category.POLITICS:
                return url + "g_politics/feed/";
            case Category.SCIENCE_TEC:
                return url + "g_tech/feed/";
            case Category.ANIME_GAME:
                return url + "g_culture2/feed/";
            case Category.OTHERS:
                return url + "g_other/feed/";
            case Category.R18:
                return url + "g_r18/feed/";
            case Category.ENT:
                return url + "ent/feed/";
            case Category.MUSIC:
                return url + "music/feed/";
            case Category.SING:
                return url + "sing/feed/";
            case Category.PLAY:
                return url + "play/feed/";
            case Category.DANCE:
                return url + "dance/feed/";
            case Category.VOCALOID:
                return url + "vocaloid/feed/";
            case Category.NNI:
                return url + "nicoindies/feed/";
            case Category.ANIMAL:
                return url + "animal/feed/";
            case Category.COOK:
                return url + "cooking/feed/";
            case Category.NATURE:
                return url + "nature/feed/";
            case Category.TRAVEL:
                return url + "travel/feed/";
            case Category.SPORTS:
                return url + "sport/feed/";
            case Category.LECTURE:
                return url + "lecture/feed/";
            case Category.DRIVE:
                return url + "drive/feed/";
            case Category.HISTORY:
                return url + "history/feed/";
            case Category.SCIENCE:
                return url + "science/feed/";
            case Category.TECHNOLOGY:
                return url + "tech/feed/";
            case Category.HANDCRAFT:
                return url + "handcraft/feed/";
            case Category.MAKE:
                return url + "make/feed/";
            case Category.ANIME:
                return url + "anime/feed/";
            case Category.GAME:
                return url + "game/feed/";
            case Category.TOHO:
                return url + "toho/feed/";
            case Category.IMAS:
                return url + "imas/feed/";
            case Category.RADIO:
                return url + "radio/feed/";
            case Category.DRAW:
                return url + "draw/feed/";
            case Category.REINOARE:
                return url + "are/feed/";
            case Category.DIALY:
                return url + "dialy/feed/";
            case Category.OTHER:
                return url + "other/feed/";
            default:
                return "";
        }
    }
}
