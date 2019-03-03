package com.example.cerdastb.Model;

public class MateriModel {
    private String Judul,image,materi,type;

    public MateriModel() {

    }

    public MateriModel(String judul, String image, String materi, String type) {
        Judul = judul;
        this.image = image;
        this.materi = materi;
        this.type = type;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
