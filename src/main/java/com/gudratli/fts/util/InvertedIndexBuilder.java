package com.gudratli.fts.util;

import com.gudratli.fts.model.Document;

import java.util.*;

public class InvertedIndexBuilder {
    public static void build(List<Document> documents) {
        System.out.println("Starting inverted index building..");

        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < documents.size(); i++) {
            System.out.printf("Indexing %s. element.\r", i);

            List<String> tokens = Analyser.analyse(documents.get(i).getAbstract());
            for (String token : tokens) {
                if (map.get(token) != null)
                    map.get(token).add(i);
                else {
                    List<Integer> ids = new ArrayList<>();
                    ids.add(i);
                    map.put(token, ids);
                }
            }
        }
        Storage.INDEX_MAP.putAll(map);

        System.out.println();
        System.out.println("Documents are indexed, index size: " + Storage.INDEX_MAP.size());
    }
}
