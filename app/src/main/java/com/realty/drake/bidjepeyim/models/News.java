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
    private String imageActualite;

    public News() { } //Needed for Firebase's auto data mapping

    public News(double idActualite,
                String titreActualite,
                String contenuActualite,
                String motsCles,
                String datePublication,
                String auteur,
                String imageActualite) {
        this.idActualite = idActualite;
        this.titreActualite = titreActualite;
        this.contenuActualite = contenuActualite;
        this.motsCles = motsCles;
        this.datePublication = datePublication;
        this.auteur = auteur;
        this.imageActualite = imageActualite;
    }

    public double getIdActualite() {
        return idActualite;
    }

    public void setIdActualite(double idActualite) {
        this.idActualite = idActualite;
    }

    public String getTitreActualite() {
        return titreActualite;
    }

    public void setTitreActualite(String titreActualite) {
        this.titreActualite = titreActualite;
    }

    public String getContenuActualite() {
        return contenuActualite;
    }

    public void setContenuActualite(String contenuActualite) {
        this.contenuActualite = contenuActualite;
    }

    public String getMotsCles() {
        return motsCles;
    }

    public void setMotsCles(String motsCles) {
        this.motsCles = motsCles;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getImageActualite() {
        return imageActualite;
    }

    public void setImageActualite(String imageActualite) {
        this.imageActualite = imageActualite;
    }

}
