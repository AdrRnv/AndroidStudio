package com.example.tpandroidstudio;

import java.util.Date;

public class Contact {
    private String nom, prenom, genre, email, adresse, telephone, codePostal, dateNaissance;

    public String getPrenom() {
        return prenom;
    }

    public Contact(String nom, String prenom, String genre, String email, String adresse, String telephone, String codePostal, String dateNaissance) {
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
