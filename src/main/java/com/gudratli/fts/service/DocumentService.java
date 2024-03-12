package com.gudratli.fts.service;

import com.gudratli.fts.model.Document;

import java.util.List;

public interface DocumentService {
    List<Document> findDocuments(String text);
}
