package com.example.bitjaipur.ui.ebook;

public class EbookData {
    private  String name,pdfUrl,key;

    public EbookData() {
    }

    public EbookData(String name, String pdfUrl,String key) {
        this.name = name;
        this.pdfUrl = pdfUrl;
        this.key=key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpdfURL() {
        return pdfUrl;
    }

    public void setPdfURL(String pdfURL) {
        this.pdfUrl = pdfUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
