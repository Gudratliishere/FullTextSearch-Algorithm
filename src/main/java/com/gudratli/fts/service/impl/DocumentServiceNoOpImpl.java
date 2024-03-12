package com.gudratli.fts.service.impl;

import com.gudratli.fts.model.Document;
import com.gudratli.fts.service.DocumentService;

import java.util.Collections;
import java.util.List;

public class DocumentServiceNoOpImpl implements DocumentService {
    @Override
    public List<Document> findDocuments(String text) {
        //This method just do nothing
        return Collections.emptyList();
    }
}
