package com.boutique.model;

import javax.persistence.*;

@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="CATEGORIE_ID")
    private long id;

    @Column(unique = true)
    private String code;

    private String libelle;

    private String username;

    public Categorie(long id, String code, String libelle, String username) {
        super();
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.username = username;    }

    public Categorie() {
        super();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
