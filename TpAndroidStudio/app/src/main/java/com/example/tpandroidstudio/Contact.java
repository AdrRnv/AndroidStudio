package com.example.tpandroidstudio;
import java.io.Serializable;
import java.util.Date;

public class Contact implements Serializable {
    private String img, nom, prenom, genre, email, adresse, telephone, codePostal, dateNaissance;

    public String getPrenom() {
        return prenom;
    }

    public String getImg() {
        return img;
    }

    public String getNom() {
        return nom;
    }

    public String getGenre() {
        return genre;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public Contact(String img, String nom, String prenom, String genre, String email, String adresse, String telephone, String codePostal, String dateNaissance) {
        this.img = img;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.codePostal = codePostal;
        this.dateNaissance = dateNaissance;
    }
}
