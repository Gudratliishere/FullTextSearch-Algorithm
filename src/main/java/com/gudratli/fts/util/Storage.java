package com.gudratli.fts.util;

import com.gudratli.fts.model.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Storage {
    //Storage for storing list of documents
    public static final List<Document> DOCUMENT_LIST = new ArrayList<>();

    //Storage for storing indexed documents indexes in list
    public static final Map<String, List<Integer>> INDEX_MAP = new HashMap<>();
}
