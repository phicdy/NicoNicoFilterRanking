package com.phicdy.niconicofilterranking.ranking;

import org.jsoup.nodes.Document;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FeatureData {

    private int[] categories;
    private Map<String, Document> documentMap;

    public FeatureData(int[] categories) {
        this.categories = categories;
        documentMap = new HashMap<>();
    }

    public synchronized void setDocument(String url, Document document) {
        documentMap.put(url, document);
    }

    public Collection<Document> getAllDocuments() {
        return documentMap.values();
    }

    public synchronized boolean isLoaded() {
        return categories.length == documentMap.size() ? true : false;
    }

}
