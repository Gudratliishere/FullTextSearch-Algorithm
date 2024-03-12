package com.gudratli.fts;

import com.gudratli.fts.service.DocumentService;
import com.gudratli.fts.service.impl.DocumentServiceFTSImpl;
import com.gudratli.fts.service.impl.DocumentServiceNoOpImpl;
import com.gudratli.fts.service.impl.DocumentServiceSimpleImpl;

public class Context {
    public static DocumentService getDocumentService(EImplType type) {
        switch (type) {
            case SIMPLE -> {
                return new DocumentServiceSimpleImpl();
            }
            case FTS -> {
                return new DocumentServiceFTSImpl();
            }
            default -> {
                return new DocumentServiceNoOpImpl();
            }
        }
    }

    public enum EImplType {
        SIMPLE, FTS
    }
}
