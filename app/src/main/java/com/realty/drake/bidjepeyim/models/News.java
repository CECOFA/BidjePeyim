package com.realty.drake.bidjepeyim.models;

import org.parceler.Parcel;

/**
 * Created by drake on 8/8/18
 */
@Parcel
public class News {
    //todo Implement News model, using parceler library
    private double idActualite;
    private String titreActualite;
    private String contenuActualite;
    private String motsCles;
    private String datePublication;
    private String auteur;

    public News() { } //Needed for Firebase's auto data mapping

    public News(double idActualite,
                String titreActualite,
                String contenuActualite,
                String motsCles,
                String datePublication,
                String auteur) {
        this.idActualite = idActualite;
        this.titreActualite = titreActualite;
        this.contenuActualite = contenuActualite;
        this.motsCles = motsCles;
        this.datePublication = datePublication;
        this.auteur = auteur;
    }
}
