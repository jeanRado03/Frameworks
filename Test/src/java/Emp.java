/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import annotation.Url;
/**
 *
 * @author rado
 */
public class Emp {
    int id;
    int id_dept;
    String nom;
    String prenom;
    double salaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_dept() {
        return id_dept;
    }

    public void setId_dept(int id_dept) {
        this.id_dept = id_dept;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public Emp(int id, int id_dept, String nom, String prenom, double salaire) {
        this.id = id;
        this.id_dept = id_dept;
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
    }
    
    @Url(path="/getAll")
    public double getAll(){
        return this.getSalaire()+this.getId();
    }
}
