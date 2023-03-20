package com.example.personallibrary.models;

public class PutPDF {

    String pdfID;
    String name;
    String author;
    String des;
    String isRead;
    String category;


    public PutPDF() {

    }

    public PutPDF(String pdfID, String name, String author, String des, String isRead , String category) {
        this.pdfID = pdfID;
        this.name = name;
        this.author = author;
        this.des = des;
        this.isRead = isRead;
        this.category = category;
    }

    public String getPdfID() {
        return pdfID;
    }

    public void setPdfID(String pdfID) {
        this.pdfID = pdfID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
