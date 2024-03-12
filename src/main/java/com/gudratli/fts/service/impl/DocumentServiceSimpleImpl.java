package com.gudratli.fts.service.impl;

import com.gudratli.fts.model.Document;
import com.gudratli.fts.service.DocumentService;
import com.gudratli.fts.util.Storage;

import java.util.ArrayList;
import java.util.List;

public class DocumentServiceSimpleImpl implements DocumentService {
    @Override
    public List<Document> findDocuments(String text) {
        long currentTime = System.currentTimeMillis();

        List<Document> documentList = new ArrayList<>();
        for (Document document : Storage.DOCUMENT_LIST)
            if (document.getAbstract().matches("(?i).*\\b" + text + "\\b.*"))
                documentList.add(document);

        System.out.println(documentList.size() + " documents are found in " +
                (System.currentTimeMillis() - currentTime) + "ms with simple search");
        return documentList;
    }
}
