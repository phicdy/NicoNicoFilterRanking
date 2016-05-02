package com.phicdy.niconicofilterranking.ranking;

import com.phicdy.niconicofilterranking.video.NicoVideo;

import java.util.Comparator;

public class NicoVideoComparator implements Comparator<NicoVideo> {

    @Override
    public int compare(NicoVideo video1, NicoVideo video2) {
        if (video1.getHourTotalPoint() <= video2.getHourTotalPoint()) {
            return 1;

        }else  if(video1.getHourTotalPoint() == video2.getHourTotalPoint()) {
            return 0;
        }
        return -1;
    }
}
