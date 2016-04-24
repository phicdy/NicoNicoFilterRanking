package com.phicdy.niconicofilterranking.ranking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NicoChartRequestChannel {

    private ExecutorService executorService;

    public NicoChartRequestChannel() {
        executorService = Executors.newFixedThreadPool(8);
    }

    public FeatureData startGetMyRanking(CategorySetting setting) {
        int[] allCategories = Category.getAllCategories();
        FeatureData data = new FeatureData(new int[]{});
        for (int category : allCategories) {
            if (setting.getAvailability(category)) {
                String url = FeedUrlProvider.getUrl(category);
                NicoChartRequest request = new NicoChartRequest(url, data);
                executorService.execute(request);
            }
        }
        return data;
    }
}
