package com.gudratli.fts;

import com.gudratli.fts.model.Document;
import com.gudratli.fts.service.DocumentService;
import com.gudratli.fts.util.InvertedIndexBuilder;
import com.gudratli.fts.util.Storage;
import com.gudratli.fts.util.XMLReader;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Reading xml file and storing it
        List<Document> documentList = XMLReader.readDocuments("exampledata.xml",
                Document.class);
        Storage.DOCUMENT_LIST.addAll(documentList);
        System.out.println("Document count is " + Storage.DOCUMENT_LIST.size());

        //Indexing documents
        InvertedIndexBuilder.build(Storage.DOCUMENT_LIST);

        //Searching over data
        DocumentService documentServiceSimple = Context.getDocumentService(Context.EImplType.SIMPLE);
        DocumentService documentServiceFts = Context.getDocumentService(Context.EImplType.FTS);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("How do you want to search? 1 - simple, 2 - with inverted index, 3 - exit");
            int option = scanner.nextInt();
            if (option == 3)
                System.exit(0);

            scanner.nextLine();
            System.out.println("What are you looking for?");
            String text = scanner.nextLine();
            switch (option) {
                case 1 -> documentServiceSimple.findDocuments(text).forEach(System.out::println);
                case 2 -> documentServiceFts.findDocuments(text).forEach(System.out::println);
                default -> System.out.println("Please choose valid option");
            }
            System.out.println();
        }
    }
}