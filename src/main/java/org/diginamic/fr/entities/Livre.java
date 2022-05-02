package org.diginamic.fr.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="livre")
public class Livre {
    @Id /**Définit la PK*/
    @GeneratedValue(strategy = GenerationType.IDENTITY) //**AutoIncrement par le SGBDR*/
    private int id;
    
    @Column(name = "titre", length = 25, nullable=false)
    private String titre;
    
    @Column(name = "auteur",length=25, nullable=false)
    private String auteur;
    
    public Livre() {
    // TODO Auto-generated constructor stub
    }
    public int getId() {
    return id;
    }
    public void setId(int id) {
    this.id = id;
    }
    public String getTitre() {
    return titre;
    }
    public void setTitre(String titre) {
    this.titre = titre;
    }
    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
