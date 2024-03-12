package com.gudratli.fts.util;

import org.tartarus.snowball.ext.englishStemmer;

import java.util.ArrayList;
import java.util.List;

public class Analyser {
    private static final List<String> STOP_WORDS =
            List.of("a", "and", "be", "have", "i", "in", "of", "that", "the", "to");
    private static final englishStemmer ENGLISH_STEMMER = new englishStemmer();

    public static List<String> analyse (String text){
        List<String> tokens = tokenize(text);
        toLowercase(tokens);
        removeStopWords(tokens);
        filterStemmer(tokens);

        return tokens;
    }

    private static List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();
        StringBuilder tokenBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                tokenBuilder.append(c);
            } else {
                if (!tokenBuilder.isEmpty()) {
                    tokens.add(tokenBuilder.toString());
                    tokenBuilder.setLength(0);
                }
            }
        }

        if (!tokenBuilder.isEmpty())
            tokens.add(tokenBuilder.toString());

        return tokens;
    }

    private static void toLowercase(List<String> list) {
        list.replaceAll(String::toLowerCase);
    }

    private static void removeStopWords(List<String> list) {
        list.removeIf(STOP_WORDS::contains);
    }

    private static void filterStemmer(List<String> list) {
        list.replaceAll(s -> {
            ENGLISH_STEMMER.setCurrent(s);
            ENGLISH_STEMMER.stem();
            return ENGLISH_STEMMER.getCurrent();
        });
    }
}
