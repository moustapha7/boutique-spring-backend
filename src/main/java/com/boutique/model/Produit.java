package com.boutique.model;


import javax.persistence.*;

@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String code;

    private String libelle;

    private int prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "CATEGORIE_ID", nullable = true)
    private Categorie categorie;




    public Produit() {
        super();
    }

    public Produit(long id, String code, String libelle, int prixUnitaire, Categorie categorie) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.prixUnitaire = prixUnitaire;
        this.categorie = categorie;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }


    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }



    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", categorie=" + categorie +

                '}';
    }
}
