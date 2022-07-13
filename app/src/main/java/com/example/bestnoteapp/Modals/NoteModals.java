package com.example.bestnoteapp.Modals;

public class NoteModals {
    String name,PdfUrl;

    public NoteModals(String name, String pdfUrl) {
        this.name = name;
        PdfUrl = pdfUrl;
    }

    public NoteModals() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        PdfUrl = pdfUrl;
    }
}
