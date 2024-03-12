package com.gudratli.fts.service.impl;

import com.gudratli.fts.model.Document;
import com.gudratli.fts.service.DocumentService;
import com.gudratli.fts.util.Analyser;
import com.gudratli.fts.util.Storage;

import java.util.*;

public class DocumentServiceFTSImpl implements DocumentService {
    @Override
    public List<Document> findDocuments(String text) {
        long currentTime = System.currentTimeMillis();

        List<String> tokens = Analyser.analyse(text);
        Set<Document> documentSet = new HashSet<>();

        if (tokens.isEmpty())
            return Collections.emptyList();

        List<Integer> ids = Storage.INDEX_MAP.get(tokens.get(0));
        tokens.forEach(token -> {
            List<Integer> tempIds = Storage.INDEX_MAP.get(token);
            ids.retainAll(tempIds);
        });
        ids.forEach(id -> documentSet.add(Storage.DOCUMENT_LIST.get(id)));

        System.out.println(documentSet.size() + " documents are found in " +
                (System.currentTimeMillis() - currentTime) + "ms with inverted index");
        return documentSet.stream().toList();
    }
}
