package com.gudratli.fts.model;

public class Document {
    public static final String DOC_NAME = "doc";

    private String title;
    private String url;
    private String abstractStr;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAbstract() {
        return abstractStr;
    }

    public void setAbstract(String abstractStr) {
        this.abstractStr = abstractStr;
    }

    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", abstractStr='" + abstractStr + '\'' +
                '}';
    }
}
